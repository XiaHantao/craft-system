package com.ruoyi.marketanalysis.service.impl;

import java.io.File;
import java.time.Year;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.SalesLedgerTableMapper;
import com.ruoyi.marketanalysis.domain.SalesLedgerTable;
import com.ruoyi.marketanalysis.service.ISalesLedgerTableService;
import com.ruoyi.marketanalysis.utils.ExcelUtils;
/**
 * 销售分析Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-05
 */
@Service
public class SalesLedgerTableServiceImpl implements ISalesLedgerTableService
{
    @Autowired
    private SalesLedgerTableMapper salesLedgerTableMapper;

    /**
     * 查询销售分析
     *
     * @param id 销售分析主键
     * @return 销售分析
     */
    @Override
    public SalesLedgerTable selectSalesLedgerTableById(Long id)
    {
        return salesLedgerTableMapper.selectSalesLedgerTableById(id);
    }

    /**
     * 查询销售分析列表
     *
     * @param salesLedgerTable 销售分析
     * @return 销售分析
     */
    @Override
    public List<SalesLedgerTable> selectSalesLedgerTableList(SalesLedgerTable salesLedgerTable)
    {
        return salesLedgerTableMapper.selectSalesLedgerTableList(salesLedgerTable);
    }

    /**
     * 新增销售分析
     *
     * @param salesLedgerTable 销售分析
     * @return 结果
     */
    @Override
    public int insertSalesLedgerTable(SalesLedgerTable salesLedgerTable)
    {
        return salesLedgerTableMapper.insertSalesLedgerTable(salesLedgerTable);
    }

    /**
     * 修改销售分析
     *
     * @param salesLedgerTable 销售分析
     * @return 结果
     */
    @Override
    public int updateSalesLedgerTable(SalesLedgerTable salesLedgerTable)
    {
        return salesLedgerTableMapper.updateSalesLedgerTable(salesLedgerTable);
    }

    /**
     * 批量删除销售分析
     *
     * @param ids 需要删除的销售分析主键
     * @return 结果
     */
    @Override
    public int deleteSalesLedgerTableByIds(Long[] ids)
    {
        return salesLedgerTableMapper.deleteSalesLedgerTableByIds(ids);
    }

    /**
     * 删除销售分析信息
     *
     * @param id 销售分析主键
     * @return 结果
     */
    @Override
    public int deleteSalesLedgerTableById(Long id)
    {
        return salesLedgerTableMapper.deleteSalesLedgerTableById(id);
    }
    @Override
    public String importSalesLedgerTable(File excelFile, boolean updateSupport) {
        try {
            List<SalesLedgerTable> salesLedgerTableList = ExcelUtils.parseExcel(excelFile);

            // 删除空数据检查，允许空数据继续流程

            // 如果允许更新且存在数据，则清空表
            if (updateSupport && checkDataExists()) {
                salesLedgerTableMapper.cleanTable();
            }

            // 批量插入数据（即使列表为空）
            salesLedgerTableMapper.batchInsertSalesLedgerTable(salesLedgerTableList);
            return "成功导入 " + salesLedgerTableList.size() + " 条数据";

        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }
    @Override
    public boolean checkDataExists() {
        return salesLedgerTableMapper.checkDataExists() > 0;
    }


    @Override
    public List<Map<String, Object>> countSalesByBranch() {
        return salesLedgerTableMapper.countSalesByBranch();
    }
    @Override
    public List<Map<String, Object>> countSalesByMonth(String vehicleType, String branch) {
        return salesLedgerTableMapper.countSalesByMonth(vehicleType, branch);
    }

    @Override
    public List<Map<String, Object>> countSalesByBranchForVehicle(String vehicleType) {
        return salesLedgerTableMapper.countSalesByBranchForVehicle(vehicleType);
    }
    @Override
    public List<Map<String, Object>> countMonthlySalesComparison(Integer year, String vehicleType, String branch) {
        if (year == null) year = Year.now().getValue();
        return salesLedgerTableMapper.countMonthlySalesComparison(year, vehicleType, branch);
    }
    @Override
    public List<Map<String, Object>> countSalesByVehicle(String vehicleType) {
        return salesLedgerTableMapper.countSalesByVehicle(vehicleType); // 传递参数给Mapper
    }
    @Override
    public List<Map<String, Object>> countSalesByVehicleForBranch(String branch) {
        return salesLedgerTableMapper.countSalesByVehicleForBranch(branch);
    }
    @Override
    public List<String> getVehicleTypes() {
        return salesLedgerTableMapper.getDistinctVehicleTypes();
    }

    @Override
    public List<String> getBranches() {
        return salesLedgerTableMapper.getDistinctBranches();
    }
//    @Override
//    public List<String> getDistinctCategories() {
//        return salesLedgerTableMapper.getDistinctCategories();
//    }
//
//    @Override
//    public List<Map<String, Object>> countMonthlySalesComparison1(Integer year, String category, String branch) {
//        return salesLedgerTableMapper.countMonthlySalesComparison(year, category, branch);
//    }
@Override
public List<Map<String, Object>> countSalesByMonth1(String vehicleCategory, String branch) {
    return salesLedgerTableMapper.countSalesByMonth1(vehicleCategory, branch);
}

    @Override
    public List<Map<String, Object>> countSalesByBranchForVehicle1(String vehicleCategory) {
        return salesLedgerTableMapper.countSalesByBranchForVehicle1(vehicleCategory);
    }

    @Override
    public List<Map<String, Object>> countMonthlySalesComparison1(Integer year, String vehicleCategory, String branch) {
        if (year == null) year = Year.now().getValue();
        return salesLedgerTableMapper.countMonthlySalesComparison1(year, vehicleCategory, branch);
    }

    @Override
    public List<String> getDistinctCategories() {
        return salesLedgerTableMapper.getDistinctCategories();
    }
    @Override
    public List<Map<String, Object>> countSalesByCategory(String vehicleCategory) {
        return salesLedgerTableMapper.countSalesByCategory(vehicleCategory);
    }
    @Override
    public List<Map<String, Object>> countSalesByVehicleForBranch1(String branch) {
        return salesLedgerTableMapper.countSalesByVehicleForBranch1(branch);
    }
}
