package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessReviewTableMapper;
import com.ruoyi.process.domain.ProcessReviewTable;
import com.ruoyi.process.service.IProcessReviewTableService;

/**
 * 工艺性审查Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-19
 */
@Service
public class ProcessReviewTableServiceImpl implements IProcessReviewTableService 
{
    @Autowired
    private ProcessReviewTableMapper processReviewTableMapper;

    /**
     * 查询工艺性审查
     * 
     * @param id 工艺性审查主键
     * @return 工艺性审查
     */
    @Override
    public ProcessReviewTable selectProcessReviewTableById(Long id)
    {
        return processReviewTableMapper.selectProcessReviewTableById(id);
    }

    /**
     * 查询工艺性审查列表
     * 
     * @param processReviewTable 工艺性审查
     * @return 工艺性审查
     */
    @Override
    public List<ProcessReviewTable> selectProcessReviewTableList(ProcessReviewTable processReviewTable)
    {
        return processReviewTableMapper.selectProcessReviewTableList(processReviewTable);
    }

    /**
     * 新增工艺性审查
     * 
     * @param processReviewTable 工艺性审查
     * @return 结果
     */
    @Override
    public int insertProcessReviewTable(ProcessReviewTable processReviewTable)
    {
        return processReviewTableMapper.insertProcessReviewTable(processReviewTable);
    }

    /**
     * 修改工艺性审查
     * 
     * @param processReviewTable 工艺性审查
     * @return 结果
     */
    @Override
    public int updateProcessReviewTable(ProcessReviewTable processReviewTable)
    {
        return processReviewTableMapper.updateProcessReviewTable(processReviewTable);
    }

    /**
     * 批量删除工艺性审查
     * 
     * @param ids 需要删除的工艺性审查主键
     * @return 结果
     */
    @Override
    public int deleteProcessReviewTableByIds(Long[] ids)
    {
        return processReviewTableMapper.deleteProcessReviewTableByIds(ids);
    }

    /**
     * 删除工艺性审查信息
     * 
     * @param id 工艺性审查主键
     * @return 结果
     */
    @Override
    public int deleteProcessReviewTableById(Long id)
    {
        return processReviewTableMapper.deleteProcessReviewTableById(id);
    }
}
