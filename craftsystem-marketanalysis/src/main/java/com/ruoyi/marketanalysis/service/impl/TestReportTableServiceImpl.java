package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.TestReportTableMapper;
import com.ruoyi.marketanalysis.domain.TestReportTable;
import com.ruoyi.marketanalysis.service.ITestReportTableService;

/**
 * 试验报告Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-20
 */
@Service
public class TestReportTableServiceImpl implements ITestReportTableService 
{
    @Autowired
    private TestReportTableMapper testReportTableMapper;

    /**
     * 查询试验报告
     * 
     * @param id 试验报告主键
     * @return 试验报告
     */
    @Override
    public TestReportTable selectTestReportTableById(Long id)
    {
        return testReportTableMapper.selectTestReportTableById(id);
    }

    /**
     * 查询试验报告列表
     * 
     * @param testReportTable 试验报告
     * @return 试验报告
     */
    @Override
    public List<TestReportTable> selectTestReportTableList(TestReportTable testReportTable)
    {
        return testReportTableMapper.selectTestReportTableList(testReportTable);
    }

    /**
     * 新增试验报告
     * 
     * @param testReportTable 试验报告
     * @return 结果
     */
    @Override
    public int insertTestReportTable(TestReportTable testReportTable)
    {
        testReportTable.setCreateTime(DateUtils.getNowDate());
        return testReportTableMapper.insertTestReportTable(testReportTable);
    }

    /**
     * 修改试验报告
     * 
     * @param testReportTable 试验报告
     * @return 结果
     */
    @Override
    public int updateTestReportTable(TestReportTable testReportTable)
    {
        return testReportTableMapper.updateTestReportTable(testReportTable);
    }

    /**
     * 批量删除试验报告
     * 
     * @param ids 需要删除的试验报告主键
     * @return 结果
     */
    @Override
    public int deleteTestReportTableByIds(Long[] ids)
    {
        return testReportTableMapper.deleteTestReportTableByIds(ids);
    }

    /**
     * 删除试验报告信息
     * 
     * @param id 试验报告主键
     * @return 结果
     */
    @Override
    public int deleteTestReportTableById(Long id)
    {
        return testReportTableMapper.deleteTestReportTableById(id);
    }
}
