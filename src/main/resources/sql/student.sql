CREATE TABLE `student` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `no` varchar(20) NOT NULL DEFAULT '' COMMENT '学号',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '年龄',
  `sex` tinyint(1) unsigned NOT NULL COMMENT '性别 1-男 2-女',
  `gradeId` bigint(20) unsigned NOT NULL COMMENT '年级编号',
  `clazzId` bigint(20) unsigned NOT NULL COMMENT '班级编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_key_no` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;