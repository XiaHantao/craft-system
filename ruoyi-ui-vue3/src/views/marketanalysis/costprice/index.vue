<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="制造商" prop="manufacturer">
        <el-input v-model="queryParams.manufacturer" placeholder="请输入制造商" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="车型" prop="vehicleType">
        <el-input v-model="queryParams.vehicleType" placeholder="请输入车型" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['marketanalysis:costprice:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['marketanalysis:costprice:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['marketanalysis:costprice:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['marketanalysis:costprice:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Upload" @click="handleImport">导入</el-button>
      </el-col>
      <input ref="importRef" type="file" hidden accept=".xlsx, .xls" @change="handleFileChange" />
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="costpriceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="制造商" align="center" prop="manufacturer" />
      <el-table-column label="车型" align="center" prop="vehicleType" />
      <el-table-column label="成本（元，不含税）" align="center" prop="cost" />
      <el-table-column label="车体（元，不含税）" align="center" prop="carBody" />
      <el-table-column label="起重（元，不含税）" align="center" prop="lifting" />
      <el-table-column label="其它（元，不含税）" align="center" prop="other" />
      <el-table-column label="盘锦定价（元，含税）" align="center" prop="panjinPricing" />
      <el-table-column label="收入（元，不含税）" align="center" prop="income" />
      <el-table-column label="毛利（元，不含税）" align="center" prop="grossMargin" />
      <el-table-column label="毛利率" align="center" prop="grossMarginRate" />
      <el-table-column label="股份定价（元，含税）" align="center" prop="stockPricing" />
      <el-table-column label="网点售价（元，含税）" align="center" prop="outletSellingPrice" />
      <el-table-column label="毛利（元，含税）" align="center" prop="grossProfitIncludingTax" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['marketanalysis:costprice:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['marketanalysis:costprice:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改成本售价表对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="costpriceRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="车型" prop="vehicleType">
          <el-input v-model="form.vehicleType" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="成本" prop="cost">
          <el-input v-model="form.cost" placeholder="请输入成本" />
        </el-form-item>
        <el-form-item label="车体" prop="carBody">
          <el-input v-model="form.carBody" placeholder="请输入车体" />
        </el-form-item>
        <el-form-item label="起重" prop="lifting">
          <el-input v-model="form.lifting" placeholder="请输入起重" />
        </el-form-item>
        <el-form-item label="其它" prop="other">
          <el-input v-model="form.other" placeholder="请输入其它" />
        </el-form-item>
        <el-form-item label="盘锦定价" prop="panjinPricing">
          <el-input v-model="form.panjinPricing" placeholder="请输入盘锦定价" />
        </el-form-item>
        <el-form-item label="收入" prop="income">
          <el-input v-model="form.income" placeholder="请输入收入" />
        </el-form-item>
        <el-form-item label="毛利" prop="grossMargin">
          <el-input v-model="form.grossMargin" placeholder="请输入毛利" />
        </el-form-item>
        <el-form-item label="毛利率" prop="grossMarginRate">
          <el-input v-model="form.grossMarginRate" placeholder="请输入毛利率" />
        </el-form-item>
        <el-form-item label="股份定价" prop="stockPricing">
          <el-input v-model="form.stockPricing" placeholder="请输入股份定价" />
        </el-form-item>
        <el-form-item label="网点售价" prop="outletSellingPrice">
          <el-input v-model="form.outletSellingPrice" placeholder="请输入网点售价" />
        </el-form-item>
        <el-form-item label="毛利（含税）" prop="grossProfitIncludingTax">
          <el-input v-model="form.grossProfitIncludingTax" placeholder="请输入毛利" />
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

<script setup name="Costprice">
import { listCostprice, getCostprice, delCostprice, addCostprice, updateCostprice,importCostprice, checkCostpriceDataExists} from "@/api/marketanalysis/costprice/costprice";

const { proxy } = getCurrentInstance();

const costpriceList = ref([]);
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
    manufacturer: null,
    vehicleType: null,
  },
  rules: {
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
    const res = await checkCostpriceDataExists();
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
    await importCostprice(formData);
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

/** 查询成本售价表列表 */
function getList() {
  loading.value = true;
  listCostprice(queryParams.value).then(response => {
    costpriceList.value = response.rows;
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
    manufacturer: null,
    vehicleType: null,
    cost: null,
    carBody: null,
    lifting: null,
    other: null,
    panjinPricing: null,
    income: null,
    grossMargin: null,
    grossMarginRate: null,
    stockPricing: null,
    outletSellingPrice: null,
    grossProfitIncludingTax: null
  };
  proxy.resetForm("costpriceRef");
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
  title.value = "添加成本售价表";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCostprice(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改成本售价表";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["costpriceRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCostprice(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCostprice(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除成本售价表编号为"' + _ids + '"的数据项？').then(function() {
    return delCostprice(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/costprice/export', {
    ...queryParams.value
  }, `costprice_${new Date().getTime()}.xlsx`)
}

getList();
</script>
