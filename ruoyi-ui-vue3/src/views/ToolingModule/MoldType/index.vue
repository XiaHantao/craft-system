<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="序号" prop="serialNumber">
        <el-input
          v-model="queryParams.serialNumber"
          placeholder="请输入序号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类别描述" prop="moldDescribe">
        <el-input
          v-model="queryParams.moldDescribe"
          placeholder="请输入类别描述"
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
          v-hasPermi="['ToolingModule:MoldType:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ToolingModule:MoldType:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ToolingModule:MoldType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['ToolingModule:MoldType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="MoldTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="序号" align="center">
        <template #default="{ $index }">
          <span>{{ ($index + 1) + (queryParams.pageNum - 1) * queryParams.pageSize }}</span> <!-- 根据当前页计算序号 -->
        </template>
      </el-table-column>
<!--      <el-table-column label="序号" align="center" prop="serialNumber" />-->
      <el-table-column label="类别" align="center" prop="moldType" />
      <el-table-column label="类别描述" align="center" prop="moldDescribe" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['ToolingModule:MoldType:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['ToolingModule:MoldType:remove']">删除</el-button>
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

    <!-- 添加或修改工装类别字典对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="MoldTypeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类别描述" prop="moldType">
          <el-input v-model="form.moldType" placeholder="请输入类别名称" />
        </el-form-item>
        <el-form-item label="类别描述" prop="moldDescribe">
          <el-input v-model="form.moldDescribe" placeholder="请输入类别描述" />
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

<script setup name="MoldType">
import { listMoldType, getMoldType, delMoldType, addMoldType, updateMoldType } from "@/api/ToolingModule/MoldType";

const { proxy } = getCurrentInstance();

const MoldTypeList = ref([]);
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
    serialNumber: null,
    moldType: null,
    moldDescribe: null
  },
  rules: {
    moldType: [
      { required: true, message: "类别不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询工装类别字典列表 */
function getList() {
  loading.value = true;
  listMoldType(queryParams.value).then(response => {
    MoldTypeList.value = response.rows;
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
    serialNumber: null,
    moldType: null,
    moldDescribe: null
  };
  proxy.resetForm("MoldTypeRef");
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
  title.value = "添加工装类别字典";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getMoldType(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改工装类别字典";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["MoldTypeRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMoldType(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMoldType(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除工装类别字典编号为"' + _ids + '"的数据项？').then(function() {
    return delMoldType(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('ToolingModule/MoldType/export', {
    ...queryParams.value
  }, `MoldType_${new Date().getTime()}.xlsx`)
}

getList();
</script>
