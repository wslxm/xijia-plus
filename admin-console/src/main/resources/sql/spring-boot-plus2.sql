/*
 Navicat Premium Data Transfer

 Source Server         : 172.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 127.0.0.1:3306
 Source Schema         : spring-boot-plus2

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 14/11/2019 21:23:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_menu`;
CREATE TABLE `t_admin_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT 0 COMMENT '指定父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单名',
  `url` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单url',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '图标',
  `sort` int(255) DEFAULT 0 COMMENT '排序',
  `root` int(11) DEFAULT 0 COMMENT '目录级别(1，系统, 2、一级菜单 ，3，二级菜单, 4、页面)',
  `authority` int(255) DEFAULT NULL COMMENT '当前页面权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES (1, 0, '系统', '0', '0', 1, 1, 0);
INSERT INTO `t_admin_menu` VALUES (4, 1, '系统管理', '0', 'layui-icon-home', 1, 2, 0);
INSERT INTO `t_admin_menu` VALUES (5, 1, '菜单权限', '0', 'layui-icon-home', 2, 2, 0);
INSERT INTO `t_admin_menu` VALUES (6, 1, '代码生成', '0', 'layui-icon-home', 3, 2, 0);
INSERT INTO `t_admin_menu` VALUES (7, 4, '菜单管理', 'page/admin_menu_menu', 'layui-icon-home', 0, 4, 0);
INSERT INTO `t_admin_menu` VALUES (9, 0, '养🐏', '', '', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES (10, 9, '用户管理', '', '/', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (11, 10, '用户信息', 'page/game_user_user', '', 0, 4, 0);
INSERT INTO `t_admin_menu` VALUES (13, 0, '测试系统', '1', '1', 1, 1, 2);
INSERT INTO `t_admin_menu` VALUES (14, 13, '一级菜单', '', '1', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (15, 14, '页面', '', '1', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (16, 14, '二级菜单', '', '1', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (17, 16, '页面', '1', '', 0, 4, 0);
INSERT INTO `t_admin_menu` VALUES (19, 1, 'Layui 相关', '', '0', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (21, 4, '系统用户', '/page/admin_user_user', '', 0, 4, 0);
INSERT INTO `t_admin_menu` VALUES (22, 4, '角色管理', 'page/admin_role_role', '', 0, 4, 0);

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描叙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES (8, '系统管理员', '');
INSERT INTO `t_admin_role` VALUES (9, '开发人员', '');
INSERT INTO `t_admin_role` VALUES (10, '测试人员', '');

-- ----------------------------
-- Table structure for t_admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user_role`;
CREATE TABLE `t_admin_user_role`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` int(11) DEFAULT NULL COMMENT '性别（1男，2女）',
  `emp_id` int(11) DEFAULT NULL COMMENT '部门id',
  `time` datetime(6) DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES (1, '1', '王松', '1720696548', '123456', '四川成都', 23, 1, 1, '2019-11-14 15:17:50.000000');

-- ----------------------------
-- Table structure for t_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu`  (
  `id` int(11) NOT NULL,
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `role_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_admin_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_emp`;
CREATE TABLE `t_admin_emp`  (
  `id` int(11) NOT NULL,
  `fid` int(11) DEFAULT NULL COMMENT '上级部门',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组织名/机构/部门',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描叙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_authority`;
CREATE TABLE `t_admin_authority`  (
  `id` int(11) DEFAULT NULL COMMENT 'id',
  `authority` int(11) DEFAULT NULL COMMENT '权限id，指定菜单表权限id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限url',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限描叙'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
