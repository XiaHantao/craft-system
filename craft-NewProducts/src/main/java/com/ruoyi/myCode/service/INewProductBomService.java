package com.ruoyi.myCode.service;

import java.io.InputStream;
import java.util.List;
import com.ruoyi.myCode.domain.NewProductBom;

/**
 * 新产品BOMService接口
 * 
 * @author xj
 * @date 2025-07-22
 */
public interface INewProductBomService 
{
    /**
     * 查询新产品BOM
     * 
     * @param id 新产品BOM主键
     * @return 新产品BOM
     */
    public NewProductBom selectNewProductBomById(Long id);

    /**
     * 查询新产品BOM列表
     * 
     * @param newProductBom 新产品BOM
     * @return 新产品BOM集合
     */
    public List<NewProductBom> selectNewProductBomList(NewProductBom newProductBom);

    /**
     * 新增新产品BOM
     * 
     * @param newProductBom 新产品BOM
     * @return 结果
     */
    public int insertNewProductBom(NewProductBom newProductBom);

    /**
     * 修改新产品BOM
     * 
     * @param newProductBom 新产品BOM
     * @return 结果
     */
    public int updateNewProductBom(NewProductBom newProductBom);

    /**
     * 批量删除新产品BOM
     * 
     * @param ids 需要删除的新产品BOM主键集合
     * @return 结果
     */
    public int deleteNewProductBomByIds(Long[] ids);

    /**
     * 删除新产品BOM信息
     * 
     * @param id 新产品BOM主键
     * @return 结果
     */
    public int deleteNewProductBomById(Long id);

    /**
     * 导入新产品BOM数据
     *
     * @param inputStream Excel文件输入流
     * @param projectCode 项目编号
     * @param projectName 项目名称
     */
    void importBom(InputStream inputStream, String projectCode, String projectName);

    /**
     * 检查项目数据是否存在
     *
     * @param projectCode 项目编号
     * @return 是否存在数据
     */
    boolean checkProjectDataExists(String projectCode);

    /**
     * 清空项目下的所有数据
     *
     * @param projectCode 项目编号
     * @return 删除的数据量
     */
    int cleanProjectData(String projectCode);

}
