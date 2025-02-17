package com.ruoyi.workClothes.mapper;

import java.util.List;
import com.ruoyi.workClothes.domain.ToolingLedgerTable;

/**
 * 工装台账Mapper接口
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
public interface ToolingLedgerTableMapper 
{
    /**
     * 查询工装台账
     * 
     * @param id 工装台账主键
     * @return 工装台账
     */
    public ToolingLedgerTable selectToolingLedgerTableById(Long id);

    /**
     * 查询工装台账列表
     * 
     * @param toolingLedgerTable 工装台账
     * @return 工装台账集合
     */
    public List<ToolingLedgerTable> selectToolingLedgerTableList(ToolingLedgerTable toolingLedgerTable);

    /**
     * 新增工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    public int insertToolingLedgerTable(ToolingLedgerTable toolingLedgerTable);

    /**
     * 修改工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    public int updateToolingLedgerTable(ToolingLedgerTable toolingLedgerTable);

    /**
     * 删除工装台账
     * 
     * @param id 工装台账主键
     * @return 结果
     */
    public int deleteToolingLedgerTableById(Long id);

    /**
     * 批量删除工装台账
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteToolingLedgerTableByIds(Long[] ids);
}
