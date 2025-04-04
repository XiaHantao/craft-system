package com.ruoyi.workClothes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工装台账对象 tooling_ledger_table
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
public class ToolingLedgerTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 模具所属 */
    private String moldOwnership;

    /** 序号 */
    @Excel(name = "序号")
    private Long serialNumber;

    /** 模具用途 */
    @Excel(name = "模具用途")
    private String moldUsage;

    /** 模具名称 */
    @Excel(name = "模具名称")
    private String moldName;

    /** 模具号 */
    @Excel(name = "模具号")
    private String moldNumber;

    /** 种类 */
    @Excel(name = "种类")
    private String moldType;

    /** 投入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date investTime;

    /** 模具位置 */
    @Excel(name = "模具位置")
    private String moldPosition;

    /** 数量 */
    @Excel(name = "数量")
    private String quantityOfTooling;

    /** 模具状态 */
    @Excel(name = "模具状态")
    private String moldStatus;

    /** 组装产品 */
    @Excel(name = "组装产品")
    private String assemblingProducts;

    /** 是否为共用件 */
    private String sharedComponents;

    /** 工装图纸 */
    @Excel(name = "工装图纸")
    private String toolingDrawings;

    /** 工艺文件名 */
    @Excel(name = "工艺文件名")
    private String processDocumentsName;

    /** 工艺文件路径 */
    @Excel(name = "工艺文件路径")
    private String processDocuments;

    /** 物料清单名 */
    @Excel(name = "物料清单名")
    private String mbomName;

    /** 物料清单路径 */
    @Excel(name = "物料清单路径")
    private String mbomFile;

    /** 验证文件 */
    @Excel(name = "验证文件")
    private String verifyFile;

    /** 验证结论 */
    @Excel(name = "验证结论")
    private String verificationConclusion;

    /** 保养提醒日期 */
    private Date reminderDate;

    /** 保养类别 */
    private String maintenanceCategory;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMoldOwnership(String moldOwnership) 
    {
        this.moldOwnership = moldOwnership;
    }

    public String getMoldOwnership() 
    {
        return moldOwnership;
    }
    public void setSerialNumber(Long serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public Long getSerialNumber() 
    {
        return serialNumber;
    }
    public void setMoldUsage(String moldUsage) 
    {
        this.moldUsage = moldUsage;
    }

    public String getMoldUsage() 
    {
        return moldUsage;
    }
    public void setMoldName(String moldName) 
    {
        this.moldName = moldName;
    }

    public String getMoldName() 
    {
        return moldName;
    }
    public void setMoldNumber(String moldNumber) 
    {
        this.moldNumber = moldNumber;
    }

    public String getMoldNumber() 
    {
        return moldNumber;
    }
    public void setMoldType(String moldType) 
    {
        this.moldType = moldType;
    }

    public String getMoldType() 
    {
        return moldType;
    }
    public void setInvestTime(Date investTime) 
    {
        this.investTime = investTime;
    }

    public Date getInvestTime() 
    {
        return investTime;
    }
    public void setMoldPosition(String moldPosition) 
    {
        this.moldPosition = moldPosition;
    }

    public String getMoldPosition() 
    {
        return moldPosition;
    }
    public void setQuantityOfTooling(String quantityOfTooling) 
    {
        this.quantityOfTooling = quantityOfTooling;
    }

    public String getQuantityOfTooling() 
    {
        return quantityOfTooling;
    }
    public void setMoldStatus(String moldStatus) 
    {
        this.moldStatus = moldStatus;
    }

    public String getMoldStatus() 
    {
        return moldStatus;
    }
    public void setAssemblingProducts(String assemblingProducts) 
    {
        this.assemblingProducts = assemblingProducts;
    }

    public String getAssemblingProducts() 
    {
        return assemblingProducts;
    }
    public void setSharedComponents(String sharedComponents) 
    {
        this.sharedComponents = sharedComponents;
    }

    public String getSharedComponents() 
    {
        return sharedComponents;
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

    public void setVerificationConclusion(String verificationConclusion) 
    {
        this.verificationConclusion = verificationConclusion;
    }

    public void setProcessDocumentsName(String processDocumentsName)
    {
        this.processDocumentsName = processDocumentsName;
    }

    public String getProcessDocumentsName()
    {
        return processDocumentsName;
    }
    public void setProcessDocuments(String processDocuments)
    {
        this.processDocuments = processDocuments;
    }

    public String getProcessDocuments()
    {
        return processDocuments;
    }
    public void setMbomName(String mbomName)
    {
        this.mbomName = mbomName;
    }

    public String getMbomName()
    {
        return mbomName;
    }
    public void setMbomFile(String mbomFile)
    {
        this.mbomFile = mbomFile;
    }

    public String getMbomFile()
    {
        return mbomFile;
    }

    public String getVerificationConclusion() 
    {
        return verificationConclusion;
    }
    public void setReminderDate(Date reminderDate) 
    {
        this.reminderDate = reminderDate;
    }

    public Date getReminderDate() 
    {
        return reminderDate;
    }
    public void setMaintenanceCategory(String maintenanceCategory) 
    {
        this.maintenanceCategory = maintenanceCategory;
    }

    public String getMaintenanceCategory() 
    {
        return maintenanceCategory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("moldOwnership", getMoldOwnership())
            .append("serialNumber", getSerialNumber())
            .append("moldUsage", getMoldUsage())
            .append("moldName", getMoldName())
            .append("moldNumber", getMoldNumber())
            .append("moldType", getMoldType())
            .append("investTime", getInvestTime())
            .append("moldPosition", getMoldPosition())
            .append("quantityOfTooling", getQuantityOfTooling())
            .append("moldStatus", getMoldStatus())
            .append("assemblingProducts", getAssemblingProducts())
                .append("processDocumentsName", getProcessDocumentsName())
                .append("processDocuments", getProcessDocuments())
                .append("mbomName", getMbomName())
                .append("mbomFile", getMbomFile())
            .append("remark", getRemark())
            .append("sharedComponents", getSharedComponents())
            .append("toolingDrawings", getToolingDrawings())
            .append("verifyFile", getVerifyFile())
            .append("verificationConclusion", getVerificationConclusion())
            .append("reminderDate", getReminderDate())
            .append("maintenanceCategory", getMaintenanceCategory())
            .toString();
    }
}
