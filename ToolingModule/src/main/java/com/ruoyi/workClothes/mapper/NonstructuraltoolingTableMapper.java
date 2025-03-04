package com.ruoyi.workClothes.mapper;

import java.util.List;
import com.ruoyi.workClothes.domain.NonstructuraltoolingTable;

/**
 * 非结构工装台账Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
public interface NonstructuraltoolingTableMapper 
{
    /**
     * 查询非结构工装台账
     * 
     * @param id 非结构工装台账主键
     * @return 非结构工装台账
     */
    public NonstructuraltoolingTable selectNonstructuraltoolingTableById(Long id);

    /**
     * 查询非结构工装台账列表
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账集合
     */
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList(NonstructuraltoolingTable nonstructuraltoolingTable);

    /**
     * 查询金工班工装台账列表
     *
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账集合
     */
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList1(NonstructuraltoolingTable nonstructuraltoolingTable);

    /**
     * 查询备料班工装台账列表
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
     * 删除非结构工装台账
     * 
     * @param id 非结构工装台账主键
     * @return 结果
     */
    public int deleteNonstructuraltoolingTableById(Long id);

    /**
     * 批量删除非结构工装台账
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNonstructuraltoolingTableByIds(Long[] ids);
}
