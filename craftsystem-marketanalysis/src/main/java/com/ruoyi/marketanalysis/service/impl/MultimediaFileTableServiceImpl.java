package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.MultimediaFileTableMapper;
import com.ruoyi.marketanalysis.domain.MultimediaFileTable;
import com.ruoyi.marketanalysis.service.IMultimediaFileTableService;

/**
 * 多媒体文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
@Service
public class MultimediaFileTableServiceImpl implements IMultimediaFileTableService 
{
    @Autowired
    private MultimediaFileTableMapper multimediaFileTableMapper;

    /**
     * 查询多媒体文件
     * 
     * @param id 多媒体文件主键
     * @return 多媒体文件
     */
    @Override
    public MultimediaFileTable selectMultimediaFileTableById(Long id)
    {
        return multimediaFileTableMapper.selectMultimediaFileTableById(id);
    }

    /**
     * 查询多媒体文件列表
     * 
     * @param multimediaFileTable 多媒体文件
     * @return 多媒体文件
     */
    @Override
    public List<MultimediaFileTable> selectMultimediaFileTableList(MultimediaFileTable multimediaFileTable)
    {
        return multimediaFileTableMapper.selectMultimediaFileTableList(multimediaFileTable);
    }

    /**
     * 新增多媒体文件
     * 
     * @param multimediaFileTable 多媒体文件
     * @return 结果
     */
    @Override
    public int insertMultimediaFileTable(MultimediaFileTable multimediaFileTable)
    {
        return multimediaFileTableMapper.insertMultimediaFileTable(multimediaFileTable);
    }

    /**
     * 修改多媒体文件
     * 
     * @param multimediaFileTable 多媒体文件
     * @return 结果
     */
    @Override
    public int updateMultimediaFileTable(MultimediaFileTable multimediaFileTable)
    {
        return multimediaFileTableMapper.updateMultimediaFileTable(multimediaFileTable);
    }

    /**
     * 批量删除多媒体文件
     * 
     * @param ids 需要删除的多媒体文件主键
     * @return 结果
     */
    @Override
    public int deleteMultimediaFileTableByIds(Long[] ids)
    {
        return multimediaFileTableMapper.deleteMultimediaFileTableByIds(ids);
    }

    /**
     * 删除多媒体文件信息
     * 
     * @param id 多媒体文件主键
     * @return 结果
     */
    @Override
    public int deleteMultimediaFileTableById(Long id)
    {
        return multimediaFileTableMapper.deleteMultimediaFileTableById(id);
    }
}
