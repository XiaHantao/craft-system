package com.ruoyi.process.controller;

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
import com.ruoyi.process.domain.ProcessOptimizationAndImprovementTable;
import com.ruoyi.process.service.IProcessOptimizationAndImprovementTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺优化与改进Controller
 * 
 * @author Kanna Bush
 * @date 2025-01-20
 */
@RestController
@RequestMapping("/process/ProcessOptimizationAndImprovement")
public class ProcessOptimizationAndImprovementTableController extends BaseController
{
    @Autowired
    private IProcessOptimizationAndImprovementTableService processOptimizationAndImprovementTableService;

    /**
     * 查询工艺优化与改进列表
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessOptimizationAndImprovement:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable)
    {
        startPage();
        List<ProcessOptimizationAndImprovementTable> list = processOptimizationAndImprovementTableService.selectProcessOptimizationAndImprovementTableList(processOptimizationAndImprovementTable);
        return getDataTable(list);
    }

    /**
     * 导出工艺优化与改进列表
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessOptimizationAndImprovement:export')")
    @Log(title = "工艺优化与改进", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable)
    {
        List<ProcessOptimizationAndImprovementTable> list = processOptimizationAndImprovementTableService.selectProcessOptimizationAndImprovementTableList(processOptimizationAndImprovementTable);
        ExcelUtil<ProcessOptimizationAndImprovementTable> util = new ExcelUtil<ProcessOptimizationAndImprovementTable>(ProcessOptimizationAndImprovementTable.class);
        util.exportExcel(response, list, "工艺优化与改进数据");
    }

    /**
     * 获取工艺优化与改进详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessOptimizationAndImprovement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processOptimizationAndImprovementTableService.selectProcessOptimizationAndImprovementTableById(id));
    }

    /**
     * 新增工艺优化与改进
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessOptimizationAndImprovement:add')")
    @Log(title = "工艺优化与改进", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable)
    {
        return toAjax(processOptimizationAndImprovementTableService.insertProcessOptimizationAndImprovementTable(processOptimizationAndImprovementTable));
    }

    /**
     * 修改工艺优化与改进
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessOptimizationAndImprovement:edit')")
    @Log(title = "工艺优化与改进", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessOptimizationAndImprovementTable processOptimizationAndImprovementTable)
    {
        return toAjax(processOptimizationAndImprovementTableService.updateProcessOptimizationAndImprovementTable(processOptimizationAndImprovementTable));
    }

    /**
     * 删除工艺优化与改进
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessOptimizationAndImprovement:remove')")
    @Log(title = "工艺优化与改进", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processOptimizationAndImprovementTableService.deleteProcessOptimizationAndImprovementTableByIds(ids));
    }
}
