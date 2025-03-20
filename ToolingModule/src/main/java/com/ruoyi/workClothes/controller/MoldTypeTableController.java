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
import com.ruoyi.workClothes.domain.MoldTypeTable;
import com.ruoyi.workClothes.service.IMoldTypeTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工装类别字典Controller
 * 
 * @author ruoyi
 * @date 2025-03-06
 */
@RestController
@RequestMapping("/ToolingModule/MoldType")
public class MoldTypeTableController extends BaseController
{
    @Autowired
    private IMoldTypeTableService moldTypeTableService;

    /**
     * 查询工装类别字典列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:MoldType:list')")
    @GetMapping("/list")
    public TableDataInfo list(MoldTypeTable moldTypeTable)
    {
        startPage();
        List<MoldTypeTable> list = moldTypeTableService.selectMoldTypeTableList(moldTypeTable);
        return getDataTable(list);
    }
    /**
     * 查询所有工装类别字典的名称
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:MoldType:list')")
    @GetMapping("/listname")
    public AjaxResult getAllIds() {
        List<String> list = moldTypeTableService.listname();
        return success(list);
    }


    /**
     * 导出工装类别字典列表
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:MoldType:export')")
    @Log(title = "工装类别字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MoldTypeTable moldTypeTable)
    {
        List<MoldTypeTable> list = moldTypeTableService.selectMoldTypeTableList(moldTypeTable);
        ExcelUtil<MoldTypeTable> util = new ExcelUtil<MoldTypeTable>(MoldTypeTable.class);
        util.exportExcel(response, list, "工装类别字典数据");
    }

    /**
     * 获取工装类别字典详细信息
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:MoldType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(moldTypeTableService.selectMoldTypeTableById(id));
    }

    /**
     * 新增工装类别字典
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:MoldType:add')")
    @Log(title = "工装类别字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MoldTypeTable moldTypeTable)
    {
        return toAjax(moldTypeTableService.insertMoldTypeTable(moldTypeTable));
    }

    /**
     * 修改工装类别字典
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:MoldType:edit')")
    @Log(title = "工装类别字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MoldTypeTable moldTypeTable)
    {
        return toAjax(moldTypeTableService.updateMoldTypeTable(moldTypeTable));
    }

    /**
     * 删除工装类别字典
     */
    @PreAuthorize("@ss.hasPermi('ToolingModule:MoldType:remove')")
    @Log(title = "工装类别字典", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(moldTypeTableService.deleteMoldTypeTableByIds(ids));
    }
}
