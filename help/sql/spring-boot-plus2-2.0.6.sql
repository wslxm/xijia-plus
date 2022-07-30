/*
 Navicat Premium Data Transfer

 Source Server         : 39.103.135.29(姐)
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-8vbkpoec225c821d8vo.mysql.zhangbei.rds.aliyuncs.com:3306
 Source Schema         : spring-boot-plus2

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 30/07/2022 13:29:07
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
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '权限类Id(方法与类/层级关系展示)',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式(GET/POST/PUT/DELETE)',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限url',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限备注信息',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  `type` int(1) NOT NULL COMMENT '终端(字典code, 如 0-管理端 1-用户端 更多待定)',
  `state` int(1) NOT NULL COMMENT '授权状态(字典code   -1-表示类 0-无需登录 1-需登录 2-需登录+授权 )',
  `is_sign` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要验签(不受限于登录授权)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--权限接口' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES ('590081231815839745', NULL, NULL, '2021-09-16 17:39:00', '2021-09-16 17:39:00', 0, 901, '0', '', '/api/admin/generate', 'base--gc--代码生成', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590082183939624962', NULL, NULL, '2021-09-16 17:42:47', '2021-10-08 22:22:16', 0, 900, '590081231815839745', 'GET', '/api/admin/generate/getPath', '代码生成路径', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624963', NULL, NULL, '2021-09-16 17:42:47', '2021-10-08 22:22:16', 0, 900, '590081231815839745', 'POST', '/api/admin/generate/generateCode', '生成代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624964', NULL, NULL, '2021-09-16 17:42:47', '2021-10-08 22:22:15', 0, 900, '590081231815839745', 'POST', '/api/admin/generate/preview', '生成预览代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518954377221', NULL, NULL, '2021-09-17 14:27:52', '2021-09-17 14:27:52', 0, 867, '0', '', '/api/admin/xj/config', 'base--plus--全局配置', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518958571520', NULL, NULL, '2021-09-17 14:27:52', '2021-10-08 22:22:12', 0, 867, '590395518954377221', 'GET', '/api/admin/xj/config/list', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571521', NULL, NULL, '2021-09-17 14:27:52', '2021-10-08 22:22:14', 0, 867, '590395518954377221', 'POST', '/api/admin/xj/config', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571522', NULL, NULL, '2021-09-17 14:27:52', '2021-10-08 22:22:13', 0, 867, '590395518954377221', 'DELETE', '/api/admin/xj/config/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571524', NULL, NULL, '2021-09-17 14:27:52', '2021-10-08 22:22:12', 0, 867, '590395518954377221', 'PUT', '/api/admin/xj/config/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571526', NULL, NULL, '2021-09-17 14:27:52', '2021-09-17 14:27:52', 0, 867, '0', '', '/api/admin/xj/log', 'base--plus--操作记录', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765825', NULL, NULL, '2021-09-17 14:27:52', '2021-10-08 22:21:57', 0, 867, '590395518958571526', 'GET', '/api/admin/xj/log/list', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518962765828', NULL, NULL, '2021-09-17 14:27:52', '2021-09-17 14:27:52', 0, 867, '0', '', '/api/admin/xj/msg', 'base--plus--消息通知', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765829', NULL, NULL, '2021-09-17 14:27:52', '2022-06-23 23:19:52', 0, 867, '590395518962765828', 'POST', '/api/admin/xj/msg', '添加/发送消息', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765830', NULL, NULL, '2021-09-17 14:27:52', '2021-10-08 22:22:10', 0, 867, '590395518962765828', 'GET', '/api/admin/xj/msg/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590398100552683520', NULL, NULL, '2021-09-17 14:38:08', '2021-10-08 22:22:09', 0, 866, '590395518962765828', 'PUT', '/api/admin/xj/msg/{id}/read', '消息修改为已读', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590417006180831232', NULL, NULL, '2021-09-17 15:53:15', '2021-10-08 22:22:10', 0, 857, '590395518962765828', 'GET', '/api/admin/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862656', NULL, NULL, '2021-09-17 17:51:37', '2021-09-17 17:51:37', 0, 848, '0', '', '/api/admin/authority', 'base--admin--URL权限管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590446794257862657', NULL, NULL, '2021-09-17 17:51:37', '2022-07-28 06:03:21', 0, 848, '590446794257862656', 'GET', '/api/admin/authority/list', '查询所有-接口管理', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862658', NULL, NULL, '2021-09-17 17:51:37', '2022-07-28 06:03:23', 0, 848, '590446794257862656', 'PUT', '/api/admin/authority/{id}', 'ID编辑', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862660', NULL, NULL, '2021-09-17 17:51:37', '2022-07-28 06:03:20', 0, 848, '590446794257862656', 'PUT', '/api/admin/authority/refreshAuthority', '扫描权限', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717120', NULL, NULL, '2021-09-17 18:22:46', '2021-09-17 18:22:46', 0, 845, '0', '', '/api/admin/dictionary', 'base--admin--字典管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590454633059717121', NULL, NULL, '2021-09-17 18:22:46', '2021-10-08 22:21:40', 0, 845, '590454633059717120', 'GET', '/api/admin/dictionary/list', '列表查询 (默认返回Tree数据,可指定Tree或List)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717122', NULL, NULL, '2021-09-17 18:22:46', '2021-10-08 22:21:43', 0, 845, '590454633059717120', 'POST', '/api/admin/dictionary', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911424', NULL, NULL, '2021-09-17 18:22:46', '2021-10-08 22:21:41', 0, 845, '590454633059717120', 'DELETE', '/api/admin/dictionary/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911425', NULL, NULL, '2021-09-17 18:22:46', '2022-07-28 06:03:14', 0, 845, '590454633059717120', 'GET', '/api/admin/dictionary/findCodeGroup', '查询所有-code分组', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911426', NULL, NULL, '2021-09-17 18:22:46', '2021-10-08 22:21:39', 0, 845, '590454633059717120', 'PUT', '/api/admin/dictionary/{id}', '编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911427', NULL, NULL, '2021-09-17 18:22:46', '2022-07-28 06:03:28', 0, 845, '590454633059717120', 'GET', '/api/admin/dictionary/generateEnum', '生成枚举', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911429', NULL, NULL, '2021-09-17 18:22:46', '2021-10-08 22:21:40', 0, 845, '590454633059717120', 'GET', '/api/admin/dictionary/list/category', '获取类别(级联数据)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452416', NULL, NULL, '2021-09-17 19:57:14', '2021-09-17 19:57:14', 0, 840, '0', '', '/api/admin/menu', 'base--admin--菜单管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452417', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:58', 0, 840, '590478406475452416', 'GET', '/api/admin/menu/list', '列表查询(不支持分页)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452418', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:58', 0, 840, '590478406475452416', 'POST', '/api/admin/menu', '菜单添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452419', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:59', 0, 840, '590478406475452416', 'PUT', '/api/admin/menu/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452421', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:59', 0, 840, '590478406475452416', 'GET', '/api/admin/menu/findTree', '左导航菜单', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452423', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:57', 0, 840, '590478406475452416', 'DELETE', '/api/admin/menu/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452424', NULL, NULL, '2021-09-17 19:57:14', '2021-09-17 19:57:14', 0, 840, '0', '', '/api/admin/role', 'base--admin--角色管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452425', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:47', 0, 840, '590478406475452424', 'POST', '/api/admin/role', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646720', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:46', 0, 840, '590478406475452424', 'GET', '/api/admin/role/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646721', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:45', 0, 840, '590478406475452424', 'PUT', '/api/admin/role/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646722', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:46', 0, 840, '590478406475452424', 'PUT', '/api/admin/role/updRoleAuth', '角色的URL权限分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646723', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:48', 0, 840, '590478406475452424', 'PUT', '/api/admin/role/updRoleAuthAll', '所有角色拥有所有权限', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646724', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:50', 0, 840, '590478406475452424', 'PUT', '/api/admin/role/updRoleMenu', '角色的菜单分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646725', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:48', 0, 840, '590478406475452424', 'PUT', '/api/admin/role/updUserRole', '用户的角色分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646727', NULL, NULL, '2021-09-17 19:57:14', '2021-10-08 22:21:47', 0, 840, '590478406475452424', 'DELETE', '/api/admin/role/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('592136133727621120', NULL, NULL, '2021-09-22 09:44:28', '2021-10-08 22:22:08', 0, 782, '590395518962765828', 'GET', '/api/admin/xj/msg/findAllNum', '查询全部/已读/未读数量(当前登录用户)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761932664832', NULL, NULL, '2021-09-29 15:17:51', '2021-09-29 15:17:51', 0, 741, '0', '', '/api/client/dictionary', 'yh--base--字典管理', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664833', NULL, NULL, '2021-09-29 15:17:51', '2021-09-29 15:17:51', 0, 741, '594756761932664832', 'GET', '/api/client/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664834', NULL, NULL, '2021-09-29 15:17:51', '2022-01-10 18:43:01', 0, 741, '594756761932664832', 'GET', '/api/client/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664835', NULL, NULL, '2021-09-29 15:17:51', '2021-09-29 15:17:51', 0, 741, '0', '', '/api/client/xj/banner', 'yh--base-plus--banner', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442057', NULL, NULL, '2021-09-29 15:17:51', '2021-09-29 15:17:51', 0, 741, '0', '', '/api/admin/datasource', 'base--gc--代码生成--数据源维护', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442058', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:21:55', 0, 741, '594756761949442057', 'GET', '/api/admin/datasource/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442059', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:21:54', 0, 741, '594756761949442057', 'POST', '/api/admin/datasource', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442060', NULL, NULL, '2021-09-29 15:17:51', '2021-12-31 00:05:54', 0, 741, '594756761949442057', 'PUT', '/api/admin/datasource/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636352', NULL, NULL, '2021-09-29 15:17:51', '2021-12-13 19:21:00', 0, 741, '594756761949442057', 'DELETE', '/api/admin/datasource/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636354', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:21:55', 0, 741, '594756761949442057', 'PUT', '/api/admin/datasource/{id}/updPwd', '修改/重置密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636358', NULL, NULL, '2021-09-29 15:17:51', '2021-09-29 15:17:51', 0, 741, '0', '', '/api/admin/xj/banner', 'base--plus--banner', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761953636359', NULL, NULL, '2021-09-29 15:17:51', '2022-03-14 08:31:01', 0, 741, '594756761953636358', 'GET', '/api/admin/xj/banner/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636360', NULL, NULL, '2021-09-29 15:17:51', '2022-07-28 06:03:31', 0, 741, '594756761953636358', 'POST', '/api/admin/xj/banner', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636361', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:22:02', 0, 741, '594756761953636358', 'PUT', '/api/admin/xj/banner/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636362', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:22:01', 0, 741, '594756761953636358', 'DELETE', '/api/admin/xj/banner/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830656', NULL, NULL, '2021-09-29 15:17:51', '2021-09-29 15:17:51', 0, 741, '0', '', '/api/admin/xj/blacklist', 'base--plus--黑名单', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761957830657', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:21:51', 0, 741, '594756761957830656', 'GET', '/api/admin/xj/blacklist/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830658', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:21:51', 0, 741, '594756761957830656', 'POST', '/api/admin/xj/blacklist', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830659', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:21:51', 0, 741, '594756761957830656', 'PUT', '/api/admin/xj/blacklist/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830660', NULL, NULL, '2021-09-29 15:17:51', '2021-10-08 22:21:50', 0, 741, '594756761957830656', 'DELETE', '/api/admin/xj/blacklist/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558656827392', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '0', '', '/api/client/xj/config', 'yh--base-plus--全局配置', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827393', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '595094558656827392', 'GET', '/api/client/xj/config/findByCode', 'CODE查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827394', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '0', '', '/api/client/xj/msg', 'yh--base-plus--消息通知', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827395', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '595094558656827394', 'GET', '/api/client/xj/msg/list', '分页查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827396', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '595094558656827394', 'PUT', '/api/client/xj/msg/{id}/read', '消息修改为已读', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827397', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '595094558656827394', 'GET', '/api/client/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410304', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '0', '', '/api/admin/user', 'base--admin--用户管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410305', NULL, NULL, '2021-09-30 13:40:08', '2022-01-10 23:24:24', 0, 706, '595094558669410304', 'GET', '/api/admin/user/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410306', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:25', 0, 706, '595094558669410304', 'POST', '/api/admin/user', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410307', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:23', 0, 706, '595094558669410304', 'DELETE', '/api/admin/user/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410308', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:25', 0, 706, '595094558669410304', 'GET', '/api/admin/user/{id}', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410309', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:26', 0, 706, '595094558669410304', 'PUT', '/api/admin/user/updUser', '修改当前登录人的信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410310', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:25', 0, 706, '595094558669410304', 'GET', '/api/admin/user/findByRoleId', '获取指定角色的用户列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410311', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:22', 0, 706, '595094558669410304', 'PUT', '/api/admin/user/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410312', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:19', 0, 706, '595094558669410304', 'PUT', '/api/admin/user/updByPassword', '修改当前登录人的密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410313', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:24', 0, 706, '595094558669410304', 'GET', '/api/admin/user/list/keyData', '查询所有-只返回关键数据(姓名/昵称/电话/id)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410314', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:22:26', 0, 706, '595094558669410304', 'PUT', '/api/admin/user/{id}/resetPassword', '重置任意用户密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604608', NULL, NULL, '2021-09-30 13:40:08', '2022-02-02 14:43:43', 0, 706, '595094558669410304', 'GET', '/api/admin/user/findUser', '查询当前登录人的个人信息', 0, 0, 3, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604609', NULL, NULL, '2021-09-30 13:40:08', '2021-10-08 22:23:30', 0, 706, '595094558669410304', 'POST', '/api/admin/user/login', '用户登录', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604610', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '0', '', '/api/admin/dataBase', 'base--gc--代码生成--查询表数据', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558673604611', NULL, NULL, '2021-09-30 13:40:08', '2022-07-28 06:01:55', 0, 706, '595094558673604610', 'GET', '/api/admin/dataBase/table/list', '查询所有表名', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558677798912', NULL, NULL, '2021-09-30 13:40:08', '2021-12-13 19:20:48', 0, 706, '595094558673604610', 'GET', '/api/admin/dataBase/table/field', '查询指定表下所有字段内容', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558686187520', NULL, NULL, '2021-09-30 13:40:08', '2021-09-30 13:40:08', 0, 706, '0', '', '/api/admin/xj/jvm', 'base--plus--jvm信息获取', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558686187521', NULL, NULL, '2021-09-30 13:40:08', '2022-07-28 06:03:37', 0, 706, '595094558686187520', 'GET', '/api/admin/xj/jvm/jvmInfo', '3、系统的jvm信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('598128304653996032', NULL, NULL, '2021-10-08 22:35:13', '2021-10-08 22:36:06', 0, 695, '590395518954377221', 'GET', '/api/admin/xj/config/findByCode', 'CODE查询', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537088', NULL, NULL, '2021-10-31 12:00:10', '2021-10-31 12:00:10', 0, 605, '0', '', '/api/admin/organ', 'base--admin--组织机构', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('606303420856537089', NULL, NULL, '2021-10-31 12:00:10', '2022-01-10 06:06:15', 0, 605, '606303420856537088', 'GET', '/api/admin/organ/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537090', NULL, NULL, '2021-10-31 12:00:10', '2022-01-10 06:06:15', 0, 605, '606303420856537088', 'POST', '/api/admin/organ', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537091', NULL, NULL, '2021-10-31 12:00:10', '2022-01-10 06:06:14', 0, 605, '606303420856537088', 'DELETE', '/api/admin/organ/{id}', 'ID删除(并删除子数据)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537092', NULL, NULL, '2021-10-31 12:00:10', '2022-01-10 06:06:16', 0, 605, '606303420856537088', 'PUT', '/api/admin/organ/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('613640045747900416', NULL, NULL, '2021-11-20 17:53:18', '2022-01-19 22:15:22', 0, 528, '590081231815839745', 'POST', '/api/admin/generate/generateCodeVue', '生成Vue代码(将直接下载)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('620065229904154624', NULL, NULL, '2021-12-08 11:24:40', '2022-07-28 08:11:43', 0, 470, '594756761932664835', 'GET', '/api/client/xj/banner/list/{position}', '列表-位置查询', 0, 1, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('691539110942347264', NULL, NULL, '2022-06-05 08:56:21', '2022-06-05 08:56:21', 0, 206, '590395518954377221', 'GET', '/api/admin/xj/config/{id}', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('697255086296010752', NULL, NULL, '2022-06-19 03:29:37', '2022-06-19 03:29:37', 0, 121, '0', '', '/api/open/redis', 'Redis  -->  Redis 测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('697255086300205057', NULL, NULL, '2022-06-19 03:29:37', '2022-06-19 03:29:37', 0, 121, '697255086296010752', 'GET', '/api/open/redis/getNo', '获取分布式唯一编号', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('697398103711551488', NULL, NULL, '2022-06-19 04:57:58', '2022-06-19 04:57:58', 0, 119, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest2/{key}', 'redis 分布式锁加锁测试2', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('697398103711551489', NULL, NULL, '2022-06-19 04:57:58', '2022-06-19 04:57:58', 0, 119, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest1/{key}', 'redis 分布式锁加锁测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('697398103711551490', NULL, NULL, '2022-06-19 04:57:58', '2022-06-19 04:57:58', 0, 119, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest3', 'redis 分布式注解锁测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734145421312', NULL, NULL, '2022-06-27 03:32:41', '2022-06-27 03:32:41', 0, 85, '0', '', '/api/admin/test/gcTest', '代码生成测试表', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734145421313', NULL, NULL, '2022-06-27 03:32:41', '2022-06-27 03:32:41', 0, 85, '700879734145421312', 'GET', '/api/admin/test/gcTest/list', '列表查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734145421314', NULL, NULL, '2022-06-27 03:32:41', '2022-06-27 03:32:41', 0, 85, '700879734145421312', 'POST', '/api/admin/test/gcTest', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734145421315', NULL, NULL, '2022-06-27 03:32:41', '2022-06-27 03:32:41', 0, 85, '700879734145421312', 'DELETE', '/api/admin/test/gcTest/{id}', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734149615616', NULL, NULL, '2022-06-27 03:32:41', '2022-06-27 03:32:41', 0, 85, '700879734145421312', 'PUT', '/api/admin/test/gcTest/{id}', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734149615617', NULL, NULL, '2022-06-27 03:32:41', '2022-06-27 03:32:41', 0, 85, '700879734145421312', 'GET', '/api/admin/test/gcTest/{id}', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('711817171210211328', NULL, NULL, '2022-07-27 07:54:07', '2022-07-27 07:54:07', 0, 45, '590081231815839745', 'POST', '/api/admin/generate/generateCodeJavaAndVue', '生成java + vue代码(将直接下载)', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('712993653219528704', NULL, NULL, '2022-07-30 05:49:02', '2022-07-30 05:49:02', 0, 12, '594756761949442057', 'GET', '/api/admin/datasource/{id}', 'id查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('713000537204854784', NULL, NULL, '2022-07-30 06:16:24', '2022-07-30 06:16:24', 0, 11, '594756761949442057', 'POST', '/api/admin/datasource/dataSourceTest/{id}', '数据源连接测试', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296349229056', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '0', '', '/api/open/xj/valid', '参数验证测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423360', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296349229056', 'POST', '/api/open/xj/valid/test', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423361', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '0', '', '/api/open/xj/thread', '异步测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423362', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423361', 'GET', '/api/open/xj/thread/test', '异步测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423363', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '0', '', '/api/open/xj/transaction', '事务执行后执行', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423364', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423363', 'GET', '/api/open/xj/transaction/test', '事务执行后执行测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423365', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '0', '', '/api/open/xj/cache', '缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423366', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423365', 'PUT', '/api/open/xj/cache/updCache', '更新缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423367', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423365', 'DELETE', '/api/open/xj/cache/delCache', '删除缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423368', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423365', 'POST', '/api/open/xj/cache/getCache', '获取缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296353423369', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '0', '', '/api/open/xj/excel', 'excel测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296357617664', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423369', 'GET', '/api/open/xj/excel/exportExcelDownload', 'excel 导出', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296357617665', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423369', 'POST', '/api/open/xj/excel/upload', '解析excel数据', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296357617666', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '0', '', '/api/open/xj/sign', 'body参数验签测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296357617667', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617666', 'POST', '/api/open/xj/sign/test1', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296357617668', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617666', 'POST', '/api/open/xj/sign/test2', '参数加密', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296357617669', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617666', 'POST', '/api/open/xj/sign/test3/{a}', '参数加密', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296357617670', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617666', 'POST', '/api/open/xj/sign/test8', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296479252480', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '0', '', '/api/open/aliOssFile', 'AliYun --> OSS文件管理', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296479252481', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', 'GET', '/api/open/aliOssFile/fileList', 'OSS-文件Object列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296479252482', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', 'DELETE', '/api/open/aliOssFile/del', 'OSS-文件删除', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296479252483', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', 'GET', '/api/open/aliOssFile/downloadZip', 'OSS-文件下载--多文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296479252484', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', 'GET', '/api/open/aliOssFile/download', 'OSS-文件下载--单文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296479252485', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', 'POST', '/api/open/aliOssFile/upload', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296483446784', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '0', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296483446785', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446784', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296487641088', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446784', 'GET', '/api/open/websocket/getOnlineCount', '获取在线人数', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296487641089', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446784', 'GET', '/api/open/websocket/getOnlineUsersList', '获取当前在线用户列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('713227296487641090', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446784', 'GET', '/api/open/websocket/getPath', '获取模拟游客登录的 websocket 连接地址', 0, 2, 0, 0);

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
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父Id',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  `ext1` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_dictionary
-- ----------------------------
INSERT INTO `t_admin_dictionary` VALUES ('1290684671448936449', NULL, '-1', '2020-08-05 00:23:00', '2021-12-02 09:01:04', 0, 0, 'ENUMS', '枚举字典', '0', '状态/动态字段值，如：state，type，gender等, 可直接生成 前/ 后端枚举对象类代码', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290686507555844098', NULL, '-1', '2020-08-05 00:30:17', '2022-06-26 10:54:17', 0, 0, 'ADMIN', '系统枚举(动态值)', '1290684671448936449', '-', 3, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290687277911076865', NULL, '-1', '2020-08-05 00:33:21', '2022-05-15 10:39:20', 0, 0, 'MENU_ROOT', '菜单级别', '1290688121255587841', '【固定值】', 1000, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290687351005212673', NULL, '-1', '2020-08-05 00:33:38', '2021-11-10 15:33:28', 0, 0, '1', '顶部菜单', '1290687277911076865', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290687461252493314', NULL, '-1', '2020-08-05 00:34:05', '2021-11-10 15:33:28', 0, 0, '2', '菜单', '1290687277911076865', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290687547940368386', NULL, '-1', '2020-08-05 00:34:25', '2021-11-10 15:33:28', 0, 0, '3', '页面', '1290687277911076865', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290688121255587841', NULL, '-1', '2020-08-05 00:36:42', '2022-05-15 10:38:15', 0, 0, 'BASE', '系统枚举(固定值)', '1290684671448936449', '-', 2, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290688660164931586', NULL, '-1', '2020-08-05 00:38:51', '2022-05-15 10:39:14', 0, 0, 'GENDER', '性别', '1290688121255587841', '【固定值】', 700, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290688703043301378', NULL, '-1', '2020-08-05 00:39:01', '2021-11-10 15:33:28', 0, 0, '1', '男', '1290688660164931586', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1290688742289403906', NULL, '-1', '2020-08-05 00:39:10', '2022-03-05 17:59:57', 0, 0, '2', '女', '1290688660164931586', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1291341478399897601', NULL, '-1', '2020-08-06 11:52:54', '2021-11-10 15:33:29', 0, 0, '0', '未知', '1290688660164931586', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296469448399593474', NULL, '-1', '2020-08-20 15:29:38', '2022-05-15 10:39:12', 0, 0, 'DISABLE', '是否禁用', '1290688121255587841', '【固定值】', 600, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296469518025039873', NULL, '-1', '2020-08-20 15:29:55', '2021-12-09 14:31:33', 0, 0, '1', '禁用', '1296469448399593474', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296469564455985154', NULL, '-1', '2020-08-20 15:30:06', '2021-11-10 15:33:29', 0, 0, '0', '启用', '1296469448399593474', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296995475064434690', NULL, '-1', '2020-08-22 02:19:52', '2022-05-15 10:39:19', 0, 0, 'AUTHORITY_TYPE', '权限类型', '1290688121255587841', '【固定值】：用于新接口自动生成【权限状态】', 900, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296995560007479298', NULL, '-1', '2020-08-22 02:20:12', '2021-11-10 15:33:29', 0, 0, '0', '管理端接口', '1296995475064434690', '管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296995610632728578', NULL, '-1', '2020-08-22 02:20:24', '2021-11-10 15:33:29', 0, 0, '1', '用户端接口', '1296995475064434690', '用户端, 类上标有该参数所有接口都会默认被列为-[需登录]', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296995742778470401', NULL, '-1', '2020-08-22 02:20:55', '2022-05-15 10:39:16', 0, 0, 'AUTHORITY_STATE', '权限状态', '1290688121255587841', '【固定值】：用于编辑指定接口的【权限状态】', 800, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296995839977271297', NULL, '-1', '2020-08-22 02:21:19', '2021-11-10 15:33:29', 0, 0, '0', '无', '1296995742778470401', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296996062090833922', NULL, '-1', '2020-08-22 02:22:12', '2021-11-10 15:33:29', 0, 0, '1', '需登录', '1296995742778470401', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1296996224863383554', NULL, '-1', '2020-08-22 02:22:50', '2021-11-10 15:33:29', 0, 0, '2', '需登录+授权', '1296995742778470401', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1297705363906273282', NULL, '-1', '2020-08-24 01:20:43', '2021-11-10 15:33:30', 0, 0, '2', '通用接口', '1296995475064434690', '通用接口, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1298191840981327873', NULL, '-1', '2020-08-25 09:33:48', '2022-05-15 10:39:11', 0, 0, 'DELETED', '逻辑删除', '1290688121255587841', '【固定值】', 500, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1298191898313269249', NULL, '-1', '2020-08-25 09:34:02', '2021-11-10 15:33:30', 0, 0, '0', '正常', '1298191840981327873', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1298191981998022658', NULL, '-1', '2020-08-25 09:34:22', '2021-11-10 15:33:30', 0, 0, '1', '已删除', '1298191840981327873', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1298194668697198594', NULL, '-1', '2020-08-25 09:45:02', '2022-05-15 10:39:22', 0, 0, 'BANNER_IS_SKIP', 'banner是否跳转', '1290688121255587841', '【固定值】', 1100, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1298194722350735361', NULL, '-1', '2020-08-25 09:45:15', '2021-11-10 15:33:30', 0, 0, '0', '否', '1298194668697198594', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1298194801014906881', NULL, '-1', '2020-08-25 09:45:34', '2021-11-10 15:33:30', 0, 0, '1', '内部跳转', '1298194668697198594', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1298194850285395969', NULL, '-1', '2020-08-25 09:45:46', '2021-11-10 15:33:30', 0, 0, '2', '外部跳转', '1298194668697198594', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1303872194494435330', NULL, '-1', '2020-09-10 09:45:30', '2021-11-10 15:33:30', 0, 0, 'BANNER_POSITION', 'banner 位置', '1290686507555844098', '【动态值】', 300, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1303872308608864257', NULL, '-1', '2020-09-10 09:45:57', '2021-11-10 15:33:30', 0, 0, '1', '用户端首页', '1303872194494435330', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1308580167513370625', NULL, '-1', '2020-09-23 09:33:17', '2021-11-10 15:33:30', 0, 0, 'MSG_USER_TYPE', '及时消息终端', '1290686507555844098', '【动态值】', 500, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1308580236161544193', NULL, '-1', '2020-09-23 09:33:33', '2021-11-10 15:33:30', 0, 0, '1', '用户端', '1308580167513370625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1308580275248263169', NULL, '-1', '2020-09-23 09:33:42', '2021-11-10 15:33:31', 0, 0, '2', '管理端', '1308580167513370625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1308585499920769025', NULL, '-1', '2020-09-23 09:54:28', '2021-11-10 15:33:31', 0, 0, 'MSG_TYPE', '及时消息类型', '1290686507555844098', '【动态值】', 400, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1308585615968772098', NULL, '-1', '2020-09-23 09:54:56', '2022-06-22 10:33:35', 0, 0, '1', '管理端 - 系统通知', '1308585499920769025', '-', 1, 0, '系统通知', NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1332329973427494913', NULL, '-1', '2020-11-27 22:26:31', '2022-05-15 10:39:26', 0, 0, 'BLACKLIST_TYPE', '黑/白名单类型', '1290688121255587841', '【固定值】', 1300, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1332330105569042434', NULL, '-1', '2020-11-27 22:27:02', '2021-11-10 15:33:31', 0, 0, '1', '白名单', '1332329973427494913', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1332330148963311619', NULL, '-1', '2020-11-27 22:27:13', '2021-11-10 15:33:31', 0, 0, '2', '黑名单', '1332329973427494913', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352826209829978114', NULL, '-1', '2021-01-23 11:51:17', '2021-11-10 15:33:31', 0, 0, '3', 'Oauth2 接口', '1296995475064434690', '接口默认默认需通过 appId，appSecret生成的 accessToken 来进行oauth2 令牌验证可访问', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352826389480407041', NULL, '-1', '2021-01-23 11:52:00', '2021-11-10 15:33:31', 0, 0, '3', '需Oauth2 授权', '1296995742778470401', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352856400451117058', NULL, '-1', '2021-01-23 13:51:15', '2022-05-15 10:39:33', 0, 0, 'PAY_TYPE', '支付类型', '1290688121255587841', '【固定值】', 1600, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352856492264431617', NULL, '-1', '2021-01-23 13:51:37', '2021-11-10 15:33:31', 0, 0, '1', '支付', '1352856400451117058', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352856528012484610', NULL, '-1', '2021-01-23 13:51:45', '2021-11-10 15:33:31', 0, 0, '2', '充值', '1352856400451117058', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352856603073748994', NULL, '-1', '2021-01-23 13:52:03', '2021-11-10 15:33:31', 0, 0, '3', '退款', '1352856400451117058', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352856663249428482', NULL, '-1', '2021-01-23 13:52:18', '2021-11-10 15:33:32', 0, 0, '4', '商家打款', '1352856400451117058', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352856892170346498', NULL, '-1', '2021-01-23 13:53:12', '2022-05-15 10:39:28', 0, 0, 'PAY_CHANNEL', '支付渠道', '1290688121255587841', '【固定值】', 1400, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352856932938981378', NULL, '-1', '2021-01-23 13:53:22', '2021-11-10 15:33:32', 0, 0, '1', '支付宝', '1352856892170346498', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352856979743219713', NULL, '-1', '2021-01-23 13:53:33', '2021-11-10 15:33:32', 0, 0, '2', '微信', '1352856892170346498', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857025708597250', NULL, '-1', '2021-01-23 13:53:44', '2021-11-10 15:33:32', 0, 0, '3', '银联', '1352856892170346498', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857215228223489', NULL, '-1', '2021-01-23 13:54:29', '2022-05-15 10:39:30', 0, 0, 'PAY_STATE', '支付状态', '1290688121255587841', '【固定值】用于记录支付交易请求状态', 1500, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857264104448001', NULL, '-1', '2021-01-23 13:54:41', '2021-11-10 15:33:32', 0, 0, '0', '已发起', '1352857215228223489', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857305888104450', NULL, '-1', '2021-01-23 13:54:51', '2021-11-10 15:33:32', 0, 0, '1', '回调成功', '1352857215228223489', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857349592752130', NULL, '-1', '2021-01-23 13:55:01', '2021-11-10 15:33:32', 0, 0, '2', '交易失败', '1352857215228223489', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857389069541377', NULL, '-1', '2021-01-23 13:55:11', '2021-11-10 15:33:32', 0, 0, '3', '交易成功', '1352857215228223489', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857426407235585', NULL, '-1', '2021-01-23 13:55:20', '2021-11-10 15:33:32', 0, 0, '4', '订单异常', '1352857215228223489', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857793505304577', NULL, '-1', '2021-01-23 13:56:47', '2021-11-10 15:37:17', 0, 0, 'PAY_BUSINESS', '支付业务', '1290686507555844098', '【动态值】当前支付业务', 600, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352857906709569537', NULL, '-1', '2021-01-23 13:57:14', '2021-11-10 15:37:17', 0, 0, '1', '用户下单', '1352857793505304577', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1352858096959004674', NULL, '-1', '2021-01-23 13:57:59', '2021-11-10 15:37:18', 0, 0, '2', 'vip 充值/续费', '1352857793505304577', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1357528050694148097', NULL, '-1', '2021-02-05 11:14:43', '2022-05-15 10:39:35', 0, 0, 'WALLET_TYPE', '流水类型', '1290688121255587841', '【固定值】', 1700, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1357528121372364801', NULL, '-1', '2021-02-05 11:15:00', '2021-11-10 15:33:33', 0, 0, '1', '收入', '1357528050694148097', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1357528154167627779', NULL, '-1', '2021-02-05 11:15:07', '2021-11-10 15:33:33', 0, 0, '2', '支出', '1357528050694148097', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1357612114939858945', NULL, '-1', '2021-02-05 16:48:45', '2022-05-15 10:39:23', 0, 0, 'IS_READ', '是否已读', '1290688121255587841', '【固定值】', 1200, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1357612150536916994', NULL, '-1', '2021-02-05 16:48:54', '2021-11-10 15:33:33', 0, 0, '0', '未读', '1357612114939858945', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1357612182854029315', NULL, '-1', '2021-02-05 16:49:01', '2021-11-10 15:33:33', 0, 0, '1', '已读', '1357612114939858945', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1368739295631798273', NULL, '-1', '2021-03-08 09:44:11', '2021-11-10 15:33:33', 0, 0, 'POSITION', '部门职位', '1290686507555844098', '【动态值】, 如有需要根据业务指定', 200, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1368739394596401154', NULL, '-1', '2021-03-08 09:44:35', '2022-06-22 23:37:04', 0, 0, '0', '系统管理员(老板)', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1384697257961463810', NULL, '-1', '2021-04-21 10:35:27', '2022-01-13 22:19:46', 0, 0, '1', '部门经理', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1399968409441050625', NULL, '-1', '2021-06-02 13:57:32', '2022-05-15 10:39:09', 0, 0, 'DEFAULT', '默认字典(代码生成默认字典)', '1290688121255587841', '【固定值】用于代码生成默认使用的code值', 400, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1399968449656037377', NULL, '-1', '2021-06-02 13:57:42', '2021-11-10 15:33:34', 0, 0, '1', '默认值 1', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1399968504043577346', NULL, '-1', '2021-06-02 13:57:55', '2021-11-10 15:33:34', 0, 0, '2', '默认值 2', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1399968544350838786', NULL, '-1', '2021-06-02 13:58:04', '2022-06-04 10:29:20', 0, 0, '3', '默认值 3', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1404005220177985537', NULL, '-1', '2021-06-13 17:18:21', '2022-06-22 10:33:19', 0, 0, '0', '管理端 - 测试消息', '1308585499920769025', '-', 0, 0, '测试消息', NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1427513445955194882', NULL, '-1', '2021-08-17 14:11:42', '2021-11-10 15:33:34', 0, 0, '4', '其他', '1352856892170346498', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1427513925234118658', NULL, '-1', '2021-08-17 14:13:36', '2021-11-10 15:37:18', 0, 0, '3', '月卡购买', '1352857793505304577', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1427513998571524097', NULL, '-1', '2021-08-17 14:13:53', '2021-11-10 15:37:18', 0, 0, '4', '其他', '1352857793505304577', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1432260997380349954', NULL, '-1', '2021-08-30 16:36:49', '2021-11-10 15:33:34', 0, 0, 'TERMINAL', '终端', '1290686507555844098', '【动态值】 如有需要根据业务指定', 100, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1432261183641001986', NULL, '-1', '2021-08-30 16:37:33', '2022-01-17 14:33:51', 0, 0, '1', 'vue 主系统端', '1432260997380349954', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1432261342928084993', NULL, '-1', '2021-08-30 16:38:11', '2022-01-18 20:11:54', 0, 0, '2', '商家端', '1432260997380349954', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1443767289248788481', NULL, '-1', '2021-10-01 10:38:39', '2022-05-15 10:39:07', 0, 0, 'ORGAN_ROOT', '机构组织级别', '1290688121255587841', '-', 200, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1443767335407104002', NULL, '-1', '2021-10-01 10:38:50', '2021-11-10 15:33:35', 0, 0, '1', '一级', '1443767289248788481', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1443767375802445826', NULL, '-1', '2021-10-01 10:39:00', '2021-11-10 15:33:35', 0, 0, '2', '二级', '1443767289248788481', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1443767410984267777', NULL, '-1', '2021-10-01 10:39:08', '2021-11-10 15:33:35', 0, 0, '3', '三级', '1443767289248788481', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455153732051349505', NULL, '-1', '2021-11-01 20:44:19', '2022-05-15 10:43:12', 0, 0, 'VUE_FIELD_TYPE', 'VUE字段类型', '1290688121255587841', 'vue代码生成使用', 300, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455153814570086402', NULL, '-1', '2021-11-01 20:44:39', '2022-06-30 10:12:07', 0, 0, '1', '文本-(input)', '1455153732051349505', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455153864008347650', NULL, '-1', '2021-11-01 20:44:51', '2022-05-15 10:43:13', 0, 0, '2', '数字-(number)', '1455153732051349505', '-', 2, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455154454599905281', NULL, '-1', '2021-11-01 20:47:12', '2022-05-15 10:43:13', 0, 0, '4', '单选-(radio)', '1455153732051349505', '-', 4, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455154827834241025', NULL, '-1', '2021-11-01 20:48:41', '2022-05-15 10:43:13', 0, 0, '9', '开关-(switch)', '1455153732051349505', '-', 9, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455154939453059073', NULL, '-1', '2021-11-01 20:49:07', '2022-05-15 10:43:13', 0, 0, '5', '多选-(checkbox)', '1455153732051349505', '-', 5, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455155390269435905', NULL, '-1', '2021-11-01 20:50:55', '2022-05-15 10:43:13', 0, 0, '10', '日期-(data)', '1455153732051349505', 'yyyy-MM-dd', 10, 1, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455155744738455553', NULL, '-1', '2021-11-01 20:52:19', '2022-05-15 10:43:14', 0, 0, '11', '日期时间-(datetime)', '1455153732051349505', 'yyyy-MM-dd hh:mm:ss', 11, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455155995658498049', NULL, '-1', '2021-11-01 20:53:19', '2022-05-15 10:43:14', 0, 0, '12', '时间-(time)', '1455153732051349505', '-', 12, 1, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455156254728073217', NULL, '-1', '2021-11-01 20:54:21', '2022-05-15 10:43:14', 0, 0, '6', '下拉选择-(select-单选)', '1455153732051349505', '-', 6, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455156611625594881', NULL, '-1', '2021-11-01 20:55:46', '2022-05-15 10:43:14', 0, 0, '3', '密码-(password)', '1455153732051349505', '-', 3, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455157509512835073', NULL, '-1', '2021-11-01 20:59:20', '2022-05-15 10:43:14', 0, 0, '7', '下拉选择 (select-单选+搜索)', '1455153732051349505', '-', 7, 1, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455157594946613250', NULL, '-1', '2021-11-01 20:59:40', '2022-05-15 10:43:15', 0, 0, '8', '下拉选择 (select-多选+搜索)', '1455153732051349505', '-', 8, 1, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455158056173252609', NULL, '-1', '2021-11-01 21:01:30', '2022-05-15 10:43:15', 0, 0, '13', '文件上传(默认单图)', '1455153732051349505', '-', 13, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455158128055234561', NULL, '-1', '2021-11-01 21:01:47', '2022-05-15 10:43:15', 0, 0, '14', '文件上传(多图)', '1455153732051349505', '-', 14, 1, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455158254165372929', NULL, '-1', '2021-11-01 21:02:18', '2022-05-15 10:43:15', 0, 0, '15', '文件上传（缩略图）', '1455153732051349505', '-', 15, 1, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1455160116759310338', NULL, '-1', '2021-11-01 21:09:42', '2022-05-15 10:43:16', 0, 0, '16', '文件上传（附件）', '1455153732051349505', '-', 16, 1, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1456761651003920386', NULL, '-1', '2021-11-06 07:13:37', '2022-05-15 10:43:16', 0, 0, '17', '大文本(textarea)', '1455153732051349505', '-', 17, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1459853262768320513', NULL, '-1', '2021-11-14 19:58:34', '2022-05-15 10:43:16', 0, 0, 'CONFIG_TYPE', '全局配置类型', '1290688121255587841', '-', 100, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1459853371690201089', NULL, '-1', '2021-11-14 19:59:00', '2022-05-15 10:43:16', 0, 0, '0', '文本', '1459853262768320513', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1459853401289404418', NULL, '-1', '2021-11-14 19:59:07', '2022-06-17 16:31:40', 0, 0, '1', '图片', '1459853262768320513', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1459853475214012418', NULL, '-1', '2021-11-14 19:59:25', '2022-06-17 16:31:41', 0, 0, '2', '开关', '1459853262768320513', '-', 2, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131575404531714', NULL, '-1', '2022-01-01 12:16:26', '2022-07-26 14:08:30', 0, 0, 'DEMO_TEST', 'demo模块测试', '1290684671448936449', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131743994580994', NULL, '-1', '2022-01-01 12:17:06', '2022-05-15 10:43:17', 0, 0, 'SEX', '性别', '1477131575404531714', '-', 100, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131857387589634', NULL, '-1', '2022-01-01 12:17:33', '2022-05-15 10:43:17', 0, 0, '0', '女', '1477131743994580994', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131918590873601', NULL, '-1', '2022-01-01 12:17:47', '2022-06-04 10:28:36', 0, 0, '1', '男', '1477131743994580994', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131953227436034', NULL, '-1', '2022-01-01 12:17:56', '2022-06-04 10:28:49', 0, 0, '2', '未知', '1477131743994580994', '-', 2, 0, '1', '2', '3');
INSERT INTO `t_admin_dictionary` VALUES ('1481632150259240961', NULL, '-1', '2022-01-13 22:20:04', '2022-05-15 10:43:18', 0, 0, '2', '员工', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1524590015236018178', NULL, '-1', '2022-05-12 11:19:19', '2022-06-17 16:31:41', 0, 0, '3', '富文本', '1459853262768320513', '-', 3, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1525481792788762625', NULL, '-1', '2022-05-14 22:22:56', '2022-05-18 21:20:41', 0, 0, '18', '富文本(tinymce)', '1455153732051349505', 'vue-tinymce 富文本插件', 18, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1537714554153410561', NULL, NULL, '2022-06-17 16:31:31', '2022-06-17 16:31:31', 0, 0, '4', 'markdown 文本', '1459853262768320513', '-', 4, 0, '', '', '');
INSERT INTO `t_admin_dictionary` VALUES ('1539432646667579394', NULL, NULL, '2022-06-22 10:18:38', '2022-06-22 11:04:12', 0, 0, '2', '管理端 - 用户信息变动', '1308585499920769025', '-已配置路由', 2, 0, '用户信息变动', '/views/admin/user/user', '/app/user/details');
INSERT INTO `t_admin_dictionary` VALUES ('1540889700338765825', NULL, NULL, '2022-06-26 10:48:27', '2022-06-30 10:13:47', 0, 0, '19', 'md 编辑器', '1455153732051349505', '-', 19, 0, '', '', '');
INSERT INTO `t_admin_dictionary` VALUES ('1551812023422029825', NULL, NULL, '2022-07-26 14:09:52', '2022-07-26 14:11:33', 1, 0, '啦啦啦', '啦啦测试', '0', '-', 0, 0, '', '', '');

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
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '指定父id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
  `two_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第二路由url, 前后端分离前端使用第二路由',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单url',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `root` int(1) NOT NULL DEFAULT 0 COMMENT '目录级别(1-系统, 2-菜单 ，3-页面, 4-按钮)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  `terminal` int(1) DEFAULT 1 COMMENT '终端 (字段code)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES ('1440255471893200897', '1', '-1', '2021-09-21 18:03:57', '2022-07-26 17:10:51', 0, 0, '0', 'a-vue', '', '', 'el-icon-goods', 1, 1, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440255602914869250', '1', '-1', '2021-09-21 18:04:29', '2022-01-17 14:33:00', 0, 0, '1440255471893200897', '系统管理', '', '', 'el-icon-setting', 100, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440255716299489282', '1', '-1', '2021-09-21 18:04:56', '2022-04-20 16:49:12', 0, 0, '1440255602914869250', '菜单管理', '', '/views/admin/menu/menu', 'el-icon-document-remove', 20005, 3, 1, 1);
INSERT INTO `t_admin_menu` VALUES ('1440256392576483330', '1', '-1', '2021-09-21 18:07:37', '2022-06-05 23:09:33', 0, 0, '0', 'a-vue-2 (test)', '', '', 'layui-icon-file-b', 2, 1, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440256481906769922', '1', '-1', '2021-09-21 18:07:58', '2022-01-17 14:33:00', 0, 0, '1440256392576483330', '功能测试', '', '', 'layui-icon-file-b', 200, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440271162033684482', '1', '-1', '2021-09-21 19:06:18', '2022-01-17 14:33:00', 0, 0, '1440255602914869250', '用户管理', '', '/views/admin/user/user', 'el-icon-document-remove', 10001, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1442156484086480897', '1', '-1', '2021-09-26 23:57:54', '2022-01-17 14:33:00', 0, 0, '1440255602914869250', '角色管理', '', '/views/admin/role/role', 'el-icon-document-remove', 10003, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1442156557235142657', '1', '-1', '2021-09-26 23:58:11', '2022-04-20 16:49:15', 0, 0, '1440255602914869250', '字典管理', '', '/views/admin/adminDictionary/adminDictionary', 'el-icon-document-remove', 20006, 3, 1, 1);
INSERT INTO `t_admin_menu` VALUES ('1442156599396286466', '1', '-1', '2021-09-26 23:58:21', '2022-04-20 16:49:14', 0, 0, '1440255602914869250', '接口管理', '', '/views/admin/adminAuthority/adminAuthority', 'el-icon-document-remove', 10004, 3, 1, 1);
INSERT INTO `t_admin_menu` VALUES ('1449698334917865473', '1', '-1', '2021-10-17 19:26:30', '2022-05-15 10:44:50', 0, 0, '1449698274373087233', '页面-5级', NULL, '', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1449764190750285826', '1', '-1', '2021-10-17 23:48:11', '2022-05-15 10:44:50', 0, 0, '1440255471893200897', '增强功能', '', '', 'el-icon-copy-document', 200, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1450796419538522114', '1', '-1', '2021-10-20 20:09:55', '2022-05-15 10:44:50', 0, 0, '1440256481906769922', '测试页1', NULL, '/test1', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1451979503835369474', '1443465040706416642', '-1', '2021-10-24 02:31:02', '2022-05-15 10:44:51', 0, 0, '1449764190750285826', 'Banner管理', '', '/views/xj/xjAdminBanner/xjAdminBanner', 'el-icon-document-remove', 20003, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1452091447254749186', '1', '-1', '2021-10-24 09:55:54', '2022-05-15 10:44:51', 0, 0, '1440255471893200897', '代码生成', NULL, '', 'el-icon-edit', 0, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1452091513113710594', '1', '-1', '2021-10-24 09:56:10', '2022-07-30 00:02:45', 0, 0, '1452091447254749186', '数据表', NULL, '/views/gc/codeGeneration/codeGeneration', 'el-icon-document-remove', 2, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1456054437146644481', '1', '-1', '2021-11-04 08:23:24', '2022-07-30 00:02:46', 0, 0, '1452091447254749186', '生成的代码测试页', NULL, '/views/test/gcTest/gcTest', 'el-icon-document-remove', 3, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1457369967249879042', '1', '-1', '2021-11-07 23:30:51', '2022-05-15 10:44:51', 0, 0, '0', '商家端', NULL, NULL, 'el-icon-document-remove', 0, 1, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457370029065531394', '1', '-1', '2021-11-07 23:31:06', '2022-05-15 10:44:52', 0, 0, '1457369967249879042', '系统管理', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457370075530031105', '1', '-1', '2021-11-07 23:31:17', '2022-05-15 10:44:52', 0, 0, '1457370029065531394', '角色管理', NULL, '/views/admin/role/role', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457372083897004033', '1', '-1', '2021-11-07 23:39:16', '2022-05-15 10:44:52', 0, 0, '1457370029065531394', '员工管理', NULL, '/views/admin/user/user', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1459712656557576194', '1', '-1', '2021-11-14 10:39:51', '2022-05-15 10:44:52', 0, 0, '1440255602914869250', '组织机构', NULL, '/views/admin/adminOrgan/adminOrgan', 'el-icon-document-remove', 10002, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1459850402525622274', '1', '-1', '2021-11-14 19:47:12', '2022-05-15 10:44:53', 0, 0, '1449764190750285826', '全局配置', NULL, '/views/xj/xjAdminConfig/xjAdminConfig', 'el-icon-document-remove', 20001, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1461987433667141634', '1', '-1', '2021-11-20 17:19:01', '2022-05-15 10:44:53', 0, 0, '1440255471893200897', '首页', NULL, '/wel/jvmInfo', 'el-icon-menu', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1462256665587916801', '1', '-1', '2021-11-21 11:08:51', '2022-05-15 10:44:53', 0, 0, '1449764190750285826', '黑/白名单', NULL, '/views/xj/xjAdminBlacklist/xjAdminBlacklist', 'el-icon-document-remove', 20002, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1462261436877152258', '1', '-1', '2021-11-21 11:27:48', '2022-05-15 10:44:53', 0, 0, '1449764190750285826', '请求日志', NULL, '/views/xj/xjAdminLog/xjAdminLog', 'el-icon-document-remove', 20005, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1468415037767946242', '1', '-1', '2021-12-08 11:00:01', '2022-05-15 10:44:53', 0, 0, '1449764190750285826', '消息管理', NULL, '/views/xj/xjAdminMsg/xjAdminMsg', 'el-icon-s-comment', 20004, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1481642095692222465', '1', '-1', '2022-01-13 22:59:36', '2022-05-15 10:44:54', 0, 0, '1440256481906769922', '测试页2', NULL, '/test2', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1482970741228965889', '1', '-1', '2022-01-17 14:59:11', '2022-05-15 10:44:54', 0, 0, '1457369967249879042', '业务管理', NULL, NULL, 'el-icon-document-remove', 1, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1482970818676789249', '1', '-1', '2022-01-17 14:59:30', '2022-05-15 10:44:54', 0, 0, '1482970741228965889', '业务功能1', NULL, '/yw1', 'el-icon-document-remove', 1, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1482970868152799234', '1', '-1', '2022-01-17 14:59:41', '2022-05-15 10:44:54', 0, 0, '1482970741228965889', '业务功能2', NULL, '/yw2', 'el-icon-document-remove', 2, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1482970903854714882', '1', '-1', '2022-01-17 14:59:50', '2022-05-15 10:44:54', 0, 0, '1482970741228965889', '业务功能3', NULL, '/yw3', 'el-icon-document-remove', 3, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1482979903354703874', '1', '-1', '2022-01-17 15:35:36', '2022-07-26 14:29:50', 0, 0, '1457370029065531394', '菜单管理', NULL, '/views/admin/menu/menu', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1516699625820524545', '1', '-1', '2022-04-20 16:45:44', '2022-05-15 10:44:55', 0, 0, '1440255602914869250', '菜单管理V2', NULL, '/views/admin/menuv2/menu', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1516699798743289857', '1', '-1', '2022-04-20 16:46:25', '2022-05-15 10:44:55', 0, 0, '1440255602914869250', '字典管理v2', NULL, '/views/admin/dictionaryv2/adminDictionary', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1516699922710138882', '1', '-1', '2022-04-20 16:46:55', '2022-05-15 10:44:55', 0, 0, '1440255602914869250', '接口管理v2', NULL, '/views/admin/adminAuthorityv2/adminAuthority', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1552119439552417793', '1460427339745763329', NULL, '2022-07-27 10:31:26', '2022-07-27 11:32:56', 1, 0, '1452091447254749186', '测试页', NULL, '/views/test/gcTest/gcTestaasa', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1552135127595749377', '1460427339745763329', NULL, '2022-07-27 11:33:46', '2022-07-27 11:34:39', 1, 0, '1452091447254749186', 'aavavav', NULL, '/views/test/gcTest/ssssss', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1552998827391221761', '1', NULL, '2022-07-29 20:45:45', '2022-07-30 00:02:45', 0, 0, '1452091447254749186', '数据源', NULL, '/views/gc/db/xjAdminDatasource', 'el-icon-document-remove', 1, 3, 0, 1);

-- ----------------------------
-- Table structure for t_admin_organ
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_organ`;
CREATE TABLE `t_admin_organ`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父Id (顶级父id=0)',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码 (开始查询使用,不可重复)',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门/公司名称',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门/公司描叙',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  `root` int(1) DEFAULT 1 COMMENT '级别( 1-一级 2-二级 3-三级)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--组织机构' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_organ
-- ----------------------------
INSERT INTO `t_admin_organ` VALUES ('1443501889504210946', NULL, '-1', '2021-09-30 17:04:03', '2022-06-27 09:13:41', 1, 0, '0', 'zgs', '成都总公司', '-', 0, 0, 1);
INSERT INTO `t_admin_organ` VALUES ('1443502090977603585', NULL, '-1', '2021-09-30 17:04:51', '2022-06-27 09:13:41', 1, 0, '1443501889504210946', 'zgsa', '子公司a', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1443502157943861250', NULL, '-1', '2021-09-30 17:05:07', '2022-06-27 09:13:41', 1, 0, '1443501889504210946', 'zgsb', '子公司b', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1443502302433439746', NULL, '-1', '2021-09-30 17:05:42', '2021-10-08 16:24:00', 0, 0, '1443502090977603585', 'csb', '测试部', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1443502428644241409', NULL, '-1', '2021-09-30 17:06:12', '2021-11-29 19:50:02', 0, 0, '1443502157943861250', 'yyb', '运营部', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1468426496627490818', NULL, '-1', '2021-12-08 11:45:33', '2022-06-27 09:13:41', 1, 0, '1443501889504210946', 'zb', '公司总部', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1481913168983756802', NULL, '-1', '2022-01-14 16:56:46', '2022-05-15 10:45:21', 0, 0, '1481913127925714945', 'xx-dep', 'xx部门', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1481913213086863362', NULL, '-1', '2022-01-14 16:56:57', '2022-05-15 10:45:21', 0, 0, '1481913127925714945', 'xx-dep2', 'xx部门2', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1548901388543594498', NULL, NULL, '2022-07-18 13:24:02', '2022-07-26 14:41:00', 0, 0, '0', 'TEST01', '测试公司1', '测试公司1的描述', 0, 0, 1);
INSERT INTO `t_admin_organ` VALUES ('1548901468621246466', NULL, NULL, '2022-07-18 13:24:22', '2022-07-18 13:25:13', 0, 0, '1548901388543594498', '1', '部门1', '部门1描述', 3, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1548901576150618114', NULL, NULL, '2022-07-18 13:24:47', '2022-07-18 13:24:47', 0, 0, '1548901468621246466', '2', '部门1_1', '部门1_1desc', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1548901827913715713', NULL, NULL, '2022-07-18 13:25:47', '2022-07-18 13:25:47', 0, 0, '1548901388543594498', '11', '部门2', '部门2desc', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1548901950412558337', NULL, NULL, '2022-07-18 13:26:16', '2022-07-18 13:26:16', 0, 0, '1548901827913715713', '11', '部门2_1', '部门2_1', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1549253663384408066', NULL, NULL, '2022-07-19 12:43:51', '2022-07-19 12:43:51', 0, 0, '1548901827913715713', '12', '11', '-', 0, 0, 3);

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
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '查询code(不能重复)',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  `terminal` int(1) DEFAULT 1 COMMENT '终端 (字段code)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('1443467633444806658', '1', '-1', '2021-09-30 14:47:56', '2022-07-26 16:42:33', 0, 0, 'avue-超管', 'SYS', 'avue超管', 0, 1);
INSERT INTO `t_admin_role` VALUES ('1447115588580159489', '1446872739607478273', '-1', '2021-10-10 16:23:36', '2022-07-28 16:08:23', 0, 0, 'avue 体验账号', 'test', '-', 0, 1);
INSERT INTO `t_admin_role` VALUES ('1483412338848567297', '1', NULL, '2022-01-18 20:13:56', '2022-01-18 20:13:56', 0, 0, '商家端超管', 'sj-sys', '-', 0, 2);
INSERT INTO `t_admin_role` VALUES ('1483415755868344322', '643392744110297088', NULL, '2022-01-18 20:27:31', '2022-01-18 20:27:31', 0, 0, '业务2和3处理人员', 'yw-23', '-', 0, 2);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/接口权限关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_auth
-- ----------------------------
INSERT INTO `t_admin_role_auth` VALUES ('1536915707277611010', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590081231815839745', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805313', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624962', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805314', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624963', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805315', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624964', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805316', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518954377221', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805317', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805318', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571521', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805319', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571522', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805320', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571524', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805321', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571526', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805322', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765825', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805323', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765828', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805324', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765829', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805325', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765830', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805326', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590398100552683520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805327', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590417006180831232', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805328', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805329', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805330', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862658', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805331', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862660', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805332', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717120', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707281805333', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717121', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719873', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717122', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719874', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911424', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719875', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911425', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719876', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911426', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719877', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911427', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719878', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911429', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719879', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452416', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719880', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452417', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719881', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452418', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719882', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452419', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707344719883', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452421', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108481', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452423', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108482', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452424', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108483', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452425', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108484', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646720', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108485', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646721', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108486', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646722', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108487', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646723', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108488', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646724', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108489', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646725', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108490', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646727', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108491', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '592136133727621120', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108492', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442057', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108493', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442058', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108494', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442059', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108495', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442060', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108496', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636352', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108497', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636353', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108498', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636354', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108499', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636358', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108500', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636359', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108501', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636360', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707353108502', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636361', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023042', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636362', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023043', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023044', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023045', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830658', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023046', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830659', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023047', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830660', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023048', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410304', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023049', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410305', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023050', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410306', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023051', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410307', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023052', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410308', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023053', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410309', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023054', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410310', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023055', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410311', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023056', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410312', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023057', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410313', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023058', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410314', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707416023059', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604608', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160386', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604609', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160387', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604610', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160388', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604611', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160389', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558677798912', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160390', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558686187520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160391', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558686187521', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160392', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '598128304653996032', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160393', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537088', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160394', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537089', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160395', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537090', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160396', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537091', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160397', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537092', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160398', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '613640045747900416', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160399', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621379493888', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160400', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688192', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160401', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688193', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160402', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688194', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160403', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688195', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160404', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688196', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707462160405', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '691539110942347264', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847116', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590081231815839745', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847117', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624962', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847118', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624963', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847119', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624964', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847120', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518954377221', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847121', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847122', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571521', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847123', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571522', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707692847124', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571524', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790145', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571526', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790146', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765825', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790147', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765828', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790148', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765829', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790149', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765830', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790150', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590398100552683520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790151', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590417006180831232', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790152', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862656', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790153', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862657', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790154', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862658', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790155', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862660', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790156', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717120', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790157', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717121', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790158', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717122', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790159', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911424', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790160', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911425', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790161', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911426', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790162', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911427', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790163', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911429', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707734790164', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452416', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927489', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452417', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927490', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452418', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927491', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452419', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927492', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452421', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927493', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452423', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927494', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452424', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927495', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452425', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927496', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646720', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927497', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646721', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927498', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646722', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927499', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646723', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927500', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646724', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927501', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646725', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927502', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646727', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927503', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '592136133727621120', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927504', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442057', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927505', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442058', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927506', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442059', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927507', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442060', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927508', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636352', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707780927509', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636353', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676225', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636354', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676226', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636358', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676227', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636359', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676228', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636360', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676229', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636361', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676230', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636362', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676231', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830656', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676232', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830657', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676233', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830658', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676234', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830659', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676235', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830660', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676236', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410304', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676237', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410305', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676238', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410306', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676239', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410307', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676240', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410308', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676241', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410309', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676242', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410310', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676243', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410311', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676244', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410312', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676245', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410313', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676246', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410314', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676247', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604608', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707818676248', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604609', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813569', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604610', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813570', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604611', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813571', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558677798912', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813572', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558686187520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813573', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558686187521', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813574', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '598128304653996032', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813575', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537088', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813576', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537089', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813577', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537090', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813578', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537091', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813579', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537092', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813580', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '613640045747900416', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813581', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621379493888', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813582', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688192', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813583', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688193', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813584', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688194', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813585', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688195', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813586', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688196', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707864813587', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '691539110942347264', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533826', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590081231815839745', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533827', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624962', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533828', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624963', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533829', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590082183939624964', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533830', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518954377221', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533831', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533832', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571521', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533833', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571522', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533834', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571524', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533835', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518958571526', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533836', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765825', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707923533837', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765828', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088258', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765829', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088259', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590395518962765830', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088260', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590398100552683520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088261', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590417006180831232', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088262', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862656', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088263', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862657', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088264', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862658', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088265', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590446794257862660', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088266', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717120', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088267', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717121', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088268', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633059717122', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088269', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911424', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088270', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911425', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088271', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911426', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088272', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911427', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088273', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590454633063911429', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088274', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452416', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088275', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452417', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088276', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452418', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088277', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452419', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088278', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452421', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088279', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452423', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088280', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452424', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915707957088281', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406475452425', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419905', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646720', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419906', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646721', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419907', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646722', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419908', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646723', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419909', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646724', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419910', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646725', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419911', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '590478406479646727', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419912', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '592136133727621120', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419913', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442057', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419914', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442058', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419915', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442059', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419916', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761949442060', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419917', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636352', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419918', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636353', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419919', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636354', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419920', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636358', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419921', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636359', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708007419922', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636360', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751553', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636361', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751554', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761953636362', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751555', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830656', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751556', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830657', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751557', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830658', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751558', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830659', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751559', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '594756761957830660', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751560', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410304', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751561', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410305', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751562', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410306', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751563', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410307', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751564', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410308', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751565', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410309', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751566', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410310', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751567', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410311', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751568', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410312', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751569', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410313', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751570', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558669410314', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751571', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604608', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751572', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604609', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751573', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604610', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751574', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558673604611', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751575', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558677798912', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751576', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558686187520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751577', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '595094558686187521', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751578', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '598128304653996032', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751579', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537088', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708057751580', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537089', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305985', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537090', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305986', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537091', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305987', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '606303420856537092', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305988', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '613640045747900416', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305989', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621379493888', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305990', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688192', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305991', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688193', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305992', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688194', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305993', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688195', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305994', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '641607621383688196', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1536915708091305995', NULL, NULL, '2022-06-15 11:37:13', '2022-06-15 11:37:13', 0, 0, '691539110942347264', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183228452866', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086296010753', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183262007298', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086300205056', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183262007299', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086300205057', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183262007300', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086296010752', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183282978820', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086296010753', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183312338945', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086300205056', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183312338946', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086300205057', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183312338947', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086296010752', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183312338948', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086296010753', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183320727554', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086300205056', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183320727555', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086300205057', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537276183320727556', NULL, NULL, '2022-06-16 11:29:37', '2022-06-16 11:29:37', 0, 0, '697255086296010752', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203240292353', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551488', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203240292354', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551489', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203248680962', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551490', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203248680966', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551488', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203252875265', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551489', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203252875266', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551490', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203252875267', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551488', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203252875268', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551489', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537419203252875269', NULL, NULL, '2022-06-16 20:57:58', '2022-06-16 20:57:58', 0, 0, '697398103711551490', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457115967489', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483585', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457115967490', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483586', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457115967491', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483587', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457115967492', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872192', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457115967493', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483584', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457115967494', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872194', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457115967495', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872195', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457166299137', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872196', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457178882049', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872197', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457178882050', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872198', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457178882051', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872193', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457195659273', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483585', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457195659274', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483586', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457195659275', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483587', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457195659276', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872192', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457195659277', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483584', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457195659278', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872194', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457212436481', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872195', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457212436482', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872196', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457212436483', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872197', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457212436484', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872198', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457212436485', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872193', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213698', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483585', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213699', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483586', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213700', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483587', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213701', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872192', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213702', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359772483584', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213703', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872194', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213704', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872195', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213705', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872196', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213706', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872197', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213707', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872198', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1537423457229213708', NULL, NULL, '2022-06-16 21:14:50', '2022-06-16 21:14:50', 0, 0, '697402359780872193', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354955935746', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016384', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354968518657', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016385', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354968518658', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016386', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354968518659', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016387', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354968518660', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257339822080', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354968518661', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210688', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354968518662', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016388', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354968518663', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210690', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354993684481', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210689', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354993684482', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210692', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354993684483', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210691', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354993684484', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210694', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354993684485', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210695', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354993684486', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210696', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885354993684487', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210693', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355002073089', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210698', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355002073090', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210699', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355002073091', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210697', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355002073092', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542337', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355002073093', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542338', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355002073094', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542339', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355018850306', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542340', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355018850307', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542341', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355018850308', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542336', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355031433218', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542343', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355031433219', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736640', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355031433220', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736641', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355031433221', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736642', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355031433222', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542342', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355085959173', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016384', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355094347778', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016385', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542082', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016386', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542083', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016387', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542084', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257339822080', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542085', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210688', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542086', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016388', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542087', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210690', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542088', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210689', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542089', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210692', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542090', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210691', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542091', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210694', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542092', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210695', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542093', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210696', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355098542094', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210693', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355111124993', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210698', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355111124994', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210699', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355111124995', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210697', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355111124996', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542337', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355111124997', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542338', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355111124998', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542339', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355111124999', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542340', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355111125000', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542341', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707905', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542336', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707906', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542343', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707907', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736640', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707908', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736641', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707909', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736642', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707910', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542342', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707911', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016384', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707912', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016385', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707913', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016386', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707914', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016387', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707915', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257339822080', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355123707916', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210688', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096513', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257344016388', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096514', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210690', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096515', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210689', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096516', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210692', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096517', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210691', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096518', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210694', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096519', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210695', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096520', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210696', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096521', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210693', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096522', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210698', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355132096523', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210699', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485122', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257348210697', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485123', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542337', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485124', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542338', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485125', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542339', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485126', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542340', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485127', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542341', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485128', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542336', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485129', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542343', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355140485130', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736640', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355148873730', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736641', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355148873731', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257402736642', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540885355148873732', NULL, NULL, '2022-06-26 10:31:11', '2022-06-26 10:31:11', 0, 0, '700864257398542342', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831912644610', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421313', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831916838913', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421314', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831916838914', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421315', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831916838915', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734149615616', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831916838916', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734149615617', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831929421825', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421312', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831929421832', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421313', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831929421833', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421314', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831937810434', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421315', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831937810435', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734149615616', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831937810436', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734149615617', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831937810437', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421312', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831937810438', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421313', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831937810439', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421314', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831942004737', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421315', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831942004738', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734149615616', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831942004739', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734149615617', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1540900831942004740', NULL, NULL, '2022-06-26 11:32:42', '2022-06-26 11:32:42', 0, 0, '700879734145421312', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591873', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895233', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591874', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895234', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591875', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895235', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591876', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895232', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591877', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895237', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591878', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895238', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591879', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895236', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591880', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089537', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591881', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089538', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591882', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089539', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591883', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089540', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591884', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089536', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591885', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089542', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591886', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089541', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591887', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283840', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591888', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089543', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591889', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283842', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591890', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283841', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591909', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895233', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591910', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895234', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591911', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895235', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591912', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895232', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591913', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895237', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591914', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895238', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591915', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895236', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591916', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089537', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591917', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089538', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591918', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089539', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591919', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089540', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591920', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089536', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591921', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089542', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591922', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089541', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591923', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283840', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591924', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089543', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591925', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283842', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591926', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283841', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591927', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895233', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591928', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895234', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591929', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895235', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591930', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895232', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591931', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895237', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591932', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895238', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591933', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078204895236', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591934', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089537', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591935', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089538', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591936', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089539', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591937', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089540', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591938', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089536', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591939', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089542', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591940', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089541', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591941', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283840', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591942', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078209089543', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591943', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283842', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542182174688591944', NULL, NULL, '2022-06-30 00:24:17', '2022-06-30 00:24:17', 0, 0, '702161078213283841', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581698', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683073', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581699', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683074', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581700', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942043877376', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581701', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942048071680', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581702', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942069043200', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581703', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683072', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581704', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942077431808', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581705', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942077431809', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581706', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942081626112', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581707', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942081626113', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039303581708', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942073237504', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301958', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683073', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301959', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683074', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301960', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942043877376', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301961', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942048071680', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301962', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942069043200', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301963', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683072', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301964', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942077431808', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301965', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942077431809', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301966', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942081626112', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301967', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942081626113', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301968', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942073237504', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301969', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683073', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301970', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683074', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301971', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942043877376', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301972', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942048071680', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301973', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942069043200', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039362301974', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942039683072', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039429410817', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942077431808', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039429410818', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942077431809', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039429410819', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942081626112', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039429410820', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942081626113', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542186039429410821', NULL, NULL, '2022-06-30 00:39:38', '2022-06-30 00:39:38', 0, 0, '702164942073237504', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716161', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242432', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716162', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242433', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716163', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242434', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716164', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026320048128', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716165', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436737', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716166', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436738', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716167', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436736', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716168', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436740', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716169', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436741', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716170', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436742', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716171', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436743', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716172', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436739', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716173', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631041', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716174', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631040', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716175', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631043', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716176', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631042', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716177', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631045', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123386716178', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631044', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630722', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242432', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630723', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242433', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630724', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242434', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630725', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026320048128', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630726', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436737', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630727', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436738', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630728', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436736', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630729', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436740', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630730', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436741', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630731', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436742', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630732', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436743', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630733', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436739', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630734', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631041', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630735', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631040', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630736', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631043', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630737', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631042', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630738', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631045', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630739', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631044', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630740', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242432', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630741', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242433', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630742', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026324242434', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630743', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026320048128', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630744', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436737', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630745', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436738', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630746', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436736', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630747', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436740', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630748', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436741', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630749', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436742', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630750', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436743', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630751', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026328436739', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630752', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631041', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630753', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631040', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630754', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631043', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630755', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631042', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630756', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631045', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1542837123449630757', NULL, NULL, '2022-07-01 19:46:49', '2022-07-01 19:46:49', 0, 0, '702816026332631044', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286503222915073', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386342256640', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505332649986', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386346450944', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505416536066', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386350645248', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505416536067', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386350645249', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505525587970', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386354839552', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505567531009', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386308702208', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505651417089', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386359033856', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505722720257', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386363228160', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505760468994', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386363228161', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505869520897', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386367422464', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286505869520898', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386354839553', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506456723457', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386342256640', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506456723458', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386346450944', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506511249409', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386350645248', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506511249410', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386350645249', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506511249411', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386354839552', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506511249412', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386308702208', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506511249413', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386359033856', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506628689922', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386363228160', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506683215873', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386363228161', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506741936130', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386367422464', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506821627905', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386354839553', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543286506892931074', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386342256640', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507173949442', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386346450944', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507194920962', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386350645248', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507194920963', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386350645249', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507220086786', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386354839552', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507220086787', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386308702208', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507249446914', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386359033856', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507249446915', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386363228160', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507249446916', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386363228161', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507291389954', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386367422464', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543286507291389955', NULL, NULL, '2022-07-03 01:32:30', '2022-07-03 01:32:30', 0, 0, '703265386354839553', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930532151297', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817145434112', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930532151298', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817153822720', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930532151299', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817153822721', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930779615234', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817158017024', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930779615235', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817141239808', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930779615236', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817162211328', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930779615237', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817162211329', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930779615238', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817166405632', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930968358914', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817158017025', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930968358915', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817170599936', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930968358916', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817166405633', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930968358917', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817279651840', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930968358918', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817283846144', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930968358919', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817275457536', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577930968358920', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817288040448', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931131936770', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817283846145', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931131936771', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817292234752', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931131936772', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817288040449', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931131936773', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818416308224', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931131936774', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818416308225', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931131936775', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818420502528', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931131936776', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818420502529', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931131936777', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818424696832', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931299708929', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818412113920', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931299708930', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818428891137', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931299708931', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818433085440', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931299708932', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818433085441', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931299708933', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818554720256', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577931299708934', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818428891136', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932159541251', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817145434112', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932159541252', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817153822720', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932159541253', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817153822721', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932159541254', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817158017024', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932159541255', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817141239808', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932159541256', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817162211328', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932159541257', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817162211329', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932331507713', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817166405632', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932331507714', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817158017025', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932331507715', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817170599936', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932331507716', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817166405633', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932331507717', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817279651840', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932331507718', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817283846144', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932331507719', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817275457536', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932331507720', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817288040448', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932507668482', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817283846145', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932507668483', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817292234752', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932507668484', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817288040449', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932507668485', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818416308224', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932507668486', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818416308225', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932507668487', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818420502528', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932507668488', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818420502529', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932507668489', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818424696832', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932650274817', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818412113920', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932650274818', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818428891137', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932650274819', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818433085440', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932650274820', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818433085441', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932650274821', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818554720256', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932650274822', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818428891136', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932650274823', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817145434112', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932834824193', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817153822720', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932834824194', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817153822721', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932834824195', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817158017024', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932834824196', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817141239808', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932834824197', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817162211328', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932834824198', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817162211329', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932834824199', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817166405632', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577932834824200', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817158017025', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933010984961', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817170599936', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933010984962', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817166405633', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933010984963', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817279651840', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933010984964', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817283846144', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933010984965', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817275457536', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933010984966', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817288040448', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933010984967', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817283846145', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933153591298', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817292234752', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933153591299', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556817288040449', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933153591300', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818416308224', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933153591301', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818416308225', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933153591302', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818420502528', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933153591303', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818420502529', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933153591304', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818424696832', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933153591305', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818412113920', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933317169154', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818428891137', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933317169155', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818433085440', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933317169156', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818433085441', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933317169157', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818554720256', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1543577933317169158', NULL, NULL, '2022-07-03 20:50:32', '2022-07-03 20:50:32', 0, 0, '703556818428891136', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1551838267018776578', NULL, NULL, '2022-07-26 15:54:07', '2022-07-26 15:54:07', 0, 0, '711817171210211328', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1551838267018776580', NULL, NULL, '2022-07-26 15:54:07', '2022-07-26 15:54:07', 0, 0, '711817171210211328', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1551838267018776581', NULL, NULL, '2022-07-26 15:54:07', '2022-07-26 15:54:07', 0, 0, '711817171210211328', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018371780610', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975169', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018371780611', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975168', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018371780612', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975171', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018409529346', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975170', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018409529347', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169473', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018409529348', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169474', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018409529349', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169475', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018422112257', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169472', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018426306561', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169477', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018426306562', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363776', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018426306563', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169476', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018426306564', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363778', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018426306565', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363777', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018430500865', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363780', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018430500866', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363781', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018430500867', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363782', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018430500868', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363783', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018434695169', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363779', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018434695170', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084033', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018434695171', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084034', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018434695172', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278336', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018434695173', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278337', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018438889474', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278338', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018438889475', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084032', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018438889476', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278340', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018438889477', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278341', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018443083777', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278342', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018443083778', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278343', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018443083779', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278339', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018472443908', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975169', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018472443909', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975168', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018472443910', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975171', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018476638210', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975170', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018476638211', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169473', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018476638212', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169474', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018476638213', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169475', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018480832513', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169472', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018480832514', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169477', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018480832515', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363776', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018480832516', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169476', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018485026817', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363778', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018485026818', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363777', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018485026819', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363780', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018485026820', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363781', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018485026821', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363782', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018489221121', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363783', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018489221122', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363779', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018489221123', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084033', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018489221124', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084034', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018493415426', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278336', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018493415427', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278337', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018493415428', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278338', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018493415429', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084032', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018497609729', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278340', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018497609730', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278341', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018497609731', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278342', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018497609732', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278343', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018497609733', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278339', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018501804034', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975169', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018501804035', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975168', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018501804036', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975171', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018501804037', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920675975170', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018501804038', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169473', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018505998337', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169474', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018505998338', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169475', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018505998339', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169472', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018505998340', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169477', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018510192641', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363776', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969858', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920680169476', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969859', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363778', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969860', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363777', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969861', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363780', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969862', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363781', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969863', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363782', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969864', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363783', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969865', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920684363779', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969866', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084033', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969867', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084034', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969868', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278336', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969869', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278337', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969870', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278338', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969871', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920743084032', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969872', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278340', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018526969873', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278341', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018543747073', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278342', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018543747074', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278343', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552287018543747075', NULL, NULL, '2022-07-27 21:37:20', '2022-07-27 21:37:20', 0, 0, '712265920747278339', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858498', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590446794257862657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858499', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590454633059717121', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858500', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590454633063911425', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858501', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590454633063911427', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858502', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590454633063911429', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858503', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '595094558669410308', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858504', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '595094558669410305', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858505', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '595094558673604608', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858506', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '595094558669410313', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858507', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '595094558669410310', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858508', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '606303420856537089', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858509', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590478406475452417', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858510', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590478406475452421', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858511', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590478406479646720', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858512', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590082183939624962', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858513', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '711817171210211328', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858514', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '613640045747900416', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858515', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590082183939624964', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858516', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '594756761949442058', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858517', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '595094558673604611', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858518', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '595094558677798912', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858519', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '594756761953636359', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858520', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '595094558686187521', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858521', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '598128304653996032', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858522', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '691539110942347264', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858523', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590395518958571520', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858524', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590395518962765825', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858525', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590395518962765830', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858526', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '592136133727621120', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858527', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '590417006180831232', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858528', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '594756761957830657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858529', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '700879734149615617', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1552565601866858530', NULL, NULL, '2022-07-28 16:04:18', '2022-07-28 16:04:18', 0, 0, '700879734145421313', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553014751343161345', NULL, NULL, '2022-07-29 21:49:02', '2022-07-29 21:49:02', 0, 0, '712993653219528704', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553014751351549954', NULL, NULL, '2022-07-29 21:49:02', '2022-07-29 21:49:02', 0, 0, '712993653219528704', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553014751351549955', NULL, NULL, '2022-07-29 21:49:02', '2022-07-29 21:49:02', 0, 0, '712993653219528704', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553014751351549956', NULL, NULL, '2022-07-29 21:49:02', '2022-07-29 21:49:02', 0, 0, '712993653219528704', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553021637748596737', NULL, NULL, '2022-07-29 22:16:24', '2022-07-29 22:16:24', 0, 0, '713000537204854784', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553021637748596738', NULL, NULL, '2022-07-29 22:16:24', '2022-07-29 22:16:24', 0, 0, '713000537204854784', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553021637748596739', NULL, NULL, '2022-07-29 22:16:24', '2022-07-29 22:16:24', 0, 0, '713000537204854784', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553021637748596740', NULL, NULL, '2022-07-29 22:16:24', '2022-07-29 22:16:24', 0, 0, '713000537204854784', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394120531970', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423360', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394133114881', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296349229056', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394133114882', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423362', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394133114883', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423361', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394141503489', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423364', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394145697793', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423363', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394145697794', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423366', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394145697795', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423367', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394149892098', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423368', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394149892099', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423365', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394149892100', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617664', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394149892101', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617665', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394154086401', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423369', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394154086402', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617667', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394154086403', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617668', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394154086404', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617669', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394154086405', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617670', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394154086406', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617666', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394154086407', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252481', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394154086408', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252482', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863617', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252483', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863618', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252484', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863619', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252485', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863620', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863621', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446785', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863622', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641088', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863623', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641089', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863624', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641090', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863625', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446784', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863626', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423360', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863627', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296349229056', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394170863628', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423362', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394187640833', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423361', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394187640834', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423364', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394187640835', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423363', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835138', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423366', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835139', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423367', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835140', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423368', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835141', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423365', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835142', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617664', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835143', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617665', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835144', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423369', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835145', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617667', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835146', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617668', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394191835147', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617669', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223745', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617670', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223746', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617666', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223747', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252481', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223748', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252482', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223749', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252483', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223750', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252484', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223751', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252485', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223752', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223753', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446785', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223754', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641088', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223755', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641089', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223756', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641090', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223757', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446784', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223758', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423360', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394200223759', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296349229056', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394217000962', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423362', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394217000963', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423361', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394217000964', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423364', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394217000965', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423363', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394217000966', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423366', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394217000967', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423367', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394217000968', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423368', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394217000969', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423365', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195265', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617664', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195266', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617665', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195267', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423369', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195268', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617667', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195269', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617668', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195270', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617669', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195271', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617670', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195272', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617666', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195273', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252481', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195274', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252482', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195275', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252483', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195276', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252484', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195277', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252485', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394221195278', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394233778178', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446785', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394233778179', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641088', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394233778180', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641089', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394233778181', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641090', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394233778182', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446784', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394233778183', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423360', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394233778184', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296349229056', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394233778185', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423362', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394242166785', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423361', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394242166786', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423364', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394242166787', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423363', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394242166788', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423366', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394242166789', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423367', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394242166790', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423368', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394242166791', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423365', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394242166792', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617664', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749698', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617665', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749699', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296353423369', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749700', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617667', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749701', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617668', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749702', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617669', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749703', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617670', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749704', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296357617666', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749705', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252481', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749706', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252482', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749707', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252483', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749708', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252484', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749709', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252485', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749710', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296479252480', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749711', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446785', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749712', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641088', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394254749713', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641089', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394271526913', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296487641090', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1553248394271526914', NULL, NULL, '2022-07-30 13:17:29', '2022-07-30 13:17:29', 0, 0, '713227296483446784', '1483415755868344322');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/菜单关联' ROW_FORMAT = Dynamic;

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
INSERT INTO `t_admin_role_menu` VALUES ('1533471026485137410', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1440255471893200897');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914625', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1461987433667141634');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914626', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1452091447254749186');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914627', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1452091513113710594');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914628', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1456054437146644481');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914629', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1440255602914869250');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914630', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1516699922710138882');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914631', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1516699798743289857');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914632', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1516699625820524545');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914633', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1440271162033684482');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026501914634', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1459712656557576194');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026535469057', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1442156484086480897');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026535469058', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1449764190750285826');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026535469059', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1459850402525622274');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026535469060', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1462256665587916801');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026548051970', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1451979503835369474');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026548051971', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1468415037767946242');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026548051972', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1462261436877152258');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026548051973', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1440256392576483330');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026548051974', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1440256481906769922');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026548051975', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1481642095692222465');
INSERT INTO `t_admin_role_menu` VALUES ('1533471026548051976', NULL, NULL, '2022-06-05 23:29:17', '2022-06-05 23:29:17', 0, 0, '1447115588580159489', '1450796419538522114');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102072590337', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1457369967249879042');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102072590338', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1457370029065531394');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102072590339', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1482979903354703874');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102072590340', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1457372083897004033');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102072590341', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1457370075530031105');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102072590342', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1482970741228965889');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102072590343', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1482970818676789249');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102072590344', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1482970868152799234');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173250', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1482970903854714882');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173251', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1440255471893200897');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173252', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1461987433667141634');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173253', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1452091447254749186');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173254', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1452091513113710594');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173255', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1456054437146644481');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173256', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1440255602914869250');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173257', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1516699922710138882');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173258', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1516699798743289857');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173259', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1516699625820524545');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173260', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1440271162033684482');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173261', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1459712656557576194');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173262', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1442156484086480897');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173263', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1449764190750285826');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173264', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1459850402525622274');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173265', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1462256665587916801');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173266', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1451979503835369474');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173267', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1468415037767946242');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173268', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1462261436877152258');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173269', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1440256392576483330');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173270', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1440256481906769922');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173271', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1481642095692222465');
INSERT INTO `t_admin_role_menu` VALUES ('1550671102085173272', NULL, NULL, '2022-07-23 10:36:15', '2022-07-23 10:36:15', 0, 0, '1443467633444806658', '1450796419538522114');
INSERT INTO `t_admin_role_menu` VALUES ('1552998828297191425', NULL, NULL, '2022-07-29 20:45:46', '2022-07-29 20:45:46', 0, 0, '1443467633444806658', '1552998827391221761');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色/用户关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_user
-- ----------------------------
INSERT INTO `t_admin_role_user` VALUES ('1476437959124643841', NULL, NULL, '2021-12-30 14:20:14', '2021-12-30 14:20:14', 0, 0, '1476437958340308994', '1447115588580159489');
INSERT INTO `t_admin_role_user` VALUES ('1483413837825708033', NULL, NULL, '2022-01-18 20:19:54', '2022-01-18 20:19:54', 0, 0, '643392744110297088', '1483412338848567297');
INSERT INTO `t_admin_role_user` VALUES ('1483416303262765058', NULL, NULL, '2022-01-18 20:29:41', '2022-01-18 20:29:41', 0, 0, '643395209551548416', '1483415755868344322');
INSERT INTO `t_admin_role_user` VALUES ('1551818199752773633', NULL, NULL, '2022-07-26 14:34:24', '2022-07-26 14:34:24', 0, 0, '1460427339745763329', '1447115588580159489');
INSERT INTO `t_admin_role_user` VALUES ('1552122024024059906', NULL, NULL, '2022-07-27 10:41:41', '2022-07-27 10:41:41', 0, 0, '1', '1443467633444806658');

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
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号/用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `gender` int(1) NOT NULL DEFAULT 0 COMMENT '性别 (0-未知 1-男 2-女)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 (0-否，1-是)',
  `organ_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司/部门id,多层级前端自行分割数据',
  `position` int(1) NOT NULL DEFAULT 0 COMMENT '职位 (字典code)',
  `terminal` int(1) NOT NULL DEFAULT 1 COMMENT '终端 (字段code)',
  `head` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号(第二账号)',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `reg_time` datetime(0) DEFAULT NULL COMMENT '注册时间',
  `end_time` datetime(0) DEFAULT NULL COMMENT '最后登录时间',
  `wx_open_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信公众号openId (需进行h5授权获得, 用于给管理端用户发送微信模板信息)',
  `remarks` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', NULL, '-1', '2020-08-05 07:11:04', '2022-07-30 13:19:12', 0, 10, 'admin', 'd9880822dd584adce3cde4b024776eef', 1, 0, '1548901388543594498,1548901827913715713,1548901950412558337', 0, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/53206946-4.png', '10000', '平台主账号', '', 22, '2020-08-05 15:11:05', '2022-07-30 13:19:12', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('1460427339745763329', '1', '-1', '2021-11-16 09:59:45', '2022-07-29 22:41:23', 0, 0, 'test', '992171f4f472ae8360a32663d9529339', 2, 0, '1443501889504210946,1443502090977603585,1443502302433439746', 2, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/22358982-11.jpg', '17600000001', 'Qqq', '0', 0, '2021-11-16 09:59:46', '2022-07-29 22:41:24', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('685453406529261568', '1', '-1', '2022-05-14 21:53:57', '2022-07-22 09:49:02', 1, 0, 'hexin', 'd508eefe214884b363bf33882afb4ed3', 0, 0, '1443501889504210946,1468426496627490818', 0, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/25847053-8.jpg', '17600000000', '何鑫', '1', 20, '2022-05-14 21:53:57', '2022-06-17 13:53:52', NULL, NULL);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统通用字段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_gc_test
-- ----------------------------
DROP TABLE IF EXISTS `t_gc_test`;
CREATE TABLE `t_gc_test`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称 (文本)',
  `age` double(10, 2) DEFAULT NULL COMMENT '年龄 (数字)',
  `sex` int(1) DEFAULT NULL COMMENT '性别 (单选--字典)',
  `like` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '爱好 (多选--字典)',
  `city` int(1) DEFAULT NULL COMMENT '城市 (下拉选--字典)',
  `disable` int(1) DEFAULT NULL COMMENT '禁用 (开关--字典)',
  `head_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像 (文件上传)',
  `time` datetime(0) DEFAULT NULL COMMENT '时间',
  `text` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更多信息(大文本)',
  `text_two` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '更多信息(富文本)',
  `text_three` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '更多信息(md编辑器)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_gc_test
-- ----------------------------
INSERT INTO `t_gc_test` VALUES ('1456780575175118849', NULL, NULL, '2021-11-06 08:28:48', '2021-11-09 21:32:51', 1, 0, '兮家小二', 20.00, 3, '3,2', 2, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/32859734-timg(3).jpg', '2021-11-06 08:28:31', '啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦', NULL, NULL);
INSERT INTO `t_gc_test` VALUES ('1458065155967528961', NULL, NULL, '2021-11-09 21:33:17', '2022-01-13 22:09:16', 1, 0, '测试', 22.00, 2, '2,3', 3, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/09798637-timg(2).jpg', '2021-11-09 09:33:11', '啦啦啦啦啦啦啦', NULL, NULL);
INSERT INTO `t_gc_test` VALUES ('1475676902860955649', NULL, NULL, '2021-12-28 11:56:04', '2022-01-13 22:09:19', 1, 0, '测试数据1', 23.00, 2, '2,3', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/56163330-6226ba315230576c0c8c99a6c1fbc4b7.jpeg', '2021-12-28 11:56:00', '测试数据', NULL, NULL);
INSERT INTO `t_gc_test` VALUES ('1481629780875939841', NULL, NULL, '2022-01-13 22:10:40', '2022-06-15 16:08:46', 0, 0, '代码生成测试', 221.00, 1, '2,1', 1, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/01628736-qs44ufe2024qs44ufe2024.jpg', '2022-01-13 10:10:07', 'asjdasnjkasnkdnaskdkasa', NULL, NULL);
INSERT INTO `t_gc_test` VALUES ('1482723544927358977', NULL, NULL, '2022-01-16 22:36:53', '2022-01-25 14:55:24', 1, 0, '测试', 22.00, 2, '1,2', 2, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/49117698-timg.jpg', '2022-01-16 18:36:52', '测试', NULL, NULL);
INSERT INTO `t_gc_test` VALUES ('1485868996178743298', NULL, NULL, '2022-01-25 14:55:49', '2022-06-15 16:08:45', 0, 0, '111', 0.00, 1, '1', 1, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/24735070-234308-16202293885efd.jpg', '2022-01-26 04:00:00', '111', '<div class=\"c-font-normal c-color-text ellipsis_efK7X\" aria-label=\"职业：演员、导演\">\n<div class=\"text_2NOr6\">职业：演员、导演</div>\n</div>\n<div class=\"c-font-normal c-color-text ellipsis_efK7X\" aria-label=\"生日：1984年6月26日\">\n<div class=\"text_2NOr6\">生日：1984年6月26日</div>\n</div>\n<div class=\"c-font-normal c-color-text ellipsis_efK7X\" aria-label=\"个人信息：172 cm/巨蟹座/O型\">\n<div class=\"text_2NOr6\">个人信息：172 cm/巨蟹座/O型</div>\n</div>\n<div class=\"c-font-normal c-color-text ellipsis_efK7X\" aria-label=\"代表作品：雪豹、西游&middot;降魔篇、裸婚时代、失恋33天、海洋天堂、小爸爸、奋斗、少帅、陆垚知马俐、剃刀边缘\">\n<div class=\"text_2NOr6\">代表作品：雪豹、西游&middot;降魔篇、裸婚时代、失恋33天、海洋天堂、小爸爸、奋斗、少帅、陆垚知马俐、剃刀边缘</div>\n</div>\n<div>\n<div class=\"c-font-normal c-color-text\" aria-label=\"文章，1984年6月26日出生于陕西省西安市雁塔区，中国内地男演员、导演，毕业于中央戏剧学院表演系。2006年，参演电视剧《与青春有关的日子》，开始在影视圈崭露头角。2005年，拍摄古装剧《锦衣卫》。2007年，主演赵宝刚导演的青春剧《奋斗》；同年，主演首部电影《走着瞧》。2008年，主演滕华涛执导的电视剧《蜗居》，饰演80后城市青年小贝。2009年，在电影《海洋天堂》中扮演自闭症患者王大福；同年，参演抗战剧《雪豹》。2011年，主演的电视剧《裸婚时代》播出；同年，连续2年获得北京大学生电影节最受大学生欢迎男演员奖。2012年，凭借电影《失恋33天》获得第31届大众电影百花奖最佳男主角奖；同年，成立北京君竹影视文化有限公司，并导演第一部影视作品《小爸爸》。2013年2月，主演的电影《西游&middot;降魔篇》在全国上映。2014年3月28日，主演的爱情片《我在路上最爱你》上映。2014年，在姜文执导的\">\n<div class=\"text_2NOr6\">简介：<em>文章</em>，1984年6月26日出生于陕西省西安市雁塔区，中...</div>\n<div class=\"text_2NOr6\">&nbsp;</div>\n<div class=\"text_2NOr6\"><img src=\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vueTinymce/50305961-mceclip0.png\" /></div>\n</div>\n</div>', NULL);
INSERT INTO `t_gc_test` VALUES ('1536984392793067521', NULL, NULL, '2022-06-15 16:10:09', '2022-06-26 11:43:38', 0, 0, 'ht', 12.00, 0, '2,1', 4, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/53319591-1.jpg', '2022-06-15 12:09:59', '123', '<p>啦啦啦啦</p>', '啦啦');
INSERT INTO `t_gc_test` VALUES ('1540901585683599362', NULL, NULL, '2022-06-26 11:35:42', '2022-06-26 11:35:50', 0, 0, '小二', 10.00, 1, '1', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/gc/42424699-5.png', '2022-06-26 12:00:00', '啦啦啦啦啦啦啦啦啦啦', '<p><img src=\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vueTinymce/14503340-mceclip0.png\" width=\"207\" height=\"224\" /> &nbsp;</p>\n<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n<tbody>\n<tr>\n<td style=\"width: 25%;\">&nbsp;</td>\n<td style=\"width: 25%;\">&nbsp;</td>\n<td style=\"width: 25%;\">&nbsp;</td>\n<td style=\"width: 25%;\">&nbsp;</td>\n</tr>\n<tr>\n<td style=\"width: 25%;\">&nbsp;</td>\n<td style=\"width: 25%;\">&nbsp;</td>\n<td style=\"width: 25%;\">&nbsp;</td>\n<td style=\"width: 25%;\">&nbsp;</td>\n</tr>\n</tbody>\n</table>\n<p>啦啦啦啦啦啦啦啦</p>', '![image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vMdEditor/13388095-image.png)\n\nlla\n## 一级目录\n### 二级目录');
INSERT INTO `t_gc_test` VALUES ('1540913681519374338', NULL, NULL, '2022-06-26 12:23:46', '2022-07-27 16:14:14', 0, 0, '小五', 20.00, 1, '2', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/gc/14829444-4.png', '2022-06-23 12:00:00', '啦啦啦啦啦啦啦', '<p><img src=\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vueTinymce/14020004-mceclip1.png\" width=\"364\" height=\"158\" /></p>', '## 一级目录\n### 二级目录\n\n\n![image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vMdEditor/26206899-image.png)');
INSERT INTO `t_gc_test` VALUES ('1540915502342238210', NULL, NULL, '2022-06-26 12:31:00', '2022-07-27 16:14:12', 0, 0, '小刘', 100.00, 1, '1,2', 4, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/gc/43718824-5.png', '2022-06-26 20:29:52', '1\n', '<p>1</p>', '## 一级目录\n### 二级目录\n![image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vMdEditor/51645894-image.png)');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--banner' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_xj_admin_banner
-- ----------------------------
INSERT INTO `t_xj_admin_banner` VALUES ('1300260217146548226', NULL, NULL, '2020-08-31 10:32:48', '2022-06-27 09:14:34', 0, 0, 1, '测试2', '测试数据2', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210401214853224489-qs44ufe2024qs44ufe2024.jpg', 1, 0, 0, '/page/logoBanner/page/logoBanner');
INSERT INTO `t_xj_admin_banner` VALUES ('1300262684328435714', NULL, NULL, '2020-08-31 10:42:36', '2022-06-15 11:38:39', 0, 0, 1, '测试1', '测试数据一', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210322103754968659-aaaa.png', 0, 0, 2, 'http://www.baidu.com');
INSERT INTO `t_xj_admin_banner` VALUES ('1309111625118248961', NULL, NULL, '2020-09-24 20:45:06', '2022-06-15 11:38:37', 0, 0, 1, '测试', '测试描叙', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/2.jpg', 0, 0, 0, '');
INSERT INTO `t_xj_admin_banner` VALUES ('1369571919208206338', NULL, NULL, '2021-03-10 16:52:44', '2021-11-14 18:58:41', 1, 0, 1, '1', '1', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210310165236917290-5.jpg', 0, 0, 1, 'bbb');
INSERT INTO `t_xj_admin_banner` VALUES ('1451984726834319362', NULL, NULL, '2021-10-24 02:51:50', '2021-10-24 02:51:54', 1, 0, 1, '2', '3', '4', 5, 6, 7, '8');
INSERT INTO `t_xj_admin_banner` VALUES ('1459838027261095938', NULL, NULL, '2021-11-14 18:58:02', '2022-07-25 10:29:20', 0, 0, 1, '测试', '啦啦啦啦', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/41883812-timg(2).jpg', 0, 0, 0, '');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--黑名单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_xj_admin_blacklist
-- ----------------------------
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332333202949324802', NULL, NULL, '2020-11-27 22:39:21', '2021-11-21 11:21:10', 0, 0, 1, '*', '允许所有 ip 地址访问，优先级比黑名单(*) 高 ， 开启了白名单(*), 黑名单（*）将无效', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332337401510551554', NULL, NULL, '2020-11-27 22:56:02', '2022-07-12 23:05:17', 1, 0, 2, '*', '禁止所有 ip 访问,除本地 [127.0.0.1 / localhost] ,不建议配置在所有资源上，一旦配置，所有用户(包括自己) 将无法访问所有资源，因为每个用户的ip地址都不一样， 开启此功能需提前配置所有用户的ip地址为白名单', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1421369811404894210', NULL, NULL, '2021-07-31 15:19:05', '2022-07-14 23:32:22', 0, 0, 2, '192.168.1.10', '本地', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1462259668311121921', NULL, NULL, '2021-11-21 11:20:47', '2021-12-09 13:48:18', 1, 0, 1, '1', '1', 0);

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
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '类型(0-文本 1-图片 2-开关 3-富文本)',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `ext1` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--全局数据配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_xj_admin_config
-- ----------------------------
INSERT INTO `t_xj_admin_config` VALUES ('1365174805250260994', NULL, '-1', '2021-02-26 13:40:11', '2022-07-26 14:30:54', 0, 0, 'entry_name', '项目名称', 'spring-boot-plus2', 0, 0, '登录页和左菜单顶部标题', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1365182627308433409', NULL, '-1', '2021-02-26 14:11:17', '2022-06-04 15:30:01', 0, 0, 'login_bg_img', '背景图(登录页)', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210311113615990505-1.jpg', 0, 1, '未启用', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1365185332319997953', NULL, '-1', '2021-02-26 14:22:01', '2022-06-04 15:30:05', 0, 0, 'beian', '备案号(登录页)', '备案号：蜀ICP备19022468号-1', 0, 0, '未启用', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1365187122549551105', NULL, '-1', '2021-02-26 14:29:09', '2022-06-04 15:30:08', 0, 0, 'project_desc', '项目描叙(登录页)', '©2020-2021 该后台系统为个人开发运营，作者联系方式 QQ:1720696548', 0, 0, '未启用', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1383627414470467586', NULL, '-1', '2021-04-18 11:44:16', '2022-06-04 10:44:12', 0, 0, 'is_sign', '验签开关', 'true', 0, 2, '验签总开关 |  true  需验签(默认)   false=无需验签, 开启后可在接口管理中对单个接口进行配置', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1383636872395255809', NULL, '-1', '2021-04-18 12:21:51', '2022-06-04 10:46:12', 0, 0, 'is_swagger', 'swagger文档开关', 'true', 0, 2, '动态开关是否可在线查看接口文档，关闭后所有接口将隐藏展示', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1432597381643304961', NULL, '-1', '2021-08-31 14:53:26', '2022-06-04 10:48:36', 0, 0, 'is_auth', '接口是否验权', 'true', 0, 2, '接口权限管理，开启后在接口管理中可单独配置，和登录人当前拥有角色是否分配指定接口权限相关', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1441701074921598977', NULL, '-1', '2021-09-25 17:48:16', '2022-06-04 10:51:28', 0, 0, 'login_expiration_manage', '登录有效期', '60', 0, 0, '登录状态切对当前系统无如何操作后，当前登录状态保持时长,  防止离开后被别人操作， 单位分', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1481920988265320449', NULL, NULL, '2022-01-14 17:27:51', '2022-06-27 09:14:01', 1, 0, '测试数据', 'test', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/45321474-232917-1636990157bc70.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/47041365-234759-16363000792588.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/49545567-193708-15779650288588.jpg', 0, 1, '测试数据', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1524630203798654978', NULL, NULL, '2022-05-12 13:59:01', '2022-07-12 23:05:08', 1, 0, 'fwb-test', '富文本%测试 ', '<p>&nbsp;</p>\n<p>啦啦啦</p>\n<p><img src=\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vueTinymce/30177849-mceclip0.png\" /></p>', 0, 3, '测试', '', NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1537781744705613825', NULL, NULL, '2022-06-17 20:58:30', '2022-06-27 09:14:07', 1, 0, 'md-test', 'md编辑器测试', '## 一级测试\n### 二级测试\n\n图片测试\n\n![image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vMdEditor/20968351-image.png)', 0, 4, '', '', '', '');

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
  `db_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'db -标题',
  `db_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'db 库名',
  `db_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'db 连接地址',
  `db_username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'db 账号',
  `db_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'db 密码',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `describe` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述信息',
  `project_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目名/路径，如：xj-server/xj-test-server',
  `pack_path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '包路径 (如: io.github.wslxm)',
  `root_module` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '根模块 (固定为：modules(管理端), 用户端为：client)',
  `modules_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子模块 (业务分类,如用户管理,订单管理模块拆分，也可以统一一个名称放在一起)',
  `db_table_prefix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'db 表前缀 (生成的类名会过滤掉前缀)',
  `db_field_prefix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'db 字段前缀 (生成的字段名会过滤掉前缀)',
  `entity_swagger` tinyint(1) DEFAULT NULL COMMENT '实体类是否使用swagger注释 (false情况下使用doc注释)',
  `father_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成路径(不填默认当前项目跟目录,可指定绝对路径)',
  `vue_field_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '排除vue字段类型 (字典code值，参考字典生成字段类型，如: 18=富文本 19=md编辑器 )',
  `base_fields` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库通用字段',
  `keyword_array` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库关键字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--代码生成动态数据源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_xj_admin_datasource
-- ----------------------------
INSERT INTO `t_xj_admin_datasource` VALUES ('1553023868891852801', NULL, NULL, '2022-07-29 22:25:16', '2022-07-30 11:01:54', 0, 0, 'spring-boot-plus2 (本地)', 'spring-boot-plus2', '127.0.0.1:3306', 'root', 'MTIzNDU2', 'ws', '1720696548@qq.com', '::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655', 'xj-base/xj-base-admin', 'io.github.wslxm.springbootplus2', 'manage', 'test1', 't_', NULL, 1, '', '18,19', 'id,create_user,update_user,create_time,update_time,deleted,version', 'time,desc,name,key,value,mysql,info,form,sort,icon,like,unlock,unLock,comment,disable,force,describe');
INSERT INTO `t_xj_admin_datasource` VALUES ('1553190478550188034', NULL, NULL, '2022-07-30 09:27:21', '2022-07-30 11:02:30', 0, 0, 'xijia-tool-app (pro)', 'xijia-tool-app', 'rm-8vbkpoec225c821d8vo.mysql.zhangbei.rds.aliyuncs.com', 'root', 'WEpyb290MTIzNDU2', 'ws', '1720696548@qq.com', '::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655', 'xj-base/xj-base-admin', 'io.github.wslxm.springbootplus2', 'manage', 'test', 't_', NULL, 0, 'F://javagc/xijia-tool-app/', '18,19', 'id,create_user,update_user,create_time,update_time,deleted,version', 'time,desc,name,key,value,mysql,info,form,sort,icon,like,unlock,unLock,comment,disable,force,describe');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--请求记录表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--消息通知' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_xj_admin_msg
-- ----------------------------
INSERT INTO `t_xj_admin_msg` VALUES ('1539641080650199042', NULL, NULL, '2022-06-23 00:07:02', '2022-07-14 16:22:35', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?fullName=平台主账号\",\"routePathTwo\":\"/app/user/details&fullName=平台主账号\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询 名字= 平台主账号 的数据\"}', 2, 2, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1539641211692838913', NULL, NULL, '2022-06-23 00:07:34', '2022-07-14 16:22:32', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?null\",\"routePathTwo\":\"/app/user/details&null\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询所有数据\"}', 2, 2, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1539641447932817410', NULL, NULL, '2022-06-23 00:08:30', '2022-07-26 15:47:01', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin\",\"routePathTwo\":\"/app/user/details&username=admin\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询账号= admin的数据\"}', 2, 2, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1539642142241124354', NULL, NULL, '2022-06-23 00:11:16', '2022-07-26 15:10:52', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin\",\"routePathTwo\":\"/app/user/details&username=admin\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询账号= admin的数据\"}', 2, 2, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1539642214546731009', NULL, NULL, '2022-06-23 00:11:33', '2022-07-26 15:10:50', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin\",\"routePathTwo\":\"/app/user/details&username=admin\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询账号= admin的数据\"}', 2, 2, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1539643597421342721', NULL, NULL, '2022-06-23 00:17:02', '2022-07-26 15:10:48', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin&fullname=平台主账号\",\"routePathTwo\":\"/app/user/details&username=admin&fullname=平台主账号\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,多参数查询\"}', 2, 2, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1539643743827718145', NULL, NULL, '2022-06-23 00:17:37', '2022-07-26 15:10:45', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin&fullName=平台主账号\",\"routePathTwo\":\"/app/user/details&username=admin&fullName=平台主账号\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,多参数查询\"}', 2, 2, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1547124953474666498', NULL, NULL, '2022-07-13 15:45:07', '2022-07-16 11:47:51', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"测试消息\",\"message\":\"测试消息\\n\"}', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1547125098266234881', NULL, NULL, '2022-07-13 15:45:42', '2022-07-14 23:30:56', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"测试消息\",\"message\":\"测试消息2\"}', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1547133897974747137', NULL, NULL, '2022-07-13 16:20:40', '2022-07-14 23:30:53', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"测试消息\",\"message\":\"测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息\"}', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1549224392100155393', NULL, NULL, '2022-07-19 10:47:33', '2022-07-19 10:48:42', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"测试消息\",\"message\":\"2133213123\"}', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1549224545079005186', NULL, NULL, '2022-07-19 10:48:09', '2022-07-19 10:48:35', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"系统通知\",\"message\":\"2121222\"}', 1, 1, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1549270171959431170', NULL, NULL, '2022-07-19 13:49:27', '2022-07-19 13:49:43', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"测试消息\",\"message\":\"1111111111\"}', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1550126127786233858', NULL, NULL, '2022-07-21 22:30:43', '2022-07-21 22:32:09', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"测试消息\",\"message\":\"111\"}', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1550126379075375105', NULL, NULL, '2022-07-21 22:31:43', '2022-07-21 22:32:07', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"系统通知\",\"message\":\"123123123123\"}', 2, 1, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1550126552832806913', NULL, NULL, '2022-07-21 22:32:24', '2022-07-21 22:33:06', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"测试消息\",\"message\":\"234\"}', 1, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1550126622894460929', NULL, NULL, '2022-07-21 22:32:41', '2022-07-21 22:33:05', 0, 0, '1460427339745763329', '{\"routePath\":\"/views/admin/user/user?null\",\"routePathTwo\":\"/app/user/details&null\",\"title\":\"用户信息变动\",\"message\":\"1\"}', 2, 2, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1550126684873691138', NULL, NULL, '2022-07-21 22:32:56', '2022-07-26 13:57:17', 0, 0, '1', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"系统通知\",\"message\":\"1\"}', 2, 1, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1550128180927401985', NULL, NULL, '2022-07-21 22:38:53', '2022-07-22 09:48:33', 0, 0, '1460427339745763329', '{\"routePath\":\"\",\"routePathTwo\":\"\",\"title\":\"测试消息\",\"message\":\"32232\"}', 1, 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
