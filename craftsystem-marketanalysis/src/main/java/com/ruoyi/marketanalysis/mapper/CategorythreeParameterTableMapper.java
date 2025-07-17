package com.ruoyi.marketanalysis.mapper;

import java.util.List;
import com.ruoyi.marketanalysis.domain.CategorythreeParameterTable;

/**
 * 三类车参数Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-01
 */
public interface CategorythreeParameterTableMapper 
{
    /**
     * 查询三类车参数
     * 
     * @param id 三类车参数主键
     * @return 三类车参数
     */
    public CategorythreeParameterTable selectCategorythreeParameterTableById(Long id);

    /**
     * 查询三类车参数列表
     * 
     * @param categorythreeParameterTable 三类车参数
     * @return 三类车参数集合
     */
    public List<CategorythreeParameterTable> selectCategorythreeParameterTableList(CategorythreeParameterTable categorythreeParameterTable);

    /**
     * 新增三类车参数
     * 
     * @param categorythreeParameterTable 三类车参数
     * @return 结果
     */
    public int insertCategorythreeParameterTable(CategorythreeParameterTable categorythreeParameterTable);

    /**
     * 修改三类车参数
     * 
     * @param categorythreeParameterTable 三类车参数
     * @return 结果
     */
    public int updateCategorythreeParameterTable(CategorythreeParameterTable categorythreeParameterTable);

    /**
     * 删除三类车参数
     * 
     * @param id 三类车参数主键
     * @return 结果
     */
    public int deleteCategorythreeParameterTableById(Long id);

    /**
     * 批量删除三类车参数
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategorythreeParameterTableByIds(Long[] ids);
    int batchInsertCategorythreeParameterTable(List<CategorythreeParameterTable> list);
    int checkDataExists();
    int cleanTable();
}
