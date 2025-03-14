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
import com.ruoyi.process.domain.ProcessResearchTable;
import com.ruoyi.process.service.IProcessResearchTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺调研Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
@RestController
@RequestMapping("/process/processResearchTable")
public class ProcessResearchTableController extends BaseController
{
    @Autowired
    private IProcessResearchTableService processResearchTableService;

    /**
     * 查询工艺调研列表
     */
    @PreAuthorize("@ss.hasPermi('process:processResearchTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessResearchTable processResearchTable)
    {
        startPage();
        List<ProcessResearchTable> list = processResearchTableService.selectProcessResearchTableList(processResearchTable);
        return getDataTable(list);
    }

    /**
     * 导出工艺调研列表
     */
    @PreAuthorize("@ss.hasPermi('process:processResearchTable:export')")
    @Log(title = "工艺调研", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessResearchTable processResearchTable)
    {
        List<ProcessResearchTable> list = processResearchTableService.selectProcessResearchTableList(processResearchTable);
        ExcelUtil<ProcessResearchTable> util = new ExcelUtil<ProcessResearchTable>(ProcessResearchTable.class);
        util.exportExcel(response, list, "工艺调研数据");
    }

    /**
     * 获取工艺调研详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processResearchTable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processResearchTableService.selectProcessResearchTableById(id));
    }

    /**
     * 新增工艺调研
     */
    @PreAuthorize("@ss.hasPermi('process:processResearchTable:add')")
    @Log(title = "工艺调研", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessResearchTable processResearchTable)
    {
        return toAjax(processResearchTableService.insertProcessResearchTable(processResearchTable));
    }

    /**
     * 修改工艺调研
     */
    @PreAuthorize("@ss.hasPermi('process:processResearchTable:edit')")
    @Log(title = "工艺调研", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessResearchTable processResearchTable)
    {
        return toAjax(processResearchTableService.updateProcessResearchTable(processResearchTable));
    }

    /**
     * 删除工艺调研
     */
    @PreAuthorize("@ss.hasPermi('process:processResearchTable:remove')")
    @Log(title = "工艺调研", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processResearchTableService.deleteProcessResearchTableByIds(ids));
    }
}
