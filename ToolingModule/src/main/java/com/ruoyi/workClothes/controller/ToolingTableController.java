package com.ruoyi.workClothes.controller;

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
import com.ruoyi.workClothes.domain.ToolingTable;
import com.ruoyi.workClothes.service.IToolingTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工装详细Controller
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
@RestController
@RequestMapping("/ToolingModule/toolingDetail")
public class ToolingTableController extends BaseController
{
    @Autowired
    private IToolingTableService toolingTableService;

    /**
     * 查询工装详细列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:toolingDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(ToolingTable toolingTable)
    {
        startPage();
        List<ToolingTable> list = toolingTableService.selectToolingTableList(toolingTable);
        return getDataTable(list);
    }

    /**
     * 导出工装详细列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:toolingDetail:export')")
    @Log(title = "工装详细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ToolingTable toolingTable)
    {
        List<ToolingTable> list = toolingTableService.selectToolingTableList(toolingTable);
        ExcelUtil<ToolingTable> util = new ExcelUtil<ToolingTable>(ToolingTable.class);
        util.exportExcel(response, list, "工装详细数据");
    }

    /**
     * 获取工装详细详细信息
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:toolingDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(toolingTableService.selectToolingTableById(id));
    }

    /**
     * 新增工装详细
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:toolingDetail:add')")
    @Log(title = "工装详细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ToolingTable toolingTable)
    {
        return toAjax(toolingTableService.insertToolingTable(toolingTable));
    }

    /**
     * 修改工装详细
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:toolingDetail:edit')")
    @Log(title = "工装详细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ToolingTable toolingTable)
    {
        return toAjax(toolingTableService.updateToolingTable(toolingTable));
    }

    /**
     * 删除工装详细
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:toolingDetail:remove')")
    @Log(title = "工装详细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(toolingTableService.deleteToolingTableByIds(ids));
    }
}
