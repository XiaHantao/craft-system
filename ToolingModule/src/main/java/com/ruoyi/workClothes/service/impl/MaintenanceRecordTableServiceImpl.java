package com.ruoyi.workClothes.service.impl;

import java.util.Collections;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.MaintenanceRecordTableMapper;
import com.ruoyi.workClothes.domain.MaintenanceRecordTable;
import com.ruoyi.workClothes.service.IMaintenanceRecordTableService;

/**
 * 维修记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-28
 */
@Service
public class MaintenanceRecordTableServiceImpl implements IMaintenanceRecordTableService 
{
    @Autowired
    private MaintenanceRecordTableMapper maintenanceRecordTableMapper;

    /**
     * 查询维修记录
     * 
     * @param id 维修记录主键
     * @return 维修记录
     */
    @Override
    public MaintenanceRecordTable selectMaintenanceRecordTableById(Long id)
    {
        return maintenanceRecordTableMapper.selectMaintenanceRecordTableById(id);
    }

    /**
     * 查询维修记录列表
     * 
     * @param maintenanceRecordTable 维修记录
     * @return 维修记录
     */
    @Override
    public List<MaintenanceRecordTable> selectMaintenanceRecordTableList(MaintenanceRecordTable maintenanceRecordTable)
    {
        return maintenanceRecordTableMapper.selectMaintenanceRecordTableList(maintenanceRecordTable);
    }

    @Override
    public List<MaintenanceRecordTable> selectMaintenanceRecordTableListbynumber(MaintenanceRecordTable maintenanceRecordTable, String moldOwnership) {
        return maintenanceRecordTableMapper.selectMaintenanceRecordTableListbynumber(maintenanceRecordTable ,moldOwnership);
    }

    /**
     * 新增维修记录
     * 
     * @param maintenanceRecordTable 维修记录
     * @return 结果
     */
    @Override
    public int insertMaintenanceRecordTable(MaintenanceRecordTable maintenanceRecordTable)
    {
        maintenanceRecordTable.setCreateTime(DateUtils.getNowDate());
        String createAccount = SecurityUtils.getUsername();
//        System.out.println("createAccount:"+createAccount);
        maintenanceRecordTable.setCreateBy(createAccount);
        return maintenanceRecordTableMapper.insertMaintenanceRecordTable(maintenanceRecordTable);
    }

    /**
     * 修改维修记录
     * 
     * @param maintenanceRecordTable 维修记录
     * @return 结果
     */
    @Override
    public int updateMaintenanceRecordTable(MaintenanceRecordTable maintenanceRecordTable)
    {
        return maintenanceRecordTableMapper.updateMaintenanceRecordTable(maintenanceRecordTable);
    }

    /**
     * 批量删除维修记录
     * 
     * @param ids 需要删除的维修记录主键
     * @return 结果
     */
    @Override
    public int deleteMaintenanceRecordTableByIds(Long[] ids)
    {
        return maintenanceRecordTableMapper.deleteMaintenanceRecordTableByIds(ids);
    }

    /**
     * 删除维修记录信息
     * 
     * @param id 维修记录主键
     * @return 结果
     */
    @Override
    public int deleteMaintenanceRecordTableById(Long id)
    {
        return maintenanceRecordTableMapper.deleteMaintenanceRecordTableById(id);
    }
}
