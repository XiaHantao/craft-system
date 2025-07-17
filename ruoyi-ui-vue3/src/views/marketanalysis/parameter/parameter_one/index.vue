<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="制造商" prop="manufacturer">
        <el-input v-model="queryParams.manufacturer" placeholder="请输入制造商" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="配置号" prop="configurationNumber">
        <el-input v-model="queryParams.configurationNumber" placeholder="请输入配置号" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['marketanalysis:parameter_one:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['marketanalysis:parameter_one:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['marketanalysis:parameter_one:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['marketanalysis:parameter_one:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Upload" @click="handleImport"
          v-hasPermi="['marketanalysis:parameter_one:import']">导入</el-button>
      </el-col>
      <input ref="importRef" type="file" hidden accept=".xlsx, .xls" @change="handleFileChange" />
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="parameter_oneList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <!-- 特性 -->
      <el-table-column label="特性" align="center">
        <el-table-column label="制造商" align="center" prop="manufacturer" />
        <el-table-column label="配置号" align="center" prop="configurationNumber" />
        <el-table-column label="起升重量" align="center" prop="liftingWeight" />
        <el-table-column label="载荷中心" align="center" prop="loadCenter" />
        <el-table-column label="动力方式" align="center" prop="powerMode" />
        <el-table-column label="驾驶方式" align="center" prop="drivingStyle" />
      </el-table-column>

      <!-- 尺寸 -->
      <el-table-column label="尺寸" align="center">
        <el-table-column label="起升高度（标准）" align="center" prop="liftingHeight" />
        <el-table-column label="门架全高（货叉落地、门架垂直）" align="center" prop="doorframeHeight" />
        <el-table-column label="货叉起升最大高度（带挡货架）" align="center" prop="maximumLiftingHeightOfFork" />
        <el-table-column label="自由提升高度" align="center" prop="freeElevationHeight" />
        <el-table-column label="全高（护顶架）" align="center" prop="fullHeight" />
        <el-table-column label="最小离地间隙（门架处）" align="center" prop="minimumGroundClearance" />
        <el-table-column label="全长（含货叉/不含货叉）" align="center" prop="overallLength" />
        <el-table-column label="轴距" align="center" prop="wheelbase" />
        <el-table-column label="前悬距" align="center" prop="frontSuspensionDistance" />
        <el-table-column label="后悬距" align="center" prop="rearSuspensionDistance" />
        <el-table-column label="全宽" align="center" prop="fullWidth" />
        <el-table-column label="轮距（前轮距/后轮距）" align="center" prop="trackWidth" />
        <el-table-column label="最小外侧转弯半径" align="center" prop="minimumOuterTurningRadius" />
        <el-table-column label="门架倾角（前/后）" align="center" prop="gateFrameInclinationAngle" />
        <el-table-column label="货叉尺寸" align="center" prop="forkSize" />
      </el-table-column>

      <!-- 性能 -->
      <el-table-column label="性能" align="center">
        <el-table-column label="最高行驶速度（满载/空载）" align="center" prop="maximumDrivingSpeed" />
        <el-table-column label="最大爬坡能力（满载/空载）" align="center" prop="maxClimbCapability" />
        <el-table-column label="最大牵引力（满载/空载）" align="center" prop="maximumTractionForce" />
        <el-table-column label="起升速度（满载/空载）" align="center" prop="liftingSpeed" />
        <el-table-column label="下降速度（满载/空载）" align="center" prop="descentSpeed" />
      </el-table-column>

      <!-- 重量 -->
      <el-table-column label="重量" align="center">
        <el-table-column label="总重" align="center" prop="totalWeight" />
        <el-table-column label="重量分配 满载（前/后）" align="center" prop="weightDistributionWithFullLoad" />
        <el-table-column label="重量分配 空载（前/后）" align="center" prop="weightDistributionEmptyLoad" />
      </el-table-column>

      <!-- 车轮和轮胎 -->
      <el-table-column label="车轮和轮胎" align="center">
        <el-table-column label="车轮数量 X=驱动轮（前/后）" align="center" prop="numberOfWheels" />
        <el-table-column label="轮胎类型（前/后）" align="center" prop="tireType" />
        <el-table-column label="轮胎尺寸（前/后）" align="center" prop="tireSize" />
      </el-table-column>

      <!-- 电池 -->
      <el-table-column label="电池" align="center">
        <el-table-column label="蓄电池电压/容量" align="center" prop="batteryVoltageCapacity" />
      </el-table-column>

      <!-- 电机和控制器 -->
      <el-table-column label="电机和控制器" align="center">
        <el-table-column label="驱动电机功率(S2-60min)" align="center" prop="driveMotorPower" />
        <el-table-column label="起升电机功率(S3-15%)" align="center" prop="powerOfLiftingMotor" />
        <el-table-column label="驱动电机控制方式" align="center" prop="driveMotorControlMode" />
        <el-table-column label="起升电机控制方式" align="center" prop="controlModeOfLiftingMotor" />
      </el-table-column>

      <!-- 操作 -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['marketanalysis:parameter_one:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['marketanalysis:parameter_one:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改I类车参数对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="parameter_oneRef" :model="form" :rules="rules" label-width="135px">
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="配置号" prop="configurationNumber">
          <el-input v-model="form.configurationNumber" placeholder="请输入配置号" />
        </el-form-item>
        <el-form-item label="起升重量" prop="liftingWeight">
          <el-input v-model="form.liftingWeight" placeholder="请输入起升重量" />
        </el-form-item>
        <el-form-item label="载荷中心" prop="loadCenter">
          <el-input v-model="form.loadCenter" placeholder="请输入载荷中心" />
        </el-form-item>
        <el-form-item label="动力方式" prop="powerMode">
          <el-input v-model="form.powerMode" placeholder="请输入动力方式" />
        </el-form-item>
        <el-form-item label="驾驶方式" prop="drivingStyle">
          <el-input v-model="form.drivingStyle" placeholder="请输入驾驶方式" />
        </el-form-item>
        <el-form-item label="起升高度" prop="liftingHeight">
          <el-input v-model="form.liftingHeight" placeholder="请输入起升高度" />
        </el-form-item>
        <el-form-item label="门架全高" prop="doorframeHeight">
          <el-input v-model="form.doorframeHeight" placeholder="请输入门架全高" />
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
        <el-form-item label="蓄电池电压/容量" prop="batteryVoltageCapacity">
          <el-input v-model="form.batteryVoltageCapacity" placeholder="请输入蓄电池电压/容量" />
        </el-form-item>
        <el-form-item label="驱动电机功率" prop="driveMotorPower">
          <el-input v-model="form.driveMotorPower" placeholder="请输入驱动电机功率(S2-60min)" />
        </el-form-item>
        <el-form-item label="起升电机功率" prop="powerOfLiftingMotor">
          <el-input v-model="form.powerOfLiftingMotor" placeholder="请输入起升电机功率(S3-15%)" />
        </el-form-item>
        <el-form-item label="驱动电机控制方式" prop="driveMotorControlMode">
          <el-input v-model="form.driveMotorControlMode" placeholder="请输入驱动电机控制方式" />
        </el-form-item>
        <el-form-item label="起升电机控制方式" prop="controlModeOfLiftingMotor">
          <el-input v-model="form.controlModeOfLiftingMotor" placeholder="请输入起升电机控制方式" />
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

<script setup name="Parameter_one">
import { listParameter_one, getParameter_one, delParameter_one, addParameter_one, updateParameter_one,importParameter_one, checkDataExists } from "@/api/marketanalysis/parameter/parameter_one";
const importRef = ref(null);
const updateSupport = ref(false);

const handleImport = () => importRef.value.click();

const handleFileChange = async (e) => {
  const files = e.target.files;
  if (!files.length) return;

  try {
    loading.value = true;
    const res = await checkDataExists();
    const dataExists = res.data;
    if (dataExists) {
      proxy.$modal.confirm('检测到已有数据，是否覆盖？').then(() => {
        updateSupport.value = true;
        uploadFile(files[0]);
      }).catch(() => {
        updateSupport.value = false;
        uploadFile(files[0]);
      });
    } else {
      updateSupport.value = false;
      uploadFile(files[0]);
    }
  } finally {
    loading.value = false;
  }
};

const uploadFile = async (file) => {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('updateSupport', updateSupport.value);

  try {
    await importParameter_one(formData);
    proxy.$modal.msgSuccess("导入成功");
    getList();
  } catch (e) {
    proxy.$modal.msgError("导入失败");
  } finally {
    importRef.value.value = '';
  }
};
const { proxy } = getCurrentInstance();

const parameter_oneList = ref([]);
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
    configurationNumber: null,
  },
  rules: {
    manufacturer: [
      { required: true, message: "制造商不能为空", trigger: "blur" }
    ],
    configurationNumber: [
      { required: true, message: "配置号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询I类车参数列表 */
function getList() {
  loading.value = true;
  listParameter_one(queryParams.value).then(response => {
    parameter_oneList.value = response.rows;
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
    configurationNumber: null,
    liftingWeight: null,
    loadCenter: null,
    powerMode: null,
    drivingStyle: null,
    liftingHeight: null,
    doorframeHeight: null,
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
    driveMotorPower: null,
    powerOfLiftingMotor: null,
    driveMotorControlMode: null,
    controlModeOfLiftingMotor: null
  };
  proxy.resetForm("parameter_oneRef");
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
  title.value = "添加I类车参数";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getParameter_one(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改I类车参数";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["parameter_oneRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateParameter_one(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addParameter_one(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除I类车参数编号为"' + _ids + '"的数据项？').then(function() {
    return delParameter_one(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/parameter_one/export', {
    ...queryParams.value
  }, `I类车参数表.xlsx`)
}

getList();
</script>
