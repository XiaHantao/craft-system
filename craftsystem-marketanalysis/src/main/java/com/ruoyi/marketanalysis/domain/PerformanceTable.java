package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 性能表对象 performance_table
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
public class PerformanceTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 车类 */
    @Excel(name = "车类")
    private String vehicleCategory;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleType;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;

    /** 最高行驶速度（满载） */
    @Excel(name = "最高行驶速度", readConverterExp = "满=载")
    private String maximumDrivingSpeedFull;

    /** 最高行驶速度（空载） */
    @Excel(name = "最高行驶速度", readConverterExp = "空=载")
    private String maximumDrivingSpeedEmpty;

    /** 最大爬坡能力（满载） */
    @Excel(name = "最大爬坡能力", readConverterExp = "满=载")
    private String maxClimbCapabilityFull;

    /** 最大爬坡能力（空载） */
    @Excel(name = "最大爬坡能力", readConverterExp = "空=载")
    private String maxClimbCapabilityEmpty;

    /** 最大牵引力（满载） */
    @Excel(name = "最大牵引力", readConverterExp = "满=载")
    private String maximumTractionForceFull;

    /** 最大牵引力（空载） */
    @Excel(name = "最大牵引力", readConverterExp = "空=载")
    private String maximumTractionForceEmpty;

    /** 起升速度（满载） */
    @Excel(name = "起升速度", readConverterExp = "满=载")
    private String liftingSpeedFull;

    /** 起升速度（空载） */
    @Excel(name = "起升速度", readConverterExp = "空=载")
    private String liftingSpeedEmpty;

    /** 下降速度（满载） */
    @Excel(name = "下降速度", readConverterExp = "满=载")
    private String descentSpeedFull;

    /** 下降速度（空载） */
    @Excel(name = "下降速度", readConverterExp = "空=载")
    private String descentSpeedEmpty;

    /** 运行速度（满载） */
    @Excel(name = "运行速度", readConverterExp = "满=载")
    private String runningSpeedFull;

    /** 运行速度（空载） */
    @Excel(name = "运行速度", readConverterExp = "空=载")
    private String runningSpeedEmpty;

    /** 提升速度（满载） */
    @Excel(name = "提升速度", readConverterExp = "满=载")
    private String increaseSpeedFull;

    /** 提升速度（空载） */
    @Excel(name = "提升速度", readConverterExp = "空=载")
    private String increaseSpeedEmpty;

    /** 侧移速度（满载） */
    @Excel(name = "侧移速度", readConverterExp = "满=载")
    private String lateralMovementSpeedFull;

    /** 侧移速度（空载） */
    @Excel(name = "侧移速度", readConverterExp = "空=载")
    private String lateralMovementSpeedEmpty;

    /** 行程制动 */
    @Excel(name = "行程制动")
    private String travelBrake;

    /** 停车制动 */
    @Excel(name = "停车制动")
    private String parkingBrake;

    /** 桥负荷（满载）前 */
    @Excel(name = "桥负荷", readConverterExp = "满=载")
    private String bridgeLoadFullyLoadedFront;

    /** 桥负荷（满载）后 */
    @Excel(name = "桥负荷", readConverterExp = "满=载")
    private String bridgeLoadFullyLoadedBehind;

    /** 桥负荷（空载）前 */
    @Excel(name = "桥负荷", readConverterExp = "空=载")
    private String bridgeLoadUnloadedFront;

    /** 桥负荷（空载）后 */
    @Excel(name = "桥负荷", readConverterExp = "空=载")
    private String bridgeLoadUnloadedBehind;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVehicleCategory(String vehicleCategory) 
    {
        this.vehicleCategory = vehicleCategory;
    }

    public String getVehicleCategory() 
    {
        return vehicleCategory;
    }
    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setMaximumDrivingSpeedFull(String maximumDrivingSpeedFull) 
    {
        this.maximumDrivingSpeedFull = maximumDrivingSpeedFull;
    }

    public String getMaximumDrivingSpeedFull() 
    {
        return maximumDrivingSpeedFull;
    }
    public void setMaximumDrivingSpeedEmpty(String maximumDrivingSpeedEmpty) 
    {
        this.maximumDrivingSpeedEmpty = maximumDrivingSpeedEmpty;
    }

    public String getMaximumDrivingSpeedEmpty() 
    {
        return maximumDrivingSpeedEmpty;
    }
    public void setMaxClimbCapabilityFull(String maxClimbCapabilityFull) 
    {
        this.maxClimbCapabilityFull = maxClimbCapabilityFull;
    }

    public String getMaxClimbCapabilityFull() 
    {
        return maxClimbCapabilityFull;
    }
    public void setMaxClimbCapabilityEmpty(String maxClimbCapabilityEmpty) 
    {
        this.maxClimbCapabilityEmpty = maxClimbCapabilityEmpty;
    }

    public String getMaxClimbCapabilityEmpty() 
    {
        return maxClimbCapabilityEmpty;
    }
    public void setMaximumTractionForceFull(String maximumTractionForceFull) 
    {
        this.maximumTractionForceFull = maximumTractionForceFull;
    }

    public String getMaximumTractionForceFull() 
    {
        return maximumTractionForceFull;
    }
    public void setMaximumTractionForceEmpty(String maximumTractionForceEmpty) 
    {
        this.maximumTractionForceEmpty = maximumTractionForceEmpty;
    }

    public String getMaximumTractionForceEmpty() 
    {
        return maximumTractionForceEmpty;
    }
    public void setLiftingSpeedFull(String liftingSpeedFull) 
    {
        this.liftingSpeedFull = liftingSpeedFull;
    }

    public String getLiftingSpeedFull() 
    {
        return liftingSpeedFull;
    }
    public void setLiftingSpeedEmpty(String liftingSpeedEmpty) 
    {
        this.liftingSpeedEmpty = liftingSpeedEmpty;
    }

    public String getLiftingSpeedEmpty() 
    {
        return liftingSpeedEmpty;
    }
    public void setDescentSpeedFull(String descentSpeedFull) 
    {
        this.descentSpeedFull = descentSpeedFull;
    }

    public String getDescentSpeedFull() 
    {
        return descentSpeedFull;
    }
    public void setDescentSpeedEmpty(String descentSpeedEmpty) 
    {
        this.descentSpeedEmpty = descentSpeedEmpty;
    }

    public String getDescentSpeedEmpty() 
    {
        return descentSpeedEmpty;
    }
    public void setRunningSpeedFull(String runningSpeedFull) 
    {
        this.runningSpeedFull = runningSpeedFull;
    }

    public String getRunningSpeedFull() 
    {
        return runningSpeedFull;
    }
    public void setRunningSpeedEmpty(String runningSpeedEmpty) 
    {
        this.runningSpeedEmpty = runningSpeedEmpty;
    }

    public String getRunningSpeedEmpty() 
    {
        return runningSpeedEmpty;
    }
    public void setIncreaseSpeedFull(String increaseSpeedFull) 
    {
        this.increaseSpeedFull = increaseSpeedFull;
    }

    public String getIncreaseSpeedFull() 
    {
        return increaseSpeedFull;
    }
    public void setIncreaseSpeedEmpty(String increaseSpeedEmpty) 
    {
        this.increaseSpeedEmpty = increaseSpeedEmpty;
    }

    public String getIncreaseSpeedEmpty() 
    {
        return increaseSpeedEmpty;
    }
    public void setLateralMovementSpeedFull(String lateralMovementSpeedFull) 
    {
        this.lateralMovementSpeedFull = lateralMovementSpeedFull;
    }

    public String getLateralMovementSpeedFull() 
    {
        return lateralMovementSpeedFull;
    }
    public void setLateralMovementSpeedEmpty(String lateralMovementSpeedEmpty) 
    {
        this.lateralMovementSpeedEmpty = lateralMovementSpeedEmpty;
    }

    public String getLateralMovementSpeedEmpty() 
    {
        return lateralMovementSpeedEmpty;
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
    public void setBridgeLoadFullyLoadedFront(String bridgeLoadFullyLoadedFront) 
    {
        this.bridgeLoadFullyLoadedFront = bridgeLoadFullyLoadedFront;
    }

    public String getBridgeLoadFullyLoadedFront() 
    {
        return bridgeLoadFullyLoadedFront;
    }
    public void setBridgeLoadFullyLoadedBehind(String bridgeLoadFullyLoadedBehind) 
    {
        this.bridgeLoadFullyLoadedBehind = bridgeLoadFullyLoadedBehind;
    }

    public String getBridgeLoadFullyLoadedBehind() 
    {
        return bridgeLoadFullyLoadedBehind;
    }
    public void setBridgeLoadUnloadedFront(String bridgeLoadUnloadedFront) 
    {
        this.bridgeLoadUnloadedFront = bridgeLoadUnloadedFront;
    }

    public String getBridgeLoadUnloadedFront() 
    {
        return bridgeLoadUnloadedFront;
    }
    public void setBridgeLoadUnloadedBehind(String bridgeLoadUnloadedBehind) 
    {
        this.bridgeLoadUnloadedBehind = bridgeLoadUnloadedBehind;
    }

    public String getBridgeLoadUnloadedBehind() 
    {
        return bridgeLoadUnloadedBehind;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleCategory", getVehicleCategory())
            .append("vehicleType", getVehicleType())
            .append("manufacturer", getManufacturer())
            .append("maximumDrivingSpeedFull", getMaximumDrivingSpeedFull())
            .append("maximumDrivingSpeedEmpty", getMaximumDrivingSpeedEmpty())
            .append("maxClimbCapabilityFull", getMaxClimbCapabilityFull())
            .append("maxClimbCapabilityEmpty", getMaxClimbCapabilityEmpty())
            .append("maximumTractionForceFull", getMaximumTractionForceFull())
            .append("maximumTractionForceEmpty", getMaximumTractionForceEmpty())
            .append("liftingSpeedFull", getLiftingSpeedFull())
            .append("liftingSpeedEmpty", getLiftingSpeedEmpty())
            .append("descentSpeedFull", getDescentSpeedFull())
            .append("descentSpeedEmpty", getDescentSpeedEmpty())
            .append("runningSpeedFull", getRunningSpeedFull())
            .append("runningSpeedEmpty", getRunningSpeedEmpty())
            .append("increaseSpeedFull", getIncreaseSpeedFull())
            .append("increaseSpeedEmpty", getIncreaseSpeedEmpty())
            .append("lateralMovementSpeedFull", getLateralMovementSpeedFull())
            .append("lateralMovementSpeedEmpty", getLateralMovementSpeedEmpty())
            .append("travelBrake", getTravelBrake())
            .append("parkingBrake", getParkingBrake())
            .append("bridgeLoadFullyLoadedFront", getBridgeLoadFullyLoadedFront())
            .append("bridgeLoadFullyLoadedBehind", getBridgeLoadFullyLoadedBehind())
            .append("bridgeLoadUnloadedFront", getBridgeLoadUnloadedFront())
            .append("bridgeLoadUnloadedBehind", getBridgeLoadUnloadedBehind())
            .toString();
    }
}
