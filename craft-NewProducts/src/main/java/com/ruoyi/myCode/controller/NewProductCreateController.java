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
import com.ruoyi.myCode.domain.NewProductCreate;
import com.ruoyi.myCode.service.INewProductCreateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品信息Controller
 * 
 * @author xj
 * @date 2025-07-20
 */
@RestController
@RequestMapping("/newproducts/create")
public class NewProductCreateController extends BaseController
{
    @Autowired
    private INewProductCreateService newProductCreateService;

    /**
     * 查询新产品信息列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:create:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewProductCreate newProductCreate)
    {
        startPage();
        List<NewProductCreate> list = newProductCreateService.selectNewProductCreateList(newProductCreate);
        return getDataTable(list);
    }

    /**
     * 导出新产品信息列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:create:export')")
    @Log(title = "新产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewProductCreate newProductCreate)
    {
        List<NewProductCreate> list = newProductCreateService.selectNewProductCreateList(newProductCreate);
        ExcelUtil<NewProductCreate> util = new ExcelUtil<NewProductCreate>(NewProductCreate.class);
        util.exportExcel(response, list, "新产品信息数据");
    }

    /**
     * 获取新产品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:create:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newProductCreateService.selectNewProductCreateById(id));
    }

    /**
     * 新增新产品信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:create:add')")
    @Log(title = "新产品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewProductCreate newProductCreate)
    {
        return toAjax(newProductCreateService.insertNewProductCreate(newProductCreate));
    }

    /**
     * 修改新产品信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:create:edit')")
    @Log(title = "新产品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewProductCreate newProductCreate)
    {
        return toAjax(newProductCreateService.updateNewProductCreate(newProductCreate));
    }

    /**
     * 删除新产品信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:create:remove')")
    @Log(title = "新产品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newProductCreateService.deleteNewProductCreateByIds(ids));
    }
}
