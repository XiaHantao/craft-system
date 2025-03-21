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
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['process:processValidationAndSummary:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['process:processValidationAndSummary:edit']"
        >修改</el-button>
      </el-col>
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
      <el-col :span="1.5">
        <el-button
            plain
            icon="view"
            @click="handleHistory"
            v-hasPermi="['process:processValidationAndSummary:list']"
        >历史记录</el-button>
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
          <el-button link type="primary" icon="Edit" @click="addImprovementReport(scope.row)" v-if="scope.row.verificationReportPath">上传改进报告</el-button>
          <el-button link type="primary" icon="Edit" @click="handleExamine(scope.row)" v-if="scope.row.improvementReportPath">审核</el-button>
          <el-button link type="primary" icon="Edit" @click="addSummaryReport(scope.row)" v-if="scope.row.status === '审核通过'">上传总结报告</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:processValidationAndSummary:edit']">修改</el-button>
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

    <!-- 添加或修改验证报告对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="processValidationAndSummaryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车型" prop="vehicleModel">
          <el-select
              v-model="form.vehicleModel"
              placeholder="请选择车型"
              clearable
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
        <el-form-item label="验证报告名称" prop="verificationReportName">
          <el-input v-model="form.verificationReportName" placeholder="请输入验证报告名称" />
        </el-form-item>
        <el-form-item label="验证报告" prop="verificationReportPath">
          <file-upload v-model="form.verificationReportPath"/>
        </el-form-item>
        <el-form-item label="改进报告整改人" prop="improvementReportUploadPerson">
          <el-cascader
              v-model="selectedUser"
              :options="cascaderOptions"
              :props="{ expandTrigger: 'hover' }"
              @change="handleUserChange"
              placeholder="请选择部门和用户"
          />
        </el-form-item>

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 上传改进报告对话框 -->
    <el-dialog :title="title" v-model="openImprovment" width="500px" append-to-body>
      <el-form ref="processValidationAndSummaryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="改进报告名称" prop="improvementReportName">
          <el-input v-model="form.improvementReportName" placeholder="请输入改进报告名称" />
        </el-form-item>
        <el-form-item label="改进报告" prop="improvementReportPath">
          <file-upload v-model="form.improvementReportPath"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="improvementSubmitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 审核对话框 -->
    <el-dialog :title="title" v-model="openExamine" width="300px" append-to-body>
      <el-radio-group v-model="examineOption">
        <el-radio label="通过">通过</el-radio>
        <el-radio label="不通过">不通过</el-radio>
      </el-radio-group>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="examineSubmitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 上传总结报告对话框 -->
    <el-dialog :title="title" v-model="openSummary" width="500px" append-to-body>
      <el-form ref="processValidationAndSummaryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="总结报告名称" prop="summaryReportName">
          <el-input v-model="form.summaryReportName" placeholder="请输入总结报告名称" />
        </el-form-item>
        <el-form-item label="总结报告" prop="summaryReportPath">
          <file-upload v-model="form.summaryReportPath"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="summarySubmitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup name="ProcessValidationAndSummary">
import {
  listProcessValidationAndSummary,
  getProcessValidationAndSummary,
  delProcessValidationAndSummary,
  addProcessValidationAndSummary,
  updateProcessValidationAndSummary,
  getLatestRecord
} from "@/api/process/processValidationAndSummary";
import {listModelTable} from "@/api/process/modelTable";
import {getUserProfile, listUser} from "@/api/system/user";
import {listDept} from "@/api/system/dept";
import {onMounted, watch} from "vue";
import {useRouter, onBeforeRouteUpdate} from "vue-router";
import {addSysMessageNotification} from "@/api/system/sysMessageNotification";

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

const router = useRouter();

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
    vehicleModel: [
      { required: true, message: "车型不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询工艺验证与总结列表 */
function getList() {
  const id = proxy.$route.query.id;
  console.log('id', id);
  if(id) {
    getSpecificRecord(id);
    proxy.$route.query.id = null;
  } else {
    loading.value = true;
    listProcessValidationAndSummary(queryParams.value).then(response => {
      processValidationAndSummaryList.value = response.rows;
      total.value = Number(response.total);
      loading.value = false;
    });
  }
  getUserInfo();
  getDepartments();

}

/** 查询特定工艺验证与总结记录 */
function getSpecificRecord(id) {
  loading.value = true;
  getProcessValidationAndSummary(id).then(response => {
    processValidationAndSummaryList.value = [response.data];
    total.value = 1;
    loading.value = false;
  });
}

/** 查询车型列表 */
function getModelList() {
  listModelTable().then(response => {
    modelList.value = response.rows;
  });
}

// 获取当前用户信息
function getUserInfo() {
  getUserProfile().then(response => {
    userInfoList.value = response.data;
  });
}
function getDepartments() {
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

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加工艺验证与总结";
}

/** 改进报告上传按钮操作 */
function addImprovementReport(row) {
  if(userInfoList.value.nickName !== row.improvementReportUploadPerson) {
    proxy.$modal.msgError("当前操作没有权限");
    return;
  }
  reset();
  getProcessValidationAndSummary(row.id).then(response => {
    form.value = response.data;
    openImprovment.value = true;
    title.value = "上传改进报告";
  });
}

/** 审核按钮操作 */
function handleExamine(row) {
  if(userInfoList.value.nickName !== row.verificationReportUploadPerson) {
    proxy.$modal.msgError("当前操作没有权限");
    return;
  }
  openExamine.value = true;
  title.value = "审核改进报告";
  currentRow.value = row; // 保存当前行数
  examineOption.value = ""; // 清空之前的选项
}

/** 总结报告上传按钮操作 */
function addSummaryReport (row) {
  if(userInfoList.value.nickName !== row.verificationReportUploadPerson) {
    proxy.$modal.msgError("当前操作没有权限");
    return;
  }
  reset();
  getProcessValidationAndSummary(row.id).then(response => {
    form.value = response.data;
    openSummary.value = true;
    title.value = "上传总结报告";
  });
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
  proxy.$refs["processValidationAndSummaryRef"].validate(valid => {
    if (valid) {
      form.value.verificationReportUploadPerson = userInfoList.value.nickName;
      form.value.newFlag = 1;
      if(form.value.verificationReportPath) {
        form.value.verificationReportUploadTime = new Date();
        form.value.status = "审核中";
      } else form.value.verificationReportUploadTime = null;
      if (form.value.id != null) {
        updateProcessValidationAndSummary(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessValidationAndSummary(form.value).then(response => {
          //新增消息通知
          getLatestRecord().then(response => {
            console.log("response3333===>",response)
            addSysMessageNotification({
              noticeTitle: "工艺验证与总结",
              noticeContent: "请上传改进报告",
              createdBy: form.value.verificationReportUploadPerson,
              createdTime: form.value.verificationReportUploadTime,
              executedBy: form.value.improvementReportUploadPerson,
              path: "/processManagement/ProcessValidationAndSummary",
              pathId: response.data.id,
              status: 0
            })
          })
          proxy.$modal.msgSuccess("上传成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 改进报告提交按钮 */
function improvementSubmitForm() {
  proxy.$refs["processValidationAndSummaryRef"].validate(valid => {
    if (valid) {
      form.value.improvementReportUploadPerson = userInfoList.value.nickName;
      form.value.status = "审核中";
      if(form.value.improvementReportPath) {
        form.value.improvementReportUploadTime = new Date();
      } else form.value.improvementReportUploadTime = null;
      if (form.value.id != null) {
        updateProcessValidationAndSummary(form.value).then(response => {
          //新增消息通知
          getLatestRecord().then(response => {
            console.log("response3333===>",response)
            addSysMessageNotification({
              noticeTitle: "工艺验证与总结",
              noticeContent: "请审核改进报告",
              createdBy: form.value.improvementReportUploadPerson,
              createdTime: form.value.improvementReportUploadTime,
              executedBy: form.value.verificationReportUploadPerson,
              path: "/processManagement/ProcessValidationAndSummary",
              pathId: response.data.id,
              status: 0
            })
          })
          proxy.$modal.msgSuccess("上传成功");
          openImprovment.value = false;
          getList();
        });
      } else {
        proxy.$modal.msgSuccess("上传失败");
        openImprovment.value = false;
        getList();
      }
    }
  });
}

/** 审核提交按钮 */
function examineSubmitForm () {
  if (!examineOption.value) {
    proxy.$modal.msgError("请选择审核选项");
    return;
  }
  const newStatus = examineOption.value === "通过" ? "审核通过" : "驳回";
  if (newStatus === "审核通过") {
    updateProcessValidationAndSummary({
      id: currentRow.value.id,
      status: newStatus
    }).then(response => {
      //新增消息通知
      getLatestRecord().then(response => {
        console.log("response3333===>",response)
        addSysMessageNotification({
          noticeTitle: "工艺验证与总结",
          noticeContent: "改进报告审核通过",
          createdBy: currentRow.value.verificationReportUploadPerson,
          createdTime: currentRow.value.verificationReportUploadTime,
          executedBy: currentRow.value.improvementReportUploadPerson,
          path: "/processManagement/ProcessValidationAndSummary",
          pathId: response.data.id,
          status: 0
        })
      })
      proxy.$modal.msgSuccess("审核成功");
      openExamine.value = false;
      getList(); // 刷新列表
    }).catch(error => {
      proxy.$modal.msgError("审核失败");
    });
  } else if (newStatus === "驳回") {
    // 更新当前行的 newFlag 字段
    updateProcessValidationAndSummary({
      id: currentRow.value.id,
      status: newStatus,
      newFlag: 0
    }).then(response => {
      // 新增一条记录
      const newRow = {
        ...currentRow.value,
        improvementReportName: null,
        improvementReportPath: null,
        improvementReportUploadTime: null,
        status: newStatus,
        newFlag: 1
      };
      addProcessValidationAndSummary(newRow).then(response => {
        //新增消息通知
        getLatestRecord().then(response => {
          console.log("response3333===>",response)
          addSysMessageNotification({
            noticeTitle: "工艺验证与总结",
            noticeContent: "改进报告已驳回，请重新上传",
            createdBy: currentRow.value.verificationReportUploadPerson,
            createdTime: currentRow.value.verificationReportUploadTime,
            executedBy: currentRow.value.improvementReportUploadPerson,
            path: "/processManagement/ProcessValidationAndSummary",
            pathId: response.data.id,
            status: 0
          })
        })
        proxy.$modal.msgSuccess("已驳回");
        openExamine.value = false;
        getList(); // 刷新列表
      }).catch(error => {
        proxy.$modal.msgError("审核失败");
      });
    }).catch(error => {
      proxy.$modal.msgError("更新当前行失败");
    });
  }
}

/** 总结报告提交按钮 */
function summarySubmitForm() {
  proxy.$refs["processValidationAndSummaryRef"].validate(valid => {
    if (valid) {
      form.value.summaryReportUploadPerson = userInfoList.value.nickName;
      form.value.status = "已完成";
      if(form.value.summaryReportPath) {
        form.value.summaryReportUploadTime = new Date();
      } else form.value.summaryReportUploadTime = null;
      if (form.value.id != null) {
        updateProcessValidationAndSummary(form.value).then(response => {
          proxy.$modal.msgSuccess("上传成功");
          openSummary.value = false;
          getList();
        });
      } else {
        proxy.$modal.msgSuccess("上传失败");
        openImprovment.value = false;
        getList();
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
  proxy.download('process/processValidationAndSummary/export', {
    ...queryParams.value
  }, `processValidationAndSummary_${new Date().getTime()}.xlsx`)
}

// 选择 improvementReportUploadPerson
function handleUserChange(value) {
  if (value && value.length === 2) {
    form.value.improvementReportUploadPerson = value[1];
  } else {
    form.value.improvementReportUploadPerson = null;
  }
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

// 历史记录页面跳转
function handleHistory() {
  // 导航到历史版本页面
  proxy.$router.push({ name: 'history' });
}

/** 文件下载 */
function downloadFile(url) {
  fetch(url)
      .then(response => response.blob())
      .then(blob => {
        const downloadUrl = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadUrl;
        link.setAttribute('download', decodeURIComponent(url.split('/').pop())); // 解码文件名
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        window.URL.revokeObjectURL(downloadUrl);
      })
      .catch(error => console.error('Download error:', error));
}



// 页面加载时执行
onMounted(() => {
  getList();
  getModelList();
});

// 监听路由变化
watch(
    () => router.currentRoute.value.name,
    (newName, oldName) => {
      if (newName === 'ProcessValidationAndSummary') {
        getList();
      }
    },
    { immediate: true }
);






</script>
