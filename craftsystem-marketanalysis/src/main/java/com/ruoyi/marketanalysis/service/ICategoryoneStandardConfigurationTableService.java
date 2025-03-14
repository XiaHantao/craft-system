package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.CategoryoneStandardConfigurationTable;

/**
 * 一类车标准配置Service接口
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
public interface ICategoryoneStandardConfigurationTableService 
{
    /**
     * 查询一类车标准配置
     * 
     * @param id 一类车标准配置主键
     * @return 一类车标准配置
     */
    public CategoryoneStandardConfigurationTable selectCategoryoneStandardConfigurationTableById(Long id);

    /**
     * 查询一类车标准配置列表
     * 
     * @param categoryoneStandardConfigurationTable 一类车标准配置
     * @return 一类车标准配置集合
     */
    public List<CategoryoneStandardConfigurationTable> selectCategoryoneStandardConfigurationTableList(CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable);

    /**
     * 新增一类车标准配置
     * 
     * @param categoryoneStandardConfigurationTable 一类车标准配置
     * @return 结果
     */
    public int insertCategoryoneStandardConfigurationTable(CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable);

    /**
     * 修改一类车标准配置
     * 
     * @param categoryoneStandardConfigurationTable 一类车标准配置
     * @return 结果
     */
    public int updateCategoryoneStandardConfigurationTable(CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable);

    /**
     * 批量删除一类车标准配置
     * 
     * @param ids 需要删除的一类车标准配置主键集合
     * @return 结果
     */
    public int deleteCategoryoneStandardConfigurationTableByIds(Long[] ids);

    /**
     * 删除一类车标准配置信息
     * 
     * @param id 一类车标准配置主键
     * @return 结果
     */
    public int deleteCategoryoneStandardConfigurationTableById(Long id);
}
