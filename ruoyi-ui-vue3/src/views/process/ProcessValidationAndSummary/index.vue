<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="验证报告名称" prop="verificationReportName">
        <el-input
          v-model="queryParams.verificationReportName"
          placeholder="请输入验证报告名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="改进清单名称" prop="improvementChecklistName">
        <el-input
          v-model="queryParams.improvementChecklistName"
          placeholder="请输入改进清单名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="改进报告名称" prop="improvementReportName">
        <el-input
          v-model="queryParams.improvementReportName"
          placeholder="请输入改进报告名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总结报告名称" prop="summaryReportName">
        <el-input
          v-model="queryParams.summaryReportName"
          placeholder="请输入总结报告名称"
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
          v-hasPermi="['process:ProcessValidationAndSummary:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['process:ProcessValidationAndSummary:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['process:ProcessValidationAndSummary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['process:ProcessValidationAndSummary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ProcessValidationAndSummaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="验证报告名称" align="center" prop="verificationReportName" />
      <el-table-column label="验证报告路径" align="center" prop="verificationReportPath" />
      <el-table-column label="改进清单名称" align="center" prop="improvementChecklistName" />
      <el-table-column label="改进清单路径" align="center" prop="improvementChecklistPath" />
      <el-table-column label="改进报告名称" align="center" prop="improvementReportName" />
      <el-table-column label="改进报告路径" align="center" prop="improvementReportPath" />
      <el-table-column label="改进状态" align="center" prop="improvementStatus" />
      <el-table-column label="总结报告名称" align="center" prop="summaryReportName" />
      <el-table-column label="总结报告路径" align="center" prop="summaryReportPath" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:ProcessValidationAndSummary:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:ProcessValidationAndSummary:remove']">删除</el-button>
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

    <!-- 添加或修改工艺验证与总结对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="ProcessValidationAndSummaryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="验证报告名称" prop="verificationReportName">
          <el-input v-model="form.verificationReportName" placeholder="请输入验证报告名称" />
        </el-form-item>
        <el-form-item label="验证报告路径" prop="verificationReportPath">
          <file-upload v-model="form.verificationReportPath"/>
        </el-form-item>
        <el-form-item label="改进清单名称" prop="improvementChecklistName">
          <el-input v-model="form.improvementChecklistName" placeholder="请输入改进清单名称" />
        </el-form-item>
        <el-form-item label="改进清单路径" prop="improvementChecklistPath">
          <file-upload v-model="form.improvementChecklistPath"/>
        </el-form-item>
        <el-form-item label="改进报告名称" prop="improvementReportName">
          <el-input v-model="form.improvementReportName" placeholder="请输入改进报告名称" />
        </el-form-item>
        <el-form-item label="改进报告路径" prop="improvementReportPath">
          <file-upload v-model="form.improvementReportPath"/>
        </el-form-item>
        <el-form-item label="总结报告名称" prop="summaryReportName">
          <el-input v-model="form.summaryReportName" placeholder="请输入总结报告名称" />
        </el-form-item>
        <el-form-item label="总结报告路径" prop="summaryReportPath">
          <file-upload v-model="form.summaryReportPath"/>
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

<script setup name="ProcessValidationAndSummary">
import { listProcessValidationAndSummary, getProcessValidationAndSummary, delProcessValidationAndSummary, addProcessValidationAndSummary, updateProcessValidationAndSummary } from "@/api/process/ProcessValidationAndSummary";

const { proxy } = getCurrentInstance();

const ProcessValidationAndSummaryList = ref([]);
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
    verificationReportName: null,
    verificationReportPath: null,
    improvementChecklistName: null,
    improvementChecklistPath: null,
    improvementReportName: null,
    improvementReportPath: null,
    improvementStatus: null,
    summaryReportName: null,
    summaryReportPath: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询工艺验证与总结列表 */
function getList() {
  loading.value = true;
  listProcessValidationAndSummary(queryParams.value).then(response => {
    ProcessValidationAndSummaryList.value = response.rows;
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
    verificationReportName: null,
    verificationReportPath: null,
    improvementChecklistName: null,
    improvementChecklistPath: null,
    improvementReportName: null,
    improvementReportPath: null,
    improvementStatus: null,
    summaryReportName: null,
    summaryReportPath: null
  };
  proxy.resetForm("ProcessValidationAndSummaryRef");
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
  title.value = "添加工艺验证与总结";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcessValidationAndSummary(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改工艺验证与总结";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ProcessValidationAndSummaryRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProcessValidationAndSummary(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessValidationAndSummary(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除工艺验证与总结编号为"' + _ids + '"的数据项？').then(function() {
    return delProcessValidationAndSummary(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/ProcessValidationAndSummary/export', {
    ...queryParams.value
  }, `ProcessValidationAndSummary_${new Date().getTime()}.xlsx`)
}

getList();
</script>
