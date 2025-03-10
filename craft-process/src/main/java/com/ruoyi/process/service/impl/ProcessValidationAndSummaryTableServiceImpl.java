package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessValidationAndSummaryTableMapper;
import com.ruoyi.process.domain.ProcessValidationAndSummaryTable;
import com.ruoyi.process.service.IProcessValidationAndSummaryTableService;

/**
 * 工艺验证与总结Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-01-20
 */
@Service
public class ProcessValidationAndSummaryTableServiceImpl implements IProcessValidationAndSummaryTableService 
{
    @Autowired
    private ProcessValidationAndSummaryTableMapper processValidationAndSummaryTableMapper;

    /**
     * 查询工艺验证与总结
     * 
     * @param id 工艺验证与总结主键
     * @return 工艺验证与总结
     */
    @Override
    public ProcessValidationAndSummaryTable selectProcessValidationAndSummaryTableById(Long id)
    {
        return processValidationAndSummaryTableMapper.selectProcessValidationAndSummaryTableById(id);
    }

    /**
     * 查询工艺验证与总结列表
     * 
     * @param processValidationAndSummaryTable 工艺验证与总结
     * @return 工艺验证与总结
     */
    @Override
    public List<ProcessValidationAndSummaryTable> selectProcessValidationAndSummaryTableList(ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        return processValidationAndSummaryTableMapper.selectProcessValidationAndSummaryTableList(processValidationAndSummaryTable);
    }

    /**
     * 新增工艺验证与总结
     * 
     * @param processValidationAndSummaryTable 工艺验证与总结
     * @return 结果
     */
    @Override
    public int insertProcessValidationAndSummaryTable(ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        return processValidationAndSummaryTableMapper.insertProcessValidationAndSummaryTable(processValidationAndSummaryTable);
    }

    /**
     * 修改工艺验证与总结
     * 
     * @param processValidationAndSummaryTable 工艺验证与总结
     * @return 结果
     */
    @Override
    public int updateProcessValidationAndSummaryTable(ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        return processValidationAndSummaryTableMapper.updateProcessValidationAndSummaryTable(processValidationAndSummaryTable);
    }

    /**
     * 批量删除工艺验证与总结
     * 
     * @param ids 需要删除的工艺验证与总结主键
     * @return 结果
     */
    @Override
    public int deleteProcessValidationAndSummaryTableByIds(Long[] ids)
    {
        return processValidationAndSummaryTableMapper.deleteProcessValidationAndSummaryTableByIds(ids);
    }

    /**
     * 删除工艺验证与总结信息
     * 
     * @param id 工艺验证与总结主键
     * @return 结果
     */
    @Override
    public int deleteProcessValidationAndSummaryTableById(Long id)
    {
        return processValidationAndSummaryTableMapper.deleteProcessValidationAndSummaryTableById(id);
    }
}
