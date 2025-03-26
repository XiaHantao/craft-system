package com.ruoyi.myCode.mapper;

import java.util.List;
import com.ruoyi.myCode.domain.NewproductsPlan;

/**
 * 新产品生产计划Mapper接口
 * 
 * @author xj
 * @date 2025-03-03
 */
public interface NewproductsPlanMapper 
{
    /**
     * 查询新产品生产计划
     * 
     * @param id 新产品生产计划主键
     * @return 新产品生产计划
     */
    public NewproductsPlan selectNewproductsPlanById(Long id);

    /**
     * 查询新产品生产计划列表
     * 
     * @param newproductsPlan 新产品生产计划
     * @return 新产品生产计划集合
     */
    public List<NewproductsPlan> selectNewproductsPlanList(NewproductsPlan newproductsPlan);

    /**
     * 新增新产品生产计划
     * 
     * @param newproductsPlan 新产品生产计划
     * @return 结果
     */
    public int insertNewproductsPlan(NewproductsPlan newproductsPlan);

    /**
     * 修改新产品生产计划
     * 
     * @param newproductsPlan 新产品生产计划
     * @return 结果
     */
    public int updateNewproductsPlan(NewproductsPlan newproductsPlan);

    /**
     * 删除新产品生产计划
     * 
     * @param id 新产品生产计划主键
     * @return 结果
     */
    public int deleteNewproductsPlanById(Long id);

    /**
     * 批量删除新产品生产计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewproductsPlanByIds(Long[] ids);
}
