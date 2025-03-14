package com.ruoyi.marketanalysis.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车型分类对象 vehicle_category_table
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
public class VehicleCategoryTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 车类 */
    private String vehicleCategory;

    /** 车型 */
    private String vehicleType;

    /** 系列 */
    @Excel(name = "系列")
    private String series;

    /** 吨位 */
    @Excel(name = "吨位")
    private String tonnage;

    /** 发动机型号 */
    @Excel(name = "发动机型号")
    private String engineType;

    /** 发动机电池容量 */
    @Excel(name = "发动机电池容量")
    private String enginebatteryCapacity;

    /** 额定功率 */
    @Excel(name = "额定功率")
    private String powerRating;

    /** 扭矩转速 */
    @Excel(name = "扭矩转速")
    private String torqueRotationalspeed;

    /** 燃油喷射系统型式 */
    @Excel(name = "燃油喷射系统型式")
    private String fuelInjectionSystem;

    /** 排放标准 */
    @Excel(name = "排放标准")
    private String effluentStandard;

    /** 阀片数量 */
    @Excel(name = "阀片数量")
    private String valveplateNumber;

    /** 电瓶容量 */
    @Excel(name = "电瓶容量")
    private String batteryCapacity;

    /** 轮胎规格 */
    @Excel(name = "轮胎规格")
    private String tireSpecification;

    /** 业务支持人员 */
    @Excel(name = "业务支持人员")
    private String businessPersonnel;

    /** 开发类别 */
    @Excel(name = "开发类别")
    private String developmentClass;

    /** 上市时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上市时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseDate;

    /** 备用字段1 */
    private String beiyongOne;

    /** 备用字段2 */
    private String beiyongTwo;

    /** 备用字段3 */
    private String beiyongThree;

    /** 备用字段4 */
    private Date beiyongFour;

    /** 备用字段5 */
    private Date beiyongFive;

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
    public void setSeries(String series) 
    {
        this.series = series;
    }

    public String getSeries() 
    {
        return series;
    }
    public void setTonnage(String tonnage) 
    {
        this.tonnage = tonnage;
    }

    public String getTonnage() 
    {
        return tonnage;
    }
    public void setEngineType(String engineType) 
    {
        this.engineType = engineType;
    }

    public String getEngineType() 
    {
        return engineType;
    }
    public void setEnginebatteryCapacity(String enginebatteryCapacity) 
    {
        this.enginebatteryCapacity = enginebatteryCapacity;
    }

    public String getEnginebatteryCapacity() 
    {
        return enginebatteryCapacity;
    }
    public void setPowerRating(String powerRating) 
    {
        this.powerRating = powerRating;
    }

    public String getPowerRating() 
    {
        return powerRating;
    }
    public void setTorqueRotationalspeed(String torqueRotationalspeed) 
    {
        this.torqueRotationalspeed = torqueRotationalspeed;
    }

    public String getTorqueRotationalspeed() 
    {
        return torqueRotationalspeed;
    }
    public void setFuelInjectionSystem(String fuelInjectionSystem) 
    {
        this.fuelInjectionSystem = fuelInjectionSystem;
    }

    public String getFuelInjectionSystem() 
    {
        return fuelInjectionSystem;
    }
    public void setEffluentStandard(String effluentStandard) 
    {
        this.effluentStandard = effluentStandard;
    }

    public String getEffluentStandard() 
    {
        return effluentStandard;
    }
    public void setValveplateNumber(String valveplateNumber) 
    {
        this.valveplateNumber = valveplateNumber;
    }

    public String getValveplateNumber() 
    {
        return valveplateNumber;
    }
    public void setBatteryCapacity(String batteryCapacity) 
    {
        this.batteryCapacity = batteryCapacity;
    }

    public String getBatteryCapacity() 
    {
        return batteryCapacity;
    }
    public void setTireSpecification(String tireSpecification) 
    {
        this.tireSpecification = tireSpecification;
    }

    public String getTireSpecification() 
    {
        return tireSpecification;
    }
    public void setBusinessPersonnel(String businessPersonnel) 
    {
        this.businessPersonnel = businessPersonnel;
    }

    public String getBusinessPersonnel() 
    {
        return businessPersonnel;
    }
    public void setDevelopmentClass(String developmentClass) 
    {
        this.developmentClass = developmentClass;
    }

    public String getDevelopmentClass() 
    {
        return developmentClass;
    }
    public void setReleaseDate(Date releaseDate) 
    {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() 
    {
        return releaseDate;
    }
    public void setBeiyongOne(String beiyongOne) 
    {
        this.beiyongOne = beiyongOne;
    }

    public String getBeiyongOne() 
    {
        return beiyongOne;
    }
    public void setBeiyongTwo(String beiyongTwo) 
    {
        this.beiyongTwo = beiyongTwo;
    }

    public String getBeiyongTwo() 
    {
        return beiyongTwo;
    }
    public void setBeiyongThree(String beiyongThree) 
    {
        this.beiyongThree = beiyongThree;
    }

    public String getBeiyongThree() 
    {
        return beiyongThree;
    }
    public void setBeiyongFour(Date beiyongFour) 
    {
        this.beiyongFour = beiyongFour;
    }

    public Date getBeiyongFour() 
    {
        return beiyongFour;
    }
    public void setBeiyongFive(Date beiyongFive) 
    {
        this.beiyongFive = beiyongFive;
    }

    public Date getBeiyongFive() 
    {
        return beiyongFive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleCategory", getVehicleCategory())
            .append("vehicleType", getVehicleType())
            .append("series", getSeries())
            .append("tonnage", getTonnage())
            .append("engineType", getEngineType())
            .append("enginebatteryCapacity", getEnginebatteryCapacity())
            .append("powerRating", getPowerRating())
            .append("torqueRotationalspeed", getTorqueRotationalspeed())
            .append("fuelInjectionSystem", getFuelInjectionSystem())
            .append("effluentStandard", getEffluentStandard())
            .append("valveplateNumber", getValveplateNumber())
            .append("batteryCapacity", getBatteryCapacity())
            .append("tireSpecification", getTireSpecification())
            .append("businessPersonnel", getBusinessPersonnel())
            .append("developmentClass", getDevelopmentClass())
            .append("releaseDate", getReleaseDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("beiyongOne", getBeiyongOne())
            .append("beiyongTwo", getBeiyongTwo())
            .append("beiyongThree", getBeiyongThree())
            .append("beiyongFour", getBeiyongFour())
            .append("beiyongFive", getBeiyongFive())
            .toString();
    }
}
