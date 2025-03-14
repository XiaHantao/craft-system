package com.ruoyi.process.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺定额字典对象 process_quota_information_dictionary
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
public class ProcessQuotaInformationDictionary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 定额名称 */
    @Excel(name = "定额名称")
    private String quotaName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQuotaName(String quotaName) 
    {
        this.quotaName = quotaName;
    }

    public String getQuotaName() 
    {
        return quotaName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("quotaName", getQuotaName())
            .toString();
    }
}
