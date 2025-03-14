package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ModelTableMapper;
import com.ruoyi.process.domain.ModelTable;
import com.ruoyi.process.service.IModelTableService;

/**
 * 车型Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
@Service
public class ModelTableServiceImpl implements IModelTableService 
{
    @Autowired
    private ModelTableMapper modelTableMapper;

    /**
     * 查询车型
     * 
     * @param id 车型主键
     * @return 车型
     */
    @Override
    public ModelTable selectModelTableById(Long id)
    {
        return modelTableMapper.selectModelTableById(id);
    }

    /**
     * 查询车型列表
     * 
     * @param modelTable 车型
     * @return 车型
     */
    @Override
    public List<ModelTable> selectModelTableList(ModelTable modelTable)
    {
        return modelTableMapper.selectModelTableList(modelTable);
    }

    /**
     * 新增车型
     * 
     * @param modelTable 车型
     * @return 结果
     */
    @Override
    public int insertModelTable(ModelTable modelTable)
    {
        return modelTableMapper.insertModelTable(modelTable);
    }

    /**
     * 修改车型
     * 
     * @param modelTable 车型
     * @return 结果
     */
    @Override
    public int updateModelTable(ModelTable modelTable)
    {
        return modelTableMapper.updateModelTable(modelTable);
    }

    /**
     * 批量删除车型
     * 
     * @param ids 需要删除的车型主键
     * @return 结果
     */
    @Override
    public int deleteModelTableByIds(Long[] ids)
    {
        return modelTableMapper.deleteModelTableByIds(ids);
    }

    /**
     * 删除车型信息
     * 
     * @param id 车型主键
     * @return 结果
     */
    @Override
    public int deleteModelTableById(Long id)
    {
        return modelTableMapper.deleteModelTableById(id);
    }
}
