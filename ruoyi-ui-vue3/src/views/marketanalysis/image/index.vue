<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="配置名称" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入配置名称"
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
          v-hasPermi="['marketanalysis:image:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:image:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:image:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:image:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="imageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="配置名称" align="center" prop="fileName" />
      <el-table-column label="说明" align="center" prop="notes" />
      <el-table-column label="视频地址" align="center" prop="file" />
      <el-table-column label="图片" align="center" prop="image" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.image" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:image:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:image:remove']">删除</el-button>
          <el-button size="mini" type="text" icon="Download"
          @click="handleDownload(scope.row.file)">下载视频</el-button>
          <el-button link type="info" icon="View" @click="handlePreview(scope.row)">预览视频</el-button>
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

    <!-- 添加或修改配置图片表对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="imageRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="配置名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入配置名称" />
        </el-form-item>
        <el-form-item label="说明" prop="notes">
          <el-input v-model="form.notes" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="视频地址" prop="file">
          <file-upload v-model="form.file"/>
        </el-form-item>
        <el-form-item label="图片" prop="image">
          <image-upload v-model="form.image"/>
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
  <el-dialog v-model="previewOpen" title="视频预览" width="60%">
  <video 
    :src="previewVideoUrl" 
    controls 
    autoplay
    style="width: 100%; outline: none;"
    class="video-preview"
    @error="handleVideoError"
  >
    您的浏览器不支持视频播放
  </video>
  <template #footer>
    <span class="dialog-footer">
      <el-button @click="previewOpen = false">关闭</el-button>
    </span>
  </template>
</el-dialog>
</template>

<script setup name="Image">
import { listImage, getImage, delImage, addImage, updateImage } from "@/api/marketanalysis/image/image";
import { getCurrentInstance, ref, reactive, toRefs } from 'vue';

// 新增预览相关状态
const previewOpen = ref(false);
const previewVideoUrl = ref('');

// 在script部分新增错误处理方法，并确保URL有效性
function handleVideoError(event) {
  proxy.$modal.msgError('视频加载失败，未检测到上传的视频！');
  console.error('视频加载错误:', event);
}
const handleDownload = (fileUrl) => {
  if (!fileUrl) {
    proxy.$modal.msgError("未检测到视频，请检查视频上传！");
    return;
  }
  proxy.$download.resource(fileUrl, false);
};
function handlePreview(row) {
  // 确保URL包含协议（如果存储的是相对路径，需要拼接基础URL）
  const baseUrl = import.meta.env.VITE_APP_BASE_API; // 示例：从环境变量获取
  previewVideoUrl.value = row.file.startsWith('http') 
    ? row.file 
    : `${baseUrl}${row.file}`;
    
  // 强制刷新视频源（解决缓存问题）
  previewOpen.value = false;
  nextTick(() => {
    previewOpen.value = true;
  });
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

getList();
</script>
