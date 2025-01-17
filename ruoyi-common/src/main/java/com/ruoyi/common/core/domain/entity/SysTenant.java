package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 租户对象 sys_tenant
 *
 * @author xuqihang
 * @date 2023-05-21
 */

public class SysTenant extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    private Long tenantId;


    /**
     * 用户id
     */
    private Long userId;


    /**
     * 部门id
     */
    private Long deptId;


    /**
     * 显示顺序
     */
    private Integer orderNum;


    /**
     * 租户状态（0正常 1停用）
     */
    private String status;


    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    /**
     * 关联用户
     */
    private SysUser sysUser;

    /**
     * 关联机构
     */
    private SysDept sysDept;

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }


    public Long getTenantId() {
        return tenantId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


    public Long getDeptId() {
        return deptId;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }


    public Integer getOrderNum() {
        return orderNum;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


    public String getDelFlag() {
        return delFlag;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tenantId", getTenantId())
                .append("userId", getUserId())
                .append("deptId", getDeptId())
                .append("orderNum", getOrderNum())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }

}
