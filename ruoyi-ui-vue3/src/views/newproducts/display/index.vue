<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目编号" prop="projectCode">
        <el-input
          v-model="queryParams.projectCode"
          placeholder="请输入项目编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>

<!--       <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="扩展字段1" prop="extField1">
        <el-input
          v-model="queryParams.extField1"
          placeholder="请输入扩展字段1"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="扩展字段2" prop="extField2">
        <el-input
          v-model="queryParams.extField2"
          placeholder="请输入扩展字段2"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="扩展字段3" prop="extField3">
        <el-input
          v-model="queryParams.extField3"
          placeholder="请输入扩展字段3"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->

      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['newproducts:display:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['newproducts:display:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['newproducts:display:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['newproducts:display:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading" :data="displayList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="项目编号" align="center" prop="projectCode" />
      <!-- <el-table-column label="项目名称" align="center" prop="projectName" /> -->
      <el-table-column label="任务单展示文件" align="center" prop="taskOrderDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.taskOrderDisplay" icon="Download" @click="downloadFiles(scope.row.taskOrderDisplay)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="技术交底展示文件" align="center" prop="techDisclosureDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.techDisclosureDisplay" icon="Download" @click="downloadFiles(scope.row.techDisclosureDisplay)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="BOM展示文件" align="center" prop="bomDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.bomDisplay" icon="Download" @click="downloadFiles(scope.row.bomDisplay)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="其他提交展示文件" align="center" prop="otherSubmissionDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.otherSubmissionDisplay" icon="Download" @click="downloadFiles(scope.row.otherSubmissionDisplay)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="核对展示文件" align="center" prop="verificationDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.verificationDisplay" icon="Download" @click="downloadFiles(scope.row.verificationDisplay)">
          </el-button>
        </template>
      </el-table-column>
<!--       <el-table-column label="BOM质检结果展示文件" align="center" prop="bomInspectionDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.bomInspectionDisplay" icon="Download" @click="downloadFiles(scope.row.bomInspectionDisplay)">
          </el-button>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="生产问题记录展示文件" align="center" prop="productionIssueDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.productionIssueDisplay" icon="Download" @click="downloadFiles(scope.row.productionIssueDisplay)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="生产总结展示文件" align="center" prop="productionSummaryDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.productionSummaryDisplay" icon="Download" @click="downloadFiles(scope.row.productionSummaryDisplay)">
          </el-button>
        </template>
      </el-table-column> -->
      <el-table-column label="强化试验方案展示文件" align="center" prop="enhancementPlanDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.enhancementPlanDisplay" icon="Download" @click="downloadFiles(scope.row.enhancementPlanDisplay)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="强化试验结果展示文件" align="center" prop="enhancementResultDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.enhancementResultDisplay" icon="Download" @click="downloadFiles(scope.row.enhancementResultDisplay)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="TR4/TR5总结展示文件" align="center" prop="trSummaryDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.trSummaryDisplay" icon="Download" @click="downloadFiles(scope.row.trSummaryDisplay)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="内部上市报告展示文件" align="center" prop="launchReportDisplay" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.launchReportDisplay" icon="Download" @click="downloadFiles(scope.row.launchReportDisplay)">
          </el-button>
        </template>
      </el-table-column>

<!--       <el-table-column label="扩展字段1" align="center" prop="extField1" />
      <el-table-column label="扩展字段2" align="center" prop="extField2" />
      <el-table-column label="扩展字段3" align="center" prop="extField3" /> -->

<!--       <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:display:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:display:remove']">删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改新产品项目文件展示对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="displayRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目编号" prop="projectCode">
           <el-select
              v-model="form.projectCode"
              aria-placeholder="请选择项目编号！"
              clearable
              filterable
              @keyup.enter="handleQuery"
           >
            <el-option
                v-for="model in projectCodeList"
                :key="model.projectCode"
                :label="model.projectCode"
                :value="model.projectCode"
            ></el-option>
           </el-select>
        </el-form-item>

<!--         <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item> -->

        <el-form-item label="任务单展示文件" prop="taskOrderDisplay">
          <file-upload v-model="form.taskOrderDisplay"/>
        </el-form-item>
        <el-form-item label="技术交底展示文件" prop="techDisclosureDisplay">
          <file-upload v-model="form.techDisclosureDisplay"/>
        </el-form-item>
        <el-form-item label="BOM展示文件" prop="bomDisplay">
          <file-upload v-model="form.bomDisplay"/>
        </el-form-item>
        <el-form-item label="其他提交展示文件" prop="otherSubmissionDisplay">
          <file-upload v-model="form.otherSubmissionDisplay"/>
        </el-form-item>
        <el-form-item label="核对展示文件" prop="verificationDisplay">
          <file-upload v-model="form.verificationDisplay"/>
        </el-form-item>
        <el-form-item label="BOM质检结果展示文件" prop="bomInspectionDisplay">
          <file-upload v-model="form.bomInspectionDisplay"/>
        </el-form-item>
        <el-form-item label="生产问题记录展示文件" prop="productionIssueDisplay">
          <file-upload v-model="form.productionIssueDisplay"/>
        </el-form-item>
        <el-form-item label="生产总结展示文件" prop="productionSummaryDisplay">
          <file-upload v-model="form.productionSummaryDisplay"/>
        </el-form-item>
        <el-form-item label="强化试验方案展示文件" prop="enhancementPlanDisplay">
          <file-upload v-model="form.enhancementPlanDisplay"/>
        </el-form-item>
        <el-form-item label="强化试验结果展示文件" prop="enhancementResultDisplay">
          <file-upload v-model="form.enhancementResultDisplay"/>
        </el-form-item>
        <el-form-item label="TR4/TR5总结展示文件" prop="trSummaryDisplay">
          <file-upload v-model="form.trSummaryDisplay"/>
        </el-form-item>
        <el-form-item label="内部上市报告展示文件" prop="launchReportDisplay">
          <file-upload v-model="form.launchReportDisplay"/>
        </el-form-item>

<!--         <el-form-item label="扩展字段1" prop="extField1">
          <el-input v-model="form.extField1" placeholder="请输入扩展字段1" />
        </el-form-item>
        <el-form-item label="扩展字段2" prop="extField2">
          <el-input v-model="form.extField2" placeholder="请输入扩展字段2" />
        </el-form-item>
        <el-form-item label="扩展字段3" prop="extField3">
          <el-input v-model="form.extField3" placeholder="请输入扩展字段3" />
        </el-form-item> -->

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
          <el-checkbox v-for="(file, index) in downloadableFiles" :key="index" :label="file.fullUrl" class="file-item">
            <div class="file-info">
              <el-icon class="file-type-icon">
                <component :is="getFileIcon(file.fullUrl)" />
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

<script setup name="Display">
import { listDisplay, getDisplay, delDisplay, addDisplay, updateDisplay } from "@/api/newproducts/display";
import { listCreate } from "@/api/newproducts/create";

const { proxy } = getCurrentInstance();

const projectCodeList =ref([]);//项目编号列表
const displayList = ref([]);
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
    projectCode: null,
    projectName: null,
    extField1: null,
    extField2: null,
    extField3: null
  },
  rules: {
    projectCode: [
      { required: true, message: "项目编号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询新产品项目文件展示列表 */
function getList() {
  loading.value = true;
  listDisplay(queryParams.value).then(response => {
    displayList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
}

//查询项目编号列表
function getprojectCodeList () {
  listCreate().then(response => {
    projectCodeList.value = response.rows;
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
    projectCode: null,
    projectName: null,
    taskOrderDisplay: null,
    techDisclosureDisplay: null,
    bomDisplay: null,
    otherSubmissionDisplay: null,
    verificationDisplay: null,
    bomInspectionDisplay: null,
    productionIssueDisplay: null,
    productionSummaryDisplay: null,
    enhancementPlanDisplay: null,
    enhancementResultDisplay: null,
    trSummaryDisplay: null,
    launchReportDisplay: null,
    extField1: null,
    extField2: null,
    extField3: null
  };
  proxy.resetForm("displayRef");
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
  title.value = "添加新产品项目文件展示";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getDisplay(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品项目文件展示";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["displayRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateDisplay(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addDisplay(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除新产品项目文件展示编号为"' + _ids + '"的数据项？').then(function() {
    return delDisplay(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/display/export', {
    ...queryParams.value
  }, `display_${new Date().getTime()}.xlsx`)
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
  return decodedUrl.split('/').pop(); // 只显示文件名部分
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
    const fullUrl = formatFileUrl(url);
    const link = document.createElement('a');
    link.href = fullUrl;
    link.download = getFileNameForDisplay(url);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  });
};

// 格式化文件URL（添加基础路径）
const formatFileUrl = (url) => {
  if (url.startsWith('http')) return url;
  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  return `${baseUrl}/${url}`;
};

// 解析文件URL（处理逗号分隔的多文件情况）
const parseFileUrls = (urls) => {
  if (!urls) return [];
  if (Array.isArray(urls)) return urls;
  return urls.split(',').map(url => url.trim());
};

// 获取文件扩展名
const getFileExtension = (url) => {
  const filename = url.split(/[\\/]/).pop();
  return filename.split('.').pop() || '';
};

// 获取文件图标
const getFileIcon = (url) => {
  const ext = getFileExtension(url).toLowerCase();
  const iconMap = {
    mp4: 'VideoPlay', webm: 'VideoPlay', mov: 'VideoPlay',
    jpg: 'Picture', jpeg: 'Picture', png: 'Picture', gif: 'Picture', webp: 'Picture',
    pdf: 'Document', doc: 'Document', docx: 'Document', xls: 'Document', xlsx: 'Document', 
    txt: 'Document', zip: 'Document', rar: 'Document'
  };
  return iconMap[ext] || 'Document';
};

getprojectCodeList();
getList();
</script>
