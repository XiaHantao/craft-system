package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductDocumentDisplay;

/**
 * 新产品项目文件展示Service接口
 * 
 * @author xj
 * @date 2025-07-21
 */
public interface INewProductDocumentDisplayService 
{
    /**
     * 查询新产品项目文件展示
     * 
     * @param id 新产品项目文件展示主键
     * @return 新产品项目文件展示
     */
    public NewProductDocumentDisplay selectNewProductDocumentDisplayById(Long id);

    /**
     * 查询新产品项目文件展示列表
     * 
     * @param newProductDocumentDisplay 新产品项目文件展示
     * @return 新产品项目文件展示集合
     */
    public List<NewProductDocumentDisplay> selectNewProductDocumentDisplayList(NewProductDocumentDisplay newProductDocumentDisplay);

    /**
     * 新增新产品项目文件展示
     * 
     * @param newProductDocumentDisplay 新产品项目文件展示
     * @return 结果
     */
    public int insertNewProductDocumentDisplay(NewProductDocumentDisplay newProductDocumentDisplay);

    /**
     * 修改新产品项目文件展示
     * 
     * @param newProductDocumentDisplay 新产品项目文件展示
     * @return 结果
     */
    public int updateNewProductDocumentDisplay(NewProductDocumentDisplay newProductDocumentDisplay);

    /**
     * 批量删除新产品项目文件展示
     * 
     * @param ids 需要删除的新产品项目文件展示主键集合
     * @return 结果
     */
    public int deleteNewProductDocumentDisplayByIds(Long[] ids);

    /**
     * 删除新产品项目文件展示信息
     * 
     * @param id 新产品项目文件展示主键
     * @return 结果
     */
    public int deleteNewProductDocumentDisplayById(Long id);
}
