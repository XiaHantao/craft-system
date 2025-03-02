package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessSpecificationDesignTableMapper;
import com.ruoyi.process.domain.ProcessSpecificationDesignTable;
import com.ruoyi.process.service.IProcessSpecificationDesignTableService;

/**
 * 工艺规程设计Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-24
 */
@Service
public class ProcessSpecificationDesignTableServiceImpl implements IProcessSpecificationDesignTableService 
{
    @Autowired
    private ProcessSpecificationDesignTableMapper processSpecificationDesignTableMapper;

    /**
     * 查询工艺规程设计
     * 
     * @param id 工艺规程设计主键
     * @return 工艺规程设计
     */
    @Override
    public ProcessSpecificationDesignTable selectProcessSpecificationDesignTableById(Long id)
    {
        return processSpecificationDesignTableMapper.selectProcessSpecificationDesignTableById(id);
    }

    /**
     * 查询工艺规程设计列表
     * 
     * @param processSpecificationDesignTable 工艺规程设计
     * @return 工艺规程设计
     */
    @Override
    public List<ProcessSpecificationDesignTable> selectProcessSpecificationDesignTableList(ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        return processSpecificationDesignTableMapper.selectProcessSpecificationDesignTableList(processSpecificationDesignTable);
    }

    /**
     * 查询工艺规程设计列表
     *
     * @param processSpecificationDesignTable 工艺规程设计
     * @return 工艺规程设计
     */
    @Override
    public List<ProcessSpecificationDesignTable> selectGeneralList(ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        return processSpecificationDesignTableMapper.selectGeneralList(processSpecificationDesignTable);
    }

    /**
     * 新增工艺规程设计
     * 
     * @param processSpecificationDesignTable 工艺规程设计
     * @return 结果
     */
    @Override
    public int insertProcessSpecificationDesignTable(ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        return processSpecificationDesignTableMapper.insertProcessSpecificationDesignTable(processSpecificationDesignTable);
    }

    /**
     * 修改工艺规程设计
     * 
     * @param processSpecificationDesignTable 工艺规程设计
     * @return 结果
     */
    @Override
    public int updateProcessSpecificationDesignTable(ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        return processSpecificationDesignTableMapper.updateProcessSpecificationDesignTable(processSpecificationDesignTable);
    }

    /**
     * 批量删除工艺规程设计
     * 
     * @param ids 需要删除的工艺规程设计主键
     * @return 结果
     */
    @Override
    public int deleteProcessSpecificationDesignTableByIds(Long[] ids)
    {
        return processSpecificationDesignTableMapper.deleteProcessSpecificationDesignTableByIds(ids);
    }

    /**
     * 删除工艺规程设计信息
     * 
     * @param id 工艺规程设计主键
     * @return 结果
     */
    @Override
    public int deleteProcessSpecificationDesignTableById(Long id)
    {
        return processSpecificationDesignTableMapper.deleteProcessSpecificationDesignTableById(id);
    }
}
