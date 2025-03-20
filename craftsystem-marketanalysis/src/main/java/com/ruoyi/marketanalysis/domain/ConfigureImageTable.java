package com.ruoyi.marketanalysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 配置图片表对象 configure_image_table
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public class ConfigureImageTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 配置名称 */
    @Excel(name = "配置名称")
    private String fileName;

    /** 说明 */
    @Excel(name = "说明")
    private String notes;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String file;

    /** 图片地址 */
    private String image;

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
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileName", getFileName())
            .append("notes", getNotes())
            .append("file", getFile())
            .append("image", getImage())
            .toString();
    }
}
