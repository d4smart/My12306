/*
Navicat MySQL Data Transfer

Source Server         : my12306
Source Server Version : 50548
Source Host           : www.d4smarter.com:3306
Source Database       : my12306

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2017-07-04 10:46:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bureau
-- ----------------------------
DROP TABLE IF EXISTS `bureau`;
CREATE TABLE `bureau` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for line
-- ----------------------------
DROP TABLE IF EXISTS `line`;
CREATE TABLE `line` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '线路名称',
  `section_ids` text NOT NULL,
  `station_names` text NOT NULL COMMENT '车站名，多个',
  `begin_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '线路的基础价格，最终价格按车座类型乘对应系数',
  `mileage` int(11) NOT NULL DEFAULT '0',
  `stay_times` text NOT NULL COMMENT '停留时间，以分钟为单位',
  `spend_time` int(11) NOT NULL DEFAULT '0' COMMENT '分钟',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for marshalling
-- ----------------------------
DROP TABLE IF EXISTS `marshalling`;
CREATE TABLE `marshalling` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL DEFAULT '' COMMENT '车次代码，与车次多对一',
  `cabin` varchar(60) NOT NULL DEFAULT '' COMMENT '车厢号',
  `seat_type` enum('硬座','软座','硬卧','软卧') NOT NULL DEFAULT '硬座',
  `count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '车厢坐席数量',
  `type` enum('硬座车','软座车','硬卧车','软卧车','餐车') NOT NULL DEFAULT '硬座车' COMMENT '车厢类型',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0',
  `actual_name` varchar(60) NOT NULL DEFAULT '',
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '订单总价',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from_station` varchar(60) NOT NULL DEFAULT '',
  `to_station` varchar(60) NOT NULL DEFAULT '',
  `mileage` int(11) NOT NULL DEFAULT '0',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '区段的基础价格，最终价格按车座类型乘对应系数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL DEFAULT '',
  `code` varchar(60) NOT NULL DEFAULT '' COMMENT '拼音码',
  `bureau` varchar(60) NOT NULL DEFAULT '',
  `level` enum('一等站','二等站','三等站','四等站','五等站') NOT NULL DEFAULT '四等站',
  `region` varchar(60) NOT NULL DEFAULT '' COMMENT '所属行政区域',
  `address` varchar(60) NOT NULL DEFAULT '' COMMENT '联系地址',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '订单的id',
  `trip` varchar(30) NOT NULL DEFAULT '' COMMENT '车次',
  `date` date DEFAULT NULL,
  `cabin` varchar(30) NOT NULL DEFAULT '',
  `seat` varchar(30) NOT NULL DEFAULT '',
  `seat_type` enum('硬座','软座','硬卧','软卧','无座') NOT NULL DEFAULT '无座',
  `begin_station` varchar(60) NOT NULL DEFAULT '',
  `end_station` varchar(60) NOT NULL DEFAULT '',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `passenger_name` varchar(60) NOT NULL DEFAULT '',
  `passenger_type` enum('成人','儿童','学生') NOT NULL DEFAULT '成人',
  `identity_number` varchar(18) NOT NULL DEFAULT '',
  `sale_method` enum('网络','售票处','电话','车站') NOT NULL DEFAULT '网络',
  `sale_time` datetime DEFAULT NULL,
  `status` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '车票状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL DEFAULT '' COMMENT '车次代码',
  `line_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '线路的id',
  `begin_station` varchar(60) NOT NULL DEFAULT '',
  `end_station` varchar(60) NOT NULL DEFAULT '',
  `begin_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `spend_time` int(11) NOT NULL DEFAULT '0',
  `mileage` int(11) NOT NULL DEFAULT '0' COMMENT '里程',
  `noseat_count` int(11) NOT NULL DEFAULT '0',
  `vehicle_type` enum('新空调','高铁') NOT NULL DEFAULT '新空调' COMMENT '车辆车体分类',
  `train_type` enum('直达特快','特快','普快','普客') NOT NULL DEFAULT '普客' COMMENT '列车类型',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(30) NOT NULL DEFAULT '',
  `identity_number` varchar(18) NOT NULL DEFAULT '',
  `email` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(60) NOT NULL DEFAULT '',
  `actual_name` varchar(60) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `sex` enum('男','女') NOT NULL DEFAULT '男' COMMENT '性别',
  `role` int(4) unsigned NOT NULL DEFAULT '0' COMMENT '用户角色',
  `last_login` datetime DEFAULT NULL,
  `status` int(11) unsigned NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
