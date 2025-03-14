package com.ruoyi.marketanalysis.controller;

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
import com.ruoyi.marketanalysis.domain.TestReportTable;
import com.ruoyi.marketanalysis.service.ITestReportTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 试验报告Controller
 * 
 * @author ruoyi
 * @date 2025-02-20
 */
@RestController
@RequestMapping("/marketanalysis/report")
public class TestReportTableController extends BaseController
{
    @Autowired
    private ITestReportTableService testReportTableService;

    /**
     * 查询试验报告列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestReportTable testReportTable)
    {
        startPage();
        List<TestReportTable> list = testReportTableService.selectTestReportTableList(testReportTable);
        return getDataTable(list);
    }

    /**
     * 导出试验报告列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:report:export')")
    @Log(title = "试验报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TestReportTable testReportTable)
    {
        List<TestReportTable> list = testReportTableService.selectTestReportTableList(testReportTable);
        ExcelUtil<TestReportTable> util = new ExcelUtil<TestReportTable>(TestReportTable.class);
        util.exportExcel(response, list, "试验报告数据");
    }

    /**
     * 获取试验报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(testReportTableService.selectTestReportTableById(id));
    }

    /**
     * 新增试验报告
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:report:add')")
    @Log(title = "试验报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestReportTable testReportTable)
    {
        return toAjax(testReportTableService.insertTestReportTable(testReportTable));
    }

    /**
     * 修改试验报告
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:report:edit')")
    @Log(title = "试验报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestReportTable testReportTable)
    {
        return toAjax(testReportTableService.updateTestReportTable(testReportTable));
    }

    /**
     * 删除试验报告
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:report:remove')")
    @Log(title = "试验报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(testReportTableService.deleteTestReportTableByIds(ids));
    }
}
