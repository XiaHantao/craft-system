<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工装编号" prop="toolNumber">
        <el-input
          v-model="queryParams.toolNumber"
          placeholder="请输入工装编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工装名称" prop="toolName">
        <el-input
          v-model="queryParams.toolName"
          placeholder="请输入工装名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品编号" prop="productId">
        <el-input
          v-model="queryParams.productId"
          placeholder="请输入产品编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用班组" prop="useTeam">
        <el-input
          v-model="queryParams.useTeam"
          placeholder="请输入使用班组"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeUploadTime"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['process:specializedProcessEquipment:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['process:specializedProcessEquipment:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['process:specializedProcessEquipment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['process:specializedProcessEquipment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="specializedProcessEquipmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="工装编号" align="center" prop="toolNumber" />
      <el-table-column label="工装名称" align="center" prop="toolName" />
      <el-table-column label="工装类型" align="center" prop="toolType" />
      <el-table-column label="产品编号" align="center" prop="productId" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="使用班组" align="center" prop="useTeam" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:specializedProcessEquipment:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:specializedProcessEquipment:remove']">删除</el-button>
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

    <!-- 添加或修改专用工艺装备清单对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="specializedProcessEquipmentRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工装编号" prop="toolNumber">
          <el-input v-model="form.toolNumber" placeholder="请输入工装编号" />
        </el-form-item>
        <el-form-item label="工装名称" prop="toolName">
          <el-input v-model="form.toolName" placeholder="请输入工装名称" />
        </el-form-item>
        <el-form-item label="产品编号" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入产品编号" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="使用班组" prop="useTeam">
          <el-input v-model="form.useTeam" placeholder="请输入使用班组" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="上传时间" prop="uploadTime">
          <el-date-picker clearable
            v-model="form.uploadTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择上传时间">
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

<script setup name="SpecializedProcessEquipment">
import { listSpecializedProcessEquipment, getSpecializedProcessEquipment, delSpecializedProcessEquipment, addSpecializedProcessEquipment, updateSpecializedProcessEquipment } from "@/api/process/specializedProcessEquipment";

const { proxy } = getCurrentInstance();

const specializedProcessEquipmentList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const daterangeUploadTime = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    toolNumber: null,
    toolName: null,
    toolType: null,
    productId: null,
    productName: null,
    useTeam: null,
    uploadTime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询专用工艺装备清单列表 */
function getList() {
  loading.value = true;
  queryParams.value.params = {};
  if (null != daterangeUploadTime && '' != daterangeUploadTime) {
    queryParams.value.params["beginUploadTime"] = daterangeUploadTime.value[0];
    queryParams.value.params["endUploadTime"] = daterangeUploadTime.value[1];
  }
  listSpecializedProcessEquipment(queryParams.value).then(response => {
    specializedProcessEquipmentList.value = response.rows;
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
    toolNumber: null,
    toolName: null,
    toolType: null,
    productId: null,
    productName: null,
    useTeam: null,
    remark: null,
    uploadTime: null
  };
  proxy.resetForm("specializedProcessEquipmentRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeUploadTime.value = [];
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
  title.value = "添加专用工艺装备清单";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getSpecializedProcessEquipment(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改专用工艺装备清单";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["specializedProcessEquipmentRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSpecializedProcessEquipment(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSpecializedProcessEquipment(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除专用工艺装备清单编号为"' + _ids + '"的数据项？').then(function() {
    return delSpecializedProcessEquipment(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/specializedProcessEquipment/export', {
    ...queryParams.value
  }, `specializedProcessEquipment_${new Date().getTime()}.xlsx`)
}

getList();
</script>
