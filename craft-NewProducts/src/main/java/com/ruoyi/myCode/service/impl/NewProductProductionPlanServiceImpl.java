package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductProductionPlanMapper;
import com.ruoyi.myCode.domain.NewProductProductionPlan;
import com.ruoyi.myCode.service.INewProductProductionPlanService;

/**
 * 新产品生产计划Service业务层处理
 * 
 * @author xj
 * @date 2025-07-21
 */
@Service
public class NewProductProductionPlanServiceImpl implements INewProductProductionPlanService 
{
    @Autowired
    private NewProductProductionPlanMapper newProductProductionPlanMapper;

    /**
     * 查询新产品生产计划
     * 
     * @param id 新产品生产计划主键
     * @return 新产品生产计划
     */
    @Override
    public NewProductProductionPlan selectNewProductProductionPlanById(Long id)
    {
        return newProductProductionPlanMapper.selectNewProductProductionPlanById(id);
    }

    /**
     * 查询新产品生产计划列表
     * 
     * @param newProductProductionPlan 新产品生产计划
     * @return 新产品生产计划
     */
    @Override
    public List<NewProductProductionPlan> selectNewProductProductionPlanList(NewProductProductionPlan newProductProductionPlan)
    {
        return newProductProductionPlanMapper.selectNewProductProductionPlanList(newProductProductionPlan);
    }

    /**
     * 新增新产品生产计划
     * 
     * @param newProductProductionPlan 新产品生产计划
     * @return 结果
     */
    @Override
    public int insertNewProductProductionPlan(NewProductProductionPlan newProductProductionPlan)
    {
        return newProductProductionPlanMapper.insertNewProductProductionPlan(newProductProductionPlan);
    }

    /**
     * 修改新产品生产计划
     * 
     * @param newProductProductionPlan 新产品生产计划
     * @return 结果
     */
    @Override
    public int updateNewProductProductionPlan(NewProductProductionPlan newProductProductionPlan)
    {
        return newProductProductionPlanMapper.updateNewProductProductionPlan(newProductProductionPlan);
    }

    /**
     * 批量删除新产品生产计划
     * 
     * @param ids 需要删除的新产品生产计划主键
     * @return 结果
     */
    @Override
    public int deleteNewProductProductionPlanByIds(Long[] ids)
    {
        return newProductProductionPlanMapper.deleteNewProductProductionPlanByIds(ids);
    }

    /**
     * 删除新产品生产计划信息
     * 
     * @param id 新产品生产计划主键
     * @return 结果
     */
    @Override
    public int deleteNewProductProductionPlanById(Long id)
    {
        return newProductProductionPlanMapper.deleteNewProductProductionPlanById(id);
    }
}
