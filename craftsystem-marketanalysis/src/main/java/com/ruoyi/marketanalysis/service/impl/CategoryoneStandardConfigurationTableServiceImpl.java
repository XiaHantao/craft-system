package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategoryoneStandardConfigurationTableMapper;
import com.ruoyi.marketanalysis.domain.CategoryoneStandardConfigurationTable;
import com.ruoyi.marketanalysis.service.ICategoryoneStandardConfigurationTableService;

/**
 * 一类车标准配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
@Service
public class CategoryoneStandardConfigurationTableServiceImpl implements ICategoryoneStandardConfigurationTableService 
{
    @Autowired
    private CategoryoneStandardConfigurationTableMapper categoryoneStandardConfigurationTableMapper;

    /**
     * 查询一类车标准配置
     * 
     * @param id 一类车标准配置主键
     * @return 一类车标准配置
     */
    @Override
    public CategoryoneStandardConfigurationTable selectCategoryoneStandardConfigurationTableById(Long id)
    {
        return categoryoneStandardConfigurationTableMapper.selectCategoryoneStandardConfigurationTableById(id);
    }

    /**
     * 查询一类车标准配置列表
     * 
     * @param categoryoneStandardConfigurationTable 一类车标准配置
     * @return 一类车标准配置
     */
    @Override
    public List<CategoryoneStandardConfigurationTable> selectCategoryoneStandardConfigurationTableList(CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable)
    {
        return categoryoneStandardConfigurationTableMapper.selectCategoryoneStandardConfigurationTableList(categoryoneStandardConfigurationTable);
    }

    /**
     * 新增一类车标准配置
     * 
     * @param categoryoneStandardConfigurationTable 一类车标准配置
     * @return 结果
     */
    @Override
    public int insertCategoryoneStandardConfigurationTable(CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable)
    {
        return categoryoneStandardConfigurationTableMapper.insertCategoryoneStandardConfigurationTable(categoryoneStandardConfigurationTable);
    }

    /**
     * 修改一类车标准配置
     * 
     * @param categoryoneStandardConfigurationTable 一类车标准配置
     * @return 结果
     */
    @Override
    public int updateCategoryoneStandardConfigurationTable(CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable)
    {
        return categoryoneStandardConfigurationTableMapper.updateCategoryoneStandardConfigurationTable(categoryoneStandardConfigurationTable);
    }

    /**
     * 批量删除一类车标准配置
     * 
     * @param ids 需要删除的一类车标准配置主键
     * @return 结果
     */
    @Override
    public int deleteCategoryoneStandardConfigurationTableByIds(Long[] ids)
    {
        return categoryoneStandardConfigurationTableMapper.deleteCategoryoneStandardConfigurationTableByIds(ids);
    }

    /**
     * 删除一类车标准配置信息
     * 
     * @param id 一类车标准配置主键
     * @return 结果
     */
    @Override
    public int deleteCategoryoneStandardConfigurationTableById(Long id)
    {
        return categoryoneStandardConfigurationTableMapper.deleteCategoryoneStandardConfigurationTableById(id);
    }
}
