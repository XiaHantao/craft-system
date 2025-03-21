<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="新产品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入新产品名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交日期" prop="submissionDate">
        <el-date-picker clearable
          v-model="queryParams.submissionDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择提交日期">
        </el-date-picker>
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
          v-hasPermi="['newproducts:submit:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['newproducts:submit:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['newproducts:submit:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['newproducts:submit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="submitList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column v-if="false" label="主键ID" align="center" prop="id" />
      <el-table-column label="新产品名称" align="center" prop="name" />
      <el-table-column label="任务单文件" align="center" prop="task">
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.task" icon="Download" @click="downloadFiles(scope.row.task)">
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="技术交底文件" align="center" prop="technical" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.technical" icon="Download" @click="downloadFiles(scope.row.technical)"></el-button>
        </template>
      </el-table-column>  
      <el-table-column label="BOM文件" align="center" prop="bom" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.bom" icon="Download" @click="downloadFiles(scope.row.bom)"></el-button>
        </template>
      </el-table-column>  
      <el-table-column label="其他文件" align="center" prop="other" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.other" icon="Download" @click="downloadFiles(scope.row.other)"></el-button>
        </template> 
      </el-table-column>  
      <el-table-column label="提交日期" align="center" prop="submissionDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.submissionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="核对文件" align="center" prop="checking" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.checking" icon="Download" @click="downloadFiles(scope.row.checking)"></el-button>
        </template> 
      </el-table-column> 
      <el-table-column label="核对结果" align="center" prop="checked" >
        <template #default="scope">
          <el-tag :type="scope.row.checked === '通过' ? 'success' : 'danger'" >
            {{ scope.row.checked }}
          </el-tag>
        </template>
      </el-table-column>  
      <el-table-column label="核对备注" align="center" prop="checkremark" />
<!--       <el-table-column label="" align="center" prop="a" />
      <el-table-column label="" align="center" prop="b" />
      <el-table-column label="" align="center" prop="c" />
      <el-table-column label="" align="center" prop="d" />
      <el-table-column label="" align="center" prop="e" />
      <el-table-column label="" align="center" prop="f" />
      <el-table-column label="" align="center" prop="g" />
      <el-table-column label="" align="center" prop="h" />
      <el-table-column label="" align="center" prop="i" />
      <el-table-column label="" align="center" prop="j" />
      <el-table-column label="" align="center" prop="k" />
      <el-table-column label="" align="center" prop="l" />
      <el-table-column label="" align="center" prop="m" />
      <el-table-column label="" align="center" prop="n" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:submit:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:submit:remove']">删除</el-button>
          <el-button link type="primary" icon="Check" @click="handleCheck(scope.row)" v-hasPermi="['newproducts:submit:edit']">核对</el-button>
          <el-button link type="primary" icon="UploadFilled" @click="handleRecord(scope.row)" v-hasPermi="['newproducts:submit:edit']">试制记录</el-button>
        </template>
      </el-table-column>
    </el-table>
    
   <!--  分页显示 -->
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改新产品提交对话框 -->
    <el-dialog :title="title" v-model="openEditDialog" width="800px" append-to-body>
      <el-form ref="submitRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="新产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入新产品名称" />
        </el-form-item>
        <el-form-item label="任务单文件" prop="task">
          <file-upload v-model="form.task"/>
        </el-form-item>
        <el-form-item label="技术交底文件" prop="technical">
          <file-upload v-model="form.technical"/>
        </el-form-item>
        <el-form-item label="BOM文件" prop="bom">
          <file-upload v-model="form.bom"/>
        </el-form-item>
        <el-form-item label="其他文件" prop="other">
          <file-upload v-model="form.other"/>
        </el-form-item>
        <el-form-item label="提交日期" prop="submissionDate">
          <el-date-picker clearable
            v-model="form.submissionDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择提交日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
<!--         <el-form-item label="核对文件" prop="checking">
          <file-upload v-model="form.checking"/>
        </el-form-item>
        <el-form-item label="核对结果" prop="checked">
          <el-input v-model="form.checked" placeholder="请输入核对结果" />
        </el-form-item>
        <el-form-item label="核对备注" prop="checkremark">
          <el-input v-model="form.checkremark" placeholder="请输入核对备注" />
        </el-form-item> -->

<!--         <el-form-item label="" prop="a">
          <el-input v-model="form.a" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="b">
          <el-input v-model="form.b" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="c">
          <el-input v-model="form.c" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="d">
          <el-input v-model="form.d" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="e">
          <file-upload v-model="form.e"/>
        </el-form-item>
        <el-form-item label="" prop="f">
          <file-upload v-model="form.f"/>
        </el-form-item>
        <el-form-item label="" prop="g">
          <file-upload v-model="form.g"/>
        </el-form-item>
        <el-form-item label="" prop="h">
          <file-upload v-model="form.h"/>
        </el-form-item>
        <el-form-item label="" prop="i">
          <el-input v-model="form.i" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="j">
          <el-input v-model="form.j" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="k">
          <el-input v-model="form.k" placeholder="请输入" />
        </el-form-item>submitForm
        <el-form-item label="" prop="l">
          <el-input v-model="form.l" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="m">
          <el-input v-model="form.m" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="n">
          <el-input v-model="form.n" placeholder="请输入" />
        </el-form-item> -->
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 核对对话框 -->
    <el-dialog :title="checkTitle" v-model="openCheckDialog" width="800px" append-to-body>
      <el-form ref="submitRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="核对文件" prop="checking">
          <file-upload v-model="form.checking"/>
        </el-form-item>
<!--   下拉框形式
       <el-form-item label="核对结果" prop="checked">
          <el-select v-model="form.checked" placeholder="请选择核对结果">
            <el-option
              v-for="item in checkOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>  -->  
        <!-- 单选框形式 -->
        <el-form-item label="核对结果" prop="checked">
            <el-radio-group v-model="form.checked">
            <el-radio label="通过" />
            <el-radio label="不通过" />
            </el-radio-group>
        </el-form-item>

        <el-form-item label="核对备注" prop="checkremark">
          <el-input v-model="form.checkremark" placeholder="请输入核对备注" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitCheckForm">确 定</el-button>
          <el-button @click="cancelCheck">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Submit">
import { listSubmit, getSubmit, delSubmit, addSubmit, updateSubmit } from "@/api/newproducts/submit";


const { proxy } = getCurrentInstance();
const submitList = ref([]);
const openEditDialog = ref(false);
const openCheckDialog = ref(false);

const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const checkTitle = ref("");

/* 添加核对结果的可选项
const checkOptions = ref([
  { value: '通过', label: '通过' },
  { value: '未通过', label: '未通过' }
]); */

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    submissionDate: null,
  },
  rules: {
    name: [
      { required: true, message: "新产品名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询新产品提交列表 */
function getList() {
  loading.value = true;
  listSubmit(queryParams.value).then(response => {
    submitList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  openEditDialog.value = false;
  reset();
}


// 表单重置
function reset() {
  form.value = {
    id: null,
    name: null,
    task: null,
    technical: null,
    bom: null,
    other: null,
    submissionDate: null,
    //createBy: null,
    //createTime: null,
    remark: null,
    checking: null,
    checked: null,
    checkremark: null,
/*     a: null,
    b: null,
    c: null,
    d: null,
    e: null,
    f: null,
    g: null,
    h: null,
    i: null,
    j: null,
    k: null,
    l: null,
    m: null,
    n: null */
  };
  proxy.resetForm("submitRef");
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
  openEditDialog.value = true;
  title.value = "添加新产品提交";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getSubmit(_id).then(response => {
    form.value = response.data;
    openEditDialog.value = true;
    title.value = "修改新产品提交";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["submitRef"].validate(valid => {   
    if (valid) {     
      if (form.value.id != null) {
        updateSubmit(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          openEditDialog.value = false;
          getList();         
        });
      } else {       
        addSubmit(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          openEditDialog.value = false;
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
    return delSubmit(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/submit/export', {
    ...queryParams.value
  }, `submit_${new Date().getTime()}.xlsx`)
}

/** 核对按钮操作 */
function handleCheck(row) {
  reset();
  // const _id = row.id || ids.value;
  // getSubmit(_id).then(response => {
    openCheckDialog.value = true;
    checkTitle.value = "核对新产品提交";
    form.value.id = row.id;
  // });
}
/* 核对提交按钮 */
function submitCheckForm() {
  proxy.$refs["submitRef"].validate(valid => {
  //if(valid){  
    if (form.value.id != null) {
      updateSubmit(form.value).then(response => {
        proxy.$modal.msgSuccess("核对完成");
        openCheckDialog.value = false;
        getList();
      });
   // } 
    }
  });
}

// 取消核对对话框
function cancelCheck() {
  openCheckDialog.value = false;
  reset();
}

// 试制记录按钮操作
function handleRecord(row) {
  form.value.checked = row.checked;
  if (form.value.checked == '通过') {
  
  // 跳转到试制记录页面，并传递当前行的 id 作为参数
  proxy.$router.push({
    path: '/newproducts/process',
    query: {
      id: row.id, // 传递当前行的 id 作为参数
      name: row.name
    }
  });
  }else {
    proxy.$modal.msgError("请先通过核对!");
  }
}
/** 多文件下载 */
function downloadFiles(urls) {
  if (typeof urls === 'string') {
    urls = urls.split(',');
  }
  if (!Array.isArray(urls)) {
    console.error('urls 必须是数组或逗号分隔的字符串');
    return;
  }
  urls.forEach(url => {
    fetch(url)
      .then(response => response.blob())
      .then(blob => {
        const downloadUrl = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadUrl;
        // 修改文件名解码逻辑
        const encodedFilename = url.split('/').pop();
        const decodedFilename = decodeURIComponent(encodedFilename);
        link.setAttribute('download', decodedFilename);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        window.URL.revokeObjectURL(downloadUrl);
      })
      .catch(error => console.error('下载错误:', error));
  });
}

getList();
</script>
