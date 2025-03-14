package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CategoryfiveStandardConfigurationTableMapper;
import com.ruoyi.marketanalysis.domain.CategoryfiveStandardConfigurationTable;
import com.ruoyi.marketanalysis.service.ICategoryfiveStandardConfigurationTableService;

/**
 * 五类/七类车标准配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
@Service
public class CategoryfiveStandardConfigurationTableServiceImpl implements ICategoryfiveStandardConfigurationTableService 
{
    @Autowired
    private CategoryfiveStandardConfigurationTableMapper categoryfiveStandardConfigurationTableMapper;

    /**
     * 查询五类/七类车标准配置
     * 
     * @param id 五类/七类车标准配置主键
     * @return 五类/七类车标准配置
     */
    @Override
    public CategoryfiveStandardConfigurationTable selectCategoryfiveStandardConfigurationTableById(Long id)
    {
        return categoryfiveStandardConfigurationTableMapper.selectCategoryfiveStandardConfigurationTableById(id);
    }

    /**
     * 查询五类/七类车标准配置列表
     * 
     * @param categoryfiveStandardConfigurationTable 五类/七类车标准配置
     * @return 五类/七类车标准配置
     */
    @Override
    public List<CategoryfiveStandardConfigurationTable> selectCategoryfiveStandardConfigurationTableList(CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable)
    {
        return categoryfiveStandardConfigurationTableMapper.selectCategoryfiveStandardConfigurationTableList(categoryfiveStandardConfigurationTable);
    }

    /**
     * 新增五类/七类车标准配置
     * 
     * @param categoryfiveStandardConfigurationTable 五类/七类车标准配置
     * @return 结果
     */
    @Override
    public int insertCategoryfiveStandardConfigurationTable(CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable)
    {
        return categoryfiveStandardConfigurationTableMapper.insertCategoryfiveStandardConfigurationTable(categoryfiveStandardConfigurationTable);
    }

    /**
     * 修改五类/七类车标准配置
     * 
     * @param categoryfiveStandardConfigurationTable 五类/七类车标准配置
     * @return 结果
     */
    @Override
    public int updateCategoryfiveStandardConfigurationTable(CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable)
    {
        return categoryfiveStandardConfigurationTableMapper.updateCategoryfiveStandardConfigurationTable(categoryfiveStandardConfigurationTable);
    }

    /**
     * 批量删除五类/七类车标准配置
     * 
     * @param ids 需要删除的五类/七类车标准配置主键
     * @return 结果
     */
    @Override
    public int deleteCategoryfiveStandardConfigurationTableByIds(Long[] ids)
    {
        return categoryfiveStandardConfigurationTableMapper.deleteCategoryfiveStandardConfigurationTableByIds(ids);
    }

    /**
     * 删除五类/七类车标准配置信息
     * 
     * @param id 五类/七类车标准配置主键
     * @return 结果
     */
    @Override
    public int deleteCategoryfiveStandardConfigurationTableById(Long id)
    {
        return categoryfiveStandardConfigurationTableMapper.deleteCategoryfiveStandardConfigurationTableById(id);
    }
}
