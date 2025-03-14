package com.ruoyi.workClothes.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.SelfMadePartsLedgerMapper;
import com.ruoyi.workClothes.domain.SelfMadePartsLedger;
import com.ruoyi.workClothes.service.ISelfMadePartsLedgerService;

/**
 * 自制件台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-04
 */
@Service
public class SelfMadePartsLedgerServiceImpl implements ISelfMadePartsLedgerService 
{
    @Autowired
    private SelfMadePartsLedgerMapper selfMadePartsLedgerMapper;

    /**
     * 查询自制件台账
     * 
     * @param id 自制件台账主键
     * @return 自制件台账
     */
    @Override
    public SelfMadePartsLedger selectSelfMadePartsLedgerById(Long id)
    {
        return selfMadePartsLedgerMapper.selectSelfMadePartsLedgerById(id);
    }

    /**
     * 查询自制件台账列表
     * 
     * @param selfMadePartsLedger 自制件台账
     * @return 自制件台账
     */
    @Override
    public List<SelfMadePartsLedger> selectSelfMadePartsLedgerList(SelfMadePartsLedger selfMadePartsLedger)
    {
        return selfMadePartsLedgerMapper.selectSelfMadePartsLedgerList(selfMadePartsLedger);
    }

    /**
     * 新增自制件台账
     * 
     * @param selfMadePartsLedger 自制件台账
     * @return 结果
     */
    @Override
    public int insertSelfMadePartsLedger(SelfMadePartsLedger selfMadePartsLedger)
    {
        selfMadePartsLedger.setToolUploader(SecurityUtils.getUsername());
        return selfMadePartsLedgerMapper.insertSelfMadePartsLedger(selfMadePartsLedger);
    }

    /**
     * 修改自制件台账
     * 
     * @param selfMadePartsLedger 自制件台账
     * @return 结果
     */
    @Override
    public int updateSelfMadePartsLedger(SelfMadePartsLedger selfMadePartsLedger)
    {
        return selfMadePartsLedgerMapper.updateSelfMadePartsLedger(selfMadePartsLedger);
    }

    /**
     * 批量删除自制件台账
     * 
     * @param ids 需要删除的自制件台账主键
     * @return 结果
     */
    @Override
    public int deleteSelfMadePartsLedgerByIds(Long[] ids)
    {
        return selfMadePartsLedgerMapper.deleteSelfMadePartsLedgerByIds(ids);
    }

    /**
     * 删除自制件台账信息
     * 
     * @param id 自制件台账主键
     * @return 结果
     */
    @Override
    public int deleteSelfMadePartsLedgerById(Long id)
    {
        return selfMadePartsLedgerMapper.deleteSelfMadePartsLedgerById(id);
    }
}
