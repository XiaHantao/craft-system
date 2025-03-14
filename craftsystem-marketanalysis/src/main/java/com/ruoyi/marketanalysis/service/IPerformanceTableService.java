package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.PerformanceTable;

/**
 * 性能表Service接口
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
public interface IPerformanceTableService 
{
    /**
     * 查询性能表
     * 
     * @param id 性能表主键
     * @return 性能表
     */
    public PerformanceTable selectPerformanceTableById(Long id);

    /**
     * 查询性能表列表
     * 
     * @param performanceTable 性能表
     * @return 性能表集合
     */
    public List<PerformanceTable> selectPerformanceTableList(PerformanceTable performanceTable);

    /**
     * 新增性能表
     * 
     * @param performanceTable 性能表
     * @return 结果
     */
    public int insertPerformanceTable(PerformanceTable performanceTable);

    /**
     * 修改性能表
     * 
     * @param performanceTable 性能表
     * @return 结果
     */
    public int updatePerformanceTable(PerformanceTable performanceTable);

    /**
     * 批量删除性能表
     * 
     * @param ids 需要删除的性能表主键集合
     * @return 结果
     */
    public int deletePerformanceTableByIds(Long[] ids);

    /**
     * 删除性能表信息
     * 
     * @param id 性能表主键
     * @return 结果
     */
    public int deletePerformanceTableById(Long id);
}
