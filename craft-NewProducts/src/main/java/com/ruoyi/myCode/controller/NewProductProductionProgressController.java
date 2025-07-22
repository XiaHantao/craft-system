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
import com.ruoyi.myCode.domain.NewProductProductionProgress;
import com.ruoyi.myCode.service.INewProductProductionProgressService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品生产进度跟踪Controller
 * 
 * @author xj
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/newproducts/progress")
public class NewProductProductionProgressController extends BaseController
{
    @Autowired
    private INewProductProductionProgressService newProductProductionProgressService;

    /**
     * 查询新产品生产进度跟踪列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:progress:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewProductProductionProgress newProductProductionProgress)
    {
        startPage();
        List<NewProductProductionProgress> list = newProductProductionProgressService.selectNewProductProductionProgressList(newProductProductionProgress);
        return getDataTable(list);
    }

    /**
     * 导出新产品生产进度跟踪列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:progress:export')")
    @Log(title = "新产品生产进度跟踪", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewProductProductionProgress newProductProductionProgress)
    {
        List<NewProductProductionProgress> list = newProductProductionProgressService.selectNewProductProductionProgressList(newProductProductionProgress);
        ExcelUtil<NewProductProductionProgress> util = new ExcelUtil<NewProductProductionProgress>(NewProductProductionProgress.class);
        util.exportExcel(response, list, "新产品生产进度跟踪数据");
    }

    /**
     * 获取新产品生产进度跟踪详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:progress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newProductProductionProgressService.selectNewProductProductionProgressById(id));
    }

    /**
     * 新增新产品生产进度跟踪
     */
    @PreAuthorize("@ss.hasPermi('newproducts:progress:add')")
    @Log(title = "新产品生产进度跟踪", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewProductProductionProgress newProductProductionProgress)
    {
        return toAjax(newProductProductionProgressService.insertNewProductProductionProgress(newProductProductionProgress));
    }

    /**
     * 修改新产品生产进度跟踪
     */
    @PreAuthorize("@ss.hasPermi('newproducts:progress:edit')")
    @Log(title = "新产品生产进度跟踪", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewProductProductionProgress newProductProductionProgress)
    {
        return toAjax(newProductProductionProgressService.updateNewProductProductionProgress(newProductProductionProgress));
    }

    /**
     * 删除新产品生产进度跟踪
     */
    @PreAuthorize("@ss.hasPermi('newproducts:progress:remove')")
    @Log(title = "新产品生产进度跟踪", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newProductProductionProgressService.deleteNewProductProductionProgressByIds(ids));
    }
}
