package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewproductsPlanMapper;
import com.ruoyi.myCode.domain.NewproductsPlan;
import com.ruoyi.myCode.service.INewproductsPlanService;

import javax.annotation.Resource;

/**
 * 新产品生产计划Service业务层处理
 * 
 * @author xj
 * @date 2025-03-03
 */
@Service
public class NewproductsPlanServiceImpl implements INewproductsPlanService 
{
    @Resource
    private NewproductsPlanMapper newproductsPlanMapper;

    /**
     * 查询新产品生产计划
     * 
     * @param id 新产品生产计划主键
     * @return 新产品生产计划
     */
    @Override
    public NewproductsPlan selectNewproductsPlanById(Long id)
    {
        return newproductsPlanMapper.selectNewproductsPlanById(id);
    }

    /**
     * 查询新产品生产计划列表
     * 
     * @param newproductsPlan 新产品生产计划
     * @return 新产品生产计划
     */
    @Override
    public List<NewproductsPlan> selectNewproductsPlanList(NewproductsPlan newproductsPlan)
    {
        return newproductsPlanMapper.selectNewproductsPlanList(newproductsPlan);
    }

    /**
     * 新增新产品生产计划
     * 
     * @param newproductsPlan 新产品生产计划
     * @return 结果
     */
    @Override
    public int insertNewproductsPlan(NewproductsPlan newproductsPlan)
    {
        return newproductsPlanMapper.insertNewproductsPlan(newproductsPlan);
    }

    /**
     * 修改新产品生产计划
     * 
     * @param newproductsPlan 新产品生产计划
     * @return 结果
     */
    @Override
    public int updateNewproductsPlan(NewproductsPlan newproductsPlan)
    {
        return newproductsPlanMapper.updateNewproductsPlan(newproductsPlan);
    }

    /**
     * 批量删除新产品生产计划
     * 
     * @param ids 需要删除的新产品生产计划主键
     * @return 结果
     */
    @Override
    public int deleteNewproductsPlanByIds(Long[] ids)
    {
        return newproductsPlanMapper.deleteNewproductsPlanByIds(ids);
    }

    /**
     * 删除新产品生产计划信息
     * 
     * @param id 新产品生产计划主键
     * @return 结果
     */
    @Override
    public int deleteNewproductsPlanById(Long id)
    {
        return newproductsPlanMapper.deleteNewproductsPlanById(id);
    }
}
