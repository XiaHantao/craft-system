<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="版本号" prop="versionNumber">
        <el-input
          v-model="queryParams.versionNumber"
          placeholder="请输入版本号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件名" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入文件名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改人" prop="updatePerson">
        <el-input
          v-model="queryParams.updatePerson"
          placeholder="请输入修改人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为新版本文件" prop="newFlag">
        <el-input
          v-model="queryParams.newFlag"
          placeholder="请输入是否为新版本文件"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="新版本文件id" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入新版本文件id"
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
          v-hasPermi="['process:ProcessOptimizationAndImprovement:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['process:ProcessOptimizationAndImprovement:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['process:ProcessOptimizationAndImprovement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['process:ProcessOptimizationAndImprovement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ProcessOptimizationAndImprovementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="版本号" align="center" prop="versionNumber" />
      <el-table-column label="文件名" align="center" prop="fileName" />
      <el-table-column label="文件路径" align="center" prop="filePath" />
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改内容" align="center" prop="updateContent" />
      <el-table-column label="修改人" align="center" prop="updatePerson" />
      <el-table-column label="是否为新版本文件" align="center" prop="newFlag" />
      <el-table-column label="新版本文件id" align="center" prop="parentId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:ProcessOptimizationAndImprovement:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:ProcessOptimizationAndImprovement:remove']">删除</el-button>
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

    <!-- 添加或修改工艺优化与改进对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="ProcessOptimizationAndImprovementRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="版本号" prop="versionNumber">
          <el-input v-model="form.versionNumber" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="文件名" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名" />
        </el-form-item>
        <el-form-item label="文件路径" prop="filePath">
          <file-upload v-model="form.filePath"/>
        </el-form-item>
        <el-form-item label="修改内容">
          <el-input v-model="form.updateContent" placeholder="请输入修改内容"/>
        </el-form-item>
        <el-form-item label="修改人" prop="updatePerson">
          <el-input v-model="form.updatePerson" placeholder="请输入修改人" />
        </el-form-item>
        <el-form-item label="是否为新版本文件" prop="newFlag">
          <el-input v-model="form.newFlag" placeholder="请输入是否为新版本文件" />
        </el-form-item>
        <el-form-item label="新版本文件id" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入新版本文件id" />
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

<script setup name="ProcessOptimizationAndImprovement">
import { listProcessOptimizationAndImprovement, getProcessOptimizationAndImprovement, delProcessOptimizationAndImprovement, addProcessOptimizationAndImprovement, updateProcessOptimizationAndImprovement } from "@/api/process/ProcessOptimizationAndImprovement";

const { proxy } = getCurrentInstance();

const ProcessOptimizationAndImprovementList = ref([]);
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
    versionNumber: null,
    fileName: null,
    filePath: null,
    updateTime: null,
    updateContent: null,
    updatePerson: null,
    newFlag: null,
    parentId: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询工艺优化与改进列表 */
function getList() {
  loading.value = true;
  listProcessOptimizationAndImprovement(queryParams.value).then(response => {
    ProcessOptimizationAndImprovementList.value = response.rows;
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
    versionNumber: null,
    fileName: null,
    filePath: null,
    updateTime: null,
    updateContent: null,
    updatePerson: null,
    newFlag: null,
    parentId: null
  };
  proxy.resetForm("ProcessOptimizationAndImprovementRef");
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
  title.value = "添加工艺优化与改进";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcessOptimizationAndImprovement(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改工艺优化与改进";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ProcessOptimizationAndImprovementRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProcessOptimizationAndImprovement(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessOptimizationAndImprovement(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除工艺优化与改进编号为"' + _ids + '"的数据项？').then(function() {
    return delProcessOptimizationAndImprovement(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/ProcessOptimizationAndImprovement/export', {
    ...queryParams.value
  }, `ProcessOptimizationAndImprovement_${new Date().getTime()}.xlsx`)
}

getList();
</script>
