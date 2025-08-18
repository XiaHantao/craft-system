package com.ruoyi.marketanalysis.controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.marketanalysis.domain.CostpriceTable;
import com.ruoyi.marketanalysis.service.ICostpriceTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 成本售价表Controller
 * 
 * @author ruoyi
 * @date 2025-03-05
 */
@RestController
@RequestMapping("/marketanalysis/costprice")
public class CostpriceTableController extends BaseController
{
    @Autowired
    private ICostpriceTableService costpriceTableService;

    /**
     * 查询成本售价表列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:costprice:list')")
    @GetMapping("/list")
    public TableDataInfo list(CostpriceTable costpriceTable)
    {
        startPage();
        List<CostpriceTable> list = costpriceTableService.selectCostpriceTableList(costpriceTable);
        return getDataTable(list);
    }

    /**
     * 导出成本售价表列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:costprice:export')")
    @Log(title = "成本售价表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CostpriceTable costpriceTable)
    {
        List<CostpriceTable> list = costpriceTableService.selectCostpriceTableList(costpriceTable);
        ExcelUtil<CostpriceTable> util = new ExcelUtil<CostpriceTable>(CostpriceTable.class);
        util.exportExcel(response, list, "成本售价表数据");
    }

    /**
     * 获取成本售价表详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:costprice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(costpriceTableService.selectCostpriceTableById(id));
    }

    /**
     * 新增成本售价表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:costprice:add')")
    @Log(title = "成本售价表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CostpriceTable costpriceTable)
    {
        return toAjax(costpriceTableService.insertCostpriceTable(costpriceTable));
    }

    /**
     * 修改成本售价表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:costprice:edit')")
    @Log(title = "成本售价表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CostpriceTable costpriceTable)
    {
        return toAjax(costpriceTableService.updateCostpriceTable(costpriceTable));
    }

    /**
     * 删除成本售价表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:costprice:remove')")
    @Log(title = "成本售价表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(costpriceTableService.deleteCostpriceTableByIds(ids));
    }
    // 新增导入方法
//    @PreAuthorize("@ss.hasPermi('marketanalysis:costprice:import')")
    @Log(title = "成本售价表", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = costpriceTableService.importCostpriceTable(excelFile, updateSupport);
        return AjaxResult.success(message);
    }
    @PreAuthorize("@ss.hasPermi('marketanalysis:costprice:list')")
    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        boolean exists = costpriceTableService.checkDataExists();
        return success(exists);
    }
}
