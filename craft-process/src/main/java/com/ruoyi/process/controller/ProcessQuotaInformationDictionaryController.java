package com.ruoyi.process.controller;

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
import com.ruoyi.process.domain.ProcessQuotaInformationDictionary;
import com.ruoyi.process.service.IProcessQuotaInformationDictionaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工艺定额字典Controller
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
@RestController
@RequestMapping("/process/processQuotaInformation")
public class ProcessQuotaInformationDictionaryController extends BaseController
{
    @Autowired
    private IProcessQuotaInformationDictionaryService processQuotaInformationDictionaryService;

    /**
     * 查询工艺定额字典列表
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaInformation:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessQuotaInformationDictionary processQuotaInformationDictionary)
    {
        startPage();
        List<ProcessQuotaInformationDictionary> list = processQuotaInformationDictionaryService.selectProcessQuotaInformationDictionaryList(processQuotaInformationDictionary);
        return getDataTable(list);
    }

    /**
     * 导出工艺定额字典列表
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaInformation:export')")
    @Log(title = "工艺定额字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcessQuotaInformationDictionary processQuotaInformationDictionary)
    {
        List<ProcessQuotaInformationDictionary> list = processQuotaInformationDictionaryService.selectProcessQuotaInformationDictionaryList(processQuotaInformationDictionary);
        ExcelUtil<ProcessQuotaInformationDictionary> util = new ExcelUtil<ProcessQuotaInformationDictionary>(ProcessQuotaInformationDictionary.class);
        util.exportExcel(response, list, "工艺定额字典数据");
    }

    /**
     * 获取工艺定额字典详细信息
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaInformation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(processQuotaInformationDictionaryService.selectProcessQuotaInformationDictionaryById(id));
    }

    /**
     * 新增工艺定额字典
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaInformation:add')")
    @Log(title = "工艺定额字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcessQuotaInformationDictionary processQuotaInformationDictionary)
    {
        return toAjax(processQuotaInformationDictionaryService.insertProcessQuotaInformationDictionary(processQuotaInformationDictionary));
    }

    /**
     * 修改工艺定额字典
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaInformation:edit')")
    @Log(title = "工艺定额字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcessQuotaInformationDictionary processQuotaInformationDictionary)
    {
        return toAjax(processQuotaInformationDictionaryService.updateProcessQuotaInformationDictionary(processQuotaInformationDictionary));
    }

    /**
     * 删除工艺定额字典
     */
    @PreAuthorize("@ss.hasPermi('process:processQuotaInformation:remove')")
    @Log(title = "工艺定额字典", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(processQuotaInformationDictionaryService.deleteProcessQuotaInformationDictionaryByIds(ids));
    }
}
