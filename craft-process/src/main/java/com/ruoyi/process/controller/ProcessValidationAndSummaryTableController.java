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
import com.ruoyi.process.domain.ProcessValidationAndSummaryTable;
import com.ruoyi.process.service.IProcessValidationAndSummaryTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺验证与总结Controller
 * 
 * @author Kanna Bush
 * @date 2025-01-20
 */
@RestController
@RequestMapping("/process/ProcessValidationAndSummary")
public class ProcessValidationAndSummaryTableController extends BaseController
{
    @Autowired
    private IProcessValidationAndSummaryTableService processValidationAndSummaryTableService;

    /**
     * 查询工艺验证与总结列表
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessValidationAndSummary:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        startPage();
        List<ProcessValidationAndSummaryTable> list = processValidationAndSummaryTableService.selectProcessValidationAndSummaryTableList(processValidationAndSummaryTable);
        return getDataTable(list);
    }

    /**
     * 导出工艺验证与总结列表
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessValidationAndSummary:export')")
    @Log(title = "工艺验证与总结", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        List<ProcessValidationAndSummaryTable> list = processValidationAndSummaryTableService.selectProcessValidationAndSummaryTableList(processValidationAndSummaryTable);
        ExcelUtil<ProcessValidationAndSummaryTable> util = new ExcelUtil<ProcessValidationAndSummaryTable>(ProcessValidationAndSummaryTable.class);
        util.exportExcel(response, list, "工艺验证与总结数据");
    }

    /**
     * 获取工艺验证与总结详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessValidationAndSummary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processValidationAndSummaryTableService.selectProcessValidationAndSummaryTableById(id));
    }

    /**
     * 新增工艺验证与总结
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessValidationAndSummary:add')")
    @Log(title = "工艺验证与总结", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        return toAjax(processValidationAndSummaryTableService.insertProcessValidationAndSummaryTable(processValidationAndSummaryTable));
    }

    /**
     * 修改工艺验证与总结
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessValidationAndSummary:edit')")
    @Log(title = "工艺验证与总结", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        return toAjax(processValidationAndSummaryTableService.updateProcessValidationAndSummaryTable(processValidationAndSummaryTable));
    }

    /**
     * 删除工艺验证与总结
     */
    @PreAuthorize("@ss.hasPermi('process:ProcessValidationAndSummary:remove')")
    @Log(title = "工艺验证与总结", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processValidationAndSummaryTableService.deleteProcessValidationAndSummaryTableByIds(ids));
    }
}
