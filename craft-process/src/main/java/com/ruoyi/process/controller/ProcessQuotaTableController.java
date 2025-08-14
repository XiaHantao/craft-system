package com.ruoyi.process.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.process.domain.ProcessQuotaTable;
import com.ruoyi.process.service.IProcessQuotaTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺定额Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
@RestController
@RequestMapping("/process/processQuotaTable")
public class ProcessQuotaTableController extends BaseController
{
    @Autowired
    private IProcessQuotaTableService processQuotaTableService;

    /**
     * 查询工艺定额列表
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessQuotaTable processQuotaTable)
    {
        startPage();
        List<ProcessQuotaTable> list = processQuotaTableService.selectProcessQuotaTableList(processQuotaTable);
        return getDataTable(list);
    }

    /**
     * 查询当前车型相关联的列表
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaTable:list')")
    @GetMapping(value = "/related")
    public TableDataInfo listRelated(@RequestParam String vehicleModel)
    {
        List<ProcessQuotaTable> list = processQuotaTableService.selectRelatedList(vehicleModel);
        return getDataTable(list);
    }

    /**
     * 导出工艺定额列表
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaTable:export')")
    @Log(title = "工艺定额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessQuotaTable processQuotaTable)
    {
        List<ProcessQuotaTable> list = processQuotaTableService.selectProcessQuotaTableList(processQuotaTable);
        ExcelUtil<ProcessQuotaTable> util = new ExcelUtil<ProcessQuotaTable>(ProcessQuotaTable.class);
        util.exportExcel(response, list, "工艺定额数据");
    }

    /**
     * 获取工艺定额详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaTable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processQuotaTableService.selectProcessQuotaTableById(id));
    }

    /**
     * 新增工艺定额
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaTable:add')")
    @Log(title = "工艺定额", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessQuotaTable processQuotaTable)
    {
        return toAjax(processQuotaTableService.insertProcessQuotaTable(processQuotaTable));
    }

    /**
     * 修改工艺定额
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaTable:edit')")
    @Log(title = "工艺定额", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessQuotaTable processQuotaTable)
    {
        return toAjax(processQuotaTableService.updateProcessQuotaTable(processQuotaTable));
    }

    /**
     * 删除工艺定额
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaTable:remove')")
    @Log(title = "工艺定额", businessType = BusinessType.DELETE)
	@DeleteMapping("/delete")
    public AjaxResult remove(@RequestParam String[] vehicleModels)
    {
        return toAjax(processQuotaTableService.deleteProcessQuotaTableByIds(vehicleModels));
    }

    /**
     * 删除step不为空的工艺定额
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaTable:remove')")
    @Log(title = "工艺定额", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete02")
    public AjaxResult remove02(@RequestParam String[] vehicleModels)
    {
        return toAjax(processQuotaTableService.deleteProcessQuotaTableByIds02(vehicleModels));
    }

}
