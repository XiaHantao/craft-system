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
import com.ruoyi.process.domain.ProcessSpecificationDesignTable;
import com.ruoyi.process.service.IProcessSpecificationDesignTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺规程设计Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-24
 */
@RestController
@RequestMapping("/process/processSpecificationDesign")
public class ProcessSpecificationDesignTableController extends BaseController
{
    @Autowired
    private IProcessSpecificationDesignTableService processSpecificationDesignTableService;

    /**
     * 查询专用规程列表
     */
    @PreAuthorize("@ss.hasPermi('process:processSpecificationDesign:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        startPage();
        List<ProcessSpecificationDesignTable> list = processSpecificationDesignTableService.selectProcessSpecificationDesignTableList(processSpecificationDesignTable);
        return getDataTable(list);
    }

    /**
     * 查询通用规程列表
     */
    @PreAuthorize("@ss.hasPermi('process:processSpecificationDesign:list')")
    @GetMapping("/generallist")
    public TableDataInfo generalList(ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        startPage();
        List<ProcessSpecificationDesignTable> list = processSpecificationDesignTableService.selectGeneralList(processSpecificationDesignTable);
        return getDataTable(list);
    }



    /**
     * 导出工艺规程设计列表
     */
    @PreAuthorize("@ss.hasPermi('process:processSpecificationDesign:export')")
    @Log(title = "工艺规程设计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        List<ProcessSpecificationDesignTable> list = processSpecificationDesignTableService.selectProcessSpecificationDesignTableList(processSpecificationDesignTable);
        ExcelUtil<ProcessSpecificationDesignTable> util = new ExcelUtil<ProcessSpecificationDesignTable>(ProcessSpecificationDesignTable.class);
        util.exportExcel(response, list, "工艺规程设计数据");
    }

    /**
     * 获取工艺规程设计详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processSpecificationDesign:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processSpecificationDesignTableService.selectProcessSpecificationDesignTableById(id));
    }

    /**
     * 新增工艺规程设计
     */
    @PreAuthorize("@ss.hasPermi('process:processSpecificationDesign:add')")
    @Log(title = "工艺规程设计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        return toAjax(processSpecificationDesignTableService.insertProcessSpecificationDesignTable(processSpecificationDesignTable));
    }

    /**
     * 修改工艺规程设计
     */
    @PreAuthorize("@ss.hasPermi('process:processSpecificationDesign:edit')")
    @Log(title = "工艺规程设计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessSpecificationDesignTable processSpecificationDesignTable)
    {
        return toAjax(processSpecificationDesignTableService.updateProcessSpecificationDesignTable(processSpecificationDesignTable));
    }

    /**
     * 删除工艺规程设计
     */
    @PreAuthorize("@ss.hasPermi('process:processSpecificationDesign:remove')")
    @Log(title = "工艺规程设计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processSpecificationDesignTableService.deleteProcessSpecificationDesignTableByIds(ids));
    }
}
