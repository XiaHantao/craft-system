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
import com.ruoyi.process.domain.ProcessSiteManagementTable;
import com.ruoyi.process.service.IProcessSiteManagementTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺现场管理Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-21
 */
@RestController
@RequestMapping("/process/processSiteManagement")
public class ProcessSiteManagementTableController extends BaseController
{
    @Autowired
    private IProcessSiteManagementTableService processSiteManagementTableService;

    /**
     * 查询工艺现场管理列表
     */
    @PreAuthorize("@ss.hasPermi('process:processSiteManagement:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessSiteManagementTable processSiteManagementTable)
    {
        startPage();
        List<ProcessSiteManagementTable> list = processSiteManagementTableService.selectProcessSiteManagementTableList(processSiteManagementTable);
        return getDataTable(list);
    }

    /**
     * 导出工艺现场管理列表
     */
    @PreAuthorize("@ss.hasPermi('process:processSiteManagement:export')")
    @Log(title = "工艺现场管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessSiteManagementTable processSiteManagementTable)
    {
        List<ProcessSiteManagementTable> list = processSiteManagementTableService.selectProcessSiteManagementTableList(processSiteManagementTable);
        ExcelUtil<ProcessSiteManagementTable> util = new ExcelUtil<ProcessSiteManagementTable>(ProcessSiteManagementTable.class);
        util.exportExcel(response, list, "工艺现场管理数据");
    }

    /**
     * 获取工艺现场管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processSiteManagement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processSiteManagementTableService.selectProcessSiteManagementTableById(id));
    }

    /**
     * 新增工艺现场管理
     */
    @PreAuthorize("@ss.hasPermi('process:processSiteManagement:add')")
    @Log(title = "工艺现场管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessSiteManagementTable processSiteManagementTable)
    {
        return toAjax(processSiteManagementTableService.insertProcessSiteManagementTable(processSiteManagementTable));
    }

    /**
     * 修改工艺现场管理
     */
    @PreAuthorize("@ss.hasPermi('process:processSiteManagement:edit')")
    @Log(title = "工艺现场管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessSiteManagementTable processSiteManagementTable)
    {
        return toAjax(processSiteManagementTableService.updateProcessSiteManagementTable(processSiteManagementTable));
    }

    /**
     * 删除工艺现场管理
     */
    @PreAuthorize("@ss.hasPermi('process:processSiteManagement:remove')")
    @Log(title = "工艺现场管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processSiteManagementTableService.deleteProcessSiteManagementTableByIds(ids));
    }
}
