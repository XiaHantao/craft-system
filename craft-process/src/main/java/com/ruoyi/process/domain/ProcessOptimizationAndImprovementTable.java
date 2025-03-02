package com.ruoyi.process.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺优化与改进对象 process_optimization_and_improvement_table
 * 
 * @author Kanna Bush
 * @date 2025-01-20
 */
public class ProcessOptimizationAndImprovementTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionNumber;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 修改内容 */
    @Excel(name = "修改内容")
    private String updateContent;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updatePerson;

    /** 是否为新版本文件 */
    @Excel(name = "是否为新版本文件")
    private Integer newFlag;

    /** 新版本文件id */
    @Excel(name = "新版本文件id")
    private Long parentId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVersionNumber(String versionNumber) 
    {
        this.versionNumber = versionNumber;
    }

    public String getVersionNumber() 
    {
        return versionNumber;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setUpdateContent(String updateContent) 
    {
        this.updateContent = updateContent;
    }

    public String getUpdateContent() 
    {
        return updateContent;
    }
    public void setUpdatePerson(String updatePerson) 
    {
        this.updatePerson = updatePerson;
    }

    public String getUpdatePerson() 
    {
        return updatePerson;
    }
    public void setNewFlag(Integer newFlag) 
    {
        this.newFlag = newFlag;
    }

    public Integer getNewFlag() 
    {
        return newFlag;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("versionNumber", getVersionNumber())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("updateTime", getUpdateTime())
            .append("updateContent", getUpdateContent())
            .append("updatePerson", getUpdatePerson())
            .append("newFlag", getNewFlag())
            .append("parentId", getParentId())
            .toString();
    }
}
