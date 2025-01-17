package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 test
 * 
 * @author ruoyi
 * @date 2025-01-17
 */
public class Test extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long testId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String testOne;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String testTwo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String testThree;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String testFour;

    public void setTestId(Long testId) 
    {
        this.testId = testId;
    }

    public Long getTestId() 
    {
        return testId;
    }
    public void setTestOne(String testOne) 
    {
        this.testOne = testOne;
    }

    public String getTestOne() 
    {
        return testOne;
    }
    public void setTestTwo(String testTwo) 
    {
        this.testTwo = testTwo;
    }

    public String getTestTwo() 
    {
        return testTwo;
    }
    public void setTestThree(String testThree) 
    {
        this.testThree = testThree;
    }

    public String getTestThree() 
    {
        return testThree;
    }
    public void setTestFour(String testFour) 
    {
        this.testFour = testFour;
    }

    public String getTestFour() 
    {
        return testFour;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("testId", getTestId())
            .append("testOne", getTestOne())
            .append("testTwo", getTestTwo())
            .append("testThree", getTestThree())
            .append("testFour", getTestFour())
            .toString();
    }
}
