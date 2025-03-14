package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺规程设计对象 process_specification_design_table
 * 
 * @author Kanna Bush
 * @date 2025-02-24
 */
@Data
public class ProcessSpecificationDesignTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 工艺类型（通用工艺/专用工艺） */
    @Excel(name = "工艺类型", readConverterExp = "通用工艺/专用工艺")
    private String processType;

    /** 工艺文件路径 */
    @Excel(name = "工艺文件路径")
    private String filePath;

    /** 关键工艺控制工序 */
    @Excel(name = "关键工艺控制工序")
    private String keyProcessControlProcedures;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadPerson;

    /** 标志位 */
    private Integer tabFlag;

//    public void setId(Long id)
//    {
//        this.id = id;
//    }
//
//    public Long getId()
//    {
//        return id;
//    }
//    public void setVehicleModel(String vehicleModel)
//    {
//        this.vehicleModel = vehicleModel;
//    }
//
//    public String getVehicleModel()
//    {
//        return vehicleModel;
//    }
//    public void setProcessType(String processType)
//    {
//        this.processType = processType;
//    }
//
//    public String getProcessType()
//    {
//        return processType;
//    }
//    public void setFilePath(String filePath)
//    {
//        this.filePath = filePath;
//    }
//
//    public String getFilePath()
//    {
//        return filePath;
//    }
//    public void setKeyProcessControlProcedures(String keyProcessControlProcedures)
//    {
//        this.keyProcessControlProcedures = keyProcessControlProcedures;
//    }
//
//    public String getKeyProcessControlProcedures()
//    {
//        return keyProcessControlProcedures;
//    }
//    public void setUploadTime(Date uploadTime)
//    {
//        this.uploadTime = uploadTime;
//    }
//
//    public Date getUploadTime()
//    {
//        return uploadTime;
//    }
//    public void setUploadPerson(String uploadPerson)
//    {
//        this.uploadPerson = uploadPerson;
//    }
//
//    public String getUploadPerson()
//    {
//        return uploadPerson;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("vehicleModel", getVehicleModel())
//            .append("processType", getProcessType())
//            .append("filePath", getFilePath())
//            .append("keyProcessControlProcedures", getKeyProcessControlProcedures())
//            .append("uploadTime", getUploadTime())
//            .append("uploadPerson", getUploadPerson())
//            .toString();
//    }
}
