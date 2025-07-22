package com.ruoyi.myCode.mapper;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductFiles;

/**
 * 新产品文件提交Mapper接口
 * 
 * @author xj
 * @date 2025-07-21
 */
public interface NewProductFilesMapper 
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
     * 删除新产品文件提交
     * 
     * @param id 新产品文件提交主键
     * @return 结果
     */
    public int deleteNewProductFilesById(Long id);

    /**
     * 批量删除新产品文件提交
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewProductFilesByIds(Long[] ids);
}
