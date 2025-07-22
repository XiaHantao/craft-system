package com.ruoyi.myCode.controller;

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
import com.ruoyi.myCode.domain.NewProductProductionPlan;
import com.ruoyi.myCode.service.INewProductProductionPlanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品生产计划Controller
 * 
 * @author xj
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/newproducts/plan")
public class NewProductProductionPlanController extends BaseController
{
    @Autowired
    private INewProductProductionPlanService newProductProductionPlanService;

    /**
     * 查询新产品生产计划列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewProductProductionPlan newProductProductionPlan)
    {
        startPage();
        List<NewProductProductionPlan> list = newProductProductionPlanService.selectNewProductProductionPlanList(newProductProductionPlan);
        return getDataTable(list);
    }

    /**
     * 导出新产品生产计划列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:export')")
    @Log(title = "新产品生产计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewProductProductionPlan newProductProductionPlan)
    {
        List<NewProductProductionPlan> list = newProductProductionPlanService.selectNewProductProductionPlanList(newProductProductionPlan);
        ExcelUtil<NewProductProductionPlan> util = new ExcelUtil<NewProductProductionPlan>(NewProductProductionPlan.class);
        util.exportExcel(response, list, "新产品生产计划数据");
    }

    /**
     * 获取新产品生产计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newProductProductionPlanService.selectNewProductProductionPlanById(id));
    }

    /**
     * 新增新产品生产计划
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:add')")
    @Log(title = "新产品生产计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewProductProductionPlan newProductProductionPlan)
    {
        return toAjax(newProductProductionPlanService.insertNewProductProductionPlan(newProductProductionPlan));
    }

    /**
     * 修改新产品生产计划
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:edit')")
    @Log(title = "新产品生产计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewProductProductionPlan newProductProductionPlan)
    {
        return toAjax(newProductProductionPlanService.updateNewProductProductionPlan(newProductProductionPlan));
    }

    /**
     * 删除新产品生产计划
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:remove')")
    @Log(title = "新产品生产计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newProductProductionPlanService.deleteNewProductProductionPlanByIds(ids));
    }
}
