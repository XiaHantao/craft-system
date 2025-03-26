package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysMessageNotification;

/**
 * 消息通知Service接口
 * 
 * @author Kanna Bush
 * @date 2025-03-16
 */
public interface ISysMessageNotificationService 
{
    /**
     * 查询消息通知
     * 
     * @param noticeId 消息通知主键
     * @return 消息通知
     */
    public SysMessageNotification selectSysMessageNotificationByNoticeId(Long noticeId);

    /**
     * 查询消息通知列表
     * 
     * @param sysMessageNotification 消息通知
     * @return 消息通知集合
     */
    public List<SysMessageNotification> selectSysMessageNotificationList(SysMessageNotification sysMessageNotification);

    /**
     * 新增消息通知
     * 
     * @param sysMessageNotification 消息通知
     * @return 结果
     */
    public int insertSysMessageNotification(SysMessageNotification sysMessageNotification);

    /**
     * 修改消息通知
     * 
     * @param sysMessageNotification 消息通知
     * @return 结果
     */
    public int updateSysMessageNotification(SysMessageNotification sysMessageNotification);

    /**
     * 批量删除消息通知
     * 
     * @param noticeIds 需要删除的消息通知主键集合
     * @return 结果
     */
    public int deleteSysMessageNotificationByNoticeIds(Long[] noticeIds);

    /**
     * 删除消息通知信息
     * 
     * @param noticeId 消息通知主键
     * @return 结果
     */
    public int deleteSysMessageNotificationByNoticeId(Long noticeId);
}
