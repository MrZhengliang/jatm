/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : atm

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2015-12-16 17:30:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_bank_card`
-- ----------------------------
DROP TABLE IF EXISTS `t_bank_card`;
CREATE TABLE `t_bank_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自动增长',
  `name` varchar(20) DEFAULT NULL COMMENT '账户名称',
  `card_no` varchar(20) DEFAULT NULL COMMENT '账户卡号',
  `password` varchar(20) DEFAULT NULL COMMENT '账户密码',
  `money` double DEFAULT NULL COMMENT '金额',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bank_card
-- ----------------------------
INSERT INTO `t_bank_card` VALUES ('1', '哈哈', '123123', '321', '899', null);
