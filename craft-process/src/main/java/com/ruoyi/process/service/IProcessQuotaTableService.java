package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ProcessQuotaTable;

/**
 * 工艺定额Service接口
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
public interface IProcessQuotaTableService 
{
    /**
     * 查询工艺定额
     * 
     * @param id 工艺定额主键
     * @return 工艺定额
     */
    public ProcessQuotaTable selectProcessQuotaTableById(Long id);

    /**
     * 查询工艺定额列表
     *
     * @param processQuotaTable 工艺定额
     * @return 工艺定额集合
     */
    public List<ProcessQuotaTable> selectProcessQuotaTableList(ProcessQuotaTable processQuotaTable);

    /**
     * 查询当前车型关联的工艺定额列表
     *
     * @param vehicleModel 工艺定额
     * @return 工艺定额集合
     */
    public List<ProcessQuotaTable> selectRelatedList(String vehicleModel);

    /**
     * 新增工艺定额
     * 
     * @param processQuotaTable 工艺定额
     * @return 结果
     */
    public int insertProcessQuotaTable(ProcessQuotaTable processQuotaTable);

    /**
     * 修改工艺定额
     * 
     * @param processQuotaTable 工艺定额
     * @return 结果
     */
    public int updateProcessQuotaTable(ProcessQuotaTable processQuotaTable);

    /**
     * 批量删除工艺定额
     * 
     * @param vehicleModels 需要删除的工艺定额主键集合
     * @return 结果
     */
    public int deleteProcessQuotaTableByIds(String[] vehicleModels);

    /**
     * 批量删除step不为空的工艺定额
     *
     * @param vehicleModels 需要删除的工艺定额主键集合
     * @return 结果
     */
    public int deleteProcessQuotaTableByIds02(String[] vehicleModels);

    /**
     * 删除工艺定额信息
     * 
     * @param vehicleModel 工艺定额主键
     * @return 结果
     */
    public int deleteProcessQuotaTableById(String vehicleModel);
}
