package com.ruoyi.workClothes.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.ToolingLedgerTableMapper;
import com.ruoyi.workClothes.domain.ToolingLedgerTable;
import com.ruoyi.workClothes.service.IToolingLedgerTableService;

/**
 * 工装台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
@Service
public class ToolingLedgerTableServiceImpl implements IToolingLedgerTableService 
{
    @Autowired
    private ToolingLedgerTableMapper toolingLedgerTableMapper;

    /**
     * 查询工装台账
     * 
     * @param id 工装台账主键
     * @return 工装台账
     */
    @Override
    public ToolingLedgerTable selectToolingLedgerTableById(Long id)
    {
        return toolingLedgerTableMapper.selectToolingLedgerTableById(id);
    }

    /**
     * 查询工装台账列表
     * 
     * @param toolingLedgerTable 工装台账
     * @return 工装台账
     */
    @Override
    public List<ToolingLedgerTable> selectToolingLedgerTableList(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.selectToolingLedgerTableList(toolingLedgerTable);
    }

    /**
     * 新增工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    @Override
    public int insertToolingLedgerTable(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.insertToolingLedgerTable(toolingLedgerTable);
    }

    /**
     * 修改工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    @Override
    public int updateToolingLedgerTable(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.updateToolingLedgerTable(toolingLedgerTable);
    }

    /**
     * 批量删除工装台账
     * 
     * @param ids 需要删除的工装台账主键
     * @return 结果
     */
    @Override
    public int deleteToolingLedgerTableByIds(Long[] ids)
    {
        return toolingLedgerTableMapper.deleteToolingLedgerTableByIds(ids);
    }

    /**
     * 删除工装台账信息
     * 
     * @param id 工装台账主键
     * @return 结果
     */
    @Override
    public int deleteToolingLedgerTableById(Long id)
    {
        return toolingLedgerTableMapper.deleteToolingLedgerTableById(id);
    }

    @Override
    public int updateWorkClothesfile(Map<String, Object> fileform) {
        String fileType = (String) fileform.get("fileType");
        String file = (String)fileform.get("file");
        String moldname = (String)fileform.get("moldname");
        System.out.println("传入数据" + fileform);
        // 1. 获取数据库中所有的moldNumber
        List<String> moldNumbers = toolingLedgerTableMapper.getAllMoldNumbers(); // 假设你有方法来获取所有moldNumbers

        // 2. 遍历moldNumbers与moldname进行对比
        for (String moldNumber : moldNumbers) {
            // 假设这里有一个方法获取模具信息，根据 moldname 匹配
            if (moldname.equals(moldNumber)) {
                // 如果 fileType 是 processDocuments
                if ("processDocuments".equals(fileType)) {
                    updateFilePath(moldNumber, file, "processDocuments");
                }
                // 如果 fileType 是 mbom
                else if ("mbom".equals(fileType)) {
                    updateFilePath(moldNumber, file, "mbom");
                }
                else if ("toolingDrawings".equals(fileType)) {
                    updateFilePath(moldNumber, file, "toolingDrawings");
                }
                return 1;
            }
        }
        return 0;
    }
    // 更新文件路径的具体方法
    private void updateFilePath(String moldNumber, String file, String fileType) {
        // 根据moldNumber和fileType更新数据库中的路径
        // 在这个方法中可以使用MyBatis的update方法来执行数据库更新操作

        if ("processDocuments".equals(fileType)) {
            toolingLedgerTableMapper.updateFilePath(moldNumber, file, "processDocuments");
        } else if ("mbom".equals(fileType)) {
            toolingLedgerTableMapper.updateFilePath(moldNumber, file, "mbom");
        }
        else if ("toolingDrawings".equals(fileType)) {
            toolingLedgerTableMapper.updateFilePath(moldNumber, file, "toolingDrawings");
        }
    }

}
