package com.ruoyi.marketanalysis.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategorytwoStandardConfigurationTableMapper;
import com.ruoyi.marketanalysis.domain.CategorytwoStandardConfigurationTable;
import com.ruoyi.marketanalysis.service.ICategorytwoStandardConfigurationTableService;
import com.ruoyi.marketanalysis.utils.ExcelUtils;
/**
 * 二类车标准配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class CategorytwoStandardConfigurationTableServiceImpl implements ICategorytwoStandardConfigurationTableService 
{
    @Autowired
    private CategorytwoStandardConfigurationTableMapper categorytwoStandardConfigurationTableMapper;

    /**
     * 查询二类车标准配置
     * 
     * @param id 二类车标准配置主键
     * @return 二类车标准配置
     */
    @Override
    public CategorytwoStandardConfigurationTable selectCategorytwoStandardConfigurationTableById(Long id)
    {
        return categorytwoStandardConfigurationTableMapper.selectCategorytwoStandardConfigurationTableById(id);
    }

    /**
     * 查询二类车标准配置列表
     * 
     * @param categorytwoStandardConfigurationTable 二类车标准配置
     * @return 二类车标准配置
     */
    @Override
    public List<CategorytwoStandardConfigurationTable> selectCategorytwoStandardConfigurationTableList(CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable)
    {
        return categorytwoStandardConfigurationTableMapper.selectCategorytwoStandardConfigurationTableList(categorytwoStandardConfigurationTable);
    }

    /**
     * 新增二类车标准配置
     * 
     * @param categorytwoStandardConfigurationTable 二类车标准配置
     * @return 结果
     */
    @Override
    public int insertCategorytwoStandardConfigurationTable(CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable)
    {
        return categorytwoStandardConfigurationTableMapper.insertCategorytwoStandardConfigurationTable(categorytwoStandardConfigurationTable);
    }

    /**
     * 修改二类车标准配置
     * 
     * @param categorytwoStandardConfigurationTable 二类车标准配置
     * @return 结果
     */
    @Override
    public int updateCategorytwoStandardConfigurationTable(CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable)
    {
        return categorytwoStandardConfigurationTableMapper.updateCategorytwoStandardConfigurationTable(categorytwoStandardConfigurationTable);
    }

    /**
     * 批量删除二类车标准配置
     * 
     * @param ids 需要删除的二类车标准配置主键
     * @return 结果
     */
    @Override
    public int deleteCategorytwoStandardConfigurationTableByIds(Long[] ids)
    {
        return categorytwoStandardConfigurationTableMapper.deleteCategorytwoStandardConfigurationTableByIds(ids);
    }

    /**
     * 删除二类车标准配置信息
     * 
     * @param id 二类车标准配置主键
     * @return 结果
     */
    @Override
    public int deleteCategorytwoStandardConfigurationTableById(Long id)
    {
        return categorytwoStandardConfigurationTableMapper.deleteCategorytwoStandardConfigurationTableById(id);
    }
    @Override
    public String importCategorytwoStandard(File excelFile, boolean updateSupport) {
        try {
            List<CategorytwoStandardConfigurationTable> list = ExcelUtils.parseCategorytwoStandardExcel(excelFile);

            // 如果允许更新且存在数据，则清空表
            if (updateSupport && checkDataExists()) {
                categorytwoStandardConfigurationTableMapper.cleanTable();
            }

            // 批量插入数据
            if (!list.isEmpty()) {
                categorytwoStandardConfigurationTableMapper.batchInsertCategorytwoStandard(list);
            }

            return "成功导入 " + list.size() + " 条数据";
        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }

    @Override
    public boolean checkDataExists() {
        return categorytwoStandardConfigurationTableMapper.checkDataExists() > 0;
    }
}
