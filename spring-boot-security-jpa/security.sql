/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50152
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50152
File Encoding         : 65001

Date: 2018-11-28 15:25:35
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('2', 'ROLE_TEACHER');
INSERT INTO `role` VALUES ('3', 'ROLE_STUDENT');
INSERT INTO `role` VALUES ('4', 'ROLE_COUNSELOR');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$NmtmORbN/ATToou17gvjl.CUu1yTNxxRjsO2GOJUbJFsWd21pYmFi');
INSERT INTO `user` VALUES ('2', 'js', '$2a$10$NmtmORbN/ATToou17gvjl.CUu1yTNxxRjsO2GOJUbJFsWd21pYmFi');
INSERT INTO `user` VALUES ('3', 'xs', '$2a$10$NmtmORbN/ATToou17gvjl.CUu1yTNxxRjsO2GOJUbJFsWd21pYmFi');
INSERT INTO `user` VALUES ('4', 'fdy', '$2a$10$NmtmORbN/ATToou17gvjl.CUu1yTNxxRjsO2GOJUbJFsWd21pYmFi');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKuser_id` (`role_id`),
  KEY `FKrole_id` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('3', '3');
INSERT INTO `user_role` VALUES ('4', '4');
