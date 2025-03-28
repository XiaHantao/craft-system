<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="配置名称" prop="fileName">
        <el-input v-model="queryParams.fileName" placeholder="请输入配置名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮区域 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['marketanalysis:image:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['marketanalysis:image:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['marketanalysis:image:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['marketanalysis:image:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="imageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="配置名称" align="center" prop="fileName" />
      <el-table-column label="相关说明" align="center" prop="notes" />
      <el-table-column label="配置相关文件" align="center" prop="file">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.file" icon="Download" @click="downloadFiles(scope.row.file)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['marketanalysis:image:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['marketanalysis:image:remove']">删除</el-button>
          <el-button link type="primary" icon="View" @click="handlePreview(scope.row)">预览视频</el-button>
          <el-button link type="primary" icon="Picture" @click="handlePreviewImage(scope.row)">预览图片</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <pagination v-show="total>0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 新增/修改对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="imageRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="配置名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入配置名称" />
        </el-form-item>
        <el-form-item label="说明" prop="notes">
          <el-input v-model="form.notes" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="配置相关文件" prop="file">
          <file-upload v-model="form.file" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 视频预览对话框 -->
    <el-dialog v-model="previewOpen" title="视频预览" width="60%">
      <video :src="previewVideoUrl" controls autoplay style="width: 100%;" class="video-preview">
        您的浏览器不支持视频播放
      </video>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="previewOpen = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 图片预览对话框 -->
    <el-dialog v-model="previewImageOpen" title="图片预览" width="60%">
      <img :src="previewImageUrl" style="width: 100%; max-height: 70vh; object-fit: contain;" alt="图片预览">
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="previewImageOpen = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 文件选择对话框 -->
    <el-dialog v-model="fileSelectVisible" :title="fileSelectTitle" width="500px">
      <el-scrollbar height="300px">
        <el-radio-group v-model="selectedFileIndex" class="file-select-group">
          <el-radio 
            v-for="(file, index) in filteredFiles" 
            :key="index" 
            :label="index"
            class="file-item"
          >
            <div class="file-info">
              <el-icon class="file-type-icon">
                <component :is="getFileIcon(file)"/>
              </el-icon>
              <span class="file-name">{{ getFileName(file) }}</span>
            </div>
          </el-radio>
        </el-radio-group>
      </el-scrollbar>
      <template #footer>
        <el-button @click="fileSelectVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmPreview">确认预览</el-button>
      </template>
    </el-dialog>
    <!-- 新增文件选择下载对话框 -->
  <el-dialog v-model="downloadSelectVisible" title="选择下载文件" width="500px">
    <el-scrollbar height="300px">
      <el-checkbox-group v-model="selectedDownloadFiles" class="file-select-group">
        <el-checkbox 
          v-for="(file, index) in downloadableFiles" 
          :key="index" 
          :label="file.fullUrl"
          class="file-item"
        >
          <div class="file-info">
            <el-icon class="file-type-icon">
              <component :is="getFileIcon(file.fullUrl)"/>
            </el-icon>
            <span class="file-name">{{ file.displayName }}</span>
          </div>
        </el-checkbox>
      </el-checkbox-group>
    </el-scrollbar>
    <template #footer>
      <el-button @click="downloadSelectVisible = false">取消</el-button>
      <el-button type="primary" @click="confirmDownload">下载选中文件</el-button>
    </template>
  </el-dialog>
  </div>
</template>

<script setup name="Image">
import { listImage, getImage, delImage, addImage, updateImage } from "@/api/marketanalysis/image/image";
import { getCurrentInstance, ref, reactive, toRefs } from 'vue';

const { proxy } = getCurrentInstance();

// 状态管理
const imageList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const previewOpen = ref(false);
const previewImageOpen = ref(false);
const previewVideoUrl = ref("");
const previewImageUrl = ref("");
const fileSelectVisible = ref(false);
const selectedFileIndex = ref(0);
const currentPreviewType = ref('');
const filteredFiles = ref([]);
const fileSelectTitle = ref('请选择预览文件');
const downloadSelectVisible = ref(false);
const downloadableFiles = ref([]);
const selectedDownloadFiles = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    fileName: null,
  },
  rules: {
    fileName: [
      { required: true, message: "配置名称不能为空", trigger: "blur" }
    ],
    file: [
      { required: true, message: "文件不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

// 核心预览逻辑
const handlePreview = (row) => {
  const files = parseFileUrls(row.file);
  const videoFiles = filterFilesByType(files, ['mp4', 'webm', 'mov']);
  if (videoFiles.length === 0) {
    proxy.$modal.msgError('未找到可预览的视频文件');
    return;
  }
  handleMultiFilePreview(videoFiles, 'video');
};

const handlePreviewImage = (row) => {
  const files = parseFileUrls(row.file);
  const imageFiles = filterFilesByType(files, ['jpg', 'jpeg', 'png', 'gif', 'webp']);
  if (imageFiles.length === 0) {
    proxy.$modal.msgError('未找到可预览的图片文件');
    return;
  }
  handleMultiFilePreview(imageFiles, 'image');
};

const parseFileUrls = (fileString) => {
  if (!fileString) return [];
  return decodeURIComponent(fileString)
    .split(',')
    .map(url => url.trim().replace(/^\/+/, '')) // 去除开头斜杠
    .filter(url => url.length > 0);
};

const filterFilesByType = (files, allowedExtensions) => {
  return files.filter(file => {
    const ext = getFileExtension(file).toLowerCase();
    return allowedExtensions.includes(ext);
  });
};

const handleMultiFilePreview = (files, type) => {
  if (files.length === 1) {
    type === 'video' ? showVideoPreview(files[0]) : showImagePreview(files[0]);
  } else {
    showFileSelection(files, type);
  }
};

const showFileSelection = (files, type) => {
  filteredFiles.value = files;
  currentPreviewType.value = type;
  fileSelectTitle.value = `请选择要预览的${type === 'video' ? '视频' : '图片'}（共 ${files.length} 个）`;
  selectedFileIndex.value = 0;
  fileSelectVisible.value = true;
};

// 文件信息处理
const getFileExtension = (url) => {
  const filename = url.split(/[\\/]/).pop();
  return filename.split('.').pop() || '';
};

// 修改原来的getFileName函数（仅影响预览显示）
const getFileName = (url) => {
  const filename = url.split(/[\\/]/).pop();
  return filename.split('_').slice(0, -1).join('_'); // 保持原有预览文件名逻辑不变
};

const getFileIcon = (url) => {
  const ext = getFileExtension(url).toLowerCase();
  const iconMap = {
    mp4: 'VideoPlay', webm: 'VideoPlay', mov: 'VideoPlay',
    jpg: 'Picture', jpeg: 'Picture', png: 'Picture', gif: 'Picture', webp: 'Picture'
  };
  return iconMap[ext] || 'Document';
};

// 预览显示
const showVideoPreview = (fileUrl) => {
  previewVideoUrl.value = formatFileUrl(fileUrl);
  previewOpen.value = true;
};

const showImagePreview = (fileUrl) => {
  previewImageUrl.value = formatFileUrl(fileUrl);
  previewImageOpen.value = true;
};

const formatFileUrl = (url) => {
  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  if (url.startsWith('http')) return url;
  return `${baseUrl}/${url}`;
};

// 确认预览
const confirmPreview = () => {
  const selectedFile = filteredFiles.value[selectedFileIndex.value];
  currentPreviewType.value === 'video' 
    ? showVideoPreview(selectedFile)
    : showImagePreview(selectedFile);
  fileSelectVisible.value = false;
};

// 基础CRUD操作
function getList() {
  loading.value = true;
  listImage(queryParams.value).then(response => {
    imageList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
}

function reset() {
  form.value = { id: null, fileName: null, notes: null, file: null };
  proxy.resetForm("imageRef");
}

function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}

function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加配置";
}

function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getImage(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改配置";
  });
}

function submitForm() {
  proxy.$refs["imageRef"].validate(valid => {
    if (valid) {
      (form.value.id ? updateImage(form.value) : addImage(form.value))
        .then(() => {
          proxy.$modal.msgSuccess(form.value.id ? "修改成功" : "新增成功");
          open.value = false;
          getList();
        });
    }
  });
}

function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm(`确认删除编号为"${_ids}"的数据？`).then(() => {
    delImage(_ids).then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    });
  }).catch(() => {});
}

function handleExport() {
  proxy.download('marketanalysis/image/export', queryParams.value, `image_${Date.now()}.xlsx`);
}
//多文件下载代码
const downloadFiles = (urls) => {
  const files = parseFileUrls(urls);
  if (files.length <= 1) {
    // 单个文件直接下载
    handleDirectDownload(files);
    return;
  }
  // 多个文件显示选择框
  showDownloadSelection(files);
};

// 显示下载选择对话框
const showDownloadSelection = (files) => {
  downloadableFiles.value = files.map(file => ({
    fullUrl: file,
    displayName: getFileNameForDisplay(file)
  }));
  selectedDownloadFiles.value = [files[0]]; // 默认选中第一个
  downloadSelectVisible.value = true;
};

// 获取显示文件名（完整文件名）
const getFileNameForDisplay = (url) => {
  const decodedUrl = decodeURIComponent(url);
  return decodedUrl.split('/').pop().split('_').join('_'); // 保留完整文件名
};

// 确认下载
const confirmDownload = () => {
  if (selectedDownloadFiles.value.length === 0) {
    proxy.$modal.msgError('请至少选择一个文件');
    return;
  }
  handleDirectDownload(selectedDownloadFiles.value);
  downloadSelectVisible.value = false;
};

// 直接下载处理
const handleDirectDownload = (urls) => {
  urls.forEach(url => {
    const link = document.createElement('a');
    link.href = formatFileUrl(url);
    link.download = getFileNameForDisplay(url);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  });
};


getList();
</script>

<style scoped>
.file-select-group {
  width: 100%;
}
.file-item {
  width: 100%;
  padding: 12px;
  margin: 8px 0;
  border: 1px solid #ebeef5;
  border-radius: 4px;
}
.file-info {
  display: flex;
  align-items: center;
  gap: 12px;
}
.file-type-icon {
  font-size: 20px;
  color: #409eff;
}
.file-name {
  flex: 1;
  word-break: break-all;
}
.el-checkbox-group {
  width: 100%;
}
/* 文件下载需要添加如下代码 */
.el-checkbox {
  width: 100%;
  margin-left: 0;
  padding: 12px;
  margin: 8px 0;
  border: 1px solid #ebeef5;
  border-radius: 4px;
}
.el-checkbox:deep(.el-checkbox__label) {
  width: 100%;
}
</style>