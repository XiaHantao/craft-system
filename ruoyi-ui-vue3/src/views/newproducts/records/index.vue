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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['newproducts:records:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['newproducts:records:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['newproducts:records:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['newproducts:records:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordsList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="项目编号" align="center" prop="projectCode" />
      <!-- <el-table-column label="项目名称" align="center" prop="projectName" /> -->
      <el-table-column label="问题记录文件" align="center" prop="issueRecordFile" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.issueRecordFile" icon="Download" @click="downloadFiles(scope.row.issueRecordFile)">
          </el-button>
        </template>        
      </el-table-column>
      <el-table-column label="总结文件" align="center" prop="summaryFile" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.summaryFile" icon="Download" @click="downloadFiles(scope.row.summaryFile)">
          </el-button>
        </template>
      </el-table-column>
      <!-- <el-table-column label="其他附件文件" align="center" prop="attachmentFiles" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.attachmentFiles" icon="Download" @click="downloadFiles(scope.row.attachmentFiles)">
          </el-button>
        </template>
      </el-table-column> -->

<!--       <el-table-column label="扩展字段1" align="center" prop="extField1" />
      <el-table-column label="扩展字段2" align="center" prop="extField2" />
      <el-table-column label="扩展字段3" align="center" prop="extField3" /> -->

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:records:edit']">上传文件</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:records:remove']">删除</el-button>
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

    <!-- 添加或修改新产品生产问题记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="recordsRef" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="项目编号" prop="projectCode">
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
        </el-form-item> -->

<!--         <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item> -->

        <el-form-item label="问题记录文件" prop="issueRecordFile">
          <file-upload v-model="form.issueRecordFile"/>
        </el-form-item>
        <el-form-item label="总结文件" prop="summaryFile">
          <file-upload v-model="form.summaryFile"/>
        </el-form-item>
        <!-- <el-form-item label="其他附件文件" prop="attachmentFiles">
          <file-upload v-model="form.attachmentFiles"/>
        </el-form-item> -->

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

    <!-- 新增对话框 -->
    <el-dialog :title="title" v-model="openAdd" width="500px" append-to-body>
      <el-form ref="recordsRef" :model="form" :rules="rules" label-width="80px">
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

<script setup name="Records">
import { listRecords, getRecords, delRecords, addRecords, updateRecords } from "@/api/newproducts/records";
import { listCreate } from "@/api/newproducts/create";

const { proxy } = getCurrentInstance();

const projectCodeList =ref([]);//项目编号列表
const recordsList = ref([]);
const open = ref(false);
const openAdd = ref(false);//新增对话框
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

/** 查询新产品生产问题记录列表 */
function getList() {
  loading.value = true;
  listRecords(queryParams.value).then(response => {
    recordsList.value = response.rows;
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


/** 多文件下载 */
const formatFileUrl = (url) => {
  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  if (url.startsWith('http')) return url;
  return `${baseUrl}/${url}`;
};
//文件下载
function downloadFiles(urls) {
  // 统一处理输入为数组
  if (typeof urls === 'string') {
    urls = decodeURIComponent(urls).split(',').map(url => url.trim());
  }
  
  // 确保是数组格式
  if (!Array.isArray(urls)) {
    console.error('urls 必须是数组或逗号分隔的字符串');
    return;
  }

  // 遍历下载每个文件
  urls.forEach(url => {
    const formattedUrl = formatFileUrl(url);
    const link = document.createElement('a');
    link.href = formattedUrl;
    link.download = decodeURIComponent(url.split('/').pop());
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  openAdd.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    projectCode: null,
    projectName: null,
    issueRecordFile: null,
    summaryFile: null,
    attachmentFiles: null,
    extField1: null,
    extField2: null,
    extField3: null
  };
  proxy.resetForm("recordsRef");
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
  openAdd.value = true;
  title.value = "添加新产品生产问题记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getRecords(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品生产问题记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["recordsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateRecords(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          openAdd.value = false;
          getList();
        });
      } else {
        addRecords(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          openAdd.value = falsel;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除？').then(function() {
    return delRecords(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/records/export', {
    ...queryParams.value
  }, `records_${new Date().getTime()}.xlsx`)
}

getprojectCodeList();
getList();
</script>
