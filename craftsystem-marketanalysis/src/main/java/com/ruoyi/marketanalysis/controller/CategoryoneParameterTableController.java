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
import com.ruoyi.marketanalysis.domain.CategoryoneParameterTable;
import com.ruoyi.marketanalysis.service.ICategoryoneParameterTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 一类车参数Controller
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/marketanalysis/parameter_one")
public class CategoryoneParameterTableController extends BaseController
{
    @Autowired
    private ICategoryoneParameterTableService categoryoneParameterTableService;

    /**
     * 查询一类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_one:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategoryoneParameterTable categoryoneParameterTable)
    {
        startPage();
        List<CategoryoneParameterTable> list = categoryoneParameterTableService.selectCategoryoneParameterTableList(categoryoneParameterTable);
        return getDataTable(list);
    }

    /**
     * 导出一类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_one:export')")
    @Log(title = "一类车参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryoneParameterTable categoryoneParameterTable)
    {
        List<CategoryoneParameterTable> list = categoryoneParameterTableService.selectCategoryoneParameterTableList(categoryoneParameterTable);
        ExcelUtil<CategoryoneParameterTable> util = new ExcelUtil<CategoryoneParameterTable>(CategoryoneParameterTable.class);
        util.exportExcel(response, list, "一类车参数数据");
    }

    /**
     * 获取一类车参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_one:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryoneParameterTableService.selectCategoryoneParameterTableById(id));
    }

    /**
     * 新增一类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_one:add')")
    @Log(title = "一类车参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryoneParameterTable categoryoneParameterTable)
    {
        return toAjax(categoryoneParameterTableService.insertCategoryoneParameterTable(categoryoneParameterTable));
    }

    /**
     * 修改一类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_one:edit')")
    @Log(title = "一类车参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryoneParameterTable categoryoneParameterTable)
    {
        return toAjax(categoryoneParameterTableService.updateCategoryoneParameterTable(categoryoneParameterTable));
    }

    /**
     * 删除一类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_one:remove')")
    @Log(title = "一类车参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryoneParameterTableService.deleteCategoryoneParameterTableByIds(ids));
    }
    // 一类车参数Excel导入接口
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_one:import')")
    @Log(title = "一类车参数导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = categoryoneParameterTableService.importCategoryoneParameterTable(excelFile, updateSupport);
        return AjaxResult.success(message);
    }

    // 检查一类车参数数据是否存在接口
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_one:list')")
    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        boolean exists = categoryoneParameterTableService.checkDataExists();
        return success(exists);
    }
}
