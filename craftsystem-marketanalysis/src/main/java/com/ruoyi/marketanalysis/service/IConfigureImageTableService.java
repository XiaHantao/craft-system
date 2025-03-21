package com.ruoyi.marketanalysis.service;

import java.util.List;
import com.ruoyi.marketanalysis.domain.ConfigureImageTable;

/**
 * 配置图片表Service接口
 * 
 * @author ruoyi
 * @date 2025-03-19
 */
public interface IConfigureImageTableService 
{
    /**
     * 查询配置图片表
     * 
     * @param id 配置图片表主键
     * @return 配置图片表
     */
    public ConfigureImageTable selectConfigureImageTableById(Long id);

    /**
     * 查询配置图片表列表
     * 
     * @param configureImageTable 配置图片表
     * @return 配置图片表集合
     */
    public List<ConfigureImageTable> selectConfigureImageTableList(ConfigureImageTable configureImageTable);

    /**
     * 新增配置图片表
     * 
     * @param configureImageTable 配置图片表
     * @return 结果
     */
    public int insertConfigureImageTable(ConfigureImageTable configureImageTable);

    /**
     * 修改配置图片表
     * 
     * @param configureImageTable 配置图片表
     * @return 结果
     */
    public int updateConfigureImageTable(ConfigureImageTable configureImageTable);

    /**
     * 批量删除配置图片表
     * 
     * @param ids 需要删除的配置图片表主键集合
     * @return 结果
     */
    public int deleteConfigureImageTableByIds(Long[] ids);

    /**
     * 删除配置图片表信息
     * 
     * @param id 配置图片表主键
     * @return 结果
     */
    public int deleteConfigureImageTableById(Long id);
}
