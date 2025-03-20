package com.ruoyi.workClothes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工装类别字典对象 mold_type_table
 * 
 * @author ruoyi
 * @date 2025-03-06
 */
public class MoldTypeTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 序号 */
    @Excel(name = "序号")
    private Long serialNumber;

    /** 类别 */
    @Excel(name = "类别")
    private String moldType;

    /** 类别描述 */
    @Excel(name = "类别描述")
    private String moldDescribe;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSerialNumber(Long serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public Long getSerialNumber() 
    {
        return serialNumber;
    }
    public void setMoldType(String moldType) 
    {
        this.moldType = moldType;
    }

    public String getMoldType() 
    {
        return moldType;
    }
    public void setMoldDescribe(String moldDescribe) 
    {
        this.moldDescribe = moldDescribe;
    }

    public String getMoldDescribe() 
    {
        return moldDescribe;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("serialNumber", getSerialNumber())
            .append("moldType", getMoldType())
            .append("moldDescribe", getMoldDescribe())
            .toString();
    }
}
