<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="108px">
      <el-form-item label="项目编号" prop="projectCode">
        <el-input
          v-model="queryParams.projectCode"
          placeholder="请输入项目编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
<el-form-item label="计划开始日期" prop="planDate">
        <el-date-picker clearable
          v-model="queryParams.planDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择计划开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="工作人员" prop="workers">
        <el-input
          v-model="queryParams.workers"
          placeholder="请输入工作人员"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工作场地" prop="workLocation">
        <el-input
          v-model="queryParams.workLocation"
          placeholder="请输入工作场地"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="安排人" prop="arranger">
        <el-input
          v-model="queryParams.arranger"
          placeholder="请输入安排人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="完成结果" prop="resultStatus">
        <el-select
           v-model="queryParams.resultStatus"
          placeholder="请选择完成结果"
          clearable
          @change="handleQuery"
          style="width: 200px"
        >
          <el-option label="完成" value="完成" />
          <el-option label="未完成" value="未完成" />
        </el-select>     
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
          v-hasPermi="['newproducts:plan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['newproducts:plan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['newproducts:plan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['newproducts:plan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="项目编号" align="center" prop="projectCode" />
      <!-- <el-table-column label="项目名称" align="center" prop="projectName" /> -->
      <el-table-column label="计划开始日期" align="center" prop="planDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.planDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工作人员" align="center" prop="workers" />
      <el-table-column label="工作场地" align="center" prop="workLocation" />
      <el-table-column label="使用设备" align="center" prop="equipment" />
      <el-table-column label="作业内容" align="center" prop="workContent" />
      <el-table-column label="安排人" align="center" prop="arranger" />
      <el-table-column label="完成结果" align="center" prop="resultStatus" >
        <template #default="scope">
          <el-tag :type="scope.row.resultStatus === '完成' ? 'success' : 'danger'" >
            {{ scope.row.resultStatus }}
          </el-tag>
        </template>        
      </el-table-column>

<!--       <el-table-column label="扩展字段1" align="center" prop="extField1" />
      <el-table-column label="扩展字段2" align="center" prop="extField2" />
      <el-table-column label="扩展字段3" align="center" prop="extField3" /> -->

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:plan:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:plan:remove']">删除</el-button>
          <el-button link type="primary" icon="Edit" @click="ResultStatus(scope.row)" v-hasPermi="['newproducts:plan:edit']">完成结果</el-button>
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

    <!-- 添加或修改新产品生产计划对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="planRef" :model="form" :rules="rules" label-width="80px">
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

        <el-form-item label="计划开始日期" prop="planDate">
          <el-date-picker clearable
            v-model="form.planDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择计划开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工作人员" prop="workers">
          <el-input v-model="form.workers" placeholder="请输入工作人员" />
        </el-form-item>
        <el-form-item label="工作场地" prop="workLocation">
          <el-input v-model="form.workLocation" placeholder="请输入工作场地" />
        </el-form-item>
        <el-form-item label="使用设备" prop="equipment">
          <el-input v-model="form.equipment" placeholder="请输入使用设备" />
        </el-form-item>
        <el-form-item label="作业内容" prop="workContent">
          <el-input v-model="form.workContent" type="textarea" placeholder="请输入作业内容" />
        </el-form-item>
        <el-form-item label="安排人" prop="arranger">
          <el-input v-model="form.arranger" placeholder="请输入安排人" />
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

    <!-- 计划完成结果对话框 -->
    <el-dialog :title="title" v-model="openResultStatus" width="500px" append-to-body>
      <el-form ref="planRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="核对状态" prop="resultStatus">
            <el-radio-group v-model="form.resultStatus">
            <el-radio label="完成" /> 
            <el-radio label="未完成" /> 
            </el-radio-group>
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

<script setup name="Plan">
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/newproducts/plan";
import { listCreate } from "@/api/newproducts/create";

const { proxy } = getCurrentInstance();

const projectCodeList =ref([]);//项目编号列表
const planList = ref([]);
const open = ref(false);
const openResultStatus = ref(false);//计划完成结果对话框
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
    resultStatus: null,
    extField1: null,
    extField2: null,
    extField3: null
  },
  rules: {
    projectCode: [
      { required: true, message: "项目编号不能为空", trigger: "blur" }
    ],
    planDate: [
      { required: true, message: "计划开始日期不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询新产品生产计划列表 */
function getList() {
  loading.value = true;
  listPlan(queryParams.value).then(response => {
    planList.value = response.rows;
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
  openResultStatus.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    projectCode: null,
    projectName: null,
    planDate: null,
    workers: null,
    workLocation: null,
    equipment: null,
    workContent: null,
    arranger: null,
    resultStatus: null,
    extField1: null,
    extField2: null,
    extField3: null
  };
  proxy.resetForm("planRef");
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
  title.value = "添加新产品生产计划";
}

//计划完成结果按钮
function ResultStatus(row) {
  reset();
  openResultStatus.value = true;
  title.value = "计划完成结果";
  form.value.id = row.id;  console.log(form.value.id);
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getPlan(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品生产计划";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["planRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePlan(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          openResultStatus.value = false;
          getList();
        });
      } else {
        form.value.resultStatus = "未完成";        
        addPlan(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          openResultStatus.value = false;
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
    return delPlan(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/plan/export', {
    ...queryParams.value
  }, `plan_${new Date().getTime()}.xlsx`)
}

getprojectCodeList();
getList();
</script>
