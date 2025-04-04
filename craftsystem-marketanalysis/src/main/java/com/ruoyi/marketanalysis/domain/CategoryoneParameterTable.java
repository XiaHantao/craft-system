package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 一类车参数对象 categoryone_parameter_table
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class CategoryoneParameterTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;

    /** 配置号 */
    @Excel(name = "配置号")
    private String configurationNumber;

    /** 起升重量 */
    @Excel(name = "起升重量")
    private String liftingWeight;

    /** 载荷中心 */
    @Excel(name = "载荷中心")
    private String loadCenter;

    /** 动力方式 */
    @Excel(name = "动力方式")
    private String powerMode;

    /** 驾驶方式 */
    @Excel(name = "驾驶方式")
    private String drivingStyle;

    /** 起升高度（标准） */
    @Excel(name = "起升高度（标准）")
    private String liftingHeight;

    /** 门架全高（货叉落地、门架垂直） */
    @Excel(name = "门架全高（货叉落地、门架垂直）")
    private String doorframeHeight;

    /** 货叉起升最大高度（带挡货架） */
    @Excel(name = "货叉起升最大高度（带挡货架）")
    private String maximumLiftingHeightOfFork;

    /** 自由提升高度 */
    @Excel(name = "自由提升高度")
    private String freeElevationHeight;

    /** 全高（护顶架） */
    @Excel(name = "全高（护顶架）")
    private String fullHeight;

    /** 最小离地间隙（门架处） */
    @Excel(name = "最小离地间隙（门架处）")
    private String minimumGroundClearance;

    /** 全长（含货叉/不含货叉） */
    @Excel(name = "全长（含货叉/不含货叉）")
    private String overallLength;

    /** 轴距 */
    @Excel(name = "轴距")
    private String wheelbase;

    /** 前悬距 */
    @Excel(name = "前悬距")
    private String frontSuspensionDistance;

    /** 后悬距 */
    @Excel(name = "后悬距")
    private String rearSuspensionDistance;

    /** 全宽 */
    @Excel(name = "全宽")
    private String fullWidth;

    /** 轮距（前轮距/后轮距） */
    @Excel(name = "轮距（前轮距/后轮距）")
    private String trackWidth;

    /** 最小外侧转弯半径 */
    @Excel(name = "最小外侧转弯半径")
    private String minimumOuterTurningRadius;

    /** 门架倾角（前/后） */
    @Excel(name = "门架倾角（前/后）")
    private String gateFrameInclinationAngle;

    /** 货叉尺寸 */
    @Excel(name = "货叉尺寸")
    private String forkSize;

    /** 最高行驶速度（满载/空载） */
    @Excel(name = " 最高行驶速度（满载/空载）")
    private String maximumDrivingSpeed;

    /** 最大爬坡能力（满载/空载） */
    @Excel(name = "最大爬坡能力（满载/空载）")
    private String maxClimbCapability;

    /** 最大牵引力（满载/空载） */
    @Excel(name = "最大牵引力（满载/空载）")
    private String maximumTractionForce;

    /** 起升速度（满载/空载） */
    @Excel(name = "起升速度（满载/空载）")
    private String liftingSpeed;

    /** 下降速度（满载/空载） */
    @Excel(name = "下降速度（满载/空载）")
    private String descentSpeed;

    /** 总重 */
    @Excel(name = "总重")
    private String totalWeight;

    /** 重量分配 满载（前/后） */
    @Excel(name = "重量分配 满载（前/后）")
    private String weightDistributionWithFullLoad;

    /** 重量分配 空载（前/后） */
    @Excel(name = "重量分配 空载（前/后）")
    private String weightDistributionEmptyLoad;

    /** 车轮数量  X=驱动轮（前/后） */
    @Excel(name = "车轮数量  X=驱动轮（前/后）")
    private String numberOfWheels;

    /** 轮胎类型（前/后） */
    @Excel(name = "轮胎类型（前/后）")
    private String tireType;

    /** 轮胎尺寸（前/后） */
    @Excel(name = "轮胎尺寸（前/后）")
    private String tireSize;

    /** 蓄电池电压/容量 */
    @Excel(name = "蓄电池电压/容量")
    private String batteryVoltageCapacity;

    /** 驱动电机功率(S2-60min) */
    @Excel(name = "驱动电机功率(S2-60min)")
    private String driveMotorPower;

    /** 起升电机功率(S3-15%) */
    @Excel(name = "起升电机功率(S3-15%)")
    private String powerOfLiftingMotor;

    /** 驱动电机控制方式 */
    @Excel(name = "驱动电机控制方式")
    private String driveMotorControlMode;

    /** 起升电机控制方式 */
    @Excel(name = "起升电机控制方式")
    private String controlModeOfLiftingMotor;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setConfigurationNumber(String configurationNumber) 
    {
        this.configurationNumber = configurationNumber;
    }

    public String getConfigurationNumber() 
    {
        return configurationNumber;
    }
    public void setLiftingWeight(String liftingWeight) 
    {
        this.liftingWeight = liftingWeight;
    }

    public String getLiftingWeight() 
    {
        return liftingWeight;
    }
    public void setLoadCenter(String loadCenter) 
    {
        this.loadCenter = loadCenter;
    }

    public String getLoadCenter() 
    {
        return loadCenter;
    }
    public void setPowerMode(String powerMode) 
    {
        this.powerMode = powerMode;
    }

    public String getPowerMode() 
    {
        return powerMode;
    }
    public void setDrivingStyle(String drivingStyle) 
    {
        this.drivingStyle = drivingStyle;
    }

    public String getDrivingStyle() 
    {
        return drivingStyle;
    }
    public void setLiftingHeight(String liftingHeight) 
    {
        this.liftingHeight = liftingHeight;
    }

    public String getLiftingHeight() 
    {
        return liftingHeight;
    }
    public void setDoorframeHeight(String doorframeHeight) 
    {
        this.doorframeHeight = doorframeHeight;
    }

    public String getDoorframeHeight() 
    {
        return doorframeHeight;
    }
    public void setMaximumLiftingHeightOfFork(String maximumLiftingHeightOfFork) 
    {
        this.maximumLiftingHeightOfFork = maximumLiftingHeightOfFork;
    }

    public String getMaximumLiftingHeightOfFork() 
    {
        return maximumLiftingHeightOfFork;
    }
    public void setFreeElevationHeight(String freeElevationHeight) 
    {
        this.freeElevationHeight = freeElevationHeight;
    }

    public String getFreeElevationHeight() 
    {
        return freeElevationHeight;
    }
    public void setFullHeight(String fullHeight) 
    {
        this.fullHeight = fullHeight;
    }

    public String getFullHeight() 
    {
        return fullHeight;
    }
    public void setMinimumGroundClearance(String minimumGroundClearance) 
    {
        this.minimumGroundClearance = minimumGroundClearance;
    }

    public String getMinimumGroundClearance() 
    {
        return minimumGroundClearance;
    }
    public void setOverallLength(String overallLength) 
    {
        this.overallLength = overallLength;
    }

    public String getOverallLength() 
    {
        return overallLength;
    }
    public void setWheelbase(String wheelbase) 
    {
        this.wheelbase = wheelbase;
    }

    public String getWheelbase() 
    {
        return wheelbase;
    }
    public void setFrontSuspensionDistance(String frontSuspensionDistance) 
    {
        this.frontSuspensionDistance = frontSuspensionDistance;
    }

    public String getFrontSuspensionDistance() 
    {
        return frontSuspensionDistance;
    }
    public void setRearSuspensionDistance(String rearSuspensionDistance) 
    {
        this.rearSuspensionDistance = rearSuspensionDistance;
    }

    public String getRearSuspensionDistance() 
    {
        return rearSuspensionDistance;
    }
    public void setFullWidth(String fullWidth) 
    {
        this.fullWidth = fullWidth;
    }

    public String getFullWidth() 
    {
        return fullWidth;
    }
    public void setTrackWidth(String trackWidth) 
    {
        this.trackWidth = trackWidth;
    }

    public String getTrackWidth() 
    {
        return trackWidth;
    }
    public void setMinimumOuterTurningRadius(String minimumOuterTurningRadius) 
    {
        this.minimumOuterTurningRadius = minimumOuterTurningRadius;
    }

    public String getMinimumOuterTurningRadius() 
    {
        return minimumOuterTurningRadius;
    }
    public void setGateFrameInclinationAngle(String gateFrameInclinationAngle) 
    {
        this.gateFrameInclinationAngle = gateFrameInclinationAngle;
    }

    public String getGateFrameInclinationAngle() 
    {
        return gateFrameInclinationAngle;
    }
    public void setForkSize(String forkSize) 
    {
        this.forkSize = forkSize;
    }

    public String getForkSize() 
    {
        return forkSize;
    }
    public void setMaximumDrivingSpeed(String maximumDrivingSpeed) 
    {
        this.maximumDrivingSpeed = maximumDrivingSpeed;
    }

    public String getMaximumDrivingSpeed() 
    {
        return maximumDrivingSpeed;
    }
    public void setMaxClimbCapability(String maxClimbCapability) 
    {
        this.maxClimbCapability = maxClimbCapability;
    }

    public String getMaxClimbCapability() 
    {
        return maxClimbCapability;
    }
    public void setMaximumTractionForce(String maximumTractionForce) 
    {
        this.maximumTractionForce = maximumTractionForce;
    }

    public String getMaximumTractionForce() 
    {
        return maximumTractionForce;
    }
    public void setLiftingSpeed(String liftingSpeed) 
    {
        this.liftingSpeed = liftingSpeed;
    }

    public String getLiftingSpeed() 
    {
        return liftingSpeed;
    }
    public void setDescentSpeed(String descentSpeed) 
    {
        this.descentSpeed = descentSpeed;
    }

    public String getDescentSpeed() 
    {
        return descentSpeed;
    }
    public void setTotalWeight(String totalWeight) 
    {
        this.totalWeight = totalWeight;
    }

    public String getTotalWeight() 
    {
        return totalWeight;
    }
    public void setWeightDistributionWithFullLoad(String weightDistributionWithFullLoad) 
    {
        this.weightDistributionWithFullLoad = weightDistributionWithFullLoad;
    }

    public String getWeightDistributionWithFullLoad() 
    {
        return weightDistributionWithFullLoad;
    }
    public void setWeightDistributionEmptyLoad(String weightDistributionEmptyLoad) 
    {
        this.weightDistributionEmptyLoad = weightDistributionEmptyLoad;
    }

    public String getWeightDistributionEmptyLoad() 
    {
        return weightDistributionEmptyLoad;
    }
    public void setNumberOfWheels(String numberOfWheels) 
    {
        this.numberOfWheels = numberOfWheels;
    }

    public String getNumberOfWheels() 
    {
        return numberOfWheels;
    }
    public void setTireType(String tireType) 
    {
        this.tireType = tireType;
    }

    public String getTireType() 
    {
        return tireType;
    }
    public void setTireSize(String tireSize) 
    {
        this.tireSize = tireSize;
    }

    public String getTireSize() 
    {
        return tireSize;
    }
    public void setBatteryVoltageCapacity(String batteryVoltageCapacity) 
    {
        this.batteryVoltageCapacity = batteryVoltageCapacity;
    }

    public String getBatteryVoltageCapacity() 
    {
        return batteryVoltageCapacity;
    }
    public void setDriveMotorPower(String driveMotorPower) 
    {
        this.driveMotorPower = driveMotorPower;
    }

    public String getDriveMotorPower() 
    {
        return driveMotorPower;
    }
    public void setPowerOfLiftingMotor(String powerOfLiftingMotor) 
    {
        this.powerOfLiftingMotor = powerOfLiftingMotor;
    }

    public String getPowerOfLiftingMotor() 
    {
        return powerOfLiftingMotor;
    }
    public void setDriveMotorControlMode(String driveMotorControlMode) 
    {
        this.driveMotorControlMode = driveMotorControlMode;
    }

    public String getDriveMotorControlMode() 
    {
        return driveMotorControlMode;
    }
    public void setControlModeOfLiftingMotor(String controlModeOfLiftingMotor) 
    {
        this.controlModeOfLiftingMotor = controlModeOfLiftingMotor;
    }

    public String getControlModeOfLiftingMotor() 
    {
        return controlModeOfLiftingMotor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("manufacturer", getManufacturer())
            .append("configurationNumber", getConfigurationNumber())
            .append("liftingWeight", getLiftingWeight())
            .append("loadCenter", getLoadCenter())
            .append("powerMode", getPowerMode())
            .append("drivingStyle", getDrivingStyle())
            .append("liftingHeight", getLiftingHeight())
            .append("doorframeHeight", getDoorframeHeight())
            .append("maximumLiftingHeightOfFork", getMaximumLiftingHeightOfFork())
            .append("freeElevationHeight", getFreeElevationHeight())
            .append("fullHeight", getFullHeight())
            .append("minimumGroundClearance", getMinimumGroundClearance())
            .append("overallLength", getOverallLength())
            .append("wheelbase", getWheelbase())
            .append("frontSuspensionDistance", getFrontSuspensionDistance())
            .append("rearSuspensionDistance", getRearSuspensionDistance())
            .append("fullWidth", getFullWidth())
            .append("trackWidth", getTrackWidth())
            .append("minimumOuterTurningRadius", getMinimumOuterTurningRadius())
            .append("gateFrameInclinationAngle", getGateFrameInclinationAngle())
            .append("forkSize", getForkSize())
            .append("maximumDrivingSpeed", getMaximumDrivingSpeed())
            .append("maxClimbCapability", getMaxClimbCapability())
            .append("maximumTractionForce", getMaximumTractionForce())
            .append("liftingSpeed", getLiftingSpeed())
            .append("descentSpeed", getDescentSpeed())
            .append("totalWeight", getTotalWeight())
            .append("weightDistributionWithFullLoad", getWeightDistributionWithFullLoad())
            .append("weightDistributionEmptyLoad", getWeightDistributionEmptyLoad())
            .append("numberOfWheels", getNumberOfWheels())
            .append("tireType", getTireType())
            .append("tireSize", getTireSize())
            .append("batteryVoltageCapacity", getBatteryVoltageCapacity())
            .append("driveMotorPower", getDriveMotorPower())
            .append("powerOfLiftingMotor", getPowerOfLiftingMotor())
            .append("driveMotorControlMode", getDriveMotorControlMode())
            .append("controlModeOfLiftingMotor", getControlModeOfLiftingMotor())
            .toString();
    }
}
