package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.SpecializedProcessEquipmentTableMapper;
import com.ruoyi.process.domain.SpecializedProcessEquipmentTable;
import com.ruoyi.process.service.ISpecializedProcessEquipmentTableService;

/**
 * 专用工艺装备清单Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-01-19
 */
@Service
public class SpecializedProcessEquipmentTableServiceImpl implements ISpecializedProcessEquipmentTableService 
{
    @Autowired
    private SpecializedProcessEquipmentTableMapper specializedProcessEquipmentTableMapper;

    /**
     * 查询专用工艺装备清单
     * 
     * @param id 专用工艺装备清单主键
     * @return 专用工艺装备清单
     */
    @Override
    public SpecializedProcessEquipmentTable selectSpecializedProcessEquipmentTableById(Long id)
    {
        return specializedProcessEquipmentTableMapper.selectSpecializedProcessEquipmentTableById(id);
    }

    /**
     * 查询专用工艺装备清单列表
     * 
     * @param specializedProcessEquipmentTable 专用工艺装备清单
     * @return 专用工艺装备清单
     */
    @Override
    public List<SpecializedProcessEquipmentTable> selectSpecializedProcessEquipmentTableList(SpecializedProcessEquipmentTable specializedProcessEquipmentTable)
    {
        return specializedProcessEquipmentTableMapper.selectSpecializedProcessEquipmentTableList(specializedProcessEquipmentTable);
    }

    /**
     * 新增专用工艺装备清单
     * 
     * @param specializedProcessEquipmentTable 专用工艺装备清单
     * @return 结果
     */
    @Override
    public int insertSpecializedProcessEquipmentTable(SpecializedProcessEquipmentTable specializedProcessEquipmentTable)
    {
        return specializedProcessEquipmentTableMapper.insertSpecializedProcessEquipmentTable(specializedProcessEquipmentTable);
    }

    /**
     * 修改专用工艺装备清单
     * 
     * @param specializedProcessEquipmentTable 专用工艺装备清单
     * @return 结果
     */
    @Override
    public int updateSpecializedProcessEquipmentTable(SpecializedProcessEquipmentTable specializedProcessEquipmentTable)
    {
        return specializedProcessEquipmentTableMapper.updateSpecializedProcessEquipmentTable(specializedProcessEquipmentTable);
    }

    /**
     * 批量删除专用工艺装备清单
     * 
     * @param ids 需要删除的专用工艺装备清单主键
     * @return 结果
     */
    @Override
    public int deleteSpecializedProcessEquipmentTableByIds(Long[] ids)
    {
        return specializedProcessEquipmentTableMapper.deleteSpecializedProcessEquipmentTableByIds(ids);
    }

    /**
     * 删除专用工艺装备清单信息
     * 
     * @param id 专用工艺装备清单主键
     * @return 结果
     */
    @Override
    public int deleteSpecializedProcessEquipmentTableById(Long id)
    {
        return specializedProcessEquipmentTableMapper.deleteSpecializedProcessEquipmentTableById(id);
    }
}
