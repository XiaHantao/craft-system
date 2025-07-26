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
      </el-form-item> -->

      <el-form-item label="提交时间" prop="submitTime">
        <el-date-picker clearable
          v-model="queryParams.submitTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择提交时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="核对状态" prop="checkStatus">
        <el-select v-model="queryParams.checkStatus" placeholder="请选择核对状态" clearable style="width: 200px">
          <el-option
            v-for="dict in np_file_check"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--       <el-form-item label="扩展字段1" prop="extField1">
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
          v-hasPermi="['newproducts:files:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['newproducts:files:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['newproducts:files:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['newproducts:files:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="filesList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="项目编号" align="center" prop="projectCode" />
      <!-- <el-table-column label="项目名称" align="center" prop="projectName" /> -->
      <el-table-column label="任务单文件" align="center" prop="taskOrderFile" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.taskOrderFile" icon="Download" @click="downloadFiles(scope.row.taskOrderFile)">
          </el-button>
        </template>
      </el-table-column>            
      <el-table-column label="技术交底文件" align="center" prop="techDisclosureFile" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.techDisclosureFile" icon="Download" @click="downloadFiles(scope.row.techDisclosureFile)">
          </el-button>
        </template>
      </el-table-column>       
      <el-table-column label="BOM文件" align="center" prop="bomFile" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.bomFile" icon="Download" @click="downloadFiles(scope.row.bomFile)">
          </el-button>
        </template>
      </el-table-column>       
      <el-table-column label="其他文件" align="center" prop="otherFiles" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.otherFiles" icon="Download" @click="downloadFiles(scope.row.otherFiles)">
          </el-button>
        </template>
      </el-table-column> 
      <el-table-column label="提交备注" align="center" prop="submitRemarks" />
      <el-table-column label="提交时间" align="center" prop="submitTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="核对文件" align="center" prop="checkFile" >
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.checkFile" icon="Download" @click="downloadFiles(scope.row.checkFile)">
          </el-button>
        </template>
      </el-table-column> 
      <el-table-column label="核对状态" align="center" prop="checkStatus">
        <template #default="scope">
          <dict-tag :options="np_file_check" :value="scope.row.checkStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="核对备注" align="center" prop="checkRemarks" />
      <el-table-column label="核对时间" align="center" prop="checkTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--       <el-table-column label="扩展字段1" align="center" prop="extField1" />
      <el-table-column label="扩展字段2" align="center" prop="extField2" />
      <el-table-column label="扩展字段3" align="center" prop="extField3" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right"  width="200">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:files:edit']">上传文件</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:files:remove']">删除</el-button>
          <el-button link type="primary" icon="check" @click="CheckTask(scope.row)" v-if = "['1','2'].includes(scope.row.checkStatus) " v-hasPermi="['newproducts:files:checktask']">审核任务单</el-button>
          <el-button link type="primary" icon="check" @click="CheckFiles(scope.row)" v-if = " ['3','4','5'].includes(scope.row.checkStatus) " v-hasPermi="['newproducts:files:checkfiles']">核对文件</el-button>
        <el-button link type="primary" icon="check" @click="openExcelPreviewDialog(scope.row.taskOrderFile)" v-hasPermi="['newproducts:files:checktask']">任务单预览</el-button>
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

    <!-- 添加或修改新产品文件提交对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="filesRef" :model="form" :rules="rules" label-width="80px">
<!--         <el-form-item label="项目编号" prop="projectCode">
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
        </el-form-item>   --> 
<!--         <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item> -->
        <el-form-item label="任务单文件" prop="taskOrderFile">
          <file-upload v-model="form.taskOrderFile"/>
        </el-form-item>
        <el-form-item label="技术交底文件" prop="techDisclosureFile">
          <file-upload v-model="form.techDisclosureFile"/>
        </el-form-item>
        <el-form-item label="BOM文件" prop="bomFile">
          <file-upload v-model="form.bomFile"/>
        </el-form-item>
        <el-form-item label="其他文件" prop="otherFiles">
          <file-upload v-model="form.otherFiles"/>
        </el-form-item>
        <el-form-item label="提交备注" prop="submitRemarks">
          <el-input v-model="form.submitRemarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>
<!--         <el-form-item label="提交时间" prop="submitTime">
          <el-date-picker clearable
            v-model="form.submitTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择提交时间">
          </el-date-picker>
        </el-form-item> -->
<!--         <el-form-item label="核对文件" prop="checkFile">
          <file-upload v-model="form.checkFile"/>
        </el-form-item>
        <el-form-item label="核对状态"  prop="checkStatus">
          <el-select v-model="form.checkStatus" placeholder="请选择核对状态">
            <el-option
              v-for="dict in np_file_check"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="核对备注" prop="checkRemarks">
          <el-input v-model="form.checkRemarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="核对时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择核对时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="扩展字段1" prop="extField1">
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
      <el-form ref="filesRef" :model="form" :rules="rules" label-width="80px">
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
        <el-form-item label="提交时间" prop="submitTime">
          <el-date-picker clearable
            v-model="form.submitTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择提交时间">
          </el-date-picker>
        </el-form-item>                
     </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>       

    <!-- 审核任务单对话框 -->
    <el-dialog :title="title" v-model="openCheckTask" width="500px" append-to-body>
      <el-form ref="filesRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="核对状态" prop="checkStatus">
            <el-radio-group v-model="form.checkStatus">
                <el-radio label = "3">通过</el-radio>
                <el-radio label = "2">不通过</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="核对备注" prop="checkRemarks">
          <el-input v-model="form.checkRemarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>        
     </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>    
<!-- 核对文件对话框 -->
    <el-dialog :title="title" v-model="openCheckFiles" width="500px" append-to-body>
      <el-form ref="filesRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="核对状态" prop="checkStatus">
            <el-radio-group v-model="form.checkStatus">
                <el-radio label = "4">通过</el-radio>
                <el-radio label = "5">不通过</el-radio>
            </el-radio-group>
        </el-form-item>
     </el-form>
     <el-form-item label="核对文件上传" prop="checkFile">
          <file-upload v-model="form.checkFile"/>
        </el-form-item>
        <el-form-item label="核对备注" prop="checkRemarks">
          <el-input v-model="form.checkRemarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="核对时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择核对时间">
          </el-date-picker>
        </el-form-item>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>   

    <!-- Excel 预览对话框 -->
    <el-dialog v-model="excelPreviewDialogVisible"
      title="Excel 预览"
      width="80%"
      :center="true"
      append-to-body
      :before-close="closeExcelPreviewDialog">

      <vue-office-docx v-if="isExcelPreview" :src="previewSrc"  style="height: 80vh;"/>
    </el-dialog>

  </div>
</template>



<script setup name="Files">
import { listFiles, getFiles, delFiles, addFiles, updateFiles } from "@/api/newproducts/files";
import { listCreate } from "@/api/newproducts/create";
//引入相关样式
import '@vue-office/docx/lib/index.css';
import '@vue-office/excel/lib/index.css';
// 引入 VueOffice 组件
import VueOfficeDocx from '@vue-office/docx';
import VueOfficeExcel from '@vue-office/excel';
import VueOfficePdf from '@vue-office/pdf';
import { reactive, ref } from "vue";

const { proxy } = getCurrentInstance();
const { np_file_check } = proxy.useDict('np_file_check');

const projectCodeList =ref([]);//项目编号列表
const filesList = ref([]);
const open = ref(false);
const openAdd = ref(false);//新增对话框
const openCheckTask =ref(false);//审核任务单对话框
const openCheckFiles =ref(false);//审核文件对话框
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");



const options = reactive(
  {
        xls: false,       //预览xlsx文件设为false；预览xls文件设为true
        minColLength: 0,  // excel最少渲染多少列，如果想实现xlsx文件内容有几列，就渲染几列，可以将此值设置为0.
        minRowLength: 0,  // excel最少渲染多少行，如果想实现根据xlsx实际函数渲染，可以将此值设置为0.
        widthOffset: 10,  //如果渲染出来的结果感觉单元格宽度不够，可以在默认渲染的列表宽度上再加 Npx宽
        heightOffset: 10, //在默认渲染的列表高度上再加 Npx高
        beforeTransformData: (workbookData) => {return workbookData}, //底层通过exceljs获取excel文件内容，通过该钩子函数，可以对获取的excel文件内容进行修改，比如某个单元格的数据显示不正确，可以在此自行修改每个单元格的value值。
        transformData: (workbookData) => {return workbookData}, //将获取到的excel数据进行处理之后且渲染到页面之前，可通过transformData对即将渲染的数据及样式进行修改，此时每个单元格的text值就是即将渲染到页面上的内容
      }
);

const  excelPreviewDialogVisible = ref(false);
const isExcelPreview= ref(false);
const previewSrc = ref('');

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    projectCode: null,
    projectName: null,
    submitTime: null,
    checkStatus: null,
    extField1: null,
    extField2: null,
    extField3: null
  },
  rules: {
    projectCode: [
      { required: true, message: "项目编号不能为空", trigger: "blur" }
    ],
    submitTime: [
      { required: true, message: "提交时间不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询新产品文件提交列表 */
function getList() {
  loading.value = true;
  listFiles(queryParams.value).then(response => {
    filesList.value = response.rows;
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
  openCheckTask.value = false;
  openCheckFiles.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    projectCode: null,
    projectName: null,
    taskOrderFile: null,
    techDisclosureFile: null,
    bomFile: null,
    otherFiles: null,
    submitRemarks: null,
    submitTime: null,
    checkFile: null,
    checkStatus: null,
    checkRemarks: null,
    checkTime: null,
    extField1: null,
    extField2: null,
    extField3: null
  };
  proxy.resetForm("filesRef");
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
  title.value = "添加新产品文件提交";
}

// 审核任务单按钮操作
function CheckTask(row) {
  reset();
  const _id = row.id || ids.value
  getFiles(_id).then(response => {
    form.value = response.data;
    openCheckTask.value = true;
    title.value = "审核任务单";
  });
}

// 核对文件按钮操作
function CheckFiles(row){
  reset();
  const _id = row.id || ids.value
  getFiles(_id).then(response => {
    form.value = response.data;
    openCheckFiles.value =true;
    title.value = "核对文件";
  })
}


/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getFiles(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品文件提交";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["filesRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateFiles(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          openAdd.value = false;
          openCheckTask.value = false;
          openCheckFiles.value = false;
          getList();
        });
      } else {
        
        // 检查是否上传文件
/*         if(form.value.taskOrderFile !== null){
          form.value.checkStatus = 1;
        }else{
          form.value.checkStatus = 0;
        } */
          form.value.checkStatus = 1;
        addFiles(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          openAdd.value = false;
          openCheckTask.value = false;
          openCheckFiles.value = false;
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
    return delFiles(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/files/export', {
    ...queryParams.value
  }, `files_${new Date().getTime()}.xlsx`)
}

// 任务单预览
  function openExcelPreviewDialog(filePath) {
      this.previewSrc = "http://172.19.3.63:8080"+filePath;
      this.isExcelPreview = true;
      this.excelPreviewDialogVisible = true;
    }
  function closeExcelPreviewDialog(done) {
      this.isExcelPreview = false;
      this.previewSrc = '';
      done();
    }

getprojectCodeList();
getList();
</script>
