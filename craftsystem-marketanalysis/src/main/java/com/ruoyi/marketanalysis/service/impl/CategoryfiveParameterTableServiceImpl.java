package com.ruoyi.marketanalysis.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategoryfiveParameterTableMapper;
import com.ruoyi.marketanalysis.domain.CategoryfiveParameterTable;
import com.ruoyi.marketanalysis.service.ICategoryfiveParameterTableService;
import com.ruoyi.marketanalysis.utils.ExcelUtils;
/**
 * 五类车参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class CategoryfiveParameterTableServiceImpl implements ICategoryfiveParameterTableService 
{
    @Autowired
    private CategoryfiveParameterTableMapper categoryfiveParameterTableMapper;

    /**
     * 查询五类车参数
     * 
     * @param id 五类车参数主键
     * @return 五类车参数
     */
    @Override
    public CategoryfiveParameterTable selectCategoryfiveParameterTableById(Long id)
    {
        return categoryfiveParameterTableMapper.selectCategoryfiveParameterTableById(id);
    }

    /**
     * 查询五类车参数列表
     * 
     * @param categoryfiveParameterTable 五类车参数
     * @return 五类车参数
     */
    @Override
    public List<CategoryfiveParameterTable> selectCategoryfiveParameterTableList(CategoryfiveParameterTable categoryfiveParameterTable)
    {
        return categoryfiveParameterTableMapper.selectCategoryfiveParameterTableList(categoryfiveParameterTable);
    }

    /**
     * 新增五类车参数
     * 
     * @param categoryfiveParameterTable 五类车参数
     * @return 结果
     */
    @Override
    public int insertCategoryfiveParameterTable(CategoryfiveParameterTable categoryfiveParameterTable)
    {
        return categoryfiveParameterTableMapper.insertCategoryfiveParameterTable(categoryfiveParameterTable);
    }

    /**
     * 修改五类车参数
     * 
     * @param categoryfiveParameterTable 五类车参数
     * @return 结果
     */
    @Override
    public int updateCategoryfiveParameterTable(CategoryfiveParameterTable categoryfiveParameterTable)
    {
        return categoryfiveParameterTableMapper.updateCategoryfiveParameterTable(categoryfiveParameterTable);
    }

    /**
     * 批量删除五类车参数
     * 
     * @param ids 需要删除的五类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategoryfiveParameterTableByIds(Long[] ids)
    {
        return categoryfiveParameterTableMapper.deleteCategoryfiveParameterTableByIds(ids);
    }

    /**
     * 删除五类车参数信息
     * 
     * @param id 五类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategoryfiveParameterTableById(Long id)
    {
        return categoryfiveParameterTableMapper.deleteCategoryfiveParameterTableById(id);
    }
    @Override
    public String importCategoryfiveParameterTable(File excelFile, boolean updateSupport) {
        try {
            List<CategoryfiveParameterTable> list = ExcelUtils.parseCategoryfiveParameterExcel(excelFile);

            // 如果允许更新且存在数据，则清空表
            if (updateSupport && checkDataExists()) {
                categoryfiveParameterTableMapper.cleanTable();
            }

            // 批量插入数据
            categoryfiveParameterTableMapper.batchInsertCategoryfiveParameterTable(list);
            return "成功导入 " + list.size() + " 条数据";
        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }

    @Override
    public boolean checkDataExists() {
        return categoryfiveParameterTableMapper.checkDataExists() > 0;
    }
}
