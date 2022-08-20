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

 Date: 20/08/2022 13:20:22
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
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '接口禁用(0-否 1-是)',
  `type` int(1) NOT NULL COMMENT '终端(字典code, 如 0-管理端 1-用户端 更多待定)',
  `state` int(1) NOT NULL COMMENT '授权状态(字典code  0-无需登录 1-需登录 2-需登录+授权(已废弃) 3-需Oauth2 授权 )',
  `is_sign` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要验签(不受限于登录授权)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--权限接口' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES ('590081231815839745', NULL, '0', '2021-09-17 17:39:00', '2021-09-17 17:39:00', 0, 959, '0', '', '/api/admin/generate', 'base--gc--代码生成', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590082183939624962', NULL, '0', '2021-09-17 17:42:47', '2022-08-20 08:44:09', 0, 958, '590081231815839745', 'GET', '/api/admin/generate/getPath', '代码生成路径', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624963', NULL, '0', '2021-09-17 17:42:47', '2022-08-20 08:44:10', 0, 958, '590081231815839745', 'POST', '/api/admin/generate/generateCode', '生成代码', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624964', NULL, '0', '2021-09-17 17:42:47', '2022-08-20 08:44:10', 0, 958, '590081231815839745', 'POST', '/api/admin/generate/preview', '生成预览代码', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518954377221', NULL, '0', '2021-09-18 14:27:52', '2021-09-18 14:27:52', 0, 925, '0', '', '/api/admin/xj/config', 'base--plus--全局配置', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518958571521', NULL, '0', '2021-09-18 14:27:52', '2022-08-20 08:44:10', 0, 925, '590395518954377221', 'POST', '/api/admin/xj/config', '添加', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571522', NULL, '0', '2021-09-18 14:27:52', '2022-08-20 08:44:10', 0, 925, '590395518954377221', 'DELETE', '/api/admin/xj/config/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571524', NULL, '0', '2021-09-18 14:27:52', '2022-08-20 08:44:10', 0, 925, '590395518954377221', 'PUT', '/api/admin/xj/config/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571526', NULL, '0', '2021-09-18 14:27:52', '2021-09-18 14:27:52', 0, 925, '0', '', '/api/admin/xj/log', 'base--plus--操作记录', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765828', NULL, '0', '2021-09-18 14:27:52', '2021-09-18 14:27:52', 0, 925, '0', '', '/api/admin/xj/msg', 'base--plus--消息通知', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765829', NULL, '0', '2021-09-18 14:27:52', '2022-08-20 08:44:10', 0, 925, '590395518962765828', 'POST', '/api/admin/xj/msg', '添加/发送消息', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590398100552683520', NULL, '0', '2021-09-18 14:38:08', '2022-08-20 08:44:11', 0, 924, '590395518962765828', 'PUT', '/api/admin/xj/msg/{id}/read', '消息修改为已读', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590417006180831232', NULL, '0', '2021-09-18 15:53:15', '2022-08-20 08:44:11', 0, 915, '590395518962765828', 'GET', '/api/admin/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862656', NULL, '0', '2021-09-18 17:51:37', '2021-09-18 17:51:37', 0, 906, '0', '', '/api/admin/authority', 'base--admin--URL权限管理', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590446794257862657', NULL, '0', '2021-09-18 17:51:37', '2022-08-06 11:58:24', 0, 906, '590446794257862656', 'GET', '/api/admin/authority/list', '查询所有-接口管理', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862658', NULL, '0', '2021-09-18 17:51:37', '2022-07-29 06:03:23', 0, 906, '590446794257862656', 'PUT', '/api/admin/authority/{id}', 'ID编辑', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862660', NULL, '0', '2021-09-18 17:51:37', '2022-07-29 06:03:20', 0, 906, '590446794257862656', 'PUT', '/api/admin/authority/refreshAuthority', '扫描权限', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717120', NULL, '0', '2021-09-18 18:22:46', '2021-09-18 18:22:46', 0, 903, '0', '', '/api/admin/dictionary', 'base--admin--字典管理', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590454633059717121', NULL, '0', '2021-09-18 18:22:46', '2022-08-20 08:44:11', 0, 903, '590454633059717120', 'GET', '/api/admin/dictionary/list', '列表查询 (默认返回Tree数据,可指定Tree或List)', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717122', NULL, '0', '2021-09-18 18:22:46', '2022-08-20 08:44:11', 0, 903, '590454633059717120', 'POST', '/api/admin/dictionary', '添加', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911424', NULL, '0', '2021-09-18 18:22:46', '2022-08-20 08:44:11', 0, 903, '590454633059717120', 'DELETE', '/api/admin/dictionary/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911425', NULL, '0', '2021-09-18 18:22:46', '2022-08-20 08:44:11', 0, 903, '590454633059717120', 'GET', '/api/admin/dictionary/findCodeGroup', '查询所有-code分组', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911426', NULL, '0', '2021-09-18 18:22:46', '2022-08-20 08:44:11', 0, 903, '590454633059717120', 'PUT', '/api/admin/dictionary/{id}', '编辑', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911427', NULL, '0', '2021-09-18 18:22:46', '2022-08-20 08:44:12', 0, 903, '590454633059717120', 'GET', '/api/admin/dictionary/generateEnum', '生成枚举', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911429', NULL, '0', '2021-09-18 18:22:46', '2022-08-20 08:44:12', 0, 903, '590454633059717120', 'GET', '/api/admin/dictionary/list/category', '获取类别(级联数据)', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452416', NULL, '0', '2021-09-18 19:57:14', '2021-09-18 19:57:14', 0, 898, '0', '', '/api/admin/menu', 'base--admin--菜单管理', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452417', NULL, '0', '2021-09-18 19:57:14', '2022-08-20 08:44:12', 0, 898, '590478406475452416', 'GET', '/api/admin/menu/list', '列表查询(不支持分页)', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452418', NULL, '0', '2021-09-18 19:57:14', '2022-08-20 08:44:12', 0, 898, '590478406475452416', 'POST', '/api/admin/menu', '菜单添加', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452419', NULL, '0', '2021-09-18 19:57:14', '2022-08-20 08:44:12', 0, 898, '590478406475452416', 'PUT', '/api/admin/menu/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452421', NULL, '0', '2021-09-18 19:57:14', '2022-08-20 08:44:12', 0, 898, '590478406475452416', 'GET', '/api/admin/menu/findTree', '左导航菜单', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452423', NULL, '0', '2021-09-18 19:57:14', '2022-08-20 08:44:12', 0, 898, '590478406475452416', 'DELETE', '/api/admin/menu/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452424', NULL, '0', '2021-09-18 19:57:14', '2021-09-18 19:57:14', 0, 898, '0', '', '/api/admin/role', 'base--admin--角色管理', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452425', NULL, '0', '2021-09-18 19:57:14', '2022-08-20 08:44:13', 0, 898, '590478406475452424', 'POST', '/api/admin/role', '添加', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646721', NULL, '0', '2021-09-18 19:57:14', '2022-08-20 08:44:13', 0, 898, '590478406475452424', 'PUT', '/api/admin/role/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646727', NULL, '0', '2021-09-18 19:57:14', '2022-08-20 08:44:13', 0, 898, '590478406475452424', 'DELETE', '/api/admin/role/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('592136133727621120', NULL, '0', '2021-09-23 09:44:28', '2022-08-20 08:44:13', 0, 840, '590395518962765828', 'GET', '/api/admin/xj/msg/findAllNum', '查询全部/已读/未读数量(当前登录用户)', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761932664832', NULL, '0', '2021-09-30 15:17:51', '2021-09-30 15:17:51', 0, 799, '0', '', '/api/client/dictionary', 'yh--base--字典管理', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664833', NULL, '0', '2021-09-30 15:17:51', '2021-09-30 15:17:51', 0, 799, '594756761932664832', 'GET', '/api/client/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664834', NULL, '0', '2021-09-30 15:17:51', '2022-01-11 18:43:01', 0, 799, '594756761932664832', 'GET', '/api/client/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664835', NULL, '0', '2021-09-30 15:17:51', '2021-09-30 15:17:51', 0, 799, '0', '', '/api/client/xj/banner', 'yh--base-plus--banner', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442057', NULL, '0', '2021-09-30 15:17:51', '2021-09-30 15:17:51', 0, 799, '0', '', '/api/admin/datasource', 'base--gc--代码生成--数据源维护', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442059', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:13', 0, 799, '594756761949442057', 'POST', '/api/admin/datasource', '添加', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442060', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:13', 0, 799, '594756761949442057', 'PUT', '/api/admin/datasource/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636352', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:13', 0, 799, '594756761949442057', 'DELETE', '/api/admin/datasource/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636354', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:14', 0, 799, '594756761949442057', 'PUT', '/api/admin/datasource/{id}/updPwd', '修改/重置密码', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636358', NULL, '0', '2021-09-30 15:17:51', '2021-09-30 15:17:51', 0, 799, '0', '', '/api/admin/xj/banner', 'base--plus--banner', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761953636360', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:14', 0, 799, '594756761953636358', 'POST', '/api/admin/xj/banner', '添加', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636361', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:14', 0, 799, '594756761953636358', 'PUT', '/api/admin/xj/banner/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636362', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:14', 0, 799, '594756761953636358', 'DELETE', '/api/admin/xj/banner/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830656', NULL, '0', '2021-09-30 15:17:51', '2021-09-30 15:17:51', 0, 799, '0', '', '/api/admin/xj/blacklist', 'base--plus--黑名单', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761957830658', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:14', 0, 799, '594756761957830656', 'POST', '/api/admin/xj/blacklist', '添加', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830659', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:14', 0, 799, '594756761957830656', 'PUT', '/api/admin/xj/blacklist/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830660', NULL, '0', '2021-09-30 15:17:51', '2022-08-20 08:44:14', 0, 799, '594756761957830656', 'DELETE', '/api/admin/xj/blacklist/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558656827392', NULL, '0', '2021-10-01 13:40:08', '2021-10-01 13:40:08', 0, 764, '0', '', '/api/client/xj/config', 'yh--base-plus--全局配置', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827393', NULL, '0', '2021-10-01 13:40:08', '2021-10-01 13:40:08', 0, 764, '595094558656827392', 'GET', '/api/client/xj/config/findByCode', 'CODE查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827394', NULL, '0', '2021-10-01 13:40:08', '2021-10-01 13:40:08', 0, 764, '0', '', '/api/client/xj/msg', 'yh--base-plus--消息通知', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827396', NULL, '0', '2021-10-01 13:40:08', '2021-10-01 13:40:08', 0, 764, '595094558656827394', 'PUT', '/api/client/xj/msg/{id}/read', '消息修改为已读', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827397', NULL, '0', '2021-10-01 13:40:08', '2021-10-01 13:40:08', 0, 764, '595094558656827394', 'GET', '/api/client/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410304', NULL, '0', '2021-10-01 13:40:08', '2021-10-01 13:40:08', 0, 764, '0', '', '/api/admin/user', 'base--admin--用户管理', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410306', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:14', 0, 764, '595094558669410304', 'POST', '/api/admin/user', '添加', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410307', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:15', 0, 764, '595094558669410304', 'DELETE', '/api/admin/user/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410308', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:15', 0, 764, '595094558669410304', 'GET', '/api/admin/user/{id}', 'ID查询', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410309', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:15', 0, 764, '595094558669410304', 'PUT', '/api/admin/user/updUser', '修改当前登录人的信息', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410310', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:15', 0, 764, '595094558669410304', 'GET', '/api/admin/user/findByRoleId', '获取指定角色的用户列表', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410311', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:15', 0, 764, '595094558669410304', 'PUT', '/api/admin/user/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410312', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:15', 0, 764, '595094558669410304', 'PUT', '/api/admin/user/updByPassword', '修改当前登录人的密码', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410313', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:15', 0, 764, '595094558669410304', 'GET', '/api/admin/user/list/keyData', '查询所有-只返回关键数据(姓名/昵称/电话/id)', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410314', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:15', 0, 764, '595094558669410304', 'PUT', '/api/admin/user/{id}/resetPassword', '重置任意用户密码', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604608', NULL, '0', '2021-10-01 13:40:08', '2022-02-03 14:43:43', 0, 764, '595094558669410304', 'GET', '/api/admin/user/findUser', '查询当前登录人的个人信息', 0, 0, 3, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604609', NULL, '0', '2021-10-01 13:40:08', '2021-10-09 22:23:30', 0, 764, '595094558669410304', 'POST', '/api/admin/user/login', '用户登录', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604610', NULL, '0', '2021-10-01 13:40:08', '2021-10-01 13:40:08', 0, 764, '0', '', '/api/admin/dataBase', 'base--gc--代码生成--查询表数据', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558673604611', NULL, '0', '2021-10-01 13:40:08', '2022-07-29 06:01:55', 0, 764, '595094558673604610', 'GET', '/api/admin/dataBase/table/list', '查询所有表名', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558677798912', NULL, '0', '2021-10-01 13:40:08', '2021-12-14 19:20:48', 0, 764, '595094558673604610', 'GET', '/api/admin/dataBase/table/field', '查询指定表下所有字段内容', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558686187520', NULL, '0', '2021-10-01 13:40:08', '2021-10-01 13:40:08', 0, 764, '0', '', '/api/admin/xj/jvm', 'base--plus--jvm信息获取', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558686187521', NULL, '0', '2021-10-01 13:40:08', '2022-08-20 08:44:16', 0, 764, '595094558686187520', 'GET', '/api/admin/xj/jvm/jvmInfo', '3、系统的jvm信息', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('598128304653996032', NULL, '0', '2021-10-09 22:35:13', '2021-10-09 22:36:06', 0, 753, '590395518954377221', 'GET', '/api/admin/xj/config/findByCode', 'CODE查询', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('613640045747900416', NULL, '0', '2021-11-21 17:53:18', '2022-08-20 08:44:16', 0, 586, '590081231815839745', 'POST', '/api/admin/generate/generateCodeVue', '生成Vue代码(将直接下载)', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('620065229904154624', NULL, '0', '2021-12-09 11:24:40', '2022-07-29 08:11:43', 0, 528, '594756761932664835', 'GET', '/api/client/xj/banner/list/{position}', '列表-位置查询', 0, 1, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('691539110942347264', NULL, '0', '2022-06-06 08:56:21', '2022-08-20 08:44:16', 0, 264, '590395518954377221', 'GET', '/api/admin/xj/config/{id}', 'ID查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('697255086296010752', NULL, '0', '2022-06-20 03:29:37', '2022-06-20 03:29:37', 0, 179, '0', '', '/api/open/redis', 'Redis  -->  Redis 测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('697255086300205057', NULL, '0', '2022-06-20 03:29:37', '2022-06-20 03:29:37', 0, 179, '697255086296010752', 'GET', '/api/open/redis/getNo', '获取分布式唯一编号', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('697398103711551488', NULL, '0', '2022-06-20 04:57:58', '2022-06-20 04:57:58', 0, 177, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest2/{key}', 'redis 分布式锁加锁测试2', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('697398103711551489', NULL, '0', '2022-06-20 04:57:58', '2022-06-20 04:57:58', 0, 177, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest1/{key}', 'redis 分布式锁加锁测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('697398103711551490', NULL, '0', '2022-06-20 04:57:58', '2022-06-20 04:57:58', 0, 177, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest3', 'redis 分布式注解锁测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734145421312', NULL, '0', '2022-06-28 03:32:41', '2022-06-28 03:32:41', 0, 143, '0', '', '/api/admin/test/gcTest', '代码生成测试表', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734145421313', NULL, '0', '2022-06-28 03:32:41', '2022-08-20 08:44:17', 0, 143, '700879734145421312', 'GET', '/api/admin/test/gcTest/list', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734145421314', NULL, '0', '2022-06-28 03:32:41', '2022-08-20 08:44:17', 0, 143, '700879734145421312', 'POST', '/api/admin/test/gcTest', '添加', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734145421315', NULL, '0', '2022-06-28 03:32:41', '2022-08-20 08:44:17', 0, 143, '700879734145421312', 'DELETE', '/api/admin/test/gcTest/{id}', 'ID删除', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734149615616', NULL, '0', '2022-06-28 03:32:41', '2022-08-20 08:44:17', 0, 143, '700879734145421312', 'PUT', '/api/admin/test/gcTest/{id}', 'ID编辑', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('700879734149615617', NULL, '0', '2022-06-28 03:32:41', '2022-08-20 08:44:17', 0, 143, '700879734145421312', 'GET', '/api/admin/test/gcTest/{id}', 'ID查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('711817171210211328', NULL, '0', '2022-07-28 07:54:07', '2022-08-20 08:44:17', 0, 103, '590081231815839745', 'POST', '/api/admin/generate/generateCodeJavaAndVue', '生成java + vue代码(将直接下载)', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('712993653219528704', NULL, '0', '2022-07-31 05:49:02', '2022-08-20 08:44:17', 0, 70, '594756761949442057', 'GET', '/api/admin/datasource/{id}', 'id查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('713000537204854784', NULL, '0', '2022-07-31 06:16:24', '2022-08-20 08:44:18', 0, 69, '594756761949442057', 'POST', '/api/admin/datasource/dataSourceTest/{id}', '数据源连接测试', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720153067504406528', NULL, NULL, '2022-08-18 15:58:00', '2022-08-20 08:44:18', 0, 17, '590478406475452424', 'GET', '/api/admin/role/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720153067504406529', NULL, NULL, '2022-08-18 15:58:00', '2022-08-20 08:44:18', 0, 17, '595094558669410304', 'GET', '/api/admin/user/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720160435072012288', NULL, NULL, '2022-08-18 16:27:17', '2022-08-18 16:27:17', 0, 16, '595094558656827394', 'GET', '/api/client/xj/msg/findPage', '分页查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720160435084595200', NULL, NULL, '2022-08-18 16:27:17', '2022-08-20 08:44:18', 0, 16, '594756761949442057', 'GET', '/api/admin/datasource/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720160435088789504', NULL, NULL, '2022-08-18 16:27:17', '2022-08-20 08:44:18', 0, 16, '594756761953636358', 'GET', '/api/admin/xj/banner/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720160435092983808', NULL, NULL, '2022-08-18 16:27:17', '2022-08-20 08:44:18', 0, 16, '594756761957830656', 'GET', '/api/admin/xj/blacklist/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720160435092983809', NULL, NULL, '2022-08-18 16:27:17', '2022-08-20 08:44:18', 0, 16, '590395518954377221', 'GET', '/api/admin/xj/config/findPage', '分页查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720160435092983810', NULL, NULL, '2022-08-18 16:27:17', '2022-08-20 08:44:18', 0, 16, '590395518958571526', 'GET', '/api/admin/xj/log/findPage', '分页查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720160435092983811', NULL, NULL, '2022-08-18 16:27:17', '2022-08-20 08:44:19', 0, 16, '590395518962765828', 'GET', '/api/admin/xj/msg/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973740335104', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '0', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973740335105', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973740335104', 'GET', '/api/open/websocket/getPath', '获取模拟游客登录的 websocket 连接地址', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973740335106', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973740335104', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973740335107', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973740335104', 'GET', '/api/open/websocket/getOnlineUsersList', '获取当前在线用户列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973740335108', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973740335104', 'GET', '/api/open/websocket/getOnlineCount', '获取在线人数', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973761306624', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '0', '', '/api/open/aliOssFile', 'AliYun --> OSS文件管理', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973761306625', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973761306624', 'POST', '/api/open/aliOssFile/upload', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973761306626', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973761306624', 'DELETE', '/api/open/aliOssFile/del', 'OSS-文件删除', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973761306627', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973761306624', 'GET', '/api/open/aliOssFile/fileList', 'OSS-文件Object列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973761306628', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973761306624', 'GET', '/api/open/aliOssFile/downloadZip', 'OSS-文件下载--多文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720779973761306629', NULL, NULL, '2022-08-20 09:29:10', '2022-08-20 09:29:10', 0, 7, '720779973761306624', 'GET', '/api/open/aliOssFile/download', 'OSS-文件下载--单文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('720786445962448896', NULL, NULL, '2022-08-20 09:54:54', '2022-08-20 09:54:54', 0, 6, '0', '', '/api/admin/dep', 'base--admin--组织机构', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720786445962448897', NULL, NULL, '2022-08-20 09:54:54', '2022-08-20 09:54:54', 0, 6, '720786445962448896', 'GET', '/api/admin/dep/list', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720786445962448898', NULL, NULL, '2022-08-20 09:54:54', '2022-08-20 09:54:54', 0, 6, '720786445962448896', 'POST', '/api/admin/dep', '添加', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720786445966643200', NULL, NULL, '2022-08-20 09:54:54', '2022-08-20 09:54:54', 0, 6, '720786445962448896', 'PUT', '/api/admin/dep/{id}', 'ID编辑', 0, 0, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('720786445966643201', NULL, NULL, '2022-08-20 09:54:54', '2022-08-20 09:54:54', 0, 6, '720786445962448896', 'DELETE', '/api/admin/dep/{id}', 'ID删除(并删除子数据)', 0, 0, 1, 0);

-- ----------------------------
-- Table structure for t_admin_dep
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_dep`;
CREATE TABLE `t_admin_dep`  (
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
-- Records of t_admin_dep
-- ----------------------------
INSERT INTO `t_admin_dep` VALUES ('1443501889504210946', NULL, '-1', '2021-09-30 17:04:03', '2022-06-27 09:13:41', 1, 0, '0', 'zgs', '成都总公司', '-', 0, 0, 1);
INSERT INTO `t_admin_dep` VALUES ('1443502090977603585', NULL, '-1', '2021-09-30 17:04:51', '2022-06-27 09:13:41', 1, 0, '1443501889504210946', 'zgsa', '子公司a', '-', 0, 0, 2);
INSERT INTO `t_admin_dep` VALUES ('1443502157943861250', NULL, '-1', '2021-09-30 17:05:07', '2022-06-27 09:13:41', 1, 0, '1443501889504210946', 'zgsb', '子公司b', '-', 0, 0, 2);
INSERT INTO `t_admin_dep` VALUES ('1443502302433439746', NULL, '-1', '2021-09-30 17:05:42', '2021-10-08 16:24:00', 0, 0, '1443502090977603585', 'csb', '测试部', '-', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1443502428644241409', NULL, '-1', '2021-09-30 17:06:12', '2021-11-29 19:50:02', 0, 0, '1443502157943861250', 'yyb', '运营部', '-', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1468426496627490818', NULL, '-1', '2021-12-08 11:45:33', '2022-06-27 09:13:41', 1, 0, '1443501889504210946', 'zb', '公司总部', '-', 0, 0, 2);
INSERT INTO `t_admin_dep` VALUES ('1481913168983756802', NULL, '-1', '2022-01-14 16:56:46', '2022-05-15 10:45:21', 0, 0, '1481913127925714945', 'xx-dep', 'xx部门', '-', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1481913213086863362', NULL, '-1', '2022-01-14 16:56:57', '2022-05-15 10:45:21', 0, 0, '1481913127925714945', 'xx-dep2', 'xx部门2', '-', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1548901388543594498', NULL, NULL, '2022-07-18 13:24:02', '2022-07-26 14:41:00', 0, 0, '0', 'TEST01', '测试公司1', '测试公司1的描述', 0, 0, 1);
INSERT INTO `t_admin_dep` VALUES ('1548901468621246466', NULL, NULL, '2022-07-18 13:24:22', '2022-07-18 13:25:13', 0, 0, '1548901388543594498', '1', '部门1', '部门1描述', 3, 0, 2);
INSERT INTO `t_admin_dep` VALUES ('1548901576150618114', NULL, NULL, '2022-07-18 13:24:47', '2022-07-18 13:24:47', 0, 0, '1548901468621246466', '2', '部门1_1', '部门1_1desc', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1548901827913715713', NULL, NULL, '2022-07-18 13:25:47', '2022-07-18 13:25:47', 0, 0, '1548901388543594498', '11', '部门2', '部门2desc', 0, 0, 2);
INSERT INTO `t_admin_dep` VALUES ('1548901950412558337', NULL, NULL, '2022-07-18 13:26:16', '2022-07-18 13:26:16', 0, 0, '1548901827913715713', '11', '部门2_1', '部门2_1', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1549253663384408066', NULL, NULL, '2022-07-19 12:43:51', '2022-07-19 12:43:51', 0, 0, '1548901827913715713', '12', '11', '-', 0, 0, 3);

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
INSERT INTO `t_admin_dictionary` VALUES ('1296996224863383554', NULL, '-1', '2020-08-22 02:22:50', '2022-08-20 08:38:06', 0, 0, '2', '需登录+授权', '1296995742778470401', '-已移除角色关联url, 菜单即权限', 0, 1, NULL, NULL, NULL);
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
INSERT INTO `t_admin_dictionary` VALUES ('1477131575404531714', NULL, '-1', '2022-01-01 12:16:26', '2022-08-18 17:33:31', 0, 0, 'DEMO_TEST', 'demo模块测试', '1290684671448936449', '-', 1, 0, NULL, NULL, NULL);
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
INSERT INTO `t_admin_menu` VALUES ('1440256392576483330', '1', '-1', '2021-09-21 18:07:37', '2022-08-18 17:33:35', 0, 0, '0', 'a-vue-2 (test)', '', '', 'layui-icon-file-b', 2, 1, 0, 1);
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
INSERT INTO `t_admin_menu` VALUES ('1459712656557576194', '1', '-1', '2021-11-14 10:39:51', '2022-08-20 10:13:13', 0, 0, '1440255602914869250', '部门管理', NULL, '/views/admin/adminDep/adminDep', 'el-icon-document-remove', 10002, 3, 0, 1);
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
INSERT INTO `t_admin_menu` VALUES ('1552998827391221761', '1', NULL, '2022-07-29 20:45:45', '2022-08-09 16:32:20', 0, 0, '1452091447254749186', '数据源', NULL, '/views/gc/db/xjAdminDatasource', 'el-icon-document-remove', 1, 3, 1, 1);

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
INSERT INTO `t_admin_role` VALUES ('1560826767760027650', '1', NULL, '2022-08-20 11:11:13', '2022-08-20 11:11:23', 0, 0, '测试角色', 'test', '-测试', 0, 1);

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
INSERT INTO `t_admin_role_menu` VALUES ('1560826813914148866', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1440255471893200897');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813926731777', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1461987433667141634');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813926731778', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1452091447254749186');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813926731779', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1452091513113710594');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813926731780', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1456054437146644481');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813939314690', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1440255602914869250');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813939314691', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1516699922710138882');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813939314692', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1516699798743289857');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813939314693', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1516699625820524545');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813947703297', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1440271162033684482');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813947703298', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1459712656557576194');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813947703299', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1442156484086480897');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813947703300', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1449764190750285826');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813956091905', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1459850402525622274');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813956091906', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1462256665587916801');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813956091907', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1451979503835369474');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813960286209', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1468415037767946242');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813960286210', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1462261436877152258');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813964480514', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1440256392576483330');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813964480515', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1440256481906769922');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813964480516', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1481642095692222465');
INSERT INTO `t_admin_role_menu` VALUES ('1560826813964480517', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '1560826767760027650', '1450796419538522114');

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
INSERT INTO `t_admin_role_user` VALUES ('1554676109621284865', '1', '1', '2022-08-03 11:50:46', '2022-08-03 11:50:46', 0, 0, '1460427339745763329', '1447115588580159489');
INSERT INTO `t_admin_role_user` VALUES ('1554676452501442561', '1', '1', '2022-08-03 11:52:07', '2022-08-03 11:52:07', 0, 0, '685453406529261568', '1447115588580159489');
INSERT INTO `t_admin_role_user` VALUES ('1560817143874871297', NULL, NULL, '2022-08-20 10:32:58', '2022-08-20 10:32:58', 0, 0, '1', '1443467633444806658');
INSERT INTO `t_admin_role_user` VALUES ('1560820840893902849', NULL, NULL, '2022-08-20 10:47:40', '2022-08-20 10:47:40', 0, 0, '720799649379782656', '1443467633444806658');

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
  `dep_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司/部门id,多层级前端自行分割数据',
  `position` int(1) NOT NULL DEFAULT 0 COMMENT '职位 (字典code)',
  `terminal` int(1) NOT NULL DEFAULT 1 COMMENT '终端 (字段code)',
  `head_pic` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
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
INSERT INTO `t_admin_user` VALUES ('1', NULL, '0', '2020-08-05 07:11:04', '2022-08-20 13:15:29', 0, 10, 'admin', 'd9880822dd584adce3cde4b024776eef', 1, 0, '1548901388543594498,1548901468621246466,1548901576150618114', 0, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/53206946-4.png', '10000', '平台主账号', '', 11, '2020-08-05 15:11:05', '2022-08-20 13:15:31', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('1460427339745763329', '1', '0', '2021-11-16 09:59:45', '2022-08-19 15:28:18', 0, 0, 'test', '992171f4f472ae8360a32663d9529339', 2, 0, '1443501889504210946,1443502090977603585,1443502302433439746', 2, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/22358982-11.jpg', '17600000001', 'Qqq', '0', 0, '2021-11-16 09:59:46', '2022-08-19 15:28:19', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('685453406529261568', '1', '1', '2022-05-14 21:53:57', '2022-08-03 14:14:43', 0, 0, 'hexin', 'd508eefe214884b363bf33882afb4ed3', 0, 0, '1443501889504210946,1468426496627490818', 0, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/25847053-8.jpg', '17600000000', '何鑫', '1', 20, '2022-05-14 21:53:57', '2022-06-17 13:53:52', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('720799649379782656', '1', NULL, '2022-08-20 10:47:16', '2022-08-20 10:47:39', 0, 0, '1', 'cbb0474b03892e5ae9739003be53953e', 0, 0, '1548901388543594498,1548901827913715713,1548901950412558337', 0, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/38442110-1148e452faa8df9e714a6056b64b5733.jpeg', '1', '1', '1', 0, '2022-08-20 10:47:18', NULL, NULL, NULL);

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
  `filter_crud` tinyint(1) DEFAULT NULL COMMENT '是否过滤crud方法- 默认生成 (controller/service/mapper/xml)',
  `father_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成路径(不填默认当前项目跟目录,可指定绝对路径)',
  `vue_field_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '排除vue字段类型 (字典code值，参考字典生成字段类型，如: 18=富文本 19=md编辑器 )',
  `base_fields` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库通用字段',
  `keyword_array` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库关键字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--代码生成动态数据源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_xj_admin_datasource
-- ----------------------------
INSERT INTO `t_xj_admin_datasource` VALUES ('1553023868891852801', NULL, NULL, '2022-07-29 22:25:16', '2022-07-30 11:01:54', 0, 0, 'spring-boot-plus2 (本地)', 'spring-boot-plus2', '127.0.0.1:3306', 'root', 'MTIzNDU2', 'ws', '1720696548@qq.com', '::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655', 'xj-base/xj-base-admin', 'io.github.wslxm.springbootplus2', 'manage', 'test1', 't_', NULL, 1, NULL, '', '18,19', 'id,create_user,update_user,create_time,update_time,deleted,version', 'time,desc,name,key,value,mysql,info,form,sort,icon,like,unlock,unLock,comment,disable,force,describe');
INSERT INTO `t_xj_admin_datasource` VALUES ('1553190478550188034', NULL, NULL, '2022-07-30 09:27:21', '2022-07-30 11:02:30', 0, 0, 'xijia-tool-app (pro)', 'xijia-tool-app', 'rm-8vbkpoec225c821d8vo.mysql.zhangbei.rds.aliyuncs.com', 'root', 'WEpyb290MTIzNDU2', 'ws', '1720696548@qq.com', '::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655', 'xj-base/xj-base-admin', 'io.github.wslxm.springbootplus2', 'manage', 'test', 't_', NULL, 0, NULL, 'F://javagc/xijia-tool-app/', '18,19', 'id,create_user,update_user,create_time,update_time,deleted,version', 'time,desc,name,key,value,mysql,info,form,sort,icon,like,unlock,unLock,comment,disable,force,describe');
INSERT INTO `t_xj_admin_datasource` VALUES ('1556451746941767681', NULL, '1', '2022-08-08 09:26:28', '2022-08-09 17:52:08', 0, 0, '网格 (个人库 sp-wg)', 'sp-wg', 'rm-8vbkpoec225c821d8vo.mysql.zhangbei.rds.aliyuncs.com', 'root', 'WEpyb290MTIzNDU2', 'wangsong', '1720696548@qq.com', '::代码生成', 'crisps-grid', 'net.crisps.cloud', 'grid', 'info', 'crisps_mch_', NULL, 0, 0, 'D:\\code', '18,19', 'id,creater_id,creater_name,create_time,updater_id,updater_name,update_time', 'time,desc,name,key,value,mysql,info,form,sort,icon,like,unlock,unLock,comment,disable,force,describe');

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
-- Records of t_xj_admin_log
-- ----------------------------
INSERT INTO `t_xj_admin_log` VALUES ('1560819769937096705', NULL, NULL, '2022-08-20 10:43:24', '2022-08-20 10:43:24', 0, 0, '╥﹏╥', '0', -1, 'http://localhost:9000/views/admin/user/user', 'http://127.0.0.1:9048/api/open/aliOssFile/upload', '/api/open/aliOssFile/upload', '127.0.0.1', '127.0.0.1', 'POST', '127.0.0.1', '62779', 'io.github.wslxm.springbootplus2.starter.aliyun.oss.controller', 'io.github.wslxm.springbootplus2.starter.aliyun.oss.controller.AliOssController', 'AliYun --> OSS文件管理', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 'the request data cannot be parsed', '{\"code\":200,\"data\":{\"name\":\"1.jpeg\",\"url\":\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/23631585-1.jpeg\"},\"msg\":\"成功\"}', 1, 2793, 2788);
INSERT INTO `t_xj_admin_log` VALUES ('1560820714490163202', NULL, NULL, '2022-08-20 10:47:10', '2022-08-20 10:47:10', 0, 0, '╥﹏╥', '0', -1, 'http://localhost:9000/views/admin/user/user', 'http://127.0.0.1:9048/api/open/aliOssFile/upload', '/api/open/aliOssFile/upload', '127.0.0.1', '127.0.0.1', 'POST', '127.0.0.1', '63098', 'io.github.wslxm.springbootplus2.starter.aliyun.oss.controller', 'io.github.wslxm.springbootplus2.starter.aliyun.oss.controller.AliOssController', 'AliYun --> OSS文件管理', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 'the request data cannot be parsed', '{\"code\":200,\"data\":{\"name\":\"1.jpeg\",\"url\":\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/10965615-1.jpeg\"},\"msg\":\"成功\"}', 1, 679, 674);
INSERT INTO `t_xj_admin_log` VALUES ('1560820745695784961', NULL, NULL, '2022-08-20 10:47:17', '2022-08-20 10:47:17', 0, 0, '平台主账号', '1', 0, 'http://localhost:9000/views/admin/user/user', 'http://127.0.0.1:9048/api/admin/user', '/api/admin/user', '127.0.0.1', '127.0.0.1', 'POST', '127.0.0.1', '63110', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--admin--用户管理', '添加', '[{\"address\":\"1\",\"age\":0,\"depIds\":\"1548901388543594498,1548901827913715713,1548901950412558337\",\"disable\":0,\"fullName\":\"1\",\"gender\":0,\"headPic\":\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/10965615-1.jpeg\",\"password\":\"1\",\"phone\":\"1\",\"position\":0,\"roleIds\":[\"1443467633444806658\"],\"terminal\":1,\"username\":\"1\"}]', '{\"code\":200,\"data\":\"720799649379782656\",\"msg\":\"添加成功\"}', 1, 897, 888);
INSERT INTO `t_xj_admin_log` VALUES ('1560820828604592130', NULL, NULL, '2022-08-20 10:47:37', '2022-08-20 10:47:37', 0, 0, '╥﹏╥', '0', -1, 'http://localhost:9000/views/admin/user/user', 'http://127.0.0.1:9048/api/open/aliOssFile/upload', '/api/open/aliOssFile/upload', '127.0.0.1', '127.0.0.1', 'POST', '127.0.0.1', '63157', 'io.github.wslxm.springbootplus2.starter.aliyun.oss.controller', 'io.github.wslxm.springbootplus2.starter.aliyun.oss.controller.AliOssController', 'AliYun --> OSS文件管理', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 'the request data cannot be parsed', '{\"code\":200,\"data\":{\"name\":\"1148e452faa8df9e714a6056b64b5733.jpeg\",\"url\":\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/38442110-1148e452faa8df9e714a6056b64b5733.jpeg\"},\"msg\":\"成功\"}', 1, 407, 403);
INSERT INTO `t_xj_admin_log` VALUES ('1560820842080890881', NULL, NULL, '2022-08-20 10:47:40', '2022-08-20 10:47:40', 0, 0, '平台主账号', '1', 0, 'http://localhost:9000/views/admin/user/user', 'http://127.0.0.1:9048/api/admin/user/720799649379782656', '/api/admin/user/720799649379782656', '127.0.0.1', '127.0.0.1', 'PUT', '127.0.0.1', '63164', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--admin--用户管理', 'ID编辑', '[\"720799649379782656\",{\"address\":\"1\",\"age\":0,\"depIds\":\"1548901388543594498,1548901827913715713,1548901950412558337\",\"disable\":0,\"fullName\":\"1\",\"gender\":0,\"headPic\":\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/38442110-1148e452faa8df9e714a6056b64b5733.jpeg\",\"phone\":\"1\",\"position\":0,\"roleIds\":[\"1443467633444806658\"],\"terminal\":1,\"username\":\"1\"}]', '{\"code\":200,\"data\":true,\"msg\":\"编辑成功\"}', 1, 708, 702);
INSERT INTO `t_xj_admin_log` VALUES ('1560826770318553089', NULL, NULL, '2022-08-20 11:11:13', '2022-08-20 11:11:13', 0, 0, '平台主账号', '1', 0, 'http://localhost:9000/views/admin/role/role', 'http://127.0.0.1:9048/api/admin/role', '/api/admin/role', '127.0.0.1', '127.0.0.1', 'POST', '127.0.0.1', '65097', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminRoleController', 'base--admin--角色管理', '添加', '[{\"code\":\"test\",\"desc\":\"-\",\"disable\":0,\"menuIds\":[\"1440255471893200897\",\"1461987433667141634\",\"1452091447254749186\",\"1452091513113710594\",\"1456054437146644481\",\"1440255602914869250\",\"1516699922710138882\",\"1516699798743289857\",\"1516699625820524545\",\"1440271162033684482\",\"1459712656557576194\",\"1442156484086480897\",\"1449764190750285826\",\"1459850402525622274\",\"1462256665587916801\",\"1451979503835369474\",\"1468415037767946242\",\"1462261436877152258\",\"1440256392576483330\",\"1440256481906769922\",\"1481642095692222465\",\"1450796419538522114\"],\"name\":\"测试角色\",\"terminal\":1}]', '{\"code\":200,\"data\":\"1560826767760027650\",\"msg\":\"添加成功\"}', 1, 712, 707);
INSERT INTO `t_xj_admin_log` VALUES ('1560826815222771714', NULL, NULL, '2022-08-20 11:11:24', '2022-08-20 11:11:24', 0, 0, '平台主账号', '1', 0, 'http://localhost:9000/views/admin/role/role', 'http://127.0.0.1:9048/api/admin/role/1560826767760027650', '/api/admin/role/1560826767760027650', '127.0.0.1', '127.0.0.1', 'PUT', '127.0.0.1', '65114', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminRoleController', 'base--admin--角色管理', 'ID编辑', '[\"1560826767760027650\",{\"code\":\"test\",\"desc\":\"-测试\",\"disable\":0,\"menuIds\":[\"1440255471893200897\",\"1461987433667141634\",\"1452091447254749186\",\"1452091513113710594\",\"1456054437146644481\",\"1440255602914869250\",\"1516699922710138882\",\"1516699798743289857\",\"1516699625820524545\",\"1440271162033684482\",\"1459712656557576194\",\"1442156484086480897\",\"1449764190750285826\",\"1459850402525622274\",\"1462256665587916801\",\"1451979503835369474\",\"1468415037767946242\",\"1462261436877152258\",\"1440256392576483330\",\"1440256481906769922\",\"1481642095692222465\",\"1450796419538522114\"],\"name\":\"测试角色\",\"terminal\":1}]', '{\"code\":200,\"data\":true,\"msg\":\"编辑成功\"}', 1, 733, 728);
INSERT INTO `t_xj_admin_log` VALUES ('1560858040293519362', NULL, NULL, '2022-08-20 13:15:29', '2022-08-20 13:15:29', 0, 0, '╥﹏╥', '0', -1, 'http://localhost:9000/login', 'http://127.0.0.1:9048/api/admin/user/login', '/api/admin/user/login', '127.0.0.1', '127.0.0.1', 'POST', '127.0.0.1', '54204', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--admin--用户管理', '用户登录', '[\"admin\",\"NTI3dzEwbjhj\",1]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 286, 286);

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
