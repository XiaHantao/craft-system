package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Test;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-01-17
 */
public interface ITestService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param testId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Test selectTestByTestId(Long testId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param test 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Test> selectTestList(Test test);

    /**
     * 新增【请填写功能名称】
     * 
     * @param test 【请填写功能名称】
     * @return 结果
     */
    public int insertTest(Test test);

    /**
     * 修改【请填写功能名称】
     * 
     * @param test 【请填写功能名称】
     * @return 结果
     */
    public int updateTest(Test test);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param testIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTestByTestIds(Long[] testIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param testId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTestByTestId(Long testId);
}
