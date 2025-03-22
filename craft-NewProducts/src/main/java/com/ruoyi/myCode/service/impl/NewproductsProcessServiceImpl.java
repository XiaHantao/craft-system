package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewproductsProcessMapper;
import com.ruoyi.myCode.domain.NewproductsProcess;
import com.ruoyi.myCode.service.INewproductsProcessService;

import javax.annotation.Resource;

/**
 * 新产品试制过程Service业务层处理
 * 
 * @author xj
 * @date 2025-02-26
 */
@Service
public class NewproductsProcessServiceImpl implements INewproductsProcessService 
{
    @Resource
    private NewproductsProcessMapper newproductsProcessMapper;

    /**
     * 查询新产品试制过程
     * 
     * @param id 新产品试制过程主键
     * @return 新产品试制过程
     */
    @Override
    public NewproductsProcess selectNewproductsProcessById(Long id)
    {
        return newproductsProcessMapper.selectNewproductsProcessById(id);
    }

    /**
     * 查询新产品试制过程列表
     * 
     * @param newproductsProcess 新产品试制过程
     * @return 新产品试制过程
     */
    @Override
    public List<NewproductsProcess> selectNewproductsProcessList(NewproductsProcess newproductsProcess)
    {
        return newproductsProcessMapper.selectNewproductsProcessList(newproductsProcess);
    }

    /**
     * 新增新产品试制过程
     * 
     * @param newproductsProcess 新产品试制过程
     * @return 结果
     */
    @Override
    public int insertNewproductsProcess(NewproductsProcess newproductsProcess)
    {
        return newproductsProcessMapper.insertNewproductsProcess(newproductsProcess);
    }

    /**
     * 修改新产品试制过程
     * 
     * @param newproductsProcess 新产品试制过程
     * @return 结果
     */
    @Override
    public int updateNewproductsProcess(NewproductsProcess newproductsProcess)
    {
        return newproductsProcessMapper.updateNewproductsProcess(newproductsProcess);
    }

    /**
     * 批量删除新产品试制过程
     * 
     * @param ids 需要删除的新产品试制过程主键
     * @return 结果
     */
    @Override
    public int deleteNewproductsProcessByIds(Long[] ids)
    {
        return newproductsProcessMapper.deleteNewproductsProcessByIds(ids);
    }

    /**
     * 删除新产品试制过程信息
     * 
     * @param id 新产品试制过程主键
     * @return 结果
     */
    @Override
    public int deleteNewproductsProcessById(Long id)
    {
        return newproductsProcessMapper.deleteNewproductsProcessById(id);
    }
}
