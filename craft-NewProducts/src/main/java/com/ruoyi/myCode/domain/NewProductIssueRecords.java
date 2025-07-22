package com.ruoyi.myCode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新产品生产问题记录对象 new_product_issue_records
 * 
 * @author xj
 * @date 2025-07-21
 */
public class NewProductIssueRecords extends BaseEntity
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

    /** 问题记录文件 */
    @Excel(name = "问题记录文件")
    private String issueRecordFile;

    /** 总结文件 */
    @Excel(name = "总结文件")
    private String summaryFile;

    /** 其他附件文件 */
    @Excel(name = "其他附件文件")
    private String attachmentFiles;

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
    public void setIssueRecordFile(String issueRecordFile) 
    {
        this.issueRecordFile = issueRecordFile;
    }

    public String getIssueRecordFile() 
    {
        return issueRecordFile;
    }
    public void setSummaryFile(String summaryFile) 
    {
        this.summaryFile = summaryFile;
    }

    public String getSummaryFile() 
    {
        return summaryFile;
    }
    public void setAttachmentFiles(String attachmentFiles) 
    {
        this.attachmentFiles = attachmentFiles;
    }

    public String getAttachmentFiles() 
    {
        return attachmentFiles;
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
            .append("issueRecordFile", getIssueRecordFile())
            .append("summaryFile", getSummaryFile())
            .append("attachmentFiles", getAttachmentFiles())
            .append("extField1", getExtField1())
            .append("extField2", getExtField2())
            .append("extField3", getExtField3())
            .toString();
    }
}
