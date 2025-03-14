package com.ruoyi.process.mapper;

import java.util.List;
import com.ruoyi.process.domain.ProcessOptimizationAndImprovementTable;

/**
 * 工艺优化与改进Mapper接口
 * 
 * @author Kanna Bush
 * @date 2025-01-20
 */
public interface ProcessOptimizationAndImprovementTableMapper 
{
    /**
     * 查询工艺优化与改进
     * 
     * @param id 工艺优化与改进主键
     * @return 工艺优化与改进
     */
    public ProcessOptimizationAndImprovementTable selectProcessOptimizationAndImprovementTableById(Long id);

    /**
     * 查询工艺优化与改进列表
     * 
     * @param processOptimizationAndImprovementTable 工艺优化与改进
     * @return 工艺优化与改进集合
     */
    public List<ProcessOptimizationAndImprovementTable> selectProcessOptimizationAndImprovementTableList(ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable);

    /**
     * 新增工艺优化与改进
     * 
     * @param processOptimizationAndImprovementTable 工艺优化与改进
     * @return 结果
     */
    public int insertProcessOptimizationAndImprovementTable(ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable);

    /**
     * 修改工艺优化与改进
     * 
     * @param processOptimizationAndImprovementTable 工艺优化与改进
     * @return 结果
     */
    public int updateProcessOptimizationAndImprovementTable(ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable);

    /**
     * 删除工艺优化与改进
     * 
     * @param id 工艺优化与改进主键
     * @return 结果
     */
    public int deleteProcessOptimizationAndImprovementTableById(Long id);

    /**
     * 批量删除工艺优化与改进
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessOptimizationAndImprovementTableByIds(Long[] ids);
}
