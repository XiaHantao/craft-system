package com.ruoyi.process.mapper;

import java.util.List;
import com.ruoyi.process.domain.ProcessSiteManagementTable;

/**
 * 工艺现场管理Mapper接口
 * 
 * @author Kanna Bush
 * @date 2025-02-21
 */
public interface ProcessSiteManagementTableMapper 
{
    /**
     * 查询工艺现场管理
     * 
     * @param id 工艺现场管理主键
     * @return 工艺现场管理
     */
    public ProcessSiteManagementTable selectProcessSiteManagementTableById(Long id);

    /**
     * 查询工艺现场管理列表
     * 
     * @param processSiteManagementTable 工艺现场管理
     * @return 工艺现场管理集合
     */
    public List<ProcessSiteManagementTable> selectProcessSiteManagementTableList(ProcessSiteManagementTable processSiteManagementTable);

    /**
     * 新增工艺现场管理
     * 
     * @param processSiteManagementTable 工艺现场管理
     * @return 结果
     */
    public int insertProcessSiteManagementTable(ProcessSiteManagementTable processSiteManagementTable);

    /**
     * 修改工艺现场管理
     * 
     * @param processSiteManagementTable 工艺现场管理
     * @return 结果
     */
    public int updateProcessSiteManagementTable(ProcessSiteManagementTable processSiteManagementTable);

    /**
     * 删除工艺现场管理
     * 
     * @param id 工艺现场管理主键
     * @return 结果
     */
    public int deleteProcessSiteManagementTableById(Long id);

    /**
     * 批量删除工艺现场管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessSiteManagementTableByIds(Long[] ids);
}
