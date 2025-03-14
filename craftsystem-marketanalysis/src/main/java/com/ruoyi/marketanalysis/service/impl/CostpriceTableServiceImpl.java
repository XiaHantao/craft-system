package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.CostpriceTableMapper;
import com.ruoyi.marketanalysis.domain.CostpriceTable;
import com.ruoyi.marketanalysis.service.ICostpriceTableService;
import com.ruoyi.marketanalysis.utils.ExcelUtils;
/**
 * 成本售价表Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-05
 */
@Service
public class CostpriceTableServiceImpl implements ICostpriceTableService 
{
    @Autowired
    private CostpriceTableMapper costpriceTableMapper;

    /**
     * 查询成本售价表
     * 
     * @param id 成本售价表主键
     * @return 成本售价表
     */
    @Override
    public CostpriceTable selectCostpriceTableById(Long id)
    {
        return costpriceTableMapper.selectCostpriceTableById(id);
    }

    /**
     * 查询成本售价表列表
     * 
     * @param costpriceTable 成本售价表
     * @return 成本售价表
     */
    @Override
    public List<CostpriceTable> selectCostpriceTableList(CostpriceTable costpriceTable)
    {
        return costpriceTableMapper.selectCostpriceTableList(costpriceTable);
    }

    /**
     * 新增成本售价表
     * 
     * @param costpriceTable 成本售价表
     * @return 结果
     */
    @Override
    public int insertCostpriceTable(CostpriceTable costpriceTable)
    {
        return costpriceTableMapper.insertCostpriceTable(costpriceTable);
    }

    /**
     * 修改成本售价表
     * 
     * @param costpriceTable 成本售价表
     * @return 结果
     */
    @Override
    public int updateCostpriceTable(CostpriceTable costpriceTable)
    {
        return costpriceTableMapper.updateCostpriceTable(costpriceTable);
    }

    /**
     * 批量删除成本售价表
     * 
     * @param ids 需要删除的成本售价表主键
     * @return 结果
     */
    @Override
    public int deleteCostpriceTableByIds(Long[] ids)
    {
        return costpriceTableMapper.deleteCostpriceTableByIds(ids);
    }

    /**
     * 删除成本售价表信息
     * 
     * @param id 成本售价表主键
     * @return 结果
     */
    @Override
    public int deleteCostpriceTableById(Long id)
    {
        return costpriceTableMapper.deleteCostpriceTableById(id);
    }
    @Override
    public String importCostpriceTable(File excelFile, boolean updateSupport) {
        try {
            List<CostpriceTable> list = ExcelUtils.parseCostpriceExcel(excelFile);

            if (updateSupport && !list.isEmpty()) {
                costpriceTableMapper.cleanTable();
            }

            if (!list.isEmpty()) {
                costpriceTableMapper.batchInsertCostpriceTable(list);
            }
            return "成功导入 " + list.size() + " 条数据";
        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }

    // 新增检查方法
    @Override
    public boolean checkDataExists() {
        return costpriceTableMapper.checkDataExists() > 0;
    }
}
