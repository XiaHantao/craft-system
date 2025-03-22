package com.ruoyi.myCode.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.myCode.domain.NewproductsPlan;
import com.ruoyi.myCode.service.INewproductsPlanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品生产计划Controller
 * 
 * @author xj
 * @date 2025-03-03
 */
@RestController
@RequestMapping("/newproducts/plan")
public class NewproductsPlanController extends BaseController
{
    @Autowired
    private INewproductsPlanService newproductsPlanService;

    /**
     * 查询新产品生产计划列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewproductsPlan newproductsPlan)
    {
        startPage();
        List<NewproductsPlan> list = newproductsPlanService.selectNewproductsPlanList(newproductsPlan);
        return getDataTable(list);
    }

    /**
     * 导出新产品生产计划列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:export')")
    @Log(title = "新产品生产计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewproductsPlan newproductsPlan)
    {
        List<NewproductsPlan> list = newproductsPlanService.selectNewproductsPlanList(newproductsPlan);
        ExcelUtil<NewproductsPlan> util = new ExcelUtil<NewproductsPlan>(NewproductsPlan.class);
        util.exportExcel(response, list, "新产品生产计划数据");
    }

    /**
     * 获取新产品生产计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newproductsPlanService.selectNewproductsPlanById(id));
    }

    /**
     * 新增新产品生产计划
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:add')")
    @Log(title = "新产品生产计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewproductsPlan newproductsPlan)
    {
        return toAjax(newproductsPlanService.insertNewproductsPlan(newproductsPlan));
    }

    /**
     * 修改新产品生产计划
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:edit')")
    @Log(title = "新产品生产计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewproductsPlan newproductsPlan)
    {
        return toAjax(newproductsPlanService.updateNewproductsPlan(newproductsPlan));
    }

    /**
     * 删除新产品生产计划
     */
    @PreAuthorize("@ss.hasPermi('newproducts:plan:remove')")
    @Log(title = "新产品生产计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newproductsPlanService.deleteNewproductsPlanByIds(ids));
    }

    /**
     * 查询任意一张表单最新数据
     **/




}
