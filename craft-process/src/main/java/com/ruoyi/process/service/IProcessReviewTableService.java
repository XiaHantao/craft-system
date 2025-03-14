package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ProcessReviewTable;

/**
 * 工艺性审查Service接口
 * 
 * @author Kanna Bush
 * @date 2025-02-19
 */
public interface IProcessReviewTableService 
{
    /**
     * 查询工艺性审查
     * 
     * @param id 工艺性审查主键
     * @return 工艺性审查
     */
    public ProcessReviewTable selectProcessReviewTableById(Long id);

    /**
     * 查询工艺性审查列表
     * 
     * @param processReviewTable 工艺性审查
     * @return 工艺性审查集合
     */
    public List<ProcessReviewTable> selectProcessReviewTableList(ProcessReviewTable processReviewTable);

    /**
     * 新增工艺性审查
     * 
     * @param processReviewTable 工艺性审查
     * @return 结果
     */
    public int insertProcessReviewTable(ProcessReviewTable processReviewTable);

    /**
     * 修改工艺性审查
     * 
     * @param processReviewTable 工艺性审查
     * @return 结果
     */
    public int updateProcessReviewTable(ProcessReviewTable processReviewTable);

    /**
     * 批量删除工艺性审查
     * 
     * @param ids 需要删除的工艺性审查主键集合
     * @return 结果
     */
    public int deleteProcessReviewTableByIds(Long[] ids);

    /**
     * 删除工艺性审查信息
     * 
     * @param id 工艺性审查主键
     * @return 结果
     */
    public int deleteProcessReviewTableById(Long id);
}
