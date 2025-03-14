package com.ruoyi.marketanalysis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.ConfigureImageTableMapper;
import com.ruoyi.marketanalysis.domain.ConfigureImageTable;
import com.ruoyi.marketanalysis.service.IConfigureImageTableService;

/**
 * 配置图片表Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-11
 */
@Service
public class ConfigureImageTableServiceImpl implements IConfigureImageTableService 
{
    @Autowired
    private ConfigureImageTableMapper configureImageTableMapper;

    /**
     * 查询配置图片表
     * 
     * @param id 配置图片表主键
     * @return 配置图片表
     */
    @Override
    public ConfigureImageTable selectConfigureImageTableById(Long id)
    {
        return configureImageTableMapper.selectConfigureImageTableById(id);
    }

    /**
     * 查询配置图片表列表
     * 
     * @param configureImageTable 配置图片表
     * @return 配置图片表
     */
    @Override
    public List<ConfigureImageTable> selectConfigureImageTableList(ConfigureImageTable configureImageTable)
    {
        return configureImageTableMapper.selectConfigureImageTableList(configureImageTable);
    }

    /**
     * 新增配置图片表
     * 
     * @param configureImageTable 配置图片表
     * @return 结果
     */
    @Override
    public int insertConfigureImageTable(ConfigureImageTable configureImageTable)
    {
        return configureImageTableMapper.insertConfigureImageTable(configureImageTable);
    }

    /**
     * 修改配置图片表
     * 
     * @param configureImageTable 配置图片表
     * @return 结果
     */
    @Override
    public int updateConfigureImageTable(ConfigureImageTable configureImageTable)
    {
        return configureImageTableMapper.updateConfigureImageTable(configureImageTable);
    }

    /**
     * 批量删除配置图片表
     * 
     * @param ids 需要删除的配置图片表主键
     * @return 结果
     */
    @Override
    public int deleteConfigureImageTableByIds(Long[] ids)
    {
        return configureImageTableMapper.deleteConfigureImageTableByIds(ids);
    }

    /**
     * 删除配置图片表信息
     * 
     * @param id 配置图片表主键
     * @return 结果
     */
    @Override
    public int deleteConfigureImageTableById(Long id)
    {
        return configureImageTableMapper.deleteConfigureImageTableById(id);
    }
}
