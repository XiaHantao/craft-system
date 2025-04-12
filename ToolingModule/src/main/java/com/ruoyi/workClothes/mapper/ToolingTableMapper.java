package com.ruoyi.workClothes.mapper;

import java.util.List;
import com.ruoyi.workClothes.domain.ToolingTable;
import org.apache.ibatis.annotations.Param;


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
     * 批量导入工装详细
     *
     * @param toolingTableList 工装详细
     * @return 结果
     */
    int insertBatch(List<ToolingTable> toolingTableList);

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

    /**
     * 通过父编号查询工装详细
     *
     * @param Ownership 父编号
     * @return 工装详细
     */
    public List<ToolingTable> selectToolingTableListbymoldOwnership(@Param("toolingTable") ToolingTable toolingTable, @Param("Ownership") String Ownership);

    /**
     * 查询共用工装详细列表
     *
     * @param toolingTable 工装详细
     * @return 工装详细集合
     */
    public List<ToolingTable> selectshareToolingTableList(ToolingTable toolingTable);

    /**
     * 查询距离当前日期一个月内的工装详细列表
     *
     * @param toolingTable 工装详细
     * @return 工装详细集合
     */
    public List<ToolingTable> selectUpcomingChangeTimeToolingTableList(ToolingTable toolingTable);


}
