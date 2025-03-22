package com.ruoyi.myCode.mapper;

import java.util.List;
import com.ruoyi.myCode.domain.NewproductsSubmit;

/**
 * 新产品提交Mapper接口
 * 
 * @author xj
 * @date 2025-02-26
 */
public interface NewproductsSubmitMapper 
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
    public int insertNewproductsProcess(NewproductsSubmit newproductsSubmit);
    /**
     * 修改新产品提交
     * 
     * @param newproductsSubmit 新产品提交
     * @return 结果
     */
    public int updateNewproductsSubmit(NewproductsSubmit newproductsSubmit);
    public int updateNewproductsProcess(NewproductsSubmit newproductsSubmit);
    /**
     * 删除新产品提交
     * 
     * @param id 新产品提交主键
     * @return 结果
     */
    public int deleteNewproductsSubmitById(Long id);

    /**
     * 批量删除新产品提交
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewproductsSubmitByIds(Long[] ids);
    public int deleteNewproductsProcessById(Long id);

}
