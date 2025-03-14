package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺性审查对象 process_review_table
 * 
 * @author Kanna Bush
 * @date 2025-02-19
 */
@Data
public class ProcessReviewTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 物料号 */
    @Excel(name = "物料号")
    private String materialNumber;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** BOM文件 */
    @Excel(name = "BOM文件")
    private String materialFilePath;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 附带图片路径 */
    @Excel(name = "附带图片路径")
    private String imagePath;

    /** 审查状态 */
    @Excel(name = "审查状态")
    private String reviewStatus;

    /** 审查记录文件路径 */
    @Excel(name = "审查记录文件路径")
    private String reviewRecordPath;

    /** 审查人 */
    @Excel(name = "审查人")
    private String reviewer;

    /** 审查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

//    public void setId(Long id)
//    {
//        this.id = id;
//    }
//
//    public Long getId()
//    {
//        return id;
//    }
//    public void setMaterialNumber(String materialNumber)
//    {
//        this.materialNumber = materialNumber;
//    }
//
//    public String getMaterialNumber()
//    {
//        return materialNumber;
//    }
//    public void setMaterialName(String materialName)
//    {
//        this.materialName = materialName;
//    }
//
//    public String getMaterialName()
//    {
//        return materialName;
//    }
//    public void setRemarks(String remarks)
//    {
//        this.remarks = remarks;
//    }
//
//    public String getRemarks()
//    {
//        return remarks;
//    }
//    public void setImagePath(String imagePath)
//    {
//        this.imagePath = imagePath;
//    }
//
//    public String getImagePath()
//    {
//        return imagePath;
//    }
//    public void setReviewRecordPath(String reviewRecordPath)
//    {
//        this.reviewRecordPath = reviewRecordPath;
//    }
//
//    public String getReviewRecordPath()
//    {
//        return reviewRecordPath;
//    }
//    public void setReviewer(String reviewer)
//    {
//        this.reviewer = reviewer;
//    }
//
//    public String getReviewer()
//    {
//        return reviewer;
//    }
//    public void setReviewTime(Date reviewTime)
//    {
//        this.reviewTime = reviewTime;
//    }
//
//    public Date getReviewTime()
//    {
//        return reviewTime;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("materialNumber", getMaterialNumber())
//            .append("materialName", getMaterialName())
//            .append("remarks", getRemarks())
//            .append("imagePath", getImagePath())
//            .append("reviewRecordPath", getReviewRecordPath())
//            .append("reviewer", getReviewer())
//            .append("reviewTime", getReviewTime())
//            .toString();
//    }
}
