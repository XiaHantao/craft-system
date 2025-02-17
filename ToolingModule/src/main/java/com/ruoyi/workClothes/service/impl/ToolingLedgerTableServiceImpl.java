package com.ruoyi.workClothes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.ToolingLedgerTableMapper;
import com.ruoyi.workClothes.domain.ToolingLedgerTable;
import com.ruoyi.workClothes.service.IToolingLedgerTableService;

/**
 * 工装台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
@Service
public class ToolingLedgerTableServiceImpl implements IToolingLedgerTableService 
{
    @Autowired
    private ToolingLedgerTableMapper toolingLedgerTableMapper;

    /**
     * 查询工装台账
     * 
     * @param id 工装台账主键
     * @return 工装台账
     */
    @Override
    public ToolingLedgerTable selectToolingLedgerTableById(Long id)
    {
        return toolingLedgerTableMapper.selectToolingLedgerTableById(id);
    }

    /**
     * 查询工装台账列表
     * 
     * @param toolingLedgerTable 工装台账
     * @return 工装台账
     */
    @Override
    public List<ToolingLedgerTable> selectToolingLedgerTableList(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.selectToolingLedgerTableList(toolingLedgerTable);
    }

    /**
     * 新增工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    @Override
    public int insertToolingLedgerTable(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.insertToolingLedgerTable(toolingLedgerTable);
    }

    /**
     * 修改工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    @Override
    public int updateToolingLedgerTable(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.updateToolingLedgerTable(toolingLedgerTable);
    }

    /**
     * 批量删除工装台账
     * 
     * @param ids 需要删除的工装台账主键
     * @return 结果
     */
    @Override
    public int deleteToolingLedgerTableByIds(Long[] ids)
    {
        return toolingLedgerTableMapper.deleteToolingLedgerTableByIds(ids);
    }

    /**
     * 删除工装台账信息
     * 
     * @param id 工装台账主键
     * @return 结果
     */
    @Override
    public int deleteToolingLedgerTableById(Long id)
    {
        return toolingLedgerTableMapper.deleteToolingLedgerTableById(id);
    }
}
