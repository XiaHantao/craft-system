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
    </el-row>

    <el-table v-loading="loading" :data="displayList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="项目编号" align="center" prop="projectCode" />
      <!-- <el-table-column label="项目名称" align="center" prop="projectName" /> -->
      <el-table-column label="任务单展示文件" align="center" prop="taskOrderDisplay" />
      <el-table-column label="技术交底展示文件" align="center" prop="techDisclosureDisplay" />
      <el-table-column label="BOM展示文件" align="center" prop="bomDisplay" />
      <el-table-column label="其他提交展示文件" align="center" prop="otherSubmissionDisplay" />
      <el-table-column label="核对展示文件" align="center" prop="verificationDisplay" />
      <el-table-column label="BOM质检结果展示文件" align="center" prop="bomInspectionDisplay" />
      <el-table-column label="生产问题记录展示文件" align="center" prop="productionIssueDisplay" />
      <el-table-column label="生产总结展示文件" align="center" prop="productionSummaryDisplay" />
      <el-table-column label="强化试验方案展示文件" align="center" prop="enhancementPlanDisplay" />
      <el-table-column label="强化试验结果展示文件" align="center" prop="enhancementResultDisplay" />
      <el-table-column label="TR4/TR5总结展示文件" align="center" prop="trSummaryDisplay" />
      <el-table-column label="内部上市报告展示文件" align="center" prop="launchReportDisplay" />

<!--       <el-table-column label="扩展字段1" align="center" prop="extField1" />
      <el-table-column label="扩展字段2" align="center" prop="extField2" />
      <el-table-column label="扩展字段3" align="center" prop="extField3" /> -->

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:display:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:display:remove']">删除</el-button>
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

getprojectCodeList();
getList();
</script>
