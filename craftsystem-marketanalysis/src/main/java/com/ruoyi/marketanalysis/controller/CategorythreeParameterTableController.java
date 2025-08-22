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
import com.ruoyi.marketanalysis.domain.CategorythreeParameterTable;
import com.ruoyi.marketanalysis.service.ICategorythreeParameterTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 三类车参数Controller
 * 
 * @author ruoyi
 * @date 2025-07-01
 */
@RestController
@RequestMapping("/marketanalysis/parameter_three")
public class CategorythreeParameterTableController extends BaseController
{
    @Autowired
    private ICategorythreeParameterTableService categorythreeParameterTableService;

    /**
     * 查询三类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_three:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategorythreeParameterTable categorythreeParameterTable)
    {
        startPage();
        List<CategorythreeParameterTable> list = categorythreeParameterTableService.selectCategorythreeParameterTableList(categorythreeParameterTable);
        return getDataTable(list);
    }

    /**
     * 导出三类车参数列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_three:export')")
    @Log(title = "III类车参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategorythreeParameterTable categorythreeParameterTable)
    {
        List<CategorythreeParameterTable> list = categorythreeParameterTableService.selectCategorythreeParameterTableList(categorythreeParameterTable);
        ExcelUtil<CategorythreeParameterTable> util = new ExcelUtil<CategorythreeParameterTable>(CategorythreeParameterTable.class);
        util.exportExcel(response, list, "三类车参数数据");
    }

    /**
     * 获取三类车参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_three:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categorythreeParameterTableService.selectCategorythreeParameterTableById(id));
    }

    /**
     * 新增三类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_three:add')")
    @Log(title = "III类车参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategorythreeParameterTable categorythreeParameterTable)
    {
        return toAjax(categorythreeParameterTableService.insertCategorythreeParameterTable(categorythreeParameterTable));
    }

    /**
     * 修改三类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_three:edit')")
    @Log(title = "III类车参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategorythreeParameterTable categorythreeParameterTable)
    {
        return toAjax(categorythreeParameterTableService.updateCategorythreeParameterTable(categorythreeParameterTable));
    }

    /**
     * 删除三类车参数
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_three:remove')")
    @Log(title = "III类车参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categorythreeParameterTableService.deleteCategorythreeParameterTableByIds(ids));
    }
    @PostMapping("/importData")
//    @PreAuthorize("@ss.hasPermi('marketanalysis:parameter_three:import')")
    @ResponseBody
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport
    ) throws Exception {
        File excelFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(excelFile);
        String message = categorythreeParameterTableService.importCategorythreeParameterTable(excelFile, updateSupport);
        return AjaxResult.success(message);
    }

    @GetMapping("/checkDataExists")
    public AjaxResult checkDataExists() {
        boolean exists = categorythreeParameterTableService.checkDataExists();
        return success(exists);
    }
}
