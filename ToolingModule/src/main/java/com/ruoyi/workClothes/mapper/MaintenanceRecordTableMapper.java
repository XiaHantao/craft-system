package com.ruoyi.workClothes.mapper;

import java.util.List;
import com.ruoyi.workClothes.domain.MaintenanceRecordTable;
import org.apache.ibatis.annotations.Param;

/**
 * 维修记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-28
 */
public interface MaintenanceRecordTableMapper 
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
     * 删除维修记录
     * 
     * @param id 维修记录主键
     * @return 结果
     */
    public int deleteMaintenanceRecordTableById(Long id);

    /**
     * 批量删除维修记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaintenanceRecordTableByIds(Long[] ids);

    /**
     * 通过父编号查询维修记录
     *
     * @param Ownership 父编号
     * @return 工装详细
     */
    List<MaintenanceRecordTable> selectMaintenanceRecordTableListbynumber(@Param("toolingTable") MaintenanceRecordTable maintenanceRecordTable, @Param("Ownership") String Ownership);
}
