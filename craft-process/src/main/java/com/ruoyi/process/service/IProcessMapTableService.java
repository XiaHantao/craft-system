package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ProcessMapTable;

/**
 * 工艺地图Service接口
 * 
 * @author Kanna Bush
 * @date 2025-02-22
 */
public interface IProcessMapTableService 
{
    /**
     * 查询工艺地图
     * 
     * @param id 工艺地图主键
     * @return 工艺地图
     */
    public ProcessMapTable selectProcessMapTableById(Long id);

    /**
     * 查询工艺地图列表
     * 
     * @param processMapTable 工艺地图
     * @return 工艺地图集合
     */
    public List<ProcessMapTable> selectProcessMapTableList(ProcessMapTable processMapTable);

    /**
     * 新增工艺地图
     * 
     * @param processMapTable 工艺地图
     * @return 结果
     */
    public int insertProcessMapTable(ProcessMapTable processMapTable);

    /**
     * 修改工艺地图
     * 
     * @param processMapTable 工艺地图
     * @return 结果
     */
    public int updateProcessMapTable(ProcessMapTable processMapTable);

    /**
     * 批量删除工艺地图
     * 
     * @param ids 需要删除的工艺地图主键集合
     * @return 结果
     */
    public int deleteProcessMapTableByIds(Long[] ids);

    /**
     * 删除工艺地图信息
     * 
     * @param id 工艺地图主键
     * @return 结果
     */
    public int deleteProcessMapTableById(Long id);
}
