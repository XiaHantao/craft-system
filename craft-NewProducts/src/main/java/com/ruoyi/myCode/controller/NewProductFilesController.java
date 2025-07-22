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
import com.ruoyi.myCode.domain.NewProductFiles;
import com.ruoyi.myCode.service.INewProductFilesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品文件提交Controller
 * 
 * @author xj
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/newproducts/files")
public class NewProductFilesController extends BaseController
{
    @Autowired
    private INewProductFilesService newProductFilesService;

    /**
     * 查询新产品文件提交列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:files:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewProductFiles newProductFiles)
    {
        startPage();
        List<NewProductFiles> list = newProductFilesService.selectNewProductFilesList(newProductFiles);
        return getDataTable(list);
    }

    /**
     * 导出新产品文件提交列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:files:export')")
    @Log(title = "新产品文件提交", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewProductFiles newProductFiles)
    {
        List<NewProductFiles> list = newProductFilesService.selectNewProductFilesList(newProductFiles);
        ExcelUtil<NewProductFiles> util = new ExcelUtil<NewProductFiles>(NewProductFiles.class);
        util.exportExcel(response, list, "新产品文件提交数据");
    }

    /**
     * 获取新产品文件提交详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:files:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newProductFilesService.selectNewProductFilesById(id));
    }

    /**
     * 新增新产品文件提交
     */
    @PreAuthorize("@ss.hasPermi('newproducts:files:add')")
    @Log(title = "新产品文件提交", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewProductFiles newProductFiles)
    {
        return toAjax(newProductFilesService.insertNewProductFiles(newProductFiles));
    }

    /**
     * 修改新产品文件提交
     */
    @PreAuthorize("@ss.hasPermi('newproducts:files:edit')")
    @Log(title = "新产品文件提交", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewProductFiles newProductFiles)
    {
        return toAjax(newProductFilesService.updateNewProductFiles(newProductFiles));
    }

    /**
     * 删除新产品文件提交
     */
    @PreAuthorize("@ss.hasPermi('newproducts:files:remove')")
    @Log(title = "新产品文件提交", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newProductFilesService.deleteNewProductFilesByIds(ids));
    }
}
