package com.ruoyi.marketanalysis.utils;

import com.ruoyi.marketanalysis.domain.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    // 新增车型分类解析方法
    public static List<VehicleCategoryTable> parseVehicleCategoryExcel(File excelFile) throws IOException {
        List<VehicleCategoryTable> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fis);
        // 创建公式计算器
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // 跳过表头

            VehicleCategoryTable item = new VehicleCategoryTable();
            item.setVehicleCategory(getCellValue(row.getCell(0), evaluator));
            item.setVehicleType(getCellValue(row.getCell(1), evaluator));
            item.setSeries(getCellValue(row.getCell(2), evaluator));
            item.setTonnage(getCellValue(row.getCell(3), evaluator));
            item.setEngineType(getCellValue(row.getCell(4), evaluator));
            item.setEnginebatteryCapacity(getCellValue(row.getCell(5), evaluator));
            item.setPowerRating(getCellValue(row.getCell(6), evaluator));
            item.setTorqueRotationalspeed(getCellValue(row.getCell(7), evaluator));
            item.setFuelInjectionSystem(getCellValue(row.getCell(8), evaluator));
            item.setEffluentStandard(getCellValue(row.getCell(9), evaluator));
            item.setValveplateNumber(getCellValue(row.getCell(10), evaluator));
            item.setBatteryCapacity(getCellValue(row.getCell(11), evaluator));
            item.setTireSpecification(getCellValue(row.getCell(12), evaluator));
            item.setBusinessPersonnel(getCellValue(row.getCell(13), evaluator));
            item.setDevelopmentClass(getCellValue(row.getCell(14), evaluator));
            item.setReleaseDate(getDateCellValue(row.getCell(15)));

            list.add(item);
        }

        workbook.close();
        fis.close();
        return list;
    }
    // 解析一类车参数Excel
    public static List<CategoryoneParameterTable> parseCategoryoneParameterExcel(File excelFile) throws IOException {
        List<CategoryoneParameterTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头行

                CategoryoneParameterTable item = new CategoryoneParameterTable();

                // 按Excel列顺序设置所有字段
                item.setManufacturer(getCellValue(row.getCell(0), evaluator));
                item.setConfigurationNumber(getCellValue(row.getCell(1), evaluator));
                item.setLiftingWeight(getCellValue(row.getCell(2), evaluator));
                item.setLoadCenter(getCellValue(row.getCell(3), evaluator));
                item.setPowerMode(getCellValue(row.getCell(4), evaluator));
                item.setDrivingStyle(getCellValue(row.getCell(5), evaluator));
                item.setLiftingHeight(getCellValue(row.getCell(6), evaluator));
                item.setDoorframeHeight(getCellValue(row.getCell(7), evaluator));
                item.setMaximumLiftingHeightOfFork(getCellValue(row.getCell(8), evaluator));
                item.setFreeElevationHeight(getCellValue(row.getCell(9), evaluator));
                item.setFullHeight(getCellValue(row.getCell(10), evaluator));
                item.setMinimumGroundClearance(getCellValue(row.getCell(11), evaluator));
                item.setOverallLength(getCellValue(row.getCell(12), evaluator));
                item.setWheelbase(getCellValue(row.getCell(13), evaluator));
                item.setFrontSuspensionDistance(getCellValue(row.getCell(14), evaluator));
                item.setRearSuspensionDistance(getCellValue(row.getCell(15), evaluator));
                item.setFullWidth(getCellValue(row.getCell(16), evaluator));
                item.setTrackWidth(getCellValue(row.getCell(17), evaluator));
                item.setMinimumOuterTurningRadius(getCellValue(row.getCell(18), evaluator));
                item.setGateFrameInclinationAngle(getCellValue(row.getCell(19), evaluator));
                item.setForkSize(getCellValue(row.getCell(20), evaluator));
                item.setMaximumDrivingSpeed(getCellValue(row.getCell(21), evaluator));
                item.setMaxClimbCapability(getCellValue(row.getCell(22), evaluator));
                item.setMaximumTractionForce(getCellValue(row.getCell(23), evaluator));
                item.setLiftingSpeed(getCellValue(row.getCell(24), evaluator));
                item.setDescentSpeed(getCellValue(row.getCell(25), evaluator));
                item.setTotalWeight(getCellValue(row.getCell(26), evaluator));
                item.setWeightDistributionWithFullLoad(getCellValue(row.getCell(27), evaluator));
                item.setWeightDistributionEmptyLoad(getCellValue(row.getCell(28), evaluator));
                item.setNumberOfWheels(getCellValue(row.getCell(29), evaluator));
                item.setTireType(getCellValue(row.getCell(30), evaluator));
                item.setTireSize(getCellValue(row.getCell(31), evaluator));
                item.setBatteryVoltageCapacity(getCellValue(row.getCell(32), evaluator));
                item.setDriveMotorPower(getCellValue(row.getCell(33), evaluator));
                item.setPowerOfLiftingMotor(getCellValue(row.getCell(34), evaluator));
                item.setDriveMotorControlMode(getCellValue(row.getCell(35), evaluator));
                item.setControlModeOfLiftingMotor(getCellValue(row.getCell(36), evaluator));

                list.add(item);
            }
        }
        return list;
    }
    public static List<CategorytwoParameterTable> parseCategorytwoParameterExcel(File excelFile) throws IOException {
        List<CategorytwoParameterTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头行

                CategorytwoParameterTable item = new CategorytwoParameterTable();

                // 制造商
                item.setManufacturer(getCellValue(row.getCell(0), evaluator));
                // 型号
                item.setModel(getCellValue(row.getCell(1), evaluator));
                // 驾驶方式
                item.setDrivingStyle(getCellValue(row.getCell(2), evaluator));
                // 额定起重量
                item.setRatedLiftingCapacity(getCellValue(row.getCell(3), evaluator));
                // 总提升高度
                item.setRaiseHeight(getCellValue(row.getCell(4), evaluator));
                // 最高点载重
                item.setHighestPointLoadCapacity(getCellValue(row.getCell(5), evaluator));
                // 载荷中心
                item.setLoadCenter(getCellValue(row.getCell(6), evaluator));
                // 前悬距
                item.setFrontSuspensionDistance(getCellValue(row.getCell(7), evaluator));
                // 轴距
                item.setWheelbase(getCellValue(row.getCell(8), evaluator));
                // 后悬距
                item.setRearSuspensionDistance(getCellValue(row.getCell(9), evaluator));
                // 自重（含电池）
                item.setDeadWeight(getCellValue(row.getCell(10), evaluator));
                // 轮胎类型
                item.setTireType(getCellValue(row.getCell(11), evaluator));
                // 轮胎尺寸—前轮
                item.setTireSizeFrontWheel(getCellValue(row.getCell(12), evaluator));
                // 轮胎尺寸—后轮
                item.setTireSizeRearWheel(getCellValue(row.getCell(13), evaluator));
                // 轮胎数量
                item.setNumberOfTires(getCellValue(row.getCell(14), evaluator));
                // 前轮距
                item.setFrontWheelbase(getCellValue(row.getCell(15), evaluator));
                // 门架回缩时高度
                item.setDoorFrameRetractsHeight(getCellValue(row.getCell(16), evaluator));
                // 主起升高度
                item.setLiftingHeight(getCellValue(row.getCell(17), evaluator));
                // 作业时最大高度
                item.setMaximumHeightDuringHomework(getCellValue(row.getCell(18), evaluator));
                // 护顶架高度
                item.setHeightOfRoofProtectionFrame(getCellValue(row.getCell(19), evaluator));
                // 站台高度
                item.setPlatformHeight(getCellValue(row.getCell(20), evaluator));
                // 辅助提升高度
                item.setAssistInRaisingHeight(getCellValue(row.getCell(21), evaluator));
                // 提升后站台高度
                item.setRaisePlatformHeightAfterLifting(getCellValue(row.getCell(22), evaluator));
                // 总长
                item.setTotalLength(getCellValue(row.getCell(23), evaluator));
                // 车体长度（至货叉竖面）
                item.setVehicleLength(getCellValue(row.getCell(24), evaluator));
                // 总宽
                item.setOverallWidth(getCellValue(row.getCell(25), evaluator));
                // 货叉尺寸
                item.setForkSize(getCellValue(row.getCell(26), evaluator));
                // 货叉架安装等级
                item.setInstallationLevelOfForkFrame(getCellValue(row.getCell(27), evaluator));
                // 货叉架宽度
                item.setForkliftWidth(getCellValue(row.getCell(28), evaluator));
                // 跨货叉宽度
                item.setCrossForkWidth(getCellValue(row.getCell(29), evaluator));
                // 导轮间距
                item.setDistanceBetweenGuideWheels(getCellValue(row.getCell(30), evaluator));
                // 侧移距离
                item.setLateralDisplacementDistance(getCellValue(row.getCell(31), evaluator));
                // 侧伸出至叉车中心线距离
                item.setExtendCenterlineOrkliftDistance(getCellValue(row.getCell(32), evaluator));
                // 门架底部离地面间隙
                item.setGapBottomGantryGround(getCellValue(row.getCell(33), evaluator));
                // 轴距中心离地面间隙
                item.setClearanceCenterWheelbaseGround(getCellValue(row.getCell(34), evaluator));
                // 工作通道宽度
                item.setWidthOfWorkingChannel(getCellValue(row.getCell(35), evaluator));
                // 转弯半径
                item.setTurningRadius(getCellValue(row.getCell(36), evaluator));
                // 前轮轴至货叉旋转轴距离
                item.setDistanceForkRotationAxis(getCellValue(row.getCell(37), evaluator));
                // 最大拣料高度
                item.setMaximumPickingHeight(getCellValue(row.getCell(38), evaluator));
                // 托盘宽度
                item.setPalletWidth(getCellValue(row.getCell(39), evaluator));
                // 托盘长度
                item.setPalletLength(getCellValue(row.getCell(40), evaluator));
                // 驾驶仓入口净宽
                item.setClearWidthOfCockpitEntrance(getCellValue(row.getCell(41), evaluator));
                // 驾驶仓内部净高
                item.setClearHeightInsideTheCockpit(getCellValue(row.getCell(42), evaluator));
                // 驾驶仓外部总宽
                item.setOverallWidthOfTheCockpitExterior(getCellValue(row.getCell(43), evaluator));
                // 旋转前伸叉架宽度
                item.setRotatingForkFrameWidth(getCellValue(row.getCell(44), evaluator));
                // 悬臂宽度
                item.setCantileverWidth(getCellValue(row.getCell(45), evaluator));
                // 下降速度（满载/空载）
                item.setDescentSpeedR(getCellValue(row.getCell(46), evaluator));
                // 运行速度，满/空载
                item.setRunningSpeed(getCellValue(row.getCell(47), evaluator));
                // 提升速度，满/空载
                item.setIncreaseSpeed(getCellValue(row.getCell(48), evaluator));
                // 下降速度，满/空载
                item.setDescentSpeed(getCellValue(row.getCell(49), evaluator));
                // 侧移速度，满/空载
                item.setLateralMovementSpeed(getCellValue(row.getCell(50), evaluator));
                // 行程制动
                item.setTravelBrake(getCellValue(row.getCell(51), evaluator));
                // 停车制动
                item.setParkingBrake(getCellValue(row.getCell(52), evaluator));
                // 桥负荷，（满载）前/后
                item.setBridgeLoadFullyLoaded(getCellValue(row.getCell(53), evaluator));
                // 桥负荷，（空载）前/后
                item.setBridgeLoadUnloaded(getCellValue(row.getCell(54), evaluator));
                // 驱动电机功率
                item.setDriveMotorPower(getCellValue(row.getCell(55), evaluator));
                // 提升电机功率
                item.setIncreaseMotorPower(getCellValue(row.getCell(56), evaluator));
                // 电压/额定容量
                item.setVoltageRatedCapacity(getCellValue(row.getCell(57), evaluator));
                // 蓄电池重量（含辅配重）
                item.setWeightOfBattery(getCellValue(row.getCell(58), evaluator));
                // 驱动控制类型
                item.setDriveControlType(getCellValue(row.getCell(59), evaluator));
                // 驾驶员耳边噪声
                item.setDriverEarNoise(getCellValue(row.getCell(60), evaluator));
                // 转向
                item.setTurn(getCellValue(row.getCell(61), evaluator));

                list.add(item);
            }
        }
        return list;
    }
    public static List<CategorythreeParameterTable> parseCategorythreeParameterExcel(File excelFile) throws IOException {
        List<CategorythreeParameterTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头行

                CategorythreeParameterTable item = new CategorythreeParameterTable();

                // 制造商
                item.setManufacturer(getCellValue(row.getCell(0), evaluator));
                // 型号
                item.setModel(getCellValue(row.getCell(1), evaluator));
                // 驾驶方式
                item.setDrivingStyle(getCellValue(row.getCell(2), evaluator));
                // 额定起重量
                item.setRatedLiftingCapacity(getCellValue(row.getCell(3), evaluator));
                // 总提升高度
                item.setRaiseHeight(getCellValue(row.getCell(4), evaluator));
                // 最高点载重
                item.setHighestPointLoadCapacity(getCellValue(row.getCell(5), evaluator));
                // 载荷中心
                item.setLoadCenter(getCellValue(row.getCell(6), evaluator));
                // 前悬距
                item.setFrontSuspensionDistance(getCellValue(row.getCell(7), evaluator));
                // 轴距
                item.setWheelbase(getCellValue(row.getCell(8), evaluator));
                // 后悬距
                item.setRearSuspensionDistance(getCellValue(row.getCell(9), evaluator));
                // 自重（含电池）
                item.setDeadWeight(getCellValue(row.getCell(10), evaluator));
                // 轮胎类型
                item.setTireType(getCellValue(row.getCell(11), evaluator));
                // 轮胎尺寸—前轮
                item.setTireSizeFrontWheel(getCellValue(row.getCell(12), evaluator));
                // 轮胎尺寸—后轮
                item.setTireSizeRearWheel(getCellValue(row.getCell(13), evaluator));
                // 轮胎数量
                item.setNumberOfTires(getCellValue(row.getCell(14), evaluator));
                // 前轮距
                item.setFrontWheelbase(getCellValue(row.getCell(15), evaluator));
                // 门架回缩时高度
                item.setDoorFrameRetractsHeight(getCellValue(row.getCell(16), evaluator));
                // 主起升高度
                item.setLiftingHeight(getCellValue(row.getCell(17), evaluator));
                // 作业时最大高度
                item.setMaximumHeightDuringHomework(getCellValue(row.getCell(18), evaluator));
                // 护顶架高度
                item.setHeightOfRoofProtectionFrame(getCellValue(row.getCell(19), evaluator));
                // 站台高度
                item.setPlatformHeight(getCellValue(row.getCell(20), evaluator));
                // 辅助提升高度
                item.setAssistInRaisingHeight(getCellValue(row.getCell(21), evaluator));
                // 提升后站台高度
                item.setRaisePlatformHeightAfterLifting(getCellValue(row.getCell(22), evaluator));
                // 总长
                item.setTotalLength(getCellValue(row.getCell(23), evaluator));
                // 车体长度（至货叉竖面）
                item.setVehicleLength(getCellValue(row.getCell(24), evaluator));
                // 总宽
                item.setOverallWidth(getCellValue(row.getCell(25), evaluator));
                // 货叉尺寸
                item.setForkSize(getCellValue(row.getCell(26), evaluator));
                // 货叉架安装等级
                item.setInstallationLevelOfForkFrame(getCellValue(row.getCell(27), evaluator));
                // 货叉架宽度
                item.setForkliftWidth(getCellValue(row.getCell(28), evaluator));
                // 跨货叉宽度
                item.setCrossForkWidth(getCellValue(row.getCell(29), evaluator));
                // 导轮间距
                item.setDistanceBetweenGuideWheels(getCellValue(row.getCell(30), evaluator));
                // 侧移距离
                item.setLateralDisplacementDistance(getCellValue(row.getCell(31), evaluator));
                // 侧伸出至叉车中心线距离
                item.setExtendCenterlineOrkliftDistance(getCellValue(row.getCell(32), evaluator));
                // 门架底部离地面间隙
                item.setGapBottomGantryGround(getCellValue(row.getCell(33), evaluator));
                // 轴距中心离地面间隙
                item.setClearanceCenterWheelbaseGround(getCellValue(row.getCell(34), evaluator));
                // 工作通道宽度
                item.setWidthOfWorkingChannel(getCellValue(row.getCell(35), evaluator));
                // 转弯半径
                item.setTurningRadius(getCellValue(row.getCell(36), evaluator));
                // 前轮极值轴至货叉旋转轴距离
                item.setDistanceForkRotationAxis(getCellValue(row.getCell(37), evaluator));
                // 最大拣料高度
                item.setMaximumPickingHeight(getCellValue(row.getCell(38), evaluator));
                // 托盘宽度
                item.setPalletWidth(getCellValue(row.getCell(39), evaluator));
                // 托盘长度
                item.setPalletLength(getCellValue(row.getCell(40), evaluator));
                // 驾驶仓入口净宽
                item.setClearWidthOfCockpitEntrance(getCellValue(row.getCell(41), evaluator));
                // 驾驶仓内部净高
                item.setClearHeightInsideTheCockpit(getCellValue(row.getCell(42), evaluator));
                // 驾驶仓外部总宽
                item.setOverallWidthOfTheCockpitExterior(getCellValue(row.getCell(43), evaluator));
                // 旋转前伸叉架宽度
                item.setRotatingForkFrameWidth(getCellValue(row.getCell(44), evaluator));
                // 悬臂宽度
                item.setCantileverWidth(getCellValue(row.getCell(45), evaluator));
                // 下降速度（满载/空载）
                item.setDescentSpeedR(getCellValue(row.getCell(46), evaluator));
                // 运行速度，满/空载
                item.setRunningSpeed(getCellValue(row.getCell(47), evaluator));
                // 提升速度，满/空载
                item.setIncreaseSpeed(getCellValue(row.getCell(48), evaluator));
                // 下降速度，满/空载
                item.setDescentSpeed(getCellValue(row.getCell(49), evaluator));
                // 侧移速度，满/空载
                item.setLateralMovementSpeed(getCellValue(row.getCell(50), evaluator));
                // 行程制动
                item.setTravelBrake(getCellValue(row.getCell(51), evaluator));
                // 停车制动
                item.setParkingBrake(getCellValue(row.getCell(52), evaluator));
                // 桥负荷，（满载）前/后
                item.setBridgeLoadFullyLoaded(getCellValue(row.getCell(53), evaluator));
                // 桥负荷，（空载）前/后
                item.setBridgeLoadUnloaded(getCellValue(row.getCell(54), evaluator));
                // 驱动电机功率
                item.setDriveMotorPower(getCellValue(row.getCell(55), evaluator));
                // 提升电机功率
                item.setIncreaseMotorPower(getCellValue(row.getCell(56), evaluator));
                // 电压/额定容量
                item.setVoltageRatedCapacity(getCellValue(row.getCell(57), evaluator));
                // 蓄电池重量（含辅配重）
                item.setWeightOfBattery(getCellValue(row.getCell(58), evaluator));
                // 驱动控制类型
                item.setDriveControlType(getCellValue(row.getCell(59), evaluator));
                // 驾驶员耳边噪声
                item.setDriverEarNoise(getCellValue(row.getCell(60), evaluator));
                // 转向
                item.setTurn(getCellValue(row.getCell(61), evaluator));

                list.add(item);
            }
        }
        return list;
    }
    public static List<CategoryfiveParameterTable> parseCategoryfiveParameterExcel(File excelFile) throws IOException {
        List<CategoryfiveParameterTable> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fis);
        // 创建公式计算器
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // 跳过表头行

            CategoryfiveParameterTable item = new CategoryfiveParameterTable();
            item.setManufacturer(getCellValue(row.getCell(0), evaluator));
            item.setModel(getCellValue(row.getCell(1), evaluator));
            item.setLiftingWeight(getCellValue(row.getCell(2), evaluator));
            item.setLoadCenter(getCellValue(row.getCell(3), evaluator));
            item.setDrivingStyle(getCellValue(row.getCell(4), evaluator));
            item.setPowerMode(getCellValue(row.getCell(5), evaluator));
            item.setLiftingHeight(getCellValue(row.getCell(6), evaluator));
            item.setDoorFrameFullHeight(getCellValue(row.getCell(7), evaluator));
            item.setMaximumLiftingHeightOfFork(getCellValue(row.getCell(8), evaluator));
            item.setFreeElevationHeight(getCellValue(row.getCell(9), evaluator));
            item.setFullHeight(getCellValue(row.getCell(10), evaluator));
            item.setMinimumGroundClearance(getCellValue(row.getCell(11), evaluator));
            item.setOverallLength(getCellValue(row.getCell(12), evaluator));
            item.setWheelbase(getCellValue(row.getCell(13), evaluator));
            item.setFrontSuspensionDistance(getCellValue(row.getCell(14), evaluator));
            item.setRearSuspensionDistance(getCellValue(row.getCell(15), evaluator));
            item.setFullWidth(getCellValue(row.getCell(16), evaluator));
            item.setTrackWidth(getCellValue(row.getCell(17), evaluator));
            item.setMinimumOuterTurningRadius(getCellValue(row.getCell(18), evaluator));
            item.setGateFrameInclinationAngle(getCellValue(row.getCell(19), evaluator));
            item.setForkSize(getCellValue(row.getCell(20), evaluator));
            item.setMaximumDrivingSpeed(getCellValue(row.getCell(21), evaluator));
            item.setMaxClimbCapability(getCellValue(row.getCell(22), evaluator));
            item.setMaximumTractionForce(getCellValue(row.getCell(23), evaluator));
            item.setLiftingSpeed(getCellValue(row.getCell(24), evaluator));
            item.setDescentSpeed(getCellValue(row.getCell(25), evaluator));
            item.setTotalWeight(getCellValue(row.getCell(26), evaluator));
            item.setWeightDistributionWithFullLoad(getCellValue(row.getCell(27), evaluator));
            item.setWeightDistributionEmptyLoad(getCellValue(row.getCell(28), evaluator));
            item.setNumberOfWheels(getCellValue(row.getCell(29), evaluator));
            item.setTireType(getCellValue(row.getCell(30), evaluator));
            item.setTireSize(getCellValue(row.getCell(31), evaluator));
            item.setBatteryVoltageCapacity(getCellValue(row.getCell(32), evaluator));
            item.setEngineManufacturer(getCellValue(row.getCell(33), evaluator));
            item.setRatedPowerSpeed(getCellValue(row.getCell(34), evaluator));
            item.setMaximumTorqueSpeed(getCellValue(row.getCell(35), evaluator));
            item.setNumberOfCylinders(getCellValue(row.getCell(36), evaluator));
            item.setFuelTankCapacity(getCellValue(row.getCell(37), evaluator));
            item.setEmissionStandard(getCellValue(row.getCell(38), evaluator));
            item.setGearsInTheGearbox(getCellValue(row.getCell(39), evaluator));

            list.add(item);
        }

        workbook.close();
        fis.close();
        return list;
    }
    public static List<CategorysevenParameterTable> parseCategorysevenParameterExcel(File excelFile) throws IOException {
        List<CategorysevenParameterTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                CategorysevenParameterTable item = new CategorysevenParameterTable();
                item.setManufacturer(getCellValue(row.getCell(0), evaluator));
                item.setModel(getCellValue(row.getCell(1), evaluator));
                item.setLiftingWeight(getCellValue(row.getCell(2), evaluator));
                item.setLoadCenter(getCellValue(row.getCell(3), evaluator));
                item.setDrivingStyle(getCellValue(row.getCell(4), evaluator));
                item.setPowerMode(getCellValue(row.getCell(5), evaluator));
                item.setLiftingHeight(getCellValue(row.getCell(6), evaluator));
                item.setDoorFrameFullHeight(getCellValue(row.getCell(7), evaluator));
                item.setMaximumLiftingHeightOfFork(getCellValue(row.getCell(8), evaluator));
                item.setFreeElevationHeight(getCellValue(row.getCell(9), evaluator));
                item.setFullHeight(getCellValue(row.getCell(10), evaluator));
                item.setMinimumGroundClearance(getCellValue(row.getCell(11), evaluator));
                item.setOverallLength(getCellValue(row.getCell(12), evaluator));
                item.setWheelbase(getCellValue(row.getCell(13), evaluator));
                item.setFrontSuspensionDistance(getCellValue(row.getCell(14), evaluator));
                item.setRearSuspensionDistance(getCellValue(row.getCell(15), evaluator));
                item.setFullWidth(getCellValue(row.getCell(16), evaluator));
                item.setTrackWidth(getCellValue(row.getCell(17), evaluator));
                item.setMinimumOuterTurningRadius(getCellValue(row.getCell(18), evaluator));
                item.setGateFrameInclinationAngle(getCellValue(row.getCell(19), evaluator));
                item.setForkSize(getCellValue(row.getCell(20), evaluator));
                item.setMaximumDrivingSpeed(getCellValue(row.getCell(21), evaluator));
                item.setMaxClimbCapability(getCellValue(row.getCell(22), evaluator));
                item.setMaximumTractionForce(getCellValue(row.getCell(23), evaluator));
                item.setLiftingSpeed(getCellValue(row.getCell(24), evaluator));
                item.setDescentSpeed(getCellValue(row.getCell(25), evaluator));
                item.setTotalWeight(getCellValue(row.getCell(26), evaluator));
                item.setWeightDistributionWithFullLoad(getCellValue(row.getCell(27), evaluator));
                item.setWeightDistributionEmptyLoad(getCellValue(row.getCell(28), evaluator));
                item.setNumberOfWheels(getCellValue(row.getCell(29), evaluator));
                item.setTireType(getCellValue(row.getCell(30), evaluator));
                item.setTireSize(getCellValue(row.getCell(31), evaluator));
                item.setBatteryVoltageCapacity(getCellValue(row.getCell(32), evaluator));
                item.setEngineManufacturer(getCellValue(row.getCell(33), evaluator));
                item.setRatedPowerSpeed(getCellValue(row.getCell(34), evaluator));
                item.setMaximumTorqueSpeed(getCellValue(row.getCell(35), evaluator));
                item.setNumberOfCylinders(getCellValue(row.getCell(36), evaluator));
                item.setFuelTankCapacity(getCellValue(row.getCell(37), evaluator));
                item.setEmissionStandard(getCellValue(row.getCell(38), evaluator));
                item.setGearsInTheGearbox(getCellValue(row.getCell(39), evaluator));

                list.add(item);
            }
        }
        return list;
    }
    public static List<CategoryoneStandardConfigurationTable> parseCategoryoneStandardExcel(File excelFile) throws IOException {
        List<CategoryoneStandardConfigurationTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头行

                CategoryoneStandardConfigurationTable item = new CategoryoneStandardConfigurationTable();

                // 按Excel列顺序设置所有字段
                item.setManufacturer(getCellValue(row.getCell(0), evaluator)); // 制造商
                item.setPneumaticTire(getCellValue(row.getCell(1), evaluator)); // 充气胎
                item.setSolidTire(getCellValue(row.getCell(2), evaluator)); // 实心胎
                item.setSingleTire(getCellValue(row.getCell(3), evaluator)); // 单胎（前轮）
                item.setDualTire(getCellValue(row.getCell(4), evaluator)); // 双胎（前轮）
                item.setTwoPieceValve(getCellValue(row.getCell(5), evaluator)); // 两片阀
                item.setThreeOrFourPieceValve(getCellValue(row.getCell(6), evaluator)); // 三片或四片阀
                item.setThreeMeterLiftingHeight(getCellValue(row.getCell(7), evaluator)); // 基本型3米起升高度
                item.setLiftingHeightGantry(getCellValue(row.getCell(8), evaluator)); // 其它型及其它起升高度门架
                item.setStandardSeriesFork(getCellValue(row.getCell(9), evaluator)); // 标准系列货叉
                item.setNonStandardSeriesForks(getCellValue(row.getCell(10), evaluator)); // 非标系列货叉、属具
                item.setStandardBodyColor(getCellValue(row.getCell(11), evaluator)); // 标准车身颜色（蓝灰色+合力红）
                item.setCustomizedBodyColor(getCellValue(row.getCell(12), evaluator)); // 其它车身颜色（客户定制）
                item.setCompleteOpsFunctionality(getCellValue(row.getCell(13), evaluator)); // 全套OPS功能
                item.setTurningSpeedLimit(getCellValue(row.getCell(14), evaluator)); // 转弯限速
                item.setOverspeed(getCellValue(row.getCell(15), evaluator)); // 超速报警
                item.setReverseArmrestWithHorn(getCellValue(row.getCell(16), evaluator)); // 倒车扶手带喇叭开关
                item.setSeatBeltSwitch(getCellValue(row.getCell(17), evaluator)); // 座椅带安全带开关
                item.setOverloadOfMultiWayValve(getCellValue(row.getCell(18), evaluator)); // 多路阀过载
                item.setFireExtinguisher(getCellValue(row.getCell(19), evaluator)); // 灭火器
                item.setReverseBuzzer(getCellValue(row.getCell(20), evaluator)); // 倒车蜂鸣器
                item.setCentralWideangleRearviewMirror(getCellValue(row.getCell(21), evaluator)); // 中央广角后极镜
                item.setTwoSideRearviewMirrors(getCellValue(row.getCell(22), evaluator)); // 两侧后视镜
                item.setParkingSensor(getCellValue(row.getCell(23), evaluator)); // 倒车雷达
                item.setSemiEnclosedSeat(getCellValue(row.getCell(24), evaluator)); // 半包围座椅
                item.setFullySuspendedSeat(getCellValue(row.getCell(25), evaluator)); // 全悬浮座椅
                item.setUsbInterface(getCellValue(row.getCell(26), evaluator)); // USB接口
                item.setElectricFan(getCellValue(row.getCell(27), evaluator)); // 电风扇
                item.setTurnTheSteeringWheel(getCellValue(row.getCell(28), evaluator)); // 方向盘启动转向
                item.setSynchronousSteering(getCellValue(row.getCell(29), evaluator)); // 同步转向
                item.setStandardRoofProtectionFrame(getCellValue(row.getCell(30), evaluator)); // 标准护顶架
                item.setNonStandardRoofProtectionFrame(getCellValue(row.getCell(31), evaluator)); // 非标护顶架
                item.setWindscreen(getCellValue(row.getCell(32), evaluator)); // 前挡风玻璃（带雨刮器）
                item.setFrontHeatresistantWindshield(getCellValue(row.getCell(33), evaluator)); // 前耐高温挡风玻璃（带雨刮器）
                item.setRearWindshield(getCellValue(row.getCell(34), evaluator)); // 后挡风玻璃（不带雨刮器）
                item.setPcEnduranceBoardCeiling(getCellValue(row.getCell(35), evaluator)); // PC耐力板顶棚
                item.setSurfaceMountedCab(getCellValue(row.getCell(36), evaluator)); // 贴片式驾驶室
                item.setSingleCoolingAirConditioner(getCellValue(row.getCell(37), evaluator)); // 单冷空调
                item.setDualPurposeAirConditioner(getCellValue(row.getCell(38), evaluator)); // 冷暖两用空调
                item.setWarmAirBlower(getCellValue(row.getCell(39), evaluator)); // 暖风机
                item.setFullCarLedLights(getCellValue(row.getCell(40), evaluator)); // 全车LED灯
                item.setLedRearWorkLight(getCellValue(row.getCell(41), evaluator)); // LED后工作灯
                item.setBlueLightBehindTheCar(getCellValue(row.getCell(42), evaluator)); // 车后方蓝光灯
                item.setThreeSidedBlueStripLight(getCellValue(row.getCell(43), evaluator)); // 三边蓝色条形灯
                item.setThreeSidedRedStripLights(getCellValue(row.getCell(44), evaluator)); // 三边红色条形灯
                item.setLedFlashingWarningLight(getCellValue(row.getCell(45), evaluator)); // 配装LED闪光警示灯（装配式）
                item.setLedRotatingWarningLight(getCellValue(row.getCell(46), evaluator)); // 配装LED旋转式警示灯（装配式）
                item.setLedRotatingBuzzerWarningLight(getCellValue(row.getCell(47), evaluator)); // 配装LED旋转蜂鸣式警示灯（装配式）
                item.setMetric(getCellValue(row.getCell(48), evaluator)); // 公制螺纹
                item.setTiltCylinderSleeve(getCellValue(row.getCell(49), evaluator)); // 倾斜油缸护套
                item.setSteeringCylinderSleeve(getCellValue(row.getCell(50), evaluator)); // 转向油缸护套
                item.setRemaPowerConnector(getCellValue(row.getCell(51), evaluator)); // REMA电源接插件
                item.setNationalStandardPowerConnector(getCellValue(row.getCell(52), evaluator)); // 国标电源接插件
                item.setDomesticBattery(getCellValue(row.getCell(53), evaluator)); // 国产蓄电池
                item.setFicsHeliConnectedCarBasicEdition(getCellValue(row.getCell(54), evaluator)); // FICS合力车联网基本版（国内）

                list.add(item);
            }
        }
        return list;
    }
    public static List<CategorytwoStandardConfigurationTable> parseCategorytwoStandardExcel(File excelFile) throws IOException {
        List<CategorytwoStandardConfigurationTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头行

                CategorytwoStandardConfigurationTable item = new CategorytwoStandardConfigurationTable();

                // 按顺序映射所有字段
                item.setManufacturer(getCellValue(row.getCell(0), evaluator));
                item.setPolyurethaneTires(getCellValue(row.getCell(1), evaluator));
                item.setFiveMeterLiftingHeight(getCellValue(row.getCell(2), evaluator));
                item.setLiftingHeightGantry(getCellValue(row.getCell(3), evaluator));
                item.setStandardSeriesFork(getCellValue(row.getCell(4), evaluator));
                item.setNonStandardSeriesFork(getCellValue(row.getCell(5), evaluator));
                item.setStandardBodyColor(getCellValue(row.getCell(6), evaluator));
                item.setCustomizedBodyColor(getCellValue(row.getCell(7), evaluator));
                item.setAccessoriesAndSpeedLimit(getCellValue(row.getCell(8), evaluator));
                item.setTurningSpeedLimit(getCellValue(row.getCell(9), evaluator));
                item.setFireExtinguisher(getCellValue(row.getCell(10), evaluator));
                item.setReverseBuzzer(getCellValue(row.getCell(11), evaluator));
                item.setReverseVoiceHorn(getCellValue(row.getCell(12), evaluator));
                item.setTwoSideRearviewMirrors(getCellValue(row.getCell(13), evaluator));
                item.setEscapeBag(getCellValue(row.getCell(14), evaluator));
                item.setRearWheelBraking(getCellValue(row.getCell(15), evaluator));
                item.setFrontWheelBrake(getCellValue(row.getCell(16), evaluator));
                item.setGuideWheel(getCellValue(row.getCell(17), evaluator));
                item.setMonitor(getCellValue(row.getCell(18), evaluator));
                item.setMagneticNavigation(getCellValue(row.getCell(19), evaluator));
                item.setStationDrivingSoftPackSeat(getCellValue(row.getCell(20), evaluator));
                item.setCarStyleSeats(getCellValue(row.getCell(21), evaluator));
                item.setElectricFan(getCellValue(row.getCell(22), evaluator));
                item.setWindscreen(getCellValue(row.getCell(23), evaluator));
                item.setRearWindshield(getCellValue(row.getCell(24), evaluator));
                item.setFullCarLedLights(getCellValue(row.getCell(25), evaluator));
                item.setReadingLamp(getCellValue(row.getCell(26), evaluator));
                item.setLedFlashingWarningLight(getCellValue(row.getCell(27), evaluator));
                item.setMetric(getCellValue(row.getCell(28), evaluator));
                item.setNationalStandardPowerConnector(getCellValue(row.getCell(29), evaluator));
                item.setChineseIdentification(getCellValue(row.getCell(30), evaluator));
                item.setEnglishLogo(getCellValue(row.getCell(31), evaluator));

                list.add(item);
            }
        }
        return list;
    }
    public static List<CategoryfiveStandardConfigurationTable> parseCategoryfiveStandardExcel(File excelFile) throws IOException {
        List<CategoryfiveStandardConfigurationTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 创建公式计算器
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头行

                CategoryfiveStandardConfigurationTable item = new CategoryfiveStandardConfigurationTable();

                // 按Excel列顺序设置所有字段
                item.setManufacturer(getCellValue(row.getCell(0), evaluator)); // 制造商
                item.setPneumaticTire(getCellValue(row.getCell(1), evaluator)); // 充气胎
                item.setSolidTire(getCellValue(row.getCell(2), evaluator)); // 实心胎
                item.setSingleTire(getCellValue(row.getCell(3), evaluator)); // 单胎（前轮）
                item.setTwinTires(getCellValue(row.getCell(4), evaluator)); // 双胎（前轮）
                item.setTwoPieceValve(getCellValue(row.getCell(5), evaluator)); // 两片阀
                item.setThreeOrFourPieceValve(getCellValue(row.getCell(6), evaluator)); // 三片或四片阀
                item.setThreeMeterLiftingHeight(getCellValue(row.getCell(7), evaluator)); // 基本型3米起升高度
                item.setLiftingHeightGantry(getCellValue(row.getCell(8), evaluator)); // 其它型及其它起升高度门架
                item.setStandardSeriesFork(getCellValue(row.getCell(9), evaluator)); // 标准系列货叉
                item.setNonStandardSeriesForks(getCellValue(row.getCell(10), evaluator)); // 非标系列货叉属具
                item.setStandardBodyColor(getCellValue(row.getCell(11), evaluator)); // 标准车身颜色
                item.setCustomizedBodyColor(getCellValue(row.getCell(12), evaluator)); // 其它车身颜色
                item.setCompleteOpsFunctionality(getCellValue(row.getCell(13), evaluator)); // 全套OPS功能
                item.setWalkingOpsFunction(getCellValue(row.getCell(14), evaluator)); // 行走OPS功能
                item.setOverspeed(getCellValue(row.getCell(15), evaluator)); // 超速报警
                item.setReverseArmrestWithHorn(getCellValue(row.getCell(16), evaluator)); // 倒车扶手带喇叭开关
                item.setSeatBeltSwitch(getCellValue(row.getCell(17), evaluator)); // 座椅带安全带开关
                item.setOverloadOfMultiWayValve(getCellValue(row.getCell(18), evaluator)); // 多路阀过载
                item.setFireExtinguisher(getCellValue(row.getCell(19), evaluator)); // 灭火器
                item.setReverseBuzzer(getCellValue(row.getCell(20), evaluator)); // 倒车蜂鸣器
                item.setCentralWideangleRearviewMirror(getCellValue(row.getCell(21), evaluator)); // 中央广角后视镜
                item.setTwoSideRearviewMirrors(getCellValue(row.getCell(22), evaluator)); // 两侧后视镜
                item.setParkingSensor(getCellValue(row.getCell(23), evaluator)); // 倒车雷达
                item.setSemiEnclosedSeat(getCellValue(row.getCell(24), evaluator)); // 半包围座椅
                item.setFullySuspendedSeat(getCellValue(row.getCell(25), evaluator)); // 全悬浮座椅
                item.setUsbInterface(getCellValue(row.getCell(26), evaluator)); // USB接口
                item.setElectricFan(getCellValue(row.getCell(27), evaluator)); // 电风扇
                item.setTurnTheSteeringWheel(getCellValue(row.getCell(28), evaluator)); // 方向盘启动转向
                item.setSynchronousSteering(getCellValue(row.getCell(29), evaluator)); // 同步转向
                item.setStandardRoofProtectionFrame(getCellValue(row.getCell(30), evaluator)); // 标准护顶架
                item.setNonStandardRoofProtectionFrame(getCellValue(row.getCell(31), evaluator)); // 非标护顶架
                item.setWindscreen(getCellValue(row.getCell(32), evaluator)); // 前挡风玻璃（带雨刮器）
                item.setFrontHeatresistantWindshield(getCellValue(row.getCell(33), evaluator)); // 前耐高温挡风玻璃（带雨刮器）
                item.setRearWindshield(getCellValue(row.getCell(34), evaluator)); // 后挡风玻璃（不带雨刮器）
                item.setPcEnduranceBoardCeiling(getCellValue(row.getCell(35), evaluator)); // PC耐力板顶棚
                item.setSurfaceMountedCab(getCellValue(row.getCell(36), evaluator)); // 贴片式驾驶室
                item.setSingleCoolingAirConditioner(getCellValue(row.getCell(37), evaluator)); // 单冷空调
                item.setDualPurposeAirConditioner(getCellValue(row.getCell(38), evaluator)); // 冷暖两用空调
                item.setWarmAirBlower(getCellValue(row.getCell(39), evaluator)); // 暖风机
                item.setFullCarLedLights(getCellValue(row.getCell(40), evaluator)); // 全车LED灯
                item.setLedRearWorkLight(getCellValue(row.getCell(41), evaluator)); // LED后工作灯
                item.setBlueLightBehindTheCar(getCellValue(row.getCell(42), evaluator)); // 车后方蓝光灯
                item.setThreeSidedBlueStripLight(getCellValue(row.getCell(43), evaluator)); // 三边蓝色条形灯
                item.setThreeSidedRedStripLights(getCellValue(row.getCell(44), evaluator)); // 三边红色条形灯
                item.setLedFlashingWarningLight(getCellValue(row.getCell(45), evaluator)); // LED闪光警示灯
                item.setLedRotatingWarningLight(getCellValue(row.getCell(46), evaluator)); // LED旋转式警示灯
                item.setLedRotatingBuzzerWarningLight(getCellValue(row.getCell(47), evaluator)); // LED旋转蜂鸣式警示灯
                item.setMetric(getCellValue(row.getCell(48), evaluator)); // 公制螺纹
                item.setTiltCylinderSleeve(getCellValue(row.getCell(49), evaluator)); // 倾斜油缸护套
                item.setSteeringCylinderSleeve(getCellValue(row.getCell(50), evaluator)); // 转向油缸护套
                item.setMedianExhaust(getCellValue(row.getCell(51), evaluator)); // 中位排气
                item.setHighExhaust(getCellValue(row.getCell(52), evaluator)); // 高排气
                item.setTwoWheelDrive(getCellValue(row.getCell(53), evaluator)); // 两驱
                item.setFourWheelDrive(getCellValue(row.getCell(54), evaluator)); // 四驱
                item.setDentalEmbeddedDifferentialLock(getCellValue(row.getCell(55), evaluator)); // 牙嵌式差速锁
                item.setLimitedSlipDifferential(getCellValue(row.getCell(56), evaluator)); // 限滑差速器
                item.setManualDifferentialLock(getCellValue(row.getCell(57), evaluator)); // 手动差速锁
                item.setFicsHeliConnectedCarBasicEdition(getCellValue(row.getCell(58), evaluator)); // FICS合力车联网

                list.add(item);
            }
        }
        return list;
    }
    public static List<PerformanceTable> parsePerformanceExcel(File excelFile) throws IOException {
        List<PerformanceTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头

                PerformanceTable item = new PerformanceTable();
                // 按照文档16 PerformanceTable 类字段顺序解析
                item.setVehicleCategory(getCellValue(row.getCell(0), evaluator));
                item.setVehicleType(getCellValue(row.getCell(1), evaluator));
                item.setManufacturer(getCellValue(row.getCell(2), evaluator));
                item.setMaximumDrivingSpeedFull(getCellValue(row.getCell(3), evaluator));
                item.setMaximumDrivingSpeedEmpty(getCellValue(row.getCell(4), evaluator));
                item.setMaxClimbCapabilityEmpty(getCellValue(row.getCell(6), evaluator));
                item.setMaxClimbCapabilityFull(getCellValue(row.getCell(5), evaluator));
                item.setMaximumTractionForceFull(getCellValue(row.getCell(7), evaluator));
                item.setMaximumTractionForceEmpty(getCellValue(row.getCell(8), evaluator));
                item.setLiftingSpeedFull(getCellValue(row.getCell(9), evaluator));
                item.setLiftingSpeedEmpty(getCellValue(row.getCell(10), evaluator));
                item.setDescentSpeedFull(getCellValue(row.getCell(11), evaluator));
                item.setDescentSpeedEmpty(getCellValue(row.getCell(12), evaluator));
                item.setRunningSpeedFull(getCellValue(row.getCell(13), evaluator));
                item.setRunningSpeedEmpty(getCellValue(row.getCell(14), evaluator));
                item.setIncreaseSpeedFull(getCellValue(row.getCell(15), evaluator));
                item.setIncreaseSpeedEmpty(getCellValue(row.getCell(16), evaluator));
                item.setLateralMovementSpeedFull(getCellValue(row.getCell(17), evaluator));
                item.setLateralMovementSpeedEmpty(getCellValue(row.getCell(18), evaluator));
                item.setTravelBrake(getCellValue(row.getCell(19), evaluator));
                item.setParkingBrake(getCellValue(row.getCell(20), evaluator));
                item.setBridgeLoadFullyLoadedFront(getCellValue(row.getCell(21), evaluator));
                item.setBridgeLoadFullyLoadedBehind(getCellValue(row.getCell(22), evaluator));
                item.setBridgeLoadUnloadedFront(getCellValue(row.getCell(23), evaluator));
                item.setBridgeLoadUnloadedBehind(getCellValue(row.getCell(24), evaluator));

                list.add(item);
            }
        }
        return list;
    }
    // 新增解析方法
    public static List<CategoryTable> parseCategoryExcel(File excelFile) throws IOException {
        List<CategoryTable> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头

                CategoryTable item = new CategoryTable();
                item.setVehicleType(getCellValue(row.getCell(0), evaluator));
                item.setVehicleCategoryChn(getCellValue(row.getCell(1), evaluator));
                list.add(item);
            }
        }
        return list;
    }
}
