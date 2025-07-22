package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductCreate;

/**
 * 新产品信息Service接口
 * 
 * @author xj
 * @date 2025-07-20
 */
public interface INewProductCreateService 
{
    /**
     * 查询新产品信息
     * 
     * @param id 新产品信息主键
     * @return 新产品信息
     */
    public NewProductCreate selectNewProductCreateById(Long id);

    /**
     * 查询新产品信息列表
     * 
     * @param newProductCreate 新产品信息
     * @return 新产品信息集合
     */
    public List<NewProductCreate> selectNewProductCreateList(NewProductCreate newProductCreate);

    /**
     * 新增新产品信息
     * 
     * @param newProductCreate 新产品信息
     * @return 结果
     */
    public int insertNewProductCreate(NewProductCreate newProductCreate);

    /**
     * 修改新产品信息
     * 
     * @param newProductCreate 新产品信息
     * @return 结果
     */
    public int updateNewProductCreate(NewProductCreate newProductCreate);

    /**
     * 批量删除新产品信息
     * 
     * @param ids 需要删除的新产品信息主键集合
     * @return 结果
     */
    public int deleteNewProductCreateByIds(Long[] ids);

    /**
     * 删除新产品信息信息
     * 
     * @param id 新产品信息主键
     * @return 结果
     */
    public int deleteNewProductCreateById(Long id);
}
