package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewproductsSubmit;

/**
 * 新产品提交Service接口
 * 
 * @author xj
 * @date 2025-02-26
 */
public interface INewproductsSubmitService 
{
    /**
     * 查询新产品提交
     * 
     * @param id 新产品提交主键
     * @return 新产品提交
     */
    public NewproductsSubmit selectNewproductsSubmitById(Long id);

    /**
     * 查询新产品提交列表
     * 
     * @param newproductsSubmit 新产品提交
     * @return 新产品提交集合
     */
    public List<NewproductsSubmit> selectNewproductsSubmitList(NewproductsSubmit newproductsSubmit);

    /**
     * 新增新产品提交
     * 
     * @param newproductsSubmit 新产品提交
     * @return 结果
     */
    public int insertNewproductsSubmit(NewproductsSubmit newproductsSubmit);

    /**
     * 修改新产品提交
     * 
     * @param newproductsSubmit 新产品提交
     * @return 结果
     */
    public int updateNewproductsSubmit(NewproductsSubmit newproductsSubmit);

    /**
     * 批量删除新产品提交
     * 
     * @param ids 需要删除的新产品提交主键集合
     * @return 结果
     */
    public int deleteNewproductsSubmitByIds(Long[] ids);

    /**
     * 删除新产品提交信息
     * 
     * @param id 新产品提交主键
     * @return 结果
     */
    public int deleteNewproductsSubmitById(Long id);
}
