package com.ruoyi.marketanalysis.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.marketanalysis.mapper.PerformanceTableMapper;
import com.ruoyi.marketanalysis.domain.PerformanceTable;
import com.ruoyi.marketanalysis.service.IPerformanceTableService;
import com.ruoyi.marketanalysis.utils.ExcelUtils;
/**
 * 性能表Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-07
 */
@Service
public class PerformanceTableServiceImpl implements IPerformanceTableService 
{
    @Autowired
    private PerformanceTableMapper performanceTableMapper;

    /**
     * 查询性能表
     * 
     * @param id 性能表主键
     * @return 性能表
     */
    @Override
    public PerformanceTable selectPerformanceTableById(Long id)
    {
        return performanceTableMapper.selectPerformanceTableById(id);
    }

    /**
     * 查询性能表列表
     * 
     * @param performanceTable 性能表
     * @return 性能表
     */
    @Override
    public List<PerformanceTable> selectPerformanceTableList(PerformanceTable performanceTable)
    {
        return performanceTableMapper.selectPerformanceTableList(performanceTable);
    }

    /**
     * 新增性能表
     * 
     * @param performanceTable 性能表
     * @return 结果
     */
    @Override
    public int insertPerformanceTable(PerformanceTable performanceTable)
    {
        return performanceTableMapper.insertPerformanceTable(performanceTable);
    }

    /**
     * 修改性能表
     * 
     * @param performanceTable 性能表
     * @return 结果
     */
    @Override
    public int updatePerformanceTable(PerformanceTable performanceTable)
    {
        return performanceTableMapper.updatePerformanceTable(performanceTable);
    }

    /**
     * 批量删除性能表
     * 
     * @param ids 需要删除的性能表主键
     * @return 结果
     */
    @Override
    public int deletePerformanceTableByIds(Long[] ids)
    {
        return performanceTableMapper.deletePerformanceTableByIds(ids);
    }

    /**
     * 删除性能表信息
     * 
     * @param id 性能表主键
     * @return 结果
     */
    @Override
    public int deletePerformanceTableById(Long id)
    {
        return performanceTableMapper.deletePerformanceTableById(id);
    }
    @Override
    public String importPerformanceTable(File excelFile, boolean updateSupport) {
        try {
            List<PerformanceTable> list = ExcelUtils.parsePerformanceExcel(excelFile);

            // 如果允许更新且存在数据，则清空表
            if (updateSupport && checkDataExists()) {
                performanceTableMapper.cleanTable();
            }

            // 批量插入数据
            performanceTableMapper.batchInsertPerformanceTable(list);
            return "成功导入 " + list.size() + " 条数据";
        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }

    @Override
    public boolean checkDataExists() {
        return performanceTableMapper.checkDataExists() > 0;
    }
}
