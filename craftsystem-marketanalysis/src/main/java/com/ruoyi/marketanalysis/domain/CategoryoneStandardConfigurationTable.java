package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 一类车标准配置对象 categoryone_standard_configuration_table
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
public class CategoryoneStandardConfigurationTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;

    /** 充气胎 */
    @Excel(name = "充气胎")
    private String pneumaticTire;

    /** 实心胎 */
    @Excel(name = "实心胎")
    private String solidTire;

    /** 单胎（前轮） */
    @Excel(name = "单胎", readConverterExp = "前=轮")
    private String singleTire;

    /** 双胎（前轮） */
    @Excel(name = "双胎", readConverterExp = "前=轮")
    private String dualTire;

    /** 两片阀 */
    @Excel(name = "两片阀")
    private String twoPieceValve;

    /** 三片或四片阀 */
    @Excel(name = "三片或四片阀")
    private String threeOrFourPieceValve;

    /** 基本型3米起升高度 */
    @Excel(name = "基本型3米起升高度")
    private String threeMeterLiftingHeight;

    /** 其它型及其它起升高度门架 */
    @Excel(name = "其它型及其它起升高度门架")
    private String liftingHeightGantry;

    /** 标准系列货叉 */
    @Excel(name = "标准系列货叉")
    private String standardSeriesFork;

    /** 非标系列货叉、属具 */
    @Excel(name = "非标系列货叉、属具")
    private String nonStandardSeriesForks;

    /** 标准车身颜色（蓝灰色+合力红） */
    @Excel(name = "标准车身颜色", readConverterExp = "蓝=灰色+合力红")
    private String standardBodyColor;

    /** 其它车身颜色（客户定制） */
    @Excel(name = "其它车身颜色", readConverterExp = "客=户定制")
    private String customizedBodyColor;

    /** 全套OPS功能 */
    @Excel(name = "全套OPS功能")
    private String completeOpsFunctionality;

    /** 转弯限速 */
    @Excel(name = "转弯限速")
    private String turningSpeedLimit;

    /** 超速报警 */
    @Excel(name = "超速报警")
    private String overspeed;

    /** 倒车扶手带喇叭开关 */
    @Excel(name = "倒车扶手带喇叭开关")
    private String reverseArmrestWithHorn;

    /** 座椅带安全带开关 */
    @Excel(name = "座椅带安全带开关")
    private String seatBeltSwitch;

    /** 多路阀过载 */
    @Excel(name = "多路阀过载")
    private String overloadOfMultiWayValve;

    /** 灭火器 */
    @Excel(name = "灭火器")
    private String fireExtinguisher;

    /** 倒车蜂鸣器 */
    @Excel(name = "倒车蜂鸣器")
    private String reverseBuzzer;

    /** 中央广角后视镜 */
    @Excel(name = "中央广角后视镜")
    private String centralWideangleRearviewMirror;

    /** 两侧后视镜 */
    @Excel(name = "两侧后视镜")
    private String twoSideRearviewMirrors;

    /** 倒车雷达 */
    @Excel(name = "倒车雷达")
    private String parkingSensor;

    /** 半包围座椅 */
    @Excel(name = "半包围座椅")
    private String semiEnclosedSeat;

    /** 全悬浮座椅 */
    @Excel(name = "全悬浮座椅")
    private String fullySuspendedSeat;

    /** USB接口 */
    @Excel(name = "USB接口")
    private String usbInterface;

    /** 电风扇 */
    @Excel(name = "电风扇")
    private String electricFan;

    /** 方向盘启动转向 */
    @Excel(name = "方向盘启动转向")
    private String turnTheSteeringWheel;

    /** 同步转向 */
    @Excel(name = "同步转向")
    private String synchronousSteering;

    /** 标准护顶架 */
    @Excel(name = "标准护顶架")
    private String standardRoofProtectionFrame;

    /** 非标护顶架 */
    @Excel(name = "非标护顶架")
    private String nonStandardRoofProtectionFrame;

    /** 前挡风玻璃（带雨刮器） */
    @Excel(name = "前挡风玻璃", readConverterExp = "带=雨刮器")
    private String windscreen;

    /** 前耐高温挡风玻璃（带雨刮器） */
    @Excel(name = "前耐高温挡风玻璃", readConverterExp = "带=雨刮器")
    private String frontHeatresistantWindshield;

    /** 后挡风玻璃（不带雨刮器） */
    @Excel(name = "后挡风玻璃", readConverterExp = "不=带雨刮器")
    private String rearWindshield;

    /** PC耐力板顶棚 */
    @Excel(name = "PC耐力板顶棚")
    private String pcEnduranceBoardCeiling;

    /** 贴片式驾驶室 */
    @Excel(name = "贴片式驾驶室")
    private String surfaceMountedCab;

    /** 单冷空调 */
    @Excel(name = "单冷空调")
    private String singleCoolingAirConditioner;

    /** 冷暖两用空调 */
    @Excel(name = "冷暖两用空调")
    private String dualPurposeAirConditioner;

    /** 暖风机 */
    @Excel(name = "暖风机")
    private String warmAirBlower;

    /** 全车LED灯 */
    @Excel(name = "全车LED灯")
    private String fullCarLedLights;

    /** LED后工作灯 */
    @Excel(name = "LED后工作灯")
    private String ledRearWorkLight;

    /** 车后方蓝光灯 */
    @Excel(name = "车后方蓝光灯")
    private String blueLightBehindTheCar;

    /** 三边蓝色条形灯 */
    @Excel(name = "三边蓝色条形灯")
    private String threeSidedBlueStripLight;

    /** 三边红色条形灯 */
    @Excel(name = "三边红色条形灯")
    private String threeSidedRedStripLights;

    /** 配装LED闪光警示灯（装配式） */
    @Excel(name = "配装LED闪光警示灯", readConverterExp = "装=配式")
    private String ledFlashingWarningLight;

    /** 配装LED旋转式警示灯（装配式） */
    @Excel(name = "配装LED旋转式警示灯", readConverterExp = "装=配式")
    private String ledRotatingWarningLight;

    /** 配装LED旋转蜂鸣式警示灯（装配式） */
    @Excel(name = "配装LED旋转蜂鸣式警示灯", readConverterExp = "装=配式")
    private String ledRotatingBuzzerWarningLight;

    /** 公制螺纹 */
    @Excel(name = "公制螺纹")
    private String metric;

    /** 倾斜油缸护套 */
    @Excel(name = "倾斜油缸护套")
    private String tiltCylinderSleeve;

    /** 转向油缸护套 */
    @Excel(name = "转向油缸护套")
    private String steeringCylinderSleeve;

    /** REMA电源接插件 */
    @Excel(name = "REMA电源接插件")
    private String remaPowerConnector;

    /** 国标电源接插件 */
    @Excel(name = "国标电源接插件")
    private String nationalStandardPowerConnector;

    /** 国产蓄电池 */
    @Excel(name = "国产蓄电池")
    private String domesticBattery;

    /** FICS合力车联网基本版（国内） */
    @Excel(name = "FICS合力车联网基本版", readConverterExp = "国=内")
    private String ficsHeliConnectedCarBasicEdition;

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
    public void setPneumaticTire(String pneumaticTire) 
    {
        this.pneumaticTire = pneumaticTire;
    }

    public String getPneumaticTire() 
    {
        return pneumaticTire;
    }
    public void setSolidTire(String solidTire) 
    {
        this.solidTire = solidTire;
    }

    public String getSolidTire() 
    {
        return solidTire;
    }
    public void setSingleTire(String singleTire) 
    {
        this.singleTire = singleTire;
    }

    public String getSingleTire() 
    {
        return singleTire;
    }
    public void setDualTire(String dualTire) 
    {
        this.dualTire = dualTire;
    }

    public String getDualTire() 
    {
        return dualTire;
    }
    public void setTwoPieceValve(String twoPieceValve) 
    {
        this.twoPieceValve = twoPieceValve;
    }

    public String getTwoPieceValve() 
    {
        return twoPieceValve;
    }
    public void setThreeOrFourPieceValve(String threeOrFourPieceValve) 
    {
        this.threeOrFourPieceValve = threeOrFourPieceValve;
    }

    public String getThreeOrFourPieceValve() 
    {
        return threeOrFourPieceValve;
    }
    public void setThreeMeterLiftingHeight(String threeMeterLiftingHeight) 
    {
        this.threeMeterLiftingHeight = threeMeterLiftingHeight;
    }

    public String getThreeMeterLiftingHeight() 
    {
        return threeMeterLiftingHeight;
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
    public void setNonStandardSeriesForks(String nonStandardSeriesForks) 
    {
        this.nonStandardSeriesForks = nonStandardSeriesForks;
    }

    public String getNonStandardSeriesForks() 
    {
        return nonStandardSeriesForks;
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
    public void setCompleteOpsFunctionality(String completeOpsFunctionality) 
    {
        this.completeOpsFunctionality = completeOpsFunctionality;
    }

    public String getCompleteOpsFunctionality() 
    {
        return completeOpsFunctionality;
    }
    public void setTurningSpeedLimit(String turningSpeedLimit) 
    {
        this.turningSpeedLimit = turningSpeedLimit;
    }

    public String getTurningSpeedLimit() 
    {
        return turningSpeedLimit;
    }
    public void setOverspeed(String overspeed) 
    {
        this.overspeed = overspeed;
    }

    public String getOverspeed() 
    {
        return overspeed;
    }
    public void setReverseArmrestWithHorn(String reverseArmrestWithHorn) 
    {
        this.reverseArmrestWithHorn = reverseArmrestWithHorn;
    }

    public String getReverseArmrestWithHorn() 
    {
        return reverseArmrestWithHorn;
    }
    public void setSeatBeltSwitch(String seatBeltSwitch) 
    {
        this.seatBeltSwitch = seatBeltSwitch;
    }

    public String getSeatBeltSwitch() 
    {
        return seatBeltSwitch;
    }
    public void setOverloadOfMultiWayValve(String overloadOfMultiWayValve) 
    {
        this.overloadOfMultiWayValve = overloadOfMultiWayValve;
    }

    public String getOverloadOfMultiWayValve() 
    {
        return overloadOfMultiWayValve;
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
    public void setCentralWideangleRearviewMirror(String centralWideangleRearviewMirror)
    {
        this.centralWideangleRearviewMirror = centralWideangleRearviewMirror;
    }

    public String getCentralWideangleRearviewMirror()
    {
        return centralWideangleRearviewMirror;
    }
    public void setTwoSideRearviewMirrors(String twoSideRearviewMirrors) 
    {
        this.twoSideRearviewMirrors = twoSideRearviewMirrors;
    }

    public String getTwoSideRearviewMirrors() 
    {
        return twoSideRearviewMirrors;
    }
    public void setParkingSensor(String parkingSensor) 
    {
        this.parkingSensor = parkingSensor;
    }

    public String getParkingSensor() 
    {
        return parkingSensor;
    }
    public void setSemiEnclosedSeat(String semiEnclosedSeat) 
    {
        this.semiEnclosedSeat = semiEnclosedSeat;
    }

    public String getSemiEnclosedSeat() 
    {
        return semiEnclosedSeat;
    }
    public void setFullySuspendedSeat(String fullySuspendedSeat) 
    {
        this.fullySuspendedSeat = fullySuspendedSeat;
    }

    public String getFullySuspendedSeat() 
    {
        return fullySuspendedSeat;
    }
    public void setUsbInterface(String usbInterface) 
    {
        this.usbInterface = usbInterface;
    }

    public String getUsbInterface() 
    {
        return usbInterface;
    }
    public void setElectricFan(String electricFan) 
    {
        this.electricFan = electricFan;
    }

    public String getElectricFan() 
    {
        return electricFan;
    }
    public void setTurnTheSteeringWheel(String turnTheSteeringWheel) 
    {
        this.turnTheSteeringWheel = turnTheSteeringWheel;
    }

    public String getTurnTheSteeringWheel() 
    {
        return turnTheSteeringWheel;
    }
    public void setSynchronousSteering(String synchronousSteering) 
    {
        this.synchronousSteering = synchronousSteering;
    }

    public String getSynchronousSteering() 
    {
        return synchronousSteering;
    }
    public void setStandardRoofProtectionFrame(String standardRoofProtectionFrame) 
    {
        this.standardRoofProtectionFrame = standardRoofProtectionFrame;
    }

    public String getStandardRoofProtectionFrame() 
    {
        return standardRoofProtectionFrame;
    }
    public void setNonStandardRoofProtectionFrame(String nonStandardRoofProtectionFrame) 
    {
        this.nonStandardRoofProtectionFrame = nonStandardRoofProtectionFrame;
    }

    public String getNonStandardRoofProtectionFrame() 
    {
        return nonStandardRoofProtectionFrame;
    }
    public void setWindscreen(String windscreen) 
    {
        this.windscreen = windscreen;
    }

    public String getWindscreen() 
    {
        return windscreen;
    }
    public void setFrontHeatresistantWindshield(String frontHeatresistantWindshield)
    {
        this.frontHeatresistantWindshield = frontHeatresistantWindshield;
    }

    public String getFrontHeatresistantWindshield()
    {
        return frontHeatresistantWindshield;
    }
    public void setRearWindshield(String rearWindshield) 
    {
        this.rearWindshield = rearWindshield;
    }

    public String getRearWindshield() 
    {
        return rearWindshield;
    }
    public void setPcEnduranceBoardCeiling(String pcEnduranceBoardCeiling) 
    {
        this.pcEnduranceBoardCeiling = pcEnduranceBoardCeiling;
    }

    public String getPcEnduranceBoardCeiling() 
    {
        return pcEnduranceBoardCeiling;
    }
    public void setSurfaceMountedCab(String surfaceMountedCab) 
    {
        this.surfaceMountedCab = surfaceMountedCab;
    }

    public String getSurfaceMountedCab() 
    {
        return surfaceMountedCab;
    }
    public void setSingleCoolingAirConditioner(String singleCoolingAirConditioner) 
    {
        this.singleCoolingAirConditioner = singleCoolingAirConditioner;
    }

    public String getSingleCoolingAirConditioner() 
    {
        return singleCoolingAirConditioner;
    }
    public void setDualPurposeAirConditioner(String dualPurposeAirConditioner) 
    {
        this.dualPurposeAirConditioner = dualPurposeAirConditioner;
    }

    public String getDualPurposeAirConditioner() 
    {
        return dualPurposeAirConditioner;
    }
    public void setWarmAirBlower(String warmAirBlower) 
    {
        this.warmAirBlower = warmAirBlower;
    }

    public String getWarmAirBlower() 
    {
        return warmAirBlower;
    }
    public void setFullCarLedLights(String fullCarLedLights) 
    {
        this.fullCarLedLights = fullCarLedLights;
    }

    public String getFullCarLedLights() 
    {
        return fullCarLedLights;
    }
    public void setLedRearWorkLight(String ledRearWorkLight) 
    {
        this.ledRearWorkLight = ledRearWorkLight;
    }

    public String getLedRearWorkLight() 
    {
        return ledRearWorkLight;
    }
    public void setBlueLightBehindTheCar(String blueLightBehindTheCar) 
    {
        this.blueLightBehindTheCar = blueLightBehindTheCar;
    }

    public String getBlueLightBehindTheCar() 
    {
        return blueLightBehindTheCar;
    }
    public void setThreeSidedBlueStripLight(String threeSidedBlueStripLight) 
    {
        this.threeSidedBlueStripLight = threeSidedBlueStripLight;
    }

    public String getThreeSidedBlueStripLight() 
    {
        return threeSidedBlueStripLight;
    }
    public void setThreeSidedRedStripLights(String threeSidedRedStripLights) 
    {
        this.threeSidedRedStripLights = threeSidedRedStripLights;
    }

    public String getThreeSidedRedStripLights() 
    {
        return threeSidedRedStripLights;
    }
    public void setLedFlashingWarningLight(String ledFlashingWarningLight) 
    {
        this.ledFlashingWarningLight = ledFlashingWarningLight;
    }

    public String getLedFlashingWarningLight() 
    {
        return ledFlashingWarningLight;
    }
    public void setLedRotatingWarningLight(String ledRotatingWarningLight) 
    {
        this.ledRotatingWarningLight = ledRotatingWarningLight;
    }

    public String getLedRotatingWarningLight() 
    {
        return ledRotatingWarningLight;
    }
    public void setLedRotatingBuzzerWarningLight(String ledRotatingBuzzerWarningLight) 
    {
        this.ledRotatingBuzzerWarningLight = ledRotatingBuzzerWarningLight;
    }

    public String getLedRotatingBuzzerWarningLight() 
    {
        return ledRotatingBuzzerWarningLight;
    }
    public void setMetric(String metric) 
    {
        this.metric = metric;
    }

    public String getMetric() 
    {
        return metric;
    }
    public void setTiltCylinderSleeve(String tiltCylinderSleeve) 
    {
        this.tiltCylinderSleeve = tiltCylinderSleeve;
    }

    public String getTiltCylinderSleeve() 
    {
        return tiltCylinderSleeve;
    }
    public void setSteeringCylinderSleeve(String steeringCylinderSleeve) 
    {
        this.steeringCylinderSleeve = steeringCylinderSleeve;
    }

    public String getSteeringCylinderSleeve() 
    {
        return steeringCylinderSleeve;
    }
    public void setRemaPowerConnector(String remaPowerConnector) 
    {
        this.remaPowerConnector = remaPowerConnector;
    }

    public String getRemaPowerConnector() 
    {
        return remaPowerConnector;
    }
    public void setNationalStandardPowerConnector(String nationalStandardPowerConnector) 
    {
        this.nationalStandardPowerConnector = nationalStandardPowerConnector;
    }

    public String getNationalStandardPowerConnector() 
    {
        return nationalStandardPowerConnector;
    }
    public void setDomesticBattery(String domesticBattery) 
    {
        this.domesticBattery = domesticBattery;
    }

    public String getDomesticBattery() 
    {
        return domesticBattery;
    }
    public void setFicsHeliConnectedCarBasicEdition(String ficsHeliConnectedCarBasicEdition) 
    {
        this.ficsHeliConnectedCarBasicEdition = ficsHeliConnectedCarBasicEdition;
    }

    public String getFicsHeliConnectedCarBasicEdition() 
    {
        return ficsHeliConnectedCarBasicEdition;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("manufacturer", getManufacturer())
            .append("pneumaticTire", getPneumaticTire())
            .append("solidTire", getSolidTire())
            .append("singleTire", getSingleTire())
            .append("dualTire", getDualTire())
            .append("twoPieceValve", getTwoPieceValve())
            .append("threeOrFourPieceValve", getThreeOrFourPieceValve())
            .append("threeMeterLiftingHeight", getThreeMeterLiftingHeight())
            .append("liftingHeightGantry", getLiftingHeightGantry())
            .append("standardSeriesFork", getStandardSeriesFork())
            .append("nonStandardSeriesForks", getNonStandardSeriesForks())
            .append("standardBodyColor", getStandardBodyColor())
            .append("customizedBodyColor", getCustomizedBodyColor())
            .append("completeOpsFunctionality", getCompleteOpsFunctionality())
            .append("turningSpeedLimit", getTurningSpeedLimit())
            .append("overspeed", getOverspeed())
            .append("reverseArmrestWithHorn", getReverseArmrestWithHorn())
            .append("seatBeltSwitch", getSeatBeltSwitch())
            .append("overloadOfMultiWayValve", getOverloadOfMultiWayValve())
            .append("fireExtinguisher", getFireExtinguisher())
            .append("reverseBuzzer", getReverseBuzzer())
            .append("centralWideangleRearviewMirror", getCentralWideangleRearviewMirror())
            .append("twoSideRearviewMirrors", getTwoSideRearviewMirrors())
            .append("parkingSensor", getParkingSensor())
            .append("semiEnclosedSeat", getSemiEnclosedSeat())
            .append("fullySuspendedSeat", getFullySuspendedSeat())
            .append("usbInterface", getUsbInterface())
            .append("electricFan", getElectricFan())
            .append("turnTheSteeringWheel", getTurnTheSteeringWheel())
            .append("synchronousSteering", getSynchronousSteering())
            .append("standardRoofProtectionFrame", getStandardRoofProtectionFrame())
            .append("nonStandardRoofProtectionFrame", getNonStandardRoofProtectionFrame())
            .append("windscreen", getWindscreen())
            .append("frontHeatresistantWindshield", getFrontHeatresistantWindshield())
            .append("rearWindshield", getRearWindshield())
            .append("pcEnduranceBoardCeiling", getPcEnduranceBoardCeiling())
            .append("surfaceMountedCab", getSurfaceMountedCab())
            .append("singleCoolingAirConditioner", getSingleCoolingAirConditioner())
            .append("dualPurposeAirConditioner", getDualPurposeAirConditioner())
            .append("warmAirBlower", getWarmAirBlower())
            .append("fullCarLedLights", getFullCarLedLights())
            .append("ledRearWorkLight", getLedRearWorkLight())
            .append("blueLightBehindTheCar", getBlueLightBehindTheCar())
            .append("threeSidedBlueStripLight", getThreeSidedBlueStripLight())
            .append("threeSidedRedStripLights", getThreeSidedRedStripLights())
            .append("ledFlashingWarningLight", getLedFlashingWarningLight())
            .append("ledRotatingWarningLight", getLedRotatingWarningLight())
            .append("ledRotatingBuzzerWarningLight", getLedRotatingBuzzerWarningLight())
            .append("metric", getMetric())
            .append("tiltCylinderSleeve", getTiltCylinderSleeve())
            .append("steeringCylinderSleeve", getSteeringCylinderSleeve())
            .append("remaPowerConnector", getRemaPowerConnector())
            .append("nationalStandardPowerConnector", getNationalStandardPowerConnector())
            .append("domesticBattery", getDomesticBattery())
            .append("ficsHeliConnectedCarBasicEdition", getFicsHeliConnectedCarBasicEdition())
            .toString();
    }
}
