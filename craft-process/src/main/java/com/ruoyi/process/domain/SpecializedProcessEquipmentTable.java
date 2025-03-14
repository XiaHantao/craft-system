package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 专用工艺装备清单对象 specialized_process_equipment_table
 * 
 * @author Kanna Bush
 * @date 2025-01-19
 */
public class SpecializedProcessEquipmentTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 工装编号 */
    @Excel(name = "工装编号")
    private String toolNumber;

    /** 工装名称 */
    @Excel(name = "工装名称")
    private String toolName;

    /** 工装类型 */
    @Excel(name = "工装类型")
    private String toolType;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 使用班组 */
    @Excel(name = "使用班组")
    private String useTeam;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setToolNumber(String toolNumber) 
    {
        this.toolNumber = toolNumber;
    }

    public String getToolNumber() 
    {
        return toolNumber;
    }
    public void setToolName(String toolName) 
    {
        this.toolName = toolName;
    }

    public String getToolName() 
    {
        return toolName;
    }
    public void setToolType(String toolType) 
    {
        this.toolType = toolType;
    }

    public String getToolType() 
    {
        return toolType;
    }
    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setUseTeam(String useTeam) 
    {
        this.useTeam = useTeam;
    }

    public String getUseTeam() 
    {
        return useTeam;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("toolNumber", getToolNumber())
            .append("toolName", getToolName())
            .append("toolType", getToolType())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("useTeam", getUseTeam())
            .append("remark", getRemark())
            .append("uploadTime", getUploadTime())
            .toString();
    }
}
