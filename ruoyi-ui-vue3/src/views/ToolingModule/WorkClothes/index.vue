<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="序号" prop="serialNumber">
        <el-input
          v-model="queryParams.serialNumber"
          placeholder="请输入序号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模具用途" prop="moldUsage">
        <el-input
          v-model="queryParams.moldUsage"
          placeholder="请输入模具用途"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
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
      <el-form-item label="模具位置" prop="moldPosition">
        <el-input
          v-model="queryParams.moldPosition"
          placeholder="请输入模具位置"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="quantityOfTooling">
        <el-input
          v-model="queryParams.quantityOfTooling"
          placeholder="请输入数量"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组装产品" prop="assemblingProducts">
        <el-input
          v-model="queryParams.assemblingProducts"
          placeholder="请输入组装产品"
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
          v-hasPermi="['ToolingModule:WorkClothes:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ToolingModule:WorkClothes:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ToolingModule:WorkClothes:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['ToolingModule:WorkClothes:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="WorkClothesList" @selection-change="handleSelectionChange" @row-click="handleRowClick">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="序号" align="center" prop="serialNumber" />
      <el-table-column label="模具用途" align="center" prop="moldUsage" />
      <el-table-column label="模具名称" align="center" prop="moldName" />
      <el-table-column label="模具号" align="center" prop="moldNumber" />
      <el-table-column label="种类" align="center" prop="moldType" />
      <el-table-column label="投入时间" align="center" prop="investTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.investTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="模具位置" align="center" prop="moldPosition" />
      <el-table-column label="数量" align="center" prop="quantityOfTooling" />
      <el-table-column label="模具状态" align="center" prop="moldStatus" />
      <el-table-column label="组装产品" align="center" prop="assemblingProducts" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="工装图纸" align="center" prop="toolingDrawings" />
      <el-table-column label="验证文件" align="center" prop="verifyFile" />
      <el-table-column label="验证结论" align="center" prop="verificationConclusion" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['ToolingModule:WorkClothes:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['ToolingModule:WorkClothes:remove']">删除</el-button>
            <!-- 新增“工装详细”按钮 -->
            <el-button link type="info" icon="Search" @click="handleViewDetails(scope.row.id)">工装详细</el-button>
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
      <!-- 工装详细表格对话框 -->
      <el-dialog title="工装详细" v-model="dialogVisible" width="80%">
          <el-table :data="subData" v-loading="loadingDetails">
              <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="序号" align="center" prop="serialNumber" />
              <el-table-column label="工具编号" align="center" prop="toolNumber" />
              <el-table-column label="工具名称" align="center" prop="toolName" />
              <el-table-column label="合计数量" align="center" prop="totalQuantity" />
              <el-table-column label="材质" align="center" prop="textureOfMaterial" />
              <el-table-column label="下料尺寸" align="center" prop="cuttingSize" />
              <el-table-column label="总重量" align="center" prop="totalWeight" />
              <el-table-column label="原材料物料号" align="center" prop="rawMaterialNumber" />
              <el-table-column label="下料" align="center" prop="materialCutting" />
              <el-table-column label="金工" align="center" prop="metalworking" />
              <el-table-column label="组焊" align="center" prop="assemblyWelding" />
              <el-table-column label="涂装" align="center" prop="painting" />
              <el-table-column label="装配" align="center" prop="assembling" />
              <el-table-column label="备注" align="center" prop="remarks" />
              <el-table-column label="车型id" align="center" prop="modelId" />
              <el-table-column label="是否为共用件" align="center" prop="sharedComponents" />
              <el-table-column label="工装图纸" align="center" prop="toolingDrawings" />
              <el-table-column label="验证文件" align="center" prop="verifyFile" />
              <el-table-column label="采购清单" align="center" prop="procurementList" />
              <el-table-column label="验证结论" align="center" prop="verificationConclusion" />
              <el-table-column label="更换时间" align="center" prop="changeTime" width="180">
                  <template #default="scope">
                      <span>{{ parseTime(scope.row.changeTime, '{y}-{m}-{d}') }}</span>
                  </template>
              </el-table-column>
              <el-table-column label="是否为关键部件" align="center" prop="keyComponents" />
              <el-table-column label="维修记录" align="center" prop="maintenanceRecord" />
              <el-table-column label="模具所属" align="center" prop="moldOwnership" />
              <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                  <template #default="scope">
                      <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['ToolingModule:toolingDetail:edit']">修改</el-button>
                      <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['ToolingModule:toolingDetail:remove']">删除</el-button>
                  </template>
              </el-table-column>
          </el-table>

          <template #footer>
              <el-button @click="dialogVisible = false">关闭</el-button>
          </template>
      </el-dialog>

    <!-- 添加或修改工装台账对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="WorkClothesRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模具所属" prop="moldOwnership">
          <el-input v-model="form.moldOwnership" placeholder="请输入模具所属" />
        </el-form-item>
        <el-form-item label="序号" prop="serialNumber">
          <el-input v-model="form.serialNumber" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="模具用途" prop="moldUsage">
          <el-input v-model="form.moldUsage" placeholder="请输入模具用途" />
        </el-form-item>
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
        <el-form-item label="模具位置" prop="moldPosition">
          <el-input v-model="form.moldPosition" placeholder="请输入模具位置" />
        </el-form-item>
        <el-form-item label="数量" prop="quantityOfTooling">
          <el-input v-model="form.quantityOfTooling" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="组装产品" prop="assemblingProducts">
          <el-input v-model="form.assemblingProducts" placeholder="请输入组装产品" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="是否为共用件" prop="sharedComponents">
          <el-input v-model="form.sharedComponents" placeholder="请输入是否为共用件" />
        </el-form-item>
        <el-form-item label="工装图纸" prop="toolingDrawings">
          <el-input v-model="form.toolingDrawings" placeholder="请输入工装图纸" />
        </el-form-item>
        <el-form-item label="验证文件" prop="verifyFile">
          <file-upload v-model="form.verifyFile"/>
        </el-form-item>
        <el-form-item label="验证结论" prop="verificationConclusion">
          <el-input v-model="form.verificationConclusion" placeholder="请输入验证结论" />
        </el-form-item>
        <el-form-item label="保养提醒日期" prop="reminderDate">
          <el-date-picker clearable
            v-model="form.reminderDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择保养提醒日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="保养类别" prop="maintenanceCategory">
          <el-input v-model="form.maintenanceCategory" placeholder="请输入保养类别" />
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

<script setup name="WorkClothes">
import { listWorkClothes, getWorkClothes, delWorkClothes, addWorkClothes, updateWorkClothes } from "@/api/ToolingModule/WorkClothes";
import {getToolingDetail, listToolingDetail} from "@/api/ToolingModule/toolingDetail";

const { proxy } = getCurrentInstance();

const WorkClothesList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const total1 = ref(0);
const title = ref("");
const dialogVisible = ref(false); // 控制弹框的显示与隐藏
const loadingDetails = ref(false);  // 控制工装详细表格的加载状态
const subData = ref([]); // 存储子数据

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    serialNumber: null,
    moldUsage: null,
    moldName: null,
    moldNumber: null,
    moldType: null,
    investTime: null,
    moldPosition: null,
    quantityOfTooling: null,
    moldStatus: null,
    assemblingProducts: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

// 查看工装详细
function handleViewDetails(id) {
    detailsDialogVisible.value = true;  // 打开弹框
    loadingDetails.value = true;  // 开始加载
    // 根据传入的 id 查询工装详细信息
    getWorkClothesDetails(id).then(response => {
        detailsList.value = response.data;  // 假设返回的数据格式是 { data: [...] }
        loadingDetails.value = false;  // 加载完成
    }).catch(error => {
        loadingDetails.value = false;
        proxy.$modal.msgError("加载工装详细失败");
    });
}
/** 查询工装台账列表 */
function getList() {
  loading.value = true;
  listWorkClothes(queryParams.value).then(response => {
    WorkClothesList.value = response.rows;
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
    moldOwnership: null,
    serialNumber: null,
    moldUsage: null,
    moldName: null,
    moldNumber: null,
    moldType: null,
    investTime: null,
    moldPosition: null,
    quantityOfTooling: null,
    moldStatus: null,
    assemblingProducts: null,
    remark: null,
    sharedComponents: null,
    toolingDrawings: null,
    verifyFile: null,
    verificationConclusion: null,
    reminderDate: null,
    maintenanceCategory: null
  };
  proxy.resetForm("WorkClothesRef");
}

// 处理点击行事件
function handleRowClick(row) {
    // 假设子数据存储在另一个表中，使用 `row.id` 来获取子数据
    listToolingDetail().then(response => {
        subData.value = response.rows; // 设置子数据
        total1.value = Number(response.total);
        dialogVisible.value = true; // 打开弹框
    });
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
  title.value = "添加工装台账";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getWorkClothes(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改工装台账";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["WorkClothesRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateWorkClothes(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addWorkClothes(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除工装台账编号为"' + _ids + '"的数据项？').then(function() {
    return delWorkClothes(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('ToolingModule/WorkClothes/export', {
    ...queryParams.value
  }, `WorkClothes_${new Date().getTime()}.xlsx`)
}

getList();
</script>
