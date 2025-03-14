package com.ruoyi.marketanalysis.service;

import java.io.File;
import java.util.List;
import com.ruoyi.marketanalysis.domain.CostpriceTable;

/**
 * 成本售价表Service接口
 * 
 * @author ruoyi
 * @date 2025-03-05
 */
public interface ICostpriceTableService 
{
    /**
     * 查询成本售价表
     * 
     * @param id 成本售价表主键
     * @return 成本售价表
     */
    public CostpriceTable selectCostpriceTableById(Long id);

    /**
     * 查询成本售价表列表
     * 
     * @param costpriceTable 成本售价表
     * @return 成本售价表集合
     */
    public List<CostpriceTable> selectCostpriceTableList(CostpriceTable costpriceTable);

    /**
     * 新增成本售价表
     * 
     * @param costpriceTable 成本售价表
     * @return 结果
     */
    public int insertCostpriceTable(CostpriceTable costpriceTable);

    /**
     * 修改成本售价表
     * 
     * @param costpriceTable 成本售价表
     * @return 结果
     */
    public int updateCostpriceTable(CostpriceTable costpriceTable);

    /**
     * 批量删除成本售价表
     * 
     * @param ids 需要删除的成本售价表主键集合
     * @return 结果
     */
    public int deleteCostpriceTableByIds(Long[] ids);

    /**
     * 删除成本售价表信息
     * 
     * @param id 成本售价表主键
     * @return 结果
     */
    public int deleteCostpriceTableById(Long id);
    // 新增导入方法声明
    String importCostpriceTable(File excelFile, boolean updateSupport);
    boolean checkDataExists();
}
