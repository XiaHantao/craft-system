package com.ruoyi.workClothes.mapper;

import java.util.List;
import com.ruoyi.workClothes.domain.ToolingTable;

/**
 * 工装详细Mapper接口
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
public interface ToolingTableMapper 
{
    /**
     * 查询工装详细
     * 
     * @param id 工装详细主键
     * @return 工装详细
     */
    public ToolingTable selectToolingTableById(Long id);

    /**
     * 查询工装详细列表
     * 
     * @param toolingTable 工装详细
     * @return 工装详细集合
     */
    public List<ToolingTable> selectToolingTableList(ToolingTable toolingTable);

    /**
     * 新增工装详细
     * 
     * @param toolingTable 工装详细
     * @return 结果
     */
    public int insertToolingTable(ToolingTable toolingTable);

    /**
     * 修改工装详细
     * 
     * @param toolingTable 工装详细
     * @return 结果
     */
    public int updateToolingTable(ToolingTable toolingTable);

    /**
     * 删除工装详细
     * 
     * @param id 工装详细主键
     * @return 结果
     */
    public int deleteToolingTableById(Long id);

    /**
     * 批量删除工装详细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteToolingTableByIds(Long[] ids);
}
