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
import com.ruoyi.process.domain.ProcessQuotaValueTable;
import com.ruoyi.process.service.IProcessQuotaValueTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺定额值Controller
 * 
 * @author Kanna Bush
 * @date 2025-03-04
 */
@RestController
@RequestMapping("/process/processQuotaValue")
public class ProcessQuotaValueTableController extends BaseController
{
    @Autowired
    private IProcessQuotaValueTableService processQuotaValueTableService;

    /**
     * 查询工艺定额值列表
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaValue:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessQuotaValueTable processQuotaValueTable)
    {
        startPage();
        List<ProcessQuotaValueTable> list = processQuotaValueTableService.selectProcessQuotaValueTableList(processQuotaValueTable);
        return getDataTable(list);
    }


    /**
     * 导出工艺定额值列表
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaValue:export')")
    @Log(title = "工艺定额值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessQuotaValueTable processQuotaValueTable)
    {
        List<ProcessQuotaValueTable> list = processQuotaValueTableService.selectProcessQuotaValueTableList(processQuotaValueTable);
        ExcelUtil<ProcessQuotaValueTable> util = new ExcelUtil<ProcessQuotaValueTable>(ProcessQuotaValueTable.class);
        util.exportExcel(response, list, "工艺定额值数据");
    }

    /**
     * 获取工艺定额值详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaValue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processQuotaValueTableService.selectProcessQuotaValueTableById(id));
    }

    /**
     * 新增工艺定额值
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaValue:add')")
    @Log(title = "工艺定额值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessQuotaValueTable processQuotaValueTable)
    {
        return toAjax(processQuotaValueTableService.insertProcessQuotaValueTable(processQuotaValueTable));
    }

    /**
     * 修改工艺定额值
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaValue:edit')")
    @Log(title = "工艺定额值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessQuotaValueTable processQuotaValueTable)
    {
        return toAjax(processQuotaValueTableService.updateProcessQuotaValueTable(processQuotaValueTable));
    }

    /**
     * 删除工艺定额值
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaValue:remove')")
    @Log(title = "工艺定额值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processQuotaValueTableService.deleteProcessQuotaValueTableByIds(ids));
    }
}
