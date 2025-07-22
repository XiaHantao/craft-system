package com.ruoyi.myCode.service;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductFiles;

/**
 * 新产品文件提交Service接口
 * 
 * @author xj
 * @date 2025-07-21
 */
public interface INewProductFilesService 
{
    /**
     * 查询新产品文件提交
     * 
     * @param id 新产品文件提交主键
     * @return 新产品文件提交
     */
    public NewProductFiles selectNewProductFilesById(Long id);

    /**
     * 查询新产品文件提交列表
     * 
     * @param newProductFiles 新产品文件提交
     * @return 新产品文件提交集合
     */
    public List<NewProductFiles> selectNewProductFilesList(NewProductFiles newProductFiles);

    /**
     * 新增新产品文件提交
     * 
     * @param newProductFiles 新产品文件提交
     * @return 结果
     */
    public int insertNewProductFiles(NewProductFiles newProductFiles);

    /**
     * 修改新产品文件提交
     * 
     * @param newProductFiles 新产品文件提交
     * @return 结果
     */
    public int updateNewProductFiles(NewProductFiles newProductFiles);

    /**
     * 批量删除新产品文件提交
     * 
     * @param ids 需要删除的新产品文件提交主键集合
     * @return 结果
     */
    public int deleteNewProductFilesByIds(Long[] ids);

    /**
     * 删除新产品文件提交信息
     * 
     * @param id 新产品文件提交主键
     * @return 结果
     */
    public int deleteNewProductFilesById(Long id);
}
