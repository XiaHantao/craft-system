package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.MultimediaFileTable;

/**
 * 多媒体文件Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface IMultimediaFileTableService 
{
    /**
     * 查询多媒体文件
     * 
     * @param id 多媒体文件主键
     * @return 多媒体文件
     */
    public MultimediaFileTable selectMultimediaFileTableById(Long id);

    /**
     * 查询多媒体文件列表
     * 
     * @param multimediaFileTable 多媒体文件
     * @return 多媒体文件集合
     */
    public List<MultimediaFileTable> selectMultimediaFileTableList(MultimediaFileTable multimediaFileTable);

    /**
     * 新增多媒体文件
     * 
     * @param multimediaFileTable 多媒体文件
     * @return 结果
     */
    public int insertMultimediaFileTable(MultimediaFileTable multimediaFileTable);

    /**
     * 修改多媒体文件
     * 
     * @param multimediaFileTable 多媒体文件
     * @return 结果
     */
    public int updateMultimediaFileTable(MultimediaFileTable multimediaFileTable);

    /**
     * 批量删除多媒体文件
     * 
     * @param ids 需要删除的多媒体文件主键集合
     * @return 结果
     */
    public int deleteMultimediaFileTableByIds(Long[] ids);

    /**
     * 删除多媒体文件信息
     * 
     * @param id 多媒体文件主键
     * @return 结果
     */
    public int deleteMultimediaFileTableById(Long id);
}
