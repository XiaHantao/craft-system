package com.ruoyi.common.config;

import com.ruoyi.common.utils.uuid.SnowflakeIdUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取雪花ID相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "snowid")
public class SnowIdConfig {


    /**
     * 获取雪花ID
     *
     * @return long
     */
    public long getSnowId() {
        return SnowflakeIdUtils.getInstance(this.workerId, this.datacenterId).nextId();
    }

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

}
