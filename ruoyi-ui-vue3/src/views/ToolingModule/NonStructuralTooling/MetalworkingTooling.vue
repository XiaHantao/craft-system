<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模具名称" prop="moldName">
        <el-input
            v-model="queryParams.moldName"
            placeholder="请输入模具名称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模具号" prop="moldNumber">
        <el-input
            v-model="queryParams.moldNumber"
            placeholder="请输入模具号"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投入时间" prop="investTime">
        <el-date-picker clearable
                        v-model="queryParams.investTime"
                        type="date"
                        value-format="YYYY-MM-DD"
                        placeholder="请选择投入时间">
        </el-date-picker>
      </el-form-item>
      <!--      <el-form-item label="数量" prop="quantityOfTooling">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.quantityOfTooling"-->
      <!--          placeholder="请输入数量"-->
      <!--          clearable-->
      <!--          @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="适用物料号" prop="assemblingProducts">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.assemblingProducts"-->
      <!--          placeholder="请输入适用物料号"-->
      <!--          clearable-->
      <!--          @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="模具位置" prop="moldPosition">
        <el-input
            v-model="queryParams.moldPosition"
            placeholder="请输入模具位置"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="是否为共用件" prop="sharedComponents">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.sharedComponents"-->
      <!--          placeholder="请输入是否为共用件"-->
      <!--          clearable-->
      <!--          @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="验证结论" prop="verificationConclusion">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.verificationConclusion"-->
      <!--          placeholder="请输入验证结论"-->
      <!--          clearable-->
      <!--          @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="保养提醒日期" prop="reminderDate">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.reminderDate"-->
      <!--          type="date"-->
      <!--          value-format="YYYY-MM-DD"-->
      <!--          placeholder="请选择保养提醒日期">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="保养类别" prop="maintenanceCategory">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.maintenanceCategory"-->
      <!--          placeholder="请输入保养类别"-->
      <!--          clearable-->
      <!--          @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="模具所属班组" prop="moldOwnership">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.moldOwnership"-->
      <!--          placeholder="请输入模具所属班组"-->
      <!--          clearable-->
      <!--          @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="模具用途" prop="moldUsage">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.moldUsage"-->
      <!--          placeholder="请输入模具用途"-->
      <!--          clearable-->
      <!--          @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
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
            v-hasPermi="['ToolingModule:NonStructuralTooling:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['ToolingModule:NonStructuralTooling:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['ToolingModule:NonStructuralTooling:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['ToolingModule:NonStructuralTooling:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="NonStructuralToolingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="id" align="center" prop="id" />-->
<!--      <el-table-column label="用途" align="center" prop="moldUsage" />-->
      <el-table-column label="模具名称" align="center" prop="moldName" />
      <el-table-column label="模具号" align="center" prop="moldNumber" />
      <el-table-column label="种类" align="center" prop="moldType" />
      <el-table-column label="投入时间" align="center" prop="investTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.investTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="模具状态" align="center" prop="moldStatus" />
      <el-table-column label="数量" align="center" prop="quantityOfTooling" />
      <el-table-column label="适用物料号" align="center" prop="assemblingProducts" />
      <el-table-column label="模具位置" align="center" prop="moldPosition" />
      <el-table-column label="备注" align="center" prop="remark" />
      <!--      <el-table-column label="是否为共用件" align="center" prop="sharedComponents" />-->
      <el-table-column label="工装图纸" align="center" prop="toolingDrawings">
        <template #default="{ row }">
            <span v-if="row.toolingDrawings">
              <!-- 如果有文件地址，显示预览按钮 -->
              <el-button type="text" @click="previewFile(row.toolingDrawings)">预览</el-button>
            </span>
          <span v-else>
            <!-- 如果没有文件地址，显示“无图纸” -->
            无图纸
          </span>
        </template>
      </el-table-column>
      <!--      <el-table-column label="验证文件" align="center" prop="verifyFile" />-->
      <!--      <el-table-column label="验证结论" align="center" prop="verificationConclusion" />-->
      <!--      <el-table-column label="保养提醒日期" align="center" prop="reminderDate" width="180">-->
      <!--        <template #default="scope">-->
      <!--          <span>{{ parseTime(scope.row.reminderDate, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="保养类别" align="center" prop="maintenanceCategory" />-->
      <!--      <el-table-column label="模具所属班组" align="center" prop="moldOwnership" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['ToolingModule:NonStructuralTooling:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['ToolingModule:NonStructuralTooling:remove']">删除</el-button>
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

    <!-- 添加或修改非结构工装台账对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="NonStructuralToolingRef" :model="form" :rules="rules" label-width="120px">
        <!--        <el-form-item label="序号" prop="serialNumber">-->
        <!--          <el-input v-model="form.serialNumber" placeholder="请输入序号" />-->
        <!--        </el-form-item>-->
        <el-form-item label="模具名称" prop="moldName">
          <el-input v-model="form.moldName" placeholder="请输入模具名称" />
        </el-form-item>
        <el-form-item label="模具号" prop="moldNumber">
          <el-input v-model="form.moldNumber" placeholder="请输入模具号" />
        </el-form-item>
        <el-form-item label="投入时间" prop="investTime">
          <el-date-picker clearable
                          v-model="form.investTime"
                          type="date"
                          value-format="YYYY-MM-DD"
                          placeholder="请选择投入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="数量" prop="quantityOfTooling">
          <el-input v-model="form.quantityOfTooling" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="适用物料号" prop="assemblingProducts">
          <el-input v-model="form.assemblingProducts" placeholder="请输入适用物料号
" />
        </el-form-item>
        <el-form-item label="模具位置" prop="moldPosition">
          <el-input v-model="form.moldPosition" placeholder="请输入模具位置" />
        </el-form-item>

        <el-form-item label="是否为共用件" prop="sharedComponents">
          <el-radio-group v-model="form.sharedComponents">
            <el-radio label="1">是</el-radio>
            <el-radio label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="工装图纸" prop="toolingDrawings">
          <file-upload v-model="form.toolingDrawings"/>
        </el-form-item>
        <!--        <el-form-item label="验证文件" prop="verifyFile">-->
        <!--          <file-upload v-model="form.verifyFile"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="验证结论" prop="verificationConclusion">-->
        <!--          <el-input v-model="form.verificationConclusion" placeholder="请输入验证结论" />-->
        <!--        </el-form-item>-->
        <el-form-item label="保养提醒日期" prop="reminderDate">
          <el-date-picker clearable
                          v-model="form.reminderDate"
                          type="date"
                          value-format="YYYY-MM-DD"
                          placeholder="请选择保养提醒日期">
          </el-date-picker>
        </el-form-item>
        <!--        <el-form-item label="保养类别" prop="maintenanceCategory">-->
        <!--          <el-input v-model="form.maintenanceCategory" placeholder="请输入保养类别" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="模具所属班组" prop="moldOwnership">-->
        <!--          <el-input v-model="form.moldOwnership" placeholder="请输入模具所属班组" />-->
        <!--        </el-form-item>-->
<!--        <el-form-item label="模具用途" prop="moldUsage">-->
<!--          <el-input v-model="form.moldUsage" placeholder="请输入模具用途" />-->
<!--        </el-form-item>-->
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

<script setup name="NonStructuralTooling">
import { listNonStructuralTooling1, getNonStructuralTooling, delNonStructuralTooling, addNonStructuralTooling, updateNonStructuralTooling } from "@/api/ToolingModule/NonStructuralTooling";

const { proxy } = getCurrentInstance();

const NonStructuralToolingList = ref([]);
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
    moldName: null,
    moldNumber: null,
    moldType: null,
    investTime: null,
    moldStatus: null,
    quantityOfTooling: null,
    assemblingProducts: null,
    moldPosition: null,
    sharedComponents: null,
    toolingDrawings: null,
    verifyFile: null,
    verificationConclusion: null,
    reminderDate: null,
    maintenanceCategory: null,
    moldOwnership: null,
    moldUsage: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);


//预览文件
function previewFile(fileUrl) {
  const baseUrl = import.meta.env.VITE_APP_BASE_API; // 或者你可以直接赋值为基础文件路径
  const fullUrl = `${baseUrl}${fileUrl}`; // 拼接文件的完整 URL 地址
  console.log('处理中');
  window.open(fullUrl, '_blank');
}


/** 查询金工班工装台账列表 */
function getList() {
  loading.value = true;
  listNonStructuralTooling1(queryParams.value).then(response => {
    NonStructuralToolingList.value = response.rows;
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
    serialNumber: null,
    moldName: null,
    moldNumber: null,
    moldType: null,
    investTime: null,
    moldStatus: null,
    quantityOfTooling: null,
    assemblingProducts: null,
    moldPosition: null,
    remark: null,
    sharedComponents: null,
    toolingDrawings: null,
    verifyFile: null,
    verificationConclusion: null,
    reminderDate: null,
    maintenanceCategory: null,
    createBy: null,
    createTime: null,
    moldOwnership: null,
    moldUsage: null
  };
  proxy.resetForm("NonStructuralToolingRef");
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
  title.value = "添加非结构工装台账";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getNonStructuralTooling(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改非结构工装台账";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["NonStructuralToolingRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateNonStructuralTooling(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        form.value.moldOwnership = 'metalworkingClassTooling';
        addNonStructuralTooling(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除非结构工装台账编号为"' + _ids + '"的数据项？').then(function() {
    return delNonStructuralTooling(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('ToolingModule/NonStructuralTooling/export', {
    ...queryParams.value
  }, `NonStructuralTooling_${new Date().getTime()}.xlsx`)
}

getList();
</script>
