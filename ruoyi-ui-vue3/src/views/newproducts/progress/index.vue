<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目编号" prop="projectCode">
        <el-input v-model="queryParams.projectCode" placeholder="请输入项目编号" clearable @keyup.enter="handleQuery" />
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
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['newproducts:progress:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['newproducts:progress:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['newproducts:progress:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['newproducts:progress:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="progressList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="项目编号" align="center" prop="projectCode" />
      <!-- <el-table-column label="项目名称" align="center" prop="projectName" /> -->
      <el-table-column label="备料" align="center" prop="materialPreparation" />
      <el-table-column label="金工" align="center" prop="metalworking" />
      <el-table-column label="门架结构" align="center" prop="mastStructure" />
      <el-table-column label="车架结构" align="center" prop="frameStructure" />
      <el-table-column label="涂装" align="center" prop="painting" />
      <el-table-column label="门架装配" align="center" prop="mastAssembly" />
      <el-table-column label="车体装配" align="center" prop="bodyAssembly" />
      <el-table-column label="试车" align="center" prop="testDrive" />

      <!--       <el-table-column label="扩展字段1" align="center" prop="extField1" />
      <el-table-column label="扩展字段2" align="center" prop="extField2" />
      <el-table-column label="扩展字段3" align="center" prop="extField3" /> -->

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['newproducts:progress:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['newproducts:progress:remove']">删除</el-button>
          <el-button link type="primary" icon="DataAnalysis" @click="handleDetail(scope.row)"
            v-hasPermi="['newproducts:progress:view']">详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改新产品生产进度跟踪对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="progressRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目编号" prop="projectCode">
          <el-select v-model="form.projectCode" aria-placeholder="请选择项目编号！" clearable filterable
            @keyup.enter="handleQuery">
            <el-option v-for="model in projectCodeList" :key="model.projectCode" :label="model.projectCode"
              :value="model.projectCode"></el-option>
          </el-select>
        </el-form-item>

        <!--         <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item> -->

        <el-form-item label="备料" prop="materialPreparation">
          <el-input v-model="form.materialPreparation" placeholder="请输入备料进度（小数形式）" />
        </el-form-item>
        <el-form-item label="金工" prop="metalworking">
          <el-input v-model="form.metalworking" placeholder="请输入金工进度（小数形式）" />
        </el-form-item>
        <el-form-item label="门架结构" prop="mastStructure">
          <el-input v-model="form.mastStructure" placeholder="请输入门架结构进度（小数形式）" />
        </el-form-item>
        <el-form-item label="车架结构" prop="frameStructure">
          <el-input v-model="form.frameStructure" placeholder="请输入车架结构进度（小数形式）" />
        </el-form-item>
        <el-form-item label="涂装" prop="painting">
          <el-input v-model="form.painting" placeholder="请输入涂装进度（小数形式）" />
        </el-form-item>
        <el-form-item label="门架装配" prop="mastAssembly">
          <el-input v-model="form.mastAssembly" placeholder="请输入门架装配进度（小数形式）" />
        </el-form-item>
        <el-form-item label="车体装配" prop="bodyAssembly">
          <el-input v-model="form.bodyAssembly" placeholder="请输入车体装配进度（小数形式）" />
        </el-form-item>
        <el-form-item label="试车" prop="testDrive">
          <el-input v-model="form.testDrive" placeholder="请输入试车进度（小数形式）" />
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
  </div>
</template>

<script setup name="Progress">
import { listProgress, getProgress, delProgress, addProgress, updateProgress } from "@/api/newproducts/progress";
import { listCreate } from "@/api/newproducts/create";


const { proxy } = getCurrentInstance();

const projectCodeList =ref([]);//项目编号列表
const progressList = ref([]);
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

/** 查询新产品生产进度跟踪列表 */
function getList() {
  loading.value = true;
  listProgress(queryParams.value).then(response => {
    progressList.value = response.rows;
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
    materialPreparation: null,
    metalworking: null,
    mastStructure: null,
    frameStructure: null,
    painting: null,
    mastAssembly: null,
    bodyAssembly: null,
    testDrive: null,
    extField1: null,
    extField2: null,
    extField3: null
  };
  proxy.resetForm("progressRef");
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
  title.value = "添加新产品生产进度跟踪";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProgress(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品生产进度跟踪";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["progressRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProgress(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProgress(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除？').then(function() {
    return delProgress(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/progress/export', {
    ...queryParams.value
  }, `progress_${new Date().getTime()}.xlsx`)
}
function handleDetail(row) {
  const projectCode = row.projectCode;
  // 跳转到图表页面并传递项目编号
  proxy.$router.push({
    path: '/newproducts/progress/charts',
    query: { projectCode }
  });
}
getprojectCodeList();
getList();
</script>
