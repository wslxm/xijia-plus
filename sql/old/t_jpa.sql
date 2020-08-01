/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : spring-boot-plus2

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 15/04/2020 22:05:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_authority`;
CREATE TABLE `t_admin_authority`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) DEFAULT NULL COMMENT '权限类',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限url',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限描叙',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES (72, 78, 'user:delete', '/userAdmin/delete', '删除', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (73, 78, 'user:save', '/userAdmin/save', '添加/修改', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (74, 78, 'user:login', '/userAdmin/login', '登录', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (75, 78, 'user:updPwd', '/userAdmin/updPwd', '密码修改', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (76, 78, 'user:findAll', '/userAdmin/findAll', '查询', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (78, 0, 'userAdmin', '/userAdmin', '系统用户', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (79, 0, 'dictionary', '/dictionaryAdmin', '字典表', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (80, 79, 'dictionary:delete', '/dictionaryAdmin/delete', '删除', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (81, 79, 'dictionary:save', '/dictionaryAdmin/save', '添加/修改', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (82, 79, 'dictionary:findByType', '/dictionaryAdmin/findByType', '根据类型查询Type', NULL, 0);
INSERT INTO `t_admin_authority` VALUES (83, 79, 'dictionary:findAll', '/dictionaryAdmin/findAll', '分页查询', NULL, 0);

-- ----------------------------
-- Table structure for t_admin_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_dictionary`;
CREATE TABLE `t_admin_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典类型',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '搜索值',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '选择值',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描叙',
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_dictionary
-- ----------------------------
INSERT INTO `t_admin_dictionary` VALUES (2, 'root', 'root', 'root', '字典表系统自身', 0);
INSERT INTO `t_admin_dictionary` VALUES (5, 'root', '1', 'root2', '1', 0);
INSERT INTO `t_admin_dictionary` VALUES (6, 'root', '2', 'root3', '1', 0);
INSERT INTO `t_admin_dictionary` VALUES (7, 'root3', '1', '1', '111', 0);

-- ----------------------------
-- Table structure for t_admin_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_emp`;
CREATE TABLE `t_admin_emp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) DEFAULT NULL COMMENT '上级部门',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组织名/机构/部门',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描叙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_emp
-- ----------------------------
INSERT INTO `t_admin_emp` VALUES (3, 1, '1', '1');

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
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES (1, 0, '系统', '0', '0', 1, 1, 0, 0);
INSERT INTO `t_admin_menu` VALUES (4, 1, '系统管理', '0', 'layui-icon-home', 1, 2, 0, 0);
INSERT INTO `t_admin_menu` VALUES (5, 1, '权限管理', '0', 'layui-icon-home', 2, 2, 0, 0);
INSERT INTO `t_admin_menu` VALUES (6, 1, '代码生成', '0', 'layui-icon-home', 3, 2, 0, 0);
INSERT INTO `t_admin_menu` VALUES (7, 4, '菜单管理', '/page/console_menu_menu', 'layui-icon-home', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (9, 0, '&#20846;&#23478;', '', '', 0, 1, 0, 0);
INSERT INTO `t_admin_menu` VALUES (10, 9, '用户管理', '', '/', 0, 2, 0, 0);
INSERT INTO `t_admin_menu` VALUES (11, 10, '用户信息', '/page/game_user_user', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (13, 0, '测试系统', '1', '1', 1, 1, 2, 0);
INSERT INTO `t_admin_menu` VALUES (14, 13, '一级菜单', '', '1', 0, 2, 0, 0);
INSERT INTO `t_admin_menu` VALUES (15, 14, '页面', '', '1', 0, 3, 0, 0);
INSERT INTO `t_admin_menu` VALUES (16, 14, '二级菜单', '', '1', 0, 3, 0, 0);
INSERT INTO `t_admin_menu` VALUES (17, 16, '页面', '1', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (19, 1, 'Layui 相关', '', '0', 0, 2, 0, 0);
INSERT INTO `t_admin_menu` VALUES (21, 4, '系统用户', '/page/console_user_user', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (22, 4, '角色管理', '/page/console_role_role', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (23, 5, '角色菜单权限', '/page/console_role_roleMenuAuth', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (24, 5, '角色URL权限', '/page/console_role_roleUrlAuth', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (25, 4, '字典管理', '/page/console_dictionary_dictionary', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (27, 14, '登录', '/page/console_user_login', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (29, 4, 'Test', '/1111', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (30, 6, '数据表', '/page/console_dataBase_dataBase', '', 0, 4, 0, 0);
INSERT INTO `t_admin_menu` VALUES (36, 5, 'url', '/page/console_authority_authority', '', 0, 4, 0, 0);

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描叙',
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES (1, '系统管理员', NULL, 0);
INSERT INTO `t_admin_role` VALUES (2, '开发人员', '', 0);
INSERT INTO `t_admin_role` VALUES (3, '测试人员', '', 0);

-- ----------------------------
-- Table structure for t_admin_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_auth`;
CREATE TABLE `t_admin_role_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_id` int(11) DEFAULT NULL COMMENT 'url权限id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_auth
-- ----------------------------
INSERT INTO `t_admin_role_auth` VALUES (1, 72, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (5, 73, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (7, 74, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (10, 78, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (11, 78, 0, 0);
INSERT INTO `t_admin_role_auth` VALUES (12, 72, 0, 0);
INSERT INTO `t_admin_role_auth` VALUES (13, 73, 0, 0);
INSERT INTO `t_admin_role_auth` VALUES (14, 74, 0, 0);
INSERT INTO `t_admin_role_auth` VALUES (15, 75, 0, 0);
INSERT INTO `t_admin_role_auth` VALUES (16, 76, 0, 0);
INSERT INTO `t_admin_role_auth` VALUES (23, 75, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (27, 76, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (28, 79, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (29, 80, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (30, 81, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (31, 82, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (32, 83, 1, 0);

-- ----------------------------
-- Table structure for t_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `role_id` int(11) DEFAULT NULL COMMENT '用户id',
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------
INSERT INTO `t_admin_role_menu` VALUES (71, 5, 0, 0);
INSERT INTO `t_admin_role_menu` VALUES (72, 23, 0, 0);
INSERT INTO `t_admin_role_menu` VALUES (73, 4, 0, 0);
INSERT INTO `t_admin_role_menu` VALUES (74, 7, 0, 0);
INSERT INTO `t_admin_role_menu` VALUES (89, 1, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (90, 4, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (91, 7, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (92, 21, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (93, 22, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (94, 25, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (108, 5, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (109, 23, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (117, 1, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (118, 4, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (119, 7, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (120, 21, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (121, 22, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (122, 25, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (123, 5, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (124, 23, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (125, 24, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (126, 6, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (127, 19, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (128, 9, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (129, 10, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (130, 11, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (131, 13, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (132, 14, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (133, 15, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (134, 16, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (135, 17, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (136, 27, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (137, 28, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (140, 28, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (141, 6, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (142, 9, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (143, 10, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (144, 11, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (145, 13, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (146, 14, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (147, 15, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (148, 16, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (149, 17, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (150, 27, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (151, 29, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (152, 30, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (153, 31, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (154, 24, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (155, 36, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (156, 35, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (157, 6, 1, 0);

-- ----------------------------
-- Table structure for t_admin_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_user`;
CREATE TABLE `t_admin_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_user
-- ----------------------------
INSERT INTO `t_admin_role_user` VALUES (1, 1, 1, 0);
INSERT INTO `t_admin_role_user` VALUES (7, 1, 2, 0);
INSERT INTO `t_admin_role_user` VALUES (8, 20, 1, 0);
INSERT INTO `t_admin_role_user` VALUES (28, 18, 1, 0);
INSERT INTO `t_admin_role_user` VALUES (29, 18, 2, 0);

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
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES (1, '1', '王松', '1720696548', '123456', '四川成都', 23, 1, 1, '2019-11-14 15:17:50.000000', 0);
INSERT INTO `t_admin_user` VALUES (18, '1', '1', '1', '11111111', '1', 1, 1, 1, '2019-11-15 10:43:24.790000', 0);
INSERT INTO `t_admin_user` VALUES (20, '1', 'admin', 'admin', 'admin', '四川成都', 0, 1, 1, '2020-01-31 10:15:06.728000', 0);

SET FOREIGN_KEY_CHECKS = 1;
