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
import com.ruoyi.marketanalysis.domain.CategorysevenParameterTable;
import com.ruoyi.marketanalysis.service.ICategorysevenParameterTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 七类车参数Controller
 * 
 * @author ruoyi
 * @date 2025-03-06
 */
@RestController
@RequestMapping("/marketanalysis/parameterseven")
public class CategorysevenParameterTableController extends BaseController
{
    @Autowired
    private ICategorysevenParameterTableService categorysevenParameterTableService;

    /**
     * 查询七类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameterseven:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategorysevenParameterTable categorysevenParameterTable)
    {
        startPage();
        List<CategorysevenParameterTable> list = categorysevenParameterTableService.selectCategorysevenParameterTableList(categorysevenParameterTable);
        return getDataTable(list);
    }

    /**
     * 导出七类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameterseven:export')")
    @Log(title = "七类车参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategorysevenParameterTable categorysevenParameterTable)
    {
        List<CategorysevenParameterTable> list = categorysevenParameterTableService.selectCategorysevenParameterTableList(categorysevenParameterTable);
        ExcelUtil<CategorysevenParameterTable> util = new ExcelUtil<CategorysevenParameterTable>(CategorysevenParameterTable.class);
        util.exportExcel(response, list, "七类车参数数据");
    }

    /**
     * 获取七类车参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameterseven:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categorysevenParameterTableService.selectCategorysevenParameterTableById(id));
    }

    /**
     * 新增七类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameterseven:add')")
    @Log(title = "七类车参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategorysevenParameterTable categorysevenParameterTable)
    {
        return toAjax(categorysevenParameterTableService.insertCategorysevenParameterTable(categorysevenParameterTable));
    }

    /**
     * 修改七类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameterseven:edit')")
    @Log(title = "七类车参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategorysevenParameterTable categorysevenParameterTable)
    {
        return toAjax(categorysevenParameterTableService.updateCategorysevenParameterTable(categorysevenParameterTable));
    }

    /**
     * 删除七类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameterseven:remove')")
    @Log(title = "七类车参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categorysevenParameterTableService.deleteCategorysevenParameterTableByIds(ids));
    }
}
