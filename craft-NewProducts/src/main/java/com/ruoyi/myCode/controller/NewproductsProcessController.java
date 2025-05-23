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
import com.ruoyi.myCode.domain.NewproductsProcess;
import com.ruoyi.myCode.service.INewproductsProcessService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品试制过程Controller
 * 
 * @author xj
 * @date 2025-02-26
 */
@RestController
@RequestMapping("/newproducts/process")
public class NewproductsProcessController extends BaseController
{
    @Autowired
    private INewproductsProcessService newproductsProcessService;

    /**
     * 查询新产品试制过程列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:process:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewproductsProcess newproductsProcess)
    {
        startPage();
        List<NewproductsProcess> list = newproductsProcessService.selectNewproductsProcessList(newproductsProcess);
        return getDataTable(list);
    }

    /**
     * 导出新产品试制过程列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:process:export')")
    @Log(title = "新产品试制过程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewproductsProcess newproductsProcess)
    {
        List<NewproductsProcess> list = newproductsProcessService.selectNewproductsProcessList(newproductsProcess);
        ExcelUtil<NewproductsProcess> util = new ExcelUtil<NewproductsProcess>(NewproductsProcess.class);
        util.exportExcel(response, list, "新产品试制过程数据");
    }

    /**
     * 获取新产品试制过程详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:process:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newproductsProcessService.selectNewproductsProcessById(id));
    }

    /**
     * 新增新产品试制过程
     */
    @PreAuthorize("@ss.hasPermi('newproducts:process:add')")
    @Log(title = "新产品试制过程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewproductsProcess newproductsProcess)
    {
        return toAjax(newproductsProcessService.insertNewproductsProcess(newproductsProcess));
    }

    /**
     * 修改新产品试制过程
     */
    @PreAuthorize("@ss.hasPermi('newproducts:process:edit') or @ss.hasPermi('newproducts:process:updatea') or @ss.hasPermi('newproducts:process:updateb')")
    @Log(title = "新产品试制过程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewproductsProcess newproductsProcess)
    {
        return toAjax(newproductsProcessService.updateNewproductsProcess(newproductsProcess));
    }

    /**
     * 删除新产品试制过程
     */
    @PreAuthorize("@ss.hasPermi('newproducts:process:remove')")
    @Log(title = "新产品试制过程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newproductsProcessService.deleteNewproductsProcessByIds(ids));
    }
}
