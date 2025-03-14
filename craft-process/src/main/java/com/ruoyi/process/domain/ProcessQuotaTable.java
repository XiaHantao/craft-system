package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺定额对象 process_quota_table
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
public class ProcessQuotaTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 步骤 */
    @Excel(name = "步骤")
    private Long step;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 文件 */
    @Excel(name = "文件")
    private String filePath;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadPerson;

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
    public void setStep(Long step) 
    {
        this.step = step;
    }

    public Long getStep() 
    {
        return step;
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
    public void setUploadPerson(String uploadPerson) 
    {
        this.uploadPerson = uploadPerson;
    }

    public String getUploadPerson() 
    {
        return uploadPerson;
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
            .append("step", getStep())
            .append("vehicleModel", getVehicleModel())
            .append("filePath", getFilePath())
            .append("uploadPerson", getUploadPerson())
            .append("uploadTime", getUploadTime())
            .toString();
    }
}
