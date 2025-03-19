package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.CategoryfiveParameterTable;

/**
 * 五类车参数Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface ICategoryfiveParameterTableService 
{
    /**
     * 查询五类车参数
     * 
     * @param id 五类车参数主键
     * @return 五类车参数
     */
    public CategoryfiveParameterTable selectCategoryfiveParameterTableById(Long id);

    /**
     * 查询五类车参数列表
     * 
     * @param categoryfiveParameterTable 五类车参数
     * @return 五类车参数集合
     */
    public List<CategoryfiveParameterTable> selectCategoryfiveParameterTableList(CategoryfiveParameterTable categoryfiveParameterTable);

    /**
     * 新增五类车参数
     * 
     * @param categoryfiveParameterTable 五类车参数
     * @return 结果
     */
    public int insertCategoryfiveParameterTable(CategoryfiveParameterTable categoryfiveParameterTable);

    /**
     * 修改五类车参数
     * 
     * @param categoryfiveParameterTable 五类车参数
     * @return 结果
     */
    public int updateCategoryfiveParameterTable(CategoryfiveParameterTable categoryfiveParameterTable);

    /**
     * 批量删除五类车参数
     * 
     * @param ids 需要删除的五类车参数主键集合
     * @return 结果
     */
    public int deleteCategoryfiveParameterTableByIds(Long[] ids);

    /**
     * 删除五类车参数信息
     * 
     * @param id 五类车参数主键
     * @return 结果
     */
    public int deleteCategoryfiveParameterTableById(Long id);
}
