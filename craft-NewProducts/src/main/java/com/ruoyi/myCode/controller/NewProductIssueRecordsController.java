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
import com.ruoyi.myCode.domain.NewProductIssueRecords;
import com.ruoyi.myCode.service.INewProductIssueRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新产品生产问题记录Controller
 * 
 * @author xj
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/newproducts/records")
public class NewProductIssueRecordsController extends BaseController
{
    @Autowired
    private INewProductIssueRecordsService newProductIssueRecordsService;

    /**
     * 查询新产品生产问题记录列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewProductIssueRecords newProductIssueRecords)
    {
        startPage();
        List<NewProductIssueRecords> list = newProductIssueRecordsService.selectNewProductIssueRecordsList(newProductIssueRecords);
        return getDataTable(list);
    }

    /**
     * 导出新产品生产问题记录列表
     */
    @PreAuthorize("@ss.hasPermi('newproducts:records:export')")
    @Log(title = "新产品生产问题记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewProductIssueRecords newProductIssueRecords)
    {
        List<NewProductIssueRecords> list = newProductIssueRecordsService.selectNewProductIssueRecordsList(newProductIssueRecords);
        ExcelUtil<NewProductIssueRecords> util = new ExcelUtil<NewProductIssueRecords>(NewProductIssueRecords.class);
        util.exportExcel(response, list, "新产品生产问题记录数据");
    }

    /**
     * 获取新产品生产问题记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('newproducts:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newProductIssueRecordsService.selectNewProductIssueRecordsById(id));
    }

    /**
     * 新增新产品生产问题记录
     */
    @PreAuthorize("@ss.hasPermi('newproducts:records:add')")
    @Log(title = "新产品生产问题记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewProductIssueRecords newProductIssueRecords)
    {
        return toAjax(newProductIssueRecordsService.insertNewProductIssueRecords(newProductIssueRecords));
    }

    /**
     * 修改新产品生产问题记录
     */
    @PreAuthorize("@ss.hasPermi('newproducts:records:edit')")
    @Log(title = "新产品生产问题记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewProductIssueRecords newProductIssueRecords)
    {
        return toAjax(newProductIssueRecordsService.updateNewProductIssueRecords(newProductIssueRecords));
    }

    /**
     * 删除新产品生产问题记录
     */
    @PreAuthorize("@ss.hasPermi('newproducts:records:remove')")
    @Log(title = "新产品生产问题记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newProductIssueRecordsService.deleteNewProductIssueRecordsByIds(ids));
    }
}
