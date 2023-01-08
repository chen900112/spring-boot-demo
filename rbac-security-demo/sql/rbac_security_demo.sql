/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : rbac_security_demo

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 08/01/2023 21:03:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission` (
  `permission_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名',
  `permission_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限类型（M菜单 C控制器）',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限表达式',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='权限表';

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
BEGIN;
INSERT INTO `ums_permission` (`permission_id`, `permission_name`, `permission_type`, `permission`) VALUES (1, '管理菜单', 'M', 'admin:menu:one');
INSERT INTO `ums_permission` (`permission_id`, `permission_name`, `permission_type`, `permission`) VALUES (2, '员工菜单', 'M', 'employee:menu:one');
COMMIT;

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE KEY `name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- ----------------------------
-- Records of ums_role
-- ----------------------------
BEGIN;
INSERT INTO `ums_role` (`role_id`, `role_name`) VALUES (1, 'admin');
INSERT INTO `ums_role` (`role_id`, `role_name`) VALUES (2, 'employee');
COMMIT;

-- ----------------------------
-- Table structure for ums_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_permission`;
CREATE TABLE `ums_role_permission` (
  `role_id` bigint NOT NULL COMMENT '角色主键',
  `permission_id` bigint NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色权限关系表';

-- ----------------------------
-- Records of ums_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `ums_role_permission` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `ums_role_permission` (`role_id`, `permission_id`) VALUES (1, 2);
INSERT INTO `ums_role_permission` (`role_id`, `permission_id`) VALUES (2, 2);
COMMIT;

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of ums_user
-- ----------------------------
BEGIN;
INSERT INTO `ums_user` (`user_id`, `user_name`, `password`) VALUES (1, 'user01', '{noop}123');
INSERT INTO `ums_user` (`user_id`, `user_name`, `password`) VALUES (2, 'user02', '{noop}123');
COMMIT;

-- ----------------------------
-- Table structure for ums_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role`;
CREATE TABLE `ums_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户主键',
  `role_id` bigint NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of ums_user_role
-- ----------------------------
BEGIN;
INSERT INTO `ums_user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `ums_user_role` (`user_id`, `role_id`) VALUES (2, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
