package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.VehicleCategoryTable;

/**
 * 车型分类Service接口
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
public interface IVehicleCategoryTableService 
{
    /**
     * 查询车型分类
     * 
     * @param id 车型分类主键
     * @return 车型分类
     */
    public VehicleCategoryTable selectVehicleCategoryTableById(Long id);

    /**
     * 查询车型分类列表
     * 
     * @param vehicleCategoryTable 车型分类
     * @return 车型分类集合
     */
    public List<VehicleCategoryTable> selectVehicleCategoryTableList(VehicleCategoryTable vehicleCategoryTable);

    /**
     * 新增车型分类
     * 
     * @param vehicleCategoryTable 车型分类
     * @return 结果
     */
    public int insertVehicleCategoryTable(VehicleCategoryTable vehicleCategoryTable);

    /**
     * 修改车型分类
     * 
     * @param vehicleCategoryTable 车型分类
     * @return 结果
     */
    public int updateVehicleCategoryTable(VehicleCategoryTable vehicleCategoryTable);

    /**
     * 批量删除车型分类
     * 
     * @param ids 需要删除的车型分类主键集合
     * @return 结果
     */
    public int deleteVehicleCategoryTableByIds(Long[] ids);

    /**
     * 删除车型分类信息
     * 
     * @param id 车型分类主键
     * @return 结果
     */
    public int deleteVehicleCategoryTableById(Long id);
}
