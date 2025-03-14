package com.ruoyi.process.controller;

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
import com.ruoyi.process.domain.SpecializedProcessEquipmentTable;
import com.ruoyi.process.service.ISpecializedProcessEquipmentTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 专用工艺装备清单Controller
 * 
 * @author Kanna Bush
 * @date 2025-01-19
 */
@RestController
@RequestMapping("/process/specializedProcessEquipment")
public class SpecializedProcessEquipmentTableController extends BaseController
{
    @Autowired
    private ISpecializedProcessEquipmentTableService specializedProcessEquipmentTableService;

    /**
     * 查询专用工艺装备清单列表
     */
    @PreAuthorize("@ss.hasPermi('process:specializedProcessEquipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpecializedProcessEquipmentTable specializedProcessEquipmentTable)
    {
        startPage();
        List<SpecializedProcessEquipmentTable> list = specializedProcessEquipmentTableService.selectSpecializedProcessEquipmentTableList(specializedProcessEquipmentTable);
        return getDataTable(list);
    }

    /**
     * 导出专用工艺装备清单列表
     */
    @PreAuthorize("@ss.hasPermi('process:specializedProcessEquipment:export')")
    @Log(title = "专用工艺装备清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpecializedProcessEquipmentTable specializedProcessEquipmentTable)
    {
        List<SpecializedProcessEquipmentTable> list = specializedProcessEquipmentTableService.selectSpecializedProcessEquipmentTableList(specializedProcessEquipmentTable);
        ExcelUtil<SpecializedProcessEquipmentTable> util = new ExcelUtil<SpecializedProcessEquipmentTable>(SpecializedProcessEquipmentTable.class);
        util.exportExcel(response, list, "专用工艺装备清单数据");
    }

    /**
     * 获取专用工艺装备清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:specializedProcessEquipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(specializedProcessEquipmentTableService.selectSpecializedProcessEquipmentTableById(id));
    }

    /**
     * 新增专用工艺装备清单
     */
    @PreAuthorize("@ss.hasPermi('process:specializedProcessEquipment:add')")
    @Log(title = "专用工艺装备清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpecializedProcessEquipmentTable specializedProcessEquipmentTable)
    {
        return toAjax(specializedProcessEquipmentTableService.insertSpecializedProcessEquipmentTable(specializedProcessEquipmentTable));
    }

    /**
     * 修改专用工艺装备清单
     */
    @PreAuthorize("@ss.hasPermi('process:specializedProcessEquipment:edit')")
    @Log(title = "专用工艺装备清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpecializedProcessEquipmentTable specializedProcessEquipmentTable)
    {
        return toAjax(specializedProcessEquipmentTableService.updateSpecializedProcessEquipmentTable(specializedProcessEquipmentTable));
    }

    /**
     * 删除专用工艺装备清单
     */
    @PreAuthorize("@ss.hasPermi('process:specializedProcessEquipment:remove')")
    @Log(title = "专用工艺装备清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(specializedProcessEquipmentTableService.deleteSpecializedProcessEquipmentTableByIds(ids));
    }
}
