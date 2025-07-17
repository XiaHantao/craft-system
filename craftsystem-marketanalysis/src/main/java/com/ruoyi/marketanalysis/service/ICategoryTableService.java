package com.ruoyi.marketanalysis.service;

import java.io.File;
import java.util.List;
import com.ruoyi.marketanalysis.domain.CategoryTable;

/**
 * 车类Service接口
 * 
 * @author ruoyi
 * @date 2025-07-08
 */
public interface ICategoryTableService 
{
    /**
     * 查询车类
     * 
     * @param id 车类主键
     * @return 车类
     */
    public CategoryTable selectCategoryTableById(Long id);

    /**
     * 查询车类列表
     * 
     * @param categoryTable 车类
     * @return 车类集合
     */
    public List<CategoryTable> selectCategoryTableList(CategoryTable categoryTable);

    /**
     * 新增车类
     * 
     * @param categoryTable 车类
     * @return 结果
     */
    public int insertCategoryTable(CategoryTable categoryTable);

    /**
     * 修改车类
     * 
     * @param categoryTable 车类
     * @return 结果
     */
    public int updateCategoryTable(CategoryTable categoryTable);

    /**
     * 批量删除车类
     * 
     * @param ids 需要删除的车类主键集合
     * @return 结果
     */
    public int deleteCategoryTableByIds(Long[] ids);

    /**
     * 删除车类信息
     * 
     * @param id 车类主键
     * @return 结果
     */
    public int deleteCategoryTableById(Long id);
    // 新增导入方法声明
    String importCategoryTable(File excelFile, boolean updateSupport);
    // 新增数据存在检查方法
    boolean checkDataExists();
}
