CREATE TABLE `clazz` (
  `clazzId` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'clazzId',
  `clazzNo` varchar(20) NOT NULL DEFAULT '' COMMENT '班级编号',
  `clazzName` varchar(20) NOT NULL DEFAULT '' COMMENT '班级名称',
  `teacherName` varchar(30) NOT NULL DEFAULT '' COMMENT '教师姓名',
  PRIMARY KEY (`clazzId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;