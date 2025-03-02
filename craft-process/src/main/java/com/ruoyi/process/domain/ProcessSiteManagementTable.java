package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺现场管理对象 process_site_management_table
 * 
 * @author Kanna Bush
 * @date 2025-02-21
 */
public class ProcessSiteManagementTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 检查报告名称 */
    @Excel(name = "检查报告名称")
    private String inspectionReportName;

    /** 检查报告路径 */
    @Excel(name = "检查报告路径")
    private String inspectionReportPath;

    /** 整改要求 */
    @Excel(name = "整改要求")
    private String rectificationRequirements;

    /** 检查报告上传人 */
    @Excel(name = "检查报告上传人")
    private String inspectionReportUploadPerson;

    /** 检查报告上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查报告上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectionReportUploadTime;

    /** 整改报告名称 */
    @Excel(name = "整改报告名称")
    private String rectificationReportName;

    /** 整改报告路径 */
    @Excel(name = "整改报告路径")
    private String rectificationReportPath;

    /** 整改报告上传人 */
    @Excel(name = "整改报告上传人")
    private String rectificationReportUploadPerson;

    /** 整改报告上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "整改报告上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rectificationReportUploadTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setInspectionReportName(String inspectionReportName) 
    {
        this.inspectionReportName = inspectionReportName;
    }

    public String getInspectionReportName() 
    {
        return inspectionReportName;
    }
    public void setInspectionReportPath(String inspectionReportPath) 
    {
        this.inspectionReportPath = inspectionReportPath;
    }

    public String getInspectionReportPath() 
    {
        return inspectionReportPath;
    }
    public void setRectificationRequirements(String rectificationRequirements) 
    {
        this.rectificationRequirements = rectificationRequirements;
    }

    public String getRectificationRequirements() 
    {
        return rectificationRequirements;
    }
    public void setInspectionReportUploadPerson(String inspectionReportUploadPerson) 
    {
        this.inspectionReportUploadPerson = inspectionReportUploadPerson;
    }

    public String getInspectionReportUploadPerson() 
    {
        return inspectionReportUploadPerson;
    }
    public void setInspectionReportUploadTime(Date inspectionReportUploadTime) 
    {
        this.inspectionReportUploadTime = inspectionReportUploadTime;
    }

    public Date getInspectionReportUploadTime() 
    {
        return inspectionReportUploadTime;
    }
    public void setRectificationReportName(String rectificationReportName) 
    {
        this.rectificationReportName = rectificationReportName;
    }

    public String getRectificationReportName() 
    {
        return rectificationReportName;
    }
    public void setRectificationReportPath(String rectificationReportPath) 
    {
        this.rectificationReportPath = rectificationReportPath;
    }

    public String getRectificationReportPath() 
    {
        return rectificationReportPath;
    }
    public void setRectificationReportUploadPerson(String rectificationReportUploadPerson) 
    {
        this.rectificationReportUploadPerson = rectificationReportUploadPerson;
    }

    public String getRectificationReportUploadPerson() 
    {
        return rectificationReportUploadPerson;
    }
    public void setRectificationReportUploadTime(Date rectificationReportUploadTime) 
    {
        this.rectificationReportUploadTime = rectificationReportUploadTime;
    }

    public Date getRectificationReportUploadTime() 
    {
        return rectificationReportUploadTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("inspectionReportName", getInspectionReportName())
            .append("inspectionReportPath", getInspectionReportPath())
            .append("rectificationRequirements", getRectificationRequirements())
            .append("inspectionReportUploadPerson", getInspectionReportUploadPerson())
            .append("inspectionReportUploadTime", getInspectionReportUploadTime())
            .append("rectificationReportName", getRectificationReportName())
            .append("rectificationReportPath", getRectificationReportPath())
            .append("rectificationReportUploadPerson", getRectificationReportUploadPerson())
            .append("rectificationReportUploadTime", getRectificationReportUploadTime())
            .toString();
    }
}
