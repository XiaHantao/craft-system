package com.ruoyi.marketanalysis.service;

import java.io.File;
import java.util.List;
import com.ruoyi.marketanalysis.domain.CategoryoneParameterTable;

/**
 * 一类车参数Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface ICategoryoneParameterTableService 
{
    /**
     * 查询一类车参数
     * 
     * @param id 一类车参数主键
     * @return 一类车参数
     */
    public CategoryoneParameterTable selectCategoryoneParameterTableById(Long id);

    /**
     * 查询一类车参数列表
     * 
     * @param categoryoneParameterTable 一类车参数
     * @return 一类车参数集合
     */
    public List<CategoryoneParameterTable> selectCategoryoneParameterTableList(CategoryoneParameterTable categoryoneParameterTable);

    /**
     * 新增一类车参数
     * 
     * @param categoryoneParameterTable 一类车参数
     * @return 结果
     */
    public int insertCategoryoneParameterTable(CategoryoneParameterTable categoryoneParameterTable);

    /**
     * 修改一类车参数
     * 
     * @param categoryoneParameterTable 一类车参数
     * @return 结果
     */
    public int updateCategoryoneParameterTable(CategoryoneParameterTable categoryoneParameterTable);

    /**
     * 批量删除一类车参数
     * 
     * @param ids 需要删除的一类车参数主键集合
     * @return 结果
     */
    public int deleteCategoryoneParameterTableByIds(Long[] ids);

    /**
     * 删除一类车参数信息
     * 
     * @param id 一类车参数主键
     * @return 结果
     */
    public int deleteCategoryoneParameterTableById(Long id);
    // 导入一类车参数数据
    String importCategoryoneParameterTable(File excelFile, boolean updateSupport) throws Exception;

    // 检查表中是否有数据
    boolean checkDataExists();
}
