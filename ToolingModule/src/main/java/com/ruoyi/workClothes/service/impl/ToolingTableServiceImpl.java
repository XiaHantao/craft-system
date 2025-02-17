package com.ruoyi.workClothes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.ToolingTableMapper;
import com.ruoyi.workClothes.domain.ToolingTable;
import com.ruoyi.workClothes.service.IToolingTableService;

/**
 * 工装详细Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
@Service
public class ToolingTableServiceImpl implements IToolingTableService 
{
    @Autowired
    private ToolingTableMapper toolingTableMapper;

    /**
     * 查询工装详细
     * 
     * @param id 工装详细主键
     * @return 工装详细
     */
    @Override
    public ToolingTable selectToolingTableById(Long id)
    {
        return toolingTableMapper.selectToolingTableById(id);
    }

    /**
     * 查询工装详细列表
     * 
     * @param toolingTable 工装详细
     * @return 工装详细
     */
    @Override
    public List<ToolingTable> selectToolingTableList(ToolingTable toolingTable)
    {
        return toolingTableMapper.selectToolingTableList(toolingTable);
    }

    /**
     * 新增工装详细
     * 
     * @param toolingTable 工装详细
     * @return 结果
     */
    @Override
    public int insertToolingTable(ToolingTable toolingTable)
    {
        return toolingTableMapper.insertToolingTable(toolingTable);
    }

    /**
     * 修改工装详细
     * 
     * @param toolingTable 工装详细
     * @return 结果
     */
    @Override
    public int updateToolingTable(ToolingTable toolingTable)
    {
        return toolingTableMapper.updateToolingTable(toolingTable);
    }

    /**
     * 批量删除工装详细
     * 
     * @param ids 需要删除的工装详细主键
     * @return 结果
     */
    @Override
    public int deleteToolingTableByIds(Long[] ids)
    {
        return toolingTableMapper.deleteToolingTableByIds(ids);
    }

    /**
     * 删除工装详细信息
     * 
     * @param id 工装详细主键
     * @return 结果
     */
    @Override
    public int deleteToolingTableById(Long id)
    {
        return toolingTableMapper.deleteToolingTableById(id);
    }
}
