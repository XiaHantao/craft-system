package com.ruoyi.myCode.mapper;

import java.util.List;
import com.ruoyi.myCode.domain.NewproductsProcess;

/**
 * 新产品试制过程Mapper接口
 * 
 * @author xj
 * @date 2025-02-26
 */
public interface NewproductsProcessMapper 
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
     * 删除新产品试制过程
     * 
     * @param id 新产品试制过程主键
     * @return 结果
     */
    public int deleteNewproductsProcessById(Long id);

    /**
     * 批量删除新产品试制过程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewproductsProcessByIds(Long[] ids);
}
