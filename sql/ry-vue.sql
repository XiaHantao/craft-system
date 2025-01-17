-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: ry-vue
-- ------------------------------------------------------
-- Server version	5.7.38-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table`
(
    `table_id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_name`        varchar(200)  DEFAULT '' COMMENT '表名称',
    `table_comment`     varchar(500)  DEFAULT '' COMMENT '表描述',
    `sub_table_name`    varchar(64)   DEFAULT NULL COMMENT '关联子表的表名',
    `sub_table_fk_name` varchar(64)   DEFAULT NULL COMMENT '子表关联的外键名',
    `class_name`        varchar(100)  DEFAULT '' COMMENT '实体类名称',
    `tpl_category`      varchar(200)  DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
    `package_name`      varchar(100)  DEFAULT NULL COMMENT '生成包路径',
    `module_name`       varchar(30)   DEFAULT NULL COMMENT '生成模块名',
    `business_name`     varchar(30)   DEFAULT NULL COMMENT '生成业务名',
    `function_name`     varchar(50)   DEFAULT NULL COMMENT '生成功能名',
    `function_author`   varchar(50)   DEFAULT NULL COMMENT '生成功能作者',
    `gen_type`          char(1)       DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
    `gen_path`          varchar(200)  DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
    `options`           varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
    `create_by`         varchar(64)   DEFAULT '' COMMENT '创建者',
    `create_time`       datetime      DEFAULT NULL COMMENT '创建时间',
    `update_by`         varchar(64)   DEFAULT '' COMMENT '更新者',
    `update_time`       datetime      DEFAULT NULL COMMENT '更新时间',
    `remark`            varchar(500)  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`table_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10036
  DEFAULT CHARSET = utf8 COMMENT ='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column`
(
    `column_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_id`       varchar(64)  DEFAULT NULL COMMENT '归属表编号',
    `column_name`    varchar(200) DEFAULT NULL COMMENT '列名称',
    `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
    `column_type`    varchar(100) DEFAULT NULL COMMENT '列类型',
    `java_type`      varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
    `java_field`     varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
    `is_pk`          char(1)      DEFAULT NULL COMMENT '是否主键（1是）',
    `is_increment`   char(1)      DEFAULT NULL COMMENT '是否自增（1是）',
    `is_required`    char(1)      DEFAULT NULL COMMENT '是否必填（1是）',
    `is_insert`      char(1)      DEFAULT NULL COMMENT '是否为插入字段（1是）',
    `is_edit`        char(1)      DEFAULT NULL COMMENT '是否编辑字段（1是）',
    `is_list`        char(1)      DEFAULT NULL COMMENT '是否列表字段（1是）',
    `is_query`       char(1)      DEFAULT NULL COMMENT '是否查询字段（1是）',
    `query_type`     varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
    `html_type`      varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    `dict_type`      varchar(200) DEFAULT '' COMMENT '字典类型',
    `sort`           int(11)      DEFAULT NULL COMMENT '排序',
    `create_by`      varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`column_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10384
  DEFAULT CHARSET = utf8 COMMENT ='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--

LOCK TABLES `gen_table_column` WRITE;
/*!40000 ALTER TABLE `gen_table_column`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table_column`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_blob_triggers`
--

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_blob_triggers`
(
    `sched_name`    varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`  varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `blob_data`     blob COMMENT '存放持久化Trigger对象',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='Blob类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_calendars`
(
    `sched_name`    varchar(120) NOT NULL COMMENT '调度名称',
    `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
    `calendar`      blob         NOT NULL COMMENT '存放持久化calendar对象',
    PRIMARY KEY (`sched_name`, `calendar_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='日历信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_cron_triggers`
(
    `sched_name`      varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`    varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`   varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `cron_expression` varchar(200) NOT NULL COMMENT 'cron表达式',
    `time_zone_id`    varchar(80) DEFAULT NULL COMMENT '时区',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='Cron类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_fired_triggers`
(
    `sched_name`        varchar(120) NOT NULL COMMENT '调度名称',
    `entry_id`          varchar(95)  NOT NULL COMMENT '调度器实例id',
    `trigger_name`      varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`     varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `instance_name`     varchar(200) NOT NULL COMMENT '调度器实例名',
    `fired_time`        bigint(13)   NOT NULL COMMENT '触发的时间',
    `sched_time`        bigint(13)   NOT NULL COMMENT '定时器制定的时间',
    `priority`          int(11)      NOT NULL COMMENT '优先级',
    `state`             varchar(16)  NOT NULL COMMENT '状态',
    `job_name`          varchar(200) DEFAULT NULL COMMENT '任务名称',
    `job_group`         varchar(200) DEFAULT NULL COMMENT '任务组名',
    `is_nonconcurrent`  varchar(1)   DEFAULT NULL COMMENT '是否并发',
    `requests_recovery` varchar(1)   DEFAULT NULL COMMENT '是否接受恢复执行',
    PRIMARY KEY (`sched_name`, `entry_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='已触发的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_job_details`
(
    `sched_name`        varchar(120) NOT NULL COMMENT '调度名称',
    `job_name`          varchar(200) NOT NULL COMMENT '任务名称',
    `job_group`         varchar(200) NOT NULL COMMENT '任务组名',
    `description`       varchar(250) DEFAULT NULL COMMENT '相关介绍',
    `job_class_name`    varchar(250) NOT NULL COMMENT '执行任务类名称',
    `is_durable`        varchar(1)   NOT NULL COMMENT '是否持久化',
    `is_nonconcurrent`  varchar(1)   NOT NULL COMMENT '是否并发',
    `is_update_data`    varchar(1)   NOT NULL COMMENT '是否更新数据',
    `requests_recovery` varchar(1)   NOT NULL COMMENT '是否接受恢复执行',
    `job_data`          blob COMMENT '存放持久化job对象',
    PRIMARY KEY (`sched_name`, `job_name`, `job_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='任务详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_details`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_locks`
(
    `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
    `lock_name`  varchar(40)  NOT NULL COMMENT '悲观锁名称',
    PRIMARY KEY (`sched_name`, `lock_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='存储的悲观锁信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_locks`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_paused_trigger_grps`
(
    `sched_name`    varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    PRIMARY KEY (`sched_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='暂停的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_scheduler_state`
(
    `sched_name`        varchar(120) NOT NULL COMMENT '调度名称',
    `instance_name`     varchar(200) NOT NULL COMMENT '实例名称',
    `last_checkin_time` bigint(13)   NOT NULL COMMENT '上次检查时间',
    `checkin_interval`  bigint(13)   NOT NULL COMMENT '检查间隔时间',
    PRIMARY KEY (`sched_name`, `instance_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='调度器状态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_scheduler_state`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simple_triggers`
(
    `sched_name`      varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`    varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`   varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `repeat_count`    bigint(7)    NOT NULL COMMENT '重复的次数统计',
    `repeat_interval` bigint(12)   NOT NULL COMMENT '重复的间隔时间',
    `times_triggered` bigint(10)   NOT NULL COMMENT '已经触发的次数',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='简单触发器的信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simprop_triggers`
(
    `sched_name`    varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`  varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `str_prop_1`    varchar(512)   DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
    `str_prop_2`    varchar(512)   DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
    `str_prop_3`    varchar(512)   DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
    `int_prop_1`    int(11)        DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
    `int_prop_2`    int(11)        DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
    `long_prop_1`   bigint(20)     DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
    `long_prop_2`   bigint(20)     DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
    `dec_prop_1`    decimal(13, 4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
    `dec_prop_2`    decimal(13, 4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
    `bool_prop_1`   varchar(1)     DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
    `bool_prop_2`   varchar(1)     DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='同步机制的行锁表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_triggers`
(
    `sched_name`     varchar(120) NOT NULL COMMENT '调度名称',
    `trigger_name`   varchar(200) NOT NULL COMMENT '触发器的名字',
    `trigger_group`  varchar(200) NOT NULL COMMENT '触发器所属组的名字',
    `job_name`       varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
    `job_group`      varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
    `description`    varchar(250) DEFAULT NULL COMMENT '相关介绍',
    `next_fire_time` bigint(13)   DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
    `prev_fire_time` bigint(13)   DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
    `priority`       int(11)      DEFAULT NULL COMMENT '优先级',
    `trigger_state`  varchar(16)  NOT NULL COMMENT '触发器状态',
    `trigger_type`   varchar(8)   NOT NULL COMMENT '触发器的类型',
    `start_time`     bigint(13)   NOT NULL COMMENT '开始时间',
    `end_time`       bigint(13)   DEFAULT NULL COMMENT '结束时间',
    `calendar_name`  varchar(200) DEFAULT NULL COMMENT '日程表名称',
    `misfire_instr`  smallint(2)  DEFAULT NULL COMMENT '补偿执行的策略',
    `job_data`       blob COMMENT '存放持久化job对象',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    KEY `sched_name` (`sched_name`, `job_name`, `job_group`),
    CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='触发器详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_triggers`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config`
(
    `config_id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `config_name`  varchar(100) DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
    `config_type`  char(1)      DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_by`    varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    `remark`       varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`config_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1109634094700040201
  DEFAULT CHARSET = utf8 COMMENT ='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config`
    DISABLE KEYS */;
INSERT INTO `sys_config`
VALUES (1109633958661984300, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin',
        '2023-05-21 01:27:42', '', NULL,
        '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
       (1109633983643258900, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin',
        '2023-05-21 01:27:42', '', NULL, '初始化密码 123456'),
       (1109634009631166500, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin',
        '2023-05-21 01:27:42', '', NULL, '深色主题theme-dark，浅色主题theme-light'),
       (1109634037938524200, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin',
        '2023-05-21 01:27:42', '', NULL, '是否开启验证码功能（true开启，false关闭）'),
       (1109634068003295200, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin',
        '2023-05-21 01:27:42', '', NULL, '是否开启注册用户功能（true开启，false关闭）'),
       (1109634094700040200, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2023-05-21 01:27:42',
        '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');
/*!40000 ALTER TABLE `sys_config`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept`
(
    `dept_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `parent_id`   bigint(20)   DEFAULT '0' COMMENT '父部门id',
    `parent_name` varchar(30)  DEFAULT '' COMMENT '父部门名称',
    `ancestors`   varchar(200) DEFAULT '' COMMENT '祖级列表',
    `user_id`     bigint(20)   DEFAULT NULL COMMENT '用户id',
    `tenant_id`   bigint(20) NOT NULL COMMENT '租户id',
    `dept_name`   varchar(30)  DEFAULT '' COMMENT '部门名称',
    `order_num`   int(4)       DEFAULT '100' COMMENT '显示顺序',
    `leader`      varchar(20)  DEFAULT NULL COMMENT '负责人',
    `phone`       varchar(11)  DEFAULT NULL COMMENT '联系电话',
    `email`       varchar(50)  DEFAULT NULL COMMENT '邮箱',
    `status`      char(1)      DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
    `del_flag`    char(1)      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`dept_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1110942587344261121
  DEFAULT CHARSET = utf8 COMMENT ='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept`
    DISABLE KEYS */;
INSERT INTO `sys_dept`
VALUES (1109506726979309600, 0, '', '0', 1109511216780415000, 1109495825869967400, '老实人科技', 0, '老徐',
        '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-05-21 01:27:42', '', NULL),
       (1109506801340125200, 0, '', '0', 1109511251509252100, 1109501449164951600, '测试科技', 0, '老徐', '15888888888',
        'ry@qq.com', '0', '0', 'admin', '2023-05-21 01:27:42', '', NULL),
       (1110270359690678273, 0, '', '0', 1110270359690678272, 1110270359690678274, '121', 100, NULL, NULL, NULL, '0',
        '0', '', '2023-05-22 18:18:05', '', NULL),
       (1110599748030697472, 1109506726979309600, '老实人科技', '0,1109506726979309600', NULL, 1109495825869967400,
        'sedasdasdaaa', 108, 'asdasdd', '18087759031', 'asdasd@sd.csaa', '0', '0', 'admin', '2023-05-23 16:06:53',
        'admin', '2023-05-23 16:07:43'),
       (1110601143777628160, 1110270359690678273, '', '0,1110270359690678273', NULL, 1110270359690678274, 'sdasdddsa',
        0, 'asdasd', NULL, NULL, '0', '0', 'xuqihang', '2023-05-23 16:12:26', '', NULL),
       (1110601165814501376, 1110601143777628160, '', '0,1110270359690678273,1110601143777628160', NULL,
        1110270359690678274, 'asdasdasd', 0, NULL, NULL, NULL, '0', '0', 'xuqihang', '2023-05-23 16:12:31', '', NULL),
       (1110942231856025600, 1109506726979309600, '', '0,1109506726979309600', NULL, 1109495825869967400, 'wwww', 0,
        NULL, NULL, NULL, '0', '2', 'admin', '2023-05-24 14:47:48', '', NULL),
       (1110942339444117504, 1110270359690678273, '', '0,1110270359690678273', NULL, 1110270359690678274, 'qqqe', 0,
        NULL, NULL, NULL, '0', '0', 'xuqihang', '2023-05-24 14:48:14', '', NULL),
       (1110942370737819648, 1110942339444117504, '', '0,1110270359690678273,1110942339444117504', NULL,
        1110270359690678274, 'qeeew', 0, NULL, NULL, NULL, '0', '0', 'xuqihang', '2023-05-24 14:48:21', '', NULL),
       (1110942587344261120, 1110270359690678273, '', '0,1110270359690678273', NULL, 1110270359690678274, 'dddddd', 0,
        NULL, NULL, NULL, '0', '0', 'xuqihang', '2023-05-24 14:49:13', '', NULL);
/*!40000 ALTER TABLE `sys_dept`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_data`
(
    `dict_code`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_sort`   int(4)       DEFAULT '0' COMMENT '字典排序',
    `dict_label`  varchar(100) DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100) DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) DEFAULT '' COMMENT '字典类型',
    `css_class`   varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class`  varchar(100) DEFAULT NULL COMMENT '表格回显样式',
    `is_default`  char(1)      DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status`      char(1)      DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1109635920589623301
  DEFAULT CHARSET = utf8 COMMENT ='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data`
    DISABLE KEYS */;
INSERT INTO `sys_dict_data`
VALUES (1109634856515670000, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '性别男'),
       (1109634897158475800, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '性别女'),
       (1109634923838443500, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '性别未知'),
       (1109634949406920700, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '显示菜单'),
       (1109634975675846700, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '隐藏菜单'),
       (1109635001860886500, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '正常状态'),
       (1109635028507299800, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '停用状态'),
       (1109635059750670300, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '正常状态'),
       (1109635090901766100, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '停用状态'),
       (1109635364613656600, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '默认分组'),
       (1109635391968907300, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2023-05-21 01:27:42', '',
        NULL, '系统分组'),
       (1109635428417409000, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2023-05-21 01:27:42', '',
        NULL, '系统默认是'),
       (1109635452538851300, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2023-05-21 01:27:42', '',
        NULL, '系统默认否'),
       (1109635474303094800, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '通知'),
       (1109635498189656000, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '公告'),
       (1109635519396057100, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '正常状态'),
       (1109635563427860500, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '关闭状态'),
       (1109635593765261300, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-05-21 01:27:42', '',
        NULL, '其他操作'),
       (1109635614711615500, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-05-21 01:27:42', '',
        NULL, '新增操作'),
       (1109635636199034900, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-05-21 01:27:42', '',
        NULL, '修改操作'),
       (1109635655538970600, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '删除操作'),
       (1109635681501712400, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '授权操作'),
       (1109635699960844300, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '导出操作'),
       (1109635812267528200, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '导入操作'),
       (1109635836573519900, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-05-21 01:27:42',
        '', NULL, '强退操作'),
       (1109635863073132500, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '生成操作'),
       (1109635881821671400, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '清空操作'),
       (1109635902281486300, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '正常状态'),
       (1109635920589623300, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '停用状态');
/*!40000 ALTER TABLE `sys_dict_data`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_type`
(
    `dict_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name`   varchar(100) DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) DEFAULT '' COMMENT '字典类型',
    `status`      char(1)      DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_id`),
    UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1109636760931012601
  DEFAULT CHARSET = utf8 COMMENT ='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type`
    DISABLE KEYS */;
INSERT INTO `sys_dict_type`
VALUES (1109636148675874800, '用户性别', 'sys_user_sex', '0', 'admin', '2023-05-21 01:27:42', '', NULL, '用户性别列表'),
       (1109636595696406500, '菜单状态', 'sys_show_hide', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '菜单状态列表'),
       (1109636622154076200, '系统开关', 'sys_normal_disable', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '系统开关列表'),
       (1109636642911686700, '任务状态', 'sys_job_status', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '任务状态列表'),
       (1109636663325364200, '任务分组', 'sys_job_group', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '任务分组列表'),
       (1109636680769474600, '系统是否', 'sys_yes_no', '0', 'admin', '2023-05-21 01:27:42', '', NULL, '系统是否列表'),
       (1109636699408961500, '通知类型', 'sys_notice_type', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '通知类型列表'),
       (1109636720376287200, '通知状态', 'sys_notice_status', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '通知状态列表'),
       (1109636740722856000, '操作类型', 'sys_oper_type', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '操作类型列表'),
       (1109636760931012600, '系统状态', 'sys_common_status', '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        '登录状态列表');
/*!40000 ALTER TABLE `sys_dict_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job`
(
    `job_id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '任务ID',
    `job_name`        varchar(64)  NOT NULL DEFAULT '' COMMENT '任务名称',
    `job_group`       varchar(64)  NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
    `invoke_target`   varchar(500) NOT NULL COMMENT '调用目标字符串',
    `cron_expression` varchar(255)          DEFAULT '' COMMENT 'cron执行表达式',
    `misfire_policy`  varchar(20)           DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    `concurrent`      char(1)               DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
    `status`          char(1)               DEFAULT '0' COMMENT '状态（0正常 1暂停）',
    `create_by`       varchar(64)           DEFAULT '' COMMENT '创建者',
    `create_time`     datetime              DEFAULT NULL COMMENT '创建时间',
    `update_by`       varchar(64)           DEFAULT '' COMMENT '更新者',
    `update_time`     datetime              DEFAULT NULL COMMENT '更新时间',
    `remark`          varchar(500)          DEFAULT '' COMMENT '备注信息',
    PRIMARY KEY (`job_id`, `job_name`, `job_group`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1109633840722350101
  DEFAULT CHARSET = utf8 COMMENT ='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job`
    DISABLE KEYS */;
INSERT INTO `sys_job`
VALUES (1109633789136605200, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin',
        '2023-05-21 01:27:42', '', NULL, ''),
       (1109633816215031800, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1',
        'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109633840722350100, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)',
        '0/20 * * * * ?', '3', '1', '1', 'admin', '2023-05-21 01:27:42', '', NULL, '');
/*!40000 ALTER TABLE `sys_job`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job_log`
(
    `job_log_id`     bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
    `job_name`       varchar(64)  NOT NULL COMMENT '任务名称',
    `job_group`      varchar(64)  NOT NULL COMMENT '任务组名',
    `invoke_target`  varchar(500) NOT NULL COMMENT '调用目标字符串',
    `job_message`    varchar(500)  DEFAULT NULL COMMENT '日志信息',
    `status`         char(1)       DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
    `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
    `create_time`    datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`job_log_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8 COMMENT ='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_logininfor`
(
    `info_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `tenant_id`      bigint(20)   DEFAULT '0' COMMENT '租户id',
    `user_name`      varchar(50)  DEFAULT '' COMMENT '用户账号',
    `ipaddr`         varchar(128) DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50)  DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50)  DEFAULT '' COMMENT '操作系统',
    `status`         char(1)      DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255) DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime     DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`info_id`),
    KEY `idx_sys_logininfor_s` (`status`),
    KEY `idx_sys_logininfor_lt` (`login_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_logininfor`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu`
(
    `menu_id`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name`   varchar(50) NOT NULL COMMENT '菜单名称',
    `parent_id`   bigint(20)   DEFAULT '0' COMMENT '父菜单ID',
    `order_num`   int(4)       DEFAULT '0' COMMENT '显示顺序',
    `path`        varchar(200) DEFAULT '' COMMENT '路由地址',
    `component`   varchar(255) DEFAULT NULL COMMENT '组件路径',
    `query`       varchar(255) DEFAULT NULL COMMENT '路由参数',
    `is_frame`    int(1)       DEFAULT '1' COMMENT '是否为外链（0是 1否）',
    `is_cache`    int(1)       DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type`   char(1)      DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible`     char(1)      DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `status`      char(1)      DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(100) DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) DEFAULT '#' COMMENT '菜单图标',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`menu_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1110176645119086598
  DEFAULT CHARSET = utf8 COMMENT ='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu`
    DISABLE KEYS */;
INSERT INTO `sys_menu`
VALUES (1109642713386586100, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin',
        '2023-05-21 01:27:42', '', NULL, '系统管理目录'),
       (1109643216606597100, '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin',
        '2023-05-21 01:27:42', '', NULL, '系统监控目录'),
       (1109644186531008500, '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin',
        '2023-05-21 01:27:42', '', NULL, '系统工具目录'),
       (1109644393796735000, '若依官网', 0, 4, 'http://ruoyi.vip', NULL, '', 0, 0, 'M', '0', '0', '', 'guide', 'admin',
        '2023-05-21 01:27:42', '', NULL, '若依官网地址'),
       (1109644589599428600, '用户管理', 1109642713386586100, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0',
        'system:user:list', 'user', 'admin', '2023-05-21 01:27:42', '', NULL, '用户管理菜单'),
       (1109645168614707200, '角色管理', 1109642713386586100, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0',
        'system:role:list', 'peoples', 'admin', '2023-05-21 01:27:42', '', NULL, '角色管理菜单'),
       (1109645280053170200, '菜单管理', 1109642713386586100, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0',
        'system:menu:list', 'tree-table', 'admin', '2023-05-21 01:27:42', '', NULL, '菜单管理菜单'),
       (1109645424991539200, '部门管理', 1109642713386586100, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0',
        'system:dept:list', 'tree', 'admin', '2023-05-21 01:27:42', '', NULL, '部门管理菜单'),
       (1109645568545787900, '岗位管理', 1109642713386586100, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0',
        'system:post:list', 'post', 'admin', '2023-05-21 01:27:42', '', NULL, '岗位管理菜单'),
       (1109645677182455800, '字典管理', 1109642713386586100, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0',
        'system:dict:list', 'dict', 'admin', '2023-05-21 01:27:42', '', NULL, '字典管理菜单'),
       (1109645750964457500, '参数设置', 1109642713386586100, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0',
        '0', 'system:config:list', 'edit', 'admin', '2023-05-21 01:27:42', '', NULL, '参数设置菜单'),
       (1109645839950811100, '通知公告', 1109642713386586100, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0',
        '0', 'system:notice:list', 'message', 'admin', '2023-05-21 01:27:42', '', NULL, '通知公告菜单'),
       (1109645914907217900, '日志管理', 1109642713386586100, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin',
        '2023-05-21 01:27:42', '', NULL, '日志管理菜单'),
       (1109646040325296100, '在线用户', 1109643216606597100, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0',
        '0', 'monitor:online:list', 'online', 'admin', '2023-05-21 01:27:42', '', NULL, '在线用户菜单'),
       (1109646110235955200, '定时任务', 1109643216606597100, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0',
        'monitor:job:list', 'job', 'admin', '2023-05-21 01:27:42', '', NULL, '定时任务菜单'),
       (1109646184722600000, '数据监控', 1109643216606597100, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0',
        '0', 'monitor:druid:list', 'druid', 'admin', '2023-05-21 01:27:42', '', NULL, '数据监控菜单'),
       (1109646308517482500, '服务监控', 1109643216606597100, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', '0',
        '0', 'monitor:server:list', 'server', 'admin', '2023-05-21 01:27:42', '', NULL, '服务监控菜单'),
       (1109646399336747000, '缓存监控', 1109643216606597100, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0',
        '0', 'monitor:cache:list', 'redis', 'admin', '2023-05-21 01:27:42', '', NULL, '缓存监控菜单'),
       (1109646451186733000, '缓存列表', 1109643216606597100, 6, 'cacheList', 'monitor/cache/list', '', 1, 0, 'C', '0',
        '0', 'monitor:cache:list', 'redis-list', 'admin', '2023-05-21 01:27:42', '', NULL, '缓存列表菜单'),
       (1109646507931471900, '表单构建', 1109644186531008500, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0',
        'tool:build:list', 'build', 'admin', '2023-05-21 01:27:42', '', NULL, '表单构建菜单'),
       (1109646561459179500, '代码生成', 1109644186531008500, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0',
        'tool:gen:list', 'code', 'admin', '2023-05-21 01:27:42', '', NULL, '代码生成菜单'),
       (1109646688722751500, '系统接口', 1109644186531008500, 3, 'swagger', 'tool/swagger/index', '', 1, 0, 'C', '0',
        '0', 'tool:swagger:list', 'swagger', 'admin', '2023-05-21 01:27:42', '', NULL, '系统接口菜单'),
       (1109646780020166700, '操作日志', 1109645914907217900, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0',
        '0', 'monitor:operlog:list', 'form', 'admin', '2023-05-21 01:27:42', '', NULL, '操作日志菜单'),
       (1109646869740523500, '登录日志', 1109645914907217900, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0,
        'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2023-05-21 01:27:42', '', NULL,
        '登录日志菜单'),
       (1109647149341216800, '用户查询', 1109644589599428600, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647216504606700, '用户新增', 1109644589599428600, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647262209937400, '用户修改', 1109644589599428600, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647322234622000, '用户删除', 1109644589599428600, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647471073693700, '用户导出', 1109644589599428600, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647529655537700, '用户导入', 1109644589599428600, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647718713790500, '重置密码', 1109644589599428600, 7, '', '', '', 1, 0, 'F', '0', '0',
        'system:user:resetPwd', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647829921566700, '角色查询', 1109645168614707200, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647895998632000, '角色新增', 1109645168614707200, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109647948716838900, '角色修改', 1109645168614707200, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648004882763800, '角色删除', 1109645168614707200, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648056036495400, '角色导出', 1109645168614707200, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648145979150300, '菜单查询', 1109645280053170200, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648274626842600, '菜单新增', 1109645280053170200, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648333087051800, '菜单修改', 1109645280053170200, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648381824864300, '菜单删除', 1109645280053170200, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648422836768800, '部门查询', 1109645424991539200, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648471461335000, '部门新增', 1109645424991539200, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648518265573400, '部门修改', 1109645424991539200, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648560187641900, '部门删除', 1109645424991539200, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648634288410600, '岗位查询', 1109645568545787900, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648684171268100, '岗位新增', 1109645568545787900, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648728148545500, '岗位修改', 1109645568545787900, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648782905184300, '岗位删除', 1109645568545787900, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648823023702000, '岗位导出', 1109645568545787900, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109648997489971200, '字典查询', 1109645677182455800, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649046584299500, '字典新增', 1109645677182455800, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649105711403000, '字典修改', 1109645677182455800, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649156634447900, '字典删除', 1109645677182455800, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649197260476400, '字典导出', 1109645677182455800, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649246669377500, '参数查询', 1109645750964457500, 1, '#', '', '', 1, 0, 'F', '0', '0',
        'system:config:query', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649286724980700, '参数新增', 1109645750964457500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649332958793700, '参数修改', 1109645750964457500, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649379125497900, '参数删除', 1109645750964457500, 4, '#', '', '', 1, 0, 'F', '0', '0',
        'system:config:remove', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649420149985300, '参数导出', 1109645750964457500, 5, '#', '', '', 1, 0, 'F', '0', '0',
        'system:config:export', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649466396381200, '公告查询', 1109645839950811100, 1, '#', '', '', 1, 0, 'F', '0', '0',
        'system:notice:query', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649507592835100, '公告新增', 1109645839950811100, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649548462133200, '公告修改', 1109645839950811100, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649588719063000, '公告删除', 1109645839950811100, 4, '#', '', '', 1, 0, 'F', '0', '0',
        'system:notice:remove', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649878486749200, '操作查询', 1109646780020166700, 1, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:operlog:query', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649940910575600, '操作删除', 1109646780020166700, 2, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:operlog:remove', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109649988687892500, '日志导出', 1109646780020166700, 3, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:operlog:export', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650054110646300, '登录查询', 1109646869740523500, 1, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:logininfor:query', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650104379379700, '登录删除', 1109646869740523500, 2, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:logininfor:remove', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650171173670900, '日志导出', 1109646869740523500, 3, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:logininfor:export', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650229227032600, '账户解锁', 1109646869740523500, 4, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:logininfor:unlock', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650296889544700, '在线查询', 1109646040325296100, 1, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:online:query', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650356951978000, '批量强退', 1109646040325296100, 2, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:online:batchLogout', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650402246266900, '单条强退', 1109646040325296100, 3, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:online:forceLogout', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650456302456800, '任务查询', 1109646110235955200, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650500061630500, '任务新增', 1109646110235955200, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650539429367800, '任务修改', 1109646110235955200, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650585633820700, '任务删除', 1109646110235955200, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650633339834400, '状态修改', 1109646110235955200, 5, '#', '', '', 1, 0, 'F', '0', '0',
        'monitor:job:changeStatus', '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650688096473100, '任务导出', 1109646110235955200, 6, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650737174024200, '生成查询', 1109646561459179500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650818241531900, '生成修改', 1109646561459179500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#',
        'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650905181065200, '生成删除', 1109646561459179500, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650942988521500, '导入代码', 1109646561459179500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109650982326898700, '预览代码', 1109646561459179500, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview',
        '#', 'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109651025805054000, '生成代码', 1109646561459179500, 6, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#',
        'admin', '2023-05-21 01:27:42', '', NULL, ''),
       (1109815790540034000, '租户', 1109642713386586100, 1, 'tenant', 'system/tenant/index', NULL, 1, 0, 'C', '0', '0',
        'system:tenant:list', 'peoples', 'admin', '2023-05-21 12:13:58', 'admin', '2023-05-22 11:00:19', '租户菜单'),
       (1110159513291460600, '租户查询', 1109815790540034000, 1, '#', '', NULL, 1, 0, 'F', '0', '0',
        'system:tenant:query', '#', 'admin', '2023-05-21 12:13:58', '', NULL, ''),
       (1110159573722992600, '租户新增', 1109815790540034000, 2, '#', '', NULL, 1, 0, 'F', '0', '0',
        'system:tenant:add', '#', 'admin', '2023-05-21 12:13:58', '', NULL, ''),
       (1110159595126526000, '租户修改', 1109815790540034000, 3, '#', '', NULL, 1, 0, 'F', '0', '0',
        'system:tenant:edit', '#', 'admin', '2023-05-21 12:13:58', '', NULL, ''),
       (1110159617880625200, '租户删除', 1109815790540034000, 4, '#', '', NULL, 1, 0, 'F', '0', '0',
        'system:tenant:remove', '#', 'admin', '2023-05-21 12:13:58', '', NULL, ''),
       (1110159639120580600, '租户导出', 1109815790540034000, 5, '#', '', NULL, 1, 0, 'F', '0', '0',
        'system:tenant:export', '#', 'admin', '2023-05-21 12:13:58', '', NULL, '');
/*!40000 ALTER TABLE `sys_menu`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice`
(
    `notice_id`      bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `notice_title`   varchar(50) NOT NULL COMMENT '公告标题',
    `notice_type`    char(1)     NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` longblob COMMENT '公告内容',
    `status`         char(1)      DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_by`      varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    `remark`         varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`notice_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8 COMMENT ='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_notice`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oper_log`
(
    `oper_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `tenant_id`      bigint(20)    DEFAULT '0' COMMENT '租户id',
    `title`          varchar(50)   DEFAULT '' COMMENT '模块标题',
    `business_type`  int(2)        DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100)  DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10)   DEFAULT '' COMMENT '请求方式',
    `operator_type`  int(1)        DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50)   DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50)   DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255)  DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128)  DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255)  DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) DEFAULT '' COMMENT '返回参数',
    `status`         int(1)        DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime      DEFAULT NULL COMMENT '操作时间',
    `cost_time`      bigint(20)    DEFAULT '0' COMMENT '消耗时间',
    PRIMARY KEY (`oper_id`),
    KEY `idx_sys_oper_log_bt` (`business_type`),
    KEY `idx_sys_oper_log_s` (`status`),
    KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8 COMMENT ='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log`
    DISABLE KEYS */;
INSERT INTO `sys_oper_log`
VALUES (1, 0, '操作日志', 9, 'com.ruoyi.web.controller.monitor.SysOperlogController.clean()', 'DELETE', 1, 'xuqihang',
        NULL, '/monitor/operlog/clean', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL,
        '2023-05-24 15:22:54', 8);
/*!40000 ALTER TABLE `sys_oper_log`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post`
(
    `post_id`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `tenant_id`   bigint(20)  NOT NULL COMMENT '租户id',
    `post_code`   varchar(64) NOT NULL COMMENT '岗位编码',
    `post_name`   varchar(50) NOT NULL COMMENT '岗位名称',
    `post_sort`   int(4)      NOT NULL COMMENT '显示顺序',
    `status`      char(1)     NOT NULL COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`post_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1110943134856122369
  DEFAULT CHARSET = utf8 COMMENT ='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post`
    DISABLE KEYS */;
INSERT INTO `sys_post`
VALUES (1109513946722865200, 1109495825869967400, 'ceo', '董事长', 1, '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        ''),
       (1109513972387811300, 1109495825869967400, 'se', '项目经理', 2, '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        ''),
       (1109514000175075300, 1109495825869967400, 'hr', '人力资源', 3, '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        ''),
       (1109514023038226400, 1109495825869967400, 'user', '普通员工', 4, '0', 'admin', '2023-05-21 01:27:42', '', NULL,
        ''),
       (1110615087888076800, 1110270359690678274, '阿松大', '撒打算', 0, '0', 'xuqihang', '2023-05-23 17:07:51', '',
        NULL, '阿松大'),
       (1110943134856122368, 1110270359690678274, 'asdasd', 'asda', 0, '0', 'xuqihang', '2023-05-24 14:51:23', '', NULL,
        'asdasd');
/*!40000 ALTER TABLE `sys_post`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role`
(
    `role_id`             bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `tenant_id`           bigint(20)   NOT NULL COMMENT '租户id',
    `role_name`           varchar(30)  NOT NULL COMMENT '角色名称',
    `role_key`            varchar(100) NOT NULL COMMENT '角色权限字符串',
    `role_sort`           int(4)       NOT NULL COMMENT '显示顺序',
    `data_scope`          char(1)      DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限 5：仅本人数据权限）',
    `menu_check_strictly` tinyint(1)   DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
    `dept_check_strictly` tinyint(1)   DEFAULT '1' COMMENT '部门树选择项是否关联显示',
    `status`              char(1)      NOT NULL COMMENT '角色状态（0正常 1停用）',
    `del_flag`            char(1)      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`           varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`         datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`           varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`         datetime     DEFAULT NULL COMMENT '更新时间',
    `remark`              varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1110626941519663105
  DEFAULT CHARSET = utf8 COMMENT ='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role`
    DISABLE KEYS */;
INSERT INTO `sys_role`
VALUES (1109516320476631000, 1109495825869967400, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin',
        '2023-05-21 01:27:42', 'admin', '2023-05-24 14:32:00', '超级管理员'),
       (1109626885060563000, 1109495825869967400, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL, '普通角色'),
       (1110626941519663104, 1110270359690678274, 'ssss', 'aaaaaa', 0, '1', 1, 1, '0', '0', 'xuqihang',
        '2023-05-23 17:54:57', 'admin', '2023-05-24 14:35:46', NULL);
/*!40000 ALTER TABLE `sys_role`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
    PRIMARY KEY (`role_id`, `dept_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept`
    DISABLE KEYS */;
INSERT INTO `sys_role_dept`
VALUES (1109626885060563000, 1109506726979309600),
       (1109626885060563000, 1109506801340125200);
/*!40000 ALTER TABLE `sys_role_dept`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu`
    DISABLE KEYS */;
INSERT INTO `sys_role_menu`
VALUES (1109626885060563000, 1109642713386586100),
       (1109626885060563000, 1109643216606597100),
       (1109626885060563000, 1109644186531008500),
       (1109626885060563000, 1109644393796735000),
       (1109626885060563000, 1109644589599428600),
       (1109626885060563000, 1109645168614707200),
       (1109626885060563000, 1109645280053170200),
       (1109626885060563000, 1109645424991539200),
       (1109626885060563000, 1109645568545787900),
       (1109626885060563000, 1109645677182455800),
       (1109626885060563000, 1109645750964457500),
       (1109626885060563000, 1109645839950811100),
       (1109626885060563000, 1109645914907217900),
       (1109626885060563000, 1109646040325296100),
       (1109626885060563000, 1109646110235955200),
       (1109626885060563000, 1109646184722600000),
       (1109626885060563000, 1109646308517482500),
       (1109626885060563000, 1109646399336747000),
       (1109626885060563000, 1109646451186733000),
       (1109626885060563000, 1109646507931471900),
       (1109626885060563000, 1109646561459179500),
       (1109626885060563000, 1109646688722751500),
       (1109626885060563000, 1109646780020166700),
       (1109626885060563000, 1109646869740523500),
       (1109626885060563000, 1109647149341216800),
       (1109626885060563000, 1109647216504606700),
       (1109626885060563000, 1109647262209937400),
       (1109626885060563000, 1109647322234622000),
       (1109626885060563000, 1109647471073693700),
       (1109626885060563000, 1109647529655537700),
       (1109626885060563000, 1109647718713790500),
       (1109626885060563000, 1109647829921566700),
       (1109626885060563000, 1109647895998632000),
       (1109626885060563000, 1109647948716838900),
       (1109626885060563000, 1109648004882763800),
       (1109626885060563000, 1109648056036495400),
       (1109626885060563000, 1109648145979150300),
       (1109626885060563000, 1109648274626842600),
       (1109626885060563000, 1109648333087051800),
       (1109626885060563000, 1109648381824864300),
       (1109626885060563000, 1109648422836768800),
       (1109626885060563000, 1109648471461335000),
       (1109626885060563000, 1109648518265573400),
       (1109626885060563000, 1109648560187641900),
       (1109626885060563000, 1109648634288410600),
       (1109626885060563000, 1109648684171268100),
       (1109626885060563000, 1109648728148545500),
       (1109626885060563000, 1109648782905184300),
       (1109626885060563000, 1109648823023702000),
       (1109626885060563000, 1109648997489971200),
       (1109626885060563000, 1109649046584299500),
       (1109626885060563000, 1109649105711403000),
       (1109626885060563000, 1109649156634447900),
       (1109626885060563000, 1109649197260476400),
       (1109626885060563000, 1109649246669377500),
       (1109626885060563000, 1109649286724980700),
       (1109626885060563000, 1109649332958793700),
       (1109626885060563000, 1109649379125497900),
       (1109626885060563000, 1109649420149985300),
       (1109626885060563000, 1109649466396381200),
       (1109626885060563000, 1109649507592835100),
       (1109626885060563000, 1109649548462133200),
       (1109626885060563000, 1109649588719063000),
       (1109626885060563000, 1109649878486749200),
       (1109626885060563000, 1109649940910575600),
       (1109626885060563000, 1109649988687892500),
       (1109626885060563000, 1109650054110646300),
       (1109626885060563000, 1109650104379379700),
       (1109626885060563000, 1109650171173670900),
       (1109626885060563000, 1109650229227032600),
       (1109626885060563000, 1109650296889544700),
       (1109626885060563000, 1109650356951978000),
       (1109626885060563000, 1109650402246266900),
       (1109626885060563000, 1109650456302456800),
       (1109626885060563000, 1109650500061630500),
       (1109626885060563000, 1109650539429367800),
       (1109626885060563000, 1109650585633820700),
       (1109626885060563000, 1109650633339834400),
       (1109626885060563000, 1109650688096473100),
       (1109626885060563000, 1109650737174024200),
       (1109626885060563000, 1109650818241531900),
       (1109626885060563000, 1109650905181065200),
       (1109626885060563000, 1109650942988521500),
       (1109626885060563000, 1109650982326898700),
       (1109626885060563000, 1109651025805054000),
       (1110626941519663104, 1109642713386586100),
       (1110626941519663104, 1109643216606597100),
       (1110626941519663104, 1109644186531008500),
       (1110626941519663104, 1109644393796735000),
       (1110626941519663104, 1109644589599428600),
       (1110626941519663104, 1109645168614707200),
       (1110626941519663104, 1109645280053170200),
       (1110626941519663104, 1109645424991539200),
       (1110626941519663104, 1109645568545787900),
       (1110626941519663104, 1109645677182455800),
       (1110626941519663104, 1109645750964457500),
       (1110626941519663104, 1109645839950811100),
       (1110626941519663104, 1109645914907217900),
       (1110626941519663104, 1109646040325296100),
       (1110626941519663104, 1109646110235955200),
       (1110626941519663104, 1109646184722600000),
       (1110626941519663104, 1109646308517482500),
       (1110626941519663104, 1109646399336747000),
       (1110626941519663104, 1109646451186733000),
       (1110626941519663104, 1109646507931471900),
       (1110626941519663104, 1109646561459179500),
       (1110626941519663104, 1109646688722751500),
       (1110626941519663104, 1109646780020166700),
       (1110626941519663104, 1109646869740523500),
       (1110626941519663104, 1109647149341216800),
       (1110626941519663104, 1109647216504606700),
       (1110626941519663104, 1109647262209937400),
       (1110626941519663104, 1109647322234622000),
       (1110626941519663104, 1109647471073693700),
       (1110626941519663104, 1109647529655537700),
       (1110626941519663104, 1109647718713790500),
       (1110626941519663104, 1109647829921566700),
       (1110626941519663104, 1109647895998632000),
       (1110626941519663104, 1109647948716838900),
       (1110626941519663104, 1109648004882763800),
       (1110626941519663104, 1109648056036495400),
       (1110626941519663104, 1109648145979150300),
       (1110626941519663104, 1109648274626842600),
       (1110626941519663104, 1109648333087051800),
       (1110626941519663104, 1109648381824864300),
       (1110626941519663104, 1109648422836768800),
       (1110626941519663104, 1109648471461335000),
       (1110626941519663104, 1109648518265573400),
       (1110626941519663104, 1109648560187641900),
       (1110626941519663104, 1109648634288410600),
       (1110626941519663104, 1109648684171268100),
       (1110626941519663104, 1109648728148545500),
       (1110626941519663104, 1109648782905184300),
       (1110626941519663104, 1109648823023702000),
       (1110626941519663104, 1109648997489971200),
       (1110626941519663104, 1109649046584299500),
       (1110626941519663104, 1109649105711403000),
       (1110626941519663104, 1109649156634447900),
       (1110626941519663104, 1109649197260476400),
       (1110626941519663104, 1109649246669377500),
       (1110626941519663104, 1109649286724980700),
       (1110626941519663104, 1109649332958793700),
       (1110626941519663104, 1109649379125497900),
       (1110626941519663104, 1109649420149985300),
       (1110626941519663104, 1109649466396381200),
       (1110626941519663104, 1109649507592835100),
       (1110626941519663104, 1109649548462133200),
       (1110626941519663104, 1109649588719063000),
       (1110626941519663104, 1109649878486749200),
       (1110626941519663104, 1109649940910575600),
       (1110626941519663104, 1109649988687892500),
       (1110626941519663104, 1109650054110646300),
       (1110626941519663104, 1109650104379379700),
       (1110626941519663104, 1109650171173670900),
       (1110626941519663104, 1109650229227032600),
       (1110626941519663104, 1109650296889544700),
       (1110626941519663104, 1109650356951978000),
       (1110626941519663104, 1109650402246266900),
       (1110626941519663104, 1109650456302456800),
       (1110626941519663104, 1109650500061630500),
       (1110626941519663104, 1109650539429367800),
       (1110626941519663104, 1109650585633820700),
       (1110626941519663104, 1109650633339834400),
       (1110626941519663104, 1109650688096473100),
       (1110626941519663104, 1109650737174024200),
       (1110626941519663104, 1109650818241531900),
       (1110626941519663104, 1109650905181065200),
       (1110626941519663104, 1109650942988521500),
       (1110626941519663104, 1109650982326898700),
       (1110626941519663104, 1109651025805054000);
/*!40000 ALTER TABLE `sys_role_menu`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_tenant`
--

DROP TABLE IF EXISTS `sys_tenant`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_tenant`
(
    `tenant_id`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '租户id',
    `user_id`     bigint(20) NOT NULL COMMENT '用户id',
    `dept_id`     bigint(20) NOT NULL COMMENT '部门id',
    `order_num`   int(4)       DEFAULT '100' COMMENT '显示顺序',
    `remark`      varchar(255) DEFAULT NULL COMMENT '备注',
    `status`      char(1)      DEFAULT '0' COMMENT '租户状态（0正常 1停用）',
    `del_flag`    char(1)      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`tenant_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1109501449164951602
  DEFAULT CHARSET = utf8 COMMENT ='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_tenant`
--

LOCK TABLES `sys_tenant` WRITE;
/*!40000 ALTER TABLE `sys_tenant`
    DISABLE KEYS */;
INSERT INTO `sys_tenant`
VALUES (1109495825869967400, 1109511216780415000, 1109506726979309600, 0, '系统租户', '0', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL),
       (1109501449164951600, 1109511251509252100, 1109506801340125200, 0, '测试租户', '0', '0', 'admin',
        '2023-05-21 01:27:42', '', NULL),
       (1109501449164951601, 1110270359690678272, 1110270359690678273, 100, 'ces', '0', '0', '', '2023-05-22 18:18:02',
        '', NULL);
/*!40000 ALTER TABLE `sys_tenant`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user`
(
    `user_id`      bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `dept_id`      bigint(20)   DEFAULT NULL COMMENT '部门ID',
    `tenant_id`    bigint(20)  NOT NULL COMMENT '租户id',
    `user_name`    varchar(30) NOT NULL COMMENT '用户账号',
    `nick_name`    varchar(30) NOT NULL COMMENT '用户昵称',
    `user_type`    varchar(3)   DEFAULT '1' COMMENT '用户类型（999超级管理员）',
    `email`        varchar(50)  DEFAULT '' COMMENT '用户邮箱',
    `phone_number` varchar(11)  DEFAULT '' COMMENT '手机号码',
    `sex`          char(1)      DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar`       varchar(100) DEFAULT '' COMMENT '头像地址',
    `password`     varchar(100) DEFAULT '' COMMENT '密码',
    `status`       char(1)      DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag`     char(1)      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`     varchar(128) DEFAULT '' COMMENT '最后登录IP',
    `login_date`   datetime     DEFAULT NULL COMMENT '最后登录时间',
    `create_by`    varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    `remark`       varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1110270359690678273
  DEFAULT CHARSET = utf8 COMMENT ='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user`
    DISABLE KEYS */;
INSERT INTO `sys_user`
VALUES (1109511216780415000, 1109506726979309600, 1109495825869967400, 'admin', '若依', '999', 'ry@163.com',
        '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1',
        '2023-05-24 14:28:59', 'admin', '2023-05-21 01:27:42', '', '2023-05-24 14:28:59', '管理员'),
       (1109511251509252100, 1109506801340125200, 1109501449164951600, 'ry', '测试', '1', 'ry@qq.com', '15666666666',
        '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1',
        '2023-05-21 01:27:42', 'admin', '2023-05-21 01:27:42', 'admin', '2023-05-24 11:40:45', '测试员'),
       (1110270359690678272, 1110270359690678273, 1110270359690678274, 'xuqihang', 'xuqihang', '1', '', '18087759031',
        '0', '', '$2a$10$wfVxjNpRrBmtxkol80zqhelj2RINj9WpTKcvHRu9Tu/Sul8gpcE02', '0', '0', '127.0.0.1',
        '2023-05-24 14:29:39', '', '2023-05-22 18:18:05', 'xuqihang', '2023-05-24 15:18:33', NULL);
/*!40000 ALTER TABLE `sys_user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
    PRIMARY KEY (`user_id`, `post_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post`
    DISABLE KEYS */;
INSERT INTO `sys_user_post`
VALUES (1109511216780415000, 1109513946722865200),
       (1110270359690678272, 1110615087888076800),
       (1110270359690678272, 1110943134856122368);
/*!40000 ALTER TABLE `sys_user_post`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role`
    DISABLE KEYS */;
INSERT INTO `sys_user_role`
VALUES (1109511216780415000, 1109516320476631000),
       (1110270359690678272, 1110626941519663104);
/*!40000 ALTER TABLE `sys_user_role`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2023-05-24 15:23:43
