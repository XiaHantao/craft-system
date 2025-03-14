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
import com.ruoyi.marketanalysis.domain.ConfigureImageTable;
import com.ruoyi.marketanalysis.service.IConfigureImageTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 配置图片表Controller
 * 
 * @author ruoyi
 * @date 2025-03-11
 */
@RestController
@RequestMapping("/marketanalysis/image")
public class ConfigureImageTableController extends BaseController
{
    @Autowired
    private IConfigureImageTableService configureImageTableService;

    /**
     * 查询配置图片表列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:image:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConfigureImageTable configureImageTable)
    {
        startPage();
        List<ConfigureImageTable> list = configureImageTableService.selectConfigureImageTableList(configureImageTable);
        return getDataTable(list);
    }

    /**
     * 导出配置图片表列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:image:export')")
    @Log(title = "配置图片表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConfigureImageTable configureImageTable)
    {
        List<ConfigureImageTable> list = configureImageTableService.selectConfigureImageTableList(configureImageTable);
        ExcelUtil<ConfigureImageTable> util = new ExcelUtil<ConfigureImageTable>(ConfigureImageTable.class);
        util.exportExcel(response, list, "配置图片表数据");
    }

    /**
     * 获取配置图片表详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:image:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(configureImageTableService.selectConfigureImageTableById(id));
    }

    /**
     * 新增配置图片表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:image:add')")
    @Log(title = "配置图片表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConfigureImageTable configureImageTable)
    {
        return toAjax(configureImageTableService.insertConfigureImageTable(configureImageTable));
    }

    /**
     * 修改配置图片表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:image:edit')")
    @Log(title = "配置图片表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConfigureImageTable configureImageTable)
    {
        return toAjax(configureImageTableService.updateConfigureImageTable(configureImageTable));
    }

    /**
     * 删除配置图片表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:image:remove')")
    @Log(title = "配置图片表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(configureImageTableService.deleteConfigureImageTableByIds(ids));
    }
}
