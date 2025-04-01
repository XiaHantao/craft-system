<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="序号" prop="serialNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.serialNumber"-->
<!--          placeholder="请输入序号"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="模具号" prop="moldNumber">
        <el-input
          v-model="queryParams.moldNumber"
          placeholder="请输入模具号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模具名称" prop="moldName">
        <el-input
          v-model="queryParams.moldName"
          placeholder="请输入模具名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="模具类别" prop="moldCategory">-->
<!--        <el-input-->
<!--          v-model="queryParams.moldCategory"-->
<!--          placeholder="请输入模具类别"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="设计要求" prop="designRequirement">-->
<!--        <el-input-->
<!--          v-model="queryParams.designRequirement"-->
<!--          placeholder="请输入设计要求"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="上传人" prop="toolUploader">-->
<!--        <el-input-->
<!--          v-model="queryParams.toolUploader"-->
<!--          placeholder="请输入上传人"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="验证结论" prop="verificationConclusion">-->
<!--        <el-input-->
<!--          v-model="queryParams.verificationConclusion"-->
<!--          placeholder="请输入验证结论"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="审核人" prop="reviewer">-->
<!--        <el-input-->
<!--          v-model="queryParams.reviewer"-->
<!--          placeholder="请输入审核人"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="状态" prop="verificationState">-->
<!--        <el-input-->
<!--          v-model="queryParams.verificationState"-->
<!--          placeholder="请输入状态"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['ToolingModule:SelfMadePartsLedger:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ToolingModule:SelfMadePartsLedger:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ToolingModule:SelfMadePartsLedger:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['ToolingModule:SelfMadePartsLedger:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="SelfMadePartsLedgerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
<!--      <el-table-column label="序号" align="center" prop="serialNumber" />-->
      <el-table-column label="序号" align="center">
        <template #default="{ $index }">
          <span>{{ ($index + 1) + (queryParams.pageNum - 1) * queryParams.pageSize }}</span> <!-- 根据当前页计算序号 -->
        </template>
      </el-table-column>
      <el-table-column label="模具号" align="center" prop="moldNumber" />
      <el-table-column label="模具名称" align="center" prop="moldName" />
      <el-table-column label="模具类别" align="center" prop="moldCategory" />
      <el-table-column label="设计要求" align="center" prop="designRequirement" />
<!--      <el-table-column label="工装图纸" align="center" prop="toolingDrawings" />-->
      <el-table-column label="工装图纸" align="center" prop="toolingDrawings">
        <template #default="{ row }">
            <span v-if="row.toolingDrawings">
              <!-- 如果有文件地址，显示预览按钮 -->
              <el-button type="text" @click="previewFile(row.toolingDrawings)">预览</el-button>
            </span>
          <span v-else>
            <!-- 如果没有文件地址，显示“无图纸” -->
            无文件
          </span>
        </template>
      </el-table-column>
<!--      <el-table-column label="验证文件" align="center" prop="verifyFile" />-->
      <el-table-column label="验证文件" align="center" prop="verifyFile">
        <template #default="{ row }">
            <span v-if="row.verifyFile">
              <!-- 如果有文件地址，显示预览按钮 -->
              <el-button type="text" @click="previewFile(row.verifyFile)">预览</el-button>
            </span>
          <span v-else>
            <!-- 如果没有文件地址，显示“无图纸” -->
            无文件
          </span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="toolUploader" />
<!--      <el-table-column label="验证结论" align="center" prop="verificationConclusion" />-->
<!--      <el-table-column label="审核人" align="center" prop="reviewer" />-->
      <el-table-column prop="reviewer" label="审核人">
        <template #default="{ row }">
          {{ getReviewerName(row.reviewer) }}
        </template>
      </el-table-column>
<!--      <el-table-column label="状态" align="center" prop="verificationState" />-->
      <el-table-column label="状态" align="center" prop="verificationState" fixed="right">
        <template #default="{ row }">
          <el-tag :type="getTagType(row.verificationState)" effect="light">
            {{ getVerificationLabel(row.verificationState) }}
          </el-tag>
        </template>
      </el-table-column>
<!--      <el-table-column label="备注" align="center" prop="remark" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['ToolingModule:SelfMadePartsLedger:edit']">修改</el-button>
<!--          <el-button link type="primary" icon="Edit" @click="toExamine(scope.row)" v-hasPermi="['ToolingModule:SelfMadePartsLedger:edit']">审核</el-button>-->
          <el-button
              link
              type="primary"
              :icon="getButtonIcon(scope.row.verificationState).value"
              @click="toExamine(scope.row)"
              v-hasPermi="['ToolingModule:SelfMadePartsLedger:edit']"
          >
            {{ getButtonText(scope.row.verificationState).value }}
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['ToolingModule:SelfMadePartsLedger:remove']">删除</el-button>
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

    <!-- 添加或修改自制件台账对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="SelfMadePartsLedgerRef" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="序号" prop="serialNumber">-->
<!--          <el-input v-model="form.serialNumber" placeholder="请输入序号" />-->
<!--        </el-form-item>-->
        <el-form-item label="模具号" prop="moldNumber">
          <el-input v-model="form.moldNumber" placeholder="请输入模具号" />
        </el-form-item>
        <el-form-item label="模具名称" prop="moldName">
          <el-input v-model="form.moldName" placeholder="请输入模具名称" />
        </el-form-item>
<!--        <el-form-item label="模具类别" prop="moldCategory">-->
<!--          <el-input v-model="form.moldCategory" placeholder="请输入模具类别" />-->
<!--        </el-form-item>-->
        <el-form-item label="模具类别" prop="moldCategory">
          <el-select v-model="form.moldCategory" placeholder="请选择模具类别" clearable style="width: 50%;">
            <el-option
                v-for="item in moldTypeList"
                :key="item.id"
                :label="item.label"
                :value="item.label">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设计要求" prop="designRequirement">
          <el-input v-model="form.designRequirement" placeholder="请输入设计要求" />
        </el-form-item>
<!--        <el-form-item label="工装图纸" prop="toolingDrawings">-->
<!--          <file-upload v-model="form.toolingDrawings"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="验证文件" prop="verifyFile">-->
<!--          <file-upload v-model="form.verifyFile"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="上传人" prop="toolUploader">-->
<!--          <el-input v-model="form.toolUploader" placeholder="请输入上传人" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="验证结论" prop="verificationConclusion">-->
<!--          <el-input v-model="form.verificationConclusion" placeholder="请输入验证结论" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="审核人" prop="reviewer">-->
<!--          <el-input v-model="form.reviewer" placeholder="请输入审核人" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="状态" prop="verificationState">-->
<!--          <el-input v-model="form.verificationState" placeholder="请输入状态" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注" prop="remark">-->
<!--          <el-input v-model="form.remark" placeholder="请输入备注" />-->
<!--        </el-form-item>-->
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>

  <div>
<!--    <el-button-->
<!--        link-->
<!--        type="primary"-->
<!--        icon="Edit"-->
<!--        @click="toExamine(scope.row)"-->
<!--        v-hasPermi="['ToolingModule:SelfMadePartsLedger:edit']"-->
<!--    >-->
<!--      审核-->
<!--    </el-button>-->

    <!-- 审核弹框 -->
    <el-dialog title="审核" v-model="dialogVisible" width="600px">
      <el-steps :active="currentStep" finish-status="success" align-center>
        <el-step title="上传文件" />
        <el-step title="审核" />
      </el-steps>

      <div v-if="currentStep === 1">
        <!-- 步骤 1：上传图纸、验证结论、指定验证人 -->
        <el-form ref="SelfMadePartsLedgerRef" :model="form" label-width="120px">
          <el-form-item label="工装图纸" prop="toolingDrawings">
            <file-upload v-model="form.toolingDrawings"/>
          </el-form-item>
          <el-form-item label="验证文件" prop="verifyFile">
            <file-upload v-model="form.verifyFile"/>
          </el-form-item>
          <el-form-item label="验证结论" prop="verificationConclusion">
            <el-input v-model="form.verificationConclusion" placeholder="请输入验证结论" />
          </el-form-item>
          <!--        <el-form-item label="审核人" prop="reviewer">-->
          <!--          <el-input v-model="form.reviewer" placeholder="请输入审核人" />-->
          <!--        </el-form-item>-->
<!--          <el-form-item label="指定审核人">-->
<!--            <el-select v-model="form.reviewer" placeholder="请选择审核人">-->
<!--              <el-option-->
<!--                  v-for="user in userOptions"-->
<!--                  :key="user.value"-->
<!--                  :label="user.label"-->
<!--                  :value="user.value"-->
<!--              />-->
<!--            </el-select>-->
<!--          </el-form-item>-->
          <el-form-item label="指定审核人" prop="reviewer">
            <el-cascader
                v-model="form.reviewer"
                :options="cascaderOptions"
                :props="{ expandTrigger: 'hover' }"
                @change="handleUserChange"
                placeholder="请选择审核人"
            />
          </el-form-item>
        </el-form>
        <div style="text-align: right;">
          <el-button type="primary" @click="submitStepOne">提交</el-button>
        </div>
      </div>

      <div v-else-if="currentStep === 2">
        <!-- 步骤 2：审核操作 -->
        <el-form ref="SelfMadePartsLedgerRef" :model="form" label-width="120px">
          <el-form-item label="审核结果">
            <el-radio-group v-model="form.verificationState">
              <el-radio label="通过">通过</el-radio>
              <el-radio label="未通过">未通过</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注">
            <el-input type="textarea" v-model="form.remark" />
          </el-form-item>
        </el-form>
        <div style="text-align: right;">
          <el-button type="primary" @click="submitStepTwo">提交审核</el-button>
        </div>
      </div>
    </el-dialog>
  </div>


</template>

<script setup name="SelfMadePartsLedger">
import { listSelfMadePartsLedger, getSelfMadePartsLedger, delSelfMadePartsLedger, addSelfMadePartsLedger, updateSelfMadePartsLedger } from "@/api/ToolingModule/SelfMadePartsLedger";
import {getUserProfile, listUser} from "@/api/system/user";
import { ElMessage } from 'element-plus';
import {listMoldTypename} from "@/api/ToolingModule/MoldType";
import {getLatestRecord02} from "@/api/process/ProcessValidationAndSummary";
import {addSysMessageNotification} from "@/api/system/sysMessageNotification";
import {listDept} from "@/api/system/dept";

const { proxy } = getCurrentInstance();

const SelfMadePartsLedgerList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const dialogVisible = ref(false);
const currentStep = ref(1);
const moldTypeList = ref([]); // 存储后端返回的数组

// const userList = ref([]); // 用户信息列表
const deptList = ref([]);  // 部门列表
const userList = ref([]);  // 用户列表
const cascaderOptions = ref([]); // 级联选择器选项
const userOptions = ref([]); // 验证人列表
const currentUserId = ref(0); // 假设当前用户 ID
const userMap = ref({}); // 存储用户 ID -> 姓名 映射

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    serialNumber: null,
    moldNumber: null,
    moldName: null,
    moldCategory: null,
    designRequirement: null,
    toolingDrawings: null,
    verifyFile: null,
    toolUploader: null,
    verificationConclusion: null,
    reviewer: null,
    verificationState: null,
  },
  rules: {
  }
});
// 获取工装类型数据
const fetchMoldTypeList = async () => {
  try {
    const response = await listMoldTypename();
    // moldTypeList.value = response.data || []; // 确保数据是数组
    moldTypeList.value = response.data.map((item, index) => ({
      id: index,  // 使用索引作为唯一标识
      label: item // 使用数组的字符串作为显示的 label
    }));
    // console.log('加载中....' , moldTypeList.value)

  } catch (error) {
    console.error("获取工装类型失败：", error);
  }
};
// 组件加载时调用
onMounted(() => {
  fetchMoldTypeList();
  // console.log('加载中....' , moldTypeList.value)
});
const { queryParams, form, rules } = toRefs(data);

const getButtonText = (state) => {
  return computed(() => {
    if (state === '进行中') return '上传文件';
    if (state === '审核中') return '审核';
    return '操作';
  });
};

const getButtonIcon = (state) => {
  return computed(() => (state === '进行中' ? 'Upload' : 'Edit'));
};


// 定义状态对应的标签类型
const getTagType = (state) => {
  switch (state) {
    case "通过":
      return "success"; // 绿色
    case "进行中":
      return "warning"; // 橙色
    case "未通过":
      return "danger";  // 红色
    default:
      return "info";    // 灰色
  }
};
// 处理状态显示，防止为空
const getVerificationLabel = (state) => state || "";

const toExamine = async (row) => {
  const _id = row.id || ids.value
  getSelfMadePartsLedger(_id).then(response => {
    form.value = response.data;
    // open.value = true;

    // title.value = "修改自制件台账";
  });
  // dialogVisible.value = true;
  await fecthuser(); //获取自身信息

  // 判断进入步骤一还是步骤二
  if (row.verificationState == '进行中') {
    dialogVisible.value = true;
    currentStep.value = 1; // 进入步骤一
    await fetchUserList(); // 获取审核人列表
  } else if (row.verificationState == '审核中'){
    dialogVisible.value = true;
    currentStep.value = 2; // 进入步骤二
    // console.log('API 返回完整数据1:', currentUserId.value);
    // console.log('API 返回完整数据2:', row.reviewer);

    // 权限校验
    if (currentUserId.value !== row.reviewer) {
      ElMessage.warning("您不是审核人");
      dialogVisible.value = false;
      return;
    }
  } else if (row.verificationState == '通过' || row.verificationState == '未通过'){
    dialogVisible.value = false;
    ElMessage.error("请勿重复操作");
    return;
  }

};

// 获取用户列表
const fetchUserList = async () => {
  try {
    const response = await listUser();


    if (response.code === 200 ) {
      console.log('API 返回完整数据:', response.rows);
      // userList.value = response.rows;
      // console.log('API 返回完整数据:', userList.value[0] ); // 调试日志
      userOptions.value = response.rows.map(user => ({
        label: user.nickName,
        value: user.userId
      }));
      userMap.value = response.rows.reduce((acc, user) => {
        acc[user.userId] = user.nickName; // 存储 ID 到姓名的映射
        return acc;
      }, {});
    }
    console.log('userlsit' ,userMap.value);
  } catch (error) {
    ElMessage.error("获取审核人列表失败");
  }
};

// 计算属性：获取用户名
const getReviewerName = (reviewerId) => {
  return userMap.value[reviewerId] || "未知";
};

const fecthuser = async () => {
  try {
    const response = await getUserProfile();
    // console.log('API 返回完整数据:', response.data.userId);
    // console.log('API 返回完整数据:', currentUserId.value);
    currentUserId.value = response.data.userId;
    // console.log('API 返回完整数据:', currentUserId.value);

  }catch (error){
    ElMessage.error("获取信息失败");
  }
};


const submitStepOne = () => {
  // console.log("步骤一提交:", form.value);
  form.value.verificationState = '审核中';
  console.log("步骤一提交:", form.value.verificationState);
  updateSelfMadePartsLedger(form.value).then(response => {
    //新增消息通知
    // getLatestRecord02("self_made_parts_ledger").then(response => {
    //   console.log("response3333===>",response)
      addSysMessageNotification({
        noticeTitle: "自制件审核通知",
        noticeContent: "请进行自制件审核",
        createdBy: form.value.toolUploader,
        createdTime: form.value.verificationReportUploadTime,
        executedBy: getReviewerName(form.value.reviewer),
        path: "/ToolingModule/SelfMadePartsLedger",
        pathId: form.value.id,
        status: 0
      })
    // })
    // proxy.$modal.msgSuccess("修改成功");
    ElMessage.success("提交成功，进入审核阶段");
    dialogVisible.value = false;
    getList();
  });
};

const submitStepTwo = () => {
  // console.log("步骤二审核提交:", stepTwoData.value);
  updateSelfMadePartsLedger(form.value).then(response => {
    // proxy.$modal.msgSuccess("修改成功");
    ElMessage.success("审核完成");
    dialogVisible.value = false;
    getList();
  });
};


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
          value: user.userId,
          label: user.nickName
        }))
      }));
    });
  });
}

// 选择 improvementReportUploadPerson
function handleUserChange(value) {
  if (value && value.length === 2) {
    form.value.reviewer = value[1];
  } else {
    form.value.reviewer = null;
  }
}

//预览文件
function previewFile(fileUrl) {
  const baseUrl = import.meta.env.VITE_APP_BASE_API; // 或者你可以直接赋值为基础文件路径
  const fullUrl = `${baseUrl}${fileUrl}`; // 拼接文件的完整 URL 地址
  // console.log('处理中');
  window.open(fullUrl, '_blank');
}

/** 查询自制件台账列表 */
function getList() {
  loading.value = true;
  fetchUserList(); // 获取审核人列表
  listSelfMadePartsLedger(queryParams.value).then(response => {
    SelfMadePartsLedgerList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
  getDepartments();
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
    moldNumber: null,
    moldName: null,
    moldCategory: null,
    designRequirement: null,
    toolingDrawings: null,
    verifyFile: null,
    toolUploader: null,
    verificationConclusion: null,
    reviewer: null,
    verificationState: null,
    remark: null
  };
  proxy.resetForm("SelfMadePartsLedgerRef");
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
  // getUserProfile().then(response => {
  //   userInfoList.value = response.data;
  //   console.log(userInfoList.value);
  // });
  reset();
  open.value = true;
  title.value = "添加自制件台账";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getSelfMadePartsLedger(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改自制件台账";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["SelfMadePartsLedgerRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSelfMadePartsLedger(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        form.value.verificationState = '进行中';
        addSelfMadePartsLedger(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除自制件台账编号为"' + _ids + '"的数据项？').then(function() {
    return delSelfMadePartsLedger(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('ToolingModule/SelfMadePartsLedger/export', {
    ...queryParams.value
  }, `SelfMadePartsLedger_${new Date().getTime()}.xlsx`)
}

getList();
</script>
