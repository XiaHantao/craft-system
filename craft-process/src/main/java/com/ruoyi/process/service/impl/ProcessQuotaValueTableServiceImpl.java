package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessQuotaValueTableMapper;
import com.ruoyi.process.domain.ProcessQuotaValueTable;
import com.ruoyi.process.service.IProcessQuotaValueTableService;

/**
 * 工艺定额值Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-03-04
 */
@Service
public class ProcessQuotaValueTableServiceImpl implements IProcessQuotaValueTableService 
{
    @Autowired
    private ProcessQuotaValueTableMapper processQuotaValueTableMapper;

    /**
     * 查询工艺定额值
     * 
     * @param id 工艺定额值主键
     * @return 工艺定额值
     */
    @Override
    public ProcessQuotaValueTable selectProcessQuotaValueTableById(Long id)
    {
        return processQuotaValueTableMapper.selectProcessQuotaValueTableById(id);
    }

    /**
     * 查询工艺定额值列表
     * 
     * @param processQuotaValueTable 工艺定额值
     * @return 工艺定额值
     */
    @Override
    public List<ProcessQuotaValueTable> selectProcessQuotaValueTableList(ProcessQuotaValueTable processQuotaValueTable)
    {
        return processQuotaValueTableMapper.selectProcessQuotaValueTableList(processQuotaValueTable);
    }

    /**
     * 新增工艺定额值
     * 
     * @param processQuotaValueTable 工艺定额值
     * @return 结果
     */
    @Override
    public int insertProcessQuotaValueTable(ProcessQuotaValueTable processQuotaValueTable)
    {
        return processQuotaValueTableMapper.insertProcessQuotaValueTable(processQuotaValueTable);
    }

    /**
     * 修改工艺定额值
     * 
     * @param processQuotaValueTable 工艺定额值
     * @return 结果
     */
    @Override
    public int updateProcessQuotaValueTable(ProcessQuotaValueTable processQuotaValueTable)
    {
        return processQuotaValueTableMapper.updateProcessQuotaValueTable(processQuotaValueTable);
    }

    /**
     * 批量删除工艺定额值
     * 
     * @param ids 需要删除的工艺定额值主键
     * @return 结果
     */
    @Override
    public int deleteProcessQuotaValueTableByIds(String[] vehicleModels)
    {
        return processQuotaValueTableMapper.deleteProcessQuotaValueTableByIds(vehicleModels);
    }

    /**
     * 删除工艺定额值信息
     * 
     * @param id 工艺定额值主键
     * @return 结果
     */
    @Override
    public int deleteProcessQuotaValueTableById(Long id)
    {
        return processQuotaValueTableMapper.deleteProcessQuotaValueTableById(id);
    }
}
