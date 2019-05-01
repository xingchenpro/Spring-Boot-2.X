/*
Navicat MySQL Data Transfer

Source Server         : jsjxyxt
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : videoservice

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-04-09 10:41:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `video`
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `v_id` int(11) NOT NULL AUTO_INCREMENT,
  `v_name` varchar(20) NOT NULL DEFAULT '',
  `a_id` int(11) NOT NULL,
  PRIMARY KEY (`v_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', 'java', '1');
INSERT INTO `video` VALUES ('2', 'SQL', '2');
