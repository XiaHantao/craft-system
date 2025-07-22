package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductCreateMapper;
import com.ruoyi.myCode.domain.NewProductCreate;
import com.ruoyi.myCode.service.INewProductCreateService;

/**
 * 新产品信息Service业务层处理
 * 
 * @author xj
 * @date 2025-07-20
 */
@Service
public class NewProductCreateServiceImpl implements INewProductCreateService 
{
    @Autowired
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
        return newProductCreateMapper.insertNewProductCreate(newProductCreate);
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
        return newProductCreateMapper.updateNewProductCreate(newProductCreate);
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
        return newProductCreateMapper.deleteNewProductCreateByIds(ids);
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
