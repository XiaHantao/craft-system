package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二类车标准配置对象 categorytwo_standard_configuration_table
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class CategorytwoStandardConfigurationTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;

    /** 聚氨酯轮胎 */
    @Excel(name = "聚氨酯轮胎")
    private String polyurethaneTires;

    /** 基本型5米起升高度 */
    @Excel(name = "基本型5米起升高度")
    private String fiveMeterLiftingHeight;

    /** 其它型及其它起升高度门架 */
    @Excel(name = "其它型及其它起升高度门架")
    private String liftingHeightGantry;

    /** 标准系列货叉 */
    @Excel(name = "标准系列货叉")
    private String standardSeriesFork;

    /** 非标系列货叉 */
    @Excel(name = "非标系列货叉")
    private String nonStandardSeriesFork;

    /** 标准车身颜色（蓝灰色+合力红） */
    @Excel(name = "标准车身颜色（蓝灰色+合力红）")
    private String standardBodyColor;

    /** 其它车身颜色（客户定制） */
    @Excel(name = "其它车身颜色（客户定制）")
    private String customizedBodyColor;

    /** 属具未到位限速 */
    @Excel(name = "属具未到位限速")
    private String accessoriesAndSpeedLimit;

    /** 转弯限速 */
    @Excel(name = "转弯限速")
    private String turningSpeedLimit;

    /** 灭火器 */
    @Excel(name = "灭火器")
    private String fireExtinguisher;

    /** 倒车蜂鸣器 */
    @Excel(name = "倒车蜂鸣器")
    private String reverseBuzzer;

    /** 倒车语音喇叭 */
    @Excel(name = "倒车语音喇叭")
    private String reverseVoiceHorn;

    /** 两侧后视镜 */
    @Excel(name = "两侧后视镜")
    private String twoSideRearviewMirrors;

    /** 逃生包 */
    @Excel(name = "逃生包")
    private String escapeBag;

    /** 后轮制动 */
    @Excel(name = "后轮制动")
    private String rearWheelBraking;

    /** 前轮制动 */
    @Excel(name = "前轮制动")
    private String frontWheelBrake;

    /** 导向轮 */
    @Excel(name = "导向轮")
    private String guideWheel;

    /** 监控（货叉、倒车影像） */
    @Excel(name = "监控（货叉、倒车影像）")
    private String monitor;

    /** 磁导航 */
    @Excel(name = "磁导航")
    private String magneticNavigation;

    /** 站驾软包座椅 */
    @Excel(name = "站驾软包座椅")
    private String stationDrivingSoftPackSeat;

    /** 座驾式座椅 */
    @Excel(name = "座驾式座椅")
    private String carStyleSeats;

    /** 电风扇 */
    @Excel(name = "电风扇")
    private String electricFan;

    /** 前挡风玻璃 */
    @Excel(name = "前挡风玻璃")
    private String windscreen;

    /** 后挡风玻璃 */
    @Excel(name = "后挡风玻璃")
    private String rearWindshield;

    /** 全车LED灯 */
    @Excel(name = "全车LED灯")
    private String fullCarLedLights;

    /** 阅读灯 */
    @Excel(name = "阅读灯")
    private String readingLamp;

    /** 配装LED闪光警示灯（装配式） */
    @Excel(name = "配装LED闪光警示灯（装配式）")
    private String ledFlashingWarningLight;

    /** 公制螺纹 */
    @Excel(name = "公制螺纹")
    private String metric;

    /** 国标电源接插件 */
    @Excel(name = "国标电源接插件")
    private String nationalStandardPowerConnector;

    /** 中文标识 */
    @Excel(name = "中文标识")
    private String chineseIdentification;

    /** 英文标识 */
    @Excel(name = "英文标识")
    private String englishLogo;

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
    public void setPolyurethaneTires(String polyurethaneTires) 
    {
        this.polyurethaneTires = polyurethaneTires;
    }

    public String getPolyurethaneTires() 
    {
        return polyurethaneTires;
    }
    public void setFiveMeterLiftingHeight(String fiveMeterLiftingHeight) 
    {
        this.fiveMeterLiftingHeight = fiveMeterLiftingHeight;
    }

    public String getFiveMeterLiftingHeight() 
    {
        return fiveMeterLiftingHeight;
    }
    public void setLiftingHeightGantry(String liftingHeightGantry) 
    {
        this.liftingHeightGantry = liftingHeightGantry;
    }

    public String getLiftingHeightGantry() 
    {
        return liftingHeightGantry;
    }
    public void setStandardSeriesFork(String standardSeriesFork) 
    {
        this.standardSeriesFork = standardSeriesFork;
    }

    public String getStandardSeriesFork() 
    {
        return standardSeriesFork;
    }
    public void setNonStandardSeriesFork(String nonStandardSeriesFork) 
    {
        this.nonStandardSeriesFork = nonStandardSeriesFork;
    }

    public String getNonStandardSeriesFork() 
    {
        return nonStandardSeriesFork;
    }
    public void setStandardBodyColor(String standardBodyColor) 
    {
        this.standardBodyColor = standardBodyColor;
    }

    public String getStandardBodyColor() 
    {
        return standardBodyColor;
    }
    public void setCustomizedBodyColor(String customizedBodyColor) 
    {
        this.customizedBodyColor = customizedBodyColor;
    }

    public String getCustomizedBodyColor() 
    {
        return customizedBodyColor;
    }
    public void setAccessoriesAndSpeedLimit(String accessoriesAndSpeedLimit) 
    {
        this.accessoriesAndSpeedLimit = accessoriesAndSpeedLimit;
    }

    public String getAccessoriesAndSpeedLimit() 
    {
        return accessoriesAndSpeedLimit;
    }
    public void setTurningSpeedLimit(String turningSpeedLimit) 
    {
        this.turningSpeedLimit = turningSpeedLimit;
    }

    public String getTurningSpeedLimit() 
    {
        return turningSpeedLimit;
    }
    public void setFireExtinguisher(String fireExtinguisher) 
    {
        this.fireExtinguisher = fireExtinguisher;
    }

    public String getFireExtinguisher() 
    {
        return fireExtinguisher;
    }
    public void setReverseBuzzer(String reverseBuzzer) 
    {
        this.reverseBuzzer = reverseBuzzer;
    }

    public String getReverseBuzzer() 
    {
        return reverseBuzzer;
    }
    public void setReverseVoiceHorn(String reverseVoiceHorn) 
    {
        this.reverseVoiceHorn = reverseVoiceHorn;
    }

    public String getReverseVoiceHorn() 
    {
        return reverseVoiceHorn;
    }
    public void setTwoSideRearviewMirrors(String twoSideRearviewMirrors) 
    {
        this.twoSideRearviewMirrors = twoSideRearviewMirrors;
    }

    public String getTwoSideRearviewMirrors() 
    {
        return twoSideRearviewMirrors;
    }
    public void setEscapeBag(String escapeBag) 
    {
        this.escapeBag = escapeBag;
    }

    public String getEscapeBag() 
    {
        return escapeBag;
    }
    public void setRearWheelBraking(String rearWheelBraking) 
    {
        this.rearWheelBraking = rearWheelBraking;
    }

    public String getRearWheelBraking() 
    {
        return rearWheelBraking;
    }
    public void setFrontWheelBrake(String frontWheelBrake) 
    {
        this.frontWheelBrake = frontWheelBrake;
    }

    public String getFrontWheelBrake() 
    {
        return frontWheelBrake;
    }
    public void setGuideWheel(String guideWheel) 
    {
        this.guideWheel = guideWheel;
    }

    public String getGuideWheel() 
    {
        return guideWheel;
    }
    public void setMonitor(String monitor) 
    {
        this.monitor = monitor;
    }

    public String getMonitor() 
    {
        return monitor;
    }
    public void setMagneticNavigation(String magneticNavigation) 
    {
        this.magneticNavigation = magneticNavigation;
    }

    public String getMagneticNavigation() 
    {
        return magneticNavigation;
    }
    public void setStationDrivingSoftPackSeat(String stationDrivingSoftPackSeat) 
    {
        this.stationDrivingSoftPackSeat = stationDrivingSoftPackSeat;
    }

    public String getStationDrivingSoftPackSeat() 
    {
        return stationDrivingSoftPackSeat;
    }
    public void setCarStyleSeats(String carStyleSeats) 
    {
        this.carStyleSeats = carStyleSeats;
    }

    public String getCarStyleSeats() 
    {
        return carStyleSeats;
    }
    public void setElectricFan(String electricFan) 
    {
        this.electricFan = electricFan;
    }

    public String getElectricFan() 
    {
        return electricFan;
    }
    public void setWindscreen(String windscreen) 
    {
        this.windscreen = windscreen;
    }

    public String getWindscreen() 
    {
        return windscreen;
    }
    public void setRearWindshield(String rearWindshield) 
    {
        this.rearWindshield = rearWindshield;
    }

    public String getRearWindshield() 
    {
        return rearWindshield;
    }
    public void setFullCarLedLights(String fullCarLedLights) 
    {
        this.fullCarLedLights = fullCarLedLights;
    }

    public String getFullCarLedLights() 
    {
        return fullCarLedLights;
    }
    public void setReadingLamp(String readingLamp) 
    {
        this.readingLamp = readingLamp;
    }

    public String getReadingLamp() 
    {
        return readingLamp;
    }
    public void setLedFlashingWarningLight(String ledFlashingWarningLight) 
    {
        this.ledFlashingWarningLight = ledFlashingWarningLight;
    }

    public String getLedFlashingWarningLight() 
    {
        return ledFlashingWarningLight;
    }
    public void setMetric(String metric) 
    {
        this.metric = metric;
    }

    public String getMetric() 
    {
        return metric;
    }
    public void setNationalStandardPowerConnector(String nationalStandardPowerConnector) 
    {
        this.nationalStandardPowerConnector = nationalStandardPowerConnector;
    }

    public String getNationalStandardPowerConnector() 
    {
        return nationalStandardPowerConnector;
    }
    public void setChineseIdentification(String chineseIdentification) 
    {
        this.chineseIdentification = chineseIdentification;
    }

    public String getChineseIdentification() 
    {
        return chineseIdentification;
    }
    public void setEnglishLogo(String englishLogo) 
    {
        this.englishLogo = englishLogo;
    }

    public String getEnglishLogo() 
    {
        return englishLogo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("manufacturer", getManufacturer())
            .append("polyurethaneTires", getPolyurethaneTires())
            .append("fiveMeterLiftingHeight", getFiveMeterLiftingHeight())
            .append("liftingHeightGantry", getLiftingHeightGantry())
            .append("standardSeriesFork", getStandardSeriesFork())
            .append("nonStandardSeriesFork", getNonStandardSeriesFork())
            .append("standardBodyColor", getStandardBodyColor())
            .append("customizedBodyColor", getCustomizedBodyColor())
            .append("accessoriesAndSpeedLimit", getAccessoriesAndSpeedLimit())
            .append("turningSpeedLimit", getTurningSpeedLimit())
            .append("fireExtinguisher", getFireExtinguisher())
            .append("reverseBuzzer", getReverseBuzzer())
            .append("reverseVoiceHorn", getReverseVoiceHorn())
            .append("twoSideRearviewMirrors", getTwoSideRearviewMirrors())
            .append("escapeBag", getEscapeBag())
            .append("rearWheelBraking", getRearWheelBraking())
            .append("frontWheelBrake", getFrontWheelBrake())
            .append("guideWheel", getGuideWheel())
            .append("monitor", getMonitor())
            .append("magneticNavigation", getMagneticNavigation())
            .append("stationDrivingSoftPackSeat", getStationDrivingSoftPackSeat())
            .append("carStyleSeats", getCarStyleSeats())
            .append("electricFan", getElectricFan())
            .append("windscreen", getWindscreen())
            .append("rearWindshield", getRearWindshield())
            .append("fullCarLedLights", getFullCarLedLights())
            .append("readingLamp", getReadingLamp())
            .append("ledFlashingWarningLight", getLedFlashingWarningLight())
            .append("metric", getMetric())
            .append("nationalStandardPowerConnector", getNationalStandardPowerConnector())
            .append("chineseIdentification", getChineseIdentification())
            .append("englishLogo", getEnglishLogo())
            .toString();
    }
}
