package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategorysevenParameterTableMapper;
import com.ruoyi.marketanalysis.domain.CategorysevenParameterTable;
import com.ruoyi.marketanalysis.service.ICategorysevenParameterTableService;

/**
 * 七类车参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-06
 */
@Service
public class CategorysevenParameterTableServiceImpl implements ICategorysevenParameterTableService 
{
    @Autowired
    private CategorysevenParameterTableMapper categorysevenParameterTableMapper;

    /**
     * 查询七类车参数
     * 
     * @param id 七类车参数主键
     * @return 七类车参数
     */
    @Override
    public CategorysevenParameterTable selectCategorysevenParameterTableById(Long id)
    {
        return categorysevenParameterTableMapper.selectCategorysevenParameterTableById(id);
    }

    /**
     * 查询七类车参数列表
     * 
     * @param categorysevenParameterTable 七类车参数
     * @return 七类车参数
     */
    @Override
    public List<CategorysevenParameterTable> selectCategorysevenParameterTableList(CategorysevenParameterTable categorysevenParameterTable)
    {
        return categorysevenParameterTableMapper.selectCategorysevenParameterTableList(categorysevenParameterTable);
    }

    /**
     * 新增七类车参数
     * 
     * @param categorysevenParameterTable 七类车参数
     * @return 结果
     */
    @Override
    public int insertCategorysevenParameterTable(CategorysevenParameterTable categorysevenParameterTable)
    {
        return categorysevenParameterTableMapper.insertCategorysevenParameterTable(categorysevenParameterTable);
    }

    /**
     * 修改七类车参数
     * 
     * @param categorysevenParameterTable 七类车参数
     * @return 结果
     */
    @Override
    public int updateCategorysevenParameterTable(CategorysevenParameterTable categorysevenParameterTable)
    {
        return categorysevenParameterTableMapper.updateCategorysevenParameterTable(categorysevenParameterTable);
    }

    /**
     * 批量删除七类车参数
     * 
     * @param ids 需要删除的七类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategorysevenParameterTableByIds(Long[] ids)
    {
        return categorysevenParameterTableMapper.deleteCategorysevenParameterTableByIds(ids);
    }

    /**
     * 删除七类车参数信息
     * 
     * @param id 七类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategorysevenParameterTableById(Long id)
    {
        return categorysevenParameterTableMapper.deleteCategorysevenParameterTableById(id);
    }
}
