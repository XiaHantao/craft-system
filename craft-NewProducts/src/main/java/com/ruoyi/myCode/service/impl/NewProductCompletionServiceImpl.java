package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductCompletionMapper;
import com.ruoyi.myCode.domain.NewProductCompletion;
import com.ruoyi.myCode.service.INewProductCompletionService;

/**
 * 新产品生产完成Service业务层处理
 * 
 * @author xj
 * @date 2025-07-21
 */
@Service
public class NewProductCompletionServiceImpl implements INewProductCompletionService 
{
    @Autowired
    private NewProductCompletionMapper newProductCompletionMapper;

    /**
     * 查询新产品生产完成
     * 
     * @param id 新产品生产完成主键
     * @return 新产品生产完成
     */
    @Override
    public NewProductCompletion selectNewProductCompletionById(Long id)
    {
        return newProductCompletionMapper.selectNewProductCompletionById(id);
    }

    /**
     * 查询新产品生产完成列表
     * 
     * @param newProductCompletion 新产品生产完成
     * @return 新产品生产完成
     */
    @Override
    public List<NewProductCompletion> selectNewProductCompletionList(NewProductCompletion newProductCompletion)
    {
        return newProductCompletionMapper.selectNewProductCompletionList(newProductCompletion);
    }

    /**
     * 新增新产品生产完成
     * 
     * @param newProductCompletion 新产品生产完成
     * @return 结果
     */
    @Override
    public int insertNewProductCompletion(NewProductCompletion newProductCompletion)
    {
        return newProductCompletionMapper.insertNewProductCompletion(newProductCompletion);
    }

    /**
     * 修改新产品生产完成
     * 
     * @param newProductCompletion 新产品生产完成
     * @return 结果
     */
    @Override
    public int updateNewProductCompletion(NewProductCompletion newProductCompletion)
    {
        int Completionresult = newProductCompletionMapper.updateNewProductCompletion(newProductCompletion);
        //如果修改成功，继续修改 NewproductDocumentDisplay
        if(Completionresult > 0) {
            int Displayresult = newProductCompletionMapper.updateNewproductDocumentDisplay(newProductCompletion);
            if(Displayresult > 0){
                return Displayresult;
            }
        }
        return 0;
    }

    /**
     * 批量删除新产品生产完成
     * 
     * @param ids 需要删除的新产品生产完成主键
     * @return 结果
     */
    @Override
    public int deleteNewProductCompletionByIds(Long[] ids)
    {
        return newProductCompletionMapper.deleteNewProductCompletionByIds(ids);
    }

    /**
     * 删除新产品生产完成信息
     * 
     * @param id 新产品生产完成主键
     * @return 结果
     */
    @Override
    public int deleteNewProductCompletionById(Long id)
    {
        return newProductCompletionMapper.deleteNewProductCompletionById(id);
    }
}
