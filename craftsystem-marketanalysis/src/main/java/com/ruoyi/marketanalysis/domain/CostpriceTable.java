package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成本售价表对象 costprice_table
 * 
 * @author ruoyi
 * @date 2025-03-05
 */
public class CostpriceTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;


    /** 车型 */
    @Excel(name = "车型")
    private String vehicleType;

    /** 成本（元，不含税） */
    @Excel(name = "成本（元，不含税）")
    private String cost;

    /** 车体（元，不含税） */
    @Excel(name = "车体（元，不含税）")
    private String carBody;

    /** 起重（元，不含税） */
    @Excel(name = "起重（元，不含税）")
    private String lifting;

    /** 其它（元，不含税） */
    @Excel(name = "其它（元，不含税）")
    private String other;

    /** 盘锦定价（元，含税） */
    @Excel(name = "盘锦定价（元，含税）")
    private String panjinPricing;

    /** 收入（元，不含税） */
    @Excel(name = "收入（元，不含税）")
    private String income;

    /** 毛利（元，不含税） */
    @Excel(name = "毛利（元，不含税）")
    private String grossMargin;

    /** 毛利率 */
    @Excel(name = "毛利率")
    private String grossMarginRate;

    /** 股份定价（元，含税） */
    @Excel(name = "股份定价（元，含税）")
    private String stockPricing;

    /** 网点售价（元，含税） */
    @Excel(name = "网点售价（元，含税）")
    private String outletSellingPrice;

    /** 毛利（元，含税） */
    @Excel(name = "毛利（元，含税）")
    private String grossProfitIncludingTax;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;


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
    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }
    public void setCost(String cost) 
    {
        this.cost = cost;
    }

    public String getCost() 
    {
        return cost;
    }
    public void setCarBody(String carBody) 
    {
        this.carBody = carBody;
    }

    public String getCarBody() 
    {
        return carBody;
    }
    public void setLifting(String lifting) 
    {
        this.lifting = lifting;
    }

    public String getLifting() 
    {
        return lifting;
    }
    public void setOther(String other) 
    {
        this.other = other;
    }

    public String getOther() 
    {
        return other;
    }
    public void setPanjinPricing(String panjinPricing) 
    {
        this.panjinPricing = panjinPricing;
    }

    public String getPanjinPricing() 
    {
        return panjinPricing;
    }
    public void setIncome(String income) 
    {
        this.income = income;
    }

    public String getIncome() 
    {
        return income;
    }
    public void setGrossMargin(String grossMargin) 
    {
        this.grossMargin = grossMargin;
    }

    public String getGrossMargin() 
    {
        return grossMargin;
    }
    public void setGrossMarginRate(String grossMarginRate) 
    {
        this.grossMarginRate = grossMarginRate;
    }

    public String getGrossMarginRate() 
    {
        return grossMarginRate;
    }
    public void setStockPricing(String stockPricing) 
    {
        this.stockPricing = stockPricing;
    }

    public String getStockPricing() 
    {
        return stockPricing;
    }
    public void setOutletSellingPrice(String outletSellingPrice) 
    {
        this.outletSellingPrice = outletSellingPrice;
    }

    public String getOutletSellingPrice() 
    {
        return outletSellingPrice;
    }
    public void setGrossProfitIncludingTax(String grossProfitIncludingTax) 
    {
        this.grossProfitIncludingTax = grossProfitIncludingTax;
    }

    public String getGrossProfitIncludingTax() 
    {
        return grossProfitIncludingTax;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("manufacturer", getManufacturer())
            .append("vehicleType", getVehicleType())
            .append("cost", getCost())
            .append("carBody", getCarBody())
            .append("lifting", getLifting())
            .append("other", getOther())
            .append("panjinPricing", getPanjinPricing())
            .append("income", getIncome())
            .append("grossMargin", getGrossMargin())
            .append("grossMarginRate", getGrossMarginRate())
            .append("stockPricing", getStockPricing())
            .append("outletSellingPrice", getOutletSellingPrice())
            .append("grossProfitIncludingTax", getGrossProfitIncludingTax())
            .toString();
    }
}
