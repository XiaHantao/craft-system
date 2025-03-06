package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessQuotaTableMapper;
import com.ruoyi.process.domain.ProcessQuotaTable;
import com.ruoyi.process.service.IProcessQuotaTableService;

/**
 * 工艺定额Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
@Service
public class ProcessQuotaTableServiceImpl implements IProcessQuotaTableService 
{
    @Autowired
    private ProcessQuotaTableMapper processQuotaTableMapper;

    /**
     * 查询工艺定额
     * 
     * @param id 工艺定额主键
     * @return 工艺定额
     */
    @Override
    public ProcessQuotaTable selectProcessQuotaTableById(Long id)
    {
        return processQuotaTableMapper.selectProcessQuotaTableById(id);
    }

    /**
     * 查询工艺定额列表
     * 
     * @param processQuotaTable 工艺定额
     * @return 工艺定额
     */
    @Override
    public List<ProcessQuotaTable> selectProcessQuotaTableList(ProcessQuotaTable processQuotaTable)
    {
        return processQuotaTableMapper.selectProcessQuotaTableList(processQuotaTable);
    }

    /**
     * 查询当前车型关联的工艺定额列表
     *
     * @param vehicleModel 工艺定额
     * @return 工艺定额
     */
    @Override
    public List<ProcessQuotaTable> selectRelatedList(String vehicleModel)
    {
        return processQuotaTableMapper.selectRelatedList(vehicleModel);
    }

    /**
     * 新增工艺定额
     * 
     * @param processQuotaTable 工艺定额
     * @return 结果
     */
    @Override
    public int insertProcessQuotaTable(ProcessQuotaTable processQuotaTable)
    {
        return processQuotaTableMapper.insertProcessQuotaTable(processQuotaTable);
    }

    /**
     * 修改工艺定额
     * 
     * @param processQuotaTable 工艺定额
     * @return 结果
     */
    @Override
    public int updateProcessQuotaTable(ProcessQuotaTable processQuotaTable)
    {
        return processQuotaTableMapper.updateProcessQuotaTable(processQuotaTable);
    }

    /**
     * 批量删除工艺定额
     * 
     * @param ids 需要删除的工艺定额主键
     * @return 结果
     */
    @Override
    public int deleteProcessQuotaTableByIds(Long[] ids)
    {
        return processQuotaTableMapper.deleteProcessQuotaTableByIds(ids);
    }

    /**
     * 删除工艺定额信息
     * 
     * @param id 工艺定额主键
     * @return 结果
     */
    @Override
    public int deleteProcessQuotaTableById(Long id)
    {
        return processQuotaTableMapper.deleteProcessQuotaTableById(id);
    }
}
