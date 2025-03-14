package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.CategorysevenParameterTable;

/**
 * 七类车参数Service接口
 * 
 * @author ruoyi
 * @date 2025-03-06
 */
public interface ICategorysevenParameterTableService 
{
    /**
     * 查询七类车参数
     * 
     * @param id 七类车参数主键
     * @return 七类车参数
     */
    public CategorysevenParameterTable selectCategorysevenParameterTableById(Long id);

    /**
     * 查询七类车参数列表
     * 
     * @param categorysevenParameterTable 七类车参数
     * @return 七类车参数集合
     */
    public List<CategorysevenParameterTable> selectCategorysevenParameterTableList(CategorysevenParameterTable categorysevenParameterTable);

    /**
     * 新增七类车参数
     * 
     * @param categorysevenParameterTable 七类车参数
     * @return 结果
     */
    public int insertCategorysevenParameterTable(CategorysevenParameterTable categorysevenParameterTable);

    /**
     * 修改七类车参数
     * 
     * @param categorysevenParameterTable 七类车参数
     * @return 结果
     */
    public int updateCategorysevenParameterTable(CategorysevenParameterTable categorysevenParameterTable);

    /**
     * 批量删除七类车参数
     * 
     * @param ids 需要删除的七类车参数主键集合
     * @return 结果
     */
    public int deleteCategorysevenParameterTableByIds(Long[] ids);

    /**
     * 删除七类车参数信息
     * 
     * @param id 七类车参数主键
     * @return 结果
     */
    public int deleteCategorysevenParameterTableById(Long id);
}
