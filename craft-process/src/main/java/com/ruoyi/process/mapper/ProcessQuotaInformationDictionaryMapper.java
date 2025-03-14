package com.ruoyi.process.mapper;

import java.util.List;
import com.ruoyi.process.domain.ProcessQuotaInformationDictionary;

/**
 * 工艺定额字典Mapper接口
 * 
 * @author Kanna Bush
 * @date 2025-02-28
 */
public interface ProcessQuotaInformationDictionaryMapper 
{
    /**
     * 查询工艺定额字典
     * 
     * @param id 工艺定额字典主键
     * @return 工艺定额字典
     */
    public ProcessQuotaInformationDictionary selectProcessQuotaInformationDictionaryById(Long id);

    /**
     * 查询工艺定额字典列表
     * 
     * @param processQuotaInformationDictionary 工艺定额字典
     * @return 工艺定额字典集合
     */
    public List<ProcessQuotaInformationDictionary> selectProcessQuotaInformationDictionaryList(ProcessQuotaInformationDictionary processQuotaInformationDictionary);

    /**
     * 新增工艺定额字典
     * 
     * @param processQuotaInformationDictionary 工艺定额字典
     * @return 结果
     */
    public int insertProcessQuotaInformationDictionary(ProcessQuotaInformationDictionary processQuotaInformationDictionary);

    /**
     * 修改工艺定额字典
     * 
     * @param processQuotaInformationDictionary 工艺定额字典
     * @return 结果
     */
    public int updateProcessQuotaInformationDictionary(ProcessQuotaInformationDictionary processQuotaInformationDictionary);

    /**
     * 删除工艺定额字典
     * 
     * @param id 工艺定额字典主键
     * @return 结果
     */
    public int deleteProcessQuotaInformationDictionaryById(Long id);

    /**
     * 批量删除工艺定额字典
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessQuotaInformationDictionaryByIds(Long[] ids);
}
