package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessMapTableMapper;
import com.ruoyi.process.domain.ProcessMapTable;
import com.ruoyi.process.service.IProcessMapTableService;

/**
 * 工艺地图Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-22
 */
@Service
public class ProcessMapTableServiceImpl implements IProcessMapTableService 
{
    @Autowired
    private ProcessMapTableMapper processMapTableMapper;

    /**
     * 查询工艺地图
     * 
     * @param id 工艺地图主键
     * @return 工艺地图
     */
    @Override
    public ProcessMapTable selectProcessMapTableById(Long id)
    {
        return processMapTableMapper.selectProcessMapTableById(id);
    }

    /**
     * 查询工艺地图列表
     * 
     * @param processMapTable 工艺地图
     * @return 工艺地图
     */
    @Override
    public List<ProcessMapTable> selectProcessMapTableList(ProcessMapTable processMapTable)
    {
        return processMapTableMapper.selectProcessMapTableList(processMapTable);
    }

    /**
     * 新增工艺地图
     * 
     * @param processMapTable 工艺地图
     * @return 结果
     */
    @Override
    public int insertProcessMapTable(ProcessMapTable processMapTable)
    {
        return processMapTableMapper.insertProcessMapTable(processMapTable);
    }

    /**
     * 修改工艺地图
     * 
     * @param processMapTable 工艺地图
     * @return 结果
     */
    @Override
    public int updateProcessMapTable(ProcessMapTable processMapTable)
    {
        return processMapTableMapper.updateProcessMapTable(processMapTable);
    }

    /**
     * 批量删除工艺地图
     * 
     * @param ids 需要删除的工艺地图主键
     * @return 结果
     */
    @Override
    public int deleteProcessMapTableByIds(Long[] ids)
    {
        return processMapTableMapper.deleteProcessMapTableByIds(ids);
    }

    /**
     * 删除工艺地图信息
     * 
     * @param id 工艺地图主键
     * @return 结果
     */
    @Override
    public int deleteProcessMapTableById(Long id)
    {
        return processMapTableMapper.deleteProcessMapTableById(id);
    }
}
