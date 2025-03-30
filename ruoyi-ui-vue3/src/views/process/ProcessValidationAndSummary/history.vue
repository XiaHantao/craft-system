<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
            v-model="queryParams.vehicleModel"
            placeholder="请输入车型"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="验证报告名称" prop="verificationReportName">
        <el-input
            v-model="queryParams.verificationReportName"
            placeholder="请输入验证报告名称"
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
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['process:processValidationAndSummary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['process:processValidationAndSummary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processValidationAndSummaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="验证报告名称" align="center" prop="verificationReportName" />
      <el-table-column label="验证报告" align="center" prop="verificationReportPath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.verificationReportPath" icon="Download" @click="downloadFile(scope.row.verificationReportPath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="验证报告上传人" align="center" prop="verificationReportUploadPerson" />
      <el-table-column label="验证报告上传时间" align="center" prop="verificationReportUploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.verificationReportUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="改进报告名称" align="center" prop="improvementReportName" />
      <el-table-column label="改进报告" align="center" prop="improvementReportPath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.improvementReportPath" icon="Download" @click="downloadFile(scope.row.improvementReportPath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="改进报告上传人" align="center" prop="improvementReportUploadPerson" />
      <el-table-column label="改进报告上传时间" align="center" prop="improvementReportUploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.improvementReportUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总结报告名称" align="center" prop="summaryReportName" />
      <el-table-column label="总结报告" align="center" prop="summaryReportPath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.summaryReportPath" icon="Download" @click="downloadFile(scope.row.summaryReportPath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="总结报告上传人" align="center" prop="summaryReportUploadPerson" />
      <el-table-column label="总结报告上传时间" align="center" prop="summaryReportUploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.summaryReportUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <el-tag v-if="scope.row.status" :type="getStatusType(scope.row.status)">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:processValidationAndSummary:remove']">删除</el-button>
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

  </div>
</template>

<script setup name="history">
import {
  getProcessValidationAndSummary,
  delProcessValidationAndSummary,
  addProcessValidationAndSummary,
  updateProcessValidationAndSummary,
  listHistory
} from "@/api/process/processValidationAndSummary";
import {listModelTable} from "@/api/process/modelTable";
import {getUserProfile, listUser} from "@/api/system/user";
import {listDept} from "@/api/system/dept";

const { proxy } = getCurrentInstance();

const openExamine = ref(false); // 审核对话框
const currentRow = ref(null);  // 当前行数据
const examineOption = ref("");  // 审核选项

const deptList = ref([]);  // 部门列表
const userList = ref([]);  // 用户列表
const cascaderOptions = ref([]); // 级联选择器选项
const selectedUser = ref(null);  // 选中的用户


const userInfoList = ref([]);  // 当前用户信息
const modelList = ref([]); // 车型列表
const processValidationAndSummaryList = ref([]);
const open = ref(false);  // 上传验证报告对话框
const openImprovment = ref(false); // 上传改进报告对话框
const openSummary = ref(false); // 上传总结报告对话框
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
    vehicleModel: null,
    verificationReportName: null,
    verificationReportPath: null,
    verificationReportUploadPerson: null,
    verificationReportUploadTime: null,
    improvementReportName: null,
    improvementReportPath: null,
    improvementReportUploadPerson: null,
    improvementReportUploadTime: null,
    summaryReportName: null,
    summaryReportPath: null,
    summaryReportUploadPerson: null,
    summaryReportUploadTime: null,
    status: null,
    newFlag: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询工艺验证与总结列表 */
function getList() {
  loading.value = true;
  listHistory(queryParams.value).then(response => {
    processValidationAndSummaryList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });

  //获取当前用户信息
  getUserProfile().then(response => {
    userInfoList.value = response.data;
  });

  // 获取部门列表
  listDept().then(response => {
    deptList.value = response.data;
    console.log("response===>",response);
    console.log("deptList.value===>",deptList.value);
    // 获取用户列表
    listUser().then(response => {
      userList.value = response.rows;
      console.log("userList.value===>",userList.value);
      // 组织级联选择器选项
      cascaderOptions.value = deptList.value.map(dept => ({
        value: dept.deptId,
        label: dept.deptName,
        children: userList.value.filter(user => user.deptId === dept.deptId).map(user => ({
          value: user.nickName,
          label: user.nickName
        }))
      }));
    });
  });
}

/** 查询车型列表 */
function getModelList() {
  listModelTable().then(response => {
    modelList.value = response.rows;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  openImprovment.value = false;
  openExamine.value = false;
  openSummary.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    vehicleModel: null,
    verificationReportName: null,
    verificationReportPath: null,
    verificationReportUploadPerson: null,
    verificationReportUploadTime: null,
    improvementReportName: null,
    improvementReportPath: null,
    improvementReportUploadPerson: null,
    improvementReportUploadTime: null,
    summaryReportName: null,
    summaryReportPath: null,
    summaryReportUploadPerson: null,
    summaryReportUploadTime: null,
    status: null,
    newFlag: null
  };
  proxy.resetForm("processValidationAndSummaryRef");
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
  proxy.download('process/processValidationAndSummary/export', {
    ...queryParams.value
  }, `processValidationAndSummary_${new Date().getTime()}.xlsx`)
}


// 获取状态类型
function getStatusType(status) {
  switch (status) {
    case '审核中':
      return 'warning';
    case '驳回':
      return 'danger';
    case '审核通过':
      return ;
    case '已完成':
      return 'success';
    default:
      return 'info';
  }
}


/** 文件下载 */
function downloadFile(filePath) {
  proxy.$download.resource(filePath);
}



getModelList();
getList();
</script>
