package com.ruoyi.process.controller;

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
import com.ruoyi.process.domain.ProcessValidationAndSummaryTable;
import com.ruoyi.process.service.IProcessValidationAndSummaryTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺验证与总结Controller
 * 
 * @author Kanna Bush
 * @date 2025-03-10
 */
@RestController
@RequestMapping("/process/processValidationAndSummary")
public class ProcessValidationAndSummaryTableController extends BaseController
{
    @Autowired
    private IProcessValidationAndSummaryTableService processValidationAndSummaryTableService;

    /**
     * 查询工艺验证与总结列表
     */
    @PreAuthorize("@ss.hasPermi('process:processValidationAndSummary:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        startPage();
        List<ProcessValidationAndSummaryTable> list = processValidationAndSummaryTableService.selectProcessValidationAndSummaryTableList(processValidationAndSummaryTable);
        return getDataTable(list);
    }

    /**
     * 查询历史记录
     */
    @PreAuthorize("@ss.hasPermi('process:processValidationAndSummary:list')")
    @GetMapping("/listHistory")
    public TableDataInfo listHistory(ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        startPage();
        List<ProcessValidationAndSummaryTable> list = processValidationAndSummaryTableService.selectHistoryList(processValidationAndSummaryTable);
        return getDataTable(list);
    }

    /**
     * 导出工艺验证与总结列表
     */
    @PreAuthorize("@ss.hasPermi('process:processValidationAndSummary:export')")
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
    @PreAuthorize("@ss.hasPermi('process:processValidationAndSummary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processValidationAndSummaryTableService.selectProcessValidationAndSummaryTableById(id));
    }

    /**
     * 新增工艺验证与总结
     */
    @PreAuthorize("@ss.hasPermi('process:processValidationAndSummary:add')")
    @Log(title = "工艺验证与总结", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        return success(processValidationAndSummaryTableService.insertProcessValidationAndSummaryTable(processValidationAndSummaryTable));
    }

    /**
     * 修改工艺验证与总结
     */
    @PreAuthorize("@ss.hasPermi('process:processValidationAndSummary:edit')")
    @Log(title = "工艺验证与总结", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessValidationAndSummaryTable processValidationAndSummaryTable)
    {
        return toAjax(processValidationAndSummaryTableService.updateProcessValidationAndSummaryTable(processValidationAndSummaryTable));
    }

    /**
     * 删除工艺验证与总结
     */
    @PreAuthorize("@ss.hasPermi('process:processValidationAndSummary:remove')")
    @Log(title = "工艺验证与总结", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processValidationAndSummaryTableService.deleteProcessValidationAndSummaryTableByIds(ids));
    }

    /**
     * 查询最新的一条记录
     */
    @PreAuthorize("@ss.hasPermi('process:processValidationAndSummary:query')")
    @GetMapping("/latest")
    public AjaxResult getLatestRecord()
    {
        ProcessValidationAndSummaryTable latestRecord = processValidationAndSummaryTableService.selectLatestRecord();
        return success(latestRecord);
    }



    @GetMapping(value = "/{tableName}")
    public AjaxResult getLatestRecord(@PathVariable("tableName") String tableName) {
        Map<String, Object> latestRecord = processValidationAndSummaryTableService.selectLatestRecord02(tableName);
        return success(latestRecord);
    }


}
