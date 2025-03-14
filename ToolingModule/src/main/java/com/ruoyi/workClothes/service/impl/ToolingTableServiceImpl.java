package com.ruoyi.workClothes.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.ToolingTableMapper;
import com.ruoyi.workClothes.domain.ToolingTable;
import com.ruoyi.workClothes.service.IToolingTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工装详细Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
@Service
public class ToolingTableServiceImpl implements IToolingTableService 
{
    @Autowired
    private ToolingTableMapper toolingTableMapper;

    /**
     * 查询工装详细
     * 
     * @param id 工装详细主键
     * @return 工装详细
     */
    @Override
    public ToolingTable selectToolingTableById(Long id)
    {
        return toolingTableMapper.selectToolingTableById(id);
    }

    /**
     * 查询工装详细列表
     * 
     * @param toolingTable 工装详细
     * @return 工装详细
     */
    @Override
    public List<ToolingTable> selectToolingTableList(ToolingTable toolingTable)
    {
        return toolingTableMapper.selectToolingTableList(toolingTable);
    }

    /**
     * 导入工装详细
     *
     * @return 结果
     */
    @Override
    public int loadImport(MultipartFile file) {
        int rows = 0; // 用于记录成功导入的行数
        String parentWorkwear = null ;
        List<ToolingTable> toolingTableList = new ArrayList<>();

        try {
            // 获取文件的输入流
            InputStream inputStream = file.getInputStream();

            // 创建工作簿（XSSFWorkbook适用于.xlsx格式）
            Workbook workbook = new XSSFWorkbook(inputStream);

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 遍历所有行
            for (int rowIndex = 5; rowIndex <= sheet.getPhysicalNumberOfRows(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                // 跳过空行
                if (row == null) continue;

                // 检查某一列是否包含合并单元格的文字（例如"合计工时"）
                Cell firstCell = row.getCell(0); // 假设合并单元格会在第1列
                if (firstCell != null && "合计工时".equals(firstCell.getStringCellValue())) {
                    break; // 如果当前行包含“合计”，停止读取
                }

                // 获取每一列的数据（根据实际列数调整）
                ToolingTable toolingTable = new ToolingTable();

                // 获取并打印工具编号
                String toolNumber = getStringCellValue(row, 1);
                System.out.println("Tool Number: " + toolNumber);  // 打印工具编号
                toolingTable.setToolNumber(toolNumber);

                // 第一行工具编号作为父模具
                if (parentWorkwear == null) {
                    parentWorkwear = toolNumber;
                }
                toolingTable.setMoldOwnership(parentWorkwear);
                System.out.println("MoldOwnership: " + parentWorkwear);  // 打印工具编号

                // 获取并打印工具名称
                String toolName = getStringCellValue(row, 2);
                System.out.println("Tool Name: " + toolName);  // 打印工具名称
                toolingTable.setToolName(toolName);

                // 获取并打印合计数量
                double totalQuantity = row.getCell(4).getNumericCellValue();
                System.out.println("Total Quantity: " + totalQuantity);  // 打印合计数量

                // 如果总数量是0，则设置为null
                if (totalQuantity == 0) {
                    toolingTable.setTotalQuantity(null); // 设置为null
                } else {
                    toolingTable.setTotalQuantity((long) totalQuantity); // 设置为有效的数量
                }


                // 获取并打印材质
                String textureOfMaterial = getStringCellValue(row, 5);
                System.out.println("Texture Of Material: " + textureOfMaterial);  // 打印材质
                toolingTable.setTextureOfMaterial(textureOfMaterial);

                // 获取并打印下料尺寸
                String cuttingSize = getStringCellValue(row, 6);
                System.out.println("Cutting Size: " + cuttingSize);  // 打印下料尺寸
                toolingTable.setCuttingSize(cuttingSize);

                // 获取并打印总重量
                double totalWeight = row.getCell(8).getNumericCellValue();
                System.out.println("Total Weight: " + totalWeight);  // 打印总重量
                if (totalWeight == 0) {
                    toolingTable.setTotalWeight(null); // 设置为null
                } else {
                    toolingTable.setTotalWeight(BigDecimal.valueOf(totalWeight)); // 设置有效的重量
                }

                // 获取并打印原材料物料号
                String rawMaterialNumber = getStringCellValue(row, 9);
                System.out.println("Raw Material Number: " + rawMaterialNumber);  // 打印原材料物料号
                toolingTable.setRawMaterialNumber(rawMaterialNumber);

                // 获取并打印下料
                double materialCutting = row.getCell(10).getNumericCellValue();
                System.out.println("Material Cutting: " + materialCutting);  // 打印下料
                if (materialCutting == 0) {
                    toolingTable.setMaterialCutting(null); // 设置为null
                } else {
                    toolingTable.setMaterialCutting((long) materialCutting); // 设置有效的下料数
                }

                // 获取并打印金工
                double metalworking = row.getCell(11).getNumericCellValue();
                System.out.println("Metalworking: " + metalworking);  // 打印金工
                if (metalworking == 0) {
                    toolingTable.setMetalworking(null); // 设置为null
                } else {
                    toolingTable.setMetalworking((long) metalworking); // 设置有效的金工数
                }

                // 获取并打印组焊
                double assemblyWelding = row.getCell(12).getNumericCellValue();
                System.out.println("Assembly Welding: " + assemblyWelding);  // 打印组焊
                if (assemblyWelding == 0) {
                    toolingTable.setAssemblyWelding(null); // 设置为null
                } else {
                    toolingTable.setAssemblyWelding((long) assemblyWelding); // 设置有效的组焊数
                }

                // 获取并打印涂装
                double painting = row.getCell(13).getNumericCellValue();
                System.out.println("Painting: " + painting);  // 打印涂装
                if (painting == 0) {
                    toolingTable.setPainting(null); // 设置为null
                } else {
                    toolingTable.setPainting((long) painting); // 设置有效的涂装数
                }

                // 获取并打印装配
                double assembling = row.getCell(14).getNumericCellValue();
                System.out.println("Assembling: " + assembling);  // 打印装配
                if (assembling == 0) {
                    toolingTable.setAssembling(null); // 设置为null
                } else {
                    toolingTable.setAssembling((long) assembling); // 设置有效的装配数
                }

                // 获取并打印备注
                String remarks = getStringCellValue(row, 15);
                System.out.println("Remarks: " + remarks);  // 打印备注
                toolingTable.setRemarks(remarks);

                toolingTableList.add(toolingTable); // 将解析的数据添加到列表中
            }

            // 将列表批量插入数据库
            rows = toolingTableMapper.insertBatch(toolingTableList);
        } catch (Exception e) {
            e.printStackTrace();
            // 如果发生异常，记录错误信息
            return -1; // 返回失败标识
        }

        return rows; // 返回成功导入的行数
    }


    /**
     * 新增工装详细
     * 
     * @param toolingTable 工装详细
     * @return 结果
     */
    @Override
    public int insertToolingTable(ToolingTable toolingTable)
    {
        return toolingTableMapper.insertToolingTable(toolingTable);
    }

    /**
     * 修改工装详细
     * 
     * @param toolingTable 工装详细
     * @return 结果
     */
    @Override
    public int updateToolingTable(ToolingTable toolingTable)
    {
        return toolingTableMapper.updateToolingTable(toolingTable);
    }

    /**
     * 批量删除工装详细
     * 
     * @param ids 需要删除的工装详细主键
     * @return 结果
     */
    @Override
    public int deleteToolingTableByIds(Long[] ids)
    {
        return toolingTableMapper.deleteToolingTableByIds(ids);
    }

    /**
     * 删除工装详细信息
     * 
     * @param id 工装详细主键
     * @return 结果
     */
    @Override
    public int deleteToolingTableById(Long id)
    {
        return toolingTableMapper.deleteToolingTableById(id);
    }

    /**
     * 根据父编号查询工装详细列表
     *
     * @param toolingTable 工装详细
     * @return 工装详细集合
     */
    @Override
    public List<ToolingTable> selectToolingTableListbymoldOwnership(ToolingTable toolingTable, String moldOwnership) {
//        System.out.println("===0" + moldOwnership);
        return toolingTableMapper.selectToolingTableListbymoldOwnership(toolingTable ,moldOwnership);
    }


    /**
     * 查询共用工装详细列表
     *
     * @param toolingTable 工装详细
     * @return 工装详细
     */
    @Override
    public List<ToolingTable> selectshareToolingTableList(ToolingTable toolingTable) {
        return toolingTableMapper.selectshareToolingTableList(toolingTable);
    }

    @Override
    public List<ToolingTable> selectUpcomingChangeTimeToolingTableList(ToolingTable toolingTable) {

        return toolingTableMapper.selectUpcomingChangeTimeToolingTableList(toolingTable);    }



    // 获取单元格的内容，处理数字和字符串类型的情况
    public String getStringCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            return ""; // 如果单元格为空，返回空字符串
        }

        // 判断单元格类型
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue(); // 字符串类型直接返回
            case NUMERIC:
                // 如果是数字类型，可能是日期或普通数字，需要判断
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 如果是日期格式，则返回日期字符串
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(cell.getDateCellValue());
                } else {
                    // 如果是数字，转换为字符串
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue()); // 布尔类型返回字符串
            case FORMULA:
                return cell.getCellFormula(); // 公式类型返回公式字符串
            default:
                return ""; // 默认返回空字符串
        }
    }

}
