package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategoryoneParameterTableMapper;
import com.ruoyi.marketanalysis.domain.CategoryoneParameterTable;
import com.ruoyi.marketanalysis.service.ICategoryoneParameterTableService;

/**
 * 一类车参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-19
 */
@Service
public class CategoryoneParameterTableServiceImpl implements ICategoryoneParameterTableService 
{
    @Autowired
    private CategoryoneParameterTableMapper categoryoneParameterTableMapper;

    /**
     * 查询一类车参数
     * 
     * @param id 一类车参数主键
     * @return 一类车参数
     */
    @Override
    public CategoryoneParameterTable selectCategoryoneParameterTableById(Long id)
    {
        return categoryoneParameterTableMapper.selectCategoryoneParameterTableById(id);
    }

    /**
     * 查询一类车参数列表
     * 
     * @param categoryoneParameterTable 一类车参数
     * @return 一类车参数
     */
    @Override
    public List<CategoryoneParameterTable> selectCategoryoneParameterTableList(CategoryoneParameterTable categoryoneParameterTable)
    {
        return categoryoneParameterTableMapper.selectCategoryoneParameterTableList(categoryoneParameterTable);
    }

    /**
     * 新增一类车参数
     * 
     * @param categoryoneParameterTable 一类车参数
     * @return 结果
     */
    @Override
    public int insertCategoryoneParameterTable(CategoryoneParameterTable categoryoneParameterTable)
    {
        return categoryoneParameterTableMapper.insertCategoryoneParameterTable(categoryoneParameterTable);
    }

    /**
     * 修改一类车参数
     * 
     * @param categoryoneParameterTable 一类车参数
     * @return 结果
     */
    @Override
    public int updateCategoryoneParameterTable(CategoryoneParameterTable categoryoneParameterTable)
    {
        return categoryoneParameterTableMapper.updateCategoryoneParameterTable(categoryoneParameterTable);
    }

    /**
     * 批量删除一类车参数
     * 
     * @param ids 需要删除的一类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategoryoneParameterTableByIds(Long[] ids)
    {
        return categoryoneParameterTableMapper.deleteCategoryoneParameterTableByIds(ids);
    }

    /**
     * 删除一类车参数信息
     * 
     * @param id 一类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategoryoneParameterTableById(Long id)
    {
        return categoryoneParameterTableMapper.deleteCategoryoneParameterTableById(id);
    }
}
