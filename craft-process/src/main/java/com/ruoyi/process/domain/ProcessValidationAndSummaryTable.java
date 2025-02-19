package com.ruoyi.process.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺验证与总结对象 process_validation_and_summary_table
 * 
 * @author Kanna Bush
 * @date 2025-01-20
 */
public class ProcessValidationAndSummaryTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 验证报告名称 */
    @Excel(name = "验证报告名称")
    private String verificationReportName;

    /** 验证报告路径 */
    @Excel(name = "验证报告路径")
    private String verificationReportPath;

    /** 改进清单名称 */
    @Excel(name = "改进清单名称")
    private String improvementChecklistName;

    /** 改进清单路径 */
    @Excel(name = "改进清单路径")
    private String improvementChecklistPath;

    /** 改进报告名称 */
    @Excel(name = "改进报告名称")
    private String improvementReportName;

    /** 改进报告路径 */
    @Excel(name = "改进报告路径")
    private String improvementReportPath;

    /** 改进状态 */
    @Excel(name = "改进状态")
    private String improvementStatus;

    /** 总结报告名称 */
    @Excel(name = "总结报告名称")
    private String summaryReportName;

    /** 总结报告路径 */
    @Excel(name = "总结报告路径")
    private String summaryReportPath;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVerificationReportName(String verificationReportName) 
    {
        this.verificationReportName = verificationReportName;
    }

    public String getVerificationReportName() 
    {
        return verificationReportName;
    }
    public void setVerificationReportPath(String verificationReportPath) 
    {
        this.verificationReportPath = verificationReportPath;
    }

    public String getVerificationReportPath() 
    {
        return verificationReportPath;
    }
    public void setImprovementChecklistName(String improvementChecklistName) 
    {
        this.improvementChecklistName = improvementChecklistName;
    }

    public String getImprovementChecklistName() 
    {
        return improvementChecklistName;
    }
    public void setImprovementChecklistPath(String improvementChecklistPath) 
    {
        this.improvementChecklistPath = improvementChecklistPath;
    }

    public String getImprovementChecklistPath() 
    {
        return improvementChecklistPath;
    }
    public void setImprovementReportName(String improvementReportName) 
    {
        this.improvementReportName = improvementReportName;
    }

    public String getImprovementReportName() 
    {
        return improvementReportName;
    }
    public void setImprovementReportPath(String improvementReportPath) 
    {
        this.improvementReportPath = improvementReportPath;
    }

    public String getImprovementReportPath() 
    {
        return improvementReportPath;
    }
    public void setImprovementStatus(String improvementStatus) 
    {
        this.improvementStatus = improvementStatus;
    }

    public String getImprovementStatus() 
    {
        return improvementStatus;
    }
    public void setSummaryReportName(String summaryReportName) 
    {
        this.summaryReportName = summaryReportName;
    }

    public String getSummaryReportName() 
    {
        return summaryReportName;
    }
    public void setSummaryReportPath(String summaryReportPath) 
    {
        this.summaryReportPath = summaryReportPath;
    }

    public String getSummaryReportPath() 
    {
        return summaryReportPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("verificationReportName", getVerificationReportName())
            .append("verificationReportPath", getVerificationReportPath())
            .append("improvementChecklistName", getImprovementChecklistName())
            .append("improvementChecklistPath", getImprovementChecklistPath())
            .append("improvementReportName", getImprovementReportName())
            .append("improvementReportPath", getImprovementReportPath())
            .append("improvementStatus", getImprovementStatus())
            .append("summaryReportName", getSummaryReportName())
            .append("summaryReportPath", getSummaryReportPath())
            .toString();
    }
}
