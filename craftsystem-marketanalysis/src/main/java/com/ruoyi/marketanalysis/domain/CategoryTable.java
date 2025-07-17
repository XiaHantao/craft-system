package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车类对象 category_table
 * 
 * @author ruoyi
 * @date 2025-07-08
 */
public class CategoryTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleType;

    /** 车类 */
    @Excel(name = "车类")
    private String vehicleCategoryChn;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }
    public void setVehicleCategoryChn(String vehicleCategoryChn) 
    {
        this.vehicleCategoryChn = vehicleCategoryChn;
    }

    public String getVehicleCategoryChn() 
    {
        return vehicleCategoryChn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleType", getVehicleType())
            .append("vehicleCategoryChn", getVehicleCategoryChn())
            .toString();
    }
}
