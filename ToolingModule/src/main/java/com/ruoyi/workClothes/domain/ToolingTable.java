package com.ruoyi.workClothes.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工装详细对象 tooling_table
 *
 * @author ruoyi
 * @date 2025-02-20
 */
public class ToolingTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 序号 */
    @Excel(name = "序号")
    private Long serialNumber;

    /** 工具编号 */
    @Excel(name = "工具编号")
    private String toolNumber;

    /** 工具名称 */
    @Excel(name = "工具名称")
    private String toolName;

    /** 合计数量 */
    @Excel(name = "合计数量")
    private Long totalQuantity;

    /** 材质 */
    @Excel(name = "材质")
    private String textureOfMaterial;

    /** 下料尺寸 */
    @Excel(name = "下料尺寸")
    private String cuttingSize;

    /** 总重量 */
    @Excel(name = "总重量")
    private BigDecimal totalWeight;

    /** 原材料物料号 */
    @Excel(name = "原材料物料号")
    private String rawMaterialNumber;

    /** 下料（min） */
    @Excel(name = "下料", readConverterExp = "m=in")
    private Long materialCutting;

    /** 金工 */
    @Excel(name = "金工")
    private Long metalworking;

    /** 组焊 */
    @Excel(name = "组焊")
    private Long assemblyWelding;

    /** 涂装 */
    @Excel(name = "涂装")
    private Long painting;

    /** 装配 */
    @Excel(name = "装配")
    private Long assembling;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 车型id */
    @Excel(name = "车型id")
    private Long modelId;

    /** 是否为共用件 */
    @Excel(name = "是否为共用件")
    private String sharedComponents;

    /** 工装图纸 */
    @Excel(name = "工装图纸")
    private String toolingDrawings;

    /** 验证文件 */
    @Excel(name = "验证文件")
    private String verifyFile;

    /** 采购清单 */
    @Excel(name = "采购清单")
    private String procurementList;

    /** 验证结论 */
    @Excel(name = "验证结论")
    private String verificationConclusion;

    /** 更换时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更换时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date changeTime;

    /** 是否为关键部件 */
    @Excel(name = "是否为关键部件")
    private String keyComponents;

    /** 维修记录 */
    @Excel(name = "维修记录")
    private String maintenanceRecord;

    /** 模具所属 */
    @Excel(name = "模具所属")
    private String moldOwnership;

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
    public void setTotalQuantity(Long totalQuantity)
    {
        this.totalQuantity = totalQuantity;
    }

    public Long getTotalQuantity()
    {
        return totalQuantity;
    }
    public void setTextureOfMaterial(String textureOfMaterial)
    {
        this.textureOfMaterial = textureOfMaterial;
    }

    public String getTextureOfMaterial()
    {
        return textureOfMaterial;
    }
    public void setCuttingSize(String cuttingSize)
    {
        this.cuttingSize = cuttingSize;
    }

    public String getCuttingSize()
    {
        return cuttingSize;
    }
    public void setTotalWeight(BigDecimal totalWeight)
    {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalWeight()
    {
        return totalWeight;
    }
    public void setRawMaterialNumber(String rawMaterialNumber)
    {
        this.rawMaterialNumber = rawMaterialNumber;
    }

    public String getRawMaterialNumber()
    {
        return rawMaterialNumber;
    }
    public void setMaterialCutting(Long materialCutting)
    {
        this.materialCutting = materialCutting;
    }

    public Long getMaterialCutting()
    {
        return materialCutting;
    }
    public void setMetalworking(Long metalworking)
    {
        this.metalworking = metalworking;
    }

    public Long getMetalworking()
    {
        return metalworking;
    }
    public void setAssemblyWelding(Long assemblyWelding)
    {
        this.assemblyWelding = assemblyWelding;
    }

    public Long getAssemblyWelding()
    {
        return assemblyWelding;
    }
    public void setPainting(Long painting)
    {
        this.painting = painting;
    }

    public Long getPainting()
    {
        return painting;
    }
    public void setAssembling(Long assembling)
    {
        this.assembling = assembling;
    }

    public Long getAssembling()
    {
        return assembling;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
    }
    public void setModelId(Long modelId)
    {
        this.modelId = modelId;
    }

    public Long getModelId()
    {
        return modelId;
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
    public void setProcurementList(String procurementList)
    {
        this.procurementList = procurementList;
    }

    public String getProcurementList()
    {
        return procurementList;
    }
    public void setVerificationConclusion(String verificationConclusion)
    {
        this.verificationConclusion = verificationConclusion;
    }

    public String getVerificationConclusion()
    {
        return verificationConclusion;
    }
    public void setChangeTime(Date changeTime)
    {
        this.changeTime = changeTime;
    }

    public Date getChangeTime()
    {
        return changeTime;
    }
    public void setKeyComponents(String keyComponents)
    {
        this.keyComponents = keyComponents;
    }

    public String getKeyComponents()
    {
        return keyComponents;
    }
    public void setMaintenanceRecord(String maintenanceRecord)
    {
        this.maintenanceRecord = maintenanceRecord;
    }

    public String getMaintenanceRecord()
    {
        return maintenanceRecord;
    }
    public void setMoldOwnership(String moldOwnership)
    {
        this.moldOwnership = moldOwnership;
    }

    public String getMoldOwnership()
    {
        return moldOwnership;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("serialNumber", getSerialNumber())
                .append("toolNumber", getToolNumber())
                .append("toolName", getToolName())
                .append("totalQuantity", getTotalQuantity())
                .append("textureOfMaterial", getTextureOfMaterial())
                .append("cuttingSize", getCuttingSize())
                .append("totalWeight", getTotalWeight())
                .append("rawMaterialNumber", getRawMaterialNumber())
                .append("materialCutting", getMaterialCutting())
                .append("metalworking", getMetalworking())
                .append("assemblyWelding", getAssemblyWelding())
                .append("painting", getPainting())
                .append("assembling", getAssembling())
                .append("remarks", getRemarks())
                .append("modelId", getModelId())
                .append("sharedComponents", getSharedComponents())
                .append("toolingDrawings", getToolingDrawings())
                .append("verifyFile", getVerifyFile())
                .append("procurementList", getProcurementList())
                .append("verificationConclusion", getVerificationConclusion())
                .append("changeTime", getChangeTime())
                .append("keyComponents", getKeyComponents())
                .append("maintenanceRecord", getMaintenanceRecord())
                .append("moldOwnership", getMoldOwnership())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .toString();
    }
}