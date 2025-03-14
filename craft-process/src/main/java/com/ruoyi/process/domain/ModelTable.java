package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车型对象 model_table
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
public class ModelTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 系列 */
    @Excel(name = "系列")
    private String series;

    /** 吨位 */
    @Excel(name = "吨位")
    private String tonnage;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 发动机/主要配置 */
    @Excel(name = "发动机/主要配置")
    private String mainConfiguration;

    /** 阀片数量 */
    @Excel(name = "阀片数量")
    private String numberOfValvePlates;

    /** 电瓶容量 */
    @Excel(name = "电瓶容量")
    private String batteryCapacity;

    /** 轮胎规格 */
    @Excel(name = "轮胎规格")
    private String tireSpecifications;

    /** 业务支持人员 */
    @Excel(name = "业务支持人员")
    private String businessSupportPersonnel;

    /** 开发类别 */
    @Excel(name = "开发类别")
    private String developmentCategory;

    /** 上市时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上市时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date listingTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setMainConfiguration(String mainConfiguration) 
    {
        this.mainConfiguration = mainConfiguration;
    }

    public String getMainConfiguration() 
    {
        return mainConfiguration;
    }
    public void setNumberOfValvePlates(String numberOfValvePlates) 
    {
        this.numberOfValvePlates = numberOfValvePlates;
    }

    public String getNumberOfValvePlates() 
    {
        return numberOfValvePlates;
    }
    public void setBatteryCapacity(String batteryCapacity) 
    {
        this.batteryCapacity = batteryCapacity;
    }

    public String getBatteryCapacity() 
    {
        return batteryCapacity;
    }
    public void setTireSpecifications(String tireSpecifications) 
    {
        this.tireSpecifications = tireSpecifications;
    }

    public String getTireSpecifications() 
    {
        return tireSpecifications;
    }
    public void setBusinessSupportPersonnel(String businessSupportPersonnel) 
    {
        this.businessSupportPersonnel = businessSupportPersonnel;
    }

    public String getBusinessSupportPersonnel() 
    {
        return businessSupportPersonnel;
    }
    public void setDevelopmentCategory(String developmentCategory) 
    {
        this.developmentCategory = developmentCategory;
    }

    public String getDevelopmentCategory() 
    {
        return developmentCategory;
    }
    public void setListingTime(Date listingTime) 
    {
        this.listingTime = listingTime;
    }

    public Date getListingTime() 
    {
        return listingTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("series", getSeries())
            .append("tonnage", getTonnage())
            .append("vehicleModel", getVehicleModel())
            .append("mainConfiguration", getMainConfiguration())
            .append("numberOfValvePlates", getNumberOfValvePlates())
            .append("batteryCapacity", getBatteryCapacity())
            .append("tireSpecifications", getTireSpecifications())
            .append("businessSupportPersonnel", getBusinessSupportPersonnel())
            .append("developmentCategory", getDevelopmentCategory())
            .append("listingTime", getListingTime())
            .toString();
    }
}
