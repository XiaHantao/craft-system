package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductProductionPlan;

/**
 * 新产品生产计划Service接口
 * 
 * @author xj
 * @date 2025-07-21
 */
public interface INewProductProductionPlanService 
{
    /**
     * 查询新产品生产计划
     * 
     * @param id 新产品生产计划主键
     * @return 新产品生产计划
     */
    public NewProductProductionPlan selectNewProductProductionPlanById(Long id);

    /**
     * 查询新产品生产计划列表
     * 
     * @param newProductProductionPlan 新产品生产计划
     * @return 新产品生产计划集合
     */
    public List<NewProductProductionPlan> selectNewProductProductionPlanList(NewProductProductionPlan newProductProductionPlan);

    /**
     * 新增新产品生产计划
     * 
     * @param newProductProductionPlan 新产品生产计划
     * @return 结果
     */
    public int insertNewProductProductionPlan(NewProductProductionPlan newProductProductionPlan);

    /**
     * 修改新产品生产计划
     * 
     * @param newProductProductionPlan 新产品生产计划
     * @return 结果
     */
    public int updateNewProductProductionPlan(NewProductProductionPlan newProductProductionPlan);

    /**
     * 批量删除新产品生产计划
     * 
     * @param ids 需要删除的新产品生产计划主键集合
     * @return 结果
     */
    public int deleteNewProductProductionPlanByIds(Long[] ids);

    /**
     * 删除新产品生产计划信息
     * 
     * @param id 新产品生产计划主键
     * @return 结果
     */
    public int deleteNewProductProductionPlanById(Long id);
}
