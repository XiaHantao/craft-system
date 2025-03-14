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
import com.ruoyi.process.domain.ProcessSchemeDesignTable;
import com.ruoyi.process.service.IProcessSchemeDesignTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺方案设计Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-20
 */
@RestController
@RequestMapping("/process/processSchemeDesign")
public class ProcessSchemeDesignTableController extends BaseController
{
    @Autowired
    private IProcessSchemeDesignTableService processSchemeDesignTableService;

    /**
     * 查询工艺方案设计列表
     */
    @PreAuthorize("@ss.hasPermi('process:processSchemeDesign:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessSchemeDesignTable processSchemeDesignTable)
    {
        startPage();
        List<ProcessSchemeDesignTable> list = processSchemeDesignTableService.selectProcessSchemeDesignTableList(processSchemeDesignTable);
        return getDataTable(list);
    }

    /**
     * 导出工艺方案设计列表
     */
    @PreAuthorize("@ss.hasPermi('process:processSchemeDesign:export')")
    @Log(title = "工艺方案设计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessSchemeDesignTable processSchemeDesignTable)
    {
        List<ProcessSchemeDesignTable> list = processSchemeDesignTableService.selectProcessSchemeDesignTableList(processSchemeDesignTable);
        ExcelUtil<ProcessSchemeDesignTable> util = new ExcelUtil<ProcessSchemeDesignTable>(ProcessSchemeDesignTable.class);
        util.exportExcel(response, list, "工艺方案设计数据");
    }

    /**
     * 获取工艺方案设计详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processSchemeDesign:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processSchemeDesignTableService.selectProcessSchemeDesignTableById(id));
    }

    /**
     * 新增工艺方案设计
     */
    @PreAuthorize("@ss.hasPermi('process:processSchemeDesign:add')")
    @Log(title = "工艺方案设计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessSchemeDesignTable processSchemeDesignTable)
    {
        return toAjax(processSchemeDesignTableService.insertProcessSchemeDesignTable(processSchemeDesignTable));
    }

    /**
     * 修改工艺方案设计
     */
    @PreAuthorize("@ss.hasPermi('process:processSchemeDesign:edit')")
    @Log(title = "工艺方案设计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessSchemeDesignTable processSchemeDesignTable)
    {
        return toAjax(processSchemeDesignTableService.updateProcessSchemeDesignTable(processSchemeDesignTable));
    }

    /**
     * 删除工艺方案设计
     */
    @PreAuthorize("@ss.hasPermi('process:processSchemeDesign:remove')")
    @Log(title = "工艺方案设计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processSchemeDesignTableService.deleteProcessSchemeDesignTableByIds(ids));
    }
}
