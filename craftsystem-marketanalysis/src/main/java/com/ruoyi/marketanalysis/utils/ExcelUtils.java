package com.ruoyi.marketanalysis.utils;

import com.ruoyi.marketanalysis.domain.SalesLedgerTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ruoyi.marketanalysis.domain.CostpriceTable;

public class ExcelUtils {

    public static List<SalesLedgerTable> parseExcel(File excelFile) throws IOException {
        List<SalesLedgerTable> salesLedgerTableList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        // 创建公式计算器
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                // Skip header row
                continue;
            }

            SalesLedgerTable salesLedgerTable = new SalesLedgerTable();
            salesLedgerTable.setBranch(getCellValue(row.getCell(0), evaluator));
            salesLedgerTable.setContractNumber(getCellValue(row.getCell(1), evaluator));
            salesLedgerTable.setOrderNumber(getCellValue(row.getCell(2), evaluator));
            salesLedgerTable.setOrderAcceptanceDate(getDateCellValue(row.getCell(3)));
            salesLedgerTable.setVehicleType(getCellValue(row.getCell(4), evaluator));
            salesLedgerTable.setNumber(getCellValue(row.getCell(5), evaluator));
            salesLedgerTable.setValveplateNumber(getCellValue(row.getCell(6), evaluator));
            salesLedgerTable.setGoodsFork(getCellValue(row.getCell(7), evaluator));
            salesLedgerTable.setGantry(getCellValue(row.getCell(8), evaluator));
            salesLedgerTable.setAirFilter(getCellValue(row.getCell(9), evaluator));
            salesLedgerTable.setAccessory(getCellValue(row.getCell(10), evaluator));
            salesLedgerTable.setTyre(getCellValue(row.getCell(11), evaluator));
            salesLedgerTable.setConfiguration(getCellValue(row.getCell(12), evaluator));
            salesLedgerTable.setCarNumber(getCellValue(row.getCell(13), evaluator));
            salesLedgerTable.setRemark(getCellValue(row.getCell(14), evaluator));
            salesLedgerTable.setDeliveryForm(getCellValue(row.getCell(15), evaluator));
            salesLedgerTable.setDeliveryLocation(getCellValue(row.getCell(16), evaluator));
            salesLedgerTable.setContactPerson(getCellValue(row.getCell(17), evaluator));
            salesLedgerTable.setPhoneNumber(getCellValue(row.getCell(18), evaluator));
            salesLedgerTable.setDeliveryTime(getDateCellValue(row.getCell(19)));
            salesLedgerTable.setPlanDepartureDate(getDateCellValue(row.getCell(20)));
            salesLedgerTable.setActualDepartureDate(getDateCellValue(row.getCell(21)));
            salesLedgerTable.setSystemDeliveryTime(getDateCellValue(row.getCell(22)));
            salesLedgerTable.setDeliveryOrderNumber(getCellValue(row.getCell(23), evaluator));
            salesLedgerTable.setCompletionTime(getDateCellValue(row.getCell(24)));
            salesLedgerTable.setRedemptionRate(getCellValue(row.getCell(25), evaluator));

            salesLedgerTableList.add(salesLedgerTable);
        }

        workbook.close();
        fis.close();
        return salesLedgerTableList;
    }

    // 修改后的getCellValue方法（支持公式计算）
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
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }
            default:
                return null;
        }
    }
    // 新增成本售价表解析方法
    public static List<CostpriceTable> parseCostpriceExcel(File excelFile) throws IOException {
        List<CostpriceTable> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fis);
        // 创建公式计算器
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // 跳过表头

            CostpriceTable item = new CostpriceTable();
            item.setVehicleType(getCellValue(row.getCell(1), evaluator));
            item.setCost(getCellValue(row.getCell(2), evaluator));
            item.setCarBody(getCellValue(row.getCell(3), evaluator));
            item.setLifting(getCellValue(row.getCell(4), evaluator));
            item.setOther(getCellValue(row.getCell(5), evaluator));
            item.setPanjinPricing(getCellValue(row.getCell(6), evaluator));
            item.setIncome(getCellValue(row.getCell(7), evaluator));
            item.setGrossMargin(getCellValue(row.getCell(8), evaluator));
            item.setGrossMarginRate(getCellValue(row.getCell(9), evaluator));
            item.setStockPricing(getCellValue(row.getCell(10), evaluator));
            item.setOutletSellingPrice(getCellValue(row.getCell(11), evaluator));
            item.setGrossProfitIncludingTax(getCellValue(row.getCell(12), evaluator));
            item.setManufacturer(getCellValue(row.getCell(13), evaluator));
            list.add(item);
        }

        workbook.close();
        fis.close();
        return list;
    }
}
