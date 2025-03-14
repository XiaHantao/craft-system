package com.ruoyi.marketanalysis.controller;

import java.io.File;
import java.time.Year;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.marketanalysis.domain.SalesLedgerTable;
import com.ruoyi.marketanalysis.service.ISalesLedgerTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 销售分析Controller
 *
 * @author ruoyi
 * @date 2025-03-05
 */
@RestController
@RequestMapping("/marketanalysis/saleanalysis")
public class SalesLedgerTableController extends BaseController
{
    @Autowired
    private ISalesLedgerTableService salesLedgerTableService;

    /**
     * 查询销售分析列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesLedgerTable salesLedgerTable)
    {
        startPage();
        List<SalesLedgerTable> list = salesLedgerTableService.selectSalesLedgerTableList(salesLedgerTable);
        return getDataTable(list);
    }

    /**
     * 导出销售分析列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:export')")
    @Log(title = "销售分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SalesLedgerTable salesLedgerTable)
    {
        List<SalesLedgerTable> list = salesLedgerTableService.selectSalesLedgerTableList(salesLedgerTable);
        ExcelUtil<SalesLedgerTable> util = new ExcelUtil<SalesLedgerTable>(SalesLedgerTable.class);
        util.exportExcel(response, list, "销售分析数据");
    }

    /**
     * 获取销售分析详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(salesLedgerTableService.selectSalesLedgerTableById(id));
    }

    /**
     * 新增销售分析
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:add')")
    @Log(title = "销售分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SalesLedgerTable salesLedgerTable)
    {
        return toAjax(salesLedgerTableService.insertSalesLedgerTable(salesLedgerTable));
    }

    /**
     * 修改销售分析
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:edit')")
    @Log(title = "销售分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SalesLedgerTable salesLedgerTable)
    {
        return toAjax(salesLedgerTableService.updateSalesLedgerTable(salesLedgerTable));
    }

    /**
     * 删除销售分析
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:remove')")
    @Log(title = "销售分析", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(salesLedgerTableService.deleteSalesLedgerTableByIds(ids));
    }
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport // 添加默认值
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = salesLedgerTableService.importSalesLedgerTable(excelFile, updateSupport);
        return AjaxResult.success(message);
    }
    // 新增检查数据存在接口
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:list')")
    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        boolean exists = salesLedgerTableService.checkDataExists();
        return success(exists);
    }
    // 新增统计接口
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:list')")
    @GetMapping("/countByBranch")
    public AjaxResult countByBranch() {
        List<Map<String, Object>> data = salesLedgerTableService.countSalesByBranch();
        return success(data);
    }
    // 按月份统计销量
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:list')")
    @GetMapping("/countByMonth")
    public AjaxResult countByMonth(
            @RequestParam String vehicleType,
            @RequestParam String branch
    ) {
        List<Map<String, Object>> data = salesLedgerTableService.countSalesByMonth(vehicleType, branch);
        return success(data);
    }

    // 按网点统计销量（特定车型）
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:list')")
    @GetMapping("/countByBranchForVehicle")
    public AjaxResult countByBranchForVehicle(@RequestParam String vehicleType) {
        List<Map<String, Object>> data = salesLedgerTableService.countSalesByBranchForVehicle(vehicleType);
        return success(data);
    }
    // SalesLedgerTableController.java 新增接口
    @GetMapping("/countByVehicleForBranch")
    public AjaxResult countByVehicleForBranch(@RequestParam String branch) {
        List<Map<String, Object>> data = salesLedgerTableService.countSalesByVehicleForBranch(branch);
        return success(data);
    }
    // SalesLedgerTableController.java 修改countByVehicle接口
    @PreAuthorize("@ss.hasPermi('marketanalysis:saleanalysis:list')")
    @GetMapping("/countByVehicle")
    public TableDataInfo countByVehicle(@RequestParam(required = false) String vehicleType) { // 添加参数接收
        startPage();
        List<Map<String, Object>> data = salesLedgerTableService.countSalesByVehicle(vehicleType); // 修改服务层方法
        return getDataTable(data);
    }
    // 同比环比柱状图
    @GetMapping("/countMonthlyComparison")
    public AjaxResult countMonthlyComparison(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String vehicleType,
            @RequestParam(required = false) String branch) {

        if (year == null) year = Year.now().getValue();
        List<Map<String, Object>> data = salesLedgerTableService.countMonthlySalesComparison(year, vehicleType, branch);
        return success(data);
    }
    // 获取所有车型列表
    @GetMapping("/vehicleTypes")
    public AjaxResult getVehicleTypes() {
        List<String> data = salesLedgerTableService.getVehicleTypes();
        return success(data);
    }

    // 获取所有网点列表
    @GetMapping("/branches")
    public AjaxResult getBranches() {
        List<String> data = salesLedgerTableService.getBranches();
        return success(data);
    }
}
