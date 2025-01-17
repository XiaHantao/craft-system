<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="租户状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择租户状态" clearable>
          <el-option v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['system:tenant:add']">新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:tenant:edit']">修改</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:tenant:remove']">删除</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:tenant:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tenantList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="租户id" align="center" prop="tenantId" />
      <!-- <el-table-column label="用户id" align="center" prop="userId" /> -->
      <el-table-column label="管理员" align="center" prop="sysUser">
        <template #default="scope">
          <Span v-if="scope.row.sysUser">{{ scope.row.sysUser.userName }}</Span>
        </template>
      </el-table-column>
      <el-table-column label="绑定机构" align="center" prop="sysDept">
        <template #default="scope">
          <Span v-if="scope.row.sysDept">{{ scope.row.sysDept.deptName }}</Span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="部门id" align="center" prop="deptId" /> -->
      <el-table-column label="显示顺序" align="center" prop="orderNum" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="租户状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="sys_normal_disable" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:tenant:edit']">修改</el-button>
          <!-- <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:tenant:remove']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改租户对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="tenantRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="form.deptName" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="管理员账号" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="租户备注说明" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="租户状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
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
    <el-dialog :title="title" v-model="openEdit" width="500px" append-to-body>
      <el-form ref="tenantRef" :model="formEdit" :rules="rulesEdit" label-width="100px">
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-if="formEdit.sysDept" disabled v-model="formEdit.sysDept.deptName" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="管理员账号" prop="userName">
          <el-input v-if="formEdit.sysUser" disabled v-model="formEdit.sysUser.userName" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="租户备注说明" prop="remark">
          <el-input v-model="formEdit.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="租户状态" prop="status">
          <el-radio-group v-model="formEdit.status">
            <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitFormEdit">确 定</el-button>
          <el-button @click="cancelEdit">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>
  
<script setup name="Tenant">
import { listTenant, getTenant, delTenant, addTenant, updateTenant } from "@/api/system/tenant";

const { proxy } = getCurrentInstance();
const { sys_normal_disable } = proxy.useDict('sys_normal_disable');

const tenantList = ref([]);
const open = ref(false);
const openEdit = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  formEdit: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: null,
    deptId: null,
    orderNum: null,
    status: null,
  },
  rules: {
    deptName: [{ required: true, message: "用户名称不能为空", trigger: "blur" }, { min: 2, max: 20, message: "部门名称长度必须介于 2 和 20 之间", trigger: "blur" }],
    userName: [{ required: true, message: "用户昵称不能为空", trigger: "blur" }, { min: 6, max: 20, message: "用户账号长度必须介于 6 和 20 之间", trigger: "blur" }],
    remark: [{ min: 0, max: 20, message: "备注说明不超过20个字符", trigger: "blur" }],
    status: [{ required: true, message: "请勾选租户状态", trigger: "blur" }],
  },
  rulesEdit: {
    remark: [{ min: 0, max: 20, message: "备注说明不超过20个字符", trigger: "blur" }],
    status: [{ required: true, message: "请勾选租户状态", trigger: "blur" }],
  }
});

const { queryParams, form, rules, formEdit } = toRefs(data);

/** 查询租户列表 */
function getList() {
  loading.value = true;
  listTenant(queryParams.value).then(response => {
    tenantList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 取消按钮
function cancelEdit() {
  openEdit.value = false;
  resetEdit();
}

function resetEdit() {
  formEdit.value = {
    tenantId: null,
    userId: null,
    deptId: null,
    orderNum: null,
    remark: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("tenantRef");
}

// 表单重置
function reset() {
  form.value = {
    deptName: null,
    userName: null,
    remark: null,
    status: null
  };
  proxy.resetForm("tenantRef");
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
  ids.value = selection.map(item => item.tenantId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加租户";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _tenantId = row.tenantId || ids.value
  getTenant(_tenantId).then(response => {
    formEdit.value = response.data;
    openEdit.value = true;
    title.value = "修改租户";
  });
}


function submitFormEdit() {
  proxy.$refs["tenantRef"].validate(valid => {
    if (valid) {
      updateTenant(form.value).then(response => {
        proxy.$modal.msgSuccess("修改成功");
        open.value = false;
        getList();
      });
    }
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["tenantRef"].validate(valid => {
    if (valid) {
      addTenant(form.value).then(response => {
        proxy.$modal.msgSuccess("新增成功");
        open.value = false;
        getList();
      });
    }
  });
}




/** 删除按钮操作 */
function handleDelete(row) {
  const _tenantIds = row.tenantId || ids.value;
  proxy.$modal.confirm('是否确认删除租户编号为"' + _tenantIds + '"的数据项？').then(function () {
    return delTenant(_tenantIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => { });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/tenant/export', {
    ...queryParams.value
  }, `tenant_${new Date().getTime()}.xlsx`)
}

getList();
</script>
  