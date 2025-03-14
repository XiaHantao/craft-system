package com.ruoyi.workClothes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 自制件台账对象 self_made_parts_ledger
 * 
 * @author ruoyi
 * @date 2025-03-04
 */
public class SelfMadePartsLedger extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 序号 */
    @Excel(name = "序号")
    private Long serialNumber;

    /** 模具号 */
    @Excel(name = "模具号")
    private String moldNumber;

    /** 模具名称 */
    @Excel(name = "模具名称")
    private String moldName;

    /** 模具类别 */
    @Excel(name = "模具类别")
    private String moldCategory;

    /** 设计要求 */
    @Excel(name = "设计要求")
    private String designRequirement;

    /** 工装图纸 */
    @Excel(name = "工装图纸")
    private String toolingDrawings;

    /** 验证文件 */
    @Excel(name = "验证文件")
    private String verifyFile;

    /** 上传人 */
    @Excel(name = "上传人")
    private String toolUploader;

    /** 验证结论 */
    @Excel(name = "验证结论")
    private String verificationConclusion;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewer;

    /** 状态 */
    @Excel(name = "状态")
    private String verificationState;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSerialNumber(Long serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public Long getSerialNumber() 
    {
        return serialNumber;
    }
    public void setMoldNumber(String moldNumber) 
    {
        this.moldNumber = moldNumber;
    }

    public String getMoldNumber() 
    {
        return moldNumber;
    }
    public void setMoldName(String moldName) 
    {
        this.moldName = moldName;
    }

    public String getMoldName() 
    {
        return moldName;
    }
    public void setMoldCategory(String moldCategory) 
    {
        this.moldCategory = moldCategory;
    }

    public String getMoldCategory() 
    {
        return moldCategory;
    }
    public void setDesignRequirement(String designRequirement) 
    {
        this.designRequirement = designRequirement;
    }

    public String getDesignRequirement() 
    {
        return designRequirement;
    }
    public void setToolingDrawings(String toolingDrawings) 
    {
        this.toolingDrawings = toolingDrawings;
    }

    public String getToolingDrawings() 
    {
        return toolingDrawings;
    }
    public void setVerifyFile(String verifyFile) 
    {
        this.verifyFile = verifyFile;
    }

    public String getVerifyFile() 
    {
        return verifyFile;
    }
    public void setToolUploader(String toolUploader) 
    {
        this.toolUploader = toolUploader;
    }

    public String getToolUploader() 
    {
        return toolUploader;
    }
    public void setVerificationConclusion(String verificationConclusion) 
    {
        this.verificationConclusion = verificationConclusion;
    }

    public String getVerificationConclusion() 
    {
        return verificationConclusion;
    }
    public void setReviewer(String reviewer) 
    {
        this.reviewer = reviewer;
    }

    public String getReviewer() 
    {
        return reviewer;
    }
    public void setVerificationState(String verificationState) 
    {
        this.verificationState = verificationState;
    }

    public String getVerificationState() 
    {
        return verificationState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("serialNumber", getSerialNumber())
            .append("moldNumber", getMoldNumber())
            .append("moldName", getMoldName())
            .append("moldCategory", getMoldCategory())
            .append("designRequirement", getDesignRequirement())
            .append("toolingDrawings", getToolingDrawings())
            .append("verifyFile", getVerifyFile())
            .append("toolUploader", getToolUploader())
            .append("verificationConclusion", getVerificationConclusion())
            .append("reviewer", getReviewer())
            .append("verificationState", getVerificationState())
            .append("remark", getRemark())
            .toString();
    }
}
