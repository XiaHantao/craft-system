package com.ruoyi.workClothes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 维修记录对象 maintenance_record_table
 * 
 * @author ruoyi
 * @date 2025-02-28
 */
public class MaintenanceRecordTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 维修记录 */
    private String maintenanceRecord;

    /** 维修文件 */
    @Excel(name = "维修文件")
    private String maintenanceFile;

    /** 维修工装 */
    private String maintenanceTool;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMaintenanceRecord(String maintenanceRecord) 
    {
        this.maintenanceRecord = maintenanceRecord;
    }

    public String getMaintenanceRecord() 
    {
        return maintenanceRecord;
    }
    public void setMaintenanceFile(String maintenanceFile) 
    {
        this.maintenanceFile = maintenanceFile;
    }

    public String getMaintenanceFile() 
    {
        return maintenanceFile;
    }
    public void setMaintenanceTool(String maintenanceTool) 
    {
        this.maintenanceTool = maintenanceTool;
    }

    public String getMaintenanceTool() 
    {
        return maintenanceTool;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("maintenanceRecord", getMaintenanceRecord())
            .append("maintenanceFile", getMaintenanceFile())
            .append("maintenanceTool", getMaintenanceTool())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
