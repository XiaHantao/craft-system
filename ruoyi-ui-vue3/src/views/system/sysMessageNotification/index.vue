<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="通知标题" prop="noticeTitle">
        <el-input
          v-model="queryParams.noticeTitle"
          placeholder="请输入通知标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发起人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入发起人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发起时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择发起时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入状态"
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
          v-hasPermi="['system:sysMessageNotification:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:sysMessageNotification:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:sysMessageNotification:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['system:sysMessageNotification:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sysMessageNotificationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="通知标题" align="center" prop="noticeTitle" />
      <el-table-column label="通知内容" align="center" prop="noticeContent" />
      <el-table-column label="发起人" align="center" prop="createdBy" />
      <el-table-column label="发起时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="执行人" align="center" prop="executedBy" />
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <el-tag :type="scope.row.status === '1' ? 'success' : 'danger'">{{ scope.row.status === '1' ? '已处理' : '未处理' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="toHandle(scope.row)" >去处理</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:sysMessageNotification:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['system:sysMessageNotification:remove']">删除</el-button>
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

    <!-- 添加或修改消息通知对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="sysMessageNotificationRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="通知标题" prop="noticeTitle">
          <el-input v-model="form.noticeTitle" placeholder="请输入通知标题" />
        </el-form-item>
        <el-form-item label="通知内容">
          <el-input v-model="form.noticeContent" placeholder="请输入通知内容"/>
        </el-form-item>
        <el-form-item label="发起人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入发起人" />
        </el-form-item>
        <el-form-item label="发起时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择发起时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="执行人" prop="executedBy">
          <el-input v-model="form.executedBy" placeholder="请输入执行人" />
        </el-form-item>
        <el-form-item label="执行时间" prop="executedTime">
          <el-date-picker clearable
            v-model="form.executedTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择执行时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="路径" prop="path">
          <el-input v-model="form.path" placeholder="请输入路径" />
        </el-form-item>
        <el-form-item label="路径id" prop="status">
          <el-input v-model="form.pathId" placeholder="请输入路径id" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="form.status" placeholder="请输入状态" />
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

<script setup name="SysMessageNotification">
import { listSysMessageNotification, getSysMessageNotification, delSysMessageNotification, addSysMessageNotification, updateSysMessageNotification } from "@/api/system/sysMessageNotification";
import {getUserProfile} from "@/api/system/user";
import {onMounted, onUnmounted} from "vue";

const { proxy } = getCurrentInstance();

const userInfoList = ref([]);  // 当前用户信息
const sysMessageNotificationList = ref([]);
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
    noticeTitle: null,
    noticeContent: null,
    createdBy: null,
    createdTime: null,
    executedBy: null,
    executedTime: null,
    path: null,
    pathId: null,
    status: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询消息通知列表 */
function getList() {
  loading.value = true;
  getUserProfile().then(response => {
    userInfoList.value = response.data;
    queryParams.value.executedBy = userInfoList.value.nickName;
    listSysMessageNotification(queryParams.value).then(response => {
      sysMessageNotificationList.value = response.rows;
      total.value = Number(response.total);
      loading.value = false;
    });
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
    noticeId: null,
    noticeTitle: null,
    noticeContent: null,
    createdBy: null,
    createdTime: null,
    executedBy: null,
    executedTime: null,
    path: null,
    pathId: null,
    status: null
  };
  proxy.resetForm("sysMessageNotificationRef");
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
  ids.value = selection.map(item => item.noticeId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加消息通知";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _noticeId = row.noticeId || ids.value
  getSysMessageNotification(_noticeId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改消息通知";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["sysMessageNotificationRef"].validate(valid => {
    if (valid) {
      if (form.value.noticeId != null) {
        updateSysMessageNotification(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSysMessageNotification(form.value).then(response => {
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
  const _noticeIds = row.noticeId || ids.value;
  proxy.$modal.confirm('是否确认删除消息通知编号为"' + _noticeIds + '"的数据项？').then(function() {
    return delSysMessageNotification(_noticeIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}


/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/sysMessageNotification/export', {
    ...queryParams.value
  }, `sysMessageNotification_${new Date().getTime()}.xlsx`)
}

/** 处理按钮操作 */
function toHandle(row) {
  const path = row.path;
  const pathId = row.pathId;
  updateSysMessageNotification({
    noticeId: row.noticeId,
    status: 1
  }).then(response => {
    getList();
    if (path && pathId) {
      proxy.$router.push({ path: path, query: { id: pathId } });
    } else {
      proxy.$modal.msgError("路径不存在");
    }
  })
}
getList();
</script>
