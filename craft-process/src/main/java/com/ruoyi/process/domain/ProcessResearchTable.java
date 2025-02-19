package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺调研对象 process_research_table
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
public class ProcessResearchTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 报告标题 */
    @Excel(name = "报告标题")
    private String reportTitle;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** 调研日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "调研日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date researchDate;

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
    public void setReportTitle(String reportTitle) 
    {
        this.reportTitle = reportTitle;
    }

    public String getReportTitle() 
    {
        return reportTitle;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setKeyword(String keyword) 
    {
        this.keyword = keyword;
    }

    public String getKeyword() 
    {
        return keyword;
    }
    public void setResearchDate(Date researchDate) 
    {
        this.researchDate = researchDate;
    }

    public Date getResearchDate() 
    {
        return researchDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleModel", getVehicleModel())
            .append("reportTitle", getReportTitle())
            .append("filePath", getFilePath())
            .append("keyword", getKeyword())
            .append("researchDate", getResearchDate())
            .toString();
    }
}
