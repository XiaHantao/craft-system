package com.ruoyi.process.mapper;

import java.util.List;
import com.ruoyi.process.domain.ProcessResearchTable;

/**
 * 工艺调研Mapper接口
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
public interface ProcessResearchTableMapper 
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
     * 删除工艺调研
     * 
     * @param id 工艺调研主键
     * @return 结果
     */
    public int deleteProcessResearchTableById(Long id);

    /**
     * 批量删除工艺调研
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessResearchTableByIds(Long[] ids);
}
