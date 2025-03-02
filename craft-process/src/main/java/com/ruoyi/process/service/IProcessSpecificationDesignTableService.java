package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ProcessSpecificationDesignTable;

/**
 * 工艺规程设计Service接口
 * 
 * @author Kanna Bush
 * @date 2025-02-24
 */
public interface IProcessSpecificationDesignTableService 
{
    /**
     * 查询工艺规程设计
     * 
     * @param id 工艺规程设计主键
     * @return 工艺规程设计
     */
    public ProcessSpecificationDesignTable selectProcessSpecificationDesignTableById(Long id);

    /**
     * 查询工艺规程设计列表
     *
     * @param processSpecificationDesignTable 工艺规程设计
     * @return 工艺规程设计集合
     */
    public List<ProcessSpecificationDesignTable> selectProcessSpecificationDesignTableList(ProcessSpecificationDesignTable processSpecificationDesignTable);

    /**
     * 查询通用规程列表
     *
     * @param processSpecificationDesignTable 工艺规程设计
     * @return 工艺规程设计集合
     */
    public List<ProcessSpecificationDesignTable> selectGeneralList(ProcessSpecificationDesignTable processSpecificationDesignTable);


    /**
     * 新增工艺规程设计
     * 
     * @param processSpecificationDesignTable 工艺规程设计
     * @return 结果
     */
    public int insertProcessSpecificationDesignTable(ProcessSpecificationDesignTable processSpecificationDesignTable);

    /**
     * 修改工艺规程设计
     * 
     * @param processSpecificationDesignTable 工艺规程设计
     * @return 结果
     */
    public int updateProcessSpecificationDesignTable(ProcessSpecificationDesignTable processSpecificationDesignTable);

    /**
     * 批量删除工艺规程设计
     * 
     * @param ids 需要删除的工艺规程设计主键集合
     * @return 结果
     */
    public int deleteProcessSpecificationDesignTableByIds(Long[] ids);

    /**
     * 删除工艺规程设计信息
     * 
     * @param id 工艺规程设计主键
     * @return 结果
     */
    public int deleteProcessSpecificationDesignTableById(Long id);
}
