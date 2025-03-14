<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="128px">
      <el-form-item label="新产品计划名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入新产品计划名称"
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
      <el-table-column v-if="false" label="id" align="center" prop="id" />
      <el-table-column label="新产品计划名称" align="center" prop="name" />
      <el-table-column label="生产计划" align="center" prop="planfile" />
      <el-table-column label="技术科确认" align="center" prop="technicalcheck" />
      <el-table-column label="技术科备注" align="center" prop="technicalremark" />
      <el-table-column label="质量科确认" align="center" prop="qualitycheck" />
      <el-table-column label="质量科备注" align="center" prop="qualityremark" />
      <el-table-column label="安环科确认" align="center" prop="securitycheck" />
      <el-table-column label="安环科备注" align="center" prop="securityremark" />
<!--       <el-table-column label="" align="center" prop="time" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="testa" />
      <el-table-column label="" align="center" prop="testb" />
      <el-table-column label="" align="center" prop="testc" />
      <el-table-column label="" align="center" prop="testd" />
      <el-table-column label="" align="center" prop="teste" />
      <el-table-column label="" align="center" prop="testf" />
      <el-table-column label="" align="center" prop="testg" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:plan:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:plan:remove']">删除</el-button>
          <el-button link type="primary" icon="Check" @click="handleCheck(scope.row)" v-hasPermi="['newproducts:submit:edit']">确认</el-button>
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
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="planRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="新产品计划名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入新产品计划名称" />
        </el-form-item>
        <el-form-item label="生产计划" prop="planfile">
          <file-upload v-model="form.planfile"/>
        </el-form-item>
<!--         <el-form-item label="技术科确认" prop="technicalcheck">
          <el-input v-model="form.technicalcheck" placeholder="请输入技术科确认" />
        </el-form-item>
        <el-form-item label="技术科备注" prop="technicalremark">
          <el-input v-model="form.technicalremark" placeholder="请输入技术科备注" />
        </el-form-item>
        <el-form-item label="质量科确认" prop="qualitycheck">
          <el-input v-model="form.qualitycheck" placeholder="请输入质量科确认" />
        </el-form-item>
        <el-form-item label="质量科备注" prop="qualityremark">
          <el-input v-model="form.qualityremark" placeholder="请输入质量科备注" />
        </el-form-item>
        <el-form-item label="安环科确认" prop="securitycheck">
          <el-input v-model="form.securitycheck" placeholder="请输入安环科确认" />
        </el-form-item>
        <el-form-item label="安环科备注" prop="securityremark">
          <el-input v-model="form.securityremark" placeholder="请输入安环科备注" />
        </el-form-item> -->
<!--         <el-form-item label="" prop="time">
          <el-date-picker clearable
            v-model="form.time"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="testa">
          <el-input v-model="form.testa" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="testb">
          <el-input v-model="form.testb" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="testc">
          <el-input v-model="form.testc" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="testd">
          <file-upload v-model="form.testd"/>
        </el-form-item>
        <el-form-item label="" prop="teste">
          <file-upload v-model="form.teste"/>
        </el-form-item>
        <el-form-item label="" prop="testf">
          <el-input v-model="form.testf" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="testg">
          <el-input v-model="form.testg" placeholder="请输入" />
        </el-form-item> -->
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 确认对话框 -->
    <el-dialog :title="checkTitle" v-model="openCheckDialog" width="800px" append-to-body>
      <el-form ref="submitRef" :model="form" :rules="rules" label-width="150px">
        
        <div v-if="users =='老实人科技' || users =='技术科'">        
        <el-form-item label="技术科确认结果" prop="technicalcheck">
          <el-radio-group v-model="form.technicalcheck">
            <el-radio label="通过" />
            <el-radio label="不通过" />
          </el-radio-group>
        </el-form-item>        
        <el-form-item label="技术科备注" prop="technicalremark">
          <el-input v-model="form.technicalremark" placeholder="请输入备注" />
        </el-form-item>
        </div>

        <div v-if="users =='老实人科技' || users =='质量科'"> 
        <el-form-item label="质量科确认结果" prop="qualitycheck">
          <el-radio-group v-model="form.qualitycheck">
            <el-radio label="通过" />
            <el-radio label="不通过" />
          </el-radio-group>
        </el-form-item>        
        <el-form-item label="质量科备注" prop="qualityremark">
          <el-input v-model="form.qualityremark" placeholder="请输入备注" />
        </el-form-item>
        </div>

       <div v-if="users =='老实人科技' || users =='安环科'"> 
        <el-form-item label="安环科确认结果" prop="securitycheck">          
          <el-radio-group v-model="form.securitycheck">
            <el-radio label="通过" />
            <el-radio label="不通过" />
          </el-radio-group>
        </el-form-item>        
        <el-form-item label="安环科备注" prop="securityremark">
          <el-input v-model="form.securityremark" placeholder="请输入备注" />
        </el-form-item>
        </div>

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

<script setup name="Plan">
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/newproducts/plan";
import { getUserProfile } from "@/api/system/user";

const { proxy } = getCurrentInstance();

const planList = ref([]);
const open = ref(false);
const openCheckDialog = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const checkTitle = ref("");
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

/** 查询新产品生产计划列表 */
function getList() {
  loading.value = true;
  listPlan(queryParams.value).then(response => {
    planList.value = response.rows;
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
    planfile: null,
    technicalcheck: null,
    technicalremark: null,
    qualitycheck: null,
    qualityremark: null,
    securitycheck: null,
    securityremark: null,
/*     time: null,
    testa: null,
    testb: null,
    testc: null,
    testd: null,
    teste: null,
    testf: null,
    testg: null */
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
          getList();
        });
      } else {
        addPlan(form.value).then(response => {
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

/** 确认按钮操作 */
function handleCheck(row) {
  reset();
  // const _id = row.id || ids.value;
  // getSubmit(_id).then(response => {
    getUserProfile().then(response => {
      users.value = response.data.dept.deptName;  //获得用户部门
    });
    openCheckDialog.value = true;
    checkTitle.value = "确认新产品生产计划提交";
    form.value.id = row.id;
  // });
}
/* 确认提交按钮 */
function submitCheckForm() {
  proxy.$refs["submitRef"].validate(valid => {
    if(valid){    
    if (form.value.id != null) {
      updatePlan(form.value).then(response => {
        proxy.$modal.msgSuccess("确认完成");
        openCheckDialog.value = false;
        getList();
      });
    } 
    }
  });
}
// 取消确认对话框
function cancelCheck() {
  openCheckDialog.value = false;
  reset();
}

getList();
</script>
