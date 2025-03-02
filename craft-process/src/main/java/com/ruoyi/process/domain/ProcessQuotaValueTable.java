package com.ruoyi.process.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺定额值对象 process_quota_value_table
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
public class ProcessQuotaValueTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 步骤 */
    @Excel(name = "步骤")
    private Long step;

    /** 定额名称 */
    @Excel(name = "定额名称")
    private String quotaName;

    /** 定额值 */
    @Excel(name = "定额值")
    private String quotaValue;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setStep(Long step) 
    {
        this.step = step;
    }

    public Long getStep() 
    {
        return step;
    }
    public void setQuotaName(String quotaName) 
    {
        this.quotaName = quotaName;
    }

    public String getQuotaName() 
    {
        return quotaName;
    }
    public void setQuotaValue(String quotaValue) 
    {
        this.quotaValue = quotaValue;
    }

    public String getQuotaValue() 
    {
        return quotaValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleModel", getVehicleModel())
            .append("step", getStep())
            .append("quotaName", getQuotaName())
            .append("quotaValue", getQuotaValue())
            .toString();
    }
}
