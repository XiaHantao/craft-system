<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
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
          v-hasPermi="['marketanalysis:standardtwo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:standardtwo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:standardtwo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:standardtwo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="standardtwoList" @selection-change="handleSelectionChange">
  <el-table-column type="selection" width="55" align="center" />
  <el-table-column label="id" align="center" prop="id" />
  <el-table-column label="制造商" align="center" prop="manufacturer" />
  <!-- 基本项 -->
  <el-table-column label="基本项" align="center">
    <el-table-column label="聚氨酯轮胎" align="center" prop="polyurethaneTires" />
    <el-table-column label="基本型5米起升高度" align="center" prop="fiveMeterLiftingHeight" />
    <el-table-column label="其它型及其它起升高度门架" align="center" prop="liftingHeightGantry" />
    <el-table-column label="标准系列货叉" align="center" prop="standardSeriesFork" />
    <el-table-column label="非标系列货叉" align="center" prop="nonStandardSeriesFork" />
    <el-table-column label="标准车身颜色" align="center" prop="standardBodyColor" />
    <el-table-column label="其它车身颜色" align="center" prop="customizedBodyColor" />
  </el-table-column>

  <!-- 安全类 -->
  <el-table-column label="安全类" align="center">
    <el-table-column label="属具未到位限速" align="center" prop="accessoriesAndSpeedLimit" />
    <el-table-column label="转弯限速" align="center" prop="turningSpeedLimit" />
    <el-table-column label="灭火器" align="center" prop="fireExtinguisher" />
    <el-table-column label="倒车蜂鸣器" align="center" prop="reverseBuzzer" />
    <el-table-column label="倒车语音喇叭" align="center" prop="reverseVoiceHorn" />
    <el-table-column label="两侧后视镜" align="center" prop="twoSideRearviewMirrors" />
    <el-table-column label="逃生包" align="center" prop="escapeBag" />
    <el-table-column label="后轮制动" align="center" prop="rearWheelBraking" />
    <el-table-column label="前轮制动" align="center" prop="frontWheelBrake" />
    <el-table-column label="导向轮" align="center" prop="guideWheel" />
    <el-table-column label="监控" align="center" prop="monitor" />
    <el-table-column label="磁导航" align="center" prop="magneticNavigation" />
  </el-table-column>

  <!-- 舒适类 -->
  <el-table-column label="舒适类" align="center">
    <el-table-column label="站驾软包座椅" align="center" prop="stationDrivingSoftPackSeat" />
    <el-table-column label="座驾式座椅" align="center" prop="carStyleSeats" />
    <el-table-column label="电风扇" align="center" prop="electricFan" />
  </el-table-column>

  <!-- 驾驶室/挡风类 -->
  <el-table-column label="驾驶室/挡风类" align="center">
    <el-table-column label="前挡风玻璃" align="center" prop="windscreen" />
    <el-table-column label="后挡风玻璃" align="center" prop="rearWindshield" />
  </el-table-column>

  <!-- 灯光类 -->
  <el-table-column label="灯光类" align="center">
    <el-table-column label="全车LED灯" align="center" prop="fullCarLedLights" />
    <el-table-column label="阅读灯" align="center" prop="readingLamp" />
    <el-table-column label="配装LED闪光警示灯" align="center" prop="ledFlashingWarningLight" />
  </el-table-column>

  <!-- 其他 -->
  <el-table-column label="其他" align="center">
    <el-table-column label="公制螺纹" align="center" prop="metric" />
    <el-table-column label="国标电源接插件" align="center" prop="nationalStandardPowerConnector" />
    <el-table-column label="中文标识" align="center" prop="chineseIdentification" />
    <el-table-column label="英文标识" align="center" prop="englishLogo" />
  </el-table-column>

  <!-- 操作 -->
  <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
    <template #default="scope">
      <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:standardtwo:edit']">
        修改
      </el-button>
      <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:standardtwo:remove']">
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

    <!-- 添加或修改二类车标准配置对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="standardtwoRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="聚氨酯轮胎" prop="polyurethaneTires">
          <el-input v-model="form.polyurethaneTires" placeholder="请输入聚氨酯轮胎" />
        </el-form-item>
        <el-form-item label="基本型5米起升高度" prop="fiveMeterLiftingHeight">
          <el-input v-model="form.fiveMeterLiftingHeight" placeholder="请输入基本型5米起升高度" />
        </el-form-item>
        <el-form-item label="其它型及其它起升高度门架" prop="liftingHeightGantry">
          <el-input v-model="form.liftingHeightGantry" placeholder="请输入其它型及其它起升高度门架" />
        </el-form-item>
        <el-form-item label="标准系列货叉" prop="standardSeriesFork">
          <el-input v-model="form.standardSeriesFork" placeholder="请输入标准系列货叉" />
        </el-form-item>
        <el-form-item label="非标系列货叉" prop="nonStandardSeriesFork">
          <el-input v-model="form.nonStandardSeriesFork" placeholder="请输入非标系列货叉" />
        </el-form-item>
        <el-form-item label="标准车身颜色" prop="standardBodyColor">
          <el-input v-model="form.standardBodyColor" placeholder="请输入标准车身颜色" />
        </el-form-item>
        <el-form-item label="其它车身颜色" prop="customizedBodyColor">
          <el-input v-model="form.customizedBodyColor" placeholder="请输入其它车身颜色" />
        </el-form-item>
        <el-form-item label="属具未到位限速" prop="accessoriesAndSpeedLimit">
          <el-input v-model="form.accessoriesAndSpeedLimit" placeholder="请输入属具未到位限速" />
        </el-form-item>
        <el-form-item label="转弯限速" prop="turningSpeedLimit">
          <el-input v-model="form.turningSpeedLimit" placeholder="请输入转弯限速" />
        </el-form-item>
        <el-form-item label="灭火器" prop="fireExtinguisher">
          <el-input v-model="form.fireExtinguisher" placeholder="请输入灭火器" />
        </el-form-item>
        <el-form-item label="倒车蜂鸣器" prop="reverseBuzzer">
          <el-input v-model="form.reverseBuzzer" placeholder="请输入倒车蜂鸣器" />
        </el-form-item>
        <el-form-item label="倒车语音喇叭" prop="reverseVoiceHorn">
          <el-input v-model="form.reverseVoiceHorn" placeholder="请输入倒车语音喇叭" />
        </el-form-item>
        <el-form-item label="两侧后视镜" prop="twoSideRearviewMirrors">
          <el-input v-model="form.twoSideRearviewMirrors" placeholder="请输入两侧后视镜" />
        </el-form-item>
        <el-form-item label="逃生包" prop="escapeBag">
          <el-input v-model="form.escapeBag" placeholder="请输入逃生包" />
        </el-form-item>
        <el-form-item label="后轮制动" prop="rearWheelBraking">
          <el-input v-model="form.rearWheelBraking" placeholder="请输入后轮制动" />
        </el-form-item>
        <el-form-item label="前轮制动" prop="frontWheelBrake">
          <el-input v-model="form.frontWheelBrake" placeholder="请输入前轮制动" />
        </el-form-item>
        <el-form-item label="导向轮" prop="guideWheel">
          <el-input v-model="form.guideWheel" placeholder="请输入导向轮" />
        </el-form-item>
        <el-form-item label="监控" prop="monitor">
          <el-input v-model="form.monitor" placeholder="请输入监控" />
        </el-form-item>
        <el-form-item label="磁导航" prop="magneticNavigation">
          <el-input v-model="form.magneticNavigation" placeholder="请输入磁导航" />
        </el-form-item>
        <el-form-item label="站驾软包座椅" prop="stationDrivingSoftPackSeat">
          <el-input v-model="form.stationDrivingSoftPackSeat" placeholder="请输入站驾软包座椅" />
        </el-form-item>
        <el-form-item label="座驾式座椅" prop="carStyleSeats">
          <el-input v-model="form.carStyleSeats" placeholder="请输入座驾式座椅" />
        </el-form-item>
        <el-form-item label="电风扇" prop="electricFan">
          <el-input v-model="form.electricFan" placeholder="请输入电风扇" />
        </el-form-item>
        <el-form-item label="前挡风玻璃" prop="windscreen">
          <el-input v-model="form.windscreen" placeholder="请输入前挡风玻璃" />
        </el-form-item>
        <el-form-item label="后挡风玻璃" prop="rearWindshield">
          <el-input v-model="form.rearWindshield" placeholder="请输入后挡风玻璃" />
        </el-form-item>
        <el-form-item label="全车LED灯" prop="fullCarLedLights">
          <el-input v-model="form.fullCarLedLights" placeholder="请输入全车LED灯" />
        </el-form-item>
        <el-form-item label="阅读灯" prop="readingLamp">
          <el-input v-model="form.readingLamp" placeholder="请输入阅读灯" />
        </el-form-item>
        <el-form-item label="配装LED闪光警示灯" prop="ledFlashingWarningLight">
          <el-input v-model="form.ledFlashingWarningLight" placeholder="请输入配装LED闪光警示灯" />
        </el-form-item>
        <el-form-item label="公制螺纹" prop="metric">
          <el-input v-model="form.metric" placeholder="请输入公制螺纹" />
        </el-form-item>
        <el-form-item label="国标电源接插件" prop="nationalStandardPowerConnector">
          <el-input v-model="form.nationalStandardPowerConnector" placeholder="请输入国标电源接插件" />
        </el-form-item>
        <el-form-item label="中文标识" prop="chineseIdentification">
          <el-input v-model="form.chineseIdentification" placeholder="请输入中文标识" />
        </el-form-item>
        <el-form-item label="英文标识" prop="englishLogo">
          <el-input v-model="form.englishLogo" placeholder="请输入英文标识" />
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

<script setup name="Standardtwo">
import { listStandardtwo, getStandardtwo, delStandardtwo, addStandardtwo, updateStandardtwo } from "@/api/marketanalysis/standard/standardtwo";

const { proxy } = getCurrentInstance();

const standardtwoList = ref([]);
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

/** 查询二类车标准配置列表 */
function getList() {
  loading.value = true;
  listStandardtwo(queryParams.value).then(response => {
    standardtwoList.value = response.rows;
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
    polyurethaneTires: null,
    fiveMeterLiftingHeight: null,
    liftingHeightGantry: null,
    standardSeriesFork: null,
    nonStandardSeriesFork: null,
    standardBodyColor: null,
    customizedBodyColor: null,
    accessoriesAndSpeedLimit: null,
    turningSpeedLimit: null,
    fireExtinguisher: null,
    reverseBuzzer: null,
    reverseVoiceHorn: null,
    twoSideRearviewMirrors: null,
    escapeBag: null,
    rearWheelBraking: null,
    frontWheelBrake: null,
    guideWheel: null,
    monitor: null,
    magneticNavigation: null,
    stationDrivingSoftPackSeat: null,
    carStyleSeats: null,
    electricFan: null,
    windscreen: null,
    rearWindshield: null,
    fullCarLedLights: null,
    readingLamp: null,
    ledFlashingWarningLight: null,
    metric: null,
    nationalStandardPowerConnector: null,
    chineseIdentification: null,
    englishLogo: null
  };
  proxy.resetForm("standardtwoRef");
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
  ids.value = selection.map(item => item.polyurethaneTires);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加二类车标准配置";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _polyurethaneTires = row.polyurethaneTires || ids.value
  getStandardtwo(_polyurethaneTires).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改二类车标准配置";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["standardtwoRef"].validate(valid => {
    if (valid) {
      if (form.value.polyurethaneTires != null) {
        updateStandardtwo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addStandardtwo(form.value).then(response => {
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
  const _polyurethaneTiress = row.polyurethaneTires || ids.value;
  proxy.$modal.confirm('是否确认删除二类车标准配置编号为"' + _polyurethaneTiress + '"的数据项？').then(function() {
    return delStandardtwo(_polyurethaneTiress);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/standardtwo/export', {
    ...queryParams.value
  }, `standardtwo_${new Date().getTime()}.xlsx`)
}

getList();
</script>
