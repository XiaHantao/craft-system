package com.ruoyi.myCode.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新产品生产计划对象 new_product_production_plan
 * 
 * @author xj
 * @date 2025-07-21
 */
public class NewProductProductionPlan extends BaseEntity
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

    /** 计划开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planDate;

    /** 工作人员 */
    @Excel(name = "工作人员")
    private String workers;

    /** 工作场地 */
    @Excel(name = "工作场地")
    private String workLocation;

    /** 使用设备 */
    @Excel(name = "使用设备")
    private String equipment;

    /** 作业内容 */
    @Excel(name = "作业内容")
    private String workContent;

    /** 安排人 */
    @Excel(name = "安排人")
    private String arranger;

    /** 完成结果 */
    @Excel(name = "完成结果")
    private String resultStatus;

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
    public void setPlanDate(Date planDate) 
    {
        this.planDate = planDate;
    }

    public Date getPlanDate() 
    {
        return planDate;
    }
    public void setWorkers(String workers) 
    {
        this.workers = workers;
    }

    public String getWorkers() 
    {
        return workers;
    }
    public void setWorkLocation(String workLocation) 
    {
        this.workLocation = workLocation;
    }

    public String getWorkLocation() 
    {
        return workLocation;
    }
    public void setEquipment(String equipment) 
    {
        this.equipment = equipment;
    }

    public String getEquipment() 
    {
        return equipment;
    }
    public void setWorkContent(String workContent) 
    {
        this.workContent = workContent;
    }

    public String getWorkContent() 
    {
        return workContent;
    }
    public void setArranger(String arranger) 
    {
        this.arranger = arranger;
    }

    public String getArranger() 
    {
        return arranger;
    }
    public void setResultStatus(String resultStatus) 
    {
        this.resultStatus = resultStatus;
    }

    public String getResultStatus() 
    {
        return resultStatus;
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
            .append("planDate", getPlanDate())
            .append("workers", getWorkers())
            .append("workLocation", getWorkLocation())
            .append("equipment", getEquipment())
            .append("workContent", getWorkContent())
            .append("arranger", getArranger())
            .append("resultStatus", getResultStatus())
            .append("extField1", getExtField1())
            .append("extField2", getExtField2())
            .append("extField3", getExtField3())
            .toString();
    }
}
