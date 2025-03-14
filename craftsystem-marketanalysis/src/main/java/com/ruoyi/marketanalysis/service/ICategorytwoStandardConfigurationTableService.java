package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.CategorytwoStandardConfigurationTable;

/**
 * 二类车标准配置Service接口
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
public interface ICategorytwoStandardConfigurationTableService 
{
    /**
     * 查询二类车标准配置
     * 
     * @param id 二类车标准配置主键
     * @return 二类车标准配置
     */
    public CategorytwoStandardConfigurationTable selectCategorytwoStandardConfigurationTableById(Long id);

    /**
     * 查询二类车标准配置列表
     * 
     * @param categorytwoStandardConfigurationTable 二类车标准配置
     * @return 二类车标准配置集合
     */
    public List<CategorytwoStandardConfigurationTable> selectCategorytwoStandardConfigurationTableList(CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable);

    /**
     * 新增二类车标准配置
     * 
     * @param categorytwoStandardConfigurationTable 二类车标准配置
     * @return 结果
     */
    public int insertCategorytwoStandardConfigurationTable(CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable);

    /**
     * 修改二类车标准配置
     * 
     * @param categorytwoStandardConfigurationTable 二类车标准配置
     * @return 结果
     */
    public int updateCategorytwoStandardConfigurationTable(CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable);

    /**
     * 批量删除二类车标准配置
     * 
     * @param ids 需要删除的二类车标准配置主键集合
     * @return 结果
     */
    public int deleteCategorytwoStandardConfigurationTableByIds(Long[] ids);

    /**
     * 删除二类车标准配置信息
     * 
     * @param id 二类车标准配置主键
     * @return 结果
     */
    public int deleteCategorytwoStandardConfigurationTableById(Long id);
}
