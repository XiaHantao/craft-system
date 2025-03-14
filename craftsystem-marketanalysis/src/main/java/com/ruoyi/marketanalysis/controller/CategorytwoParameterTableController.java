package com.ruoyi.marketanalysis.controller;

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
import com.ruoyi.marketanalysis.domain.CategorytwoParameterTable;
import com.ruoyi.marketanalysis.service.ICategorytwoParameterTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 二类车参数Controller
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
@RestController
@RequestMapping("/marketanalysis/parameter_two")
public class CategorytwoParameterTableController extends BaseController
{
    @Autowired
    private ICategorytwoParameterTableService categorytwoParameterTableService;

    /**
     * 查询二类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_two:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategorytwoParameterTable categorytwoParameterTable)
    {
        startPage();
        List<CategorytwoParameterTable> list = categorytwoParameterTableService.selectCategorytwoParameterTableList(categorytwoParameterTable);
        return getDataTable(list);
    }

    /**
     * 导出二类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_two:export')")
    @Log(title = "二类车参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategorytwoParameterTable categorytwoParameterTable)
    {
        List<CategorytwoParameterTable> list = categorytwoParameterTableService.selectCategorytwoParameterTableList(categorytwoParameterTable);
        ExcelUtil<CategorytwoParameterTable> util = new ExcelUtil<CategorytwoParameterTable>(CategorytwoParameterTable.class);
        util.exportExcel(response, list, "二类车参数数据");
    }

    /**
     * 获取二类车参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_two:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categorytwoParameterTableService.selectCategorytwoParameterTableById(id));
    }

    /**
     * 新增二类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_two:add')")
    @Log(title = "二类车参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategorytwoParameterTable categorytwoParameterTable)
    {
        return toAjax(categorytwoParameterTableService.insertCategorytwoParameterTable(categorytwoParameterTable));
    }

    /**
     * 修改二类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_two:edit')")
    @Log(title = "二类车参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategorytwoParameterTable categorytwoParameterTable)
    {
        return toAjax(categorytwoParameterTableService.updateCategorytwoParameterTable(categorytwoParameterTable));
    }

    /**
     * 删除二类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_two:remove')")
    @Log(title = "二类车参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categorytwoParameterTableService.deleteCategorytwoParameterTableByIds(ids));
    }
}
