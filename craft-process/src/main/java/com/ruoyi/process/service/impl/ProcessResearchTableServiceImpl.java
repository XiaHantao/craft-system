package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessResearchTableMapper;
import com.ruoyi.process.domain.ProcessResearchTable;
import com.ruoyi.process.service.IProcessResearchTableService;

/**
 * 工艺调研Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
@Service
public class ProcessResearchTableServiceImpl implements IProcessResearchTableService 
{
    @Autowired
    private ProcessResearchTableMapper processResearchTableMapper;

    /**
     * 查询工艺调研
     * 
     * @param id 工艺调研主键
     * @return 工艺调研
     */
    @Override
    public ProcessResearchTable selectProcessResearchTableById(Long id)
    {
        return processResearchTableMapper.selectProcessResearchTableById(id);
    }

    /**
     * 查询工艺调研列表
     * 
     * @param processResearchTable 工艺调研
     * @return 工艺调研
     */
    @Override
    public List<ProcessResearchTable> selectProcessResearchTableList(ProcessResearchTable processResearchTable)
    {
        return processResearchTableMapper.selectProcessResearchTableList(processResearchTable);
    }

    /**
     * 新增工艺调研
     * 
     * @param processResearchTable 工艺调研
     * @return 结果
     */
    @Override
    public int insertProcessResearchTable(ProcessResearchTable processResearchTable)
    {
        return processResearchTableMapper.insertProcessResearchTable(processResearchTable);
    }

    /**
     * 修改工艺调研
     * 
     * @param processResearchTable 工艺调研
     * @return 结果
     */
    @Override
    public int updateProcessResearchTable(ProcessResearchTable processResearchTable)
    {
        return processResearchTableMapper.updateProcessResearchTable(processResearchTable);
    }

    /**
     * 批量删除工艺调研
     * 
     * @param ids 需要删除的工艺调研主键
     * @return 结果
     */
    @Override
    public int deleteProcessResearchTableByIds(Long[] ids)
    {
        return processResearchTableMapper.deleteProcessResearchTableByIds(ids);
    }

    /**
     * 删除工艺调研信息
     * 
     * @param id 工艺调研主键
     * @return 结果
     */
    @Override
    public int deleteProcessResearchTableById(Long id)
    {
        return processResearchTableMapper.deleteProcessResearchTableById(id);
    }
}
