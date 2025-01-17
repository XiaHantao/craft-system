package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TestMapper;
import com.ruoyi.system.domain.Test;
import com.ruoyi.system.service.ITestService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-17
 */
@Service
public class TestServiceImpl implements ITestService 
{
    @Autowired
    private TestMapper testMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param testId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Test selectTestByTestId(Long testId)
    {
        return testMapper.selectTestByTestId(testId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param test 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Test> selectTestList(Test test)
    {
        return testMapper.selectTestList(test);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param test 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTest(Test test)
    {
        return testMapper.insertTest(test);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param test 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTest(Test test)
    {
        return testMapper.updateTest(test);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param testIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTestByTestIds(Long[] testIds)
    {
        return testMapper.deleteTestByTestIds(testIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param testId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTestByTestId(Long testId)
    {
        return testMapper.deleteTestByTestId(testId);
    }
}
