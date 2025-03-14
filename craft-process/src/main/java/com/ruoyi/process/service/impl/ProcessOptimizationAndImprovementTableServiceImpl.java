package com.ruoyi.process.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessOptimizationAndImprovementTableMapper;
import com.ruoyi.process.domain.ProcessOptimizationAndImprovementTable;
import com.ruoyi.process.service.IProcessOptimizationAndImprovementTableService;

/**
 * 工艺优化与改进Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-01-20
 */
@Service
public class ProcessOptimizationAndImprovementTableServiceImpl implements IProcessOptimizationAndImprovementTableService 
{
    @Autowired
    private ProcessOptimizationAndImprovementTableMapper processOptimizationAndImprovementTableMapper;

    /**
     * 查询工艺优化与改进
     * 
     * @param id 工艺优化与改进主键
     * @return 工艺优化与改进
     */
    @Override
    public ProcessOptimizationAndImprovementTable selectProcessOptimizationAndImprovementTableById(Long id)
    {
        return processOptimizationAndImprovementTableMapper.selectProcessOptimizationAndImprovementTableById(id);
    }

    /**
     * 查询工艺优化与改进列表
     * 
     * @param processOptimizationAndImprovementTable 工艺优化与改进
     * @return 工艺优化与改进
     */
    @Override
    public List<ProcessOptimizationAndImprovementTable> selectProcessOptimizationAndImprovementTableList(ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable)
    {
        return processOptimizationAndImprovementTableMapper.selectProcessOptimizationAndImprovementTableList(processOptimizationAndImprovementTable);
    }

    /**
     * 新增工艺优化与改进
     * 
     * @param processOptimizationAndImprovementTable 工艺优化与改进
     * @return 结果
     */
    @Override
    public int insertProcessOptimizationAndImprovementTable(ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable)
    {
        return processOptimizationAndImprovementTableMapper.insertProcessOptimizationAndImprovementTable(processOptimizationAndImprovementTable);
    }

    /**
     * 修改工艺优化与改进
     * 
     * @param processOptimizationAndImprovementTable 工艺优化与改进
     * @return 结果
     */
    @Override
    public int updateProcessOptimizationAndImprovementTable(ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable)
    {
        processOptimizationAndImprovementTable.setUpdateTime(DateUtils.getNowDate());
        return processOptimizationAndImprovementTableMapper.updateProcessOptimizationAndImprovementTable(processOptimizationAndImprovementTable);
    }

    /**
     * 批量删除工艺优化与改进
     * 
     * @param ids 需要删除的工艺优化与改进主键
     * @return 结果
     */
    @Override
    public int deleteProcessOptimizationAndImprovementTableByIds(Long[] ids)
    {
        return processOptimizationAndImprovementTableMapper.deleteProcessOptimizationAndImprovementTableByIds(ids);
    }

    /**
     * 删除工艺优化与改进信息
     * 
     * @param id 工艺优化与改进主键
     * @return 结果
     */
    @Override
    public int deleteProcessOptimizationAndImprovementTableById(Long id)
    {
        return processOptimizationAndImprovementTableMapper.deleteProcessOptimizationAndImprovementTableById(id);
    }
}
