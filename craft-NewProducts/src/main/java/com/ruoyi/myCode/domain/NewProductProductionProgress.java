package com.ruoyi.myCode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新产品生产进度跟踪对象 new_product_production_progress
 * 
 * @author xj
 * @date 2025-07-21
 */
public class NewProductProductionProgress extends BaseEntity
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

    /** 备料 */
    @Excel(name = "备料")
    private String materialPreparation;

    /** 金工 */
    @Excel(name = "金工")
    private String metalworking;

    /** 门架结构 */
    @Excel(name = "门架结构")
    private String mastStructure;

    /** 车架结构 */
    @Excel(name = "车架结构")
    private String frameStructure;

    /** 涂装 */
    @Excel(name = "涂装")
    private String painting;

    /** 门架装配 */
    @Excel(name = "门架装配")
    private String mastAssembly;

    /** 车体装配 */
    @Excel(name = "车体装配")
    private String bodyAssembly;

    /** 试车 */
    @Excel(name = "试车")
    private String testDrive;

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
    public void setMaterialPreparation(String materialPreparation) 
    {
        this.materialPreparation = materialPreparation;
    }

    public String getMaterialPreparation() 
    {
        return materialPreparation;
    }
    public void setMetalworking(String metalworking) 
    {
        this.metalworking = metalworking;
    }

    public String getMetalworking() 
    {
        return metalworking;
    }
    public void setMastStructure(String mastStructure) 
    {
        this.mastStructure = mastStructure;
    }

    public String getMastStructure() 
    {
        return mastStructure;
    }
    public void setFrameStructure(String frameStructure) 
    {
        this.frameStructure = frameStructure;
    }

    public String getFrameStructure() 
    {
        return frameStructure;
    }
    public void setPainting(String painting) 
    {
        this.painting = painting;
    }

    public String getPainting() 
    {
        return painting;
    }
    public void setMastAssembly(String mastAssembly) 
    {
        this.mastAssembly = mastAssembly;
    }

    public String getMastAssembly() 
    {
        return mastAssembly;
    }
    public void setBodyAssembly(String bodyAssembly) 
    {
        this.bodyAssembly = bodyAssembly;
    }

    public String getBodyAssembly() 
    {
        return bodyAssembly;
    }
    public void setTestDrive(String testDrive) 
    {
        this.testDrive = testDrive;
    }

    public String getTestDrive() 
    {
        return testDrive;
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
            .append("materialPreparation", getMaterialPreparation())
            .append("metalworking", getMetalworking())
            .append("mastStructure", getMastStructure())
            .append("frameStructure", getFrameStructure())
            .append("painting", getPainting())
            .append("mastAssembly", getMastAssembly())
            .append("bodyAssembly", getBodyAssembly())
            .append("testDrive", getTestDrive())
            .append("extField1", getExtField1())
            .append("extField2", getExtField2())
            .append("extField3", getExtField3())
            .toString();
    }
}
