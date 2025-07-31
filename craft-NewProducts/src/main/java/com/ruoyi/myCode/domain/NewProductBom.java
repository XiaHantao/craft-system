package com.ruoyi.myCode.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;
import org.springframework.data.annotation.Transient;


/**
 * 新产品BOM对象 new_product_bom
 * 
 * @author xj
 * @date 2025-07-22
 */
public class NewProductBom extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 项目名称 */
//    @Excel(name = "项目名称")
    private String projectName;

    /** 层 */
    @Excel(name = "层")
    private Long layer;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private String materialCode;

    /** 物料描述 */
    @Excel(name = "物料描述")
    private String materialDescription;

    /** 数量 */
    @Excel(name = "数量")
    private String quantity;

    /** 采购类型 */
    @Excel(name = "采购类型")
    private String purchaseType;

    /** 到货情况 */
    @Excel(name = "到货情况")
    private String arrivalStatus;

    /** 质检情况 */
    @Excel(name = "质检情况")
    private String inspectionStatus;

    /** 质检结果 */
    @Excel(name = "质检结果")
    private String inspectionResult;

    /** 结果文件 */
    @Excel(name = "质检结果文件")
    private String inspectionFile;

    /** 质检结果处理 */
    @Excel(name = "质检结果处理")
    private String inspectionSolve;

    /** 质检处理备注 */
    @Excel(name = "质检处理备注")
    private String inspectionRemarks;

    /** 领用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领用日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receivingDate;

    /** 问题记录 */
    @Excel(name = "问题记录")
    private String issueRecord;

    /** 问题记录筛选标志（非数据库字段） */
    private Boolean issueRecordFilter;

    // Getter 和 Setter
    public Boolean getIssueRecordFilter() {
        return issueRecordFilter;
    }

    public void setIssueRecordFilter(Boolean issueRecordFilter) {
        this.issueRecordFilter = issueRecordFilter;
    }
    /** 扩展字段1 */
    private String extField1;

    /** 扩展字段2 */
    @Excel(name = "领用记录")
    private String extField2;

    /** 扩展字段3 */
    private String extField3;
    // 临时属性
    @Transient
    private Long tempId;

    @Transient
    private Long tempParentId;

    // getter和setter
    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Long getTempParentId() {
        return tempParentId;
    }

    public void setTempParentId(Long tempParentId) {
        this.tempParentId = tempParentId;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setLayer(Long layer) 
    {
        this.layer = layer;
    }

    public Long getLayer() 
    {
        return layer;
    }
    public void setMaterialCode(String materialCode) 
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode() 
    {
        return materialCode;
    }
    public void setMaterialDescription(String materialDescription) 
    {
        this.materialDescription = materialDescription;
    }

    public String getMaterialDescription() 
    {
        return materialDescription;
    }
    public void setQuantity(String quantity) 
    {
        this.quantity = quantity;
    }

    public String getQuantity() 
    {
        return quantity;
    }
    public void setPurchaseType(String purchaseType) 
    {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseType() 
    {
        return purchaseType;
    }
    public void setArrivalStatus(String arrivalStatus) 
    {
        this.arrivalStatus = arrivalStatus;
    }

    public String getArrivalStatus() 
    {
        return arrivalStatus;
    }
    public void setInspectionStatus(String inspectionStatus) 
    {
        this.inspectionStatus = inspectionStatus;
    }

    public String getInspectionStatus()
    {
        return inspectionStatus;
    }
    public void setInspectionResult(String inspectionResult)
    {
        this.inspectionResult = inspectionResult;
    }

    public String getInspectionResult()
    {
        return inspectionResult;
    }
    public void setInspectionFile(String inspectionFile)
    {
        this.inspectionFile = inspectionFile;
    }

    public void setInspectionSolve(String inspectionSolve)
    {
        this.inspectionSolve = inspectionSolve;
    }

    public String getInspectionSolve()
    {
        return inspectionSolve;
    }
    public String getInspectionFile() 
    {
        return inspectionFile;
    }
    public void setInspectionRemarks(String inspectionRemarks) 
    {
        this.inspectionRemarks = inspectionRemarks;
    }

    public String getInspectionRemarks() 
    {
        return inspectionRemarks;
    }
    public void setReceivingDate(Date receivingDate) 
    {
        this.receivingDate = receivingDate;
    }

    public Date getReceivingDate() 
    {
        return receivingDate;
    }
    public void setIssueRecord(String issueRecord) 
    {
        this.issueRecord = issueRecord;
    }

    public String getIssueRecord() 
    {
        return issueRecord;
    }
    public void setExtField1(String extField1) 
    {
        this.extField1 = extField1;
    }

    public String getExtField1() 
    {
        return extField1;
    }
    public void setExtField2(String extField2) 
    {
        this.extField2 = extField2;
    }

    public String getExtField2() 
    {
        return extField2;
    }
    public void setExtField3(String extField3) 
    {
        this.extField3 = extField3;
    }

    public String getExtField3() 
    {
        return extField3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("layer", getLayer())
            .append("materialCode", getMaterialCode())
            .append("materialDescription", getMaterialDescription())
            .append("quantity", getQuantity())
            .append("purchaseType", getPurchaseType())
            .append("arrivalStatus", getArrivalStatus())
            .append("inspectionStatus", getInspectionStatus())
            .append("inspectionResult", getInspectionResult())
            .append("inspectionFile", getInspectionFile())
            .append("inspectionRemarks", getInspectionRemarks())
            .append("receivingDate", getReceivingDate())
            .append("issueRecord", getIssueRecord())
            .append("parentId", getParentId())
            .append("extField1", getExtField1())
            .append("extField2", getExtField2())
            .append("extField3", getExtField3())
            .toString();
    }
}
