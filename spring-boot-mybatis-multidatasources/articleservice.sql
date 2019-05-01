/*
Navicat MySQL Data Transfer

Source Server         : jsjxyxt
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : articleservice

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-04-09 10:41:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'java');
INSERT INTO `article` VALUES ('2', 'SQL');
