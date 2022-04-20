/*
 Navicat Premium Data Transfer

 Source Server         : spring-boot-plus2
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-8vbkpoec225c821d8vo.mysql.zhangbei.rds.aliyuncs.com:3306
 Source Schema         : spring-boot-plus2

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 20/04/2022 16:56:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_authority`;
CREATE TABLE `t_admin_authority`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '权限类Id(方法与类/层级关系展示)',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式(GET/POST/PUT/DELETE)',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限url',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限备注信息',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  `type` int(1) NOT NULL COMMENT '终端(字典code, 如 0-管理端 1-用户端 更多待定)',
  `state` int(1) NOT NULL COMMENT '授权状态(字典code   -1-表示类 0-无需登录 1-需登录 2-需登录+授权 )',
  `is_sign` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要验签(不受限于登录授权)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--权限接口' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES ('590081231815839745', NULL, NULL, '2021-09-09 01:39:00', '2021-09-09 01:39:00', 0, 615, '0', '', '/api/admin/generate', 'base-gc--代码生成', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590082183939624962', NULL, NULL, '2021-09-09 01:42:47', '2021-10-01 06:22:16', 0, 614, '590081231815839745', 'GET', '/api/admin/generate/getPath', '代码生成路径', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624963', NULL, NULL, '2021-09-09 01:42:47', '2021-10-01 06:22:16', 0, 614, '590081231815839745', 'POST', '/api/admin/generate/generateCode', '生成代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624964', NULL, NULL, '2021-09-09 01:42:47', '2021-10-01 06:22:15', 0, 614, '590081231815839745', 'POST', '/api/admin/generate/preview', '生成预览代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518954377221', NULL, NULL, '2021-09-09 22:27:52', '2021-09-09 22:27:52', 0, 581, '0', '', '/api/admin/xj/config', 'base-plus--全局配置', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518958571520', NULL, NULL, '2021-09-09 22:27:52', '2021-10-01 06:22:12', 0, 581, '590395518954377221', 'GET', '/api/admin/xj/config/list', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571521', NULL, NULL, '2021-09-09 22:27:52', '2021-10-01 06:22:14', 0, 581, '590395518954377221', 'POST', '/api/admin/xj/config', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571522', NULL, NULL, '2021-09-09 22:27:52', '2021-10-01 06:22:13', 0, 581, '590395518954377221', 'DELETE', '/api/admin/xj/config/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571524', NULL, NULL, '2021-09-09 22:27:52', '2021-10-01 06:22:12', 0, 581, '590395518954377221', 'PUT', '/api/admin/xj/config/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571526', NULL, NULL, '2021-09-09 22:27:52', '2021-09-09 22:27:52', 0, 581, '0', '', '/api/admin/xj/log', 'base-plus--操作记录', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765825', NULL, NULL, '2021-09-09 22:27:52', '2021-10-01 06:21:57', 0, 581, '590395518958571526', 'GET', '/api/admin/xj/log/list', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518962765828', NULL, NULL, '2021-09-09 22:27:52', '2021-09-09 22:27:52', 0, 581, '0', '', '/api/admin/xj/msg', 'base-plus--消息通知', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765829', NULL, NULL, '2021-09-09 22:27:52', '2021-10-01 06:22:09', 0, 581, '590395518962765828', 'POST', '/api/admin/xj/msg', '添加/发送消息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518962765830', NULL, NULL, '2021-09-09 22:27:52', '2021-10-01 06:22:10', 0, 581, '590395518962765828', 'GET', '/api/admin/xj/msg/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590398100552683520', NULL, NULL, '2021-09-09 22:38:08', '2021-10-01 06:22:09', 0, 580, '590395518962765828', 'PUT', '/api/admin/xj/msg/{id}/read', '消息修改为已读', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590417006180831232', NULL, NULL, '2021-09-09 23:53:15', '2021-10-01 06:22:10', 0, 571, '590395518962765828', 'GET', '/api/admin/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862656', NULL, NULL, '2021-09-10 01:51:37', '2021-09-10 01:51:37', 0, 562, '0', '', '/api/admin/authority', 'base--URL权限管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590446794257862657', NULL, NULL, '2021-09-10 01:51:37', '2022-02-23 23:14:45', 0, 562, '590446794257862656', 'GET', '/api/admin/authority/list', '查询所有-接口管理', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862658', NULL, NULL, '2021-09-10 01:51:37', '2021-11-27 02:17:42', 0, 562, '590446794257862656', 'PUT', '/api/admin/authority/{id}', 'ID编辑', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862660', NULL, NULL, '2021-09-10 01:51:37', '2022-01-25 22:43:23', 0, 562, '590446794257862656', 'PUT', '/api/admin/authority/refreshAuthority', '扫描权限', 0, 0, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('590454633059717120', NULL, NULL, '2021-09-10 02:22:46', '2021-09-10 02:22:46', 0, 559, '0', '', '/api/admin/dictionary', 'base--字典管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590454633059717121', NULL, NULL, '2021-09-10 02:22:46', '2021-10-01 06:21:40', 0, 559, '590454633059717120', 'GET', '/api/admin/dictionary/list', '列表查询 (默认返回Tree数据,可指定Tree或List)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717122', NULL, NULL, '2021-09-10 02:22:46', '2021-10-01 06:21:43', 0, 559, '590454633059717120', 'POST', '/api/admin/dictionary', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911424', NULL, NULL, '2021-09-10 02:22:46', '2021-10-01 06:21:41', 0, 559, '590454633059717120', 'DELETE', '/api/admin/dictionary/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911425', NULL, NULL, '2021-09-10 02:22:46', '2021-10-01 06:23:06', 0, 559, '590454633059717120', 'GET', '/api/admin/dictionary/findCodeGroup', '查询所有-code分组', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911426', NULL, NULL, '2021-09-10 02:22:46', '2021-10-01 06:21:39', 0, 559, '590454633059717120', 'PUT', '/api/admin/dictionary/{id}', '编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911427', NULL, NULL, '2021-09-10 02:22:46', '2021-10-01 06:21:44', 0, 559, '590454633059717120', 'GET', '/api/admin/dictionary/generateEnum', '生成枚举', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911429', NULL, NULL, '2021-09-10 02:22:46', '2021-10-01 06:21:40', 0, 559, '590454633059717120', 'GET', '/api/admin/dictionary/list/category', '获取类别(级联数据)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452416', NULL, NULL, '2021-09-10 03:57:14', '2021-09-10 03:57:14', 0, 554, '0', '', '/api/admin/menu', 'base--菜单管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452417', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:58', 0, 554, '590478406475452416', 'GET', '/api/admin/menu/list', '列表查询(不支持分页)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452418', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:58', 0, 554, '590478406475452416', 'POST', '/api/admin/menu', '菜单添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452419', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:59', 0, 554, '590478406475452416', 'PUT', '/api/admin/menu/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452421', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:59', 0, 554, '590478406475452416', 'GET', '/api/admin/menu/findTree', '左导航菜单', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452423', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:57', 0, 554, '590478406475452416', 'DELETE', '/api/admin/menu/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452424', NULL, NULL, '2021-09-10 03:57:14', '2021-09-10 03:57:14', 0, 554, '0', '', '/api/admin/role', 'base--角色管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452425', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:47', 0, 554, '590478406475452424', 'POST', '/api/admin/role', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646720', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:46', 0, 554, '590478406475452424', 'GET', '/api/admin/role/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646721', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:45', 0, 554, '590478406475452424', 'PUT', '/api/admin/role/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646722', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:46', 0, 554, '590478406475452424', 'PUT', '/api/admin/role/updRoleAuth', '角色的URL权限分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646723', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:48', 0, 554, '590478406475452424', 'PUT', '/api/admin/role/updRoleAuthAll', '所有角色拥有所有权限', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646724', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:50', 0, 554, '590478406475452424', 'PUT', '/api/admin/role/updRoleMenu', '角色的菜单分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646725', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:48', 0, 554, '590478406475452424', 'PUT', '/api/admin/role/updUserRole', '用户的角色分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646727', NULL, NULL, '2021-09-10 03:57:14', '2021-10-01 06:21:47', 0, 554, '590478406475452424', 'DELETE', '/api/admin/role/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('592136133727621120', NULL, NULL, '2021-09-14 17:44:28', '2021-10-01 06:22:08', 0, 496, '590395518962765828', 'GET', '/api/admin/xj/msg/findAllNum', '查询全部/已读/未读数量(当前登录用户)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761932664832', NULL, NULL, '2021-09-21 23:17:51', '2021-09-21 23:17:51', 0, 455, '0', '', '/api/client/dictionary', 'yh--base--字典管理', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664833', NULL, NULL, '2021-09-21 23:17:51', '2021-09-21 23:17:51', 0, 455, '594756761932664832', 'GET', '/api/client/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664834', NULL, NULL, '2021-09-21 23:17:51', '2022-01-03 02:43:01', 0, 455, '594756761932664832', 'GET', '/api/client/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664835', NULL, NULL, '2021-09-21 23:17:51', '2021-09-21 23:17:51', 0, 455, '0', '', '/api/client/xj/banner', 'yh--base-plus--banner', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442057', NULL, NULL, '2021-09-21 23:17:51', '2021-09-21 23:17:51', 0, 455, '0', '', '/api/admin/datasource', 'base-gc--代码生成--数据源维护', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442058', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:21:55', 0, 455, '594756761949442057', 'GET', '/api/admin/datasource/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442059', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:21:54', 0, 455, '594756761949442057', 'POST', '/api/admin/datasource', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442060', NULL, NULL, '2021-09-21 23:17:51', '2021-12-23 08:05:54', 0, 455, '594756761949442057', 'PUT', '/api/admin/datasource/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636352', NULL, NULL, '2021-09-21 23:17:51', '2021-12-06 03:21:00', 0, 455, '594756761949442057', 'DELETE', '/api/admin/datasource/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636353', NULL, NULL, '2021-09-21 23:17:51', '2022-01-14 14:28:10', 0, 455, '594756761949442057', 'POST', '/api/admin/datasource/dataSourceTest', '数据源连接测试', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636354', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:21:55', 0, 455, '594756761949442057', 'PUT', '/api/admin/datasource/{id}/updPwd', '修改/重置密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636358', NULL, NULL, '2021-09-21 23:17:51', '2021-09-21 23:17:51', 0, 455, '0', '', '/api/admin/xj/banner', 'base-plus--banner', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761953636359', NULL, NULL, '2021-09-21 23:17:51', '2022-03-06 16:31:01', 0, 455, '594756761953636358', 'GET', '/api/admin/xj/banner/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636360', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:22:02', 0, 455, '594756761953636358', 'POST', '/api/admin/xj/banner', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636361', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:22:02', 0, 455, '594756761953636358', 'PUT', '/api/admin/xj/banner/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636362', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:22:01', 0, 455, '594756761953636358', 'DELETE', '/api/admin/xj/banner/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830656', NULL, NULL, '2021-09-21 23:17:51', '2021-09-21 23:17:51', 0, 455, '0', '', '/api/admin/xj/blacklist', 'base-plus--黑名单', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761957830657', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:21:51', 0, 455, '594756761957830656', 'GET', '/api/admin/xj/blacklist/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830658', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:21:51', 0, 455, '594756761957830656', 'POST', '/api/admin/xj/blacklist', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830659', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:21:51', 0, 455, '594756761957830656', 'PUT', '/api/admin/xj/blacklist/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830660', NULL, NULL, '2021-09-21 23:17:51', '2021-10-01 06:21:50', 0, 455, '594756761957830656', 'DELETE', '/api/admin/xj/blacklist/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558656827392', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '0', '', '/api/client/xj/config', 'yh--base-plus--全局配置', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827393', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '595094558656827392', 'GET', '/api/client/xj/config/findByCode', 'CODE查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827394', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '0', '', '/api/client/xj/msg', 'yh--base-plus--消息通知', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827395', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '595094558656827394', 'GET', '/api/client/xj/msg/list', '分页查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827396', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '595094558656827394', 'PUT', '/api/client/xj/msg/{id}/read', '消息修改为已读', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827397', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '595094558656827394', 'GET', '/api/client/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410304', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '0', '', '/api/admin/user', 'base--用户管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410305', NULL, NULL, '2021-09-22 21:40:08', '2022-01-03 07:24:24', 0, 420, '595094558669410304', 'GET', '/api/admin/user/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410306', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:25', 0, 420, '595094558669410304', 'POST', '/api/admin/user', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410307', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:23', 0, 420, '595094558669410304', 'DELETE', '/api/admin/user/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410308', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:25', 0, 420, '595094558669410304', 'GET', '/api/admin/user/{id}', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410309', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:26', 0, 420, '595094558669410304', 'PUT', '/api/admin/user/updUser', '修改当前登录人的信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410310', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:25', 0, 420, '595094558669410304', 'GET', '/api/admin/user/findByRoleId', '获取指定角色的用户列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410311', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:22', 0, 420, '595094558669410304', 'PUT', '/api/admin/user/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410312', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:19', 0, 420, '595094558669410304', 'PUT', '/api/admin/user/updByPassword', '修改当前登录人的密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410313', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:24', 0, 420, '595094558669410304', 'GET', '/api/admin/user/list/keyData', '查询所有-只返回关键数据(姓名/昵称/电话/id)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410314', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:26', 0, 420, '595094558669410304', 'PUT', '/api/admin/user/{id}/resetPassword', '重置任意用户密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604608', NULL, NULL, '2021-09-22 21:40:08', '2022-01-25 22:43:43', 0, 420, '595094558669410304', 'GET', '/api/admin/user/findUser', '查询当前登录人的个人信息', 0, 0, 3, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604609', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:23:30', 0, 420, '595094558669410304', 'POST', '/api/admin/user/login', '用户登录', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604610', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '0', '', '/api/admin/dataBase', 'base-gc--代码生成--查询表数据', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558673604611', NULL, NULL, '2021-09-22 21:40:08', '2021-12-06 03:20:55', 0, 420, '595094558673604610', 'GET', '/api/admin/dataBase/table/list', '查询所有表名', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558677798912', NULL, NULL, '2021-09-22 21:40:08', '2021-12-06 03:20:48', 0, 420, '595094558673604610', 'GET', '/api/admin/dataBase/table/field', '查询指定表下所有字段内容', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558686187520', NULL, NULL, '2021-09-22 21:40:08', '2021-09-22 21:40:08', 0, 420, '0', '', '/api/admin/xj/jvm', 'base-plus--jvm信息获取', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558686187521', NULL, NULL, '2021-09-22 21:40:08', '2021-10-01 06:22:19', 0, 420, '595094558686187520', 'GET', '/api/admin/xj/jvm/jvmInfo', '3、系统的jvm信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('598128304653996032', NULL, NULL, '2021-10-01 06:35:13', '2021-10-01 06:36:06', 0, 409, '590395518954377221', 'GET', '/api/admin/xj/config/findByCode', 'CODE查询', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537088', NULL, NULL, '2021-10-23 20:00:10', '2021-10-23 20:00:10', 0, 319, '0', '', '/api/admin/organ', 'base--组织机构', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('606303420856537089', NULL, NULL, '2021-10-23 20:00:10', '2022-01-02 14:06:15', 0, 319, '606303420856537088', 'GET', '/api/admin/organ/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537090', NULL, NULL, '2021-10-23 20:00:10', '2022-01-02 14:06:15', 0, 319, '606303420856537088', 'POST', '/api/admin/organ', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537091', NULL, NULL, '2021-10-23 20:00:10', '2022-01-02 14:06:14', 0, 319, '606303420856537088', 'DELETE', '/api/admin/organ/{id}', 'ID删除(并删除子数据)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537092', NULL, NULL, '2021-10-23 20:00:10', '2022-01-02 14:06:16', 0, 319, '606303420856537088', 'PUT', '/api/admin/organ/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('613640045747900416', NULL, NULL, '2021-11-13 01:53:18', '2022-01-12 06:15:22', 0, 242, '590081231815839745', 'POST', '/api/admin/generate/generateCodeVue', '生成Vue代码(将直接下载)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('620065229904154624', NULL, NULL, '2021-11-30 19:24:40', '2022-01-03 07:23:22', 0, 184, '594756761932664835', 'GET', '/api/client/xj/banner/list/{position}', '列表-位置查询', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621379493888', NULL, NULL, '2022-01-14 14:06:26', '2022-01-14 14:06:26', 0, 53, '0', '', '/api/admin/test/gcTest', '代码生成测试表', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688192', NULL, NULL, '2022-01-14 14:06:26', '2022-01-14 14:06:26', 0, 53, '641607621379493888', 'GET', '/api/admin/test/gcTest/list', '列表查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688193', NULL, NULL, '2022-01-14 14:06:26', '2022-01-14 14:06:26', 0, 53, '641607621379493888', 'POST', '/api/admin/test/gcTest', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688194', NULL, NULL, '2022-01-14 14:06:26', '2022-01-14 14:06:26', 0, 53, '641607621379493888', 'GET', '/api/admin/test/gcTest/{id}', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688195', NULL, NULL, '2022-01-14 14:06:26', '2022-01-14 14:06:26', 0, 53, '641607621379493888', 'DELETE', '/api/admin/test/gcTest/{id}', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688196', NULL, NULL, '2022-01-14 14:06:26', '2022-01-14 14:06:26', 0, 53, '641607621379493888', 'PUT', '/api/admin/test/gcTest/{id}', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313841881088', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '0', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313841881089', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313841881088', 'GET', '/api/open/websocket/getPath', '游客登录获取websocket连接地址', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313841881090', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313841881088', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313841881091', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313841881088', 'GET', '/api/open/websocket/getOnlineCount', '获取在线人数', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313841881092', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313841881088', 'GET', '/api/open/websocket/getOnlineUsersList', '获取当前在线用户列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313875435520', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '0', '', '/api/open/aliOssFile', 'AliYun --> OSS文件管理', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313879629824', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313875435520', 'GET', '/api/open/aliOssFile/fileList', 'OSS-文件Object列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313879629825', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313875435520', 'DELETE', '/api/open/aliOssFile/del', 'OSS-文件删除', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313879629826', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313875435520', 'POST', '/api/open/aliOssFile/upload', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313879629827', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313875435520', 'GET', '/api/open/aliOssFile/downloadZip', 'OSS-文件下载--多文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('642608313879629828', NULL, NULL, '2022-01-17 00:22:51', '2022-01-17 00:22:51', 0, 52, '642608313875435520', 'GET', '/api/open/aliOssFile/download', 'OSS-文件下载--单文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576302923776', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '0', '', '/api/open/xj/sign', 'body参数验签测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576302923777', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923776', 'POST', '/api/open/xj/sign/test3/{a}', '参数加密', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576302923778', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923776', 'POST', '/api/open/xj/sign/test2', '参数加密', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576302923779', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923776', 'POST', '/api/open/xj/sign/test1', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576302923780', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923776', 'POST', '/api/open/xj/sign/test8', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576307118080', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '0', '', '/api/open/xj/valid', '参数验证测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576307118081', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118080', 'POST', '/api/open/xj/valid/test', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576307118082', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '0', '', '/api/open/xj/cache', '缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576307118083', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118082', 'POST', '/api/open/xj/cache/getCache', '获取缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576319700992', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118082', 'PUT', '/api/open/xj/cache/updCache', '更新缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576319700993', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118082', 'DELETE', '/api/open/xj/cache/delCache', '删除缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576323895296', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '0', '', '/api/open/xj/excel', 'excel测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576323895297', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895296', 'POST', '/api/open/xj/excel/upload', '解析excel数据', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576323895298', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895296', 'GET', '/api/open/xj/excel/exportExcelDownload', 'excel 导出', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576638468096', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '0', '', '/api/open/qq', 'QQ  -->  QQ互联', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576638468097', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468096', 'GET', '/api/open/qq/login', 'qq登录,通过code', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576638468098', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468096', 'GET', '/api/open/qq/getQQLoginUrl', '获取qq登录链接', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576684605440', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '0', '', '/api/open/baidu', 'BaiDu  -->  百度API', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('676677576684605441', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576684605440', 'POST', '/api/open/baidu/textRecognition', '文字识别通用接口', 0, 2, 0, 0);

-- ----------------------------
-- Table structure for t_admin_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_dictionary`;
CREATE TABLE `t_admin_dictionary`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父Id',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_dictionary
-- ----------------------------
INSERT INTO `t_admin_dictionary` VALUES ('1290684671448936449', NULL, '-1', '2020-08-05 00:23:00', '2021-12-02 09:01:04', 0, 0, 'ENUMS', '枚举字典', '0', '状态/动态字段值，如：state，type，gender等, 可直接生成 前/ 后端枚举对象类代码', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290686507555844098', NULL, '-1', '2020-08-05 00:30:17', '2021-11-10 15:33:28', 0, 0, 'ADMIN', '系统枚举(动态值)', '1290684671448936449', '-', 20000, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687277911076865', NULL, '-1', '2020-08-05 00:33:21', '2021-11-10 15:33:28', 0, 0, 'MENU_ROOT', '菜单级别', '1290688121255587841', '【固定值】', 1200, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687351005212673', NULL, '-1', '2020-08-05 00:33:38', '2021-11-10 15:33:28', 0, 0, '1', '顶部菜单', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687461252493314', NULL, '-1', '2020-08-05 00:34:05', '2021-11-10 15:33:28', 0, 0, '2', '菜单', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687547940368386', NULL, '-1', '2020-08-05 00:34:25', '2021-11-10 15:33:28', 0, 0, '3', '页面', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688121255587841', NULL, '-1', '2020-08-05 00:36:42', '2021-11-10 15:33:28', 0, 0, 'BASE', '系统枚举(固定值)', '1290684671448936449', '-', 10000, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688660164931586', NULL, '-1', '2020-08-05 00:38:51', '2021-11-10 15:33:28', 0, 0, 'GENDER', '性别', '1290688121255587841', '【固定值】', 400, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688703043301378', NULL, '-1', '2020-08-05 00:39:01', '2021-11-10 15:33:28', 0, 0, '1', '男', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688742289403906', NULL, '-1', '2020-08-05 00:39:10', '2022-03-05 17:59:57', 0, 0, '2', '女', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1291341478399897601', NULL, '-1', '2020-08-06 11:52:54', '2021-11-10 15:33:29', 0, 0, '0', '未知', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469448399593474', NULL, '-1', '2020-08-20 15:29:38', '2021-11-10 15:33:29', 0, 0, 'DISABLE', '是否禁用', '1290688121255587841', '【固定值】', 300, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469518025039873', NULL, '-1', '2020-08-20 15:29:55', '2021-12-09 14:31:33', 0, 0, '1', '禁用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469564455985154', NULL, '-1', '2020-08-20 15:30:06', '2021-11-10 15:33:29', 0, 0, '0', '启用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995475064434690', NULL, '-1', '2020-08-22 02:19:52', '2021-11-10 15:33:29', 0, 0, 'AUTHORITY_TYPE', '权限类型', '1290688121255587841', '【固定值】：用于新接口自动生成【权限状态】', 1100, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995560007479298', NULL, '-1', '2020-08-22 02:20:12', '2021-11-10 15:33:29', 0, 0, '0', '管理端接口', '1296995475064434690', '管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995610632728578', NULL, '-1', '2020-08-22 02:20:24', '2021-11-10 15:33:29', 0, 0, '1', '用户端接口', '1296995475064434690', '用户端, 类上标有该参数所有接口都会默认被列为-[需登录]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995742778470401', NULL, '-1', '2020-08-22 02:20:55', '2021-11-10 15:33:29', 0, 0, 'AUTHORITY_STATE', '权限状态', '1290688121255587841', '【固定值】：用于编辑指定接口的【权限状态】', 1000, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995839977271297', NULL, '-1', '2020-08-22 02:21:19', '2021-11-10 15:33:29', 0, 0, '0', '无', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996062090833922', NULL, '-1', '2020-08-22 02:22:12', '2021-11-10 15:33:29', 0, 0, '1', '需登录', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996224863383554', NULL, '-1', '2020-08-22 02:22:50', '2021-11-10 15:33:29', 0, 0, '2', '需登录+授权', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1297705363906273282', NULL, '-1', '2020-08-24 01:20:43', '2021-11-10 15:33:30', 0, 0, '2', '通用接口', '1296995475064434690', '通用接口, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191840981327873', NULL, '-1', '2020-08-25 09:33:48', '2021-11-10 15:33:30', 0, 0, 'DELETED', '逻辑删除', '1290688121255587841', '【固定值】', 200, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191898313269249', NULL, '-1', '2020-08-25 09:34:02', '2021-11-10 15:33:30', 0, 0, '0', '正常', '1298191840981327873', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191981998022658', NULL, '-1', '2020-08-25 09:34:22', '2021-11-10 15:33:30', 0, 0, '1', '已删除', '1298191840981327873', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194668697198594', NULL, '-1', '2020-08-25 09:45:02', '2021-11-10 15:33:30', 0, 0, 'BANNER_IS_SKIP', 'banner是否跳转', '1290688121255587841', '【固定值】', 2000, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194722350735361', NULL, '-1', '2020-08-25 09:45:15', '2021-11-10 15:33:30', 0, 0, '0', '否', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194801014906881', NULL, '-1', '2020-08-25 09:45:34', '2021-11-10 15:33:30', 0, 0, '1', '内部跳转', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194850285395969', NULL, '-1', '2020-08-25 09:45:46', '2021-11-10 15:33:30', 0, 0, '2', '外部跳转', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1303872194494435330', NULL, '-1', '2020-09-10 09:45:30', '2021-11-10 15:33:30', 0, 0, 'BANNER_POSITION', 'banner 位置', '1290686507555844098', '【动态值】', 300, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1303872308608864257', NULL, '-1', '2020-09-10 09:45:57', '2021-11-10 15:33:30', 0, 0, '1', '用户端首页', '1303872194494435330', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580167513370625', NULL, '-1', '2020-09-23 09:33:17', '2021-11-10 15:33:30', 0, 0, 'MSG_USER_TYPE', '及时消息终端', '1290686507555844098', '【动态值】', 500, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580236161544193', NULL, '-1', '2020-09-23 09:33:33', '2021-11-10 15:33:30', 0, 0, '1', '用户端', '1308580167513370625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580275248263169', NULL, '-1', '2020-09-23 09:33:42', '2021-11-10 15:33:31', 0, 0, '2', '管理端', '1308580167513370625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585499920769025', NULL, '-1', '2020-09-23 09:54:28', '2021-11-10 15:33:31', 0, 0, 'MSG_TYPE', '及时消息类型', '1290686507555844098', '【动态值】', 400, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585615968772098', NULL, '-1', '2020-09-23 09:54:56', '2021-11-10 15:33:31', 0, 0, '1', '系统通知', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332329973427494913', NULL, '-1', '2020-11-27 22:26:31', '2021-11-10 15:33:31', 0, 0, 'BLACKLIST_TYPE', '黑/白名单类型', '1290688121255587841', '【固定值】', 2200, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332330105569042434', NULL, '-1', '2020-11-27 22:27:02', '2021-11-10 15:33:31', 0, 0, '1', '白名单', '1332329973427494913', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332330148963311619', NULL, '-1', '2020-11-27 22:27:13', '2021-11-10 15:33:31', 0, 0, '2', '黑名单', '1332329973427494913', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352826209829978114', NULL, '-1', '2021-01-23 11:51:17', '2021-11-10 15:33:31', 0, 0, '3', 'Oauth2 接口', '1296995475064434690', '接口默认默认需通过 appId，appSecret生成的 accessToken 来进行oauth2 令牌验证可访问', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352826389480407041', NULL, '-1', '2021-01-23 11:52:00', '2021-11-10 15:33:31', 0, 0, '3', '需Oauth2 授权', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856400451117058', NULL, '-1', '2021-01-23 13:51:15', '2021-11-10 15:33:31', 0, 0, 'PAY_TYPE', '支付类型', '1290688121255587841', '【固定值】', 3200, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856492264431617', NULL, '-1', '2021-01-23 13:51:37', '2021-11-10 15:33:31', 0, 0, '1', '支付', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856528012484610', NULL, '-1', '2021-01-23 13:51:45', '2021-11-10 15:33:31', 0, 0, '2', '充值', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856603073748994', NULL, '-1', '2021-01-23 13:52:03', '2021-11-10 15:33:31', 0, 0, '3', '退款', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856663249428482', NULL, '-1', '2021-01-23 13:52:18', '2021-11-10 15:33:32', 0, 0, '4', '商家打款', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856892170346498', NULL, '-1', '2021-01-23 13:53:12', '2021-11-10 15:33:32', 0, 0, 'PAY_CHANNEL', '支付渠道', '1290688121255587841', '【固定值】', 3000, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856932938981378', NULL, '-1', '2021-01-23 13:53:22', '2021-11-10 15:33:32', 0, 0, '1', '支付宝', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856979743219713', NULL, '-1', '2021-01-23 13:53:33', '2021-11-10 15:33:32', 0, 0, '2', '微信', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857025708597250', NULL, '-1', '2021-01-23 13:53:44', '2021-11-10 15:33:32', 0, 0, '3', '银联', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857215228223489', NULL, '-1', '2021-01-23 13:54:29', '2021-11-10 15:33:32', 0, 0, 'PAY_STATE', '支付状态', '1290688121255587841', '【固定值】用于记录支付交易请求状态', 3100, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857264104448001', NULL, '-1', '2021-01-23 13:54:41', '2021-11-10 15:33:32', 0, 0, '0', '已发起', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857305888104450', NULL, '-1', '2021-01-23 13:54:51', '2021-11-10 15:33:32', 0, 0, '1', '回调成功', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857349592752130', NULL, '-1', '2021-01-23 13:55:01', '2021-11-10 15:33:32', 0, 0, '2', '交易失败', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857389069541377', NULL, '-1', '2021-01-23 13:55:11', '2021-11-10 15:33:32', 0, 0, '3', '交易成功', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857426407235585', NULL, '-1', '2021-01-23 13:55:20', '2021-11-10 15:33:32', 0, 0, '4', '订单异常', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857793505304577', NULL, '-1', '2021-01-23 13:56:47', '2021-11-10 15:37:17', 0, 0, 'PAY_BUSINESS', '支付业务', '1290686507555844098', '【动态值】当前支付业务', 600, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857906709569537', NULL, '-1', '2021-01-23 13:57:14', '2021-11-10 15:37:17', 0, 0, '1', '用户下单', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352858096959004674', NULL, '-1', '2021-01-23 13:57:59', '2021-11-10 15:37:18', 0, 0, '2', 'vip 充值/续费', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528050694148097', NULL, '-1', '2021-02-05 11:14:43', '2021-11-10 15:33:33', 0, 0, 'WALLET_TYPE', '流水类型', '1290688121255587841', '【固定值】', 3300, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528121372364801', NULL, '-1', '2021-02-05 11:15:00', '2021-11-10 15:33:33', 0, 0, '1', '收入', '1357528050694148097', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528154167627779', NULL, '-1', '2021-02-05 11:15:07', '2021-11-10 15:33:33', 0, 0, '2', '支出', '1357528050694148097', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612114939858945', NULL, '-1', '2021-02-05 16:48:45', '2021-11-10 15:33:33', 0, 0, 'IS_READ', '是否已读', '1290688121255587841', '【固定值】', 2100, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612150536916994', NULL, '-1', '2021-02-05 16:48:54', '2021-11-10 15:33:33', 0, 0, '0', '未读', '1357612114939858945', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612182854029315', NULL, '-1', '2021-02-05 16:49:01', '2021-11-10 15:33:33', 0, 0, '1', '已读', '1357612114939858945', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1368739295631798273', NULL, '-1', '2021-03-08 09:44:11', '2021-11-10 15:33:33', 0, 0, 'POSITION', '部门职位', '1290686507555844098', '【动态值】, 如有需要根据业务指定', 200, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1368739394596401154', NULL, '-1', '2021-03-08 09:44:35', '2022-01-13 22:20:48', 0, 0, '0', '系统管理员(老板)', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1384697257961463810', NULL, '-1', '2021-04-21 10:35:27', '2022-01-13 22:19:46', 0, 0, '1', '部门经理', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968409441050625', NULL, '-1', '2021-06-02 13:57:32', '2021-11-10 15:33:34', 0, 0, 'DEFAULT', '默认字典(代码生成默认字典)', '1290688121255587841', '【固定值】用于代码生成默认使用的code值', 100, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968449656037377', NULL, '-1', '2021-06-02 13:57:42', '2021-11-10 15:33:34', 0, 0, '1', '默认值 1', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968504043577346', NULL, '-1', '2021-06-02 13:57:55', '2021-11-10 15:33:34', 0, 0, '2', '默认值 2', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968544350838786', NULL, '-1', '2021-06-02 13:58:04', '2022-01-01 12:28:16', 0, 0, '3', '默认值 3', '1399968409441050625', '-', 0, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1404005220177985537', NULL, '-1', '2021-06-13 17:18:21', '2021-11-10 15:33:34', 0, 0, '0', '测试消息', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513445955194882', NULL, '-1', '2021-08-17 14:11:42', '2021-11-10 15:33:34', 0, 0, '4', '其他', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513925234118658', NULL, '-1', '2021-08-17 14:13:36', '2021-11-10 15:37:18', 0, 0, '3', '月卡购买', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513998571524097', NULL, '-1', '2021-08-17 14:13:53', '2021-11-10 15:37:18', 0, 0, '4', '其他', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1432260997380349954', NULL, '-1', '2021-08-30 16:36:49', '2021-11-10 15:33:34', 0, 0, 'TERMINAL', '终端', '1290686507555844098', '【动态值】 如有需要根据业务指定', 100, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1432261183641001986', NULL, '-1', '2021-08-30 16:37:33', '2022-01-17 14:33:51', 0, 0, '1', 'vue 主系统端', '1432260997380349954', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1432261342928084993', NULL, '-1', '2021-08-30 16:38:11', '2022-01-18 20:11:54', 0, 0, '2', '商家端', '1432260997380349954', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1435127387682508801', NULL, '-1', '2021-09-07 14:26:46', '2022-01-17 14:33:55', 1, 0, '3', 'xxx端', '1432260997380349954', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1443767289248788481', NULL, '-1', '2021-10-01 10:38:39', '2021-12-21 13:41:33', 0, 0, 'ORGAN_ROOT', '机构组织级别', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1443767335407104002', NULL, '-1', '2021-10-01 10:38:50', '2021-11-10 15:33:35', 0, 0, '1', '一级', '1443767289248788481', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1443767375802445826', NULL, '-1', '2021-10-01 10:39:00', '2021-11-10 15:33:35', 0, 0, '2', '二级', '1443767289248788481', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1443767410984267777', NULL, '-1', '2021-10-01 10:39:08', '2021-11-10 15:33:35', 0, 0, '3', '三级', '1443767289248788481', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455153732051349505', NULL, NULL, '2021-11-01 20:44:19', '2021-11-10 15:33:35', 0, 0, 'VUE_FIELD_TYPE', 'VUE字段类型', '1290688121255587841', 'vue代码生成使用', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455153814570086402', NULL, NULL, '2021-11-01 20:44:39', '2021-12-14 16:10:34', 0, 0, '1', '文本-(input)', '1455153732051349505', '-', 1, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455153864008347650', NULL, NULL, '2021-11-01 20:44:51', '2021-12-14 16:10:36', 0, 0, '2', '数字-(number)', '1455153732051349505', '-', 2, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455154454599905281', NULL, NULL, '2021-11-01 20:47:12', '2021-11-10 15:33:35', 0, 0, '4', '单选-(radio)', '1455153732051349505', '-', 4, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455154827834241025', NULL, NULL, '2021-11-01 20:48:41', '2021-11-10 15:33:35', 0, 0, '9', '开关-(switch)', '1455153732051349505', '-', 9, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455154939453059073', NULL, NULL, '2021-11-01 20:49:07', '2021-11-10 15:33:35', 0, 0, '5', '多选-(checkbox)', '1455153732051349505', '-', 5, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455155390269435905', NULL, NULL, '2021-11-01 20:50:55', '2021-11-10 15:33:35', 0, 0, '10', '日期-(data)', '1455153732051349505', 'yyyy-MM-dd', 10, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1455155744738455553', NULL, NULL, '2021-11-01 20:52:19', '2021-11-10 15:33:35', 0, 0, '11', '日期时间-(datetime)', '1455153732051349505', 'yyyy-MM-dd hh:mm:ss', 11, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455155995658498049', NULL, NULL, '2021-11-01 20:53:19', '2021-12-10 14:02:58', 0, 0, '12', '时间-(time)', '1455153732051349505', '-', 12, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1455156254728073217', NULL, NULL, '2021-11-01 20:54:21', '2021-11-10 15:33:36', 0, 0, '6', '下拉选择-(select-单选)', '1455153732051349505', '-', 6, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455156611625594881', NULL, NULL, '2021-11-01 20:55:46', '2021-12-14 16:10:36', 0, 0, '3', '密码-(password)', '1455153732051349505', '-', 3, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455157509512835073', NULL, NULL, '2021-11-01 20:59:20', '2021-11-10 15:33:36', 0, 0, '7', '下拉选择 (select-单选+搜索)', '1455153732051349505', '-', 7, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1455157594946613250', NULL, NULL, '2021-11-01 20:59:40', '2021-11-10 15:33:36', 0, 0, '8', '下拉选择 (select-多选+搜索)', '1455153732051349505', '-', 8, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1455158056173252609', NULL, NULL, '2021-11-01 21:01:30', '2021-11-10 15:33:36', 0, 0, '13', '文件上传(默认单图)', '1455153732051349505', '-', 13, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455158128055234561', NULL, NULL, '2021-11-01 21:01:47', '2021-11-10 15:33:36', 0, 0, '14', '文件上传(多图)', '1455153732051349505', '-', 14, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1455158254165372929', NULL, NULL, '2021-11-01 21:02:18', '2021-11-10 15:33:36', 0, 0, '15', '文件上传（缩略图）', '1455153732051349505', '-', 15, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1455160116759310338', NULL, NULL, '2021-11-01 21:09:42', '2021-11-10 15:33:36', 0, 0, '16', '文件上传（附件）', '1455153732051349505', '-', 16, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1456761651003920386', NULL, NULL, '2021-11-06 07:13:37', '2021-11-10 15:33:36', 0, 0, '17', '大文本(textarea)', '1455153732051349505', '-', 17, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459042333664313346', NULL, NULL, '2021-11-12 14:16:14', '2021-11-16 09:48:06', 1, 0, 'TEST', '测试枚举-一级', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459042413939097601', NULL, NULL, '2021-11-12 14:16:33', '2021-11-16 09:48:06', 1, 0, 'one-test', '测试二级', '1459042333664313346', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459042547246661633', NULL, NULL, '2021-11-12 14:17:05', '2021-11-16 09:46:27', 0, 0, '1', '测试类别 1-1', '1459083146960142338', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459079813935497217', NULL, NULL, '2021-11-12 16:45:10', '2021-11-12 16:45:19', 1, 0, '1', '测试1', '1459042547246661633', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459081574796591106', NULL, NULL, '2021-11-12 16:52:10', '2021-11-16 09:47:11', 0, 0, 'TEST2', '测试2级-2', '1459082841933578241', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459082841933578241', NULL, NULL, '2021-11-12 16:57:12', '2021-11-16 09:41:57', 0, 0, 'test3', '测试顶级', '0', '1', 1, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459083070342791169', NULL, NULL, '2021-11-12 16:58:07', '2021-11-12 16:58:11', 1, 0, '3', '参数2', '1459082841933578241', '3', 1, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459083146960142338', NULL, NULL, '2021-11-12 16:58:25', '2021-11-16 09:46:44', 0, 0, 'asasa', '测试2级-1', '1459082841933578241', '-----', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459853103061807105', NULL, NULL, '2021-11-14 19:57:56', '2021-11-14 19:58:13', 1, 0, 'CONFIG_TYPE', '全局配置类型', '0', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459853262768320513', NULL, NULL, '2021-11-14 19:58:34', '2021-11-14 19:58:34', 0, 0, 'CONFIG_TYPE', '全局配置类型', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459853371690201089', NULL, NULL, '2021-11-14 19:59:00', '2021-11-14 20:01:45', 0, 0, '0', '文本', '1459853262768320513', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459853401289404418', NULL, NULL, '2021-11-14 19:59:07', '2021-11-14 20:01:55', 0, 0, '1', '图片', '1459853262768320513', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1459853475214012418', NULL, NULL, '2021-11-14 19:59:25', '2021-11-14 20:01:59', 0, 0, '2', '开关', '1459853262768320513', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1460421323620655106', NULL, NULL, '2021-11-16 09:35:51', '2021-11-16 09:44:18', 1, 0, '3', '测试33', '1459083146960142338', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1460423724855500801', NULL, NULL, '2021-11-16 09:45:24', '2021-11-16 09:46:32', 0, 0, '2', '测试类别 1-2', '1459083146960142338', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1460423875812696066', NULL, NULL, '2021-11-16 09:46:00', '2021-11-16 09:46:20', 0, 0, '1', '测试类别 2-1', '1459081574796591106', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1460423936965648386', NULL, NULL, '2021-11-16 09:46:14', '2021-11-16 09:46:14', 0, 0, '2', '测试类别 2-2', '1459081574796591106', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1477131259376308225', NULL, NULL, '2022-01-01 12:15:10', '2022-01-01 12:15:54', 1, 0, 'DEMO_TEST', 'demo 业务模快枚举', '0', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1477131575404531714', NULL, NULL, '2022-01-01 12:16:26', '2022-01-01 12:16:26', 0, 0, 'DEMO_TEST', 'demo模块枚举', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1477131743994580994', NULL, NULL, '2022-01-01 12:17:06', '2022-01-01 12:17:06', 0, 0, 'SEX', '性别', '1477131575404531714', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1477131792388460546', NULL, NULL, '2022-01-01 12:17:17', '2022-01-01 12:31:00', 1, 0, '0', '女', '0', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1477131857387589634', NULL, NULL, '2022-01-01 12:17:33', '2022-01-01 12:17:33', 0, 0, '0', '女', '1477131743994580994', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1477131918590873601', NULL, NULL, '2022-01-01 12:17:47', '2022-01-01 12:17:47', 0, 0, '1', '男', '1477131743994580994', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1477131953227436034', NULL, NULL, '2022-01-01 12:17:56', '2022-01-14 17:16:09', 0, 0, '2', '未知', '1477131743994580994', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1481632150259240961', NULL, NULL, '2022-01-13 22:20:04', '2022-01-13 22:20:24', 0, 0, '2', '员工', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1481916460358963201', NULL, NULL, '2022-01-14 17:09:51', '2022-01-14 17:10:12', 1, 0, 'zw', '职位', '0', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1481917469114241025', NULL, NULL, '2022-01-14 17:13:51', '2022-01-14 17:14:02', 1, 0, 'zw', '职位', '1477131575404531714', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1481917496742121474', NULL, NULL, '2022-01-14 17:13:58', '2022-01-14 17:14:02', 1, 0, '1', 'cs', '1481917469114241025', '-', 0, 0);

-- ----------------------------
-- Table structure for t_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_menu`;
CREATE TABLE `t_admin_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '指定父id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
  `two_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第二路由url, 前后端分离前端使用第二路由',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `root` int(1) NOT NULL DEFAULT 0 COMMENT '目录级别(1-系统, 2-菜单 ，3-页面, 4-按钮)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  `terminal` int(1) NULL DEFAULT 1 COMMENT '终端 (字段code)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES ('1440255471893200897', '1', '-1', '2021-09-21 18:03:57', '2022-01-17 14:33:00', 0, 0, '0', 'a-vue', '', '', 'layui-icon-file-b', 1, 1, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440255602914869250', '1', '-1', '2021-09-21 18:04:29', '2022-01-17 14:33:00', 0, 0, '1440255471893200897', '系统管理', '', '', 'el-icon-setting', 100, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440255716299489282', '1', '-1', '2021-09-21 18:04:56', '2022-04-20 16:49:12', 0, 0, '1440255602914869250', '菜单管理', '', '/views/admin/menu/menu', 'el-icon-document-remove', 20005, 3, 1, 1);
INSERT INTO `t_admin_menu` VALUES ('1440256392576483330', '1', '-1', '2021-09-21 18:07:37', '2022-01-17 14:55:41', 0, 0, '0', 'a-vue-2 (test)', '', '', 'layui-icon-file-b', 2, 1, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440256481906769922', '1', '-1', '2021-09-21 18:07:58', '2022-01-17 14:33:00', 0, 0, '1440256392576483330', '功能测试', '', '', 'layui-icon-file-b', 200, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440271162033684482', '1', '-1', '2021-09-21 19:06:18', '2022-01-17 14:33:00', 0, 0, '1440255602914869250', '用户管理', '', '/views/admin/user/user', 'el-icon-document-remove', 10001, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1442156484086480897', '1', '-1', '2021-09-26 23:57:54', '2022-01-17 14:33:00', 0, 0, '1440255602914869250', '角色管理', '', '/views/admin/role/role', 'el-icon-document-remove', 10003, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1442156557235142657', '1', '-1', '2021-09-26 23:58:11', '2022-04-20 16:49:15', 0, 0, '1440255602914869250', '字典管理', '', '/views/admin/adminDictionary/adminDictionary', 'el-icon-document-remove', 20006, 3, 1, 1);
INSERT INTO `t_admin_menu` VALUES ('1442156599396286466', '1', '-1', '2021-09-26 23:58:21', '2022-04-20 16:49:14', 0, 0, '1440255602914869250', '接口管理', '', '/views/admin/adminAuthority/adminAuthority', 'el-icon-document-remove', 10004, 3, 1, 1);
INSERT INTO `t_admin_menu` VALUES ('1449698334917865473', '1', NULL, '2021-10-17 19:26:30', '2022-01-17 14:33:00', 0, 0, '1449698274373087233', '页面-5级', NULL, '', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1449764190750285826', '1', NULL, '2021-10-17 23:48:11', '2022-01-17 14:33:01', 0, 0, '1440255471893200897', '增强功能', '', '', 'el-icon-copy-document', 200, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1449764248052867074', '1', NULL, '2021-10-17 23:48:25', '2022-01-17 14:33:01', 1, 0, '1449764190750285826', '怕怕', NULL, '', 'el-icon-document-remove', 10001, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1450796419538522114', '1', NULL, '2021-10-20 20:09:55', '2022-01-17 14:33:01', 0, 0, '1440256481906769922', '测试页1', NULL, '/test1', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1451979503835369474', '1443465040706416642', NULL, '2021-10-24 02:31:02', '2022-01-17 14:33:01', 0, 0, '1449764190750285826', 'Banner管理', '', '/views/xj/xjAdminBanner/xjAdminBanner', 'el-icon-document-remove', 20003, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1452091447254749186', '1', NULL, '2021-10-24 09:55:54', '2022-01-17 14:33:01', 0, 0, '1440255471893200897', '代码生成', NULL, '', 'el-icon-edit', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1452091513113710594', '1', NULL, '2021-10-24 09:56:10', '2022-01-17 14:33:01', 0, 0, '1452091447254749186', '数据表', NULL, '/views/gc/codeGeneration/codeGeneration', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1456054437146644481', '1', NULL, '2021-11-04 08:23:24', '2022-01-17 14:33:01', 0, 0, '1452091447254749186', '生成的代码测试页', NULL, '/views/test/gcTest/gcTest', 'el-icon-document-remove', 1, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1457369967249879042', '1', NULL, '2021-11-07 23:30:51', '2022-01-18 20:12:26', 0, 0, '0', '商家端', NULL, NULL, 'el-icon-document-remove', 0, 1, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457370029065531394', '1', NULL, '2021-11-07 23:31:06', '2022-01-17 14:55:59', 0, 0, '1457369967249879042', '系统管理', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457370075530031105', '1', NULL, '2021-11-07 23:31:17', '2022-01-17 14:57:17', 0, 0, '1457370029065531394', '角色管理', NULL, '/views/admin/role/role', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457372083897004033', '1', NULL, '2021-11-07 23:39:16', '2022-01-17 14:57:23', 0, 0, '1457370029065531394', '员工管理', NULL, '/views/admin/user/user', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1459712656557576194', '1', NULL, '2021-11-14 10:39:51', '2022-01-17 14:33:01', 0, 0, '1440255602914869250', '组织机构', NULL, '/views/admin/adminOrgan/adminOrgan', 'el-icon-document-remove', 10002, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1459850402525622274', '1', NULL, '2021-11-14 19:47:12', '2022-01-17 14:33:01', 0, 0, '1449764190750285826', '全局配置', NULL, '/views/xj/xjAdminConfig/xjAdminConfig', 'el-icon-document-remove', 20001, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1460481331314073602', '1', NULL, '2021-11-16 13:34:18', '2022-01-17 14:33:01', 1, 0, '1440255471893200897', '首页', NULL, '/views/wel/jvmInfo', 'el-icon-document-remove', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1461987433667141634', '1', NULL, '2021-11-20 17:19:01', '2022-02-15 18:16:24', 0, 0, '1440255471893200897', '首页', NULL, '/wel/jvmInfo', 'el-icon-menu', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1462256665587916801', '1', NULL, '2021-11-21 11:08:51', '2022-01-17 14:33:02', 0, 0, '1449764190750285826', '黑/白名单', NULL, '/views/xj/xjAdminBlacklist/xjAdminBlacklist', 'el-icon-document-remove', 20002, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1462261436877152258', '1', NULL, '2021-11-21 11:27:48', '2022-01-17 14:33:02', 0, 0, '1449764190750285826', '请求日志', NULL, '/views/xj/xjAdminLog/xjAdminLog', 'el-icon-document-remove', 20005, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1468415037767946242', '1', NULL, '2021-12-08 11:00:01', '2022-01-17 14:33:02', 0, 0, '1449764190750285826', '消息管理', NULL, '/views/xj/xjAdminMsg/xjAdminMsg', 'el-icon-s-comment', 20004, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481640320645992449', '1', NULL, '2022-01-13 22:52:32', '2022-01-17 14:33:02', 1, 0, '1440256481906769922', '二级菜单测试', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481640347023970305', '1', NULL, '2022-01-13 22:52:39', '2022-01-17 14:33:02', 1, 0, '1440256481906769922', '二级菜单测试', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481640473729699842', '1', NULL, '2022-01-13 22:53:09', '2022-01-17 14:33:02', 1, 0, '1440256481906769922', '二级菜单测试', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481640923703021570', '1', NULL, '2022-01-13 22:54:56', '2022-01-17 14:33:02', 1, 0, '1440256481906769922', '测试二级菜单', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481641152082874369', '1', NULL, '2022-01-13 22:55:51', '2022-01-17 14:33:02', 1, 0, '1481640923703021570', '测试三级菜单', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481641211155451905', '1', NULL, '2022-01-13 22:56:05', '2022-01-17 14:33:02', 1, 0, '1481641152082874369', '测试页面', NULL, 'kkk', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481641284945842178', '1', NULL, '2022-01-13 22:56:22', '2022-01-17 14:33:02', 1, 0, '1481640923703021570', '测试页面2', NULL, 'sss', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481642095692222465', '1', NULL, '2022-01-13 22:59:36', '2022-01-17 14:33:02', 0, 0, '1440256481906769922', '测试页2', NULL, '/test2', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481642335702880257', '1', NULL, '2022-01-13 23:00:33', '2022-01-17 14:33:03', 1, 0, '1440256481906769922', '测试菜单2', NULL, '/llll', 'el-icon-document-remove', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481642552846192641', '1', NULL, '2022-01-13 23:01:25', '2022-01-17 14:33:03', 1, 0, '1481642335702880257', '测试菜单3', NULL, '/xxxxxx', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1482970741228965889', '1', NULL, '2022-01-17 14:59:11', '2022-01-17 14:59:18', 0, 0, '1457369967249879042', '业务管理', NULL, NULL, 'el-icon-document-remove', 1, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1482970818676789249', '1', NULL, '2022-01-17 14:59:30', '2022-01-18 20:12:37', 0, 0, '1482970741228965889', '业务功能1', NULL, '/yw1', 'el-icon-document-remove', 1, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1482970868152799234', '1', NULL, '2022-01-17 14:59:41', '2022-01-18 20:12:40', 0, 0, '1482970741228965889', '业务功能2', NULL, '/yw2', 'el-icon-document-remove', 2, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1482970903854714882', '1', NULL, '2022-01-17 14:59:50', '2022-01-18 20:12:44', 0, 0, '1482970741228965889', '业务功能3', NULL, '/yw3', 'el-icon-document-remove', 3, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1482979903354703874', '1', NULL, '2022-01-17 15:35:36', '2022-01-17 15:35:36', 0, 0, '1457370029065531394', '菜单管理', NULL, '/views/admin/menu/menu', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1516699625820524545', '1', NULL, '2022-04-20 16:45:44', '2022-04-20 16:45:59', 0, 0, '1440255602914869250', '菜单管理V2', NULL, '/views/admin/menuv2/menu', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1516699798743289857', '1', NULL, '2022-04-20 16:46:25', '2022-04-20 16:48:34', 0, 0, '1440255602914869250', '字典管理v2', NULL, '/views/admin/dictionaryv2/adminDictionary', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1516699922710138882', '1', NULL, '2022-04-20 16:46:55', '2022-04-20 16:46:55', 0, 0, '1440255602914869250', '接口管理v2', NULL, '/views/admin/adminAuthorityv2/adminAuthority', 'el-icon-document-remove', 0, 3, 0, 1);

-- ----------------------------
-- Table structure for t_admin_organ
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_organ`;
CREATE TABLE `t_admin_organ`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父Id (顶级父id=0)',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码 (开始查询使用,不可重复)',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门/公司名称',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门/公司描叙',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  `root` int(1) NULL DEFAULT 1 COMMENT '级别( 1-一级 2-二级 3-三级)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--组织机构' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_organ
-- ----------------------------
INSERT INTO `t_admin_organ` VALUES ('1443501889504210946', NULL, '-1', '2021-09-30 17:04:03', '2022-01-11 10:16:20', 0, 0, '0', 'zgs', '成都总公司', '-', 0, 0, 1);
INSERT INTO `t_admin_organ` VALUES ('1443502090977603585', NULL, '-1', '2021-09-30 17:04:51', '2021-10-08 16:24:00', 0, 0, '1443501889504210946', 'zgsa', '子公司a', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1443502157943861250', NULL, '-1', '2021-09-30 17:05:07', '2022-02-23 15:13:43', 0, 0, '1443501889504210946', 'zgsb', '子公司b', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1443502249136418817', NULL, '-1', '2021-09-30 17:05:29', '2022-01-14 16:56:06', 1, 0, '1443502090977603585', 'kfb', '开发部', '-', 0, 1, 3);
INSERT INTO `t_admin_organ` VALUES ('1443502302433439746', NULL, '-1', '2021-09-30 17:05:42', '2021-10-08 16:24:00', 0, 0, '1443502090977603585', 'csb', '测试部', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1443502428644241409', NULL, '-1', '2021-09-30 17:06:12', '2021-11-29 19:50:02', 0, 0, '1443502157943861250', 'yyb', '运营部', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1459717628875608065', NULL, NULL, '2021-11-14 10:59:36', '2021-11-28 21:27:45', 1, 0, '0', '111', '测试11', '-', 0, 0, 1);
INSERT INTO `t_admin_organ` VALUES ('1459717651424186369', NULL, NULL, '2021-11-14 10:59:42', '2021-11-28 21:27:42', 1, 0, '1459717628875608065', '121', '121', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1459717674845179906', NULL, NULL, '2021-11-14 10:59:47', '2021-11-28 21:27:42', 1, 0, '1459717651424186369', '11', '测试', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1459717751051489281', NULL, NULL, '2021-11-14 11:00:05', '2021-11-28 21:27:42', 1, 0, '1459717651424186369', 'test3', '11', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1468426496627490818', NULL, NULL, '2021-12-08 11:45:33', '2021-12-08 11:45:33', 0, 0, '1443501889504210946', 'zb', '公司总部', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1481913070589579265', NULL, NULL, '2022-01-14 16:56:23', '2022-02-18 11:03:52', 1, 0, '0', 'bj-gs', '北京总公司', '-', 0, 0, 1);
INSERT INTO `t_admin_organ` VALUES ('1481913127925714945', NULL, NULL, '2022-01-14 16:56:36', '2022-02-18 11:03:52', 1, 0, '1481913070589579265', 'zgs1', '子公司1', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1481913168983756802', NULL, NULL, '2022-01-14 16:56:46', '2022-01-14 16:56:46', 0, 0, '1481913127925714945', 'xx-dep', 'xx部门', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1481913213086863362', NULL, NULL, '2022-01-14 16:56:57', '2022-01-14 16:56:57', 0, 0, '1481913127925714945', 'xx-dep2', 'xx部门2', '-', 0, 0, 3);

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询code(不能重复)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  `terminal` int(1) NULL DEFAULT 1 COMMENT '终端 (字段code)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('1443467633444806658', '1', '-1', '2021-09-30 14:47:56', '2022-01-17 14:35:01', 0, 0, 'avue-超管', 'avue超管', 'SYS', 0, 1);
INSERT INTO `t_admin_role` VALUES ('1447115588580159489', '1446872739607478273', NULL, '2021-10-10 16:23:36', '2022-01-17 14:35:01', 0, 0, 'avue 体验账号', '-', 'test', 0, 1);
INSERT INTO `t_admin_role` VALUES ('1482968335795621890', '1', NULL, '2022-01-17 14:49:38', '2022-01-18 20:09:28', 1, 0, '扩展端1主账号', '-', 'kz-sys', 0, 2);
INSERT INTO `t_admin_role` VALUES ('1482986533383311362', '642946758053335040', NULL, '2022-01-17 16:01:56', '2022-01-18 20:09:26', 1, 0, '业务1处理人员', '-', 'yw1', 0, 2);
INSERT INTO `t_admin_role` VALUES ('1483412338848567297', '1', NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '商家端超管', '-', 'sj-sys', 0, 2);
INSERT INTO `t_admin_role` VALUES ('1483415755868344322', '643392744110297088', NULL, '2022-01-18 20:27:31', '2022-01-18 20:27:31', 0, 0, '业务2和3处理人员', '-', 'yw-23', 0, 2);

-- ----------------------------
-- Table structure for t_admin_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_auth`;
CREATE TABLE `t_admin_role_auth`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `auth_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/接口权限关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_role_auth
-- ----------------------------
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870018', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590081231815839745', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870019', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624962', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870020', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624963', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870021', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624964', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870022', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518954377221', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870023', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870024', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571521', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870025', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571522', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870026', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571524', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870027', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571526', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870028', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765825', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864054870029', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765828', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064321', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765829', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064322', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765830', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064323', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590398100552683520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064324', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590417006180831232', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064325', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064326', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064327', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862658', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064328', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862660', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064329', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717120', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064330', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717121', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064331', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717122', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064332', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911424', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064333', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911425', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064334', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911426', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064335', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911427', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064336', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911429', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064337', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452416', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064338', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452417', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064339', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452418', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064340', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452419', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064341', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452421', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064342', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452423', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064343', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452424', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064344', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452425', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064345', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646720', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064346', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646721', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064347', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646722', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064348', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646723', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064349', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646724', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064350', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646725', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064351', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646727', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064352', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '592136133727621120', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064353', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442057', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064354', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442058', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064355', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442059', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064356', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442060', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064357', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636352', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864059064358', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636353', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590273', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636354', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590274', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636358', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590275', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636359', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590276', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636360', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590277', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636361', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590278', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636362', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590279', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590280', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590281', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830658', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590282', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830659', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590283', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830660', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590284', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410304', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590285', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410305', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590286', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410306', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590287', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410307', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590288', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410308', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590289', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410309', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590290', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410310', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590291', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410311', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590292', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410312', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590293', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410313', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590294', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410314', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590295', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604608', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590296', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604609', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590297', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604610', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590298', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604611', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590299', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558677798912', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590300', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558686187520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590301', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558686187521', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590302', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '598128304653996032', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590303', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537088', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590304', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537089', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864113590305', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537090', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978881', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537091', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978882', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537092', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978883', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '613640045747900416', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978884', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621379493888', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978885', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688192', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978886', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688193', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978887', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688194', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978888', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688195', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864121978889', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688196', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921922', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590081231815839745', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921923', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624962', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921924', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624963', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921925', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624964', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921926', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518954377221', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921927', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921928', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571521', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921929', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571522', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921930', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571524', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921931', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571526', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921932', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765825', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921933', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765828', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921934', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765829', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921935', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765830', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921936', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590398100552683520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921937', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590417006180831232', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921938', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862656', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921939', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862657', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921940', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862658', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921941', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862660', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921942', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717120', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921943', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717121', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921944', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717122', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921945', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911424', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921946', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911425', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921947', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911426', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921948', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911427', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921949', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911429', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921950', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452416', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921951', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452417', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921952', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452418', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921953', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452419', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921954', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452421', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921955', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452423', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864163921956', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452424', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864962', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452425', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864963', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646720', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864964', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646721', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864965', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646722', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864966', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646723', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864967', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646724', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864968', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646725', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864969', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646727', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864970', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '592136133727621120', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864971', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442057', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864972', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442058', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864973', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442059', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864974', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442060', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864975', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636352', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864976', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636353', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864977', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636354', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864978', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636358', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864979', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636359', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864980', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636360', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864981', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636361', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864982', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636362', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864983', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830656', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864205864984', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830657', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168130', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830658', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168131', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830659', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168132', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830660', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168133', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410304', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168134', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410305', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168135', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410306', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168136', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410307', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168137', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410308', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168138', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410309', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168139', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410310', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168140', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410311', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168141', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410312', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168142', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410313', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168143', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410314', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168144', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604608', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168145', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604609', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168146', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604610', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168147', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604611', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168148', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558677798912', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168149', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558686187520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168150', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558686187521', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168151', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '598128304653996032', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864277168152', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537088', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362433', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537089', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362434', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537090', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362435', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537091', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362436', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537092', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362437', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '613640045747900416', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362438', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621379493888', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362439', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688192', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362440', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688193', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362441', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688194', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362442', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688195', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362443', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688196', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362444', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590081231815839745', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362445', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624962', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362446', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624963', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362447', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590082183939624964', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362448', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518954377221', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362449', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864281362450', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571521', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916866', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571522', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916867', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571524', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916868', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518958571526', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916869', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765825', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916870', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765828', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916871', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765829', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916872', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590395518962765830', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916873', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590398100552683520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916874', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590417006180831232', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916875', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862656', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916876', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862657', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916877', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862658', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916878', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590446794257862660', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916879', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717120', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916880', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717121', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916881', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633059717122', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916882', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911424', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916883', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911425', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916884', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911426', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916885', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911427', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916886', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590454633063911429', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916887', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452416', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864314916888', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452417', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864319111169', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452418', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864319111170', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452419', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864319111171', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452421', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864319111172', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452423', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864319111173', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452424', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864319111174', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406475452425', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864319111175', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646720', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499778', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646721', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499779', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646722', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499780', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646723', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499781', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646724', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499782', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646725', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499783', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '590478406479646727', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499784', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '592136133727621120', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499785', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442057', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499786', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442058', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499787', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442059', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499788', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761949442060', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499789', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636352', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499790', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636353', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499791', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636354', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499792', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636358', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499793', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636359', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499794', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636360', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499795', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636361', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499796', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761953636362', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499797', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830656', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499798', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830657', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499799', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830658', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499800', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830659', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499801', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '594756761957830660', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499802', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410304', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499803', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410305', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499804', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410306', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499805', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410307', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499806', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410308', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499807', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410309', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499808', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410310', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499809', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410311', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499810', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410312', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499811', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410313', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499812', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558669410314', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499813', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604608', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864327499814', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604609', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248514', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604610', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248515', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558673604611', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248516', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558677798912', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248517', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558686187520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248518', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '595094558686187521', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248519', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '598128304653996032', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248520', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537088', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248521', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537089', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248522', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537090', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248523', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537091', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248524', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '606303420856537092', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248525', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '613640045747900416', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248526', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621379493888', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248527', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688192', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248528', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688193', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248529', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688194', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248530', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688195', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1491649864365248531', NULL, NULL, '2022-02-10 13:46:55', '2022-02-10 13:46:55', 0, 0, '641607621383688196', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673289891841', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923777', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673289891842', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923778', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673289891843', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923779', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673289891844', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923780', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673289891845', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923776', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673419915265', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118081', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673419915266', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118080', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498178', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118083', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498179', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576319700992', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498180', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576319700993', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498181', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118082', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498182', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895297', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498183', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895298', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498184', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895296', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498185', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468097', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673432498186', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468098', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673470246913', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468096', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673470246914', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576684605441', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673470246915', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576684605440', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673570910211', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923777', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673570910212', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923778', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673570910213', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923779', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673570910214', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923780', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673570910215', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923776', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673596076034', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118081', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673596076035', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118080', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673596076036', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118083', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673612853250', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576319700992', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673612853251', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576319700993', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673612853252', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118082', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673612853253', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895297', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673612853254', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895298', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673612853255', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895296', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673612853256', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468097', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673612853257', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468098', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601986', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468096', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601987', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576684605441', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601988', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576684605440', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601989', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923777', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601990', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923778', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601991', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923779', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601992', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923780', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601993', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576302923776', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673650601994', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118081', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673692545026', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118080', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673692545027', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118083', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673692545028', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576319700992', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673692545029', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576319700993', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673692545030', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576307118082', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673717710849', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895297', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673717710850', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895298', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673726099458', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576323895296', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673726099459', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468097', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673726099460', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468098', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673726099461', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576638468096', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673726099462', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576684605441', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516698673789014017', NULL, NULL, '2022-04-20 16:41:57', '2022-04-20 16:41:57', 0, 0, '676677576684605440', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1516700816990277633', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590446794257862656', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700816990277634', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590446794257862657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700816990277635', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558673604610', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700816990277636', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558677798912', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700816990277637', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558673604611', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700816990277638', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '594756761949442057', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054850', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '594756761949442058', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054851', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590454633059717120', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054852', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590454633063911425', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054853', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590454633063911427', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054854', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590454633059717121', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054855', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590454633063911429', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054856', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590081231815839745', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054857', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590082183939624962', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054858', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '613640045747900416', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054859', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590082183939624964', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054860', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590478406475452416', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054861', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590478406475452421', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054862', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590478406475452417', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817007054863', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '606303420856537088', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414977', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '606303420856537089', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414978', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590478406475452424', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414979', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590478406479646720', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414980', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '641607621379493888', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414981', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '641607621383688192', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414982', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '641607621383688194', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414983', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558669410304', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414984', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558669410310', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414985', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558673604608', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414986', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558669410305', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414987', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558669410313', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414988', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558669410308', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414989', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '594756761953636358', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414990', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '594756761953636359', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817036414991', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '594756761957830656', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358017', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '594756761957830657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358018', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590395518954377221', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358019', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '598128304653996032', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358020', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590395518958571520', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358021', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558686187520', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358022', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '595094558686187521', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358023', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590395518958571526', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358024', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590395518962765825', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358025', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590395518962765828', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358026', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '592136133727621120', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358027', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590417006180831232', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1516700817078358028', NULL, NULL, '2022-04-20 16:50:28', '2022-04-20 16:50:28', 0, 0, '590395518962765830', '1447115588580159489');

-- ----------------------------
-- Table structure for t_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/菜单关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------
INSERT INTO `t_admin_role_menu` VALUES ('1432596175978037250', NULL, NULL, '2021-08-31 14:48:38', '2021-08-31 14:48:38', 0, 0, '1432596175629910017', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1432596175978037251', NULL, NULL, '2021-08-31 14:48:38', '2021-08-31 14:48:38', 0, 0, '1432596175629910017', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1432596175978037252', NULL, NULL, '2021-08-31 14:48:38', '2021-08-31 14:48:38', 0, 0, '1432596175629910017', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1432596175978037253', NULL, NULL, '2021-08-31 14:48:38', '2021-08-31 14:48:38', 0, 0, '1432596175629910017', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1432596175978037254', NULL, NULL, '2021-08-31 14:48:38', '2021-08-31 14:48:38', 0, 0, '1432596175629910017', '1297047088646905857');
INSERT INTO `t_admin_role_menu` VALUES ('1435124452189843458', NULL, NULL, '2021-09-07 14:15:06', '2021-09-07 14:15:06', 0, 0, '1430703281969082369', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1435124452189843459', NULL, NULL, '2021-09-07 14:15:06', '2021-09-07 14:15:06', 0, 0, '1430703281969082369', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1435124452189843460', NULL, NULL, '2021-09-07 14:15:06', '2021-09-07 14:15:06', 0, 0, '1430703281969082369', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1435124452189843461', NULL, NULL, '2021-09-07 14:15:06', '2021-09-07 14:15:06', 0, 0, '1430703281969082369', '1297047088646905857');
INSERT INTO `t_admin_role_menu` VALUES ('1435124452198232066', NULL, NULL, '2021-09-07 14:15:06', '2021-09-07 14:15:06', 0, 0, '1430703281969082369', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480765538306', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1440255471893200897');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480773926914', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1461987433667141634');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480773926915', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1452091447254749186');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121217', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1452091513113710594');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121218', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1456054437146644481');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121219', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1440255602914869250');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121220', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1440271162033684482');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121221', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1459712656557576194');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121222', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1442156484086480897');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121223', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1442156599396286466');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121224', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1440255716299489282');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121225', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1442156557235142657');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480778121226', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1449764190750285826');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480782315521', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1459850402525622274');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480782315522', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1462256665587916801');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480782315523', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1451979503835369474');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480782315524', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1468415037767946242');
INSERT INTO `t_admin_role_menu` VALUES ('1481634480782315525', NULL, NULL, '2022-01-13 22:29:20', '2022-01-13 22:29:20', 0, 0, '1447115588580159489', '1462261436877152258');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631490', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1440255471893200897');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631491', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1461987433667141634');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631492', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1452091447254749186');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631493', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1452091513113710594');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631494', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1456054437146644481');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631495', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1440255602914869250');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631496', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1440271162033684482');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631497', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1459712656557576194');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631498', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1442156484086480897');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631499', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1442156599396286466');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631500', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1440255716299489282');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631501', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1442156557235142657');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889357631502', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1449764190750285826');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889366020098', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1459850402525622274');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889366020099', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1462256665587916801');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889366020100', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1451979503835369474');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889366020101', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1468415037767946242');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889366020102', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1462261436877152258');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889366020103', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1440256392576483330');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889366020104', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1440256481906769922');
INSERT INTO `t_admin_role_menu` VALUES ('1481641889378603010', NULL, NULL, '2022-01-13 22:58:46', '2022-01-13 22:58:46', 0, 0, '1443467633444806658', '1450796419538522114');
INSERT INTO `t_admin_role_menu` VALUES ('1481642096300396546', NULL, NULL, '2022-01-13 22:59:36', '2022-01-13 22:59:36', 0, 0, '1443467633444806658', '1481642095692222465');
INSERT INTO `t_admin_role_menu` VALUES ('1482970741279297537', NULL, NULL, '2022-01-17 14:59:11', '2022-01-17 14:59:11', 0, 0, '1443467633444806658', '1482970741228965889');
INSERT INTO `t_admin_role_menu` VALUES ('1482970818743898114', NULL, NULL, '2022-01-17 14:59:30', '2022-01-17 14:59:30', 0, 0, '1443467633444806658', '1482970818676789249');
INSERT INTO `t_admin_role_menu` VALUES ('1482970868194742273', NULL, NULL, '2022-01-17 14:59:41', '2022-01-17 14:59:41', 0, 0, '1443467633444806658', '1482970868152799234');
INSERT INTO `t_admin_role_menu` VALUES ('1482970903900852225', NULL, NULL, '2022-01-17 14:59:50', '2022-01-17 14:59:50', 0, 0, '1443467633444806658', '1482970903854714882');
INSERT INTO `t_admin_role_menu` VALUES ('1482979903388258306', NULL, NULL, '2022-01-17 15:35:36', '2022-01-17 15:35:36', 0, 0, '1443467633444806658', '1482979903354703874');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344514', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1457369967249879042');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344515', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1457370029065531394');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344516', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1482979903354703874');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344517', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1457372083897004033');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344518', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1457370075530031105');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344519', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1482970741228965889');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344520', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1482970818676789249');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344521', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1482970868152799234');
INSERT INTO `t_admin_role_menu` VALUES ('1483412338865344522', NULL, NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '1483412338848567297', '1482970903854714882');
INSERT INTO `t_admin_role_menu` VALUES ('1483415755897704450', NULL, NULL, '2022-01-18 20:27:31', '2022-01-18 20:27:31', 0, 0, '1483415755868344322', '1482970868152799234');
INSERT INTO `t_admin_role_menu` VALUES ('1483415755897704451', NULL, NULL, '2022-01-18 20:27:31', '2022-01-18 20:27:31', 0, 0, '1483415755868344322', '1482970903854714882');
INSERT INTO `t_admin_role_menu` VALUES ('1483415755897704452', NULL, NULL, '2022-01-18 20:27:31', '2022-01-18 20:27:31', 0, 0, '1483415755868344322', '1457369967249879042');
INSERT INTO `t_admin_role_menu` VALUES ('1483415755897704453', NULL, NULL, '2022-01-18 20:27:31', '2022-01-18 20:27:31', 0, 0, '1483415755868344322', '1482970741228965889');
INSERT INTO `t_admin_role_menu` VALUES ('1516699625996685313', NULL, NULL, '2022-04-20 16:45:44', '2022-04-20 16:45:44', 0, 0, '1443467633444806658', '1516699625820524545');
INSERT INTO `t_admin_role_menu` VALUES ('1516699798852341761', NULL, NULL, '2022-04-20 16:46:25', '2022-04-20 16:46:25', 0, 0, '1443467633444806658', '1516699798743289857');
INSERT INTO `t_admin_role_menu` VALUES ('1516699922781442050', NULL, NULL, '2022-04-20 16:46:55', '2022-04-20 16:46:55', 0, 0, '1443467633444806658', '1516699922710138882');

-- ----------------------------
-- Table structure for t_admin_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_user`;
CREATE TABLE `t_admin_role_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/用户关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_role_user
-- ----------------------------
INSERT INTO `t_admin_role_user` VALUES ('1476437959124643841', NULL, NULL, '2021-12-30 14:20:14', '2021-12-30 14:20:14', 0, 0, '1476437958340308994', '1447115588580159489');
INSERT INTO `t_admin_role_user` VALUES ('1481890636197007362', NULL, NULL, '2022-01-14 15:27:14', '2022-01-14 15:27:14', 0, 0, '1460427339745763329', '1447115588580159489');
INSERT INTO `t_admin_role_user` VALUES ('1482965188683501570', NULL, NULL, '2022-01-17 14:37:07', '2022-01-17 14:37:07', 0, 0, '1', '1443467633444806658');
INSERT INTO `t_admin_role_user` VALUES ('1483413837825708033', NULL, NULL, '2022-01-18 20:19:54', '2022-01-18 20:19:54', 0, 0, '643392744110297088', '1483412338848567297');
INSERT INTO `t_admin_role_user` VALUES ('1483416303262765058', NULL, NULL, '2022-01-18 20:29:41', '2022-01-18 20:29:41', 0, 0, '643395209551548416', '1483415755868344322');

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号/用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `gender` int(1) NOT NULL DEFAULT 0 COMMENT '性别 (0-未知 1-男 2-女)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 (0-否，1-是)',
  `organ_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司/部门id,多层级前端自行分割数据',
  `position` int(1) NOT NULL DEFAULT 0 COMMENT '职位 (字典code)',
  `terminal` int(1) NOT NULL DEFAULT 1 COMMENT '终端 (字段code)',
  `head` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号(第二账号)',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `reg_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `wx_open_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信公众号openId (需进行h5授权获得, 用于给管理端用户发送微信模板信息)',
  `remarks` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--系统用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', NULL, '-1', '2020-08-05 07:11:04', '2022-04-20 16:42:03', 0, 10, 'admin', 'd9880822dd584adce3cde4b024776eef', 1, 0, '1443501889504210946,1443502090977603585,1443502249136418817', 0, 1, 'http://xijia.plus/oss/file/image/head/20200822150143006266-5.png', '10000', '平台主账号', '四川成都', 22, '2020-08-05 15:11:05', '2022-04-20 16:42:04', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('1460427339745763329', '1', NULL, '2021-11-16 09:59:45', '2022-03-05 23:21:08', 0, 0, 'test', '992171f4f472ae8360a32663d9529339', 1, 0, '1443501889504210946,1443502090977603585,1443502302433439746', 2, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/44592984-timg(9).jpg', '17600000001', 'test', '0', 0, '2021-11-16 09:59:46', '2022-03-05 23:21:13', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('642946758053335040', '1', NULL, '2022-01-17 14:47:42', '2022-01-18 20:09:53', 1, 0, 'admin', 'c7527eb5cc20388af7d28ba39b4cd8e1', 1, 0, '1443501889504210946,1443502157943861250,1443502428644241409', 0, 2, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/46334880-224204-1620744124ecd7.jpg', '17628689969', '扩展端1主账号', '四川成都', 22, '2022-01-17 14:47:43', '2022-01-17 16:24:39', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('642965648774926336', '642946758053335040', NULL, '2022-01-17 16:02:46', '2022-01-18 20:09:55', 1, 0, 'test1', '8a8d799008a6164b36d55083b1e11076', 0, 0, '1443501889504210946,1443502090977603585,1443502302433439746', 2, 2, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/06914860-234759-16363000792588.jpg', '17628689968', '测试', '四川成都', 22, '2022-01-17 16:02:47', '2022-01-17 16:03:29', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('643392744110297088', '1', NULL, '2022-01-18 20:19:54', '2022-02-15 22:03:48', 0, 0, 'admin', '536f67aae1fbc775018124ccc866b30c', 1, 0, '1481913070589579265,1481913127925714945,1481913213086863362', 0, 2, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/59955584-timg(5).jpg', '17628680000', '兮家小二', '四川成都', 22, '2022-01-18 20:19:54', '2022-02-15 22:03:49', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('643395209551548416', '643392744110297088', NULL, '2022-01-18 20:29:41', '2022-01-18 20:30:19', 0, 0, 'xiaoer', '9c9809e34b0516c6719bb6bcc4c0d695', 2, 0, '1443501889504210946,1468426496627490818', 2, 2, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/50543987-qs44ufe2024qs44ufe2024.jpg', '17628680001', '小二', '四川', 22, '2022-01-18 20:29:42', '2022-01-18 20:30:20', NULL, NULL);

-- ----------------------------
-- Table structure for t_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_basic`;
CREATE TABLE `t_basic`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统通用字段表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_basic
-- ----------------------------

-- ----------------------------
-- Table structure for t_gc_test
-- ----------------------------
DROP TABLE IF EXISTS `t_gc_test`;
CREATE TABLE `t_gc_test`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称 (文本)',
  `age` double(10, 2) NULL DEFAULT NULL COMMENT '年龄 (数字)',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别 (单选--字典)',
  `like` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '爱好 (多选--字典)',
  `city` int(1) NULL DEFAULT NULL COMMENT '城市 (下拉选--字典)',
  `disable` int(1) NULL DEFAULT NULL COMMENT '禁用 (开关--字典)',
  `head_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像 (文件上传)',
  `time` datetime NULL DEFAULT NULL COMMENT '时间',
  `text` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更多信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成测试表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_gc_test
-- ----------------------------
INSERT INTO `t_gc_test` VALUES ('1456780575175118849', NULL, NULL, '2021-11-06 08:28:48', '2021-11-09 21:32:51', 1, 0, '兮家小二', 20.00, 3, '3,2', 2, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/32859734-timg(3).jpg', '2021-11-06 08:28:31', '啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦');
INSERT INTO `t_gc_test` VALUES ('1458065155967528961', NULL, NULL, '2021-11-09 21:33:17', '2022-01-13 22:09:16', 1, 0, '测试', 22.00, 2, '2,3', 3, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/09798637-timg(2).jpg', '2021-11-09 09:33:11', '啦啦啦啦啦啦啦');
INSERT INTO `t_gc_test` VALUES ('1475676902860955649', NULL, NULL, '2021-12-28 11:56:04', '2022-01-13 22:09:19', 1, 0, '测试数据1', 23.00, 2, '2,3', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/56163330-6226ba315230576c0c8c99a6c1fbc4b7.jpeg', '2021-12-28 11:56:00', '测试数据');
INSERT INTO `t_gc_test` VALUES ('1481629780875939841', NULL, NULL, '2022-01-13 22:10:40', '2022-01-13 22:11:50', 0, 0, '代码生成测试', 221.00, 1, '2,1', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/01628736-qs44ufe2024qs44ufe2024.jpg', '2022-01-13 10:10:07', 'asjdasnjkasnkdnaskdkasa');
INSERT INTO `t_gc_test` VALUES ('1482723544927358977', NULL, NULL, '2022-01-16 22:36:53', '2022-01-25 14:55:24', 1, 0, '测试', 22.00, 2, '1,2', 2, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/49117698-timg.jpg', '2022-01-16 18:36:52', '测试');
INSERT INTO `t_gc_test` VALUES ('1485868996178743298', NULL, NULL, '2022-01-25 14:55:49', '2022-04-20 16:53:27', 0, 0, '111', 0.00, 1, '1', 1, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/24735070-234308-16202293885efd.jpg', '2022-01-26 04:00:00', '111');

-- ----------------------------
-- Table structure for t_xj_admin_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_banner`;
CREATE TABLE `t_xj_admin_banner`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `position` int(2) NOT NULL COMMENT '位置(字典code)',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'banner标题',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'banner描叙',
  `img_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'banner图片',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT 'banner排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT 'banner禁用(0-启用 1-禁用)',
  `is_skip` int(1) NOT NULL DEFAULT 0 COMMENT '是否跳转(0-无  1-内部链接 2-外部链接)',
  `skip_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转地址url(地址直接添加或字典表配置)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--banner' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_xj_admin_banner
-- ----------------------------
INSERT INTO `t_xj_admin_banner` VALUES ('1300260217146548226', NULL, NULL, '2020-08-31 10:32:48', '2021-12-23 10:27:07', 0, 0, 1, '测试2', '测试数据2', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210401214853224489-qs44ufe2024qs44ufe2024.jpg', 1, 1, 0, '/page/logoBanner/page/logoBanner');
INSERT INTO `t_xj_admin_banner` VALUES ('1300262684328435714', NULL, NULL, '2020-08-31 10:42:36', '2021-03-22 10:38:07', 0, 0, 1, '测试1', '测试数据一', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210322103754968659-aaaa.png', 0, 0, 2, 'http://www.baidu.com');
INSERT INTO `t_xj_admin_banner` VALUES ('1309111625118248961', NULL, NULL, '2020-09-24 20:45:06', '2021-02-05 15:36:11', 0, 0, 1, '测试', '测试描叙', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/2.jpg', 0, 0, 0, '');
INSERT INTO `t_xj_admin_banner` VALUES ('1369571919208206338', NULL, NULL, '2021-03-10 16:52:44', '2021-11-14 18:58:41', 1, 0, 1, '1', '1', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210310165236917290-5.jpg', 0, 0, 1, 'bbb');
INSERT INTO `t_xj_admin_banner` VALUES ('1451984726834319362', NULL, NULL, '2021-10-24 02:51:50', '2021-10-24 02:51:54', 1, 0, 1, '2', '3', '4', 5, 6, 7, '8');
INSERT INTO `t_xj_admin_banner` VALUES ('1459838027261095938', NULL, NULL, '2021-11-14 18:58:02', '2022-03-05 21:31:53', 0, 0, 1, '测试', '啦啦啦啦', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/41883812-timg(2).jpg', 0, 0, 0, '');

-- ----------------------------
-- Table structure for t_xj_admin_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_blacklist`;
CREATE TABLE `t_xj_admin_blacklist`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `type` int(1) NOT NULL COMMENT '1-白名单(* 表示允许除黑名单外的所有ip请求, 否则只允许白名单中的ip请求) 2-黑名单(禁止ip访问)',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '白名单ip/黑名单ip',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--黑名单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_xj_admin_blacklist
-- ----------------------------
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332333202949324802', NULL, NULL, '2020-11-27 22:39:21', '2021-11-21 11:21:10', 0, 0, 1, '*', '允许所有 ip 地址访问，优先级比黑名单(*) 高 ， 开启了白名单(*), 黑名单（*）将无效', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332337401510551554', NULL, NULL, '2020-11-27 22:56:02', '2021-11-21 11:18:55', 0, 0, 2, '*', '禁止所有 ip 访问,除本地 [127.0.0.1 / localhost] ,不建议配置在所有资源上，一旦配置，所有用户(包括自己) 将无法访问所有资源，因为每个用户的ip地址都不一样， 开启此功能需提前配置所有用户的ip地址为白名单', 1);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1421369811404894210', NULL, NULL, '2021-07-31 15:19:05', '2022-01-01 23:05:48', 0, 0, 2, '192.168.1.10', '本地', 1);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1462259668311121921', NULL, NULL, '2021-11-21 11:20:47', '2021-12-09 13:48:18', 1, 0, 1, '1', '1', 0);

-- ----------------------------
-- Table structure for t_xj_admin_config
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_config`;
CREATE TABLE `t_xj_admin_config`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置code|搜索值(不能重复)',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置内容',
  `sort` int(11) NOT NULL COMMENT '排序',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '类型(0-文本 1-图片 2-开关)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--全局数据配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_xj_admin_config
-- ----------------------------
INSERT INTO `t_xj_admin_config` VALUES ('1365174805250260994', NULL, '-1', '2021-02-26 13:40:11', '2021-09-15 22:15:48', 0, 0, 'entry_name', 'spring-boot-plus2', 'spring-boot-plus2-通用后台管理系统', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365182627308433409', NULL, '-1', '2021-02-26 14:11:17', '2021-12-09 13:41:30', 1, 0, 'login_bg_img', '背景图(登录页)', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210311113615990505-1.jpg', 0, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1365185332319997953', NULL, '-1', '2021-02-26 14:22:01', '2021-12-09 13:47:22', 1, 0, 'beian', '备案号(登录页)', '备案号：蜀ICP备19022468号-1', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365187122549551105', NULL, '-1', '2021-02-26 14:29:09', '2021-12-09 13:47:18', 1, 0, 'project_desc', '项目描叙(登录页)', '©2020-2021 该后台系统为个人开发运营，作者联系方式 QQ:1720696548', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1369572634420924417', NULL, '0', '2021-03-10 16:55:34', '2021-12-09 13:45:59', 1, 0, 'test', '测试数据', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/00717589-timg(2).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/13054806-timg(1).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/16142646-timg(3).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/19460031-timg(7).jpg', 3, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1383627414470467586', NULL, '-1', '2021-04-18 11:44:16', '2022-01-13 22:30:58', 0, 0, 'is_sign', '验签开关 ( true / false) -默认true', 'true', 0, 2);
INSERT INTO `t_xj_admin_config` VALUES ('1383636872395255809', NULL, '-1', '2021-04-18 12:21:51', '2022-01-01 18:13:23', 0, 0, 'is_swagger', 'swagger文档开关(true / false)', 'true', 0, 2);
INSERT INTO `t_xj_admin_config` VALUES ('1383644845431689218', NULL, '-1', '2021-04-18 12:53:32', '2021-12-09 13:41:51', 1, 0, 'is_login_token', '访问登录页是否需要令牌', 'false', 0, 2);
INSERT INTO `t_xj_admin_config` VALUES ('1432597381643304961', NULL, '-1', '2021-08-31 14:53:26', '2021-12-09 13:40:36', 0, 0, 'is_auth', 'api 接口是否需要验权(true / false)', 'true', 0, 2);
INSERT INTO `t_xj_admin_config` VALUES ('1441701074921598977', NULL, '-1', '2021-09-25 17:48:16', '2022-01-04 15:19:47', 0, 0, 'login_expiration_manage', '管理端登录令牌有效期 (单位分)', '60', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1481920988265320449', NULL, NULL, '2022-01-14 17:27:51', '2022-01-14 17:27:51', 0, 0, '测试数据', 'test', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/45321474-232917-1636990157bc70.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/47041365-234759-16363000792588.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/49545567-193708-15779650288588.jpg', 0, 1);

-- ----------------------------
-- Table structure for t_xj_admin_datasource
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_datasource`;
CREATE TABLE `t_xj_admin_datasource`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `db_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库标题',
  `db_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库名',
  `db_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库连接',
  `db_username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库账号',
  `db_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库密码',
  `db_prefix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据表前缀',
  `db_field_prefix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据字段前缀',
  `db_general_field` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库通用字段,逗号分隔',
  `pack_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '包根路径',
  `modules` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父模块名',
  `modules_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子模块名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--代码生成动态数据源' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_xj_admin_datasource
-- ----------------------------
INSERT INTO `t_xj_admin_datasource` VALUES ('1430451154784948226', NULL, NULL, '2021-08-25 16:45:07', '2021-08-25 16:45:07', 0, 0, 'spring-boot-plus2', 'spring-boot-plus2', '127.0.0.1', 'root', '123456', '3', '4', '', '', '', '');

-- ----------------------------
-- Table structure for t_xj_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_log`;
CREATE TABLE `t_xj_admin_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求人',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求人Id (根据端来区分)',
  `type` int(1) NULL DEFAULT NULL COMMENT '请求终端(字典code, 如 0-管理端 1-用户端 -1-其他)',
  `referer` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求来源',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求uri',
  `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户真实Ip',
  `host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户主机名',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式(post-get)',
  `server_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器地址',
  `port` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器端口',
  `package_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求包',
  `class_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类',
  `class_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类--swagger注释',
  `method_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法--swagger注释',
  `request_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求数据',
  `response_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回数据',
  `state` int(1) NULL DEFAULT 0 COMMENT '1-请求成功 0-请求失败',
  `execute_time` bigint(20) NULL DEFAULT NULL COMMENT '程序响应总耗时',
  `business_time` bigint(20) NULL DEFAULT NULL COMMENT '业务执行总耗时',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--请求记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_xj_admin_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_xj_admin_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_admin_msg`;
CREATE TABLE `t_xj_admin_msg`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息接收人',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容( 根据消息类型区分数据-str || json)',
  `user_type` int(1) NOT NULL COMMENT '通知终端: 1-用户端信息 2-管理端消息',
  `msg_type` int(11) NOT NULL COMMENT '消息类型:  1-系统通知  2-订单业务通知 ',
  `is_read` int(1) NOT NULL DEFAULT 0 COMMENT '是否已读(0-未读 1-已读)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--消息通知' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_xj_admin_msg
-- ----------------------------
INSERT INTO `t_xj_admin_msg` VALUES ('1468559672842653698', NULL, NULL, '2021-12-08 20:34:45', '2021-12-09 16:27:34', 0, 0, '1', '测试消息', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1468560203682156546', NULL, NULL, '2021-12-08 20:36:51', '2022-02-10 22:55:54', 0, 0, '1460427339745763329', '你只是一个小测试人员，没有修改数据的权限，知道嘛', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1468560484188819457', NULL, NULL, '2021-12-08 20:37:58', '2022-02-10 22:55:57', 0, 0, '1460427339745763329', '但是你有生成预览代码，和生成vue代码的权限哦，嘻哈，快去尝试一下吧', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1468562513732833282', NULL, NULL, '2021-12-08 20:46:02', '2021-12-09 16:10:37', 0, 0, '1', '发一条测试消息给自己', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1468569468480262145', NULL, NULL, '2021-12-08 21:13:40', '2021-12-09 16:27:32', 0, 0, '1', '啦啦啦啦', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1470672728192192513', NULL, NULL, '2021-12-14 16:31:16', '2021-12-30 11:48:21', 0, 0, '1', '1111', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1471490852424519682', NULL, NULL, '2021-12-16 22:42:12', '2021-12-30 11:48:13', 0, 0, '1', '啦啦啦啦', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1481612239579385858', NULL, NULL, '2022-01-13 21:00:59', '2022-01-13 21:00:59', 0, 0, '1', '测试', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1481618032873771009', NULL, NULL, '2022-01-13 21:23:59', '2022-01-13 21:23:59', 0, 0, '1', '我是一条测试消息', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1481620125940518913', NULL, NULL, '2022-01-13 21:32:18', '2022-01-13 21:33:01', 0, 0, '1', '我是测试消息', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1493529203935547394', NULL, NULL, '2022-02-15 18:14:45', '2022-02-15 18:14:45', 0, 0, '643395209551548416', '23423423424', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1493529263423361025', NULL, NULL, '2022-02-15 18:14:59', '2022-02-23 15:11:49', 0, 0, '1460427339745763329', '微软微软', 2, 0, 1);

-- ----------------------------
-- Table structure for t_xj_pay_record
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_pay_record`;
CREATE TABLE `t_xj_pay_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `trade_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易号 ( 发生第三方交易时生成)',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号 ( 对应各订单表)',
  `platform_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '平台手续费(元)',
  `channel_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '第三方手续费(元), 为0时,手续费累加在 platform_fee',
  `money_total` decimal(10, 2) NULL DEFAULT NULL COMMENT '交易总金额( 元)',
  `money_surplus` decimal(10, 2) NULL DEFAULT NULL COMMENT '剩余金额( 如存在子商户, 则为子商户实际收入)',
  `pay_state` int(1) NULL DEFAULT NULL COMMENT '交易状态( 0-已发起 1-回调成功(临时状态) 2-交易失败 3-交易成功 4-订单异常 )',
  `pay_channel` int(1) NULL DEFAULT NULL COMMENT '支付渠道( 字典code 如1-支付宝 2-微信 3-银行卡 等)',
  `pay_type` int(1) NULL DEFAULT NULL COMMENT '支付类型( 字典code 如 1-支付 2-充值 3-退款 等)',
  `business_type` int(1) NULL DEFAULT NULL COMMENT '业务类型( 字典code -根据系统业务定制 如  1-用户支付  2-平台打款 等)',
  `business_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务描叙( 追踪具体业务)',
  `request_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求数据',
  `response_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应数据',
  `callback_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `error_remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '支付失败备注(下单异常信息,失败异常信息)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方支付记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_xj_pay_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_xj_pay_wallet_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_pay_wallet_flow`;
CREATE TABLE `t_xj_pay_wallet_flow`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '账号id (用户id或商家id  |  平台总账账号为0)',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `money_after` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '用户剩余金额 (交易后金额,如没有钱包默认0)',
  `money` decimal(10, 2) NOT NULL COMMENT '收入支出金额(正数)',
  `wallet_type` int(1) NOT NULL COMMENT '流水类型(1-收入 2-支出)',
  `business_type` int(1) NOT NULL COMMENT '业务类型( 字段code)',
  `business_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务描叙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单/流水/支付流水表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_xj_pay_wallet_flow
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
