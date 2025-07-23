package com.ruoyi.myCode.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.myCode.domain.NewProductCompletion;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.myCode.domain.NewProductBom;
import com.ruoyi.myCode.service.INewProductBomService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 新产品BOMController
 * 
 * @author xj
 * @date 2025-07-22
 */
@RestController
@RequestMapping("/newproducts/bom")
public class NewProductBomController extends BaseController
{
    @Autowired
    private INewProductBomService newProductBomService;

    /**
     * 查询新产品BOM列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:bom:list')")
    @GetMapping("/list")
    public AjaxResult list(NewProductBom newProductBom)
    {
        List<NewProductBom> list = newProductBomService.selectNewProductBomList(newProductBom);
        return success(list);
    }

    /**
     * 导出新产品BOM列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:bom:export')")
    @Log(title = "新产品BOM", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewProductBom newProductBom,
                       @RequestParam(value = "issueRecord", required = false) Boolean issueRecord) // 新增参数
    {
        // 设置问题记录筛选标志
        newProductBom.setIssueRecordFilter(issueRecord != null && issueRecord);
        List<NewProductBom> list = newProductBomService.selectNewProductBomList(newProductBom);
        ExcelUtil<NewProductBom> util = new ExcelUtil<NewProductBom>(NewProductBom.class);
        util.exportExcel(response, list, "新产品BOM数据");
    }


    /**
     * 获取新产品BOM详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:bom:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newProductBomService.selectNewProductBomById(id));
    }

    /**
     * 新增新产品BOM
     */
    @PreAuthorize("@ss.hasPermi('newproducts:bom:add')")
    @Log(title = "新产品BOM", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewProductBom newProductBom)
    {
        return toAjax(newProductBomService.insertNewProductBom(newProductBom));
    }

    /**
     * 修改新产品BOM
     */
    @PreAuthorize("@ss.hasPermi('newproducts:bom:edit')")
    @Log(title = "新产品BOM", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewProductBom newProductBom)
    {
        return toAjax(newProductBomService.updateNewProductBom(newProductBom));
    }

    /**
     * 删除新产品BOM
     */
    @PreAuthorize("@ss.hasPermi('newproducts:bom:remove')")
    @Log(title = "新产品BOM", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newProductBomService.deleteNewProductBomByIds(ids));
    }
    @Log(title = "新产品BOM导入", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(
            @RequestParam("file") MultipartFile file,
            @RequestParam("projectCode") String projectCode,
            @RequestParam("projectName") String projectName) throws IOException {

        if (file.isEmpty()) {
            return AjaxResult.error("请选择要上传的文件！");
        }

        try (InputStream inputStream = file.getInputStream()) {
            newProductBomService.importBom(inputStream, projectCode, projectName);
            return AjaxResult.success("导入成功！");
        } catch (Exception e) {
            return AjaxResult.error("导入失败：" + e.getMessage());
        }
    }

    @GetMapping("/checkProjectDataExists")
    public AjaxResult checkProjectDataExists(@RequestParam String projectCode) {
        boolean exists = newProductBomService.checkProjectDataExists(projectCode);
        return AjaxResult.success(exists);
    }

}

