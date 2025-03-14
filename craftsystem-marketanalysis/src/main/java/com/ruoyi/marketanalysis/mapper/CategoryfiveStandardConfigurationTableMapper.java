package com.ruoyi.marketanalysis.mapper;

import java.util.List;
import com.ruoyi.marketanalysis.domain.CategoryfiveStandardConfigurationTable;

/**
 * 五类/七类车标准配置Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
public interface CategoryfiveStandardConfigurationTableMapper 
{
    /**
     * 查询五类/七类车标准配置
     * 
     * @param id 五类/七类车标准配置主键
     * @return 五类/七类车标准配置
     */
    public CategoryfiveStandardConfigurationTable selectCategoryfiveStandardConfigurationTableById(Long id);

    /**
     * 查询五类/七类车标准配置列表
     * 
     * @param categoryfiveStandardConfigurationTable 五类/七类车标准配置
     * @return 五类/七类车标准配置集合
     */
    public List<CategoryfiveStandardConfigurationTable> selectCategoryfiveStandardConfigurationTableList(CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable);

    /**
     * 新增五类/七类车标准配置
     * 
     * @param categoryfiveStandardConfigurationTable 五类/七类车标准配置
     * @return 结果
     */
    public int insertCategoryfiveStandardConfigurationTable(CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable);

    /**
     * 修改五类/七类车标准配置
     * 
     * @param categoryfiveStandardConfigurationTable 五类/七类车标准配置
     * @return 结果
     */
    public int updateCategoryfiveStandardConfigurationTable(CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable);

    /**
     * 删除五类/七类车标准配置
     * 
     * @param id 五类/七类车标准配置主键
     * @return 结果
     */
    public int deleteCategoryfiveStandardConfigurationTableById(Long id);

    /**
     * 批量删除五类/七类车标准配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryfiveStandardConfigurationTableByIds(Long[] ids);
}
