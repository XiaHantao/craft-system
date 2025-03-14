package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessSchemeDesignTableMapper;
import com.ruoyi.process.domain.ProcessSchemeDesignTable;
import com.ruoyi.process.service.IProcessSchemeDesignTableService;

/**
 * 工艺方案设计Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-20
 */
@Service
public class ProcessSchemeDesignTableServiceImpl implements IProcessSchemeDesignTableService 
{
    @Autowired
    private ProcessSchemeDesignTableMapper processSchemeDesignTableMapper;

    /**
     * 查询工艺方案设计
     * 
     * @param id 工艺方案设计主键
     * @return 工艺方案设计
     */
    @Override
    public ProcessSchemeDesignTable selectProcessSchemeDesignTableById(Long id)
    {
        return processSchemeDesignTableMapper.selectProcessSchemeDesignTableById(id);
    }

    /**
     * 查询工艺方案设计列表
     * 
     * @param processSchemeDesignTable 工艺方案设计
     * @return 工艺方案设计
     */
    @Override
    public List<ProcessSchemeDesignTable> selectProcessSchemeDesignTableList(ProcessSchemeDesignTable processSchemeDesignTable)
    {
        return processSchemeDesignTableMapper.selectProcessSchemeDesignTableList(processSchemeDesignTable);
    }

    /**
     * 新增工艺方案设计
     * 
     * @param processSchemeDesignTable 工艺方案设计
     * @return 结果
     */
    @Override
    public int insertProcessSchemeDesignTable(ProcessSchemeDesignTable processSchemeDesignTable)
    {
        return processSchemeDesignTableMapper.insertProcessSchemeDesignTable(processSchemeDesignTable);
    }

    /**
     * 修改工艺方案设计
     * 
     * @param processSchemeDesignTable 工艺方案设计
     * @return 结果
     */
    @Override
    public int updateProcessSchemeDesignTable(ProcessSchemeDesignTable processSchemeDesignTable)
    {
        return processSchemeDesignTableMapper.updateProcessSchemeDesignTable(processSchemeDesignTable);
    }

    /**
     * 批量删除工艺方案设计
     * 
     * @param ids 需要删除的工艺方案设计主键
     * @return 结果
     */
    @Override
    public int deleteProcessSchemeDesignTableByIds(Long[] ids)
    {
        return processSchemeDesignTableMapper.deleteProcessSchemeDesignTableByIds(ids);
    }

    /**
     * 删除工艺方案设计信息
     * 
     * @param id 工艺方案设计主键
     * @return 结果
     */
    @Override
    public int deleteProcessSchemeDesignTableById(Long id)
    {
        return processSchemeDesignTableMapper.deleteProcessSchemeDesignTableById(id);
    }
}
