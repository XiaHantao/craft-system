package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMessageNotificationMapper;
import com.ruoyi.system.domain.SysMessageNotification;
import com.ruoyi.system.service.ISysMessageNotificationService;

/**
 * 消息通知Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-03-16
 */
@Service
public class SysMessageNotificationServiceImpl implements ISysMessageNotificationService 
{
    @Autowired
    private SysMessageNotificationMapper sysMessageNotificationMapper;

    /**
     * 查询消息通知
     * 
     * @param noticeId 消息通知主键
     * @return 消息通知
     */
    @Override
    public SysMessageNotification selectSysMessageNotificationByNoticeId(Long noticeId)
    {
        return sysMessageNotificationMapper.selectSysMessageNotificationByNoticeId(noticeId);
    }

    /**
     * 查询消息通知列表
     * 
     * @param sysMessageNotification 消息通知
     * @return 消息通知
     */
    @Override
    public List<SysMessageNotification> selectSysMessageNotificationList(SysMessageNotification sysMessageNotification)
    {
        return sysMessageNotificationMapper.selectSysMessageNotificationList(sysMessageNotification);
    }

    /**
     * 新增消息通知
     * 
     * @param sysMessageNotification 消息通知
     * @return 结果
     */
    @Override
    public int insertSysMessageNotification(SysMessageNotification sysMessageNotification)
    {
        return sysMessageNotificationMapper.insertSysMessageNotification(sysMessageNotification);
    }

    /**
     * 修改消息通知
     * 
     * @param sysMessageNotification 消息通知
     * @return 结果
     */
    @Override
    public int updateSysMessageNotification(SysMessageNotification sysMessageNotification)
    {
        return sysMessageNotificationMapper.updateSysMessageNotification(sysMessageNotification);
    }

    /**
     * 批量删除消息通知
     * 
     * @param noticeIds 需要删除的消息通知主键
     * @return 结果
     */
    @Override
    public int deleteSysMessageNotificationByNoticeIds(Long[] noticeIds)
    {
        return sysMessageNotificationMapper.deleteSysMessageNotificationByNoticeIds(noticeIds);
    }

    /**
     * 删除消息通知信息
     * 
     * @param noticeId 消息通知主键
     * @return 结果
     */
    @Override
    public int deleteSysMessageNotificationByNoticeId(Long noticeId)
    {
        return sysMessageNotificationMapper.deleteSysMessageNotificationByNoticeId(noticeId);
    }
}
