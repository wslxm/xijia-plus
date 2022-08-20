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

 Date: 04/06/2022 15:31:07
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
INSERT INTO `t_admin_authority` VALUES ('590081231815839745', NULL, NULL, '2021-09-12 17:39:00', '2021-09-12 17:39:00', 0, 758, '0', '', '/api/admin/generate', 'base-gc--代码生成', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590082183939624962', NULL, NULL, '2021-09-12 17:42:47', '2021-10-04 22:22:16', 0, 757, '590081231815839745', 'GET', '/api/admin/generate/getPath', '代码生成路径', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624963', NULL, NULL, '2021-09-12 17:42:47', '2021-10-04 22:22:16', 0, 757, '590081231815839745', 'POST', '/api/admin/generate/generateCode', '生成代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624964', NULL, NULL, '2021-09-12 17:42:47', '2021-10-04 22:22:15', 0, 757, '590081231815839745', 'POST', '/api/admin/generate/preview', '生成预览代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518954377221', NULL, NULL, '2021-09-13 14:27:52', '2021-09-13 14:27:52', 0, 724, '0', '', '/api/admin/xj/config', 'base-plus--全局配置', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518958571520', NULL, NULL, '2021-09-13 14:27:52', '2021-10-04 22:22:12', 0, 724, '590395518954377221', 'GET', '/api/admin/xj/config/list', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571521', NULL, NULL, '2021-09-13 14:27:52', '2021-10-04 22:22:14', 0, 724, '590395518954377221', 'POST', '/api/admin/xj/config', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571522', NULL, NULL, '2021-09-13 14:27:52', '2021-10-04 22:22:13', 0, 724, '590395518954377221', 'DELETE', '/api/admin/xj/config/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571524', NULL, NULL, '2021-09-13 14:27:52', '2021-10-04 22:22:12', 0, 724, '590395518954377221', 'PUT', '/api/admin/xj/config/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571526', NULL, NULL, '2021-09-13 14:27:52', '2021-09-13 14:27:52', 0, 724, '0', '', '/api/admin/xj/log', 'base-plus--操作记录', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765825', NULL, NULL, '2021-09-13 14:27:52', '2021-10-04 22:21:57', 0, 724, '590395518958571526', 'GET', '/api/admin/xj/log/list', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518962765828', NULL, NULL, '2021-09-13 14:27:52', '2021-09-13 14:27:52', 0, 724, '0', '', '/api/admin/xj/msg', 'base-plus--消息通知', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765829', NULL, NULL, '2021-09-13 14:27:52', '2021-10-04 22:22:09', 0, 724, '590395518962765828', 'POST', '/api/admin/xj/msg', '添加/发送消息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518962765830', NULL, NULL, '2021-09-13 14:27:52', '2021-10-04 22:22:10', 0, 724, '590395518962765828', 'GET', '/api/admin/xj/msg/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590398100552683520', NULL, NULL, '2021-09-13 14:38:08', '2021-10-04 22:22:09', 0, 723, '590395518962765828', 'PUT', '/api/admin/xj/msg/{id}/read', '消息修改为已读', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590417006180831232', NULL, NULL, '2021-09-13 15:53:15', '2021-10-04 22:22:10', 0, 714, '590395518962765828', 'GET', '/api/admin/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862656', NULL, NULL, '2021-09-13 17:51:37', '2021-09-13 17:51:37', 0, 705, '0', '', '/api/admin/authority', 'base--URL权限管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590446794257862657', NULL, NULL, '2021-09-13 17:51:37', '2022-02-27 15:14:45', 0, 705, '590446794257862656', 'GET', '/api/admin/authority/list', '查询所有-接口管理', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862658', NULL, NULL, '2021-09-13 17:51:37', '2022-06-04 22:36:14', 0, 705, '590446794257862656', 'PUT', '/api/admin/authority/{id}', 'ID编辑', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862660', NULL, NULL, '2021-09-13 17:51:37', '2022-05-08 02:31:42', 0, 705, '590446794257862656', 'PUT', '/api/admin/authority/refreshAuthority', '扫描权限', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717120', NULL, NULL, '2021-09-13 18:22:46', '2021-09-13 18:22:46', 0, 702, '0', '', '/api/admin/dictionary', 'base--字典管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590454633059717121', NULL, NULL, '2021-09-13 18:22:46', '2021-10-04 22:21:40', 0, 702, '590454633059717120', 'GET', '/api/admin/dictionary/list', '列表查询 (默认返回Tree数据,可指定Tree或List)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717122', NULL, NULL, '2021-09-13 18:22:46', '2021-10-04 22:21:43', 0, 702, '590454633059717120', 'POST', '/api/admin/dictionary', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911424', NULL, NULL, '2021-09-13 18:22:46', '2021-10-04 22:21:41', 0, 702, '590454633059717120', 'DELETE', '/api/admin/dictionary/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911425', NULL, NULL, '2021-09-13 18:22:46', '2021-10-04 22:23:06', 0, 702, '590454633059717120', 'GET', '/api/admin/dictionary/findCodeGroup', '查询所有-code分组', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911426', NULL, NULL, '2021-09-13 18:22:46', '2021-10-04 22:21:39', 0, 702, '590454633059717120', 'PUT', '/api/admin/dictionary/{id}', '编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911427', NULL, NULL, '2021-09-13 18:22:46', '2021-10-04 22:21:44', 0, 702, '590454633059717120', 'GET', '/api/admin/dictionary/generateEnum', '生成枚举', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911429', NULL, NULL, '2021-09-13 18:22:46', '2021-10-04 22:21:40', 0, 702, '590454633059717120', 'GET', '/api/admin/dictionary/list/category', '获取类别(级联数据)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452416', NULL, NULL, '2021-09-13 19:57:14', '2021-09-13 19:57:14', 0, 697, '0', '', '/api/admin/menu', 'base--菜单管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452417', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:58', 0, 697, '590478406475452416', 'GET', '/api/admin/menu/list', '列表查询(不支持分页)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452418', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:58', 0, 697, '590478406475452416', 'POST', '/api/admin/menu', '菜单添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452419', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:59', 0, 697, '590478406475452416', 'PUT', '/api/admin/menu/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452421', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:59', 0, 697, '590478406475452416', 'GET', '/api/admin/menu/findTree', '左导航菜单', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452423', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:57', 0, 697, '590478406475452416', 'DELETE', '/api/admin/menu/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452424', NULL, NULL, '2021-09-13 19:57:14', '2021-09-13 19:57:14', 0, 697, '0', '', '/api/admin/role', 'base--角色管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452425', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:47', 0, 697, '590478406475452424', 'POST', '/api/admin/role', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646720', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:46', 0, 697, '590478406475452424', 'GET', '/api/admin/role/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646721', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:45', 0, 697, '590478406475452424', 'PUT', '/api/admin/role/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646722', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:46', 0, 697, '590478406475452424', 'PUT', '/api/admin/role/updRoleAuth', '角色的URL权限分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646723', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:48', 0, 697, '590478406475452424', 'PUT', '/api/admin/role/updRoleAuthAll', '所有角色拥有所有权限', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646724', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:50', 0, 697, '590478406475452424', 'PUT', '/api/admin/role/updRoleMenu', '角色的菜单分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646725', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:48', 0, 697, '590478406475452424', 'PUT', '/api/admin/role/updUserRole', '用户的角色分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646727', NULL, NULL, '2021-09-13 19:57:14', '2021-10-04 22:21:47', 0, 697, '590478406475452424', 'DELETE', '/api/admin/role/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('592136133727621120', NULL, NULL, '2021-09-18 09:44:28', '2021-10-04 22:22:08', 0, 639, '590395518962765828', 'GET', '/api/admin/xj/msg/findAllNum', '查询全部/已读/未读数量(当前登录用户)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761932664832', NULL, NULL, '2021-09-25 15:17:51', '2021-09-25 15:17:51', 0, 598, '0', '', '/api/client/dictionary', 'yh--base--字典管理', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664833', NULL, NULL, '2021-09-25 15:17:51', '2021-09-25 15:17:51', 0, 598, '594756761932664832', 'GET', '/api/client/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664834', NULL, NULL, '2021-09-25 15:17:51', '2022-01-06 18:43:01', 0, 598, '594756761932664832', 'GET', '/api/client/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664835', NULL, NULL, '2021-09-25 15:17:51', '2021-09-25 15:17:51', 0, 598, '0', '', '/api/client/xj/banner', 'yh--base-plus--banner', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442057', NULL, NULL, '2021-09-25 15:17:51', '2021-09-25 15:17:51', 0, 598, '0', '', '/api/admin/datasource', 'base-gc--代码生成--数据源维护', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442058', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:21:55', 0, 598, '594756761949442057', 'GET', '/api/admin/datasource/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442059', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:21:54', 0, 598, '594756761949442057', 'POST', '/api/admin/datasource', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442060', NULL, NULL, '2021-09-25 15:17:51', '2021-12-27 00:05:54', 0, 598, '594756761949442057', 'PUT', '/api/admin/datasource/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636352', NULL, NULL, '2021-09-25 15:17:51', '2021-12-09 19:21:00', 0, 598, '594756761949442057', 'DELETE', '/api/admin/datasource/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636353', NULL, NULL, '2021-09-25 15:17:51', '2022-01-18 06:28:10', 0, 598, '594756761949442057', 'POST', '/api/admin/datasource/dataSourceTest', '数据源连接测试', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636354', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:21:55', 0, 598, '594756761949442057', 'PUT', '/api/admin/datasource/{id}/updPwd', '修改/重置密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636358', NULL, NULL, '2021-09-25 15:17:51', '2021-09-25 15:17:51', 0, 598, '0', '', '/api/admin/xj/banner', 'base-plus--banner', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761953636359', NULL, NULL, '2021-09-25 15:17:51', '2022-03-10 08:31:01', 0, 598, '594756761953636358', 'GET', '/api/admin/xj/banner/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636360', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:22:02', 0, 598, '594756761953636358', 'POST', '/api/admin/xj/banner', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636361', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:22:02', 0, 598, '594756761953636358', 'PUT', '/api/admin/xj/banner/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636362', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:22:01', 0, 598, '594756761953636358', 'DELETE', '/api/admin/xj/banner/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830656', NULL, NULL, '2021-09-25 15:17:51', '2021-09-25 15:17:51', 0, 598, '0', '', '/api/admin/xj/blacklist', 'base-plus--黑名单', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761957830657', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:21:51', 0, 598, '594756761957830656', 'GET', '/api/admin/xj/blacklist/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830658', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:21:51', 0, 598, '594756761957830656', 'POST', '/api/admin/xj/blacklist', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830659', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:21:51', 0, 598, '594756761957830656', 'PUT', '/api/admin/xj/blacklist/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830660', NULL, NULL, '2021-09-25 15:17:51', '2021-10-04 22:21:50', 0, 598, '594756761957830656', 'DELETE', '/api/admin/xj/blacklist/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558656827392', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '0', '', '/api/client/xj/config', 'yh--base-plus--全局配置', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827393', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '595094558656827392', 'GET', '/api/client/xj/config/findByCode', 'CODE查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827394', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '0', '', '/api/client/xj/msg', 'yh--base-plus--消息通知', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827395', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '595094558656827394', 'GET', '/api/client/xj/msg/list', '分页查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827396', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '595094558656827394', 'PUT', '/api/client/xj/msg/{id}/read', '消息修改为已读', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827397', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '595094558656827394', 'GET', '/api/client/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410304', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '0', '', '/api/admin/user', 'base--用户管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410305', NULL, NULL, '2021-09-26 13:40:08', '2022-01-06 23:24:24', 0, 563, '595094558669410304', 'GET', '/api/admin/user/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410306', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:25', 0, 563, '595094558669410304', 'POST', '/api/admin/user', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410307', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:23', 0, 563, '595094558669410304', 'DELETE', '/api/admin/user/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410308', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:25', 0, 563, '595094558669410304', 'GET', '/api/admin/user/{id}', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410309', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:26', 0, 563, '595094558669410304', 'PUT', '/api/admin/user/updUser', '修改当前登录人的信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410310', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:25', 0, 563, '595094558669410304', 'GET', '/api/admin/user/findByRoleId', '获取指定角色的用户列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410311', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:22', 0, 563, '595094558669410304', 'PUT', '/api/admin/user/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410312', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:19', 0, 563, '595094558669410304', 'PUT', '/api/admin/user/updByPassword', '修改当前登录人的密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410313', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:24', 0, 563, '595094558669410304', 'GET', '/api/admin/user/list/keyData', '查询所有-只返回关键数据(姓名/昵称/电话/id)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410314', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:26', 0, 563, '595094558669410304', 'PUT', '/api/admin/user/{id}/resetPassword', '重置任意用户密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604608', NULL, NULL, '2021-09-26 13:40:08', '2022-01-29 14:43:43', 0, 563, '595094558669410304', 'GET', '/api/admin/user/findUser', '查询当前登录人的个人信息', 0, 0, 3, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604609', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:23:30', 0, 563, '595094558669410304', 'POST', '/api/admin/user/login', '用户登录', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604610', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '0', '', '/api/admin/dataBase', 'base-gc--代码生成--查询表数据', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558673604611', NULL, NULL, '2021-09-26 13:40:08', '2021-12-09 19:20:55', 0, 563, '595094558673604610', 'GET', '/api/admin/dataBase/table/list', '查询所有表名', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558677798912', NULL, NULL, '2021-09-26 13:40:08', '2021-12-09 19:20:48', 0, 563, '595094558673604610', 'GET', '/api/admin/dataBase/table/field', '查询指定表下所有字段内容', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558686187520', NULL, NULL, '2021-09-26 13:40:08', '2021-09-26 13:40:08', 0, 563, '0', '', '/api/admin/xj/jvm', 'base-plus--jvm信息获取', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558686187521', NULL, NULL, '2021-09-26 13:40:08', '2021-10-04 22:22:19', 0, 563, '595094558686187520', 'GET', '/api/admin/xj/jvm/jvmInfo', '3、系统的jvm信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('598128304653996032', NULL, NULL, '2021-10-04 22:35:13', '2021-10-04 22:36:06', 0, 552, '590395518954377221', 'GET', '/api/admin/xj/config/findByCode', 'CODE查询', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537088', NULL, NULL, '2021-10-27 12:00:10', '2021-10-27 12:00:10', 0, 462, '0', '', '/api/admin/dep', 'base--组织机构', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('606303420856537089', NULL, NULL, '2021-10-27 12:00:10', '2022-01-06 06:06:15', 0, 462, '606303420856537088', 'GET', '/api/admin/dep/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537090', NULL, NULL, '2021-10-27 12:00:10', '2022-01-06 06:06:15', 0, 462, '606303420856537088', 'POST', '/api/admin/dep', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537091', NULL, NULL, '2021-10-27 12:00:10', '2022-01-06 06:06:14', 0, 462, '606303420856537088', 'DELETE', '/api/admin/dep/{id}', 'ID删除(并删除子数据)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537092', NULL, NULL, '2021-10-27 12:00:10', '2022-01-06 06:06:16', 0, 462, '606303420856537088', 'PUT', '/api/admin/dep/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('613640045747900416', NULL, NULL, '2021-11-16 17:53:18', '2022-01-15 22:15:22', 0, 385, '590081231815839745', 'POST', '/api/admin/generate/generateCodeVue', '生成Vue代码(将直接下载)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('620065229904154624', NULL, NULL, '2021-12-04 11:24:40', '2022-05-08 02:41:31', 0, 327, '594756761932664835', 'GET', '/api/client/xj/banner/list/{position}', '列表-位置查询', 0, 1, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('641607621379493888', NULL, NULL, '2022-01-18 06:06:26', '2022-01-18 06:06:26', 0, 196, '0', '', '/api/admin/test/gcTest', '代码生成测试表', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688192', NULL, NULL, '2022-01-18 06:06:26', '2022-01-18 06:06:26', 0, 196, '641607621379493888', 'GET', '/api/admin/test/gcTest/list', '列表查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688193', NULL, NULL, '2022-01-18 06:06:26', '2022-01-18 06:06:26', 0, 196, '641607621379493888', 'POST', '/api/admin/test/gcTest', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688194', NULL, NULL, '2022-01-18 06:06:26', '2022-01-18 06:06:26', 0, 196, '641607621379493888', 'GET', '/api/admin/test/gcTest/{id}', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688195', NULL, NULL, '2022-01-18 06:06:26', '2022-01-18 06:06:26', 0, 196, '641607621379493888', 'DELETE', '/api/admin/test/gcTest/{id}', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('641607621383688196', NULL, NULL, '2022-01-18 06:06:26', '2022-01-18 06:06:26', 0, 196, '641607621379493888', 'PUT', '/api/admin/test/gcTest/{id}', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('691539110942347264', NULL, NULL, '2022-06-01 08:56:21', '2022-06-01 08:56:21', 0, 63, '590395518954377221', 'GET', '/api/admin/xj/config/{id}', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('692227930469830656', NULL, NULL, '2022-06-03 06:33:28', '2022-06-03 06:33:28', 0, 55, '0', '', '/api/open/redis', 'Redis  -->  Redis 测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692227930469830657', NULL, NULL, '2022-06-03 06:33:28', '2022-06-03 06:33:28', 0, 55, '692227930469830656', 'GET', '/api/open/redis/redisson/{key}', 'redis 分布式锁加锁测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692512424506560512', NULL, NULL, '2022-06-04 01:24:00', '2022-06-04 01:24:00', 0, 40, '692227930469830656', 'GET', '/api/open/redis/getNo', '获取分布式唯一编号', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692512424506560513', NULL, NULL, '2022-06-04 01:24:00', '2022-06-04 01:24:00', 0, 40, '692227930469830656', 'GET', '/api/open/redis/redissonTest2', 'redis 分布式注解锁测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841490284544', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '0', '', '/api/open/xj/sign', 'body参数验签测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841490284545', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284544', 'POST', '/api/open/xj/sign/test1', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841490284546', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284544', 'POST', '/api/open/xj/sign/test8', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841515450368', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284544', 'POST', '/api/open/xj/sign/test3/{a}', '参数加密', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841515450369', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284544', 'POST', '/api/open/xj/sign/test2', '参数加密', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644672', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '0', '', '/api/open/xj/valid', '参数验证测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644673', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644672', 'POST', '/api/open/xj/valid/test', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644674', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '0', '', '/api/open/xj/cache', '缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644675', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644674', 'POST', '/api/open/xj/cache/getCache', '获取缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644676', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644674', 'DELETE', '/api/open/xj/cache/delCache', '删除缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644677', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644674', 'PUT', '/api/open/xj/cache/updCache', '更新缓存测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644678', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '0', '', '/api/open/xj/excel', 'excel测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644679', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644678', 'GET', '/api/open/xj/excel/exportExcelDownload', 'excel 导出', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841519644680', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644678', 'POST', '/api/open/xj/excel/upload', '解析excel数据', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841762914304', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '0', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841767108608', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841762914304', 'GET', '/api/open/websocket/getPath', '获取模拟游客登录的 websocket 连接地址', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841767108609', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841762914304', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841767108610', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841762914304', 'GET', '/api/open/websocket/getOnlineCount', '获取在线人数', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841767108611', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841762914304', 'GET', '/api/open/websocket/getOnlineUsersList', '获取当前在线用户列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841767108612', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '0', '', '/api/open/aliOssFile', 'AliYun --> OSS文件管理', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841788080128', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', 'GET', '/api/open/aliOssFile/fileList', 'OSS-文件Object列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841788080129', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', 'DELETE', '/api/open/aliOssFile/del', 'OSS-文件删除', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841788080130', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', 'POST', '/api/open/aliOssFile/upload', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841792274432', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', 'GET', '/api/open/aliOssFile/downloadZip', 'OSS-文件下载--多文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('692965841792274433', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', 'GET', '/api/open/aliOssFile/download', 'OSS-文件下载--单文件下载', 0, 2, 0, 0);

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
INSERT INTO `t_admin_dictionary` VALUES ('1290686507555844098', NULL, '-1', '2020-08-05 00:30:17', '2022-05-15 10:38:16', 0, 0, 'ADMIN', '系统枚举(动态值)', '1290684671448936449', '-', 3, 0, NULL, NULL, NULL);
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
INSERT INTO `t_admin_dictionary` VALUES ('1308585615968772098', NULL, '-1', '2020-09-23 09:54:56', '2021-11-10 15:33:31', 0, 0, '1', '系统通知', '1308585499920769025', '-', 0, 0, NULL, NULL, NULL);
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
INSERT INTO `t_admin_dictionary` VALUES ('1368739394596401154', NULL, '-1', '2021-03-08 09:44:35', '2022-01-13 22:20:48', 0, 0, '0', '系统管理员(老板)', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1384697257961463810', NULL, '-1', '2021-04-21 10:35:27', '2022-01-13 22:19:46', 0, 0, '1', '部门经理', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1399968409441050625', NULL, '-1', '2021-06-02 13:57:32', '2022-05-15 10:39:09', 0, 0, 'DEFAULT', '默认字典(代码生成默认字典)', '1290688121255587841', '【固定值】用于代码生成默认使用的code值', 400, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1399968449656037377', NULL, '-1', '2021-06-02 13:57:42', '2021-11-10 15:33:34', 0, 0, '1', '默认值 1', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1399968504043577346', NULL, '-1', '2021-06-02 13:57:55', '2021-11-10 15:33:34', 0, 0, '2', '默认值 2', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1399968544350838786', NULL, '-1', '2021-06-02 13:58:04', '2022-06-04 10:29:20', 0, 0, '3', '默认值 3', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1404005220177985537', NULL, '-1', '2021-06-13 17:18:21', '2021-11-10 15:33:34', 0, 0, '0', '测试消息', '1308585499920769025', '-', 0, 0, NULL, NULL, NULL);
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
INSERT INTO `t_admin_dictionary` VALUES ('1455153814570086402', NULL, '-1', '2021-11-01 20:44:39', '2022-05-15 10:43:12', 0, 0, '1', '文本-(input)', '1455153732051349505', '-', 1, 0, NULL, NULL, NULL);
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
INSERT INTO `t_admin_dictionary` VALUES ('1459853401289404418', NULL, '-1', '2021-11-14 19:59:07', '2022-05-15 10:43:16', 0, 0, '1', '图片', '1459853262768320513', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1459853475214012418', NULL, '-1', '2021-11-14 19:59:25', '2022-05-15 10:43:17', 0, 0, '2', '开关', '1459853262768320513', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131575404531714', NULL, '-1', '2022-01-01 12:16:26', '2022-05-15 10:43:17', 0, 0, 'DEMO_TEST', 'demo模块测试', '1290684671448936449', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131743994580994', NULL, '-1', '2022-01-01 12:17:06', '2022-05-15 10:43:17', 0, 0, 'SEX', '性别', '1477131575404531714', '-', 100, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131857387589634', NULL, '-1', '2022-01-01 12:17:33', '2022-05-15 10:43:17', 0, 0, '0', '女', '1477131743994580994', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131918590873601', NULL, '-1', '2022-01-01 12:17:47', '2022-06-04 10:28:36', 0, 0, '1', '男', '1477131743994580994', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1477131953227436034', NULL, '-1', '2022-01-01 12:17:56', '2022-06-04 10:28:49', 0, 0, '2', '未知', '1477131743994580994', '-', 2, 0, '1', '2', '3');
INSERT INTO `t_admin_dictionary` VALUES ('1481632150259240961', NULL, '-1', '2022-01-13 22:20:04', '2022-05-15 10:43:18', 0, 0, '2', '员工', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1524590015236018178', NULL, '-1', '2022-05-12 11:19:19', '2022-05-15 10:43:18', 0, 0, '3', '富文本', '1459853262768320513', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_admin_dictionary` VALUES ('1525481792788762625', NULL, '-1', '2022-05-14 22:22:56', '2022-05-18 21:20:41', 0, 0, '18', '富文本(tinymce)', '1455153732051349505', 'vue-tinymce 富文本插件', 18, 0, NULL, NULL, NULL);

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
INSERT INTO `t_admin_menu` VALUES ('1440255471893200897', '1', '-1', '2021-09-21 18:03:57', '2022-01-17 14:33:00', 0, 0, '0', 'a-vue', '', '', 'layui-icon-file-b', 1, 1, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440255602914869250', '1', '-1', '2021-09-21 18:04:29', '2022-01-17 14:33:00', 0, 0, '1440255471893200897', '系统管理', '', '', 'el-icon-setting', 100, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440255716299489282', '1', '-1', '2021-09-21 18:04:56', '2022-04-20 16:49:12', 0, 0, '1440255602914869250', '菜单管理', '', '/views/admin/menu/menu', 'el-icon-document-remove', 20005, 3, 1, 1);
INSERT INTO `t_admin_menu` VALUES ('1440256392576483330', '1', '-1', '2021-09-21 18:07:37', '2022-01-17 14:55:41', 0, 0, '0', 'a-vue-2 (test)', '', '', 'layui-icon-file-b', 2, 1, 0, 1);
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
INSERT INTO `t_admin_menu` VALUES ('1452091513113710594', '1', '-1', '2021-10-24 09:56:10', '2022-05-15 10:44:51', 0, 0, '1452091447254749186', '数据表', NULL, '/views/gc/codeGeneration/codeGeneration', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1456054437146644481', '1', '-1', '2021-11-04 08:23:24', '2022-05-15 10:44:51', 0, 0, '1452091447254749186', '生成的代码测试页', NULL, '/views/test/gcTest/gcTest', 'el-icon-document-remove', 1, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1457369967249879042', '1', '-1', '2021-11-07 23:30:51', '2022-05-15 10:44:51', 0, 0, '0', '商家端', NULL, NULL, 'el-icon-document-remove', 0, 1, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457370029065531394', '1', '-1', '2021-11-07 23:31:06', '2022-05-15 10:44:52', 0, 0, '1457369967249879042', '系统管理', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457370075530031105', '1', '-1', '2021-11-07 23:31:17', '2022-05-15 10:44:52', 0, 0, '1457370029065531394', '角色管理', NULL, '/views/admin/role/role', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457372083897004033', '1', '-1', '2021-11-07 23:39:16', '2022-05-15 10:44:52', 0, 0, '1457370029065531394', '员工管理', NULL, '/views/admin/user/user', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1459712656557576194', '1', '-1', '2021-11-14 10:39:51', '2022-05-15 10:44:52', 0, 0, '1440255602914869250', '组织机构', NULL, '/views/admin/adminDep/adminDep', 'el-icon-document-remove', 10002, 3, 0, 1);
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
INSERT INTO `t_admin_menu` VALUES ('1482979903354703874', '1', '-1', '2022-01-17 15:35:36', '2022-05-15 10:44:55', 0, 0, '1457370029065531394', '菜单管理', NULL, '/views/admin/menu/menu', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1516699625820524545', '1', '-1', '2022-04-20 16:45:44', '2022-05-15 10:44:55', 0, 0, '1440255602914869250', '菜单管理V2', NULL, '/views/admin/menuv2/menu', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1516699798743289857', '1', '-1', '2022-04-20 16:46:25', '2022-05-15 10:44:55', 0, 0, '1440255602914869250', '字典管理v2', NULL, '/views/admin/dictionaryv2/adminDictionary', 'el-icon-document-remove', 0, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1516699922710138882', '1', '-1', '2022-04-20 16:46:55', '2022-05-15 10:44:55', 0, 0, '1440255602914869250', '接口管理v2', NULL, '/views/admin/adminAuthorityv2/adminAuthority', 'el-icon-document-remove', 0, 3, 0, 1);

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
INSERT INTO `t_admin_dep` VALUES ('1443501889504210946', NULL, '-1', '2021-09-30 17:04:03', '2022-01-11 10:16:20', 0, 0, '0', 'zgs', '成都总公司', '-', 0, 0, 1);
INSERT INTO `t_admin_dep` VALUES ('1443502090977603585', NULL, '-1', '2021-09-30 17:04:51', '2021-10-08 16:24:00', 0, 0, '1443501889504210946', 'zgsa', '子公司a', '-', 0, 0, 2);
INSERT INTO `t_admin_dep` VALUES ('1443502157943861250', NULL, '-1', '2021-09-30 17:05:07', '2022-02-23 15:13:43', 0, 0, '1443501889504210946', 'zgsb', '子公司b', '-', 0, 0, 2);
INSERT INTO `t_admin_dep` VALUES ('1443502302433439746', NULL, '-1', '2021-09-30 17:05:42', '2021-10-08 16:24:00', 0, 0, '1443502090977603585', 'csb', '测试部', '-', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1443502428644241409', NULL, '-1', '2021-09-30 17:06:12', '2021-11-29 19:50:02', 0, 0, '1443502157943861250', 'yyb', '运营部', '-', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1468426496627490818', NULL, '-1', '2021-12-08 11:45:33', '2022-05-15 10:45:21', 0, 0, '1443501889504210946', 'zb', '公司总部', '-', 0, 0, 2);
INSERT INTO `t_admin_dep` VALUES ('1481913168983756802', NULL, '-1', '2022-01-14 16:56:46', '2022-05-15 10:45:21', 0, 0, '1481913127925714945', 'xx-dep', 'xx部门', '-', 0, 0, 3);
INSERT INTO `t_admin_dep` VALUES ('1481913213086863362', NULL, '-1', '2022-01-14 16:56:57', '2022-05-15 10:45:21', 0, 0, '1481913127925714945', 'xx-dep2', 'xx部门2', '-', 0, 0, 3);

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
INSERT INTO `t_admin_role` VALUES ('1443467633444806658', '1', '-1', '2021-09-30 14:47:56', '2022-01-17 14:35:01', 0, 0, 'avue-超管', 'SYS', 'avue超管', 0, 1);
INSERT INTO `t_admin_role` VALUES ('1447115588580159489', '1446872739607478273', '-1', '2021-10-10 16:23:36', '2022-05-15 10:45:34', 0, 0, 'avue 体验账号', 'test', '-', 0, 1);
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
INSERT INTO `t_admin_role_auth` VALUES ('1531535443407933441', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590081231815839745', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443407933442', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624962', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443407933443', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624963', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443407933444', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624964', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443407933445', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518954377221', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443407933446', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487874', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571521', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487875', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571522', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487876', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571524', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487877', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571526', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487878', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765825', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487879', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765828', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487880', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765829', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487881', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765830', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487882', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590398100552683520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487883', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590417006180831232', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487884', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487885', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487886', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862658', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487887', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862660', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487888', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717120', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487889', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717121', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487890', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717122', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487891', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911424', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487892', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911425', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487893', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911426', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487894', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911427', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443441487895', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911429', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042306', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452416', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042307', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452417', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042308', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452418', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042309', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452419', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042310', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452421', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042311', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452423', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042312', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452424', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042313', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452425', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042314', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646720', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042315', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646721', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042316', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646722', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042317', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646723', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042318', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646724', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042319', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646725', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042320', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646727', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042321', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '592136133727621120', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042322', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442057', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042323', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442058', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042324', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442059', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443475042325', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442060', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179649', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636352', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179650', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636353', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179651', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636354', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179652', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636358', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179653', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636359', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179654', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636360', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179655', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636361', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179656', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636362', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179657', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179658', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179659', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830658', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179660', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830659', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179661', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830660', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179662', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410304', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179663', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410305', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179664', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410306', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179665', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410307', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179666', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410308', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179667', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410309', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443521179668', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410310', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122690', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410311', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122691', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410312', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122692', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410313', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122693', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410314', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122694', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604608', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122695', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604609', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122696', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604610', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122697', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604611', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122698', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558677798912', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122699', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558686187520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122700', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558686187521', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122701', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '598128304653996032', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122702', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537088', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122703', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537089', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122704', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537090', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122705', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537091', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122706', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537092', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122707', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '613640045747900416', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443563122708', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621379493888', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065730', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688192', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065731', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688193', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065732', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688194', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065733', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688195', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065734', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688196', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065735', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590081231815839745', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065736', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624962', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065737', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624963', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065738', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624964', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065739', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518954377221', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065740', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571520', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065741', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571521', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065742', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571522', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065743', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571524', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065744', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571526', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065745', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765825', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065746', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765828', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065747', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765829', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065748', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765830', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065749', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590398100552683520', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443605065750', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590417006180831232', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008769', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862656', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008770', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008771', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862658', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008772', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862660', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008773', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717120', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008774', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717121', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008775', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717122', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008776', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911424', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008777', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911425', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008778', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911426', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008779', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911427', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008780', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911429', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008781', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452416', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008782', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452417', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008783', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452418', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008784', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452419', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008785', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452421', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008786', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452423', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008787', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452424', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008788', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452425', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008789', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646720', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443647008790', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646721', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757506', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646722', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757507', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646723', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757508', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646724', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757509', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646725', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757510', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646727', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757511', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '592136133727621120', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757512', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442057', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757513', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442058', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757514', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442059', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757515', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442060', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757516', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636352', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757517', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636353', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757518', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636354', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757519', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636358', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757520', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636359', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757521', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636360', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757522', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636361', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757523', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636362', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757524', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830656', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757525', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443684757526', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830658', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506242', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830659', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506243', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830660', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506244', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410304', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506245', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410305', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506246', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410306', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506247', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410307', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506248', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410308', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506249', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410309', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506250', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410310', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506251', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410311', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506252', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410312', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506253', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410313', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506254', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410314', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506255', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604608', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506256', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604609', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506257', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604610', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506258', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604611', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506259', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558677798912', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506260', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558686187520', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506261', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558686187521', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506262', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '598128304653996032', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443722506263', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537088', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254978', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537089', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254979', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537090', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254980', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537091', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254981', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537092', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254982', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '613640045747900416', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254983', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621379493888', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254984', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688192', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254985', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688193', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254986', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688194', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254987', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688195', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254988', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688196', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254989', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590081231815839745', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254990', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624962', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254991', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624963', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254992', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624964', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254993', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518954377221', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254994', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254995', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571521', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443760254996', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571522', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392322', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571524', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392323', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571526', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392324', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765825', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392325', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765828', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392326', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765829', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392327', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765830', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392328', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590398100552683520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392329', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590417006180831232', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392330', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862656', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392331', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862657', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392332', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862658', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392333', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862660', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392334', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717120', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392335', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717121', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392336', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717122', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392337', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911424', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392338', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911425', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392339', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911426', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392340', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911427', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392341', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911429', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392342', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452416', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443806392343', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452417', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529666', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452418', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529667', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452419', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529668', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452421', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529669', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452423', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529670', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452424', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529671', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452425', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529672', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646720', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529673', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646721', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529674', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646722', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529675', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646723', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529676', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646724', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529677', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646725', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529678', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646727', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529679', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '592136133727621120', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529680', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442057', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529681', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442058', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529682', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442059', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529683', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442060', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529684', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636352', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529685', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636353', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529686', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636354', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443852529687', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636358', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861313', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636359', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861314', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636360', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861315', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636361', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861316', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636362', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861317', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830656', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861318', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830657', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861319', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830658', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861320', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830659', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861321', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830660', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861322', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410304', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861323', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410305', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861324', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410306', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861325', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410307', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861326', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410308', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861327', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410309', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861328', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410310', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861329', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410311', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861330', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410312', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861331', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410313', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861332', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410314', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861333', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604608', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443902861334', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604609', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027137', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604610', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027138', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604611', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027139', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558677798912', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027140', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558686187520', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027141', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558686187521', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027142', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '598128304653996032', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027143', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537088', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027144', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537089', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027145', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537090', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027146', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537091', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027147', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537092', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027148', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '613640045747900416', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027149', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621379493888', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027150', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688192', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027151', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688193', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027152', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688194', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027153', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688195', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027154', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688196', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027155', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590081231815839745', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027156', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624962', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027157', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624963', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027158', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590082183939624964', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027159', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518954377221', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443928027160', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775874', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571521', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775875', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571522', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775876', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571524', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775877', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518958571526', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775878', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765825', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775879', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765828', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775880', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765829', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775881', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590395518962765830', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775882', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590398100552683520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775883', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590417006180831232', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775884', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862656', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775885', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862657', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775886', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862658', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775887', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590446794257862660', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775888', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717120', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775889', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717121', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775890', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633059717122', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775891', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911424', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775892', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911425', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775893', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911426', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775894', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911427', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775895', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590454633063911429', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775896', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452416', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535443965775897', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452417', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107521', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452418', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107522', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452419', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107523', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452421', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107524', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452423', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107525', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452424', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107526', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406475452425', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107527', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646720', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107528', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646721', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107529', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646722', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107530', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646723', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107531', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646724', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107532', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646725', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107533', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '590478406479646727', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107534', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '592136133727621120', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107535', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442057', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107536', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442058', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107537', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442059', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107538', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761949442060', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107539', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636352', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444016107540', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636353', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244865', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636354', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244866', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636358', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244867', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636359', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244868', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636360', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244869', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636361', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244870', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761953636362', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244871', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830656', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244872', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830657', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244873', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830658', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244874', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830659', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244875', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '594756761957830660', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244876', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410304', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244877', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410305', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244878', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410306', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244879', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410307', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244880', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410308', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244881', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410309', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244882', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410310', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244883', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410311', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244884', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410312', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244885', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410313', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444062244886', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558669410314', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799298', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604608', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799299', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604609', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799300', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604610', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799301', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558673604611', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799302', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558677798912', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799303', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558686187520', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799304', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '595094558686187521', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799305', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '598128304653996032', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799306', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537088', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799307', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537089', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799308', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537090', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799309', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537091', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799310', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '606303420856537092', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799311', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '613640045747900416', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799312', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621379493888', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799313', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688192', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799314', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688193', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799315', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688194', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799316', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688195', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531535444095799317', NULL, NULL, '2022-05-31 15:17:59', '2022-05-31 15:17:59', 0, 0, '641607621383688196', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1531560206759305217', NULL, NULL, '2022-05-31 16:56:21', '2022-05-31 16:56:21', 0, 0, '691539110942347264', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1531560206759305218', NULL, NULL, '2022-05-31 16:56:21', '2022-05-31 16:56:21', 0, 0, '691539110942347264', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1531560206759305219', NULL, NULL, '2022-05-31 16:56:21', '2022-05-31 16:56:21', 0, 0, '691539110942347264', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1531560206759305220', NULL, NULL, '2022-05-31 16:56:21', '2022-05-31 16:56:21', 0, 0, '691539110942347264', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532249026202923010', NULL, NULL, '2022-06-02 14:33:28', '2022-06-02 14:33:28', 0, 0, '692227930469830657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532249026202923011', NULL, NULL, '2022-06-02 14:33:28', '2022-06-02 14:33:28', 0, 0, '692227930469830656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532249026202923012', NULL, NULL, '2022-06-02 14:33:28', '2022-06-02 14:33:28', 0, 0, '692227930469830657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532249026202923013', NULL, NULL, '2022-06-02 14:33:28', '2022-06-02 14:33:28', 0, 0, '692227930469830656', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532249026202923014', NULL, NULL, '2022-06-02 14:33:28', '2022-06-02 14:33:28', 0, 0, '692227930469830657', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532249026202923015', NULL, NULL, '2022-06-02 14:33:28', '2022-06-02 14:33:28', 0, 0, '692227930469830656', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532249026202923016', NULL, NULL, '2022-06-02 14:33:28', '2022-06-02 14:33:28', 0, 0, '692227930469830657', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532249026202923017', NULL, NULL, '2022-06-02 14:33:28', '2022-06-02 14:33:28', 0, 0, '692227930469830656', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532533523108380674', NULL, NULL, '2022-06-03 09:24:01', '2022-06-03 09:24:01', 0, 0, '692512424506560512', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532533523108380675', NULL, NULL, '2022-06-03 09:24:01', '2022-06-03 09:24:01', 0, 0, '692512424506560513', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532533523108380676', NULL, NULL, '2022-06-03 09:24:01', '2022-06-03 09:24:01', 0, 0, '692512424506560512', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532533523108380677', NULL, NULL, '2022-06-03 09:24:01', '2022-06-03 09:24:01', 0, 0, '692512424506560513', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532533523120963586', NULL, NULL, '2022-06-03 09:24:01', '2022-06-03 09:24:01', 0, 0, '692512424506560512', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532533523120963587', NULL, NULL, '2022-06-03 09:24:01', '2022-06-03 09:24:01', 0, 0, '692512424506560513', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532533523120963588', NULL, NULL, '2022-06-03 09:24:01', '2022-06-03 09:24:01', 0, 0, '692512424506560512', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532533523120963589', NULL, NULL, '2022-06-03 09:24:01', '2022-06-03 09:24:01', 0, 0, '692512424506560513', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079940579330', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813824', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079940579331', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813825', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079940579332', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813826', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079940579333', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008128', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079940579334', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983528230912', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079990910978', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008130', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079990910979', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008129', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079990910980', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008132', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570079990910981', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008133', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076802', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008134', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076803', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008131', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076804', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008136', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076805', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983549202432', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076806', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008135', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076807', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083457', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076808', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083458', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076809', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083459', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076810', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083460', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080016076811', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083456', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242625', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083462', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242626', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083463', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242627', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083464', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242628', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083465', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242629', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083466', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242630', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083461', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242631', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813824', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242632', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813825', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242633', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813826', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080041242634', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008128', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991361', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983528230912', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991362', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008130', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991363', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008129', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991364', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008132', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991365', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008133', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991366', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008134', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991367', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008131', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991368', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008136', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080078991369', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983549202432', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323009', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008135', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323010', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083457', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323011', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083458', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323012', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083459', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323013', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083460', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323014', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083456', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323015', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083462', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323016', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083463', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323017', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083464', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323018', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083465', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080129323019', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083466', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488833', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083461', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488834', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813824', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488835', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813825', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488836', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813826', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488837', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008128', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488838', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983528230912', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488839', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008130', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488840', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008129', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488841', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008132', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488842', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008133', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080154488843', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008134', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431873', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008131', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431874', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008136', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431875', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983549202432', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431876', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008135', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431877', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083457', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431878', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083458', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431879', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083459', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431880', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083460', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431881', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083456', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431882', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083462', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080196431883', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083463', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080229986306', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083464', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080229986307', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083465', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080229986308', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083466', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080229986309', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083461', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080229986310', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813824', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080229986311', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813825', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080246763522', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983540813826', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080246763523', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008128', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080246763524', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983528230912', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080246763525', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008130', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080246763526', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008129', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080246763527', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008132', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080246763528', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008133', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080271929345', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008134', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080271929346', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008131', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080271929347', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008136', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080271929348', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983549202432', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080271929349', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983545008135', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706561', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083457', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706562', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083458', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706563', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083459', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706564', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083460', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706565', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083456', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706566', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083462', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706567', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083463', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706568', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083464', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706569', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083465', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706570', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083466', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532570080288706571', NULL, NULL, '2022-06-03 11:49:15', '2022-06-03 11:49:15', 0, 0, '692548983784083461', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938296897537', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284545', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938296897538', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284546', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938296897539', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841515450368', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938296897540', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841515450369', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938296897541', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284544', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938296897542', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644673', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938364006401', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644672', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938376589313', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644675', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938376589314', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644676', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938376589315', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644677', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938376589316', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644674', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938376589317', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644679', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172226', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644680', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172227', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644678', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172228', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108608', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172229', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108609', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172230', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108610', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172231', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108611', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172232', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841762914304', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172233', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080128', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172234', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080129', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172235', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080130', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938389172236', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841792274432', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938422726658', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841792274433', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938422726659', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938422726660', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284545', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938422726661', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284546', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938422726662', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841515450368', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938422726663', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841515450369', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938422726664', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284544', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938443698178', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644673', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938443698179', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644672', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938443698180', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644675', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938443698181', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644676', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938443698182', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644677', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938443698183', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644674', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864001', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644679', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864002', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644680', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864003', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644678', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864004', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108608', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864005', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108609', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864006', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108610', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864007', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108611', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864008', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841762914304', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864009', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080128', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864010', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080129', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938468864011', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080130', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418433', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841792274432', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418434', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841792274433', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418435', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418436', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284545', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418437', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284546', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418438', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841515450368', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418439', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841515450369', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418440', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284544', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418441', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644673', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938502418442', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644672', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938531778562', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644675', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938531778563', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644676', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938531778564', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644677', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938531778565', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644674', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555778', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644679', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555779', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644680', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555780', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644678', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555781', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108608', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555782', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108609', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555783', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108610', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555784', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108611', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555785', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841762914304', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555786', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080128', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555787', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080129', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555788', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080130', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938548555789', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841792274432', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498818', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841792274433', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498819', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', '1483412338848567297');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498820', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284545', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498821', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284546', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498822', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841515450368', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498823', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841515450369', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498824', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841490284544', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498825', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644673', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498826', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644672', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938590498827', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644675', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938619858945', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644676', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938619858946', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644677', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938619858947', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644674', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938619858948', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644679', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938636636162', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644680', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938636636163', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841519644678', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938636636164', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108608', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938636636165', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108609', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938649219074', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108610', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938649219075', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108611', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938649219076', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841762914304', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938649219077', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080128', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938649219078', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080129', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938649219079', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841788080130', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938674384897', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841792274432', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938674384898', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841792274433', '1483415755868344322');
INSERT INTO `t_admin_role_auth` VALUES ('1532986938674384899', NULL, NULL, '2022-06-04 15:25:42', '2022-06-04 15:25:42', 0, 0, '692965841767108612', '1483415755868344322');

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
INSERT INTO `t_admin_role_user` VALUES ('1481890636197007362', NULL, NULL, '2022-01-14 15:27:14', '2022-01-14 15:27:14', 0, 0, '1460427339745763329', '1447115588580159489');
INSERT INTO `t_admin_role_user` VALUES ('1482965188683501570', NULL, NULL, '2022-01-17 14:37:07', '2022-01-17 14:37:07', 0, 0, '1', '1443467633444806658');
INSERT INTO `t_admin_role_user` VALUES ('1483413837825708033', NULL, NULL, '2022-01-18 20:19:54', '2022-01-18 20:19:54', 0, 0, '643392744110297088', '1483412338848567297');
INSERT INTO `t_admin_role_user` VALUES ('1483416303262765058', NULL, NULL, '2022-01-18 20:29:41', '2022-01-18 20:29:41', 0, 0, '643395209551548416', '1483415755868344322');
INSERT INTO `t_admin_role_user` VALUES ('1525474500416638977', NULL, NULL, '2022-05-14 21:53:57', '2022-05-14 21:53:57', 0, 0, '685453406529261568', '1443467633444806658');

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
  `dep_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司/部门id,多层级前端自行分割数据',
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
INSERT INTO `t_admin_user` VALUES ('1', NULL, '-1', '2020-08-05 07:11:04', '2022-06-04 15:27:32', 0, 10, 'admin', 'd9880822dd584adce3cde4b024776eef', 1, 0, '1443501889504210946,1443502090977603585,1443502249136418817', 0, 1, 'http://xijia.plus/oss/file/image/head/20200822150143006266-5.png', '10000', '平台主账号', '四川成都', 22, '2020-08-05 15:11:05', '2022-06-04 15:27:32', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('1460427339745763329', '1', '-1', '2021-11-16 09:59:45', '2022-06-04 15:23:16', 0, 0, 'test', '992171f4f472ae8360a32663d9529339', 1, 0, '1443501889504210946,1443502090977603585,1443502302433439746', 2, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/44592984-timg(9).jpg', '17600000001', 'test', '0', 0, '2021-11-16 09:59:46', '2022-06-04 15:23:16', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('685453406529261568', '1', '-1', '2022-05-14 21:53:57', '2022-05-15 10:48:56', 0, 0, 'hexin', 'd508eefe214884b363bf33882afb4ed3', 0, 0, '1443501889504210946,1468426496627490818', 0, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/25847053-8.jpg', '17600000000', '何鑫', '1', 20, '2022-05-14 21:53:57', '2022-05-14 21:54:12', NULL, NULL);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_gc_test
-- ----------------------------
INSERT INTO `t_gc_test` VALUES ('1456780575175118849', NULL, NULL, '2021-11-06 08:28:48', '2021-11-09 21:32:51', 1, 0, '兮家小二', 20.00, 3, '3,2', 2, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/32859734-timg(3).jpg', '2021-11-06 08:28:31', '啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦', NULL);
INSERT INTO `t_gc_test` VALUES ('1458065155967528961', NULL, NULL, '2021-11-09 21:33:17', '2022-01-13 22:09:16', 1, 0, '测试', 22.00, 2, '2,3', 3, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/09798637-timg(2).jpg', '2021-11-09 09:33:11', '啦啦啦啦啦啦啦', NULL);
INSERT INTO `t_gc_test` VALUES ('1475676902860955649', NULL, NULL, '2021-12-28 11:56:04', '2022-01-13 22:09:19', 1, 0, '测试数据1', 23.00, 2, '2,3', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/56163330-6226ba315230576c0c8c99a6c1fbc4b7.jpeg', '2021-12-28 11:56:00', '测试数据', NULL);
INSERT INTO `t_gc_test` VALUES ('1481629780875939841', NULL, NULL, '2022-01-13 22:10:40', '2022-01-13 22:11:50', 0, 0, '代码生成测试', 221.00, 1, '2,1', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/01628736-qs44ufe2024qs44ufe2024.jpg', '2022-01-13 10:10:07', 'asjdasnjkasnkdnaskdkasa', NULL);
INSERT INTO `t_gc_test` VALUES ('1482723544927358977', NULL, NULL, '2022-01-16 22:36:53', '2022-01-25 14:55:24', 1, 0, '测试', 22.00, 2, '1,2', 2, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/49117698-timg.jpg', '2022-01-16 18:36:52', '测试', NULL);
INSERT INTO `t_gc_test` VALUES ('1485868996178743298', NULL, NULL, '2022-01-25 14:55:49', '2022-05-14 23:54:54', 0, 0, '111', 0.00, 1, '1', 1, 1, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/24735070-234308-16202293885efd.jpg', '2022-01-26 04:00:00', '111', '<div class=\"c-font-normal c-color-text ellipsis_efK7X\" aria-label=\"职业：演员、导演\">\n<div class=\"text_2NOr6\">职业：演员、导演</div>\n</div>\n<div class=\"c-font-normal c-color-text ellipsis_efK7X\" aria-label=\"生日：1984年6月26日\">\n<div class=\"text_2NOr6\">生日：1984年6月26日</div>\n</div>\n<div class=\"c-font-normal c-color-text ellipsis_efK7X\" aria-label=\"个人信息：172 cm/巨蟹座/O型\">\n<div class=\"text_2NOr6\">个人信息：172 cm/巨蟹座/O型</div>\n</div>\n<div class=\"c-font-normal c-color-text ellipsis_efK7X\" aria-label=\"代表作品：雪豹、西游&middot;降魔篇、裸婚时代、失恋33天、海洋天堂、小爸爸、奋斗、少帅、陆垚知马俐、剃刀边缘\">\n<div class=\"text_2NOr6\">代表作品：雪豹、西游&middot;降魔篇、裸婚时代、失恋33天、海洋天堂、小爸爸、奋斗、少帅、陆垚知马俐、剃刀边缘</div>\n</div>\n<div>\n<div class=\"c-font-normal c-color-text\" aria-label=\"文章，1984年6月26日出生于陕西省西安市雁塔区，中国内地男演员、导演，毕业于中央戏剧学院表演系。2006年，参演电视剧《与青春有关的日子》，开始在影视圈崭露头角。2005年，拍摄古装剧《锦衣卫》。2007年，主演赵宝刚导演的青春剧《奋斗》；同年，主演首部电影《走着瞧》。2008年，主演滕华涛执导的电视剧《蜗居》，饰演80后城市青年小贝。2009年，在电影《海洋天堂》中扮演自闭症患者王大福；同年，参演抗战剧《雪豹》。2011年，主演的电视剧《裸婚时代》播出；同年，连续2年获得北京大学生电影节最受大学生欢迎男演员奖。2012年，凭借电影《失恋33天》获得第31届大众电影百花奖最佳男主角奖；同年，成立北京君竹影视文化有限公司，并导演第一部影视作品《小爸爸》。2013年2月，主演的电影《西游&middot;降魔篇》在全国上映。2014年3月28日，主演的爱情片《我在路上最爱你》上映。2014年，在姜文执导的\">\n<div class=\"text_2NOr6\">简介：<em>文章</em>，1984年6月26日出生于陕西省西安市雁塔区，中...</div>\n<div class=\"text_2NOr6\">&nbsp;</div>\n<div class=\"text_2NOr6\"><img src=\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vueTinymce/50305961-mceclip0.png\" /></div>\n</div>\n</div>');

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
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332337401510551554', NULL, NULL, '2020-11-27 22:56:02', '2021-11-21 11:18:55', 0, 0, 2, '*', '禁止所有 ip 访问,除本地 [127.0.0.1 / localhost] ,不建议配置在所有资源上，一旦配置，所有用户(包括自己) 将无法访问所有资源，因为每个用户的ip地址都不一样， 开启此功能需提前配置所有用户的ip地址为白名单', 1);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1421369811404894210', NULL, NULL, '2021-07-31 15:19:05', '2022-05-04 10:23:34', 0, 0, 2, '192.168.1.10', '本地', 0);
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
INSERT INTO `t_xj_admin_config` VALUES ('1365174805250260994', NULL, '-1', '2021-02-26 13:40:11', '2022-06-04 10:44:43', 0, 0, 'entry_name', '项目名称', 'spring-boot-plus2', 0, 0, '登录页和左菜单顶部标题', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1365182627308433409', NULL, '-1', '2021-02-26 14:11:17', '2022-06-04 15:30:01', 0, 0, 'login_bg_img', '背景图(登录页)', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210311113615990505-1.jpg', 0, 1, '未启用', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1365185332319997953', NULL, '-1', '2021-02-26 14:22:01', '2022-06-04 15:30:05', 0, 0, 'beian', '备案号(登录页)', '备案号：蜀ICP备19022468号-1', 0, 0, '未启用', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1365187122549551105', NULL, '-1', '2021-02-26 14:29:09', '2022-06-04 15:30:08', 0, 0, 'project_desc', '项目描叙(登录页)', '©2020-2021 该后台系统为个人开发运营，作者联系方式 QQ:1720696548', 0, 0, '未启用', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1383627414470467586', NULL, '-1', '2021-04-18 11:44:16', '2022-06-04 10:44:12', 0, 0, 'is_sign', '验签开关', 'true', 0, 2, '验签总开关 |  true  需验签(默认)   false=无需验签, 开启后可在接口管理中对单个接口进行配置', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1383636872395255809', NULL, '-1', '2021-04-18 12:21:51', '2022-06-04 10:46:12', 0, 0, 'is_swagger', 'swagger文档开关', 'true', 0, 2, '动态开关是否可在线查看接口文档，关闭后所有接口将隐藏展示', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1432597381643304961', NULL, '-1', '2021-08-31 14:53:26', '2022-06-04 10:48:36', 0, 0, 'is_auth', '接口是否验权', 'true', 0, 2, '接口权限管理，开启后在接口管理中可单独配置，和登录人当前拥有角色是否分配指定接口权限相关', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1441701074921598977', NULL, '-1', '2021-09-25 17:48:16', '2022-06-04 10:51:28', 0, 0, 'login_expiration_manage', '登录有效期', '60', 0, 0, '登录状态切对当前系统无如何操作后，当前登录状态保持时长,  防止离开后被别人操作， 单位分', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1481920988265320449', NULL, NULL, '2022-01-14 17:27:51', '2022-06-04 15:30:24', 0, 0, '测试数据', 'test', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/45321474-232917-1636990157bc70.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/47041365-234759-16363000792588.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/49545567-193708-15779650288588.jpg', 0, 1, '测试数据', NULL, NULL, NULL);
INSERT INTO `t_xj_admin_config` VALUES ('1524630203798654978', NULL, NULL, '2022-05-12 13:59:01', '2022-06-04 15:29:27', 0, 0, 'fwb-test', '富文本%测试 ', '<p>哈哈哈</p>\n<p><img src=\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vueTinymce/21839730-mceclip2.png\" /></p>', 0, 3, '测试', '', NULL, NULL);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--代码生成动态数据源' ROW_FORMAT = Dynamic;

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
INSERT INTO `t_xj_admin_log` VALUES ('1532986237026045953', NULL, NULL, '2022-06-04 15:22:55', '2022-06-04 15:22:55', 0, 0, '╥﹏╥', '0', -1, 'http://vue.xijia.plus/login', 'http://127.0.0.1:9049/api/admin/user/login', '/api/admin/user/login', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '52192', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', '用户登录', '[\"admin\",\"MTIzNDU2\",1]', '{\"code\":8101,\"msg\":\"密码错误\"}', 1, 24, 13);
INSERT INTO `t_xj_admin_log` VALUES ('1532986285898076162', NULL, NULL, '2022-06-04 15:23:06', '2022-06-04 15:23:06', 0, 0, '╥﹏╥', '0', -1, 'http://vue.xijia.plus/login', 'http://127.0.0.1:9049/api/admin/user/login', '/api/admin/user/login', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '52196', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', '用户登录', '[\"test\",\"MTIzNDU2Nzgr\",1]', '{\"code\":8101,\"msg\":\"密码错误\"}', 1, 10, 8);
INSERT INTO `t_xj_admin_log` VALUES ('1532986312036978689', NULL, NULL, '2022-06-04 15:23:12', '2022-06-04 15:23:12', 0, 0, '╥﹏╥', '0', -1, 'http://vue.xijia.plus/login', 'http://127.0.0.1:9049/api/admin/user/login', '/api/admin/user/login', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '52200', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', '用户登录', '[\"test\",\"MTIzNDU2Nzg5\",1]', '{\"code\":8101,\"msg\":\"密码错误\"}', 1, 12, 10);
INSERT INTO `t_xj_admin_log` VALUES ('1532986326452801537', NULL, NULL, '2022-06-04 15:23:16', '2022-06-04 15:23:16', 0, 0, '╥﹏╥', '0', -1, 'http://vue.xijia.plus/login', 'http://127.0.0.1:9049/api/admin/user/login', '/api/admin/user/login', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '52204', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', '用户登录', '[\"test\",\"MTIzNDU2\",1]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 24, 22);
INSERT INTO `t_xj_admin_log` VALUES ('1532987373959254017', NULL, NULL, '2022-06-04 15:27:26', '2022-06-04 15:27:26', 0, 0, '╥﹏╥', '0', -1, 'http://vue.xijia.plus/login', 'http://127.0.0.1:9049/api/admin/user/login', '/api/admin/user/login', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '52480', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', '用户登录', '[\"admin\",\"MTIzNDU2\",1]', '{\"code\":8101,\"msg\":\"密码错误\"}', 1, 151, 129);
INSERT INTO `t_xj_admin_log` VALUES ('1532987400358203394', NULL, NULL, '2022-06-04 15:27:32', '2022-06-04 15:27:32', 0, 0, '╥﹏╥', '0', -1, 'http://vue.xijia.plus/login', 'http://127.0.0.1:9049/api/admin/user/login', '/api/admin/user/login', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '52484', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', '用户登录', '[\"admin\",\"NTI3dzEwbjhj\",1]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 79, 68);
INSERT INTO `t_xj_admin_log` VALUES ('1532987882245984257', NULL, NULL, '2022-06-04 15:29:27', '2022-06-04 15:29:27', 0, 0, '平台主账号', '1', 0, 'http://vue.xijia.plus/views/xj/xjAdminConfig/xjAdminConfig', 'http://127.0.0.1:9049/api/admin/xj/config/1524630203798654978', '/api/admin/xj/config/1524630203798654978', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '52694', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminConfigController', 'base-plus--全局配置', 'ID编辑', '[\"1524630203798654978\",{\"code\":\"fwb-test\",\"content\":\"<p>哈哈哈</p>\\n<p><img src=\\\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vueTinymce/21839730-mceclip2.png\\\" /></p>\",\"desc\":\"测试\",\"ext1\":\"\",\"name\":\"富文本%测试 \",\"sort\":0,\"type\":3}]', '{\"code\":200,\"data\":true,\"msg\":\"编辑成功\"}', 1, 127, 93);
INSERT INTO `t_xj_admin_log` VALUES ('1532988024151871489', NULL, NULL, '2022-06-04 15:30:01', '2022-06-04 15:30:01', 0, 0, '平台主账号', '1', 0, 'http://vue.xijia.plus/views/xj/xjAdminConfig/xjAdminConfig', 'http://127.0.0.1:9049/api/admin/xj/config/1365182627308433409', '/api/admin/xj/config/1365182627308433409', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '52712', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminConfigController', 'base-plus--全局配置', 'ID编辑', '[\"1365182627308433409\",{\"code\":\"login_bg_img\",\"content\":\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210311113615990505-1.jpg\",\"desc\":\"未启用\",\"name\":\"背景图(登录页)\",\"sort\":0,\"type\":1}]', '{\"code\":200,\"data\":true,\"msg\":\"编辑成功\"}', 1, 49, 27);
INSERT INTO `t_xj_admin_log` VALUES ('1532988040585154562', NULL, NULL, '2022-06-04 15:30:05', '2022-06-04 15:30:05', 0, 0, '平台主账号', '1', 0, 'http://vue.xijia.plus/views/xj/xjAdminConfig/xjAdminConfig', 'http://127.0.0.1:9049/api/admin/xj/config/1365185332319997953', '/api/admin/xj/config/1365185332319997953', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '52724', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminConfigController', 'base-plus--全局配置', 'ID编辑', '[\"1365185332319997953\",{\"code\":\"beian\",\"content\":\"备案号：蜀ICP备19022468号-1\",\"desc\":\"未启用\",\"name\":\"备案号(登录页)\",\"sort\":0,\"type\":0}]', '{\"code\":200,\"data\":true,\"msg\":\"编辑成功\"}', 1, 61, 31);
INSERT INTO `t_xj_admin_log` VALUES ('1532988055487516674', NULL, NULL, '2022-06-04 15:30:08', '2022-06-04 15:30:08', 0, 0, '平台主账号', '1', 0, 'http://vue.xijia.plus/views/xj/xjAdminConfig/xjAdminConfig', 'http://127.0.0.1:9049/api/admin/xj/config/1365187122549551105', '/api/admin/xj/config/1365187122549551105', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '52736', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminConfigController', 'base-plus--全局配置', 'ID编辑', '[\"1365187122549551105\",{\"code\":\"project_desc\",\"content\":\"©2020-2021 该后台系统为个人开发运营，作者联系方式 QQ:1720696548\",\"desc\":\"未启用\",\"name\":\"项目描叙(登录页)\",\"sort\":0,\"type\":0}]', '{\"code\":200,\"data\":true,\"msg\":\"编辑成功\"}', 1, 48, 31);
INSERT INTO `t_xj_admin_log` VALUES ('1532988120696360961', NULL, NULL, '2022-06-04 15:30:24', '2022-06-04 15:30:24', 0, 0, '平台主账号', '1', 0, 'http://vue.xijia.plus/views/xj/xjAdminConfig/xjAdminConfig', 'http://127.0.0.1:9049/api/admin/xj/config/1481920988265320449', '/api/admin/xj/config/1481920988265320449', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '52748', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminConfigController', 'base-plus--全局配置', 'ID编辑', '[\"1481920988265320449\",{\"code\":\"测试数据\",\"content\":\"http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/45321474-232917-1636990157bc70.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/47041365-234759-16363000792588.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/49545567-193708-15779650288588.jpg\",\"desc\":\"测试数据\",\"name\":\"test\",\"sort\":0,\"type\":1}]', '{\"code\":200,\"data\":true,\"msg\":\"编辑成功\"}', 1, 48, 23);

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
INSERT INTO `t_xj_admin_msg` VALUES ('1468559672842653698', NULL, NULL, '2021-12-08 20:34:45', '2021-12-09 16:27:34', 0, 0, '1', '测试消息', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1468560203682156546', NULL, NULL, '2021-12-08 20:36:51', '2022-02-10 22:55:54', 0, 0, '1460427339745763329', '你只是一个小测试人员，没有修改数据的权限，知道嘛', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1468560484188819457', NULL, NULL, '2021-12-08 20:37:58', '2022-02-10 22:55:57', 0, 0, '1460427339745763329', '但是你有生成预览代码，和生成vue代码的权限哦，嘻哈，快去尝试一下吧', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1468562513732833282', NULL, NULL, '2021-12-08 20:46:02', '2021-12-09 16:10:37', 0, 0, '1', '发一条测试消息给自己', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1468569468480262145', NULL, NULL, '2021-12-08 21:13:40', '2021-12-09 16:27:32', 0, 0, '1', '啦啦啦啦', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1470672728192192513', NULL, NULL, '2021-12-14 16:31:16', '2021-12-30 11:48:21', 0, 0, '1', '1111', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1471490852424519682', NULL, NULL, '2021-12-16 22:42:12', '2021-12-30 11:48:13', 0, 0, '1', '啦啦啦啦', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1481612239579385858', NULL, NULL, '2022-01-13 21:00:59', '2022-01-13 21:00:59', 0, 0, '1', '测试', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1481618032873771009', NULL, NULL, '2022-01-13 21:23:59', '2022-06-04 15:14:50', 0, 0, '1', '我是一条测试消息', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1481620125940518913', NULL, NULL, '2022-01-13 21:32:18', '2022-01-13 21:33:01', 0, 0, '1', '我是测试消息', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1493529203935547394', NULL, NULL, '2022-02-15 18:14:45', '2022-02-15 18:14:45', 0, 0, '643395209551548416', '23423423424', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1493529263423361025', NULL, NULL, '2022-02-15 18:14:59', '2022-02-23 15:11:49', 0, 0, '1460427339745763329', '微软微软', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1532980893143044097', NULL, NULL, '2022-06-04 15:01:41', '2022-06-04 15:01:41', 0, 0, '1', '消息测试', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1532981379309015041', NULL, NULL, '2022-06-04 15:03:37', '2022-06-04 15:03:37', 0, 0, '1', '啦啦啦啦', 2, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
