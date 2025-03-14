package com.ruoyi.process.mapper;

import java.util.List;
import com.ruoyi.process.domain.SpecializedProcessEquipmentTable;

/**
 * 专用工艺装备清单Mapper接口
 * 
 * @author Kanna Bush
 * @date 2025-01-19
 */
public interface SpecializedProcessEquipmentTableMapper 
{
    /**
     * 查询专用工艺装备清单
     * 
     * @param id 专用工艺装备清单主键
     * @return 专用工艺装备清单
     */
    public SpecializedProcessEquipmentTable selectSpecializedProcessEquipmentTableById(Long id);

    /**
     * 查询专用工艺装备清单列表
     * 
     * @param specializedProcessEquipmentTable 专用工艺装备清单
     * @return 专用工艺装备清单集合
     */
    public List<SpecializedProcessEquipmentTable> selectSpecializedProcessEquipmentTableList(SpecializedProcessEquipmentTable specializedProcessEquipmentTable);

    /**
     * 新增专用工艺装备清单
     * 
     * @param specializedProcessEquipmentTable 专用工艺装备清单
     * @return 结果
     */
    public int insertSpecializedProcessEquipmentTable(SpecializedProcessEquipmentTable specializedProcessEquipmentTable);

    /**
     * 修改专用工艺装备清单
     * 
     * @param specializedProcessEquipmentTable 专用工艺装备清单
     * @return 结果
     */
    public int updateSpecializedProcessEquipmentTable(SpecializedProcessEquipmentTable specializedProcessEquipmentTable);

    /**
     * 删除专用工艺装备清单
     * 
     * @param id 专用工艺装备清单主键
     * @return 结果
     */
    public int deleteSpecializedProcessEquipmentTableById(Long id);

    /**
     * 批量删除专用工艺装备清单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpecializedProcessEquipmentTableByIds(Long[] ids);
}
