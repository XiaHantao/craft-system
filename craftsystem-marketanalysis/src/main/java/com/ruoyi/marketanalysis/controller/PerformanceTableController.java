package com.ruoyi.marketanalysis.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.marketanalysis.domain.PerformanceTable;
import com.ruoyi.marketanalysis.service.IPerformanceTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 性能表Controller
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
@RestController
@RequestMapping("/marketanalysis/performance")
public class PerformanceTableController extends BaseController
{
    @Autowired
    private IPerformanceTableService performanceTableService;

    /**
     * 查询性能表列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:performance:list')")
    @GetMapping("/list")
    public TableDataInfo list(PerformanceTable performanceTable)
    {
        startPage();
        List<PerformanceTable> list = performanceTableService.selectPerformanceTableList(performanceTable);
        return getDataTable(list);
    }

    /**
     * 导出性能表列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:performance:export')")
    @Log(title = "性能表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PerformanceTable performanceTable)
    {
        List<PerformanceTable> list = performanceTableService.selectPerformanceTableList(performanceTable);
        ExcelUtil<PerformanceTable> util = new ExcelUtil<PerformanceTable>(PerformanceTable.class);
        util.exportExcel(response, list, "性能表数据");
    }

    /**
     * 获取性能表详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:performance:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(performanceTableService.selectPerformanceTableById(id));
    }

    /**
     * 新增性能表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:performance:add')")
    @Log(title = "性能表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PerformanceTable performanceTable)
    {
        return toAjax(performanceTableService.insertPerformanceTable(performanceTable));
    }

    /**
     * 修改性能表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:performance:edit')")
    @Log(title = "性能表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PerformanceTable performanceTable)
    {
        return toAjax(performanceTableService.updatePerformanceTable(performanceTable));
    }

    /**
     * 删除性能表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:performance:remove')")
    @Log(title = "性能表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(performanceTableService.deletePerformanceTableByIds(ids));
    }
}
