package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.config.SnowIdConfig;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysTenant;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.CreateTenantBody;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysTenantMapper;
import com.ruoyi.system.service.ISysTenantService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 租户Service业务层处理
 *
 * @author xuqihang
 * @date 2023-05-21
 */
@Service
public class SysTenantServiceImpl implements ISysTenantService
{
    @Autowired
    private SysTenantMapper sysTenantMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SnowIdConfig snowIdConfig;

    /**
     * 查询租户
     *
     * @param tenantId 租户主键
     * @return 租户
     */
    @Override
    public SysTenant selectSysTenantByTenantId(Long tenantId)
    {
        return sysTenantMapper.selectSysTenantByTenantId(tenantId);
    }

    /**
     * 查询租户列表
     *
     * @param sysTenant 租户
     * @return 租户
     */
    @Override
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant)
    {
        return sysTenantMapper.selectSysTenantList(sysTenant);
    }

    /**
     * 新增租户
     *
     * @param createTenantBody 租户
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysTenant(CreateTenantBody createTenantBody)
    {
        SysUser sysUser =  new SysUser();
        sysUser.setUserId(snowIdConfig.getSnowId());
        sysUser.setNickName(createTenantBody.getUserName());
        sysUser.setUserName(createTenantBody.getUserName());
        SysDept sysDept =  new SysDept();
        sysDept.setDeptId(snowIdConfig.getSnowId());
        sysDept.setDeptName(createTenantBody.getDeptName());
        sysDept.setAncestors("0");
        SysTenant sysTenant =  new SysTenant();
        sysTenant.setTenantId(snowIdConfig.getSnowId());
        sysTenant.setRemark(createTenantBody.getRemark());

        sysUser.setDeptId(sysDept.getDeptId());
        sysUser.setTenantId(sysTenant.getTenantId());
        sysDept.setTenantId(sysTenant.getTenantId());
        sysDept.setUserId(sysUser.getUserId());
        sysTenant.setUserId(sysUser.getUserId());
        sysTenant.setDeptId(sysDept.getDeptId());

        sysUser.setCreateTime(DateUtils.getNowDate());
        sysDept.setCreateTime(DateUtils.getNowDate());
        sysTenant.setCreateTime(DateUtils.getNowDate());

        sysUserMapper.insertUser(sysUser);
        sysDeptMapper.insertDept(sysDept);
        return sysTenantMapper.insertSysTenant(sysTenant);
    }

    /**
     * 修改租户
     *
     * @param sysTenant 租户
     * @return 结果
     */
    @Override
    public int updateSysTenant(SysTenant sysTenant)
    {
        sysTenant.setUpdateTime(DateUtils.getNowDate());
        return sysTenantMapper.updateSysTenant(sysTenant);
    }

    /**
     * 批量删除租户
     *
     * @param tenantIds 需要删除的租户主键
     * @return 结果
     */
    @Override
    public int deleteSysTenantByTenantIds(Long[] tenantIds)
    {
        return sysTenantMapper.deleteSysTenantByTenantIds(tenantIds);
    }

    /**
     * 删除租户信息
     *
     * @param tenantId 租户主键
     * @return 结果
     */
    @Override
    public int deleteSysTenantByTenantId(Long tenantId)
    {
        return sysTenantMapper.deleteSysTenantByTenantId(tenantId);
    }
}
