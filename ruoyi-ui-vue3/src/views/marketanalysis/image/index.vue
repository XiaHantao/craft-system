<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="配置名称" prop="fileName">
        <el-input v-model="queryParams.fileName" placeholder="请输入配置名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

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

    <el-table v-loading="loading" :data="imageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="编号" align="center" prop="id" /> -->
      <el-table-column label="配置名称" align="center" prop="fileName" />
      <el-table-column label="相关说明" align="center" prop="notes" />
      <el-table-column label="配置相关文件" align="center" prop="file" >
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
            <!-- <el-button size="mini" type="text" icon="Download" @click="download.resource(scope.row.file)">下载</el-button> -->
          <el-button link type="info" icon="View" @click="handlePreview(scope.row)">预览视频</el-button>
          <el-button link type="info" icon="View" @click="handlePreviewImage(scope.row)">预览图片</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改配置图片表对话框 -->
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
    <el-dialog v-model="previewOpen" title="视频预览" width="60%">
      <video :src="previewVideoUrl" controls autoplay style="width: 100%; outline: none;" class="video-preview"
        @error="handleVideoError">
        您的浏览器不支持视频播放
      </video>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="previewOpen = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 新增图片预览对话框 -->
    <el-dialog v-model="previewImageOpen" title="图片预览" width="60%">
      <img :src="previewImageUrl" style="width: 100%; max-height: 70vh; object-fit: contain;" alt="图片预览"
        @error="handleImageError">
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="previewImageOpen = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Image">
import { listImage, getImage, delImage, addImage, updateImage } from "@/api/marketanalysis/image/image";
import { getCurrentInstance, ref, reactive, toRefs } from 'vue';
import download from "@/plugins/download"; 

// 新增预览相关状态
const previewOpen = ref(false);
const previewVideoUrl = ref('');
// 新增图片预览相关状态
const previewImageOpen = ref(false);
const previewImageUrl = ref('');


function handlePreview(row) {
  if (!validateFileType(row.file, ['mp4'])) {
    proxy.$modal.msgError('未检测到视频，请检查文件格式！');
    return;
  }
  
  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  previewVideoUrl.value = formatFileUrl(row.file, baseUrl);
  
  // 强制刷新视频源
  previewOpen.value = false;
  nextTick(() => {
    previewOpen.value = true;
  });
}

// 新增图片预览方法
function handlePreviewImage(row) {
  if (!validateFileType(row.file, ['jpg', 'jpeg', 'png'])) {
    proxy.$modal.msgError('未检测到图片，请检查文件格式！');
    return;
  }

  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  previewImageUrl.value = formatFileUrl(row.file, baseUrl);
  previewImageOpen.value = true;
}

// 通用文件类型验证方法
function validateFileType(fileUrl, allowedTypes) {
  if (!fileUrl) return false;
  
  // 获取干净的文件名（去除查询参数和哈希）
  const cleanUrl = fileUrl.split(/[?#]/)[0];
  const extension = cleanUrl.split('.').pop().toLowerCase();
  
  return allowedTypes.includes(extension);
}

// 通用URL格式化方法
function formatFileUrl(fileUrl, baseUrl) {
  return fileUrl.startsWith('http') 
    ? fileUrl 
    : `${baseUrl}${fileUrl}`;
}

// 新增图片加载错误处理
function handleImageError(event) {
  proxy.$modal.msgError('图片加载失败！');
  console.error('图片加载错误:', event);
  previewImageOpen.value = false;
}
const { proxy } = getCurrentInstance();

const imageList = ref([]);
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
      { required: true, message: "配置名称不能为空", trigger: "blur" }
    ],
    file: [
      { required: true, message: "文件不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询配置图片表列表 */
function getList() {
  loading.value = true;
  listImage(queryParams.value).then(response => {
    imageList.value = response.rows;
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
    notes: null,
    file: null,
    image: null
  };
  proxy.resetForm("imageRef");
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
  title.value = "添加配置图片表";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getImage(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改配置图片表";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["imageRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateImage(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addImage(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除配置图片表编号为"' + _ids + '"的数据项？').then(function() {
    return delImage(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/image/export', {
    ...queryParams.value
  }, `image_${new Date().getTime()}.xlsx`)
}
/** 多文件下载 */
function downloadFiles(urls) {
  // 如果 urls 是字符串，则按逗号分隔为数组
  if (typeof urls === 'string') {
    urls = urls.split(',');
  }
  // 确保 urls 是数组
  if (!Array.isArray(urls)) {
    console.error('urls 必须是数组或逗号分隔的字符串');
    return;
  }
  // 遍历每个 URL，下载并保存文件
  urls.forEach(url => {
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
  });
}
getList();
</script>
