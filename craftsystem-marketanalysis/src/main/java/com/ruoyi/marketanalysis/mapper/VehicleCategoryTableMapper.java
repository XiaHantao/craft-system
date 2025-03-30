package com.ruoyi.marketanalysis.mapper;

import java.util.List;
import com.ruoyi.marketanalysis.domain.VehicleCategoryTable;

/**
 * 车型分类Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
public interface VehicleCategoryTableMapper 
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
     * 删除车型分类
     * 
     * @param id 车型分类主键
     * @return 结果
     */
    public int deleteVehicleCategoryTableById(Long id);

    /**
     * 批量删除车型分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVehicleCategoryTableByIds(Long[] ids);
}
