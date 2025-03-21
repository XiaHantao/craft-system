package com.ruoyi.workClothes.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.workClothes.domain.MaintenanceRecordTable;
import com.ruoyi.workClothes.service.IMaintenanceRecordTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 维修记录Controller
 * 
 * @author ruoyi
 * @date 2025-02-28
 */
@RestController
@RequestMapping("/ToolingModule/maintenanceRecord")
public class MaintenanceRecordTableController extends BaseController
{
    @Autowired
    private IMaintenanceRecordTableService maintenanceRecordTableService;

    /**
     * 查询维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:maintenanceRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaintenanceRecordTable maintenanceRecordTable)
    {
        startPage();
        List<MaintenanceRecordTable> list = maintenanceRecordTableService.selectMaintenanceRecordTableList(maintenanceRecordTable);
        return getDataTable(list);
    }

    /**
     * 新查询维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:maintenanceRecord:list')")
    @GetMapping("/listnew")
    public TableDataInfo listnew(@RequestParam(required = false) String toolNumber , MaintenanceRecordTable maintenanceRecordTable)
    {
        startPage();
        List<MaintenanceRecordTable> list = maintenanceRecordTableService.selectMaintenanceRecordTableListbynumber(maintenanceRecordTable ,toolNumber);
        return getDataTable(list);
    }

    /**
     * 导出维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:maintenanceRecord:export')")
    @Log(title = "维修记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaintenanceRecordTable maintenanceRecordTable)
    {
        List<MaintenanceRecordTable> list = maintenanceRecordTableService.selectMaintenanceRecordTableList(maintenanceRecordTable);
        ExcelUtil<MaintenanceRecordTable> util = new ExcelUtil<MaintenanceRecordTable>(MaintenanceRecordTable.class);
        util.exportExcel(response, list, "维修记录数据");
    }

    /**
     * 获取维修记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:maintenanceRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(maintenanceRecordTableService.selectMaintenanceRecordTableById(id));
    }

    /**
     * 新增维修记录
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:maintenanceRecord:add')")
    @Log(title = "维修记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaintenanceRecordTable maintenanceRecordTable)
    {
        return toAjax(maintenanceRecordTableService.insertMaintenanceRecordTable(maintenanceRecordTable));
    }

    /**
     * 修改维修记录
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:maintenanceRecord:edit')")
    @Log(title = "维修记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaintenanceRecordTable maintenanceRecordTable)
    {
        return toAjax(maintenanceRecordTableService.updateMaintenanceRecordTable(maintenanceRecordTable));
    }

    /**
     * 删除维修记录
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:maintenanceRecord:remove')")
    @Log(title = "维修记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(maintenanceRecordTableService.deleteMaintenanceRecordTableByIds(ids));
    }
}
