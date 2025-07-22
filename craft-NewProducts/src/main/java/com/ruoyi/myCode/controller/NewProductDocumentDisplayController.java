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
import com.ruoyi.myCode.domain.NewProductDocumentDisplay;
import com.ruoyi.myCode.service.INewProductDocumentDisplayService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品项目文件展示Controller
 * 
 * @author xj
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/newproducts/display")
public class NewProductDocumentDisplayController extends BaseController
{
    @Autowired
    private INewProductDocumentDisplayService newProductDocumentDisplayService;

    /**
     * 查询新产品项目文件展示列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:display:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewProductDocumentDisplay newProductDocumentDisplay)
    {
        startPage();
        List<NewProductDocumentDisplay> list = newProductDocumentDisplayService.selectNewProductDocumentDisplayList(newProductDocumentDisplay);
        return getDataTable(list);
    }

    /**
     * 导出新产品项目文件展示列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:display:export')")
    @Log(title = "新产品项目文件展示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewProductDocumentDisplay newProductDocumentDisplay)
    {
        List<NewProductDocumentDisplay> list = newProductDocumentDisplayService.selectNewProductDocumentDisplayList(newProductDocumentDisplay);
        ExcelUtil<NewProductDocumentDisplay> util = new ExcelUtil<NewProductDocumentDisplay>(NewProductDocumentDisplay.class);
        util.exportExcel(response, list, "新产品项目文件展示数据");
    }

    /**
     * 获取新产品项目文件展示详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:display:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newProductDocumentDisplayService.selectNewProductDocumentDisplayById(id));
    }

    /**
     * 新增新产品项目文件展示
     */
    @PreAuthorize("@ss.hasPermi('newproducts:display:add')")
    @Log(title = "新产品项目文件展示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewProductDocumentDisplay newProductDocumentDisplay)
    {
        return toAjax(newProductDocumentDisplayService.insertNewProductDocumentDisplay(newProductDocumentDisplay));
    }

    /**
     * 修改新产品项目文件展示
     */
    @PreAuthorize("@ss.hasPermi('newproducts:display:edit')")
    @Log(title = "新产品项目文件展示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewProductDocumentDisplay newProductDocumentDisplay)
    {
        return toAjax(newProductDocumentDisplayService.updateNewProductDocumentDisplay(newProductDocumentDisplay));
    }

    /**
     * 删除新产品项目文件展示
     */
    @PreAuthorize("@ss.hasPermi('newproducts:display:remove')")
    @Log(title = "新产品项目文件展示", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newProductDocumentDisplayService.deleteNewProductDocumentDisplayByIds(ids));
    }
}
