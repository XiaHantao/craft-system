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
import com.ruoyi.process.domain.ProcessReviewTable;
import com.ruoyi.process.service.IProcessReviewTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺性审查Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/process/processReviewTable")
public class ProcessReviewTableController extends BaseController
{
    @Autowired
    private IProcessReviewTableService processReviewTableService;

    /**
     * 查询工艺性审查列表
     */
    @PreAuthorize("@ss.hasPermi('process:processReviewTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessReviewTable processReviewTable)
    {
        startPage();
        List<ProcessReviewTable> list = processReviewTableService.selectProcessReviewTableList(processReviewTable);
        return getDataTable(list);
    }

    /**
     * 导出工艺性审查列表
     */
    @PreAuthorize("@ss.hasPermi('process:processReviewTable:export')")
    @Log(title = "工艺性审查", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessReviewTable processReviewTable)
    {
        List<ProcessReviewTable> list = processReviewTableService.selectProcessReviewTableList(processReviewTable);
        ExcelUtil<ProcessReviewTable> util = new ExcelUtil<ProcessReviewTable>(ProcessReviewTable.class);
        util.exportExcel(response, list, "工艺性审查数据");
    }

    /**
     * 获取工艺性审查详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processReviewTable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processReviewTableService.selectProcessReviewTableById(id));
    }

    /**
     * 新增工艺性审查
     */
    @PreAuthorize("@ss.hasPermi('process:processReviewTable:add')")
    @Log(title = "工艺性审查", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessReviewTable processReviewTable)
    {
        return toAjax(processReviewTableService.insertProcessReviewTable(processReviewTable));
    }

    /**
     * 修改工艺性审查
     */
    @PreAuthorize("@ss.hasPermi('process:processReviewTable:edit')")
    @Log(title = "工艺性审查", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessReviewTable processReviewTable)
    {
        return toAjax(processReviewTableService.updateProcessReviewTable(processReviewTable));
    }

    /**
     * 删除工艺性审查
     */
    @PreAuthorize("@ss.hasPermi('process:processReviewTable:remove')")
    @Log(title = "工艺性审查", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processReviewTableService.deleteProcessReviewTableByIds(ids));
    }
}
