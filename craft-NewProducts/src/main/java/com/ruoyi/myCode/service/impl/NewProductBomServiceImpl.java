package com.ruoyi.myCode.service.impl;

import java.io.InputStream;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.myCode.mapper.NewProductBomMapper;
import com.ruoyi.myCode.domain.NewProductBom;
import com.ruoyi.myCode.service.INewProductBomService;
import com.ruoyi.myCode.utils.Excelutil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 新产品BOMService业务层处理
 * 
 * @author xj
 * @date 2025-07-22
 */
@Service
public class NewProductBomServiceImpl implements INewProductBomService 
{
    @Autowired
    private NewProductBomMapper newProductBomMapper;

    /**
     * 查询新产品BOM
     * 
     * @param id 新产品BOM主键
     * @return 新产品BOM
     */
    @Override
    public NewProductBom selectNewProductBomById(Long id)
    {
        return newProductBomMapper.selectNewProductBomById(id);
    }

    /**
     * 查询新产品BOM列表
     * 
     * @param newProductBom 新产品BOM
     * @return 新产品BOM
     */
    @Override
    public List<NewProductBom> selectNewProductBomList(NewProductBom newProductBom)
    {
        return newProductBomMapper.selectNewProductBomList(newProductBom);
    }

    /**
     * 新增新产品BOM
     * 
     * @param newProductBom 新产品BOM
     * @return 结果
     */
    @Override
    public int insertNewProductBom(NewProductBom newProductBom)
    {
        return newProductBomMapper.insertNewProductBom(newProductBom);
    }

    /**
     * 修改新产品BOM
     * 
     * @param newProductBom 新产品BOM
     * @return 结果
     */
    @Override
    public int updateNewProductBom(NewProductBom newProductBom)
    {
        int Bomresult = newProductBomMapper.updateNewProductBom(newProductBom);
        //如果修改成功，继续修改 NewproductDocumentDisplay
        if(Bomresult > 0) {
            int Displayresult = newProductBomMapper.updateNewproductDocumentDisplay(newProductBom);
            if(Displayresult > 0){
                return Displayresult;
            }
        }
        return 0;
    }

    /**
     * 批量删除新产品BOM
     * 
     * @param ids 需要删除的新产品BOM主键
     * @return 结果
     */
    @Override
    public int deleteNewProductBomByIds(Long[] ids)
    {
        return newProductBomMapper.deleteNewProductBomByIds(ids);
    }

    /**
     * 删除新产品BOM信息
     * 
     * @param id 新产品BOM主键
     * @return 结果
     */
    @Override
    public int deleteNewProductBomById(Long id)
    {
        return newProductBomMapper.deleteNewProductBomById(id);
    }
    @Override
    public boolean checkProjectDataExists(String projectCode) {
        return newProductBomMapper.checkDataExists(projectCode) > 0;
    }

    @Override
    public int cleanProjectData(String projectCode) {
        return newProductBomMapper.cleanProjectData(projectCode);
    }


    @Override
    @Transactional
    public void importBom(InputStream inputStream, String projectCode, String projectName) {
        // 检查项目数据是否存在
        boolean exists = checkProjectDataExists(projectCode);

        // 如果数据存在，先清空该项目下的数据
        if (exists) {
            cleanProjectData(projectCode);
        }

        // 解析Excel
        List<NewProductBom> bomList = Excelutil.importNewProductBom(
                inputStream,
                projectCode,
                projectName
        );

        // 按层级排序（确保父节点在子节点之前）
        bomList.sort(Comparator.comparingLong(NewProductBom::getLayer));

        // 创建临时ID到实际ID的映射表
        Map<Long, Long> tempIdMap = new HashMap<>();
        tempIdMap.put(0L, 0L); // 根节点映射（临时ID为0对应数据库根节点0）

        // 第一遍：插入所有节点（此时parent_id=0）
        for (NewProductBom item : bomList) {
            // 设置项目信息
            item.setProjectCode(projectCode);
            item.setProjectName(projectName);

            // 插入数据库（使用返回主键的insert语句）
            newProductBomMapper.insertNewProductBom(item);

            // 建立当前节点的临时ID与实际ID的映射
            tempIdMap.put(item.getTempId(), item.getId());
        }

        // 第二遍：更新父节点关系
        for (NewProductBom item : bomList) {
            // 获取临时父节点ID
            Long tempParentId = item.getTempParentId();

            // 通过映射表找到实际父节点ID
            Long realParentId = tempIdMap.get(tempParentId);
            if (realParentId == null) {
                // 如果找不到父节点，默认为根节点（0）
                realParentId = 0L;
            }
            // 如果父节点需要更新
            if (!realParentId.equals(item.getParentId())) {
                item.setParentId(realParentId);
                newProductBomMapper.updateNewProductBom(item);
            }
        }
}
}
