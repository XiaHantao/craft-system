package com.ruoyi.myCode.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewproductsSubmitMapper;
import com.ruoyi.myCode.domain.NewproductsSubmit;
import com.ruoyi.myCode.service.INewproductsSubmitService;

import javax.annotation.Resource;

/**
 * 新产品提交Service业务层处理
 * 
 * @author xj
 * @date 2025-02-26
 */
@Service
public class NewproductsSubmitServiceImpl implements INewproductsSubmitService 
{
    @Resource
    private NewproductsSubmitMapper newproductsSubmitMapper;

    /**
     * 查询新产品提交
     * 
     * @param id 新产品提交主键
     * @return 新产品提交
     */
    @Override
    public NewproductsSubmit selectNewproductsSubmitById(Long id)
    {
        return newproductsSubmitMapper.selectNewproductsSubmitById(id);
    }

    /**
     * 查询新产品提交列表
     * 
     * @param newproductsSubmit 新产品提交
     * @return 新产品提交
     */
    @Override
    public List<NewproductsSubmit> selectNewproductsSubmitList(NewproductsSubmit newproductsSubmit)
    {
        return newproductsSubmitMapper.selectNewproductsSubmitList(newproductsSubmit);
    }

    /**
     * 新增新产品提交
     * 
     * @param newproductsSubmit 新产品提交
     * @return 结果
     */
    @Override
    public int insertNewproductsSubmit(NewproductsSubmit newproductsSubmit)
    {
        newproductsSubmit.setCreateTime(DateUtils.getNowDate());
//        return newproductsSubmitMapper.insertNewproductsSubmit(newproductsSubmit);
//        return newproductsSubmitMapper.insertNewproductsProcess(newproductsSubmit);
        // 插入 NewproductsSubmit
        int result = newproductsSubmitMapper.insertNewproductsSubmit(newproductsSubmit);

        // 如果插入成功，继续插入 NewproductsProcess
        if (result > 0) {
            result = newproductsSubmitMapper.insertNewproductsProcess(newproductsSubmit);
        }

        return result;

    }

    /**
     * 修改新产品提交
     * 
     * @param newproductsSubmit 新产品提交
     * @return 结果
     */
    @Override
    public int updateNewproductsSubmit(NewproductsSubmit newproductsSubmit) {
        // 更新 NewproductsSubmit
        int submitResult = newproductsSubmitMapper.updateNewproductsSubmit(newproductsSubmit);

        // 如果第一个操作成功，继续更新 NewproductsProcess
        if (submitResult > 0) {
            int processResult = newproductsSubmitMapper.updateNewproductsProcess(newproductsSubmit);
            // 如果第二个操作也成功，返回成功
            if (processResult > 0) {
                return processResult; // 或者返回 1 表示成功
            }
        }

        // 如果任何一个操作失败，返回失败
        return 0;
    }

    /**
     * 批量删除新产品提交
     * 
     * @param ids 需要删除的新产品提交主键
     * @return 结果
     */
    @Override
    public int deleteNewproductsSubmitByIds(Long[] ids)
    {
//        return newproductsSubmitMapper.deleteNewproductsSubmitByIds(ids);
//        return newproductsSubmitMapper.deleteNewproductsProcessById(id);
        int result = newproductsSubmitMapper.deleteNewproductsSubmitByIds(ids);
        if (result > 0) {
            for (Long id : ids) {
                newproductsSubmitMapper.deleteNewproductsProcessById(id);//同时删除newproductsProcess表数据
            }
        }
        return result;
    }

    /**
     * 删除新产品提交信息
     * 
     * @param id 新产品提交主键
     * @return 结果
     */
    @Override
    public int deleteNewproductsSubmitById(Long id)
    {
        return newproductsSubmitMapper.deleteNewproductsSubmitById(id);
    }
}
