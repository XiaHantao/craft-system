package com.ruoyi.marketanalysis.controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.marketanalysis.domain.CategoryoneStandardConfigurationTable;
import com.ruoyi.marketanalysis.service.ICategoryoneStandardConfigurationTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 一类车标准配置Controller
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
@RestController
@RequestMapping("/marketanalysis/standardone")
public class CategoryoneStandardConfigurationTableController extends BaseController
{
    @Autowired
    private ICategoryoneStandardConfigurationTableService categoryoneStandardConfigurationTableService;

    /**
     * 查询一类车标准配置列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardone:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable)
    {
        startPage();
        List<CategoryoneStandardConfigurationTable> list = categoryoneStandardConfigurationTableService.selectCategoryoneStandardConfigurationTableList(categoryoneStandardConfigurationTable);
        return getDataTable(list);
    }

    /**
     * 导出一类车标准配置列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardone:export')")
    @Log(title = "I类车标准配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable)
    {
        List<CategoryoneStandardConfigurationTable> list = categoryoneStandardConfigurationTableService.selectCategoryoneStandardConfigurationTableList(categoryoneStandardConfigurationTable);
        ExcelUtil<CategoryoneStandardConfigurationTable> util = new ExcelUtil<CategoryoneStandardConfigurationTable>(CategoryoneStandardConfigurationTable.class);
        util.exportExcel(response, list, "一类车标准配置数据");
    }

    /**
     * 获取一类车标准配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardone:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryoneStandardConfigurationTableService.selectCategoryoneStandardConfigurationTableById(id));
    }

    /**
     * 新增一类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardone:add')")
    @Log(title = "I类车标准配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable)
    {
        return toAjax(categoryoneStandardConfigurationTableService.insertCategoryoneStandardConfigurationTable(categoryoneStandardConfigurationTable));
    }

    /**
     * 修改一类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardone:edit')")
    @Log(title = "I类车标准配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryoneStandardConfigurationTable categoryoneStandardConfigurationTable)
    {
        return toAjax(categoryoneStandardConfigurationTableService.updateCategoryoneStandardConfigurationTable(categoryoneStandardConfigurationTable));
    }

    /**
     * 删除一类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardone:remove')")
    @Log(title = "I类车标准配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryoneStandardConfigurationTableService.deleteCategoryoneStandardConfigurationTableByIds(ids));
    }
    @PostMapping("/importData")
//    @PreAuthorize("@ss.hasPermi('marketanalysis:standardone:import')")
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = categoryoneStandardConfigurationTableService.importCategoryoneStandardConfigurationTable(excelFile, updateSupport);
        return AjaxResult.success(message);
    }

    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        boolean exists = categoryoneStandardConfigurationTableService.checkDataExists();
        return success(exists);
    }
}
