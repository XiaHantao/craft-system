package com.ruoyi.workClothes.controller;

import java.util.List;
import java.util.Map;
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
import com.ruoyi.workClothes.domain.ToolingLedgerTable;
import com.ruoyi.workClothes.service.IToolingLedgerTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工装台账Controller
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
@RestController
@RequestMapping("/ToolingModule/WorkClothes")
public class ToolingLedgerTableController extends BaseController
{
    @Autowired
    private IToolingLedgerTableService toolingLedgerTableService;

    /**
     * 查询工装台账列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:WorkClothes:list')")
    @GetMapping("/list")
    public TableDataInfo list(ToolingLedgerTable toolingLedgerTable)
    {
        startPage();
        List<ToolingLedgerTable> list = toolingLedgerTableService.selectToolingLedgerTableList(toolingLedgerTable);
        return getDataTable(list);
    }

    /**
     * 导出工装台账列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:WorkClothes:export')")
    @Log(title = "工装台账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ToolingLedgerTable toolingLedgerTable)
    {
        List<ToolingLedgerTable> list = toolingLedgerTableService.selectToolingLedgerTableList(toolingLedgerTable);
        ExcelUtil<ToolingLedgerTable> util = new ExcelUtil<ToolingLedgerTable>(ToolingLedgerTable.class);
        util.exportExcel(response, list, "工装台账数据");
    }

    /**
     * 获取工装台账详细信息
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:WorkClothes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(toolingLedgerTableService.selectToolingLedgerTableById(id));
    }

    /**
     * 新增工装台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:WorkClothes:add')")
    @Log(title = "工装台账", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ToolingLedgerTable toolingLedgerTable)
    {
        return toAjax(toolingLedgerTableService.insertToolingLedgerTable(toolingLedgerTable));
    }

    /**
     * 修改工装台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:WorkClothes:edit')")
    @Log(title = "工装台账", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ToolingLedgerTable toolingLedgerTable)
    {
        return toAjax(toolingLedgerTableService.updateToolingLedgerTable(toolingLedgerTable));
    }

    /**
     * 修改工装台账工艺及物料
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:WorkClothes:edit')")
    @Log(title = "工装台账", businessType = BusinessType.UPDATE)
    @PutMapping("/fileupdate")
    public AjaxResult updateWorkClothesfile(@RequestBody Map<String, Object> fileform)
    {
//        System.out.println("传过来的数据: " + fileform);

        return toAjax(toolingLedgerTableService.updateWorkClothesfile(fileform));
    }

    /**
     * 删除工装台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:WorkClothes:remove')")
    @Log(title = "工装台账", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(toolingLedgerTableService.deleteToolingLedgerTableByIds(ids));
    }
}
