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
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--       <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['newproducts:process:add']"
        >新增</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdatea"
          v-hasPermi="['newproducts:process:updatea']"
        >上传记录文件</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdateb"
          v-hasPermi="['newproducts:process:updateb']"
        >上传总结文件</el-button>
      </el-col>      
<!--       <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['newproducts:process:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['newproducts:process:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column v-if="false" label="id" align="center" prop="id" />
      <el-table-column label="新产品名称" align="center" prop="name" />
      <el-table-column label="试验记录文件" align="center" prop="testprocess" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.testprocess" icon="Download" @click="downloadFiles(scope.row.testprocess)"></el-button>
        </template> 
      </el-table-column>       
      <el-table-column label="总结文件" align="center" prop="conclude" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.conclude" icon="Download" @click="downloadFiles(scope.row.conclude)"></el-button>
        </template> 
      </el-table-column>        
<!--       <el-table-column label="其他文件" align="center" prop="other" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.other" icon="Download" @click="downloadFiles(scope.row.other)"></el-button>
        </template> 
      </el-table-column>  -->       
      <el-table-column label="备注" align="center" prop="remark" />
<!--       <el-table-column label="" align="center" prop="a" />
      <el-table-column label="" align="center" prop="b" />
      <el-table-column label="" align="center" prop="c" />
      <el-table-column label="" align="center" prop="d" />
      <el-table-column label="" align="center" prop="e" />
      <el-table-column label="" align="center" prop="f" />
      <el-table-column label="" align="center" prop="g" />
      <el-table-column label="" align="center" prop="h" />
      <el-table-column label="" align="center" prop="i" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right"  width="200">
        <template #default="scope">
          <el-button link type="primary" icon="UploadFilled" @click="handleUpdatea(scope.row)" v-hasPermi="['newproducts:process:updatea']">上传记录文件</el-button>
          <el-button link type="primary" icon="UploadFilled" @click="handleUpdateb(scope.row)" v-hasPermi="['newproducts:process:updateb']">上传总结文件</el-button>          
          <!-- <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:process:remove']">删除</el-button> -->
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

    <!-- 添加或修改新产品试制过程对话框 -->
    <!-- 上传试验文件对话框 -->
    <el-dialog :title="titlea" v-model="opena" width="800px" append-to-body>
      <el-form ref="processRef" :model="form" :rules="rules" label-width="100px">
<!--         <el-form-item label="新产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入新产品名称" />
        </el-form-item> -->        
        <el-form-item label="试验记录文件" prop="testprocess">
          <file-upload v-model="form.testprocess"/>
        </el-form-item>        
<!--         <div v-if="users =='技术科' || users =='老实人科技' ">
        <el-form-item label="总结文件" prop="conclude">
          <file-upload v-model="form.conclude"/>
        </el-form-item>
        </div> -->
<!--         <el-form-item label="其他文件" prop="other">
          <file-upload v-model="form.other"/>
        </el-form-item> -->
<!--         <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
          <file-upload v-model="form.d"/>
        </el-form-item>
        <el-form-item label="" prop="e">
          <file-upload v-model="form.e"/>
        </el-form-item>
        <el-form-item label="" prop="f">
          <file-upload v-model="form.f"/>
        </el-form-item>
        <el-form-item label="" prop="g">
          <el-input v-model="form.g" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="h">
          <el-input v-model="form.h" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="i">
          <el-input v-model="form.i" placeholder="请输入" />
        </el-form-item> -->
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 上传总结文件对话框 -->
    <el-dialog :title="titleb" v-model="openb" width="800px" append-to-body>
      <el-form ref="processRef" :model="form" :rules="rules" label-width="100px">
<!--         <el-form-item label="新产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入新产品名称" />
        </el-form-item> -->
        <el-form-item label="总结文件" prop="conclude">
          <file-upload v-model="form.conclude"/>
        </el-form-item>
<!--         <el-form-item label="其他文件" prop="other">
          <file-upload v-model="form.other"/>
        </el-form-item> -->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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

<script setup name="Process">
import { listProcess, getProcess, delProcess, addProcess, updateProcess } from "@/api/newproducts/process";
import { getUserProfile } from "@/api/system/user";

const { proxy } = getCurrentInstance();
const processList = ref([]);
const open = ref(false);
const opena = ref(false);//初始化上传记录文件对话框
const openb = ref(false);//初始化上传总结文件对话框
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const titlea = ref("");//初始化上传记录文件对话框标题
const titleb = ref("");//初始化上传总结文件对话框标题
const users = ref({}); //  初始化 users根据其值显示不同确认框

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);


// 监听试制记录按钮路由，实时跳转
watch(
  () => proxy.$route.query,
  (newQuery) => {
    // 更新查询参数
    queryParams.value.id = newQuery.id;
    queryParams.value.name = newQuery.name;
    getList(); // 重新查询数据
  },
  { immediate: true } // 立即执行一次
);


/** 查询新产品试制过程列表 */
function getList() {
  loading.value = true;
  listProcess(queryParams.value).then(response => {
    processList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  opena.value = false;
  openb.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    name: null,
    testprocess: null,
    conclude: null,
    other: null,
    remark: null,
/*     a: null,
    b: null,
    c: null,
    d: null,
    e: null,
    f: null,
    g: null,
    h: null,
    i: null */
  };
  proxy.resetForm("processRef");
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
/* function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加新产品试制过程";
} */

/** 修改按钮操作 */
/* 上传试制记录文件按钮 */
function handleUpdatea(row) {
  reset();
/*   getUserProfile().then(response => {
  users.value = response.data.dept.deptName;  //获得用户部门
  }); */
/*   if(users == '技术科' && !queryParams.testprocess) {
    proxy.$modal.msgError("请先上传试验记录文件");
  } else { */
  const _id = row.id || ids.value
  getProcess(_id).then(response => {
    form.value = response.data;
    opena.value = true;
    titlea.value = "上传试制过程记录文件";
  });
  //}
}

/* 上传总结文件按钮 */
function handleUpdateb(row) {
  reset();
/*   getUserProfile().then(response => {
  users.value = response.data.dept.deptName;  //获得用户部门
  }); */
  if(!row.testprocess) {
    proxy.$modal.msgError("请先上传试验记录文件");
  } else {
  const _id = row.id || ids.value
  getProcess(_id).then(response => {
    form.value = response.data;
    openb.value = true;
    titleb.value = "上传总结文件";
  });
  }
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["processRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProcess(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          opena.value = false;
          openb.value = false;
          getList();
        });
      } else {
        addProcess(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          opena.value = false;
          openb.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
/* function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除？').then(function() {
    return delProcess(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
} */

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/process/export', {
    ...queryParams.value
  }, `process_${new Date().getTime()}.xlsx`)
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
