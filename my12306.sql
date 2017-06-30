/*
Navicat MySQL Data Transfer

Source Server         : DigitalOcean1
Source Server Version : 50548
Source Host           : 138.68.30.118:3306
Source Database       : my12306

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2017-06-30 16:38:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bureau
-- ----------------------------
DROP TABLE IF EXISTS `bureau`;
CREATE TABLE `bureau` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL COMMENT '车次代码，与车次多对一',
  `cabin` varchar(60) NOT NULL COMMENT '车厢号',
  `seat_type` enum('硬座','软座','硬卧','软卧') NOT NULL DEFAULT '硬座',
  `count` int(10) unsigned NOT NULL COMMENT '车厢坐席数量',
  `type` enum('硬座车','软座车','硬卧车','软卧车','餐车') NOT NULL DEFAULT '硬座车' COMMENT '车厢类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for line
-- ----------------------------
DROP TABLE IF EXISTS `line`;
CREATE TABLE `line` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '线路名称',
  `station_ids` text NOT NULL COMMENT '车站序号，多个',
  `station_names` text NOT NULL COMMENT '车站名，多个',
  `begin_time` time NOT NULL,
  `end_time` time NOT NULL,
  `price` decimal(10,2) unsigned NOT NULL COMMENT '线路的基础价格，最终价格按车座类型乘对应系数',
  `mileage` int(11) NOT NULL,
  `stay_times` text NOT NULL COMMENT '停留时间，以分钟为单位',
  `spend_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `actual_name` varchar(60) NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `price` decimal(10,2) unsigned NOT NULL COMMENT '订单总价',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from` varchar(60) NOT NULL,
  `to` varchar(60) NOT NULL,
  `mileage` int(11) NOT NULL,
  `price` decimal(10,2) unsigned NOT NULL COMMENT '区段的基础价格，最终价格按车座类型乘对应系数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL DEFAULT '',
  `name` varchar(60) NOT NULL DEFAULT '',
  `pinyin_code` varchar(100) NOT NULL,
  `bureau` varchar(60) NOT NULL,
  `level` enum('一等站','二等站','三等站','四等站','五等站') NOT NULL DEFAULT '四等站',
  `region` varchar(60) NOT NULL COMMENT '所属行政区域',
  `address` varchar(60) NOT NULL COMMENT '联系地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned NOT NULL COMMENT '订单的id',
  `trip` varchar(30) NOT NULL COMMENT '车次',
  `date` date NOT NULL,
  `cabin` varchar(30) NOT NULL,
  `seat` varchar(30) NOT NULL,
  `seat_type` enum('硬座','软座','硬卧','软卧','无座') NOT NULL,
  `begin_station` varchar(60) NOT NULL,
  `end_station` varchar(60) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `passenger_name` varchar(60) NOT NULL,
  `passenger_type` enum('成人','儿童','学生') NOT NULL DEFAULT '成人',
  `identity_number` varchar(18) NOT NULL,
  `sale_method` enum('网络','售票处','电话','车站') NOT NULL DEFAULT '网络',
  `sale_time` datetime NOT NULL,
  `status` int(10) unsigned NOT NULL COMMENT '车票状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL COMMENT '车次代码',
  `line_id` int(10) unsigned NOT NULL COMMENT '线路的id',
  `begin_station` varchar(60) NOT NULL,
  `end_station` varchar(60) NOT NULL,
  `begin_time` time NOT NULL,
  `end_time` time NOT NULL,
  `spend_time` int(11) NOT NULL,
  `mileage` int(11) NOT NULL COMMENT '里程',
  `noseat_count` int(11) NOT NULL,
  `vehicle_type` enum('新空调','高铁') NOT NULL DEFAULT '新空调' COMMENT '车辆车体分类',
  `train_type` enum('直达特快','特快','普快','普客') NOT NULL DEFAULT '普客' COMMENT '列车类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(30) NOT NULL,
  `identity_number` varchar(18) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `actual_name` varchar(60) NOT NULL COMMENT '真实姓名',
  `sex` enum('男','女') NOT NULL COMMENT '性别',
  `is_admin` tinyint(4) NOT NULL COMMENT '是否是管理员',
  `last_login` datetime NOT NULL,
  `status` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
