<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车类" prop="vehicleCategory">
        <el-input v-model="queryParams.vehicleCategory" placeholder="请输入车类" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="车型" prop="vehicleType">
        <el-input v-model="queryParams.vehicleType" placeholder="请输入车型" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="制造商" prop="manufacturer">
        <el-input v-model="queryParams.manufacturer" placeholder="请输入制造商" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['marketanalysis:performance:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['marketanalysis:performance:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['marketanalysis:performance:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['marketanalysis:performance:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="performanceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="车类" align="center" prop="vehicleCategory" />
      <el-table-column label="车型" align="center" prop="vehicleType" />
      <el-table-column label="制造商" align="center" prop="manufacturer" />
      <el-table-column label="最高行驶速度（满载）" align="center" prop="maximumDrivingSpeedFull" />
      <el-table-column label="最高行驶速度（空载）" align="center" prop="maximumDrivingSpeedEmpty" />
      <el-table-column label="最大爬坡能力（满载）" align="center" prop="maxClimbCapabilityFull" />
      <el-table-column label="最大爬坡能力（空载）" align="center" prop="maxClimbCapabilityEmpty" />
      <el-table-column label="最大牵引力（满载）" align="center" prop="maximumTractionForceFull" />
      <el-table-column label="最大牵引力（空载）" align="center" prop="maximumTractionForceEmpty" />
      <el-table-column label="起升速度（满载）" align="center" prop="liftingSpeedFull" />
      <el-table-column label="起升速度（空载）" align="center" prop="liftingSpeedEmpty" />
      <el-table-column label="下降速度（满载）" align="center" prop="descentSpeedFull" />
      <el-table-column label="下降速度（空载）" align="center" prop="descentSpeedEmpty" />
      <el-table-column label="运行速度（满载）" align="center" prop="runningSpeedFull" />
      <el-table-column label="运行速度（空载）" align="center" prop="runningSpeedEmpty" />
      <el-table-column label="提升速度（满载）" align="center" prop="increaseSpeedFull" />
      <el-table-column label="提升速度（空载）" align="center" prop="increaseSpeedEmpty" />
      <el-table-column label="侧移速度（满载）" align="center" prop="lateralMovementSpeedFull" />
      <el-table-column label="侧移速度（空载）" align="center" prop="lateralMovementSpeedEmpty" />
      <el-table-column label="行程制动" align="center" prop="travelBrake" />
      <el-table-column label="停车制动" align="center" prop="parkingBrake" />
      <el-table-column label="桥负荷（满载）前" align="center" prop="bridgeLoadFullyLoadedFront" />
      <el-table-column label="桥负荷（满载）后" align="center" prop="bridgeLoadFullyLoadedBehind" />
      <el-table-column label="桥负荷（空载）前" align="center" prop="bridgeLoadUnloadedFront" />
      <el-table-column label="桥负荷（空载）后" align="center" prop="bridgeLoadUnloadedBehind" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['marketanalysis:performance:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['marketanalysis:performance:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改性能表对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="performanceRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车类" prop="vehicleCategory">
          <el-input v-model="form.vehicleCategory" placeholder="请输入车类" />
        </el-form-item>
        <el-form-item label="车型" prop="vehicleType">
          <el-input v-model="form.vehicleType" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="最高行驶速度（满载）" prop="maximumDrivingSpeedFull">
          <el-input v-model="form.maximumDrivingSpeedFull" placeholder="请输入最高行驶速度（满载）" />
        </el-form-item>
        <el-form-item label="最高行驶速度（空载）" prop="maximumDrivingSpeedEmpty">
          <el-input v-model="form.maximumDrivingSpeedEmpty" placeholder="请输入最高行驶速度（空载）" />
        </el-form-item>
        <el-form-item label="最大爬坡能力（满载）" prop="maxClimbCapabilityFull">
          <el-input v-model="form.maxClimbCapabilityFull" placeholder="请输入最大爬坡能力（满载）" />
        </el-form-item>
        <el-form-item label="最大爬坡能力（空载）" prop="maxClimbCapabilityEmpty">
          <el-input v-model="form.maxClimbCapabilityEmpty" placeholder="请输入最大爬坡能力（空载）" />
        </el-form-item>
        <el-form-item label="最大牵引力（满载）" prop="maximumTractionForceFull">
          <el-input v-model="form.maximumTractionForceFull" placeholder="请输入最大牵引力（满载）" />
        </el-form-item>
        <el-form-item label="最大牵引力（空载）" prop="maximumTractionForceEmpty">
          <el-input v-model="form.maximumTractionForceEmpty" placeholder="请输入最大牵引力（空载）" />
        </el-form-item>
        <el-form-item label="起升速度（满载）" prop="liftingSpeedFull">
          <el-input v-model="form.liftingSpeedFull" placeholder="请输入起升速度（满载）" />
        </el-form-item>
        <el-form-item label="起升速度（空载）" prop="liftingSpeedEmpty">
          <el-input v-model="form.liftingSpeedEmpty" placeholder="请输入起升速度（空载）" />
        </el-form-item>
        <el-form-item label="下降速度（满载）" prop="descentSpeedFull">
          <el-input v-model="form.descentSpeedFull" placeholder="请输入下降速度（满载）" />
        </el-form-item>
        <el-form-item label="下降速度（空载）" prop="descentSpeedEmpty">
          <el-input v-model="form.descentSpeedEmpty" placeholder="请输入下降速度（空载）" />
        </el-form-item>
        <el-form-item label="运行速度（满载）" prop="runningSpeedFull">
          <el-input v-model="form.runningSpeedFull" placeholder="请输入运行速度（满载）" />
        </el-form-item>
        <el-form-item label="运行速度（空载）" prop="runningSpeedEmpty">
          <el-input v-model="form.runningSpeedEmpty" placeholder="请输入运行速度（空载）" />
        </el-form-item>
        <el-form-item label="提升速度（满载）" prop="increaseSpeedFull">
          <el-input v-model="form.increaseSpeedFull" placeholder="请输入提升速度（满载）" />
        </el-form-item>
        <el-form-item label="提升速度（空载）" prop="increaseSpeedEmpty">
          <el-input v-model="form.increaseSpeedEmpty" placeholder="请输入提升速度（空载）" />
        </el-form-item>
        <el-form-item label="侧移速度（满载）" prop="lateralMovementSpeedFull">
          <el-input v-model="form.lateralMovementSpeedFull" placeholder="请输入侧移速度（满载）" />
        </el-form-item>
        <el-form-item label="侧移速度（空载）" prop="lateralMovementSpeedEmpty">
          <el-input v-model="form.lateralMovementSpeedEmpty" placeholder="请输入侧移速度（空载）" />
        </el-form-item>
        <el-form-item label="行程制动" prop="travelBrake">
          <el-input v-model="form.travelBrake" placeholder="请输入行程制动" />
        </el-form-item>
        <el-form-item label="停车制动" prop="parkingBrake">
          <el-input v-model="form.parkingBrake" placeholder="请输入停车制动" />
        </el-form-item>
        <el-form-item label="桥负荷（满载）前" prop="bridgeLoadFullyLoadedFront">
          <el-input v-model="form.bridgeLoadFullyLoadedFront" placeholder="请输入桥负荷（满载）前" />
        </el-form-item>
        <el-form-item label="桥负荷（满载）后" prop="bridgeLoadFullyLoadedBehind">
          <el-input v-model="form.bridgeLoadFullyLoadedBehind" placeholder="请输入桥负荷（满载）后" />
        </el-form-item>
        <el-form-item label="桥负荷（空载）前" prop="bridgeLoadUnloadedFront">
          <el-input v-model="form.bridgeLoadUnloadedFront" placeholder="请输入桥负荷（空载）前" />
        </el-form-item>
        <el-form-item label="桥负荷（空载）后" prop="bridgeLoadUnloadedBehind">
          <el-input v-model="form.bridgeLoadUnloadedBehind" placeholder="请输入桥负荷（空载）后" />
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

<script setup name="Performance">
import { listPerformance, getPerformance, delPerformance, addPerformance, updatePerformance } from "@/api/marketanalysis/performance/performance";

const { proxy } = getCurrentInstance();

const performanceList = ref([]);
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
    vehicleCategory: null,
    vehicleType: null,
    manufacturer: null,
  },
  rules: {}
});

const { queryParams, form, rules } = toRefs(data);

/** 查询性能表列表 */
function getList() {
  loading.value = true;
  listPerformance(queryParams.value).then(response => {
    performanceList.value = response.rows;
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
    vehicleCategory: null,
    vehicleType: null,
    manufacturer: null,
    maximumDrivingSpeedFull: null,
    maximumDrivingSpeedEmpty: null,
    maxClimbCapabilityFull: null,
    maxClimbCapabilityEmpty: null,
    maximumTractionForceFull: null,
    maximumTractionForceEmpty: null,
    liftingSpeedFull: null,
    liftingSpeedEmpty: null,
    descentSpeedFull: null,
    descentSpeedEmpty: null,
    runningSpeedFull: null,
    runningSpeedEmpty: null,
    increaseSpeedFull: null,
    increaseSpeedEmpty: null,
    lateralMovementSpeedFull: null,
    lateralMovementSpeedEmpty: null,
    travelBrake: null,
    parkingBrake: null,
    bridgeLoadFullyLoadedFront: null,
    bridgeLoadFullyLoadedBehind: null,
    bridgeLoadUnloadedFront: null,
    bridgeLoadUnloadedBehind: null
  };
  proxy.resetForm("performanceRef");
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
  title.value = "添加性能表";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getPerformance(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改性能表";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["performanceRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePerformance(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPerformance(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除性能表编号为"' + _ids + '"的数据项？').then(function () {
    return delPerformance(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => { });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/performance/export', {
    ...queryParams.value
  }, `performance_${new Date().getTime()}.xlsx`)
}

getList();
</script>