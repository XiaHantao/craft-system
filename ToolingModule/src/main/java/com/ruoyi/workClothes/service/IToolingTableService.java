package com.ruoyi.workClothes.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.workClothes.domain.ToolingTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工装详细Service接口
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
public interface IToolingTableService 
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
     * 导入工装详细
     *
     * @param file 工装表格
     * @return 结果
     */
    public int loadImport(MultipartFile file);

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
     * 批量删除工装详细
     * 
     * @param ids 需要删除的工装详细主键集合
     * @return 结果
     */
    public int deleteToolingTableByIds(Long[] ids);

    /**
     * 删除工装详细信息
     * 
     * @param id 工装详细主键
     * @return 结果
     */
    public int deleteToolingTableById(Long id);

    /**
     * 根据父编号查询工装详细列表
     *
     * @param toolingTable 工装详细
     * @return 工装详细集合
     */
    public List<ToolingTable> selectToolingTableListbymoldOwnership(ToolingTable toolingTable , String moldOwnership);

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

    int updateWorkClothesfile(Map<String, Object> fileform);
}
