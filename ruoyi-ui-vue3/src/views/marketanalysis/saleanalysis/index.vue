<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="网点 " prop="branch">
        <el-input
          v-model="queryParams.branch"
          placeholder="请输入网点 "
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
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['marketanalysis:saleanalysis:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['marketanalysis:saleanalysis:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['marketanalysis:saleanalysis:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['marketanalysis:saleanalysis:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
      <el-button type="info" plain icon="Upload" @click="handleImport">导入</el-button>
      </el-col>
      <input ref="importRef" type="file" hidden accept=".xlsx, .xls" @change="handleFileChange" />
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="saleanalysisList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="网点 " align="center" prop="branch" />
      <el-table-column label="合同号" align="center" prop="contractNumber" />
      <el-table-column label="订单号" align="center" prop="orderNumber" />
      <el-table-column label="接单日期" align="center" prop="orderAcceptanceDate" />
      <el-table-column label="车型" align="center" prop="vehicleType" />
      <el-table-column label="数量" align="center" prop="number" />
      <el-table-column label="阀片数量" align="center" prop="valveplateNumber" />
      <el-table-column label="货叉" align="center" prop="goodsFork" />
      <el-table-column label="门架" align="center" prop="gantry" />
      <el-table-column label="空滤" align="center" prop="airFilter" />
      <el-table-column label="属具" align="center" prop="accessory" />
      <el-table-column label="轮胎" align="center" prop="tyre" />
      <el-table-column label="配置" align="center" prop="configuration" />
      <el-table-column label="车号" align="center" prop="carNumber" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="交货形式" align="center" prop="deliveryForm" />
      <el-table-column label="交货地点" align="center" prop="deliveryLocation" />
      <el-table-column label="联系人" align="center" prop="contactPerson" />
      <el-table-column label="电话" align="center" prop="phoneNumber" />
      <el-table-column label="订单系统交货期" align="center" prop="deliveryTime" />
      <el-table-column label="计划发车日期" align="center" prop="planDepartureDate" />
      <el-table-column label="实际发车日期" align="center" prop="actualDepartureDate" />
      <el-table-column label="系统交货期" align="center" prop="systemDeliveryTime" />
      <el-table-column label="交货单号" align="center" prop="deliveryOrderNumber" />
      <el-table-column label="技术准备完成时间" align="center" prop="completionTime" />
      <el-table-column label="兑现率" align="center" prop="redemptionRate" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:saleanalysis:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:saleanalysis:remove']">删除</el-button>
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

    <!-- 添加或修改销售分析对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="saleanalysisRef" :model="form" :rules="rules" label-width="130px">
        <el-form-item label="网点 " prop="branch">
          <el-input v-model="form.branch" placeholder="请输入网点 " />
        </el-form-item>
        <el-form-item label="合同号" prop="contractNumber">
          <el-input v-model="form.contractNumber" placeholder="请输入合同号" />
        </el-form-item>
        <el-form-item label="订单号" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="接单日期" prop="orderAcceptanceDate">
          <el-date-picker clearable
            v-model="form.orderAcceptanceDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择接单日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车型" prop="vehicleType">
          <el-input v-model="form.vehicleType" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="阀片数量" prop="valveplateNumber">
          <el-input v-model="form.valveplateNumber" placeholder="请输入阀片数量" />
        </el-form-item>
        <el-form-item label="货叉" prop="goodsFork">
          <el-input v-model="form.goodsFork" placeholder="请输入货叉" />
        </el-form-item>
        <el-form-item label="门架" prop="gantry">
          <el-input v-model="form.gantry" placeholder="请输入门架" />
        </el-form-item>
        <el-form-item label="空滤" prop="airFilter">
          <el-input v-model="form.airFilter" placeholder="请输入空滤" />
        </el-form-item>
        <el-form-item label="属具" prop="accessory">
          <el-input v-model="form.accessory" placeholder="请输入属具" />
        </el-form-item>
        <el-form-item label="轮胎" prop="tyre">
          <el-input v-model="form.tyre" placeholder="请输入轮胎" />
        </el-form-item>
        <el-form-item label="配置" prop="configuration">
          <el-input v-model="form.configuration"  placeholder="请输入配置" />
        </el-form-item>
        <el-form-item label="车号" prop="carNumber">
          <el-input v-model="form.carNumber" placeholder="请输入车号" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="交货形式" prop="deliveryForm">
          <el-input v-model="form.deliveryForm" placeholder="请输入交货形式" />
        </el-form-item>
        <el-form-item label="交货地点" prop="deliveryLocation">
          <el-input v-model="form.deliveryLocation" placeholder="请输入交货地点" />
        </el-form-item>
        <el-form-item label="联系人" prop="contactPerson">
          <el-input v-model="form.contactPerson" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="电话" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="订单系统交货期" prop="deliveryTime">
          <el-date-picker clearable
            v-model="form.deliveryTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择订单系统交货期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="计划发车日期" prop="planDepartureDate">
          <el-date-picker clearable
            v-model="form.planDepartureDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择计划发车日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际发车日期" prop="actualDepartureDate">
          <el-date-picker clearable
            v-model="form.actualDepartureDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择实际发车日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="系统交货期" prop="systemDeliveryTime">
          <el-date-picker clearable
            v-model="form.systemDeliveryTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择系统交货期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交货单号" prop="deliveryOrderNumber">
          <el-input v-model="form.deliveryOrderNumber" placeholder="请输入交货单号" />
        </el-form-item>
        <el-form-item label="技术准备完成时间" prop="completionTime">
          <el-date-picker clearable
            v-model="form.completionTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择技术准备完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="兑现率" prop="redemptionRate">
          <el-input v-model="form.redemptionRate" placeholder="请输入兑现率" />
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

<script setup name="Saleanalysis">
import { listSaleanalysis, getSaleanalysis, delSaleanalysis, addSaleanalysis, importSaleanalysis,checkDataExists } from "@/api/marketanalysis/saleanalysis/saleanalysis";


const { proxy } = getCurrentInstance();

const saleanalysisList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([])
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    branch: null,
    vehicleType: null,
  },
  rules: {
    branch: [
      { required: true, message: "网点不能为空", trigger: "blur" }
    ],
    vehicleType: [
      { required: true, message: "车型不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);
// 导入相关状态和方法
const importRef = ref(null);
const updateSupport = ref(false);

// 导入按钮操作
function handleImport() {
  importRef.value.click();
}

// 文件选择处理（修改后的逻辑）
async function handleFileChange(e) {
  const files = e.target.files;
  if (!files.length) return;

  try {
    loading.value = true;
    // 先检查数据是否存在
    const res = await checkDataExists();
    // 明确处理布尔值结果
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
      // 不存在数据时直接导入，无需覆盖
      updateSupport.value = false;
      uploadFile(files[0]);
    }
  } catch (error) {
    proxy.$modal.msgError("数据检查失败：" + error.message);
  } finally {
    loading.value = false;
  }
}

// 执行上传（确保正确传递参数）
async function uploadFile(file) {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('updateSupport', updateSupport.value); // 明确传递布尔值

  try {
    loading.value = true;
    await importSaleanalysis(formData);
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
/** 查询销售分析列表 */
function getList() {
  loading.value = true;
  listSaleanalysis(queryParams.value).then(response => {
    saleanalysisList.value = response.rows;
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
    branch: null,
    contractNumber: null,
    orderNumber: null,
    orderAcceptanceDate: null,
    vehicleType: null,
    number: null,
    valveplateNumber: null,
    goodsFork: null,
    gantry: null,
    airFilter: null,
    accessory: null,
    tyre: null,
    configuration: null,
    carNumber: null,
    remark: null,
    deliveryForm: null,
    deliveryLocation: null,
    contactPerson: null,
    phoneNumber: null,
    deliveryTime: null,
    planDepartureDate: null,
    actualDepartureDate: null,
    systemDeliveryTime: null,
    deliveryOrderNumber: null,
    completionTime: null,
    redemptionRate: null,
    id: null
  };
  proxy.resetForm("saleanalysisRef");
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
  title.value = "添加销售分析";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getSaleanalysis(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改销售分析";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["saleanalysisRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSaleanalysis(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSaleanalysis(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除销售分析编号为"' + _ids + '"的数据项？').then(function() {
    return delSaleanalysis(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/saleanalysis/export', {
    ...queryParams.value
  }, `销售台账.xlsx`)
}

getList();
</script>