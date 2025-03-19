<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="制造商" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入制造商"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="型号" prop="model">
        <el-input
          v-model="queryParams.model"
          placeholder="请输入型号"
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
          v-hasPermi="['marketanalysis:parameter_five:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:parameter_five:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:parameter_five:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:parameter_five:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="parameter_fiveList" @selection-change="handleSelectionChange">
  <el-table-column type="selection" width="55" align="center" />

  <!-- 特性 -->
  <el-table-column label="特性" align="center">
    <!-- <el-table-column label="id" align="center" prop="id" /> -->
    <el-table-column label="制造商" align="center" prop="manufacturer" />
    <el-table-column label="型号" align="center" prop="model" />
    <el-table-column label="起升重量" align="center" prop="liftingWeight" />
    <el-table-column label="载荷中心" align="center" prop="loadCenter" />
    <el-table-column label="动力方式" align="center" prop="drivingStyle" />
    <el-table-column label="驾驶方式" align="center" prop="powerMode" />
  </el-table-column>

  <!-- 尺寸 -->
  <el-table-column label="尺寸" align="center">
    <el-table-column label="起升高度" align="center" prop="liftingHeight" />
    <el-table-column label="门架全高" align="center" prop="doorFrameFullHeight" />
    <el-table-column label="货叉起升最大高度" align="center" prop="maximumLiftingHeightOfFork" />
    <el-table-column label="自由提升高度" align="center" prop="freeElevationHeight" />
    <el-table-column label="全高" align="center" prop="fullHeight" />
    <el-table-column label="最小离地间隙" align="center" prop="minimumGroundClearance" />
    <el-table-column label="全长" align="center" prop="overallLength" />
    <el-table-column label="轴距" align="center" prop="wheelbase" />
    <el-table-column label="前悬距" align="center" prop="frontSuspensionDistance" />
    <el-table-column label="后悬距" align="center" prop="rearSuspensionDistance" />
    <el-table-column label="全宽" align="center" prop="fullWidth" />
    <el-table-column label="轮距" align="center" prop="trackWidth" />
    <el-table-column label="最小外侧转弯半径" align="center" prop="minimumOuterTurningRadius" />
    <el-table-column label="门架倾角" align="center" prop="gateFrameInclinationAngle" />
    <el-table-column label="货叉尺寸" align="center" prop="forkSize" />
  </el-table-column>

  <!-- 性能 -->
  <el-table-column label="性能" align="center">
    <el-table-column label="最高行驶速度" align="center" prop="maximumDrivingSpeed" />
    <el-table-column label="最大爬坡能力" align="center" prop="maxClimbCapability" />
    <el-table-column label="最大牵引力" align="center" prop="maximumTractionForce" />
    <el-table-column label="起升速度" align="center" prop="liftingSpeed" />
    <el-table-column label="下降速度" align="center" prop="descentSpeed" />
  </el-table-column>

  <!-- 重量 -->
  <el-table-column label="重量" align="center">
    <el-table-column label="总重" align="center" prop="totalWeight" />
    <el-table-column label="重量分配 满载" align="center" prop="weightDistributionWithFullLoad" />
    <el-table-column label="重量分配 空载" align="center" prop="weightDistributionEmptyLoad" />
  </el-table-column>

  <!-- 车轮和轮胎 -->
  <el-table-column label="车轮和轮胎" align="center">
    <el-table-column label="车轮数量 X=驱动轮" align="center" prop="numberOfWheels" />
    <el-table-column label="轮胎类型" align="center" prop="tireType" />
    <el-table-column label="轮胎尺寸" align="center" prop="tireSize" />
  </el-table-column>

  <!-- 驱动、传动控制装置 -->
  <el-table-column label="驱动、传动控制装置" align="center">
    <el-table-column label="蓄电池（电压/容量）" align="center" prop="batteryVoltageCapacity" />
    <el-table-column label="发动机制造厂商/型号" align="center" prop="engineManufacturer" />
    <el-table-column label="额定功率/转速" align="center" prop="ratedPowerSpeed" />
    <el-table-column label="最大扭矩/转速" align="center" prop="maximumTorqueSpeed" />
    <el-table-column label="缸数-缸径×行程/排量" align="center" prop="numberOfCylinders" />
    <el-table-column label="燃油箱容积" align="center" prop="fuelTankCapacity" />
    <el-table-column label="排放标准" align="center" prop="emissionStandard" />
    <el-table-column label="变速箱变速档数" align="center" prop="gearsInTheGearbox" />
  </el-table-column>

  <!-- 操作 -->
  <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
    <template #default="scope">
      <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:parameter_five:edit']">修改</el-button>
      <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:parameter_five:remove']">删除</el-button>
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

    <!-- 添加或修改五类车参数对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="parameter_fiveRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="型号" prop="model">
          <el-input v-model="form.model" placeholder="请输入型号" />
        </el-form-item>
        <el-form-item label="起升重量" prop="liftingWeight">
          <el-input v-model="form.liftingWeight" placeholder="请输入起升重量" />
        </el-form-item>
        <el-form-item label="载荷中心" prop="loadCenter">
          <el-input v-model="form.loadCenter" placeholder="请输入载荷中心" />
        </el-form-item>
        <el-form-item label="动力方式" prop="drivingStyle">
          <el-input v-model="form.drivingStyle" placeholder="请输入动力方式" />
        </el-form-item>
        <el-form-item label="驾驶方式" prop="powerMode">
          <el-input v-model="form.powerMode" placeholder="请输入驾驶方式" />
        </el-form-item>
        <el-form-item label="起升高度" prop="liftingHeight">
          <el-input v-model="form.liftingHeight" placeholder="请输入起升高度" />
        </el-form-item>
        <el-form-item label="门架全高" prop="doorFrameFullHeight">
          <el-input v-model="form.doorFrameFullHeight" placeholder="请输入门架全高" />
        </el-form-item>
        <el-form-item label="货叉起升最大高度" prop="maximumLiftingHeightOfFork">
          <el-input v-model="form.maximumLiftingHeightOfFork" placeholder="请输入货叉起升最大高度" />
        </el-form-item>
        <el-form-item label="自由提升高度" prop="freeElevationHeight">
          <el-input v-model="form.freeElevationHeight" placeholder="请输入自由提升高度" />
        </el-form-item>
        <el-form-item label="全高" prop="fullHeight">
          <el-input v-model="form.fullHeight" placeholder="请输入全高" />
        </el-form-item>
        <el-form-item label="最小离地间隙" prop="minimumGroundClearance">
          <el-input v-model="form.minimumGroundClearance" placeholder="请输入最小离地间隙" />
        </el-form-item>
        <el-form-item label="全长" prop="overallLength">
          <el-input v-model="form.overallLength" placeholder="请输入全长" />
        </el-form-item>
        <el-form-item label="轴距" prop="wheelbase">
          <el-input v-model="form.wheelbase" placeholder="请输入轴距" />
        </el-form-item>
        <el-form-item label="前悬距" prop="frontSuspensionDistance">
          <el-input v-model="form.frontSuspensionDistance" placeholder="请输入前悬距" />
        </el-form-item>
        <el-form-item label="后悬距" prop="rearSuspensionDistance">
          <el-input v-model="form.rearSuspensionDistance" placeholder="请输入后悬距" />
        </el-form-item>
        <el-form-item label="全宽" prop="fullWidth">
          <el-input v-model="form.fullWidth" placeholder="请输入全宽" />
        </el-form-item>
        <el-form-item label="轮距" prop="trackWidth">
          <el-input v-model="form.trackWidth" placeholder="请输入轮距" />
        </el-form-item>
        <el-form-item label="最小外侧转弯半径" prop="minimumOuterTurningRadius">
          <el-input v-model="form.minimumOuterTurningRadius" placeholder="请输入最小外侧转弯半径" />
        </el-form-item>
        <el-form-item label="门架倾角" prop="gateFrameInclinationAngle">
          <el-input v-model="form.gateFrameInclinationAngle" placeholder="请输入门架倾角" />
        </el-form-item>
        <el-form-item label="货叉尺寸" prop="forkSize">
          <el-input v-model="form.forkSize" placeholder="请输入货叉尺寸" />
        </el-form-item>
        <el-form-item label="最高行驶速度" prop="maximumDrivingSpeed">
          <el-input v-model="form.maximumDrivingSpeed" placeholder="请输入最高行驶速度" />
        </el-form-item>
        <el-form-item label="最大爬坡能力" prop="maxClimbCapability">
          <el-input v-model="form.maxClimbCapability" placeholder="请输入最大爬坡能力" />
        </el-form-item>
        <el-form-item label="最大牵引力" prop="maximumTractionForce">
          <el-input v-model="form.maximumTractionForce" placeholder="请输入最大牵引力" />
        </el-form-item>
        <el-form-item label="起升速度" prop="liftingSpeed">
          <el-input v-model="form.liftingSpeed" placeholder="请输入起升速度" />
        </el-form-item>
        <el-form-item label="下降速度" prop="descentSpeed">
          <el-input v-model="form.descentSpeed" placeholder="请输入下降速度" />
        </el-form-item>
        <el-form-item label="总重" prop="totalWeight">
          <el-input v-model="form.totalWeight" placeholder="请输入总重" />
        </el-form-item>
        <el-form-item label="重量分配 满载" prop="weightDistributionWithFullLoad">
          <el-input v-model="form.weightDistributionWithFullLoad" placeholder="请输入重量分配 满载" />
        </el-form-item>
        <el-form-item label="重量分配 空载" prop="weightDistributionEmptyLoad">
          <el-input v-model="form.weightDistributionEmptyLoad" placeholder="请输入重量分配 空载" />
        </el-form-item>
        <el-form-item label="车轮数量  X=驱动轮" prop="numberOfWheels">
          <el-input v-model="form.numberOfWheels" placeholder="请输入车轮数量  X=驱动轮" />
        </el-form-item>
        <el-form-item label="轮胎类型" prop="tireType">
          <el-input v-model="form.tireType" placeholder="请输入轮胎类型" />
        </el-form-item>
        <el-form-item label="轮胎尺寸" prop="tireSize">
          <el-input v-model="form.tireSize" placeholder="请输入轮胎尺寸" />
        </el-form-item>
        <el-form-item label="蓄电池" prop="batteryVoltageCapacity">
          <el-input v-model="form.batteryVoltageCapacity" placeholder="请输入蓄电池" />
        </el-form-item>
        <el-form-item label="发动机制造厂商/型号" prop="engineManufacturer">
          <el-input v-model="form.engineManufacturer" placeholder="请输入发动机制造厂商/型号" />
        </el-form-item>
        <el-form-item label="额定功率/转速" prop="ratedPowerSpeed">
          <el-input v-model="form.ratedPowerSpeed" placeholder="请输入额定功率/转速" />
        </el-form-item>
        <el-form-item label="最大扭矩/转速" prop="maximumTorqueSpeed">
          <el-input v-model="form.maximumTorqueSpeed" placeholder="请输入最大扭矩/转速" />
        </el-form-item>
        <el-form-item label="缸数-缸径×行程/排量" prop="numberOfCylinders">
          <el-input v-model="form.numberOfCylinders" placeholder="请输入缸数-缸径×行程/排量" />
        </el-form-item>
        <el-form-item label="燃油箱容积" prop="fuelTankCapacity">
          <el-input v-model="form.fuelTankCapacity" placeholder="请输入燃油箱容积" />
        </el-form-item>
        <el-form-item label="排放标准" prop="emissionStandard">
          <el-input v-model="form.emissionStandard" placeholder="请输入排放标准" />
        </el-form-item>
        <el-form-item label="变速箱变速档数" prop="gearsInTheGearbox">
          <el-input v-model="form.gearsInTheGearbox" placeholder="请输入变速箱变速档数" />
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

<script setup name="Parameter_five">
import { listParameter_five, getParameter_five, delParameter_five, addParameter_five, updateParameter_five } from "@/api/marketanalysis/parameter/parameter_five";

const { proxy } = getCurrentInstance();

const parameter_fiveList = ref([]);
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
    manufacturer: null,
    model: null,
  },
  rules: {
    manufacturer: [
      { required: true, message: "制造商不能为空", trigger: "blur" }
    ],
    model: [
      { required: true, message: "型号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询五类车参数列表 */
function getList() {
  loading.value = true;
  listParameter_five(queryParams.value).then(response => {
    parameter_fiveList.value = response.rows;
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
    manufacturer: null,
    model: null,
    liftingWeight: null,
    loadCenter: null,
    drivingStyle: null,
    powerMode: null,
    liftingHeight: null,
    doorFrameFullHeight: null,
    maximumLiftingHeightOfFork: null,
    freeElevationHeight: null,
    fullHeight: null,
    minimumGroundClearance: null,
    overallLength: null,
    wheelbase: null,
    frontSuspensionDistance: null,
    rearSuspensionDistance: null,
    fullWidth: null,
    trackWidth: null,
    minimumOuterTurningRadius: null,
    gateFrameInclinationAngle: null,
    forkSize: null,
    maximumDrivingSpeed: null,
    maxClimbCapability: null,
    maximumTractionForce: null,
    liftingSpeed: null,
    descentSpeed: null,
    totalWeight: null,
    weightDistributionWithFullLoad: null,
    weightDistributionEmptyLoad: null,
    numberOfWheels: null,
    tireType: null,
    tireSize: null,
    batteryVoltageCapacity: null,
    engineManufacturer: null,
    ratedPowerSpeed: null,
    maximumTorqueSpeed: null,
    numberOfCylinders: null,
    fuelTankCapacity: null,
    emissionStandard: null,
    gearsInTheGearbox: null
  };
  proxy.resetForm("parameter_fiveRef");
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
  title.value = "添加五类车参数";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getParameter_five(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改五类车参数";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["parameter_fiveRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateParameter_five(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addParameter_five(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除五类车参数编号为"' + _ids + '"的数据项？').then(function() {
    return delParameter_five(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/parameter_five/export', {
    ...queryParams.value
  }, `parameter_five_${new Date().getTime()}.xlsx`)
}

getList();
</script>
