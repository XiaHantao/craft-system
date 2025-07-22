package com.ruoyi.myCode.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新产品文件提交对象 new_product_files
 * 
 * @author xj
 * @date 2025-07-21
 */
public class NewProductFiles extends BaseEntity
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

    /** 任务单文件 */
    @Excel(name = "任务单文件")
    private String taskOrderFile;

    /** 技术交底文件 */
    @Excel(name = "技术交底文件")
    private String techDisclosureFile;

    /** BOM文件 */
    @Excel(name = "BOM文件")
    private String bomFile;

    /** 其他文件 */
    @Excel(name = "其他文件")
    private String otherFiles;

    /** 提交备注 */
    @Excel(name = "提交备注")
    private String submitRemarks;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 核对文件 */
    @Excel(name = "核对文件")
    private String checkFile;

    /** 核对状态 */
    @Excel(name = "核对状态")
    private String checkStatus;

    /** 核对备注 */
    @Excel(name = "核对备注")
    private String checkRemarks;

    /** 核对时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "核对时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

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
    public void setTaskOrderFile(String taskOrderFile) 
    {
        this.taskOrderFile = taskOrderFile;
    }

    public String getTaskOrderFile() 
    {
        return taskOrderFile;
    }
    public void setTechDisclosureFile(String techDisclosureFile) 
    {
        this.techDisclosureFile = techDisclosureFile;
    }

    public String getTechDisclosureFile() 
    {
        return techDisclosureFile;
    }
    public void setBomFile(String bomFile) 
    {
        this.bomFile = bomFile;
    }

    public String getBomFile() 
    {
        return bomFile;
    }
    public void setOtherFiles(String otherFiles) 
    {
        this.otherFiles = otherFiles;
    }

    public String getOtherFiles() 
    {
        return otherFiles;
    }
    public void setSubmitRemarks(String submitRemarks) 
    {
        this.submitRemarks = submitRemarks;
    }

    public String getSubmitRemarks() 
    {
        return submitRemarks;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setCheckFile(String checkFile) 
    {
        this.checkFile = checkFile;
    }

    public String getCheckFile() 
    {
        return checkFile;
    }
    public void setCheckStatus(String checkStatus) 
    {
        this.checkStatus = checkStatus;
    }

    public String getCheckStatus() 
    {
        return checkStatus;
    }
    public void setCheckRemarks(String checkRemarks) 
    {
        this.checkRemarks = checkRemarks;
    }

    public String getCheckRemarks() 
    {
        return checkRemarks;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
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
            .append("taskOrderFile", getTaskOrderFile())
            .append("techDisclosureFile", getTechDisclosureFile())
            .append("bomFile", getBomFile())
            .append("otherFiles", getOtherFiles())
            .append("submitRemarks", getSubmitRemarks())
            .append("submitTime", getSubmitTime())
            .append("checkFile", getCheckFile())
            .append("checkStatus", getCheckStatus())
            .append("checkRemarks", getCheckRemarks())
            .append("checkTime", getCheckTime())
            .append("extField1", getExtField1())
            .append("extField2", getExtField2())
            .append("extField3", getExtField3())
            .toString();
    }
}
