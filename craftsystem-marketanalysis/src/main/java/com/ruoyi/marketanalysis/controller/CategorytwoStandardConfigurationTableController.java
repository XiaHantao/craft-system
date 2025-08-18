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
import com.ruoyi.marketanalysis.domain.CategorytwoStandardConfigurationTable;
import com.ruoyi.marketanalysis.service.ICategorytwoStandardConfigurationTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 二类车标准配置Controller
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/marketanalysis/standardtwo")
public class CategorytwoStandardConfigurationTableController extends BaseController
{
    @Autowired
    private ICategorytwoStandardConfigurationTableService categorytwoStandardConfigurationTableService;

    /**
     * 查询二类车标准配置列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardtwo:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable)
    {
        startPage();
        List<CategorytwoStandardConfigurationTable> list = categorytwoStandardConfigurationTableService.selectCategorytwoStandardConfigurationTableList(categorytwoStandardConfigurationTable);
        return getDataTable(list);
    }

    /**
     * 导出二类车标准配置列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardtwo:export')")
    @Log(title = "二类车标准配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable)
    {
        List<CategorytwoStandardConfigurationTable> list = categorytwoStandardConfigurationTableService.selectCategorytwoStandardConfigurationTableList(categorytwoStandardConfigurationTable);
        ExcelUtil<CategorytwoStandardConfigurationTable> util = new ExcelUtil<CategorytwoStandardConfigurationTable>(CategorytwoStandardConfigurationTable.class);
        util.exportExcel(response, list, "二类车标准配置数据");
    }

    /**
     * 获取二类车标准配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardtwo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categorytwoStandardConfigurationTableService.selectCategorytwoStandardConfigurationTableById(id));
    }

    /**
     * 新增二类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardtwo:add')")
    @Log(title = "二类车标准配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable)
    {
        return toAjax(categorytwoStandardConfigurationTableService.insertCategorytwoStandardConfigurationTable(categorytwoStandardConfigurationTable));
    }

    /**
     * 修改二类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardtwo:edit')")
    @Log(title = "二类车标准配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategorytwoStandardConfigurationTable categorytwoStandardConfigurationTable)
    {
        return toAjax(categorytwoStandardConfigurationTableService.updateCategorytwoStandardConfigurationTable(categorytwoStandardConfigurationTable));
    }

    /**
     * 删除二类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardtwo:remove')")
    @Log(title = "二类车标准配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categorytwoStandardConfigurationTableService.deleteCategorytwoStandardConfigurationTableByIds(ids));
    }
    @PostMapping("/importData")
//    @PreAuthorize("@ss.hasPermi('marketanalysis:standardtwo:import')")
    @ResponseBody
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = categorytwoStandardConfigurationTableService.importCategorytwoStandard(excelFile, updateSupport);
        return AjaxResult.success(message);
    }

    // 添加检查数据存在接口
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardtwo:list')")
    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        boolean exists = categorytwoStandardConfigurationTableService.checkDataExists();
        return success(exists);
    }
}
