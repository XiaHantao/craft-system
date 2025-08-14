package com.ruoyi.myCode.mapper;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductCreate;

/**
 * 新产品信息Mapper接口
 * 
 * @author xj
 * @date 2025-07-20
 */
public interface NewProductCreateMapper 
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
    public int insertNewproductDocumentDisplay(NewProductCreate newProductCreate);

    /**
     * 修改新产品信息
     * 
     * @param newProductCreate 新产品信息
     * @return 结果
     */
    public int updateNewProductCreate(NewProductCreate newProductCreate);
    public int updateNewproductDocumentDisplay(NewProductCreate newProductCreate);

    /**
     * 删除新产品信息
     * 
     * @param id 新产品信息主键
     * @return 结果
     */
    public int deleteNewProductCreateById(Long id);
    public int deleteNewproductNewproductDocumentDisplayById(long id);
    /**
     * 批量删除新产品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewProductCreateByIds(Long[] ids);

}
