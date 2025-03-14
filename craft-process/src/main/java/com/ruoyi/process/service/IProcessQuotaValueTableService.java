package com.ruoyi.process.service;

import java.util.List;
import com.ruoyi.process.domain.ProcessQuotaValueTable;

/**
 * 工艺定额值Service接口
 * 
 * @author Kanna Bush
 * @date 2025-03-04
 */
public interface IProcessQuotaValueTableService 
{
    /**
     * 查询工艺定额值
     * 
     * @param id 工艺定额值主键
     * @return 工艺定额值
     */
    public ProcessQuotaValueTable selectProcessQuotaValueTableById(Long id);

    /**
     * 查询工艺定额值列表
     * 
     * @param processQuotaValueTable 工艺定额值
     * @return 工艺定额值集合
     */
    public List<ProcessQuotaValueTable> selectProcessQuotaValueTableList(ProcessQuotaValueTable processQuotaValueTable);

    /**
     * 新增工艺定额值
     * 
     * @param processQuotaValueTable 工艺定额值
     * @return 结果
     */
    public int insertProcessQuotaValueTable(ProcessQuotaValueTable processQuotaValueTable);

    /**
     * 修改工艺定额值
     * 
     * @param processQuotaValueTable 工艺定额值
     * @return 结果
     */
    public int updateProcessQuotaValueTable(ProcessQuotaValueTable processQuotaValueTable);

    /**
     * 批量删除工艺定额值
     * 
     * @param vehicleModels 需要删除的工艺定额值主键集合
     * @return 结果
     */
    public int deleteProcessQuotaValueTableByIds(String[] vehicleModels);

    /**
     * 删除工艺定额值信息
     * 
     * @param id 工艺定额值主键
     * @return 结果
     */
    public int deleteProcessQuotaValueTableById(Long id);
}
