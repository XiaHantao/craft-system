<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['marketanalysis:standardone:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:standardone:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:standardone:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:standardone:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="standardoneList" @selection-change="handleSelectionChange">
  <el-table-column type="selection" width="55" align="center" />
  <el-table-column label="id" align="center" prop="id" />
  <el-table-column label="制造商" align="center" prop="manufacturer" />
  <!-- 基本项 -->
  <el-table-column label="基本项" align="center">
    <el-table-column label="充气胎" align="center" prop="pneumaticTire" />
    <el-table-column label="实心胎" align="center" prop="solidTire" />
    <el-table-column label="单胎" align="center" prop="singleTire" />
    <el-table-column label="双胎" align="center" prop="dualTire" />
    <el-table-column label="两片阀" align="center" prop="twoPieceValve" />
    <el-table-column label="三片或四片阀" align="center" prop="threeOrFourPieceValve" />
    <el-table-column label="基本型3米起升高度" align="center" prop="threeMeterLiftingHeight" />
    <el-table-column label="其它型及其它起升高度门架" align="center" prop="liftingHeightGantry" />
    <el-table-column label="标准系列货叉" align="center" prop="standardSeriesFork" />
    <el-table-column label="非标系列货叉、属具" align="center" prop="nonStandardSeriesForks" />
    <el-table-column label="标准车身颜色" align="center" prop="standardBodyColor" />
    <el-table-column label="其它车身颜色" align="center" prop="customizedBodyColor" />
  </el-table-column>

  <!-- 安全类 -->
  <el-table-column label="安全类" align="center">
    <el-table-column label="全套OPS功能" align="center" prop="completeOpsFunctionality" />
    <el-table-column label="转弯限速" align="center" prop="turningSpeedLimit" />
    <el-table-column label="超速报警" align="center" prop="overspeed" />
    <el-table-column label="倒车扶手带喇叭开关" align="center" prop="reverseArmrestWithHorn" />
    <el-table-column label="座椅带安全带开关" align="center" prop="seatBeltSwitch" />
    <el-table-column label="多路阀过载" align="center" prop="overloadOfMultiWayValve" />
    <el-table-column label="灭火器" align="center" prop="fireExtinguisher" />
    <el-table-column label="倒车蜂鸣器" align="center" prop="reverseBuzzer" />
  </el-table-column>

  <!-- 舒适类 -->
  <el-table-column label="舒适类" align="center">
    <el-table-column label="半包围座椅" align="center" prop="semiEnclosedSeat" />
    <el-table-column label="全悬浮座椅" align="center" prop="fullySuspendedSeat" />
    <el-table-column label="USB接口" align="center" prop="usbInterface" />
    <el-table-column label="电风扇" align="center" prop="electricFan" />
    <el-table-column label="方向盘启动转向" align="center" prop="turnTheSteeringWheel" />
    <el-table-column label="同步转向" align="center" prop="synchronousSteering" />
  </el-table-column>

  <!-- 护顶架选项 -->
  <el-table-column label="护顶架选项" align="center">
    <el-table-column label="标准护顶架" align="center" prop="standardRoofProtectionFrame" />
    <el-table-column label="非标护顶架" align="center" prop="nonStandardRoofProtectionFrame" />
  </el-table-column>

  <!-- 驾驶室/挡风类 -->
  <el-table-column label="驾驶室/挡风类" align="center">
    <el-table-column label="前挡风玻璃" align="center" prop="windscreen" />
    <el-table-column label="前耐高温挡风玻璃" align="center" prop="frontHeatresistantWindshield" />
    <el-table-column label="后挡风玻璃" align="center" prop="rearWindshield" />
    <el-table-column label="PC耐力板顶棚" align="center" prop="pcEnduranceBoardCeiling" />
    <el-table-column label="贴片式驾驶室" align="center" prop="surfaceMountedCab" />
    <el-table-column label="单冷空调" align="center" prop="singleCoolingAirConditioner" />
    <el-table-column label="冷暖两用空调" align="center" prop="dualPurposeAirConditioner" />
    <el-table-column label="暖风机" align="center" prop="warmAirBlower" />
  </el-table-column>

  <!-- 灯光类 -->
  <el-table-column label="灯光类" align="center">
    <el-table-column label="全车LED灯" align="center" prop="fullCarLedLights" />
    <el-table-column label="LED后工作灯" align="center" prop="ledRearWorkLight" />
    <el-table-column label="车后方蓝光灯" align="center" prop="blueLightBehindTheCar" />
    <el-table-column label="三边蓝色条形灯" align="center" prop="threeSidedBlueStripLight" />
    <el-table-column label="三边红色条形灯" align="center" prop="threeSidedRedStripLights" />
    <el-table-column label="配装LED闪光警示灯" align="center" prop="ledFlashingWarningLight" />
    <el-table-column label="配装LED旋转式警示灯" align="center" prop="ledRotatingWarningLight" />
    <el-table-column label="配装LED旋转蜂鸣式警示灯" align="center" prop="ledRotatingBuzzerWarningLight" />
  </el-table-column>

  <!-- 其他 -->
  <el-table-column label="其他" align="center">
    <el-table-column label="公制螺纹" align="center" prop="metric" />
    <el-table-column label="倾斜油缸护套" align="center" prop="tiltCylinderSleeve" />
    <el-table-column label="转向油缸护套" align="center" prop="steeringCylinderSleeve" />
    <el-table-column label="REMA电源接插件" align="center" prop="remaPowerConnector" />
    <el-table-column label="国标电源接插件" align="center" prop="nationalStandardPowerConnector" />
    <el-table-column label="国产蓄电池" align="center" prop="domesticBattery" />
    <el-table-column label="FICS合力车联网基本版" align="center" prop="ficsHeliConnectedCarBasicEdition" />
  </el-table-column>

  <!-- 操作 -->
  <el-table-column label="操作" align="center" class-name="smallpadding fixed-width">
    <template #default="scope">
      <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:standardone:edit']">
        修改
      </el-button>
      <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:standardone:remove']">
        删除
      </el-button>
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

    <!-- 添加或修改一类车标准配置对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="standardoneRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="充气胎" prop="pneumaticTire">
          <el-input v-model="form.pneumaticTire" placeholder="请输入充气胎" />
        </el-form-item>
        <el-form-item label="实心胎" prop="solidTire">
          <el-input v-model="form.solidTire" placeholder="请输入实心胎" />
        </el-form-item>
        <el-form-item label="单胎" prop="singleTire">
          <el-input v-model="form.singleTire" placeholder="请输入单胎" />
        </el-form-item>
        <el-form-item label="双胎" prop="dualTire">
          <el-input v-model="form.dualTire" placeholder="请输入双胎" />
        </el-form-item>
        <el-form-item label="两片阀" prop="twoPieceValve">
          <el-input v-model="form.twoPieceValve" placeholder="请输入两片阀" />
        </el-form-item>
        <el-form-item label="三片或四片阀" prop="threeOrFourPieceValve">
          <el-input v-model="form.threeOrFourPieceValve" placeholder="请输入三片或四片阀" />
        </el-form-item>
        <el-form-item label="基本型3米起升高度" prop="threeMeterLiftingHeight">
          <el-input v-model="form.threeMeterLiftingHeight" placeholder="请输入基本型3米起升高度" />
        </el-form-item>
        <el-form-item label="其它型及其它起升高度门架" prop="liftingHeightGantry">
          <el-input v-model="form.liftingHeightGantry" placeholder="请输入其它型及其它起升高度门架" />
        </el-form-item>
        <el-form-item label="标准系列货叉" prop="standardSeriesFork">
          <el-input v-model="form.standardSeriesFork" placeholder="请输入标准系列货叉" />
        </el-form-item>
        <el-form-item label="非标系列货叉、属具" prop="nonStandardSeriesForks">
          <el-input v-model="form.nonStandardSeriesForks" placeholder="请输入非标系列货叉、属具" />
        </el-form-item>
        <el-form-item label="标准车身颜色" prop="standardBodyColor">
          <el-input v-model="form.standardBodyColor" placeholder="请输入标准车身颜色" />
        </el-form-item>
        <el-form-item label="其它车身颜色" prop="customizedBodyColor">
          <el-input v-model="form.customizedBodyColor" placeholder="请输入其它车身颜色" />
        </el-form-item>
        <el-form-item label="全套OPS功能" prop="completeOpsFunctionality">
          <el-input v-model="form.completeOpsFunctionality" placeholder="请输入全套OPS功能" />
        </el-form-item>
        <el-form-item label="转弯限速" prop="turningSpeedLimit">
          <el-input v-model="form.turningSpeedLimit" placeholder="请输入转弯限速" />
        </el-form-item>
        <el-form-item label="超速报警" prop="overspeed">
          <el-input v-model="form.overspeed" placeholder="请输入超速报警" />
        </el-form-item>
        <el-form-item label="倒车扶手带喇叭开关" prop="reverseArmrestWithHorn">
          <el-input v-model="form.reverseArmrestWithHorn" placeholder="请输入倒车扶手带喇叭开关" />
        </el-form-item>
        <el-form-item label="座椅带安全带开关" prop="seatBeltSwitch">
          <el-input v-model="form.seatBeltSwitch" placeholder="请输入座椅带安全带开关" />
        </el-form-item>
        <el-form-item label="多路阀过载" prop="overloadOfMultiWayValve">
          <el-input v-model="form.overloadOfMultiWayValve" placeholder="请输入多路阀过载" />
        </el-form-item>
        <el-form-item label="灭火器" prop="fireExtinguisher">
          <el-input v-model="form.fireExtinguisher" placeholder="请输入灭火器" />
        </el-form-item>
        <el-form-item label="倒车蜂鸣器" prop="reverseBuzzer">
          <el-input v-model="form.reverseBuzzer" placeholder="请输入倒车蜂鸣器" />
        </el-form-item>
        <el-form-item label="中央广角后视镜" prop="centralWideangleRearviewMirror">
          <el-input v-model="form.centralWideangleRearviewMirror" placeholder="请输入中央广角后视镜" />
        </el-form-item>
        <el-form-item label="两侧后视镜" prop="twoSideRearviewMirrors">
          <el-input v-model="form.twoSideRearviewMirrors" placeholder="请输入两侧后视镜" />
        </el-form-item>
        <el-form-item label="倒车雷达" prop="parkingSensor">
          <el-input v-model="form.parkingSensor" placeholder="请输入倒车雷达" />
        </el-form-item>
        <el-form-item label="半包围座椅" prop="semiEnclosedSeat">
          <el-input v-model="form.semiEnclosedSeat" placeholder="请输入半包围座椅" />
        </el-form-item>
        <el-form-item label="全悬浮座椅" prop="fullySuspendedSeat">
          <el-input v-model="form.fullySuspendedSeat" placeholder="请输入全悬浮座椅" />
        </el-form-item>
        <el-form-item label="USB接口" prop="usbInterface">
          <el-input v-model="form.usbInterface" placeholder="请输入USB接口" />
        </el-form-item>
        <el-form-item label="电风扇" prop="electricFan">
          <el-input v-model="form.electricFan" placeholder="请输入电风扇" />
        </el-form-item>
        <el-form-item label="方向盘启动转向" prop="turnTheSteeringWheel">
          <el-input v-model="form.turnTheSteeringWheel" placeholder="请输入方向盘启动转向" />
        </el-form-item>
        <el-form-item label="同步转向" prop="synchronousSteering">
          <el-input v-model="form.synchronousSteering" placeholder="请输入同步转向" />
        </el-form-item>
        <el-form-item label="标准护顶架" prop="standardRoofProtectionFrame">
          <el-input v-model="form.standardRoofProtectionFrame" placeholder="请输入标准护顶架" />
        </el-form-item>
        <el-form-item label="非标护顶架" prop="nonStandardRoofProtectionFrame">
          <el-input v-model="form.nonStandardRoofProtectionFrame" placeholder="请输入非标护顶架" />
        </el-form-item>
        <el-form-item label="前挡风玻璃" prop="windscreen">
          <el-input v-model="form.windscreen" placeholder="请输入前挡风玻璃" />
        </el-form-item>
        <el-form-item label="前耐高温挡风玻璃" prop="frontHeatresistantWindshield">
          <el-input v-model="form.frontHeatresistantWindshield" placeholder="请输入前耐高温挡风玻璃" />
        </el-form-item>
        <el-form-item label="后挡风玻璃" prop="rearWindshield">
          <el-input v-model="form.rearWindshield" placeholder="请输入后挡风玻璃" />
        </el-form-item>
        <el-form-item label="PC耐力板顶棚" prop="pcEnduranceBoardCeiling">
          <el-input v-model="form.pcEnduranceBoardCeiling" placeholder="请输入PC耐力板顶棚" />
        </el-form-item>
        <el-form-item label="贴片式驾驶室" prop="surfaceMountedCab">
          <el-input v-model="form.surfaceMountedCab" placeholder="请输入贴片式驾驶室" />
        </el-form-item>
        <el-form-item label="单冷空调" prop="singleCoolingAirConditioner">
          <el-input v-model="form.singleCoolingAirConditioner" placeholder="请输入单冷空调" />
        </el-form-item>
        <el-form-item label="冷暖两用空调" prop="dualPurposeAirConditioner">
          <el-input v-model="form.dualPurposeAirConditioner" placeholder="请输入冷暖两用空调" />
        </el-form-item>
        <el-form-item label="暖风机" prop="warmAirBlower">
          <el-input v-model="form.warmAirBlower" placeholder="请输入暖风机" />
        </el-form-item>
        <el-form-item label="全车LED灯" prop="fullCarLedLights">
          <el-input v-model="form.fullCarLedLights" placeholder="请输入全车LED灯" />
        </el-form-item>
        <el-form-item label="LED后工作灯" prop="ledRearWorkLight">
          <el-input v-model="form.ledRearWorkLight" placeholder="请输入LED后工作灯" />
        </el-form-item>
        <el-form-item label="车后方蓝光灯" prop="blueLightBehindTheCar">
          <el-input v-model="form.blueLightBehindTheCar" placeholder="请输入车后方蓝光灯" />
        </el-form-item>
        <el-form-item label="三边蓝色条形灯" prop="threeSidedBlueStripLight">
          <el-input v-model="form.threeSidedBlueStripLight" placeholder="请输入三边蓝色条形灯" />
        </el-form-item>
        <el-form-item label="三边红色条形灯" prop="threeSidedRedStripLights">
          <el-input v-model="form.threeSidedRedStripLights" placeholder="请输入三边红色条形灯" />
        </el-form-item>
        <el-form-item label="配装LED闪光警示灯" prop="ledFlashingWarningLight">
          <el-input v-model="form.ledFlashingWarningLight" placeholder="请输入配装LED闪光警示灯" />
        </el-form-item>
        <el-form-item label="配装LED旋转式警示灯" prop="ledRotatingWarningLight">
          <el-input v-model="form.ledRotatingWarningLight" placeholder="请输入配装LED旋转式警示灯" />
        </el-form-item>
        <el-form-item label="配装LED旋转蜂鸣式警示灯" prop="ledRotatingBuzzerWarningLight">
          <el-input v-model="form.ledRotatingBuzzerWarningLight" placeholder="请输入配装LED旋转蜂鸣式警示灯" />
        </el-form-item>
        <el-form-item label="公制螺纹" prop="metric">
          <el-input v-model="form.metric" placeholder="请输入公制螺纹" />
        </el-form-item>
        <el-form-item label="倾斜油缸护套" prop="tiltCylinderSleeve">
          <el-input v-model="form.tiltCylinderSleeve" placeholder="请输入倾斜油缸护套" />
        </el-form-item>
        <el-form-item label="转向油缸护套" prop="steeringCylinderSleeve">
          <el-input v-model="form.steeringCylinderSleeve" placeholder="请输入转向油缸护套" />
        </el-form-item>
        <el-form-item label="REMA电源接插件" prop="remaPowerConnector">
          <el-input v-model="form.remaPowerConnector" placeholder="请输入REMA电源接插件" />
        </el-form-item>
        <el-form-item label="国标电源接插件" prop="nationalStandardPowerConnector">
          <el-input v-model="form.nationalStandardPowerConnector" placeholder="请输入国标电源接插件" />
        </el-form-item>
        <el-form-item label="国产蓄电池" prop="domesticBattery">
          <el-input v-model="form.domesticBattery" placeholder="请输入国产蓄电池" />
        </el-form-item>
        <el-form-item label="FICS合力车联网基本版" prop="ficsHeliConnectedCarBasicEdition">
          <el-input v-model="form.ficsHeliConnectedCarBasicEdition" placeholder="请输入FICS合力车联网基本版" />
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

<script setup name="Standardone">
import { listStandardone, getStandardone, delStandardone, addStandardone, updateStandardone } from "@/api/marketanalysis/standard/standardone";

const { proxy } = getCurrentInstance();

const standardoneList = ref([]);
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
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询一类车标准配置列表 */
function getList() {
  loading.value = true;
  listStandardone(queryParams.value).then(response => {
    standardoneList.value = response.rows;
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
    pneumaticTire: null,
    solidTire: null,
    singleTire: null,
    dualTire: null,
    twoPieceValve: null,
    threeOrFourPieceValve: null,
    threeMeterLiftingHeight: null,
    liftingHeightGantry: null,
    standardSeriesFork: null,
    nonStandardSeriesForks: null,
    standardBodyColor: null,
    customizedBodyColor: null,
    completeOpsFunctionality: null,
    turningSpeedLimit: null,
    overspeed: null,
    reverseArmrestWithHorn: null,
    seatBeltSwitch: null,
    overloadOfMultiWayValve: null,
    fireExtinguisher: null,
    reverseBuzzer: null,
    centralWideangleRearviewMirror: null,
    twoSideRearviewMirrors: null,
    parkingSensor: null,
    semiEnclosedSeat: null,
    fullySuspendedSeat: null,
    usbInterface: null,
    electricFan: null,
    turnTheSteeringWheel: null,
    synchronousSteering: null,
    standardRoofProtectionFrame: null,
    nonStandardRoofProtectionFrame: null,
    windscreen: null,
    frontHeatresistantWindshield: null,
    rearWindshield: null,
    pcEnduranceBoardCeiling: null,
    surfaceMountedCab: null,
    singleCoolingAirConditioner: null,
    dualPurposeAirConditioner: null,
    warmAirBlower: null,
    fullCarLedLights: null,
    ledRearWorkLight: null,
    blueLightBehindTheCar: null,
    threeSidedBlueStripLight: null,
    threeSidedRedStripLights: null,
    ledFlashingWarningLight: null,
    ledRotatingWarningLight: null,
    ledRotatingBuzzerWarningLight: null,
    metric: null,
    tiltCylinderSleeve: null,
    steeringCylinderSleeve: null,
    remaPowerConnector: null,
    nationalStandardPowerConnector: null,
    domesticBattery: null,
    ficsHeliConnectedCarBasicEdition: null
  };
  proxy.resetForm("standardoneRef");
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
  ids.value = selection.map(item => item.pneumaticTire);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加一类车标准配置";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _pneumaticTire = row.pneumaticTire || ids.value
  getStandardone(_pneumaticTire).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改一类车标准配置";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["standardoneRef"].validate(valid => {
    if (valid) {
      if (form.value.pneumaticTire != null) {
        updateStandardone(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addStandardone(form.value).then(response => {
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
  const _pneumaticTires = row.pneumaticTire || ids.value;
  proxy.$modal.confirm('是否确认删除一类车标准配置编号为"' + _pneumaticTires + '"的数据项？').then(function() {
    return delStandardone(_pneumaticTires);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/standardone/export', {
    ...queryParams.value
  }, `standardone_${new Date().getTime()}.xlsx`)
}

getList();
</script>
