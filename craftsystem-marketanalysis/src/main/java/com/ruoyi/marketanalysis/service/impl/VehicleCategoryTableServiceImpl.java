package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.VehicleCategoryTableMapper;
import com.ruoyi.marketanalysis.domain.VehicleCategoryTable;
import com.ruoyi.marketanalysis.service.IVehicleCategoryTableService;

/**
 * 车型分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
@Service
public class VehicleCategoryTableServiceImpl implements IVehicleCategoryTableService 
{
    @Autowired
    private VehicleCategoryTableMapper vehicleCategoryTableMapper;

    /**
     * 查询车型分类
     * 
     * @param id 车型分类主键
     * @return 车型分类
     */
    @Override
    public VehicleCategoryTable selectVehicleCategoryTableById(Long id)
    {
        return vehicleCategoryTableMapper.selectVehicleCategoryTableById(id);
    }

    /**
     * 查询车型分类列表
     * 
     * @param vehicleCategoryTable 车型分类
     * @return 车型分类
     */
    @Override
    public List<VehicleCategoryTable> selectVehicleCategoryTableList(VehicleCategoryTable vehicleCategoryTable)
    {
        return vehicleCategoryTableMapper.selectVehicleCategoryTableList(vehicleCategoryTable);
    }

    /**
     * 新增车型分类
     * 
     * @param vehicleCategoryTable 车型分类
     * @return 结果
     */
    @Override
    public int insertVehicleCategoryTable(VehicleCategoryTable vehicleCategoryTable)
    {
        return vehicleCategoryTableMapper.insertVehicleCategoryTable(vehicleCategoryTable);
    }

    /**
     * 修改车型分类
     * 
     * @param vehicleCategoryTable 车型分类
     * @return 结果
     */
    @Override
    public int updateVehicleCategoryTable(VehicleCategoryTable vehicleCategoryTable)
    {
        return vehicleCategoryTableMapper.updateVehicleCategoryTable(vehicleCategoryTable);
    }

    /**
     * 批量删除车型分类
     * 
     * @param ids 需要删除的车型分类主键
     * @return 结果
     */
    @Override
    public int deleteVehicleCategoryTableByIds(Long[] ids)
    {
        return vehicleCategoryTableMapper.deleteVehicleCategoryTableByIds(ids);
    }

    /**
     * 删除车型分类信息
     * 
     * @param id 车型分类主键
     * @return 结果
     */
    @Override
    public int deleteVehicleCategoryTableById(Long id)
    {
        return vehicleCategoryTableMapper.deleteVehicleCategoryTableById(id);
    }
}
