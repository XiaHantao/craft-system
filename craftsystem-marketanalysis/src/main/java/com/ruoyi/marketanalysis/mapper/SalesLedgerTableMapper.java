package com.ruoyi.marketanalysis.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.marketanalysis.domain.SalesLedgerTable;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
/**
 * 销售分析Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-05
 */
public interface SalesLedgerTableMapper
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
     * 删除销售分析
     *
     * @param id 销售分析主键
     * @return 结果
     */
    public int deleteSalesLedgerTableById(Long id);

    /**
     * 批量删除销售分析
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSalesLedgerTableByIds(Long[] ids);
    public int batchInsertSalesLedgerTable(List<SalesLedgerTable> list);
    // 新增统计方法
    List<Map<String, Object>> countSalesByBranch();
    // 按月份统计销量（车型+网点）
    List<Map<String, Object>> countSalesByMonth(
            @Param("vehicleType") String vehicleType,
            @Param("branch") String branch
    );

    // 按网点统计销量（仅车型）
    List<Map<String, Object>> countSalesByBranchForVehicle(
            @Param("vehicleType") String vehicleType
    );
    List<Map<String, Object>> countMonthlySalesComparison(
            @Param("year") Integer year,
            @Param("vehicleType") String vehicleType,
            @Param("branch") String branch);
    List<Map<String, Object>> countSalesByVehicleForBranch(@Param("branch") String branch);
//    List<Map<String, Object>> countSalesByVehicle(@Param("vehicleType") String vehicleType);
List<Map<String, Object>> countSalesByVehicle(
        @Param("vehicleType") String vehicleType,
        @Param("month") String month // 新增月份参数
);
   // 新增数据存在检查方法
    int checkDataExists();
    // 清空表数据
    int cleanTable();
    List<String> getDistinctVehicleTypes();
    List<String> getDistinctBranches();
//    // 获取所有车类
//    List<String> getDistinctCategories();
//
//    // 按月统计销量（按车类）
//    List<Map<String, Object>> countMonthlySalesComparison1(
//            @Param("year") Integer year,
//            @Param("category") String category,
//            @Param("branch") String branch);
// 修改方法参数和返回类型
List<Map<String, Object>> countSalesByMonth1(
        @Param("vehicleCategory") String vehicleCategory,
        @Param("branch") String branch
);

    List<Map<String, Object>> countSalesByBranchForVehicle1(
            @Param("vehicleCategory") String vehicleCategory
    );

    List<Map<String, Object>> countMonthlySalesComparison1(
            @Param("year") Integer year,
            @Param("vehicleCategory") String vehicleCategory,
            @Param("branch") String branch
    );

    // 新增方法
    List<String> getDistinctCategories();
//    List<Map<String, Object>> countSalesByCategory(@Param("vehicleCategory") String vehicleCategory);
List<Map<String, Object>> countSalesByCategory(
        @Param("vehicleCategory") String vehicleCategory,
        @Param("month") String month // 新增月份参数
);
    List<Map<String, Object>> countSalesByVehicleForBranch1(@Param("branch") String branch);
    List<Map<String, Object>> countSalesByBranchForMonth(@Param("month") String month);
}
