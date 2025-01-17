package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysTenant;
import java.util.List;

/**
 * 租户Mapper接口
 *
 * @author xuqihang
 * @date 2023-05-21
 */
public interface SysTenantMapper {

    /**
     * 查询租户
     *
     * @param tenantId 租户主键
     * @return 租户
     */
    public SysTenant selectSysTenantByTenantId(Long tenantId);

    /**
     * 查询租户列表
     *
     * @param sysTenant 租户
     * @return 租户集合
     */
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant);

    /**
     * 新增租户
     *
     * @param sysTenant 租户
     * @return 结果
     */
    public int insertSysTenant(SysTenant sysTenant);

    /**
     * 修改租户
     *
     * @param sysTenant 租户
     * @return 结果
     */
    public int updateSysTenant(SysTenant sysTenant);

    /**
     * 删除租户
     *
     * @param tenantId 租户主键
     * @return 结果
     */
    public int deleteSysTenantByTenantId(Long tenantId);

    /**
     * 批量删除租户
     *
     * @param tenantIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTenantByTenantIds(Long[] tenantIds);
}
