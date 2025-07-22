package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductProductionProgress;

/**
 * 新产品生产进度跟踪Service接口
 * 
 * @author xj
 * @date 2025-07-21
 */
public interface INewProductProductionProgressService 
{
    /**
     * 查询新产品生产进度跟踪
     * 
     * @param id 新产品生产进度跟踪主键
     * @return 新产品生产进度跟踪
     */
    public NewProductProductionProgress selectNewProductProductionProgressById(Long id);

    /**
     * 查询新产品生产进度跟踪列表
     * 
     * @param newProductProductionProgress 新产品生产进度跟踪
     * @return 新产品生产进度跟踪集合
     */
    public List<NewProductProductionProgress> selectNewProductProductionProgressList(NewProductProductionProgress newProductProductionProgress);

    /**
     * 新增新产品生产进度跟踪
     * 
     * @param newProductProductionProgress 新产品生产进度跟踪
     * @return 结果
     */
    public int insertNewProductProductionProgress(NewProductProductionProgress newProductProductionProgress);

    /**
     * 修改新产品生产进度跟踪
     * 
     * @param newProductProductionProgress 新产品生产进度跟踪
     * @return 结果
     */
    public int updateNewProductProductionProgress(NewProductProductionProgress newProductProductionProgress);

    /**
     * 批量删除新产品生产进度跟踪
     * 
     * @param ids 需要删除的新产品生产进度跟踪主键集合
     * @return 结果
     */
    public int deleteNewProductProductionProgressByIds(Long[] ids);

    /**
     * 删除新产品生产进度跟踪信息
     * 
     * @param id 新产品生产进度跟踪主键
     * @return 结果
     */
    public int deleteNewProductProductionProgressById(Long id);
}
