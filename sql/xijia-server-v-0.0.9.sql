/*
 Navicat Premium Data Transfer

 Source Server         : 47.107.128.84
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 47.107.128.84:3306
 Source Schema         : spring-boot-plus2

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 05/03/2021 14:39:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_authority`;
CREATE TABLE `t_admin_authority`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限类Id(方法与类/层级关系展示)',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式(GET/POST/PUT/DELETE)',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限url',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限备注信息',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  `type` int(1) NOT NULL COMMENT '终端(字典code, 如 0-管理端 1-用户端 更多待定)',
  `state` int(1) NOT NULL COMMENT '授权状态(字典code   -1-表示类 0-无需登录 1-需登录 2-需登录+授权 )',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--权限接口' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES ('502027470577471488', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminAuthority', 'base--URL权限管理', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471489', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471488', 'GET', '/api/admin/adminAuthority/findByRoleIdAuthorityTreeChecked', '查询所有 || 根据角色ID选中 -> Tree ', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471490', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471488', 'PUT', '/api/admin/adminAuthority/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471491', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471488', 'PUT', '/api/admin/adminAuthority/refreshAuthority', '扫描权限', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471492', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471488', 'GET', '/api/admin/adminAuthority/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471493', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471488', 'GET', '/api/admin/adminAuthority/findByRoleIdList', '查询所有 || 根据角色ID选中', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471494', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminDictionary', 'base--字典管理', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471495', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471494', 'POST', '/api/admin/adminDictionary/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471496', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471494', 'DELETE', '/api/admin/adminDictionary/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471497', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471494', 'PUT', '/api/admin/adminDictionary/updBySort', '修改排序', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471498', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471494', 'GET', '/api/admin/adminDictionary/generateEnum', '生成枚举', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471501', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471494', 'GET', '/api/admin/adminDictionary/findCodeGroup', '查询所有-code分组', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471502', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471494', 'PUT', '/api/admin/adminDictionary/upd', '编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471505', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminMenu', 'base--菜单管理', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470577471506', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471505', 'POST', '/api/admin/adminMenu/insert', '菜单添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665792', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471505', 'GET', '/api/admin/adminMenu/findByPidOrRoleId', 'pid + roleId 查询菜单列表', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665793', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471505', 'GET', '/api/admin/adminMenu/findByPidOrRoleIdTree', 'pid + roleId 查询菜单列表', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665794', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471505', 'DELETE', '/api/admin/adminMenu/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665795', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471505', 'GET', '/api/admin/adminMenu/findTree', '左导航菜单', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665796', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471505', 'PUT', '/api/admin/adminMenu/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665797', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470577471505', 'GET', '/api/admin/adminMenu/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665798', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminRole', 'base--角色管理', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665799', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'POST', '/api/admin/adminRole/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665800', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'PUT', '/api/admin/adminRole/updRoleAuthAll', '所有角色拥有所有权限', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665801', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'PUT', '/api/admin/adminRole/updRoleAuth', '角色的URL权限分配', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665802', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'GET', '/api/admin/adminRole/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665803', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'GET', '/api/admin/adminRole/findUserRole', '获取用户的当前角色', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665804', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'DELETE', '/api/admin/adminRole/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665805', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'PUT', '/api/admin/adminRole/updUserRole', '用户的角色分配', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665806', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'PUT', '/api/admin/adminRole/updRoleMenu', '角色的菜单分配', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665807', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'PUT', '/api/admin/adminRole/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665808', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665798', 'GET', '/api/admin/adminRole/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665809', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminUser', 'base--用户管理', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665810', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'POST', '/api/admin/adminUser/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665812', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'GET', '/api/admin/adminUser/findUser', '个人信息', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665813', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'GET', '/api/admin/adminUser/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665814', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'DELETE', '/api/admin/adminUser/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665815', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'POST', '/api/admin/adminUser/login', '登录', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470581665816', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'POST', '/api/admin/adminUser/bindWeChatMq', '微信公众号openId绑定', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470585860096', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'GET', '/api/admin/adminUser/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470585860097', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'PUT', '/api/admin/adminUser/updByPassword', '当前登录用户密码修改', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470585860098', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'GET', '/api/admin/adminUser/findByRoleId', '获取指定角色的用户列表', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470585860099', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'DELETE', '/api/admin/adminUser/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470585860100', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'PUT', '/api/admin/adminUser/updResetPassword', '重置任意用户密码', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470585860101', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470581665809', 'PUT', '/api/admin/adminUser/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470590054424', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminDatasource', 'base-plus--代码生成数据源维护表', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470590054425', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'POST', '/api/admin/adminDatasource/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470590054426', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'GET', '/api/admin/adminDatasource/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470590054427', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'DELETE', '/api/admin/adminDatasource/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248704', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'GET', '/api/admin/adminDatasource/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248705', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'DELETE', '/api/admin/adminDatasource/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248706', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'POST', '/api/admin/adminDatasource/dataSourceTest', '数据源连接测试', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248707', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'PUT', '/api/admin/adminDatasource/updPwd', '修改/重置密码', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248708', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'PUT', '/api/admin/adminDatasource/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248709', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470590054424', 'GET', '/api/admin/adminDatasource/findList', '列表查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248720', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminBanner', 'base-plus--banner', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248721', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248720', 'POST', '/api/admin/adminBanner/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248722', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248720', 'GET', '/api/admin/adminBanner/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248723', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248720', 'DELETE', '/api/admin/adminBanner/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248724', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248720', 'GET', '/api/admin/adminBanner/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248725', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248720', 'DELETE', '/api/admin/adminBanner/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248726', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248720', 'PUT', '/api/admin/adminBanner/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248727', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminBlacklist', 'base-plus--黑名单', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248728', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248727', 'POST', '/api/admin/adminBlacklist/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248729', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248727', 'GET', '/api/admin/adminBlacklist/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248730', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248727', 'DELETE', '/api/admin/adminBlacklist/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248731', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248727', 'GET', '/api/admin/adminBlacklist/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248732', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248727', 'PUT', '/api/admin/adminBlacklist/updDisable', '禁用/启用', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248733', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248727', 'PUT', '/api/admin/adminBlacklist/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248734', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminConfig', 'base-plus--全局配置', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248735', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248734', 'POST', '/api/admin/adminConfig/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248736', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248734', 'GET', '/api/admin/adminConfig/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470594248737', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248734', 'DELETE', '/api/admin/adminConfig/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443008', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248734', 'GET', '/api/admin/adminConfig/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443010', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470594248734', 'PUT', '/api/admin/adminConfig/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443011', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminHelp', 'base-plus--帮助中心', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443012', NULL, NULL, '2020-12-24 17:04:47', '2021-01-29 16:07:45', 0, 351, '502027470598443011', 'POST', '/api/admin/adminHelp/insert', '添加', 0, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('502027470598443013', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443011', 'GET', '/api/admin/adminHelp/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443014', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443011', 'DELETE', '/api/admin/adminHelp/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443015', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443011', 'GET', '/api/admin/adminHelp/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443016', NULL, NULL, '2020-12-24 17:04:47', '2021-01-04 12:41:32', 0, 351, '502027470598443011', 'GET', '/api/admin/adminHelp/findById', '查看 -->  ID查询 -- 查看详情调用此方法获取数据,浏览量自动+1', 0, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('502027470598443017', NULL, NULL, '2020-12-24 17:04:47', '2021-01-04 12:41:31', 0, 351, '502027470598443011', 'GET', '/api/admin/adminHelp/findTree', '查看 -->  左侧菜单-- 帮助中心tree菜单', 0, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('502027470598443018', NULL, NULL, '2020-12-24 17:04:47', '2021-01-29 16:07:43', 0, 351, '502027470598443011', 'PUT', '/api/admin/adminHelp/upd', 'ID编辑', 0, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('502027470598443019', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminLog', 'base-plus--操作记录', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443020', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443019', 'POST', '/api/admin/adminLog/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443021', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443019', 'GET', '/api/admin/adminLog/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443022', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443019', 'DELETE', '/api/admin/adminLog/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443023', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443019', 'GET', '/api/admin/adminLog/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443024', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443019', 'DELETE', '/api/admin/adminLog/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443025', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443019', 'PUT', '/api/admin/adminLog/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443026', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '', '', '/api/admin/adminMsg', 'base-plus--消息通知', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('502027470598443027', NULL, NULL, '2020-12-24 17:04:47', '2020-12-24 17:04:47', 0, 351, '502027470598443026', 'GET', '/api/admin/adminMsg/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('508560134616780800', NULL, NULL, '2021-01-11 17:43:16', '2021-01-11 17:43:16', 0, 285, '', '', '/api/client/admin/dictionary', 'yh--base--字典管理', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('508560134616780801', NULL, NULL, '2021-01-11 17:43:16', '2021-01-11 17:43:16', 0, 285, '508560134616780800', 'GET', '/api/client/admin/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('508560134620975104', NULL, NULL, '2021-01-11 17:43:16', '2021-01-11 17:43:16', 0, 285, '508560134616780800', 'GET', '/api/client/admin/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('508560134620975105', NULL, NULL, '2021-01-11 17:43:16', '2021-01-11 17:43:16', 0, 285, '', '', '/api/client/xj/adminBanner', 'yh--base-plus--banner', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('508560134620975106', NULL, NULL, '2021-01-11 17:43:16', '2021-01-11 17:43:16', 0, 285, '508560134620975105', 'GET', '/api/client/xj/adminBanner/findByPosition', '位置查询', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('510791294763798528', NULL, NULL, '2021-01-17 22:29:05', '2021-01-17 22:29:05', 0, 234, '502027470577471494', 'GET', '/api/admin/adminDictionary/findByCode', 'Code查询(默认返回Tree数据, 可指定Tree或List)', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('512907437410488320', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 156, '', '', '/api/consumer/astory/xjAstoryCategory', '独立功能--段子分类(用户端)', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('512907437414682624', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 156, '512907437410488320', 'GET', '/api/consumer/astory/xjAstoryCategory/findList', '查询所有', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('512907437414682625', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 156, '', '', '/api/consumer/astory/xjAstory', '独立功能--段子(用户端)', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('512907437414682626', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 156, '512907437414682625', 'GET', '/api/consumer/astory/xjAstory/findPage', '分页查询', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('512907437465014272', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 156, '', '', '/api/tool/', '独立功能--相关工具', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('512907437465014273', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 156, '512907437465014272', 'GET', '/api/tool//fhConvert', '', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('512907437465014274', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 156, '512907437465014272', 'POST', '/api/tool//javaCodeRun', 'java代码运行器', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192236994236416', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/consumer/caipu/cpCategory', '独立功能--菜谱类别(用户端)', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192236994236417', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236994236416', 'GET', '/consumer/caipu/cpCategory/findTree', '查询所有 Tree', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192236994236418', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/consumer/caipu/cpInfo', '独立功能--菜谱(用户端)', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192236994236419', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236994236418', 'GET', '/consumer/caipu/cpInfo/findPage', '分页查询', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192236998430720', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236994236418', 'GET', '/consumer/caipu/cpInfo/findId', 'ID查询', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192236998430721', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/admin/astory/xjAstoryCategory', '独立功能--段子分类', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430722', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430721', 'POST', '/api/admin/astory/xjAstoryCategory/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430723', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430721', 'GET', '/api/admin/astory/xjAstoryCategory/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430724', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430721', 'GET', '/api/admin/astory/xjAstoryCategory/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430725', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430721', 'DELETE', '/api/admin/astory/xjAstoryCategory/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430726', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430721', 'GET', '/api/admin/astory/xjAstoryCategory/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430727', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430721', 'PUT', '/api/admin/astory/xjAstoryCategory/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430728', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430721', 'DELETE', '/api/admin/astory/xjAstoryCategory/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430729', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/admin/astory/xjAstory', '独立功能--段子', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430730', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430729', 'POST', '/api/admin/astory/xjAstory/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192236998430731', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430729', 'GET', '/api/admin/astory/xjAstory/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625024', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430729', 'GET', '/api/admin/astory/xjAstory/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625025', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430729', 'DELETE', '/api/admin/astory/xjAstory/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625026', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430729', 'PUT', '/api/admin/astory/xjAstory/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625027', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192236998430729', 'DELETE', '/api/admin/astory/xjAstory/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625028', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/admin/caipu/cpCategory', '独立功能--菜谱类别字典表', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625029', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'POST', '/api/admin/caipu/cpCategory/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625030', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'GET', '/api/admin/caipu/cpCategory/findTree', '查询所有 Tree', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625031', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'GET', '/api/admin/caipu/cpCategory/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625032', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'PUT', '/api/admin/caipu/cpCategory/upd', '编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625033', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'DELETE', '/api/admin/caipu/cpCategory/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625034', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'PUT', '/api/admin/caipu/cpCategory/updBySort', '修改排序', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625035', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'PUT', '/api/admin/caipu/cpCategory/insertInit', '初始数据写人', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237002625036', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'GET', '/api/admin/caipu/cpCategory/findByCode', 'Code查询(Tree)', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819328', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237002625028', 'GET', '/api/admin/caipu/cpCategory/findUpdPidTree', '变更父级查询所有 Tree', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819329', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/admin/caipu/cpInfo', '独立功能--菜谱表', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819330', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819329', 'POST', '/api/admin/caipu/cpInfo/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819331', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819329', 'GET', '/api/admin/caipu/cpInfo/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819332', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819329', 'GET', '/api/admin/caipu/cpInfo/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819333', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819329', 'DELETE', '/api/admin/caipu/cpInfo/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819334', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819329', 'PUT', '/api/admin/caipu/cpInfo/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819335', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819329', 'DELETE', '/api/admin/caipu/cpInfo/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819336', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/admin/file/xjFile', '独立功能--常用工具文件管理', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819337', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819336', 'POST', '/api/admin/file/xjFile/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819338', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819336', 'GET', '/api/admin/file/xjFile/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819339', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819336', 'GET', '/api/admin/file/xjFile/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819340', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819336', 'DELETE', '/api/admin/file/xjFile/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237006819341', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819336', 'PUT', '/api/admin/file/xjFile/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237011013632', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237006819336', 'DELETE', '/api/admin/file/xjFile/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237011013633', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/admin/test/file2', '额外功能表--常用工具文件管理', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237011013634', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237011013633', 'POST', '/api/admin/test/file2/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237011013635', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237011013633', 'GET', '/api/admin/test/file2/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237011013636', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237011013633', 'GET', '/api/admin/test/file2/findId', 'ID查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237011013637', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237011013633', 'DELETE', '/api/admin/test/file2/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237011013638', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237011013633', 'PUT', '/api/admin/test/file2/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237011013639', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237011013633', 'DELETE', '/api/admin/test/file2/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237015207936', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/client/xj/adminConfig', 'yh--base-plus--全局配置', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('522192237015207937', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237015207936', 'GET', '/api/client/xj/adminConfig/findByCode', 'CODE查询', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('522192237019402240', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '502027470577471494', 'GET', '/api/admin/adminDictionary/findDictCategory', '查询字典类别', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237027790848', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/admin/dataBase', 'base-plus--查询数据库表数据', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237027790849', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237027790848', 'GET', '/api/admin/dataBase/findTableField', '查询指定表下使用字段内容', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237027790850', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237027790848', 'GET', '/api/admin/dataBase/findTable', '查询所有表名', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237027790851', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/admin/generate', 'base-plus--代码生成', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237027790852', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237027790851', 'GET', '/api/admin/generate/getPath', '代码生成路径', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237027790853', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237027790851', 'POST', '/api/admin/generate/generateCode', '生成代码', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237031985152', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237027790851', 'POST', '/api/admin/generate/preview', '生成预览代码', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237036179456', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '502027470598443026', 'PUT', '/api/admin/adminMsg/read', '消息修改为已读', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('522192237036179457', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '', '', '/api/open/aliOssFile', 'AliYun --> OSS文件管理', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192237040373760', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237036179457', 'DELETE', '/api/open/aliOssFile/del', 'OSS-文件删除', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192237040373761', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237036179457', 'POST', '/api/open/aliOssFile/upload', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192237040373762', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237036179457', 'GET', '/api/open/aliOssFile/fileList', 'OSS-文件Object列表', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192237040373763', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237036179457', 'GET', '/api/open/aliOssFile/downloadZip', 'OSS-文件下载--多文件下载', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522192237040373764', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 22, '522192237036179457', 'GET', '/api/open/aliOssFile/download', 'OSS-文件下载--单文件下载', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522688804579053568', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 16, '', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522688804579053569', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 16, '522688804579053568', 'GET', '/api/open/websocket/getPath', '游客登录获取websocket连接地址', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522688804579053570', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 16, '522688804579053568', 'GET', '/api/open/websocket/getOnlineUsersList', '获取当前在线用户列表', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522688804579053571', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 16, '522688804579053568', 'GET', '/api/open/websocket/getOnlineCount', '获取在线人数', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('522688804579053572', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 16, '522688804579053568', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('525156518375591936', NULL, NULL, '2021-02-26 13:51:24', '2021-02-26 13:59:44', 0, 3, '502027470594248734', 'GET', '/api/admin/adminConfig/findByCode', 'CODE查询', 0, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('526560981065797632', NULL, NULL, '2021-03-02 10:52:13', '2021-03-02 10:52:13', 0, 1, '502027470598443026', 'GET', '/api/admin/adminMsg/findUnreadNum', '查询未读数量', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('526560981065797633', NULL, NULL, '2021-03-02 10:52:13', '2021-03-02 10:52:13', 0, 1, '', '', '/api/client/msg', 'yh--base-plus--消息通知', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('526560981069991936', NULL, NULL, '2021-03-02 10:52:13', '2021-03-02 10:52:13', 0, 1, '526560981065797633', 'GET', '/api/client/msg/findPage', '分页查询', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('526560981069991937', NULL, NULL, '2021-03-02 10:52:13', '2021-03-02 10:52:13', 0, 1, '526560981065797633', 'PUT', '/api/client/msg/read', '消息修改为已读', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('526560981069991938', NULL, NULL, '2021-03-02 10:52:13', '2021-03-02 10:52:13', 0, 1, '526560981065797633', 'GET', '/api/client/msg/findUnreadNum', '查询未读数量', 0, 1, 1);

-- ----------------------------
-- Table structure for t_admin_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_dictionary`;
CREATE TABLE `t_admin_dictionary`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父Id',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--字典' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_dictionary
-- ----------------------------
INSERT INTO `t_admin_dictionary` VALUES ('1290684671448936449', NULL, NULL, '2020-08-05 00:23:00', '2020-08-28 17:57:00', 0, 0, 'ENUMS', '枚举字典', '0', '状态/动态字段值，如：state，type，gender等, 可直接生成 前/ 后端枚举对象类代码', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290686507555844098', NULL, NULL, '2020-08-05 00:30:17', '2021-01-23 14:08:06', 0, 0, 'ADMIN', '系统模块枚举', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687277911076865', NULL, NULL, '2020-08-05 00:33:21', '2020-08-09 17:41:00', 0, 0, 'MENU_ROOT', '菜单级别', '1290686507555844098', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687351005212673', NULL, NULL, '2020-08-05 00:33:38', '2020-08-07 16:47:57', 0, 0, '1', '顶部菜单', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687461252493314', NULL, NULL, '2020-08-05 00:34:05', '2020-08-07 16:47:57', 0, 0, '2', '菜单', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687547940368386', NULL, NULL, '2020-08-05 00:34:25', '2020-08-07 16:47:57', 0, 0, '3', '页面', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688121255587841', NULL, NULL, '2020-08-05 00:36:42', '2020-08-09 17:39:03', 0, 0, 'BASE', '通用枚举', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688660164931586', NULL, NULL, '2020-08-05 00:38:51', '2020-09-25 09:28:49', 0, 0, 'GENDER', '性别', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688703043301378', NULL, NULL, '2020-08-05 00:39:01', '2020-08-07 16:47:58', 0, 0, '1', '男', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688742289403906', NULL, NULL, '2020-08-05 00:39:10', '2021-01-23 13:59:16', 0, 0, '2', '女', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1291341478399897601', NULL, NULL, '2020-08-06 11:52:54', '2021-02-05 14:46:54', 0, 0, '0', '未知', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469448399593474', NULL, NULL, '2020-08-20 15:29:38', '2020-08-20 15:29:38', 0, 0, 'DISABLE', '是否禁用', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469518025039873', NULL, NULL, '2020-08-20 15:29:55', '2020-08-20 15:29:55', 0, 0, '1', '禁用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469564455985154', NULL, NULL, '2020-08-20 15:30:06', '2020-08-20 15:30:06', 0, 0, '0', '启用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995475064434690', NULL, NULL, '2020-08-22 02:19:52', '2021-01-23 12:00:35', 0, 0, 'AUTHORITY_TYPE', '权限类型', '1290686507555844098', '默认类型, 来进行默认权限指定', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995560007479298', NULL, NULL, '2020-08-22 02:20:12', '2020-08-22 12:24:42', 0, 0, '0', '管理端', '1296995475064434690', '管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995610632728578', NULL, NULL, '2020-08-22 02:20:24', '2020-08-22 12:24:51', 0, 0, '1', '用户端', '1296995475064434690', '用户端, 类上标有该参数所有接口都会默认被列为-[需登录]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995742778470401', NULL, NULL, '2020-08-22 02:20:55', '2021-01-23 12:00:15', 0, 0, 'AUTHORITY_STATE', '权限状态', '1290686507555844098', '动态编辑权限状态', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995839977271297', NULL, NULL, '2020-08-22 02:21:19', '2020-11-23 00:25:33', 0, 0, '0', '无', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996062090833922', NULL, NULL, '2020-08-22 02:22:12', '2020-08-22 02:22:31', 0, 0, '1', '需登录', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996224863383554', NULL, NULL, '2020-08-22 02:22:50', '2020-08-22 02:22:50', 0, 0, '2', '需登录+授权', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1297705363906273282', NULL, NULL, '2020-08-24 01:20:43', '2021-01-23 11:50:33', 0, 0, '2', '通用接口', '1296995475064434690', '通用接口, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191840981327873', NULL, NULL, '2020-08-25 09:33:48', '2020-08-25 09:33:48', 0, 0, 'DELETED', '逻辑删除', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191898313269249', NULL, NULL, '2020-08-25 09:34:02', '2020-08-25 09:34:02', 0, 0, '0', '正常', '1298191840981327873', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191981998022658', NULL, NULL, '2020-08-25 09:34:22', '2020-08-25 09:34:22', 0, 0, '1', '已删除', '1298191840981327873', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194668697198594', NULL, NULL, '2020-08-25 09:45:02', '2021-01-23 14:02:29', 0, 0, 'BANNER_IS_SKIP', 'banner是否跳转', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194722350735361', NULL, NULL, '2020-08-25 09:45:15', '2020-09-24 21:40:23', 0, 0, '0', '否', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194801014906881', NULL, NULL, '2020-08-25 09:45:34', '2020-08-25 09:45:34', 0, 0, '1', '内部跳转', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194850285395969', NULL, NULL, '2020-08-25 09:45:46', '2020-08-25 09:45:46', 0, 0, '2', '外部跳转', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1303872194494435330', NULL, NULL, '2020-09-10 09:45:30', '2021-01-23 14:02:37', 0, 0, 'BANNER_POSITION', 'banner 位置', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1303872308608864257', NULL, NULL, '2020-09-10 09:45:57', '2021-01-23 14:02:12', 0, 0, '1', '用户端首页', '1303872194494435330', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580167513370625', NULL, NULL, '2020-09-23 09:33:17', '2021-01-23 14:06:12', 0, 0, 'MSG_USER_TYPE', '及时消息终端', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580236161544193', NULL, NULL, '2020-09-23 09:33:33', '2020-10-03 11:38:47', 0, 0, '1', '用户端', '1308580167513370625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580275248263169', NULL, NULL, '2020-09-23 09:33:42', '2020-09-23 09:33:42', 0, 0, '2', '管理端', '1308580167513370625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585499920769025', NULL, NULL, '2020-09-23 09:54:28', '2021-01-23 14:06:05', 0, 0, 'MSG_TYPE', '及时消息类型', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585615968772098', NULL, NULL, '2020-09-23 09:54:56', '2020-09-23 09:54:56', 0, 0, '1', '系统通知', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585669429370882', NULL, NULL, '2020-09-23 09:55:09', '2020-09-23 09:55:09', 0, 0, '2', '订单业务通知', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318470835622768641', NULL, NULL, '2020-10-20 08:35:17', '2021-01-23 14:06:36', 0, 0, 'HELP_CATEGORY', '帮助中心类别', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471097611579394', NULL, NULL, '2020-10-20 08:36:20', '2021-01-23 14:06:50', 0, 0, 'HELP_VERSION', '帮助中心版本', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471212665532417', NULL, NULL, '2020-10-20 08:36:47', '2020-11-03 22:59:31', 0, 0, '1', '采用技术', '1318470835622768641', '-', 0, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1318471270811168770', NULL, NULL, '2020-10-20 08:37:01', '2020-10-25 04:12:45', 0, 0, '3', '引入技术', '1318470835622768641', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471304038445058', NULL, NULL, '2020-10-20 08:37:09', '2020-10-25 02:52:45', 0, 0, '2', '系统功能', '1318470835622768641', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471387207299073', NULL, NULL, '2020-10-20 08:37:29', '2020-10-25 03:15:50', 0, 0, '6', '其他', '1318470835622768641', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471451912826882', NULL, NULL, '2020-10-20 08:37:44', '2020-10-25 02:51:29', 0, 0, '0', '开始使用', '1318470835622768641', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471572557787138', NULL, NULL, '2020-10-20 08:38:13', '2020-10-20 12:44:41', 0, 0, '0', '0.x ', '1318471097611579394', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318533693752475649', NULL, NULL, '2020-10-20 12:45:03', '2020-10-20 12:45:03', 0, 0, '1', '1.x', '1318471097611579394', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318533820672114690', NULL, NULL, '2020-10-20 12:45:33', '2020-10-20 12:46:07', 0, 0, '2', '1.5x ', '1318471097611579394', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318533884811411458', NULL, NULL, '2020-10-20 12:45:48', '2020-10-20 12:45:48', 0, 0, '3', '2.x', '1318471097611579394', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1320196942961070081', NULL, NULL, '2020-10-25 02:54:13', '2020-10-25 02:54:13', 0, 0, '4', '运维部署', '1318470835622768641', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1320202350207533058', NULL, NULL, '2020-10-25 03:15:43', '2020-10-25 03:18:23', 0, 0, '5', '常用技术文章', '1318470835622768641', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332329973427494913', NULL, NULL, '2020-11-27 22:26:31', '2021-01-23 14:05:31', 0, 0, 'BLACKLIST_TYPE', '黑/白名单类型', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332330105569042434', NULL, NULL, '2020-11-27 22:27:02', '2020-11-27 22:27:02', 0, 0, '1', '白名单', '1332329973427494913', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332330148963311619', NULL, NULL, '2020-11-27 22:27:13', '2020-11-27 22:27:13', 0, 0, '2', '黑名单', '1332329973427494913', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244241461600257', NULL, NULL, '2020-12-11 11:54:06', '2021-01-23 14:08:26', 0, 0, 'XJ', '系统增强功能枚举', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244337121091586', NULL, NULL, '2020-12-11 11:54:29', '2020-12-11 11:54:29', 0, 0, 'FILE_TYPE', '文件类型', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244429571940353', NULL, NULL, '2020-12-11 11:54:51', '2020-12-11 11:54:51', 0, 0, '1', '开发工具', '1337244337121091586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244524086386690', NULL, NULL, '2020-12-11 11:55:14', '2020-12-11 11:55:14', 0, 0, '2', '源码', '1337244337121091586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244620177891330', NULL, NULL, '2020-12-11 11:55:36', '2020-12-11 11:56:53', 0, 0, '3', '文档', '1337244337121091586', '-pdf /word 等 ', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244705590697985', NULL, NULL, '2020-12-11 11:55:57', '2020-12-11 11:55:57', 0, 0, '4', '图片', '1337244337121091586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244757147082754', NULL, NULL, '2020-12-11 11:56:09', '2020-12-11 11:56:09', 0, 0, '5', '音频', '1337244337121091586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244818803351554', NULL, NULL, '2020-12-11 11:56:24', '2020-12-11 11:56:24', 0, 0, '6', '视频', '1337244337121091586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1339487721529364483', NULL, NULL, '2020-12-17 16:28:53', '2020-12-17 16:28:53', 0, 0, '7', 'sql', '1337244337121091586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352826209829978114', NULL, NULL, '2021-01-23 11:51:17', '2021-01-23 11:51:17', 0, 0, '3', 'Oauth2 接口', '1296995475064434690', '接口默认默认需通过 appId，appSecret生成的 accessToken 来进行oauth2 令牌验证可访问', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352826389480407041', NULL, NULL, '2021-01-23 11:52:00', '2021-01-23 11:52:00', 0, 0, '3', '需Oauth2 授权', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856255286255617', NULL, NULL, '2021-01-23 13:50:40', '2021-01-23 14:07:55', 0, 0, 'PAY', '支付枚举', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856400451117058', NULL, NULL, '2021-01-23 13:51:15', '2021-01-23 13:51:15', 0, 0, 'PAY_TYPE', '支付类型', '1352856255286255617', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856492264431617', NULL, NULL, '2021-01-23 13:51:37', '2021-01-23 13:51:37', 0, 0, '1', '支付', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856528012484610', NULL, NULL, '2021-01-23 13:51:45', '2021-01-23 13:51:52', 0, 0, '2', '充值', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856603073748994', NULL, NULL, '2021-01-23 13:52:03', '2021-01-23 13:52:03', 0, 0, '3', '退款', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856663249428482', NULL, NULL, '2021-01-23 13:52:18', '2021-01-23 13:52:18', 0, 0, '4', '商家打款', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856892170346498', NULL, NULL, '2021-01-23 13:53:12', '2021-01-23 13:53:12', 0, 0, 'PAY_CHANNEL', '支付渠道', '1352856255286255617', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856932938981378', NULL, NULL, '2021-01-23 13:53:22', '2021-01-23 13:53:22', 0, 0, '1', '支付宝', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856979743219713', NULL, NULL, '2021-01-23 13:53:33', '2021-01-23 13:53:33', 0, 0, '2', '微信', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857025708597250', NULL, NULL, '2021-01-23 13:53:44', '2021-01-23 13:53:44', 0, 0, '3', '银联', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857215228223489', NULL, NULL, '2021-01-23 13:54:29', '2021-01-23 13:54:29', 0, 0, 'PAY_STATE', '支付状态', '1352856255286255617', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857264104448001', NULL, NULL, '2021-01-23 13:54:41', '2021-01-23 13:54:41', 0, 0, '0', '已发起', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857305888104450', NULL, NULL, '2021-01-23 13:54:51', '2021-01-23 13:54:51', 0, 0, '1', '回调成功', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857349592752130', NULL, NULL, '2021-01-23 13:55:01', '2021-01-23 13:55:01', 0, 0, '2', '交易失败', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857389069541377', NULL, NULL, '2021-01-23 13:55:11', '2021-01-23 13:55:11', 0, 0, '3', '交易成功', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857426407235585', NULL, NULL, '2021-01-23 13:55:20', '2021-01-23 13:55:20', 0, 0, '4', '订单异常', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857793505304577', NULL, NULL, '2021-01-23 13:56:47', '2021-01-23 13:57:42', 0, 0, 'PAY_BUSINESS', '支付业务', '1352856255286255617', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857906709569537', NULL, NULL, '2021-01-23 13:57:14', '2021-01-23 13:57:14', 0, 0, '1', '用户下单', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352858096959004674', NULL, NULL, '2021-01-23 13:57:59', '2021-01-23 13:58:14', 0, 0, '2', 'vip充值/续费', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528050694148097', NULL, NULL, '2021-02-05 11:14:43', '2021-02-05 11:14:43', 0, 0, 'WALLET_TYPE', '流水类型', '1352856255286255617', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528121372364801', NULL, NULL, '2021-02-05 11:15:00', '2021-02-05 11:15:00', 0, 0, '1', '收入', '1357528050694148097', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528154167627779', NULL, NULL, '2021-02-05 11:15:07', '2021-02-05 11:15:07', 0, 0, '2', '支出', '1357528050694148097', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612114939858945', NULL, NULL, '2021-02-05 16:48:45', '2021-02-05 16:48:45', 0, 0, 'IS_READ', '是否已读', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612150536916994', NULL, NULL, '2021-02-05 16:48:54', '2021-02-05 16:48:54', 0, 0, '0', '未读', '1357612114939858945', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612182854029315', NULL, NULL, '2021-02-05 16:49:01', '2021-02-05 16:49:01', 0, 0, '1', '已读', '1357612114939858945', '-', 0, 0);

-- ----------------------------
-- Table structure for t_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_menu`;
CREATE TABLE `t_admin_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指定父id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
  `two_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第二路由url, 前后端分离前端使用第二路由',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单url',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `root` int(1) NOT NULL DEFAULT 0 COMMENT '目录级别(1-系统, 2-菜单 ，3-页面, 4-按钮)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES ('1', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 12:43:58', 0, 0, '0', '兮家-系统管理', '', '', '0', 1, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('100', NULL, NULL, '2020-07-25 09:29:38', '2020-11-03 19:14:34', 0, 0, '1323584197721440258', '外链', NULL, '', 'layui-icon-file-b', 600, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('101', NULL, NULL, '2020-07-25 09:29:38', '2020-08-07 16:41:52', 0, 0, '134', 'layui模板', NULL, 'http://localhost:9049/views/index.html', 'layui-icon-file-b', 60001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('102', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', 'layui官网', NULL, 'https://www.layui.com', 'layui-icon-file-b', 60002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('123', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '135', '我的CSDN', NULL, 'https://blog.csdn.net/qq_41463655', 'layui-icon-file-b', 60006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('124', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '百度', NULL, 'http://www.baidu.com', 'layui-icon-file-b', 60005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('125', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '135', '我的码云', NULL, 'https://gitee.com/wslxm', 'layui-icon-file-b', 60004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('126', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '阿里云', NULL, 'https://homenew.console.aliyun.com', 'layui-icon-file-b', 60003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879303106289665', NULL, NULL, '2020-07-31 00:49:06', '2021-01-16 12:28:43', 0, 0, '1350298064077774850', 'MD编辑器', NULL, '', 'layui-icon-file-b', 500, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879370219347969', NULL, NULL, '2020-07-31 00:49:22', '2021-01-16 13:37:45', 0, 0, '1288879303106289665', '编辑', NULL, '/page/base_markdown_index', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879436422242305', NULL, NULL, '2020-07-31 00:49:38', '2021-01-16 13:37:47', 0, 0, '1288879303106289665', '展示', NULL, '/page/base_markdown_detail', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('129', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '蚂蚁课堂', NULL, 'http://www.mayikt.com/', 'layui-icon-file-b', 60007, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1297047088646905857', NULL, NULL, '2020-08-22 05:44:58', '2021-01-16 12:19:14', 0, 0, '4', '接口管理', NULL, '/page/modules_sys_admin_authority_authority', 'layui-icon-file-b', 10005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1297533242571763714', NULL, NULL, '2020-08-23 13:56:45', '2021-01-16 12:47:04', 0, 0, '1350297066072498179', 'banner 管理', NULL, '/page/modules_sys_xj_banner_banner', 'layui-icon-file-b', 10008, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('130', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '腾讯课堂', NULL, 'https://ke.qq.com/user/index/index.html#/plan/cid=291872&term_id=102601151', 'layui-icon-file-b', 60008, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('131', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '133', '哔哩哔哩', NULL, 'https://www.bilibili.com/', 'layui-icon-file-b', 60009, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1311827586636156929', NULL, NULL, '2020-10-02 08:37:23', '2021-01-16 12:47:07', 0, 0, '1350297066072498179', '全局配置', NULL, '/page/modules_sys_xj_config_config', 'layui-icon-file-b', 10009, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1312705440890540033', NULL, NULL, '2020-10-04 18:45:40', '2021-01-16 13:04:41', 0, 0, '1350307256670900225', '菜谱', '', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1312705547715268610', NULL, NULL, '2020-10-04 18:46:06', '2021-01-16 13:00:46', 0, 0, '1312705440890540033', '菜品管理', NULL, '/page/modules_yw_caipu_cpInfo_cpInfo', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1312755066842730498', NULL, NULL, '2020-10-04 22:02:52', '2021-01-16 13:00:48', 0, 0, '1312705440890540033', '菜品类别', NULL, '/page/modules_yw_caipu_cpCategory_cpCategory', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1317864829549146113', NULL, NULL, '2020-10-18 16:27:14', '2021-01-16 12:26:45', 0, 0, '1350298064077774850', '系统信息', NULL, '', 'layui-icon-file-b', 700, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1317865066573459457', NULL, NULL, '2020-10-18 16:28:11', '2020-10-29 02:31:42', 0, 0, '1317864829549146113', '系统监控', NULL, 'http://xijia.plus/bootAdmin', 'layui-icon-file-b', 70001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1318472952462770177', NULL, NULL, '2020-10-20 08:43:41', '2021-01-16 12:47:01', 0, 0, '1350297066072498179', '兮家手册', '', '/page/modules_sys_xj_help_help', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1318535862241251330', NULL, NULL, '2020-10-20 12:53:40', '2021-01-16 13:12:17', 0, 0, '1350305706179313666', '手册展示', NULL, '/page/client_help_index', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1319948617724743682', NULL, NULL, '2020-10-24 10:27:28', '2020-10-29 02:31:47', 0, 0, '1317864829549146113', '数据监控', NULL, 'http://xijia.plus/druid', 'layui-icon-file-b', 70002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1321432835319414785', NULL, NULL, '2020-10-28 12:45:12', '2021-01-16 12:47:10', 0, 0, '1350297066072498179', '系统日志', NULL, '/page/modules_sys_xj_log_log', 'layui-icon-file-b', 10012, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584197721440258', NULL, NULL, '2020-11-03 19:13:58', '2020-11-03 19:13:58', 0, 0, '0', '兮家-server', NULL, '', 'layui-icon-file-b', 3, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584742796410881', NULL, NULL, '2020-11-03 19:16:08', '2021-01-16 12:28:23', 0, 0, '1350298064077774850', '开发文档', NULL, '', 'layui-icon-file-b', 900, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584867987996674', NULL, NULL, '2020-11-03 19:16:38', '2020-11-03 19:17:06', 0, 0, '1323584742796410881', ' knife4j-ui', NULL, '/doc.html', 'layui-icon-file-b', 90001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584939857395714', NULL, NULL, '2020-11-03 19:16:55', '2020-11-03 19:16:55', 0, 0, '1323584742796410881', 'swagger-ui', NULL, '/swagger-ui.html', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('133', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:23', 0, 0, '100', '娱乐', NULL, '', 'layui-icon-file-b', 60003, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1330860076629590017', NULL, NULL, '2020-11-23 21:05:43', '2021-01-16 13:37:58', 0, 0, '1288879303106289665', 'new编辑', NULL, '/page/base_markdown2_index', 'layui-icon-file-b', 1, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1330860135886716929', NULL, NULL, '2020-11-23 21:05:57', '2021-01-16 13:38:03', 0, 0, '1288879303106289665', ' new展示', NULL, '/page/base_markdown2_detail', 'layui-icon-file-b', 1, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1332332514865364994', NULL, NULL, '2020-11-27 22:36:37', '2021-02-05 15:33:24', 0, 0, '1350297066072498179', '黑/白名单', '/page/modules_admin_adminBlacklist_adminBlacklist', '/page/modules_sys_xj_blacklist_blacklist', 'layui-icon-file-b', 10011, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1332869984937848833', NULL, NULL, '2020-11-29 10:12:22', '2021-01-16 13:23:12', 0, 0, '1350305706179313666', 'java 代码运行器', NULL, '/page/client_tool_javaCodeRun', 'layui-icon-file-b', 50000, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1335434955345399809', NULL, NULL, '2020-12-06 12:04:39', '2021-01-16 13:23:01', 0, 0, '1350305706179313666', 'new聊天室', NULL, '/page/client_websocket_lts', 'layui-icon-file-b', 50003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1339486097113202690', NULL, NULL, '2020-12-17 16:22:26', '2021-01-16 13:04:36', 0, 0, '1350307256670900225', '文件', '', '', 'layui-icon-file-b', 900, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1339486194576244738', NULL, NULL, '2020-12-17 16:22:49', '2021-01-16 13:04:20', 0, 0, '1339486097113202690', '文件管理', '', '/page/modules_yw_file_xjFile_xjFile', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1339574156777242625', NULL, NULL, '2020-12-17 22:12:22', '2021-01-16 13:22:30', 0, 0, '1350305706179313666', '文件查看', NULL, '/page/client_file_index', 'layui-icon-file-b', 2, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('134', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:26', 0, 0, '100', '工作学习', NULL, '', 'layui-icon-file-b', 60002, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1340651066982731778', NULL, NULL, '2020-12-20 21:31:37', '2021-01-16 13:04:46', 0, 0, '1350307256670900225', '段子', '', '', 'layui-icon-file-b', 1000, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1340651170208747521', NULL, NULL, '2020-12-20 21:32:01', '2021-01-16 13:01:10', 0, 0, '1340651066982731778', '段子管理', NULL, '/page/modules_yw_astory_xjAstory_xjAstory', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1340651277373214722', NULL, NULL, '2020-12-20 21:32:27', '2021-01-16 13:01:11', 0, 0, '1340651066982731778', '段子分类', NULL, '/page/modules_yw_astory_xjAstoryCategory_xjAstoryCategory', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('135', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:10', 0, 0, '100', '我的系列', NULL, '', 'layui-icon-file-b', 60001, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350297066072498179', NULL, NULL, '2021-01-16 12:21:22', '2021-01-16 12:21:30', 0, 0, '1', '系统增强功能', '', '', 'layui-icon-file-b', 101, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350298064077774850', NULL, NULL, '2021-01-16 12:25:20', '2021-01-16 13:03:10', 0, 0, '1', '系统功能', '', '-', 'layui-icon-file-b', 103, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350305706179313666', NULL, NULL, '2021-01-16 12:55:42', '2021-01-16 13:03:15', 0, 0, '1', '业务单页', '', '', 'layui-icon-file-b', 105, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350307256670900225', NULL, NULL, '2021-01-16 13:01:51', '2021-01-16 13:03:12', 0, 0, '1', '业务管理', '', '', 'layui-icon-file-b', 104, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('136', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '133', 'bilbil视频下载', NULL, 'https://xbeibeix.com/api/bilibili/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('137', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '蚂蚁课堂资料下载', NULL, 'http://down.mayikt.com/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('140', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 13:22:57', 0, 0, '1350305706179313666', '聊天室', NULL, '/page/client_websocket_websocket', 'layui-icon-file-b', 50002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('141', NULL, NULL, '2020-07-30 18:16:24', '2021-01-16 13:03:03', 0, 0, '1', '代码生成', NULL, '', 'layui-icon-file-b', 102, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('21', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 12:19:09', 0, 0, '4', '系统用户', NULL, '/page/modules_sys_admin_user_user', '', 10002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('22', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 12:19:11', 0, 0, '4', '角色管理', NULL, '/page/modules_sys_admin_role_role', '', 10003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('23', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 12:19:16', 0, 0, '4', '角色菜单权限', NULL, '/page/modules_sys_admin_role_roleMenuAuth', '', 10006, 3, 1);
INSERT INTO `t_admin_menu` VALUES ('24', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 12:19:18', 0, 0, '4', '角色URL权限', NULL, '/page/modules_sys_admin_role_roleUrlAuth', '', 10007, 3, 1);
INSERT INTO `t_admin_menu` VALUES ('25', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 12:19:12', 0, 0, '4', '字典管理', NULL, '/page/modules_sys_admin_dictionary_dictionary', '', 10004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('30', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 12:50:29', 0, 0, '141', '数据库表', NULL, '/page/modules_sys_gc_dataBase_dataBase', '1', 40006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('4', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '系统管理', NULL, '', 'layui-icon-set', 100, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('43', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '43', 'ccc', NULL, '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('7', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 12:44:02', 0, 0, '4', '菜单管理', '', '/page/modules_sys_admin_menu_menu', 'layui-icon-home', 10001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('93', NULL, NULL, '2020-07-25 09:29:38', '2021-01-16 13:23:11', 0, 0, '1350305706179313666', '文字注释', NULL, '/page/client_tool_fhConvert', 'layui-icon-file-b', 50001, 3, 0);

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '查询code(不能重复)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('1', NULL, NULL, '2020-07-25 09:30:08', '2020-11-05 13:51:13', 0, 0, '系统管理员', '拥有该系统所有操作权限', 'SYS', 0);
INSERT INTO `t_admin_role` VALUES ('1307153583947956225', NULL, NULL, '2020-09-19 11:04:35', '2020-11-05 13:51:24', 0, 0, '测试角色', '测试是否分配权限', 'TEST', 0);
INSERT INTO `t_admin_role` VALUES ('2', NULL, NULL, '2020-07-25 09:30:08', '2020-11-05 13:51:52', 0, 0, '开发人员', '-', 'DEV', 0);
INSERT INTO `t_admin_role` VALUES ('31', NULL, NULL, '2020-07-25 09:30:08', '2020-11-05 13:51:35', 0, 0, '系统体验用户', '开发给第三方人员使用的角色，多数功能接口权限受限', 'TY_USER', 0);

-- ----------------------------
-- Table structure for t_admin_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_auth`;
CREATE TABLE `t_admin_role_auth`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `auth_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/接口权限关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_role_auth
-- ----------------------------
INSERT INTO `t_admin_role_auth` VALUES ('1330849364788150274', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784180912128', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364788150275', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106432', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364788150276', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106433', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364788150277', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106434', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364788150278', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106435', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364788150279', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106436', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316098', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106437', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316099', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106438', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316100', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106439', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316101', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106440', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316102', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300736', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316103', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300737', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316104', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300738', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316105', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300739', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316106', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300740', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316107', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300741', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316108', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300742', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316109', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300743', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316110', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300744', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316111', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300745', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316112', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300746', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316113', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300747', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316114', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300748', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316115', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495040', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316116', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495041', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316117', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495042', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316118', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495043', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316119', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495044', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316120', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495045', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316121', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495046', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316122', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495047', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316123', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495048', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316124', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495049', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316125', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495050', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316126', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495051', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316127', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689344', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316128', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689345', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316129', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689346', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316130', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689347', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316131', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689348', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316132', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689349', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316133', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689350', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316134', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689351', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316135', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689352', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316136', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689353', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316137', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689354', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316138', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689355', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316139', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689356', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316140', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883648', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316141', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883649', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316142', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883650', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316143', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883651', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316144', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883652', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316145', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883653', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316146', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883654', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316147', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883655', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316148', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883656', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316149', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883657', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316150', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883658', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316151', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883659', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316152', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077952', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316153', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077953', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316154', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077954', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316155', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077955', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316156', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077956', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316157', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077957', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316158', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077958', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316159', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077959', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316160', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077960', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316161', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077961', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316162', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077962', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316163', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272256', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316164', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272257', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316165', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272258', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316166', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272259', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316167', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272260', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316168', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272261', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316169', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272262', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316170', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272263', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316171', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272264', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316172', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272265', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316173', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660866', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316174', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660867', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316175', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660868', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316176', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660869', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316177', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660870', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316178', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660871', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316179', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660872', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316180', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784180912128', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316181', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106432', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316182', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106433', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316183', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106434', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316184', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106435', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316185', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106436', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316186', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106437', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316187', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106438', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316188', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106439', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316189', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106440', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316190', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300736', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316191', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300737', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316192', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300738', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316193', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300739', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316194', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300740', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316195', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300741', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316196', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300742', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316197', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300743', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316198', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300744', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316199', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300745', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316200', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300746', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316201', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300747', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316202', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300748', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316203', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495040', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316204', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495041', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316205', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495042', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316206', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495043', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316207', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495044', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316208', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495045', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316209', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495046', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316210', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495047', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316211', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495048', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316212', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495049', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316213', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495050', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316214', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495051', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316215', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689344', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316216', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689345', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316217', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689346', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316218', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689347', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316219', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689348', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316220', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689349', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316221', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689350', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316222', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689351', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316223', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689352', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316224', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689353', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316225', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689354', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316226', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689355', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316227', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689356', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316228', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883648', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316229', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883649', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316230', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883650', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316231', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883651', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364813316232', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883652', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230658', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883653', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230659', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883654', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230660', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883655', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230661', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883656', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230662', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883657', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230663', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883658', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230664', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883659', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230665', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077952', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230666', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077953', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230667', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077954', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230668', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077955', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230669', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077956', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230670', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077957', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230671', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077958', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230672', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077959', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230673', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077960', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230674', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077961', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230675', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077962', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230676', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272256', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230677', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272257', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230678', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272258', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230679', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272259', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230680', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272260', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230681', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272261', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230682', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272262', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230683', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272263', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230684', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272264', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230685', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272265', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230686', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660866', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230687', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660867', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230688', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660868', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230689', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660869', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230690', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660870', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230691', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660871', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230692', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660872', '1307153583947956225');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230693', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784180912128', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230694', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106432', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230695', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106433', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230696', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106434', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230697', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106435', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230698', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106436', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230699', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106437', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230700', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106438', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230701', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106439', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230702', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784185106440', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230703', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300736', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230704', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300737', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230705', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300738', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230706', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300739', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230707', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300740', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230708', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300741', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230709', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300742', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230710', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300743', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230711', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300744', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230712', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300745', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230713', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300746', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230714', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300747', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230715', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784189300748', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230716', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495040', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230717', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495041', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230718', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495042', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230719', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495043', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230720', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495044', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230721', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495045', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230722', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495046', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230723', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495047', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230724', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495048', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230725', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495049', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230726', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495050', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230727', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784193495051', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230728', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689344', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230729', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689345', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230730', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689346', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230731', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689347', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230732', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689348', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230733', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689349', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230734', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689350', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230735', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689351', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230736', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689352', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230737', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689353', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230738', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689354', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230739', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689355', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230740', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784197689356', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230741', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883648', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230742', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883649', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230743', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883650', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230744', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883651', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230745', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883652', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230746', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883653', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230747', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883654', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230748', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883655', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230749', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883656', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230750', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883657', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230751', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883658', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230752', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784201883659', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230753', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077952', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230754', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077953', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230755', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077954', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230756', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077955', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230757', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077956', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230758', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077957', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230759', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077958', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230760', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077959', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230761', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077960', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230762', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077961', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230763', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784206077962', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230764', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272256', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230765', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272257', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230766', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272258', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230767', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272259', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230768', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272260', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230769', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272261', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230770', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272262', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230771', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272263', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230772', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272264', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230773', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784210272265', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230774', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660866', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230775', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660867', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230776', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660868', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230777', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660869', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230778', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660870', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230779', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660871', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330849364876230780', NULL, NULL, '2020-11-23 20:23:09', '2020-11-23 20:23:09', 0, 0, '490815784218660872', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1330850043439185921', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113025', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850043841839106', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113026', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850043841839107', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113027', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850043841839108', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113028', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850043841839109', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113029', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850043841839110', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113030', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850043841839111', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113024', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663170', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113032', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663171', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113033', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663172', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307328', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663173', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307329', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663174', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307330', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663175', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307331', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663176', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307332', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663177', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307333', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663178', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307334', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663179', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307335', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044118663180', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949258113031', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044156411906', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307337', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044156411907', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307338', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044156411908', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307339', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044156411909', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307340', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132162', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501632', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132163', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501633', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132164', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949262307336', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132165', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501635', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132166', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501634', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132167', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501637', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132168', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501638', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132169', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501639', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132170', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501640', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132171', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501641', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132172', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501636', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132173', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501643', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132174', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501644', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132175', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501645', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132176', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132177', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695937', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132178', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695938', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132179', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695939', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132180', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949266501642', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132181', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695941', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132182', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695942', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132183', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695943', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132184', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695944', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132185', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695940', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132186', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695946', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044215132187', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695947', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880897', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695948', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880898', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695949', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880899', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890240', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880900', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890241', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880901', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949270695945', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880902', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890243', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880903', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890242', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880904', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890245', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880905', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890246', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880906', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890247', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880907', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890248', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880908', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890249', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880909', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890250', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880910', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890251', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880911', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890252', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880912', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890253', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880913', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949274890244', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880914', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084545', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880915', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084546', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880916', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084547', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880917', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084548', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880918', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084549', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880919', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084550', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880920', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084551', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880921', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084544', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880922', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084553', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880923', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084554', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044252880924', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084555', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406849', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084556', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406850', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278848', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406851', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278849', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406852', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949279084552', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406853', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278851', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406854', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278852', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406855', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278850', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406856', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278854', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406857', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278855', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406858', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278856', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406859', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278857', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406860', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278858', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406861', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278859', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406862', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278853', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406863', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473152', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406864', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473153', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406865', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473154', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406866', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473155', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406867', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473156', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406868', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473157', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406869', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473158', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406870', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473159', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406871', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473160', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406872', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949283278860', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406873', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473162', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406874', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473163', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406875', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473161', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406876', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473165', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044307406877', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473166', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127105', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949291667456', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127106', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949287473164', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127107', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949291667458', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127108', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949291667459', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127109', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949291667457', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127110', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949291667461', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127111', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949291667462', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127112', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949379747840', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127113', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942144', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127114', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942145', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127115', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942146', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127116', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942147', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127117', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942148', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127118', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942149', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127119', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942150', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127120', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949291667460', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127121', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942152', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127122', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942153', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127123', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942154', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127124', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942155', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127125', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942156', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127126', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136448', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127127', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136449', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127128', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136450', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127129', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136451', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127130', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136452', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127131', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136453', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127132', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136454', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127133', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136455', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127134', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949383942151', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127135', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136457', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127136', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136458', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127137', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136459', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127138', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136456', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127139', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136461', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127140', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136462', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044366127141', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949388136460', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044454207490', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949392330753', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044454207491', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949392330754', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044454207492', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949392330752', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044454207493', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949392330756', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1330850044454207494', NULL, NULL, '2020-11-23 20:25:51', '2020-11-23 20:25:51', 0, 0, '490828949392330755', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409423922393089', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327170281473', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269058', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327170281474', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269059', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327174475776', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269060', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327174475777', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269061', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327174475778', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269062', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327174475779', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269063', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327170281472', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269064', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058688', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269065', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058689', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269066', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058690', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269067', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058691', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269068', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058692', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269069', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058693', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269070', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058694', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269071', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058695', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269072', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327187058696', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1331409424308269073', NULL, NULL, '2020-11-25 09:28:37', '2020-11-25 09:28:37', 0, 0, '491388327182864384', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332332396053315585', NULL, NULL, '2020-11-27 22:36:08', '2020-11-27 22:36:08', 0, 0, '492311297745096705', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332332396091064322', NULL, NULL, '2020-11-27 22:36:08', '2020-11-27 22:36:08', 0, 0, '492311297745096706', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332332396091064323', NULL, NULL, '2020-11-27 22:36:08', '2020-11-27 22:36:08', 0, 0, '492311297745096707', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332332396091064324', NULL, NULL, '2020-11-27 22:36:08', '2020-11-27 22:36:08', 0, 0, '492311297749291008', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332332396091064325', NULL, NULL, '2020-11-27 22:36:08', '2020-11-27 22:36:08', 0, 0, '492311297749291009', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332332396095258626', NULL, NULL, '2020-11-27 22:36:08', '2020-11-27 22:36:08', 0, 0, '492311297749291010', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332332396095258627', NULL, NULL, '2020-11-27 22:36:08', '2020-11-27 22:36:08', 0, 0, '492311297745096704', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332339850677903361', NULL, NULL, '2020-11-27 23:05:46', '2020-11-27 23:05:46', 0, 0, '492318753229508608', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874568597506', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777711636481', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250690', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777774551040', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250691', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777778745344', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250692', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777778745345', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250693', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777778745346', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250694', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777778745347', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250695', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777778745348', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250696', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777778745349', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250697', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777778745350', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250698', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777711636480', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250699', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777795522561', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250700', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777795522562', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250701', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777795522563', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250702', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777795522564', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250703', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777795522565', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250704', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777795522566', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332377874971250705', NULL, NULL, '2020-11-28 01:36:54', '2020-11-28 01:36:54', 0, 0, '492356777795522560', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332882622858137602', NULL, NULL, '2020-11-29 11:02:35', '2020-11-29 11:02:35', 0, 0, '492861525669842945', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332882622887497730', NULL, NULL, '2020-11-29 11:02:35', '2020-11-29 11:02:35', 0, 0, '492861525669842944', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961553657401345', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455957368833', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554349461505', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455957368834', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554349461506', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455957368835', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554349461507', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455957368836', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554349461508', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455957368837', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554349461509', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455957368838', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554349461510', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455957368832', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764673', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757441', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764674', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757442', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764675', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757443', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764676', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757444', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764677', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757445', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764678', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757446', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764679', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757447', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764680', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757448', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764681', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757449', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1332961554420764682', NULL, NULL, '2020-11-29 16:16:14', '2020-11-29 16:16:14', 0, 0, '492940455965757440', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345039421441', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248320880641', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823361', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248320880642', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823362', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248392183808', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823363', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248396378112', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823364', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248396378113', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823365', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248396378114', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823366', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248320880640', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823367', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248400572417', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823368', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248404766720', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823369', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248404766721', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823370', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248404766722', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823371', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248404766723', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823372', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248404766724', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823373', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248404766725', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823374', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248404766726', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823375', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248404766727', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1336215345479823376', NULL, NULL, '2020-12-08 15:45:38', '2020-12-08 15:45:38', 0, 0, '496194248400572416', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1337303370121093122', NULL, NULL, '2020-12-11 15:49:04', '2020-12-11 15:49:04', 0, 0, '497282273629048833', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1337303370154647553', NULL, NULL, '2020-12-11 15:49:04', '2020-12-11 15:49:04', 0, 0, '497282273629048834', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1337303370154647554', NULL, NULL, '2020-12-11 15:49:04', '2020-12-11 15:49:04', 0, 0, '497282273629048832', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339486340722544642', NULL, NULL, '2020-12-17 16:23:24', '2020-12-17 16:23:24', 0, 0, '499465243018268673', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339486340743516161', NULL, NULL, '2020-12-17 16:23:24', '2020-12-17 16:23:24', 0, 0, '499465243018268674', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339486340743516162', NULL, NULL, '2020-12-17 16:23:24', '2020-12-17 16:23:24', 0, 0, '499465243018268675', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339486340751904769', NULL, NULL, '2020-12-17 16:23:24', '2020-12-17 16:23:24', 0, 0, '499465243018268676', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339486340751904770', NULL, NULL, '2020-12-17 16:23:24', '2020-12-17 16:23:24', 0, 0, '499465243018268677', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339486340751904771', NULL, NULL, '2020-12-17 16:23:24', '2020-12-17 16:23:24', 0, 0, '499465243018268678', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339486340756099073', NULL, NULL, '2020-12-17 16:23:24', '2020-12-17 16:23:24', 0, 0, '499465243018268672', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339548754763649026', NULL, NULL, '2020-12-17 20:31:25', '2020-12-17 20:31:25', 0, 0, '499527657214578689', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339548754784620546', NULL, NULL, '2020-12-17 20:31:25', '2020-12-17 20:31:25', 0, 0, '499527657214578690', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339548754784620547', NULL, NULL, '2020-12-17 20:31:25', '2020-12-17 20:31:25', 0, 0, '499527657214578691', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339548754784620548', NULL, NULL, '2020-12-17 20:31:25', '2020-12-17 20:31:25', 0, 0, '499527657218772992', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339548754784620549', NULL, NULL, '2020-12-17 20:31:25', '2020-12-17 20:31:25', 0, 0, '499527657218772993', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339548754784620550', NULL, NULL, '2020-12-17 20:31:25', '2020-12-17 20:31:25', 0, 0, '499527657218772994', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339548754784620551', NULL, NULL, '2020-12-17 20:31:25', '2020-12-17 20:31:25', 0, 0, '499527657214578688', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339574355495002114', NULL, NULL, '2020-12-17 22:13:09', '2020-12-17 22:13:09', 0, 0, '499553258247950337', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339574355511779330', NULL, NULL, '2020-12-17 22:13:09', '2020-12-17 22:13:09', 0, 0, '499553258247950338', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339574355515973633', NULL, NULL, '2020-12-17 22:13:09', '2020-12-17 22:13:09', 0, 0, '499553258247950339', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339574355515973634', NULL, NULL, '2020-12-17 22:13:09', '2020-12-17 22:13:09', 0, 0, '499553258247950340', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339574355515973635', NULL, NULL, '2020-12-17 22:13:09', '2020-12-17 22:13:09', 0, 0, '499553258247950341', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339574355515973636', NULL, NULL, '2020-12-17 22:13:09', '2020-12-17 22:13:09', 0, 0, '499553258247950342', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339574355515973637', NULL, NULL, '2020-12-17 22:13:09', '2020-12-17 22:13:09', 0, 0, '499553258247950336', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339835612785623042', NULL, NULL, '2020-12-18 15:31:17', '2020-12-18 15:31:17', 0, 0, '499814514959716353', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339835612814983170', NULL, NULL, '2020-12-18 15:31:17', '2020-12-18 15:31:17', 0, 0, '499814514959716354', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339835612814983171', NULL, NULL, '2020-12-18 15:31:17', '2020-12-18 15:31:17', 0, 0, '499814514963910656', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339835612814983172', NULL, NULL, '2020-12-18 15:31:17', '2020-12-18 15:31:17', 0, 0, '499814514963910657', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339835612814983173', NULL, NULL, '2020-12-18 15:31:17', '2020-12-18 15:31:17', 0, 0, '499814514963910658', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339835612819177474', NULL, NULL, '2020-12-18 15:31:17', '2020-12-18 15:31:17', 0, 0, '499814514963910659', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339835612819177475', NULL, NULL, '2020-12-18 15:31:17', '2020-12-18 15:31:17', 0, 0, '499814514959716352', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339862090143244290', NULL, NULL, '2020-12-18 17:16:30', '2020-12-18 17:16:30', 0, 0, '499840992959139840', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339862090168410113', NULL, NULL, '2020-12-18 17:16:30', '2020-12-18 17:16:30', 0, 0, '499840992959139841', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339862090168410114', NULL, NULL, '2020-12-18 17:16:30', '2020-12-18 17:16:30', 0, 0, '499840992959139842', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339862090168410115', NULL, NULL, '2020-12-18 17:16:30', '2020-12-18 17:16:30', 0, 0, '499840992959139843', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339862090168410116', NULL, NULL, '2020-12-18 17:16:30', '2020-12-18 17:16:30', 0, 0, '499840992959139844', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339862090172604418', NULL, NULL, '2020-12-18 17:16:30', '2020-12-18 17:16:30', 0, 0, '499840992959139845', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1339862090172604419', NULL, NULL, '2020-12-18 17:16:30', '2020-12-18 17:16:30', 0, 0, '499840992954945536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340622625826701314', NULL, NULL, '2020-12-20 19:38:37', '2020-12-20 19:38:37', 0, 0, '500601528286056449', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340622625847672834', NULL, NULL, '2020-12-20 19:38:37', '2020-12-20 19:38:37', 0, 0, '500601528286056448', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386248990722', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288897019905', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386278350849', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288897019906', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386278350850', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288897019907', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386278350851', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288897019908', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386278350852', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288897019909', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545154', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288897019910', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545155', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288897019904', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545156', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288901214209', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545157', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288901214210', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545158', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288901214211', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545159', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288901214212', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545160', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288901214213', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545161', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288901214214', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340651386282545162', NULL, NULL, '2020-12-20 21:32:54', '2020-12-20 21:32:54', 0, 0, '500630288901214208', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340657463468290049', NULL, NULL, '2020-12-20 21:57:02', '2020-12-20 21:57:02', 0, 0, '500636365755650048', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340659399663902721', NULL, NULL, '2020-12-20 22:04:44', '2020-12-20 22:04:44', 0, 0, '500638301628272640', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340659636067442690', NULL, NULL, '2020-12-20 22:05:40', '2020-12-20 22:05:40', 0, 0, '500638538321235968', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340673332743311362', NULL, NULL, '2020-12-20 23:00:06', '2020-12-20 23:00:06', 0, 0, '500652234716090368', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340673332768477186', NULL, NULL, '2020-12-20 23:00:06', '2020-12-20 23:00:06', 0, 0, '500652234711896064', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340673332768477187', NULL, NULL, '2020-12-20 23:00:06', '2020-12-20 23:00:06', 0, 0, '500652234716090370', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1340673332768477188', NULL, NULL, '2020-12-20 23:00:06', '2020-12-20 23:00:06', 0, 0, '500652234716090369', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342021999563751425', NULL, NULL, '2020-12-24 16:19:13', '2020-12-24 16:19:13', 0, 0, '502000902509563904', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342021999584722946', NULL, NULL, '2020-12-24 16:19:13', '2020-12-24 16:19:13', 0, 0, '502000902509563905', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342021999584722947', NULL, NULL, '2020-12-24 16:19:13', '2020-12-24 16:19:13', 0, 0, '502000902509563906', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342021999584722948', NULL, NULL, '2020-12-24 16:19:13', '2020-12-24 16:19:13', 0, 0, '502000902509563907', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342021999584722949', NULL, NULL, '2020-12-24 16:19:13', '2020-12-24 16:19:13', 0, 0, '502000902509563908', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342021999584722950', NULL, NULL, '2020-12-24 16:19:13', '2020-12-24 16:19:13', 0, 0, '502000902509563909', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342021999588917249', NULL, NULL, '2020-12-24 16:19:13', '2020-12-24 16:19:13', 0, 0, '502000902505369600', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342043064448929794', NULL, NULL, '2020-12-24 17:42:55', '2020-12-24 17:42:55', 0, 0, '502021967403159552', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342043064474095617', NULL, NULL, '2020-12-24 17:42:55', '2020-12-24 17:42:55', 0, 0, '502021967403159553', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564678778881', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471489', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564716527618', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471490', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564716527619', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471491', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564716527620', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471492', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564716527621', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471493', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564720721921', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471488', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564720721922', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471495', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564724916226', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471496', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564724916227', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471497', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564724916228', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471498', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564724916229', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471499', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564724916230', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471500', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564724916231', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471501', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110530', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471502', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110531', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471503', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110532', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471504', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110533', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471494', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110534', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471506', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110535', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665792', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110536', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665793', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110537', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665794', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110538', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665795', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110539', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665796', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564729110540', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665797', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304833', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470577471505', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304834', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665799', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304835', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665800', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304836', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665801', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304837', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665802', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304838', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665803', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304839', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665804', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304840', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665805', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304841', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665806', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564733304842', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665807', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499138', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665808', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499139', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665798', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499140', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665810', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499141', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665811', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499142', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665812', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499143', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665813', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499144', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665814', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499145', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665815', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499146', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665816', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499147', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860096', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564737499148', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860097', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693441', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860098', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693442', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860099', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693443', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860100', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693444', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860101', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693445', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470581665809', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693446', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860103', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693447', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860102', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693448', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860105', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693449', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860104', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693450', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860107', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693451', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860108', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693452', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860109', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693453', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860110', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693454', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860111', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693455', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860112', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693456', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860113', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564741693457', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860106', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082050', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860115', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082051', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860116', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082052', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860117', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082053', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860118', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082054', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860119', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082055', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860120', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082056', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470585860114', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082057', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054401', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082058', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054402', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082059', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054403', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564750082060', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054404', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276354', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054405', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276355', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054406', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276356', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054407', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276357', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054408', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276358', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054409', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276359', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054400', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276360', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054411', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276361', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054412', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276362', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054413', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276363', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054414', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564754276364', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054415', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470658', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054416', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470659', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054410', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470660', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054418', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470661', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054419', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470662', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054420', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470663', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054421', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470664', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054422', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470665', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054423', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470666', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054417', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470667', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054425', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564758470668', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054426', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664962', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054427', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664963', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248704', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664964', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248705', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664965', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248706', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664966', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248707', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664967', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248708', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664968', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248709', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664969', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470590054424', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664970', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248711', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664971', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248712', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664972', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248710', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564762664973', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248714', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859265', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248715', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859266', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248716', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859267', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248713', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859268', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248718', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859269', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248719', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859270', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248717', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859271', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248721', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859272', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248722', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859273', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248723', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859274', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248724', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564766859275', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248725', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053569', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248726', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053570', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248720', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053571', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248728', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053572', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248729', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053573', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248730', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053574', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248731', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053575', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248732', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053576', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248733', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053577', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248727', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053578', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248735', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564771053579', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248736', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247873', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248737', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247874', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443008', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247875', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443009', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247876', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443010', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247877', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470594248734', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247878', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443012', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247879', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443013', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247880', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443014', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247881', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443015', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247882', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443016', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564775247883', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443017', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442178', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443018', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442179', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443011', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442180', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443020', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442181', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443021', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442182', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443022', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442183', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443023', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442184', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443024', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442185', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443025', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442186', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443019', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442187', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443027', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442188', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443026', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442189', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443029', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442190', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443030', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442191', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443031', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442192', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443032', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442193', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443033', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442194', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443034', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564779442195', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443028', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636481', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637312', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636482', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637313', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636483', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637314', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636484', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637315', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636485', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470598443035', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636486', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637317', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636487', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637316', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636488', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637319', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636489', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637320', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564783636490', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637318', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830785', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637322', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830786', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637323', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830787', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637321', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830788', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637325', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830789', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637326', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830790', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637327', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830791', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637328', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830792', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637324', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830793', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637330', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830794', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637331', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564787830795', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637329', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564792025089', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637333', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342048564792025090', NULL, NULL, '2020-12-24 18:04:46', '2020-12-24 18:04:46', 0, 0, '502027470602637332', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342399819116740609', NULL, NULL, '2020-12-25 17:20:32', '2020-12-25 17:20:32', 0, 0, '502378721735479297', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342399819250958337', NULL, NULL, '2020-12-25 17:20:32', '2020-12-25 17:20:32', 0, 0, '502378721735479298', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342399819250958338', NULL, NULL, '2020-12-25 17:20:32', '2020-12-25 17:20:32', 0, 0, '502378721735479296', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342402854119370753', NULL, NULL, '2020-12-25 17:32:36', '2020-12-25 17:32:36', 0, 0, '502381756750630912', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342676985586925570', NULL, NULL, '2020-12-26 11:41:54', '2020-12-26 11:41:54', 0, 0, '502655887496843265', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342676985607897089', NULL, NULL, '2020-12-26 11:41:54', '2020-12-26 11:41:54', 0, 0, '502655887496843266', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342676985612091394', NULL, NULL, '2020-12-26 11:41:54', '2020-12-26 11:41:54', 0, 0, '502655887496843264', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342760832332804097', NULL, NULL, '2020-12-26 17:15:04', '2020-12-26 17:15:04', 0, 0, '502739734427209728', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342760832349581313', NULL, NULL, '2020-12-26 17:15:04', '2020-12-26 17:15:04', 0, 0, '502739734431404032', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1342762454316277762', NULL, NULL, '2020-12-26 17:21:31', '2020-12-26 17:21:31', 0, 0, '502741356498784256', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1343009786349772802', NULL, NULL, '2020-12-27 09:44:20', '2020-12-27 09:44:20', 0, 0, '502988688926576640', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233432522754', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134616780801', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233449299970', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134620975104', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688577', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134616780800', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688578', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134620975106', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688579', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134620975105', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688580', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134667112449', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688581', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134667112450', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688582', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134667112448', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688583', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134667112452', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688584', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134667112451', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233457688585', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134688083969', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1348581233461882882', NULL, NULL, '2021-01-11 18:43:16', '2021-01-11 18:43:16', 0, 0, '508560134688083968', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1350792175826300929', NULL, NULL, '2021-01-17 21:08:45', '2021-01-17 21:08:45', 0, 0, '510771078147215360', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1350796761660194818', NULL, NULL, '2021-01-17 21:26:58', '2021-01-17 21:26:58', 0, 0, '510775663964327936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1350812392476405761', NULL, NULL, '2021-01-17 22:29:05', '2021-01-17 22:29:05', 0, 0, '510791294763798528', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351335009532821506', NULL, NULL, '2021-01-19 09:05:46', '2021-01-19 09:05:46', 0, 0, '511313911593701377', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351335009557987329', NULL, NULL, '2021-01-19 09:05:46', '2021-01-19 09:05:46', 0, 0, '511313911593701376', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351335009557987330', NULL, NULL, '2021-01-19 09:05:46', '2021-01-19 09:05:46', 0, 0, '511313911597895681', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351335009557987331', NULL, NULL, '2021-01-19 09:05:46', '2021-01-19 09:05:46', 0, 0, '511313911597895680', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351779780147585026', NULL, NULL, '2021-01-20 14:33:09', '2021-01-20 14:33:09', 0, 0, '511758682074255361', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351779780172750849', NULL, NULL, '2021-01-20 14:33:09', '2021-01-20 14:33:09', 0, 0, '511758682074255362', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351779780176945153', NULL, NULL, '2021-01-20 14:33:09', '2021-01-20 14:33:09', 0, 0, '511758682078449664', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351779780176945154', NULL, NULL, '2021-01-20 14:33:09', '2021-01-20 14:33:09', 0, 0, '511758682078449665', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351779780176945155', NULL, NULL, '2021-01-20 14:33:09', '2021-01-20 14:33:09', 0, 0, '511758682078449666', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351779780176945156', NULL, NULL, '2021-01-20 14:33:09', '2021-01-20 14:33:09', 0, 0, '511758682082643968', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351779780181139457', NULL, NULL, '2021-01-20 14:33:09', '2021-01-20 14:33:09', 0, 0, '511758682074255360', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351840713817567234', NULL, NULL, '2021-01-20 18:35:16', '2021-01-20 18:35:16', 0, 0, '511819615026941952', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351840713846927361', NULL, NULL, '2021-01-20 18:35:16', '2021-01-20 18:35:16', 0, 0, '511819615026941953', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351840713846927362', NULL, NULL, '2021-01-20 18:35:16', '2021-01-20 18:35:16', 0, 0, '511819615026941955', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351840713846927363', NULL, NULL, '2021-01-20 18:35:16', '2021-01-20 18:35:16', 0, 0, '511819615026941954', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351848514128203777', NULL, NULL, '2021-01-20 19:06:16', '2021-01-20 19:06:16', 0, 0, '511827416033923072', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351848514161758209', NULL, NULL, '2021-01-20 19:06:16', '2021-01-20 19:06:16', 0, 0, '511827416029728768', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351848514161758210', NULL, NULL, '2021-01-20 19:06:16', '2021-01-20 19:06:16', 0, 0, '511827416033923074', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351848514161758211', NULL, NULL, '2021-01-20 19:06:16', '2021-01-20 19:06:16', 0, 0, '511827416033923075', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351848514161758212', NULL, NULL, '2021-01-20 19:06:16', '2021-01-20 19:06:16', 0, 0, '511827416033923076', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351848514165952513', NULL, NULL, '2021-01-20 19:06:16', '2021-01-20 19:06:16', 0, 0, '511827416033923073', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351852370161672193', NULL, NULL, '2021-01-20 19:21:36', '2021-01-20 19:21:36', 0, 0, '511831272058916865', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351852370199420929', NULL, NULL, '2021-01-20 19:21:36', '2021-01-20 19:21:36', 0, 0, '511831272058916866', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351852370203615233', NULL, NULL, '2021-01-20 19:21:36', '2021-01-20 19:21:36', 0, 0, '511831272058916867', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351852370203615234', NULL, NULL, '2021-01-20 19:21:36', '2021-01-20 19:21:36', 0, 0, '511831272058916868', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351852370203615235', NULL, NULL, '2021-01-20 19:21:36', '2021-01-20 19:21:36', 0, 0, '511831272058916869', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351852370203615236', NULL, NULL, '2021-01-20 19:21:36', '2021-01-20 19:21:36', 0, 0, '511831272058916870', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1351852370203615237', NULL, NULL, '2021-01-20 19:21:36', '2021-01-20 19:21:36', 0, 0, '511831272058916864', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918395564033', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818216693761', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965953', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818216693762', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965954', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818216693760', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965955', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818254442497', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965956', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818254442498', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965957', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818254442499', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965958', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818254442500', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965959', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818254442496', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965960', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818254442501', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965961', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818254442502', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965962', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818258636801', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965963', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818258636800', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965964', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818262831105', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965965', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818262831106', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965966', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818262831104', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965967', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818262831108', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965968', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818262831109', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965969', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818262831110', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965970', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818262831107', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965971', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818308968449', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965972', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818308968450', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965973', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818308968448', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965974', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818313162752', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965975', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818313162753', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965976', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818308968451', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965977', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818367688705', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965978', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818367688706', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352855918835965979', NULL, NULL, '2021-01-23 13:49:20', '2021-01-23 13:49:20', 0, 0, '512834818367688704', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351795183618', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254443290625', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351824543746', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254443290626', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351824543747', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254443290624', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351824543748', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254443290628', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351824543749', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254443290629', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351824543750', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254443290630', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351824543751', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254443290627', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351828738049', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254455873537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351828738050', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254455873538', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351828738051', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254455873536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351828738052', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254455873540', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351828738053', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254455873541', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932353', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254455873542', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932354', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254455873543', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932355', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254460067840', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932356', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254460067841', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932357', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254455873539', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932358', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254460067843', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932359', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254460067842', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932360', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254460067844', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352862351832932361', NULL, NULL, '2021-01-23 14:14:56', '2021-01-23 14:14:56', 0, 0, '512841254460067845', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352864168566345730', NULL, NULL, '2021-01-23 14:22:09', '2021-01-23 14:22:09', 0, 0, '512843070778249217', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352864168591511553', NULL, NULL, '2021-01-23 14:22:09', '2021-01-23 14:22:09', 0, 0, '512843070778249218', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352864168591511554', NULL, NULL, '2021-01-23 14:22:09', '2021-01-23 14:22:09', 0, 0, '512843070778249219', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352864168591511555', NULL, NULL, '2021-01-23 14:22:09', '2021-01-23 14:22:09', 0, 0, '512843070778249220', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352864168591511556', NULL, NULL, '2021-01-23 14:22:09', '2021-01-23 14:22:09', 0, 0, '512843070778249216', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683500793858', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585586884609', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683517571073', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585586884610', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683517571074', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585586884611', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683525959681', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585586884612', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683525959682', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585586884613', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683525959683', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585586884614', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683525959684', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585586884615', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683525959685', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585586884608', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153985', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585591078913', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153986', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585591078914', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153987', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585591078915', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153988', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585591078916', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153989', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585591078917', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153990', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585591078918', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153991', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585591078912', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153992', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273217', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153993', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273218', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683530153994', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273219', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348290', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273220', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348291', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273221', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348292', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273222', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348293', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273223', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348294', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273224', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348295', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273225', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348296', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273216', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348297', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273227', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348298', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273228', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683534348299', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273229', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542594', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273230', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542595', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273231', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542596', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467520', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542597', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585595273226', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542598', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467522', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542599', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467523', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542600', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467524', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542601', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467525', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542602', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467526', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683538542603', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467527', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683542736897', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467521', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683542736898', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467529', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683542736899', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467530', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683542736900', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467528', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683542736901', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585603661824', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683546931201', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585599467531', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683546931202', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585603661826', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683546931203', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585603661827', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683546931204', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585603661828', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683546931205', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585603661829', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683546931206', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585603661830', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683546931207', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585603661831', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352890683546931208', NULL, NULL, '2021-01-23 16:07:30', '2021-01-23 16:07:30', 0, 0, '512869585603661825', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535773229057', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437414682624', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535798394882', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437410488320', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535798394883', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437414682626', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535798394884', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437414682625', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535798394885', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437414682628', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535802589185', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437414682627', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535802589186', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437414682630', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535802589187', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437418876928', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535802589188', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437414682629', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535802589189', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437465014273', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535806783490', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437465014274', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1352928535806783491', NULL, NULL, '2021-01-23 18:37:55', '2021-01-23 18:37:55', 0, 0, '512907437465014272', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302867759106', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815552', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302888730626', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203309621248', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302888730627', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815554', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302888730628', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815553', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302888730629', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815556', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302888730630', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815557', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302888730631', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815555', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302888730632', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815559', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119234', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815560', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119235', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815558', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119236', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815562', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119237', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815563', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119238', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203313815561', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119239', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203318009857', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119240', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203318009858', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119241', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203318009859', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353244302897119242', NULL, NULL, '2021-01-24 15:32:40', '2021-01-24 15:32:40', 0, 0, '513223203318009856', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353516777048444929', NULL, NULL, '2021-01-25 09:35:22', '2021-01-25 09:35:22', 0, 0, '513495678740205569', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353516777077805058', NULL, NULL, '2021-01-25 09:35:22', '2021-01-25 09:35:22', 0, 0, '513495678740205570', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353516777077805059', NULL, NULL, '2021-01-25 09:35:22', '2021-01-25 09:35:22', 0, 0, '513495678740205571', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353516777077805060', NULL, NULL, '2021-01-25 09:35:22', '2021-01-25 09:35:22', 0, 0, '513495678740205568', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353522164480942081', NULL, NULL, '2021-01-25 09:56:46', '2021-01-25 09:56:46', 0, 0, '513501065640022016', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353890253202628609', NULL, NULL, '2021-01-26 10:19:25', '2021-01-26 10:19:25', 0, 0, '513869155548663809', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1353890253227794434', NULL, NULL, '2021-01-26 10:19:25', '2021-01-26 10:19:25', 0, 0, '513869155548663808', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573495275522', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475145129985', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573516247041', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475145129984', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573516247042', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475174490113', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573516247043', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475174490114', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573516247044', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475174490115', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573520441346', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475174490116', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573520441347', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475174490117', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573520441348', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475174490118', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354241573520441349', NULL, NULL, '2021-01-27 09:35:26', '2021-01-27 09:35:26', 0, 0, '514220475174490112', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347771199489', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251159093249', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347787976705', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251159093250', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347787976706', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251159093251', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347787976707', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251159093252', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347787976708', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251159093253', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347787976709', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251159093254', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347787976710', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251159093255', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347787976711', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251159093248', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365314', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287553', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365315', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287554', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365316', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287555', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365317', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287556', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365318', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287557', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365319', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287558', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365320', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287552', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365321', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287560', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365322', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287561', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365323', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287562', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365324', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287563', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365325', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287564', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365326', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287565', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365327', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287566', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365328', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287567', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365329', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287568', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365330', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287559', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365331', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287570', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365332', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481856', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347796365333', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481857', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753922', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481858', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753923', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481859', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753924', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481860', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753925', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251163287569', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753926', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481862', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753927', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481863', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753928', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481864', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753929', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481865', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753930', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481866', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753931', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481867', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753932', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481861', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753933', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481869', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753934', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481870', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753935', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481868', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753936', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251171676160', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753937', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251167481871', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753938', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251171676161', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753939', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251171676162', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753940', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251175870464', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753941', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251180064768', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347804753942', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259073', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142530', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259074', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142531', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259072', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142532', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259076', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142533', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259077', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142534', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259075', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142535', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259079', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142536', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259078', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142537', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259081', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142538', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259082', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142539', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251184259080', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142540', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251188453377', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142541', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251188453378', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142542', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251188453379', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142543', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251188453376', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142544', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251192647681', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142545', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251192647682', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142546', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251192647680', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142547', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251196841984', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142548', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251196841985', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142549', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251192647683', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142550', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036289', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142551', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036290', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142552', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036288', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142553', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036292', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347813142554', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036293', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531137', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036294', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531138', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036295', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531139', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036291', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531140', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036297', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531141', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036296', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531142', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036299', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531143', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036300', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531144', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036298', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531145', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036302', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531146', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036301', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531147', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251201036303', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354316347821531148', NULL, NULL, '2021-01-27 14:32:34', '2021-01-27 14:32:34', 0, 0, '514295251205230592', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932178423810', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834574827521', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932199395329', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834574827520', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932203589634', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021825', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932203589635', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021826', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932203589636', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021824', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932203589637', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021828', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932203589638', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021829', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783938', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021830', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783939', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021831', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783940', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021832', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783941', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021833', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783942', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021834', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783943', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834579021827', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783944', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216129', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783945', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216130', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783946', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216131', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783947', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216132', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783948', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216133', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783949', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216134', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783950', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216128', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783951', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216136', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783952', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216137', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783953', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216138', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783954', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216139', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783955', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216140', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932207783956', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216141', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172545', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216142', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172546', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216143', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172547', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410432', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172548', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834583216135', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172549', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410434', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172550', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410435', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172551', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410436', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172552', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410437', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172553', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410438', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172554', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410439', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172555', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410433', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172556', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410441', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172557', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410442', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172558', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410443', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172559', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410444', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172560', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410445', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172561', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410446', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172562', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834587410440', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172563', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834591604737', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172564', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834591604738', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172565', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834591604739', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172566', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834591604740', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172567', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834591604741', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172568', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834591604742', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172569', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834591604736', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172570', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834595799041', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172571', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834595799040', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172572', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834599993344', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172573', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834608381953', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172574', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834608381954', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172575', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834608381952', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172576', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834608381956', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172577', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834608381957', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172578', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834608381958', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172579', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834608381955', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172580', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834616770561', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172581', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834616770562', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932216172582', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834616770560', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932237144066', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834620964865', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932237144067', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834620964866', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932237144068', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834620964867', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932237144069', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834620964868', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932237144070', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834620964869', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932237144071', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834620964870', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354706932237144072', NULL, NULL, '2021-01-28 16:24:36', '2021-01-28 16:24:36', 0, 0, '514685834620964864', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293102477313', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194811015169', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293136031745', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194811015170', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293136031746', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194811015171', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293136031747', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194811015172', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293140226049', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194811015173', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293140226050', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194811015168', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293140226051', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194815209473', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293140226052', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194815209474', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293140226053', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194815209475', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293144420354', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194815209476', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293144420355', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194815209477', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354707293144420356', NULL, NULL, '2021-01-28 16:26:02', '2021-01-28 16:26:02', 0, 0, '514686194815209472', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354709298038476802', NULL, NULL, '2021-01-28 16:34:00', '2021-01-28 16:34:00', 0, 0, '514688200111951873', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354709298063642625', NULL, NULL, '2021-01-28 16:34:00', '2021-01-28 16:34:00', 0, 0, '514688200116146176', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354709298063642626', NULL, NULL, '2021-01-28 16:34:00', '2021-01-28 16:34:00', 0, 0, '514688200116146177', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354709298063642627', NULL, NULL, '2021-01-28 16:34:00', '2021-01-28 16:34:00', 0, 0, '514688200116146178', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354709298063642628', NULL, NULL, '2021-01-28 16:34:00', '2021-01-28 16:34:00', 0, 0, '514688200116146179', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354709298067836930', NULL, NULL, '2021-01-28 16:34:00', '2021-01-28 16:34:00', 0, 0, '514688200111951872', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354720006142730242', NULL, NULL, '2021-01-28 17:16:33', '2021-01-28 17:16:33', 0, 0, '514698908027457537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354720006180478978', NULL, NULL, '2021-01-28 17:16:33', '2021-01-28 17:16:33', 0, 0, '514698908027457538', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354720006184673281', NULL, NULL, '2021-01-28 17:16:33', '2021-01-28 17:16:33', 0, 0, '514698908027457539', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354720006184673282', NULL, NULL, '2021-01-28 17:16:33', '2021-01-28 17:16:33', 0, 0, '514698908027457540', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354720006184673283', NULL, NULL, '2021-01-28 17:16:33', '2021-01-28 17:16:33', 0, 0, '514698908027457541', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1354720006184673284', NULL, NULL, '2021-01-28 17:16:33', '2021-01-28 17:16:33', 0, 0, '514698908027457536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355051655351021569', NULL, NULL, '2021-01-29 15:14:24', '2021-01-29 15:14:24', 0, 0, '515030556975697921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355051655376187394', NULL, NULL, '2021-01-29 15:14:24', '2021-01-29 15:14:24', 0, 0, '515030556975697922', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355051655376187395', NULL, NULL, '2021-01-29 15:14:24', '2021-01-29 15:14:24', 0, 0, '515030556979892224', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355051655376187396', NULL, NULL, '2021-01-29 15:14:24', '2021-01-29 15:14:24', 0, 0, '515030556979892225', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355051655376187397', NULL, NULL, '2021-01-29 15:14:24', '2021-01-29 15:14:24', 0, 0, '515030556975697920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355075292451831809', NULL, NULL, '2021-01-29 16:48:19', '2021-01-29 16:48:19', 0, 0, '515054193598402561', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355075292472803330', NULL, NULL, '2021-01-29 16:48:19', '2021-01-29 16:48:19', 0, 0, '515054193598402560', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355082118656552962', NULL, NULL, '2021-01-29 17:15:27', '2021-01-29 17:15:27', 0, 0, '515061020054654977', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355082118677524482', NULL, NULL, '2021-01-29 17:15:27', '2021-01-29 17:15:27', 0, 0, '515061020054654978', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1355082118677524483', NULL, NULL, '2021-01-29 17:15:27', '2021-01-29 17:15:27', 0, 0, '515061020054654976', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931531571202', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832824930305', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338305', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832824930306', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338306', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832824930304', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338307', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832892039169', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338308', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832892039168', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338309', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832908816385', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338310', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832908816386', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338311', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832908816387', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338312', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832921399296', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338313', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832908816384', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338314', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832921399297', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356055931850338315', NULL, NULL, '2021-02-01 09:45:03', '2021-02-01 09:45:03', 0, 0, '516034832921399298', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264323174401', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165855535104', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264344145921', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165851340800', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264344145922', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165880700929', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264344145923', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165880700930', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264344145924', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895232', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264348340226', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895233', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264348340227', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895234', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264348340228', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895235', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264348340229', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165880700928', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264348340230', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895237', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534530', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895236', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534531', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895239', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534532', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895240', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534533', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895241', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534534', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895242', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534535', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895243', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534536', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895238', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534537', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895245', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534538', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895246', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534539', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895247', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534540', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895248', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264352534541', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165884895244', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264356728833', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165889089537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356060264356728834', NULL, NULL, '2021-02-01 10:02:14', '2021-02-01 10:02:14', 0, 0, '516039165889089536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068443736084481', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339127967745', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444126154753', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339127967746', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444126154754', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339127967744', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444126154755', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339153133569', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444126154756', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339153133568', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444126154757', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339241213953', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444126154758', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339241213954', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444197457921', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339241213955', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444197457922', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339241213956', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444197457923', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339241213952', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444197457924', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339241213957', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356068444197457925', NULL, NULL, '2021-02-01 10:34:46', '2021-02-01 10:34:46', 0, 0, '516047339241213958', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069241777893377', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132375711745', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242088271873', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132375711744', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352257', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820609', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352258', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820610', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352259', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820611', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352260', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820612', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352261', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820613', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352262', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820614', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352263', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820608', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352264', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820616', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352265', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132442820615', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352266', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014913', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352267', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014914', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352268', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014915', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352269', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014916', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352270', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014917', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352271', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014912', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352272', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014919', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352273', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352274', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352275', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014922', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352276', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014918', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352277', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014924', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356069242176352278', NULL, NULL, '2021-02-01 10:37:56', '2021-02-01 10:37:56', 0, 0, '516048132447014923', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356072314399109122', NULL, NULL, '2021-02-01 10:50:07', '2021-02-01 10:50:07', 0, 0, '516051216220950529', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356072314424274945', NULL, NULL, '2021-02-01 10:50:07', '2021-02-01 10:50:07', 0, 0, '516051216220950528', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356085440477712386', NULL, NULL, '2021-02-01 11:42:17', '2021-02-01 11:42:17', 0, 0, '516064341888471041', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356085440511266817', NULL, NULL, '2021-02-01 11:42:17', '2021-02-01 11:42:17', 0, 0, '516064341888471042', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356085440511266818', NULL, NULL, '2021-02-01 11:42:17', '2021-02-01 11:42:17', 0, 0, '516064341888471040', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356444835015307265', NULL, NULL, '2021-02-02 11:30:24', '2021-02-02 11:30:24', 0, 0, '516423736715513856', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356444835044667394', NULL, NULL, '2021-02-02 11:30:24', '2021-02-02 11:30:24', 0, 0, '516423736711319552', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356444835044667395', NULL, NULL, '2021-02-02 11:30:24', '2021-02-02 11:30:24', 0, 0, '516423736715513858', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1356444835044667396', NULL, NULL, '2021-02-02 11:30:24', '2021-02-02 11:30:24', 0, 0, '516423736715513857', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1357612623436185601', NULL, NULL, '2021-02-05 16:50:46', '2021-02-05 16:50:46', 0, 0, '517591525207707648', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1357861888343425026', NULL, NULL, '2021-02-06 09:21:16', '2021-02-06 09:21:16', 0, 0, '517840776378585088', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522039999889410', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943646134273', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040033443841', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943646134274', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040033443842', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943646134275', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040033443843', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943646134276', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040033443844', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943646134277', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040041832449', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943646134278', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040041832450', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943646134279', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040041832451', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943646134272', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026753', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328577', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026754', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328578', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026755', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328579', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026756', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328580', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026757', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328581', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026758', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328582', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026759', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328576', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026760', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328584', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026761', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328585', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026762', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328586', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026763', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328587', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026764', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328588', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026765', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328589', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026766', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328590', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026767', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522880', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026768', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522881', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026769', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943650328583', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040046026770', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522883', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415361', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522884', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415362', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522885', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415363', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522886', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415364', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522887', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415365', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522888', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415366', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522882', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415367', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522890', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415368', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522891', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415369', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717184', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040054415370', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717185', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609666', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717186', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609667', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717187', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609668', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943654522889', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609669', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717189', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609670', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717190', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609671', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717188', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609672', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717192', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609673', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943658717191', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609674', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943662911488', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040058609675', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943662911489', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803969', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943662911490', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803970', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943671300096', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803971', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943675494401', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803972', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943675494402', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803973', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943675494400', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803974', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943675494404', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803975', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943675494405', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803976', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943675494403', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803977', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943675494407', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803978', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943675494406', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040062803979', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943679688705', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998274', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943679688706', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998275', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943679688704', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998276', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943679688708', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998277', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943679688709', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998278', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943679688710', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998279', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943679688707', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998280', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943696465921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998281', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943696465922', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998282', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943696465920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998283', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943696465924', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998284', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943709048832', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998285', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943696465923', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998286', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943709048834', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998287', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243136', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998288', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943709048833', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998289', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243138', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998290', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243139', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998291', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243140', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040066998292', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243141', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386882', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243137', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386883', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243143', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386884', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243142', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386885', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243145', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386886', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943717437440', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386887', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943713243144', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386888', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943717437442', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386889', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943717437441', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386890', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943717437443', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1359522040075386891', NULL, NULL, '2021-02-10 23:18:05', '2021-02-10 23:18:05', 0, 0, '519500943730020352', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334610374658', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236994236417', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334635540482', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236994236416', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334635540483', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236994236419', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334635540484', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430720', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334635540485', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236994236418', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334639734785', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430722', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334639734786', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430723', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334639734787', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430724', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334639734788', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430725', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334639734789', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430726', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334639734790', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430727', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334639734791', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430728', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929090', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430721', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929091', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430730', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929092', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430731', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929093', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625024', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929094', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625025', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929095', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625026', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929096', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625027', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929097', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192236998430729', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929098', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625029', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334643929099', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625030', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334648123393', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625031', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334648123394', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625032', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334648123395', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625033', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334648123396', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625034', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334648123397', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625035', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334648123398', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625036', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334648123399', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819328', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317698', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237002625028', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317699', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819330', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317700', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819331', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317701', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819332', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317702', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819333', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317703', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819334', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317704', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819335', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317705', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819329', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317706', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819337', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334652317707', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819338', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512002', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819339', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512003', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819340', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512004', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819341', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512005', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237011013632', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512006', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237006819336', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512007', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237011013634', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512008', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237011013635', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512009', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237011013636', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512010', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237011013637', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512011', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237011013638', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334656512012', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237011013639', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706305', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237011013633', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706306', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237015207937', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706307', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237015207936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706308', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237019402240', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706309', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237027790849', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706310', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237027790850', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706311', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237027790848', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706312', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237027790852', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706313', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237027790853', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334660706314', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237031985152', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900609', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237027790851', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900610', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237036179456', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900611', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373760', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900612', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373761', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900613', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373762', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900614', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373763', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900615', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373764', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900616', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237036179457', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900617', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373766', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334664900618', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373767', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334669094913', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373768', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334669094914', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373769', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362213334669094915', NULL, NULL, '2021-02-18 09:32:22', '2021-02-18 09:32:22', 0, 0, '522192237040373765', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856834', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470577471488', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856835', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470577471489', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856836', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470577471492', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856837', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470577471493', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856838', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470577471494', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856839', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470577471498', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856840', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470577471501', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856841', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '510791294763798528', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856842', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237019402240', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880148856843', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470577471505', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354306', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665792', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354307', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665793', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354308', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665795', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354309', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665797', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354310', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665798', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354311', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665802', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354312', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665803', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354313', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665808', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354314', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665809', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354315', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665812', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354316', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470581665813', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354317', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470585860096', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354318', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470585860098', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354319', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470590054424', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354320', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470590054426', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354321', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248704', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354322', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248709', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354323', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248720', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354324', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248722', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354325', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248724', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354326', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248727', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354327', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248729', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354328', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248731', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354329', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248734', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354330', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470594248736', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354331', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443008', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354332', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443011', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354333', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443013', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354334', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443015', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354335', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443016', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354336', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443017', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354337', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443019', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354338', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443021', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354339', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443023', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354340', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443026', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354341', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '502027470598443027', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354342', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237036179456', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354343', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192236998430721', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354344', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192236998430723', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354345', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192236998430724', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354346', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192236998430726', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354347', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192236998430729', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354348', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192236998430731', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354349', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237002625024', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354350', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237002625028', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354351', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237002625030', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354352', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237002625031', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354353', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237002625036', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354354', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237006819328', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354355', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237006819329', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354356', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237006819331', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354357', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237006819332', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354358', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237006819336', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354359', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237006819338', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354360', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237006819339', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354361', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237011013633', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354362', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237011013635', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354363', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237011013636', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354364', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237027790848', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354365', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237027790849', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354366', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237027790850', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354367', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237027790851', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354368', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237027790852', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362649880224354369', NULL, NULL, '2021-02-19 14:27:02', '2021-02-19 14:27:02', 0, 0, '522192237031985152', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1362709901472677889', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 0, '522688804579053569', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362709901489455106', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 0, '522688804579053570', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362709901489455107', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 0, '522688804579053571', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362709901489455108', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 0, '522688804579053572', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1362709901493649410', NULL, NULL, '2021-02-19 18:25:33', '2021-02-19 18:25:33', 0, 0, '522688804579053568', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1365177627303858177', NULL, NULL, '2021-02-26 13:51:24', '2021-02-26 13:51:24', 0, 0, '525156518375591936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1366582081878061058', NULL, NULL, '2021-03-02 10:52:12', '2021-03-02 10:52:12', 0, 0, '526560981065797632', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1366582082263937026', NULL, NULL, '2021-03-02 10:52:12', '2021-03-02 10:52:12', 0, 0, '526560981069991936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1366582082263937027', NULL, NULL, '2021-03-02 10:52:12', '2021-03-02 10:52:12', 0, 0, '526560981069991937', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1366582082263937028', NULL, NULL, '2021-03-02 10:52:12', '2021-03-02 10:52:12', 0, 0, '526560981069991938', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1366582082263937029', NULL, NULL, '2021-03-02 10:52:12', '2021-03-02 10:52:12', 0, 0, '526560981065797633', '1');

-- ----------------------------
-- Table structure for t_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/菜单关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------
INSERT INTO `t_admin_role_menu` VALUES ('1291794867738976257', NULL, NULL, '2020-08-07 17:54:30', '2020-08-07 17:54:30', 0, 0, '2', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867743170562', NULL, NULL, '2020-08-07 17:54:30', '2020-08-07 17:54:30', 0, 0, '2', '4');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867743170563', NULL, NULL, '2020-08-07 17:54:30', '2020-08-07 17:54:30', 0, 0, '2', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364866', NULL, NULL, '2020-08-07 17:54:30', '2020-08-07 17:54:30', 0, 0, '2', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364867', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364868', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364869', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '5');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364870', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '23');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364871', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '24');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364872', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '141');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559170', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '30');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559171', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '95');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559172', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '97');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559173', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '138');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559174', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '99');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559175', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '98');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753474', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '2', '96');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753475', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '1288879303106289665');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753476', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '1288879436422242305');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753477', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '1288879370219347969');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753478', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '100');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753479', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '135');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753480', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '125');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947778', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '123');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947779', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '134');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947780', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '137');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947781', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '101');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947782', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '102');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947783', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '2', '126');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142082', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '124');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142083', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '129');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142084', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '130');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142085', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '133');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142086', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '136');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142087', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '131');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142088', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '139');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142089', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '9');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336385', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '10');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336386', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '11');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336387', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '91');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336388', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '92');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336389', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '2', '93');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336390', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '2', '140');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336391', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '2', '13');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336392', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '2', '40');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336393', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '2', '14');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336394', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '2', '27');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336395', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '2', '15');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175325929474', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '4');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175325929475', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175325929476', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175325929477', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175325929478', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175334318081', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '1297047088646905857');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175334318082', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '23');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175334318083', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '24');
INSERT INTO `t_admin_role_menu` VALUES ('1311827175334318084', NULL, NULL, '2020-10-02 08:35:45', '2020-10-02 08:35:45', 0, 0, '1307153583947956225', '1297533242571763714');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545561239554', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545569628162', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '4');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545569628163', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545569628164', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545569628165', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545573822466', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545573822467', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1297047088646905857');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545573822468', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '23');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545573822469', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '24');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016769', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1297533242571763714');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016770', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1311827586636156929');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016771', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1332332514865364994');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016772', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1321432835319414785');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016773', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '141');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016774', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '30');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016775', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '91');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016776', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1332869984937848833');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016777', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '93');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016778', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '140');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545578016779', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1335434955345399809');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211073', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1288879303106289665');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211074', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1288879370219347969');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211075', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1288879436422242305');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211076', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1330860076629590017');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211077', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1330860135886716929');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211078', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1317864829549146113');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211079', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1317865066573459457');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211080', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1319948617724743682');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211081', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1323584742796410881');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211082', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1323584939857395714');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211083', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1323584867987996674');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211084', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1312704794787368961');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211085', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1312705440890540033');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211086', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1312705547715268610');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211087', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1312755066842730498');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211088', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1323584197721440258');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211089', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '100');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211090', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '135');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211091', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '125');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211092', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '123');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211093', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '134');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545582211094', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '137');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545594793986', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '101');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545594793987', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '102');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545594793988', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '126');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545594793989', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '124');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545594793990', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '129');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545594793991', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '130');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545594793992', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '133');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545594793993', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '136');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988290', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '131');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988291', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1318472762968309761');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988292', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1318472952462770177');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988293', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1318535862241251330');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988294', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1339486097113202690');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988295', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1339486194576244738');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988296', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1339574156777242625');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988297', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1340651066982731778');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988298', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1340651170208747521');
INSERT INTO `t_admin_role_menu` VALUES ('1340651545598988299', NULL, NULL, '2020-12-20 21:33:32', '2020-12-20 21:33:32', 0, 0, '1', '1340651277373214722');
INSERT INTO `t_admin_role_menu` VALUES ('1340677773928189953', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203266', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '4');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203267', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203268', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203269', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203270', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203271', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1297047088646905857');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203272', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '23');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203273', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '24');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203274', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1297533242571763714');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203275', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1311827586636156929');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203276', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1332332514865364994');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203277', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1321432835319414785');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203278', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '141');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203279', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '30');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203280', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '91');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203281', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1332869984937848833');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203282', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '93');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203283', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '140');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203284', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1335434955345399809');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203285', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1288879303106289665');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203286', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1288879370219347969');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203287', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1288879436422242305');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774360203288', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1330860076629590017');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895042', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1330860135886716929');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895043', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1317864829549146113');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895044', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1317865066573459457');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895045', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1319948617724743682');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895046', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1323584742796410881');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895047', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1323584939857395714');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895048', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1323584867987996674');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895049', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1312704794787368961');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895050', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1312705440890540033');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895051', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1312705547715268610');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895052', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1312755066842730498');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895053', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1323584197721440258');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895054', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '100');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895055', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '135');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895056', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '125');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895057', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '123');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895058', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '134');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895059', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '137');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895060', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '101');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895061', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '102');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895062', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '126');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895063', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '124');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895064', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '129');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895065', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '130');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895066', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '133');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895067', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '136');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895068', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '131');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895069', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1318472762968309761');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895070', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1318472952462770177');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895071', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1318535862241251330');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895072', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1339486097113202690');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895073', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1339486194576244738');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895074', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1339574156777242625');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895075', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1340651066982731778');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895076', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1340651170208747521');
INSERT INTO `t_admin_role_menu` VALUES ('1340677774439895077', NULL, NULL, '2020-12-20 23:17:44', '2020-12-20 23:17:44', 0, 0, '31', '1340651277373214722');
INSERT INTO `t_admin_role_menu` VALUES ('1350296922706993153', NULL, NULL, '2021-01-16 12:20:47', '2021-01-16 12:20:47', 0, 0, '1', '1350296921910075393');
INSERT INTO `t_admin_role_menu` VALUES ('1350297066689060865', NULL, NULL, '2021-01-16 12:21:22', '2021-01-16 12:21:22', 0, 0, '1', '1350297066072498179');
INSERT INTO `t_admin_role_menu` VALUES ('1350298064698531842', NULL, NULL, '2021-01-16 12:25:20', '2021-01-16 12:25:20', 0, 0, '1', '1350298064077774850');
INSERT INTO `t_admin_role_menu` VALUES ('1350298308278542338', NULL, NULL, '2021-01-16 12:26:18', '2021-01-16 12:26:18', 0, 0, '1', '1350298307527761922');
INSERT INTO `t_admin_role_menu` VALUES ('1350305706816847873', NULL, NULL, '2021-01-16 12:55:42', '2021-01-16 12:55:42', 0, 0, '1', '1350305706179313666');
INSERT INTO `t_admin_role_menu` VALUES ('1350307257484595202', NULL, NULL, '2021-01-16 13:01:51', '2021-01-16 13:01:51', 0, 0, '1', '1350307256670900225');
INSERT INTO `t_admin_role_menu` VALUES ('505', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '1');
INSERT INTO `t_admin_role_menu` VALUES ('506', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '4');
INSERT INTO `t_admin_role_menu` VALUES ('507', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '7');
INSERT INTO `t_admin_role_menu` VALUES ('508', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '21');
INSERT INTO `t_admin_role_menu` VALUES ('509', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '22');
INSERT INTO `t_admin_role_menu` VALUES ('510', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '25');
INSERT INTO `t_admin_role_menu` VALUES ('511', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '5');
INSERT INTO `t_admin_role_menu` VALUES ('512', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '23');
INSERT INTO `t_admin_role_menu` VALUES ('513', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '24');
INSERT INTO `t_admin_role_menu` VALUES ('514', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '6');
INSERT INTO `t_admin_role_menu` VALUES ('515', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '30');
INSERT INTO `t_admin_role_menu` VALUES ('516', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '19');
INSERT INTO `t_admin_role_menu` VALUES ('517', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '9');
INSERT INTO `t_admin_role_menu` VALUES ('518', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '10');
INSERT INTO `t_admin_role_menu` VALUES ('519', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '11');
INSERT INTO `t_admin_role_menu` VALUES ('520', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '38');
INSERT INTO `t_admin_role_menu` VALUES ('521', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '37');
INSERT INTO `t_admin_role_menu` VALUES ('522', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '13');
INSERT INTO `t_admin_role_menu` VALUES ('523', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '14');
INSERT INTO `t_admin_role_menu` VALUES ('524', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '15');
INSERT INTO `t_admin_role_menu` VALUES ('525', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '16');
INSERT INTO `t_admin_role_menu` VALUES ('526', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '17');
INSERT INTO `t_admin_role_menu` VALUES ('527', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '27');
INSERT INTO `t_admin_role_menu` VALUES ('528', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '39');
INSERT INTO `t_admin_role_menu` VALUES ('529', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '40');
INSERT INTO `t_admin_role_menu` VALUES ('530', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '41');
INSERT INTO `t_admin_role_menu` VALUES ('531', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '42');
INSERT INTO `t_admin_role_menu` VALUES ('532', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '43');
INSERT INTO `t_admin_role_menu` VALUES ('533', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '46');
INSERT INTO `t_admin_role_menu` VALUES ('534', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '66');
INSERT INTO `t_admin_role_menu` VALUES ('535', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '3', '79');
INSERT INTO `t_admin_role_menu` VALUES ('71', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '0', '5');
INSERT INTO `t_admin_role_menu` VALUES ('72', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '0', '23');
INSERT INTO `t_admin_role_menu` VALUES ('73', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '0', '4');
INSERT INTO `t_admin_role_menu` VALUES ('74', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '0', '7');

-- ----------------------------
-- Table structure for t_admin_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_user`;
CREATE TABLE `t_admin_role_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/用户关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_role_user
-- ----------------------------
INSERT INTO `t_admin_role_user` VALUES ('1289941838182023169', NULL, NULL, '2020-08-02 15:11:14', '2020-08-02 15:11:14', 0, 0, '1289941795546923009', '1');
INSERT INTO `t_admin_role_user` VALUES ('1289941838194606081', NULL, NULL, '2020-08-02 15:11:14', '2020-08-02 15:11:14', 0, 0, '1289941795546923009', '2');
INSERT INTO `t_admin_role_user` VALUES ('1298208921189646338', NULL, NULL, '2020-08-25 10:41:41', '2020-08-25 10:41:41', 0, 0, '1291570418964058114', '2');
INSERT INTO `t_admin_role_user` VALUES ('1298924654799814658', NULL, NULL, '2020-08-27 18:05:45', '2020-08-27 18:05:45', 0, 0, '1298924600584241153', '1');
INSERT INTO `t_admin_role_user` VALUES ('1298924654799814659', NULL, NULL, '2020-08-27 18:05:45', '2020-08-27 18:05:45', 0, 0, '1298924600584241153', '2');
INSERT INTO `t_admin_role_user` VALUES ('1298924654808203266', NULL, NULL, '2020-08-27 18:05:45', '2020-08-27 18:05:45', 0, 0, '1298924600584241153', '3');
INSERT INTO `t_admin_role_user` VALUES ('1301118870858788866', NULL, NULL, '2020-09-02 19:24:47', '2020-09-02 19:24:47', 0, 0, '1301118870707793922', '1');
INSERT INTO `t_admin_role_user` VALUES ('1301119000164986881', NULL, NULL, '2020-09-02 19:25:17', '2020-09-02 19:25:17', 0, 0, '1301119000072712194', '2');
INSERT INTO `t_admin_role_user` VALUES ('1301119698961199105', NULL, NULL, '2020-09-02 19:28:04', '2020-09-27 13:48:45', 1, 0, '1301119698839564290', '1');
INSERT INTO `t_admin_role_user` VALUES ('1301395103026860033', NULL, NULL, '2020-09-03 13:42:25', '2020-09-23 16:13:11', 1, 0, '1301395102821339138', '2');
INSERT INTO `t_admin_role_user` VALUES ('1301395103026860034', NULL, NULL, '2020-09-03 13:42:25', '2020-09-23 16:13:11', 1, 0, '1301395102821339138', '1');
INSERT INTO `t_admin_role_user` VALUES ('1304668285271916545', NULL, NULL, '2020-09-12 14:28:53', '2020-09-12 14:28:53', 0, 0, '1298902907425898498', '1');
INSERT INTO `t_admin_role_user` VALUES ('1304668285276110849', NULL, NULL, '2020-09-12 14:28:53', '2020-09-12 14:28:53', 0, 0, '1298902907425898498', '2');
INSERT INTO `t_admin_role_user` VALUES ('1304668285276110850', NULL, NULL, '2020-09-12 14:28:53', '2020-09-12 14:28:53', 0, 0, '1298902907425898498', '3');
INSERT INTO `t_admin_role_user` VALUES ('1304668285276110851', NULL, NULL, '2020-09-12 14:28:53', '2020-09-12 14:28:53', 0, 0, '1298902907425898498', '31');
INSERT INTO `t_admin_role_user` VALUES ('1305423144950509569', NULL, NULL, '2020-09-14 16:28:26', '2020-09-14 16:28:26', 0, 0, '1298925383975370754', '1');
INSERT INTO `t_admin_role_user` VALUES ('1306871661673910274', NULL, NULL, '2020-09-18 16:24:19', '2020-09-18 16:24:19', 0, 0, '1306868842262761474', '1306868429283201026');
INSERT INTO `t_admin_role_user` VALUES ('1307133733712248833', NULL, NULL, '2020-09-19 09:45:42', '2020-09-21 16:21:50', 1, 0, '20', '1');
INSERT INTO `t_admin_role_user` VALUES ('1307135254592045058', NULL, NULL, '2020-09-19 09:51:44', '2020-09-24 17:45:24', 1, 0, '1', '1');
INSERT INTO `t_admin_role_user` VALUES ('1307135254629793793', NULL, NULL, '2020-09-19 09:51:44', '2020-09-24 17:45:24', 1, 0, '1', '2');
INSERT INTO `t_admin_role_user` VALUES ('1307194241404420097', NULL, NULL, '2020-09-19 13:46:08', '2020-09-21 15:56:54', 1, 0, '1298800271548674049', '1');
INSERT INTO `t_admin_role_user` VALUES ('1307951925632221186', NULL, NULL, '2020-09-21 15:56:54', '2020-09-29 15:33:43', 1, 0, '1298800271548674049', '1');
INSERT INTO `t_admin_role_user` VALUES ('1307958202026426370', NULL, NULL, '2020-09-21 16:21:50', '2020-09-21 20:20:57', 1, 0, '20', '1');
INSERT INTO `t_admin_role_user` VALUES ('1308018376619593730', NULL, NULL, '2020-09-21 20:20:57', '2020-09-23 16:13:42', 1, 0, '20', '1');
INSERT INTO `t_admin_role_user` VALUES ('1308680799609516033', NULL, NULL, '2020-09-23 16:13:11', '2020-09-23 16:13:11', 0, 0, '1301395102821339138', '2');
INSERT INTO `t_admin_role_user` VALUES ('1308680799609516034', NULL, NULL, '2020-09-23 16:13:11', '2020-09-23 16:13:11', 0, 0, '1301395102821339138', '1');
INSERT INTO `t_admin_role_user` VALUES ('1308680928638889985', NULL, NULL, '2020-09-23 16:13:42', '2020-09-24 10:21:21', 1, 0, '20', '1');
INSERT INTO `t_admin_role_user` VALUES ('1308954647542145026', NULL, NULL, '2020-09-24 10:21:21', '2020-09-25 09:29:19', 1, 0, '20', '1');
INSERT INTO `t_admin_role_user` VALUES ('1309066399045394433', NULL, NULL, '2020-09-24 17:45:24', '2020-09-24 17:45:28', 1, 0, '1', '1');
INSERT INTO `t_admin_role_user` VALUES ('1309066399053783042', NULL, NULL, '2020-09-24 17:45:24', '2020-09-24 17:45:28', 1, 0, '1', '2');
INSERT INTO `t_admin_role_user` VALUES ('1309066399053783043', NULL, NULL, '2020-09-24 17:45:24', '2020-09-24 17:45:28', 1, 0, '1', '31');
INSERT INTO `t_admin_role_user` VALUES ('1309066415977799682', NULL, NULL, '2020-09-24 17:45:28', '2020-10-03 13:20:18', 1, 0, '1', '1');
INSERT INTO `t_admin_role_user` VALUES ('1309066415977799683', NULL, NULL, '2020-09-24 17:45:28', '2020-10-03 13:20:18', 1, 0, '1', '2');
INSERT INTO `t_admin_role_user` VALUES ('1309339583554105346', NULL, NULL, '2020-09-25 11:50:57', '2020-09-26 11:30:11', 1, 0, '1309339583159840769', '1');
INSERT INTO `t_admin_role_user` VALUES ('1309696743899209729', NULL, NULL, '2020-09-26 11:30:11', '2020-09-26 11:30:11', 0, 0, '1309339583159840769', '1');
INSERT INTO `t_admin_role_user` VALUES ('1310092058437935106', NULL, NULL, '2020-09-27 13:41:00', '2020-09-27 13:41:00', 0, 0, '1310066957497204738', 'string');
INSERT INTO `t_admin_role_user` VALUES ('1310094002910519297', NULL, NULL, '2020-09-27 13:48:45', '2020-09-27 13:50:01', 1, 0, '1301119698839564290', '1');
INSERT INTO `t_admin_role_user` VALUES ('1310094321354661890', NULL, NULL, '2020-09-27 13:50:01', '2020-09-27 13:50:01', 0, 0, '1301119698839564290', '1');
INSERT INTO `t_admin_role_user` VALUES ('1310845194708025345', NULL, NULL, '2020-09-29 15:33:43', '2020-09-29 15:33:43', 0, 0, '1298800271548674049', '1');
INSERT INTO `t_admin_role_user` VALUES ('1311825787531399169', NULL, NULL, '2020-10-02 08:30:14', '2020-10-03 13:19:00', 1, 0, '20', '1');
INSERT INTO `t_admin_role_user` VALUES ('1312381642211414017', NULL, NULL, '2020-10-03 13:19:01', '2020-10-03 13:19:50', 1, 0, '20', '31');
INSERT INTO `t_admin_role_user` VALUES ('1312382201270194177', NULL, NULL, '2020-10-03 13:21:14', '2020-10-03 13:21:14', 0, 0, '1', '1');
INSERT INTO `t_admin_role_user` VALUES ('1312382264520298498', NULL, NULL, '2020-10-03 13:21:29', '2020-10-03 13:21:35', 1, 0, '20', '31');
INSERT INTO `t_admin_role_user` VALUES ('1312382306744356865', NULL, NULL, '2020-10-03 13:21:39', '2020-10-03 13:21:39', 0, 0, '20', '31');
INSERT INTO `t_admin_role_user` VALUES ('42', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '1');
INSERT INTO `t_admin_role_user` VALUES ('43', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '2');
INSERT INTO `t_admin_role_user` VALUES ('44', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '3');
INSERT INTO `t_admin_role_user` VALUES ('45', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '1');
INSERT INTO `t_admin_role_user` VALUES ('46', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '2');
INSERT INTO `t_admin_role_user` VALUES ('47', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '3');
INSERT INTO `t_admin_role_user` VALUES ('49', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '42', '2');

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `head` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号/用户名',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号(第二账号)',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `age` int(3) NOT NULL COMMENT '年龄',
  `gender` int(1) NOT NULL DEFAULT 0 COMMENT '性别(1男，0女)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '是否禁用(0-否，1-是)',
  `reg_time` datetime(0) NOT NULL COMMENT '注册时间',
  `end_time` datetime(0) DEFAULT NULL COMMENT '最后登录时间',
  `position` int(1) NOT NULL DEFAULT 0 COMMENT '职位(字典code)( 弃用)',
  `wx_open_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信openId',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', NULL, NULL, '2020-08-02 15:11:04', '2021-03-05 14:37:00', 0, 0, 'http://xijia.plus/oss/file/image/head/20200822150143006266-5.png', 'wangsong', '17628689969', '兮家小二', 'dfd5e22c8ee4de7da4f07a75fefb6420', '四川成都', 0, 1, 0, '2020-08-02 23:11:05', '2021-03-05 14:37:00', 0, NULL);
INSERT INTO `t_admin_user` VALUES ('1313865056773025794', NULL, NULL, '2020-10-07 15:33:34', '2021-01-23 14:15:53', 0, 0, 'http://xijia.plus/oss/file/image/head/20201007233323096566-tp (15).jpg', '1', '1', '1', '624bef4ab99df8464ac8dc9e4616e067', '1', 1, 1, 0, '2020-10-07 23:33:35', '2021-01-15 15:25:29', 0, NULL);
INSERT INTO `t_admin_user` VALUES ('20', NULL, NULL, '2020-07-25 09:31:07', '2021-03-05 14:35:08', 0, 0, 'http://xijia.plus/oss/file/image/head/20200712000102739895-1.png', '10000', '10000', '系统体验用户', '7121b2eeea031a4ecfffcdecb60158fa', '四川成都', 20, 2, 0, '2020-01-31 10:15:07', '2021-03-05 14:35:08', 0, NULL);

-- ----------------------------
-- Table structure for t_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_basic`;
CREATE TABLE `t_basic`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统通用字段表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_xj_admin_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_banner`;
CREATE TABLE `t_xj_admin_banner`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `position` int(2) NOT NULL COMMENT '位置(字典code)',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'banner标题',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'banner描叙',
  `img_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'banner图片',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT 'banner排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT 'banner禁用(0-启用 1-禁用)',
  `is_skip` int(1) NOT NULL DEFAULT 0 COMMENT '是否跳转(0-无  1-内部链接 2-外部链接)',
  `skip_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '跳转地址url(地址直接添加或字典表配置)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--banner' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_xj_admin_banner
-- ----------------------------
INSERT INTO `t_xj_admin_banner` VALUES ('1300258807352893441', NULL, NULL, '2020-08-31 10:27:12', '2020-09-22 19:25:03', 0, 0, 1, '测试3', '测试数据3', 'http://yabei.520ban.com/oss/file/image/banner/20200910093614691653-qs44ufe2024qs44ufe2024.jpg', 3, 0, 0, '/page/banner');
INSERT INTO `t_xj_admin_banner` VALUES ('1300260217146548226', NULL, NULL, '2020-08-31 10:32:48', '2020-09-24 16:15:26', 0, 0, 1, '测试2', '测试数据2', 'http://yabei.520ban.com/oss/file/image/banner/20200910093608751358-2.jpg', 1, 0, 0, '/page/logoBanner/page/logoBanner');
INSERT INTO `t_xj_admin_banner` VALUES ('1300262684328435714', NULL, NULL, '2020-08-31 10:42:36', '2020-09-30 17:59:22', 0, 0, 1, '测试1', '测试数据一', 'http://yabei.520ban.com/oss/file/image/banner/20200910093405696081-timg (6).jpg', 0, 0, 2, 'http://www.baidu.com');
INSERT INTO `t_xj_admin_banner` VALUES ('1303881706571894785', NULL, NULL, '2020-09-10 10:23:18', '2020-09-24 16:16:13', 1, 0, 1, '231', '312', 'http://yabei.520ban.com/oss/file/image/banner/20200919134953270044-2dea1d468fae5da7a7ec808d3ca41ca3.jpg', 1, 0, 2, 'https://www.xiaopiu.com/project?proid=5f2d1df9fd472f065fa9a43d');
INSERT INTO `t_xj_admin_banner` VALUES ('1309111625118248961', NULL, NULL, '2020-09-24 20:45:06', '2021-02-05 15:36:11', 0, 0, 1, '测试', '测试描叙', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/2.jpg', 0, 0, 0, '');
INSERT INTO `t_xj_admin_banner` VALUES ('1309668956979171329', NULL, NULL, '2020-09-26 09:39:46', '2020-09-26 09:49:12', 1, 0, 1, 'q', '1', 'http://yabei.520ban.com/oss/file/image/banner/20200926093943912599-dog_logo.jpg', 0, 0, 2, NULL);
INSERT INTO `t_xj_admin_banner` VALUES ('1309670283884990466', NULL, NULL, '2020-09-26 09:45:02', '2020-09-26 09:49:21', 1, 0, 1, '1', '1', 'http://yabei.520ban.com/oss/file/image/banner/20200926094455863590-0080L5aXgy1gcrfgxe0wsj30pg0ron7h.jpg', 0, 0, 1, NULL);
INSERT INTO `t_xj_admin_banner` VALUES ('1309670412985667586', NULL, NULL, '2020-09-26 09:45:33', '2020-09-26 09:49:18', 1, 0, 1, '1', '1', 'http://yabei.520ban.com/oss/file/image/banner/20200926094529206937-c0140160gy1gdk3igvhalj20qm0qmwgg.jpg', 0, 0, 1, NULL);
INSERT INTO `t_xj_admin_banner` VALUES ('1309671292019511297', NULL, NULL, '2020-09-26 09:49:03', '2020-09-26 09:49:15', 1, 0, 1, '1', '1', 'http://yabei.520ban.com/oss/file/image/banner/20200926094858974599-c0140160gy1gdk3igvhalj20qm0qmwgg.jpg', 0, 0, 1, '11');

-- ----------------------------
-- Table structure for t_xj_admin_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_blacklist`;
CREATE TABLE `t_xj_admin_blacklist`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `type` int(1) NOT NULL COMMENT '1-白名单(* 表示允许除黑名单外的所有ip请求, 否则只允许白名单中的ip请求) 2-黑名单(禁止ip访问)',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '白名单ip/黑名单ip',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--黑名单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_xj_admin_blacklist
-- ----------------------------
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332333202949324802', NULL, NULL, '2020-11-27 22:39:21', '2020-11-28 02:34:20', 0, 0, 1, '*', '允许所有 ip 地址访问，优先级比黑名单(*) 高 ， 开启了白名单(*), 黑名单（*）将无效', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332337401510551554', NULL, NULL, '2020-11-27 22:56:02', '2020-11-28 02:34:24', 0, 0, 2, '*', '禁止所有 ip 访问,除本地 [127.0.0.1 / localhost] ,不建议配置在所有资源上，一旦配置，所有用户(包括自己) 将无法访问所有资源，因为每个用户的ip地址都不一样， 开启此功能需提前配置所有用户的ip地址为白名单', 1);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332374043059408898', NULL, NULL, '2020-11-28 01:21:40', '2020-11-28 02:16:08', 1, 0, 1, '127.0.0.1', '本地ip', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332385237027622913', NULL, NULL, '2020-11-28 02:06:09', '2020-11-28 02:22:30', 1, 0, 1, 'xijia.plus', '放行所有在主站域名访问的请求', 0);

-- ----------------------------
-- Table structure for t_xj_admin_config
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_config`;
CREATE TABLE `t_xj_admin_config`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置code|搜索值(不能重复)',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置内容',
  `sort` int(11) NOT NULL COMMENT '排序',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '类型(0-文本 1-图片 2-开关)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--全局数据配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_xj_admin_config
-- ----------------------------
INSERT INTO `t_xj_admin_config` VALUES ('1301418489568354306', NULL, NULL, '2020-09-03 15:15:21', '2021-02-26 14:25:53', 1, 0, 'test', '轮播图', 'http://xijia.plus/oss/file/image/config/20201003225629104904-2.png,http://xijia.plus/oss/file/image/config/20201003225629152533-1.png,http://xijia.plus/oss/file/image/config/20201003225629148146-3.png,http://xijia.plus/oss/file/image/config/20201003225629160064-4.png', 12, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1302915933162500097', NULL, NULL, '2020-09-07 18:25:40', '2021-02-26 14:18:59', 1, 0, 'SHIPPING_ADDRESS', '收件地址', '姓名|17628689969|四川省|成都市|xx区|详细地址', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1307234252338278401', NULL, NULL, '2020-09-19 16:25:07', '2021-02-26 14:22:15', 1, 0, 'GG', '公告', '通知：[兮家]于2019年11月1日上线了', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1307269386961739778', NULL, NULL, '2020-09-19 18:44:44', '2021-02-26 14:18:55', 1, 0, 'WE_CHAT_E', '二维码', 'http://xijia.plus/oss/file/image/config/20201003225606039898-Screenshot_2020-10-02-18-39-19-41.jpg', 0, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1311829179448266754', NULL, NULL, '2020-10-02 08:43:43', '2020-11-27 22:40:27', 1, 0, '5', '6', '7', 8, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1311971607199105025', NULL, NULL, '2020-10-02 18:09:41', '2020-10-02 18:36:52', 1, 0, '1', '1', '', 1, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1311972234180489217', NULL, NULL, '2020-10-02 18:12:10', '2020-11-27 22:40:25', 1, 0, '2', '2', '', 2, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1311972544600928258', NULL, NULL, '2020-10-02 18:13:24', '2020-10-03 09:53:13', 1, 0, '3', '3', '', 2, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1312058240292384770', NULL, NULL, '2020-10-02 23:53:56', '2020-10-03 09:53:07', 1, 0, '1', '1', 'http://localhost:9048/oss/file/image/config/20201002235338009261-1.png,http://localhost:9048/oss/file/image/config/20201002235338010096-2.png', 1, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1312058328108527617', NULL, NULL, '2020-10-02 23:54:16', '2020-10-03 09:53:04', 1, 0, '2', '2', 'http://localhost:9048/oss/file/image/config/20201002235411672303-2.png,http://localhost:9048/oss/file/image/config/20201002235411673655-1.png', 1, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1312058412619558913', NULL, NULL, '2020-10-02 23:54:37', '2020-10-03 09:53:02', 1, 0, '4', '3', 'http://localhost:9048/oss/file/image/config/20201002235427122526-1.png,http://localhost:9048/oss/file/image/config/20201002235427123265-2.png', 3, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1312208949423935489', NULL, NULL, '2020-10-03 09:52:47', '2020-10-03 09:53:00', 1, 0, '5', '5', 'http://localhost:9048/oss/file/image/config/20201003095217761560-1601689937000.jpeg,http://localhost:9048/oss/file/image/config/20201003095243525669-3.png,http://localhost:9048/oss/file/image/config/20201003095243525552-4.png', 5, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1329266352233381889', NULL, NULL, '2020-11-19 11:32:49', '2020-11-22 23:25:50', 1, 0, 'QY_ZL', '企业资料', 'http://xijia.plus/oss/file/image/config/20201119113240516696-身份证反.png,http://xijia.plus/oss/file/image/config/20201119113240541029-身份证正.png,http://xijia.plus/oss/file/image/config/20201119113240468684-d4390aa31c8690934a51d2146c02c95.jpg,http://xijia.plus/oss/file/image/config/20201119113240726073-d57bcbb0d59a3a453078800359e9b3e.jpg', 0, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1354276340517404674', NULL, NULL, '2021-01-27 11:53:35', '2021-01-27 11:53:41', 1, 0, 'll', '参数', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210127115318106218-1611719598000.jpeg', 1, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1365174805250260994', NULL, NULL, '2021-02-26 13:40:11', '2021-02-26 14:30:12', 0, 0, 'entry_name', '项目名称(登录页+首页)', '兮家', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365182627308433409', NULL, NULL, '2021-02-26 14:11:17', '2021-02-26 14:37:51', 0, 0, 'login_bg_img', '背景图(登录页)', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/timg2028329.jpg', 0, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1365185332319997953', NULL, NULL, '2021-02-26 14:22:01', '2021-02-26 14:29:28', 0, 0, 'beian', '备案号(登录页)', '备案号：蜀ICP备19022468号-1', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365187122549551105', NULL, NULL, '2021-02-26 14:29:09', '2021-02-26 14:56:26', 0, 0, 'project_desc', '项目描叙(登录页)', '©2020-2021 该后台系统为个人开发运营，作者联系方式 QQ:1720696548', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365189285623484417', NULL, NULL, '2021-02-26 14:37:44', '2021-02-26 14:56:37', 1, 0, 'login_code_img', '滑动验证码图片集', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/timg2028929.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/u3D12224875882C42672150826fm3D21426gp3D0.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/timg.jpg', 0, 1);

-- ----------------------------
-- Table structure for t_xj_admin_datasource
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_datasource`;
CREATE TABLE `t_xj_admin_datasource`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `db_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库标题',
  `db_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库名',
  `db_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库连接',
  `db_username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库账号',
  `db_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库密码',
  `db_prefix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据表前缀',
  `db_field_prefix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据字段前缀',
  `db_general_field` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库通用字段,逗号分隔',
  `pack_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '包根路径',
  `modules` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父模块名',
  `modules_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子模块名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--代码生成动态数据源' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_xj_admin_help
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_help`;
CREATE TABLE `t_xj_admin_help`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '图标 | 封面图(保留字段)',
  `category` int(3) NOT NULL COMMENT '分类(字典code)',
  `help_version` int(2) NOT NULL COMMENT '版本(字典code)',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容 (富文本|文本+图片+视频)',
  `sort` int(1) NOT NULL DEFAULT 0 COMMENT '排序',
  `browse_num` int(11) NOT NULL DEFAULT 0 COMMENT '浏览量(每次Id查询+1)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--帮助中心文档' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_xj_admin_help
-- ----------------------------
INSERT INTO `t_xj_admin_help` VALUES ('1308970595280044034', NULL, NULL, '2020-09-24 11:24:44', '2020-10-20 12:35:45', 1, 0, '', 0, 5, '视频上传规则', '<h1>最贵的领克值得买吗？赛道体验领克05</h1><p>2020年08月18日 11:55来源：<a href=\"https://www.maiche.com/media/m160/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(51, 51, 51);\">小仓说车</a>作者：<span style=\"color: rgb(51, 51, 51);\">李嘉琪</span></p><p>[车友头条-<a href=\"http://media.maiche.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">车友号</a>-<a href=\"https://www.maiche.com/media/m160/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">小仓说车</a>]&nbsp;&nbsp;</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/e168c2141b144372868710e5407dd28a.jpg!jpg\"></p><p>时隔几个月~刘赛道终于重现江湖！这期下赛道的是一台最贵的<a href=\"https://www.maiche.com/brands/286/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">领克</a>--<a href=\"https://www.maiche.com/s4765/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">领克05</a>，时间有限话不多说，我们一起去看看领克05赛道的表现吧~</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/3f2e975519354d30823a1dc841a45891.jpg!jpg\"></p><p>从外观上看，领克05在延续了领克家族分体式车灯造型之外，新增横向<a href=\"https://www.maiche.com/news/detail/1283079.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">LED</a>灯柱，既生动勾勒出领克独有的家族式脸谱，又让前部造型焕然一新。能量矩阵尾灯的高辨识度令人过目难忘。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/97fbe4a6d5d042f8a6b583fe5f5a28b7.jpg!jpg\"></p><p>进入驾驶舱内，05的中控台采用了非对称设计，向驾驶员一侧倾斜，营造出比较舒适的环抱感。05全系标配双12+ 英寸全液晶仪表和中控显示屏，12.3英寸全液晶智能仪表支持导航地图全覆盖。内饰缝合采用黑色皮革搭配红色缝线，更适合这款轿跑SUV，营造出一种运动的氛围。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/f07d178245f84de7b461c722b60d5dd6.jpg!jpg\"></p><p>O5采用的2.0T高<a href=\"https://www.maiche.com/news/detail/1283514.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">功率</a><a href=\"https://www.maiche.com/news/detail/1283179.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">发动机</a>，最大功率254马力，峰值<a href=\"https://www.maiche.com/news/detail/1283525.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">扭矩</a>350N·m，匹配爱信8速手自一体变速箱，配有适时四驱系统，加速性能毋需质疑，全力刹车的情况下，整车的制动力非常充足，给到驾驶者十足的信心，只不过偏大的点头动作会让05在外面看起来不太从容而已。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/b2fd483b0442492bb9a8f324bb06fb36.jpg!jpg\"></p><p>而在<a href=\"https://www.maiche.com/news/detail/1283295.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">麋鹿测试</a>中，05展现出了极为精准的转向，车头的反应非常迅速，车尾的跟随性也有很不错的惊喜，整个过程一气呵成，只是过高的重心和过大的车身侧倾会影响整车行云流水的状态，不过鉴于它本身是SUV的定位，这种表现已经是足够出色了</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/2e57042a13fd4f918f8951a8a8a05fcb.jpg!jpg\"></p><p>适时四驱系统在05身上同样表现不俗，在面对<a href=\"https://www.maiche.com/news/detail/1283519.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">交叉轴</a>、单侧车轮打滑的情况下，它的脱困也来得足够地轻松，这用于应付一般的非铺装道路，已经是绰绰有余。（文/车友号 小仓说车）</p>', 0, 41);
INSERT INTO `t_xj_admin_help` VALUES ('1308970781012213761', NULL, NULL, '2020-09-24 11:25:28', '2020-10-20 12:35:47', 1, 0, '', 0, 3, '面相照', '<p>	[车友头条-<a href=\"http://media.maiche.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">车友号</a>-<a href=\"https://www.maiche.com/media/m160/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">小仓说车</a>]&nbsp;&nbsp;</p><p>	<img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/01/09/17/5b9c97230db548c5a0998ed08f04d427.jpg!jpg\"></p><p>		<a href=\"https://www.maiche.com/brands/14/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">本田</a>在中国赚得盆满钵满，双车战略功不可没。东风本田的<a href=\"https://www.maiche.com/s138/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">CR-V</a>在市场上攻城略地大快朵颐时，<a href=\"https://www.maiche.com/brands/45/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">广汽</a>本田内心当中难道不会有些许嫉妒，还带着一点淡淡的忧桑？终于在2019年年底时，广汽本田把CR-V重新打扮了一番，并取了个洋气的名字，就这样<a href=\"https://www.maiche.com/s4645/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">皓影</a>被推向了市场。从CR-V的销量来看，皓影未来销量肯定差不了。这么一款未来可期的车型，养车费用如何呢，我们就一起来看看吧。</p><p>	关于皓影的养车费用，我们将围绕油费、保险、保养以及易损配件四个部分进行讨论。本田同款车型下的燃油车和混动车在保养费用上差别极小，因此我们今天讨论的重点将主要针对1.5T两驱车型进行。</p><p>			<img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/01/09/17/4426a7f8bfb04bd9a6cae1608eac5199.jpg!jpg\"></p><p>	经过计算，我们得出<a href=\"https://www.maiche.com/s4645/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">本田皓影</a>1.5T车型一年用车总费用为21155元，月均费用为1763元。我们接下来再来逐项进行分析。</p><p>			<img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/01/09/17/66f3fd37b15b4dca83145e167cef07ca.jpg!jpg\"></p><p>	先来看看油费，根据网上真实车主反馈，本田皓影1.5T车型百公里综合油耗为8.2升，以目前北京92号汽油6.99元/升，一年行驶20000公里计算，则本田皓影全年油费约为11464元，平摊到每个月则为955元，每公里油费折合0.57元。本田1.5T+<a href=\"https://www.maiche.com/news/detail/1283025.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">CVT</a>的动力总成，油耗控制得不错。</p><p>			<img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/01/09/17/c7820b2967604758acd65087ad72a30c.jpg!jpg\"></p><p>	接下来我们再来聊聊本田皓影的保险费用，商业险部分选择100万<a href=\"https://www.maiche.com/news/detail/1283512.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">第三者责任险</a>，以及车辆损失险和<a href=\"https://www.maiche.com/news/detail/1283508.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">不计免赔</a>特约险，我们以指导价17.98万元的皓影两驱精英版为例，商业险部分为4854元，加上<a href=\"https://www.maiche.com/news/detail/1283520.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">交强险</a>和车船税，保险部分总额为6154元。</p><p>			<img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/01/09/17/b0ecba36162847abb69930d5f41dd3e5.jpg!jpg\"></p><p>	我们再来一起看看本田皓影的保养费用。目前，本田皓影车型全合成<a href=\"https://www.maiche.com/news/detail/1283109.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">机油</a>费用为418元，加上其它配件和厂家要求使用的<a href=\"https://www.maiche.com/news/detail/1283435.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">燃油添加剂</a>，单次<a href=\"https://www.maiche.com/news/detail/1283437.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">小保养</a>费用为817元。当然，你也可以考虑使用更便宜的半合成机油，小保养能便宜个一两百。</p><p>			<img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/01/09/17/7eeaecfe6cc54903890bb1d7c4f7a567.jpg!jpg\"></p><p>	根据保养手册5000公里一保计算，本田皓影行驶60000公里总保养费用为10611元，以一年20000公里计算，则前三年年均保养费用为3537元。目前本田车型基本都会要求每次保养，都使用燃油添加剂，因此前60000公里当中，添加剂费用就达到1540元。</p><p>			<img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/01/09/17/f0eafbbdde654d71b7827800f2f51f56.jpg!jpg\"></p><p>	最后，我们一起来看看本田皓影常用易损配件价格。皓影更换前后保险杠费用分别为2326和2096元，更换前<a href=\"https://www.maiche.com/news/detail/113020.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">LED大灯</a>总成和前挡玻璃的费用分别为4536元和2826元。除了大灯稍微贵一点以外，其它配件费用都略低于<a href=\"https://www.maiche.com/s143/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">雅阁</a>。</p><p>			<img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/01/09/17/77d33f013a5f420288a51ac77c07b477.jpg!jpg\"></p><p>	总体来讲，皓影的养车成本就是非常典型的本田风格，油耗仍然控制得不错，保养费用谈不上便宜，但也能接受，配件费用在同<a href=\"https://www.maiche.com/news/detail/113488.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">级别</a>也属于正常水平。</p><p>	好了，关于本田皓影的用车成本，我们就聊到这里了。如果您还对其它车型的养车费用感兴趣，欢迎在下方评论区留言。（文/车友号 小仓说车）</p>', 0, 54);
INSERT INTO `t_xj_admin_help` VALUES ('1309041949144526850', NULL, NULL, '2020-09-24 16:08:16', '2020-10-12 15:13:17', 1, 0, '', 0, 1, '吐烟视频', '<p><br></p><p><img src=\"https://user-assets.sxlcdn.com/images/549416/FuoUq0fhgWTvYxPU5RwLgZLGYKfE.jpg?imageMogr2/strip/auto-orient/thumbnail/500x1000%3E/quality/90!/interlace/1/format/jpeg\" width=\"100%\"></p><p>隐形牙齿矫正适合什么年龄的人群？</p><p>smilelab笑盈，被问得最多的问题一定是「我今年xx岁了，还能整牙吗」，年龄覆盖下至20+，上至50+。</p><p><br></p><p class=\"ql-align-center\"><strong>更多详情</strong></p><p class=\"ql-align-center\">在smilelab笑盈公众号的后台，被问得最多的问题一定是「我今年xx岁了，还能整牙吗」，年龄覆盖下至20+，上至50+。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">大家怀揣着同一个愿望而来，又心怀着同一个疑虑，觉得自己的牙齿还可以再拯救一下，又觉得自己的年纪摆在这，好像已经不适合也没必要做了。可是，「年龄」真的足以成为我们整牙的阻碍吗？</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\"><strong>为什么很多人觉得晚了？</strong></p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">很多人觉得现在来不及做矫正了，我们总结了下，大概是有2个原因：</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">1）一个事实：我们都知道，牙齿矫正的合适时期是青少年（10-15岁）阶段，因为这个时候牙齿畸形开始显现，恒牙也都全部长出，牙齿移动中牙槽骨的改建快、可塑性强，牙齿移动后容易稳定。</p><p class=\"ql-align-center\">2）一个误区：很多人觉得，成年后骨头就定型了，牙齿矫正会没有效果。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">但其实，年龄不是影响牙齿矫正的因素，<strong>牙周炎</strong>才是！</p><p class=\"ql-align-center\"><img src=\"http://user-assets.sxlcdn.com/images/549416/FtKpOX0ydybA4w54Zf_SKGbPOsxp.jpg?imageMogr2/strip/auto-orient/thumbnail/1200x9000%3E/interlace/1/format/jpeg\"></p><p class=\"ql-align-center\"><strong>你需要知道的牙齿矫正原理</strong></p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\"><strong>牙齿矫正的原理主要是依靠牙槽骨改建。</strong>简单来说，牙齿矫正依靠的牙槽骨，也就是包裹着牙齿的骨头，是人体全身一处比较特殊的骨头。它的特殊之处在于，如果给它一个力，它就可以随之移动，就像按着矫正方案走的隐形牙套就会赋予牙齿移动的力量。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">而这种移动的特性，是终生存在的，也就是说，牙槽骨在人的各个阶段都具有移动的能力，这就从根本上让成年后牙齿矫正具有可能性。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\"><img src=\"http://user-assets.sxlcdn.com/images/549416/FhnUxsv2jUvVYYUnjc-of5iGTkfw.png?imageMogr2/strip/auto-orient/thumbnail/1200x9000%3E/quality/90!/format/png\"></p><p class=\"ql-align-center\">但是如果没有健康的牙周的话，牙齿就会不稳定，像牙周炎（牙周组织发炎）就会破坏牙槽骨，对牙齿矫正产生影响，进而可能产生牙齿松动等问题。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">牙周炎一般是这样形成的：先是食物残屑、细菌在唾液的作用下，在牙齿表面形成「软垢」，也就是牙菌斑，继而牙菌斑沉积，形成牙结石。两者在牙龈上方沉积到一定的量时，牙龈发炎，渐渐蔓延至牙周组织，从而形成牙周炎。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">如果是严重的牙周炎，是不能矫正的；如果是轻微的牙周炎，可以矫正，但需要先做牙周治疗，在牙周稳定的情况下再进行矫正。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\"><strong>最好的时间，是现在</strong></p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">青少年阶段没有来得及矫正，这并不意味着牙齿矫正对成年人就没有机会了。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">首先，从医患配合角度来讲，成年人牙齿矫正与青少年相比，还是具有一些的优势的。成年人能很好地配合治疗，达到理想的矫正结果。相对而言，青少年自制力就会差一些，往往不能很好地遵守医嘱，不能很好地维护口腔卫生，从而影响矫正。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">其次，年龄真的不是问题，比如这些鲜活的例子：</p><p class=\"ql-align-center\">邓紫棋，<strong>25岁</strong>矫正；</p><p class=\"ql-align-center\">萧敬腾，<strong>29岁</strong>矫正；</p><p class=\"ql-align-center\">岳云鹏，<strong>31岁</strong>矫正；</p><p class=\"ql-align-center\">董璇，<strong>33岁</strong>矫正；</p><p class=\"ql-align-center\">阿雅，<strong>38岁</strong>矫正；</p><p class=\"ql-align-center\">伊能静，<strong>48岁</strong>做了牙齿矫正</p><p class=\"ql-align-center\"><img src=\"http://user-assets.sxlcdn.com/images/549416/Ft9A4dpVwrSl_YUYPRfaqCyQr1Yb.png?imageMogr2/strip/auto-orient/thumbnail/1200x9000%3E/quality/90!/format/png\"></p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">周海媚在<strong>52岁</strong>的时候，也选择了牙齿矫正来改善兔牙</p><p class=\"ql-align-center\"><img src=\"http://user-assets.sxlcdn.com/images/549416/FsIgjRuAkLnsqE_n6vyZgayrLGWg.png?imageMogr2/strip/auto-orient/thumbnail/1200x9000%3E/quality/90!/format/png\"></p><p class=\"ql-align-center\">固然明星们是出于出镜需要选择牙齿矫正，但是他们的矫正经历也正说明了：<strong>牙齿矫正真的不受年龄影响。</strong></p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">相信大家都听过这样一句话：「种一棵树最好的时间是十年前，其次是现在」，牙齿矫正也是同理，就算已经错过了青少年的黄金矫正时期，<strong>现在依然是矫正的最佳时机。</strong></p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">更何况，牙齿矫正不单单是为了牙齿美观，更是为了口腔健康啊。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">最后总结一下，</p><p class=\"ql-align-center\"><strong>只要牙周良好，或早期牙周炎经治疗后稳定，都可以进行正畸治疗。</strong></p><p class=\"ql-align-center\"><strong>只要牙周良好，或早期牙周炎经治疗后稳定，都可以进行正畸治疗。</strong></p><p class=\"ql-align-center\"><strong>只要牙周良好，或早期牙周炎经治疗后稳定，都可以进行正畸治疗。</strong></p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\">重要的事情说三遍，所以不要担心自己因为年龄问题不能矫正啦。</p><p class=\"ql-align-center\">&nbsp;</p><p class=\"ql-align-center\"><img src=\"http://user-assets.sxlcdn.com/images/549416/FraB8VdXzFkgu6pQut8EvOgiHko9.jpg?imageMogr2/strip/auto-orient/thumbnail/1200x9000%3E/quality/90!/interlace/1/format/jpeg\"></p>', 0, 162);
INSERT INTO `t_xj_admin_help` VALUES ('1309052499891187713', NULL, NULL, '2020-09-24 16:50:11', '2020-10-20 12:35:49', 1, 0, '', 1, 0, '治疗流程', '<p>想要通过整牙矫正牙齿，<strong>整牙流程是什么？</strong>很多人对整牙的理解就是戴牙套，但整牙开始前需要进行一系列的检查分析。下面就给大家介绍整牙的全过程，做的每个步骤都是为了达到预期的矫正效果。</p><p>在了解整牙流程是什么之前，首先告诉大家，牙齿并不是固定在骨头里面的，牙齿和牙槽骨之间有一种组织叫做“牙周膜”，由于牙槽骨与牙周膜的特性，给牙齿一个适当的力量，牙齿就可以在骨头里面慢慢移动，医生就是利用这种力量移动牙齿的。</p><p class=\"ql-align-center\"><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190307/8ac51403c1d6497baa7128a65b36a8c9.gif\"></p><p class=\"ql-align-center\">整牙流程是什么？概括下来，普通的矫正流程大概可以分成下面 6 步：</p><p class=\"ql-align-center\">1.各项检查，病史调查，口腔分析，建立病例档案</p><p class=\"ql-align-center\">2.结合患者主诉、时间、美观、经济，制定方案</p><p class=\"ql-align-center\">3.做准备，牙周治疗，选择托槽</p><p class=\"ql-align-center\">4.上托槽，开始治疗，定期复诊</p><p class=\"ql-align-center\">5.应急处理，根据现实情况调整计划</p><p class=\"ql-align-center\">6.结束治疗，佩戴保持器</p><p class=\"ql-align-center\"><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190307/68daf9aadde347a3af280d6625fa6cbc.jpeg\"></p><p>整牙需要多长的时间？主要有3个影响因素</p><p>1、个人的牙齿情况：牙齿基础条件好，时间相对而言也会缩短。牙齿情况比较复杂的患者，需要进行多步牙齿矫正的程序，这样矫正的时间就会比其他人要长。</p><p>2、患者的个人年龄：儿童处于生长发育阶段，颌骨可塑性高，牙齿移动起来比较快，矫正牙齿的时间就会相对的缩短，而成人则相反，具体还要看实际情况。</p><p>3、矫正的技术材料：选用不同的矫正方式进行矫正，矫正器的材料不同，所需的时长就会不一样</p><p>整牙需要多长的时间？一般在1-2年左右，但根据每个人不同的年龄、严重程度、牙齿对治疗的反应等因素，治疗所需要的时间也会有所增减。只要你和医生好好合作，按时复诊，保持口腔卫生健康，保护矫治器不损坏，治疗的效率就能大大提高！</p><p class=\"ql-align-center\"><img src=\"http://5b0988e595225.cdn.sohucs.com/images/20190307/6ba413c6bda54230a3f16ba8402b96a2.png\"></p><p>整牙过程中要多摄取的食物</p><p><strong>蔬菜和水果：</strong>蔬菜和水果的摄入量可占到每天食物总量的1/2（如果太大应注意切成小块），蔬菜和水果中富含的维生素，可以减轻进行矫正者可能常患的口腔溃疡、口角炎。葡萄、草莓、番茄、菠菜等都富含维生素C，可以适量摄入;</p><p><strong>谷物、奶制品、蛋白质：</strong>这些食物对均衡营养至关重要，有助于牙齿的矫正。但要注意咀嚼避免过硬、过黏、过大的食物，且在进食后要坚持正确刷牙及使用牙线，保持口腔卫生。</p><p>一个完整的整牙流程是什么就写到这里了。推荐去口腔专科医院的正畸科接受矫正，医生会根据检查结果制定方案，医生的技术很大程度上决定了整牙的效果，先进的设备也起到了重要的辅助作用。正规医院相对诊所，整牙更有保障，价格也更合理。</p>', 0, 27);
INSERT INTO `t_xj_admin_help` VALUES ('1309053926692745218', NULL, NULL, '2020-09-24 16:55:51', '2020-10-20 12:35:53', 1, 0, '', 2, 0, '操作指南', '<h3>1.功能简介</h3><h4>&nbsp;&nbsp;功能简介</h4><p>1.首页：我的办件、最新政策公告、常用工具以及申报事项分类。</p><p>2.平台包含的功能：政务管理、网上申报、网上预约、网上咨询、离线材料补充等等。</p><p>3.其他包含的功能：统计分析、网上投诉，通知打印。</p><p><br></p><p><br></p><p><br></p><p><br></p><h2 class=\"ql-align-center\">第二章 系统功能</h2><h3>1.政务管理</h3><h4>1.1政策公告</h4><p>公告是行政公文的主要文种之一，政策公告是政府对重要事项、法定事项、变动及政策宣布时使用的公。如下图所示：</p><p><br></p><p><span style=\"color: rgb(0, 0, 0);\"><img src=\"http://qybs.nx.gov.cn/inc_work9/incwork/ExplainAll/images/E1-1.png\"></span></p><p><br></p><p><br></p><p><br></p><h3>2.网上申报</h3><h4>2.1选择地区</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;点击选择地区，用户可以根据自己要查询的政务大厅进行更换。</p><h4>2.2.政务管理</h4><h4>2.2.1政策公告</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;公告是行政公文的主要文种之一，政策公告是政府对重要事项、法定事项、变动及政策宣布时使用的公。如下图所示：</p><h4><span style=\"color: rgb(0, 0, 0);\"><img src=\"http://qybs.nx.gov.cn/inc_work9/incwork/ExplainAll/images/E2-1-1.png\"></span>2.3网上申报</h4><h4>2.3.1.查找事项</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;查找事项列表分为查询条件和事项列表，通过查询条件查到具体事项后，可直接点击申报，也可通过“查看详细内容”功能，在申报前了解事项的具体信息。查询条件有按照政务服务中心、按照服务中心下的部门，以及按照事项名称查询。如下图所示：</p><p><br></p><p><br></p><h4>2.3.2网上申报</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;依托各级政务服务部门行政审批系统，将各级政府部门的审批事项具体内容在企业在线办事大厅平台上展示出来，按照企业所办理事项进行分类显示（按事项分类，按部门分类），如行政审批，非行政审批，公共服务等。企业根据所需选择相关事项，可查看该事项的详细内容并进行申报（包括事项名称、事项类型、所属部门等）。</p><p><br></p><p><span style=\"color: rgb(0, 0, 0);\"><img src=\"http://qybs.nx.gov.cn/inc_work9/incwork/ExplainAll/images/E2-2.png\"></span></p><p><br></p><h4>2.3.3申报查看</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;显示所有申报过的办件，可在此处查看所有办件状态、办件时间、预审回复等。</p><h4>&nbsp;&nbsp;&nbsp;&nbsp;查询</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;通过申报件的信息，如申报部门、申办件名称、办件状态查询，也可进行模糊查询。</p><h4>&nbsp;&nbsp;&nbsp;&nbsp;查看预审情况</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;通过查看预审状态，了解申报事项是否预审通过，具体情况可通过预审回复列查看详细情况。</p><p><br></p><p><span style=\"color: rgb(0, 0, 0);\"><img src=\"http://qybs.nx.gov.cn/inc_work9/incwork/ExplainAll/images/E2-3.png\"></span></p><p><br></p><p><br></p><p><br></p><h3>2.4网上预约</h3><h4>2.4.1我要预约</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;企业根据需要办理的审批事项可以进行网上预约申请，填写申请后，提交事项中所要求的资料信息，进行预约办事。</p><h4>&nbsp;&nbsp;&nbsp;&nbsp;预约流程</h4><p>1.填写企业预约表单，主要包括：预约人、联系电话、电子邮箱、预约中心、预约部门、预约办件、预约时间、预约内容。</p><p>2.提交表单，填写完表单后，提交预约。预约信息自动传到预约中心的审批系统中。</p><p>3.预约中心相关窗口人员根据预约内容等回复预约是否通过。</p><p>4.面对面咨询办理，通过后可按照预约时间到相应窗口办件。</p><h4>&nbsp;&nbsp;&nbsp;&nbsp;预约信息</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;显示已经预约的件数，和回复以及及时办理提示，如下图：</p><p><br></p><p><br></p><h4>2.4.2我的预约</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;显示我预约过的所有事项，可点击查看是否回复等。</p><h4>&nbsp;&nbsp;&nbsp;&nbsp;查询</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;通过预约信息，如预约人、预审内容、申报中心、预约部门、预约事项等查询，也可进行模糊查询。</p><h4>&nbsp;&nbsp;&nbsp;&nbsp;查看回复情况</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;通过回复情况，了解预约事项是否预约成功，具体情况可通过预约回复列查看详细情况。</p><p><br></p>', 0, 52);
INSERT INTO `t_xj_admin_help` VALUES ('1309054944159592449', NULL, NULL, '2020-09-24 16:59:54', '2020-10-20 12:35:55', 1, 0, '', 3, 0, '集团介绍', '<p><img src=\"http://yabei.520ban.com/oss/file/image/banner/20200924165935978583-4da23cb47e2a4e8d5ee2a364a9c69df5.jpg\"></p>', 0, 25);
INSERT INTO `t_xj_admin_help` VALUES ('1309055351824969729', NULL, NULL, '2020-09-24 17:01:31', '2020-10-20 12:35:57', 1, 0, '', 4, 0, '错合危害', '<p><img src=\"http://yabei.520ban.com/oss/file/image/banner/20200924170112409959-7299d1ae8ee96f5ce1f52b010d651a39.jpg\"></p>', 0, 13);
INSERT INTO `t_xj_admin_help` VALUES ('1315551450551095297', NULL, NULL, '2020-10-12 15:14:42', '2020-10-20 12:35:42', 1, 0, '', 0, 4, '口内照上传规则', '<h1>最贵的领克值得买吗？赛道体验领克05</h1><p>2020年08月18日 11:55来源：<a href=\"https://www.maiche.com/media/m160/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(51, 51, 51);\">小仓说车</a>作者：<span style=\"color: rgb(51, 51, 51);\">李嘉琪</span></p><p>[车友头条-<a href=\"http://media.maiche.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">车友号</a>-<a href=\"https://www.maiche.com/media/m160/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">小仓说车</a>]&nbsp;&nbsp;</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/e168c2141b144372868710e5407dd28a.jpg!jpg\"></p><p>时隔几个月~刘赛道终于重现江湖！这期下赛道的是一台最贵的<a href=\"https://www.maiche.com/brands/286/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">领克</a>--<a href=\"https://www.maiche.com/s4765/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">领克05</a>，时间有限话不多说，我们一起去看看领克05赛道的表现吧~</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/3f2e975519354d30823a1dc841a45891.jpg!jpg\"></p><p>从外观上看，领克05在延续了领克家族分体式车灯造型之外，新增横向<a href=\"https://www.maiche.com/news/detail/1283079.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">LED</a>灯柱，既生动勾勒出领克独有的家族式脸谱，又让前部造型焕然一新。能量矩阵尾灯的高辨识度令人过目难忘。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/97fbe4a6d5d042f8a6b583fe5f5a28b7.jpg!jpg\"></p><p>进入驾驶舱内，05的中控台采用了非对称设计，向驾驶员一侧倾斜，营造出比较舒适的环抱感。05全系标配双12+ 英寸全液晶仪表和中控显示屏，12.3英寸全液晶智能仪表支持导航地图全覆盖。内饰缝合采用黑色皮革搭配红色缝线，更适合这款轿跑SUV，营造出一种运动的氛围。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/f07d178245f84de7b461c722b60d5dd6.jpg!jpg\"></p><p>O5采用的2.0T高<a href=\"https://www.maiche.com/news/detail/1283514.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">功率</a><a href=\"https://www.maiche.com/news/detail/1283179.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">发动机</a>，最大功率254马力，峰值<a href=\"https://www.maiche.com/news/detail/1283525.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">扭矩</a>350N·m，匹配爱信8速手自一体变速箱，配有适时四驱系统，加速性能毋需质疑，全力刹车的情况下，整车的制动力非常充足，给到驾驶者十足的信心，只不过偏大的点头动作会让05在外面看起来不太从容而已。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/b2fd483b0442492bb9a8f324bb06fb36.jpg!jpg\"></p><p>而在<a href=\"https://www.maiche.com/news/detail/1283295.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">麋鹿测试</a>中，05展现出了极为精准的转向，车头的反应非常迅速，车尾的跟随性也有很不错的惊喜，整个过程一气呵成，只是过高的重心和过大的车身侧倾会影响整车行云流水的状态，不过鉴于它本身是SUV的定位，这种表现已经是足够出色了</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/2e57042a13fd4f918f8951a8a8a05fcb.jpg!jpg\"></p><p>适时四驱系统在05身上同样表现不俗，在面对<a href=\"https://www.maiche.com/news/detail/1283519.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">交叉轴</a>、单侧车轮打滑的情况下，它的脱困也来得足够地轻松，这用于应付一般的非铺装道路，已经是绰绰有余。（文/车友号 小仓说车）</p>', 0, 6);
INSERT INTO `t_xj_admin_help` VALUES ('1315551642314674177', NULL, NULL, '2020-10-12 15:15:27', '2020-10-20 12:35:10', 1, 0, '', 0, 6, 'x光上传规则', '<h1>最贵的领克值得买吗？赛道体验领克05</h1><p>2020年08月18日 11:55来源：<a href=\"https://www.maiche.com/media/m160/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(51, 51, 51);\">小仓说车</a>作者：<span style=\"color: rgb(51, 51, 51);\">李嘉琪</span></p><p>[车友头条-<a href=\"http://media.maiche.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">车友号</a>-<a href=\"https://www.maiche.com/media/m160/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">小仓说车</a>]&nbsp;&nbsp;</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/e168c2141b144372868710e5407dd28a.jpg!jpg\"></p><p>时隔几个月~刘赛道终于重现江湖！这期下赛道的是一台最贵的<a href=\"https://www.maiche.com/brands/286/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">领克</a>--<a href=\"https://www.maiche.com/s4765/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">领克05</a>，时间有限话不多说，我们一起去看看领克05赛道的表现吧~</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/3f2e975519354d30823a1dc841a45891.jpg!jpg\"></p><p>从外观上看，领克05在延续了领克家族分体式车灯造型之外，新增横向<a href=\"https://www.maiche.com/news/detail/1283079.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">LED</a>灯柱，既生动勾勒出领克独有的家族式脸谱，又让前部造型焕然一新。能量矩阵尾灯的高辨识度令人过目难忘。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/97fbe4a6d5d042f8a6b583fe5f5a28b7.jpg!jpg\"></p><p>进入驾驶舱内，05的中控台采用了非对称设计，向驾驶员一侧倾斜，营造出比较舒适的环抱感。05全系标配双12+ 英寸全液晶仪表和中控显示屏，12.3英寸全液晶智能仪表支持导航地图全覆盖。内饰缝合采用黑色皮革搭配红色缝线，更适合这款轿跑SUV，营造出一种运动的氛围。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/f07d178245f84de7b461c722b60d5dd6.jpg!jpg\"></p><p>O5采用的2.0T高<a href=\"https://www.maiche.com/news/detail/1283514.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">功率</a><a href=\"https://www.maiche.com/news/detail/1283179.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">发动机</a>，最大功率254马力，峰值<a href=\"https://www.maiche.com/news/detail/1283525.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">扭矩</a>350N·m，匹配爱信8速手自一体变速箱，配有适时四驱系统，加速性能毋需质疑，全力刹车的情况下，整车的制动力非常充足，给到驾驶者十足的信心，只不过偏大的点头动作会让05在外面看起来不太从容而已。</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/b2fd483b0442492bb9a8f324bb06fb36.jpg!jpg\"></p><p>而在<a href=\"https://www.maiche.com/news/detail/1283295.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">麋鹿测试</a>中，05展现出了极为精准的转向，车头的反应非常迅速，车尾的跟随性也有很不错的惊喜，整个过程一气呵成，只是过高的重心和过大的车身侧倾会影响整车行云流水的状态，不过鉴于它本身是SUV的定位，这种表现已经是足够出色了</p><p><img src=\"https://toutiao.image.mucang.cn/toutiao-image/2020/08/18/11/2e57042a13fd4f918f8951a8a8a05fcb.jpg!jpg\"></p><p>适时四驱系统在05身上同样表现不俗，在面对<a href=\"https://www.maiche.com/news/detail/1283519.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(72, 159, 240);\">交叉轴</a>、单侧车轮打滑的情况下，它的脱困也来得足够地轻松，这用于应付一般的非铺装道路，已经是绰绰有余。（文/车友号 小仓说车）</p>', 0, 19);
INSERT INTO `t_xj_admin_help` VALUES ('1318526613985427458', NULL, NULL, '2020-10-20 12:16:55', '2020-11-21 22:56:03', 1, 0, '', 3, 0, 'Spring Boot Admin', '## 介绍\n\nSpring Boot Admin提供了很多服务治理方面的功能，利用它能节省我们很多在治理服务方面的时间和精力Spring Boot Admin提供了如下功能（包括但不限于）：\n\n显示健康状态及详细信息，如JVM和内存指标、数据源指标、缓存指标\n跟踪并下载日志文件\n查看jvm系统-和环境属性\n查看Spring启动配置属性\n方便loglevel管理\n查看线程转储\n视图http-traces\n查看http端点\n查看计划任务\n查看和删除活动会话(使用spring-session)\n状态更改通知(通过电子邮件、Slack、Hipchat…)\n状态变化的事件日志(非持久性)\n……(and more !)\n## 一、环境配置\n因为我是单体项目  server + client 直接放一起整合了，\n-  springboot  2.2.0 ,\n-  spring-boot-admin 2.2.2 ( 用boot 对应的 2.2.0 应该好点, 目前没问题就先不管了 )\n#### 1、maven 依赖\n```\n  <!-- actuator 监控中心 -->\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-actuator</artifactId>\n        </dependency>\n       <!-- spring-boot-admin ，必须有 actuator监控中心 -->\n        <dependency>\n            <groupId>de.codecentric</groupId>\n            <artifactId>spring-boot-admin-starter-server</artifactId>\n            <version>2.2.2</version>\n        </dependency>\n        <dependency>\n            <groupId>de.codecentric</groupId>\n            <artifactId>spring-boot-admin-starter-client</artifactId>\n            <version>2.2.2</version>\n        </dependency>\n```\n\n\n#### 2、yml - actuator 监控, 配置spring-boot-admin使用\n\n```\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n```\n\n#### 3、yml  - spring-boot-admin配置\n\n```\nspring:\n  ## spring-boot-admin 配置\n  boot:\n    admin:\n      ## admin 服务端访问地址\n      context-path: /bootAdmin/\n      ## admin 客户端连接服务端\n      client:\n        # 为了显示客户端的ip否则是以主机名显示的，这样需要添加hosts影射。\n        instance:\n          prefer-ip: true\n        url: http://localhost:${server.port}/${spring.boot.admin.context-path}/\n        #username: admin\n        #password: admin@12345\n```\n#### 4、yml  - spring-boot-admin 实时日志配置\nfilePath 为自己的 logback配置 ， 主要是 logging.file  ,  spring-boot-admin 会自动读取对应的文件\n\n```\n## logback日志输出\nlogging:\n  # 日志目录, 线上请配置linux 绝对路径 如：/usr/local/work/xijia2/logs\n  filePath: logs\n  # 配置日志路径即可实时输出到 spring-boot-admin 日志管理\n  file: src/main/resources/static/log/xijia.log\n```\n#### 5、启动类添加注解\n\n```\n@EnableAdminServer\n```\n## 二、效果展示\n#### 主页\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018235829708.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70#pic_center)\n#### 系统信息\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018235850527.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70#pic_center)\n#### 环境配置\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20201019002403441.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70#pic_center)\n\n#### 日志\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018235914353.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70#pic_center)\n#### 部署配置，线上的实时输出文件    \n   `logging.file =  /usr/local/work/xijia2/log.txt`\n   \n![在这里插入图片描述](https://img-blog.csdnimg.cn/20201019001526729.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70#pic_center)\n\n\n- 个人开源项目（通用后台管理系统）--> [  https://gitee.com/wslxm/spring-boot-plus2](https://gitee.com/wslxm/spring-boot-plus2)  , 喜欢的可以看看\n\n\n- ***本文到此结束，如果觉得有用，动动小手点赞或关注一下呗，将不定时持续更新更多的内容…，感谢大家的观看！***', 0, 145);
INSERT INTO `t_xj_admin_help` VALUES ('1318531741886971905', NULL, NULL, '2020-10-20 12:37:18', '2020-10-25 03:14:23', 1, 0, '', 2, 0, 'mysql 基础语法大全', '## 序章:MySQL如何优化\n#### 1、优化方案\n1、添加适当索引(index) [四种: 普通索引、主键索引、唯一索引unique、全文索引]\n2、SQL语句优化\n3、分表技术(水平分割、垂直分割)\n4、读写[写: update/delete/add]分离\n5、存储过程 [模块化编程，可以提高速度]\n6、对mysql配置优化 [配置最大并发数my.ini, 调整缓存大小 ]\n7、mysql服务器硬件升级\n8、定时的去清除不需要的数据,定时进行碎片整理(MyISAM)\n#### 2、数据库设计三大范式\n表的设计合理化(符合3NF)\n1、第一范式：**原子性约束，不可再分解**（字段内容，如地址，可拆分成省/市/区）\n-------1NF是对属性的原子性约束，要求属性(列)具有原子性，不可再分解；(只要是关系型数据库都满足1NF)\n2、第二范式：**惟一性约束** (id主键)\n-------2NF是对记录的惟一性约束，表中的记录是唯一的, 就满足2NF, 通常我们设计一个主键来实现，主键不能包含业务逻辑。\n3、第三范式：**子段冗余性的约束** (重复数据拆分成多个表保存)\n-------3NF是对字段冗余性的约束，它要求字段没有冗余。 没有冗余的数据库设计可以做到。但是，没有冗余的数据库未必是最好的数据库，有时为了提高运行效率，就必须降低范式标准，适当保留冗余数据。具体做法是： 在概念数据模型设计时遵守第三范式，降低范式标准的工作放到物理数据模型设计时考虑。降低范式就是增加字段，允许冗余\n#### 3、慢查询\n慢查询的作用？\n------- 查询慢的语句会已日志的方式记录下来，通过日志记录查看哪些sql 查询慢，在调优查询慢的语句\n什么是慢查询？\n------  MySQL默认10秒内没有响应SQL结果,则为慢查询\n------ 可以去修改MySQL慢查询默认时间\n\n```\n--查询慢查询时间\nshow variables like \'long_query_time\';\n--修改慢查询时间\nset long_query_time=1; ---但是重启mysql之后，long_query_time依然是my.ini中的值\n```\n在默认情况下，我们的mysql不会记录慢查询，需要在启动mysql时候，指定记录慢查询才可以，在 my.ini 中指定，\n\n\n\n## 一、查询sql\n本文表sql+数据，https://download.csdn.net/download/qq_41463655/10991818\n#### 一般查询\n#### 1、简单查询  \n语法：Select * |列名 from 表名\n\n \n\n       Select * from ws_user;                        #查询所有\n        Select username from ws_user;                 #只查询username列\n        Select username,password from ws_user;        #只查询username,password列\n\n#### 2、别名  \n语法：Select 列名 别名.... from 表名\n\n    Select username \'昵称\',password \'密码\' from ws_user;  #使用别名\n\n\n#### 3、去除重复数据  \n语法：Select distinct *|列名 from 表名\n\n    Select distinct password from ws_user;         #去除重复数据,所有列数据相同才去除\n\n\n#### 4、四则运算\n如下，需加表的的别名 或 表.字段名称\n\n    `select m.rank*12  from ws_menu m;`            # 使用四则运算 + - * /\n    `select * from ws_menu m where m.rank > 100;`  # 只查询 rank字段大于100的\n\n#### 5、非空判断\n\n    select * from ws_menu m where m.rank is not null; # 查询 rank 不为空的\n    select * from ws_menu m where m.rank is null;     # 查询 rank 为空的\n\n#### 6、where  and  or 连接查询 及 非空大于小于判断\n\n    # 查询 rank 不为空 且 > 500 的    # and   \n    select * from ws_menu m where m.rank is not null and  m.rank > 500; \n    \n    # 查询 rank > 500  且小于 1500 的\n    select * from ws_menu m where m.rank >500 and m.rank < 1500;\n    select * from ws_menu m where m.rank >= 500 and m.rank <= 1500;\n    \n    # 查询 rank 不为空 或者 rank > 500 的 (满足其中一个 or)\n    select * from ws_menu m where m.rank is not null or  m.rank > 500;\n    \n    # 查询 rank > 500  或者小于 1500 的\n    select * from ws_menu m where m.rank >500 or m.rank < 1500;\n\n#### 7、时间及排序\n\n   \n\n  时间区间查询 between   升序排序：order by o.start_time asc \n\n    select * from ws_order o where o.start_time between \'2017-05-06\' and \'2019-06-6\' order by o.start_time asc;\n    \n   时间比较查询 >  <  降序排序：order by o.start_time desc\n\n    select * from ws_order o where o.start_time > \'2017-05-06\' and o.start_time <\'2019-06-6\' order by o.start_time desc;\n\n\n###### 下列所有 sql ，ws_order o 为表名 ，  o.start_time 为时间字段\n\n\n###  8、指定查询  in，not in \n\n    select * from ws_menu where menu_id = 1;                 # 查询id=1的；\n    select * from ws_menu where text = \'用户管理\';           # 使用字符串查询\n    \n    select * from ws_menu where menu_id = 1 or menu_id = 2;  # 同时查询id=1 和 2 的；\n    select * from ws_menu where menu_id in(1,2);             # 同时查询id=1 和 2 的；  \n    select * from ws_menu where text in(\'用户管理\',\'部门管理\'); \n    \n    select * from ws_menu where menu_id not in(1);        # 查询除id不等于1的；\n    select * from ws_menu where menu_id not in(1,2);      # 查询除id不等于1 和 2 的；\n\n### 9、不等于表达式 <>  !=\n\n    select * from ws_menu where menu_id != 1;   # 查询除id不等于1的；\n    select * from ws_menu where menu_id <> 1;   # 查询除id不等于1的；\n\n### 10、模糊查询 like _  %\n\n    select * from ws_menu m where m.text like \'_户管理\';  # 匹配一个任意字段内容\n    select * from ws_menu m where m.text like \'__管理\';   # 匹配两个任意字段\n    select * from ws_menu m where m.text like \'%理\';      # 匹配无数个任意字段\n    select * from ws_menu m where m.text like \'%管%\';     # 中间带管字的全部查询处理\n\n\n### 11、排序 order by 排序字段名 asc | desc\n\n    select * from ws_menu m order by m.rank asc;  # 降序，新数据在后\n    select * from ws_menu m order by m.rank desc; # 升序，新数据在前\n\n    # 可以指定多个排序字段，优先指定前一个，前一个字段相同或空根据第二个字段排序\n    select * from ws_menu m order by m.rank asc,m.menu_id asc; \n    select * from ws_menu m order by m.rank asc,m.menu_id desc; \n\n\n### 时间查询\n##### 查询48 小时内的数据\n\n    select * from ws_order o where o.start_time >=(NOW() - interval 24 hour)\n\n\n##### 今天，0点开始\n\n    select * from ws_order o where to_days(o.start_time) = to_days(now());\n\n##### 昨天到现在\n\n    SELECT * FROM ws_order o WHERE TO_DAYS( NOW( ) ) - TO_DAYS(o.start_time) <= 1;\n\n##### 7天\n\n    SELECT * FROM ws_order o where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(o.start_time);\n\n##### 近30天\n\n    SELECT * FROM ws_order o where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(o.start_time);\n\n##### 本月\n\n    SELECT * FROM ws_order o WHERE DATE_FORMAT(o.start_time, \'%Y%m\' ) = DATE_FORMAT( CURDATE( ) , \'%Y%m\' );\n\n##### 上一月\n\n    SELECT * FROM ws_order o WHERE PERIOD_DIFF( date_format( now( ) , \'%Y%m\' ) , date_format(o.start_time, \'%Y%m\' ) ) =1;\n\n##### 查询本季度数据\n\n    select * from ws_order o where QUARTER(o.start_time)=QUARTER(now());\n\n##### 查询上季度数据\n\n    select * from ws_order o where QUARTER(o.start_time)=QUARTER(DATE_SUB(now(),interval 1 QUARTER));\n\n##### 查询本年数据\n\n    select * from ws_order o where YEAR(o.start_time)=YEAR(NOW());\n\n \n##### 查询上年数据\n\n    select * from ws_order o where year(o.start_time)=year(date_sub(now(),interval 1 year));\n\n\n##### 查询当前这周的数据 \n\n    SELECT * FROM ws_order o WHERE YEARWEEK(date_format(o.start_time,\'%Y-%m-%d\')) = YEARWEEK(now());\n\n##### 查询上周的数据，及上上周\n\n    SELECT * FROM ws_order o  WHERE YEARWEEK(date_format(o.start_time,\'%Y-%m-%d\')) = YEARWEEK(now())-1;\n    SELECT * FROM ws_order o  WHERE YEARWEEK(date_format(o.start_time,\'%Y-%m-%d\')) = YEARWEEK(now())-2;\n\n##### 查询当前月份的数据\n\n    select * from ws_order o  where date_format(o.start_time,\'%Y-%m\')=date_format(now(),\'%Y-%m\')\n\n##### 查询距离当前现在6个月的数据\n\n    select * from ws_order o  where o.start_time between date_sub(now(),interval 6 month) and now();\n\n##### 查询上个月的数据\n\n    select * from ws_order o  where date_format(o.start_time,\'%Y-%m\')=date_format(DATE_SUB(curdate(), INTERVAL 1 MONTH),\'%Y-%m\');\n\n### 12、多表查询\n\n语法：SELECT {DISTINCT} *|列名.. FROM 表名 别名，表名1 别名\n{WHERE 限制条件	ORDER BY 排序字段 ASC|DESC...}\n\n#### 1、双表查询\n    # 双表查询（商品订单+订单详情(笛卡尔积,数据多+乱))\n    select * from ws_order,ws_order_detail;    \n    \n     #去掉笛卡尔积,只留下有关系的数据\n    select * from ws_order o,ws_order_detail d where o.order_id = d.order_id; \n    \n    # 查询订单号，下单时间，价格，数量及总价格\n    select o.order_no,o.start_time,d.order_price,d.order_num,d.oeder_money from ws_order o,ws_order_detail d where o.order_id = d.order_id; \n\n#### 2、三表查询（商品订单+订单详情+订单日志)\n\n    select * from ws_order o,ws_order_detail d,ws_order_log l \n    where o.order_id = d.order_id and o.order_id = l.order_id;  \n    \n    # 只看 1429 的数据\n    select * from ws_order o,ws_order_detail d,ws_order_log l \n    where o.order_id = d.order_id and o.order_id = l.order_id and o.order_id =1429;  \n    \n    # 只看 1429 的具体数据\n    select o.order_no,d.oeder_money,l.log_notes,log_time\n    from ws_order o,ws_order_detail d,ws_order_log l \n    where o.order_id = d.order_id and o.order_id = l.order_id and o.order_id =1429;  \n\n#### 3、交叉查询  cross join\n\n    交叉查询  cross join\n    select * from ws_order cross join ws_order_detail; #产生笛卡尔积\n    \n    ## 交叉查询 using子句 (类似于连接条件查询)\n    select * from ws_order cross join ws_order_detail using(order_id) where order_id = 1308;\n    \n    ## 交叉查询 on子句 ()，相当于 where\n    select * from ws_order o cross join ws_order_detail d on o.order_id = d.order_id;\n\n    ## 上一句同下\n    select * from ws_order o,ws_order_detail d where o.order_id = d.order_id; \n\n\n####  4、左连接 left join\n\n    select * from ws_order o left join ws_order_detail d on o.order_id = d.order_id;\n\n####  5、左连接 left join\n\n    select * from ws_order o right join ws_order_detail d on o.order_id = d.order_id;\n\n\n### 13、统计查询\n####  1、数量 count()\n\n    select count(*) from ws_menu;          ## 不建议使用*\n    select count(m.rank) from ws_menu m;   ## 随意指定一列无空字段的数据\n\n#### 2、取最小值 min()\n\n    select min(m.rank) from ws_menu m; \n\n#### 3、 取最大值 max()\n\n    select max(m.rank) from ws_menu m; \n\n#### 4、取平均值 avg()\n\n    select avg(m.rank) from ws_menu m; \n\n#### 5、取想加的和\n\n    select sum(m.rank) from ws_menu m; \n\n\n### 14、分组统计 group by\n#注意：只有分组条件在结果集中是重复的分组才有意义\n\n 语法：SELECT * |列名 FROM 表名 \n       {WEHRE 查询条件}   \n       {GROUP BY 分组字段} \n       ORDER BY 列名1 ASC|DESC，列名2...ASC|DESC\n\n#### 1、查询根据 type 类别分组， 返回值与分组值要相同,不能出现其他字段\n\n    select o.type from ws_order o group by o.type;   \n\n#### 2、根据 type 类别分组并计算数量\n\n    select o.type,count(type) from ws_order o group by o.type;  \n\n#### 3、根据 type 类别分组并计算数量,且类别下的数量 > 1 的（having）\n\n    select o.type,count(type) from ws_order o group by o.type having count(type) > 1;   \n\n\n#### 4、查询每个类别下价格最高的商品（type，类别）\n\n    select o.type,max(d.order_price) from ws_order o,ws_order_detail d where o.order_id = d.order_id group by o.type;\n\n####  5、查询每个类别下价格最低的商品\n\n    select o.type,min(d.order_price) from ws_order o,ws_order_detail d where o.order_id = d.order_id group by o.type;\n\n#### 6、查询每个类别下商品的平均价格\n\n    select o.type,avg(d.order_price) from ws_order o,ws_order_detail d where o.order_id = d.order_id group by o.type;\n\n#### 7、查询每个类别下商品的平均价格 且大于 2700 的（having）\n\n    select o.type,avg(d.order_price) from ws_order o,ws_order_detail d where o.order_id = d.order_id group by o.type\n    having avg(d.order_price) > 2700;\n\n#### 8、查询每个类别下商品的的总价格之和\n\n    select o.type,sum(d.oeder_money) from ws_order o,ws_order_detail d where o.order_id = d.order_id group by o.type;\n\n\n\n### 15、子查询\n\n#### 1、查询比 id = 1310 的商品价格高的所有数据\n\n    # 子sql 返回 1310 的价格\n    \n    select * from ws_order_detail o where o.order_price > (\n        select o1.order_price from ws_order_detail o1 where o1.order_detail_id = 1310\n    );\n\n#### 2、查询比 id = 1310 的商品价格高且订单数量比 id = 1310 多的所有数据\n\n    # 子sql 一返回 1310 的价格\n    # 子sql 二返回 1310 的订单数量\n    \n    select * from ws_order_detail o \n     where o.order_price > (\n        select o1.order_price from ws_order_detail o1 where o1.order_detail_id = 1310 \n    		) \n     and o.remain_num > (\n        select o2.remain_num from ws_order_detail o2 where o2.order_detail_id = 1310 \n    		);\n\n#### 3、子sql 返回的是一张表，根据 type 统计的统计表，\n\n    # typenum 是统计的数量字段名\n    # 统计分组数量 大于1 条以上的\n    \n    select * from ws_order o,(select o1.type,count(type) typenum from ws_order o1 group by o1.type) o1\n    where o.type = o1.type \n    and o1.typenum > 1;\n\n### 16、in 同时查询(list)\n\n\n ####  1、查询所有类别下的所有最低价格的商品\n    # 子sql：查询所有类别下的每个类别最低价的商品集\n    select * from ws_order_detail d \n    where d.order_price in (\n      select min(d1.order_price) minprice from ws_order_detail d1 where d1.order_price group by d1.order_price\n    	);\n\n\n#### 2、查询有密码的所有用户（无密码的 not in）\n \n\n       # 子sql 去重查询所有sql\n        select * from ws_user u where u.password in(\n             select distinct password from ws_user u1 where u.password = u1.password\n        );\n\n#### 3、查询无密码的所有用户（有密码的不要not）\n\n    # 子sql 去重查询所有sql\n    # 同上（not exists 假，exists 真）\n    \n        select * from ws_user u where not exists(\n          select * from ws_user u1 where u.password = u1.password \n        );\n\n#### 4、union 对两个结果集进行并集操作，都查询出来合并（不包括重复数据）\n\n    select * from ws_order_detail o where o.order_price > 2400\n    union\n    select * from ws_order_detail o where o.order_price > 2800;\n\n####   5、union all 对两个结果集进行并集操作，都查询出来合并（包括重复数据）\n\n    select * from ws_order_detail o where o.order_price > 2400\n    union all\n    select * from ws_order_detail o where o.order_price > 2400;\n\n\n\n\n\n### 分页查询sql 及优化\n\n\n#### 1、查询索引 x 后的 y 条数据\n\n    select * from ws_user limit 8,2;\n\n#### 2、带排序的分页\n\n    select * from ws_user order by user_id limit 8,2;\n\n\n\n####  3、带条件的分页\n\n    select * from ws_user where 1=1 limit 8,2;\n\n\n####  4、优化分页查一（使用子sql，只适用于id自增的表）\n\n    # 子sql 开始获取开始的第一条数据的id\n    # 查询大于该id 的后一百条数据\n    select * from ws_user u where u.user_id >=(select user_id from ws_user limit 5,1) limit 100;\n\n\n####  5、优化分页二（范围查询，可以通过页数和记录数计算范围）\n\n    # 查询id 1到1000范围内的，从1开始后的100条数据\n    select * from ws_user u where u.user_id between 1 and 1000 limit 100;\n\n#### 6、优化分页三（范围查询，可以通过页数和记录数计算范围）\n\n    # 查询id从1开始后的100条数据\n    select * from ws_user where user_id >= 5 limit 100;\n\n#### 7、oracle 分页sql\n\n    ## 把查询的sql  放中间一套就好了  select * from eb_item\n    select * from(\n            select rownum rw,a.* from (\n                 \n                 ## 查询sql 区\n                    select * from eb_item t\n                    where t.brand_id = 1003\n                    and t.audit_status = 1\n                    and t.show_status = 1\n                    and t.item_name like \'%星%\'\n                 ## 查询sql 区\n    \n              ) a where rownum < 11\n     ) b where b.rw > 1\n\n## 二、增删改查sql\n\n\n    select * from ws_user\n    select * from ws_order\n\n#### 1、添加数据\n\n     ## 语法 ： INSERT  INTO表名[(列名1，列名2，...)]VALUES(值1，值2，...)\n     ##   或 ： INSERT  INTO 表名VALUES(值1，值2，...)\n    insert into ws_user(user_id,username,password) values(10,\"zhangsan\",\'123456\');\n\n####  1、id自增主键为 null 就好了\n\n    insert into ws_user(user_id,username,password) values(null,\"zhangsan\",\'123456\');\n\n####  2、时间类型（now()，当前时间）\n\n    insert into ws_order(order_id,start_time) values(999,now());\n\n#### 3、指定时间\n\n    insert into ws_order(order_id,start_time) values(1001,\'1996-3-2 16:00:00\');\n    insert into ws_order(order_id,start_time) values(1001,\'1996/3/2 16:00:00\');\n\n#### 4、插入二进制文件，指定文件路径即可（这里表里的字段刚添加的，原没有该字段）\n\n    insert into ws_file(file_id,fileMp3) values(1001,\'C:\\Users\\Administrator\\Music\\虾米音乐\\黄嘉雯-情一动心就痛.mp3\');\n\n####  2、修改数据\n\n    ## 全部修改语法：UPDATE 表名 SET 列名1=值1，列名2=值2，....(且勿随意使用)\n    ## 指定修改语法：UPDATE 表名 SET 列名1=值1，列名2=值2，....WHERE 修改条件；\n    \n    update ws_user set username = \"李四\",password=\"123456\" where user_id = 10;\n####  3、删除数据\n \n    # 语法 : DELETE FROM 表名	WHERE	删除条件;\n    \n    delete from ws_user where user_id = 10;\n\n\n事务，我们打开另外一个窗口，发现上面的操作并没有生效\n数据库变更会发生锁的情况（此处应用可以解决项目多线程并发带来的数据安全问题）\n当两个数据库的连接同时来修改同一数据时，一定会有一连接先修改，另一个连接就会等待直到第一个连接修改完毕再修改\n\n####  如需提交：\n\n    commit;\n\n#### 如需回滚：\n\n    rollback;\n\n## 三、表的操作、创建/修改/删除\n\n#### 1、创建表\n\n    # 语法：ALTER TABLE 表名称 (列名1 类型 [DEFAULT 默认值]，列名1 类型 [DEFAULT 默认值]...)\n    # 如果你不想字段为 NULL 可以设置字段的属性为 NOT NULL， 在操作数据库时如果输入该字段的数据为NULL ，就会报错。\n    # AUTO_INCREMENT 定义列为自增的属性，一般用于主键，数值会自动加1。\n    # PRIMARY KEY关键字用于定义列为主键。 您可以使用多列来定义主键，列间以逗号分隔。\n    # ENGINE 设置存储引擎，CHARSET 设置编码。\n    \n    CREATE TABLE runoob_tbl(\n       runoob_id INT NOT NULL AUTO_INCREMENT, # NOT NULL：不为空，AUTO_INCREMENT：自增\n       runoob_title VARCHAR(100) NOT NULL,\n       runoob_author VARCHAR(40) NOT NULL,\n       submission_date DATE,\n       PRIMARY KEY ( runoob_id )             # PRIMARY KEY ：定义主键列\n     )ENGINE=InnoDB DEFAULT CHARSET=utf8;    # ENGINE ：设置存储引擎，CHARSET：设置编码。\n\n \n \n####  2、修改表\n \n\n    alter table runoob_tbl rename runoob_tb2;              # 修改表名\n    alter table runoob_tb2 add column name varchar(10);    # 添加表列\n    alter table runoob_tb2 modify name char(20)            # 修改表列类型\n    alter table runoob_tb2 change name username char(40)   # 修改表列类型+表列名\n    alter table runoob_tb2 drop  column name;              # 删除表列\n    alter table runoob_tb2 change column username username1 varchar(30)  #修改表列名\n\n####  3、删除表\n\n    # 语法：DROP TABLE 表名\n    DROP TABLE runoob_tb2;\n\n####  4、清空表数据（直接清空，无法回滚）\n    truncate table runoob_tb2;\n\n\n####  5、表的约束\n\n    # 主键约束：PRIMARY KEY ，主键是唯一的，不可重复\n    # 非空约束：NOT NULL ，表示该字段不能为空\n    # 唯一约束：UNIQUE，表示该字段字段值不能重复\n    # 检查约束：CHECK(GENDER IN(1,2))，表示该字段只能填写 1或2\n    # 外键约束：constraint \n    \n    \n    CREATE TABLE runoob_tbl(\n       runoob_id INT NOT NULL AUTO_INCREMENT, \n       runoob_title VARCHAR(100) NOT NULL UNIQUE,\n       runoob_author VARCHAR(40) NOT NULL,\n    	 runoob_type INT CHECK(GENDER IN(1,2)),\n       submission_date DATE,\n       PRIMARY KEY ( runoob_id )\n    	 # constraint 外键_pk primary key(order_id)\n     )ENGINE=InnoDB DEFAULT CHARSET=utf8;    \n\n## 四、序列     \n oracle使用  mysql不需要\n\n#### 1、创建序列\n\n    CREATE SEQUENCE seqpersonid;\n\n####   2、NextVal :取得序列的下一个内容\n\n    select seqpersonid.nextval from dual;#####   3、CurrVal :取得序列的当前内容\n\n    select seqpersonid.currval from dual;\n\n####  3、自增sql，\noracle自增列使用 seqpersonid.nextval，mysql 输入null 就好了\n\n    insert into ws_user(user_id,username,password) values(seqpersonid.nextval,\"zhangsan\",\'123456\');\n\n\n## 五、索引\n什么情况下所有索引？\n1、数据量大，查询慢的表\n2、经常查询的字段，where条件中经常使用\n优点：\n1、索引的作用：通过索引查找记录至少要比顺序扫描记录大致快100倍\n2、索引实现原理：生成二叉数索引文件，通过二叉数，B+树，折半查找\n3、数据量越大，索引查询提升的效率越高\n缺点：\n1、占用磁盘空间\n2、影响增删改ddl效率\n\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190915114425824.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70)\n如图：\n不使用索引：按左边图顺序查询，效率低\n使用索引后：根据查询数据的大小判断，比如查9，小于六的直接不考虑，直接查询大于6的，在往下找，如图查找2次就找到9了，顺序查询则需要查9次\n#### explain 关键字\nexplain 关键字，放在查询前面可以通过type查看查询是否使用了索引\n\n#### 1、普通索引 (不常用)\n    # 创建索引一语法：CREATE index 索引名  on 表名(列名((length)))\n    CREATE INDEX uname1 ON ws_user(username);\n    \n    # 创建索引二语法：CREATE index 表名 ADD INDEX 索引名(列名)\n    ALTER TABLE ws_user ADD INDEX uname(`username`);\n    \n#### 2、主键索引 \n\n```\n# 语法: alter table 表名 add primary key (列名);\nalter table articles drop primary key;\n```\n#### 3、唯一索引    \n 1、索引列不能有重复数据，必须唯一约束字段, 唯一约束可以为null\n 2、CHAR,VARCHAR类型,length可以小于字段的实际长度\n 3、BLOB，TEXT类型就必须指定长度\n```\n    # 语法：CREATE UNIQUE INDEX IndexName ON `TableName`(`字段名`(length)); \n    CREATE UNIQUE INDEX nam ON ws_user(`username`(10)); \n```\n####  4、全文索引 (不用，一般用搜索引擎，如es，solr)\n   \n\n说明:\n1.在mysql中fulltext 索引只针对 myisam生效\n2.mysql自己提供的fulltext针对英文生效->sphinx (coreseek) 技术处理中文\n3.使用方法是 match(字段名..) against(‘关键字’)\n4.全文索引：停止词,  因为在一个文本中创建索引是一个无穷大的数，因此，对一些常用词和字符，就不会创建这些词，称为停止词.比如（a，b，mysql，the）\n\n**创建全文索引**\n```\nCREATE TABLE articles (\n       id INT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,\n       title VARCHAR(200),\n       body TEXT,\n       FULLTEXT (title,body)    -- 添加全文索引\n     )engine=myisam charset utf8;\n```\n**查询每行和database的匹配度**\n```\n select match(title,body) against (\'database\') from articles;\n```\n**查询**\n```\n# 错误用法:\nselect * from articles where body like \'%mysql%\'; 错误用法 索引不会生效\n# 正确用法\nselect * from articles where match(title,body) against ( \'database\')\n```\n    \n####  5、组合索引\n1、不按索引最左列开始查询（多列索引）\n2、查询中某个列有范围查询，则其右边的所有列都无法使用查询（多列查询）\n3、不能跳过某个字段来进行查询,这样利用不到索引\n\n    #  语法： CREATE INDEX 索引名 On `表名`(`字段名`(字段长度),`password`(10),....);\n    CREATE INDEX up On `ws_user`(`username`(10),`password`(10));\n\n    \n####   6、查询表的索引\n\n```\ndesc  表名;   不能显示索引名称\nshow index from 表名\nshow keys from 表名\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/2019091511550648.png)\n \n\n####  7、删除表索引\n    DROP INDEX up ON `ws_user`;\n\n####  8、查询所用使用率\nshow status like ‘handler_read%’;\n\n大家可以注意：\nhandler_read_key:这个值越高越好，越高表示使用索引查询到的次数。\nhandler_read_rnd_next:这个值越高，说明查询低效。\n\n\n## 六、视图\n\n    创建视图:	CREATE VIEW 视图名(列1，列2...) AS SELECT (列1，列2...) FROM ...;\n    使用视图:	当成表使用就好\n    修改视图:	CREATE OR REPLACE VIEW 视图名 AS SELECT [...] FROM [...];\n    查看数据库已有视图:	SHOW TABLES [like...];（可以使用模糊查找）\n    查看视图详情:	DESC 视图名 或 SHOW FIELDS FROM 视图名\n    视图条件限制:	[WITH CHECK OPTION]\n\n\n#### 创建视图\n 创建视图1，vuser\n\n    create view vuser as select * from ws_user u；\n\n创建视图2，vuser，视图名存在会覆盖\n\n    create or replace view vuser AS select * from ws_user u;\n    \n\n 创建视图3，vuser ，视图名存在覆盖，且视图数据不可修改\n\n    create or replace view vuser AS select * from ws_user with check option;\n\n#### 查询/插入/修改 视图数据 \n\n    select * from vuser;\n\n-- 视图插入数据（同sql操作，需有视图数据可改权限）\n\n    insert into vuser(user_id,username,password) values(9880,\'柴油\',\'35\');\n\n-- 视图修改数据（同sql操作，需有视图数据可改权限）\n\n    update vuser set username = \'66666\',password = \'1232131\' where user_id= 9878;\n\n\n-- 查看视图详情\n\n    DESC vuser; \n    或\n    SHOW FIELDS FROM vuser;\n\n-- 查看已有视图（视图 = 临时表）\n\n    SHOW TABLES;\n\n\n## 七、临时表与复制表\n#### 临时表\n\n创建临时表，断开连接释放 (系统自动删除)\n\n    create TEMPORARY table Test(\n        regName varchar(20),\n    		mobilePhone char(11)\n    );\n\n 查询\n\n    select * from Test;\n\n 删除临时表\n\n    drop table Test;\n\n 查询所有表\n\n    show tables;\n\n\n#### 复制表 \n\n复制的是建表sql，直接在次复制字段内容\n\n    show create table ws_user;\n\n 复制结构，直接出来一个新的ws_user1 表（无数据）\n\n    create table ws_user1 like ws_user;\n\n 复制结构和数据（会丢掉一些特性，比如主键自增等）\n\n    create table ws_user2 as select * from ws_user;\n\n复制+临时表( 密码等于123456的全部复制到临时表数据中)\n\n    create TEMPORARY table leave_user as select * from ws_user u where u.password = \'123456\';\n\n查询临时表\n\n    select * from leave_user;\n\n\n## 八、if,case及判空表达式\n#### if / case\n\n if,第一个参数为判断表达式，true返回第二个参数，false返回第三个参数\n\n    select username,password,if(password=\'123456\',\'yes\',\'no\') from ws_user\n\n\n#case 判断,满足条件进入, 可以定义多个判断条件,断条件可以and连接  whene password = \'123456\' and____   then \'yes\' ，\n\n    select username,password,case when password = \'123456\' then \'yes\' else \'no\' end from ws_user\n\n案例一\n\n    select case 2\n    when 1 then \'one\'\n    when 2 then \'two\'\n    else \'more\'\n    end;\n\n案例二\n\n    select case \n    when 1 > 2 then \'false\' \n    when 1 < 2 then \'true\'\n    else \'error\'\n    end;\n\n#### 判空\n\n-- 获取空字段数据\n\n    select * from ws_user where password is null;\n\n-- 判空，空返回第二个值，不为空返回第一个参数的值\n\n    select password,ifnull(password,\"空值\") from ws_user  where password is null;\n\n\n## 九、储存函数\n说明：储存函数调用为  select + 函数名\n\n\n### 基础部分--系统函数/删除函数\n此为修改分饰符 ；为 //，防止函数中的sql 出现  ;  结束报错\n\n    delimiter ;\n    \n\n \n#### 系统函数 avg，concat等\n\n       # 平均值\n        -- select avg(leaveAmount) FROM member;\n        -- select CONCAT(\'hello\',\'-\',\'lemon\')\n        # 字符串拼接\n        select CONCAT(username,\'-\',password) from ws_user;\n\n\n#### 删除函数，return Hello World!\n\n    DROP FUNCTION IF EXISTS f_say_hello;\n\n\n#### 如果创建函数1418错误运行以下sql\n\n    show variables like \'log_bin_trust_function_creators\';\n    set global log_bin_trust_function_creators=1;\n    show variables like \'log_bin_trust_function_creators\';\n\n### 自定义函数\n#### 自定义函数语法/ 调用方法\n\n    delimiter //                                # 把结束分号改为 //\n    DROP FUNCTION IF EXISTS f_is_tuhao;         # 删除原有函数\n    CREATE FUNCTION f_is_tuhao(memberId INT)    # 创建函数 函数名(传入值 值数据类型)\n    RETURNS VARCHAR(20)                         # 返回值类型\n    BEGIN                                       # 程序体开始\n    \n    	 RETURN _msg;                           # 返回值\n    END//                                       # 程序体结束\n    delimiter;                                  # 把结束还原为 ;\n\n调用方法\n\n    select 函数名 \n\n#### 创建自定义函数，return Hello World!\n\n该函数返回 Hello World!\n\n    delimiter //\n    CREATE FUNCTION f_say_hello()    # 函数名\n    RETURNS VARCHAR(20)              # 返回的数据类型\n    BEGIN \n    	-- 多条sql。。。。             # 函数体，也就是sql\n    	RETURN \'Hello World!\';         # return 返回值\n    END\n    //\n    delimiter ;\n    \n    \n    -- 调用函数\n    select f_say_hello();\n\n\n\n#### 创建自定义带参数的函数，return 传入的值\n\n    delimiter //\n    DROP FUNCTION IF EXISTS f_say_a_word;  # 删除\n    CREATE FUNCTION f_say_a_word(word VARCHAR(20))\n    RETURNS VARCHAR(20)\n    BEGIN \n    	RETURN word;\n    END//\n    delimiter ;\n    \n    \n    -- 调用函数\n    select f_say_a_word(\'Hello World!!!\');\n\n\n#### 创建自定义带参数的函数，return a+b（1+2）\n\n    delimiter //\n    DROP FUNCTION IF EXISTS f_add_num;\n    CREATE FUNCTION f_add_num(a INT,b INT)\n    RETURNS INT\n    BEGIN \n    	RETURN a+b;\n    END\n    //\n    delimiter ;\n\n\n    -- 调用函数\n    select f_add_num(1,2);\n\n\n\n#### 创建自定义函数，return 平均消费（avg）\n\n    -- 得到某个会员的平均的消费额\n    delimiter //\n    # 删除存在\n    DROP FUNCTION IF EXISTS f_get_avg_amount;\n    # 创建\n    CREATE FUNCTION f_get_avg_amount(userId INT)\n    RETURNS DECIMAL(18,2)\n    BEGIN \n    	-- 定义一个变量：保存该用户的消费总额\n    	DECLARE _sumAmount DECIMAL(18,2) DEFAULT 0.00;\n    	-- 定义一个变量：保存该用户的消费次数\n    	DECLARE _investTimes INT DEFAULT 0;\n    \n    	-- 为变量赋值\n    -- 	SET _sumAmount=100.00;\n    \n    	-- 查询该用户的消费总额 （自定义查询sql） INTO _sumAmount 为赋值\n    	SELECT SUM(amount)   FROM invest WHERE memberId=userId;\n    	\n    	-- 查询该用户的消费次数 （自定义查询sql）\n    	SELECT COUNT(1) INTO _investTimes FROM invest WHERE memberId=userId;\n    \n    	-- IFNULL 为空判断，出现除0为空\n    	RETURN IFNULL(_sumAmount/_investTimes,0.00);\n    END//\n    delimiter ;\n\n### 函数判断\n#### IF 分支判断函数\n-- 传入一个memberId，返回会员是否是土豪（>40000）、无产(<10000)、中产(>=10000 and <40000)\n\n    delimiter //\n    DROP FUNCTION IF EXISTS f_is_tuhao;         # 删除原有函数\n    CREATE FUNCTION f_is_tuhao(memberId INT)    # 创建函数 函数名(传入值 值数据类型)\n    RETURNS VARCHAR(20)                         # 返回值类型\n    BEGIN                                       # 程序体开始\n    	\n    	DECLARE _msg VARCHAR(20);                 # 定义msg保存输出信息\n    	DECLARE _amount DECIMAL(18,2);	          # 定义一个局部变量保存查询出来的该会员的余额\n    	\n    	# 查询余额并赋值给变量 INTO （sql 数据库查询）\n    	SELECT leaveAmount INTO  _amount FROM member WHERE id=memberId;   _amount\n    \n    	if _amount<10000 THEN                    # if分支判断变量值的大小\n    		  set _msg = \'我是无产阶级\';\n    	elseif _amount>=10000 AND _amount<40000 THEN\n    		  set _msg = \'我是中产阶级\';\n    	else\n    		  set _msg = \'我是土豪\';\n    	end if;                                  # if分支判断结束标识\n    \n    	RETURN _msg;\n    END//                                       # 程序体结束标识\n    delimiter;\n\n\n\n#### CASE 判断列子1 (匹配)\n-- 创建一个函数，传入一个memberId，返回用户的类型（1：普通  2：内部  其他）\n\n    delimiter //\n    DROP FUNCTION IF EXISTS f_get_member_type_msg;        # 删除原有函数\n    CREATE FUNCTION f_get_member_type_msg(memberId INT)   # 创建函数 函数名(传入值 值数据类型)\n    RETURNS VARCHAR(20)                                   # 返回值类型\n    BEGIN                                                 #  程序体开始\n    	DECLARE _msg VARCHAR(20);                           # 定义retuen 变量\n    	DECLARE _type TINYINT;                              # 定义类型变量\n    \n    	SELECT type INTO _type FROM member WHERE id=memberId; # 查询sql并把结果赋值变量（值为 1或2或其他）\n    \n    	CASE _type                  	    #  判断开始\n    	WHEN 1 THEN SET _msg=\'普通用户\';     #  type = 1 执行 set\n    	WHEN 2 THEN SET _msg=\'内部用户\';     #  type = 2 执行 set\n    	ELSE SET _msg=\'其他用户\';            #  type 不等于1和2 执行 set\n    	END CASE; -- !!!!!                   #  判断结束\n    \n    	RETURN _msg;                         #  返回值\n    END//                                  #  程序体结束\n    delimiter;\n\n\n\n#### CASE 判断列子2 （ >=  <=） \n-- 传入一个memberId，返回会员是否是土豪（>=40000）、无产(<10000)、中产(>=10000 and <40000)\n\n    delimiter //\n    DROP FUNCTION IF EXISTS f_is_tuhao_case;\n    CREATE FUNCTION f_is_tuhao_case(memberId INT)\n    RETURNS VARCHAR(20)\n    BEGIN\n    	DECLARE _msg VARCHAR(20);	      -- 定义_msg保存输出信息\n    	DECLARE _amount DECIMAL(18,2);	  -- 定义一个局部变量保存查询出来的该会员的余额\n    	SELECT leaveAmount INTO  _amount FROM member WHERE id=memberId;  -- 查询出来的该会员的余额,保存到局部变量_amount中\n    	\n    	 -- case的条件判断、分支控制\n    	CASE	\n    	WHEN _amount >= 40000 THEN SET _msg=\'我是土豪\';\n    	WHEN _amount >= 10000 AND _amount<40000 THEN SET _msg=\'我是中产\';\n    	ELSE SET _msg = \'我是无产阶级\';\n    	END CASE;\n    \n    	RETURN _msg;\n    END//\n    delimiter;\n### 函数循环\n####  LOOP循环 ，需手动加入结束循环标识\n-- 向表中循环插入一百条数据\n\n    delimiter //\n    DROP FUNCTION IF EXISTS f_is_tuhao;         # 删除原有函数\n    CREATE FUNCTION f_is_tuhao(memberId INT)    # 创建函数 函数名(传入值 值数据类型)\n    RETURNS VARCHAR(20)                         # 返回值类型\n    BEGIN                                       # 程序体开始\n\n	DECLARE _num INT;                         # 创建变量\n	SET _num=1;                               # 初始化变量\n	insert_loop:LOOP                          # 循环开始 （定义循环名：LOOP）\n	\n		## 循环体\n		INSERT INTO tb_lemon_grade(s_name,score,c_name) VALUES(\'Tom\',80,\'30期\'); \n		SET _num=_num+1;                       # 迭代语句，每循环一次_num +1,\n		\n		IF _num%2=0                            # 判断逻辑-偶数执行下两行代码\n			THEN ITERATE insert_loop;          # 跳出本次循环\n		END IF;                                # 判断结束标识\n		\n		IF _num>100                            # 判断逻辑（是否执行if内代码跳出循环）\n			THEN LEAVE insert_loop;            # 跳出整个循环\n		END IF;                                # 判断结束标识\n		\n	 END LOOP insert_loop;                   # 循环结束标识\n	 RETURN \'1\';                             # 返回值\n    END//                                    # 程序体结束标识\n    delimiter;\n\n\n\n####  REPEAT循环 ，自带跳出循环\n\n    -- 向表中循环插入一百条数据\n    \n    delimiter //\n    DROP FUNCTION IF EXISTS f_loop_insert_3;   # 删除原有函数\n    CREATE FUNCTION f_loop_insert_3()          # 创建函数 函数名(传入值 值数据\n    RETURNS VARCHAR(20)                        # 返回值类型\n    BEGIN                                      # 程序体开始\n    \n    	DECLARE _num INT;                        # 创建变量                              \n    	SET _num=0;                              # 初始化变量\n    		\n    	insert_repeat:REPEAT                     # 循环开始 （定义循环名：REPEAT）\n    		SET _num=_num+1;                     # 迭代语句，每循环一次_num +1,\n    			\n    		## 循环体\n    		INSERT INTO tb_lemon_grade(s_name,score,c_name) VALUES(CONCAT(\'Tom_\',_num),80,CONCAT(_num,\'期\'));\n    	UNTIL _num=100 END REPEAT insert_repeat;    #  _num=100 跳出整个循环\n    \n    	RETURN \"1\";                              # 返回值\n    END\n    //\n    delimiter;\n\n\n####  WHILE 循环\n-- 向表中循环插入一百条数据\n\n    delimiter //\n    DROP FUNCTION IF EXISTS f_loop_insert_4; # 删除原有函\n    CREATE FUNCTION f_loop_insert_4()        # 创建函数 函\n    RETURNS VARCHAR(20)                      # 返回值类型\n    BEGIN                                    # 程序体开始\n    \n    	DECLARE _num INT;                      # 创建变量  \n    	SET _num=1;                            # 初始化变量 \n    	insert_while:WHILE _num<=100 DO        # 循环开始 （定义循环名：WHILE 循环条件） \n    		## 循环体\n    		INSERT INTO tb_lemon_grade(s_name,score,c_name) VALUES(CONCAT(\'Tom_\',_num),80,CONCAT(_num,\'期\'));\n    		\n    		SET _num=_num+1;                     # 迭代语句，每循环一次_num +1,\n    	    END WHILE insert_while;              # 满足while条件跳出循环\n    \n    	RETURN \"1\";                              # 返回值\n    END//\n    delimiter;\n\n## 十、储存过程\n\n注意：\n1、储存过程中可以调用储存函数，\n          2、储存函数中不可以调用储存过程\n3、储存过程可以传值，储存函数不可以\n\n #调用储存过程语法： Call 储存过程名\n \n \n -- 创建存储过程  \n 语法：CREATE PROCEDURE  过程名([[IN|OUT|INOUT] 参数名 数据类型[,[IN|OUT|INOUT] 参数名 数据类型…]]) [特性 ...] 过程体\n 如：\n \n\n     DELIMITER //\n        CREATE PROCEDURE Test(OUT s int)\n        BEGIN\n          SELECT COUNT(*) FROM ws_user;\n        END\n     // \n    DELIMITER ;\n\n\nDELIMITER ;”的意为把分隔符还原\nIN:参数的值必须在调用存储过程时指定，在存储过程中修改该参数的值不能被返回，为默认值 OUT:该值可在存储过程内部被改变，并可返回\nINOUT:调用时指定，并且可被改变和返回\nBEGIN与END 中间表示的是过程体\n\n\n### in 列子（只能传值进去，不返回值） \n\n    DELIMITER //\n      CREATE PROCEDURE in_param(out p_in int)\n        BEGIN\n         select p_in;\n         set p_in=2;\n         select p_in;\n        END;\n        //\n    DELIMITER ;\n    \n    set @p_in=1;            # 设置值\n    Call in_param(@p_in);   # 调用储存过程，in过程值可更改数据\n    select @p_in;           # 查询结果，还是1\n\n\n### out 列子\n\n    #存储过程OUT参数\n    DELIMITER //\n      CREATE PROCEDURE out_param(OUT p_out int)\n        BEGIN\n          SELECT p_out;\n          SET p_out=2;\n          SELECT p_out;\n        END;\n        //\n    DELIMITER ;\n    \n    #调用\n    SET @p_out=1;           # 设置值\n    CALL out_param(@p_out); # 调用储存过程，out 改变的值返回（原数据置空）\n    SELECT @p_out;          # 查询结果，变成2了\n\n### inout 列子\n\n    #存储过程INOUT参数\n    DELIMITER //\n      CREATE PROCEDURE inout_param(INOUT p_inout int)\n        BEGIN\n          SELECT p_inout;\n          SET p_inout=2;\n          SELECT p_inout;\n        END;\n        //\n    DELIMITER ;\n    #调用\n    SET @p_inout=1;              # 设置值\n    CALL inout_param(@p_inout) ; # 调用储存过程，inout 改变的值返回\n    SELECT @p_inout;             # 查询结果，变成2了\n\n### in out inout 的比较 =============================\n\n    delimiter //\n    DROP PROCEDURE if EXISTS proc_in_out_inout;\n    CREATE PROCEDURE proc_in_out_inout(\n    	IN in_num INT UNSIGNED,\n    	OUT out_num INT UNSIGNED,\n    	INOUT inout_num INT UNSIGNED\n    )\n    BEGIN\n    	\n    	SET in_num=100;                  # 值该变了不会返回\n    	SET out_num=out_num+1;           # 返回null，赋值具体的值可返回\n    	SET inout_num=inout_num+in_num;  # 返回改变后的值\n    \n    END\n    //\n    delimiter ;\n\n\n    ## 定义及查询\n    set @a =1;\n    set @b =10;\n    set @c =100;\n    CALL proc_in_out_inout(@a,@b,@c);\n    select @a,@b,@c;  \n\n#### 变量 \n\n    set @name = \'wangsong\';  # 定义变量\n    select @name;            # 查询变量\n    \n    set @num = 1+2+3;\n    select @num; \n\n\n\n### 在存储过程中使用用户变量 CONCAT 字符串拼接方法\n\n    CREATE PROCEDURE GreetWorld() SELECT CONCAT(@greeting,\' World\');\n    SET @greeting=\'Hello\';\n    \n    \n    CALL GreetWorld();\n\n#### 在存储过程间传递全局范围的用户变量\n\n    CREATE PROCEDURE p1() SET @last_proc=\'p1\';\n    CREATE PROCEDURE p2() SELECT CONCAT(\'Last procedure was \',@last_proc);\n    CALL p1();\n    CALL p2();\n\n\n\n#### 查询存在的所有存储过程 \n\n    #SELECT name FROM mysql.proc WHERE db=\'jpa\';\n    SELECT routine_name FROM information_schema.routines WHERE routine_schema=\'jpa\';\n    SHOW PROCEDURE STATUS WHERE db=\'jpa\';\n\n\n#### 查看存储过程详细信息\n\n    SHOW CREATE PROCEDURE jpa.p2;\n\n\n### 修改储存过程 \n\n    ALTER {PROCEDURE | FUNCTION} sp_name [characteristic ...]\n    characteristic:\n    { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }\n    | SQL SECURITY { DEFINER | INVOKER }\n    | COMMENT \'string\'\n\nsp_name 参数表示存储过程或函数的名称；\ncharacteristic 参数指定存储函数的特性。\nCONTAINS SQL 表示子程序包含SQL语句，但不包含读或写数据的语句；\nNO SQL 表示子程序中不包含SQL语句；\nREADS SQL DATA 表示子程序中包含读数据的语句；\nMODIFIES SQL DATA 表示子程序中包含写数据的语句。\nSQL SECURITY { DEFINER | INVOKER }指明谁有权限来执行，DEFINER表示只有定义者自己才能够执行；INVOKER表示调用者可以执行。\nCOMMENT \'string\'是注释信息。\n\n\n#### 将读写权限改为MODIFIES SQL DATA，并指明调用者可以执行。\n\n    ALTER  PROCEDURE  num_from_employee\n      MODIFIES SQL DATA\n      SQL SECURITY INVOKER ;\n\n#### 将读写权限改为READS SQL DATA，并加上注释信息\'FIND NAME\'。\n\n    ALTER  PROCEDURE  name_from_employee\n      READS SQL DATA\n      COMMENT \'FIND NAME\' ;\n	\n\n\n#### 删除储存过程 =================\n\n    DROP PROCEDURE [过程1[,过程2…]]\n\n\n\n#### 变量作用域\n\n内部变量在其作用域范围内享有更高的优先权，当执行到end时，内部变量消失，不再可见了，在存储\n过程外再也找不到这个内部变量，但是可以通过out参数或者将其值指派给会话变量来保存其值。\n\n    DELIMITER //\n      CREATE PROCEDURE proc()\n        BEGIN         # 储存体\n          DECLARE x1 VARCHAR(5) DEFAULT \'outer\';\n            BEGIN     # 内储存体\n              DECLARE x1 VARCHAR(5) DEFAULT \'inner\';\n              SELECT x1;\n            END;\n          SELECT x1;\n        END;\n        //\n    DELIMITER ;\n    \n    \n    #调用\n    CALL proc();\n\n\n### 条件语句 IF-THEN-ELSE\n\n\n    #条件语句IF-THEN-ELSE\n    DROP PROCEDURE IF EXISTS proc3;\n    \n    DELIMITER //\n    CREATE PROCEDURE proc3(IN parameter int)   # 创建储存过程\n      BEGIN                                    # 储存过程开始\n      \n        DECLARE var int;                       # 定义变量\n        SET var=parameter+1;                   # 定义变量\n        \n        IF var=0 THEN                          # var=0  执行\n          INSERT INTO t VALUES (17);\n        END IF ;\n        \n        IF parameter=0 THEN                    # parameter=0 执行\n          UPDATE t SET s1=s1+1;\n        ELSE                                   # parameter 不等于 0 执行\n          UPDATE t SET s1=s1+2;\n        END IF ;\n        \n      END ;\n      //\n    DELIMITER ;\n\n\n#### 储存过程定义异常\n\n    定义异常\n        delimiter //\n        DROP PROCEDURE IF EXISTS p_condition_handler;\n        CREATE PROCEDURE p_condition_handler()\n        BEGIN\n        #一：定义异常\n        -- 1:sqlstate_value\n        -- 	DECLARE does_not_exist CONDITION FOR SQLSTATE \'42S02\';\n        \n        -- 	2：mysql_error_code\n           DECLARE does_not_exist CONDITION FOR 1146;\n    	 \n    	 SELECT * FROM hello;\n    	 # 查询异常不在执行下列sql\n    	 INSERT INTO member(id) VALUES(10090);\n    END//\n    delimiter;\n    \n    \n#### 储存过程处理异常\n    \n        delimiter //\n        DROP PROCEDURE IF EXISTS p_condition_handler;\n        CREATE PROCEDURE p_condition_handler()\n        BEGIN\n    \n        # 遇到SQLSTATE \'42S02\' 错误程序继续下走，并给变量flag赋值为1\n        -- 	DECLARE CONTINUE HANDLER FOR SQLSTATE \'42S02\' SET @=1;\n    \n      \n        # 遇到 1146 错误程序继续下走，并给变量flag赋值为1\n        -- 	DECLARE CONTINUE HANDLER FOR 1146 SET @flag=1;\n    \n        # 遇到自定义异常 错误程序继续下走，并给变量flag赋值为1\n        -- 	DECLARE CONTINUE HANDLER FOR does_not_exist SET @flag=1;\n    	\n        DECLARE EXIT HANDLER FOR SQLEXCEPTION SET @flag=1;\n    \n    	SET @flag=2;\n    	# 查询出了异常，还希望执行 insert 语句，可定义相应的异常\n    	SELECT * FROM hello;\n    	INSERT INTO member(id) VALUES(10090);\n    END//\n    delimiter;\n    \n\n    -- ERROR 1146 (42S02): Table \'lemon.hello\' doesn\'t exist\n    -- 1：已经发生了错误\n    -- 2: 1146：错误码\n    -- 3：42S02：sql状态值\n\n\n## 十一、触发器 (=监听器)\n\n\n\n当向 ws_user表中插入一行记录时，触发储存过程直接向 ws_user2 表插入一条记录\n\n    delimiter //\n    DROP TRIGGER IF EXISTS t_insert_invest;         # 删除储存过程\n    CREATE TRIGGER t_insert_invest AFTER INSERT     # 创建储存过程 + INSERT触发（插入数据触发）\n    ON ws_user FOR EACH ROW                         # on 表名（触发器关联的表）\n    BEGIN\n      ## 过程体\n      INSERT INTO ws_user2(password,username) VALUES(\'123456\',\'wangwu\');\n    END//\n    delimiter ;\n    \n    \n    # 当向 ws_user表中插入一行记录时，触发 t_insert_invest 储存过程\n    INSERT INTO ws_user(password,username) VALUES(\'123456\',\'wangwu3\')\n\n\n       # 查询\n        select count(*) from ws_user;\n        select count(*) from ws_user2;\n        select * from ws_user;\n        select * from ws_user2;\n\n\n\n-- 创建一个触发器，再删除一个投资用户时，删除其相关的信息：投资、回款计划信息、用户信息\n\nOLD.id 的说明\nold ：删除触发时之前的老数据（表对象）\nnew ：新增触发时添加的新数据（表对象）\n\n\n    delimiter //\n    CREATE TRIGGER t_delete_member_info BEFORE DELETE \n    ON member FOR EACH ROW\n    BEGIN\n    	-- 删除所有的回款计划信息\n    	DELETE FROM repayment WHERE investId in(select id from invest where memberId=OLD.id);\n    \n    	-- 删除投资记录\n    	delete from invest where memberId = OLD.id;\n    END//\n    delimiter;\n\n\n\n\n\n## 十二、事件（定时任务）\n\n1、使用事件需开启定时器\n2、定义事件，使用sql 还是储存过程看业务需求\n下方有的使用过程，有的使用sql\n\n    SHOW VARIABLES LIKE \'event_scheduler\';  # 查看是否开启定时器（事件）\n    SET GLOBAL event_scheduler = 1;         # 开启定时器 0：off 1：on\n    \n    \n    -- 开启事件，event_test为事件名\n    ALTER EVENT event_insert_member ON \n    COMPLETION PRESERVE ENABLE; \n    \n    -- 关闭事件，event_test为事件名\n    ALTER EVENT event_insert_member ON \n    COMPLETION PRESERVE DISABLE;\n\n\n定义事件\n###  每隔一秒自动调用 e_test()存储过程\n\n    CREATE EVENT IF NOT EXISTS event_test\n    ON SCHEDULE EVERY 1 SECOND\n    ON COMPLETION PRESERVE \n    DO CALL e_test();         \n\n###  每隔一分钟，执行一次语句\n\n    delimiter //\n    CREATE EVENT event_insert_member    # 定义：CREATE EVENT 函数名\n    ON SCHEDULE EVERY 1 MINUTE          # ON SCHEDULE 定义时间\n    ON COMPLETION PRESERVE ENABLE DO\n    BEGIN\n        # 执行语句\n    	  INSERT INTO ws_user2(password,username) VALUES(\'123456\',\'wangwu\');\n    END//\n    delimiter ;\n\n\n\n\n\n\n###  每天凌晨一点执行 INSERT sql语句\n\n    delimiter //\n    CREATE EVENT IF NOT EXISTS temp_event   \n    ON SCHEDULE EVERY 1 DAY STARTS DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 DAY), INTERVAL 1 HOUR)   \n    ON COMPLETION PRESERVE ENABLE   \n    DO INSERT INTO ws_user2(password,username) VALUES(\'123456\',\'wangwu\');\n    //\n    delimiter ;\n\n###  每天0点05分从数据表中清除字段（yhendtime）小于当前时间戳的记录：\n\n    delimiter //\n    CREATE EVENT dele_from_thinkyh\n    ON SCHEDULE EVERY 1 DAY STARTS \'2016-12-30 00:05:00\' \n    ON COMPLETION PRESERVE ENABLE COMMENT \'每天清除数据表think_youhui中的过期的记录\' \n    DO DELETE FROM think_youhui WHERE think_youhui.yhendtime<CURRENT_DATE\n    //\n    delimiter ;\n\n###  从现在开始每隔九天定时执行 \n\n    delimiter //\n    CREATE EVENT EVENT1 \n    ON SCHEDULE EVERY 9 DAY STARTS NOW() \n    ON COMPLETION PRESERVE ENABLE \n    DO \n    BEGIN \n       INSERT INTO ws_user2(password,username) VALUES(\'123456\',\'wangwu\');\n    END \n    //\n    delimiter ;\n\n\n###  每个月的一号凌晨1 点执行 \n\n    delimiter //\n    CREATE EVENT EVENT2 \n    \n    ON SCHEDULE EVERY 1 MONTH STARTS DATE_ADD(DATE_ADD(DATE_SUB(CURDATE(),INTERVAL DAY(CURDATE())-1 DAY), INTERVAL 1 MONTH),INTERVAL 1 HOUR) \n    ON COMPLETION PRESERVE ENABLE \n    DO \n    BEGIN \n       INSERT INTO ws_user2(password,username) VALUES(\'123456\',\'wangwu\');\n    END \n    //\n    delimiter ;\n\n\n###  每个季度一号的凌晨2点执行 \n\n    delimiter //\n    CREATE EVENT TOTAL_SEASON_EVENT \n    ON SCHEDULE EVERY 1 QUARTER STARTS DATE_ADD(DATE_ADD(DATE( CONCAT(YEAR(CURDATE()),\'-\',ELT(QUARTER(CURDATE()),1,4,7,10),\'-\',1)),INTERVAL 1 QUARTER),INTERVAL 2 HOUR) \n    ON COMPLETION PRESERVE ENABLE \n    DO \n    BEGIN \n       # CALL SEASON_STAT(); 可以调用储存过程\n       INSERT INTO ws_user2(password,username) VALUES(\'123456\',\'wangwu\');\n    END \n    //\n    delimiter ;\n\n\n\n### 每年1月1号凌晨四点执行 \n\n    delimiter //\n    CREATE EVENT TOTAL_YEAR_EVENT \n    ON SCHEDULE EVERY 1 YEAR STARTS DATE_ADD(DATE(CONCAT(YEAR(CURDATE()) + 1,\'-\',1,\'-\',1)),INTERVAL 4 HOUR) \n    ON COMPLETION PRESERVE ENABLE \n    DO \n    BEGIN \n      # CALL YEAR_STAT(); 可以调用储存过程\n      INSERT INTO ws_user2(password,username) VALUES(\'123456\',\'wangwu\');\n    END\n    //\n    delimiter ;\n\n\n\n\n', 0, 275);
INSERT INTO `t_xj_admin_help` VALUES ('1318532059987181569', NULL, NULL, '2020-10-20 12:38:33', '2021-03-04 17:03:34', 0, 0, '', 5, 0, 'layui switch 开关', '### 1、展示switch 开关效果![在这里插入图片描述](https://img-blog.csdnimg.cn/20200621085135391.png)\n\n\n### 2、添加 switch 开关代码\n```\n{\n                                field: \'state\', width: 90, title: \'禁/启用\', templet: function (res) {\n                                    let menuId = res.id;\n                                    if (res.state == 0) {\n                                        return \"   <input type=\'checkbox\'  menuId = \'\" + menuId + \"\' lay-filter=\'state\' lay-skin=\'switch\' lay-text=\'ON|OFF\' checked>\"\n                                    } else if (res.state == 1) {\n                                        return \"   <input type=\'checkbox\'  menuId = \'\" + menuId + \"\'  lay-filter=\'state\' lay-skin=\'switch\' lay-text=\'ON|OFF\'>\"\n                                    }\n                                }\n                            },\n```\n![在这里插入图片描述](https://img-blog.csdnimg.cn/2020062108570637.png)\n\n### 3、监听方法\n请注意是否存在layui 的 form ，我是定义在外面的了的\n```\n    /**\n             * TODO 监听单选选中\n             * <p>\n             * switch(state) 对应 lay-filter=\'state\'\n             */\n            function xijiaEventSwitch() {\n                form.on(\'switch(state)\', function (data) {\n                    console.log(data.elem.attributes[\'menuId\'].nodeValue);\n                    console.log(data.elem.checked); // 开关是否开启，true或者false\n                    console.log(data.value);        // 开关value值，也可以通过data.elem.value得到\n\n                    // let menuId = data.elem.attributes[\'menuId\'].nodeValue;\n                    //console.log(data.elem);         // 得到checkbox原始DOM对象\n                    //console.log(data.othis);        // 得到美化后的DOM对象\n                });\n            }\n```\n### 4、打印参数\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20200621090128374.png)\n接下来就可以调用后台接口修改参数了\n\n***本文到此结束，如果觉得有用，劳烦各位点赞关注一下呗，将不定时持续更新更多的内容…，感谢大家的观看！***', 0, 146);
INSERT INTO `t_xj_admin_help` VALUES ('1318909627978547201', NULL, NULL, '2020-10-21 13:38:52', '2021-03-04 17:05:11', 0, 0, '', 0, 0, '更新日志', '## 版本：v-0.0.9\n\n------------------------------  2021-1 - 2021-2\n1、菜单第二路由管理页面增加该字段(前后端分离,前端专用字段,防止修改当前系统路由)\n2、v-0.0.8 目录结构变更 优化代码生成目录\n3、数据库数据每天自动备份: 定时任务-> MysqlDataBackupTask 类\n4、layui 页面目录结构优化\n5、查询请求日志增加成功失败颜色标识\n6、字典管理查询接口后端代码优化，增加类别，三级联动搜索\n7、增加小程序订阅消息\n8、目录结构变更后代码生成器调整 v-0.0.8 代码生成无法使用,引包路径错误\n----此处开始模快化\n9、项目模块化,拆分为 项目代码包和第三方包\n10、移除 mapstruct 工具\n11、模块化已大致完成\n12、微信mq(公众号)优化, 网页授权并增加code 获得微信用户信息接口\n13、阿里云sms代码优化,化繁为简, 修改static 为spring bend容器存放数据\n14、增加 xj-base-pay 支付模块,继承第三方支付和保存交易记录\n15、优化xj-api-wx-mp 模块,使用wxjava-sdk方法发送模版消息及网页授权\n16、优化xj-api-aliyun-sms 模块,短信我怎么签名yml配置默认\n17、优化xj-api-baidu 模块,增加通用百度api文字识别接口(银行卡/身份证/营业执照等识别)\n18、优化xj-fw-websocket 模块,增加服务端主动向客户端发送心跳,保证连接不自动断开\n19、增加xi-util-geodesy 模块, 提供 DistanceUtil 经纬度计算距离工具类\n20、优化xj-doc-swagger2 模块, 提供 yml配置测试\n21、优化字典管理逻辑代码\n22、 xj-base-core模块，枚举类 Enums 拆分成小枚举类，Admin-系统， Xj-系统增强， Base-通用， Pay-支付 四个枚举类,业务请新创建,同时字典提供搜索后生成\n23、 xj-base-admin-ui 增加消息通知查看(后台管理系统铃铛)\n24、修复文件上传特殊符号错误\n25、请求方式错误增加返回错误提示\n26、pay模块下单增加返回订单号,不用回来后主动set\n27、优化swagger,通过默认请求头token参数配置,val=token 接口直接放行，需要获取用户信息接口处返回无法解析token \n\n更多：其他细节优化\n\n## 版本：v-0.0.8\n\n------------------------------ 2020-1-14\n- 1、添加聊天室-新， 访问地址： ip：端口/lts   (2020/12/10)\n- 2、0.0.7 黑名单代码等部分代码未加入版本管理处理,已添加 v-0.0.8.sql(2020/12/11)\n- 3、优化modules下/others下部分文件目录结构   (2020/12/11)\n- 4、阿里云OSS 代码优化,移除zuul转发访问oss文件方式，使用拦截器进行重定向访问, 配置从 yml中移除，在OssConfig中配置(2020/12/11)\n- 5、优化兮家手册,每篇文档可使用独立url访问(2020/12/13)\n- 6、优化兮家手册,样式变动,文章添加标题, 单篇文章处理手机端打开兼容(2020/12/14)\n- 7、优化兮家手册，图片可点击放大,抽离为独立组件, 引入picEnlarge下的js+css， 在页面调用 addExpand()加载方法即可(2020/12/14)\n- 8、增加文件资源管理 及 页面展示列表(2020/12/17)\n- 9、增加自编写幂等框架，防止重复请求，详见文档：http://xijia.plus/help?id=1340631093145063426(2020/12/20)\n- 10、增加段子(功能系列,不属于架构)(2020/12/22)\n- 11、优化目录结构，系统基础功能 和 增强功能独立划分(2020/12/23)\n- 12、优化api文件结构，只区分系统功能 和 通用功能两个大模块(2020/12/23)\n- 13、增加七牛云 oss的整合，如何使用详见: https://blog.csdn.net/qq_41463655/article/details/111720952(2020/12/26)\n- 14、文件下载独立，从单独的文件管理中移除(阿里云oss，七牛云oss，系统服务器)(2020/12/26)\n- 15、优化部分工具类，LocalDateTimeUtil 部分方法名变动,BigDecimalUtil 增加除100，乘100方法，用于元/分 转换，更多自行查看(2020/12/22)\n- 请使用最新sql脚本\n- 16、添加微信支付(已添加下单,退款,其他待定)\n- 17、优化代码生成器，增加mysql --> tinyint字段映射为 Boolean, 优化必填字段 jsr303提示信息\n- 18、优化代码生成器, 增加jsr303 DecimalMax DecimalMin 小数验证\n- 19、优化jwt的token, 所有Deflater进行数据压缩，以此保证响应header不超过8kb限制(前后端分离出现的bug)\n- 20、优化幂等,增加定时删除过期幂等token\n- 21、添加菜单, 超管账号默认分配此菜单\n- 22、增加微信支付模块\n- 23、增加七牛oss模块\n- 24、修改目录结构, 模块主目录 models，下级分,业务目录/系统代码目录/第三方对接目录\n- 25、修改第三方对接配置\n- 26、优化阿里云oss, 上传接口增加图片压缩功能,避免大图片回显太慢\n- 27、增加日志清理定时任务, 请求数据太多, 指定保留天数\n- 28、菜单管理增加第二路由, 前后端分离前端使用第二路由字段\n- 29、时间工具类 增加 获取指定年的第一天和最后一天\n\n\n## 版本：v-0.0.7\n\n------------------------------ 2020-12-1\n- 1 修复字典枚举代码生成 页面展示 ```  <Integer>  ```丢失\n- 2 代码生成预览, 枚举字典代码生成, 兮家手册添加编辑修改弹出窗口为100% \n- 3 整合了更好看的接口文档 knife4j， 访问 --> ip:端口/doc.html\n- 4 修复兮家手册页展示代码快无法解析左右括号问题\n- 5、maven 的pom.xml 依赖版本号统一管理\n- 6、更新代码生成，可以随意连接任意数据库，在线直接添加数据库相关信息即可连接\n- 7、角色增加 code值, 用于角色查询，避免需要查询指定角色事没有查询值或使用角色名称和固定id查询的尴尬\n- 8、启动项目扫描出来的新接口,超级管理员-默认有新接口权限,基于第7个更新的角色code查询获取的角色信息\n- 9、优化代码生成的数据源维护\n- 10、添加nginx 配置文件 /nginx/nginx.html\n- 11、优化代码生成器, 页面可指定表前缀(生成过滤)，表的字段前缀(生成过滤)，通用字段(查询表字段默认不勾选生成该字段)\n- 12、日志优化,日志详情页添加展示\n- 13、登录授权验证移至aop\n- 14、BeanDtoVoUtil 优化，由 springboot的 BeanUtils 修改为 cglib的BeanCopier的实现，性能最优\n- 15、添加二维码生成工具类 CreatrQrCode.java，可生成中间带 头像的二维码图片\n- 16、优化BeanDtoVoUtil的 cglib 的BeanCopier,添加Copier缓存（2020-11-20）\n- 17、添加比cglib强的转换工具 mapstruct 可使用,可移除,习惯了BeanDtoVoUtil,性能cglib略佳一点,mapstruct功能强一点(2020-11-18)\n- 18、更新 Markdown 文档展示\n- 19、更新 菜单分配，资源分配位置到角色管理\n- 20、添加了markdown编辑器editor, 并自行扩展了1、图片粘贴上传(支持qq，wx截图） 2、本地拖拽（支持多文件）, js文件 imgCopyPull2.js（更新与 2020/11/24）\n- 21、兮家手册页面展示和代码优化，添加/编辑修改为打开新窗口操作（2020/11/26）\n- 22、完成黑名单的增删改查,缓存以及，黑名单，白名单ip权限控制（2020/11/28 01:30）\n- 23、添加java在线代码执行器\n\n## 版本: v-0.0.6\n------------------------------ 2020-11-1\n- 1 优化了菜单变更父级，不查询页面级数据\n- 2 添加了字典变更父级\n- 3 Banner 排序\n- 4 添加所有角色有所有权限的接口,防止权限错误时使用\n- 5 用户角色中间表, 角色菜单中间表, 角色权限中间表关闭逻辑删除，使用物理删除,避免数据库太多脏数据\n- 6 修复oss 文件上传pdf 时格式错误, 浏览器无法直接预览问题\n- 7 修复代码生成器遇见表名中间有 t_ 导致生成的类名缺失 t 字符问题\n- 8 顺丰接口优化，添加 Remark 备注字段，注意正式接口成功下单会推送信息到【顺丰速运】公众号，登录对应寄件人手机号的账号可接受上门取件信息\n- 9 优化jquery加载,改用min包，删除layui久版本相关代码\n- 10 整合了 spring-boot-admin （系统监控-页面管理平台），http://127.0.0.1}:9048/bootAdmin\n- 11 更新了兮家手册功能，后续将在手册里面添加操作/使用等信息文档\n- 12 Markdown 编辑器插件-功能扩展，1、图片直接赋值粘贴上传（不支持本地，支持qq，wx截图，网络图片） 2、本地文件拖拽上传（支持多文件拖拽）\n- 13 阿里云oss 上传文件优化，如果文件名存在空格,直接替换为空，防止部分地方无法解析，如： new URL() 请求, Markdown 文本解析图片\n- 14 阿里云oss 上传文件优化，如果文件名存在逗号,直接替换为空, 防止多图保存已逗号分隔，保存数据库出现错误\n- 15 更新兮家手册, 添加左菜单收缩，自动暂开第一项，自动加载并选中第一项第一条数据，带选中效果\n- 16 添加兮家手册路由( ip:端口/help ||  域名/help )直接访问\n- 17 修复分页数据显示不完整，只显示10条的缺陷\n- 18 代码生成增加mysql关键字[force] 处理\n- 19 弹出层优化 -可拉伸（右下） -可拉出窗口外（拉出内容在其他下面）-添加最大化最小化按钮\n- 20 接口管理页, 隐藏当前页的Id编辑接口/admin/adminAuthority/upd 接口, 防止无法使用, 目前权限是热更新, 一旦禁用改接口将导致禁用接口的方法无法编辑使用\n- 21 优化jwt的token生成,权限的拦截和 token刷新\n- 22 登录/授权 修改为aop 统一处理\n- 23 aop 中增加 log 操作记录到数据库, 请求来源，请求数据，ip，返回数据，成功失败等, 这也是登录/授权 修改到aop的主要原因之一\n- 24 aop 中异步执行日志记录, 通过线程池获取执行记录日志获取Future的，在业务代码执行完后,在 while (true) { if (future.isDone()) 判断记录日志操作是否完成在获取日志Id,在记录操作结果 \n- 25 登录/授权 修改为 JwtAuthFilter 处理, 过滤器性能损耗比aop低, 登录/授权移至Filter\n- 26 优化权限扫描/ 和权限数据展示\n\n\n## 版本: v-0.0.5\n------------------------------ 2020-10-1\n- 1	修复阿里云oss下载文件文件名带空格,特殊字符,中文后台异常bug 		\n- 2	添加阿里云短信对接模块		\n- 3	阿里云oss 文件访问改为zuul转发访问, 部署项目时可直接修改zuul转发地址,使访问oss资源为内网访问\n- 4	用户角色分配 ,角色URL分配，角色菜单分配优化为body传参			\n- 5	base/ 添加下载Controller,添加单文件下载 及 多文件下载(zip压缩)通用接口	\n- 6	添加 Excel 下载导出类 ExcelUtil, 需要导出的数据放入任意实体对象, List<Entity/VO>，直接传递对象到工具类方法即可直接导出，字段描叙,字段key,及List<>的每一条数据		\n- 7	添加 Token 类型,  用户端登录接口,已处理jwt的token生成/刷新,及swagger的默认账号（10000）	\n- 8	快递100 查询接口对接 ( 查询各大公司物流信息)		\n- 9	顺丰（丰桥）接口对接( 寄件)	\n- 10 websocket 代码优化, 更简洁（获取测试连接地址自动识别本地或线上）		\n- 11 微信公众号 openId 获取, 获取code请求拼接获取	\n- 12 添加微信公众号模板信息推送模块	\n- 13 增加全局配置表, 添加任意内容,（内容 json传, 字符串，字符串分隔内容, 图片） 前端通过code 获取配置		\n- 14 增加全局开关配置( 缓存开启/ 关闭， 第三方测试环境/正式环境一键切换）	\n- 15 增加 banner 功能	\n- 16 优化websocket , 增加被迫下线通知（同一账号Id 只能登陆一次, 后登录的挤掉先登录的用户）	\n- 17 代码生成页 自动获取表名称		\n- 18 修改为双账号登录( 账号+手机号) 		\n- 19 移除开发相关功能(非系统底层功能,有点多余)\n- 20 开启了逻辑删除,使用表已采用,可能会出现部分地方数据不正确问题,请注意\n- 21 layui2.4.5 升级到 layui2.5.6-当前最新版 (注意看项目中是否有存在不兼容的问题)\n\n\n## 版本: v-0.0.4\n\n--------------------------  2020-08-23\n- 1、添加登录接口 admin/login ，删除jwt 登录器（ auth/login-太繁琐，明明一个接口就能搞定，Security + jwt 要一大串)\n- 2、删除Security 登录相关配置\n- 3、使用 mapper数据操作sql 移动至xml (规范)\n- 4、权限控制修改  1、添加终端(区分管理端,用户端，pc/手机) + 2、添加权限状态（无需登录+授权，需登录，需授权））\n- 5、权限控制日志输出优化, 添加权限缓存，弃用SecurityConfig的内置权限功能，改为自写的缓存配置jwt个人权限信息来完成整个权限布局\n- 6、请求日志输出、优化，JWT生成Token优化\n- 7、统一返回枚举对象 RType 类枚举参数定义优化\n- 8、用户权限数据-热更新  --> 重新分配用户角色 + 重新分配角色权限都会,直接刷新已登录用户的Token中的权限数据  --> 使用 角色/权限/用户 关联数据的版本号来检测\n- 9、系统权限数据-热更新  --> 修改接口权限的 无需登录/ 需授权/ 需登录+授权 -->  使用权限列表数据版本号来检测 + jvm缓存机制来更新\n- 10、角色/权限 禁用数据实现热更新\n- 11、添加 Banner 功能\n- 12、处理页面上的分页选择后搜索条件丢失 (代码生成器以同步更新处理)\n- 13、修改添加编辑删除后面返回的Void变更为 Boolean, (代码生成器以同步更新处理)\n\n\n说明：\n- 1、用户禁用暂未实现热更新,必须重新登录后生效\n\n代优化功能\n- 1、代码生成器添加字段的 switch-开关\n- 2、代码生成器添加字段的 select-下拉选择\n- 3、代码生成器处理时间字段\n\n## 版本: v-0.0.3\n--------------------------  2020-08-09\n- 1、代码生成 DTO+VO 的参数在 swagger-ui 中指定顺序，按实体类顺序排序\n- 2、代码调整，删除无用代码，部分逻辑代码位置修改\n\n--------------------------  2020-08-14\n- 3、枚举字典前端缓存 js代码完成，app.js文件中, 可完全直接当枚举字典来使用\n- 4、任务支持选择多人，和选择多端（pc+app等）\n\n--------------------------  2020-08-16\n- 5、app.js 中的方法统一优化\n- 6、枚举字典优化，添加一键生成-后台枚举类+前端枚举字典key代码\n- 7、跨域配置,前端代码修正，前后端代码可抽离独立部署， 不过前端代码,依赖后端的tomcat服务器 和 通用路由跳转接口\n- 8、URL权限处理优化,启动项目自动更新接口信息, 页面分配时添加 GET/PUT/POST/DELETE 请求一键勾选功能\n- 9、增加 IdUtil 工具类, 可直接生成uuid + 雪花算法Id\n\n\n\n## 版本: v-0.0.2\n--------------------------  2020-08-05\n- 1、菜单树结递归查询代码优化\n- 2、接口权限接口查询代码优化 \n\n--------------------------  2020-08-08\n- 3、权限分配联动选择,选中父级自动选择子菜单\n- 4、字典添加禁用功能，禁用数据除了当前列表外，其他地方的页面查询都不查询出来禁用数据\n- 5、角色添加禁用功能， 1-禁用后的角色将没有对应的菜单权限 2-禁用后的角色将没有对应的url接口权限，重新登录生效\n- 6、开发任务功能优化, 点击第二页未携带第一页的查询条件\n- 7、字典管理删除优化,删除父级时同时删除所有子层级实际 优化查询，无极限 优化添加，code 不重复 优化应该，code 不重复\n- 8、所有类名优化,以数据库名称为准， 如: t_admin_user = AdminUser\n- 9、swagger 方法@ApiOperation 优化\n- 10、统一格式为： @ApiOperation(value = \"操作功能\",notes= \"详细描叙\")\n- 11、修复bug选择菜单，角色回显错误\n\n\n\n## 版本: v-0.0.1\n------------------------------ 2020-07-31\n- 1、所有模块重新划分， 登录修改为JWT ， 授权修改为 springSecurity 框架\n- 2、代码生成器根据重新划分的模块进行优化\n- 3、所有id字段修改为String\n- 4、增加MD编辑器\n- 5、增加websocket模块 + 前端->简易聊天室\n- 6、增加阿里云oss文件处理\n- 7、数据库增加所有表通用字段\n- 8、增加任务管理+bug管理（仿禅道）\n\n--------------------------------2020-08-03\n- 9、请求日志优化 请求log 日志处理\n- 10、接口名称统一 部分接口名称不统一 1、分页查询 --> findPage 2、查询使用 --> findList 3、id查询 --> findId 4、id编辑 --> upd 5、添加 --> insert 6 、id删除 --> del 7、多id删除 delByIds \n- 11、req 没有请求参数异常信息返回处理 优化请求错误返回信息\n- 12、完善代码生成器，DTO 类根据数据库是否为空，大小生成对应的传递参数限制\n- 13、分页查询排序按创建时间\n- 14、用户md5密码加密\n- 15、多环境可直接切换配置\n- 16、修复权限扫描异常，修复id 删除异常\n- 17、全局异常，请求错误优化返回，jsr303参数验证错误优化返回 2、通用返回类优化\n\n--------------------------------2020-08-04\n- 18、字典管理删除优化，删除父级时同时删除所有子层级实际 优化查询，无极限 优化添加，code 不重复 优化应该，code 不重复\n- 19、jwt TOKEN 自动刷新优化，token到期会自动失效，任意操作后自动刷新token\n- 20、点击第二页未携带第一页的查询条件优化\n\n--------------------------------2020-08-04\n- 21、logback 日志未在linux上生成日志文件优化\n- 22、启动信息优化,启动信息不在放入启动类中\n- 23、增加阿里云短信整合工具类 SmsUtil\n- 24、增加字典表增加 findCodeGroup Code分组查询方法（带版本号+缓存控制,及大的减轻了服务器的压力）,使用字典来管理所有的动态字段/状态字段\n- 25、字典优化，使用操作需手动刷新,层次错误处理\n\n\n## 版本: mybatis-plus分支  \n----------------------------- 2020/4/16 14:23\n - 1、框架替换,数据层已全部更换为 mybatis-plus\n - 2、通用IService 编写完成\n - 3、模块化jar修改为单项目，模块分包形式\n \n代处理：\n- 1、后台分页数据变动，页面数据暂无法显示，登录功能已正常，其他代处理\n\n\n## 版本: jpa分支\n----------------------------- 2020/4/15\n - 1、放弃 jpa， jpa分支已放弃更新，目前基础功能一切正常运行，代码生成器功能正常，带内部数据暂未处理\n - 2、计划下版本使用mybatis-plus 3.3.1\n\n\n## 创建项目  2019/10/29\n----------------------------- 2019/10/29\n \n创建项目，技术选型为：springboot2.2.0 + jpa + mysql8 +lombok+druid + layui + 模块化开发\n完成基础的功能\n - 用户\n - 角色\n - 菜单\n - 接口资源\n - 以及代码生成功能开发', 1, 618);
INSERT INTO `t_xj_admin_help` VALUES ('1320197388991746050', NULL, NULL, '2020-10-25 02:56:00', '2021-03-04 17:05:08', 0, 0, '', 0, 0, '快速上手', '下面介绍如何拉取代码并启动项目\n\n## 1、查看源码\n [点击前往 git 仓库 ](https://gitee.com/wslxm/spring-boot-plus2)\n\n## 2、选择分支拉取代码\n建议拉取 master 或 最新版(最新可以存在正在开发/优化中未处理的功能bug)\n\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201101121102024313-image.png)\n\n\n## 3、创建本地数据库\n找到对应版本的sql 脚本，注意查看 git 仓库描叙中当前 master 版本为那只分支，创建本地数据库导入sql\n\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201101121340593669-image.png)\n\n\n## 4、配置数据源\n修改yml 配置数据源 (application-test.yml) \n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201101121650721814-image.png)\n\n## 5、环境选择\n打开maven 面板，选择 test 的 yml 配置\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201101121812880104-image.png)\n\n## 6、启动\n\n运行启动类： XijiaServer  \n\n出现启动成功即可， 启动成功后会打印项目的所有接口信息, 可不必理会\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201101122032946706-image.png)\n\n## 7、本地访问\n\n访问地址：  [http://localhost:9048/](http://localhost:9048/)\n账号：10000\n账号：10000\n默认只有查看权限，没有编辑修改删除权限\n\n## 8、配置账号权限\n\n启动成功后打开swagger 接口文档处理权限： [http://localhost:9048/swagger-ui.html](http://localhost:9048/swagger-ui.html#/%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86/updRoleAuthAllUsingPUT)\n\n找到-  所有角色拥有所有接口权限\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201101123114469428-image.png)\n\n调用接口  -  10000 账号即可获得所有权限\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201101123239810889-image.png)\n\n\n\n', 0, 578);
INSERT INTO `t_xj_admin_help` VALUES ('1320197723818840066', NULL, NULL, '2020-10-25 02:57:20', '2021-03-05 14:37:53', 0, 0, '', 0, 0, '项目介绍', '\n![欢迎来到兮家](http://xijia.plus//oss/file/image/markdown/20201031133512794916-可爱像素动态娃娃_08_爱给网_aigei_com.gif)\n\n\n\n## 项目描叙\n\n- 该项目是一个开源的个人项目, 可供个人免费学习使用, \n- 项目主要作用于敏捷开发 (快速开发)，管理平台前后端可由一个人完成开发任务\n- 提供基于该项目的完整代码生成器, 一键生成前后端的 crud 代码, 只需添加一下菜单页即可\n- 所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加修改，以及 html代码\n- 所有接口均采用 RESTful 风格 和 http协议, 支持前 / 后 分离开发\n- 丰富的第三方api 对接, 阿里云 SMS / 阿里云 oss / 快递100 / 微信网页授权 / 微信公众号模板消息推送等 (后续将持续扩展)\n\ngitee地址:  [https://gitee.com/wslxm/spring-boot-plus2)]\n展示地址:  [http://xijia.plus/](http://xijia.plus/) \n\n账号：10000  \n密码：10000\n\n## 版本说明\n当前主分支为v-0.0.6 的分支最后更新代码,可稳定运行, 下个更新版本的版本 v-0.0.7\n具体内容:   开始使用 -->  更新日志\n\n## 采用框架\n- jdk1.8\n- springboot 2.2.0\n- mysql 8.0.16 + druid 1.1.21\n- layui 2.5.6\n- jwt + springSecurity\n- swagger2.9.2\n- websocket\n- 阿里云OSS/SMS\n\n## 目录结构\n```bash\n\nv-0.0.9+ 版本后端代码目录结构(模块化后结构)\n```base\n1、模块\nspring-boot-plus2\n        └──xj-base\n               └──xj-base-core             核心包: 通用base类层+枚举字典\n               └──xj-base-jwt              核心包: 登录认证\n               └──xj-base-result           核心包: 统一返回 + 全局异常\n        └──xj-server     \n               └──xj-admin-server          系统基础代码+增强功能+代码生成(后端接口)\n               └──xj-admin-ui              系统基础代码+增强功能+代码生成(前端页面)\n               └──xj-pay-server            聚合支付(微信/支付宝等)\n               └──xj-test-server           业务代码(个人功能开发/架构测试)\n               └──xxx                      开发具体项目业务代码模块\n        └──xj-third\n               └──xj-api-aliyun-oss        阿里云oss集成(文件存储)\n               └──xj-api-aliyun-sms        阿里云sms集成(短信)\n               └──xj-api-baidu             百度api集成(图片/身份证/银行卡识别)\n               └──xj-api-kuaidi-kuaidi100  快递100集成(查询物流)\n               └──xj-api-kuaidi-sf         顺丰-丰桥(顺丰寄件)\n               └──xj-api-qiniu-oss         七牛云oss集成(文件存储)\n               └──xj-api-wx-app            微信小程序集成(小程序登录/小程序订阅消息)\n               └──xj-api-wx-mp             微信公众号集成(公众号网页授权/公众号模块消息)\n               └──xj-api-wx-pay            微信支付集成(支付/退款/打款)\n               └──xj-doc-knife4j           接口文档ui集成(依赖swagger2：访问地址: ip/doc.html)\n               └──xj-doc-swagger2          接口文档集成\n               └──xj-doc-swagger2-ui       接口文档ui集成(依赖swagger2：访问地址: ip/swagger-ui.html)\n               └──xj-fw-boot-admin         监控中心集成    \n               └──xj-fw-redis              redis集成    \n               └──xj-fw-websocket          websocket集成    \n        └──xj-utils\n               └──xj-util-core             核心工具(注意：使用了核心工具类需启动服务需禁数据源)\n               └──xj-util-fastjson         fastjson工具\n               └──xj-util-poi              excel 工具\n               └──xj-util-qrcode           二维码 工具\n```\n\n\nv-0.0.8 - v-0.0.9 版本，后端代码目录结构 (模块化前结构)\n```base\n\nspring-boot-plus2\n            └──client  用户端接口 (目录结构同 modules)\n            └──common  工具\n                 └──function     Lambda8 获取字段名工具\n                 └──result       统一返回\n                 └──utils        util工具类\n            └──config  系统配置\n                 └──aspect       aop 接口入口(登录认证/权限/日志/黑名单/幂等拦截处理等)\n                 └──auth         jwt登录（获取用户信息工具类 / 登录处理核心逻辑，aop调用） \n                 └──datasource   数据源配置(mybatis-plus/druid)\n                 └──datetime     统一时间处理 (yyyy-MM-dd HH:mm:ss)\n                 └──error        全局异常\n                 └──idempotent   幂等\n                 └──init         系统启动存放,初始化相关数据\n                 └──mvc          mvc 配置\n                 └──swagger      接口文档配置\n            └──enums   常量/枚举\n            └──modules\n                 └──business     业务模块\n                        └──xx1         \n                        └──xx2   \n                 └──sys          系统模块\n                        └──admin        系统核心功能\n                        └──base         系统核心通用层\n                        └──gc           代码生成器\n                        └──pay          支付封装\n                        └──xj           系统增强功能\n                 └──third        第三方服务\n                        └──aliyun       阿里云API\n                                └──oss           阿里云对象存储(文件)\n                                └──sms           阿里云短信\n                        └──baidu        百度api\n                     \n                        └──kuaidi       快递\n                                └──kuaidi100     快递100查询物流\n                                └──sf            顺丰寄件\n                        └──qiniu        七牛云oss\n                        └──websocket    及时通知/聊天(注意与xjAdminMsg及时通知表有关联)\n                        └──wechat       微信API\n                                └──app           小程序登录 \n                                └──mq            公众号(网页授权/模板信息推送)\n                                └──pay           微信支付\n   └──task     定时任务\n\n```\n\n\n\n## 作者: \n兮家小二（王松）\nqq/wx: 1720696548\n## 作者CSDN首页\n [https://blog.csdn.net/qq_41463655](https://blog.csdn.net/qq_41463655)\n## Git地址\n\nhttps://gitee.com/wslxm/spring-boot-plus2\n## 注意事项\n请勿恶意分享产品源代码、二次转售等，违者将承担相应的法律责任。\n如需商业使用,请联系作者免费获取版权。\n© 2020 xijia.plus 版权所有', 0, 1187);
INSERT INTO `t_xj_admin_help` VALUES ('1320203552471220225', NULL, NULL, '2020-10-25 03:20:29', '2020-11-03 22:58:53', 1, 0, '', 1, 0, 'mybatis-plus', '待定', 0, 16);
INSERT INTO `t_xj_admin_help` VALUES ('1320203622570622978', NULL, NULL, '2020-10-25 03:20:46', '2020-11-03 22:58:28', 1, 0, '', 1, 0, 'springboot', '待定', 0, 16);
INSERT INTO `t_xj_admin_help` VALUES ('1320203790619607042', NULL, NULL, '2020-10-25 03:21:26', '2020-11-03 22:58:20', 1, 0, '', 1, 0, 'mysql+druid', '待定', 0, 13);
INSERT INTO `t_xj_admin_help` VALUES ('1320203848756854785', NULL, NULL, '2020-10-25 03:21:40', '2020-11-03 22:58:24', 1, 0, '', 1, 0, 'layui', '待定', 0, 17);
INSERT INTO `t_xj_admin_help` VALUES ('1320204195596435457', NULL, NULL, '2020-10-25 03:23:03', '2021-02-19 10:31:19', 0, 0, '', 2, 0, '全局配置', '待定', 4, 117);
INSERT INTO `t_xj_admin_help` VALUES ('1320204253012262914', NULL, NULL, '2020-10-25 03:23:16', '2021-02-19 10:31:36', 0, 0, '', 2, 0, '代码生成', '\n文档版本 | 编写时间 | 编写人\n:---:|:--:|:---:\n0.0.1 | 2020-11-2 | 兮家小二\n\n\n## 一、基础使用\n\n本项目提供了自编写的代码生成器，可直接生成前后端的 crud 方法，生成出来后可以直接在新生成的代码页面进行增删改查操作，是不是很nb 呢 face[鼓掌] \n\n### 注意事项\n- 如果数据表操作 sql 关键字, 请自行处理和配置到 GenerateConfig 的 KEYWORD_ARRAY 中）\n- 代码生成后， 需重启项目生效\n- 新生成的代码会覆盖项目中对应的同名文件的代码, 也就是上一次生成的代码，就是已经修改过，也会覆盖\n\n\n\n###  1.1、代码生成目录结构\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103002015421855-image.png)\n\n### 1.2、预览代码生成位置\n点击预览的代码会被生成在File 目录下\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201102235723166533-image.png)\n\n\n\n### 1.3、页面基础操作\n \n左边菜单打开代码生成的数据表\n\n- 1、选择对应的表（默认读取当前 jdbc 配置的数据源,  已支持在线配置任意数据源, 读取任意库的表数据）\n- 2、可以选择需要生成的字段, 未勾选的字段生成的实体类中不会出现\n- 3、选择出查询字段，不会受到勾选的影响，会在controller 的分页查询中生成对应的查询条件（默认eq），自动处理数据类型\n- 4、生成前可以点击 -> 查看生成路径来查看代码最后的生成位置\n- 5、生成前可以预览，来查看生成的代码是否正确  \n  \n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201102233255160031-image.png)\n\n\n### 1.4、预览页展示\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201102235551435682-image.png)\n\n\n### 1.5、生成代码访问路由\n\n- 代码生成后控制台会打印菜单路径和代码生成位置\n- 路径可以配置到菜单里面 或者  ip:端口/ 菜单路径 直接访问，如：http://localhost:9049/page/modules_admin_adminLog_adminLog\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103001033029810-image.png)\n\n### 1.6、路由访问规则说明\n\nip + 端口 / page / 目录_目录_文件名 （目录看层级关系确定多少, 用下划线分隔）\n\n如：ip + 端口   /page/modules_admin_adminLog_adminLog\n\n\n\n## 二、配置\n\n### 2.1、代码生成位置\n文件： src/main/java/com/ws/ldy/others/generatecode/config/GenerateConfig.java\n\n- 生成的代码位置配置,  生成代码前注意修改生成位置\n-注意：新生成的代码会覆盖原代码，如果代码已添加业务代码，请谨慎处理 （可以使用预览来进行复制替换）\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201102234501402068-image.png)\n\n\n### 2.2、关键字 /通用字段配置\n\n- 通用字段配置, 通用字段列表，在页面展示字段时默认不会勾选\n- 数据库关键字，关键字数据实体类的 TableField 注解值会自动添加转译号, 如：@TableField(value = \"`sort`\")，避免插入更新操作异常\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201102235001139275-image.png)\n\n### 2.3、动态生成查询条件\n\n\n#### 2.3.1 - 选择的查询条件\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103000300115593-image.png)\n\n#### 2.3.2 - findPage方法自动追加请求参数\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103000428121942-image.png)\n\n#### 2.3.3 -demoHtml 页自动追加搜索框\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103000604236141-image.png)\n\n#### 2.3.4 - demoHtml 页自动追加的搜索条件\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103000633489821-image.png)\n\n\n## 三、多数据源配置（0.0.7版本新增）\n\n### 3.1、说明\n\n为什么要连接多个库?\n  \n项目中可以采用了模块话开发，也或许我们有多个环境，也或者有多个开发项目，需要连接不同的库来查看表数据并生成代码\n\n功能\n- 1、采用原始的 jdbc 来动态连接数据库\n- 2、动态配置表前缀,  前缀在生成代码时会自动过滤（文件名/类名不会出现表前缀，如: t_user -> User.java）\n- 3、动态配置通用字段,  通用字段一般都是继承父类, 过滤的通用字段将不会生成在 dto/entity/vo 实体类中\n- 4、动态配置字段前缀, 同山，自动过滤生成的字段名前缀\n- 5、通过连接测试, 添加成功后，查询不返回密码, 防止被盗取\n\n### 3.2、数据源管理\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201121234003053610-image.png)\n\n### 3.3、添加数据源\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201121234233468815-image.png)\n\n\n### 3.4、选择数据源\n\n可选择任意已添加的数据源配置进行切换\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201121234114297782-image.png)\n\n## 四、代码位置\n\n### 4.1 代码模板位置\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201122212023113868-image.png)\n\n### 4.1 逻辑代码位置\n\n这个喜欢研究的小伙伴就自行研究了，不过多描叙\n\n \n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201122212214852088-image.png)\n\n\n\n\n\n\n', 5, 141);
INSERT INTO `t_xj_admin_help` VALUES ('1320204291310452737', NULL, NULL, '2020-10-25 03:23:25', '2021-02-19 10:31:44', 0, 0, '', 2, 0, '用户/角色/菜单', '待定', 0, 149);
INSERT INTO `t_xj_admin_help` VALUES ('1320204382809194498', NULL, NULL, '2020-10-25 03:23:47', '2021-02-19 10:31:46', 0, 0, '', 2, 0, '字典管理', '## 一、字典管理描叙\n\n字典的作用：\n- 1、 定义展示值 name 和 数据库存储值 code\n- 2、 动态管理页面内容条目\n\n### 1.1、作用一： 字段状态定义\n\n规则：\n- 所有字段 code 值全为字母, 不得重复（程序判重）\n- 字段类型为数字,不能重复, 定义类型人员自行检查\n\n#### 1.1.1、说明\n\n该系统中， 枚举字典(ENUMS)  下数据字典的设计主要针对于逻辑字段类型， 比如一个字段用多个含义，即枚举字典对应数据库的字段\n- 列1： 性别： 0-女  1-男  2-未知\n- 列2： 支付类型： 0-支付宝  1-微信  2-网银 3-其他 \n- 列3： 菜单类型： 0-目录  1-菜单  2-页面 3-按钮\n  \n  对应数据库字段示例：\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201122234759518556-image.png)\n\n\n- 更多如下示例：  \n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201122235039872670-image.png)\n\n#### 1.1.2、为什么进行字段状态定义?\n\n- 1、在这里区分主要是为了统一管理状态值，数据库中，表太多，字段更多，时间一长，就不知道哪里有状态字段了\n- 2、生成枚举，新建或者编辑后，可以直接生成枚举类，供代码层使用，并且提供统一枚举字段规则。不用每次脑壳大怎么命名了\n- 3、节约代码预读成本，统一使用枚举类的枚举值进行判断操作和参数读取操作， 如： (type  == Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())\n- 4、提供前端缓存，并支持动态 增/删/改 前端的状态值,节约开发成本以及服务资源使用\n\n\n### 1.2、作用二： 层级关系定义\n\n规则：\n- 所有层级数据 code 值全字母，用户自定义字母填写规则，不得重复（程序判重复）\n\n#### 1.2.1、说明\n\n系统中，经常会出现层级业务数据，为了更好的处理这个数据，可以使用字典直接进行配置\n- 列1： 省/市/区\n- 列2： 一级类目/二级类目/三级类目\n\n- 列图：\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201123001416087316-image.png)\n\n\n\n#### 1.2.2、为什么进行字段状态定义?\n\n- 1、提供前端缓存，并支持动态 增/删/改 ,节约开发成本以及服务资源使用\n\n\n\n\n\n## 二、枚举生成\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201123005111482209-image.png)\n\n字段修改完成后生成枚举\n- 1、拷贝 java 数据代 Enums.java   覆盖整个文件\n- 2、拷贝 js 数据到  app.js  覆盖Enums 参数下所有内容\n\n\n## 三、简单操作演示\n\n### 3.1、添加枚举字段对应数据库\n\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201123002720447646-image.png)\n\n### 3.2、对应数据库字段  \n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201123002934903332-image.png)\n\n  \n### 3.3、页面展示\n\n如何展示参考： 4.2.1  select 展示\n  \n  \n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201123003057104950-image.png)\n  \n  \n \n\n当第一个步骤添加了新的值，或者修改了原有值，那么第三步的页面展示值也会随之发生变化，是不是很有趣呢，不过需要注意修改后需要刷新一下系统才生效哦，因为前端读取的缓存数据，重新加载系统才会生效的哟\n\n## 四、前端使用\n\n注意： 此段文档描叙不适用于 layui 外的前端框架\n\n核心代码可参考： app.js  -->  Dict 下的方法\n\n打开项目调用 Dict .refreshDict 生成前端缓存数据\n\n### 4.1、数据表格,返回参数转换\n\n\n转换代码：\n \n后台返回的数字，传入枚举key,加返回数据code ， 方法将直接返回对应的 name 值, 如： 0 --> 女 |  1 --> 男 到页面输出\n```\nDict.convert(Enums.Base.Gender, res.gender);\n```\n layui中转换示例：\n```\nfield: \'gender\', title: \'性别\', templet: function (res) {\n       return Dict.convert(Enums.Base.Gender, res.gender);\n }\n```\n    \nEnums.Base.Gender 为  生成枚举拷贝到app.js 的数据，根据字段自行切换\n\n###  4.2. select 展示 /回显\n\n####  4.2.1  select 展示    \n在添加页和编辑页以及数据表格页中，经常需要用到字段的select输入框，那么我们可以直接动态生成 select 内的数据 \n\nlayui 位置, 注意，外层div需要 layui-form 的calss，否则可能无法正常展示select\n```\n<div class=\"layui-form-item\">\n        <label class=\"layui-form-label\">性别</label>\n        <div class=\"layui-input-inline\">\n            <select id=\"gender\" name=\"gender\" lay-verify=\"\" lay-filter=\"gender\">\n            </select>\n        </div>\n    </div>\n```\n获取 option 放入html，jqyery 方式    \n\n```\n$(\"#gender\").html(Dict.getDictSelect(Enums.Base.Gender));                    // 性别\n$(\"#gender\").html(Dict.getDictSelect(Enums.Base.Gender, -1, \"默认值\" ));      // 性别，带自定义默认值方式\n```\n动态生成后页面没有参数，可参考： 4.2.3、 layui-select 回显/生成不生效\n\n\n#### 4.2.2、 select 回显 \n\njquery 方式\n```\n $(\"#gender\").find(\"option\").each(function () {\n     if ($(this).val() == parent.data.gender) {\n           $(this).attr(\"selected\", \"selected\");\n     }\n});\n```\n#### 4.2.3、 layui-select 回显/生成不生效\n\n在select 回显后添加以下代码刷新, 否则select可能不生效,放在执行代码的最后\n```\n layui.form.render();\n```\n\n## 五、后端使用\n\n主要做字段判断和参数读取\n\n### 5.1 示例1，参数读取\n\n```\n  // mybatis-plis中获取参数\n  .eq(AdminAuthority::getType, Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())\n\n   // 赋值\n  .setAuthorityType(Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())\n```\n### 5.2 示例2，类型判断\n\n```\n  // 直接使用数据库值，或者传入值，进行判断\n  if (jwtUser.getType().equals(Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())\n```\n\n\n\n\n', 2, 157);
INSERT INTO `t_xj_admin_help` VALUES ('1320204426199269377', NULL, NULL, '2020-10-25 03:23:58', '2020-10-25 03:38:37', 1, 0, '', 0, 0, '接口管理', '待定', 0, 3);
INSERT INTO `t_xj_admin_help` VALUES ('1320204668290301953', NULL, NULL, '2020-10-25 03:24:55', '2020-11-21 23:55:15', 1, 0, '', 2, 0, '菜单+URL 权限分配', '待定', 0, 55);
INSERT INTO `t_xj_admin_help` VALUES ('1320206185969790977', NULL, NULL, '2020-10-25 03:30:57', '2021-02-03 15:37:00', 0, 0, '', 2, 0, '接口管理', '待定', 2, 117);
INSERT INTO `t_xj_admin_help` VALUES ('1320207506596777986', NULL, NULL, '2020-10-25 03:36:12', '2020-11-03 22:58:11', 1, 0, '', 1, 0, 'jwt+security', '待定', 0, 18);
INSERT INTO `t_xj_admin_help` VALUES ('1320207653317726210', NULL, NULL, '2020-10-25 03:36:47', '2020-11-03 22:58:08', 1, 0, '', 1, 0, 'maven', '待定', 0, 16);
INSERT INTO `t_xj_admin_help` VALUES ('1320214188680892418', NULL, NULL, '2020-10-25 04:02:45', '2021-03-04 17:03:59', 0, 0, '', 6, 0, '作者信息', '\n## 基础信息\n- 姓名：王松\n- 艺名：兮家小二（叫我小二就好了）\n- 作者qq:  1720696548 \n- 作者微信: 1720696548 \n- 出生地：四川宜宾泥南  ->  四川省宜宾市叙州区\n- 现居：四川成都\n- 当前工作公司：四川成都乐陪乐伴有限公司\n- 当前职业年限：2018年-至今\n- 作者 csdn 地址：https://blog.csdn.net/qq_41463655\n- 作者 码云地址：https://gitee.com/wslxm \n- 作者 快手号：wx1720696548 （偶尔更新相关技术看点内容）\n- 作者 bilbil 用户名：兮家小二\n\n## 性格爱好\n- 喜欢动漫，不太喜欢看电视剧，喜欢周星驰的电影，喜欢希腊,北欧等神话电影\n- 喜欢专研技术，不喜欢勾心斗角，不喜欢管理 (自己都管不好自己,emmm....)\n- 喜欢单机游戏( 没时间玩，也没ps4等设备，emmm....)， 偶尔玩玩王者荣耀等游戏\n- 喜欢下雨天，有点小抑郁，不太会说话，也没什么朋友，经常一个人宅家\n- 喜欢风景好的地方，以及可爱的人\n\n\n\n', 0, 72);
INSERT INTO `t_xj_admin_help` VALUES ('1320215201798246401', NULL, NULL, '2020-10-25 04:06:47', '2021-02-19 10:30:36', 0, 0, '', 6, 0, '赞赏作者', '待定', 1, 49);
INSERT INTO `t_xj_admin_help` VALUES ('1320215573619101698', NULL, NULL, '2020-10-25 04:08:15', '2021-02-19 15:02:40', 0, 0, '', 4, 0, '项目上线', '## 一、 jar 部署脚本\n\n\n### 1.1、脚本命令\n\n启动：`./start.sh  start`  \n停止：`./start.sh  stop`  \n重启：`./start.sh  restart`  \n输出运行状态：`./start.sh  status`  \n\n\n### 1.2、编写脚本\n创建文件 **start.sh** 文件放至 jar 同目录（linux 服务器）\n添加内容\n```\n#!/bin/bash\n#这里可替换为你自己的执行程序，其他代码无需更改\nAPP_NAME=txlcn-tm-5.0.2.RELEASE.jar\n\n#使用说明，用来提示输入参数\nusage() {\necho \"Usage: sh 脚本名.sh [start|stop|restart|status]\"\nexit 1\n}\n\n#检查程序是否在运行\nis_exist(){\npid=`ps -ef|grep $APP_NAME|grep -v grep|awk \'{print $2}\' `\n#如果不存在返回1，存在返回0\nif [ -z \"${pid}\" ]; then\nreturn 1\nelse\nreturn 0\nfi\n}\n\n#启动方法\nstart(){\nis_exist\nif [ $? -eq \"0\" ]; then\necho \"${APP_NAME} is already running. pid=${pid} .\"\nelse\nnohup java  -Xms512m -Xmx1024m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC -jar /usr/local/work/lcn/$APP_NAME > log.txt &\necho \"${APP_NAME} start success\"\nfi\n}\n\n#停止方法\nstop(){\nis_exist\nif [ $? -eq \"0\" ]; then\nkill -9 $pid\nelse\necho \"${APP_NAME} is not running\"\nfi\n}\n\n#输出运行状态\nstatus(){\nis_exist\nif [ $? -eq \"0\" ]; then\necho \"${APP_NAME} is running. Pid is ${pid}\"\nelse\necho \"${APP_NAME} is NOT running.\"\nfi\n}\n\n#重启\nrestart(){\nstop\nstart\n}\n\n#根据输入参数，选择执行对应方法，不输入则执行使用说明\ncase \"$1\" in\n\"start\")\nstart\n;;\n\"stop\")\nstop\n;;\n\"status\")\nstatus\n;;\n\"restart\")\nrestart\n;;\n*)\nusage\n;;\nesac\n```\n### 1.3、修改内容\n\n[点击查看jvm参数描叙](https://blog.csdn.net/qq_41463655/article/details/101594685?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522158477151019724845024409%2522%252C%2522scm%2522%253A%252220140713.130056874..%2522%257D&request_id=158477151019724845024409&biz_id=0&utm_source=distribute.pc_search_result.none-task)\n\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20200321140927396.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70)\n\n\n### 1.4、启动 jar 服务\n\n```\n ./start.sh  start\n```\n\n## 二、Nginx 转发配置\n\n该配置仅供参考  \n  \n\n```\nworker_processes  1;\n\n\nevents {\n  worker_connections  1024;\n}\n\nhttp {\n  # websocket支持 start\n  map $http_upgrade $connection_upgrade {\n    default upgrade;\n    \'\' close;\n  }\n  # websocket支持 end\n\n  include       mime.types;\n  default_type  application/octet-stream;\n  sendfile        on;\n  keepalive_timeout  65;\n\n  # 502 bad gateway 错误解决配置 start\n  proxy_buffer_size 64k;\n  proxy_buffers 32 32k;\n  proxy_busy_buffers_size 128k;\n  # 502 bad gateway 错误解决配置 end\n\n\n  ## 80端口配置\n  server {\n    listen       80;\n    server_name  localhost;\n    proxy_intercept_errors on;\n\n\n    location / {\n      # root   html;\n      # index  index.html index.htm;\n\n      proxy_set_header   Host $host;\n      proxy_set_header   X-Real-IP $remote_addr;\n      proxy_set_header   X-Real-PORT $remote_port;\n      proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;\n\n      proxy_pass http://127.0.0.1:9049;\n        error_page 405 =200 http://$host$request_uri;\n\n      # 启用支持websocket连接\n      proxy_set_header Upgrade $http_upgrade;\n      proxy_set_header Connection \"upgrade\";\n    }\n\n    location /oss {\n      proxy_pass  http://xijia-sz.oss-cn-shenzhen-internal.aliyuncs.com;\n    }\n\n    error_page   500 502 503 504  /50x.html;\n    location = /50x.html {\n      root   html;\n    }\n  }\n\n\n\n  ## https  协议支持 以下属性中以ssl开头的属性代表与证书配置有关，其他属性请根据自己的需要进行配置。\n  ## 443 端口配置\n  server {\n\n    listen 443 ssl;                               # SSL协议访问端口号为443。此处如未添加ssl，可能会造成Nginx无法启动。\n    server_name www.xijia.plus;    # 将localhost修改为您证书绑定的域名，例如：www.example.com。\n\n    proxy_intercept_errors on;\n    root html;\n    index index.html index.htm;\n\n    ssl_certificate cert/4588060_xijia.plus.pem;         # 将domain name.pem替换成您证书的文件名。\n    ssl_certificate_key cert/4588060_xijia.plus.key;     # 将domain name.key替换成您证书的密钥文件名。\n    ssl_session_timeout 5m;\n    ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4;  #使用此加密套件。\n    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;   #使用该协议进行配置。\n    ssl_prefer_server_ciphers on;\n\n    location / {\n      # root   html;\n      # index  index.html index.htm;\n\n      proxy_set_header   Host $host;\n      proxy_set_header   X-Real-IP $remote_addr;\n      proxy_set_header   X-Real-PORT $remote_port;\n      proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;\n\n      proxy_pass http://127.0.0.1:9049;\n        error_page 405 =200 http://$host$request_uri;\n\n      # 启用支持websocket连接\n      proxy_set_header Upgrade $http_upgrade;\n      proxy_set_header Connection \"upgrade\";\n\n    }\n\n    location /oss {\n      proxy_pass  http://xijia-sz.oss-cn-shenzhen-internal.aliyuncs.com;\n    }\n\n    error_page   500 502 503 504  /50x.html;\n    location = /50x.html {\n      root   html;\n    }\n  }\n}\n\n\n```\n\n## 三、https 配置\n待定\n\n\n', 0, 89);
INSERT INTO `t_xj_admin_help` VALUES ('1320215640753131521', NULL, NULL, '2020-10-25 04:08:31', '2021-03-02 10:54:56', 0, 0, '', 3, 0, '阿里云-OSS', '阿里云oss 申请和创建bucket 和 获取accessKeyId + accessKeySecret 自行查阅相关文档\n阿里云oss官网地址：[阿里云oss官网](https://oss.console.aliyun.com/overview \"阿里云oss官网\")\n\n## 1、引入子模块jar\n\n在需要阿里云oss的业务模块导入\n```bash\n     <dependency>\n         <groupId>com.ws.ldy</groupId>\n         <artifactId>xj-api-aliyun-sms</artifactId>\n         <version>${xj-api-aliyun-sms.version}</version>\n     </dependency>\n```\n## 2、配置yml\n\n参数说明：\n- endpoint ：  oss 上传地址\n- accessKeyId： 秘钥keyId\n- accessKeySecret： 秘钥KeySecret\n- bucketName： Bucket 名称\n- bucket：  访问域名\n注意：\n1、endpoint 上传地址本地测试请使用外网地址，部署尽量使用内网地址，使用内网服务器和oss需要在同一个地域下\n2、bucket 访问域名使用外网, 直接配置oss控制台的外网地址即可\n3、bucket 访问域名 + endpoint 上传地址 使用cdn加速, 直接配置oss控制台的cdn的加速地址即可\n4、使用内网访问文件, bucket 配置服务器域名, 在使用 nginx 配置转发到内网即可, 外网访问也可使用此方式\n\n\nnginx转发配置如下\n```bash\n location /oss {\n             proxy_pass  http://Bucket 名.oss-cn-shenzhen-internal.aliyuncs.com;\n }\n```\n\nyml 配置\n```bash\naliyun:\n  oss:\n    endpoint:                                 \n    accessKeyId:                             \n    accessKeySecret:                          \n    bucketName:                        \n    bucket: \n```\n## 3、使用\n打开swagger 的通用接口列表， 会出现oss文件管理接口，前端直接调用即可\n\n ![/20210129144140946776-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210129144140946776-image.png)\n\n上传接口响应 data 为可访问的文件链接，业务图片由前端获取url后，通过具体业务接口在发送给后台保存即可\n', 0, 53);
INSERT INTO `t_xj_admin_help` VALUES ('1320215701985775618', NULL, NULL, '2020-10-25 04:08:46', '2021-03-04 17:02:28', 0, 0, '', 3, 0, '阿里云-SMS', '\n[阿里云短信官网](https://dysms.console.aliyun.com/dysms.htm#/overview \"阿里云短信官网\")\n## 1、导入子模块\n\n```bash\n<!-- 第三方服务- 阿里云sms -->\n <dependency>\n     <groupId>com.ws.ldy</groupId>\n     <artifactId>xj-api-aliyun-sms</artifactId>\n     <version>${xj-api-aliyun-sms.version}</version>\n </dependency>\n```\n## 2、配置yml\n```bash\n## 阿里云\naliyun:\n  sms:\n    accessKeyId: \"\"                       # 请在阿里云短信控制台获取\n    accessKeySecret: \"\"                   # 请在阿里云短信控制台获取\n    signName: \"阿里云短信测试专用\"          # 签名\n    templateCode: \"SMS_141915022\"         # 短信验证码模版(每个账号不一致,单独配置，默认短信验证模板)\n```\n获取签名： 申请签名请尽量设置为通用,避免多个签名\n\n![/20210129164522997242-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210129164522997242-image.png)\n\n\n\n获取短信模块： 申请短信后生成的默认短信验证模板\n\n ![/20210129165253317192-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210129165253317192-image.png)\n\n\n## 3、发送短信验证码\n\n\n3.1、发送短信验证码\n````bash\n// 引入\n@Autowired\nprivate AliSmsUtil aliSmsUtil;\n\n// 调用发送方法\naliSmsUtil.sendCode(phone)\n````\n\n\n3.2、验证验证码是否正确\n```bash\n\n/**\n * 正确返回 code =200,   data=验证码\n * 错误返回 code !=200,  msg=返回对应的错误信息\n */\npublic R<String> verifySMS(String phone, String code)\n```\n\n\n## 4、更多模板消息定义发送\n\n在业务模块 添加  短信模块[工具类] 或 [service层]， 如:  SmsSendUtil\n在阿里云sms控制台申请业务短信模板\n\n示例代码\nsignName=签名(可使用yml配置签名 aliSmsUtil.getSignName()获取 )\ntemplateCode=模板id\n```bash\n @Autowired\n private AliSmsUtil aliSmsUtil;\n\n\n // 拼接参数\n Map<String, String> mapParam = new HashMap<>();\n mapParam.put(\"param1\", \"\");\n mapParam.put(\"param2\", \"\");\n mapParam.put(\"param3\", \"\");\n // 发送短信\n boolean result = this.sendMsg(phone, signName, templateCode, JSON.toJSONString(mapParam));\n \n```', 0, 63);
INSERT INTO `t_xj_admin_help` VALUES ('1320216077984157698', NULL, NULL, '2020-10-25 04:10:16', '2021-03-02 10:54:56', 0, 0, '', 3, 0, 'websocket', '## 一、后端部分\n\n### 1、导入子模块\n```bash\n <dependency>\n         <groupId>com.ws.ldy</groupId>\n         <artifactId>xj-fw-websocket</artifactId>\n         <version>${xj-fw-websocket.version}</version>\n  </dependency>\n```\n\n### 2、在线测试\n\n启动即可使用\n\n\n地址1 =   ws:// 域名 /websocket/ 用户id / 用户名 / 用户头像 \n地址2 =   ws:// ip+端口 /websocket/ 用户id / 用户名 / 用户头像 \n示例：[ws://localhost:9049/websocket/20210129151620589/游客:20589/1.jpg  ](ws://localhost:9049/websocket/20210129151620589/游客:20589/1.jpg  )，可重复使用, 第二次使用会把第一次使用的挤下线\n\n\n在线测试网址： http://www.websocket-test.com/\n\n输入地址点击链接（正常响应表示成功）\n\n ![/20210129152609006485-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210129152609006485-image.png)\n\n正常响应\n\n ![/20210129152647335760-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210129152647335760-image.png)\n\n### 3、消息推送\n\n1、获取 websocketService\n````bash\n@Autowired\n private WebsocketService websocketService;\n````\n2、推送消息\n\n```bash\n /**\n  * 发送消息\n  *\n  * @param form     发送人id  (业务系统用户id)\n  * @param username 发送人用户名  (业务系统用户名称)\n  * @param to       接收人id（多个逗号分隔）(业务系统用户id)\n  * @param content  发送内容 (text/json) (前后端自行约定内容, 多种通知类型可使用json添加type字段，前端解析内容处理)\n  * @param extras   扩暂发送内容\n  * @return\n  */\n public Boolean send(String form, String username,String headPic, String to, String content, String extras);\n```\n\nswagger 有发送消息测试接口，可以自行查看使用\n\n### 4、部署 Nginx支持(域名访问必备)\n\n```bash\n# 1、在http 块添加\nmap $http_upgrade $connection_upgrade {\n             default upgrade;\n            \'\' close;\n         }\n\n\n# 2、在localhost 块添加, 启用支持websocket连接\n\nproxy_set_header Upgrade $http_upgrade;\nproxy_set_header Connection \"upgrade\";\n\n```\n重新加载 nginx 配置文件即可 ` ./nginx -s  reload`\n\n\n## 二、前端部分\n\n### 1、连接地址\n\n可看后端部分查看 3,4 步骤 查询websocket地址是否可以正常连接到服务器\n\n- 地址=   ws:// 域名 /websocket/ 用户id / 用户名 / 用户头像 \n- 地址=   ws:// ip+端口 /websocket/ 用户id / 用户名 / 用户头像 \n示例：[ws://localhost:9049/websocket/20210129151620589/游客:20589/1.jpg](ws://localhost:9049/websocket/20210129151620589/游客:20589/1.jpg) ，可重复使用, 第二次使用会把第一次使用的挤下线\n\n### 2、websocket 连接代码示例\n```bash\n    var webSocket;\n    http:\n        if (\"WebSocket\" in window) {\n            let socketPath = \"ws:// 域名 /websocket/ 用户id / 用户名 / 用户头像\"\n            // 连接地址：后端获取\n            websocket = new WebSocket(socketPath);  \n\n            // 连通成功的监听\n            websocket.onopen = function () {\n                 console.log(\"连接成功...\");\n            };\n\n            // 接收后台服务端的消息监听\n            websocket.onmessage = function (evt) {\n                let received_msg = evt.data;           // 接收到的数据\n				//alert(JSON.stringify(received_msg));\n				// 解析数据  \n                let obj = JSON.parse(received_msg);    // json数据\n                let mesType = obj.mesType;             // 数据类型 (1上线通知 /2下线通知 /3 在线用户名单通知 /4 推送消息 0心跳检测-30秒一次)\n                let from = obj.from;                   // 来源Id，上下线时为上下线的用户id\n                let username = obj.username;           // 来源用户，上下线时为上下线的用户名\n                let to = obj.to;                       // 目标Id（接收人用户Id,逗号分隔，所有人时为-ALL)）\n                let content = obj.content;             // 内容 (text/json 前后端协商)\n                let extras = obj.extras;               // 扩展内容 (text/json 前后端协商)\n                let onlineNum = obj.onlineNum;         // 在线人数\n                let createTime = obj.createTime;       // 消息时间\n              \n			    // 心跳检测\n                if (mesType === 0) {\n\n                }\n			  \n                // 上线通知\n                if (mesType === 1) {\n\n                }\n\n                // 下线通知\n                else if (mesType === 2) {\n\n                }\n\n                // 在线列表通知（自己登录时给自己发送）\n                else if (mesType === 3) {\n \n                }\n\n                // 信息接收通知\n                else if (mesType === 4) {\n                    if (to === \"ALL\") {\n                        // 给所有人推送的信息 (所有登录用户都能接收)\n                    } else {\n                        // 给指定人推送的消息 (当前登录用户接收)\n                    }\n                }\n            };\n\n            // 连接关闭的回调事件\n            websocket.onclose = function () {\n                console.log(\"连接已关闭...\");\n            };\n\n        } else {\n            // 浏览器不支持 WebSocket\n            alert(\"您的浏览器不支持 WebSocket!\");\n        }\n\n\n    /**\n     * 关闭websocket 连接 --> 关闭窗口也会自动断开连接\n     */\n    function closeWebSocket() {\n        //直接关闭websocket的连接\n        websocket.close();\n    }\n\n\n```\n### 3、发送消息示例\n```bash\n // 参数拼接\n let message = {\n     \"content\": content,   // 发送内容\n     \"to\": to              // 接收人用户id ( ALL=发送给所有人  )\n };\n //发送数据\n websocket.send(JSON.stringify(message));\n```\n\n', 0, 58);
INSERT INTO `t_xj_admin_help` VALUES ('1320216172930617346', NULL, NULL, '2020-10-25 04:10:38', '2021-03-02 10:57:23', 0, 0, '', 3, 0, '顺丰快递', '顺丰(丰桥api中心)：  http://qiao.sf-express.com/pages/developDoc/index.html?level2=107366\n\n## 使用说明\n### 1、加载子模块\n```bash\n<!-- 第三方服务- 顺丰 -->\n<dependency>\n    <groupId>com.ws.ldy</groupId>\n    <artifactId>xj-api-kuaidi-sf</artifactId>\n    <version>${xj-api-kuaidi-sf.version}</version>\n</dependency>\n```\n### 2、yml添加配置\n顺丰提供了沙盒测试，可先使用沙盒环境进行参数使用\n正式地址：https://sfapi.sf-express.com/std/service\n沙盒地址：https://sfapi-sbox.sf-express.com/std/service\n```bash\n## 快递\nkuaidu:\n  # 顺丰(丰桥) 个人账号沙盒测试地址(17628689969/ ws/19960302)\n  sf:\n    url: https://sfapi-sbox.sf-express.com/std/service    # 请求地址\n    customerCode: WSXX                                    # 客户编码\n    customerCheckWord: sKfq2WO8V38rRFsyverQrnuuPfI0DS87   # 客户校检码\n    customerMonthlyCard: 7551234567                       # 客户月结卡号（测试卡号）\n```\n\n### 3、寄送快递\n注意：\n1、正式接口需要少量寄件需要跳过面单步骤( 详见步骤5)，\n2、需要申请正式月结卡号, 丰桥服务 -> api接入 -> 申请\n\n引入service\n```\n   @Autowired\n   private SFService sfService;\n```\n示例代码\n```bash\n\n   // 地址\n   List<ContactInfoList> contactInfoList = new ArrayList<>();\n   // 寄件人信息\n   ContactInfoList contactInfo1 = new ContactInfoList();\n   contactInfo1.setContactType(1);     // 1，寄件方信息 2，到件方信息\n   contactInfo1.setContact(\"\");        // 姓名\n   contactInfo1.setCountry(\"CN\");      // 国家或地区 2位代码\n   contactInfo1.setTel(\"\");            // 电话\n   contactInfo1.setAddress(\"四川省成都市双流区xx小区xx单元\");\n   contactInfoList.add(contactInfo1);\n   // 到件方信息\n   ContactInfoList contactInfo2 = new ContactInfoList();\n   contactInfo2.setContactType(2);     // 1，寄件方信息 2，到件方信息\n   contactInfo2.setContact(\"\");        // 姓名 \n   contactInfo2.setCountry(\"CN\");      // 国家或地区 2位代码\n   contactInfo2.setTel(\"\");            // 电话\n   contactInfo2.setAddress(\"四川省成都市双流区xx小区xx单元\");\n   contactInfoList.add(contactInfo2);\n   // 货物明细(只需商品名称即可)\n   CargoDetails cargoDetails = new CargoDetails();\n   cargoDetails.setName(\"测试商品\");\n   ArrayList<CargoDetails> cargoDetails1 = new ArrayList<CargoDetails>() {{\n       add(cargoDetails);\n   }};\n   // 请求数据\n   SFOrder en = new SFOrder();\n   en.setContactInfoList(contactInfoList);\n   en.setCargoDetails(cargoDetails1);\n   en.setOrderId(orderId);\n   en.setRemark(\"下单备注\");\n   return sfService.placeAnOrder(en);\n```\n// 区域表\n ![/20210201111456577623-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210201111456577623-image.png)\n\n\n### 4、退单\norderId = 当前系统下单生成的快递单号， 非顺丰的快递单号\n```\n@Autowired\nprivate SFService sfService;\n\nsfService.cancelOrder(orderId)\n```\n\n### 5、申请跳过面单打印步骤\n\n在提问中进行申请(目前没有申请渠道，比较坑)\n\n ![/20210201110259375149-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210201110259375149-image.png)\n \n 申请示例内容如下：\n ```\n申请免电子面单\n用户名:  \n顾客编码:  \n公司名:  \n理由: 散单不需要\n ```\n等待回复即可\n \n \n### 6、测试\n引入jar 即查询测试接口，可自行配合后台测试代码进行调试与测试\n\n ![/20210201114350842863-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210201114350842863-image.png)\n', 0, 69);
INSERT INTO `t_xj_admin_help` VALUES ('1320216210813571073', NULL, NULL, '2020-10-25 04:10:47', '2021-03-02 10:57:23', 0, 0, '', 3, 0, '快递100', '\n快递100 api访问官网地址：https://api.kuaidi100.com/home\n\n## 使用说明\n### 1、业务模块加载子模块\n```\n <!--  第三方服务- 快递100-->\n <dependency>\n     <groupId>com.ws.ldy</groupId>\n     <artifactId>xj-api-kuaidi-kuaidi100</artifactId>\n     <version>${xj-api-kuaidi-kuaidi100.version}</version>\n </dependency>\n```\n\n\n### 2、yml 配置\n快递100，授权码,客户码, 请到快递100页面申请企业版接口获取\n```bash\nkuaidu:\n  kuaidi100:\n    key:  快递100控制台获取\n    customer:  快递100控制台获取\n```\n### 3、获得快递查询接口\n\n- 引入jar即获得\n- swagger2 --> 通用接口 --> 快递100  -->  /api/open/kuaidi100/findOrder\n\n ![/20210201105334466940-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210201105334466940-image.png)\n\n\n### 4、业务层使用\n\n```bash\n@Autowired\nprivate KuaiDi100Service kuaiDi100Service;\n\n//\nkuaiDi100Service.findOrder(orderId)\n```\n\n\n暂未对接其他业务\n', 0, 74);
INSERT INTO `t_xj_admin_help` VALUES ('1320216424949567489', NULL, NULL, '2020-10-25 04:11:38', '2021-01-29 14:58:24', 1, 0, '', 3, 0, '微信-模板信息推送', '待定', 0, 64);
INSERT INTO `t_xj_admin_help` VALUES ('1320216490724642818', NULL, NULL, '2020-10-25 04:11:54', '2021-03-04 17:01:51', 0, 0, '', 3, 0, '微信mp (公众号)', '\n该文档仅适用于spring-boot-plus2 架构模板\n接口可参考swagger2 文档 -->  通用接口 --> 微信Mp\n\n[微信公众平台](https://mp.weixin.qq.com/ \"微信公众平台\")\n[微信公众测试平台](https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login \"微信公众平台\")\n\n## 开始使用\n### 导入jar\n```bash\n <!-- 第三方服务- 微信公众号相关 -->\n  <dependency>\n      <groupId>com.ws.ldy</groupId>\n      <artifactId>xj-api-wx-mp</artifactId>\n      <version>${xj-api-wx-mp.version}</version>\n  </dependency>\n```\n\n### 添加yml\n```bash\nwx:\n  # 公众号\n  mq:\n    appId: \"wx497ebbd2f211e249\"\n    secret: \"9fa0b9c2a98ac1d6d00f5f9fbf59efef\" \n    token: \"\"   # 非必填\n    aesKey: \"\"  # 非必填\n\n```\n\n### 一、网页授权\n本地测试需要内网穿透工具, 或把需要授权的页面部署到服务器来操作\n\n#### 1、获取网页授权url(后端拼接)\n调用接口获取： `/api/open/wx/mp/auth/getAuthCodeUrl`  \n\n公众号接口管理配置授权域名\n ![/20210128173248113039-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210128173248113039-image.png)\n\n\n下载授权验证文件到配置的域名( 让： 域名/文件名可直接访问  )\n ![/20210128173346807293-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210128173346807293-image.png)\n\n#### 2、打开网页授权url\n在微信客户端打开第一步获取到的网页授权url 。 注意：是微信客户端，也可在微信开发工具中打开\n\n\n打开后会重定向到第一步传递的参数地址，并携带code\n\n ![/20210128173659367522-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210128173659367522-image.png)\n\n\n#### 3、获取openId / 用户信息\n- 1、静默登录： `/api/open/wx/mp/auth/getOpenId` ，只能获取openId来登录，无法获取用户信息\n- 2、用户确认登录： `/api/open/wx/mp/auth/getUserInfo` ， 如用户未授权,只返回openId参数 \n\n\n#### 4、使用openId 登录\n\n业务方法，请自定义，\n注意：为了模快之间的独立，切勿在使用code获取openId操作直接进行业务登录操作，因为这将增加的业务的耦合度\n\n### 二、模板信息 \n\n#### 1、开通\n微信公众号 -->  功能  --> 添加功能插件  --> 申请开通\n\n\n![/20210128174649666649-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210128174649666649-image.png)\n\n#### 2、创建模版\n开通后模块管理添加模版，或直接使用市场模版\n\n在xj-api-wx-mp 模板中的` com/ws/ldy/modules/third/wechat/mq/util/WxMpTemplateMsgUtil.java` 中添加模板\n\n```\n   public void sendTest(String openId, String content) {\n        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()\n                .toUser(openId)\n                .templateId(\"P6llNez3CAcxEYzhQ_MDdFJGUifXfey15ZIX7MKiB3k\")\n                .url(\"\")\n                .build();\n        templateMessage.addData(new WxMpTemplateData(\"MSG\", content, \"#173177\"));\n        try {\n            // 发送\n            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);\n        } catch (WxErrorException e) {\n            log.info(\"模板信息发送失败：\" +e.getMessage());\n            e.printStackTrace();\n        }\n    }\n```\n\n\n#### 3、使用\n在controller中写一个测试调用方法 来测试即可\n业务层引用\n```\n @Autowired\n private WxMpTemplateMsgUtil wxMpTemplateMsgUtil;\n```\n注意：openId 和 公众号 必须对应才可以发送消息, 需公众号配置网页授权地址，需获得公众号对应的openId(公众号开发或网页授权获取)\n\n### 三、JSSDK\n\n调用接口  `/api/open/wx/mp/jsapi/sign` 获取JSSDK 需要的验证参数消息 \n剩下的就是前端的事的,暂时没有前端对接代码\n\n\n\n', 0, 112);
INSERT INTO `t_xj_admin_help` VALUES ('1320217901843697665', NULL, NULL, '2020-10-25 04:17:31', '2021-02-26 14:00:43', 0, 0, '', 3, 0, '系统/数据监控', '## 一、Spring Boot Admin\n\nSpring Boot Admin提供了很多服务治理方面的功能，利用它能节省我们很多在治理服务方面的时间和精力Spring Boot Admin提供了如下功能（包括但不限于）：\n\n显示健康状态及详细信息，如JVM和内存指标、数据源指标、缓存指标\n跟踪并下载日志文件\n查看jvm系统-和环境属性\n查看Spring启动配置属性\n方便loglevel管理\n查看线程转储\n视图http-traces\n查看http端点\n查看计划任务\n查看和删除活动会话(使用spring-session)\n状态更改通知(通过电子邮件、Slack、Hipchat…)\n状态变化的事件日志(非持久性)\n……(and more !)\n\n### 1、引入子模块\n\n```bash\n<!-- 服务集成- 监控中心SpringBootAdmin  -->\n <dependency>\n     <groupId>com.ws.ldy</groupId>\n     <artifactId>xj-fw-boot-admin</artifactId>\n     <version>${xj-fw-boot-admin.version}</version>\n </dependency>\n```\n\n\n### 2、yml配置\n因为我是单体项目，所有  server + client 放在一起\n```bash\nserver:\n  port: 9049\n\nspring:\n  ## spring-boot-admin 配置\n  boot:\n    admin:\n      ## admin 服务端访问地址\n      context-path: /bootAdmin/\n      ## admin 客户端连接服务端\n      client:\n        # 为了显示客户端的ip否则是以主机名显示的，这样需要添加hosts影射。\n        instance:\n          prefer-ip: true\n        url: http://localhost:${server.port}/${spring.boot.admin.context-path}/\n        #username: admin\n        #password: admin@12345\n\n# actuator 监控, 配置spring-boot-admin使用\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n# spring-boot-admin 实时日志配置,读取：logging.file\nlogging:\n  # 配置日志路径即可实时输出到 spring-boot-admin 日志管理\n  file: src/main/resources/static/log/xijia.log\n```\n\n### 3、启动类添加注解\n@EnableAdminServer\n\n### 4、访问\n\n访问地址： ip+端口/bootAdmin  或 域名/bootAdmin\n如： http://localhost:9048/bootAdmin\n\n\n\n### 5、展示\n\n\n#### 5.1、系统信息\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018235850527.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70#pic_center)\n\n#### 5.2、实时日志\n    \n![在这里插入图片描述](https://img-blog.csdnimg.cn/20201019001526729.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNDYzNjU1,size_16,color_FFFFFF,t_70#pic_center)\n\n\n## 二、druid 监控中心 (数据源)  \n \n### 2.1 访问地址\n访问地址： ``ip+端口/druid/index.html``    或     ``域名/druid/index.html ``\n 如： [http://localhost:9048/druid/index.html](http://localhost:9048/druid/index.html)\n### 2.2 展示\n\n ![/20210201142140641647-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210201142140641647-image.png)\n\n\n\n\n', 0, 179);
INSERT INTO `t_xj_admin_help` VALUES ('1320218085856202753', NULL, NULL, '2020-10-25 04:18:14', '2021-03-04 17:04:48', 0, 0, '', 0, 0, '开发文档', 'springboot整合swagger2 参考:  https://blog.csdn.net/qq_41463655/article/details/103455795   (第二步)\nspringboot整合knife4j  参考：https://blog.csdn.net/qq_41463655/article/details/109477010\n\n## 描叙\n为更好的提供前后端对接，项目集成了市场上广为使用的 swagger-ui  ，同时引入了 knife4j 页面, 能在线查看文档和测试接口的同时，也支持生成本地接口文档进行交付和第三方人员阅读\n\nswagger2 在 xj-base-admin 已内置,无需再次引入，本文主要针对文档 ui \n\n### 编辑/添加 application.yml 配置\n```bash\n# swagger配置\n# swagger配置\nswagger:\n  sysAdminPackage: com.ws.ldy.modules.sys          # 系统代码接口目录\n  sysBasePackage: com.ws.ldy.modules.third         # 通用及第三方对接接口目录\n  ywAdminPackage:  com.ws.ldy.modules.yw           # 业务管理端接口目录\n  ywClientPackage: com.ws.ldy.client               # 业务用户端接口目录\n  author: wangsong                                 # 作者/负责人姓名\n  email: 1270696548@qq.com                         # 作者/负责人邮箱\n  url: https://gitee.com/wslxm/spring-boot-plus2   # 访问地址\n  version: 1.0.0                                   # 版本号\n  defaultKey: TOKEN                                # 默认请求头key\n  defaultValue: token                              # 默认请求头值-当为token时，可以访问任意接口, 但接口中存在获取token用户信息时,将返回jwt解析错误\n  isShield: false                                  # 是否展示文档 true展示 false隐藏\n```\n\n### 一、swagger2-ui \n\n#### 1、加载ui界面\n```\n <!-- 接口文档- swagger2-ui -->\n <dependency>\n     <groupId>com.ws.ldy</groupId>\n     <artifactId>xj-doc-swagger2-ui</artifactId>\n     <version>${xj-doc-swagger2-ui.version}</version>\n </dependency>\n```\n\n#### 2、访问\n\n访问：ip:端口/swagger-ui.html \n示例：  http://xijia.plus/swagger-ui.html\n\n### 二、knife4j-ui \n#### 1、加载ui界面\n```\n <!-- 接口文档- knife4j-ui 注意加载顺序,必须在swagger2之下引入 -->\n <dependency>\n     <groupId>com.ws.ldy</groupId>\n     <artifactId>xj-doc-knife4j</artifactId>\n     <version>${xj-doc-knife4j.version}</version>\n </dependency>\n```\n#### 2、访问\n\n\n访问：  ip:端口/doc.html\n示例： http://xijia.plus/doc.html\n\n\n\n\n\n\n\n', 4, 282);
INSERT INTO `t_xj_admin_help` VALUES ('1320218364043415553', NULL, NULL, '2020-10-25 04:19:21', '2021-03-04 17:05:07', 0, 0, '', 0, 0, '环境切换', '本项目采用maven 来管理项目的第三方依赖包，并使用maven 进行环境配置，即不同的环境使用不同的配置文件\n\n## 核心配置\n \n在 xj-test-server 中的 pom.xml 中你会发现存在以下配置，配置了这个在idea 中的maven 面板就会出现可操作的环境选择了   \n  \n \n```base\n <!-- 多环境配置 -->\n    <profiles>\n\n        <profile>\n            <id>dev</id>\n            <!-- 环境标识，需要与配置文件的名称相对应 -->\n            <properties>\n                <profiles.active>dev</profiles.active>\n            </properties>\n            <!-- 默认环境 -->\n            <activation>\n                <activeByDefault>true</activeByDefault>\n            </activation>\n        </profile>\n\n        <profile>\n            <id>test</id>\n            <properties>\n                <profiles.active>test</profiles.active>\n            </properties>\n        </profile>\n\n        <profile>\n            <id>pro</id>\n            <properties>\n                <profiles.active>pro</profiles.active>\n            </properties>\n        </profile>\n    </profiles>\n```\n   \n直接勾选对应的环境就好了，启动和打包项目会自动读取对应的环境， 切换尽量重编译一下，防止编辑器缓存\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103223942714613-image.png)\n\n## 环境说明\n\n- dev -->  application-dev.yml  (生产环境)\n- pro -->  application-pro.yml  (正式环境)\n- test -->  application-test .yml  (本地/ 测试环境)\n\n\n\n\n', 2, 369);
INSERT INTO `t_xj_admin_help` VALUES ('1322729369042444290', NULL, NULL, '2020-11-01 02:37:11', '2021-02-24 09:48:40', 0, 0, '', 0, 0, '更新计划', '### 0.0.7 版本更新计划 \n序号  |  标题  |  内容  |  月份  | 状态\n:---:|:--:|:--:|:---:|:---:\n1 | 兮家手册文档 |  帮助文档编写 | 2020/11 | 部分完成\n2 | 操作日志 |  详情页展示 / 日志范围控制 / 调用链 / sql 执行数据和时间  | 2020/11| 完成\n3 | 数据加密 |  定义请求统一加密方式, 保证数据安全  | 2020/12 | 待定\n4 | 系统配置 |  动态配置系统的相关数据, 以及第三方接口, 测试环境, 正式环境线上直接切换  | 2020/12 | 完成\n5 |  黑 /白名单  |  控制系统访问来源权限  |  2020/12 | 完成\n6 |  权限配置优化  |  菜单/ 接口权限从角色管理处打开, 减少菜单项  |  2020/12 | 完成\n\n\n### 0.0.8 版本更新计划\n\n序号  |  标题  |  内容  |  月份    | 状态\n:---:|:--:|:--:|:---:\n1 | 菜单图标选择 |  提交菜单可选择图标 | 2021/1| 待定\n2 | 新接口默认权限 |  新编写的接口所有角色默认有权限   | 2021/1| 完成\n3 | 代码生成/ 新增 | 在线连接任意数据库, 批量代码生成, 下载生成代码，动态配置相关生成参数 |  2021/11| 完成\n4 | 支付对接 |  1、支付宝 2、微信  3、第三方支持个人支付（支付宝,微信只支持企业） | 2021/1| 完成微信\n5 | 邮件 |  邮件发送 | 2021/1| 待定\n6 | 首页面板  |  添加首页, 用于数据统计展示, 暂不统计数据, 做一个静态的排版页面  |  2020/1| 待定\n\n\n### 0.0.9 版本更新计划\n序号  |  标题  |  内容  |  月份 | 状态 |\n:---:|:--:|:--:|:---:\n1 | websocket 及时通知  |  添加socket 及时消息弹出/ 已读未读功能  |  2020/2| 待定\n2 | websocket 在线客服  |  添加及时聊天功能, 添加发送图片信息功能  |  2020/2| 待定\n\n\n### 0.1.0 版本更新计划 \n\n序号  |  标题  |  内容  |  月份 | 状态 |\n:---:|:--:|:--:|:---:\n1 | 请求日志颜色  |  失败和成功明显区分  |  2020/3  | 待定\n2 | 系统代码优化 |  优化系统层代码，让代码结构更清晰  |  2020/3  | 待定\n3 | 尝试模块化系统 |  让所有模块分离，使用jar引入使用 (尝试)  |  2020/3  | 待定\n4 | 字典管理 |  增加层级搜索  |  2020/3  | 待定\n5 | 菜单管理 |  增加层级搜索  |  2020/3  | 待定\n5 | 代码生成 | 优化编辑，时间字段, 复选字段  |  2020/3  | 待定\n6 | 二级缓存 | redis 集成( 让jvm缓存和redis 缓存可随意切换和 可直接移除redis )  |  2020/3  | 待定\n7 | qq登录 | 集成qq登录  |  2020/3  | 待定\n8 | 微信登录 | 集成微信登录  |  2020/3  | 待定\n9 | 数据备份 | 服务器数据自动备份  |  2020/3  | 待定\n\n\n### 更多计划\n序号  |  标题  |  内容  |  预计时间\n:---:|:--:|:--:|:---:\n1 | 时间工具类 LocalDateTimeUtil 优化  | 优化代码,方法命名，注释，及更多方法 | 完成\n2 | 二维码工具类 CreatrQrCode 优化  | 优化生成的图片样式 | 完成\n3 | 日志优化  | 记录异常信息| 完成 \n\n', 5, 220);
INSERT INTO `t_xj_admin_help` VALUES ('1323647578092400642', NULL, NULL, '2020-11-03 23:25:49', '2021-02-04 14:23:09', 0, 0, '', 2, 0, 'banner ', ' ## banner 作用\nbanner  的主要作用网站首页的导航图，app 的轮播图等作用，点击图片可跳转对的页面或外部链接，到达快速定位目标也和  \n广告推广作用\n\n如： \n\n淘宝首页的广告图\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103232529470004-image.png)\n\n\n## banner 添加\n\n一个简单的配置页，添加数据即可\n- 位置表示本条数据在哪里展示， 前端通过位置查询接口，即可获得对应位置的数据集\n- 排序表示数据集的顺序，越小越前  \n\n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201103230919174568-image.png)\n\n\n## 跳转形式\n\n跳转分为三种形式\n\n### - 1、不跳转 \n 点击图片没有任何费用\n\n### - 2、内链 \n 点击图片跳到当前系统的指定页面，如果带查询条件，需要在这里配置url 参数,  如：跳转到指定的商品： ```http://xijia.plus/commodity/find?id=???  ``` \n如果要求比较高，可以这些效果代码，把 ``` http://xijia.plus/commodity/find?id= ```  配置在字典中进行选择， 在拉取商品数据选择商品后面自动拼接 请求的路由\n\n### - 3、外链\n作用于联动推广，广告等，直接配置第三方完成 url 路径即可，如 http://www.baidu.com \n', 0, 133);
INSERT INTO `t_xj_admin_help` VALUES ('1330158825788940289', NULL, NULL, '2020-11-21 22:39:11', '2021-02-18 18:25:34', 0, 0, '', 2, 0, '系统日志', '## 功能描叙\n为了更好的跟踪系统所有情况，以及前后端对接开发，本系统添加了对该系统操作日志的详细记录\n\n如下图：  \n\n![图片未命名](http://xijia.plus/oss/file/image/markdown/20201121224548842737-image.png)\n\n## 其他说明\n\n- 实现代码位于 ->   src/main/java/com/ws/ldy/config/aspect/SysAspect.java\n- 已进行 jmeter 压测，无需担心并发时数据串号\n- 使用多线程+ future 技术进行处理, 日志记录异步执行，业务逻辑代码执行不等待, 无需担心响应慢', 2, 108);
INSERT INTO `t_xj_admin_help` VALUES ('1331233814139166721', NULL, NULL, '2020-11-24 21:50:49', '2020-11-24 21:51:03', 1, 0, '', 0, 0, '541454165', 'lala\n### 652565265\n\n#### 652965259865', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1331917352526245889', NULL, NULL, '2020-11-26 19:06:56', '2020-11-26 19:08:47', 1, 0, '', 0, 0, '1111', '', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1331917371119595523', NULL, NULL, '2020-11-26 19:07:00', '2020-11-26 19:08:43', 1, 0, '', 0, 0, '1111', '', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1331917418511036418', NULL, NULL, '2020-11-26 19:07:11', '2020-11-26 19:08:41', 1, 0, '', 0, 0, '1111', '', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1331917477193543682', NULL, NULL, '2020-11-26 19:07:25', '2020-11-26 19:08:39', 1, 0, '', 0, 0, '1111', '', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1331917733163528194', NULL, NULL, '2020-11-26 19:08:26', '2020-11-26 19:08:36', 1, 0, '', 0, 0, '452412', '', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1331923517297401858', NULL, NULL, '2020-11-26 19:31:25', '2020-11-26 19:33:48', 1, 0, '', 0, 0, '啦啦', '11110000025361', 0, 2);
INSERT INTO `t_xj_admin_help` VALUES ('1338331275932471297', NULL, NULL, '2020-12-14 11:53:35', '2021-02-26 11:50:45', 0, 0, '', 2, 0, '登录 Token 设置与获取', '## 获取登录用户信息\n\n- Token核心类：JwtUtil (自行修改加密key 和 主题， 注意主题和加密key切勿一致, 否则可能导致信息暴露, 主题可以通过base解密获取)\n- 在当前系统中,只要访问的接口需要登录权限来访问，那么在该接口中即可直接获取当前登录用户信息\n- 在当前系统中,每次有效请求将重新刷新返回的token，以此来保证用户在使用过程中不会出现token过期问题\n- 所有用户信息保存在token并存放在客户端，以此来减轻服务器的压力\n### 1、获取token\n\n```\nrequest.getHeader(JwtUtil.TOKEN);\n```\n\n### 2、获取用户信息示例\n```\n// 数据为第4步录入数据\nJwtUser jwtUser = JwtUtil.getJwtUser(request);\n```\n\n### 3、快速获取用户ID示例\n```\nJwtUtil.getJwtUser(request).getUserId();\n```\n\n### 4、登录-并生成Token\n\n- 当用户登录成功后, 保存用户信息到Token中并生成Jwt的Token参数\n- 注意在枚举 AuthorityType 中添加对应登录端, 该字段由字典管理, 可在字典管理中添加，并生成 Enums.java 类的数据\n- 不论登录端为管理端还是用户端, JwtUser 中统一保存用户信息\n-  JwtUser参数 -->  用户ID-id\n-  JwtUser参数 -->  用户账号-username\n-  JwtUser参数 -->  用户昵称-fullName \n-  JwtUser参数 -->  用户登录端-type\n-  JwtUser参数 -->  用户头像-head\n-  JwtUser参数 -->  用户电话-phone\nps：(若不想保存过多信息到JWT,可只保存id和type, 如参数不满足使用, 也可以在JwtUser中自行进行参数扩展,如微信openId)\n\n```\n // 登录成功\n JwtUser jwtUser = new JwtUser();\n jwtUser.setUserId(user.getId());\n jwtUser.setUsername(user.getName());\n jwtUser.setFullName(user.getName());\n jwtUser.setType(JwtUtil.userType[1]); //登录端 如： 0=管理端 1=用户端\n jwtUser.setHead(user.getHeadPic());\n jwtUser.setPhone(user.getPhone());\n // 生成Token并放入响应头\n String jwtToken = JwtUtil.createToken(jwtUser, response);\n```\n\n- 管理端登录额外权限参数(已内置,用户登录,可无视此内容,主要为保存管理端登录用户的接口权限数据) \n- 管理端参数说明：接口权限和权限数据版本号\n- 管理端参数作用：主要用于当权限发生改变时,直接刷新登录中的用户token权限信息\n\n```\njwtUser.setAuthList(authList);\njwtUser.setAuthVersion(BaseConstant.Cache.AUTH_VERSION);\n```', 0, 49);
INSERT INTO `t_xj_admin_help` VALUES ('1340630267240439810', NULL, NULL, '2020-12-20 20:08:58', '2020-12-20 20:13:01', 1, 0, '', 2, 0, '幂等 (防重)', '在项目开发中，经常会遇到重复提交问题，比如打开添加页，最后快速点击两次添加按钮，那么会向后台发起两次请求，数据库会出现两条相同的数据，为了解决这个问题，当前项目决定编写了一套幂等处理方案，使用注解快速处理该问题\n\n## 如何使用\n### 1、获取幂等TOKEN\n\n- 获取幂等TOKEN方式 1、向后台 /api/idempotent/findIdempotentToken 接口发起请求 (响应Header中会自动增加 IDEMPOTENT_TOKEN )\n\n- 获取幂等TOKEN方式 2、在上一步的操作请求接口中获取幂等TOKEN (增加 @XjApiIdempotent 注解即可) (响应Header中会自动增加 IDEMPOTENT_TOKEN )\n\n### 2、访问幂等接口\n- 3、需要防重的接口，在接口上添加  @XjIsApiIdempotent 注解即可, 这样前端必须先获取幂等TOKEN传入,否则直接返回{\"9995\",\"重复操作\"}\n\n\n', 0, 3);
INSERT INTO `t_xj_admin_help` VALUES ('1340631093145063426', NULL, NULL, '2020-12-20 20:12:15', '2021-02-26 11:37:27', 0, 0, '', 2, 0, '幂等 (防重)', '在项目开发中，经常会遇到重复提交问题，比如打开添加页，最后快速点击两次添加按钮，那么会向后台发起两次请求，数据库会出现两条相同的数据，为了解决这个问题，当前项目决定编写了一套幂等处理方案，使用注解快速处理该问题\n\n## 如何使用\n- 访问幂等接口前获取 IDEMPOTENT_TOKEN  ，访问幂等接口时传递 IDEMPOTENT_TOKEN\n获取幂等 @ApiIdempotent     \n验证幂等 @ApiIdempotentAuth \n \n### 1、获取幂等TOKEN\n\n- ~~获取幂等TOKEN方式 1、向后台 /api/idempotent/findIdempotentToken 接口发起请求 (响应Header中会自动增加 IDEMPOTENT_TOKEN )~~ - **已移除**\n\n- 获取幂等TOKEN方式 2、在上一步的操作请求接口中获取幂等TOKEN (增加 @ApiIdempotent 注解即可) (响应Header中会自动增加 IDEMPOTENT_TOKEN )\n\n### 2、访问幂等接口\n- 3、需要防重的接口，在接口上添加  @ApiIdempotentAuth 注解即可, 这样前端必须先获取幂等TOKEN传入,否则直接返回{\"9995\",\"重复操作\"}\n\n\n', 0, 35);
INSERT INTO `t_xj_admin_help` VALUES ('1354695947258540034', NULL, NULL, '2021-01-28 15:40:57', '2021-02-24 15:47:20', 0, 0, '', 3, 0, '聚合支付', '\n\n该文档仅适用于spring-boot-plus2 架构模板\n[微信支付文档](https://pay.weixin.qq.com/wiki/doc/apiv3/index.shtml \"微信支付文档\")\n[微信公众平台](https://mp.weixin.qq.com/ \"微信公众平台\")\n[微信商户平台](https://pay.weixin.qq.com/index.php/apply/applyment_home/guide_normal \"微信商户平台\")\n\n## 一、微信支付\n\n### 使用\n\n#### 1、添加maven依赖\n\n```\n <!-- 聚合支付(微信/支付宝) -->\n<dependency>\n      <groupId>com.ws.ldy</groupId>\n      <artifactId>xj-base-pay</artifactId>\n      <version>${xj-base-pay.version}</version>\n</dependency>\n```\n\n#### 2、添加yml配置\n```\n## 微信api\nwx:\n  # 支付配置\n  pay:\n    appId: \"\"     # 必填\n    mchId: \"\"     # 必填\n    mchKey: \"\"    # 必填\n    subAppId: \"\"\n    subMchId: \"\"\n    keyPath: \"classpath:cert/apiclient_cert.p12\"\n    # 额外参数配置\n    notifyBase: \"http://api.savingmypets.com\"                     # 额外参数：回调域名\n    orderNotifyUrl: \"/api/client/pets/petsOrder/wx/order/notify\"  # 额外参数：支付回调接口\n```\n\n#### 3、微信支付 bend\n```\n    @Autowired\n    private WxPayServiceImpl wxPayService;\n```\n\n#### 4、微信证书文件(仅针对需要单向证书或双向证书的接口)\n放入目录： xj-server/xj-pay-server/src/main/resources/cert \n文件1： apiclient_cert.p12 \n文件2： apiclient_cert.pem \n文件3： apiclient_key.pem \n\n### 1、下单\n#### 1.1、发起支付\n自动记录支付请求信息到 t_xj_pay_record 表中\n注意：商品平台需开通对应的支付方式 和 ip白名单放行\nopenId = 公众号可通过网页授权获取(需接助后端接口)，小程序可使用小程序内部接口获取(需接助后端接口)，其他自行研究\n```\n PayOrderDTO payDto = new PayOrderDTO();\n payDto.setPlatformFee(capitalFindFee.getPlatformFee());  // 平台手续费(当前系统收取)\n payDto.setChannelFee(capitalFindFee.getWxChannelFee());  // 渠道手续费(微信收取)\n payDto.setMoneyTotal(order.getMoney());                  // 订单金额\n payDto.setOrderNo(order.getOrderNo());                   // 订单号\n payDto.setBody(petsMonthFee.getMonthDesc());             // 商品描叙\n payDto.setWxOpenid(wxOrderId);                           // 微信openId\n payDto.setWxTradeType(\"JSAPI\");                          // 支付方式-默认JSAPI\n PayOrderResultVO orderVO = wxPayService.createOrder(payDto);\n```\n#### 1.2、支付回调(直接回调到业务层)\n自动记录支付回调信息到 t_xj_pay_record 表中，并进行回调判重复等操作\n注意：回调接口需要放行,可直接进行外部调用\n\ncontroller 层\n```\n@RequestMapping(value = \"/wx/order/notify\", method = RequestMethod.POST)\n@ApiOperation(value = \"微信支付回调\", notes = \"\")\npublic String orderCallback(@RequestBody String xmlData) {\n        return baseService.orderCallback(xmlData);\n }\n```\nservice 层\n```\npublic String orderCallback(String xmlData) {\n        log.info(\"\\r\\n 支付回调参数：{}\" + xmlData);\n        // 解析回调参数\n	    R<PayRecordVO> payRecordVOR = wxPayService.orderCallback(xmlData);\n		// 处理业务\n        if (payRecordVOR.getCode().equals(RType.SYS_SUCCESS.getValue())) {\n            // 支付成功\n            // this.paySuccess(payRecordVOR.getData());       //成功业务逻辑\n            return WxPayNotifyResponse.success(\"ok\");\n        } else {\n            // 支付失败\n            if (payRecordVOR.getData() == null) {\n                // 重复回调\n                return WxPayNotifyResponse.fail(\"fail\");\n            }\n            this.payFail(payRecordVOR.getData().getOrderNo()); // 失败业务处理\n            return WxPayNotifyResponse.fail(\"fail\");\n        }\n }\n```\n#### 1.3、支付成功记录流水 (已提供流水查询接口, 只需记录即可)\n参数详见方法注释\n```\n// 流水\n@Autowired\nprivate PayWalletFlowService payWalletFlowService;\n\n// 添加用户/商家流水\npayWalletFlowService.addUserWalletFlow(order.getUserId(), order.getOrderNo(), order.getMoney(), new BigDecimal(\"0\"),\n          Enums.Pet.WalletType.WALLET_TYPE_2, Enums.Pay.PayBusiness.PAY_BUSINESS_1\n );\n		\n// 添加平台总资产记录\npayWalletFlowService.addPlatformWalletFlow(order.getOrderNo(), order.getMoney(), moneyAfter,\n           Enums.Pet.WalletType.WALLET_TYPE_1, Enums.Pay.PayBusiness.PAY_BUSINESS_1\n );\n```\n\n### 2、企业打款 (需要配置双向证书)\n注意：请先处理完业务在进行打款(打款失败回滚)，打款一但成功无法回退\n同支付1.3 记录用户流水, 在打款前记录\n```\n// 发起支付（商家打款）\n String wxOrderId = petsUserService.findWxOrderId(userId);\n EntPayDTO dto = new EntPayDTO();\n dto.setOpenid(wxOrderId);\n dto.setAmount(paidInAmount);\n dto.setDescription(\"理赔\");\n dto.setOrderNo(orderNo);\n // dto.setCheckName();\n // dto.setReUserName();\n R<EntPayResultVO> vo = payService.entPay(dto);\n```\n\n## 二、支付宝支付(暂未集成)', 0, 37);
INSERT INTO `t_xj_admin_help` VALUES ('1355079883042070530', NULL, NULL, '2021-01-29 17:06:34', '2021-01-29 17:08:51', 1, 0, '', 0, 0, '微信mini-app (小程序)', '待定', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1355080395141423106', NULL, NULL, '2021-01-29 17:08:36', '2021-02-19 16:19:35', 0, 0, '', 3, 0, '微信mini-app (小程序)', '\n\n### 导入子模块\n```\n     <!--第三方服务- 微信小程序相关-->\n        <dependency>\n            <groupId>com.ws.ldy</groupId>\n            <artifactId>xj-api-wx-app</artifactId>\n            <version>${xj-api-wx-app.version}</version>\n        </dependency>\n```\n添加yml配置\n```bash\n# 小程序\nwx:\n  app:\n    appId:      # 小程序appId (微信公众平台获取)\n    secret:     # 小程序secret(微信公众平台获取)\n```\n\n### 一、登录(获取openId)\n\n引入jar自动获取接口，小程序调用即可， 参数 code 小程序可直接获取\n\n ![/20210129173152909308-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210129173152909308-image.png)\n\n\n\n### 二、发送订阅消息\n\n#### 1、创建模块\n微信公众平台 -> 小程序 -> 功能 -> 订阅消息\n ![/20210129171902018207-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210129171902018207-image.png)\n\n\n#### 2、定义模块示例\n\n业务模块中创建 订阅消息模块工具类 如: WxAppSubscribeSendUtil\n\n示例如下：\n```bash\n@Component\npublic class WxAppSubscribeSendUtil {\n\n    @Autowired\n    private WxAppSubscribeUtil wxAppSubscribeUtil;\n\n\n    /**\n     * 订单支付提醒\n     * @param userName    // 付款人\n     * @param productName // 产品名称\n     * @param amount      // 金额\n     * @param time        // 日期\n     * @return\n     */\n    public R<String> payMsg(String openId, String userName, String productName, BigDecimal amount, String time) {\n        // 模板Id\n        String templateId = \"-Sc4vvyw2ontip0wPatcDYOfzEqaUqJUrOJvsPJmy3U\";\n        // 模板参数\n        Map<String, WxAppMsg> sendMag = new HashMap<String, WxAppMsg>();\n        sendMag.put(\"thing1\", new WxAppMsg(userName));\n        sendMag.put(\"thing2\", new WxAppMsg(productName));\n        sendMag.put(\"amount3\", new WxAppMsg(amount.toString()));\n        sendMag.put(\"time4\", new WxAppMsg(time));\n        // 发送\n        R<String> resData = wxAppSubscribeUtil.sendMsg(openId, templateId, sendMag);\n        if (!resData.getCode().equals(RType.SYS_SUCCESS.getValue())) {\n            return R.error(resData.getCode(), resData.getMsg());\n        }\n        return R.success(resData.getData());\n    }\n}\n```\n\n\n#### 3、发送消息\n\n引用模块工具类/ 传递指定参数发送即可\n\n```bash\n@Autowired\nprivate WxAppSubscribeSendUtil wxAppSubscribeSendUtil;\n//使用\nwxAppSubscribeSendUtil.payMsg（a,b,c,d,e）\n```\n\n\n', 0, 21);
INSERT INTO `t_xj_admin_help` VALUES ('1356118655389261825', NULL, NULL, '2021-02-01 13:54:17', '2021-03-02 10:57:22', 0, 0, '', 3, 0, '七牛云oss', '七牛云oss控制台地址： https://portal.qiniu.com/kodo/bucket\n七牛云oss文档中心：https://developer.qiniu.com/kodo\n注册使用七牛oss说明详见：https://blog.csdn.net/qq_41463655/article/details/111720952\n \n ## 使用说明\n ### 1、引入子模块jar\n       <!-- 第三方服务- 七牛云oss -->\n       <dependency>\n           <groupId>com.ws.ldy</groupId>\n           <artifactId>xj-api-qiniu-oss</artifactId>\n           <version>${xj-api-qiniu-oss.version}</version>\n       </dependency>\n \n \n ### 2、yml 配置\n ```bash\n## 七牛云\nqiniu:\n  oss:\n    accessKey: \"\"  \n    secretKey: \"\"\n    bucket: \"\"       \n    hosts: \"\"        # 访问域名,oss绑定域名获得, 前端直传上传域名自行查阅文档获取\n    priKey: \"\"       # 可不填\n    ## 地域(华东 z0 华北 z1 华南 z2 北美 na0 东南亚 as0)\n    area: \"z2\"\n```\n### 3、后端上传\n  \n  引入子模块jar后自动后端上传接口，直接使用上传接口即可\n \n ![/20210201135359009624-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210201135359009624-image.png)\n \n \n### 4、前端直传\n调用前端直传获取 token 的接口获取上传的 token令牌\n剩下查看官方文档 JavaScript SDK 上传即可，文档直达链接：https://developer.qiniu.com/kodo/1283/javascript\n', 0, 11);
INSERT INTO `t_xj_admin_help` VALUES ('1356491516587139074', NULL, NULL, '2021-02-02 14:35:54', '2021-02-02 14:36:37', 1, 0, '', 0, 0, '百度api', '\n## 使用说明\n### 一、文字识别\n\n#### 1、创建应用\n\n在百度智能云 控制台中创建应用，勾选全部文字识别相关接口\n\n ![/20210202143428453736-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210202143428453736-image.png)\n\n\n#### 2、引入子模块\n```bash\n<!-- 百度api-->\n<dependency>\n    <groupId>com.ws.ldy</groupId>\n    <artifactId>xj-api-baidu</artifactId>\n    <version>${xj-api-baidu.version}</version>\n    <exclusions>\n        <exclusion>\n            <groupId>log4j</groupId>\n            <artifactId>log4j</artifactId>\n        </exclusion>\n        <exclusion>\n            <groupId>org.slf4j</groupId>\n            <artifactId>slf4j-simple</artifactId>\n        </exclusion>\n    </exclusions>\n</dependency>\n```\n\n#### 3、获得文字识别接口\n文字识别通用接口\n\n ![/20210202143205948497-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210202143205948497-image.png)\n', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1356491540427563010', NULL, NULL, '2021-02-02 14:36:00', '2021-02-02 14:36:35', 1, 0, '', 0, 0, '百度智能云api', '\n## 使用说明\n### 一、文字识别\n\n#### 1、创建应用\n\n在百度智能云 控制台中创建应用，勾选全部文字识别相关接口\n\n ![/20210202143428453736-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210202143428453736-image.png)\n\n\n#### 2、引入子模块\n```bash\n<!-- 百度api-->\n<dependency>\n    <groupId>com.ws.ldy</groupId>\n    <artifactId>xj-api-baidu</artifactId>\n    <version>${xj-api-baidu.version}</version>\n    <exclusions>\n        <exclusion>\n            <groupId>log4j</groupId>\n            <artifactId>log4j</artifactId>\n        </exclusion>\n        <exclusion>\n            <groupId>org.slf4j</groupId>\n            <artifactId>slf4j-simple</artifactId>\n        </exclusion>\n    </exclusions>\n</dependency>\n```\n\n#### 3、获得文字识别接口\n文字识别通用接口\n\n ![/20210202143205948497-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210202143205948497-image.png)\n', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1356491566713266177', NULL, NULL, '2021-02-02 14:36:06', '2021-03-02 10:57:22', 0, 0, '', 3, 0, '百度智能云', '文档地址：https://ai.baidu.com/ai-doc/OCR/1k3h7y3db\n控制台地址：https://console.bce.baidu.com\n## 使用说明\n\n### 引入百度子模块\n```bash\n <!-- 百度api -->\n <dependency>\n     <groupId>com.ws.ldy</groupId>\n     <artifactId>xj-api-baidu</artifactId>\n     <version>${xj-api-baidu.version}</version>\n </dependency>\n```\n### 一、文字识别\n\n\n\n\n#### 1、创建应用\n\n在百度智能云 控制台中创建应用，勾选全部文字识别相关接口\n\n ![/20210202143428453736-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210202143428453736-image.png)\n\n#### 2、添加yml配置\n控制台管理应用中查看\n\n```bash\nbaidu:\n  # 百度文字识别\n  text:\n    appId: \"23624003\"\n    apiKey: \"Y8yMV28vTw4FHuYGNbb4oyS9\"\n    secretKey: \"xTKv7EHNPrQ1LOnfOIGKjnXlOCIqIMoX\"\n```\n\n#### 3、获得文字识别接口\n文字识别通用接口 ( 引入子模块jar 自动获得)\n根据识别类型上传不同的图片进行识别操作即可\n\n ![/20210202143205948497-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210202143205948497-image.png)\n\n### 二、图片识别(待定)\n菜品/植物/地标/汽车/动物识别\n', 0, 16);
INSERT INTO `t_xj_admin_help` VALUES ('1357149665392381954', NULL, NULL, '2021-02-04 10:11:09', '2021-02-26 11:34:35', 0, 0, '', 0, 0, '默认 application.yml 配置', '\n\n### 1、默认 application.yml 配置\n```bash\nspring:\n  ## maven面板动态切换dev/pro环境\n  profiles:\n    active: \"@profiles.active@\"\n  ## 项目名\n  application:\n    name: xijia\n  ## 当遇到同样beng名字的时候，是否允许覆盖注册\n  main:\n    allow-bean-definition-overriding: true\n  ## 关闭thymeleaf缓存 开发时使用,否则没有实时画面\n  thymeleaf:\n    cache: false\n    check-template-location: true\n    enabled: true\n    mode: LEGACYHTML5\n    prefix: classpath:/templates/\n    suffix: .html\n  ## 时间格式处理\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  ## 文件上传配置\n  servlet:\n    multipart:\n      max-file-size: 1024MB      # 单个文件的最大上限\n      max-request-size: 1024MB   # 单个请求的文件总大小上限\n  ## 数据源的连接的基础配置, 当前为mybatis-plus配置方法\n  datasource:\n    dynamic:\n      druid:\n        initialSize: 5    # 初始化大小\n        minIdle: 5        # 最小值\n        maxActive: 20     # 最大值\n        maxWait: 60000    # 最大等待时间，配置获取连接等待超时，时间单位都是毫秒ms\n        timeBetweenEvictionRunsMillis: 60000  # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接\n        minEvictableIdleTimeMillis: 300000    # 配置一个连接在池中最小生存的时间\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\n        # \'wall\'用于防火墙，SpringBoot中没有log4j，我改成了 slf4j | log4j2\n        filters: stat,wall,slf4j\n        #最大PSCache连接\n        maxPoolPreparedStatementPerConnectionSize: 20\n        useGlobalDataSourceStat: true\n        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\n        connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500\n    ## druid监控平台配置\n    druid:\n      # 配置StatFilter\n      web-stat-filter:\n        # 默认为false，设置为true启动\n        enabled: true\n        url-pattern: \"/*\"\n        exclusions: \"*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*\"\n      # 配置StatViewServlet\n      stat-view-servlet:\n        # 访问地址\n        url-pattern: \"/druid/*\"   # 访问地址 ip+端口/druid\n        # allow: 127.0.0.1        # 允许那些ip, 不配置允许所有\n        login-username: admin     # 账号\n        login-password: 123456    # 密码\n        deny: 192.168.1.102       # 禁止那些ip\n        reset-enable: true        # 是否可以重置\n        enabled: true             # 启用\n\n\n# mybatis-plus相关配置\nmybatis-plus:\n  mapper-locations: classpath*:mapper/**/*.xml\n  type-enums-package: com.ws.ldy.enums.*\n  configuration:\n    # 是否开启自动驼峰命名规则映射:从数据库列名到Java属性驼峰命名的类似映射\n    map-underscore-to-camel-case: true\n    # 如果查询结果中包含空值的列，则 MyBatis 在映射的时候，不会映射这个字段\n    call-setters-on-nulls: true\n    # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用\n    # log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\n\n# swagger配置\nswagger:\n  sysAdminPackage: com.ws.ldy.modules.sys          # 系统代码接口目录\n  sysBasePackage: com.ws.ldy.modules.third         # 通用及第三方对接接口目录\n  ywAdminPackage:  com.ws.ldy.modules.yw           # 业务管理端接口目录\n  ywClientPackage: com.ws.ldy.client               # 业务用户端接口目录\n  author: wangsong                                 # 作者/负责人姓名\n  email: 1270696548@qq.com                         # 作者/负责人邮箱\n  url: https://gitee.com/wslxm/spring-boot-plus2   # 访问地址\n  version: 1.0.0                                   # 版本号\n  defaultKey: TOKEN                                # 默认请求头key\n  defaultValue: token-value                        # 默认请求头value\n  isShield: false                                  # 是否展示文档 true展示 false隐藏\n\n```\n\n### 2、默认 application-dev.yml 配置\n\n sql连接 url 后参数：\n- rewriteBatchedStatements=true mybatis批处理优化 \n- useTimezone=true&serverTimezone=GMT%2B8  使用北京时间 \n- useUnicode=true&characterEncoding=utf-8 编码\n\n```bash\n\nserver:\n  port: 9049\n\nspring:\n  autoconfigure:\n    # 为了某些版本 @SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) 无法生效\n    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure\n  datasource:\n    dynamic:\n      primary: db1\n      datasource:\n        db1:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: jdbc:mysql://xijia.plus/spring-boot-plus2?useUnicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true\n          username: root\n          password: root123456\n          type: com.alibaba.druid.pool.DruidDataSource\n\naliyun:\n  ## 个人账号,拉取此代码调试后请修改为自己的,如发现滥用,将关停\n  oss:\n    endpoint: oss-cn-shenzhen.aliyuncs.com\n    accessKeyId: LTAI4Fyfy2DTf4yUrvKqHwH4\n    accessKeySecret: Jf3gn1kSRLdh1j14F5mznnJyIBIhA8\n    bucketName: xijia-sz\n    bucket: http://xijia-sz.oss-cn-shenzhen.aliyuncs.com\n\n# 日志配置, filePath线上日志存放地址（logback-spring.xml 读取） , methods=那些请求数据需要记录保存到数据库\nlogging:\n  filePath: /usr/local/work/xijia2/logs\n  methods: POST,PUT,DELETE\n\n```\n\n', 7, 16);
INSERT INTO `t_xj_admin_help` VALUES ('1364391389198127105', NULL, NULL, '2021-02-24 09:47:11', '2021-02-24 09:48:04', 1, 0, '', 0, 0, '包路径全局修改', '修改流程\n1、快捷键：shift+f6 (选中包使用)全局替换包路径shift+f6    如: com.lplb 转为 com.lplb\n\n ![/20210224094340259305-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210224094340259305-image.png)\n\n\n2、快捷键：shift+ctrl+r (选中内容使用)全局替换模块的 groupId 以及部分代码中的包路径,  如：com.ws.ldy 转为 com.lplb\n\n ![/20210224094450672828-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210224094450672828-image.png)\n\n3、快捷键：ctrl + f9 (直接使用)编译整个项目，排查是否有不正确的引入，有自行修改\n\n ![/20210224094601307878-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210224094601307878-image.png)\n\n4、maven 重新打包在启动\n\n ![/20210224094654562115-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210224094654562115-image.png)\n\n', 0, 2);
INSERT INTO `t_xj_admin_help` VALUES ('1364391418558255106', NULL, NULL, '2021-02-24 09:47:18', '2021-02-24 09:48:40', 0, 0, '', 0, 0, '包路径全局修改', '修改流程\n1、快捷键：shift+f6 (选中包使用)全局替换包路径shift+f6    如: com.lplb 转为 com.lplb\n\n ![/20210224094340259305-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210224094340259305-image.png)\n\n\n2、快捷键：shift+ctrl+r (选中内容使用)全局替换模块的 groupId 以及部分代码中的包路径,  如：com.ws.ldy 转为 com.lplb\n\n ![/20210224094450672828-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210224094450672828-image.png)\n\n3、快捷键：ctrl + f9 (直接使用)编译整个项目，排查是否有不正确的引入，有自行修改\n\n ![/20210224094601307878-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210224094601307878-image.png)\n\n4、maven 重新打包在启动\n\n ![/20210224094654562115-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210224094654562115-image.png)\n\n', 6, 3);
INSERT INTO `t_xj_admin_help` VALUES ('1365134516666077186', NULL, NULL, '2021-02-26 11:00:06', '2021-02-26 11:00:22', 1, 0, '', 0, 0, '1', '1', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1365134521455972353', NULL, NULL, '2021-02-26 11:00:07', '2021-02-26 11:00:20', 1, 0, '', 0, 0, '1', '1', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1365135872265072642', NULL, NULL, '2021-02-26 11:05:30', '2021-02-26 11:05:41', 1, 0, '', 0, 0, '1', '1', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1365135941806632961', NULL, NULL, '2021-02-26 11:05:46', '2021-02-26 11:05:54', 1, 0, '', 0, 0, '1', '1', 0, 0);
INSERT INTO `t_xj_admin_help` VALUES ('1366579734061895681', NULL, NULL, '2021-03-02 10:42:53', '2021-03-02 10:56:57', 0, 0, '', 3, 0, 'excel 工具 poi ', '\n\n\n## Excel 工具\n\n为了方便excel的读取和导出，通过excel 相关工具，提供 ExcelUtil 工具类使用\n\n### 引入jar\n\n```bash\n <!-- poi -->\n <dependency>\n     <groupId>com.ws.ldy</groupId>\n     <artifactId>xj-util-poi</artifactId>\n     <version>${xj-util-poi.version}</version>\n </dependency>\n```\n\n### 一、使用工具 ExcelUtil 导出数据表\n\n#### 1、定义excel 的 VO 类 \n\n示例如下\n- \n- 需借助swagger2 注解\n- 需设置 notes 字段描叙 或 value 字段描叙\n\n\n```bash\n@Data\n@ToString(callSuper = true)\n@ApiModel(value = \"ExportOrderExcelVO 对象\", description = \"订单导出\")\npublic class ExportOrderExcelVO {\n\n    @ApiModelProperty(notes = \"用户编号\" ,position = 0)\n    private String userNo;\n\n    @ApiModelProperty(notes = \"用户名称\" ,position = 0)\n    private String username;\n\n    @ApiModelProperty(value = \"用户电话\" ,position = 0)\n    private String userPhone;\n}\n\n```\n\n#### 2、生成excel 并下载\n\n- 传递 response是为了下载使用 \n```bash\n List<ExportOrderExcelVO> excelVOS = new ArrayList<>();\n \n //\n // 省略往 excelVOS 放入数据, 也可以从数据库直接查询数据到 excelVOS\n //\n \n // 开始导出(数据, excel标题, 列宽, response)\n ExcelUtil.exportExcelDownload(excelVOS, \"订单信息\", 22, response);\n```\n\n#### 3、导出结果展示\n ![/20210302105236352291-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210302105236352291-image.png)\n \n### 二、导入excel\n\n\n调用方法:  ExcelUtil.readExcle(MultipartFile file);  \n文件上传获得\n\n\n导入excel 格式如下图:\n\n ![/20210302105305465355-image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/markdown/20210302105305465355-image.png)\n\n- 第一行为对应实体类或数据库的字段名称\n- 第二行开始为 对象数据, List<对象> 的每一个对象数据\n- 返回 List<Map<String, String>> 数据，可直接定义对象进行 json 转换为` List<Map<String, String>> 转为 List<对象>`\n\n', 0, 2);

-- ----------------------------
-- Table structure for t_xj_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_log`;
CREATE TABLE `t_xj_admin_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求人',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求人Id (根据端来区分)',
  `type` int(1) DEFAULT NULL COMMENT '请求终端(字典code, 如 0-管理端 1-用户端 -1-其他)',
  `referer` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求来源',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求url',
  `uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求uri',
  `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户真实Ip',
  `host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户主机名',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式(post-get)',
  `server_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务器地址',
  `port` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务器端口',
  `package_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求包',
  `class_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求类',
  `class_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求类--swagger注释',
  `method_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方法--swagger注释',
  `request_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求数据',
  `response_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '返回数据',
  `state` int(1) DEFAULT 0 COMMENT '1-请求成功 0-请求失败',
  `execute_time` bigint(20) DEFAULT NULL COMMENT '程序响应总耗时',
  `business_time` bigint(20) DEFAULT NULL COMMENT '业务执行总耗时',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--请求记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_xj_admin_log
-- ----------------------------
INSERT INTO `t_xj_admin_log` VALUES ('1367725255036071938', NULL, NULL, '2021-03-05 14:34:46', '2021-03-05 14:34:46', 0, 0, '╥﹏╥', '0', 0, 'http://xijia.plus/', 'http://xijia.plus/api/admin/adminUser/login', '/api/admin/adminUser/login', '222.211.237.34', '127.0.0.1', 'POST', 'xijia.plus', '56626', 'com.ws.ldy.modules.sys.admin.controller', 'com.ws.ldy.modules.sys.admin.controller.AdminUserController', 'base--用户管理', '登录', '[\"wangsong\",\"527w10n8c\"]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 85, 85);
INSERT INTO `t_xj_admin_log` VALUES ('1367725293392982018', NULL, NULL, '2021-03-05 14:34:55', '2021-03-05 14:34:55', 0, 0, '兮家小二', '1', 0, 'http://xijia.plus/', 'http://xijia.plus/api/admin/adminUser/login', '/api/admin/adminUser/login', '222.211.237.34', '127.0.0.1', 'POST', 'xijia.plus', '56642', 'com.ws.ldy.modules.sys.admin.controller', 'com.ws.ldy.modules.sys.admin.controller.AdminUserController', 'base--用户管理', '登录', '[\"wangsong\",\"527w10n8c\"]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 32, 32);
INSERT INTO `t_xj_admin_log` VALUES ('1367725346509647874', NULL, NULL, '2021-03-05 14:35:08', '2021-03-05 14:35:08', 0, 0, '兮家小二', '1', 0, 'http://xijia.plus/', 'http://xijia.plus/api/admin/adminUser/login', '/api/admin/adminUser/login', '222.211.237.34', '127.0.0.1', 'POST', 'xijia.plus', '56670', 'com.ws.ldy.modules.sys.admin.controller', 'com.ws.ldy.modules.sys.admin.controller.AdminUserController', 'base--用户管理', '登录', '[\"10000\",\"10000\"]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 29, 29);
INSERT INTO `t_xj_admin_log` VALUES ('1367725815961317378', NULL, NULL, '2021-03-05 14:37:00', '2021-03-05 14:37:00', 0, 0, '系统体验用户', '20', 0, 'http://xijia.plus/login', 'http://xijia.plus/api/admin/adminUser/login', '/api/admin/adminUser/login', '222.211.237.34', '127.0.0.1', 'POST', 'xijia.plus', '56706', 'com.ws.ldy.modules.sys.admin.controller', 'com.ws.ldy.modules.sys.admin.controller.AdminUserController', 'base--用户管理', '登录', '[\"wangsong\",\"527w10n8c\"]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 61, 61);

-- ----------------------------
-- Table structure for t_xj_admin_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_msg`;
CREATE TABLE `t_xj_admin_msg`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息接收人',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容( 根据消息类型区分数据-str || json)',
  `user_type` int(1) NOT NULL COMMENT '通知终端: 1-用户端信息 2-管理端消息',
  `msg_type` int(11) NOT NULL COMMENT '消息类型:  1-系统通知  2-订单业务通知 ',
  `is_read` int(1) NOT NULL DEFAULT 0 COMMENT '是否已读(0-未读 1-已读)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--消息通知' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_xj_admin_msg
-- ----------------------------
INSERT INTO `t_xj_admin_msg` VALUES ('1', NULL, NULL, '2021-02-05 17:07:20', '2021-02-06 09:59:10', 0, 0, '1', '系统通知1', 0, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('2', NULL, NULL, '2021-02-05 17:41:38', '2021-02-06 09:59:06', 0, 0, '1', '系统通知2', 0, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('3', NULL, NULL, '2021-02-05 17:42:44', '2021-02-06 09:59:12', 0, 0, '1', '系统通知3', 0, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('4', NULL, NULL, '2021-02-06 09:59:01', '2021-02-06 10:00:05', 0, 0, '1', '系统通知4', 0, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('5', NULL, NULL, '2021-02-06 09:59:21', '2021-02-06 10:00:06', 0, 0, '1', '系统通知5', 0, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('6', NULL, NULL, '2021-02-06 09:59:29', '2021-02-06 10:00:07', 0, 0, '1', '系统通知6', 0, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('7', NULL, NULL, '2021-02-06 10:00:37', '2021-02-06 10:00:54', 0, 0, '1', '系统通知7', 0, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('8', NULL, NULL, '2021-02-06 10:00:47', '2021-02-06 10:00:56', 0, 0, '1', '系统通知8', 0, 0, 0);

-- ----------------------------
-- Table structure for t_xj_pay_record
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_pay_record`;
CREATE TABLE `t_xj_pay_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `trade_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易号 ( 发生第三方交易时生成)',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号 ( 对应各订单表)',
  `platform_fee` decimal(10, 2) DEFAULT NULL COMMENT '平台手续费(元)',
  `channel_fee` decimal(10, 2) DEFAULT NULL COMMENT '第三方手续费(元), 为0时,手续费累加在 platform_fee',
  `money_total` decimal(10, 2) DEFAULT NULL COMMENT '交易总金额( 元)',
  `money_surplus` decimal(10, 2) DEFAULT NULL COMMENT '剩余金额( 如存在子商户, 则为子商户实际收入)',
  `pay_state` int(1) DEFAULT NULL COMMENT '交易状态( 0-已发起 1-回调成功(临时状态) 2-交易失败 3-交易成功 4-订单异常 )',
  `pay_channel` int(1) DEFAULT NULL COMMENT '支付渠道( 字典code 如1-支付宝 2-微信 3-银行卡 等)',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付类型( 字典code 如 1-支付 2-充值 3-退款 等)',
  `business_type` int(1) DEFAULT NULL COMMENT '业务类型( 字典code -根据系统业务定制 如  1-用户支付  2-平台打款 等)',
  `business_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务描叙( 追踪具体业务)',
  `request_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求数据',
  `response_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '响应数据',
  `callback_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '回调数据',
  `error_remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '支付失败备注(下单异常信息,失败异常信息)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方支付记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_xj_pay_wallet_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_pay_wallet_flow`;
CREATE TABLE `t_xj_pay_wallet_flow`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '账号id | 用户id或商家id  |  平台总账账号=0',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `money_after` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '剩余金额 (交易后金额)',
  `money` decimal(10, 2) NOT NULL COMMENT '金额(正数)',
  `wallet_type` int(1) NOT NULL COMMENT '流水类型(1-收入 2-支出)',
  `business_type` int(1) NOT NULL COMMENT '业务类型( 字段code)',
  `business_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务描叙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单/流水/支付流水表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
