package com.ruoyi.common.core.domain;


/**
 * 租户实体 Entity基类
 * 
 * @author ruoyi
 */
public class BaseTenantEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    private Long tenantId;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
