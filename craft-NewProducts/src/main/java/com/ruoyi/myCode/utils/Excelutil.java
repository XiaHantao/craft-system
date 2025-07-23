package com.ruoyi.myCode.utils;
import com.ruoyi.myCode.domain.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Excelutil {
    private static String getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) return "";

        // 处理公式类型的单元格
        if (cell.getCellType() == CellType.FORMULA) {
            CellValue cellValue = evaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case NUMERIC:
                    // 动态格式化：最多两位小数，无意义零自动省略
                    DecimalFormat df = new DecimalFormat("#.##");
                    return df.format(cellValue.getNumberValue());
                case STRING:
                    return cellValue.getStringValue();
                default:
                    return "";
            }
        } else if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                // 日期类型，使用DataFormatter
                DataFormatter formatter = new DataFormatter();
                return formatter.formatCellValue(cell);
            } else {
                // 数值类型：动态格式化
                DecimalFormat df = new DecimalFormat("#.##");
                return df.format(cell.getNumericCellValue());
            }
        } else {
            // 其他类型
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        }
    }

    private static Long getLongCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return (long) cell.getNumericCellValue();
            default:
                return null;
        }
    }

    private static Date getDateCellValue(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }
                break; // 添加break避免穿透

            case STRING:  // 新增字符串处理
                return parseStringDate(cell.getStringCellValue());

            case FORMULA:  // 公式单元格可能需要特殊处理
                return getFormulaCellDate(cell);
        }
        return null;
    }

    // 字符串日期解析方法
    private static Date parseStringDate(String dateStr) {
        // 日期格式列表（可根据实际需求扩展）
        String[] patterns = {
                "yyyy-MM-dd", "dd/MM/yyyy",
                "MM/dd/yyyy", "yyyyMMdd",
                "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm:ss"
        };

        for (String pattern : patterns) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.setLenient(false); // 严格模式，避免错误转换
                return sdf.parse(dateStr.trim());
            } catch (ParseException ignored) {
                // 尝试下一个格式
            }
        }
        return null; // 所有格式都不匹配
    }

    // 公式单元格处理示例
    private static Date getFormulaCellDate(Cell cell) {
        try {
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            } else if (cell.getCachedFormulaResultType() == CellType.STRING) {
                return parseStringDate(cell.getStringCellValue());
            }
        } catch (IllegalStateException e) {
            // 处理错误
        }
        return null;
    }

    public static List<NewProductBom> importNewProductBom(
            InputStream inputStream,
            String projectCode,
            String projectName
    ) {
        List<NewProductBom> list = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            // 层级关系映射
            Map<Long, Long> lastNodeIdPerLevel = new HashMap<>();
            lastNodeIdPerLevel.put(0L, 0L); // 根节点映射

            long currentTempId = 1;

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                NewProductBom item = new NewProductBom();
                item.setTempId(currentTempId);
                item.setProjectCode(projectCode);
                item.setProjectName(projectName);

                // 解析层级 (第1列)
                Cell levelCell = row.getCell(0);
                Long currentLevel = 0L;
                if (levelCell != null) {
                    String levelValue = getCellValue(levelCell, evaluator);
                    if (levelValue != null && !levelValue.isEmpty() && levelValue.matches("\\d+")) {
                        currentLevel = Long.parseLong(levelValue);
                    }
                }
                item.setLayer(currentLevel);

                // 物料号 (第2列)
                item.setMaterialCode(getCellValue(row.getCell(1), evaluator));

                // 物料描述 (第3列)
                item.setMaterialDescription(getCellValue(row.getCell(2), evaluator));

                // 数量 (第4列)
                item.setQuantity(getCellValue(row.getCell(3), evaluator));

                // 采购类型 (第5列)
                item.setPurchaseType(getCellValue(row.getCell(4), evaluator));

                // 到货情况 (第6列)
                item.setArrivalStatus(getCellValue(row.getCell(5), evaluator));

                // 质检情况 (第7列)
                item.setInspectionStatus(getCellValue(row.getCell(6), evaluator));

                // 质检结果 (第8列)
                item.setInspectionResult(getCellValue(row.getCell(7), evaluator));

                // 质检结果处理 (第9列)
                item.setInspectionSolve(getCellValue(row.getCell(8), evaluator));

//                // 质检处理备注 (第10列)
//                item.setInspectionRemarks(getCellValue(row.getCell(9), evaluator));

                // 领用日期 (第11列)
                Cell receivingDateCell = row.getCell(9);
                if (receivingDateCell != null) {
                    item.setReceivingDate(parseDateCell(receivingDateCell, evaluator));
                }

                // 问题记录 (第12列)
                item.setIssueRecord(getCellValue(row.getCell(10), evaluator));

//                // 扩展字段 (第13-15列)
//                item.setExtField1(getCellValue(row.getCell(12), evaluator));
//                item.setExtField2(getCellValue(row.getCell(13), evaluator));
//                item.setExtField3(getCellValue(row.getCell(14), evaluator));

                // 设置父节点临时ID
                Long parentLevel = currentLevel > 0 ? currentLevel - 1 : 0;
                Long parentTempId = lastNodeIdPerLevel.getOrDefault(parentLevel, 0L);
                item.setTempParentId(parentTempId);

                // 更新层级映射
                lastNodeIdPerLevel.put(currentLevel, currentTempId);

                list.add(item);
                currentTempId++;
            }
        } catch (Exception e) {
            throw new RuntimeException("解析Excel失败: " + e.getMessage(), e);
        }
        return list;
    }

    private static Date parseDateCell(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) return null;

        try {
            // 处理公式日期
            if (cell.getCellType() == CellType.FORMULA) {
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue.getCellType() == CellType.NUMERIC && DateUtil.isValidExcelDate(cellValue.getNumberValue())) {
                    return DateUtil.getJavaDate(cellValue.getNumberValue());
                }
            }

            // 处理标准日期
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            }

            // 处理字符串日期
            if (cell.getCellType() == CellType.STRING) {
                return parseStringDate(cell.getStringCellValue());
            }

            // 处理数值日期
            if (cell.getCellType() == CellType.NUMERIC) {
                return DateUtil.getJavaDate(cell.getNumericCellValue());
            }
        } catch (Exception e) {
            // 忽略解析错误
        }
        return null;
    }
}