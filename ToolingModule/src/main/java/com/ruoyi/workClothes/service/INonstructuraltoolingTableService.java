package com.ruoyi.workClothes.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.workClothes.domain.NonstructuraltoolingTable;

/**
 * 非结构工装台账Service接口
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
public interface INonstructuraltoolingTableService 
{
    /**
     * 查询非结构工装台账
     * 
     * @param id 非结构工装台账主键
     * @return 非结构工装台账
     */
    public NonstructuraltoolingTable selectNonstructuraltoolingTableById(Long id);

    /**
     * 查询外部工装台账列表
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账集合
     */
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList(NonstructuraltoolingTable nonstructuraltoolingTable);

    /**
     * 查询金工工装台账列表
     *
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账集合
     */
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList1(NonstructuraltoolingTable nonstructuraltoolingTable);

    /**
     * 查询金工工装台账列表
     *
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账集合
     */
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList2(NonstructuraltoolingTable nonstructuraltoolingTable);

    /**
     * 新增非结构工装台账
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 结果
     */
    public int insertNonstructuraltoolingTable(NonstructuraltoolingTable nonstructuraltoolingTable);

    /**
     * 修改非结构工装台账
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 结果
     */
    public int updateNonstructuraltoolingTable(NonstructuraltoolingTable nonstructuraltoolingTable);

    /**
     * 批量删除非结构工装台账
     * 
     * @param ids 需要删除的非结构工装台账主键集合
     * @return 结果
     */
    public int deleteNonstructuraltoolingTableByIds(Long[] ids);

    /**
     * 删除非结构工装台账信息
     * 
     * @param id 非结构工装台账主键
     * @return 结果
     */
    public int deleteNonstructuraltoolingTableById(Long id);

    int updateWorkClothesfile(Map<String, Object> fileform);
}
