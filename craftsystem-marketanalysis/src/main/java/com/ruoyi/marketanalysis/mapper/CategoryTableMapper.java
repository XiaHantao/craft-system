package com.ruoyi.marketanalysis.mapper;

import java.util.List;
import com.ruoyi.marketanalysis.domain.CategoryTable;

/**
 * 车类Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-08
 */
public interface CategoryTableMapper 
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
     * 删除车类
     * 
     * @param id 车类主键
     * @return 结果
     */
    public int deleteCategoryTableById(Long id);

    /**
     * 批量删除车类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryTableByIds(Long[] ids);
    // 新增方法
    int batchInsertCategoryTable(List<CategoryTable> list);
    int cleanTable();
    // 新增数据存在检查方法
    int checkDataExists();
}
