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

 Date: 20/06/2020 18:07:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_authority`;
CREATE TABLE `t_admin_authority`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) DEFAULT NULL COMMENT '权限类Id（方法与类/层级关系展示）',
  `method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求方式(GET/POST/PUT/DELETE)',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限url',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限备注信息',
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 287 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES (235, 0, '', '/authorityAdmin', 'URL权限管理', 0);
INSERT INTO `t_admin_authority` VALUES (236, 235, 'GET', '/authorityAdmin/findList', '查询所有,跟据角色赋予选中状态', 0);
INSERT INTO `t_admin_authority` VALUES (237, 235, 'PUT', '/authorityAdmin/putAuthority', '扫描权限：权限列表数据刷新', 0);
INSERT INTO `t_admin_authority` VALUES (238, 0, '', '/dictionaryAdmin', '字典管理', 0);
INSERT INTO `t_admin_authority` VALUES (246, 0, '', '/menuAdmin', '菜单管理', 0);
INSERT INTO `t_admin_authority` VALUES (247, 246, 'PUT', '/menuAdmin/update', '编辑', 0);
INSERT INTO `t_admin_authority` VALUES (248, 246, 'DELETE', '/menuAdmin/delete', 'ID删除菜单+所有子菜单', 0);
INSERT INTO `t_admin_authority` VALUES (249, 246, 'GET', '/menuAdmin/list', '菜单列表 ==>>>  列表数据  ==>>>  所有', 0);
INSERT INTO `t_admin_authority` VALUES (250, 246, 'POST', '/menuAdmin/insert', '菜单添加', 0);
INSERT INTO `t_admin_authority` VALUES (251, 246, 'POST', '/menuAdmin/test', '测试', 0);
INSERT INTO `t_admin_authority` VALUES (252, 246, 'GET', '/menuAdmin/findPidOrRoleIdList', '根据pid +角色Id获取菜单列表', 0);
INSERT INTO `t_admin_authority` VALUES (253, 246, 'POST', '/menuAdmin/test1', '测试1', 0);
INSERT INTO `t_admin_authority` VALUES (254, 0, '', '/roleAdmin', '角色管理', 0);
INSERT INTO `t_admin_authority` VALUES (255, 254, 'PUT', '/roleAdmin/update', '编辑', 0);
INSERT INTO `t_admin_authority` VALUES (256, 254, 'DELETE', '/roleAdmin/delete', '单删除', 0);
INSERT INTO `t_admin_authority` VALUES (257, 254, 'GET', '/roleAdmin/list', '查询所有', 0);
INSERT INTO `t_admin_authority` VALUES (258, 254, 'POST', '/roleAdmin/insert', '添加', 0);
INSERT INTO `t_admin_authority` VALUES (259, 0, '', '/userAdmin', '用户管理', 0);
INSERT INTO `t_admin_authority` VALUES (260, 259, 'GET', '/userAdmin/findUser', '当前登录用户信息', 0);
INSERT INTO `t_admin_authority` VALUES (261, 246, 'GET', '/menuAdmin/menuTree', '左导航菜单 ===>>> 树结构数据 ===>>> 需先登录', 0);
INSERT INTO `t_admin_authority` VALUES (262, 0, '', '/loginAdmin', '登录', 0);
INSERT INTO `t_admin_authority` VALUES (263, 262, 'GET', '/loginAdmin/logout', '退出登录', 0);
INSERT INTO `t_admin_authority` VALUES (264, 262, 'GET', '/loginAdmin/login', '登录', 0);
INSERT INTO `t_admin_authority` VALUES (265, 259, 'PUT', '/userAdmin/update', '编辑', 0);
INSERT INTO `t_admin_authority` VALUES (266, 259, 'DELETE', '/userAdmin/delete', '单行删除', 0);
INSERT INTO `t_admin_authority` VALUES (267, 259, 'POST', '/userAdmin/insert', '添加', 0);
INSERT INTO `t_admin_authority` VALUES (269, 254, 'GET', '/roleAdmin/findRoleChecked', '用户角色分配==>查询所有角色,用户拥有角色赋予isChecked=true', 0);
INSERT INTO `t_admin_authority` VALUES (270, 254, 'PUT', '/roleAdmin/updRoleUrlAuth', '角色URL分配', 0);
INSERT INTO `t_admin_authority` VALUES (271, 254, 'PUT', '/roleAdmin/updRoleMenu', '角色菜单分配', 0);
INSERT INTO `t_admin_authority` VALUES (273, 254, 'PUT', '/roleAdmin/updUserRole', '用户角色分配', 0);
INSERT INTO `t_admin_authority` VALUES (274, 254, 'GET', '/roleAdmin/findPage', '分页查询', 0);
INSERT INTO `t_admin_authority` VALUES (275, 259, 'GET', '/userAdmin/findRoleIdList', '查询指定角色下的所有用户', 0);
INSERT INTO `t_admin_authority` VALUES (276, 259, 'PUT', '/userAdmin/updPwd', '密码修改', 0);
INSERT INTO `t_admin_authority` VALUES (277, 259, 'DELETE', '/userAdmin/deleteByIds', '批量删除', 0);
INSERT INTO `t_admin_authority` VALUES (278, 259, 'GET', '/userAdmin/findPage', '分页查询', 0);
INSERT INTO `t_admin_authority` VALUES (279, 238, 'PUT', '/dictionaryAdmin/update', '编辑', 0);
INSERT INTO `t_admin_authority` VALUES (280, 238, 'DELETE', '/dictionaryAdmin/delete', 'ID删除', 0);
INSERT INTO `t_admin_authority` VALUES (281, 238, 'POST', '/dictionaryAdmin/insert', '添加', 0);
INSERT INTO `t_admin_authority` VALUES (282, 238, 'GET', '/dictionaryAdmin/findList', '列表查询', 0);
INSERT INTO `t_admin_authority` VALUES (283, 238, 'PUT', '/dictionaryAdmin/updSort', '修改排序', 0);
INSERT INTO `t_admin_authority` VALUES (284, 0, '', '/dataBase', '数据库表查询', 0);
INSERT INTO `t_admin_authority` VALUES (285, 284, 'GET', '/dataBase/findTable', '查询所有表名', 0);
INSERT INTO `t_admin_authority` VALUES (286, 284, 'GET', '/dataBase/findTableField', '查询指定表下使用字段内容', 0);

-- ----------------------------
-- Table structure for t_admin_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_dictionary`;
CREATE TABLE `t_admin_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典类型',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典名称',
  `pid` int(11) DEFAULT NULL COMMENT '父Id',
  `desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描叙',
  `sort` int(11) DEFAULT 0 COMMENT '排序',
  `deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_dictionary
-- ----------------------------
INSERT INTO `t_admin_dictionary` VALUES (1, 'gender', '性别', 0, '性别(1-男，0-女)', 1, 0);
INSERT INTO `t_admin_dictionary` VALUES (5, '1', '男', 1, '1-男', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES (6, '0', '女', 1, '0-女', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES (14, 'tx-vip', '腾讯会员', 0, '马化腾', 2, 0);
INSERT INTO `t_admin_dictionary` VALUES (15, 'tx-sp-vip', '视频会员', 14, '腾讯视频VIP特权', 1, 0);
INSERT INTO `t_admin_dictionary` VALUES (31, 'tx-lz-vip', '绿砖', 14, 'QQ音乐会员', 2, 0);

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
  `deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES (1, 0, '兮家-系统管理', '0', '0', 1, 1, 0);
INSERT INTO `t_admin_menu` VALUES (4, 1, '系统管理', '0', 'layui-icon-set', 1, 2, 0);
INSERT INTO `t_admin_menu` VALUES (5, 1, '权限管理', '0', 'layui-icon-util', 2, 2, 0);
INSERT INTO `t_admin_menu` VALUES (6, 1, '代码生成', '0', 'layui-icon-layer', 3, 2, 0);
INSERT INTO `t_admin_menu` VALUES (7, 4, '菜单管理', '/page/admin_menu_menu', 'layui-icon-home', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (9, 0, '兮家-次元空间', '0', '0', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES (10, 9, '用户管理', '', '/', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (11, 10, '用户信息', '/page/game_user_user', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (13, 0, '测试系统', '', '1', 1, 1, 0);
INSERT INTO `t_admin_menu` VALUES (14, 13, '一级菜单', '', '1', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (15, 14, '页面', '/aaaaa', '1', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (16, 14, '二级菜单', '', '1', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (17, 16, '页面', '/1', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (19, 1, 'Layui 相关', '', 'layui-icon-engine', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (21, 4, '系统用户', '/page/admin_user_user', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (22, 4, '角色管理', '/page/admin_role_role', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (23, 5, '角色菜单权限', '/page/admin_role_roleMenuAuth', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (24, 5, '角色URL权限', '/page/admin_role_roleUrlAuth', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (25, 4, '字典管理', '/page/admin_dictionary_dictionary', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (27, 14, '登录', '/page/admin_user_login', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (30, 6, '数据表', '/page/admin_dataBase_dataBase', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (39, 14, 'aaa', 'aaaa', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (40, 13, 'aaa', '0', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (41, 13, 'aaa', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (42, 41, 'bbb', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (43, 42, 'ccc', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (46, 43, 'ddd', '0', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (66, 43, 'eee', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (91, 9, '单页应用', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES (92, 91, '栅格应用', '/page/front_show_show', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (93, 94, '文字注释', '/page/front_symbol_main', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES (94, 1, '单页应用', '', 'layui-icon-file-b', 0, 2, 0);

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描叙',
  `deleted` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES (1, '系统管理员', '1', 0);
INSERT INTO `t_admin_role` VALUES (2, '开发人员', '1', 0);
INSERT INTO `t_admin_role` VALUES (3, '测试人员', '1', 0);
INSERT INTO `t_admin_role` VALUES (19, '腾讯会员', '1', 0);
INSERT INTO `t_admin_role` VALUES (28, 'bilbil会员', '1', 0);
INSERT INTO `t_admin_role` VALUES (29, '爱奇艺会员', '1', 0);
INSERT INTO `t_admin_role` VALUES (31, '系统体验用户', '开发给第三方人员使用的角色，多数功能权限受限', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 217 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_auth
-- ----------------------------
INSERT INTO `t_admin_role_auth` VALUES (60, 235, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (61, 236, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (62, 237, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (63, 238, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (64, 279, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (65, 280, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (66, 281, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (67, 282, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (68, 283, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (69, 242, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (70, 268, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (71, 246, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (72, 247, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (73, 248, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (74, 249, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (75, 250, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (76, 251, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (77, 252, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (78, 253, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (79, 261, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (80, 254, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (81, 255, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (82, 256, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (83, 257, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (84, 258, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (85, 269, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (86, 270, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (87, 271, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (88, 273, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (89, 274, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (90, 259, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (91, 260, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (92, 265, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (93, 266, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (94, 267, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (95, 275, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (96, 276, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (97, 277, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (98, 278, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (99, 262, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (100, 263, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (101, 264, 1, 0);
INSERT INTO `t_admin_role_auth` VALUES (205, 236, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (206, 282, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (207, 249, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (208, 252, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (209, 261, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (210, 257, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (211, 274, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (212, 260, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (213, 275, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (214, 278, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (215, 263, 31, 0);
INSERT INTO `t_admin_role_auth` VALUES (216, 264, 31, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 689 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------
INSERT INTO `t_admin_role_menu` VALUES (71, 5, 0, 0);
INSERT INTO `t_admin_role_menu` VALUES (72, 23, 0, 0);
INSERT INTO `t_admin_role_menu` VALUES (73, 4, 0, 0);
INSERT INTO `t_admin_role_menu` VALUES (74, 7, 0, 0);
INSERT INTO `t_admin_role_menu` VALUES (474, 1, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (475, 4, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (476, 7, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (477, 21, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (478, 22, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (479, 25, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (480, 5, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (481, 23, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (482, 24, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (483, 6, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (484, 30, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (485, 19, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (486, 9, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (487, 10, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (488, 11, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (489, 38, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (490, 37, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (491, 13, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (492, 14, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (493, 15, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (494, 16, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (495, 17, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (496, 27, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (497, 39, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (498, 40, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (499, 41, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (500, 42, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (501, 43, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (502, 46, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (503, 66, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (504, 79, 2, 0);
INSERT INTO `t_admin_role_menu` VALUES (505, 1, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (506, 4, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (507, 7, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (508, 21, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (509, 22, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (510, 25, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (511, 5, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (512, 23, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (513, 24, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (514, 6, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (515, 30, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (516, 19, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (517, 9, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (518, 10, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (519, 11, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (520, 38, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (521, 37, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (522, 13, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (523, 14, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (524, 15, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (525, 16, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (526, 17, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (527, 27, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (528, 39, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (529, 40, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (530, 41, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (531, 42, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (532, 43, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (533, 46, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (534, 66, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (535, 79, 3, 0);
INSERT INTO `t_admin_role_menu` VALUES (630, 1, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (631, 4, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (632, 7, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (633, 21, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (634, 22, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (635, 25, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (636, 5, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (637, 23, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (638, 24, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (639, 6, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (640, 30, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (641, 19, 31, 0);
INSERT INTO `t_admin_role_menu` VALUES (665, 1, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (666, 4, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (667, 7, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (668, 21, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (669, 22, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (670, 25, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (671, 5, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (672, 23, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (673, 24, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (674, 6, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (675, 30, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (676, 94, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (677, 93, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (678, 9, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (679, 10, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (680, 11, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (681, 91, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (682, 92, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (683, 13, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (684, 14, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (685, 15, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (686, 16, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (687, 17, 1, 0);
INSERT INTO `t_admin_role_menu` VALUES (688, 27, 1, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_user
-- ----------------------------
INSERT INTO `t_admin_role_user` VALUES (1, 1, 1, 0);
INSERT INTO `t_admin_role_user` VALUES (7, 1, 2, 0);
INSERT INTO `t_admin_role_user` VALUES (42, 33, 1, 0);
INSERT INTO `t_admin_role_user` VALUES (43, 33, 2, 0);
INSERT INTO `t_admin_role_user` VALUES (44, 33, 3, 0);
INSERT INTO `t_admin_role_user` VALUES (45, 18, 1, 0);
INSERT INTO `t_admin_role_user` VALUES (46, 18, 2, 0);
INSERT INTO `t_admin_role_user` VALUES (47, 18, 3, 0);
INSERT INTO `t_admin_role_user` VALUES (48, 20, 31, 0);

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '头像',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '昵称',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
  `age` int(11) NOT NULL COMMENT '年龄',
  `gender` int(11) NOT NULL DEFAULT 0 COMMENT '性别（1男，0女）',
  `emp_id` int(11) NOT NULL COMMENT '部门id',
  `time` datetime(6) NOT NULL COMMENT '注册时间',
  `deleted` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES (1, '1', '王松', '1720696548', '123456', '四川成都', 23, 1, 1, '2019-11-14 15:17:50.000000', 0);
INSERT INTO `t_admin_user` VALUES (18, '1', '1', '1', '11111111', '1', 1, 1, 1, '2019-11-15 10:43:24.790000', 0);
INSERT INTO `t_admin_user` VALUES (20, '1', '游客', 'admin', 'admin', '四川成都', 0, 1, 1, '2020-01-31 10:15:06.728000', 0);

-- ----------------------------
-- Table structure for t_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_basic`;
CREATE TABLE `t_basic`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据条目主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据创建账户',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '数据最后更新时间',
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据最后更新账户',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段（0：正常  1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基础表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
