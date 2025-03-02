package com.ruoyi.workClothes.service;

import java.util.List;
import com.ruoyi.workClothes.domain.MaintenanceRecordTable;

/**
 * 维修记录Service接口
 * 
 * @author ruoyi
 * @date 2025-02-28
 */
public interface IMaintenanceRecordTableService 
{
    /**
     * 查询维修记录
     * 
     * @param id 维修记录主键
     * @return 维修记录
     */
    public MaintenanceRecordTable selectMaintenanceRecordTableById(Long id);

    /**
     * 查询维修记录列表
     * 
     * @param maintenanceRecordTable 维修记录
     * @return 维修记录集合
     */
    public List<MaintenanceRecordTable> selectMaintenanceRecordTableList(MaintenanceRecordTable maintenanceRecordTable);

    /**
     * 新查询维修记录列表
     *
     * @param maintenanceRecordTable 维修记录
     * @return 维修记录集合
     */
    public List<MaintenanceRecordTable> selectMaintenanceRecordTableListbynumber(MaintenanceRecordTable maintenanceRecordTable , String moldOwnership);

    /**
     * 新增维修记录
     * 
     * @param maintenanceRecordTable 维修记录
     * @return 结果
     */
    public int insertMaintenanceRecordTable(MaintenanceRecordTable maintenanceRecordTable);

    /**
     * 修改维修记录
     * 
     * @param maintenanceRecordTable 维修记录
     * @return 结果
     */
    public int updateMaintenanceRecordTable(MaintenanceRecordTable maintenanceRecordTable);

    /**
     * 批量删除维修记录
     * 
     * @param ids 需要删除的维修记录主键集合
     * @return 结果
     */
    public int deleteMaintenanceRecordTableByIds(Long[] ids);

    /**
     * 删除维修记录信息
     * 
     * @param id 维修记录主键
     * @return 结果
     */
    public int deleteMaintenanceRecordTableById(Long id);
}
