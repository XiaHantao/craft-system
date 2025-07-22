package com.ruoyi.myCode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新产品项目文件展示对象 new_product_document_display
 * 
 * @author xj
 * @date 2025-07-21
 */
public class NewProductDocumentDisplay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 任务单展示文件 */
    @Excel(name = "任务单展示文件")
    private String taskOrderDisplay;

    /** 技术交底展示文件 */
    @Excel(name = "技术交底展示文件")
    private String techDisclosureDisplay;

    /** BOM展示文件 */
    @Excel(name = "BOM展示文件")
    private String bomDisplay;

    /** 其他提交展示文件 */
    @Excel(name = "其他提交展示文件")
    private String otherSubmissionDisplay;

    /** 核对展示文件 */
    @Excel(name = "核对展示文件")
    private String verificationDisplay;

    /** BOM质检结果展示文件 */
    @Excel(name = "BOM质检结果展示文件")
    private String bomInspectionDisplay;

    /** 生产问题记录展示文件 */
    @Excel(name = "生产问题记录展示文件")
    private String productionIssueDisplay;

    /** 生产总结展示文件 */
    @Excel(name = "生产总结展示文件")
    private String productionSummaryDisplay;

    /** 强化试验方案展示文件 */
    @Excel(name = "强化试验方案展示文件")
    private String enhancementPlanDisplay;

    /** 强化试验结果展示文件 */
    @Excel(name = "强化试验结果展示文件")
    private String enhancementResultDisplay;

    /** TR4/TR5总结展示文件 */
    @Excel(name = "TR4/TR5总结展示文件")
    private String trSummaryDisplay;

    /** 内部上市报告展示文件 */
    @Excel(name = "内部上市报告展示文件")
    private String launchReportDisplay;

    /** 扩展字段1 */
    @Excel(name = "扩展字段1")
    private String extField1;

    /** 扩展字段2 */
    @Excel(name = "扩展字段2")
    private String extField2;

    /** 扩展字段3 */
    @Excel(name = "扩展字段3")
    private String extField3;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setTaskOrderDisplay(String taskOrderDisplay) 
    {
        this.taskOrderDisplay = taskOrderDisplay;
    }

    public String getTaskOrderDisplay() 
    {
        return taskOrderDisplay;
    }
    public void setTechDisclosureDisplay(String techDisclosureDisplay) 
    {
        this.techDisclosureDisplay = techDisclosureDisplay;
    }

    public String getTechDisclosureDisplay() 
    {
        return techDisclosureDisplay;
    }
    public void setBomDisplay(String bomDisplay) 
    {
        this.bomDisplay = bomDisplay;
    }

    public String getBomDisplay() 
    {
        return bomDisplay;
    }
    public void setOtherSubmissionDisplay(String otherSubmissionDisplay) 
    {
        this.otherSubmissionDisplay = otherSubmissionDisplay;
    }

    public String getOtherSubmissionDisplay() 
    {
        return otherSubmissionDisplay;
    }
    public void setVerificationDisplay(String verificationDisplay) 
    {
        this.verificationDisplay = verificationDisplay;
    }

    public String getVerificationDisplay() 
    {
        return verificationDisplay;
    }
    public void setBomInspectionDisplay(String bomInspectionDisplay) 
    {
        this.bomInspectionDisplay = bomInspectionDisplay;
    }

    public String getBomInspectionDisplay() 
    {
        return bomInspectionDisplay;
    }
    public void setProductionIssueDisplay(String productionIssueDisplay) 
    {
        this.productionIssueDisplay = productionIssueDisplay;
    }

    public String getProductionIssueDisplay() 
    {
        return productionIssueDisplay;
    }
    public void setProductionSummaryDisplay(String productionSummaryDisplay) 
    {
        this.productionSummaryDisplay = productionSummaryDisplay;
    }

    public String getProductionSummaryDisplay() 
    {
        return productionSummaryDisplay;
    }
    public void setEnhancementPlanDisplay(String enhancementPlanDisplay) 
    {
        this.enhancementPlanDisplay = enhancementPlanDisplay;
    }

    public String getEnhancementPlanDisplay() 
    {
        return enhancementPlanDisplay;
    }
    public void setEnhancementResultDisplay(String enhancementResultDisplay) 
    {
        this.enhancementResultDisplay = enhancementResultDisplay;
    }

    public String getEnhancementResultDisplay() 
    {
        return enhancementResultDisplay;
    }
    public void setTrSummaryDisplay(String trSummaryDisplay) 
    {
        this.trSummaryDisplay = trSummaryDisplay;
    }

    public String getTrSummaryDisplay() 
    {
        return trSummaryDisplay;
    }
    public void setLaunchReportDisplay(String launchReportDisplay) 
    {
        this.launchReportDisplay = launchReportDisplay;
    }

    public String getLaunchReportDisplay() 
    {
        return launchReportDisplay;
    }
    public void setExtField1(String extField1) 
    {
        this.extField1 = extField1;
    }

    public String getExtField1() 
    {
        return extField1;
    }
    public void setExtField2(String extField2) 
    {
        this.extField2 = extField2;
    }

    public String getExtField2() 
    {
        return extField2;
    }
    public void setExtField3(String extField3) 
    {
        this.extField3 = extField3;
    }

    public String getExtField3() 
    {
        return extField3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("taskOrderDisplay", getTaskOrderDisplay())
            .append("techDisclosureDisplay", getTechDisclosureDisplay())
            .append("bomDisplay", getBomDisplay())
            .append("otherSubmissionDisplay", getOtherSubmissionDisplay())
            .append("verificationDisplay", getVerificationDisplay())
            .append("bomInspectionDisplay", getBomInspectionDisplay())
            .append("productionIssueDisplay", getProductionIssueDisplay())
            .append("productionSummaryDisplay", getProductionSummaryDisplay())
            .append("enhancementPlanDisplay", getEnhancementPlanDisplay())
            .append("enhancementResultDisplay", getEnhancementResultDisplay())
            .append("trSummaryDisplay", getTrSummaryDisplay())
            .append("launchReportDisplay", getLaunchReportDisplay())
            .append("extField1", getExtField1())
            .append("extField2", getExtField2())
            .append("extField3", getExtField3())
            .toString();
    }
}
