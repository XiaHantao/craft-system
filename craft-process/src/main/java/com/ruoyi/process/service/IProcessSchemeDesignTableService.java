package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ProcessSchemeDesignTable;

/**
 * 工艺方案设计Service接口
 * 
 * @author Kanna Bush
 * @date 2025-02-20
 */
public interface IProcessSchemeDesignTableService 
{
    /**
     * 查询工艺方案设计
     * 
     * @param id 工艺方案设计主键
     * @return 工艺方案设计
     */
    public ProcessSchemeDesignTable selectProcessSchemeDesignTableById(Long id);

    /**
     * 查询工艺方案设计列表
     * 
     * @param processSchemeDesignTable 工艺方案设计
     * @return 工艺方案设计集合
     */
    public List<ProcessSchemeDesignTable> selectProcessSchemeDesignTableList(ProcessSchemeDesignTable processSchemeDesignTable);

    /**
     * 新增工艺方案设计
     * 
     * @param processSchemeDesignTable 工艺方案设计
     * @return 结果
     */
    public int insertProcessSchemeDesignTable(ProcessSchemeDesignTable processSchemeDesignTable);

    /**
     * 修改工艺方案设计
     * 
     * @param processSchemeDesignTable 工艺方案设计
     * @return 结果
     */
    public int updateProcessSchemeDesignTable(ProcessSchemeDesignTable processSchemeDesignTable);

    /**
     * 批量删除工艺方案设计
     * 
     * @param ids 需要删除的工艺方案设计主键集合
     * @return 结果
     */
    public int deleteProcessSchemeDesignTableByIds(Long[] ids);

    /**
     * 删除工艺方案设计信息
     * 
     * @param id 工艺方案设计主键
     * @return 结果
     */
    public int deleteProcessSchemeDesignTableById(Long id);
}
