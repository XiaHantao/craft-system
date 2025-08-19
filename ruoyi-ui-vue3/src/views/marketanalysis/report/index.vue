<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="报告名称" prop="reportTitle">
        <el-input v-model="queryParams.reportTitle" placeholder="请输入报告名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="关联车型" prop="vehicleType">
        <el-input v-model="queryParams.vehicleType" placeholder="请输入关联车型" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="报告格式" prop="reportFormat">
        <el-input v-model="queryParams.reportFormat" placeholder="请输入报告格式" clearable @keyup.enter="handleQuery" />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['marketanalysis:report:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['marketanalysis:report:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['marketanalysis:report:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['marketanalysis:report:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="编号" align="center" prop="id" /> -->
      <el-table-column label="报告名称" align="center" prop="reportTitle" />
      <el-table-column label="关联车型" align="center" prop="vehicleType" />
      <el-table-column label="报告说明" align="center" prop="reportFormat" />
      <el-table-column label="试验报告" align="center" prop="file" >
      <template v-slot:default="scope">
          <el-button v-if="scope.row.file" icon="Download" @click="downloadFiles(scope.row.file)"></el-button>
        </template>
      </el-table-column> 
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['marketanalysis:report:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['marketanalysis:report:remove']">删除</el-button>
          <!-- <el-button size="mini" type="text" icon="Download"
            @click="$download.resource(scope.row.file, false)">下载报告</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改试验报告对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="reportRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="报告名称" prop="reportTitle">
          <el-input v-model="form.reportTitle" placeholder="请输入报告名称" />
        </el-form-item>
        <el-form-item label="关联车型" prop="vehicleType">
          <el-input v-model="form.vehicleType" placeholder="请输入关联车型" />
        </el-form-item>
        <el-form-item label="报告说明" prop="reportFormat">
          <el-input v-model="form.reportFormat" placeholder="请输入报告说明" />
        </el-form-item>
        <el-form-item label="试验报告" prop="file">
          <file-upload  v-model="form.file"/>
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

<script setup name="Report">
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/marketanalysis/report/report";

const { proxy } = getCurrentInstance();

const reportList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
// 新增下载相关状态
const downloadSelectVisible = ref(false);
const downloadableFiles = ref([]);
const selectedDownloadFiles = ref([]);
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    reportTitle: null,
    vehicleType: null,
    reportFormat: null,
  },
  rules: {
    reportTitle: [
      { required: true, message: "报告名称不能为空", trigger: "blur" }
    ],
    file: [
      { required: true, message: "试验报告文件不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询试验报告列表 */
function getList() {
  loading.value = true;
  listReport(queryParams.value).then(response => {
    reportList.value = response.rows;
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
    reportTitle: null,
    vehicleType: null,
    reportFormat: null,
    createTime: null,
    file: null,
    beiyongTwo: null,
    beiyongThree: null,
    beiyongFour: null,
    beiyongFive: null
  };
  proxy.resetForm("reportRef");
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
  title.value = "添加试验报告";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getReport(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改试验报告";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["reportRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateReport(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addReport(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除试验报告编号为"' + _ids + '"的数据项？').then(function() {
    return delReport(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/report/export', {
    ...queryParams.value
  }, `report_${new Date().getTime()}.xlsx`)
}

// 文件下载方法 - 多文件支持
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
const parseFileUrls = (fileString) => {
  if (!fileString) return [];
  return decodeURIComponent(fileString)
    .split(',')
    .map(url => url.trim().replace(/^\/+/, '')) // 去除开头斜杠
    .filter(url => url.length > 0);
};
const formatFileUrl = (url) => {
  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  if (url.startsWith('http')) return url;
  return `${baseUrl}/${url}`;
};

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

getList();
</script>
