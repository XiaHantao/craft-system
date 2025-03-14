package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategorytwoParameterTableMapper;
import com.ruoyi.marketanalysis.domain.CategorytwoParameterTable;
import com.ruoyi.marketanalysis.service.ICategorytwoParameterTableService;

/**
 * 二类车参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
@Service
public class CategorytwoParameterTableServiceImpl implements ICategorytwoParameterTableService 
{
    @Autowired
    private CategorytwoParameterTableMapper categorytwoParameterTableMapper;

    /**
     * 查询二类车参数
     * 
     * @param id 二类车参数主键
     * @return 二类车参数
     */
    @Override
    public CategorytwoParameterTable selectCategorytwoParameterTableById(Long id)
    {
        return categorytwoParameterTableMapper.selectCategorytwoParameterTableById(id);
    }

    /**
     * 查询二类车参数列表
     * 
     * @param categorytwoParameterTable 二类车参数
     * @return 二类车参数
     */
    @Override
    public List<CategorytwoParameterTable> selectCategorytwoParameterTableList(CategorytwoParameterTable categorytwoParameterTable)
    {
        return categorytwoParameterTableMapper.selectCategorytwoParameterTableList(categorytwoParameterTable);
    }

    /**
     * 新增二类车参数
     * 
     * @param categorytwoParameterTable 二类车参数
     * @return 结果
     */
    @Override
    public int insertCategorytwoParameterTable(CategorytwoParameterTable categorytwoParameterTable)
    {
        return categorytwoParameterTableMapper.insertCategorytwoParameterTable(categorytwoParameterTable);
    }

    /**
     * 修改二类车参数
     * 
     * @param categorytwoParameterTable 二类车参数
     * @return 结果
     */
    @Override
    public int updateCategorytwoParameterTable(CategorytwoParameterTable categorytwoParameterTable)
    {
        return categorytwoParameterTableMapper.updateCategorytwoParameterTable(categorytwoParameterTable);
    }

    /**
     * 批量删除二类车参数
     * 
     * @param ids 需要删除的二类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategorytwoParameterTableByIds(Long[] ids)
    {
        return categorytwoParameterTableMapper.deleteCategorytwoParameterTableByIds(ids);
    }

    /**
     * 删除二类车参数信息
     * 
     * @param id 二类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategorytwoParameterTableById(Long id)
    {
        return categorytwoParameterTableMapper.deleteCategorytwoParameterTableById(id);
    }
}
