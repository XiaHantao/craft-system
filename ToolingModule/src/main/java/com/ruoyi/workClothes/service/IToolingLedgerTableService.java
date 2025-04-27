package com.ruoyi.workClothes.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.workClothes.domain.ToolingLedgerTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工装台账Service接口
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
public interface IToolingLedgerTableService 
{
    /**
     * 查询工装台账
     * 
     * @param id 工装台账主键
     * @return 工装台账
     */
    public ToolingLedgerTable selectToolingLedgerTableById(Long id);

    /**
     * 查询工装台账列表
     * 
     * @param toolingLedgerTable 工装台账
     * @return 工装台账集合
     */
    public List<ToolingLedgerTable> selectToolingLedgerTableList(ToolingLedgerTable toolingLedgerTable);

    /**
     * 新增工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    public int insertToolingLedgerTable(ToolingLedgerTable toolingLedgerTable);

    /**
     * 修改工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    public int updateToolingLedgerTable(ToolingLedgerTable toolingLedgerTable);

    /**
     * 批量删除工装台账
     * 
     * @param ids 需要删除的工装台账主键集合
     * @return 结果
     */
    public int deleteToolingLedgerTableByIds(Long[] ids);

    /**
     * 删除工装台账信息
     * 
     * @param id 工装台账主键
     * @return 结果
     */
    public int deleteToolingLedgerTableById(Long id);

    int updateWorkClothesfile(Map<String, Object> fileform);

    int loadImport(MultipartFile file);

}
