<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文件名称" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入文件名称"
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
          v-hasPermi="['marketanalysis:media:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:media:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:media:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:media:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mediaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="编号" align="center" prop="id" /> -->
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="文件类型" align="center" prop="fileType" />
      <el-table-column label="备注" align="center" prop="notes" />
      <el-table-column label="多媒体文件" align="center" prop="file" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:media:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:media:remove']">删除</el-button>
          <el-button size="mini" type="text" icon="Download"
            @click="$download.resource(scope.row.file, false)">下载</el-button>
            <el-button link type="primary" icon="View" @click="handlePreview(scope.row)">预览图片</el-button>
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
     <!-- 新增图片预览对话框 -->
     <el-dialog v-model="previewVisible" title="图片预览" width="60%">
      <img :src="previewImageUrl" style="width: 100%; max-height: 70vh; object-fit: contain;" alt="预览图片" />
    </el-dialog>

    <!-- 添加或修改多媒体文件对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="mediaRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名称" />
        </el-form-item>
        <el-form-item label="文件类型" prop="fileType">
          <el-input v-model="form.fileType" placeholder="请输入文件类型" />
        </el-form-item>
        <el-form-item label="备注" prop="notes">
          <el-input v-model="form.notes" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="多媒体文件" prop="file">
          <file-upload v-model="form.file"/>
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

<script setup name="Media">
import { listMedia, getMedia, delMedia, addMedia, updateMedia } from "@/api/marketanalysis/media/media";
// 新增预览相关状态
const previewVisible = ref(false);
const previewImageUrl = ref('');

const { proxy } = getCurrentInstance();

const mediaList = ref([]);
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
    fileName: null,
  },
  rules: {
    fileName: [
      { required: true, message: "文件名称不能为空", trigger: "blur" }
    ],
    file: [
      { required: true, message: "文件不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);
/** 预览按钮操作 */
function handlePreview(row) {
  const fileExtension = row.file.split('.').pop().toLowerCase();
  const imageExtensions = ['jpg', 'jpeg', 'png'];
  
  if (imageExtensions.includes(fileExtension)) {
    previewImageUrl.value = row.file; // 假设file字段是完整图片URL
    previewVisible.value = true;
  } else {
    proxy.$modal.msgError('未检测到图片，请检查文件格式！');
  }
}


/** 查询多媒体文件列表 */
function getList() {
  loading.value = true;
  listMedia(queryParams.value).then(response => {
    mediaList.value = response.rows;
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
    fileName: null,
    fileType: null,
    notes: null,
    file: null
  };
  proxy.resetForm("mediaRef");
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
  title.value = "添加多媒体文件";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getMedia(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改多媒体文件";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["mediaRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMedia(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMedia(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除多媒体文件编号为"' + _ids + '"的数据项？').then(function() {
    return delMedia(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/media/export', {
    ...queryParams.value
  }, `media_${new Date().getTime()}.xlsx`)
}

getList();
</script>
