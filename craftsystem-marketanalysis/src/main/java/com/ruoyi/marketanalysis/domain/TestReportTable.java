package com.ruoyi.marketanalysis.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 试验报告对象 test_report_table
 * 
 * @author ruoyi
 * @date 2025-02-20
 */
public class TestReportTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 报告名称 */
    @Excel(name = "报告名称")
    private String reportTitle;

    /** 关联车型 */
    @Excel(name = "关联车型")
    private String vehicleType;

    /** 报告格式 */
    @Excel(name = "报告格式")
    private String reportFormat;

    /** 地址 */
    @Excel(name = "地址")
    private String file;

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
    public void setReportTitle(String reportTitle) 
    {
        this.reportTitle = reportTitle;
    }

    public String getReportTitle() 
    {
        return reportTitle;
    }
    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }
    public void setReportFormat(String reportFormat) 
    {
        this.reportFormat = reportFormat;
    }

    public String getReportFormat() 
    {
        return reportFormat;
    }
    public void setFile(String file) 
    {
        this.file = file;
    }

    public String getFile() 
    {
        return file;
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
            .append("reportTitle", getReportTitle())
            .append("vehicleType", getVehicleType())
            .append("reportFormat", getReportFormat())
            .append("createTime", getCreateTime())
            .append("file", getFile())
            .append("beiyongTwo", getBeiyongTwo())
            .append("beiyongThree", getBeiyongThree())
            .append("beiyongFour", getBeiyongFour())
            .append("beiyongFive", getBeiyongFive())
            .toString();
    }
}
