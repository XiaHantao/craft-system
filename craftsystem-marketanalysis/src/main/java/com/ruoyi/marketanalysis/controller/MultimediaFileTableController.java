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
import com.ruoyi.marketanalysis.domain.MultimediaFileTable;
import com.ruoyi.marketanalysis.service.IMultimediaFileTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 多媒体文件Controller
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/marketanalysis/media")
public class MultimediaFileTableController extends BaseController
{
    @Autowired
    private IMultimediaFileTableService multimediaFileTableService;

    /**
     * 查询多媒体文件列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:media:list')")
    @GetMapping("/list")
    public TableDataInfo list(MultimediaFileTable multimediaFileTable)
    {
        startPage();
        List<MultimediaFileTable> list = multimediaFileTableService.selectMultimediaFileTableList(multimediaFileTable);
        return getDataTable(list);
    }

    /**
     * 导出多媒体文件列表
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:media:export')")
    @Log(title = "多媒体文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MultimediaFileTable multimediaFileTable)
    {
        List<MultimediaFileTable> list = multimediaFileTableService.selectMultimediaFileTableList(multimediaFileTable);
        ExcelUtil<MultimediaFileTable> util = new ExcelUtil<MultimediaFileTable>(MultimediaFileTable.class);
        util.exportExcel(response, list, "多媒体文件数据");
    }

    /**
     * 获取多媒体文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:media:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(multimediaFileTableService.selectMultimediaFileTableById(id));
    }

    /**
     * 新增多媒体文件
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:media:add')")
    @Log(title = "多媒体文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MultimediaFileTable multimediaFileTable)
    {
        return toAjax(multimediaFileTableService.insertMultimediaFileTable(multimediaFileTable));
    }

    /**
     * 修改多媒体文件
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:media:edit')")
    @Log(title = "多媒体文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MultimediaFileTable multimediaFileTable)
    {
        return toAjax(multimediaFileTableService.updateMultimediaFileTable(multimediaFileTable));
    }

    /**
     * 删除多媒体文件
     */
    @PreAuthorize("@ss.hasPermi('marketanalysis:media:remove')")
    @Log(title = "多媒体文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(multimediaFileTableService.deleteMultimediaFileTableByIds(ids));
    }
}
