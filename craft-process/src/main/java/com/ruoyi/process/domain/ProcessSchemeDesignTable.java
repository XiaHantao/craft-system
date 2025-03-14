package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺方案设计对象 process_scheme_design_table
 * 
 * @author Kanna Bush
 * @date 2025-02-20
 */
public class ProcessSchemeDesignTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 工艺方案文件路径 */
    @Excel(name = "工艺方案文件路径")
    private String filePath;

    /** 评审状态 */
    @Excel(name = "评审状态")
    private String reviewStatus;

    /** 评审时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评审时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 评审人 */
    @Excel(name = "评审人")
    private String reviewer;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setReviewStatus(String reviewStatus) 
    {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewStatus() 
    {
        return reviewStatus;
    }
    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }
    public void setReviewer(String reviewer) 
    {
        this.reviewer = reviewer;
    }

    public String getReviewer() 
    {
        return reviewer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleModel", getVehicleModel())
            .append("filePath", getFilePath())
            .append("reviewStatus", getReviewStatus())
            .append("reviewTime", getReviewTime())
            .append("reviewer", getReviewer())
            .toString();
    }
}
