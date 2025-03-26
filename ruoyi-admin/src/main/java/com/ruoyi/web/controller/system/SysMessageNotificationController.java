package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.SysMessageNotification;
import com.ruoyi.system.service.ISysMessageNotificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 消息通知Controller
 * 
 * @author Kanna Bush
 * @date 2025-03-16
 */
@RestController
@RequestMapping("/system/sysMessageNotification")
public class SysMessageNotificationController extends BaseController
{
    @Autowired
    private ISysMessageNotificationService sysMessageNotificationService;

    /**
     * 查询消息通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:sysMessageNotification:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMessageNotification sysMessageNotification)
    {
        startPage();
        List<SysMessageNotification> list = sysMessageNotificationService.selectSysMessageNotificationList(sysMessageNotification);
        return getDataTable(list);
    }

    /**
     * 导出消息通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:sysMessageNotification:export')")
    @Log(title = "消息通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMessageNotification sysMessageNotification)
    {
        List<SysMessageNotification> list = sysMessageNotificationService.selectSysMessageNotificationList(sysMessageNotification);
        ExcelUtil<SysMessageNotification> util = new ExcelUtil<SysMessageNotification>(SysMessageNotification.class);
        util.exportExcel(response, list, "消息通知数据");
    }

    /**
     * 获取消息通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sysMessageNotification:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Long noticeId)
    {
        return success(sysMessageNotificationService.selectSysMessageNotificationByNoticeId(noticeId));
    }

    /**
     * 新增消息通知
     */
    @PreAuthorize("@ss.hasPermi('system:sysMessageNotification:add')")
    @Log(title = "消息通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMessageNotification sysMessageNotification)
    {
        return toAjax(sysMessageNotificationService.insertSysMessageNotification(sysMessageNotification));
    }

    /**
     * 修改消息通知
     */
    @PreAuthorize("@ss.hasPermi('system:sysMessageNotification:edit')")
    @Log(title = "消息通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMessageNotification sysMessageNotification)
    {
        return toAjax(sysMessageNotificationService.updateSysMessageNotification(sysMessageNotification));
    }

    /**
     * 删除消息通知
     */
    @PreAuthorize("@ss.hasPermi('system:sysMessageNotification:remove')")
    @Log(title = "消息通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(sysMessageNotificationService.deleteSysMessageNotificationByNoticeIds(noticeIds));
    }
}
