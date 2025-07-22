package com.ruoyi.myCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductFilesMapper;
import com.ruoyi.myCode.domain.NewProductFiles;
import com.ruoyi.myCode.service.INewProductFilesService;

/**
 * 新产品文件提交Service业务层处理
 * 
 * @author xj
 * @date 2025-07-21
 */
@Service
public class NewProductFilesServiceImpl implements INewProductFilesService 
{
    @Autowired
    private NewProductFilesMapper newProductFilesMapper;

    /**
     * 查询新产品文件提交
     * 
     * @param id 新产品文件提交主键
     * @return 新产品文件提交
     */
    @Override
    public NewProductFiles selectNewProductFilesById(Long id)
    {
        return newProductFilesMapper.selectNewProductFilesById(id);
    }

    /**
     * 查询新产品文件提交列表
     * 
     * @param newProductFiles 新产品文件提交
     * @return 新产品文件提交
     */
    @Override
    public List<NewProductFiles> selectNewProductFilesList(NewProductFiles newProductFiles)
    {
        return newProductFilesMapper.selectNewProductFilesList(newProductFiles);
    }

    /**
     * 新增新产品文件提交
     * 
     * @param newProductFiles 新产品文件提交
     * @return 结果
     */
    @Override
    public int insertNewProductFiles(NewProductFiles newProductFiles)
    {
        return newProductFilesMapper.insertNewProductFiles(newProductFiles);
    }

    /**
     * 修改新产品文件提交
     * 
     * @param newProductFiles 新产品文件提交
     * @return 结果
     */
    @Override
    public int updateNewProductFiles(NewProductFiles newProductFiles)
    {
        return newProductFilesMapper.updateNewProductFiles(newProductFiles);
    }

    /**
     * 批量删除新产品文件提交
     * 
     * @param ids 需要删除的新产品文件提交主键
     * @return 结果
     */
    @Override
    public int deleteNewProductFilesByIds(Long[] ids)
    {
        return newProductFilesMapper.deleteNewProductFilesByIds(ids);
    }

    /**
     * 删除新产品文件提交信息
     * 
     * @param id 新产品文件提交主键
     * @return 结果
     */
    @Override
    public int deleteNewProductFilesById(Long id)
    {
        return newProductFilesMapper.deleteNewProductFilesById(id);
    }
}
