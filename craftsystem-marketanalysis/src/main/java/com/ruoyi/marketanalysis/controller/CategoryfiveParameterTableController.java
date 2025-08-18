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
import com.ruoyi.marketanalysis.domain.CategoryfiveParameterTable;
import com.ruoyi.marketanalysis.service.ICategoryfiveParameterTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 五类车参数Controller
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/marketanalysis/parameter_five")
public class CategoryfiveParameterTableController extends BaseController
{
    @Autowired
    private ICategoryfiveParameterTableService categoryfiveParameterTableService;

    /**
     * 查询五类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_five:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategoryfiveParameterTable categoryfiveParameterTable)
    {
        startPage();
        List<CategoryfiveParameterTable> list = categoryfiveParameterTableService.selectCategoryfiveParameterTableList(categoryfiveParameterTable);
        return getDataTable(list);
    }

    /**
     * 导出五类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_five:export')")
    @Log(title = "五类车参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryfiveParameterTable categoryfiveParameterTable)
    {
        List<CategoryfiveParameterTable> list = categoryfiveParameterTableService.selectCategoryfiveParameterTableList(categoryfiveParameterTable);
        ExcelUtil<CategoryfiveParameterTable> util = new ExcelUtil<CategoryfiveParameterTable>(CategoryfiveParameterTable.class);
        util.exportExcel(response, list, "五类车参数数据");
    }

    /**
     * 获取五类车参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_five:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryfiveParameterTableService.selectCategoryfiveParameterTableById(id));
    }

    /**
     * 新增五类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_five:add')")
    @Log(title = "五类车参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryfiveParameterTable categoryfiveParameterTable)
    {
        return toAjax(categoryfiveParameterTableService.insertCategoryfiveParameterTable(categoryfiveParameterTable));
    }

    /**
     * 修改五类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_five:edit')")
    @Log(title = "五类车参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryfiveParameterTable categoryfiveParameterTable)
    {
        return toAjax(categoryfiveParameterTableService.updateCategoryfiveParameterTable(categoryfiveParameterTable));
    }

    /**
     * 删除五类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_five:remove')")
    @Log(title = "五类车参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryfiveParameterTableService.deleteCategoryfiveParameterTableByIds(ids));
    }
    // 在类中添加导入方法
//    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_five:import')")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = categoryfiveParameterTableService.importCategoryfiveParameterTable(excelFile, updateSupport);
        return AjaxResult.success(message);
    }

    // 添加数据存在检查接口
    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        boolean exists = categoryfiveParameterTableService.checkDataExists();
        return success(exists);
    }
}
