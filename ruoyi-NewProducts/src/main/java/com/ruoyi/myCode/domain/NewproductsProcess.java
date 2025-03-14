package com.ruoyi.myCode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新产品试制过程对象 newproducts_process
 * 
 * @author xj
 * @date 2025-02-26
 */
public class NewproductsProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 新产品名称 */
    @Excel(name = "新产品名称")
    private String name;

    /** 试验记录文件 */
    @Excel(name = "试验记录文件")
    private String testprocess;

    /** 总结文件 */
    @Excel(name = "总结文件")
    private String conclude;

    /** 其他文件 */
    @Excel(name = "其他文件")
    private String other;

    /**  */
    @Excel(name = "")
    private String a;

    /**  */
    @Excel(name = "")
    private String b;

    /**  */
    @Excel(name = "")
    private String c;

    /**  */
    @Excel(name = "")
    private String d;

    /**  */
    @Excel(name = "")
    private String e;

    /**  */
    @Excel(name = "")
    private String f;

    /**  */
    @Excel(name = "")
    private Long g;

    /**  */
    @Excel(name = "")
    private Long h;

    /**  */
    @Excel(name = "")
    private Long i;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTestprocess(String testprocess) 
    {
        this.testprocess = testprocess;
    }

    public String getTestprocess() 
    {
        return testprocess;
    }
    public void setConclude(String conclude) 
    {
        this.conclude = conclude;
    }

    public String getConclude() 
    {
        return conclude;
    }
    public void setOther(String other) 
    {
        this.other = other;
    }

    public String getOther() 
    {
        return other;
    }
    public void setA(String a) 
    {
        this.a = a;
    }

    public String getA() 
    {
        return a;
    }
    public void setB(String b) 
    {
        this.b = b;
    }

    public String getB() 
    {
        return b;
    }
    public void setC(String c) 
    {
        this.c = c;
    }

    public String getC() 
    {
        return c;
    }
    public void setD(String d) 
    {
        this.d = d;
    }

    public String getD() 
    {
        return d;
    }
    public void setE(String e) 
    {
        this.e = e;
    }

    public String getE() 
    {
        return e;
    }
    public void setF(String f) 
    {
        this.f = f;
    }

    public String getF() 
    {
        return f;
    }
    public void setG(Long g) 
    {
        this.g = g;
    }

    public Long getG() 
    {
        return g;
    }
    public void setH(Long h) 
    {
        this.h = h;
    }

    public Long getH() 
    {
        return h;
    }
    public void setI(Long i) 
    {
        this.i = i;
    }

    public Long getI() 
    {
        return i;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("testprocess", getTestprocess())
            .append("conclude", getConclude())
            .append("other", getOther())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())

            .append("a", getA())
            .append("b", getB())
            .append("c", getC())
            .append("d", getD())
            .append("e", getE())
            .append("f", getF())
            .append("g", getG())
            .append("h", getH())
            .append("i", getI())
            .toString();
    }
}
