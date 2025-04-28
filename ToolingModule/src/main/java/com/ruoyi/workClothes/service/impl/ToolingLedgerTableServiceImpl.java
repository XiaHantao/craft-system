package com.ruoyi.workClothes.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.workClothes.domain.NonstructuraltoolingTable;
import com.ruoyi.workClothes.domain.ToolingTable;
import com.ruoyi.workClothes.mapper.NonstructuraltoolingTableMapper;
import org.apache.el.parser.ParseException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workClothes.mapper.ToolingLedgerTableMapper;
import com.ruoyi.workClothes.domain.ToolingLedgerTable;
import com.ruoyi.workClothes.service.IToolingLedgerTableService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工装台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-20
 */
@Service
public class ToolingLedgerTableServiceImpl implements IToolingLedgerTableService 
{
    @Autowired
    private ToolingLedgerTableMapper toolingLedgerTableMapper;
    @Autowired
    private NonstructuraltoolingTableMapper nonstructuraltoolingTableMapper;

    private static final Logger logger = LoggerFactory.getLogger(ToolingLedgerTableServiceImpl.class);
    private static final String DATE_FORMAT = "yyyy.MM.dd"; // 定义常量
    /**
     * 查询工装台账
     * 
     * @param id 工装台账主键
     * @return 工装台账
     */
    @Override
    public ToolingLedgerTable selectToolingLedgerTableById(Long id)
    {
        return toolingLedgerTableMapper.selectToolingLedgerTableById(id);
    }

    /**
     * 查询工装台账列表
     * 
     * @param toolingLedgerTable 工装台账
     * @return 工装台账
     */
    @Override
    public List<ToolingLedgerTable> selectToolingLedgerTableList(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.selectToolingLedgerTableList(toolingLedgerTable);
    }

    /**
     * 新增工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    @Override
    public int insertToolingLedgerTable(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.insertToolingLedgerTable(toolingLedgerTable);
    }

    /**
     * 修改工装台账
     * 
     * @param toolingLedgerTable 工装台账
     * @return 结果
     */
    @Override
    public int updateToolingLedgerTable(ToolingLedgerTable toolingLedgerTable)
    {
        return toolingLedgerTableMapper.updateToolingLedgerTable(toolingLedgerTable);
    }

    /**
     * 批量删除工装台账
     * 
     * @param ids 需要删除的工装台账主键
     * @return 结果
     */
    @Override
    public int deleteToolingLedgerTableByIds(Long[] ids)
    {
        return toolingLedgerTableMapper.deleteToolingLedgerTableByIds(ids);
    }

    /**
     * 删除工装台账信息
     * 
     * @param id 工装台账主键
     * @return 结果
     */
    @Override
    public int deleteToolingLedgerTableById(Long id)
    {
        return toolingLedgerTableMapper.deleteToolingLedgerTableById(id);
    }

    @Override
    public int updateWorkClothesfile(Map<String, Object> fileform) {
        String fileType = (String) fileform.get("fileType");
        String file = (String)fileform.get("file");
        String moldname = (String)fileform.get("moldname");
        System.out.println("传入数据" + fileform);
        // 1. 获取数据库中所有的moldNumber
        List<String> moldNumbers = toolingLedgerTableMapper.getAllMoldNumbers(); // 假设你有方法来获取所有moldNumbers

        // 2. 遍历moldNumbers与moldname进行对比
        for (String moldNumber : moldNumbers) {
            // 假设这里有一个方法获取模具信息，根据 moldname 匹配
            if (moldname.equals(moldNumber)) {
                // 如果 fileType 是 processDocuments
                if ("processDocuments".equals(fileType)) {
                    updateFilePath(moldNumber, file, "processDocuments");
                }
                // 如果 fileType 是 mbom
                else if ("mbom".equals(fileType)) {
                    updateFilePath(moldNumber, file, "mbom");
                }
                // 如果 fileType 是 toolingDrawings
                else if ("toolingDrawings".equals(fileType)) {
                    updateFilePath(moldNumber, file, "toolingDrawings");
                }
                return 1;
            }
        }
        return 0;
    }

//    @Override
//    public int loadImport(MultipartFile file) {
//        int rows = 0; // 用于记录成功导入的行数
//        String parentWorkwear = null ;
//        List<ToolingLedgerTable> toolingLedgerTables = new ArrayList<>();
//        try {
//            // 获取文件的输入流
//            InputStream inputStream = file.getInputStream();
//
//            // 创建工作簿（XSSFWorkbook适用于.xlsx格式）
//            Workbook workbook = new XSSFWorkbook(inputStream);
//
//            // 获取第一个工作表
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // 遍历所有行
//            for (int rowIndex = 2; rowIndex <= sheet.getPhysicalNumberOfRows(); rowIndex++) {
//                Row row = sheet.getRow(rowIndex);
//
//                // 跳过空行
//                if (row == null) continue;
//
//                // 检查某一列是否包含合并单元格的文字（例如"合计工时"）
////                Cell firstCell = row.getCell(0); // 假设合并单元格会在第1列
////                if (firstCell != null && "合计工时".equals(firstCell.getStringCellValue())) {
////                    break; // 如果当前行包含“合计”，停止读取
////                }
//
//                // 获取每一列的数据（根据实际列数调整）
//                ToolingLedgerTable toolingLedgerTable = new ToolingLedgerTable();
//
//                // 获取并打印工具名称
//                String toolname = getStringCellValue(row, 1);
//                System.out.println("toolname: " + toolname);  // 打印工具编号
//                toolingLedgerTable.setMoldName(toolname);
//
//                // 第一行工具编号作为父模具
////                if (parentWorkwear == null) {
////                    parentWorkwear = toolNumber;
////                }
////                toolingTable.setMoldOwnership(parentWorkwear);
////                System.out.println("MoldOwnership: " + parentWorkwear);  // 打印工具编号
//
//                // 获取并打印工具编号
//                String toolnumber = getStringCellValue(row, 2);
//                System.out.println("toolnumber: " + toolnumber);  // 打印工具名称
//                toolingLedgerTable.setMoldNumber(toolnumber);
//
//                // 获取并打印模具位置
//                String moldPosition = getStringCellValue(row,3);
//                System.out.println("moldPosition: " + moldPosition);  // 打印合计数量
//                toolingLedgerTable.setMoldPosition(moldPosition);
//
////                // 如果总数量是0，则设置为null
////                if (totalQuantity == 0) {
////                    toolingTable.setTotalQuantity(null); // 设置为null
////                } else {
////                    toolingTable.setTotalQuantity((long) totalQuantity); // 设置为有效的数量
////                }
//
//
//                // 获取并打印模具状态
//                String moldStatus = getStringCellValue(row, 4);
//                System.out.println("moldStatus: " + moldStatus);  // 打印材质
//                toolingLedgerTable.setMoldStatus(moldStatus);
//
//                // 获取并打印组装产品
//                String assemblingProducts = getStringCellValue(row, 5);
//                System.out.println("assemblingProducts " + assemblingProducts);  // 打印下料尺寸
//                toolingLedgerTable.setAssemblingProducts(assemblingProducts);
//
//                // 获取并打印备注
//                String remark_1 = getStringCellValue(row, 6);
//                String remark_2 = getStringCellValue(row,7);
//                String remark = null;
//                System.out.println("remark_1: " + remark_1);  //
//                System.out.println("remark_2: " + remark_2);  //
//
//                if (remark_2 != null)
//                    remark = remark_1 + ',' + remark_2;
//                else remark = remark_1;
//
//                toolingLedgerTable.setRemark(remark);
////                if (totalWeight == 0) {
////                    toolingTable.setTotalWeight(null); // 设置为null
////                } else {
////                    toolingTable.setTotalWeight(BigDecimal.valueOf(totalWeight)); // 设置有效的重量
////                }
//
////
//
//                toolingLedgerTables.add(toolingLedgerTable); // 将解析的数据添加到列表中
//            }
//
//            // 将列表批量插入数据库
//            rows = toolingLedgerTableMapper.insertBatch(toolingLedgerTables);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 如果发生异常，记录错误信息
//            return -1; // 返回失败标识
//        }
//        return 0;
//    }
//@Transactional
//public int loadImport(MultipartFile file) {
//    List<ToolingLedgerTable> toolingLedgerTables = null; // 解析Excel到对象列表
//    try {
//        toolingLedgerTables = parseExcel(file);
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
//    if (toolingLedgerTables.isEmpty()) return 0;
//
//    // 1. 提取所有待导入的toolnumber
//    Set<String> incomingToolNumbers = toolingLedgerTables.stream()
//            .map(ToolingLedgerTable::getMoldNumber)
//            .collect(Collectors.toSet());
//
//    // 2. 批量查询已存在的toolnumber（分批次避免IN过大）
//    Set<String> existingToolNumbers = new HashSet<>();
//    int batchSize = 1000;
//    List<String> incomingList = new ArrayList<>(incomingToolNumbers);
//    for (int i = 0; i < incomingList.size(); i += batchSize) {
//        List<String> batch = incomingList.subList(i, Math.min(i + batchSize, incomingList.size()));
//        existingToolNumbers.addAll(toolingLedgerTableMapper.existByToolNumbers(batch));
//    }
//
//    // 3. 分离插入和更新数据
//    Map<Boolean, List<ToolingLedgerTable>> partitioned = toolingLedgerTables.stream()
//            .collect(Collectors.partitioningBy(
//                    item -> existingToolNumbers.contains(item.getMoldNumber())
//            ));
//
//    List<ToolingLedgerTable> toUpdate = partitioned.get(true);
//    List<ToolingLedgerTable> toInsert = partitioned.get(false);
//
//    // 4. 分批执行更新和插入
//    int affectedRows = 0;
//    if (!toUpdate.isEmpty()) {
//        for (int i = 0; i < toUpdate.size(); i += batchSize) {
//            List<ToolingLedgerTable> batch = toUpdate.subList(i, Math.min(i + batchSize, toUpdate.size()));
//            affectedRows += toolingLedgerTableMapper.updateBatch(batch);
//        }
//    }
//    if (!toInsert.isEmpty()) {
//        for (int i = 0; i < toInsert.size(); i += batchSize) {
//            List<ToolingLedgerTable> batch = toInsert.subList(i, Math.min(i + batchSize, toInsert.size()));
//            affectedRows += toolingLedgerTableMapper.insertBatch(batch);
//        }
//    }
//
//    return affectedRows;
//}

    @Transactional
    public int loadImport(MultipartFile file) {
        try {
            // 1. 解析Excel数据
            List<ToolingLedgerTable> importData = parseExcel(file);
            if (importData.isEmpty()) return 0;

            // 2. 解析第二个Sheet（新增逻辑）
            List<NonstructuraltoolingTable> sheet2Data = parseExcelForSheet2(file);

            // 3. 解析第三个Sheet（新增逻辑）
            List<NonstructuraltoolingTable> sheet3Data = parseExcelForSheet3(file);

            // 4. 解析第四个Sheet（新增逻辑）
            List<NonstructuraltoolingTable> sheet4Data = parseExcelForSheet4(file);

            // 3. 分批次执行合并操作（避免单条SQL过长）
            int batchSize = 1000; // 根据数据库配置调整
            int affectedRows = 0;
            for (int i = 0; i < importData.size(); i += batchSize) {
                List<ToolingLedgerTable> batch = importData.subList(i, Math.min(i + batchSize, importData.size()));
                affectedRows += toolingLedgerTableMapper.upsertBatch(batch);
            }
//            System.out.println("结构班导入成功==");
            // 4. 处理第二个Sheet数据
            if (sheet2Data != null && !sheet2Data.isEmpty()) {
                for (int i = 0; i < sheet2Data.size(); i += batchSize) {
                    List<NonstructuraltoolingTable> batch = sheet2Data.subList(i, Math.min(i + batchSize, sheet2Data.size()));
//                    System.out.println("batch" + batch);
                    affectedRows += nonstructuraltoolingTableMapper.upsertBatch(batch);
                }
            }
            // 5. 处理第三个Sheet数据
            if (sheet3Data != null && !sheet3Data.isEmpty()) {
                for (int i = 0; i < sheet3Data.size(); i += batchSize) {
                    List<NonstructuraltoolingTable> batch = sheet3Data.subList(i, Math.min(i + batchSize, sheet3Data.size()));
//                    System.out.println("batch" + batch);
                    affectedRows += nonstructuraltoolingTableMapper.upsertBatch(batch);
                }
            }
            // 5. 处理第四个Sheet数据
            if (sheet4Data != null && !sheet4Data.isEmpty()) {
                for (int i = 0; i < sheet4Data.size(); i += batchSize) {
                    List<NonstructuraltoolingTable> batch = sheet4Data.subList(i, Math.min(i + batchSize, sheet4Data.size()));
//                    System.out.println("batch" + batch);
                    affectedRows += nonstructuraltoolingTableMapper.upsertBatch(batch);
                }
            }

            return affectedRows;
        } catch (Exception e) {
            logger.error("导入失败", e);
            throw new RuntimeException("导入失败");
        }
    }
    private List<ToolingLedgerTable> parseExcel(MultipartFile file) throws IOException {
        List<ToolingLedgerTable> list = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowIndex = sheet.getLastRowNum();

            // 从第2行开始（索引从0开始）
            for (int rowIndex = 2; rowIndex <= lastRowIndex; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                ToolingLedgerTable entity = new ToolingLedgerTable();

                // 按列解析数据（假设列顺序与业务定义一致）
                String toolnumber = getStringCellValue(row, 2); // 第3列（索引2）
                if (StringUtils.isBlank(toolnumber)) {
                    // 跳过工具编号为空的行
                    continue;
                }

                // 设置字段（根据实际Excel列顺序调整）
                entity.setMoldNumber(toolnumber);
                entity.setMoldName(getStringCellValue(row, 1));       // 第2列
                entity.setMoldPosition(getStringCellValue(row, 3));  // 第4列
                entity.setMoldStatus(getStringCellValue(row, 4));    // 第5列
                entity.setAssemblingProducts(getStringCellValue(row, 5)); // 第6列

                // 处理备注（合并两列）
                String remark1 = getStringCellValue(row, 6);
                String remark2 = getStringCellValue(row, 7);
                entity.setRemark(StringUtils.join(new String[]{remark1, remark2}, ","));

                list.add(entity);
            }
        }
        return list;
    }
    /**
     * 解析Excel的第二个Sheet（非结构工装台账）
     */
    private List<NonstructuraltoolingTable> parseExcelForSheet2(MultipartFile file) throws IOException {
        List<NonstructuraltoolingTable> list = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            // 获取第二个Sheet（索引为1）
            Sheet sheet = workbook.getSheetAt(1);
            DataFormatter formatter = new DataFormatter();
            int lastRowIndex = sheet.getLastRowNum();

            // 从第3行开始解析（假设前两行为标题）
            for (int rowIndex = 2; rowIndex <= lastRowIndex; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                NonstructuraltoolingTable entity = new NonstructuraltoolingTable();

                // 按列解析数据（根据实际Excel列顺序调整索引）
                // 示例：模具号在第3列（索引1）
                String moldNumber = formatter.formatCellValue(row.getCell(2));
                if (StringUtils.isBlank(moldNumber)) {
                    continue; // 跳过模具号为空的记录
                }
                entity.setMoldNumber(moldNumber);

                // 模具名称在第2列（索引2）
                entity.setMoldName(formatter.formatCellValue(row.getCell(1)));

                // 种类在第4列（索引3）
                entity.setMoldType(formatter.formatCellValue(row.getCell(3)));

                // 处理日期字段（投入时间在第5列，索引4）
                Cell investTimeCell = row.getCell(4);
                if (investTimeCell != null) {
                    try {
                        switch (investTimeCell.getCellType()) {
                            case NUMERIC:
                                // 处理Excel内部日期格式（数值型）
                                if (DateUtil.isCellDateFormatted(investTimeCell)) {
                                    entity.setInvestTime(investTimeCell.getDateCellValue());
                                } else {
                                    logger.warn("投入时间列为非日期数值，行号：{}", rowIndex + 1);
                                }
                                break;
                            case STRING:
                                // 处理文本型日期（如"2014.1.10"）
                                String dateStr = investTimeCell.getStringCellValue().trim();
                                if (!dateStr.isEmpty()) {
                                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                                    sdf.setLenient(false); // 严格匹配格式
                                    entity.setInvestTime(sdf.parse(dateStr));
                                }
                                break;
                            default:
                                logger.warn("投入时间格式不支持，行号：{}", rowIndex + 1);
                        }
                    } catch (IllegalStateException e) {
                        logger.error("投入时间解析失败，行号：{}，值：{}",
                                rowIndex + 1,
                                formatter.formatCellValue(investTimeCell)
                        );
                        throw new RuntimeException("日期格式错误", e); // 阻止无效数据入库
                    } catch (java.text.ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.warn("投入时间为空，行号：{}", rowIndex + 1);
                    throw new RuntimeException("投入时间为必填项");
                }
                // 状态
                entity.setMoldStatus(formatter.formatCellValue(row.getCell(5)));
                //数量
                entity.setQuantityOfTooling(formatter.formatCellValue(row.getCell(6)));
                //图物料
                entity.setAssemblingProducts(formatter.formatCellValue(row.getCell(7)));
                //位置
                entity.setMoldPosition(formatter.formatCellValue(row.getCell(8)));
                //备注
                entity.setRemark(formatter.formatCellValue(row.getCell(9)));

                entity.setMoldOwnership("metalworkingClassTooling");
                // 其他字段解析（根据实际列顺序补充）...
                // entity.setMoldStatus(...);
                // entity.setQuantityOfTooling(...);

                list.add(entity);
            }
        }
        return list;
    }
    /**
     * 解析Excel的第三个Sheet（非结构工装台账）
     */
    private List<NonstructuraltoolingTable> parseExcelForSheet3(MultipartFile file) throws IOException {
        List<NonstructuraltoolingTable> list = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            // 获取第二个Sheet（索引为1）
            Sheet sheet = workbook.getSheetAt(2);
            DataFormatter formatter = new DataFormatter();
            int lastRowIndex = sheet.getLastRowNum();

            // 从第3行开始解析（假设前两行为标题）
            for (int rowIndex = 2; rowIndex <= lastRowIndex; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                NonstructuraltoolingTable entity = new NonstructuraltoolingTable();

                // 按列解析数据（根据实际Excel列顺序调整索引）
                // 示例：模具号在第3列（索引1）
                String moldNumber = formatter.formatCellValue(row.getCell(2));
                if (StringUtils.isBlank(moldNumber)) {
                    continue; // 跳过模具号为空的记录
                }
                entity.setMoldNumber(moldNumber);

                // 模具名称在第2列（索引2）
                entity.setMoldName(formatter.formatCellValue(row.getCell(1)));

                // 种类在第4列（索引3）
                entity.setMoldType(formatter.formatCellValue(row.getCell(3)));

                // 处理日期字段（投入时间在第5列，索引4）
                Cell investTimeCell = row.getCell(4);
                if (investTimeCell != null) {
                    try {
                        switch (investTimeCell.getCellType()) {
                            case NUMERIC:
                                // 处理Excel内部日期格式（数值型）
                                if (DateUtil.isCellDateFormatted(investTimeCell)) {
                                    entity.setInvestTime(investTimeCell.getDateCellValue());
                                } else {
                                    logger.warn("投入时间列为非日期数值，行号：{}", rowIndex + 1);
                                }
                                break;
                            case STRING:
                                // 处理文本型日期（如"2014.1.10"）
                                String dateStr = investTimeCell.getStringCellValue().trim();
                                if (!dateStr.isEmpty()) {
                                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                                    sdf.setLenient(false); // 严格匹配格式
                                    entity.setInvestTime(sdf.parse(dateStr));
                                }
                                break;
                            default:
                                logger.warn("投入时间格式不支持，行号：{}", rowIndex + 1);
                        }
                    } catch (IllegalStateException e) {
                        logger.error("投入时间解析失败，行号：{}，值：{}",
                                rowIndex + 1,
                                formatter.formatCellValue(investTimeCell)
                        );
                        throw new RuntimeException("日期格式错误", e); // 阻止无效数据入库
                    } catch (java.text.ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.warn("投入时间为空，行号：{}", rowIndex + 1);
                    throw new RuntimeException("投入时间为必填项");
                }
                // 状态
                entity.setMoldStatus(formatter.formatCellValue(row.getCell(5)));
                //数量
                entity.setQuantityOfTooling(formatter.formatCellValue(row.getCell(6)));
                //图物料
                entity.setAssemblingProducts(formatter.formatCellValue(row.getCell(7)));
                //位置
                entity.setMoldPosition(formatter.formatCellValue(row.getCell(8)));
                //备注
                entity.setRemark(formatter.formatCellValue(row.getCell(9)));

                entity.setMoldOwnership("materialPreparationTeamTooling");
                // 其他字段解析（根据实际列顺序补充）...
                // entity.setMoldStatus(...);
                // entity.setQuantityOfTooling(...);

                list.add(entity);
            }
        }
        return list;
    }

    /**
     * 解析Excel的第四个Sheet（非结构工装台账）
     */
    private List<NonstructuraltoolingTable> parseExcelForSheet4(MultipartFile file) throws IOException {
        List<NonstructuraltoolingTable> list = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            // 获取第二个Sheet（索引为1）
            Sheet sheet = workbook.getSheetAt(3);
            List<CellRangeAddress> mergedRegions = sheet.getMergedRegions(); // 获取合并区域

            DataFormatter formatter = new DataFormatter();
            int lastRowIndex = sheet.getLastRowNum();

            // 从第3行开始解析（假设前两行为标题）
            for (int rowIndex = 2; rowIndex <= lastRowIndex; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                NonstructuraltoolingTable entity = new NonstructuraltoolingTable();

                //--- 处理用途（合并单元格逻辑） ---
                int typeColumnIndex = 1; // 种类列索引
                String moldUsage = null;

                // 1. 检查是否在合并区域
                moldUsage = getMergedCellValue(sheet, rowIndex, typeColumnIndex, mergedRegions);
                System.out.println("moldUsage:" + moldUsage);

                // 2. 处理普通单元格
                if (moldUsage == null) {
                    Cell typeCell = row.getCell(typeColumnIndex);
                    moldUsage = (typeCell != null) ? formatter.formatCellValue(typeCell) : "";
                }

                entity.setMoldUsage(moldUsage);

                // 模具名称在第2列（索引2）
//                entity.setMoldUsage(formatter.formatCellValue(row.getCell(1)));

                // 按列解析数据（根据实际Excel列顺序调整索引）
                // 示例：模具号在第3列（索引1）
                String moldNumber = formatter.formatCellValue(row.getCell(3));
                if (StringUtils.isBlank(moldNumber)) {
                    continue; // 跳过模具号为空的记录
                }
                entity.setMoldNumber(moldNumber);

                // 模具名称在第2列（索引2）
                entity.setMoldName(formatter.formatCellValue(row.getCell(2)));

                // 种类在第4列（索引3）
                entity.setMoldType(formatter.formatCellValue(row.getCell(4)));

                // 处理日期字段（投入时间在第5列，索引4）
                Cell investTimeCell = row.getCell(5);
                if (investTimeCell != null) {
                    try {
                        switch (investTimeCell.getCellType()) {
                            case NUMERIC:
                                // 处理Excel内部日期格式（数值型）
                                if (DateUtil.isCellDateFormatted(investTimeCell)) {
                                    entity.setInvestTime(investTimeCell.getDateCellValue());
                                } else {
                                    logger.warn("投入时间列为非日期数值，行号：{}", rowIndex + 1);
                                }
                                break;
                            case STRING:
                                // 处理文本型日期（如"2014.1.10"）
                                String dateStr = investTimeCell.getStringCellValue().trim();
                                if (!dateStr.isEmpty()) {
                                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                                    sdf.setLenient(false); // 严格匹配格式
                                    entity.setInvestTime(sdf.parse(dateStr));
                                }
                                break;
                            default:
                                logger.warn("投入时间格式不支持，行号：{}", rowIndex + 1);
                        }
                    } catch (IllegalStateException e) {
                        logger.error("投入时间解析失败，行号：{}，值：{}",
                                rowIndex + 1,
                                formatter.formatCellValue(investTimeCell)
                        );
                        throw new RuntimeException("日期格式错误", e); // 阻止无效数据入库
                    } catch (java.text.ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.warn("投入时间为空，行号：{}", rowIndex + 1);
                    throw new RuntimeException("投入时间为必填项");
                }
                // 状态
                entity.setMoldStatus(formatter.formatCellValue(row.getCell(6)));
                //数量
                entity.setQuantityOfTooling(formatter.formatCellValue(row.getCell(7)));
                //图物料
                entity.setAssemblingProducts(formatter.formatCellValue(row.getCell(8)));
                //位置
                entity.setMoldPosition(formatter.formatCellValue(row.getCell(9)));
                //备注
                entity.setRemark(formatter.formatCellValue(row.getCell(10)));

                entity.setMoldOwnership("externalTooling");
                // 其他字段解析（根据实际列顺序补充）...
                // entity.setMoldStatus(...);
                // entity.setQuantityOfTooling(...);

                list.add(entity);
            }
        }
        return list;
    }
    // 辅助方法：获取合并单元格的值
    private String getMergedCellValue(Sheet sheet, int rowIndex, int columnIndex, List<CellRangeAddress> mergedRegions) {
        for (CellRangeAddress region : mergedRegions) {
            if (region.isInRange(rowIndex, columnIndex)) {
                Row firstRow = sheet.getRow(region.getFirstRow());
                Cell firstCell = firstRow.getCell(region.getFirstColumn());
                return new DataFormatter().formatCellValue(firstCell);
            }
        }
        return null;
    }
    // 辅助方法：安全获取单元格字符串值
    private String getStringCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }
    // 更新文件路径的具体方法
    private void updateFilePath(String moldNumber, String file, String fileType) {
        // 根据moldNumber和fileType更新数据库中的路径
        // 在这个方法中可以使用MyBatis的update方法来执行数据库更新操作

        if ("processDocuments".equals(fileType)) {
            toolingLedgerTableMapper.updateFilePath(moldNumber, file, "processDocuments");
        } else if ("mbom".equals(fileType)) {
            toolingLedgerTableMapper.updateFilePath(moldNumber, file, "mbom");
        }
        else if ("toolingDrawings".equals(fileType)) {
            toolingLedgerTableMapper.updateFilePath(moldNumber, file, "toolingDrawings");
        }
    }

    // 获取单元格的内容，处理数字和字符串类型的情况
//    public String getStringCellValue(Row row, int columnIndex) {
//        Cell cell = row.getCell(columnIndex);
//        if (cell == null) {
//            return ""; // 如果单元格为空，返回空字符串
//        }
//
//        // 判断单元格类型
//        switch (cell.getCellType()) {
//            case STRING:
//                return cell.getStringCellValue(); // 字符串类型直接返回
//            case NUMERIC:
//                // 如果是数字类型，可能是日期或普通数字，需要判断
//                if (DateUtil.isCellDateFormatted(cell)) {
//                    // 如果是日期格式，则返回日期字符串
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    return sdf.format(cell.getDateCellValue());
//                } else {
//                    // 如果是数字，转换为字符串
//                    return String.valueOf(cell.getNumericCellValue());
//                }
//            case BOOLEAN:
//                return String.valueOf(cell.getBooleanCellValue()); // 布尔类型返回字符串
//            case FORMULA:
//                return cell.getCellFormula(); // 公式类型返回公式字符串
//            default:
//                return ""; // 默认返回空字符串
//        }
//    }

}
