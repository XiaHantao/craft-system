package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ProcessValidationAndSummaryTable;

/**
 * 工艺验证与总结Service接口
 * 
 * @author Kanna Bush
 * @date 2025-03-10
 */
public interface IProcessValidationAndSummaryTableService 
{
    /**
     * 查询工艺验证与总结
     * 
     * @param id 工艺验证与总结主键
     * @return 工艺验证与总结
     */
    public ProcessValidationAndSummaryTable selectProcessValidationAndSummaryTableById(Long id);

    /**
     * 查询工艺验证与总结列表
     * 
     * @param processValidationAndSummaryTable 工艺验证与总结
     * @return 工艺验证与总结集合
     */
    public List<ProcessValidationAndSummaryTable> selectProcessValidationAndSummaryTableList(ProcessValidationAndSummaryTable processValidationAndSummaryTable);

    /**
     * 查询历史记录列表
     *
     * @param processValidationAndSummaryTable 工艺验证与总结
     * @return 工艺验证与总结集合
     */
    public List<ProcessValidationAndSummaryTable> selectHistoryList(ProcessValidationAndSummaryTable processValidationAndSummaryTable);

    /**
     * 新增工艺验证与总结
     * 
     * @param processValidationAndSummaryTable 工艺验证与总结
     * @return 结果
     */
    public int insertProcessValidationAndSummaryTable(ProcessValidationAndSummaryTable processValidationAndSummaryTable);

    /**
     * 修改工艺验证与总结
     * 
     * @param processValidationAndSummaryTable 工艺验证与总结
     * @return 结果
     */
    public int updateProcessValidationAndSummaryTable(ProcessValidationAndSummaryTable processValidationAndSummaryTable);

    /**
     * 批量删除工艺验证与总结
     * 
     * @param ids 需要删除的工艺验证与总结主键集合
     * @return 结果
     */
    public int deleteProcessValidationAndSummaryTableByIds(Long[] ids);

    /**
     * 删除工艺验证与总结信息
     * 
     * @param id 工艺验证与总结主键
     * @return 结果
     */
    public int deleteProcessValidationAndSummaryTableById(Long id);

    public ProcessValidationAndSummaryTable selectLatestRecord();

}
