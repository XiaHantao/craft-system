package com.ruoyi.process.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺验证与总结对象 process_validation_and_summary_table
 * 
 * @author Kanna Bush
 * @date 2025-03-10
 */
@Data
public class ProcessValidationAndSummaryTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 验证报告名称 */
    @Excel(name = "验证报告名称")
    private String verificationReportName;

    /** 验证报告路径 */
    @Excel(name = "验证报告路径")
    private String verificationReportPath;

    /** 验证报告上传人 */
    @Excel(name = "验证报告上传人")
    private String verificationReportUploadPerson;

    /** 验证报告上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "验证报告上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date verificationReportUploadTime;


    /** 改进报告名称 */
    @Excel(name = "改进报告名称")
    private String improvementReportName;

    /** 改进报告路径 */
    @Excel(name = "改进报告路径")
    private String improvementReportPath;

    /** 改进报告上传人 */
    @Excel(name = "改进报告上传人")
    private String improvementReportUploadPerson;

    /** 改进报告上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "改进报告上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date improvementReportUploadTime;

    /** 总结报告名称 */
    @Excel(name = "总结报告名称")
    private String summaryReportName;

    /** 总结报告路径 */
    @Excel(name = "总结报告路径")
    private String summaryReportPath;

    /** 总结报告上传人 */
    @Excel(name = "总结报告上传人")
    private String summaryReportUploadPerson;

    /** 总结报告上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "总结报告上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date summaryReportUploadTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 标记(历史版本为0) */
    @Excel(name = "标记(历史版本为0)")
    private Long newFlag;


}
