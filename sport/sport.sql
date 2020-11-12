/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.6.22-log : Database - rr_test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rr_test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rr_test`;

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_cron_triggers` */

insert  into `qrtz_cron_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`CRON_EXPRESSION`,`TIME_ZONE_ID`) values 

('RenrenScheduler','TASK_1','DEFAULT','0 0/30 * * * ?','Asia/Shanghai'),

('RenrenScheduler','TASK_2','DEFAULT','0 0/30 * * * ?','Asia/Shanghai'),

('RenrenScheduler','TASK_3','DEFAULT','0 0/30 * * * ?','Asia/Shanghai');

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_job_details` */

insert  into `qrtz_job_details`(`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`JOB_CLASS_NAME`,`IS_DURABLE`,`IS_NONCONCURRENT`,`IS_UPDATE_DATA`,`REQUESTS_RECOVERY`,`JOB_DATA`) values 

('RenrenScheduler','TASK_1','DEFAULT',NULL,'ScheduleJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0jJ�s@xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0'),

('RenrenScheduler','TASK_2','DEFAULT',NULL,'ScheduleJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0jO�Ҩxt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0'),

('RenrenScheduler','TASK_3','DEFAULT',NULL,'ScheduleJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0jO�5�xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0');

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values 

('RenrenScheduler','STATE_ACCESS'),

('RenrenScheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values 

('RenrenScheduler','DESKTOP-2UF0ODU1556696408760',1556701331841,15000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_triggers` */

insert  into `qrtz_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`NEXT_FIRE_TIME`,`PREV_FIRE_TIME`,`PRIORITY`,`TRIGGER_STATE`,`TRIGGER_TYPE`,`START_TIME`,`END_TIME`,`CALENDAR_NAME`,`MISFIRE_INSTR`,`JOB_DATA`) values 

('RenrenScheduler','TASK_1','DEFAULT','TASK_1','DEFAULT',NULL,1556703000000,1556701200000,5,'WAITING','CRON',1556029684000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0jJ�s@xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0'),

('RenrenScheduler','TASK_2','DEFAULT','TASK_2','DEFAULT',NULL,1556703000000,1556701200000,5,'WAITING','CRON',1556121837000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0jO�Ҩxt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0'),

('RenrenScheduler','TASK_3','DEFAULT','TASK_3','DEFAULT',NULL,1556703000000,1556701200000,5,'WAITING','CRON',1556121837000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0jO�5�xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0');

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务';

/*Data for the table `schedule_job` */

insert  into `schedule_job`(`job_id`,`bean_name`,`params`,`cron_expression`,`status`,`remark`,`create_time`) values 

(1,'testTask','renren','0 0/30 * * * ?',0,'参数测试','2019-04-23 22:09:12'),

(2,'testTask','renren','0 0/30 * * * ?',0,'参数测试','2019-04-24 23:28:57'),

(3,'testTask','renren','0 0/30 * * * ?',0,'参数测试','2019-04-24 23:34:50');

/*Table structure for table `schedule_job_log` */

DROP TABLE IF EXISTS `schedule_job_log`;

CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

/*Data for the table `schedule_job_log` */

insert  into `schedule_job_log`(`log_id`,`job_id`,`bean_name`,`params`,`status`,`error`,`times`,`create_time`) values 

(1,1,'testTask','renren',0,NULL,2,'2019-04-23 22:30:00'),

(2,1,'testTask','renren',0,NULL,14,'2019-04-23 23:00:00'),

(3,1,'testTask','renren',0,NULL,29,'2019-04-24 22:30:00'),

(4,1,'testTask','renren',0,NULL,10,'2019-04-24 23:00:00'),

(5,1,'testTask','renren',0,NULL,2,'2019-04-24 23:30:00'),

(6,1,'testTask','renren',0,NULL,4,'2019-04-25 00:30:00'),

(7,2,'testTask','renren',0,NULL,1,'2019-04-25 00:30:01'),

(8,3,'testTask','renren',0,NULL,1,'2019-04-25 00:30:01'),

(9,2,'testTask','renren',0,NULL,111,'2019-04-25 22:30:06'),

(10,1,'testTask','renren',0,NULL,102,'2019-04-25 22:30:06'),

(11,3,'testTask','renren',0,NULL,5,'2019-04-25 22:30:07'),

(12,1,'testTask','renren',0,NULL,5,'2019-04-25 23:00:00'),

(13,2,'testTask','renren',0,NULL,1,'2019-04-25 23:00:01'),

(14,3,'testTask','renren',0,NULL,3,'2019-04-25 23:00:01'),

(15,1,'testTask','renren',0,NULL,143,'2019-04-25 23:30:00'),

(16,2,'testTask','renren',0,NULL,67,'2019-04-25 23:30:01'),

(17,3,'testTask','renren',0,NULL,5,'2019-04-25 23:30:01'),

(18,3,'testTask','renren',0,NULL,764,'2019-04-26 00:00:02'),

(19,2,'testTask','renren',0,NULL,1111,'2019-04-26 00:00:01'),

(20,1,'testTask','renren',0,NULL,1692,'2019-04-26 00:00:01'),

(21,1,'testTask','renren',0,NULL,8,'2019-04-26 22:30:00'),

(22,2,'testTask','renren',0,NULL,5,'2019-04-26 22:30:00'),

(23,3,'testTask','renren',0,NULL,5,'2019-04-26 22:30:01'),

(24,1,'testTask','renren',0,NULL,4,'2019-04-26 23:00:00'),

(25,2,'testTask','renren',0,NULL,4,'2019-04-26 23:00:00'),

(26,3,'testTask','renren',0,NULL,1,'2019-04-26 23:00:01'),

(27,1,'testTask','renren',0,NULL,5,'2019-04-26 23:30:00'),

(28,2,'testTask','renren',0,NULL,1,'2019-04-26 23:30:01'),

(29,3,'testTask','renren',0,NULL,2,'2019-04-26 23:30:01'),

(30,1,'testTask','renren',0,NULL,211,'2019-04-27 00:00:00'),

(31,2,'testTask','renren',0,NULL,12,'2019-04-27 00:00:00'),

(32,3,'testTask','renren',0,NULL,5,'2019-04-27 00:00:01'),

(33,1,'testTask','renren',0,NULL,47,'2019-04-27 00:30:00'),

(34,2,'testTask','renren',0,NULL,18,'2019-04-27 00:30:02'),

(35,3,'testTask','renren',0,NULL,2,'2019-04-27 00:30:02'),

(36,1,'testTask','renren',0,NULL,56,'2019-04-27 01:00:00'),

(37,2,'testTask','renren',0,NULL,8,'2019-04-27 01:00:01'),

(38,3,'testTask','renren',0,NULL,3,'2019-04-27 01:00:01'),

(39,1,'testTask','renren',0,NULL,5,'2019-04-27 21:30:00'),

(40,2,'testTask','renren',0,NULL,0,'2019-04-27 21:30:01'),

(41,3,'testTask','renren',0,NULL,3,'2019-04-27 21:30:02'),

(42,1,'testTask','renren',0,NULL,7,'2019-04-27 22:00:00'),

(43,2,'testTask','renren',0,NULL,7,'2019-04-27 22:00:01'),

(44,3,'testTask','renren',0,NULL,0,'2019-04-27 22:00:01'),

(45,1,'testTask','renren',0,NULL,4,'2019-04-27 22:30:00'),

(46,2,'testTask','renren',0,NULL,8,'2019-04-27 22:30:01'),

(47,3,'testTask','renren',0,NULL,1,'2019-04-27 22:30:01'),

(48,1,'testTask','renren',0,NULL,4,'2019-04-27 23:00:00'),

(49,2,'testTask','renren',0,NULL,1,'2019-04-27 23:00:01'),

(50,3,'testTask','renren',0,NULL,1,'2019-04-27 23:00:01'),

(51,1,'testTask','renren',0,NULL,4,'2019-04-27 23:30:00'),

(52,2,'testTask','renren',0,NULL,2,'2019-04-27 23:30:00'),

(53,3,'testTask','renren',0,NULL,7,'2019-04-27 23:30:01'),

(54,1,'testTask','renren',0,NULL,113,'2019-04-28 00:00:01'),

(55,2,'testTask','renren',0,NULL,2,'2019-04-28 00:00:05'),

(56,3,'testTask','renren',0,NULL,1,'2019-04-28 00:00:06'),

(57,2,'testTask','renren',0,NULL,0,'2019-04-29 21:00:00'),

(58,1,'testTask','renren',0,NULL,0,'2019-04-29 21:00:00'),

(59,3,'testTask','renren',0,NULL,0,'2019-04-29 21:00:00'),

(60,1,'testTask','renren',0,NULL,1,'2019-04-29 21:30:00'),

(61,2,'testTask','renren',0,NULL,1,'2019-04-29 21:30:00'),

(62,3,'testTask','renren',0,NULL,1,'2019-04-29 21:30:00'),

(63,1,'testTask','renren',0,NULL,1,'2019-04-29 22:00:00'),

(64,2,'testTask','renren',0,NULL,0,'2019-04-29 22:00:00'),

(65,3,'testTask','renren',0,NULL,1,'2019-04-29 22:00:00'),

(66,2,'testTask','renren',0,NULL,1,'2019-04-29 22:30:00'),

(67,1,'testTask','renren',0,NULL,1,'2019-04-29 22:30:00'),

(68,3,'testTask','renren',0,NULL,1,'2019-04-29 22:30:00'),

(69,2,'testTask','renren',0,NULL,0,'2019-04-29 23:00:00'),

(70,1,'testTask','renren',0,NULL,0,'2019-04-29 23:00:00'),

(71,3,'testTask','renren',0,NULL,0,'2019-04-29 23:00:00'),

(72,1,'testTask','renren',0,NULL,1,'2019-04-29 23:30:00'),

(73,2,'testTask','renren',0,NULL,0,'2019-04-29 23:30:00'),

(74,3,'testTask','renren',0,NULL,0,'2019-04-29 23:30:00'),

(75,3,'testTask','renren',0,NULL,1,'2019-04-30 00:00:00'),

(76,2,'testTask','renren',0,NULL,2,'2019-04-30 00:00:00'),

(77,1,'testTask','renren',0,NULL,7,'2019-04-30 00:00:00'),

(78,2,'testTask','renren',0,NULL,1,'2019-04-30 20:00:00'),

(79,3,'testTask','renren',0,NULL,0,'2019-04-30 20:00:00'),

(80,1,'testTask','renren',0,NULL,2,'2019-04-30 20:00:00'),

(81,1,'testTask','renren',0,NULL,2,'2019-04-30 20:30:00'),

(82,2,'testTask','renren',0,NULL,0,'2019-04-30 20:30:00'),

(83,3,'testTask','renren',0,NULL,2,'2019-04-30 20:30:00'),

(84,1,'testTask','renren',0,NULL,1,'2019-04-30 21:00:00'),

(85,2,'testTask','renren',0,NULL,0,'2019-04-30 21:00:00'),

(86,3,'testTask','renren',0,NULL,1,'2019-04-30 21:00:00'),

(87,1,'testTask','renren',0,NULL,3,'2019-04-30 21:30:00'),

(88,2,'testTask','renren',0,NULL,2,'2019-04-30 21:30:00'),

(89,3,'testTask','renren',0,NULL,1,'2019-04-30 21:30:00'),

(90,1,'testTask','renren',0,NULL,2,'2019-05-01 09:30:00'),

(91,2,'testTask','renren',0,NULL,2,'2019-05-01 09:30:00'),

(92,3,'testTask','renren',0,NULL,1,'2019-05-01 09:30:00'),

(93,2,'testTask','renren',0,NULL,1,'2019-05-01 10:00:00'),

(94,1,'testTask','renren',0,NULL,2,'2019-05-01 10:00:00'),

(95,3,'testTask','renren',0,NULL,0,'2019-05-01 10:00:00'),

(96,2,'testTask','renren',0,NULL,0,'2019-05-01 10:30:00'),

(97,1,'testTask','renren',0,NULL,1,'2019-05-01 10:30:00'),

(98,3,'testTask','renren',0,NULL,0,'2019-05-01 10:30:00'),

(99,1,'testTask','renren',0,NULL,2,'2019-05-01 11:00:00'),

(100,2,'testTask','renren',0,NULL,1,'2019-05-01 11:00:00'),

(101,3,'testTask','renren',0,NULL,0,'2019-05-01 11:00:00'),

(102,1,'testTask','renren',0,NULL,2,'2019-05-01 11:30:00'),

(103,2,'testTask','renren',0,NULL,0,'2019-05-01 11:30:00'),

(104,3,'testTask','renren',0,NULL,0,'2019-05-01 11:30:00'),

(105,3,'testTask','renren',0,NULL,1,'2019-05-01 12:00:00'),

(106,2,'testTask','renren',0,NULL,1,'2019-05-01 12:00:00'),

(107,1,'testTask','renren',0,NULL,2,'2019-05-01 12:00:00'),

(108,1,'testTask','renren',0,NULL,7,'2019-05-01 12:30:00'),

(109,2,'testTask','renren',0,NULL,0,'2019-05-01 12:30:00'),

(110,3,'testTask','renren',0,NULL,1,'2019-05-01 12:30:00'),

(111,1,'testTask','renren',0,NULL,1,'2019-05-01 13:00:00'),

(112,2,'testTask','renren',0,NULL,1,'2019-05-01 13:00:00'),

(113,3,'testTask','renren',0,NULL,0,'2019-05-01 13:00:00'),

(114,1,'testTask','renren',0,NULL,2,'2019-05-01 13:30:00'),

(115,2,'testTask','renren',0,NULL,0,'2019-05-01 13:30:00'),

(116,3,'testTask','renren',0,NULL,0,'2019-05-01 13:30:00'),

(117,2,'testTask','renren',0,NULL,0,'2019-05-01 14:00:00'),

(118,1,'testTask','renren',0,NULL,1,'2019-05-01 14:00:00'),

(119,3,'testTask','renren',0,NULL,0,'2019-05-01 14:00:00'),

(120,2,'testTask','renren',0,NULL,1,'2019-05-01 14:30:00'),

(121,1,'testTask','renren',0,NULL,1,'2019-05-01 14:30:00'),

(122,3,'testTask','renren',0,NULL,0,'2019-05-01 14:30:00'),

(123,1,'testTask','renren',0,NULL,1,'2019-05-01 15:00:00'),

(124,2,'testTask','renren',0,NULL,1,'2019-05-01 15:00:00'),

(125,3,'testTask','renren',0,NULL,0,'2019-05-01 15:00:00'),

(126,1,'testTask','renren',0,NULL,1,'2019-05-01 15:30:00'),

(127,2,'testTask','renren',0,NULL,0,'2019-05-01 15:30:00'),

(128,3,'testTask','renren',0,NULL,0,'2019-05-01 15:30:00'),

(129,2,'testTask','renren',0,NULL,0,'2019-05-01 16:00:00'),

(130,1,'testTask','renren',0,NULL,1,'2019-05-01 16:00:00'),

(131,3,'testTask','renren',0,NULL,0,'2019-05-01 16:00:00'),

(132,1,'testTask','renren',0,NULL,1,'2019-05-01 16:30:00'),

(133,2,'testTask','renren',0,NULL,0,'2019-05-01 16:30:00'),

(134,3,'testTask','renren',0,NULL,1,'2019-05-01 16:30:00'),

(135,1,'testTask','renren',0,NULL,1,'2019-05-01 17:00:00'),

(136,2,'testTask','renren',0,NULL,0,'2019-05-01 17:00:00'),

(137,3,'testTask','renren',0,NULL,1,'2019-05-01 17:00:00');

/*Table structure for table `sport_area` */

DROP TABLE IF EXISTS `sport_area`;

CREATE TABLE `sport_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `area_name` varchar(32) DEFAULT NULL COMMENT '区域',
  `level` int(11) DEFAULT NULL COMMENT '级别 1：省 2：市',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sport_area` */

insert  into `sport_area`(`id`,`parent_id`,`area_name`,`level`,`create_time`,`update_time`) values 

(1,0,'北京市',1,'2019-05-01 12:20:04','2019-05-01 12:20:08');

/*Table structure for table `sport_bmi_config` */

DROP TABLE IF EXISTS `sport_bmi_config`;

CREATE TABLE `sport_bmi_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `norm_weight_range` varchar(256) DEFAULT NULL COMMENT '标准范围',
  `low_weight_range` varchar(256) DEFAULT NULL COMMENT '低重范围',
  `over_weight_range` varchar(256) DEFAULT NULL COMMENT '超重范围',
  `fat_weight_range` varchar(256) DEFAULT NULL COMMENT '肥胖范围',
  `min_age` int(11) DEFAULT NULL COMMENT '年龄段',
  `max_age` int(11) DEFAULT NULL COMMENT '年龄段',
  `gender` int(4) DEFAULT NULL COMMENT '性别1：男 2：女',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='年龄段体重标准';

/*Data for the table `sport_bmi_config` */

insert  into `sport_bmi_config`(`id`,`norm_weight_range`,`low_weight_range`,`over_weight_range`,`fat_weight_range`,`min_age`,`max_age`,`gender`,`remark`,`create_time`,`update_time`) values 

(1,'15.57-20.29','14.52','20.30-21.12','21.13',3,4,1,NULL,NULL,'2019-04-25 19:34:25'),

(2,'15.42-18.65','14.79','18.66-21.98','21.99',3,4,2,NULL,NULL,'2019-04-25 19:34:25'),

(3,'15.57-20.29','14.52','20.30-21.12','21.13',4,5,1,NULL,NULL,'2019-04-25 19:34:25'),

(4,'15.42-18.65','14.79','18.66-21.98','21.99',4,5,2,NULL,NULL,'2019-04-25 19:34:25'),

(5,'15.57-20.29','14.52','20.30-21.12','21.13',5,6,1,NULL,NULL,'2019-04-25 19:34:25'),

(6,'15.42-18.65','14.79','18.66-21.98','21.99',5,6,2,NULL,NULL,'2019-04-25 19:34:25'),

(7,'15.57-20.29','14.52','20.30-21.12','21.13',6,7,1,NULL,NULL,'2019-04-25 19:34:25'),

(8,'15.42-18.65','14.79','18.66-21.98','21.99',6,7,2,NULL,NULL,'2019-04-25 19:34:25'),

(9,'13.5-18.1','13.4','18.2-20.3','20.4',7,8,1,NULL,NULL,'2019-04-25 19:34:25'),

(10,'13.3-17.3','13.2','17.4-19.2','19.3',7,8,2,NULL,NULL,'2019-04-25 19:34:25'),

(11,'13.7-18.4','13.6','18.5-20.4','20.5',8,9,1,NULL,NULL,'2019-04-25 19:34:25'),

(12,'13.5-17.8','13.4','17.9-20.2','20.3',8,9,2,NULL,NULL,'2019-04-25 19:34:25'),

(13,'13.9-19.4','13.8','19.5-22.1','22.2',9,10,1,NULL,NULL,'2019-04-25 19:34:25'),

(14,'13.6-18.6','13.5','18.7-21.1','21.2',9,10,2,NULL,NULL,'2019-04-25 19:34:25'),

(15,'14.2-20.1','14.1','20.2-22.6','22.7',10,11,1,NULL,NULL,'2019-04-25 19:36:33'),

(16,'13.7-19.4','13.6','19.5-22.0','22.1',11,12,2,NULL,NULL,'2019-04-25 19:36:33'),

(17,'14.4-21.4','14.3','21.5-24.1','24.2',12,13,1,NULL,NULL,'2019-04-25 19:36:33'),

(18,'13.8-20.5','13.7','20.6-22.9','23.0',13,14,2,NULL,NULL,'2019-04-25 19:36:33');

/*Table structure for table `sport_bmi_grade` */

DROP TABLE IF EXISTS `sport_bmi_grade`;

CREATE TABLE `sport_bmi_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` int(11) DEFAULT NULL COMMENT '学号',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `age_range` varchar(16) DEFAULT NULL COMMENT '年龄段',
  `teacher_name` varchar(16) DEFAULT NULL COMMENT '测试员',
  `height` decimal(8,2) DEFAULT NULL COMMENT '身高/cm',
  `weight` decimal(8,2) DEFAULT NULL COMMENT '体重/kg',
  `check_time` datetime DEFAULT NULL COMMENT '检测时间',
  `score` varchar(32) DEFAULT NULL COMMENT '综合评分',
  `suggestion` varchar(256) DEFAULT NULL COMMENT '评测建议',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='BMI成绩表';

/*Data for the table `sport_bmi_grade` */

insert  into `sport_bmi_grade`(`id`,`student_id`,`age`,`age_range`,`teacher_name`,`height`,`weight`,`check_time`,`score`,`suggestion`,`remark`,`create_time`,`update_time`) values 

(1,1,4,'3-5','test',180.00,70.00,'2019-04-29 00:00:00','10','你这一项的成绩一般，课堂上多练习快速反应能力，变向跑，连续跳栏架等，提升下肢力量和动作频率\r\n',NULL,'2019-04-29 22:47:11','2019-05-01 15:16:26'),

(2,1,4,'3-5','test',180.00,70.00,'2019-04-29 00:00:00','5','你这一项的成绩一般，课堂上多练习快速反应能力，变向跑，连续跳栏架等，提升下肢力量和动作频率\r\n',NULL,'2019-04-29 22:50:20','2019-05-01 15:16:28'),

(3,1,4,'3-5','test',180.00,70.00,'2019-04-29 23:31:19','5','你这一项的成绩一般，课堂上多练习快速反应能力，变向跑，连续跳栏架等，提升下肢力量和动作频率\r\n',NULL,'2019-04-29 23:31:22','2019-05-01 15:16:33');

/*Table structure for table `sport_project` */

DROP TABLE IF EXISTS `sport_project`;

CREATE TABLE `sport_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_type` varchar(256) DEFAULT NULL COMMENT '项目类型',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编码',
  `project_name` varchar(48) DEFAULT NULL COMMENT '项目名称',
  `min_age` int(11) DEFAULT NULL COMMENT '最小年龄',
  `max_age` int(11) DEFAULT NULL COMMENT '最大年龄',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='项目';

/*Data for the table `sport_project` */

insert  into `sport_project`(`id`,`project_type`,`project_code`,`project_name`,`min_age`,`max_age`,`remark`,`create_time`,`update_time`) values 

(1,'速度','speed','10米折返跑\n（秒）',3,5,NULL,NULL,'2019-04-27 00:39:42'),

(2,'下肢爆发','low_strength','立定跳远\n（厘米）',3,12,NULL,NULL,'2019-04-27 00:39:50'),

(3,'上肢力量','upper_strength','网球投远\n（米）',3,8,NULL,NULL,'2019-04-27 00:39:54'),

(4,'柔韧','flexibility','坐位体前屈\n（厘米）',3,12,NULL,NULL,'2019-04-27 00:40:02'),

(5,'灵敏','sensitive','走平衡木\n（秒）',3,5,NULL,NULL,'2019-04-29 21:23:17'),

(6,'心肺耐力','cardiopulmonary','1min跳绳（次）',6,12,NULL,NULL,'2019-04-27 00:40:09'),

(7,'核心力量','core_strength','仰卧起坐（次）',9,12,NULL,NULL,'2019-04-27 00:40:12'),

(8,'上肢力量','upper_strength','前推实心球\n（米)',6,12,NULL,NULL,'2019-04-27 00:40:23'),

(9,'速度','speed','25米折返跑\n（秒）',6,12,NULL,NULL,'2019-04-27 00:40:42'),

(10,'tgmd3_check','tgmd3_check','25米折返跑\n（秒）',6,12,NULL,NULL,'2019-04-27 00:40:58');

/*Table structure for table `sport_project_config` */

DROP TABLE IF EXISTS `sport_project_config`;

CREATE TABLE `sport_project_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `train_goal` varchar(256) DEFAULT NULL COMMENT '训练目标',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `project_name` varchar(48) DEFAULT NULL COMMENT '训练项目',
  `min_score` decimal(16,2) DEFAULT NULL COMMENT '分值范围最低值',
  `max_score` decimal(16,2) DEFAULT NULL COMMENT '分值范围最高值',
  `score_level` decimal(16,0) DEFAULT NULL COMMENT '综合评分级别',
  `min_age` int(8) DEFAULT NULL COMMENT '年龄段小端',
  `max_age` int(8) DEFAULT NULL COMMENT '年龄段大端',
  `gender` int(4) DEFAULT NULL COMMENT '性别',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='训练项目';

/*Data for the table `sport_project_config` */

insert  into `sport_project_config`(`id`,`train_goal`,`project_id`,`project_name`,`min_score`,`max_score`,`score_level`,`min_age`,`max_age`,`gender`,`remark`,`create_time`,`update_time`) values 

(1,'速度',1,'10米折返跑',0.00,8.00,5,3,5,1,NULL,NULL,'2019-04-26 10:59:51'),

(2,'速度',1,'10米折返跑',8.00,9.00,4,3,5,1,NULL,NULL,'2019-04-26 10:59:52'),

(3,'速度',1,'10米折返跑',9.10,10.20,3,3,5,1,NULL,NULL,'2019-04-26 10:59:56'),

(4,'速度',1,'10米折返跑',10.30,12.80,2,3,5,1,NULL,NULL,'2019-04-26 10:59:57'),

(5,'速度',1,'10米折返跑',12.90,15.80,1,3,5,1,NULL,NULL,'2019-04-26 10:59:59'),

(6,'速度',1,'10米折返跑',15.90,99.00,0,3,5,1,NULL,NULL,'2019-04-26 11:00:24'),

(7,'下肢爆发',2,'立定跳远',76.00,760.00,5,3,5,1,NULL,NULL,'2019-04-26 23:50:54'),

(8,'下肢爆发',2,'立定跳远',59.00,76.00,4,3,5,1,NULL,NULL,'2019-04-26 23:50:55'),

(9,'下肢爆发',2,'立定跳远',43.00,58.00,3,3,5,1,NULL,NULL,'2019-04-26 23:50:56'),

(10,'下肢爆发',2,'立定跳远',30.00,42.00,2,3,5,1,NULL,NULL,'2019-04-26 23:50:57'),

(11,'下肢爆发',2,'立定跳远',21.00,29.00,1,3,5,1,NULL,NULL,'2019-04-26 23:50:58'),

(12,'下肢爆发',2,'立定跳远',0.00,20.00,0,3,5,1,NULL,NULL,'2019-04-26 23:50:59');

/*Table structure for table `sport_project_grade` */

DROP TABLE IF EXISTS `sport_project_grade`;

CREATE TABLE `sport_project_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` int(11) DEFAULT NULL COMMENT '学原ID',
  `stu_grade_id` int(11) DEFAULT NULL COMMENT '学生成绩ID',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `age_range` varchar(16) DEFAULT NULL COMMENT '年龄段',
  `teacher_name` varchar(16) DEFAULT NULL COMMENT '测试员',
  `project_id` int(8) DEFAULT NULL COMMENT '项目ID',
  `project_grade` decimal(8,2) DEFAULT NULL COMMENT '项目测试成绩',
  `check_time` datetime DEFAULT NULL COMMENT '检测时间',
  `score` int(4) DEFAULT NULL COMMENT '综合评分',
  `suggestion` varchar(256) DEFAULT NULL COMMENT '评测建议',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='项目成绩表';

/*Data for the table `sport_project_grade` */

insert  into `sport_project_grade`(`id`,`student_id`,`stu_grade_id`,`age`,`age_range`,`teacher_name`,`project_id`,`project_grade`,`check_time`,`score`,`suggestion`,`remark`,`create_time`,`update_time`) values 

(2,1,1,4,'3-5','test',1,15.00,'2019-04-29 00:00:00',1,'你这一项的成绩较差，课堂上多练习深蹲，连续跳跃，小步跑，高抬腿跑等，提升下肢力量和动作频率',NULL,'2019-04-29 22:50:20','2019-05-01 11:38:56'),

(3,1,1,4,'3-5','test',2,321.00,'2019-04-29 00:00:00',5,'你这一项成绩达到满分！请保持出勤让成绩变得更加优秀\r\n',NULL,'2019-04-29 22:50:20','2019-05-01 11:38:57'),

(4,1,1,4,'3-5','test',3,12.00,'2019-04-29 00:00:00',2,'你这一项的成绩较差，课堂上多练习深蹲，连续跳跃，小步跑，高抬腿跑等，提升下肢力量和动作频率',NULL,'2019-04-29 22:50:20','2019-05-01 11:38:58'),

(5,1,1,4,'3-5','test',4,32.00,'2019-04-29 00:00:00',0,'你这一项的成绩较差，课堂上多练习深蹲，连续跳跃，小步跑，高抬腿跑等，提升下肢力量和动作频率',NULL,'2019-04-29 22:50:20','2019-05-01 11:38:59'),

(6,1,1,4,'3-5','test',5,10.00,'2019-04-29 00:00:00',3,'你这一项的成绩一般，课堂上多练习快速反应能力，变向跑，连续跳栏架等，提升下肢力量和动作频率\r\n',NULL,'2019-04-29 22:50:20','2019-05-01 11:39:00'),

(7,1,2,4,'3-5','test',1,12.00,'2019-04-29 23:31:19',2,'你这一项的成绩较差，课堂上多练习深蹲，连续跳跃，小步跑，高抬腿跑等，提升下肢力量和动作频率',NULL,'2019-04-29 23:31:37','2019-05-01 11:39:15'),

(8,1,2,4,'3-5','test',2,322.00,'2019-04-29 23:31:19',5,'你这一项成绩达到满分！请保持出勤让成绩变得更加优秀\r\n',NULL,'2019-04-29 23:31:37','2019-05-01 11:39:16'),

(9,1,2,4,'3-5','test',3,12.00,'2019-04-29 23:31:19',2,'你这一项的成绩较差，课堂上多练习深蹲，连续跳跃，小步跑，高抬腿跑等，提升下肢力量和动作频率',NULL,'2019-04-29 23:31:37','2019-05-01 11:39:17'),

(10,1,2,4,'3-5','test',4,32.00,'2019-04-29 23:31:19',0,'你这一项的成绩较差，课堂上多练习深蹲，连续跳跃，小步跑，高抬腿跑等，提升下肢力量和动作频率',NULL,'2019-04-29 23:31:37','2019-05-01 11:39:18'),

(11,1,2,4,'3-5','test',5,10.00,'2019-04-29 23:31:19',3,'你这一项的成绩一般，课堂上多练习快速反应能力，变向跑，连续跳栏架等，提升下肢力量和动作频率\r\n',NULL,'2019-04-29 23:31:37','2019-05-01 11:39:35');

/*Table structure for table `sport_school` */

DROP TABLE IF EXISTS `sport_school`;

CREATE TABLE `sport_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级小区',
  `school_name` varchar(32) DEFAULT NULL COMMENT '学校名称',
  `school_code` varchar(32) NOT NULL COMMENT '学校编码',
  `province` varchar(32) DEFAULT NULL COMMENT '学校所在省',
  `city` varchar(32) DEFAULT NULL COMMENT '学校所在市',
  `address` varchar(256) DEFAULT NULL COMMENT '学校详细地址',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sport_school` */

insert  into `sport_school`(`id`,`parent_id`,`school_name`,`school_code`,`province`,`city`,`address`,`remark`,`create_time`,`update_time`) values 

(1,0,'望京分校','1000001','北京','北京','北京市朝阳区',NULL,'2019-05-01 14:50:39','2019-05-01 14:50:43');

/*Table structure for table `sport_score_suggestion` */

DROP TABLE IF EXISTS `sport_score_suggestion`;

CREATE TABLE `sport_score_suggestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `train_project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `min_score` decimal(16,2) DEFAULT NULL COMMENT '评分范围',
  `max_score` decimal(16,2) DEFAULT NULL,
  `suggestion` varchar(256) DEFAULT NULL COMMENT '建议',
  `gender` int(4) DEFAULT NULL COMMENT '性别1：男 2：女',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='评分建议表';

/*Data for the table `sport_score_suggestion` */

insert  into `sport_score_suggestion`(`id`,`train_project_id`,`min_score`,`max_score`,`suggestion`,`gender`,`remark`,`create_time`,`update_time`) values 

(1,1,0.00,2.00,'你这一项的成绩较差，课堂上多练习深蹲，连续跳跃，小步跑，高抬腿跑等，提升下肢力量和动作频率',NULL,NULL,NULL,'2019-04-24 16:44:38'),

(2,1,3.00,3.00,'你这一项的成绩一般，课堂上多练习快速反应能力，变向跑，连续跳栏架等，提升下肢力量和动作频率\r\n',NULL,NULL,NULL,'2019-04-24 16:55:51'),

(3,1,4.00,4.00,'你这一项的成绩较为良好，课堂上可以多练习深蹲、跳栏架、绳梯动作、折返跑等，提升下肢力量和动作频率',NULL,NULL,NULL,'2019-04-24 16:56:10'),

(4,1,5.00,5.00,'你这一项成绩达到满分！请保持出勤让成绩变得更加优秀\r\n',NULL,NULL,NULL,'2019-04-24 16:56:27'),

(5,3,0.00,2.00,'你这一项的成绩较差，课堂上多练习猫爬，蜘蛛爬，螃蟹爬等，提升上肢力量和关节支撑',NULL,NULL,NULL,'2019-04-24 16:58:27'),

(6,3,3.00,3.00,'你这一项的成绩一般，课堂上多练习爬行类动作，平板支撑，单杠悬挂等，提升上肢力量和关节支撑',NULL,NULL,NULL,'2019-04-24 16:58:31'),

(7,3,4.00,4.00,'你这一项的成绩较为良好，课堂上多练习爬行动作模式，俯卧撑，投掷类动作，提升上肢力量和关节支撑',NULL,NULL,NULL,'2019-04-24 16:58:35'),

(8,3,5.00,5.00,'你这一项成绩达到满分！请保持出勤让成绩变得更加优秀',NULL,NULL,NULL,'2019-04-24 16:58:38'),

(9,5,0.00,2.00,'你这一项的成绩较差，课堂上多练习变向跑，绳梯训练，折返跑，绕桩跑等，提升身体灵活性和关节稳定性',NULL,NULL,NULL,'2019-04-24 16:59:31'),

(10,5,3.00,3.00,'你这一项的成绩一般，课堂上多练习上下肢关节力量，快速反应能力，急停急起，提升身体灵活性和关节稳定性',NULL,NULL,NULL,'2019-04-24 16:59:34'),

(11,5,4.00,4.00,'你这一项的成绩较为良好，课堂上更加提高专注度，不仅使身体更加灵活同时让大脑也提高快速反应能力',NULL,NULL,NULL,'2019-04-24 16:59:36'),

(12,5,5.00,5.00,'你这一项成绩达到满分！请保持出勤让成绩变得更加优秀\r\n',NULL,NULL,NULL,'2019-04-24 16:59:39'),

(13,4,0.00,2.00,'你这一项的成绩较差，课堂上多练习弓箭步走，直腿摸脚尖等，提升肌肉和韧带的伸展性和弹性\r\n',NULL,NULL,NULL,'2019-04-24 17:01:03'),

(14,4,3.00,3.00,'你这一项的成绩一般，课堂上多练习弓箭步走，做好课后拉伸放松，提升肌肉和韧带的伸展性和弹性',NULL,NULL,NULL,'2019-04-24 17:01:04'),

(15,4,4.00,4.00,'你这一项的成绩较为良好，课堂上保持积极的训练，在课后拉伸上更认真对待，提升肌肉和韧带的伸展性和弹性',NULL,NULL,NULL,'2019-04-24 17:01:04'),

(16,4,5.00,5.00,'你这一项成绩达到满分！请保持出勤让成绩变得更加优秀',NULL,NULL,NULL,'2019-04-24 17:01:06');

/*Table structure for table `sport_student` */

DROP TABLE IF EXISTS `sport_student`;

CREATE TABLE `sport_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stu_number` varchar(32) DEFAULT NULL COMMENT '学号',
  `mobile` varchar(16) DEFAULT NULL COMMENT '联系方式',
  `realname` varchar(24) DEFAULT NULL COMMENT '学生姓名',
  `gender` int(1) DEFAULT NULL COMMENT '性别：1：男 2：女',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `family_name` varchar(16) DEFAULT NULL COMMENT '家长姓名',
  `family_mobile` varchar(16) DEFAULT NULL COMMENT '家长联系方式',
  `province` varchar(32) DEFAULT NULL COMMENT '省份',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `address` varchar(256) DEFAULT NULL COMMENT '详细地址',
  `school_id` int(11) DEFAULT NULL COMMENT '校区ID',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间/报名时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学员信息表';

/*Data for the table `sport_student` */

insert  into `sport_student`(`id`,`stu_number`,`mobile`,`realname`,`gender`,`birthday`,`family_name`,`family_mobile`,`province`,`city`,`address`,`school_id`,`remark`,`register_time`,`create_time`,`update_time`) values 

(1,'10000001','13533455432','test',1,'2015-01-01','aaaaaa','232323131',NULL,NULL,NULL,1,'r','2019-04-24 19:30:28',NULL,'2019-05-01 14:01:20'),

(2,'100002','13520398355','蟑螂',1,'2019-04-25','test','13520398355',NULL,NULL,NULL,1,'test','2019-04-25 00:00:00',NULL,'2019-05-01 14:01:23');

/*Table structure for table `sport_student_grade` */

DROP TABLE IF EXISTS `sport_student_grade`;

CREATE TABLE `sport_student_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` int(11) DEFAULT NULL COMMENT '学号',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `age_range` varchar(16) DEFAULT NULL COMMENT '年龄段',
  `teacher_name` varchar(16) DEFAULT NULL COMMENT '测试员',
  `height` decimal(8,2) DEFAULT NULL COMMENT '身高/cm',
  `weight` decimal(8,2) DEFAULT NULL COMMENT '体重/kg',
  `speed` decimal(8,2) DEFAULT NULL COMMENT '速度 m/s',
  `low_strength` decimal(8,2) DEFAULT NULL COMMENT '下肢力量',
  `upper_strength` decimal(8,2) DEFAULT NULL COMMENT '上肢力量',
  `flexibility` decimal(8,2) DEFAULT NULL COMMENT '柔韧性',
  `cardiopulmonary` decimal(8,2) DEFAULT NULL COMMENT '心肺能力',
  `core_strength` decimal(8,2) DEFAULT NULL COMMENT '核心力量',
  `tgmd3_check` decimal(8,2) DEFAULT NULL COMMENT 'TGMD-3测试',
  `sensitivity` decimal(8,2) DEFAULT NULL COMMENT '灵敏度',
  `check_time` datetime DEFAULT NULL COMMENT '检测时间',
  `score` varchar(32) DEFAULT NULL COMMENT '综合评分',
  `train_hours` decimal(8,2) DEFAULT NULL COMMENT '训练课时',
  `attendance` decimal(8,2) DEFAULT NULL COMMENT '出勤率',
  `suggestion` varchar(32) DEFAULT NULL COMMENT '评测建议',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='学员成绩表';

/*Data for the table `sport_student_grade` */

insert  into `sport_student_grade`(`id`,`student_id`,`age`,`age_range`,`teacher_name`,`height`,`weight`,`speed`,`low_strength`,`upper_strength`,`flexibility`,`cardiopulmonary`,`core_strength`,`tgmd3_check`,`sensitivity`,`check_time`,`score`,`train_hours`,`attendance`,`suggestion`,`remark`,`create_time`,`update_time`) values 

(1,1,4,'3-5','test',180.00,70.00,15.00,NULL,NULL,32.00,NULL,NULL,NULL,NULL,'2019-04-29 00:00:00',NULL,10.00,60.00,NULL,NULL,'2019-04-29 22:47:13','2019-05-01 13:07:34'),

(2,1,4,'3-5','test',180.00,70.00,15.00,NULL,NULL,32.00,NULL,NULL,NULL,NULL,'2019-04-29 00:00:00',NULL,8.00,50.00,NULL,NULL,'2019-04-29 22:50:24','2019-05-01 13:07:40');

/*Table structure for table `sport_train_goal` */

DROP TABLE IF EXISTS `sport_train_goal`;

CREATE TABLE `sport_train_goal` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `train_goal` varchar(256) DEFAULT NULL COMMENT '训练目标',
  `min_age` int(11) DEFAULT NULL COMMENT '年龄段',
  `max_age` int(11) DEFAULT NULL COMMENT '年龄段',
  `gender` int(4) DEFAULT NULL COMMENT '性别',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='年龄段训练目标';

/*Data for the table `sport_train_goal` */

insert  into `sport_train_goal`(`id`,`train_goal`,`min_age`,`max_age`,`gender`,`remark`,`create_time`,`update_time`) values 

(1,'该阶段通过趣味性团体训练着重发展孩子的社交能力和规则意识，通过特有的主题技巧练习，锻炼孩子的身体素质和建立动作模式，同时发展孩子大肌肉群和身体控制能力，通过模仿情景的教学方式培养孩子的专注力，以日后的学习打下基础',3,4,NULL,NULL,NULL,'2019-04-25 19:39:34'),

(2,'该阶段课程注重锻炼孩子在活动中的规则意识，团队协作以及更富有挑战性的运动技能，让孩子在集体环境中展现自我，获得自信，通过滚翻、翻越等基础体操跑酷技能学习，提升孩子力量协调灵敏基础素质。',4,5,NULL,NULL,NULL,'2019-04-25 19:39:36'),

(3,'该阶段强调运动技能学习和基础动作模式的完善，通过球类和身体控制类基础运动技能提升孩子的速度、灵敏、力量等素质，接触多种运动技能。培养孩子竞争意识，团队意识和运动过程中自我保护意识。',5,7,NULL,NULL,NULL,'2019-04-25 19:39:46'),

(4,'该阶段强调掌握多种运动技能和完善动作模式，该年龄段是提升速度、柔韧、灵敏、基础力量的黄金时期，通过多种运动技能和功能性训练让孩子在运动能力上全面发展。培养孩子坚韧、自信、热爱运动等意志品质。',7,9,NULL,NULL,NULL,'2019-04-25 19:39:53'),

(5,'该阶段强调全面提升孩子的运动能力，学习体操、跑酷、篮球、足球多样的运动技能，通过加拿大周期训练手段（LTAD）全面提高孩子力量、速度、灵敏、爆发力等素质，保持孩子对运动的热爱，预防运动损伤，培养运动品质。',9,12,NULL,NULL,NULL,'2019-04-25 19:39:56');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`param_key`,`param_value`,`status`,`remark`) values 

(1,'CLOUD_STORAGE_CONFIG_KEY','{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',0,'云存储配置信息');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门管理';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`dept_id`,`parent_id`,`name`,`order_num`,`del_flag`) values 

(1,0,'特工集团',0,0),

(2,1,'长沙分公司',1,0),

(3,1,'上海分公司',2,0),

(4,3,'技术部',0,0),

(5,3,'销售部',1,0);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `type` varchar(100) NOT NULL COMMENT '字典类型',
  `code` varchar(100) NOT NULL COMMENT '字典码',
  `value` varchar(1000) NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`type`,`code`,`value`,`order_num`,`remark`,`del_flag`) values 

(1,'性别','gender','2','女',0,NULL,0),

(2,'性别','gender','1','男',1,NULL,0),

(3,'性别','gender','0','未知',3,NULL,0);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`username`,`operation`,`method`,`params`,`time`,`ip`,`create_date`) values 

(1,'admin','修改菜单','com.sport.modules.sys.controller.SysMenuController.update()','{\"menuId\":41,\"parentId\":41,\"parentName\":\"体育管理系统\",\"name\":\"体育管理系统\",\"url\":\"\",\"type\":0,\"icon\":\"fa fa-file-code-o\",\"orderNum\":6}',43,'0:0:0:0:0:0:0:1','2019-04-25 16:16:24'),

(2,'admin','修改菜单','com.sport.modules.sys.controller.SysMenuController.update()','{\"menuId\":42,\"parentId\":41,\"parentName\":\"体育管理系统\",\"name\":\"年龄段体重标准\",\"url\":\"modules/sport/bmiconfig.html\",\"type\":1,\"icon\":\"fa fa-file-code-o\",\"orderNum\":6}',80,'0:0:0:0:0:0:0:1','2019-04-25 16:16:58'),

(3,'admin','修改菜单','com.sport.modules.sys.controller.SysMenuController.update()','{\"menuId\":41,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"体育管理系统\",\"url\":\"\",\"type\":0,\"icon\":\"fa fa-file-code-o\",\"orderNum\":6}',78,'0:0:0:0:0:0:0:1','2019-04-25 16:17:17'),

(4,'admin','保存菜单','com.sport.modules.sys.controller.SysMenuController.save()','{\"menuId\":87,\"parentId\":41,\"parentName\":\"体育管理系统\",\"name\":\"通过出生日期获取Project\",\"url\":\"sport/project/listByBirthday\",\"perms\":\"sport:project:listByBirthday\",\"type\":1,\"icon\":\"fa\",\"orderNum\":0}',91,'0:0:0:0:0:0:0:1','2019-04-26 13:47:24'),

(5,'admin','修改菜单','com.sport.modules.sys.controller.SysMenuController.update()','{\"menuId\":87,\"parentId\":72,\"parentName\":\"学员信息表\",\"name\":\"通过出生日期获取Project\",\"url\":\"sport/project/listByBirthday\",\"perms\":\"sport:project:listByBirthday\",\"type\":2,\"icon\":\"fa\",\"orderNum\":0}',45,'0:0:0:0:0:0:0:1','2019-04-26 13:48:02');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`) values 

(1,0,'系统管理',NULL,NULL,0,'fa fa-cog',0),

(2,1,'管理员管理','modules/sys/user.html',NULL,1,'fa fa-user',1),

(3,1,'角色管理','modules/sys/role.html',NULL,1,'fa fa-user-secret',2),

(4,1,'菜单管理','modules/sys/menu.html',NULL,1,'fa fa-th-list',3),

(5,1,'SQL监控','druid/sql.html',NULL,1,'fa fa-bug',4),

(6,1,'定时任务','modules/job/schedule.html',NULL,1,'fa fa-tasks',5),

(7,6,'查看',NULL,'sys:schedule:list,sys:schedule:info',2,NULL,0),

(8,6,'新增',NULL,'sys:schedule:save',2,NULL,0),

(9,6,'修改',NULL,'sys:schedule:update',2,NULL,0),

(10,6,'删除',NULL,'sys:schedule:delete',2,NULL,0),

(11,6,'暂停',NULL,'sys:schedule:pause',2,NULL,0),

(12,6,'恢复',NULL,'sys:schedule:resume',2,NULL,0),

(13,6,'立即执行',NULL,'sys:schedule:run',2,NULL,0),

(14,6,'日志列表',NULL,'sys:schedule:log',2,NULL,0),

(15,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),

(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),

(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),

(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),

(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),

(20,3,'新增',NULL,'sys:role:save,sys:menu:perms',2,NULL,0),

(21,3,'修改',NULL,'sys:role:update,sys:menu:perms',2,NULL,0),

(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),

(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),

(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),

(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),

(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),

(27,1,'参数管理','modules/sys/config.html','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'fa fa-sun-o',6),

(29,1,'系统日志','modules/sys/log.html','sys:log:list',1,'fa fa-file-text-o',7),

(30,1,'文件上传','modules/oss/oss.html','sys:oss:all',1,'fa fa-file-image-o',6),

(31,1,'部门管理','modules/sys/dept.html',NULL,1,'fa fa-file-code-o',1),

(32,31,'查看',NULL,'sys:dept:list,sys:dept:info',2,NULL,0),

(33,31,'新增',NULL,'sys:dept:save,sys:dept:select',2,NULL,0),

(34,31,'修改',NULL,'sys:dept:update,sys:dept:select',2,NULL,0),

(35,31,'删除',NULL,'sys:dept:delete',2,NULL,0),

(36,1,'字典管理','modules/sys/dict.html',NULL,1,'fa fa-bookmark-o',6),

(37,36,'查看',NULL,'sys:dict:list,sys:dict:info',2,NULL,6),

(38,36,'新增',NULL,'sys:dict:save',2,NULL,6),

(39,36,'修改',NULL,'sys:dict:update',2,NULL,6),

(40,36,'删除',NULL,'sys:dict:delete',2,NULL,6),

(41,0,'体育管理系统','',NULL,0,'fa fa-file-code-o',6),

(42,41,'年龄段体重标准','modules/sport/bmiconfig.html',NULL,1,'fa fa-file-code-o',6),

(43,42,'查看',NULL,'sport:bmiconfig:list,sport:bmiconfig:info',2,NULL,6),

(44,42,'新增',NULL,'sport:bmiconfig:save',2,NULL,6),

(45,42,'修改',NULL,'sport:bmiconfig:update',2,NULL,6),

(46,42,'删除',NULL,'sport:bmiconfig:delete',2,NULL,6),

(47,41,'BMI成绩表','modules/sport/bmigrade.html',NULL,1,'fa fa-file-code-o',6),

(48,47,'查看',NULL,'sport:bmigrade:list,sport:bmigrade:info',2,NULL,6),

(49,47,'新增',NULL,'sport:bmigrade:save',2,NULL,6),

(50,47,'修改',NULL,'sport:bmigrade:update',2,NULL,6),

(51,47,'删除',NULL,'sport:bmigrade:delete',2,NULL,6),

(52,41,'项目','modules/sport/project.html',NULL,1,'fa fa-file-code-o',6),

(53,52,'查看',NULL,'sport:project:list,sport:project:info',2,NULL,6),

(54,52,'新增',NULL,'sport:project:save',2,NULL,6),

(55,52,'修改',NULL,'sport:project:update',2,NULL,6),

(56,52,'删除',NULL,'sport:project:delete',2,NULL,6),

(57,41,'训练项目','modules/sport/projectconfig.html',NULL,1,'fa fa-file-code-o',6),

(58,57,'查看',NULL,'sport:projectconfig:list,sport:projectconfig:info',2,NULL,6),

(59,57,'新增',NULL,'sport:projectconfig:save',2,NULL,6),

(60,57,'修改',NULL,'sport:projectconfig:update',2,NULL,6),

(61,57,'删除',NULL,'sport:projectconfig:delete',2,NULL,6),

(62,41,'项目成绩表','modules/sport/projectgrade.html',NULL,1,'fa fa-file-code-o',6),

(63,62,'查看',NULL,'sport:projectgrade:list,sport:projectgrade:info',2,NULL,6),

(64,62,'新增',NULL,'sport:projectgrade:save',2,NULL,6),

(65,62,'修改',NULL,'sport:projectgrade:update',2,NULL,6),

(66,62,'删除',NULL,'sport:projectgrade:delete',2,NULL,6),

(67,41,'评分建议表','modules/sport/scoresuggestion.html',NULL,1,'fa fa-file-code-o',6),

(68,67,'查看',NULL,'sport:scoresuggestion:list,sport:scoresuggestion:info',2,NULL,6),

(69,67,'新增',NULL,'sport:scoresuggestion:save',2,NULL,6),

(70,67,'修改',NULL,'sport:scoresuggestion:update',2,NULL,6),

(71,67,'删除',NULL,'sport:scoresuggestion:delete',2,NULL,6),

(72,41,'学员信息表','modules/sport/student.html',NULL,1,'fa fa-file-code-o',6),

(73,72,'查看',NULL,'sport:student:list,sport:student:info',2,NULL,6),

(74,72,'新增',NULL,'sport:student:save',2,NULL,6),

(75,72,'修改',NULL,'sport:student:update',2,NULL,6),

(76,72,'删除',NULL,'sport:student:delete',2,NULL,6),

(77,41,'学员成绩表','modules/sport/studentgrade.html',NULL,1,'fa fa-file-code-o',6),

(78,77,'查看',NULL,'sport:studentgrade:list,sport:studentgrade:info',2,NULL,6),

(79,77,'新增',NULL,'sport:studentgrade:save',2,NULL,6),

(80,77,'修改',NULL,'sport:studentgrade:update',2,NULL,6),

(81,77,'删除',NULL,'sport:studentgrade:delete',2,NULL,6),

(82,41,'年龄段训练目标','modules/sport/traingoal.html',NULL,1,'fa fa-file-code-o',6),

(83,82,'查看',NULL,'sport:traingoal:list,sport:traingoal:info',2,NULL,6),

(84,82,'新增',NULL,'sport:traingoal:save',2,NULL,6),

(85,82,'修改',NULL,'sport:traingoal:update',2,NULL,6),

(86,82,'删除',NULL,'sport:traingoal:delete',2,NULL,6),

(87,72,'通过出生日期获取Project','sport/project/listByBirthday','sport:project:listByBirthday',2,'fa',0);

/*Table structure for table `sys_oss` */

DROP TABLE IF EXISTS `sys_oss`;

CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

/*Data for the table `sys_oss` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `sys_role` */

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

/*Data for the table `sys_role_dept` */

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`salt`,`email`,`mobile`,`status`,`dept_id`,`create_time`) values 

(1,'admin','e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b','YzcmCZNvbXocrsz9dm8e','root@renren.io','13612345678',1,1,'2016-11-11 11:11:11');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

/*Data for the table `sys_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
