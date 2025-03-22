package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息通知对象 sys_message_notification
 * 
 * @author Kanna Bush
 * @date 2025-03-16
 */
@Data
public class SysMessageNotification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知id */
    private Long noticeId;

    /** 通知标题 */
    @Excel(name = "通知标题")
    private String noticeTitle;

    /** 通知内容 */
    @Excel(name = "通知内容")
    private String noticeContent;

    /** 发起人 */
    @Excel(name = "发起人")
    private String createdBy;

    /** 发起时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发起时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 执行人 */
    @Excel(name = "执行人")
    private String executedBy;

    /** 执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "执行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date executedTime;

    /** 路径 */
    @Excel(name = "路径")
    private String path;

    /** 路径id */
    @Excel(name = "路径id")
    private String pathId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setNoticeId(Long noticeId) 
    {
        this.noticeId = noticeId;
    }

    public Long getNoticeId() 
    {
        return noticeId;
    }
    public void setNoticeTitle(String noticeTitle) 
    {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeTitle() 
    {
        return noticeTitle;
    }
    public void setNoticeContent(String noticeContent) 
    {
        this.noticeContent = noticeContent;
    }

    public String getNoticeContent() 
    {
        return noticeContent;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setExecutedBy(String executedBy) 
    {
        this.executedBy = executedBy;
    }

    public String getExecutedBy() 
    {
        return executedBy;
    }
    public void setExecutedTime(Date executedTime) 
    {
        this.executedTime = executedTime;
    }

    public Date getExecutedTime() 
    {
        return executedTime;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("noticeTitle", getNoticeTitle())
            .append("noticeContent", getNoticeContent())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("executedBy", getExecutedBy())
            .append("executedTime", getExecutedTime())
            .append("path", getPath())
            .append("status", getStatus())
            .toString();
    }
}
