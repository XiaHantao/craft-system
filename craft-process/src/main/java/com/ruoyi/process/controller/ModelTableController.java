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
import com.ruoyi.process.domain.ModelTable;
import com.ruoyi.process.service.IModelTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车型Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-18
 */
@RestController
@RequestMapping("/process/modelTable")
public class ModelTableController extends BaseController
{
    @Autowired
    private IModelTableService modelTableService;

    /**
     * 查询车型列表
     */
    @PreAuthorize("@ss.hasPermi('process:modelTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(ModelTable modelTable)
    {
        startPage();
        List<ModelTable> list = modelTableService.selectModelTableList(modelTable);
        return getDataTable(list);
    }

    /**
     * 导出车型列表
     */
    @PreAuthorize("@ss.hasPermi('process:modelTable:export')")
    @Log(title = "车型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ModelTable modelTable)
    {
        List<ModelTable> list = modelTableService.selectModelTableList(modelTable);
        ExcelUtil<ModelTable> util = new ExcelUtil<ModelTable>(ModelTable.class);
        util.exportExcel(response, list, "车型数据");
    }

    /**
     * 获取车型详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:modelTable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(modelTableService.selectModelTableById(id));
    }

    /**
     * 新增车型
     */
    @PreAuthorize("@ss.hasPermi('process:modelTable:add')")
    @Log(title = "车型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ModelTable modelTable)
    {
        return toAjax(modelTableService.insertModelTable(modelTable));
    }

    /**
     * 修改车型
     */
    @PreAuthorize("@ss.hasPermi('process:modelTable:edit')")
    @Log(title = "车型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ModelTable modelTable)
    {
        return toAjax(modelTableService.updateModelTable(modelTable));
    }

    /**
     * 删除车型
     */
    @PreAuthorize("@ss.hasPermi('process:modelTable:remove')")
    @Log(title = "车型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(modelTableService.deleteModelTableByIds(ids));
    }
}
