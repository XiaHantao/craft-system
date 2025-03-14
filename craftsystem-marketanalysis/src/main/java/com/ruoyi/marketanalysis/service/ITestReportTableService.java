package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.TestReportTable;

/**
 * 试验报告Service接口
 * 
 * @author ruoyi
 * @date 2025-02-20
 */
public interface ITestReportTableService 
{
    /**
     * 查询试验报告
     * 
     * @param id 试验报告主键
     * @return 试验报告
     */
    public TestReportTable selectTestReportTableById(Long id);

    /**
     * 查询试验报告列表
     * 
     * @param testReportTable 试验报告
     * @return 试验报告集合
     */
    public List<TestReportTable> selectTestReportTableList(TestReportTable testReportTable);

    /**
     * 新增试验报告
     * 
     * @param testReportTable 试验报告
     * @return 结果
     */
    public int insertTestReportTable(TestReportTable testReportTable);

    /**
     * 修改试验报告
     * 
     * @param testReportTable 试验报告
     * @return 结果
     */
    public int updateTestReportTable(TestReportTable testReportTable);

    /**
     * 批量删除试验报告
     * 
     * @param ids 需要删除的试验报告主键集合
     * @return 结果
     */
    public int deleteTestReportTableByIds(Long[] ids);

    /**
     * 删除试验报告信息
     * 
     * @param id 试验报告主键
     * @return 结果
     */
    public int deleteTestReportTableById(Long id);
}
