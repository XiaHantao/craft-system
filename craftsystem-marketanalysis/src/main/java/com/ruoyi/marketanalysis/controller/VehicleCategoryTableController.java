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
import com.ruoyi.marketanalysis.domain.VehicleCategoryTable;
import com.ruoyi.marketanalysis.service.IVehicleCategoryTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车型分类Controller
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
@RestController
@RequestMapping("/marketanalysis/vehicletype")
public class VehicleCategoryTableController extends BaseController
{
    @Autowired
    private IVehicleCategoryTableService vehicleCategoryTableService;

    /**
     * 查询车型分类列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:vehicletype:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehicleCategoryTable vehicleCategoryTable)
    {
        startPage();
        List<VehicleCategoryTable> list = vehicleCategoryTableService.selectVehicleCategoryTableList(vehicleCategoryTable);
        return getDataTable(list);
    }

    /**
     * 导出车型分类列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:vehicletype:export')")
    @Log(title = "车型分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VehicleCategoryTable vehicleCategoryTable)
    {
        List<VehicleCategoryTable> list = vehicleCategoryTableService.selectVehicleCategoryTableList(vehicleCategoryTable);
        ExcelUtil<VehicleCategoryTable> util = new ExcelUtil<VehicleCategoryTable>(VehicleCategoryTable.class);
        util.exportExcel(response, list, "车型分类数据");
    }

    /**
     * 获取车型分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:vehicletype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vehicleCategoryTableService.selectVehicleCategoryTableById(id));
    }

    /**
     * 新增车型分类
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:vehicletype:add')")
    @Log(title = "车型分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VehicleCategoryTable vehicleCategoryTable)
    {
        return toAjax(vehicleCategoryTableService.insertVehicleCategoryTable(vehicleCategoryTable));
    }

    /**
     * 修改车型分类
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:vehicletype:edit')")
    @Log(title = "车型分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VehicleCategoryTable vehicleCategoryTable)
    {
        return toAjax(vehicleCategoryTableService.updateVehicleCategoryTable(vehicleCategoryTable));
    }

    /**
     * 删除车型分类
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:vehicletype:remove')")
    @Log(title = "车型分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vehicleCategoryTableService.deleteVehicleCategoryTableByIds(ids));
    }
}
