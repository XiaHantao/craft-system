package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺地图对象 process_map_table
 * 
 * @author Kanna Bush
 * @date 2025-02-22
 */
public class ProcessMapTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 工艺地图名称 */
    @Excel(name = "工艺地图名称")
    private String craftMapName;

    /** 工艺地图路径 */
    @Excel(name = "工艺地图路径")
    private String craftMapPath;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadPerson;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCraftMapName(String craftMapName) 
    {
        this.craftMapName = craftMapName;
    }

    public String getCraftMapName() 
    {
        return craftMapName;
    }
    public void setCraftMapPath(String craftMapPath) 
    {
        this.craftMapPath = craftMapPath;
    }

    public String getCraftMapPath() 
    {
        return craftMapPath;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setUploadPerson(String uploadPerson) 
    {
        this.uploadPerson = uploadPerson;
    }

    public String getUploadPerson() 
    {
        return uploadPerson;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("craftMapName", getCraftMapName())
            .append("craftMapPath", getCraftMapPath())
            .append("uploadTime", getUploadTime())
            .append("uploadPerson", getUploadPerson())
            .toString();
    }
}
