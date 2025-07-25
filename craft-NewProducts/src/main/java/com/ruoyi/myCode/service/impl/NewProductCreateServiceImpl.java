package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductCreateMapper;
import com.ruoyi.myCode.domain.NewProductCreate;
import com.ruoyi.myCode.service.INewProductCreateService;

import javax.annotation.Resource;

/**
 * 新产品信息Service业务层处理
 * 
 * @author xj
 * @date 2025-07-20
 */
@Service
public class NewProductCreateServiceImpl implements INewProductCreateService 
{
    @Resource
    private NewProductCreateMapper newProductCreateMapper;

    /**
     * 查询新产品信息
     * 
     * @param id 新产品信息主键
     * @return 新产品信息
     */
    @Override
    public NewProductCreate selectNewProductCreateById(Long id)
    {
        return newProductCreateMapper.selectNewProductCreateById(id);
    }

    /**
     * 查询新产品信息列表
     * 
     * @param newProductCreate 新产品信息
     * @return 新产品信息
     */
    @Override
    public List<NewProductCreate> selectNewProductCreateList(NewProductCreate newProductCreate)
    {
        return newProductCreateMapper.selectNewProductCreateList(newProductCreate);
    }

    /**
     * 新增新产品信息
     * 
     * @param newProductCreate 新产品信息
     * @return 结果
     */
    @Override
    public int insertNewProductCreate(NewProductCreate newProductCreate)
    {
        int result = newProductCreateMapper.insertNewProductCreate(newProductCreate);

        // 如果插入成功，继续插入 NewproductDocumentDisplay
        if (result > 0) {
            result = newProductCreateMapper.insertNewproductDocumentDisplay(newProductCreate);
        }

        return result;
    }

    /**
     * 修改新产品信息
     * 
     * @param newProductCreate 新产品信息
     * @return 结果
     */
    @Override
    public int updateNewProductCreate(NewProductCreate newProductCreate)
    {
        int Createresult = newProductCreateMapper.updateNewProductCreate(newProductCreate);
        //如果修改成功，继续修改 NewproductDocumentDisplay
        if(Createresult > 0) {
            int Displayresult = newProductCreateMapper.updateNewproductDocumentDisplay(newProductCreate);
            if(Displayresult > 0){
                return Displayresult;
            }
        }
        return 0;
    }

    /**
     * 批量删除新产品信息
     * 
     * @param ids 需要删除的新产品信息主键
     * @return 结果
     */
    @Override
    public int deleteNewProductCreateByIds(Long[] ids)
    {
        int result = newProductCreateMapper.deleteNewProductCreateByIds(ids);
        if (result > 0) {
            for (Long id : ids) {
                newProductCreateMapper.deleteNewproductNewproductDocumentDisplayById(id);//同时删除NewproductDocumentDisplay表数据
            }
        }
        return result;
    }

    /**
     * 删除新产品信息信息
     * 
     * @param id 新产品信息主键
     * @return 结果
     */
    @Override
    public int deleteNewProductCreateById(Long id)
    {
        return newProductCreateMapper.deleteNewProductCreateById(id);
    }
}
