package com.ruoyi.process.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.process.mapper.ProcessQuotaInformationDictionaryMapper;
import com.ruoyi.process.domain.ProcessQuotaInformationDictionary;
import com.ruoyi.process.service.IProcessQuotaInformationDictionaryService;

/**
 * 工艺定额字典Service业务层处理
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
@Service
public class ProcessQuotaInformationDictionaryServiceImpl implements IProcessQuotaInformationDictionaryService 
{
    @Autowired
    private ProcessQuotaInformationDictionaryMapper processQuotaInformationDictionaryMapper;

    /**
     * 查询工艺定额字典
     * 
     * @param id 工艺定额字典主键
     * @return 工艺定额字典
     */
    @Override
    public ProcessQuotaInformationDictionary selectProcessQuotaInformationDictionaryById(Long id)
    {
        return processQuotaInformationDictionaryMapper.selectProcessQuotaInformationDictionaryById(id);
    }

    /**
     * 查询工艺定额字典列表
     * 
     * @param processQuotaInformationDictionary 工艺定额字典
     * @return 工艺定额字典
     */
    @Override
    public List<ProcessQuotaInformationDictionary> selectProcessQuotaInformationDictionaryList(ProcessQuotaInformationDictionary processQuotaInformationDictionary)
    {
        return processQuotaInformationDictionaryMapper.selectProcessQuotaInformationDictionaryList(processQuotaInformationDictionary);
    }

    /**
     * 新增工艺定额字典
     * 
     * @param processQuotaInformationDictionary 工艺定额字典
     * @return 结果
     */
    @Override
    public int insertProcessQuotaInformationDictionary(ProcessQuotaInformationDictionary processQuotaInformationDictionary)
    {
        return processQuotaInformationDictionaryMapper.insertProcessQuotaInformationDictionary(processQuotaInformationDictionary);
    }

    /**
     * 修改工艺定额字典
     * 
     * @param processQuotaInformationDictionary 工艺定额字典
     * @return 结果
     */
    @Override
    public int updateProcessQuotaInformationDictionary(ProcessQuotaInformationDictionary processQuotaInformationDictionary)
    {
        return processQuotaInformationDictionaryMapper.updateProcessQuotaInformationDictionary(processQuotaInformationDictionary);
    }

    /**
     * 批量删除工艺定额字典
     * 
     * @param ids 需要删除的工艺定额字典主键
     * @return 结果
     */
    @Override
    public int deleteProcessQuotaInformationDictionaryByIds(Long[] ids)
    {
        return processQuotaInformationDictionaryMapper.deleteProcessQuotaInformationDictionaryByIds(ids);
    }

    /**
     * 删除工艺定额字典信息
     * 
     * @param id 工艺定额字典主键
     * @return 结果
     */
    @Override
    public int deleteProcessQuotaInformationDictionaryById(Long id)
    {
        return processQuotaInformationDictionaryMapper.deleteProcessQuotaInformationDictionaryById(id);
    }
}
