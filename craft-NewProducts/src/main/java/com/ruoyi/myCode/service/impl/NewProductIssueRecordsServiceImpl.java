package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductIssueRecordsMapper;
import com.ruoyi.myCode.domain.NewProductIssueRecords;
import com.ruoyi.myCode.service.INewProductIssueRecordsService;

/**
 * 新产品生产问题记录Service业务层处理
 * 
 * @author xj
 * @date 2025-07-21
 */
@Service
public class NewProductIssueRecordsServiceImpl implements INewProductIssueRecordsService 
{
    @Autowired
    private NewProductIssueRecordsMapper newProductIssueRecordsMapper;

    /**
     * 查询新产品生产问题记录
     * 
     * @param id 新产品生产问题记录主键
     * @return 新产品生产问题记录
     */
    @Override
    public NewProductIssueRecords selectNewProductIssueRecordsById(Long id)
    {
        return newProductIssueRecordsMapper.selectNewProductIssueRecordsById(id);
    }

    /**
     * 查询新产品生产问题记录列表
     * 
     * @param newProductIssueRecords 新产品生产问题记录
     * @return 新产品生产问题记录
     */
    @Override
    public List<NewProductIssueRecords> selectNewProductIssueRecordsList(NewProductIssueRecords newProductIssueRecords)
    {
        return newProductIssueRecordsMapper.selectNewProductIssueRecordsList(newProductIssueRecords);
    }

    /**
     * 新增新产品生产问题记录
     * 
     * @param newProductIssueRecords 新产品生产问题记录
     * @return 结果
     */
    @Override
    public int insertNewProductIssueRecords(NewProductIssueRecords newProductIssueRecords)
    {
        return newProductIssueRecordsMapper.insertNewProductIssueRecords(newProductIssueRecords);
    }

    /**
     * 修改新产品生产问题记录
     * 
     * @param newProductIssueRecords 新产品生产问题记录
     * @return 结果
     */
    @Override
    public int updateNewProductIssueRecords(NewProductIssueRecords newProductIssueRecords)
    {
        int Recordsresult = newProductIssueRecordsMapper.updateNewProductIssueRecords(newProductIssueRecords);
        //如果修改成功，继续修改 NewproductDocumentDisplay
        if(Recordsresult > 0) {
            int Displayresult = newProductIssueRecordsMapper.updateNewproductDocumentDisplay(newProductIssueRecords);
            if(Displayresult > 0){
                return Displayresult;
            }
        }
        return 0;
    }

    /**
     * 批量删除新产品生产问题记录
     * 
     * @param ids 需要删除的新产品生产问题记录主键
     * @return 结果
     */
    @Override
    public int deleteNewProductIssueRecordsByIds(Long[] ids)
    {
        return newProductIssueRecordsMapper.deleteNewProductIssueRecordsByIds(ids);
    }

    /**
     * 删除新产品生产问题记录信息
     * 
     * @param id 新产品生产问题记录主键
     * @return 结果
     */
    @Override
    public int deleteNewProductIssueRecordsById(Long id)
    {
        return newProductIssueRecordsMapper.deleteNewProductIssueRecordsById(id);
    }
}
