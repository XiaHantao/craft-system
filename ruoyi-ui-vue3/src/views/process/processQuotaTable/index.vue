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
        <el-step v-for="(step, index) in steps" :key="index" :title= "step.title"></el-step>
      </el-steps>
      <el-form ref="processQuotaTableRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件" prop="filePath">
          <file-upload v-model="form.filePath"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="prevStep" v-if="activeStep > 0">上一步</el-button>
          <el-button type="primary" @click="nextStep" >下一步</el-button>
          <el-button type="primary" @click="submitForm" v-if="activeStep === steps.length - 1">确 定</el-button>
          <el-button @click="quotaCancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup name="ProcessQuotaTable">
import { listProcessQuotaTable, getProcessQuotaTable, delProcessQuotaTable, addProcessQuotaTable, updateProcessQuotaTable } from "@/api/process/processQuotaTable";
import {getCurrentInstance} from "vue";
import {listModelTable} from "@/api/process/modelTable";

const { proxy } = getCurrentInstance();

const steps = ref([]);  // 步骤信息
const  activeStep = ref(0); // 当前激活的步骤
const modelList = ref([]); // 车型列表
const processQuotaTableList = ref([]);
const open = ref(false);
const openQuota = ref(false); // 上传定额对话框
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
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

const { queryParams, form, rules } = toRefs(data);

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
  openQuota.value = false;
  reset();
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
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["processQuotaTableRef"].validate(valid => {
    if (valid) {
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

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除工艺定额编号为"' + _ids + '"的数据项？').then(function() {
    return delProcessQuotaTable(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
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

// 下一步操作
function nextStep() {
  if (activeStep.value < steps.value.length - 1) {
    activeStep.value++;
  } else {
    // 增加新的步骤
    steps.value.push({ title: `步骤 ${steps.value.length + 1}` });
    activeStep.value++;
  }
}


// 上一步操作
function prevStep() {
  activeStep.value--;
}

getModelList();
getList();
</script>
