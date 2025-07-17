package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 多媒体文件对象 multimedia_file_table
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class MultimediaFileTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件类型 */
    private String fileType;

    /** 系列 */
    @Excel(name = "系列")
    private String series;

    /** 吨位 */
    @Excel(name = "吨位")
    private String tonnage;

    /** 文件说明 */
    @Excel(name = "文件说明")
    private String notes;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String file;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getFileType()
    {
        return fileType;
    }
    public void setSeries(String series)
    {
        this.series = series;
    }

    public String getSeries()
    {
        return series;
    }
    public void setTonnage(String tonnage)
    {
        this.tonnage = tonnage;
    }

    public String getTonnage()
    {
        return tonnage;
    }
    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getNotes()
    {
        return notes;
    }
    public void setFile(String file)
    {
        this.file = file;
    }

    public String getFile()
    {
        return file;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("fileName", getFileName())
                .append("fileType", getFileType())
                .append("series", getSeries())
                .append("tonnage", getTonnage())
                .append("notes", getNotes())
                .append("file", getFile())
                .toString();
    }
}