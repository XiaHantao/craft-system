package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 五类车参数对象 categoryfive_parameter_table
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class CategoryfiveParameterTable extends BaseEntity
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

    /** 起升重量 */
    @Excel(name = "起升重量")
    private String liftingWeight;

    /** 载荷中心 */
    @Excel(name = "载荷中心")
    private String loadCenter;

    /** 动力方式 */
    @Excel(name = "动力方式")
    private String drivingStyle;

    /** 驾驶方式 */
    @Excel(name = "驾驶方式")
    private String powerMode;

    /** 起升高度（标准） */
    @Excel(name = "起升高度", readConverterExp = "标=准")
    private String liftingHeight;

    /** 门架全高（货叉落地、门架垂直） */
    @Excel(name = "门架全高", readConverterExp = "货=叉落地、门架垂直")
    private String doorFrameFullHeight;

    /** 货叉起升最大高度（带挡货架） */
    @Excel(name = "货叉起升最大高度", readConverterExp = "带=挡货架")
    private String maximumLiftingHeightOfFork;

    /** 自由提升高度 */
    @Excel(name = "自由提升高度")
    private String freeElevationHeight;

    /** 全高（护顶架） */
    @Excel(name = "全高", readConverterExp = "护=顶架")
    private String fullHeight;

    /** 最小离地间隙（门架处） */
    @Excel(name = "最小离地间隙", readConverterExp = "门=架处")
    private String minimumGroundClearance;

    /** 全长（含货叉/不含货叉） */
    @Excel(name = "全长", readConverterExp = "含=货叉/不含货叉")
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
    @Excel(name = "轮距", readConverterExp = "前=轮距/后轮距")
    private String trackWidth;

    /** 最小外侧转弯半径 */
    @Excel(name = "最小外侧转弯半径")
    private String minimumOuterTurningRadius;

    /** 门架倾角（前/后） */
    @Excel(name = "门架倾角", readConverterExp = "前=/后")
    private String gateFrameInclinationAngle;

    /** 货叉尺寸 */
    @Excel(name = "货叉尺寸")
    private String forkSize;

    /** 最高行驶速度（满载/空载） */
    @Excel(name = "最高行驶速度", readConverterExp = "满=载/空载")
    private String maximumDrivingSpeed;

    /** 最大爬坡能力（满载/空载） */
    @Excel(name = "最大爬坡能力", readConverterExp = "满=载/空载")
    private String maxClimbCapability;

    /** 最大牵引力（满载/空载） */
    @Excel(name = "最大牵引力", readConverterExp = "满=载/空载")
    private String maximumTractionForce;

    /** 起升速度（满载/空载） */
    @Excel(name = "起升速度", readConverterExp = "满=载/空载")
    private String liftingSpeed;

    /** 下降速度（满载/空载） */
    @Excel(name = "下降速度", readConverterExp = "满=载/空载")
    private String descentSpeed;

    /** 总重 */
    @Excel(name = "总重")
    private String totalWeight;

    /** 重量分配 满载（前/后） */
    @Excel(name = "重量分配 满载", readConverterExp = "前=/后")
    private String weightDistributionWithFullLoad;

    /** 重量分配 空载（前/后） */
    @Excel(name = "重量分配 空载", readConverterExp = "前=/后")
    private String weightDistributionEmptyLoad;

    /** 车轮数量  X=驱动轮（前/后） */
    @Excel(name = "车轮数量  X=驱动轮", readConverterExp = "前=/后")
    private String numberOfWheels;

    /** 轮胎类型（前/后） */
    @Excel(name = "轮胎类型", readConverterExp = "前=/后")
    private String tireType;

    /** 轮胎尺寸（前/后） */
    @Excel(name = "轮胎尺寸", readConverterExp = "前=/后")
    private String tireSize;

    /** 蓄电池（电压/容量） */
    @Excel(name = "蓄电池", readConverterExp = "电=压/容量")
    private String batteryVoltageCapacity;

    /** 发动机制造厂商/型号 */
    @Excel(name = "发动机制造厂商/型号")
    private String engineManufacturer;

    /** 额定功率/转速 */
    @Excel(name = "额定功率/转速")
    private String ratedPowerSpeed;

    /** 最大扭矩/转速 */
    @Excel(name = "最大扭矩/转速")
    private String maximumTorqueSpeed;

    /** 缸数-缸径×行程/排量 */
    @Excel(name = "缸数-缸径×行程/排量")
    private String numberOfCylinders;

    /** 燃油箱容积 */
    @Excel(name = "燃油箱容积")
    private String fuelTankCapacity;

    /** 排放标准 */
    @Excel(name = "排放标准")
    private String emissionStandard;

    /** 变速箱变速档数（前/后 型式） */
    @Excel(name = "变速箱变速档数", readConverterExp = "前=/后,型=式")
    private String gearsInTheGearbox;

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
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
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
    public void setDrivingStyle(String drivingStyle) 
    {
        this.drivingStyle = drivingStyle;
    }

    public String getDrivingStyle() 
    {
        return drivingStyle;
    }
    public void setPowerMode(String powerMode) 
    {
        this.powerMode = powerMode;
    }

    public String getPowerMode() 
    {
        return powerMode;
    }
    public void setLiftingHeight(String liftingHeight) 
    {
        this.liftingHeight = liftingHeight;
    }

    public String getLiftingHeight() 
    {
        return liftingHeight;
    }
    public void setDoorFrameFullHeight(String doorFrameFullHeight) 
    {
        this.doorFrameFullHeight = doorFrameFullHeight;
    }

    public String getDoorFrameFullHeight() 
    {
        return doorFrameFullHeight;
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
    public void setEngineManufacturer(String engineManufacturer) 
    {
        this.engineManufacturer = engineManufacturer;
    }

    public String getEngineManufacturer() 
    {
        return engineManufacturer;
    }
    public void setRatedPowerSpeed(String ratedPowerSpeed) 
    {
        this.ratedPowerSpeed = ratedPowerSpeed;
    }

    public String getRatedPowerSpeed() 
    {
        return ratedPowerSpeed;
    }
    public void setMaximumTorqueSpeed(String maximumTorqueSpeed) 
    {
        this.maximumTorqueSpeed = maximumTorqueSpeed;
    }

    public String getMaximumTorqueSpeed() 
    {
        return maximumTorqueSpeed;
    }
    public void setNumberOfCylinders(String numberOfCylinders) 
    {
        this.numberOfCylinders = numberOfCylinders;
    }

    public String getNumberOfCylinders() 
    {
        return numberOfCylinders;
    }
    public void setFuelTankCapacity(String fuelTankCapacity) 
    {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public String getFuelTankCapacity() 
    {
        return fuelTankCapacity;
    }
    public void setEmissionStandard(String emissionStandard) 
    {
        this.emissionStandard = emissionStandard;
    }

    public String getEmissionStandard() 
    {
        return emissionStandard;
    }
    public void setGearsInTheGearbox(String gearsInTheGearbox) 
    {
        this.gearsInTheGearbox = gearsInTheGearbox;
    }

    public String getGearsInTheGearbox() 
    {
        return gearsInTheGearbox;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("manufacturer", getManufacturer())
            .append("model", getModel())
            .append("liftingWeight", getLiftingWeight())
            .append("loadCenter", getLoadCenter())
            .append("drivingStyle", getDrivingStyle())
            .append("powerMode", getPowerMode())
            .append("liftingHeight", getLiftingHeight())
            .append("doorFrameFullHeight", getDoorFrameFullHeight())
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
            .append("engineManufacturer", getEngineManufacturer())
            .append("ratedPowerSpeed", getRatedPowerSpeed())
            .append("maximumTorqueSpeed", getMaximumTorqueSpeed())
            .append("numberOfCylinders", getNumberOfCylinders())
            .append("fuelTankCapacity", getFuelTankCapacity())
            .append("emissionStandard", getEmissionStandard())
            .append("gearsInTheGearbox", getGearsInTheGearbox())
            .toString();
    }
}
