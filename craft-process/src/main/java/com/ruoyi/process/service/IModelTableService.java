package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ModelTable;

/**
 * 车型Service接口
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
public interface IModelTableService 
{
    /**
     * 查询车型
     * 
     * @param id 车型主键
     * @return 车型
     */
    public ModelTable selectModelTableById(Long id);

    /**
     * 查询车型列表
     * 
     * @param modelTable 车型
     * @return 车型集合
     */
    public List<ModelTable> selectModelTableList(ModelTable modelTable);

    /**
     * 新增车型
     * 
     * @param modelTable 车型
     * @return 结果
     */
    public int insertModelTable(ModelTable modelTable);

    /**
     * 修改车型
     * 
     * @param modelTable 车型
     * @return 结果
     */
    public int updateModelTable(ModelTable modelTable);

    /**
     * 批量删除车型
     * 
     * @param ids 需要删除的车型主键集合
     * @return 结果
     */
    public int deleteModelTableByIds(Long[] ids);

    /**
     * 删除车型信息
     * 
     * @param id 车型主键
     * @return 结果
     */
    public int deleteModelTableById(Long id);
}
