package com.ruoyi.workClothes.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.MoldTypeTableMapper;
import com.ruoyi.workClothes.domain.MoldTypeTable;
import com.ruoyi.workClothes.service.IMoldTypeTableService;

/**
 * 工装类别字典Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-06
 */
@Service
public class MoldTypeTableServiceImpl implements IMoldTypeTableService 
{
    @Autowired
    private MoldTypeTableMapper moldTypeTableMapper;

    /**
     * 查询工装类别字典
     * 
     * @param id 工装类别字典主键
     * @return 工装类别字典
     */
    @Override
    public MoldTypeTable selectMoldTypeTableById(Long id)
    {
        return moldTypeTableMapper.selectMoldTypeTableById(id);
    }

    /**
     * 查询工装类别字典列表
     * 
     * @param moldTypeTable 工装类别字典
     * @return 工装类别字典
     */
    @Override
    public List<MoldTypeTable> selectMoldTypeTableList(MoldTypeTable moldTypeTable)
    {
        return moldTypeTableMapper.selectMoldTypeTableList(moldTypeTable);
    }

    /**
     * 新增工装类别字典
     * 
     * @param moldTypeTable 工装类别字典
     * @return 结果
     */
    @Override
    public int insertMoldTypeTable(MoldTypeTable moldTypeTable)
    {
        return moldTypeTableMapper.insertMoldTypeTable(moldTypeTable);
    }

    /**
     * 修改工装类别字典
     * 
     * @param moldTypeTable 工装类别字典
     * @return 结果
     */
    @Override
    public int updateMoldTypeTable(MoldTypeTable moldTypeTable)
    {
        return moldTypeTableMapper.updateMoldTypeTable(moldTypeTable);
    }

    /**
     * 批量删除工装类别字典
     * 
     * @param ids 需要删除的工装类别字典主键
     * @return 结果
     */
    @Override
    public int deleteMoldTypeTableByIds(Long[] ids)
    {
        return moldTypeTableMapper.deleteMoldTypeTableByIds(ids);
    }

    /**
     * 删除工装类别字典信息
     * 
     * @param id 工装类别字典主键
     * @return 结果
     */
    @Override
    public int deleteMoldTypeTableById(Long id)
    {
        return moldTypeTableMapper.deleteMoldTypeTableById(id);
    }

    @Override
    public List<String> listname() {
        return moldTypeTableMapper.selectAllIds();
    }
}
