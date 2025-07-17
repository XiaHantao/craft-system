package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二类车参数对象 categorytwo_parameter_table
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class CategorytwoParameterTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 驾驶方式 */
    @Excel(name = "驾驶方式")
    private String drivingStyle;

    /** 额定起重量 */
    @Excel(name = "额定起重量")
    private String ratedLiftingCapacity;

    /** 总提升高度 */
    @Excel(name = "总提升高度")
    private String raiseHeight;

    /** 最高点载重 */
    @Excel(name = "最高点载重")
    private String highestPointLoadCapacity;

    /** 载荷中心 */
    @Excel(name = "载荷中心")
    private String loadCenter;

    /** 前悬距 */
    @Excel(name = "前悬距")
    private String frontSuspensionDistance;

    /** 轴距 */
    @Excel(name = "轴距")
    private String wheelbase;

    /** 后悬距 */
    @Excel(name = "后悬距")
    private String rearSuspensionDistance;

    /** 自重（含电池） */
    @Excel(name = "自重（含电池）")
    private String deadWeight;

    /** 轮胎类型 */
    @Excel(name = "轮胎类型")
    private String tireType;

    /** 轮胎尺寸—前轮 */
    @Excel(name = "轮胎尺寸—前轮")
    private String tireSizeFrontWheel;

    /** 轮胎尺寸—后轮 */
    @Excel(name = "轮胎尺寸—后轮")
    private String tireSizeRearWheel;

    /** 轮胎数量 */
    @Excel(name = "轮胎数量")
    private String numberOfTires;

    /** 前轮距 */
    @Excel(name = "前轮距")
    private String frontWheelbase;

    /** 门架回缩时高度 */
    @Excel(name = "门架回缩时高度")
    private String doorFrameRetractsHeight;

    /** 主起升高度 */
    @Excel(name = "主起升高度")
    private String liftingHeight;

    /** 作业时最大高度 */
    @Excel(name = "作业时最大高度")
    private String maximumHeightDuringHomework;

    /** 护顶架高度 */
    @Excel(name = "护顶架高度")
    private String heightOfRoofProtectionFrame;

    /** 站台高度 */
    @Excel(name = "站台高度")
    private String platformHeight;

    /** 辅助提升高度 */
    @Excel(name = "辅助提升高度")
    private String assistInRaisingHeight;

    /** 提升后站台高度 */
    @Excel(name = "提升后站台高度")
    private String raisePlatformHeightAfterLifting;

    /** 总长 */
    @Excel(name = "总长")
    private String totalLength;

    /** 车体长度（至货叉竖面） */
    @Excel(name = "车体长度（至货叉竖面）")
    private String vehicleLength;

    /** 总宽 */
    @Excel(name = "总宽")
    private String overallWidth;

    /** 货叉尺寸 */
    @Excel(name = "货叉尺寸")
    private String forkSize;

    /** 货叉架安装等级 */
    @Excel(name = "货叉架安装等级")
    private String installationLevelOfForkFrame;

    /** 货叉架宽度 */
    @Excel(name = "货叉架宽度")
    private String forkliftWidth;

    /** 跨货叉宽度 */
    @Excel(name = "跨货叉宽度")
    private String crossForkWidth;

    /** 导轮间距 */
    @Excel(name = "导轮间距")
    private String distanceBetweenGuideWheels;

    /** 侧移距离 */
    @Excel(name = "侧移距离")
    private String lateralDisplacementDistance;

    /** 侧伸出至叉车中心线距离 */
    @Excel(name = "侧伸出至叉车中心线距离")
    private String extendCenterlineOrkliftDistance;

    /** 门架底部离地面间隙 */
    @Excel(name = "门架底部离地面间隙")
    private String gapBottomGantryGround;

    /** 轴距中心离地面间隙 */
    @Excel(name = "轴距中心离地面间隙")
    private String clearanceCenterWheelbaseGround;

    /** 工作通道宽度 */
    @Excel(name = "工作通道宽度")
    private String widthOfWorkingChannel;

    /** 转弯半径 */
    @Excel(name = "转弯半径")
    private String turningRadius;

    /** 前轮轴至货叉旋转轴距离 */
    @Excel(name = "前轮轴至货叉旋转轴距离")
    private String distanceForkRotationAxis;

    /** 最大拣料高度 */
    @Excel(name = "最大拣料高度")
    private String maximumPickingHeight;

    /** 托盘宽度 */
    @Excel(name = "托盘宽度")
    private String palletWidth;

    /** 托盘长度 */
    @Excel(name = "托盘长度")
    private String palletLength;

    /** 驾驶仓入口净宽 */
    @Excel(name = "驾驶仓入口净宽")
    private String clearWidthOfCockpitEntrance;

    /** 驾驶仓内部净高 */
    @Excel(name = "驾驶仓内部净高")
    private String clearHeightInsideTheCockpit;

    /** 驾驶仓外部总宽 */
    @Excel(name = "驾驶仓外部总宽")
    private String overallWidthOfTheCockpitExterior;

    /** 旋转前伸叉架宽度 */
    @Excel(name = "旋转前伸叉架宽度")
    private String rotatingForkFrameWidth;

    /** 悬臂宽度 */
    @Excel(name = "悬臂宽度")
    private String cantileverWidth;

    /** 下降速度（满载/空载） */
    @Excel(name = "下降速度（满载/空载）")
    private String descentSpeedR;

    /** 运行速度，满/空载 */
    @Excel(name = "运行速度，满/空载")
    private String runningSpeed;

    /** 提升速度，满/空载 */
    @Excel(name = "提升速度，满/空载")
    private String increaseSpeed;

    /** 下降速度，满/空载 */
    @Excel(name = "下降速度，满/空载")
    private String descentSpeed;

    /** 侧移速度，满/空载 */
    @Excel(name = "侧移速度，满/空载")
    private String lateralMovementSpeed;

    /** 行程制动 */
    @Excel(name = "行程制动")
    private String travelBrake;

    /** 停车制动 */
    @Excel(name = "停车制动")
    private String parkingBrake;

    /** 桥负荷，（满载）前/后 */
    @Excel(name = "桥负荷，（满载）前/后")
    private String bridgeLoadFullyLoaded;

    /** 桥负荷，（空载）前/后 */
    @Excel(name = "桥负荷，（空载）前/后")
    private String bridgeLoadUnloaded;

    /** 驱动电机功率 */
    @Excel(name = "驱动电机功率")
    private String driveMotorPower;

    /** 提升电机功率 */
    @Excel(name = "提升电机功率")
    private String increaseMotorPower;

    /** 电压/额定容量 */
    @Excel(name = "电压/额定容量")
    private String voltageRatedCapacity;

    /** 蓄电池重量（含辅配重） */
    @Excel(name = "蓄电池重量（含辅配重）")
    private String weightOfBattery;

    /** 驱动控制类型 */
    @Excel(name = "驱动控制类型")
    private String driveControlType;

    /** 驾驶员耳边噪声 */
    @Excel(name = "驾驶员耳边噪声")
    private String driverEarNoise;

    /** 转向 */
    @Excel(name = "转向")
    private String turn;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }
    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }
    public String getManufacturer()
    {
        return manufacturer;
    }
    public String getModel() 
    {
        return model;
    }
    public void setDrivingStyle(String drivingStyle) 
    {
        this.drivingStyle = drivingStyle;
    }

    public String getDrivingStyle() 
    {
        return drivingStyle;
    }
    public void setRatedLiftingCapacity(String ratedLiftingCapacity) 
    {
        this.ratedLiftingCapacity = ratedLiftingCapacity;
    }

    public String getRatedLiftingCapacity() 
    {
        return ratedLiftingCapacity;
    }
    public void setRaiseHeight(String raiseHeight) 
    {
        this.raiseHeight = raiseHeight;
    }

    public String getRaiseHeight() 
    {
        return raiseHeight;
    }
    public void setHighestPointLoadCapacity(String highestPointLoadCapacity) 
    {
        this.highestPointLoadCapacity = highestPointLoadCapacity;
    }

    public String getHighestPointLoadCapacity() 
    {
        return highestPointLoadCapacity;
    }
    public void setLoadCenter(String loadCenter) 
    {
        this.loadCenter = loadCenter;
    }

    public String getLoadCenter() 
    {
        return loadCenter;
    }
    public void setFrontSuspensionDistance(String frontSuspensionDistance) 
    {
        this.frontSuspensionDistance = frontSuspensionDistance;
    }

    public String getFrontSuspensionDistance() 
    {
        return frontSuspensionDistance;
    }
    public void setWheelbase(String wheelbase) 
    {
        this.wheelbase = wheelbase;
    }

    public String getWheelbase() 
    {
        return wheelbase;
    }
    public void setRearSuspensionDistance(String rearSuspensionDistance) 
    {
        this.rearSuspensionDistance = rearSuspensionDistance;
    }

    public String getRearSuspensionDistance() 
    {
        return rearSuspensionDistance;
    }
    public void setDeadWeight(String deadWeight) 
    {
        this.deadWeight = deadWeight;
    }

    public String getDeadWeight() 
    {
        return deadWeight;
    }
    public void setTireType(String tireType) 
    {
        this.tireType = tireType;
    }

    public String getTireType() 
    {
        return tireType;
    }
    public void setTireSizeFrontWheel(String tireSizeFrontWheel) 
    {
        this.tireSizeFrontWheel = tireSizeFrontWheel;
    }

    public String getTireSizeFrontWheel() 
    {
        return tireSizeFrontWheel;
    }
    public void setTireSizeRearWheel(String tireSizeRearWheel) 
    {
        this.tireSizeRearWheel = tireSizeRearWheel;
    }

    public String getTireSizeRearWheel() 
    {
        return tireSizeRearWheel;
    }
    public void setNumberOfTires(String numberOfTires) 
    {
        this.numberOfTires = numberOfTires;
    }

    public String getNumberOfTires() 
    {
        return numberOfTires;
    }
    public void setFrontWheelbase(String frontWheelbase) 
    {
        this.frontWheelbase = frontWheelbase;
    }

    public String getFrontWheelbase() 
    {
        return frontWheelbase;
    }
    public void setDoorFrameRetractsHeight(String doorFrameRetractsHeight) 
    {
        this.doorFrameRetractsHeight = doorFrameRetractsHeight;
    }

    public String getDoorFrameRetractsHeight() 
    {
        return doorFrameRetractsHeight;
    }
    public void setLiftingHeight(String liftingHeight) 
    {
        this.liftingHeight = liftingHeight;
    }

    public String getLiftingHeight() 
    {
        return liftingHeight;
    }
    public void setMaximumHeightDuringHomework(String maximumHeightDuringHomework) 
    {
        this.maximumHeightDuringHomework = maximumHeightDuringHomework;
    }

    public String getMaximumHeightDuringHomework() 
    {
        return maximumHeightDuringHomework;
    }
    public void setHeightOfRoofProtectionFrame(String heightOfRoofProtectionFrame) 
    {
        this.heightOfRoofProtectionFrame = heightOfRoofProtectionFrame;
    }

    public String getHeightOfRoofProtectionFrame() 
    {
        return heightOfRoofProtectionFrame;
    }
    public void setPlatformHeight(String platformHeight) 
    {
        this.platformHeight = platformHeight;
    }

    public String getPlatformHeight() 
    {
        return platformHeight;
    }
    public void setAssistInRaisingHeight(String assistInRaisingHeight) 
    {
        this.assistInRaisingHeight = assistInRaisingHeight;
    }

    public String getAssistInRaisingHeight() 
    {
        return assistInRaisingHeight;
    }
    public void setRaisePlatformHeightAfterLifting(String raisePlatformHeightAfterLifting) 
    {
        this.raisePlatformHeightAfterLifting = raisePlatformHeightAfterLifting;
    }

    public String getRaisePlatformHeightAfterLifting() 
    {
        return raisePlatformHeightAfterLifting;
    }
    public void setTotalLength(String totalLength) 
    {
        this.totalLength = totalLength;
    }

    public String getTotalLength() 
    {
        return totalLength;
    }
    public void setVehicleLength(String vehicleLength) 
    {
        this.vehicleLength = vehicleLength;
    }

    public String getVehicleLength() 
    {
        return vehicleLength;
    }
    public void setOverallWidth(String overallWidth) 
    {
        this.overallWidth = overallWidth;
    }

    public String getOverallWidth() 
    {
        return overallWidth;
    }
    public void setForkSize(String forkSize) 
    {
        this.forkSize = forkSize;
    }

    public String getForkSize() 
    {
        return forkSize;
    }
    public void setInstallationLevelOfForkFrame(String installationLevelOfForkFrame) 
    {
        this.installationLevelOfForkFrame = installationLevelOfForkFrame;
    }

    public String getInstallationLevelOfForkFrame() 
    {
        return installationLevelOfForkFrame;
    }
    public void setForkliftWidth(String forkliftWidth) 
    {
        this.forkliftWidth = forkliftWidth;
    }

    public String getForkliftWidth() 
    {
        return forkliftWidth;
    }
    public void setCrossForkWidth(String crossForkWidth) 
    {
        this.crossForkWidth = crossForkWidth;
    }

    public String getCrossForkWidth() 
    {
        return crossForkWidth;
    }
    public void setDistanceBetweenGuideWheels(String distanceBetweenGuideWheels) 
    {
        this.distanceBetweenGuideWheels = distanceBetweenGuideWheels;
    }

    public String getDistanceBetweenGuideWheels() 
    {
        return distanceBetweenGuideWheels;
    }
    public void setLateralDisplacementDistance(String lateralDisplacementDistance) 
    {
        this.lateralDisplacementDistance = lateralDisplacementDistance;
    }

    public String getLateralDisplacementDistance() 
    {
        return lateralDisplacementDistance;
    }
    public void setExtendCenterlineOrkliftDistance(String extendCenterlineOrkliftDistance) 
    {
        this.extendCenterlineOrkliftDistance = extendCenterlineOrkliftDistance;
    }

    public String getExtendCenterlineOrkliftDistance() 
    {
        return extendCenterlineOrkliftDistance;
    }
    public void setGapBottomGantryGround(String gapBottomGantryGround) 
    {
        this.gapBottomGantryGround = gapBottomGantryGround;
    }

    public String getGapBottomGantryGround() 
    {
        return gapBottomGantryGround;
    }
    public void setClearanceCenterWheelbaseGround(String clearanceCenterWheelbaseGround) 
    {
        this.clearanceCenterWheelbaseGround = clearanceCenterWheelbaseGround;
    }

    public String getClearanceCenterWheelbaseGround() 
    {
        return clearanceCenterWheelbaseGround;
    }
    public void setWidthOfWorkingChannel(String widthOfWorkingChannel) 
    {
        this.widthOfWorkingChannel = widthOfWorkingChannel;
    }

    public String getWidthOfWorkingChannel() 
    {
        return widthOfWorkingChannel;
    }
    public void setTurningRadius(String turningRadius) 
    {
        this.turningRadius = turningRadius;
    }

    public String getTurningRadius() 
    {
        return turningRadius;
    }
    public void setDistanceForkRotationAxis(String distanceForkRotationAxis) 
    {
        this.distanceForkRotationAxis = distanceForkRotationAxis;
    }

    public String getDistanceForkRotationAxis() 
    {
        return distanceForkRotationAxis;
    }
    public void setMaximumPickingHeight(String maximumPickingHeight) 
    {
        this.maximumPickingHeight = maximumPickingHeight;
    }

    public String getMaximumPickingHeight() 
    {
        return maximumPickingHeight;
    }
    public void setPalletWidth(String palletWidth) 
    {
        this.palletWidth = palletWidth;
    }

    public String getPalletWidth() 
    {
        return palletWidth;
    }
    public void setPalletLength(String palletLength) 
    {
        this.palletLength = palletLength;
    }

    public String getPalletLength() 
    {
        return palletLength;
    }
    public void setClearWidthOfCockpitEntrance(String clearWidthOfCockpitEntrance) 
    {
        this.clearWidthOfCockpitEntrance = clearWidthOfCockpitEntrance;
    }

    public String getClearWidthOfCockpitEntrance() 
    {
        return clearWidthOfCockpitEntrance;
    }
    public void setClearHeightInsideTheCockpit(String clearHeightInsideTheCockpit) 
    {
        this.clearHeightInsideTheCockpit = clearHeightInsideTheCockpit;
    }

    public String getClearHeightInsideTheCockpit() 
    {
        return clearHeightInsideTheCockpit;
    }
    public void setOverallWidthOfTheCockpitExterior(String overallWidthOfTheCockpitExterior) 
    {
        this.overallWidthOfTheCockpitExterior = overallWidthOfTheCockpitExterior;
    }

    public String getOverallWidthOfTheCockpitExterior() 
    {
        return overallWidthOfTheCockpitExterior;
    }
    public void setRotatingForkFrameWidth(String rotatingForkFrameWidth) 
    {
        this.rotatingForkFrameWidth = rotatingForkFrameWidth;
    }

    public String getRotatingForkFrameWidth() 
    {
        return rotatingForkFrameWidth;
    }
    public void setCantileverWidth(String cantileverWidth) 
    {
        this.cantileverWidth = cantileverWidth;
    }

    public String getCantileverWidth() 
    {
        return cantileverWidth;
    }
    public void setDescentSpeedR(String descentSpeedR) 
    {
        this.descentSpeedR = descentSpeedR;
    }

    public String getDescentSpeedR() 
    {
        return descentSpeedR;
    }
    public void setRunningSpeed(String runningSpeed) 
    {
        this.runningSpeed = runningSpeed;
    }

    public String getRunningSpeed() 
    {
        return runningSpeed;
    }
    public void setIncreaseSpeed(String increaseSpeed) 
    {
        this.increaseSpeed = increaseSpeed;
    }

    public String getIncreaseSpeed() 
    {
        return increaseSpeed;
    }
    public void setDescentSpeed(String descentSpeed) 
    {
        this.descentSpeed = descentSpeed;
    }

    public String getDescentSpeed() 
    {
        return descentSpeed;
    }
    public void setLateralMovementSpeed(String lateralMovementSpeed) 
    {
        this.lateralMovementSpeed = lateralMovementSpeed;
    }

    public String getLateralMovementSpeed() 
    {
        return lateralMovementSpeed;
    }
    public void setTravelBrake(String travelBrake) 
    {
        this.travelBrake = travelBrake;
    }

    public String getTravelBrake() 
    {
        return travelBrake;
    }
    public void setParkingBrake(String parkingBrake) 
    {
        this.parkingBrake = parkingBrake;
    }

    public String getParkingBrake() 
    {
        return parkingBrake;
    }
    public void setBridgeLoadFullyLoaded(String bridgeLoadFullyLoaded) 
    {
        this.bridgeLoadFullyLoaded = bridgeLoadFullyLoaded;
    }

    public String getBridgeLoadFullyLoaded() 
    {
        return bridgeLoadFullyLoaded;
    }
    public void setBridgeLoadUnloaded(String bridgeLoadUnloaded) 
    {
        this.bridgeLoadUnloaded = bridgeLoadUnloaded;
    }

    public String getBridgeLoadUnloaded() 
    {
        return bridgeLoadUnloaded;
    }
    public void setDriveMotorPower(String driveMotorPower) 
    {
        this.driveMotorPower = driveMotorPower;
    }

    public String getDriveMotorPower() 
    {
        return driveMotorPower;
    }
    public void setIncreaseMotorPower(String increaseMotorPower) 
    {
        this.increaseMotorPower = increaseMotorPower;
    }

    public String getIncreaseMotorPower() 
    {
        return increaseMotorPower;
    }
    public void setVoltageRatedCapacity(String voltageRatedCapacity) 
    {
        this.voltageRatedCapacity = voltageRatedCapacity;
    }

    public String getVoltageRatedCapacity() 
    {
        return voltageRatedCapacity;
    }
    public void setWeightOfBattery(String weightOfBattery) 
    {
        this.weightOfBattery = weightOfBattery;
    }

    public String getWeightOfBattery() 
    {
        return weightOfBattery;
    }
    public void setDriveControlType(String driveControlType) 
    {
        this.driveControlType = driveControlType;
    }

    public String getDriveControlType() 
    {
        return driveControlType;
    }
    public void setDriverEarNoise(String driverEarNoise) 
    {
        this.driverEarNoise = driverEarNoise;
    }

    public String getDriverEarNoise() 
    {
        return driverEarNoise;
    }
    public void setTurn(String turn) 
    {
        this.turn = turn;
    }

    public String getTurn() 
    {
        return turn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("manufacturer", getManufacturer())
                .append("model", getModel())
                .append("drivingStyle", getDrivingStyle())
                .append("ratedLiftingCapacity", getRatedLiftingCapacity())
                .append("raiseHeight", getRaiseHeight())
                .append("highestPointLoadCapacity", getHighestPointLoadCapacity())
                .append("loadCenter", getLoadCenter())
                .append("frontSuspensionDistance", getFrontSuspensionDistance())
                .append("wheelbase", getWheelbase())
                .append("rearSuspensionDistance", getRearSuspensionDistance())
                .append("deadWeight", getDeadWeight())
                .append("tireType", getTireType())
                .append("tireSizeFrontWheel", getTireSizeFrontWheel())
                .append("tireSizeRearWheel", getTireSizeRearWheel())
                .append("numberOfTires", getNumberOfTires())
                .append("frontWheelbase", getFrontWheelbase())
                .append("doorFrameRetractsHeight", getDoorFrameRetractsHeight())
                .append("liftingHeight", getLiftingHeight())
                .append("maximumHeightDuringHomework", getMaximumHeightDuringHomework())
                .append("heightOfRoofProtectionFrame", getHeightOfRoofProtectionFrame())
                .append("platformHeight", getPlatformHeight())
                .append("assistInRaisingHeight", getAssistInRaisingHeight())
                .append("raisePlatformHeightAfterLifting", getRaisePlatformHeightAfterLifting())
                .append("totalLength", getTotalLength())
                .append("vehicleLength", getVehicleLength())
                .append("overallWidth", getOverallWidth())
                .append("forkSize", getForkSize())
                .append("installationLevelOfForkFrame", getInstallationLevelOfForkFrame())
                .append("forkliftWidth", getForkliftWidth())
                .append("crossForkWidth", getCrossForkWidth())
                .append("distanceBetweenGuideWheels", getDistanceBetweenGuideWheels())
                .append("lateralDisplacementDistance", getLateralDisplacementDistance())
                .append("extendCenterlineOrkliftDistance", getExtendCenterlineOrkliftDistance())
                .append("gapBottomGantryGround", getGapBottomGantryGround())
                .append("clearanceCenterWheelbaseGround", getClearanceCenterWheelbaseGround())
                .append("widthOfWorkingChannel", getWidthOfWorkingChannel())
                .append("turningRadius", getTurningRadius())
                .append("distanceForkRotationAxis", getDistanceForkRotationAxis())
                .append("maximumPickingHeight", getMaximumPickingHeight())
                .append("palletWidth", getPalletWidth())
                .append("palletLength", getPalletLength())
                .append("clearWidthOfCockpitEntrance", getClearWidthOfCockpitEntrance())
                .append("clearHeightInsideTheCockpit", getClearHeightInsideTheCockpit())
                .append("overallWidthOfTheCockpitExterior", getOverallWidthOfTheCockpitExterior())
                .append("rotatingForkFrameWidth", getRotatingForkFrameWidth())
                .append("cantileverWidth", getCantileverWidth())
                .append("descentSpeedR", getDescentSpeedR())
                .append("runningSpeed", getRunningSpeed())
                .append("increaseSpeed", getIncreaseSpeed())
                .append("descentSpeed", getDescentSpeed())
                .append("lateralMovementSpeed", getLateralMovementSpeed())
                .append("travelBrake", getTravelBrake())
                .append("parkingBrake", getParkingBrake())
                .append("bridgeLoadFullyLoaded", getBridgeLoadFullyLoaded())
                .append("bridgeLoadUnloaded", getBridgeLoadUnloaded())
                .append("driveMotorPower", getDriveMotorPower())
                .append("increaseMotorPower", getIncreaseMotorPower())
                .append("voltageRatedCapacity", getVoltageRatedCapacity())
                .append("weightOfBattery", getWeightOfBattery())
                .append("driveControlType", getDriveControlType())
                .append("driverEarNoise", getDriverEarNoise())
                .append("turn", getTurn())
                .toString();
    }
}