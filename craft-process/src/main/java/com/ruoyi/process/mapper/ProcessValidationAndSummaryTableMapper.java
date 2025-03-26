package com.ruoyi.process.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.process.domain.ProcessValidationAndSummaryTable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 工艺验证与总结Mapper接口
 * 
 * @author Kanna Bush
 * @date 2025-03-10
 */
public interface ProcessValidationAndSummaryTableMapper 
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
     * 删除工艺验证与总结
     * 
     * @param id 工艺验证与总结主键
     * @return 结果
     */
    public int deleteProcessValidationAndSummaryTableById(Long id);

    /**
     * 批量删除工艺验证与总结
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessValidationAndSummaryTableByIds(Long[] ids);

    //查找最新一条记录
    public ProcessValidationAndSummaryTable selectLatestRecord();

    @Select("SELECT * FROM ${tableName} ORDER BY id DESC LIMIT 1")
    public Map<String, Object> selectLatestRecord02(@Param("tableName") String tableName);

}
