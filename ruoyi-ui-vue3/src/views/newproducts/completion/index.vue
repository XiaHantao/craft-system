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
          v-hasPermi="['newproducts:completion:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['newproducts:completion:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['newproducts:completion:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['newproducts:completion:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="completionList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="项目编号" align="center" prop="projectCode" />
      <!-- <el-table-column label="项目名称" align="center" prop="projectName" /> -->
      <el-table-column label="强化试验方案文件" align="center" prop="enhancementTestPlan" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.enhancementTestPlan" icon="Download" @click="downloadFiles(scope.row.enhancementTestPlan)">
          </el-button>
        </template>        
      </el-table-column>
      <el-table-column label="强化试验结果文件" align="center" prop="enhancementTestResult" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.enhancementTestResult" icon="Download" @click="downloadFiles(scope.row.enhancementTestResult)">
          </el-button>
        </template>        
      </el-table-column>
      <el-table-column label="TR4/TR5总结文件" align="center" prop="tr4Tr5Summary" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.tr4Tr5Summary" icon="Download" @click="downloadFiles(scope.row.tr4Tr5Summary)">
          </el-button>
        </template>      
      </el-table-column>
      <el-table-column label="内部上市报告文件" align="center" prop="internalLaunchReport" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.internalLaunchReport" icon="Download" @click="downloadFiles(scope.row.internalLaunchReport)">
          </el-button>
        </template>        
      </el-table-column>

<!--       <el-table-column label="扩展字段1" align="center" prop="extField1" />
      <el-table-column label="扩展字段2" align="center" prop="extField2" />
      <el-table-column label="扩展字段3" align="center" prop="extField3" /> -->

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:completion:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:completion:remove']">删除</el-button>
          <el-button link type="primary" icon="Edit" @click="TechnicalDocuments(scope.row)" v-hasPermi="['newproducts:completion:technical']">技术文件</el-button>
          <el-button link type="primary" icon="Edit" @click="ProductionDocuments(scope.row)" v-hasPermi="['newproducts:completion:production']">生产文件</el-button>
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

    <!-- 添加或修改新产品生产完成对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="completionRef" :model="form" :rules="rules" label-width="80px">
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

        <!-- <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item> -->

<!--         <el-form-item label="强化试验方案文件" prop="enhancementTestPlan">
          <file-upload v-model="form.enhancementTestPlan"/>
        </el-form-item>
        <el-form-item label="强化试验结果文件" prop="enhancementTestResult">
          <file-upload v-model="form.enhancementTestResult"/>
        </el-form-item>
        <el-form-item label="TR4/TR5总结文件" prop="tr4Tr5Summary">
          <file-upload v-model="form.tr4Tr5Summary"/>
        </el-form-item>
        <el-form-item label="内部上市报告文件" prop="internalLaunchReport">
          <file-upload v-model="form.internalLaunchReport"/>
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

    <!-- 技术科上传文件对话框 -->
    <el-dialog :title="title" v-model="opentechnicalDocuments" width="500px" append-to-body>
      <el-form ref="completionRef" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="强化试验方案文件" prop="enhancementTestPlan">
          <file-upload v-model="form.enhancementTestPlan"/>
        </el-form-item>
        <el-form-item label="TR4/TR5总结文件" prop="tr4Tr5Summary">
          <file-upload v-model="form.tr4Tr5Summary"/>
        </el-form-item>
        <el-form-item label="内部上市报告文件" prop="internalLaunchReport">
          <file-upload v-model="form.internalLaunchReport"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 生产科上传文件对话框 -->
    <el-dialog :title="title" v-model="openproductionDocuments" width="500px" append-to-body>
      <el-form ref="completionRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="强化试验结果文件" prop="enhancementTestResult">
          <file-upload v-model="form.enhancementTestResult"/>
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

<script setup name="Completion">
import { listCompletion, getCompletion, delCompletion, addCompletion, updateCompletion } from "@/api/newproducts/completion";
import { listCreate } from "@/api/newproducts/create";
import { ref } from "vue";

const { proxy } = getCurrentInstance();

const projectCodeList =ref([]);//项目编号列表
const completionList = ref([]);
const open = ref(false);
const opentechnicalDocuments = ref(false);//技术文件上传对话框
const openproductionDocuments = ref(false);//生产文件上传对话框
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

/** 查询新产品生产完成列表 */
function getList() {
  loading.value = true;
  listCompletion(queryParams.value).then(response => {
    completionList.value = response.rows;
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
  opentechnicalDocuments.value = false;
  openproductionDocuments.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    projectCode: null,
    projectName: null,
    enhancementTestPlan: null,
    enhancementTestResult: null,
    tr4Tr5Summary: null,
    internalLaunchReport: null,
    extField1: null,
    extField2: null,
    extField3: null
  };
  proxy.resetForm("completionRef");
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
  title.value = "添加新产品生产完成";
}

/** 技术文件按钮操作 */
function TechnicalDocuments(row) {
  reset();
  const _id = row.id || ids.value
  getCompletion(_id).then(response => {
    form.value = response.data;
    opentechnicalDocuments.value = true;
    title.value = "技术科上传文件";
  });
}

/** 生产文件按钮操作 */
function ProductionDocuments(row) {
  reset();
  const _id = row.id || ids.value
  getCompletion(_id).then(response => {
    form.value = response.data;
    openproductionDocuments.value = true;
    title.value = "生产科上传文件";
  });
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCompletion(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品生产完成";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["completionRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) { console.log(form.value);
        updateCompletion(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          opentechnicalDocuments.value = false;
          openproductionDocuments.value = false;
          getList();
        });
      } else {
        addCompletion(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          opentechnicalDocuments.value = false;
          openproductionDocuments.value = false;
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
    return delCompletion(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/completion/export', {
    ...queryParams.value
  }, `completion_${new Date().getTime()}.xlsx`)
}

getprojectCodeList();
getList();
</script>
