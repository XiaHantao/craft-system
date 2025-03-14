<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传人" prop="uploadPerson">
        <el-input
          v-model="queryParams.uploadPerson"
          placeholder="请输入上传人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" prop="uploadTime">
        <el-date-picker clearable
          v-model="queryParams.uploadTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['process:processQuotaTable:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['process:processQuotaTable:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['process:processQuotaTable:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['process:processQuotaTable:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processQuotaTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="上传人" align="center" prop="uploadPerson" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleAddQuota(scope.row)">上传定额</el-button>
          <el-button link type="primary" icon="View" @click="handleShowQuota(scope.row)">详情</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:processQuotaTable:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:processQuotaTable:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改工艺定额对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="processQuotaTableRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车型" prop="vehicleModel">
          <el-select
              v-model="form.vehicleModel"
              placeholder="请选择车型"
              clearable
              filterable
              @keyup.enter="handleQuery"
          >
            <el-option
                v-for="model in modelList"
                :key="model.vehicleModel"
                :label="model.vehicleModel"
                :value="model.vehicleModel"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 上传工艺定额对话框 -->
    <el-dialog :title="title" v-model="openQuota" width="1000px" append-to-body>
      <el-steps :active="activeStep" finish-status="success" style="margin-bottom: 20px;">
        <el-step v-for="(step, index) in steps" :key="index" :title="step.title"></el-step>
      </el-steps>
      <el-form ref="processQuotaTableRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件" prop="filePath">
          <file-upload v-model="form.filePath"/>
        </el-form-item>
      </el-form>
      <div v-for="(quotaForm, index) in quotaValueForms" :key="index" style="margin-bottom: 20px;">
        <el-form :ref="el => { if (el) quotaValueRefs[index] = el }" :model="quotaForm" :rules="rules" label-width="80px">
          <el-form-item label="定额" prop="quotaName">
            <el-select
                v-model="quotaForm.quotaName"
                placeholder="请选择定额"
                clearable
                filterable
                @keyup.enter="handleQuery"
            >
              <el-option
                  v-for="quota in quotaInformmationList"
                  :key="quota.quotaName"
                  :label="quota.quotaName"
                  :value="quota.quotaName"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="定额值" prop="quotaValue">
            <el-input
                v-model="quotaForm.quotaValue"
                placeholder="请输入定额值"
                clearable
            />
          </el-form-item>
          <el-button type="danger" @click="removeQuotaGroup(index)">删除</el-button>
        </el-form>
      </div>
      <el-button type="primary" @click="addQuotaGroup">添加定额组</el-button>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="prevStep" v-if="activeStep > 0">上一步</el-button>
          <el-button type="primary" @click="nextStep">下一步</el-button>
          <el-button type="primary" @click="quotaSubmitForm" v-if="activeStep === steps.length - 1">确 定</el-button>
          <el-button @click="quotaCancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 查看工艺定额详情对话框 -->
    <el-dialog :title="title" v-model="openShowQuota" width="1000px" append-to-body>
      <div v-for="group in formList" :key="group.step" style="margin-bottom: 20px;">
        <h3>步骤 {{ group.step }}</h3>
        <div v-for="(form, index) in group.QuotaTableForms" :key="index" style="margin-bottom: 10px;">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="车型">{{ form.vehicleModel }}</el-descriptions-item>
            <el-descriptions-item label="文件">
              <el-link type="primary" @click="downloadFile(form.filePath)">{{ getFileName(form.filePath) }}</el-link>
            </el-descriptions-item>
            <!-- 动态生成定额名称和定额值 -->
            <el-descriptions-item v-for="quota in group.QuotaValueForms" :key="quota.quotaName" :label="quota.quotaName">
              {{ quota.quotaValue }}
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </div>

      <el-divider></el-divider>

      <!-- 显示每个定额项的总和 -->
      <div style="margin-top: 20px;">
        <h3 class="total-title">合计</h3>
        <el-descriptions :column="2" border>
          <el-descriptions-item v-for="(total, quotaName) in quotaTotals" :key="quotaName" :label="quotaName">
            {{ total }}
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-dialog>




  </div>
</template>

<script setup name="ProcessQuotaTable">
import {
  listProcessQuotaTable,
  getProcessQuotaTable,
  delProcessQuotaTable,
  addProcessQuotaTable,
  updateProcessQuotaTable,
  listRelatedProcessQuotaTable
} from "@/api/process/processQuotaTable";
import {getCurrentInstance} from "vue";
import {listModelTable} from "@/api/process/modelTable";
import {addProcessQuotaInformation, listProcessQuotaInformation} from "@/api/process/processQuotaInformation";
import {addProcessQuotaValue, delProcessQuotaValue, listProcessQuotaValue} from "@/api/process/processQuotaValue";
import {getUserProfile} from "@/api/system/user";

const { proxy } = getCurrentInstance();

const userInfoList = ref([]);  //用户信息
const quotaTotals = ref({});  // 存储每个定额的总和
const thisVehicleModel = ref(null);  // 当前车型
const quotaValueRefs = ref([]);
const quotaValueForms = ref([]);  // 存储每一步的每一组定额信息
const quotaValueList = ref([]); // 用于存储每个步骤的定额信息
const quotaInformmationList = ref([]); // 定额字典信息
const formList = ref([]); // 用于存储每个步骤的表单数据
const steps = ref([]);  // 步骤信息
const  activeStep = ref(0); // 当前激活的步骤

const modelList = ref([]); // 车型列表
const processQuotaTableList = ref([]);
const open = ref(false);
const openQuota = ref(false); // 上传定额对话框
const openShowQuota = ref(false); // 查看定额详情对话框
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const selectedVehicleModel = ref([]); //多选选中的车型
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  quotaValueForm: {
    vehicleModel: null,
    step: null,
    quotaName: null,
    quotaValue: null
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    step: null,
    vehicleModel: null,
    filePath: null,
    uploadPerson: null,
    uploadTime: null
  },
  rules: {
  },
});

const { queryParams, form, quotaValueForm, rules } = toRefs(data);

// 在组件挂载时添加初始步骤
onMounted(() => {
  steps.value.push({ title: '步骤 1' });
});

/** 查询工艺定额列表 */
function getList() {
  loading.value = true;
  listProcessQuotaTable(queryParams.value).then(response => {
    processQuotaTableList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });

  // 获取用户信息
  getUserProfile().then(response => {
    userInfoList.value = response.data;
  });

  // 获取定额字典信息
  listProcessQuotaInformation().then(response => {
    quotaInformmationList.value = response.rows;
  })
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 取消按钮
function quotaCancel() {
  steps.value = [{ title: '步骤 1' }]; // 重置步骤为初始状态
  activeStep.value = 0; // 重置当前激活的步骤
  formList.value = []; // 重置 formList
  openQuota.value = false;
  quotaValueReset();
}


// 表单重置
function reset() {
  form.value = {
    id: null,
    step: null,
    vehicleModel: null,
    filePath: null,
    uploadPerson: null,
    uploadTime: null
  };
  proxy.resetForm("processQuotaTableRef");
}

// 表单重置
function quotaValueReset() {
  quotaValueForms.value = [];
  quotaValueRefs.value = [];
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  selectedVehicleModel.value = selection.map(item => item.vehicleModel);  //多选选中的车型
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加工艺定额";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcessQuotaTable(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改工艺定额";
  });
}

/** 上传定额操作 */
function handleAddQuota(row) {
  reset();
  openQuota.value = true;
  title.value = "上传工艺定额";
  thisVehicleModel.value = row.vehicleModel;
}

/** 打开定额详情对话框 */
function handleShowQuota(row) {
  openShowQuota.value = true;
  title.value = "定额详情";

  // 获取当前车型关联的所有表单数据
  listRelatedProcessQuotaTable(row.vehicleModel).then(response => {
    const allForms = response.rows;

    // 获取定额值数据
    listProcessQuotaValue({ vehicleModel: row.vehicleModel }).then(response => {
      const quotaValueList = response.rows;

      // 按照步骤分组
      const groupedForms = {};
      allForms.forEach(form => {
        if (!groupedForms[form.step]) {
          groupedForms[form.step] = [];
        }
        groupedForms[form.step].push(form);
      });

      const groupedQuotaValues = {};
      quotaValueList.forEach(quotaValue => {
        if (!groupedQuotaValues[quotaValue.step]) {
          groupedQuotaValues[quotaValue.step] = [];
        }
        groupedQuotaValues[quotaValue.step].push(quotaValue);
      });

      // 将分组后的数据存储到 formList 中
      formList.value = Object.keys(groupedForms).map(step => ({
        step: step,
        QuotaTableForms: groupedForms[step],
        QuotaValueForms: groupedQuotaValues[step] || []
      }));

      // 计算每个定额项的总和
      const totals = {};
      formList.value.forEach(group => {
        group.QuotaValueForms.forEach(quotaForm => {
          const quotaName = quotaForm.quotaName;
          if (!totals[quotaName]) {
            totals[quotaName] = 0;
          }
          totals[quotaName] += parseFloat(quotaForm.quotaValue || 0);
        });
      });

      // 将 totals 存储在 quotaTotals 响应式变量中
      quotaTotals.value = totals;

      console.log("formList.value===>", formList.value);
      console.log("quotaTotals===>", quotaTotals.value);
    });
  });
}


/** 提交按钮 */
function submitForm() {
  proxy.$refs["processQuotaTableRef"].validate(valid => {
    if (valid) {
      form.value.uploadPerson = userInfoList.value.nickName;
      form.value.uploadTime = new Date();
      if (form.value.id != null) {
        updateProcessQuotaTable(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessQuotaTable(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

//定额信息提交按钮
function quotaSubmitForm() {

  // 更新step字段为当前步骤索引
  form.value.step = activeStep.value + 1;
  quotaValueForms.value.forEach((quotaForm, index) => {
    quotaForm.step = activeStep.value + 1;
  });
  // 保存当前步骤的表单数据到 formList 和 quotaValueList
  formList.value[activeStep.value] = { ...form.value };
  quotaValueList.value[activeStep.value] = [...quotaValueForms.value];

  // 将当前车型添加到 formList 和 quotaValueList 中
  formList.value.forEach(form => {
    form.vehicleModel = thisVehicleModel.value;
  });
  quotaValueList.value.forEach(stepForms => {
    stepForms.forEach(quotaForm => {
      quotaForm.vehicleModel = thisVehicleModel.value;
    });
  });

  proxy.$refs["processQuotaTableRef"].validate(valid => {
    if(valid) {
      formList.value.forEach(form => {
        console.log("form.value===>",form.value);
        addProcessQuotaTable(form).then(response => {
        });
      });

      // 提交每个步骤的定额组数据
      quotaValueList.value.forEach((stepForms) => {
        stepForms.forEach((quotaForm) => {
          addProcessQuotaValue(quotaForm).then(response => {
          });
        });
      });
      proxy.$modal.msgSuccess("上传成功");
      openQuota.value = false;
      quotaCancel();
      getList();
    }
  });

}

// /** 删除按钮操作 */
// function handleDelete(row) {
//   const _ids = row.id || ids.value;
//   proxy.$modal.confirm('是否确认删除工艺定额编号为"' + _ids + '"的数据项？').then(function() {
//     return delProcessQuotaTable(_ids);
//   }).then(() => {
//     getList();
//     proxy.$modal.msgSuccess("删除成功");
//   }).catch(() => {});
// }

/** 删除按钮操作 */
function handleDelete(row) {
  const vehicleModels = row.vehicleModel ? [row.vehicleModel] : selectedVehicleModel.value;
  proxy.$modal.confirm('是否确认删除车型为"' + vehicleModels.join(", ") + '"的数据项？').then(function() {
    return Promise.all([
      delProcessQuotaTable(vehicleModels),
      delProcessQuotaValue(vehicleModels)
    ]);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
    proxy.$modal.msgError("删除失败");
  });
}


/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/processQuotaTable/export', {
    ...queryParams.value
  }, `processQuotaTable_${new Date().getTime()}.xlsx`)
}

/** 查询车型列表 */
function getModelList() {
  listModelTable().then(response => {
    modelList.value = response.rows;
  });
}

// 添加定额组
function addQuotaGroup() {
  quotaValueForms.value.push({
    vehicleModel: null,
    step: null,
    quotaName: null,
    quotaValue: null
  });
}

// 删除定额组
function removeQuotaGroup(index) {
  quotaValueForms.value.splice(index, 1);
  quotaValueRefs.value.splice(index, 1);
}


// 下一步操作
function nextStep() {
  if (activeStep.value < steps.value.length - 1) {
    // 保存当前步骤的表单数据到 formList 和 quotaValueList
    formList.value[activeStep.value] = { ...form.value };
    quotaValueList.value[activeStep.value] = [...quotaValueForms.value];
    activeStep.value++;
    // 恢复下一步的表单数据
    form.value = { ...formList.value[activeStep.value] };
    quotaValueForms.value = [...quotaValueList.value[activeStep.value]];
  } else {
    // 增加新的步骤
    steps.value.push({ title: `步骤 ${steps.value.length + 1}` });
    // 更新step字段为当前步骤索引
    form.value.step = activeStep.value + 1;
    quotaValueForms.value.forEach((quotaForm, index) => {
      quotaForm.step = activeStep.value + 1;
    });
    // 保存当前步骤的表单数据到 formList 和 quotaValueList
    formList.value[activeStep.value] = { ...form.value };
    quotaValueList.value[activeStep.value] = [...quotaValueForms.value];
    // 重置 form 和 quotaValueForms
    reset();
    quotaValueReset();
    activeStep.value++;
  }
  console.log("formList.value===>", formList.value);
  console.log("quotaValueList.value===>", quotaValueList.value);
}

// 上一步操作
function prevStep() {
  if (activeStep.value > 0) {
    // 保存当前步骤的表单数据到 formList 和 quotaValueList
    formList.value[activeStep.value] = { ...form.value };
    quotaValueList.value[activeStep.value] = [...quotaValueForms.value];
    activeStep.value--;
    // 恢复上一步的表单数据
    form.value = { ...formList.value[activeStep.value] };
    quotaValueForms.value = [...quotaValueList.value[activeStep.value]];
  }
  console.log("formList.value===>", formList.value);
  console.log("quotaValueList.value===>", quotaValueList.value);
}

/** 获取文件名 */
function getFileName(name) {
  if (!name) return "";
  // 找到最后一个斜杠或反斜杠的位置
  const lastSlashIndex = Math.max(name.lastIndexOf('/'), name.lastIndexOf('\\'));
  if (lastSlashIndex === -1) {
    return name; // 如果没有找到斜杠或反斜杠，返回整个字符串
  }
  // 提取文件名部分
  const fileName = name.slice(lastSlashIndex + 1);
  // 分割文件名
  const parts = fileName.split('_');
  console.log("parts===>",parts);
  // 如果没有找到版本号部分，返回整个文件名
  return parts.length > 1 ? parts[0] : fileName;
}

/** 文件下载 */
function downloadFile(url) {
  fetch(url)
      .then(response => response.blob())
      .then(blob => {
        const downloadUrl = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadUrl;
        link.setAttribute('download', decodeURIComponent(url.split('/').pop())); // 解码文件名
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        window.URL.revokeObjectURL(downloadUrl);
      })
      .catch(error => console.error('Download error:', error));
}


getModelList();
getList();
</script>


<style scoped>.total-title {
  font-size: 1.5em; /* 加大字体 */
  font-weight: bold; /* 加粗字体 */
}
</style>