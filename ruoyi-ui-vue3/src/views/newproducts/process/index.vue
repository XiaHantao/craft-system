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
          @click="handleUpdate"
          v-hasPermi="['newproducts:process:edit']"
        >修改</el-button>
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
      <el-table-column label="试验记录文件" align="center" prop="testprocess" />
      <el-table-column label="总结文件" align="center" prop="conclude" />
      <el-table-column label="其他文件" align="center" prop="other" />
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:process:edit']">修改</el-button>
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
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="processRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="新产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入新产品名称" />
        </el-form-item>
        <el-form-item label="试验记录文件" prop="testprocess">
          <file-upload v-model="form.testprocess"/>
        </el-form-item>
        <el-form-item label="总结文件" prop="conclude">
          <file-upload v-model="form.conclude"/>
        </el-form-item>
        <el-form-item label="其他文件" prop="other">
          <file-upload v-model="form.other"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
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
  </div>
</template>

<script setup name="Process">
import { listProcess, getProcess, delProcess, addProcess, updateProcess } from "@/api/newproducts/process";

const { proxy } = getCurrentInstance();
const processList = ref([]);
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
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcess(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品试制过程";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["processRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProcess(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcess(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
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

getList();
</script>
