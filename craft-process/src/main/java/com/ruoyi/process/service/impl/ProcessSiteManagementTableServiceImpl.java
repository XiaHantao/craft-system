package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessSiteManagementTableMapper;
import com.ruoyi.process.domain.ProcessSiteManagementTable;
import com.ruoyi.process.service.IProcessSiteManagementTableService;

/**
 * 工艺现场管理Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-21
 */
@Service
public class ProcessSiteManagementTableServiceImpl implements IProcessSiteManagementTableService 
{
    @Autowired
    private ProcessSiteManagementTableMapper processSiteManagementTableMapper;

    /**
     * 查询工艺现场管理
     * 
     * @param id 工艺现场管理主键
     * @return 工艺现场管理
     */
    @Override
    public ProcessSiteManagementTable selectProcessSiteManagementTableById(Long id)
    {
        return processSiteManagementTableMapper.selectProcessSiteManagementTableById(id);
    }

    /**
     * 查询工艺现场管理列表
     * 
     * @param processSiteManagementTable 工艺现场管理
     * @return 工艺现场管理
     */
    @Override
    public List<ProcessSiteManagementTable> selectProcessSiteManagementTableList(ProcessSiteManagementTable processSiteManagementTable)
    {
        return processSiteManagementTableMapper.selectProcessSiteManagementTableList(processSiteManagementTable);
    }

    /**
     * 新增工艺现场管理
     * 
     * @param processSiteManagementTable 工艺现场管理
     * @return 结果
     */
    @Override
    public int insertProcessSiteManagementTable(ProcessSiteManagementTable processSiteManagementTable)
    {
        return processSiteManagementTableMapper.insertProcessSiteManagementTable(processSiteManagementTable);
    }

    /**
     * 修改工艺现场管理
     * 
     * @param processSiteManagementTable 工艺现场管理
     * @return 结果
     */
    @Override
    public int updateProcessSiteManagementTable(ProcessSiteManagementTable processSiteManagementTable)
    {
        return processSiteManagementTableMapper.updateProcessSiteManagementTable(processSiteManagementTable);
    }

    /**
     * 批量删除工艺现场管理
     * 
     * @param ids 需要删除的工艺现场管理主键
     * @return 结果
     */
    @Override
    public int deleteProcessSiteManagementTableByIds(Long[] ids)
    {
        return processSiteManagementTableMapper.deleteProcessSiteManagementTableByIds(ids);
    }

    /**
     * 删除工艺现场管理信息
     * 
     * @param id 工艺现场管理主键
     * @return 结果
     */
    @Override
    public int deleteProcessSiteManagementTableById(Long id)
    {
        return processSiteManagementTableMapper.deleteProcessSiteManagementTableById(id);
    }
}
