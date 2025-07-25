package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductCompletion;

/**
 * 新产品生产完成Service接口
 * 
 * @author xj
 * @date 2025-07-21
 */
public interface INewProductCompletionService 
{
    /**
     * 查询新产品生产完成
     * 
     * @param id 新产品生产完成主键
     * @return 新产品生产完成
     */
    public NewProductCompletion selectNewProductCompletionById(Long id);

    /**
     * 查询新产品生产完成列表
     * 
     * @param newProductCompletion 新产品生产完成
     * @return 新产品生产完成集合
     */
    public List<NewProductCompletion> selectNewProductCompletionList(NewProductCompletion newProductCompletion);

    /**
     * 新增新产品生产完成
     * 
     * @param newProductCompletion 新产品生产完成
     * @return 结果
     */
    public int insertNewProductCompletion(NewProductCompletion newProductCompletion);

    /**
     * 修改新产品生产完成
     * 
     * @param newProductCompletion 新产品生产完成
     * @return 结果
     */
    public int updateNewProductCompletion(NewProductCompletion newProductCompletion);


    /**
     * 批量删除新产品生产完成
     * 
     * @param ids 需要删除的新产品生产完成主键集合
     * @return 结果
     */
    public int deleteNewProductCompletionByIds(Long[] ids);

    /**
     * 删除新产品生产完成信息
     * 
     * @param id 新产品生产完成主键
     * @return 结果
     */
    public int deleteNewProductCompletionById(Long id);
}
