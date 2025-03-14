<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="testOne">
        <el-input
          v-model="queryParams.testOne"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="testTwo">
        <el-input
          v-model="queryParams.testTwo"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="testThree">
        <el-input
          v-model="queryParams.testThree"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label=" ${comment}" prop="testFour">
        <el-input
          v-model="queryParams.testFour"
          placeholder="请输入${comment}" 
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
          v-hasPermi="['system:test:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:test:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:test:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['system:test:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="用户管理" name="first">
        <el-table :data="userData" style="width: 100%">
          <el-table-column prop="name" label="姓名" width="180"></el-table-column>
          <el-table-column prop="age" label="年龄" width="180"></el-table-column>
          <el-table-column prop="address" label="地址"></el-table-column>
        </el-table>
        <el-button type="primary" @click="dialogVisible = true">打开对话框</el-button>


      </el-tab-pane>

      <el-tab-pane label="配置管理" name="second">配置管理</el-tab-pane>
      <el-tab-pane label="角色管理" name="third">角色管理</el-tab-pane>
      <el-tab-pane label="定时任务补偿" name="fourth">定时任务补偿</el-tab-pane>
    </el-tabs>

    <el-table v-loading="loading" :data="testList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="123" align="center" prop="testId" />
      <el-table-column label="234" align="center" prop="testOne" />
      <el-table-column label="${comment}" align="center" prop="testTwo" />
      <el-table-column label="${comment}" align="center" prop="testThree" />
      <el-table-column label="${comment}" align="center" prop="testFour" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:test:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['system:test:remove']">删除</el-button>
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="testRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="testOne">
          <el-input v-model="form.testOne" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="testTwo">
          <el-input v-model="form.testTwo" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="testThree">
          <el-input v-model="form.testThree" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="testFour">
          <el-input v-model="form.testFour" placeholder="请输入${comment}" />
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

<script setup name="Test">
import { listTest, getTest, delTest, addTest, updateTest } from "@/api/system/test";

const { proxy } = getCurrentInstance();

const testList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dialogVisible = ref(false);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    testOne: null,
    testTwo: null,
    testThree: null,
    testFour: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询【请填写功能名称】列表 */
function getList() {
  loading.value = true;
  listTest(queryParams.value).then(response => {
    testList.value = response.rows;
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
    testId: null,
    testOne: null,
    testTwo: null,
    testThree: null,
    testFour: null
  };
  proxy.resetForm("testRef");
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
  ids.value = selection.map(item => item.testId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加【请填写功能名称】";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _testId = row.testId || ids.value
  getTest(_testId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改【请填写功能名称】";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["testRef"].validate(valid => {
    if (valid) {
      if (form.value.testId != null) {
        updateTest(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addTest(form.value).then(response => {
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
  const _testIds = row.testId || ids.value;
  proxy.$modal.confirm('是否确认删除【请填写功能名称】编号为"' + _testIds + '"的数据项？').then(function() {
    return delTest(_testIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/test/export', {
    ...queryParams.value
  }, `test_${new Date().getTime()}.xlsx`)
}

getList();
</script>
