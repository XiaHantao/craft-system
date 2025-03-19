package com.ruoyi.workClothes.controller;

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
import com.ruoyi.workClothes.domain.SelfMadePartsLedger;
import com.ruoyi.workClothes.service.ISelfMadePartsLedgerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 自制件台账Controller
 * 
 * @author ruoyi
 * @date 2025-03-04
 */
@RestController
@RequestMapping("/ToolingModule/SelfMadePartsLedger")
public class SelfMadePartsLedgerController extends BaseController
{
    @Autowired
    private ISelfMadePartsLedgerService selfMadePartsLedgerService;

    /**
     * 查询自制件台账列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:SelfMadePartsLedger:list')")
    @GetMapping("/list")
    public TableDataInfo list(SelfMadePartsLedger selfMadePartsLedger)
    {
        startPage();
        List<SelfMadePartsLedger> list = selfMadePartsLedgerService.selectSelfMadePartsLedgerList(selfMadePartsLedger);
        return getDataTable(list);
    }

    /**
     * 导出自制件台账列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:SelfMadePartsLedger:export')")
    @Log(title = "自制件台账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelfMadePartsLedger selfMadePartsLedger)
    {
        List<SelfMadePartsLedger> list = selfMadePartsLedgerService.selectSelfMadePartsLedgerList(selfMadePartsLedger);
        ExcelUtil<SelfMadePartsLedger> util = new ExcelUtil<SelfMadePartsLedger>(SelfMadePartsLedger.class);
        util.exportExcel(response, list, "自制件台账数据");
    }

    /**
     * 获取自制件台账详细信息
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:SelfMadePartsLedger:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(selfMadePartsLedgerService.selectSelfMadePartsLedgerById(id));
    }

    /**
     * 新增自制件台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:SelfMadePartsLedger:add')")
    @Log(title = "自制件台账", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SelfMadePartsLedger selfMadePartsLedger)
    {
        return toAjax(selfMadePartsLedgerService.insertSelfMadePartsLedger(selfMadePartsLedger));
    }

    /**
     * 修改自制件台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:SelfMadePartsLedger:edit')")
    @Log(title = "自制件台账", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SelfMadePartsLedger selfMadePartsLedger)
    {
        return toAjax(selfMadePartsLedgerService.updateSelfMadePartsLedger(selfMadePartsLedger));
    }

    /**
     * 删除自制件台账
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:SelfMadePartsLedger:remove')")
    @Log(title = "自制件台账", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(selfMadePartsLedgerService.deleteSelfMadePartsLedgerByIds(ids));
    }
}
