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
import com.ruoyi.process.domain.ProcessMapTable;
import com.ruoyi.process.service.IProcessMapTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺地图Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-22
 */
@RestController
@RequestMapping("/process/processMap")
public class ProcessMapTableController extends BaseController
{
    @Autowired
    private IProcessMapTableService processMapTableService;

    /**
     * 查询工艺地图列表
     */
    @PreAuthorize("@ss.hasPermi('process:processMap:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessMapTable processMapTable)
    {
        startPage();
        List<ProcessMapTable> list = processMapTableService.selectProcessMapTableList(processMapTable);
        return getDataTable(list);
    }

    /**
     * 导出工艺地图列表
     */
    @PreAuthorize("@ss.hasPermi('process:processMap:export')")
    @Log(title = "工艺地图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessMapTable processMapTable)
    {
        List<ProcessMapTable> list = processMapTableService.selectProcessMapTableList(processMapTable);
        ExcelUtil<ProcessMapTable> util = new ExcelUtil<ProcessMapTable>(ProcessMapTable.class);
        util.exportExcel(response, list, "工艺地图数据");
    }

    /**
     * 获取工艺地图详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processMap:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processMapTableService.selectProcessMapTableById(id));
    }

    /**
     * 新增工艺地图
     */
    @PreAuthorize("@ss.hasPermi('process:processMap:add')")
    @Log(title = "工艺地图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessMapTable processMapTable)
    {
        return toAjax(processMapTableService.insertProcessMapTable(processMapTable));
    }

    /**
     * 修改工艺地图
     */
    @PreAuthorize("@ss.hasPermi('process:processMap:edit')")
    @Log(title = "工艺地图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessMapTable processMapTable)
    {
        return toAjax(processMapTableService.updateProcessMapTable(processMapTable));
    }

    /**
     * 删除工艺地图
     */
    @PreAuthorize("@ss.hasPermi('process:processMap:remove')")
    @Log(title = "工艺地图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processMapTableService.deleteProcessMapTableByIds(ids));
    }
}
