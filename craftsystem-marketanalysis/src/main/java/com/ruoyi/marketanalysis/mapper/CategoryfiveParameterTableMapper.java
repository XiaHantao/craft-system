package com.ruoyi.marketanalysis.mapper;

import java.util.List;
import com.ruoyi.marketanalysis.domain.CategoryfiveParameterTable;

/**
 * 五类/七类车参数Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-19
 */
public interface CategoryfiveParameterTableMapper 
{
    /**
     * 查询五类/七类车参数
     * 
     * @param id 五类/七类车参数主键
     * @return 五类/七类车参数
     */
    public CategoryfiveParameterTable selectCategoryfiveParameterTableById(Long id);

    /**
     * 查询五类/七类车参数列表
     * 
     * @param categoryfiveParameterTable 五类/七类车参数
     * @return 五类/七类车参数集合
     */
    public List<CategoryfiveParameterTable> selectCategoryfiveParameterTableList(CategoryfiveParameterTable categoryfiveParameterTable);

    /**
     * 新增五类/七类车参数
     * 
     * @param categoryfiveParameterTable 五类/七类车参数
     * @return 结果
     */
    public int insertCategoryfiveParameterTable(CategoryfiveParameterTable categoryfiveParameterTable);

    /**
     * 修改五类/七类车参数
     * 
     * @param categoryfiveParameterTable 五类/七类车参数
     * @return 结果
     */
    public int updateCategoryfiveParameterTable(CategoryfiveParameterTable categoryfiveParameterTable);

    /**
     * 删除五类/七类车参数
     * 
     * @param id 五类/七类车参数主键
     * @return 结果
     */
    public int deleteCategoryfiveParameterTableById(Long id);

    /**
     * 批量删除五类/七类车参数
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryfiveParameterTableByIds(Long[] ids);
}
