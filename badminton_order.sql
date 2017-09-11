/*
Navicat MySQL Data Transfer

Source Server         : MyDataBase
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : badminton_order

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-09-11 10:45:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `badminton_order`
-- ----------------------------
CREATE DATABASE 'badminton_order';
USE badminton_order;
DROP TABLE IF EXISTS `badminton_order`;
CREATE TABLE `badminton_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `start_time` int(2) DEFAULT NULL,
  `end_time` int(2) DEFAULT NULL,
  `area` varchar(2) DEFAULT NULL,
  `flag` varchar(8) DEFAULT NULL,
  `money` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of badminton_order
-- ----------------------------
