package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ProcessResearchTable;

/**
 * 工艺调研Service接口
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
public interface IProcessResearchTableService 
{
    /**
     * 查询工艺调研
     * 
     * @param id 工艺调研主键
     * @return 工艺调研
     */
    public ProcessResearchTable selectProcessResearchTableById(Long id);

    /**
     * 查询工艺调研列表
     * 
     * @param processResearchTable 工艺调研
     * @return 工艺调研集合
     */
    public List<ProcessResearchTable> selectProcessResearchTableList(ProcessResearchTable processResearchTable);

    /**
     * 新增工艺调研
     * 
     * @param processResearchTable 工艺调研
     * @return 结果
     */
    public int insertProcessResearchTable(ProcessResearchTable processResearchTable);

    /**
     * 修改工艺调研
     * 
     * @param processResearchTable 工艺调研
     * @return 结果
     */
    public int updateProcessResearchTable(ProcessResearchTable processResearchTable);

    /**
     * 批量删除工艺调研
     * 
     * @param ids 需要删除的工艺调研主键集合
     * @return 结果
     */
    public int deleteProcessResearchTableByIds(Long[] ids);

    /**
     * 删除工艺调研信息
     * 
     * @param id 工艺调研主键
     * @return 结果
     */
    public int deleteProcessResearchTableById(Long id);
}
