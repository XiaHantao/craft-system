package com.ruoyi.workClothes.mapper;

import java.util.List;
import com.ruoyi.workClothes.domain.SelfMadePartsLedger;

/**
 * 自制件台账Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-04
 */
public interface SelfMadePartsLedgerMapper 
{
    /**
     * 查询自制件台账
     * 
     * @param id 自制件台账主键
     * @return 自制件台账
     */
    public SelfMadePartsLedger selectSelfMadePartsLedgerById(Long id);

    /**
     * 查询自制件台账列表
     * 
     * @param selfMadePartsLedger 自制件台账
     * @return 自制件台账集合
     */
    public List<SelfMadePartsLedger> selectSelfMadePartsLedgerList(SelfMadePartsLedger selfMadePartsLedger);

    /**
     * 新增自制件台账
     * 
     * @param selfMadePartsLedger 自制件台账
     * @return 结果
     */
    public int insertSelfMadePartsLedger(SelfMadePartsLedger selfMadePartsLedger);

    /**
     * 修改自制件台账
     * 
     * @param selfMadePartsLedger 自制件台账
     * @return 结果
     */
    public int updateSelfMadePartsLedger(SelfMadePartsLedger selfMadePartsLedger);

    /**
     * 删除自制件台账
     * 
     * @param id 自制件台账主键
     * @return 结果
     */
    public int deleteSelfMadePartsLedgerById(Long id);

    /**
     * 批量删除自制件台账
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfMadePartsLedgerByIds(Long[] ids);
}
