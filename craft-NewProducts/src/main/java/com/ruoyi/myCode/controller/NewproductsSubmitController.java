package com.ruoyi.myCode.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.file.FileUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.myCode.domain.NewproductsSubmit;
import com.ruoyi.myCode.service.INewproductsSubmitService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品提交Controller
 * 
 * @author xj
 * @date 2025-02-26
 */
@RestController
@RequestMapping("/newproducts/submit")
public class NewproductsSubmitController extends BaseController
{
    @Autowired
    private INewproductsSubmitService newproductsSubmitService;

    /**
     * 查询新产品提交列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:submit:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewproductsSubmit newproductsSubmit)
    {
        startPage();
        List<NewproductsSubmit> list = newproductsSubmitService.selectNewproductsSubmitList(newproductsSubmit);
        return getDataTable(list);
    }

    /**
     * 导出新产品提交列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:submit:export')")
    @Log(title = "新产品提交", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewproductsSubmit newproductsSubmit)
    {
        List<NewproductsSubmit> list = newproductsSubmitService.selectNewproductsSubmitList(newproductsSubmit);
        ExcelUtil<NewproductsSubmit> util = new ExcelUtil<NewproductsSubmit>(NewproductsSubmit.class);
        util.exportExcel(response, list, "新产品提交数据");
    }

    /**
     * 获取新产品提交详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:submit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newproductsSubmitService.selectNewproductsSubmitById(id));
    }

    /**
     * 删除服务器上传文件
     * */
    @DeleteMapping("/file")
    public AjaxResult deleteFile(@RequestParam("path") String filePath) {
        try {

            boolean flag = FileUtils.DeleteFile(filePath); // 调用工具类删除文件
            return success("文件已删除");
        } catch (Exception e) {
            return error("文件删除失败");
        }
    }


    /**
     * 新增新产品提交
     */
    @PreAuthorize("@ss.hasPermi('newproducts:submit:add')")
    @Log(title = "新产品提交", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewproductsSubmit newproductsSubmit)
    {
        return toAjax(newproductsSubmitService.insertNewproductsSubmit(newproductsSubmit));
    }

    /**
     * 修改新产品提交
     */
    @PreAuthorize("@ss.hasPermi('newproducts:submit:edit') or @ss.hasPermi('newproducts:submit:check')")
    @Log(title = "新产品提交", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewproductsSubmit newproductsSubmit)
    {
        return toAjax(newproductsSubmitService.updateNewproductsSubmit(newproductsSubmit));
    }

    /**
     * 删除新产品提交
     */
    @PreAuthorize("@ss.hasPermi('newproducts:submit:remove')")
    @Log(title = "新产品提交", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newproductsSubmitService.deleteNewproductsSubmitByIds(ids));

    }
}
