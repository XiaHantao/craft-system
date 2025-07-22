package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductDocumentDisplayMapper;
import com.ruoyi.myCode.domain.NewProductDocumentDisplay;
import com.ruoyi.myCode.service.INewProductDocumentDisplayService;

/**
 * 新产品项目文件展示Service业务层处理
 * 
 * @author xj
 * @date 2025-07-21
 */
@Service
public class NewProductDocumentDisplayServiceImpl implements INewProductDocumentDisplayService 
{
    @Autowired
    private NewProductDocumentDisplayMapper newProductDocumentDisplayMapper;

    /**
     * 查询新产品项目文件展示
     * 
     * @param id 新产品项目文件展示主键
     * @return 新产品项目文件展示
     */
    @Override
    public NewProductDocumentDisplay selectNewProductDocumentDisplayById(Long id)
    {
        return newProductDocumentDisplayMapper.selectNewProductDocumentDisplayById(id);
    }

    /**
     * 查询新产品项目文件展示列表
     * 
     * @param newProductDocumentDisplay 新产品项目文件展示
     * @return 新产品项目文件展示
     */
    @Override
    public List<NewProductDocumentDisplay> selectNewProductDocumentDisplayList(NewProductDocumentDisplay newProductDocumentDisplay)
    {
        return newProductDocumentDisplayMapper.selectNewProductDocumentDisplayList(newProductDocumentDisplay);
    }

    /**
     * 新增新产品项目文件展示
     * 
     * @param newProductDocumentDisplay 新产品项目文件展示
     * @return 结果
     */
    @Override
    public int insertNewProductDocumentDisplay(NewProductDocumentDisplay newProductDocumentDisplay)
    {
        return newProductDocumentDisplayMapper.insertNewProductDocumentDisplay(newProductDocumentDisplay);
    }

    /**
     * 修改新产品项目文件展示
     * 
     * @param newProductDocumentDisplay 新产品项目文件展示
     * @return 结果
     */
    @Override
    public int updateNewProductDocumentDisplay(NewProductDocumentDisplay newProductDocumentDisplay)
    {
        return newProductDocumentDisplayMapper.updateNewProductDocumentDisplay(newProductDocumentDisplay);
    }

    /**
     * 批量删除新产品项目文件展示
     * 
     * @param ids 需要删除的新产品项目文件展示主键
     * @return 结果
     */
    @Override
    public int deleteNewProductDocumentDisplayByIds(Long[] ids)
    {
        return newProductDocumentDisplayMapper.deleteNewProductDocumentDisplayByIds(ids);
    }

    /**
     * 删除新产品项目文件展示信息
     * 
     * @param id 新产品项目文件展示主键
     * @return 结果
     */
    @Override
    public int deleteNewProductDocumentDisplayById(Long id)
    {
        return newProductDocumentDisplayMapper.deleteNewProductDocumentDisplayById(id);
    }
}
