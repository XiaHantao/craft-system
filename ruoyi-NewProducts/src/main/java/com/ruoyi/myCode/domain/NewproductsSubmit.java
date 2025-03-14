package com.ruoyi.myCode.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新产品提交对象 newproducts_submit
 * 
 * @author xj
 * @date 2025-02-26
 */
public class NewproductsSubmit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 新产品名称 */
    @Excel(name = "新产品名称")
    private String name;

    /** 任务单文件 */
    @Excel(name = "任务单文件")
    private String task;

    /** 技术交底文件 */
    @Excel(name = "技术交底文件")
    private String technical;

    /** BOM文件 */
    @Excel(name = "BOM文件")
    private String bom;

    /** 其他文件 */
    @Excel(name = "其他文件")
    private String other;

    /** 提交日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submissionDate;

    /** 核对文件 */
    @Excel(name = "核对文件")
    private String checking;

    /** 核对结果 */
    @Excel(name = "核对结果")
    private String checked;

    /** 核对备注 */
    @Excel(name = "核对备注")
    private String checkremark;

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
    private String g;

    /**  */
    @Excel(name = "")
    private String h;

    /**  */
    @Excel(name = "")
    private Long i;

    /**  */
    @Excel(name = "")
    private Long j;

    /**  */
    @Excel(name = "")
    private Long k;

    /**  */
    @Excel(name = "")
    private Long l;

    /**  */
    @Excel(name = "")
    private Long m;

    /**  */
    @Excel(name = "")
    private Long n;

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
    public void setTask(String task) 
    {
        this.task = task;
    }

    public String getTask() 
    {
        return task;
    }
    public void setTechnical(String technical) 
    {
        this.technical = technical;
    }

    public String getTechnical() 
    {
        return technical;
    }
    public void setBom(String bom) 
    {
        this.bom = bom;
    }

    public String getBom() 
    {
        return bom;
    }
    public void setOther(String other) 
    {
        this.other = other;
    }

    public String getOther() 
    {
        return other;
    }
    public void setSubmissionDate(Date submissionDate) 
    {
        this.submissionDate = submissionDate;
    }

    public Date getSubmissionDate() 
    {
        return submissionDate;
    }
    public void setChecking(String checking) 
    {
        this.checking = checking;
    }

    public String getChecking() 
    {
        return checking;
    }
    public void setChecked(String checked) 
    {
        this.checked = checked;
    }

    public String getChecked() 
    {
        return checked;
    }
    public void setCheckremark(String checkremark) 
    {
        this.checkremark = checkremark;
    }

    public String getCheckremark() 
    {
        return checkremark;
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
    public void setG(String g) 
    {
        this.g = g;
    }

    public String getG() 
    {
        return g;
    }
    public void setH(String h) 
    {
        this.h = h;
    }

    public String getH() 
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
    public void setJ(Long j) 
    {
        this.j = j;
    }

    public Long getJ() 
    {
        return j;
    }
    public void setK(Long k) 
    {
        this.k = k;
    }

    public Long getK() 
    {
        return k;
    }
    public void setL(Long l) 
    {
        this.l = l;
    }

    public Long getL() 
    {
        return l;
    }
    public void setM(Long m) 
    {
        this.m = m;
    }

    public Long getM() 
    {
        return m;
    }
    public void setN(Long n) 
    {
        this.n = n;
    }

    public Long getN() 
    {
        return n;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("task", getTask())
            .append("technical", getTechnical())
            .append("bom", getBom())
            .append("other", getOther())
            .append("submissionDate", getSubmissionDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .append("checking", getChecking())
            .append("checked", getChecked())
            .append("checkremark", getCheckremark())
            .append("a", getA())
            .append("b", getB())
            .append("c", getC())
            .append("d", getD())
            .append("e", getE())
            .append("f", getF())
            .append("g", getG())
            .append("h", getH())
            .append("i", getI())
            .append("j", getJ())
            .append("k", getK())
            .append("l", getL())
            .append("m", getM())
            .append("n", getN())
            .toString();
    }
}
