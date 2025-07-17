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
          v-hasPermi="['marketanalysis:parameter_three:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:parameter_three:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:parameter_three:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:parameter_three:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Upload" @click="handleImport">导入</el-button>
      </el-col>
      <input ref="importRef" type="file" hidden accept=".xlsx, .xls" @change="handleFileChange" />
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="parameter_threeList" @selection-change="handleSelectionChange">
  <el-table-column type="selection" width="55" align="center" />

  <!-- 特性 -->
  <el-table-column label="特性" align="center">
    <!-- <el-table-column label="id" align="center" prop="id" /> -->
    <el-table-column label="制造商" align="center" prop="manufacturer" />
    <el-table-column label="型号" align="center" prop="model" />
    <el-table-column label="驾驶方式" align="center" prop="drivingStyle" />
    <el-table-column label="额定起重量" align="center" prop="ratedLiftingCapacity" />
    <el-table-column label="总提升高度" align="center" prop="raiseHeight" />
    <el-table-column label="最高点载重" align="center" prop="highestPointLoadCapacity" />
    <el-table-column label="载荷中心" align="center" prop="loadCenter" />
    <el-table-column label="前悬距" align="center" prop="frontSuspensionDistance" />
    <el-table-column label="轴距" align="center" prop="wheelbase" />
    <el-table-column label="后悬距" align="center" prop="rearSuspensionDistance" />
    <el-table-column label="自重（含电池）" align="center" prop="deadWeight" />
    <el-table-column label="蓄电池重量" align="center" prop="weightOfBattery" />
  </el-table-column>

  <!-- 轮胎 -->
  <el-table-column label="轮胎" align="center">
    <el-table-column label="轮胎类型" align="center" prop="tireType" />
    <el-table-column label="轮胎尺寸—前轮" align="center" prop="tireSizeFrontWheel" />
    <el-table-column label="轮胎尺寸—后轮" align="center" prop="tireSizeRearWheel" />
    <el-table-column label="轮胎数量" align="center" prop="numberOfTires" />
    <el-table-column label="前轮距" align="center" prop="frontWheelbase" />
  </el-table-column>

  <!-- 尺寸 -->
  <el-table-column label="尺寸" align="center">
    <el-table-column label="门架回缩时高度" align="center" prop="doorFrameRetractsHeight" />
    <el-table-column label="主起升高度" align="center" prop="liftingHeight" />
    <el-table-column label="作业时最大高度" align="center" prop="maximumHeightDuringHomework" />
    <el-table-column label="护顶架高度" align="center" prop="heightOfRoofProtectionFrame" />
    <el-table-column label="站台高度" align="center" prop="platformHeight" />
    <el-table-column label="辅助提升高度" align="center" prop="assistInRaisingHeight" />
    <el-table-column label="提升后站台高度" align="center" prop="raisePlatformHeightAfterLifting" />
    <el-table-column label="总长" align="center" prop="totalLength" />
    <el-table-column label="车体长度（至货叉竖面）" align="center" prop="vehicleLength" />
    <el-table-column label="总宽" align="center" prop="overallWidth" />
    <el-table-column label="货叉尺寸" align="center" prop="forkSize" />
    <el-table-column label="货叉架安装等级" align="center" prop="installationLevelOfForkFrame" />
    <el-table-column label="货叉架宽度" align="center" prop="forkliftWidth" />
    <el-table-column label="跨货叉宽度" align="center" prop="crossForkWidth" />
    <el-table-column label="导轮间距" align="center" prop="distanceBetweenGuideWheels" />
    <el-table-column label="侧移距离" align="center" prop="lateralDisplacementDistance" />
    <el-table-column label="侧伸出至叉车中心线距离" align="center" prop="extendCenterlineOrkliftDistance" />
    <el-table-column label="门架底部离地面间隙" align="center" prop="gapBottomGantryGround" />
    <el-table-column label="轴距中心离地面间隙" align="center" prop="clearanceCenterWheelbaseGround" />
    <el-table-column label="工作通道宽度" align="center" prop="widthOfWorkingChannel" />
    <el-table-column label="转弯半径" align="center" prop="turningRadius" />
    <el-table-column label="前轮轴至货叉旋转轴距离" align="center" prop="distanceForkRotationAxis" />
    <el-table-column label="最大拣料高度" align="center" prop="maximumPickingHeight" />
    <el-table-column label="托盘宽度" align="center" prop="palletWidth" />
    <el-table-column label="托盘长度" align="center" prop="palletLength" />
    <el-table-column label="驾驶仓入口净宽" align="center" prop="clearWidthOfCockpitEntrance" />
    <el-table-column label="驾驶仓内部净高" align="center" prop="clearHeightInsideTheCockpit" />
    <el-table-column label="驾驶仓外部总宽" align="center" prop="overallWidthOfTheCockpitExterior" />
    <el-table-column label="旋转前伸叉架宽度" align="center" prop="rotatingForkFrameWidth" />
    <el-table-column label="悬臂宽度" align="center" prop="cantileverWidth" />
    <el-table-column label="下降速度（满载/空载）" align="center" prop="descentSpeedR" />
  </el-table-column>

  <!-- 性能 -->
  <el-table-column label="性能" align="center">
    <el-table-column label="运行速度，满/空载" align="center" prop="runningSpeed" />
    <el-table-column label="提升速度，满/空载" align="center" prop="increaseSpeed" />
    <el-table-column label="下降速度，满/空载" align="center" prop="descentSpeed" />
    <el-table-column label="侧移速度，满/空载" align="center" prop="lateralMovementSpeed" />
    <el-table-column label="行程制动" align="center" prop="travelBrake" />
    <el-table-column label="停车制动" align="center" prop="parkingBrake" />
    <el-table-column label="桥负荷，（满载）前/后" align="center" prop="bridgeLoadFullyLoaded" />
    <el-table-column label="桥负荷，（空载）前/后" align="center" prop="bridgeLoadUnloaded" />
  </el-table-column>

  <!-- 电机和电池 -->
  <el-table-column label="电机和电池" align="center">
    <el-table-column label="驱动电机功率" align="center" prop="driveMotorPower" />
    <el-table-column label="提升电机功率" align="center" prop="increaseMotorPower" />
    <el-table-column label="电压/额定容量" align="center" prop="voltageRatedCapacity" />
    <el-table-column label="蓄电池重量（含辅配重）" align="center" prop="weightOfBattery" />
  </el-table-column>

  <!-- 其他 -->
  <el-table-column label="其他" align="center">
    <el-table-column label="驱动控制类型" align="center" prop="driveControlType" />
    <el-table-column label="驾驶员耳边噪声" align="center" prop="driverEarNoise" />
    <el-table-column label="转向" align="center" prop="turn" />
  </el-table-column>

  <!-- 操作 -->
  <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
    <template #default="scope">
      <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:parameter_three:edit']">修改</el-button>
      <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:parameter_three:remove']">删除</el-button>
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

    <!-- 添加或修改III类车参数对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="parameter_threeRef" :model="form" :rules="rules" label-width="150px">
         <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="型号" prop="model">
          <el-input v-model="form.model" placeholder="请输入型号" />
        </el-form-item>
        <el-form-item label="驾驶方式" prop="drivingStyle">
          <el-input v-model="form.drivingStyle" placeholder="请输入驾驶方式" />
        </el-form-item>
        <el-form-item label="额定起重量" prop="ratedLiftingCapacity">
          <el-input v-model="form.ratedLiftingCapacity" placeholder="请输入额定起重量" />
        </el-form-item>
        <el-form-item label="总提升高度" prop="raiseHeight">
          <el-input v-model="form.raiseHeight" placeholder="请输入总提升高度" />
        </el-form-item>
        <el-form-item label="最高点载重" prop="highestPointLoadCapacity">
          <el-input v-model="form.highestPointLoadCapacity" placeholder="请输入最高点载重" />
        </el-form-item>
        <el-form-item label="载荷中心" prop="loadCenter">
          <el-input v-model="form.loadCenter" placeholder="请输入载荷中心" />
        </el-form-item>
        <el-form-item label="前悬距" prop="frontSuspensionDistance">
          <el-input v-model="form.frontSuspensionDistance" placeholder="请输入前悬距" />
        </el-form-item>
        <el-form-item label="轴距" prop="wheelbase">
          <el-input v-model="form.wheelbase" placeholder="请输入轴距" />
        </el-form-item>
        <el-form-item label="后悬距" prop="rearSuspensionDistance">
          <el-input v-model="form.rearSuspensionDistance" placeholder="请输入后悬距" />
        </el-form-item>
        <el-form-item label="自重" prop="deadWeight">
          <el-input v-model="form.deadWeight" placeholder="请输入自重" />
        </el-form-item>
        <el-form-item label="轮胎类型" prop="tireType">
          <el-input v-model="form.tireType" placeholder="请输入轮胎类型" />
        </el-form-item>
        <el-form-item label="轮胎尺寸—前轮" prop="tireSizeFrontWheel">
          <el-input v-model="form.tireSizeFrontWheel" placeholder="请输入轮胎尺寸—前轮" />
        </el-form-item>
        <el-form-item label="轮胎尺寸—后轮" prop="tireSizeRearWheel">
          <el-input v-model="form.tireSizeRearWheel" placeholder="请输入轮胎尺寸—后轮" />
        </el-form-item>
        <el-form-item label="轮胎数量" prop="numberOfTires">
          <el-input v-model="form.numberOfTires" placeholder="请输入轮胎数量" />
        </el-form-item>
        <el-form-item label="前轮距" prop="frontWheelbase">
          <el-input v-model="form.frontWheelbase" placeholder="请输入前轮距" />
        </el-form-item>
        <el-form-item label="门架回缩时高度" prop="doorFrameRetractsHeight">
          <el-input v-model="form.doorFrameRetractsHeight" placeholder="请输入门架回缩时高度" />
        </el-form-item>
        <el-form-item label="主起升高度" prop="liftingHeight">
          <el-input v-model="form.liftingHeight" placeholder="请输入主起升高度" />
        </el-form-item>
        <el-form-item label="作业时最大高度" prop="maximumHeightDuringHomework">
          <el-input v-model="form.maximumHeightDuringHomework" placeholder="请输入作业时最大高度" />
        </el-form-item>
        <el-form-item label="护顶架高度" prop="heightOfRoofProtectionFrame">
          <el-input v-model="form.heightOfRoofProtectionFrame" placeholder="请输入护顶架高度" />
        </el-form-item>
        <el-form-item label="站台高度" prop="platformHeight">
          <el-input v-model="form.platformHeight" placeholder="请输入站台高度" />
        </el-form-item>
        <el-form-item label="辅助提升高度" prop="assistInRaisingHeight">
          <el-input v-model="form.assistInRaisingHeight" placeholder="请输入辅助提升高度" />
        </el-form-item>
        <el-form-item label="提升后站台高度" prop="raisePlatformHeightAfterLifting">
          <el-input v-model="form.raisePlatformHeightAfterLifting" placeholder="请输入提升后站台高度" />
        </el-form-item>
        <el-form-item label="总长" prop="totalLength">
          <el-input v-model="form.totalLength" placeholder="请输入总长" />
        </el-form-item>
        <el-form-item label="车体长度" prop="vehicleLength">
          <el-input v-model="form.vehicleLength" placeholder="请输入车体长度" />
        </el-form-item>
        <el-form-item label="总宽" prop="overallWidth">
          <el-input v-model="form.overallWidth" placeholder="请输入总宽" />
        </el-form-item>
        <el-form-item label="货叉尺寸" prop="forkSize">
          <el-input v-model="form.forkSize" placeholder="请输入货叉尺寸" />
        </el-form-item>
        <el-form-item label="货叉架安装等级" prop="installationLevelOfForkFrame">
          <el-input v-model="form.installationLevelOfForkFrame" placeholder="请输入货叉架安装等级" />
        </el-form-item>
        <el-form-item label="货叉架宽度" prop="forkliftWidth">
          <el-input v-model="form.forkliftWidth" placeholder="请输入货叉架宽度" />
        </el-form-item>
        <el-form-item label="跨货叉宽度" prop="crossForkWidth">
          <el-input v-model="form.crossForkWidth" placeholder="请输入跨货叉宽度" />
        </el-form-item>
        <el-form-item label="导轮间距" prop="distanceBetweenGuideWheels">
          <el-input v-model="form.distanceBetweenGuideWheels" placeholder="请输入导轮间距" />
        </el-form-item>
        <el-form-item label="侧移距离" prop="lateralDisplacementDistance">
          <el-input v-model="form.lateralDisplacementDistance" placeholder="请输入侧移距离" />
        </el-form-item>
        <el-form-item label="侧伸出至叉车中心线距离" prop="extendCenterlineOrkliftDistance">
          <el-input v-model="form.extendCenterlineOrkliftDistance" placeholder="请输入侧伸出至叉车中心线距离" />
        </el-form-item>
        <el-form-item label="门架底部离地面间隙" prop="gapBottomGantryGround">
          <el-input v-model="form.gapBottomGantryGround" placeholder="请输入门架底部离地面间隙" />
        </el-form-item>
        <el-form-item label="轴距中心离地面间隙" prop="clearanceCenterWheelbaseGround">
          <el-input v-model="form.clearanceCenterWheelbaseGround" placeholder="请输入轴距中心离地面间隙" />
        </el-form-item>
        <el-form-item label="工作通道宽度" prop="widthOfWorkingChannel">
          <el-input v-model="form.widthOfWorkingChannel" placeholder="请输入工作通道宽度" />
        </el-form-item>
        <el-form-item label="转弯半径" prop="turningRadius">
          <el-input v-model="form.turningRadius" placeholder="请输入转弯半径" />
        </el-form-item>
        <el-form-item label="前轮轴至货叉旋转轴距离" prop="distanceForkRotationAxis">
          <el-input v-model="form.distanceForkRotationAxis" placeholder="请输入前轮轴至货叉旋转轴距离" />
        </el-form-item>
        <el-form-item label="最大拣料高度" prop="maximumPickingHeight">
          <el-input v-model="form.maximumPickingHeight" placeholder="请输入最大拣料高度" />
        </el-form-item>
        <el-form-item label="托盘宽度" prop="palletWidth">
          <el-input v-model="form.palletWidth" placeholder="请输入托盘宽度" />
        </el-form-item>
        <el-form-item label="托盘长度" prop="palletLength">
          <el-input v-model="form.palletLength" placeholder="请输入托盘长度" />
        </el-form-item>
        <el-form-item label="驾驶仓入口净宽" prop="clearWidthOfCockpitEntrance">
          <el-input v-model="form.clearWidthOfCockpitEntrance" placeholder="请输入驾驶仓入口净宽" />
        </el-form-item>
        <el-form-item label="驾驶仓内部净高" prop="clearHeightInsideTheCockpit">
          <el-input v-model="form.clearHeightInsideTheCockpit" placeholder="请输入驾驶仓内部净高" />
        </el-form-item>
        <el-form-item label="驾驶仓外部总宽" prop="overallWidthOfTheCockpitExterior">
          <el-input v-model="form.overallWidthOfTheCockpitExterior" placeholder="请输入驾驶仓外部总宽" />
        </el-form-item>
        <el-form-item label="旋转前伸叉架宽度" prop="rotatingForkFrameWidth">
          <el-input v-model="form.rotatingForkFrameWidth" placeholder="请输入旋转前伸叉架宽度" />
        </el-form-item>
        <el-form-item label="悬臂宽度" prop="cantileverWidth">
          <el-input v-model="form.cantileverWidth" placeholder="请输入悬臂宽度" />
        </el-form-item>
        <el-form-item label="下降速度" prop="descentSpeedR">
          <el-input v-model="form.descentSpeedR" placeholder="请输入下降速度" />
        </el-form-item>
        <el-form-item label="运行速度，满/空载" prop="runningSpeed">
          <el-input v-model="form.runningSpeed" placeholder="请输入运行速度，满/空载" />
        </el-form-item>
        <el-form-item label="提升速度，满/空载" prop="increaseSpeed">
          <el-input v-model="form.increaseSpeed" placeholder="请输入提升速度，满/空载" />
        </el-form-item>
        <el-form-item label="下降速度，满/空载" prop="descentSpeed">
          <el-input v-model="form.descentSpeed" placeholder="请输入下降速度，满/空载" />
        </el-form-item>
        <el-form-item label="侧移速度，满/空载" prop="lateralMovementSpeed">
          <el-input v-model="form.lateralMovementSpeed" placeholder="请输入侧移速度，满/空载" />
        </el-form-item>
        <el-form-item label="行程制动" prop="travelBrake">
          <el-input v-model="form.travelBrake" placeholder="请输入行程制动" />
        </el-form-item>
        <el-form-item label="停车制动" prop="parkingBrake">
          <el-input v-model="form.parkingBrake" placeholder="请输入停车制动" />
        </el-form-item>
        <el-form-item label="桥负荷（满载）前/后" prop="bridgeLoadFullyLoaded">
          <el-input v-model="form.bridgeLoadFullyLoaded" placeholder="请输入桥负荷（满载）前/后" />
        </el-form-item>
        <el-form-item label="桥负荷（空载）前/后" prop="bridgeLoadUnloaded">
          <el-input v-model="form.bridgeLoadUnloaded" placeholder="请输入桥负荷（空载）前/后" />
        </el-form-item>
        <el-form-item label="驱动电机功率" prop="driveMotorPower">
          <el-input v-model="form.driveMotorPower" placeholder="请输入驱动电机功率" />
        </el-form-item>
        <el-form-item label="提升电机功率" prop="increaseMotorPower">
          <el-input v-model="form.increaseMotorPower" placeholder="请输入提升电机功率" />
        </el-form-item>
        <el-form-item label="电压/额定容量" prop="voltageRatedCapacity">
          <el-input v-model="form.voltageRatedCapacity" placeholder="请输入电压/额定容量" />
        </el-form-item>
        <el-form-item label="蓄电池重量" prop="weightOfBattery">
          <el-input v-model="form.weightOfBattery" placeholder="请输入蓄电池重量" />
        </el-form-item>
        <el-form-item label="驱动控制类型" prop="driveControlType">
          <el-input v-model="form.driveControlType" placeholder="请输入驱动控制类型" />
        </el-form-item>
        <el-form-item label="驾驶员耳边噪声" prop="driverEarNoise">
          <el-input v-model="form.driverEarNoise" placeholder="请输入驾驶员耳边噪声" />
        </el-form-item>
        <el-form-item label="转向" prop="turn">
          <el-input v-model="form.turn" placeholder="请输入转向" />
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

<script setup name="Parameter_three">
import { listParameter_three, getParameter_three, delParameter_three, addParameter_three, updateParameter_three,importParameter_three, checkDataExists } from "@/api/marketanalysis/parameter/parameter_three";
const importRef = ref(null);
const updateSupport = ref(false);

/** 导入按钮操作 */
function handleImport() {
  importRef.value.click();
}

/** 文件选择处理 */
async function handleFileChange(e) {
  const files = e.target.files;
  if (!files.length) return;

  try {
    loading.value = true;
    // 先检查数据是否存在
    const res = await checkDataExists();
    const dataExists = res.data;
    if (dataExists) { 
      // 存在数据时询问是否覆盖
      proxy.$modal.confirm('检测到已有数据，是否覆盖？').then(() => {
        updateSupport.value = true;
        uploadFile(files[0]);
      }).catch(() => {
        updateSupport.value = false;
        uploadFile(files[0]);
      });
    } else { 
      // 不存在数据时直接导入
      updateSupport.value = false;
      uploadFile(files[0]);
    }
  } catch (error) {
    proxy.$modal.msgError("数据检查失败：" + error.message);
  } finally {
    loading.value = false;
  }
}

/** 执行上传 */
async function uploadFile(file) {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('updateSupport', updateSupport.value);

  try {
    loading.value = true;
    await importParameter_three(formData);
    proxy.$modal.msgSuccess("导入成功");
    getList();
  } catch (e) {
    proxy.$modal.msgError("导入失败：" + (e.message || "请检查文件格式和数据有效性"));
  } finally {
    loading.value = false;
    importRef.value.value = ''; // 清空文件选择
    updateSupport.value = false; // 重置覆盖状态
  }
}
const { proxy } = getCurrentInstance();

const parameter_threeList = ref([]);
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

/** 查询III类车参数列表 */
function getList() {
  loading.value = true;
  listParameter_three(queryParams.value).then(response => {
    parameter_threeList.value = response.rows;
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
    drivingStyle: null,
    ratedLiftingCapacity: null,
    raiseHeight: null,
    highestPointLoadCapacity: null,
    loadCenter: null,
    frontSuspensionDistance: null,
    wheelbase: null,
    rearSuspensionDistance: null,
    deadWeight: null,
    tireType: null,
    tireSizeFrontWheel: null,
    tireSizeRearWheel: null,
    numberOfTires: null,
    frontWheelbase: null,
    doorFrameRetractsHeight: null,
    liftingHeight: null,
    maximumHeightDuringHomework: null,
    heightOfRoofProtectionFrame: null,
    platformHeight: null,
    assistInRaisingHeight: null,
    raisePlatformHeightAfterLifting: null,
    totalLength: null,
    vehicleLength: null,
    overallWidth: null,
    forkSize: null,
    installationLevelOfForkFrame: null,
    forkliftWidth: null,
    crossForkWidth: null,
    distanceBetweenGuideWheels: null,
    lateralDisplacementDistance: null,
    extendCenterlineOrkliftDistance: null,
    gapBottomGantryGround: null,
    clearanceCenterWheelbaseGround: null,
    widthOfWorkingChannel: null,
    turningRadius: null,
    distanceForkRotationAxis: null,
    maximumPickingHeight: null,
    palletWidth: null,
    palletLength: null,
    clearWidthOfCockpitEntrance: null,
    clearHeightInsideTheCockpit: null,
    overallWidthOfTheCockpitExterior: null,
    rotatingForkFrameWidth: null,
    cantileverWidth: null,
    descentSpeedR: null,
    runningSpeed: null,
    increaseSpeed: null,
    descentSpeed: null,
    lateralMovementSpeed: null,
    travelBrake: null,
    parkingBrake: null,
    bridgeLoadFullyLoaded: null,
    bridgeLoadUnloaded: null,
    driveMotorPower: null,
    increaseMotorPower: null,
    voltageRatedCapacity: null,
    weightOfBattery: null,
    driveControlType: null,
    driverEarNoise: null,
    turn: null
  };
  proxy.resetForm("parameter_threeRef");
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
  title.value = "添加III类车参数";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getParameter_three(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改III类车参数";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["parameter_threeRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateParameter_three(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addParameter_three(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除III类车参数编号为"' + _ids + '"的数据项？').then(function() {
    return delParameter_three(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/parameter_three/export', {
    ...queryParams.value
  }, `III类车参数表.xlsx`)
}

getList();
</script>