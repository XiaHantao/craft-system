package com.ruoyi.workClothes.service.impl;

import java.util.Collections;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.NonstructuraltoolingTableMapper;
import com.ruoyi.workClothes.domain.NonstructuraltoolingTable;
import com.ruoyi.workClothes.service.INonstructuraltoolingTableService;

/**
 * 非结构工装台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@Service
public class NonstructuraltoolingTableServiceImpl implements INonstructuraltoolingTableService 
{
    @Autowired
    private NonstructuraltoolingTableMapper nonstructuraltoolingTableMapper;

    /**
     * 查询非结构工装台账
     * 
     * @param id 非结构工装台账主键
     * @return 非结构工装台账
     */
    @Override
    public NonstructuraltoolingTable selectNonstructuraltoolingTableById(Long id)
    {
        return nonstructuraltoolingTableMapper.selectNonstructuraltoolingTableById(id);
    }

    /**
     * 查询非结构工装台账列表
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账
     */
    @Override
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return nonstructuraltoolingTableMapper.selectNonstructuraltoolingTableList(nonstructuraltoolingTable);
    }

    /**
     * 查询金工工装台账列表
     *
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账
     */
    @Override
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList1(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return nonstructuraltoolingTableMapper.selectNonstructuraltoolingTableList1(nonstructuraltoolingTable);
    }

    /**
     * 查询备料工装台账列表
     *
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账
     */
    @Override
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList2(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return nonstructuraltoolingTableMapper.selectNonstructuraltoolingTableList2(nonstructuraltoolingTable);
    }

    /**
     * 新增非结构工装台账
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 结果
     */
    @Override
    public int insertNonstructuraltoolingTable(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        nonstructuraltoolingTable.setCreateTime(DateUtils.getNowDate());
        return nonstructuraltoolingTableMapper.insertNonstructuraltoolingTable(nonstructuraltoolingTable);
    }

    /**
     * 修改非结构工装台账
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 结果
     */
    @Override
    public int updateNonstructuraltoolingTable(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return nonstructuraltoolingTableMapper.updateNonstructuraltoolingTable(nonstructuraltoolingTable);
    }

    /**
     * 批量删除非结构工装台账
     * 
     * @param ids 需要删除的非结构工装台账主键
     * @return 结果
     */
    @Override
    public int deleteNonstructuraltoolingTableByIds(Long[] ids)
    {
        return nonstructuraltoolingTableMapper.deleteNonstructuraltoolingTableByIds(ids);
    }

    /**
     * 删除非结构工装台账信息
     * 
     * @param id 非结构工装台账主键
     * @return 结果
     */
    @Override
    public int deleteNonstructuraltoolingTableById(Long id)
    {
        return nonstructuraltoolingTableMapper.deleteNonstructuraltoolingTableById(id);
    }
}
