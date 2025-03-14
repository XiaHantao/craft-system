package com.ruoyi.workClothes.controller;

import java.util.List;
import java.util.Map;
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
import com.ruoyi.workClothes.domain.NonstructuraltoolingTable;
import com.ruoyi.workClothes.service.INonstructuraltoolingTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 非结构工装台账Controller
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@RestController
@RequestMapping("/ToolingModule/NonStructuralTooling")
public class NonstructuraltoolingTableController extends BaseController
{
    @Autowired
    private INonstructuraltoolingTableService nonstructuraltoolingTableService;

    /**
     * 查询外部工装台账列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:list')")
    @GetMapping("/list")
    public TableDataInfo list(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        startPage();
        List<NonstructuraltoolingTable> list = nonstructuraltoolingTableService.selectNonstructuraltoolingTableList(nonstructuraltoolingTable);
        return getDataTable(list);
    }
    /**
     * 查询金工工装台账列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:list')")
    @GetMapping("/list1")
    public TableDataInfo list1(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        startPage();
        List<NonstructuraltoolingTable> list = nonstructuraltoolingTableService.selectNonstructuraltoolingTableList1(nonstructuraltoolingTable);
        return getDataTable(list);
    }

    /**
     * 查询备料工装台账列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:list')")
    @GetMapping("/list2")
    public TableDataInfo list2(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        startPage();
        List<NonstructuraltoolingTable> list = nonstructuraltoolingTableService.selectNonstructuraltoolingTableList2(nonstructuraltoolingTable);
        return getDataTable(list);
    }

    /**
     * 修改非结构工装台账工艺及物料
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:edit')")
    @Log(title = "工装台账", businessType = BusinessType.UPDATE)
    @PutMapping("/fileupdate")
    public AjaxResult updateWorkClothesfile(@RequestBody Map<String, Object> fileform)
    {
//        System.out.println("传过来的数据: " + fileform);

        return toAjax(nonstructuraltoolingTableService.updateWorkClothesfile(fileform));
    }
    /**
     * 导出非结构工装台账列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:export')")
    @Log(title = "非结构工装台账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        List<NonstructuraltoolingTable> list = nonstructuraltoolingTableService.selectNonstructuraltoolingTableList(nonstructuraltoolingTable);
        ExcelUtil<NonstructuraltoolingTable> util = new ExcelUtil<NonstructuraltoolingTable>(NonstructuraltoolingTable.class);
        util.exportExcel(response, list, "非结构工装台账数据");
    }

    /**
     * 获取非结构工装台账详细信息
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nonstructuraltoolingTableService.selectNonstructuraltoolingTableById(id));
    }

    /**
     * 新增非结构工装台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:add')")
    @Log(title = "非结构工装台账", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return toAjax(nonstructuraltoolingTableService.insertNonstructuraltoolingTable(nonstructuraltoolingTable));
    }

    /**
     * 修改非结构工装台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:edit')")
    @Log(title = "非结构工装台账", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return toAjax(nonstructuraltoolingTableService.updateNonstructuraltoolingTable(nonstructuraltoolingTable));
    }

    /**
     * 删除非结构工装台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:NonStructuralTooling:remove')")
    @Log(title = "非结构工装台账", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nonstructuraltoolingTableService.deleteNonstructuraltoolingTableByIds(ids));
    }
}
