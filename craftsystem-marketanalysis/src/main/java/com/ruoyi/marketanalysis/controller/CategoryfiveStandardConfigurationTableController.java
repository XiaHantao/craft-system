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
import com.ruoyi.marketanalysis.domain.CategoryfiveStandardConfigurationTable;
import com.ruoyi.marketanalysis.service.ICategoryfiveStandardConfigurationTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 五类/七类车标准配置Controller
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
@RestController
@RequestMapping("/marketanalysis/standardfive")
public class CategoryfiveStandardConfigurationTableController extends BaseController
{
    @Autowired
    private ICategoryfiveStandardConfigurationTableService categoryfiveStandardConfigurationTableService;

    /**
     * 查询五类/七类车标准配置列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardfive:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable)
    {
        startPage();
        List<CategoryfiveStandardConfigurationTable> list = categoryfiveStandardConfigurationTableService.selectCategoryfiveStandardConfigurationTableList(categoryfiveStandardConfigurationTable);
        return getDataTable(list);
    }

    /**
     * 导出五类/七类车标准配置列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardfive:export')")
    @Log(title = "五类/七类车标准配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable)
    {
        List<CategoryfiveStandardConfigurationTable> list = categoryfiveStandardConfigurationTableService.selectCategoryfiveStandardConfigurationTableList(categoryfiveStandardConfigurationTable);
        ExcelUtil<CategoryfiveStandardConfigurationTable> util = new ExcelUtil<CategoryfiveStandardConfigurationTable>(CategoryfiveStandardConfigurationTable.class);
        util.exportExcel(response, list, "五类七类车标准配置数据");
    }

    /**
     * 获取五类/七类车标准配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardfive:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryfiveStandardConfigurationTableService.selectCategoryfiveStandardConfigurationTableById(id));
    }

    /**
     * 新增五类/七类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardfive:add')")
    @Log(title = "五类/七类车标准配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable)
    {
        return toAjax(categoryfiveStandardConfigurationTableService.insertCategoryfiveStandardConfigurationTable(categoryfiveStandardConfigurationTable));
    }

    /**
     * 修改五类/七类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardfive:edit')")
    @Log(title = "五类/七类车标准配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryfiveStandardConfigurationTable categoryfiveStandardConfigurationTable)
    {
        return toAjax(categoryfiveStandardConfigurationTableService.updateCategoryfiveStandardConfigurationTable(categoryfiveStandardConfigurationTable));
    }

    /**
     * 删除五类/七类车标准配置
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:standardfive:remove')")
    @Log(title = "五类/七类车标准配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryfiveStandardConfigurationTableService.deleteCategoryfiveStandardConfigurationTableByIds(ids));
    }
    // 导入接口
    @PostMapping("/importData")
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = categoryfiveStandardConfigurationTableService.importCategoryfiveStandardConfigurationTable(excelFile, updateSupport);
        return AjaxResult.success(message);
    }

    // 数据存在检查接口
    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        boolean exists = categoryfiveStandardConfigurationTableService.checkDataExists();
        return success(exists);
    }
}
