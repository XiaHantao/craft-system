package com.ruoyi.myCode.mapper;

import java.util.List;
import com.ruoyi.myCode.domain.NewProductBom;

/**
 * 新产品BOMMapper接口
 * 
 * @author xj
 * @date 2025-07-22
 */
public interface NewProductBomMapper 
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
    //public int updateNewproductDocumentDisplay(NewProductBom newProductBom);

    /**
     * 删除新产品BOM
     * 
     * @param id 新产品BOM主键
     * @return 结果
     */
    public int deleteNewProductBomById(Long id);

    /**
     * 批量删除新产品BOM
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewProductBomByIds(Long[] ids);
    /**
     * 批量插入新产品BOM
     *
     * @param list 新产品BOM列表
     * @return 结果
     */
    int batchInsertNewProductBom(List<NewProductBom> list);

    /**
     * 检查项目数据是否存在
     *
     * @param projectCode 项目编号
     * @return 存在的数据数量
     */
    int checkDataExists(String projectCode);

    /**
     * 清空项目下的所有数据
     *
     * @param projectCode 项目编号
     * @return 结果
     */
    int cleanProjectData(String projectCode);
    /**
     * 批量更新父节点ID
     * @param list 需要更新的节点列表
     * @return 更新的记录数
     */
    int batchUpdateParentId(List<NewProductBom> list);
}
