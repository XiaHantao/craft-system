package com.ruoyi.myCode.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新产品生产计划对象 newproducts_plan
 * 
 * @author xj
 * @date 2025-03-03
 */
public class NewproductsPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 新产品计划名称 */
    @Excel(name = "新产品计划名称")
    private String name;

    /** 生产计划 */
    @Excel(name = "生产计划")
    private String planfile;

    /** 技术科确认 */
    @Excel(name = "技术科确认")
    private String technicalcheck;

    /** 技术科备注 */
    @Excel(name = "技术科备注")
    private String technicalremark;

    /** 质量科确认 */
    @Excel(name = "质量科确认")
    private String qualitycheck;

    /** 质量科备注 */
    @Excel(name = "质量科备注")
    private String qualityremark;

    /** 安环科确认 */
    @Excel(name = "安环科确认")
    private String securitycheck;

    /** 安环科备注 */
    @Excel(name = "安环科备注")
    private String securityremark;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /**  */
    @Excel(name = "")
    private String testa;

    /**  */
    @Excel(name = "")
    private String testb;

    /**  */
    @Excel(name = "")
    private String testc;

    /**  */
    @Excel(name = "")
    private String testd;

    /**  */
    @Excel(name = "")
    private String teste;

    /**  */
    @Excel(name = "")
    private Long testf;

    /**  */
    @Excel(name = "")
    private Long testg;

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
    public void setPlanfile(String planfile) 
    {
        this.planfile = planfile;
    }

    public String getPlanfile() 
    {
        return planfile;
    }
    public void setTechnicalcheck(String technicalcheck) 
    {
        this.technicalcheck = technicalcheck;
    }

    public String getTechnicalcheck() 
    {
        return technicalcheck;
    }
    public void setTechnicalremark(String technicalremark) 
    {
        this.technicalremark = technicalremark;
    }

    public String getTechnicalremark() 
    {
        return technicalremark;
    }
    public void setQualitycheck(String qualitycheck) 
    {
        this.qualitycheck = qualitycheck;
    }

    public String getQualitycheck() 
    {
        return qualitycheck;
    }
    public void setQualityremark(String qualityremark) 
    {
        this.qualityremark = qualityremark;
    }

    public String getQualityremark() 
    {
        return qualityremark;
    }
    public void setSecuritycheck(String securitycheck) 
    {
        this.securitycheck = securitycheck;
    }

    public String getSecuritycheck() 
    {
        return securitycheck;
    }
    public void setSecurityremark(String securityremark) 
    {
        this.securityremark = securityremark;
    }

    public String getSecurityremark() 
    {
        return securityremark;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setTesta(String testa) 
    {
        this.testa = testa;
    }

    public String getTesta() 
    {
        return testa;
    }
    public void setTestb(String testb) 
    {
        this.testb = testb;
    }

    public String getTestb() 
    {
        return testb;
    }
    public void setTestc(String testc) 
    {
        this.testc = testc;
    }

    public String getTestc() 
    {
        return testc;
    }
    public void setTestd(String testd) 
    {
        this.testd = testd;
    }

    public String getTestd() 
    {
        return testd;
    }
    public void setTeste(String teste) 
    {
        this.teste = teste;
    }

    public String getTeste() 
    {
        return teste;
    }
    public void setTestf(Long testf) 
    {
        this.testf = testf;
    }

    public Long getTestf() 
    {
        return testf;
    }
    public void setTestg(Long testg) 
    {
        this.testg = testg;
    }

    public Long getTestg() 
    {
        return testg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("planfile", getPlanfile())
            .append("technicalcheck", getTechnicalcheck())
            .append("technicalremark", getTechnicalremark())
            .append("qualitycheck", getQualitycheck())
            .append("qualityremark", getQualityremark())
            .append("securitycheck", getSecuritycheck())
            .append("securityremark", getSecurityremark())
            .append("time", getTime())
            .append("testa", getTesta())
            .append("testb", getTestb())
            .append("testc", getTestc())
            .append("testd", getTestd())
            .append("teste", getTeste())
            .append("testf", getTestf())
            .append("testg", getTestg())
            .toString();
    }
}
