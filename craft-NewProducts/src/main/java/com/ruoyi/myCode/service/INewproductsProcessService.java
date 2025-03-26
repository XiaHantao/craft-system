package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewproductsProcess;

/**
 * 新产品试制过程Service接口
 * 
 * @author xj
 * @date 2025-02-26
 */
public interface INewproductsProcessService 
{
    /**
     * 查询新产品试制过程
     * 
     * @param id 新产品试制过程主键
     * @return 新产品试制过程
     */
    public NewproductsProcess selectNewproductsProcessById(Long id);

    /**
     * 查询新产品试制过程列表
     * 
     * @param newproductsProcess 新产品试制过程
     * @return 新产品试制过程集合
     */
    public List<NewproductsProcess> selectNewproductsProcessList(NewproductsProcess newproductsProcess);

    /**
     * 新增新产品试制过程
     * 
     * @param newproductsProcess 新产品试制过程
     * @return 结果
     */
    public int insertNewproductsProcess(NewproductsProcess newproductsProcess);

    /**
     * 修改新产品试制过程
     * 
     * @param newproductsProcess 新产品试制过程
     * @return 结果
     */
    public int updateNewproductsProcess(NewproductsProcess newproductsProcess);

    /**
     * 批量删除新产品试制过程
     * 
     * @param ids 需要删除的新产品试制过程主键集合
     * @return 结果
     */
    public int deleteNewproductsProcessByIds(Long[] ids);

    /**
     * 删除新产品试制过程信息
     * 
     * @param id 新产品试制过程主键
     * @return 结果
     */
    public int deleteNewproductsProcessById(Long id);
}
