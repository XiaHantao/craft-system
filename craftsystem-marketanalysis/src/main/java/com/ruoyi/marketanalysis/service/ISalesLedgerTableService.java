package com.ruoyi.marketanalysis.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.ruoyi.marketanalysis.domain.SalesLedgerTable;

/**
 * 销售分析Service接口
 *
 * @author ruoyi
 * @date 2025-03-05
 */
public interface ISalesLedgerTableService
{
    /**
     * 查询销售分析
     *
     * @param id 销售分析主键
     * @return 销售分析
     */
    public SalesLedgerTable selectSalesLedgerTableById(Long id);

    /**
     * 查询销售分析列表
     *
     * @param salesLedgerTable 销售分析
     * @return 销售分析集合
     */
    public List<SalesLedgerTable> selectSalesLedgerTableList(SalesLedgerTable salesLedgerTable);

    /**
     * 新增销售分析
     *
     * @param salesLedgerTable 销售分析
     * @return 结果
     */
    public int insertSalesLedgerTable(SalesLedgerTable salesLedgerTable);

    /**
     * 修改销售分析
     *
     * @param salesLedgerTable 销售分析
     * @return 结果
     */
    public int updateSalesLedgerTable(SalesLedgerTable salesLedgerTable);

    /**
     * 批量删除销售分析
     *
     * @param ids 需要删除的销售分析主键集合
     * @return 结果
     */
    public int deleteSalesLedgerTableByIds(Long[] ids);

    /**
     * 删除销售分析信息
     *
     * @param id 销售分析主键
     * @return 结果
     */
    public int deleteSalesLedgerTableById(Long id);
    String importSalesLedgerTable(File excelFile, boolean updateSupport);
    // 新增接口方法
    List<Map<String, Object>> countSalesByBranch();
    List<Map<String, Object>> countSalesByMonth(String vehicleType, String branch);
    List<Map<String, Object>> countSalesByBranchForVehicle(String vehicleType);
    List<Map<String, Object>> countSalesByVehicleForBranch(String branch);
    List<Map<String, Object>> countMonthlySalesComparison(Integer year, String vehicleType, String branch);
    // ISalesLedgerTableService.java 修改接口
    List<Map<String, Object>> countSalesByVehicle(String vehicleType); // 添加参数
    boolean checkDataExists();
    List<String> getVehicleTypes();
    List<String> getBranches();
//    // 获取所有车类
//    List<String> getDistinctCategories();
//
//    // 按月统计销量（按车类）
//    List<Map<String, Object>> countMonthlySalesComparison1(Integer year, String category, String branch);
// 修改方法签名
    List<Map<String, Object>> countSalesByMonth1(String vehicleCategory, String branch);
    List<Map<String, Object>> countSalesByBranchForVehicle1(String vehicleCategory);
    List<Map<String, Object>> countMonthlySalesComparison1(Integer year, String vehicleCategory, String branch);
    List<Map<String, Object>> countSalesByVehicleForBranch1(String branch);
    // 新增方法
    List<String> getDistinctCategories();
    List<Map<String, Object>> countSalesByCategory(String vehicleCategory);

}
