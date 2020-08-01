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

 Date: 01/08/2020 17:51:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_authority`;
CREATE TABLE `t_admin_authority`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(自动插入)',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间(自动插入)',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限类Id（方法与类/层级关系展示）',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式(GET/POST/PUT/DELETE)',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限url',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限接口表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES ('1289497183933972482', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '238', 'GET', '/dictionaryAdmin/findCode', 'Code查询,最多向下2级，A-> BB -> CCCC');
INSERT INTO `t_admin_authority` VALUES ('1289497185192263682', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '', '', '/dev/devBug', 'Bug修复');
INSERT INTO `t_admin_authority` VALUES ('1289497185209040898', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185192263682', 'PUT', '/dev/devBug/updState', 'ID编辑状态--任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)');
INSERT INTO `t_admin_authority` VALUES ('1289497185217429506', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185192263682', 'PUT', '/dev/devBug/upd', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('1289497185225818113', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185192263682', 'DELETE', '/dev/devBug/del', '单删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185234206721', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185192263682', 'POST', '/dev/devBug/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1289497185250983937', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185192263682', 'GET', '/dev/devBug/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('1289497185255178241', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185192263682', 'DELETE', '/dev/devBug/delByIds', '批量删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185263566849', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '', '', '/dev/devNorm', '开发规范');
INSERT INTO `t_admin_authority` VALUES ('1289497185271955458', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185263566849', 'PUT', '/dev/devNorm/upd', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('1289497185276149762', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185263566849', 'DELETE', '/dev/devNorm/del', '单删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185292926977', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185263566849', 'POST', '/dev/devNorm/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1289497185301315586', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185263566849', 'GET', '/dev/devNorm/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('1289497185305509890', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185263566849', 'DELETE', '/dev/devNorm/delByIds', '批量删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185318092802', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '', '', '/dev/devTask', '开发任务');
INSERT INTO `t_admin_authority` VALUES ('1289497185322287105', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185318092802', 'PUT', '/dev/devTask/updState', 'ID编辑状态--任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)');
INSERT INTO `t_admin_authority` VALUES ('1289497185334870018', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185318092802', 'PUT', '/dev/devTask/upd', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('1289497185343258626', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185318092802', 'DELETE', '/dev/devTask/del', '单删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185355841537', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185318092802', 'POST', '/dev/devTask/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1289497185360035841', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185318092802', 'GET', '/dev/devTask/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('1289497185368424450', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 0, '1289497185318092802', 'DELETE', '/dev/devTask/delByIds', '批量删除');
INSERT INTO `t_admin_authority` VALUES ('235', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '0', '', '/authorityAdmin', 'URL权限管理');
INSERT INTO `t_admin_authority` VALUES ('236', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '235', 'GET', '/authorityAdmin/findList', '查询所有,跟据角色赋予选中状态');
INSERT INTO `t_admin_authority` VALUES ('237', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '235', 'PUT', '/authorityAdmin/putAuthority', '扫描权限：权限列表数据刷新');
INSERT INTO `t_admin_authority` VALUES ('238', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '0', '', '/dictionaryAdmin', '字典管理');
INSERT INTO `t_admin_authority` VALUES ('246', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '0', '', '/menuAdmin', '菜单管理');
INSERT INTO `t_admin_authority` VALUES ('247', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '246', 'PUT', '/menuAdmin/update', '编辑');
INSERT INTO `t_admin_authority` VALUES ('248', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '246', 'DELETE', '/menuAdmin/delete', 'ID删除菜单+所有子菜单');
INSERT INTO `t_admin_authority` VALUES ('249', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '246', 'GET', '/menuAdmin/list', '菜单列表 ==>>>  列表数据  ==>>>  所有');
INSERT INTO `t_admin_authority` VALUES ('250', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '246', 'POST', '/menuAdmin/insert', '菜单添加');
INSERT INTO `t_admin_authority` VALUES ('252', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '246', 'GET', '/menuAdmin/findPidOrRoleIdList', '根据pid +角色Id获取菜单列表');
INSERT INTO `t_admin_authority` VALUES ('254', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '0', '', '/roleAdmin', '角色管理');
INSERT INTO `t_admin_authority` VALUES ('255', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'PUT', '/roleAdmin/update', '编辑');
INSERT INTO `t_admin_authority` VALUES ('256', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'DELETE', '/roleAdmin/delete', '删除');
INSERT INTO `t_admin_authority` VALUES ('257', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'GET', '/roleAdmin/list', '查询所有');
INSERT INTO `t_admin_authority` VALUES ('258', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'POST', '/roleAdmin/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('259', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '0', '', '/userAdmin', '用户管理');
INSERT INTO `t_admin_authority` VALUES ('261', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '246', 'GET', '/menuAdmin/menuTree', '左导航菜单 ===>>> 树结构数据 ===>>> 需先登录');
INSERT INTO `t_admin_authority` VALUES ('265', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '259', 'PUT', '/userAdmin/update', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('266', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '259', 'DELETE', '/userAdmin/delete', '单删除');
INSERT INTO `t_admin_authority` VALUES ('267', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '259', 'POST', '/userAdmin/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('269', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'GET', '/roleAdmin/findRoleChecked', '用户角色分配弹出层查询所有角色,用户拥有角色赋予isChecked=true');
INSERT INTO `t_admin_authority` VALUES ('270', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'PUT', '/roleAdmin/updRoleUrlAuth', '角色URL分配');
INSERT INTO `t_admin_authority` VALUES ('271', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'PUT', '/roleAdmin/updRoleMenu', '角色菜单分配');
INSERT INTO `t_admin_authority` VALUES ('273', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'PUT', '/roleAdmin/updUserRole', '用户角色分配');
INSERT INTO `t_admin_authority` VALUES ('274', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '254', 'GET', '/roleAdmin/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('275', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '259', 'GET', '/userAdmin/findRoleIdList', '查询指定角色下的所有用户(isChecked=true)');
INSERT INTO `t_admin_authority` VALUES ('276', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '259', 'PUT', '/userAdmin/updPwd', '密码修改');
INSERT INTO `t_admin_authority` VALUES ('277', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '259', 'DELETE', '/userAdmin/deleteByIds', '批量删除');
INSERT INTO `t_admin_authority` VALUES ('278', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '259', 'GET', '/userAdmin/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('279', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '238', 'PUT', '/dictionaryAdmin/update', '编辑');
INSERT INTO `t_admin_authority` VALUES ('280', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '238', 'DELETE', '/dictionaryAdmin/delete', 'ID删除');
INSERT INTO `t_admin_authority` VALUES ('281', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '238', 'POST', '/dictionaryAdmin/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('282', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '238', 'GET', '/dictionaryAdmin/findList', '列表查询');
INSERT INTO `t_admin_authority` VALUES ('283', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '238', 'PUT', '/dictionaryAdmin/updSort', '修改排序');
INSERT INTO `t_admin_authority` VALUES ('284', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '0', '', '/dataBase', '数据库表查询');
INSERT INTO `t_admin_authority` VALUES ('285', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '284', 'GET', '/dataBase/findTable', '查询所有表名');
INSERT INTO `t_admin_authority` VALUES ('286', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '284', 'GET', '/dataBase/findTableField', '查询指定表下使用字段内容');
INSERT INTO `t_admin_authority` VALUES ('287', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '0', '', '/generate', '代码生成器-只限于页面调用');
INSERT INTO `t_admin_authority` VALUES ('289', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '287', 'POST', '/generate/preview', '生成预览代码');
INSERT INTO `t_admin_authority` VALUES ('290', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '287', 'GET', '/generate/getPath', '代码生成路径');
INSERT INTO `t_admin_authority` VALUES ('291', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 1, '287', 'POST', '/generate/generateCode', '生成代码');

-- ----------------------------
-- Table structure for t_admin_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_dictionary`;
CREATE TABLE `t_admin_dictionary`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(自动插入)',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间(自动插入)',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典类型',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典名称',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父Id',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描叙',
  `sort` int(11) DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_dictionary
-- ----------------------------
INSERT INTO `t_admin_dictionary` VALUES ('1', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, 'gender', '性别', '0', '性别(1-男，0-女)', 1);
INSERT INTO `t_admin_dictionary` VALUES ('14', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, 'tx-vip', '腾讯会员', '0', '马化腾', 2);
INSERT INTO `t_admin_dictionary` VALUES ('15', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, '1', '视频会员', '14', '腾讯视频VIP特权', 0);
INSERT INTO `t_admin_dictionary` VALUES ('31', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, '2', '绿砖', '14', 'QQ音乐会员', 0);
INSERT INTO `t_admin_dictionary` VALUES ('32', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, 'tx_sp', '腾讯视频', '15', '腾讯视频', 0);
INSERT INTO `t_admin_dictionary` VALUES ('33', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, 'tx_bilbil', '哔哩哔哩视频', '15', '哔哩哔哩大会员', 0);
INSERT INTO `t_admin_dictionary` VALUES ('34', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, 'item', '项目', '0', '所有项目顶级字段', 3);
INSERT INTO `t_admin_dictionary` VALUES ('35', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 10:07:15', 0, 0, 'item-xj-admin', 'xj-admin', '34', '个人开发项目--通用后台管理系统', 0);
INSERT INTO `t_admin_dictionary` VALUES ('36', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 10:07:47', 0, 0, 'item-xj-colud', 'xj-colud', '34', '个人搭建的基于spring-colud-ailbaba微服务基础架构', 0);
INSERT INTO `t_admin_dictionary` VALUES ('5', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, '1', '男', '1', '1-男', 0);
INSERT INTO `t_admin_dictionary` VALUES ('6', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 09:29:07', 0, 0, '0', '女', '1', '0-女', 0);

-- ----------------------------
-- Table structure for t_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_menu`;
CREATE TABLE `t_admin_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(自动插入)',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间(自动插入)',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '指定父id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单名',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单url',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '图标',
  `sort` int(11) DEFAULT 0 COMMENT '排序',
  `root` int(1) DEFAULT 0 COMMENT '目录级别(1-系统, 2-菜单 ，3-页面, 4-按钮)',
  `state` int(1) DEFAULT 0 COMMENT '0-启用 1-禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES ('1', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '0', '兮家-系统管理', '', '0', 1, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('10', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '9', '用户管理', '', '/', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('100', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '外链', '', 'layui-icon-file-b', 600, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('101', NULL, NULL, '2020-07-25 09:29:38', '2020-07-31 00:13:40', 0, 0, '134', 'layui模板', 'http://localhost:9049/views/index.html', 'layui-icon-file-b', 60001, 3, 1);
INSERT INTO `t_admin_menu` VALUES ('102', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', 'layui官网', 'https://www.layui.com', 'layui-icon-file-b', 60002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('11', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '10', '用户信息', '/page/game_user_user', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('123', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '135', '我的CSDN', 'https://blog.csdn.net/qq_41463655', 'layui-icon-file-b', 60006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('124', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '百度', 'http://www.baidu.com', 'layui-icon-file-b', 60005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('125', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '135', '我的码云', 'https://gitee.com/wslxm', 'layui-icon-file-b', 60004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('126', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '阿里云', 'https://homenew.console.aliyun.com', 'layui-icon-file-b', 60003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879303106289665', NULL, NULL, '2020-07-31 00:49:06', '2020-07-31 01:26:21', 0, 0, '1', 'MD编辑器', '', 'layui-icon-file-b', 500, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879370219347969', NULL, NULL, '2020-07-31 00:49:22', '2020-07-31 01:25:51', 0, 0, '1288879303106289665', '编辑', '/page/modules_dev_markdown_index', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879436422242305', NULL, NULL, '2020-07-31 00:49:38', '2020-07-31 01:25:54', 0, 0, '1288879303106289665', '展示', '/page/modules_dev_markdown_detail', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('129', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '蚂蚁课堂', 'http://www.mayikt.com/', 'layui-icon-file-b', 60007, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('13', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '0', '测试系统', '', '1', 3, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('130', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '腾讯课堂', 'https://ke.qq.com/user/index/index.html#/plan/cid=291872&term_id=102601151', 'layui-icon-file-b', 60008, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('131', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '133', '哔哩哔哩', 'https://www.bilibili.com/', 'layui-icon-file-b', 60009, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('133', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:23', 0, 0, '100', '娱乐', '', 'layui-icon-file-b', 60003, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('134', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:26', 0, 0, '100', '工作学习', '', 'layui-icon-file-b', 60002, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('135', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:10', 0, 0, '100', '我的系列', '', 'layui-icon-file-b', 60001, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('136', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '133', 'bilbil视频下载', 'https://xbeibeix.com/api/bilibili/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('137', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '蚂蚁课堂资料下载', 'http://down.mayikt.com/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('138', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:47:32', 0, 0, '95', 'BUG修复', '/page/modules_dev_devBug_devBug', 'layui-icon-file-b', 40002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('139', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:30', 0, 0, '100', '在线工具', '', 'layui-icon-file-b', 60004, 2, 1);
INSERT INTO `t_admin_menu` VALUES ('14', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '13', '一级菜单', '', '1', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('140', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:22:51', 0, 0, '91', '聊天室', '/page/others_websocket_websocket', 'layui-icon-file-b', 50002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('141', NULL, NULL, '2020-07-30 18:16:24', '2020-07-30 18:20:12', 0, 0, '1', '代码生成', '', 'layui-icon-file-b', 300, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('15', NULL, NULL, '2020-07-25 09:29:38', '2020-07-31 00:13:34', 0, 0, '14', '页面', '/aaaa', '1', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('16', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '14', '二级菜单', '', '1', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('21', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:21', 0, 0, '4', '系统用户', '/page/modules_admin_user_user', '', 10002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('22', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:25', 0, 0, '4', '角色管理', '/page/modules_admin_role_role', '', 10003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('23', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:29', 0, 0, '5', '角色菜单权限', '/page/modules_admin_role_roleMenuAuth', '', 20001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('24', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:33', 0, 0, '5', '角色URL权限', '/page/modules_admin_role_roleUrlAuth', '', 20002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('25', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:37', 0, 0, '4', '字典管理', '/page/modules_admin_dictionary_dictionary', '', 10004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('27', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:40', 0, 0, '14', '登录', '/page/modules_admin_user_login', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('30', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:19:04', 0, 0, '141', '数据库表', '/page/others_generatecode_dataBase_dataBase', '1', 40006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('4', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '系统管理', '', 'layui-icon-set', 100, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('40', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '13', 'aaa', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('43', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '43', 'ccc', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('5', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '权限管理', '', 'layui-icon-util', 200, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('7', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:37:20', 0, 0, '4', '菜单管理', '/page/modules_admin_menu_menu', 'layui-icon-home', 10001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('9', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '0', '兮家-次元空间', '', '0', 2, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('91', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '9', '单页应用', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('92', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:22:44', 0, 0, '91', '栅格应用', '/page/others_front_show_show', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('93', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:22:48', 0, 0, '91', '文字注释', '/page/others_front_symbol_main', 'layui-icon-file-b', 50001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('95', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '开发', '', 'layui-icon-file-b', 400, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('96', NULL, NULL, '2020-07-25 09:29:38', '2020-07-31 15:14:31', 0, 0, '95', '开发规范', '/page/modules_dev_devNorm_devNorm', 'layui-icon-file-b', 40005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('97', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:46:27', 0, 0, '95', '开发任务', '/page/modules_dev_devTask_devTask', 'layui-icon-file-b', 40001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('98', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:29:24', 0, 0, '95', '更新记录', '/2', 'layui-icon-file-b', 40004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('99', NULL, NULL, '2020-07-25 09:29:38', '2020-07-31 00:40:56', 0, 0, '95', '学习计划', '/page/modules_dev_devStudy_devStudy', 'layui-icon-file-b', 40003, 3, 0);

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(自动插入)',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间(自动插入)',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描叙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('1', NULL, NULL, '2020-07-25 09:30:08', '2020-07-25 09:30:08', 0, 0, '系统管理员', '1');
INSERT INTO `t_admin_role` VALUES ('19', NULL, NULL, '2020-07-25 09:30:08', '2020-07-25 09:30:08', 0, 0, '腾讯会员', '1');
INSERT INTO `t_admin_role` VALUES ('2', NULL, NULL, '2020-07-25 09:30:08', '2020-07-25 09:30:08', 0, 0, '开发人员', '1');
INSERT INTO `t_admin_role` VALUES ('28', NULL, NULL, '2020-07-25 09:30:08', '2020-07-25 09:30:08', 0, 0, 'bilbil会员', '1');
INSERT INTO `t_admin_role` VALUES ('29', NULL, NULL, '2020-07-25 09:30:08', '2020-07-25 09:30:08', 0, 0, '爱奇艺会员', '1');
INSERT INTO `t_admin_role` VALUES ('3', NULL, NULL, '2020-07-25 09:30:08', '2020-07-25 09:30:08', 0, 0, '测试人员', '1');
INSERT INTO `t_admin_role` VALUES ('31', NULL, NULL, '2020-07-25 09:30:08', '2020-07-25 09:30:08', 0, 0, '系统体验用户', '开发给第三方人员使用的角色，多数功能接口权限受限');

-- ----------------------------
-- Table structure for t_admin_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_auth`;
CREATE TABLE `t_admin_role_auth`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(自动插入)',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间(自动插入)',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `auth_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色/接口权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_auth
-- ----------------------------
INSERT INTO `t_admin_role_auth` VALUES ('1289497217505181698', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185192263682', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217521958914', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185209040898', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217521958915', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185217429506', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217530347522', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185225818113', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217530347523', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185234206721', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217538736129', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185250983937', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217538736130', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185255178241', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217538736131', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185263566849', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217538736132', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185271955458', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217538736133', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185276149762', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217538736134', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185292926977', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217547124737', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185301315586', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217547124738', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185305509890', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217551319041', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185318092802', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217551319042', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185322287105', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217551319043', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185334870018', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217559707650', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185343258626', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217559707651', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185355841537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217563901954', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185360035841', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217563901955', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497185368424450', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217563901956', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '235', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217568096257', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '236', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217568096258', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '237', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217576484866', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '238', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217576484867', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '1289497183933972482', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217576484868', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '279', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217576484869', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '280', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217576484870', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '281', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217576484871', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '282', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217584873474', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '283', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217584873475', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '246', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217584873476', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '247', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217584873477', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '248', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217584873478', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '249', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217589067777', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '250', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217589067778', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '252', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217593262082', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '261', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217593262083', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '254', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217593262084', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '255', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217597456385', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '256', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217597456386', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '257', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217597456387', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '258', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217597456388', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '269', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217605844993', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '270', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217605844994', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '271', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217605844995', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '273', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217614233602', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '274', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217614233603', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '259', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217614233604', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '265', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217614233605', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '266', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217618427905', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '267', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217618427906', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '275', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217618427907', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '276', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217618427908', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '277', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217618427909', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '278', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217618427910', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '284', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217618427911', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '285', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217626816514', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '286', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217626816515', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '287', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217626816516', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '289', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217631010817', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '290', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497217631010818', NULL, NULL, '2020-08-01 17:44:29', '2020-08-01 17:44:29', 0, 0, '291', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517704101890', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '1289497185250983937', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517704101891', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '1289497185301315586', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296194', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '1289497185360035841', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296195', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '236', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296196', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '282', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296197', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '249', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296198', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '252', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296199', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '261', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296200', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '257', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296201', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '274', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296202', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '275', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517708296203', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '278', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517712490497', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '285', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517712490498', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '286', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517712490499', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '289', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1289497517712490500', NULL, NULL, '2020-08-01 17:45:40', '2020-08-01 17:45:40', 0, 0, '290', '31');

-- ----------------------------
-- Table structure for t_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(自动插入)',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间(自动插入)',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色/菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------
INSERT INTO `t_admin_role_menu` VALUES ('1288879745043324930', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '1', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745047519233', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '1288879303106289665', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745055907841', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '1288879436422242305', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745055907842', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '1288879370219347969', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745060102146', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '4', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745060102147', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '7', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745060102148', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '21', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745060102149', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '22', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745060102150', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '25', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296450', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '5', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296451', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '23', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296452', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '24', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296453', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '141', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296454', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '30', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296455', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '95', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296456', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '97', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296457', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '138', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296458', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '99', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745064296459', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '98', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745072685057', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '96', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745072685058', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '100', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745072685059', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '135', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745072685060', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '125', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745072685061', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '123', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745072685062', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '134', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745072685063', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '137', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745076879362', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '101', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745076879363', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '102', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745076879364', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '126', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745076879365', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '124', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745076879366', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '129', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745076879367', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '130', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745081073665', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '133', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745081073666', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '136', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745081073667', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '131', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745081073668', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '139', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745081073669', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '9', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745081073670', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '10', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745081073671', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '11', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745085267969', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '91', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745085267970', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '92', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745085267971', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '93', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745085267972', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '140', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745085267973', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '13', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745085267974', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '14', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745085267975', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '15', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745089462274', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '27', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745089462275', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '16', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1288879745089462276', NULL, NULL, '2020-07-31 00:50:52', '2020-07-31 00:50:52', 0, 0, '40', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387684872194', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '1', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387684872195', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '4', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387684872196', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '7', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387684872197', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '21', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387684872198', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '22', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387684872199', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '25', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260802', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '5', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260803', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '23', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260804', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '24', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260805', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '141', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260806', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '30', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260807', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '95', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260808', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '97', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260809', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '138', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260810', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '99', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387693260811', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '98', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455106', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '96', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455107', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '1288879303106289665', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455108', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '1288879436422242305', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455109', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '1288879370219347969', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455110', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '100', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455111', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '135', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455112', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '125', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455113', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '123', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455114', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '134', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455115', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '137', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387697455116', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '101', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649410', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '102', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649411', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '126', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649412', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '124', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649413', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '129', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649414', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '130', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649415', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '133', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649416', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '136', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649417', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '131', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649418', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '139', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649419', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '9', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387701649420', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '10', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843713', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '11', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843714', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '91', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843715', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '92', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843716', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '93', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843717', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '140', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843718', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '13', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843719', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '14', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843720', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '15', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843721', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '27', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843722', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '16', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1289497387705843723', NULL, NULL, '2020-08-01 17:45:09', '2020-08-01 17:45:09', 0, 0, '40', '31');
INSERT INTO `t_admin_role_menu` VALUES ('474', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '1', '2');
INSERT INTO `t_admin_role_menu` VALUES ('475', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '4', '2');
INSERT INTO `t_admin_role_menu` VALUES ('476', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '7', '2');
INSERT INTO `t_admin_role_menu` VALUES ('477', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '21', '2');
INSERT INTO `t_admin_role_menu` VALUES ('478', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '22', '2');
INSERT INTO `t_admin_role_menu` VALUES ('479', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '25', '2');
INSERT INTO `t_admin_role_menu` VALUES ('480', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '5', '2');
INSERT INTO `t_admin_role_menu` VALUES ('481', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '23', '2');
INSERT INTO `t_admin_role_menu` VALUES ('482', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '24', '2');
INSERT INTO `t_admin_role_menu` VALUES ('483', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '6', '2');
INSERT INTO `t_admin_role_menu` VALUES ('484', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '30', '2');
INSERT INTO `t_admin_role_menu` VALUES ('485', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '19', '2');
INSERT INTO `t_admin_role_menu` VALUES ('486', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '9', '2');
INSERT INTO `t_admin_role_menu` VALUES ('487', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '10', '2');
INSERT INTO `t_admin_role_menu` VALUES ('488', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '11', '2');
INSERT INTO `t_admin_role_menu` VALUES ('489', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '38', '2');
INSERT INTO `t_admin_role_menu` VALUES ('490', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '37', '2');
INSERT INTO `t_admin_role_menu` VALUES ('491', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '13', '2');
INSERT INTO `t_admin_role_menu` VALUES ('492', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '14', '2');
INSERT INTO `t_admin_role_menu` VALUES ('493', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '15', '2');
INSERT INTO `t_admin_role_menu` VALUES ('494', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '16', '2');
INSERT INTO `t_admin_role_menu` VALUES ('495', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '17', '2');
INSERT INTO `t_admin_role_menu` VALUES ('496', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '27', '2');
INSERT INTO `t_admin_role_menu` VALUES ('497', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '39', '2');
INSERT INTO `t_admin_role_menu` VALUES ('498', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '40', '2');
INSERT INTO `t_admin_role_menu` VALUES ('499', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '41', '2');
INSERT INTO `t_admin_role_menu` VALUES ('500', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '42', '2');
INSERT INTO `t_admin_role_menu` VALUES ('501', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '43', '2');
INSERT INTO `t_admin_role_menu` VALUES ('502', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '46', '2');
INSERT INTO `t_admin_role_menu` VALUES ('503', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '66', '2');
INSERT INTO `t_admin_role_menu` VALUES ('504', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '79', '2');
INSERT INTO `t_admin_role_menu` VALUES ('505', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '1', '3');
INSERT INTO `t_admin_role_menu` VALUES ('506', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '4', '3');
INSERT INTO `t_admin_role_menu` VALUES ('507', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '7', '3');
INSERT INTO `t_admin_role_menu` VALUES ('508', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '21', '3');
INSERT INTO `t_admin_role_menu` VALUES ('509', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '22', '3');
INSERT INTO `t_admin_role_menu` VALUES ('510', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '25', '3');
INSERT INTO `t_admin_role_menu` VALUES ('511', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '5', '3');
INSERT INTO `t_admin_role_menu` VALUES ('512', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '23', '3');
INSERT INTO `t_admin_role_menu` VALUES ('513', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '24', '3');
INSERT INTO `t_admin_role_menu` VALUES ('514', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '6', '3');
INSERT INTO `t_admin_role_menu` VALUES ('515', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '30', '3');
INSERT INTO `t_admin_role_menu` VALUES ('516', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '19', '3');
INSERT INTO `t_admin_role_menu` VALUES ('517', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '9', '3');
INSERT INTO `t_admin_role_menu` VALUES ('518', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '10', '3');
INSERT INTO `t_admin_role_menu` VALUES ('519', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '11', '3');
INSERT INTO `t_admin_role_menu` VALUES ('520', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '38', '3');
INSERT INTO `t_admin_role_menu` VALUES ('521', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '37', '3');
INSERT INTO `t_admin_role_menu` VALUES ('522', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '13', '3');
INSERT INTO `t_admin_role_menu` VALUES ('523', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '14', '3');
INSERT INTO `t_admin_role_menu` VALUES ('524', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '15', '3');
INSERT INTO `t_admin_role_menu` VALUES ('525', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '16', '3');
INSERT INTO `t_admin_role_menu` VALUES ('526', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '17', '3');
INSERT INTO `t_admin_role_menu` VALUES ('527', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '27', '3');
INSERT INTO `t_admin_role_menu` VALUES ('528', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '39', '3');
INSERT INTO `t_admin_role_menu` VALUES ('529', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '40', '3');
INSERT INTO `t_admin_role_menu` VALUES ('530', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '41', '3');
INSERT INTO `t_admin_role_menu` VALUES ('531', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '42', '3');
INSERT INTO `t_admin_role_menu` VALUES ('532', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '43', '3');
INSERT INTO `t_admin_role_menu` VALUES ('533', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '46', '3');
INSERT INTO `t_admin_role_menu` VALUES ('534', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '66', '3');
INSERT INTO `t_admin_role_menu` VALUES ('535', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '79', '3');
INSERT INTO `t_admin_role_menu` VALUES ('71', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '5', '0');
INSERT INTO `t_admin_role_menu` VALUES ('72', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '23', '0');
INSERT INTO `t_admin_role_menu` VALUES ('73', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '4', '0');
INSERT INTO `t_admin_role_menu` VALUES ('74', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '7', '0');

-- ----------------------------
-- Table structure for t_admin_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_user`;
CREATE TABLE `t_admin_role_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(自动插入)',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间(自动插入)',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色/用户关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_user
-- ----------------------------
INSERT INTO `t_admin_role_user` VALUES ('1', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '1', '1');
INSERT INTO `t_admin_role_user` VALUES ('42', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '1');
INSERT INTO `t_admin_role_user` VALUES ('43', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '2');
INSERT INTO `t_admin_role_user` VALUES ('44', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '3');
INSERT INTO `t_admin_role_user` VALUES ('45', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '1');
INSERT INTO `t_admin_role_user` VALUES ('46', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '2');
INSERT INTO `t_admin_role_user` VALUES ('47', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '3');
INSERT INTO `t_admin_role_user` VALUES ('48', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '20', '31');
INSERT INTO `t_admin_role_user` VALUES ('49', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '42', '2');
INSERT INTO `t_admin_role_user` VALUES ('7', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '1', '2');

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(自动插入)',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间(自动插入)',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `head` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号/用户/手机号',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `age` int(3) NOT NULL COMMENT '年龄',
  `gender` int(1) NOT NULL DEFAULT 0 COMMENT '性别（1男，0女）',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '是否禁用（0-否，1-是）',
  `reg_time` datetime(0) NOT NULL COMMENT '注册时间',
  `ent_time` datetime(0) DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', NULL, NULL, '2020-07-25 09:31:07', '2020-08-01 17:43:54', 0, 0, 'http://xijia.plus/File/image/7f59169cc42b4a938c5b7a72196f0d65-timg (8).jpg', '1720696548', '王松', '527w10n8c', '四川成都', 23, 1, 0, '2019-11-14 15:17:50', '2020-08-01 17:43:55');
INSERT INTO `t_admin_user` VALUES ('20', NULL, NULL, '2020-07-25 09:31:07', '2020-07-25 09:31:07', 0, 0, 'http://xijia.plus/oss/file/image/head/20200712000102739895-1.png', 'admin', '游客', 'admin', '四川成都', 0, 0, 0, '2020-01-31 10:15:07', NULL);
INSERT INTO `t_admin_user` VALUES ('42', NULL, NULL, '2020-07-25 09:31:07', '2020-07-31 00:13:45', 0, 0, 'http://xijia.plus/oss/file/image/head/20200711235225041786-3.png', '17628689969', '李四', '527w10n8c', '四川成都', 22, 1, 0, '2020-07-11 23:53:24', NULL);

-- ----------------------------
-- Table structure for t_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_basic`;
CREATE TABLE `t_basic`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据条目主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据最后更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间 ==创建数据自动获取当前时间插入',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '数据最后更新时间  ==更新数据自动获取当前时间更新',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除字段 ==0：正常 (默认)  1：删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁 ==0 (默认)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统基表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_dev_bug
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_bug`;
CREATE TABLE `t_dev_bug`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：正常  1：删除）',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `task_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指派给id',
  `item` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目（字典表code）',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务内容',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT '任务类型(1-管理端 2-用户端 3-app端  4-所有端 )',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)',
  `planned_time` datetime(0) NOT NULL COMMENT '计划完成时间',
  `ent_time` datetime(0) DEFAULT NULL COMMENT '实际完成时间',
  `estimate_time` double(11, 2) NOT NULL COMMENT '预计耗时(小时)',
  `take_up_time` double(11, 2) DEFAULT NULL COMMENT '实际耗时(小时)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '开发任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_bug
-- ----------------------------
INSERT INTO `t_dev_bug` VALUES ('19', '2020-07-26 09:45:52', NULL, NULL, '2020-07-26 09:54:19', 0, 0, '1', 'item-xj-admin', 'bug任务一', 'bug内容222', 1, 0, '2020-08-08 00:00:00', NULL, 2.00, NULL);

-- ----------------------------
-- Table structure for t_dev_norm
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_norm`;
CREATE TABLE `t_dev_norm`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：正常  1：删除）',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规范名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规范内容(md-富文本)',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '开发规范' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_norm
-- ----------------------------
INSERT INTO `t_dev_norm` VALUES ('1289116285992501250', '2020-07-31 16:30:47', NULL, NULL, '2020-07-31 17:12:36', 0, 0, '测试添加', '啊是发撒\nsafsafasface[吐] ', 0);
INSERT INTO `t_dev_norm` VALUES ('1289126554407862273', '2020-07-31 17:11:36', NULL, NULL, '2020-07-31 17:15:20', 0, 0, '测试编辑', '\n表头|表头|表头\n:---:|:--:|:---:\n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n', 0);
INSERT INTO `t_dev_norm` VALUES ('1289133797173895169', '2020-07-31 17:40:22', NULL, NULL, '2020-07-31 17:40:22', 0, 0, '测试-数据查看', 'face[嘻嘻] face[嘻嘻] face[嘻嘻]\n\n![图片加载失败](https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1576026414&di=4121c010940f72cab1cbcea65e698d0f&src=http://hbimg.b0.upaiyun.com/b1cce6f996734bdbb9b3fb9ef7705deabc980e35493b-ysf8BZ_fw658)\n\n[http://www.baidu.com](http://www.baidu.com)\n\n - 是的发送的\n - 阿斯顿发生大\n - 阿斯顿发斯蒂芬\n\n\n1. 阿斯顿发斯蒂芬\n2. 阿斯顿发送到发士大夫\n3. 阿斯顿发送到发大\n\n\n\n~~~\n	public static void main(String[] args){\n		System.out.print(\"Hello World\");\n	}\n~~~\n\n -----\n\n\n> Java世界上最好的语言\n\n\n -  跨平台\n -  面向对象\n -  牛掰\n\n -----\n\n\n1. 大众化\n2. 应用广\n3. 方向全\n\n -----\n\n\n表头|表头|表头\n:---:|:--:|:---:\n内容|内容|内容\n\n -----\n\n\n强制换行（双空格加回车）\n\n\n\n强制换行\n		', 0);
INSERT INTO `t_dev_norm` VALUES ('1289149437230190594', '2020-07-31 18:42:31', NULL, NULL, '2020-07-31 18:48:18', 0, 0, '接口方法统一命名', '\n## 方法统一命名\n\n### 查询\n| 方法 | 方法命名| 必传参数  |\n|:--------| :-------------| :-------------:|\n| ID查询 | findId|id|\n| 分页查询 | findPage | current=页数 size=记录数|\n| 所有查询 | findList | 无|\n| 查询 必须 xx+yy 条件 | findXxAndYy | xx && yy|\n| 查询 根据 xx+yy 其中一个| findXxOrYy | xx 或 yy|\n\n### 添加\n| 方法 | 方法命名  | 必传参数 |\n|:--------| :-------------| :-------------:|\n| 添加 | add  或  insert | dto\n\n### 修改\n| 方法 | 方法命名  | 必传参数 |\n|:--------| :-------------| :-------------:|\n| ID编辑所有 | upd | dto+id|\n| 编辑 xx 字段 | updXx |  xx|\n| 编辑 xx + yy字段 | updXxAndYy | xx + yy|\n\n### 删除\n| 方法 | 方法命名 | 必传参数      |\n|:--------| :-------------| :-------------:|\n| ID删除 | removeId |id|\n| 根据 xx字段删除 | removeXx | xx|\n| 根据 xx + yy 字段删除 | removeXxAndYy | xx + yy|\n\n### 判断\n| 方法 | 方法命名 | 必传参数   | 返回\n|:--------| :-------------| :-------------:| :-------------:|\n| 判断???  | is???  | 判断必传递参数 | true 或 false|\n', 0);

-- ----------------------------
-- Table structure for t_dev_renew
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_renew`;
CREATE TABLE `t_dev_renew`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：正常  1：删除）',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新内容(md-富文本)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '更新内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_dev_study
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_study`;
CREATE TABLE `t_dev_study`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：正常  1：删除）',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '学习内容(md-富文本)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学习计划' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_study
-- ----------------------------
INSERT INTO `t_dev_study` VALUES ('1288877310027554818', '2020-07-31 00:41:11', NULL, NULL, '2020-07-31 00:41:11', 0, 0, '1', '2', 1);

-- ----------------------------
-- Table structure for t_dev_task
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_task`;
CREATE TABLE `t_dev_task`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：正常  1：删除）',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `task_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指派给id',
  `item` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目（字典表code）',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务内容',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT '任务类型(1-管理端 2-用户端 3-app端  4-所有端 )',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)',
  `planned_time` datetime(0) NOT NULL COMMENT '计划完成时间',
  `ent_time` datetime(0) DEFAULT NULL COMMENT '实际完成时间',
  `estimate_time` double(11, 2) NOT NULL COMMENT '预计耗时(小时)',
  `take_up_time` double(11, 2) DEFAULT NULL COMMENT '实际耗时(小时)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '开发任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_task
-- ----------------------------
INSERT INTO `t_dev_task` VALUES ('1', '2020-06-27 12:04:22', NULL, NULL, '2020-07-12 23:34:55', 0, 0, '1', 'item-xj-admin', '开发任务模块', '类型，状态搜索，开始，完成，撤销任务等', 1, 3, '2020-05-05 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('10', '2020-07-25 10:10:04', NULL, NULL, '2020-07-25 10:10:10', 0, 0, '1', 'item-xj-admin', '数据库表结构通用字段重定义', '除id外的6大通用字段\n-- 添加\nalter table t_dev_norm add `create_user`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT \'创建账户id\';\nalter table t_dev_norm add `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT \'更新账户id\';\nalter table t_dev_norm add `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT \'创建时间(自动插入)\';\nalter table t_dev_norm add `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT \'更新时间(自动插入)\';\nalter table t_dev_norm add `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT \'逻辑删除字段(0：正常 1：删除)\';\nalter table t_dev_norm add `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT \'乐观锁\';', 1, 2, '2020-07-25 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('11', '2020-07-25 10:11:52', NULL, NULL, '2020-07-25 10:17:58', 0, 0, '1', 'item-xj-admin', '任务字体颜色修改', '已完成改为绿色\n未开始改为红色\n正在执行默认粉红色\n撤销的默认黑色', 1, 2, '2020-07-25 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('12', '2020-07-25 10:18:52', NULL, NULL, '2020-07-25 10:39:28', 0, 0, '1', 'item-xj-admin', '添加任务完成时间', '任务完成后没有完成时间', 1, 2, '2020-07-25 00:00:00', '2020-07-25 10:39:28', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('13', '2020-07-25 10:49:21', NULL, NULL, '2020-07-25 10:51:31', 0, 0, '1', 'item-xj-admin', 'bug修复-完整功能', 'bug修复页的全部功能内容', 1, 0, '2020-08-09 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('14', '2020-07-25 10:51:01', NULL, NULL, '2020-07-25 10:51:01', 0, 0, '1', 'item-xj-admin', '学习计划-完整功能', '学习计划页的全部功能内容', 1, 0, '2020-08-08 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('15', '2020-07-25 10:52:30', NULL, NULL, '2020-07-25 10:52:30', 0, 0, '1', 'item-xj-admin', '更新记录-全功能', '更新记录也的全部功能内容', 1, 0, '2020-08-23 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('16', '2020-07-25 10:53:18', NULL, NULL, '2020-07-31 17:41:36', 0, 0, '1', 'item-xj-admin', '开发规范-全功能', '开发规范页的全部功能内容', 1, 2, '2020-08-30 00:00:00', '2020-07-31 17:41:37', 8.00, 3.00);
INSERT INTO `t_dev_task` VALUES ('18', '2020-07-25 12:24:11', NULL, NULL, '2020-07-25 12:24:28', 0, 0, '1', 'item-xj-admin', '密码加密', '用户密码进行MD5 加盐/加密', 1, 0, '2020-08-01 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('3', '2020-07-12 20:30:13', NULL, NULL, '2020-07-12 22:50:08', 0, 0, '1', 'item-xj-admin', '字典表自动刷新', '添加/编辑时未自动刷新页面数据，需优化功能', 1, 0, '2020-07-12 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('4', '2020-07-12 20:32:02', NULL, NULL, '2020-07-25 10:25:05', 0, 0, '1', 'item-xj-admin', '菜单图标', '添加和编辑时图标改为可选', 1, 0, '2020-07-18 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('7', '2020-07-12 23:26:44', NULL, NULL, '2020-07-25 10:41:23', 0, 0, '1', 'item-xj-admin', '开发任务模块', '完成输入实际耗时，撤销已完成删除完成时间及实际耗时', 1, 2, '2020-07-11 00:00:00', '2020-07-25 10:41:24', 2.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('8', '2020-07-12 23:29:02', NULL, NULL, '2020-07-30 17:08:14', 0, 0, '1', 'item-xj-admin', '开发任务模块', '添加任务详情页，鼠标移动到任务名或任务内存处自动弹出任务详情...', 1, 0, '2020-07-18 00:00:00', NULL, 0.00, NULL);
INSERT INTO `t_dev_task` VALUES ('9', '2020-07-12 23:39:11', NULL, NULL, '2020-07-25 11:25:14', 0, 0, '1', 'item-xj-admin', '每页记录数BUG处理', '选择了其他页数后面，点击搜索每页记录数被回滚到10', 4, 2, '2020-07-18 00:00:00', '2020-07-25 11:25:15', 2.00, 1.00);

SET FOREIGN_KEY_CHECKS = 1;
