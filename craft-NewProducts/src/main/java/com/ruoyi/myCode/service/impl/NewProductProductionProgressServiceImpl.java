package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductProductionProgressMapper;
import com.ruoyi.myCode.domain.NewProductProductionProgress;
import com.ruoyi.myCode.service.INewProductProductionProgressService;

/**
 * 新产品生产进度跟踪Service业务层处理
 * 
 * @author xj
 * @date 2025-07-21
 */
@Service
public class NewProductProductionProgressServiceImpl implements INewProductProductionProgressService 
{
    @Autowired
    private NewProductProductionProgressMapper newProductProductionProgressMapper;

    /**
     * 查询新产品生产进度跟踪
     * 
     * @param id 新产品生产进度跟踪主键
     * @return 新产品生产进度跟踪
     */
    @Override
    public NewProductProductionProgress selectNewProductProductionProgressById(Long id)
    {
        return newProductProductionProgressMapper.selectNewProductProductionProgressById(id);
    }

    /**
     * 查询新产品生产进度跟踪列表
     * 
     * @param newProductProductionProgress 新产品生产进度跟踪
     * @return 新产品生产进度跟踪
     */
    @Override
    public List<NewProductProductionProgress> selectNewProductProductionProgressList(NewProductProductionProgress newProductProductionProgress)
    {
        return newProductProductionProgressMapper.selectNewProductProductionProgressList(newProductProductionProgress);
    }

    /**
     * 新增新产品生产进度跟踪
     * 
     * @param newProductProductionProgress 新产品生产进度跟踪
     * @return 结果
     */
    @Override
    public int insertNewProductProductionProgress(NewProductProductionProgress newProductProductionProgress)
    {
        return newProductProductionProgressMapper.insertNewProductProductionProgress(newProductProductionProgress);
    }

    /**
     * 修改新产品生产进度跟踪
     * 
     * @param newProductProductionProgress 新产品生产进度跟踪
     * @return 结果
     */
    @Override
    public int updateNewProductProductionProgress(NewProductProductionProgress newProductProductionProgress)
    {
        return newProductProductionProgressMapper.updateNewProductProductionProgress(newProductProductionProgress);
    }

    /**
     * 批量删除新产品生产进度跟踪
     * 
     * @param ids 需要删除的新产品生产进度跟踪主键
     * @return 结果
     */
    @Override
    public int deleteNewProductProductionProgressByIds(Long[] ids)
    {
        return newProductProductionProgressMapper.deleteNewProductProductionProgressByIds(ids);
    }

    /**
     * 删除新产品生产进度跟踪信息
     * 
     * @param id 新产品生产进度跟踪主键
     * @return 结果
     */
    @Override
    public int deleteNewProductProductionProgressById(Long id)
    {
        return newProductProductionProgressMapper.deleteNewProductProductionProgressById(id);
    }
    @Override
    public NewProductProductionProgress selectProgressByProjectCode(String projectCode) {
        return newProductProductionProgressMapper.selectProgressByProjectCode(projectCode);
    }
}
