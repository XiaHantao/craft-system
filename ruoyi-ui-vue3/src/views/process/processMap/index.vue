<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="工艺地图名称" prop="craftMapName">
        <el-input
            v-model="queryParams.craftMapName"
            placeholder="请输入工艺地图名称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" prop="uploadTime">
        <el-date-picker clearable
                        v-model="queryParams.uploadTime"
                        type="date"
                        value-format="YYYY-MM-DD"
                        placeholder="请选择上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="上传人" prop="uploadPerson">
        <el-input
            v-model="queryParams.uploadPerson"
            placeholder="请输入上传人"
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
            v-hasPermi="['process:processMap:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['process:processMap:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['process:processMap:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['process:processMap:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processMapList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="工艺地图名称" align="center" prop="craftMapName" />
      <el-table-column label="工艺地图路径" align="center" prop="craftMapPath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.craftMapPath" icon="Download" @click="downloadFile(scope.row.craftMapPath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上传人" align="center" prop="uploadPerson" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:processMap:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:processMap:remove']">删除</el-button>
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

    <!-- 添加或修改工艺地图对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="processMapRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工艺地图名称" prop="craftMapName">
          <el-input v-model="form.craftMapName" placeholder="请输入工艺地图名称" />
        </el-form-item>
        <el-form-item label="工艺地图文件" prop="craftMapPath">
          <file-upload v-model="form.craftMapPath"/>
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

<script setup name="ProcessMap">
import { listProcessMap, getProcessMap, delProcessMap, addProcessMap, updateProcessMap } from "@/api/process/processMap";
import {getUserProfile} from "@/api/system/user";

const { proxy } = getCurrentInstance();

const userInfoList = ref([]);  // 用户信息列表
const processMapList = ref([]);
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
    craftMapName: null,
    craftMapPath: null,
    uploadTime: null,
    uploadPerson: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询工艺地图列表 */
function getList() {
  loading.value = true;
  listProcessMap(queryParams.value).then(response => {
    processMapList.value = response.rows;
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
    craftMapName: null,
    craftMapPath: null,
    uploadTime: null,
    uploadPerson: null
  };
  proxy.resetForm("processMapRef");
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
  title.value = "添加工艺地图";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcessMap(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改工艺地图";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["processMapRef"].validate(valid => {
    if (valid) {
      form.value.uploadPerson = userInfoList.value.nickName;
      if(form.value.craftMapPath) {
        form.value.uploadTime = new Date();
      } else form.value.uploadTime = null;
      if (form.value.id != null) {
        updateProcessMap(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessMap(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除工艺地图编号为"' + _ids + '"的数据项？').then(function() {
    return delProcessMap(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/processMap/export', {
    ...queryParams.value
  }, `processMap_${new Date().getTime()}.xlsx`)
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

getList();
</script>
