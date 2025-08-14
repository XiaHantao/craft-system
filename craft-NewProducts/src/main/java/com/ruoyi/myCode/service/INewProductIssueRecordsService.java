package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductIssueRecords;

/**
 * 新产品生产问题记录Service接口
 * 
 * @author xj
 * @date 2025-07-21
 */
public interface INewProductIssueRecordsService 
{
    /**
     * 查询新产品生产问题记录
     * 
     * @param id 新产品生产问题记录主键
     * @return 新产品生产问题记录
     */
    public NewProductIssueRecords selectNewProductIssueRecordsById(Long id);

    /**
     * 查询新产品生产问题记录列表
     * 
     * @param newProductIssueRecords 新产品生产问题记录
     * @return 新产品生产问题记录集合
     */
    public List<NewProductIssueRecords> selectNewProductIssueRecordsList(NewProductIssueRecords newProductIssueRecords);

    /**
     * 新增新产品生产问题记录
     * 
     * @param newProductIssueRecords 新产品生产问题记录
     * @return 结果
     */
    public int insertNewProductIssueRecords(NewProductIssueRecords newProductIssueRecords);

    /**
     * 修改新产品生产问题记录
     * 
     * @param newProductIssueRecords 新产品生产问题记录
     * @return 结果
     */
    public int updateNewProductIssueRecords(NewProductIssueRecords newProductIssueRecords);


    /**
     * 批量删除新产品生产问题记录
     * 
     * @param ids 需要删除的新产品生产问题记录主键集合
     * @return 结果
     */
    public int deleteNewProductIssueRecordsByIds(Long[] ids);

    /**
     * 删除新产品生产问题记录信息
     * 
     * @param id 新产品生产问题记录主键
     * @return 结果
     */
    public int deleteNewProductIssueRecordsById(Long id);
}
