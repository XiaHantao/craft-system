package com.ruoyi.marketanalysis.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategorythreeParameterTableMapper;
import com.ruoyi.marketanalysis.domain.CategorythreeParameterTable;
import com.ruoyi.marketanalysis.service.ICategorythreeParameterTableService;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.marketanalysis.utils.ExcelUtils;
/**
 * 三类车参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-01
 */
@Service
public class CategorythreeParameterTableServiceImpl implements ICategorythreeParameterTableService 
{
    @Autowired
    private CategorythreeParameterTableMapper categorythreeParameterTableMapper;

    /**
     * 查询三类车参数
     * 
     * @param id 三类车参数主键
     * @return 三类车参数
     */
    @Override
    public CategorythreeParameterTable selectCategorythreeParameterTableById(Long id)
    {
        return categorythreeParameterTableMapper.selectCategorythreeParameterTableById(id);
    }

    /**
     * 查询三类车参数列表
     * 
     * @param categorythreeParameterTable 三类车参数
     * @return 三类车参数
     */
    @Override
    public List<CategorythreeParameterTable> selectCategorythreeParameterTableList(CategorythreeParameterTable categorythreeParameterTable)
    {
        return categorythreeParameterTableMapper.selectCategorythreeParameterTableList(categorythreeParameterTable);
    }

    /**
     * 新增三类车参数
     * 
     * @param categorythreeParameterTable 三类车参数
     * @return 结果
     */
    @Override
    public int insertCategorythreeParameterTable(CategorythreeParameterTable categorythreeParameterTable)
    {
        return categorythreeParameterTableMapper.insertCategorythreeParameterTable(categorythreeParameterTable);
    }

    /**
     * 修改三类车参数
     * 
     * @param categorythreeParameterTable 三类车参数
     * @return 结果
     */
    @Override
    public int updateCategorythreeParameterTable(CategorythreeParameterTable categorythreeParameterTable)
    {
        return categorythreeParameterTableMapper.updateCategorythreeParameterTable(categorythreeParameterTable);
    }

    /**
     * 批量删除三类车参数
     * 
     * @param ids 需要删除的三类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategorythreeParameterTableByIds(Long[] ids)
    {
        return categorythreeParameterTableMapper.deleteCategorythreeParameterTableByIds(ids);
    }

    /**
     * 删除三类车参数信息
     * 
     * @param id 三类车参数主键
     * @return 结果
     */
    @Override
    public int deleteCategorythreeParameterTableById(Long id)
    {
        return categorythreeParameterTableMapper.deleteCategorythreeParameterTableById(id);
    }
    @Override
    @Transactional
    public String importCategorythreeParameterTable(File excelFile, boolean updateSupport) {
        try {
            List<CategorythreeParameterTable> list = ExcelUtils.parseCategorythreeParameterExcel(excelFile);

            // 如果允许更新且存在数据，则清空表
            if (updateSupport && checkDataExists()) {
                categorythreeParameterTableMapper.cleanTable();
            }

            // 批量插入数据
            if (!list.isEmpty()) {
                categorythreeParameterTableMapper.batchInsertCategorythreeParameterTable(list);
            }
            return "成功导入 " + list.size() + " 条数据";
        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }

    @Override
    public boolean checkDataExists() {
        return categorythreeParameterTableMapper.checkDataExists() > 0;
    }
}
