<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车型" prop="vehicleType">
        <el-input
          v-model="queryParams.vehicleType"
          placeholder="请输入车型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="制造商" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入制造商"
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
          v-hasPermi="['marketanalysis:standardfive:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:standardfive:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:standardfive:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:standardfive:export']"
        >导出</el-button>
      </el-col>
       <el-col :span="1.5">
        <el-button type="info" plain icon="Upload" @click="handleImport">导入</el-button>
      </el-col>
      <input ref="importRef" type="file" hidden accept=".xlsx, .xls" @change="handleFileChange" />
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="standardfiveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
        <el-table-column label="车型" align="center" prop="vehicleType" />
      <el-table-column label="制造商" align="center" prop="manufacturer" />
      <el-table-column label="充气胎" align="center" prop="pneumaticTire" />
      <el-table-column label="实心胎" align="center" prop="solidTire" />
      <el-table-column label="单胎" align="center" prop="singleTire" />
      <el-table-column label="双胎" align="center" prop="twinTires" />
      <el-table-column label="两片阀" align="center" prop="twoPieceValve" />
      <el-table-column label="三片或四片阀" align="center" prop="threeOrFourPieceValve" />
      <el-table-column label="基本型3米起升高度" align="center" prop="threeMeterLiftingHeight" />
      <el-table-column label="其它型及其它起升高度门架" align="center" prop="liftingHeightGantry" />
      <el-table-column label="标准系列货叉" align="center" prop="standardSeriesFork" />
      <el-table-column label="非标系列货叉、属具" align="center" prop="nonStandardSeriesForks" />
      <el-table-column label="标准车身颜色（蓝灰色+合力红）" align="center" prop="standardBodyColor" />
      <el-table-column label="其它车身颜色（客户定制）" align="center" prop="customizedBodyColor" />
      <el-table-column label="全套OPS功能" align="center" prop="completeOpsFunctionality" />
      <el-table-column label="行走OPS功能" align="center" prop="walkingOpsFunction" />
      <el-table-column label="超速报警" align="center" prop="overspeed" />
      <el-table-column label="倒车扶手带喇叭开关" align="center" prop="reverseArmrestWithHorn" />
      <el-table-column label="座椅带安全带开关" align="center" prop="seatBeltSwitch" />
      <el-table-column label="多路阀过载" align="center" prop="overloadOfMultiWayValve" />
      <el-table-column label="灭火器" align="center" prop="fireExtinguisher" />
      <el-table-column label="倒车蜂鸣器" align="center" prop="reverseBuzzer" />
      <el-table-column label="中央广角后视镜" align="center" prop="centralWideangleRearviewMirror" />
      <el-table-column label="两侧后视镜" align="center" prop="twoSideRearviewMirrors" />
      <el-table-column label="倒车雷达" align="center" prop="parkingSensor" />
      <el-table-column label="半包围座椅" align="center" prop="semiEnclosedSeat" />
      <el-table-column label="全悬浮座椅" align="center" prop="fullySuspendedSeat" />
      <el-table-column label="USB接口" align="center" prop="usbInterface" />
      <el-table-column label="电风扇" align="center" prop="electricFan" />
      <el-table-column label="方向盘启动转向" align="center" prop="turnTheSteeringWheel" />
      <el-table-column label="同步转向" align="center" prop="synchronousSteering" />
      <el-table-column label="标准护顶架" align="center" prop="standardRoofProtectionFrame" />
      <el-table-column label="非标护顶架" align="center" prop="nonStandardRoofProtectionFrame" />
      <el-table-column label="前挡风玻璃（带雨刮器）" align="center" prop="windscreen" />
      <el-table-column label="前耐高温挡风玻璃（带雨刮器）" align="center" prop="frontHeatresistantWindshield" />
      <el-table-column label="后挡风玻璃（不带雨刮器）" align="center" prop="rearWindshield" />
      <el-table-column label="PC耐力板顶棚" align="center" prop="pcEnduranceBoardCeiling" />
      <el-table-column label="贴片式驾驶室" align="center" prop="surfaceMountedCab" />
      <el-table-column label="单冷空调" align="center" prop="singleCoolingAirConditioner" />
      <el-table-column label="冷暖两用空调" align="center" prop="dualPurposeAirConditioner" />
      <el-table-column label="暖风机" align="center" prop="warmAirBlower" />
      <el-table-column label="全车LED灯" align="center" prop="fullCarLedLights" />
      <el-table-column label="LED后工作灯" align="center" prop="ledRearWorkLight" />
      <el-table-column label="车后方蓝光灯" align="center" prop="blueLightBehindTheCar" />
      <el-table-column label="三边蓝色条形灯" align="center" prop="threeSidedBlueStripLight" />
      <el-table-column label="三边红色条形灯" align="center" prop="threeSidedRedStripLights" />
      <el-table-column label="配装LED闪光警示灯（装配式）" align="center" prop="ledFlashingWarningLight" />
      <el-table-column label="配装LED旋转式警示灯（装配式）" align="center" prop="ledRotatingWarningLight" />
      <el-table-column label="配装LED旋转蜂鸣式警示灯（装配式）" align="center" prop="ledRotatingBuzzerWarningLight" />
      <el-table-column label="公制螺纹" align="center" prop="metric" />
      <el-table-column label="倾斜油缸护套" align="center" prop="tiltCylinderSleeve" />
      <el-table-column label="转向油缸护套" align="center" prop="steeringCylinderSleeve" />
      <el-table-column label="中位排气" align="center" prop="medianExhaust" />
      <el-table-column label="高排气" align="center" prop="highExhaust" />
      <el-table-column label="两驱" align="center" prop="twoWheelDrive" />
      <el-table-column label="四驱" align="center" prop="fourWheelDrive" />
      <el-table-column label="牙嵌式差速锁" align="center" prop="dentalEmbeddedDifferentialLock" />
      <el-table-column label="限滑差速器" align="center" prop="limitedSlipDifferential" />
      <el-table-column label="手动差速锁" align="center" prop="manualDifferentialLock" />
      <el-table-column label="FICS合力车联网基本版（国内）" align="center" prop="ficsHeliConnectedCarBasicEdition" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:standardfive:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:standardfive:remove']">删除</el-button>
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

    <!-- 添加或修改V类车/VII类车标准配置对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="standardfiveRef" :model="form" :rules="rules" label-width="140px">
         <el-form-item label="车型" prop="vehicleType">
          <el-input v-model="form.vehicleType" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="充气胎" prop="pneumaticTire">
          <el-select v-model="form.pneumaticTire" placeholder="请选择充气胎" >
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="实心胎" prop="solidTire">
          <el-select v-model="form.solidTire" placeholder="请选择实心胎">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="单胎（前轮）" prop="singleTire">
          <el-select v-model="form.singleTire" placeholder="请选择单胎（前轮）">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="双胎（前轮）" prop="twinTires">
          <el-select v-model="form.twinTires" placeholder="请选择双胎（前轮）">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="两片阀" prop="twoPieceValve">
          <el-select v-model="form.twoPieceValve" placeholder="请选择两片阀">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="三片或四片阀" prop="threeOrFourPieceValve">
          <el-select v-model="form.threeOrFourPieceValve" placeholder="请选择三片或四片阀">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="基本型3米起升高度" prop="threeMeterLiftingHeight">
          <el-select v-model="form.threeMeterLiftingHeight" placeholder="请选择基本型3米起升高度">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="其它型起升高度门架" prop="liftingHeightGantry">
          <el-select v-model="form.liftingHeightGantry" placeholder="请选择其它型及其它起升高度门架">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="标准系列货叉" prop="standardSeriesFork">
          <el-select v-model="form.standardSeriesFork" placeholder="请选择标准系列货叉">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="非标系列货叉属具" prop="nonStandardSeriesForks">
          <el-select v-model="form.nonStandardSeriesForks" placeholder="请选择非标系列货叉属具">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="标准车身颜色" prop="standardBodyColor">
          <el-select v-model="form.standardBodyColor" placeholder="请选择标准车身颜色">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="其它车身颜色" prop="customizedBodyColor">
          <el-select v-model="form.customizedBodyColor" placeholder="请选择其它车身颜色">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="全套OPS功能" prop="completeOpsFunctionality">
          <el-select v-model="form.completeOpsFunctionality" placeholder="请选择全套OPS功能">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="行走OPS功能" prop="walkingOpsFunction">
          <el-select v-model="form.walkingOpsFunction" placeholder="请选择行走OPS功能">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="超速报警" prop="overspeed">
          <el-select v-model="form.overspeed" placeholder="请选择超速报警">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="倒车扶手带喇叭开关" prop="reverseArmrestWithHorn">
          <el-select v-model="form.reverseArmrestWithHorn" placeholder="请选择倒车扶手带喇叭开关">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="座椅带安全带开关" prop="seatBeltSwitch">
          <el-select v-model="form.seatBeltSwitch" placeholder="请选择座椅带安全带开关">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="多路阀过载" prop="overloadOfMultiWayValve">
          <el-select v-model="form.overloadOfMultiWayValve" placeholder="请选择多路阀过载">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="灭火器" prop="fireExtinguisher">
          <el-select v-model="form.fireExtinguisher" placeholder="请选择灭火器">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="倒车蜂鸣器" prop="reverseBuzzer">
          <el-select v-model="form.reverseBuzzer" placeholder="请选择倒车蜂鸣器">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="中央广角后视镜" prop="centralWideangleRearviewMirror">
          <el-select v-model="form.centralWideangleRearviewMirror" placeholder="请选择中央广角后视镜">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="两侧后视镜" prop="twoSideRearviewMirrors">
          <el-select v-model="form.twoSideRearviewMirrors" placeholder="请选择两侧后视镜">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="倒车雷达" prop="parkingSensor">
          <el-select v-model="form.parkingSensor" placeholder="请选择倒车雷达">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="半包围座椅" prop="semiEnclosedSeat">
          <el-select v-model="form.semiEnclosedSeat" placeholder="请选择半包围座椅">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="全悬浮座椅" prop="fullySuspendedSeat">
          <el-select v-model="form.fullySuspendedSeat" placeholder="请选择全悬浮座椅">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="USB接口" prop="usbInterface">
          <el-select v-model="form.usbInterface" placeholder="请选择USB接口">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="电风扇" prop="electricFan">
          <el-select v-model="form.electricFan" placeholder="请选择电风扇">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="方向盘启动转向" prop="turnTheSteeringWheel">
          <el-select v-model="form.turnTheSteeringWheel" placeholder="请选择方向盘启动转向">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="同步转向" prop="synchronousSteering">
          <el-select v-model="form.synchronousSteering" placeholder="请选择同步转向">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="标准护顶架" prop="standardRoofProtectionFrame">
          <el-select v-model="form.standardRoofProtectionFrame" placeholder="请选择标准护顶架">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="非标护顶架" prop="nonStandardRoofProtectionFrame">
          <el-select v-model="form.nonStandardRoofProtectionFrame" placeholder="请选择非标护顶架">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="前挡风玻璃（带雨刮器）" prop="windscreen">
          <el-select v-model="form.windscreen" placeholder="请选择前挡风玻璃">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="前耐高温挡风玻璃（带雨刮器）" prop="frontHeatresistantWindshield">
          <el-select v-model="form.frontHeatresistantWindshield" placeholder="请选择前耐高温挡风玻璃">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="后挡风玻璃（不带雨刮器）" prop="rearWindshield">
          <el-select v-model="form.rearWindshield" placeholder="请选择后挡风玻璃">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="PC耐力板顶棚" prop="pcEnduranceBoardCeiling">
          <el-select v-model="form.pcEnduranceBoardCeiling" placeholder="请选择PC耐力板顶棚">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="贴片式驾驶室" prop="surfaceMountedCab">
          <el-select v-model="form.surfaceMountedCab" placeholder="请选择贴片式驾驶室">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="单冷空调" prop="singleCoolingAirConditioner">
          <el-select v-model="form.singleCoolingAirConditioner" placeholder="请选择单冷空调">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="冷暖两用空调" prop="dualPurposeAirConditioner">
          <el-select v-model="form.dualPurposeAirConditioner" placeholder="请选择冷暖两用空调">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="暖风机" prop="warmAirBlower">
          <el-select v-model="form.warmAirBlower" placeholder="请选择暖风机">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="全车LED灯" prop="fullCarLedLights">
          <el-select v-model="form.fullCarLedLights" placeholder="请选择全车LED灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="LED后工作灯" prop="ledRearWorkLight">
          <el-select v-model="form.ledRearWorkLight" placeholder="请选择LED后工作灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="车后方蓝光灯" prop="blueLightBehindTheCar">
          <el-select v-model="form.blueLightBehindTheCar" placeholder="请选择车后方蓝光灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>   
        <el-form-item label="三边蓝色条形灯" prop="threeSidedBlueStripLight">
          <el-select v-model="form.threeSidedBlueStripLight" placeholder="请选择三边蓝色条形灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="三边红色条形灯" prop="threeSidedRedStripLights">
          <el-select v-model="form.threeSidedRedStripLights" placeholder="请选择三边红色条形灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>      
        </el-form-item>
        <el-form-item label="LED闪光警示灯" prop="ledFlashingWarningLight">
          <el-select v-model="form.ledFlashingWarningLight" placeholder="请选择LED闪光警示灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="LED旋转式警示灯" prop="ledRotatingWarningLight">
          <el-select v-model="form.ledRotatingWarningLight" placeholder="请选择LED旋转式警示灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="LED旋转蜂鸣式警示灯" prop="ledRotatingBuzzerWarningLight">
          <el-select v-model="form.ledRotatingBuzzerWarningLight" placeholder="请选择LED旋转蜂鸣式警示灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="公制螺纹" prop="metric">
          <el-select v-model="form.metric" placeholder="请选择公制螺纹">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="倾斜油缸护套" prop="tiltCylinderSleeve">
          <el-select v-model="form.tiltCylinderSleeve" placeholder="请选择倾斜油缸护套">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="转向油缸护套" prop="steeringCylinderSleeve">
          <el-select v-model="form.steeringCylinderSleeve" placeholder="请选择转向油缸护套">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="中位排气" prop="medianExhaust">
          <el-select v-model="form.medianExhaust" placeholder="请选择中位排气">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="高排气" prop="highExhaust">
          <el-select v-model="form.highExhaust" placeholder="请选择高排气">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="两驱" prop="twoWheelDrive">
          <el-select v-model="form.twoWheelDrive" placeholder="请选择两驱">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="四驱" prop="fourWheelDrive">
          <el-select v-model="form.fourWheelDrive" placeholder="请选择四驱">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="牙嵌式差速锁" prop="dentalEmbeddedDifferentialLock">
          <el-select v-model="form.dentalEmbeddedDifferentialLock" placeholder="请选择牙嵌式差速锁">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="限滑差速器" prop="limitedSlipDifferential">
          <el-select v-model="form.limitedSlipDifferential" placeholder="请选择限滑差速器">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="手动差速锁" prop="manualDifferentialLock">
          <el-select v-model="form.manualDifferentialLock" placeholder="请选择手动差速锁">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="FICS合力车联网" prop="ficsHeliConnectedCarBasicEdition">
          <el-select v-model="form.ficsHeliConnectedCarBasicEdition" placeholder="请选择FICS合力车联网">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
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

<script setup name="Standardfive">
import { listStandardfive, getStandardfive, delStandardfive, addStandardfive, updateStandardfive,importstandard_five,checkDataExists } from "@/api/marketanalysis/standard/standardfive";
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
    await importstandard_five(formData);
    proxy.$modal.msgSuccess("导入成功");
    getList();
  } catch (e) {
    proxy.$modal.msgError("导入失败");
  } finally {
    importRef.value.value = '';
  }
};
const { proxy } = getCurrentInstance();
// 添加选项数据
const options = ref([
  { value: '标配', label: '标配' },
  { value: '选配', label: '选配' },
  { value: '无', label: '无' }
]);

const standardfiveList = ref([]);
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
  },
  rules: {
    vehicleType: [
      { required: true, message: "车型不能为空", trigger: "blur" }
    ],
    manufacturer: [
      { required: true, message: "制造商不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询V类车/VII类车标准配置列表 */
function getList() {
  loading.value = true;
  listStandardfive(queryParams.value).then(response => {
    standardfiveList.value = response.rows;
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
    vehicleType: null,
    manufacturer: null,
    pneumaticTire: null,
    solidTire: null,
    singleTire: null,
    twinTires: null,
    twoPieceValve: null,
    threeOrFourPieceValve: null,
    threeMeterLiftingHeight: null,
    liftingHeightGantry: null,
    standardSeriesFork: null,
    nonStandardSeriesForks: null,
    standardBodyColor: null,
    customizedBodyColor: null,
    completeOpsFunctionality: null,
    walkingOpsFunction: null,
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
    medianExhaust: null,
    highExhaust: null,
    twoWheelDrive: null,
    fourWheelDrive: null,
    dentalEmbeddedDifferentialLock: null,
    limitedSlipDifferential: null,
    manualDifferentialLock: null,
    ficsHeliConnectedCarBasicEdition: null
  };
  proxy.resetForm("standardfiveRef");
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
  title.value = "添加V类车/VII类车标准配置";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getStandardfive(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改V类车/VII类车标准配置";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["standardfiveRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateStandardfive(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addStandardfive(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除V类车/VII类车标准配置编号为"' + _ids + '"的数据项？').then(function() {
    return delStandardfive(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/standardfive/export', {
    ...queryParams.value
  }, `V类车/VII类车标准配置表.xlsx`)
}

getList();
</script>
