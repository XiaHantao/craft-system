package com.ruoyi.workClothes.service;

import java.util.List;
import com.ruoyi.workClothes.domain.MoldTypeTable;

/**
 * 工装类别字典Service接口
 * 
 * @author ruoyi
 * @date 2025-03-06
 */
public interface IMoldTypeTableService 
{
    /**
     * 查询工装类别字典
     * 
     * @param id 工装类别字典主键
     * @return 工装类别字典
     */
    public MoldTypeTable selectMoldTypeTableById(Long id);

    /**
     * 查询工装类别字典列表
     * 
     * @param moldTypeTable 工装类别字典
     * @return 工装类别字典集合
     */
    public List<MoldTypeTable> selectMoldTypeTableList(MoldTypeTable moldTypeTable);

    /**
     * 新增工装类别字典
     * 
     * @param moldTypeTable 工装类别字典
     * @return 结果
     */
    public int insertMoldTypeTable(MoldTypeTable moldTypeTable);

    /**
     * 修改工装类别字典
     * 
     * @param moldTypeTable 工装类别字典
     * @return 结果
     */
    public int updateMoldTypeTable(MoldTypeTable moldTypeTable);

    /**
     * 批量删除工装类别字典
     * 
     * @param ids 需要删除的工装类别字典主键集合
     * @return 结果
     */
    public int deleteMoldTypeTableByIds(Long[] ids);

    /**
     * 删除工装类别字典信息
     * 
     * @param id 工装类别字典主键
     * @return 结果
     */
    public int deleteMoldTypeTableById(Long id);

    List<String> listname();
}
