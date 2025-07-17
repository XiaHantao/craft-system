package com.ruoyi.marketanalysis.service;

import java.io.File;
import java.util.List;
import com.ruoyi.marketanalysis.domain.CategorytwoParameterTable;

/**
 * 二类车参数Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface ICategorytwoParameterTableService 
{
    /**
     * 查询二类车参数
     * 
     * @param id 二类车参数主键
     * @return 二类车参数
     */
    public CategorytwoParameterTable selectCategorytwoParameterTableById(Long id);

    /**
     * 查询二类车参数列表
     * 
     * @param categorytwoParameterTable 二类车参数
     * @return 二类车参数集合
     */
    public List<CategorytwoParameterTable> selectCategorytwoParameterTableList(CategorytwoParameterTable categorytwoParameterTable);

    /**
     * 新增二类车参数
     * 
     * @param categorytwoParameterTable 二类车参数
     * @return 结果
     */
    public int insertCategorytwoParameterTable(CategorytwoParameterTable categorytwoParameterTable);

    /**
     * 修改二类车参数
     * 
     * @param categorytwoParameterTable 二类车参数
     * @return 结果
     */
    public int updateCategorytwoParameterTable(CategorytwoParameterTable categorytwoParameterTable);

    /**
     * 批量删除二类车参数
     * 
     * @param ids 需要删除的二类车参数主键集合
     * @return 结果
     */
    public int deleteCategorytwoParameterTableByIds(Long[] ids);

    /**
     * 删除二类车参数信息
     * 
     * @param id 二类车参数主键
     * @return 结果
     */
    public int deleteCategorytwoParameterTableById(Long id);
    String importCategorytwoParameterTable(File excelFile, boolean updateSupport);
    boolean checkDataExists();
}
