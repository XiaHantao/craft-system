<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
       <el-form-item label="系列" prop="series">
        <el-input
          v-model="queryParams.series"
          placeholder="请输入系列"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="吨位" prop="tonnage">
        <el-input
          v-model="queryParams.tonnage"
          placeholder="请输入吨位"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
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
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mediaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系列" align="center" prop="series" />
      <el-table-column label="吨位" align="center" prop="tonnage" />
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="文件说明" align="center" prop="notes" />
      <el-table-column label="多媒体文件" align="center" prop="file" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.file" icon="Download" @click="downloadFiles(scope.row.file)"></el-button>
        </template>
      </el-table-column> 
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:media:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:media:remove']">删除</el-button>
          <el-button link type="primary" icon="View" @click="handlePreviewVideo(scope.row)">预览视频</el-button>
          <el-button link type="primary" icon="Picture" @click="handlePreviewImage(scope.row)">预览图片</el-button>
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
      <img :src="previewImageUrl" style="width: 100%; max-height: 70vh; object-fit: contain;" alt="图片预览"
        @error="handleImageError">
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

    <!-- 新增/修改对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="mediaRef" :model="form" :rules="rules" label-width="100px">
         <el-form-item label="系列" prop="series">
          <el-input v-model="form.series" placeholder="请输入系列" />
        </el-form-item>
        <el-form-item label="吨位" prop="tonnage">
          <el-input v-model="form.tonnage" placeholder="请输入吨位" />
        </el-form-item>
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名称" />
        </el-form-item>
        <el-form-item label="文件说明" prop="notes">
          <el-input v-model="form.notes" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <!-- 修改文件上传组件，支持多文件 -->
        <el-form-item label="多媒体文件" prop="file">
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

<script setup name="Media">
import { listMedia, getMedia, delMedia, addMedia, updateMedia } from "@/api/marketanalysis/media/media";
import { getCurrentInstance, ref, reactive, toRefs } from 'vue';

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
const previewOpen = ref(false);
const previewImageOpen = ref(false);
const previewVideoUrl = ref("");
const previewImageUrl = ref("");
const fileSelectVisible = ref(false);
const selectedFileIndex = ref(0);
const currentPreviewType = ref('');
const filteredFiles = ref([]);
const fileSelectTitle = ref('请选择预览文件');
// 新增下载相关状态
const downloadSelectVisible = ref(false);
const downloadableFiles = ref([]);
const selectedDownloadFiles = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    fileName: null,
    series: null,
    tonnage: null,
  },
   rules: {
    // series: [
    //   { required: true, message: "系列不能为空", trigger: "blur" }
    // ],
    // tonnage: [
    //   { required: true, message: "吨位不能为空", trigger: "blur" }
    // ],
     file: [
      { required: true, message: "文件不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

// 视频预览相关方法
const handlePreviewVideo = (row) => {
  const files = parseFileUrls(row.file);
  const videoFiles = filterFilesByType(files, ['mp4', 'webm', 'mov']);
  if (videoFiles.length === 0) {
    proxy.$modal.msgError('未找到可预览的视频文件');
    return;
  }
  handleMultiFilePreview(videoFiles, 'video');
};

const parseFileUrls = (fileString) => {
  if (!fileString) return [];
  return decodeURIComponent(fileString)
    .split(',')
    .map(url => url.trim().replace(/^\/+/, ''))
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

const getFileExtension = (url) => {
  const filename = url.split(/[\\/]/).pop();
  return filename.split('.').pop() || '';
};

const getFileName = (url) => {
  const filename = url.split(/[\\/]/).pop();
  return filename.split('_').slice(0, -1).join('_');
};

const getFileIcon = (url) => {
  const ext = getFileExtension(url).toLowerCase();
  const iconMap = {
    mp4: 'VideoPlay', webm: 'VideoPlay', mov: 'VideoPlay',
    jpg: 'Picture', jpeg: 'Picture', png: 'Picture', gif: 'Picture', webp: 'Picture'
  };
  return iconMap[ext] || 'Document';
};

const showVideoPreview = (fileUrl) => {
  previewVideoUrl.value = formatFileUrl(fileUrl);
  previewOpen.value = true;
};

const confirmPreview = () => {
  const selectedFile = filteredFiles.value[selectedFileIndex.value];
  currentPreviewType.value === 'video' 
    ? showVideoPreview(selectedFile)
    : showImagePreview(selectedFile);
  fileSelectVisible.value = false;
};

const formatFileUrl = (url) => {
  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  if (url.startsWith('http')) return url;
  return `${baseUrl}/${url}`;
};

// 图片预览相关方法
function handlePreviewImage(row) {
  const files = parseFileUrls(row.file);
  const imageFiles = filterFilesByType(files, ['jpg', 'jpeg', 'png', 'gif', 'webp']);
  if (imageFiles.length === 0) {
    proxy.$modal.msgError('未找到可预览的图片文件');
    return;
  }
  handleMultiFilePreview(imageFiles, 'image');
}

function showImagePreview(fileUrl) {
  previewImageUrl.value = formatFileUrl(fileUrl);
  previewImageOpen.value = true;
}

function handleImageError(event) {
  proxy.$modal.msgError('图片加载失败！');
  console.error('图片加载错误:', event);
  previewImageOpen.value = false;
}

// 文件下载方法 - 多文件支持
const downloadFiles = (urls) => {
  const files = parseFileUrls(urls);
  // 多个文件显示选择框
  showDownloadSelection(files);
};

// 显示下载选择对话框
const showDownloadSelection = (files) => {
  downloadableFiles.value = files.map(file => ({
    fullUrl: file,
    displayName: getFileNameForDisplay(file)
  }));
  selectedDownloadFiles.value = []; // 清空选中状态
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

// 其他原有方法保持不变
/** 查询多媒体文件列表 */
function getList() {
  loading.value = true;
  listMedia(queryParams.value).then(response => {
    mediaList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
}

function cancel() {
  open.value = false;
  reset();
}

function reset() {
  form.value = {
    id: null,
    fileName: null,
    series: null,
    tonnage: null,
    notes: null,
    file: null
  };
  proxy.resetForm("mediaRef");
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
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加多媒体文件";
}

function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getMedia(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改多媒体文件";
  });
}

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

function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除多媒体文件编号为"' + _ids + '"的数据项？').then(function() {
    return delMedia(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

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
.video-preview {
  background-color: #000;
  border-radius: 4px;
}
/* 文件下载需要添加如下代码 */
.el-checkbox-group {
  width: 100%;
}
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