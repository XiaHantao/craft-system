<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="系列" prop="series">
        <el-input
          v-model="queryParams.series"
          placeholder="请输入系列"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="吨位" prop="tonnage">
        <el-input
          v-model="queryParams.tonnage"
          placeholder="请输入吨位"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阀片数量" prop="numberOfValvePlates">
        <el-input
          v-model="queryParams.numberOfValvePlates"
          placeholder="请输入阀片数量"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电瓶容量" prop="batteryCapacity">
        <el-input
          v-model="queryParams.batteryCapacity"
          placeholder="请输入电瓶容量"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="轮胎规格" prop="tireSpecifications">
        <el-input
          v-model="queryParams.tireSpecifications"
          placeholder="请输入轮胎规格"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务支持人员" prop="businessSupportPersonnel">
        <el-input
          v-model="queryParams.businessSupportPersonnel"
          placeholder="请输入业务支持人员"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开发类别" prop="developmentCategory">
        <el-input
          v-model="queryParams.developmentCategory"
          placeholder="请输入开发类别"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上市时间" prop="listingTime">
        <el-date-picker clearable
          v-model="queryParams.listingTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择上市时间">
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
          v-hasPermi="['process:modelTable:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['process:modelTable:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['process:modelTable:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['process:modelTable:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="modelTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系列" align="center" prop="series" />
      <el-table-column label="吨位" align="center" prop="tonnage" />
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="发动机/主要配置" align="center" prop="mainConfiguration" />
      <el-table-column label="阀片数量" align="center" prop="numberOfValvePlates" />
      <el-table-column label="电瓶容量" align="center" prop="batteryCapacity" />
      <el-table-column label="轮胎规格" align="center" prop="tireSpecifications" />
      <el-table-column label="业务支持人员" align="center" prop="businessSupportPersonnel" />
      <el-table-column label="开发类别" align="center" prop="developmentCategory" />
      <el-table-column label="上市时间" align="center" prop="listingTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.listingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:modelTable:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:modelTable:remove']">删除</el-button>
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

    <!-- 添加或修改车型对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="modelTableRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="系列" prop="series">
          <el-input v-model="form.series" placeholder="请输入系列" />
        </el-form-item>
        <el-form-item label="吨位" prop="tonnage">
          <el-input v-model="form.tonnage" placeholder="请输入吨位" />
        </el-form-item>
        <el-form-item label="车型" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="发动机/主要配置" prop="mainConfiguration">
          <el-input v-model="form.mainConfiguration" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="阀片数量" prop="numberOfValvePlates">
          <el-input v-model="form.numberOfValvePlates" placeholder="请输入阀片数量" />
        </el-form-item>
        <el-form-item label="电瓶容量" prop="batteryCapacity">
          <el-input v-model="form.batteryCapacity" placeholder="请输入电瓶容量" />
        </el-form-item>
        <el-form-item label="轮胎规格" prop="tireSpecifications">
          <el-input v-model="form.tireSpecifications" placeholder="请输入轮胎规格" />
        </el-form-item>
        <el-form-item label="业务支持人员" prop="businessSupportPersonnel">
          <el-input v-model="form.businessSupportPersonnel" placeholder="请输入业务支持人员" />
        </el-form-item>
        <el-form-item label="开发类别" prop="developmentCategory">
          <el-input v-model="form.developmentCategory" placeholder="请输入开发类别" />
        </el-form-item>
        <el-form-item label="上市时间" prop="listingTime">
          <el-date-picker clearable
            v-model="form.listingTime"
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

<script setup name="ModelTable">
import { listModelTable, getModelTable, delModelTable, addModelTable, updateModelTable } from "@/api/process/modelTable";

const { proxy } = getCurrentInstance();

const modelTableList = ref([]);
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
    series: null,
    tonnage: null,
    vehicleModel: null,
    mainConfiguration: null,
    numberOfValvePlates: null,
    batteryCapacity: null,
    tireSpecifications: null,
    businessSupportPersonnel: null,
    developmentCategory: null,
    listingTime: null
  },
  rules: {
    series: [
      { required: true, message: "系列不能为空", trigger: "blur" }
    ],
    tonnage: [
      { required: true, message: "吨位不能为空", trigger: "blur" }
    ],
    vehicleModel: [
      { required: true, message: "车型不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询车型列表 */
function getList() {
  loading.value = true;
  listModelTable(queryParams.value).then(response => {
    modelTableList.value = response.rows;
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
    series: null,
    tonnage: null,
    vehicleModel: null,
    mainConfiguration: null,
    numberOfValvePlates: null,
    batteryCapacity: null,
    tireSpecifications: null,
    businessSupportPersonnel: null,
    developmentCategory: null,
    listingTime: null
  };
  proxy.resetForm("modelTableRef");
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
  title.value = "添加车型";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getModelTable(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改车型";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["modelTableRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateModelTable(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addModelTable(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除车型编号为"' + _ids + '"的数据项？').then(function() {
    return delModelTable(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/modelTable/export', {
    ...queryParams.value
  }, `modelTable_${new Date().getTime()}.xlsx`)
}

getList();
</script>
