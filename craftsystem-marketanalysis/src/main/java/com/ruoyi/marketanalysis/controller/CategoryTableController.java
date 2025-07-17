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
import com.ruoyi.marketanalysis.domain.CategoryTable;
import com.ruoyi.marketanalysis.service.ICategoryTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 车类Controller
 * 
 * @author ruoyi
 * @date 2025-07-08
 */
@RestController
@RequestMapping("/marketanalysis/category")
public class CategoryTableController extends BaseController
{
    @Autowired
    private ICategoryTableService categoryTableService;

    /**
     * 查询车类列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategoryTable categoryTable)
    {
        startPage();
        List<CategoryTable> list = categoryTableService.selectCategoryTableList(categoryTable);
        return getDataTable(list);
    }

    /**
     * 导出车类列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:category:export')")
    @Log(title = "车类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryTable categoryTable)
    {
        List<CategoryTable> list = categoryTableService.selectCategoryTableList(categoryTable);
        ExcelUtil<CategoryTable> util = new ExcelUtil<CategoryTable>(CategoryTable.class);
        util.exportExcel(response, list, "车类数据");
    }

    /**
     * 获取车类详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryTableService.selectCategoryTableById(id));
    }

    /**
     * 新增车类
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:category:add')")
    @Log(title = "车类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryTable categoryTable)
    {
        return toAjax(categoryTableService.insertCategoryTable(categoryTable));
    }

    /**
     * 修改车类
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:category:edit')")
    @Log(title = "车类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryTable categoryTable)
    {
        return toAjax(categoryTableService.updateCategoryTable(categoryTable));
    }

    /**
     * 删除车类
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:category:remove')")
    @Log(title = "车类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryTableService.deleteCategoryTableByIds(ids));
    }
    // 新增导入方法
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = categoryTableService.importCategoryTable(excelFile, updateSupport);
        return AjaxResult.success(message);
    }
    // 新增数据存在检查接口
    @PreAuthorize("@ss.hasPermi('marketanalysis:category:list')")
    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        return success(categoryTableService.checkDataExists());
    }
}
