package com.ruoyi.workClothes.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.NonstructuraltoolingTableMapper;
import com.ruoyi.workClothes.domain.NonstructuraltoolingTable;
import com.ruoyi.workClothes.service.INonstructuraltoolingTableService;

/**
 * 非结构工装台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@Service
public class NonstructuraltoolingTableServiceImpl implements INonstructuraltoolingTableService 
{
    @Autowired
    private NonstructuraltoolingTableMapper nonstructuraltoolingTableMapper;

    /**
     * 查询非结构工装台账
     * 
     * @param id 非结构工装台账主键
     * @return 非结构工装台账
     */
    @Override
    public NonstructuraltoolingTable selectNonstructuraltoolingTableById(Long id)
    {
        return nonstructuraltoolingTableMapper.selectNonstructuraltoolingTableById(id);
    }

    /**
     * 查询非结构工装台账列表
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账
     */
    @Override
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return nonstructuraltoolingTableMapper.selectNonstructuraltoolingTableList(nonstructuraltoolingTable);
    }

    /**
     * 查询金工工装台账列表
     *
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账
     */
    @Override
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList1(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return nonstructuraltoolingTableMapper.selectNonstructuraltoolingTableList1(nonstructuraltoolingTable);
    }

    /**
     * 查询备料工装台账列表
     *
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 非结构工装台账
     */
    @Override
    public List<NonstructuraltoolingTable> selectNonstructuraltoolingTableList2(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return nonstructuraltoolingTableMapper.selectNonstructuraltoolingTableList2(nonstructuraltoolingTable);
    }

    /**
     * 新增非结构工装台账
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 结果
     */
    @Override
    public int insertNonstructuraltoolingTable(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        nonstructuraltoolingTable.setCreateTime(DateUtils.getNowDate());
        return nonstructuraltoolingTableMapper.insertNonstructuraltoolingTable(nonstructuraltoolingTable);
    }

    /**
     * 修改非结构工装台账
     * 
     * @param nonstructuraltoolingTable 非结构工装台账
     * @return 结果
     */
    @Override
    public int updateNonstructuraltoolingTable(NonstructuraltoolingTable nonstructuraltoolingTable)
    {
        return nonstructuraltoolingTableMapper.updateNonstructuraltoolingTable(nonstructuraltoolingTable);
    }

    /**
     * 批量删除非结构工装台账
     * 
     * @param ids 需要删除的非结构工装台账主键
     * @return 结果
     */
    @Override
    public int deleteNonstructuraltoolingTableByIds(Long[] ids)
    {
        return nonstructuraltoolingTableMapper.deleteNonstructuraltoolingTableByIds(ids);
    }

    /**
     * 删除非结构工装台账信息
     * 
     * @param id 非结构工装台账主键
     * @return 结果
     */
    @Override
    public int deleteNonstructuraltoolingTableById(Long id)
    {
        return nonstructuraltoolingTableMapper.deleteNonstructuraltoolingTableById(id);
    }

    @Override
    public int updateWorkClothesfile(Map<String, Object> fileform) {
        String fileType = (String) fileform.get("fileType");
        String file = (String)fileform.get("file");
        String moldname = (String)fileform.get("moldname");
        String owner = (String)fileform.get("owner");
        System.out.println("传入数据" + fileform);
        // 1. 获取数据库中所有的moldNumber
        List<String> moldNumbers = nonstructuraltoolingTableMapper.getAllMoldNumbers(); // 假设你有方法来获取所有moldNumbers

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
            nonstructuraltoolingTableMapper.updateFilePath(moldNumber, file, "processDocuments");
        } else if ("mbom".equals(fileType)) {
            nonstructuraltoolingTableMapper.updateFilePath(moldNumber, file, "mbom");
        }
        else if ("toolingDrawings".equals(fileType)) {
            nonstructuraltoolingTableMapper.updateFilePath(moldNumber, file, "toolingDrawings");
        }
    }


}
