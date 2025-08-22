<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="130px">
      <el-form-item label="检查报告名称" prop="inspectionReportName">
        <el-input
          v-model="queryParams.inspectionReportName"
          placeholder="请输入检查报告名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整改要求" prop="rectificationRequirements">
        <el-input
          v-model="queryParams.rectificationRequirements"
          placeholder="请输入整改要求"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查报告上传人" prop="inspectionReportUploadPerson">
        <el-input
          v-model="queryParams.inspectionReportUploadPerson"
          placeholder="请输入检查报告上传人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查报告上传时间" prop="inspectionReportUploadTime">
        <el-date-picker clearable
          v-model="queryParams.inspectionReportUploadTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择检查报告上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="整改报告名称" prop="rectificationReportName">
        <el-input
          v-model="queryParams.rectificationReportName"
          placeholder="请输入整改报告名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整改报告上传人" prop="rectificationReportUploadPerson">
        <el-input
          v-model="queryParams.rectificationReportUploadPerson"
          placeholder="请输入整改报告上传人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整改报告上传时间" prop="rectificationReportUploadTime">
        <el-date-picker clearable
          v-model="queryParams.rectificationReportUploadTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择整改报告上传时间">
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
          v-hasPermi="['process:processSiteManagement:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['process:processSiteManagement:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['process:processSiteManagement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['process:processSiteManagement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processSiteManagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="检查报告名称" align="center" prop="inspectionReportName" />
      <el-table-column label="检查报告" align="center" prop="inspectionReportPath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.inspectionReportPath" icon="Download" @click="downloadFile(scope.row.inspectionReportPath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="整改要求" align="center" prop="rectificationRequirements">
        <template #default="scope">
          <el-tag :type="scope.row.rectificationRequirements === '无需整改' ? 'success' : 'danger'" >
            {{ scope.row.rectificationRequirements }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="检查报告上传人" align="center" prop="inspectionReportUploadPerson" />
      <el-table-column label="检查报告上传时间" align="center" prop="inspectionReportUploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.inspectionReportUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="整改报告名称" align="center" prop="rectificationReportName" />
      <el-table-column label="整改报告" align="center" prop="rectificationReportPath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.rectificationReportPath" icon="Download" @click="downloadFile(scope.row.rectificationReportPath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="整改报告上传人" align="center" prop="rectificationReportUploadPerson" />
      <el-table-column label="整改报告上传时间" align="center" prop="rectificationReportUploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.rectificationReportUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:processSiteManagement:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:processSiteManagement:remove']">删除</el-button>
          <el-button link type="primary" icon="Check" @click="handleRectification(scope.row)" v-hasPermi="['process:processRectification:rectification']">整改</el-button>
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

    <!-- 添加或修改检查报告对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="processSiteManagementRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="检查报告名称" prop="inspectionReportName">
          <el-input v-model="form.inspectionReportName" placeholder="请输入检查报告名称" />
        </el-form-item>
        <el-form-item label="检查报告路径" prop="inspectionReportPath">
          <file-upload v-model="form.inspectionReportPath"/>
        </el-form-item>
        <el-form-item label="整改要求" prop="rectificationRequirements">
          <el-select
              v-model="form.rectificationRequirements"
              placeholder="请选择整改要求"
              clearable
              filterable
          >
            <el-option label="需整改" value="需整改"></el-option>
            <el-option label="无需整改" value="无需整改"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!--  整改报告上传对话框  -->
    <el-dialog :title="title" v-model="rectificationOpen" width="500px" append-to-body>
      <el-form ref="processSiteManagementRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="整改报告名称" prop="rectificationReportName">
          <el-input v-model="form.rectificationReportName" placeholder="请输入整改报告名称" />
        </el-form-item>
        <el-form-item label="整改报告路径" prop="rectificationReportPath">
          <file-upload v-model="form.rectificationReportPath"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="rectificationSubmitForm">确 定</el-button>
          <el-button @click="rectificationCancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="ProcessSiteManagement">
import { listProcessSiteManagement, getProcessSiteManagement, delProcessSiteManagement, addProcessSiteManagement, updateProcessSiteManagement } from "@/api/process/processSiteManagement";
import {getUserProfile} from "@/api/system/user";

const { proxy } = getCurrentInstance();

const userInfoList = ref([]);  // 用户信息列表
const processSiteManagementList = ref([]);
const open = ref(false);
const rectificationOpen = ref(false); // 整改报告对话框开关
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
    inspectionReportName: null,
    inspectionReportPath: null,
    rectificationRequirements: null,
    inspectionReportUploadPerson: null,
    inspectionReportUploadTime: null,
    rectificationReportName: null,
    rectificationReportPath: null,
    rectificationReportUploadPerson: null,
    rectificationReportUploadTime: null
  },
  rules: {
    inspectionReportName: [
      { required: true, message: "检查报告名称不能为空", trigger: "blur" }
    ],
    inspectionReportPath: [
      { required: true, message: "检查报告不能为空", trigger: "blur" }
    ],
    rectificationRequirements: [
      { required: true, message: "整改要求不能为空", trigger: "blur" }
    ],
    rectificationReportName: [
      { required: true, message: "整改报告名称不能为空", trigger: "blur" }
    ],
    rectificationReportPath: [
      { required: true, message: "整改报告不能为空", trigger: "blur" }
    ]
  }

});

const { queryParams, form, rules } = toRefs(data);

/** 查询工艺现场管理列表 */
function getList() {
  loading.value = true;
  listProcessSiteManagement(queryParams.value).then(response => {
    processSiteManagementList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });

  getUserProfile().then(response => {
    userInfoList.value = response.data;
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
    inspectionReportName: null,
    inspectionReportPath: null,
    rectificationRequirements: null,
    inspectionReportUploadPerson: null,
    inspectionReportUploadTime: null,
    rectificationReportName: null,
    rectificationReportPath: null,
    rectificationReportUploadPerson: null,
    rectificationReportUploadTime: null
  };
  proxy.resetForm("processSiteManagementRef");
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
  title.value = "添加检查报告文件";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcessSiteManagement(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改检查报告文件";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["processSiteManagementRef"].validate(valid => {
    if (valid) {
      form.value.inspectionReportUploadPerson = userInfoList.value.nickName;
      if(form.value.inspectionReportPath) {
        form.value.inspectionReportUploadTime = new Date();
      } else form.value.inspectionReportUploadTime = null;
      if (form.value.id != null) {
        updateProcessSiteManagement(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessSiteManagement(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除工艺现场管理编号为"' + _ids + '"的数据项？').then(function() {
    return delProcessSiteManagement(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/processSiteManagement/export', {
    ...queryParams.value
  }, `processSiteManagement_${new Date().getTime()}.xlsx`)
}

/** 文件下载 */
function downloadFile(filePath) {
  const paths = filePath.split(',').map(path => path.trim());
  paths.forEach(path => {
    proxy.$download.resource(path);
  });
}
/** 整改报告文件上传 */
function handleRectification(row) {
  reset();
  getProcessSiteManagement(row.id).then(response => {
    form.value = response.data;
    rectificationOpen.value = true;
    title.value = "整改报告文件上传";
  });
}

/** 整改报告文件上传提交按钮 */
function rectificationSubmitForm() {
  proxy.$refs["processSiteManagementRef"].validate(valid => {
    if (valid) {
      form.value.rectificationReportUploadPerson = userInfoList.value.nickName;
      form.value.rectificationReportUploadTime = new Date();
      updateProcessSiteManagement(form.value).then(response => {
        proxy.$modal.msgSuccess("上传成功");
        rectificationOpen.value = false;
        getList();
      });
    }
  });
}

getList();
</script>
