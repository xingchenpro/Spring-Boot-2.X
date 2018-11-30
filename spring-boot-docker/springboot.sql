/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50152
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50152
File Encoding         : 65001

Date: 2018-11-30 14:41:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` varchar(20) NOT NULL,
  `user_password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('', '');
INSERT INTO `users` VALUES ('233', '233');
INSERT INTO `users` VALUES ('333', '333');
INSERT INTO `users` VALUES ('hly', '123');
