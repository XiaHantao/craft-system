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
import com.ruoyi.myCode.domain.NewProductCompletion;
import com.ruoyi.myCode.service.INewProductCompletionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品生产完成Controller
 * 
 * @author xj
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/newproducts/completion")
public class NewProductCompletionController extends BaseController
{
    @Autowired
    private INewProductCompletionService newProductCompletionService;

    /**
     * 查询新产品生产完成列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:completion:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewProductCompletion newProductCompletion)
    {
        startPage();
        List<NewProductCompletion> list = newProductCompletionService.selectNewProductCompletionList(newProductCompletion);
        return getDataTable(list);
    }

    /**
     * 导出新产品生产完成列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:completion:export')")
    @Log(title = "新产品生产完成", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewProductCompletion newProductCompletion)
    {
        List<NewProductCompletion> list = newProductCompletionService.selectNewProductCompletionList(newProductCompletion);
        ExcelUtil<NewProductCompletion> util = new ExcelUtil<NewProductCompletion>(NewProductCompletion.class);
        util.exportExcel(response, list, "新产品生产完成数据");
    }

    /**
     * 获取新产品生产完成详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:completion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newProductCompletionService.selectNewProductCompletionById(id));
    }

    /**
     * 新增新产品生产完成
     */
    @PreAuthorize("@ss.hasPermi('newproducts:completion:add')")
    @Log(title = "新产品生产完成", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewProductCompletion newProductCompletion)
    {
        return toAjax(newProductCompletionService.insertNewProductCompletion(newProductCompletion));
    }

    /**
     * 修改新产品生产完成
     */
    @PreAuthorize("@ss.hasPermi('newproducts:completion:edit')")
    @Log(title = "新产品生产完成", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewProductCompletion newProductCompletion)
    {
        return toAjax(newProductCompletionService.updateNewProductCompletion(newProductCompletion));
    }

    /**
     * 删除新产品生产完成
     */
    @PreAuthorize("@ss.hasPermi('newproducts:completion:remove')")
    @Log(title = "新产品生产完成", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newProductCompletionService.deleteNewProductCompletionByIds(ids));
    }
}
