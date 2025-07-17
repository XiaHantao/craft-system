package com.ruoyi.marketanalysis.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategoryTableMapper;
import com.ruoyi.marketanalysis.domain.CategoryTable;
import com.ruoyi.marketanalysis.service.ICategoryTableService;
import com.ruoyi.marketanalysis.utils.ExcelUtils;
/**
 * 车类Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-08
 */
@Service
public class CategoryTableServiceImpl implements ICategoryTableService {
    @Autowired
    private CategoryTableMapper categoryTableMapper;

    /**
     * 查询车类
     *
     * @param id 车类主键
     * @return 车类
     */
    @Override
    public CategoryTable selectCategoryTableById(Long id) {
        return categoryTableMapper.selectCategoryTableById(id);
    }

    /**
     * 查询车类列表
     *
     * @param categoryTable 车类
     * @return 车类
     */
    @Override
    public List<CategoryTable> selectCategoryTableList(CategoryTable categoryTable) {
        return categoryTableMapper.selectCategoryTableList(categoryTable);
    }

    /**
     * 新增车类
     *
     * @param categoryTable 车类
     * @return 结果
     */
    @Override
    public int insertCategoryTable(CategoryTable categoryTable) {
        return categoryTableMapper.insertCategoryTable(categoryTable);
    }

    /**
     * 修改车类
     *
     * @param categoryTable 车类
     * @return 结果
     */
    @Override
    public int updateCategoryTable(CategoryTable categoryTable) {
        return categoryTableMapper.updateCategoryTable(categoryTable);
    }

    /**
     * 批量删除车类
     *
     * @param ids 需要删除的车类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryTableByIds(Long[] ids) {
        return categoryTableMapper.deleteCategoryTableByIds(ids);
    }

    /**
     * 删除车类信息
     *
     * @param id 车类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryTableById(Long id) {
        return categoryTableMapper.deleteCategoryTableById(id);
    }

    @Override
    public String importCategoryTable(File excelFile, boolean updateSupport) {
        try {
            List<CategoryTable> list = ExcelUtils.parseCategoryExcel(excelFile);
            if (updateSupport) {
                categoryTableMapper.cleanTable();
            }
            int rows = categoryTableMapper.batchInsertCategoryTable(list);
            return "成功导入 " + rows + " 条数据";
        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }

    @Override
    public boolean checkDataExists() {
        return categoryTableMapper.checkDataExists() > 0;
    }
}
