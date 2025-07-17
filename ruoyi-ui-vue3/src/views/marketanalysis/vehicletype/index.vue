<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车类" prop="vehicleCategory">
        <el-input
          v-model="queryParams.vehicleCategory"
          placeholder="请输入车类"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车型" prop="vehicleType">
        <el-input
          v-model="queryParams.vehicleType"
          placeholder="请输入车型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列" prop="series">
        <el-input
          v-model="queryParams.series"
          placeholder="请输入系列"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['marketanalysis:vehicletype:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:vehicletype:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:vehicletype:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:vehicletype:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Upload" @click="handleImport">导入</el-button>
      </el-col>
      <input ref="importRef" type="file" hidden accept=".xlsx, .xls" @change="handleFileChange" />
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="vehicletypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="车类" align="center" prop="vehicleCategory" />
      <el-table-column label="车型" align="center" prop="vehicleType" />
      <el-table-column label="系列" align="center" prop="series" />
      <el-table-column label="吨位" align="center" prop="tonnage" />
      <el-table-column label="发动机型号" align="center" prop="engineType" />
      <el-table-column label="发动机电池容量" align="center" prop="enginebatteryCapacity" />
      <el-table-column label="额定功率" align="center" prop="powerRating" />
      <el-table-column label="扭矩转速" align="center" prop="torqueRotationalspeed" />
      <el-table-column label="燃油喷射系统型式" align="center" prop="fuelInjectionSystem" />
      <el-table-column label="排放标准" align="center" prop="effluentStandard" />
      <el-table-column label="阀片数量" align="center" prop="valveplateNumber" />
      <el-table-column label="电瓶容量" align="center" prop="batteryCapacity" />
      <el-table-column label="轮胎规格" align="center" prop="tireSpecification" />
      <el-table-column label="业务支持人员" align="center" prop="businessPersonnel" />
      <el-table-column label="开发类别" align="center" prop="developmentClass" />
      <el-table-column label="上市时间" align="center" prop="releaseDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.releaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:vehicletype:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:vehicletype:remove']">删除</el-button>
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

    <!-- 添加或修改车型分类对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="vehicletypeRef" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="车类" prop="vehicleCategory">
          <el-input v-model="form.vehicleCategory" placeholder="请输入车类" />
        </el-form-item> -->
        <el-form-item label="车类" prop="vehicleCategory">
          <el-select v-model="form.vehicleCategory" placeholder="请选择车类" >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
          </el-form-item>
        <el-form-item label="车型" prop="vehicleType">
          <el-input v-model="form.vehicleType" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="系列" prop="series">
          <el-input v-model="form.series" placeholder="请输入系列" />
        </el-form-item>
        <el-form-item label="吨位" prop="tonnage">
          <el-input v-model="form.tonnage" placeholder="请输入吨位" />
        </el-form-item>
        <el-form-item label="发动机电池容量" prop="enginebatteryCapacity">
          <el-input v-model="form.enginebatteryCapacity" placeholder="请输入发动机电池容量" />
        </el-form-item>
        <el-form-item label="额定功率" prop="powerRating">
          <el-input v-model="form.powerRating" placeholder="请输入额定功率" />
        </el-form-item>
        <el-form-item label="扭矩转速" prop="torqueRotationalspeed">
          <el-input v-model="form.torqueRotationalspeed" placeholder="请输入扭矩转速" />
        </el-form-item>
        <el-form-item label="燃油喷射系统型式" prop="fuelInjectionSystem">
          <el-input v-model="form.fuelInjectionSystem" placeholder="请输入燃油喷射系统型式" />
        </el-form-item>
        <el-form-item label="排放标准" prop="effluentStandard">
          <el-input v-model="form.effluentStandard" placeholder="请输入排放标准" />
        </el-form-item>
        <el-form-item label="阀片数量" prop="valveplateNumber">
          <el-input v-model="form.valveplateNumber" placeholder="请输入阀片数量" />
        </el-form-item>
        <el-form-item label="电瓶容量" prop="batteryCapacity">
          <el-input v-model="form.batteryCapacity" placeholder="请输入电瓶容量" />
        </el-form-item>
        <el-form-item label="轮胎规格" prop="tireSpecification">
          <el-input v-model="form.tireSpecification" placeholder="请输入轮胎规格" />
        </el-form-item>
        <el-form-item label="业务支持人员" prop="businessPersonnel">
          <el-input v-model="form.businessPersonnel" placeholder="请输入业务支持人员" />
        </el-form-item>
        <el-form-item label="开发类别" prop="developmentClass">
          <el-input v-model="form.developmentClass" placeholder="请输入开发类别" />
        </el-form-item>
        <el-form-item label="上市时间" prop="releaseDate">
          <el-date-picker clearable
            v-model="form.releaseDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择上市时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Vehicletype">
import { listVehicletype, getVehicletype, delVehicletype, addVehicletype, updateVehicletype, importVehicleCategory, checkDataExists  } from "@/api/marketanalysis/vehicletype/vehicletype";
// 添加导入相关引用
const importRef = ref(null);
const updateSupport = ref(false);

// 导入按钮操作
function handleImport() {
  importRef.value.click();
}

// 文件选择处理
async function handleFileChange(e) {
  const files = e.target.files;
  if (!files.length) return;

  try {
    loading.value = true;
    // 先检查数据是否存在
    const res = await checkDataExists();
    const dataExists = res.data;
    if (dataExists) { 
      // 存在数据时询问是否覆盖
      proxy.$modal.confirm('检测到已有数据，是否覆盖？').then(() => {
        updateSupport.value = true;
        uploadFile(files[0]);
      }).catch(() => {
        updateSupport.value = false;
        uploadFile(files[0]);
      });
    } else { 
      updateSupport.value = false;
      uploadFile(files[0]);
    }
  } catch (error) {
    proxy.$modal.msgError("数据检查失败：" + error.message);
  } finally {
    loading.value = false;
  }
}

// 执行上传
async function uploadFile(file) {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('updateSupport', updateSupport.value);

  try {
    loading.value = true;
    await importVehicleCategory(formData);
    proxy.$modal.msgSuccess("导入成功");
    getList();
  } catch (e) {
    proxy.$modal.msgError("导入失败：" + (e.message || "请检查文件格式和数据有效性"));
  } finally {
    loading.value = false;
    importRef.value.value = ''; // 清空文件选择
    updateSupport.value = false; // 重置覆盖状态
  }
}
const { proxy } = getCurrentInstance();

const vehicletypeList = ref([]);
const open = ref(false);
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
    vehicleCategory: null,
    vehicleType: null,
    series: null,
  },
  rules: {
    vehicleCategory: [
      { required: true, message: "车类不能为空", trigger: "blur" }
    ],
    vehicleType: [
      { required: true, message: "车型不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

// 添加选项数据
const options = ref([
  { value: 'I类车', label: 'I类车' },
  { value: 'II类车', label: 'II类车' },
  { value: 'III类车', label: 'III类车' },
  { value: 'V类车', label: 'V类车' },
  { value: 'VII类车', label: 'VII类车' }
]);
/** 查询车型分类列表 */
function getList() {
  loading.value = true;
  listVehicletype(queryParams.value).then(response => {
    vehicletypeList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    vehicleCategory: null,
    vehicleType: null,
    series: null,
    tonnage: null,
    engineType: null,
    enginebatteryCapacity: null,
    powerRating: null,
    torqueRotationalspeed: null,
    fuelInjectionSystem: null,
    effluentStandard: null,
    valveplateNumber: null,
    batteryCapacity: null,
    tireSpecification: null,
    businessPersonnel: null,
    developmentClass: null,
    releaseDate: null
  };
  proxy.resetForm("vehicletypeRef");
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
  title.value = "添加车型分类";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getVehicletype(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改车型分类";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["vehicletypeRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateVehicletype(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addVehicletype(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除车型分类编号为"' + _ids + '"的数据项？').then(function() {
    return delVehicletype(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/vehicletype/export', {
    ...queryParams.value
  }, `车型分类表.xlsx`)
}

getList();
</script>
