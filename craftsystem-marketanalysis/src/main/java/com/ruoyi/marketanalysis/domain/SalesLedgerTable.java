package com.ruoyi.marketanalysis.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售分析对象 sales_ledger_table
 *
 * @author ruoyi
 * @date 2025-03-05
 */
public class SalesLedgerTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 网点  */
    @Excel(name = "网点 ")
    private String branch;

    /** 合同号 */
    @Excel(name = "合同号")
    private String contractNumber;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNumber;

    /** 接单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderAcceptanceDate;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleType;

    /** 数量 */
    @Excel(name = "数量")
    private String number;

    /** 阀片数量 */
    @Excel(name = "阀片数量")
    private String valveplateNumber;

    /** 货叉 */
    @Excel(name = "货叉")
    private String goodsFork;

    /** 门架 */
    @Excel(name = "门架")
    private String gantry;

    /** 空滤 */
    @Excel(name = "空滤")
    private String airFilter;

    /** 属具 */
    @Excel(name = "属具")
    private String accessory;

    /** 轮胎 */
    @Excel(name = "轮胎")
    private String tyre;

    /** 配置 */
    @Excel(name = "配置")
    private String configuration;

    /** 车号 */
    @Excel(name = "车号")
    private String carNumber;

    /** 交货形式 */
    @Excel(name = "交货形式")
    private String deliveryForm;

    /** 交货地点 */
    @Excel(name = "交货地点")
    private String deliveryLocation;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 电话 */
    @Excel(name = "电话")
    private String phoneNumber;

    /** 订单系统交货期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单系统交货期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryTime;

    /** 计划发车日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划发车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planDepartureDate;

    /** 实际发车日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际发车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualDepartureDate;

    /** 系统交货期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "系统交货期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date systemDeliveryTime;

    /** 交货单号 */
    @Excel(name = "交货单号")
    private String deliveryOrderNumber;

    /** 技术准备完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "技术准备完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completionTime;

    /** 兑现率 */
    @Excel(name = "兑现率")
    private String redemptionRate;

    /** id */
    private Long id;

    public void setBranch(String branch)
    {
        this.branch = branch;
    }

    public String getBranch()
    {
        return branch;
    }
    public void setContractNumber(String contractNumber)
    {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber()
    {
        return contractNumber;
    }
    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }
    public void setOrderAcceptanceDate(Date orderAcceptanceDate)
    {
        this.orderAcceptanceDate = orderAcceptanceDate;
    }

    public Date getOrderAcceptanceDate()
    {
        return orderAcceptanceDate;
    }
    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
    public void setValveplateNumber(String valveplateNumber)
    {
        this.valveplateNumber = valveplateNumber;
    }

    public String getValveplateNumber()
    {
        return valveplateNumber;
    }
    public void setGoodsFork(String goodsFork)
    {
        this.goodsFork = goodsFork;
    }

    public String getGoodsFork()
    {
        return goodsFork;
    }
    public void setGantry(String gantry)
    {
        this.gantry = gantry;
    }

    public String getGantry()
    {
        return gantry;
    }
    public void setAirFilter(String airFilter)
    {
        this.airFilter = airFilter;
    }

    public String getAirFilter()
    {
        return airFilter;
    }
    public void setAccessory(String accessory)
    {
        this.accessory = accessory;
    }

    public String getAccessory()
    {
        return accessory;
    }
    public void setTyre(String tyre)
    {
        this.tyre = tyre;
    }

    public String getTyre()
    {
        return tyre;
    }
    public void setConfiguration(String configuration)
    {
        this.configuration = configuration;
    }

    public String getConfiguration()
    {
        return configuration;
    }
    public void setCarNumber(String carNumber)
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber()
    {
        return carNumber;
    }
    public void setDeliveryForm(String deliveryForm)
    {
        this.deliveryForm = deliveryForm;
    }

    public String getDeliveryForm()
    {
        return deliveryForm;
    }
    public void setDeliveryLocation(String deliveryLocation)
    {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDeliveryLocation()
    {
        return deliveryLocation;
    }
    public void setContactPerson(String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson()
    {
        return contactPerson;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setDeliveryTime(Date deliveryTime)
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime()
    {
        return deliveryTime;
    }
    public void setPlanDepartureDate(Date planDepartureDate)
    {
        this.planDepartureDate = planDepartureDate;
    }

    public Date getPlanDepartureDate()
    {
        return planDepartureDate;
    }
    public void setActualDepartureDate(Date actualDepartureDate)
    {
        this.actualDepartureDate = actualDepartureDate;
    }

    public Date getActualDepartureDate()
    {
        return actualDepartureDate;
    }
    public void setSystemDeliveryTime(Date systemDeliveryTime)
    {
        this.systemDeliveryTime = systemDeliveryTime;
    }

    public Date getSystemDeliveryTime()
    {
        return systemDeliveryTime;
    }
    public void setDeliveryOrderNumber(String deliveryOrderNumber)
    {
        this.deliveryOrderNumber = deliveryOrderNumber;
    }

    public String getDeliveryOrderNumber()
    {
        return deliveryOrderNumber;
    }
    public void setCompletionTime(Date completionTime)
    {
        this.completionTime = completionTime;
    }

    public Date getCompletionTime()
    {
        return completionTime;
    }
    public void setRedemptionRate(String redemptionRate)
    {
        this.redemptionRate = redemptionRate;
    }

    public String getRedemptionRate()
    {
        return redemptionRate;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("branch", getBranch())
                .append("contractNumber", getContractNumber())
                .append("orderNumber", getOrderNumber())
                .append("orderAcceptanceDate", getOrderAcceptanceDate())
                .append("vehicleType", getVehicleType())
                .append("number", getNumber())
                .append("valveplateNumber", getValveplateNumber())
                .append("goodsFork", getGoodsFork())
                .append("gantry", getGantry())
                .append("airFilter", getAirFilter())
                .append("accessory", getAccessory())
                .append("tyre", getTyre())
                .append("configuration", getConfiguration())
                .append("carNumber", getCarNumber())
                .append("remark", getRemark())
                .append("deliveryForm", getDeliveryForm())
                .append("deliveryLocation", getDeliveryLocation())
                .append("contactPerson", getContactPerson())
                .append("phoneNumber", getPhoneNumber())
                .append("deliveryTime", getDeliveryTime())
                .append("planDepartureDate", getPlanDepartureDate())
                .append("actualDepartureDate", getActualDepartureDate())
                .append("systemDeliveryTime", getSystemDeliveryTime())
                .append("deliveryOrderNumber", getDeliveryOrderNumber())
                .append("completionTime", getCompletionTime())
                .append("redemptionRate", getRedemptionRate())
                .append("id", getId())
                .toString();
    }
}