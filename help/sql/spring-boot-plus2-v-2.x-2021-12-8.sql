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

 Date: 08/12/2021 20:40:28
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
INSERT INTO `t_admin_authority` VALUES ('590081231815839745', NULL, NULL, '2021-09-06 01:39:00', '2021-09-06 01:39:00', 0, 480, '0', '', '/api/admin/generate', 'base-gc--代码生成', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590082183939624962', NULL, NULL, '2021-09-06 01:42:47', '2021-09-28 06:22:16', 0, 479, '590081231815839745', 'GET', '/api/admin/generate/getPath', '代码生成路径', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624963', NULL, NULL, '2021-09-06 01:42:47', '2021-09-28 06:22:16', 0, 479, '590081231815839745', 'POST', '/api/admin/generate/generateCode', '生成代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590082183939624964', NULL, NULL, '2021-09-06 01:42:47', '2021-09-28 06:22:15', 0, 479, '590081231815839745', 'POST', '/api/admin/generate/preview', '生成预览代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518954377221', NULL, NULL, '2021-09-06 22:27:52', '2021-09-06 22:27:52', 0, 446, '0', '', '/api/admin/xj/config', 'base-plus--全局配置', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518958571520', NULL, NULL, '2021-09-06 22:27:52', '2021-09-28 06:22:12', 0, 446, '590395518954377221', 'GET', '/api/admin/xj/config/list', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571521', NULL, NULL, '2021-09-06 22:27:52', '2021-09-28 06:22:14', 0, 446, '590395518954377221', 'POST', '/api/admin/xj/config', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571522', NULL, NULL, '2021-09-06 22:27:52', '2021-09-28 06:22:13', 0, 446, '590395518954377221', 'DELETE', '/api/admin/xj/config/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571524', NULL, NULL, '2021-09-06 22:27:52', '2021-09-28 06:22:12', 0, 446, '590395518954377221', 'PUT', '/api/admin/xj/config/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518958571526', NULL, NULL, '2021-09-06 22:27:52', '2021-09-06 22:27:52', 0, 446, '0', '', '/api/admin/xj/log', 'base-plus--操作记录', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765825', NULL, NULL, '2021-09-06 22:27:52', '2021-09-28 06:21:57', 0, 446, '590395518958571526', 'GET', '/api/admin/xj/log/list', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518962765828', NULL, NULL, '2021-09-06 22:27:52', '2021-09-06 22:27:52', 0, 446, '0', '', '/api/admin/xj/msg', 'base-plus--消息通知', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590395518962765829', NULL, NULL, '2021-09-06 22:27:52', '2021-09-28 06:22:09', 0, 446, '590395518962765828', 'POST', '/api/admin/xj/msg', '添加/发送消息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590395518962765830', NULL, NULL, '2021-09-06 22:27:52', '2021-09-28 06:22:10', 0, 446, '590395518962765828', 'GET', '/api/admin/xj/msg/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590398100552683520', NULL, NULL, '2021-09-06 22:38:08', '2021-09-28 06:22:09', 0, 445, '590395518962765828', 'PUT', '/api/admin/xj/msg/{id}/read', '消息修改为已读', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590417006180831232', NULL, NULL, '2021-09-06 23:53:15', '2021-09-28 06:22:10', 0, 436, '590395518962765828', 'GET', '/api/admin/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862656', NULL, NULL, '2021-09-07 01:51:37', '2021-09-07 01:51:37', 0, 427, '0', '', '/api/admin/authority', 'base--URL权限管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590446794257862657', NULL, NULL, '2021-09-07 01:51:37', '2021-11-27 01:10:39', 0, 427, '590446794257862656', 'GET', '/api/admin/authority/list', '查询所有-接口管理', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862658', NULL, NULL, '2021-09-07 01:51:37', '2021-11-24 02:17:42', 0, 427, '590446794257862656', 'PUT', '/api/admin/authority/{id}', 'ID编辑', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590446794257862660', NULL, NULL, '2021-09-07 01:51:37', '2021-12-03 03:20:42', 0, 427, '590446794257862656', 'PUT', '/api/admin/authority/refreshAuthority', '扫描权限', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717120', NULL, NULL, '2021-09-07 02:22:46', '2021-09-07 02:22:46', 0, 424, '0', '', '/api/admin/dictionary', 'base--字典管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590454633059717121', NULL, NULL, '2021-09-07 02:22:46', '2021-09-28 06:21:40', 0, 424, '590454633059717120', 'GET', '/api/admin/dictionary/list', '列表查询 (默认返回Tree数据,可指定Tree或List)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633059717122', NULL, NULL, '2021-09-07 02:22:46', '2021-09-28 06:21:43', 0, 424, '590454633059717120', 'POST', '/api/admin/dictionary', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911424', NULL, NULL, '2021-09-07 02:22:46', '2021-09-28 06:21:41', 0, 424, '590454633059717120', 'DELETE', '/api/admin/dictionary/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911425', NULL, NULL, '2021-09-07 02:22:46', '2021-09-28 06:23:06', 0, 424, '590454633059717120', 'GET', '/api/admin/dictionary/findCodeGroup', '查询所有-code分组', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911426', NULL, NULL, '2021-09-07 02:22:46', '2021-09-28 06:21:39', 0, 424, '590454633059717120', 'PUT', '/api/admin/dictionary/{id}', '编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911427', NULL, NULL, '2021-09-07 02:22:46', '2021-09-28 06:21:44', 0, 424, '590454633059717120', 'GET', '/api/admin/dictionary/generateEnum', '生成枚举', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590454633063911429', NULL, NULL, '2021-09-07 02:22:46', '2021-09-28 06:21:40', 0, 424, '590454633059717120', 'GET', '/api/admin/dictionary/list/category', '获取类别(级联数据)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452416', NULL, NULL, '2021-09-07 03:57:14', '2021-09-07 03:57:14', 0, 419, '0', '', '/api/admin/menu', 'base--菜单管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452417', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:58', 0, 419, '590478406475452416', 'GET', '/api/admin/menu/list', '列表查询(不支持分页)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452418', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:58', 0, 419, '590478406475452416', 'POST', '/api/admin/menu', '菜单添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452419', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:59', 0, 419, '590478406475452416', 'PUT', '/api/admin/menu/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452421', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:59', 0, 419, '590478406475452416', 'GET', '/api/admin/menu/findTree', '左导航菜单', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452423', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:57', 0, 419, '590478406475452416', 'DELETE', '/api/admin/menu/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406475452424', NULL, NULL, '2021-09-07 03:57:14', '2021-09-07 03:57:14', 0, 419, '0', '', '/api/admin/role', 'base--角色管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('590478406475452425', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:47', 0, 419, '590478406475452424', 'POST', '/api/admin/role', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646720', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:46', 0, 419, '590478406475452424', 'GET', '/api/admin/role/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646721', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:45', 0, 419, '590478406475452424', 'PUT', '/api/admin/role/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646722', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:46', 0, 419, '590478406475452424', 'PUT', '/api/admin/role/updRoleAuth', '角色的URL权限分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646723', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:48', 0, 419, '590478406475452424', 'PUT', '/api/admin/role/updRoleAuthAll', '所有角色拥有所有权限', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646724', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:50', 0, 419, '590478406475452424', 'PUT', '/api/admin/role/updRoleMenu', '角色的菜单分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646725', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:48', 0, 419, '590478406475452424', 'PUT', '/api/admin/role/updUserRole', '用户的角色分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('590478406479646727', NULL, NULL, '2021-09-07 03:57:14', '2021-09-28 06:21:47', 0, 419, '590478406475452424', 'DELETE', '/api/admin/role/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('591526406194860032', NULL, NULL, '2021-09-10 01:21:37', '2021-09-10 01:21:37', 0, 374, '0', '', '/api/open/aliOssFile', 'AliYun --> OSS文件管理', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('591526406194860033', NULL, NULL, '2021-09-10 01:21:37', '2021-09-10 01:21:37', 0, 374, '591526406194860032', 'POST', '/api/open/aliOssFile/upload', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('591526406194860034', NULL, NULL, '2021-09-10 01:21:37', '2021-09-10 01:21:37', 0, 374, '591526406194860032', 'GET', '/api/open/aliOssFile/fileList', 'OSS-文件Object列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('591526406194860035', NULL, NULL, '2021-09-10 01:21:37', '2021-09-10 01:21:37', 0, 374, '591526406194860032', 'GET', '/api/open/aliOssFile/downloadZip', 'OSS-文件下载--多文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('591526406194860036', NULL, NULL, '2021-09-10 01:21:37', '2021-09-10 01:21:37', 0, 374, '591526406194860032', 'GET', '/api/open/aliOssFile/download', 'OSS-文件下载--单文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('591526406194860037', NULL, NULL, '2021-09-10 01:21:37', '2021-09-10 01:21:37', 0, 374, '591526406194860032', 'DELETE', '/api/open/aliOssFile/del', 'OSS-文件删除', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('592136133727621120', NULL, NULL, '2021-09-11 17:44:28', '2021-09-28 06:22:08', 0, 361, '590395518962765828', 'GET', '/api/admin/xj/msg/findAllNum', '查询全部/已读/未读数量(当前登录用户)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594752701527625728', NULL, NULL, '2021-09-18 23:01:43', '2021-09-18 23:01:43', 0, 322, '0', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('594752701527625729', NULL, NULL, '2021-09-18 23:01:43', '2021-09-18 23:01:43', 0, 322, '594752701527625728', 'GET', '/api/open/websocket/getPath', '游客登录获取websocket连接地址', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('594752701527625730', NULL, NULL, '2021-09-18 23:01:43', '2021-09-18 23:01:43', 0, 322, '594752701527625728', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('594752701527625731', NULL, NULL, '2021-09-18 23:01:43', '2021-09-18 23:01:43', 0, 322, '594752701527625728', 'GET', '/api/open/websocket/getOnlineCount', '获取在线人数', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('594752701527625732', NULL, NULL, '2021-09-18 23:01:43', '2021-09-18 23:01:43', 0, 322, '594752701527625728', 'GET', '/api/open/websocket/getOnlineUsersList', '获取当前在线用户列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664832', NULL, NULL, '2021-09-18 23:17:51', '2021-09-18 23:17:51', 0, 320, '0', '', '/api/client/dictionary', 'yh--base--字典管理', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664833', NULL, NULL, '2021-09-18 23:17:51', '2021-09-18 23:17:51', 0, 320, '594756761932664832', 'GET', '/api/client/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664834', NULL, NULL, '2021-09-18 23:17:51', '2021-09-18 23:17:51', 0, 320, '594756761932664832', 'GET', '/api/client/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761932664835', NULL, NULL, '2021-09-18 23:17:51', '2021-09-18 23:17:51', 0, 320, '0', '', '/api/client/xj/banner', 'yh--base-plus--banner', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442057', NULL, NULL, '2021-09-18 23:17:51', '2021-09-18 23:17:51', 0, 320, '0', '', '/api/admin/datasource', 'base-gc--代码生成--数据源维护', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761949442058', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:55', 0, 320, '594756761949442057', 'GET', '/api/admin/datasource/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442059', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:54', 0, 320, '594756761949442057', 'POST', '/api/admin/datasource', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761949442060', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:55', 0, 320, '594756761949442057', 'PUT', '/api/admin/datasource/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636352', NULL, NULL, '2021-09-18 23:17:51', '2021-12-03 03:21:00', 0, 320, '594756761949442057', 'DELETE', '/api/admin/datasource/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636353', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:54', 0, 320, '594756761949442057', 'POST', '/api/admin/datasource/dataSourceTest', '数据源连接测试', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636354', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:55', 0, 320, '594756761949442057', 'PUT', '/api/admin/datasource/{id}/updPwd', '修改/重置密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636358', NULL, NULL, '2021-09-18 23:17:51', '2021-09-18 23:17:51', 0, 320, '0', '', '/api/admin/xj/banner', 'base-plus--banner', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761953636359', NULL, NULL, '2021-09-18 23:17:51', '2021-11-27 19:27:27', 0, 320, '594756761953636358', 'GET', '/api/admin/xj/banner/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636360', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:22:02', 0, 320, '594756761953636358', 'POST', '/api/admin/xj/banner', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636361', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:22:02', 0, 320, '594756761953636358', 'PUT', '/api/admin/xj/banner/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761953636362', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:22:01', 0, 320, '594756761953636358', 'DELETE', '/api/admin/xj/banner/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830656', NULL, NULL, '2021-09-18 23:17:51', '2021-09-18 23:17:51', 0, 320, '0', '', '/api/admin/xj/blacklist', 'base-plus--黑名单', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('594756761957830657', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:51', 0, 320, '594756761957830656', 'GET', '/api/admin/xj/blacklist/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830658', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:51', 0, 320, '594756761957830656', 'POST', '/api/admin/xj/blacklist', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830659', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:51', 0, 320, '594756761957830656', 'PUT', '/api/admin/xj/blacklist/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('594756761957830660', NULL, NULL, '2021-09-18 23:17:51', '2021-09-28 06:21:50', 0, 320, '594756761957830656', 'DELETE', '/api/admin/xj/blacklist/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558656827392', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '0', '', '/api/client/xj/config', 'yh--base-plus--全局配置', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827393', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '595094558656827392', 'GET', '/api/client/xj/config/findByCode', 'CODE查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827394', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '0', '', '/api/client/xj/msg', 'yh--base-plus--消息通知', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827395', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '595094558656827394', 'GET', '/api/client/xj/msg/list', '分页查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827396', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '595094558656827394', 'PUT', '/api/client/xj/msg/{id}/read', '消息修改为已读', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558656827397', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '595094558656827394', 'GET', '/api/client/xj/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410304', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '0', '', '/api/admin/user', 'base--用户管理', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558669410305', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:27', 0, 285, '595094558669410304', 'GET', '/api/admin/user/list', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410306', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:25', 0, 285, '595094558669410304', 'POST', '/api/admin/user', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410307', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:23', 0, 285, '595094558669410304', 'DELETE', '/api/admin/user/{id}', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410308', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:25', 0, 285, '595094558669410304', 'GET', '/api/admin/user/{id}', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410309', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:26', 0, 285, '595094558669410304', 'PUT', '/api/admin/user/updUser', '修改当前登录人的信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410310', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:25', 0, 285, '595094558669410304', 'GET', '/api/admin/user/findByRoleId', '获取指定角色的用户列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410311', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:22', 0, 285, '595094558669410304', 'PUT', '/api/admin/user/{id}', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410312', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:19', 0, 285, '595094558669410304', 'PUT', '/api/admin/user/updByPassword', '修改当前登录人的密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410313', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:24', 0, 285, '595094558669410304', 'GET', '/api/admin/user/list/keyData', '查询所有-只返回关键数据(姓名/昵称/电话/id)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558669410314', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:26', 0, 285, '595094558669410304', 'PUT', '/api/admin/user/{id}/resetPassword', '重置任意用户密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604608', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:23', 0, 285, '595094558669410304', 'GET', '/api/admin/user/findUser', '查询当前登录人的个人信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604609', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:23:30', 0, 285, '595094558669410304', 'POST', '/api/admin/user/login', '用户登录', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558673604610', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '0', '', '/api/admin/dataBase', 'base-gc--代码生成--查询表数据', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558673604611', NULL, NULL, '2021-09-19 21:40:08', '2021-12-03 03:20:55', 0, 285, '595094558673604610', 'GET', '/api/admin/dataBase/table/list', '查询所有表名', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558677798912', NULL, NULL, '2021-09-19 21:40:08', '2021-12-03 03:20:48', 0, 285, '595094558673604610', 'GET', '/api/admin/dataBase/table/field', '查询指定表下使用字段内容', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('595094558686187520', NULL, NULL, '2021-09-19 21:40:08', '2021-09-19 21:40:08', 0, 285, '0', '', '/api/admin/xj/jvm', 'base-plus--jvm信息获取', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('595094558686187521', NULL, NULL, '2021-09-19 21:40:08', '2021-09-28 06:22:19', 0, 285, '595094558686187520', 'GET', '/api/admin/xj/jvm/jvmInfo', '3、系统的jvm信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('598128304653996032', NULL, NULL, '2021-09-28 06:35:13', '2021-09-28 06:36:06', 0, 274, '590395518954377221', 'GET', '/api/admin/xj/config/findByCode', 'CODE查询', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('606303420856537088', NULL, NULL, '2021-10-20 20:00:10', '2021-10-20 20:00:10', 0, 184, '0', '', '/api/admin/organ', 'base--组织机构', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('606303420856537089', NULL, NULL, '2021-10-20 20:00:10', '2021-10-20 20:00:10', 0, 184, '606303420856537088', 'GET', '/api/admin/organ/list', '列表查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('606303420856537090', NULL, NULL, '2021-10-20 20:00:10', '2021-10-20 20:00:10', 0, 184, '606303420856537088', 'POST', '/api/admin/organ', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('606303420856537091', NULL, NULL, '2021-10-20 20:00:10', '2021-10-20 20:00:10', 0, 184, '606303420856537088', 'DELETE', '/api/admin/organ/{id}', 'ID删除(并删除子数据)', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('606303420856537092', NULL, NULL, '2021-10-20 20:00:10', '2021-10-20 20:00:10', 0, 184, '606303420856537088', 'PUT', '/api/admin/organ/{id}', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('613640045747900416', NULL, NULL, '2021-11-10 01:53:18', '2021-11-26 10:49:07', 0, 107, '590081231815839745', 'POST', '/api/admin/generate/generateCodeVue', '生成Vue代码(将直接下载)', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('616033178368479232', NULL, NULL, '2021-11-16 16:22:45', '2021-11-16 16:22:45', 0, 92, '0', '', '/api/admin/test/gcTest', '代码生成测试表', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('616033178368479233', NULL, NULL, '2021-11-16 16:22:45', '2021-11-16 16:22:45', 0, 92, '616033178368479232', 'GET', '/api/admin/test/gcTest/list', '列表查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('616033178368479234', NULL, NULL, '2021-11-16 16:22:45', '2021-11-16 16:22:45', 0, 92, '616033178368479232', 'POST', '/api/admin/test/gcTest', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('616033178368479235', NULL, NULL, '2021-11-16 16:22:45', '2021-11-16 16:22:45', 0, 92, '616033178368479232', 'PUT', '/api/admin/test/gcTest/{id}', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('616033178368479236', NULL, NULL, '2021-11-16 16:22:45', '2021-11-16 16:22:45', 0, 92, '616033178368479232', 'DELETE', '/api/admin/test/gcTest/{id}', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('620065229904154624', NULL, NULL, '2021-11-27 19:24:40', '2021-11-27 19:27:49', 0, 49, '594756761932664835', 'GET', '/api/client/xj/banner/list/{position}', '列表-位置查询', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('628404085946716160', NULL, NULL, '2021-12-08 19:40:19', '2021-12-08 19:40:19', 0, 3, '616033178368479232', 'GET', '/api/admin/test/gcTest/{id}', 'ID查询', 0, 0, 2, 0);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--字典' ROW_FORMAT = Dynamic;

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
INSERT INTO `t_admin_dictionary` VALUES ('1290688742289403906', NULL, '-1', '2020-08-05 00:39:10', '2021-11-10 15:33:29', 0, 0, '2', '女', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1291341478399897601', NULL, '-1', '2020-08-06 11:52:54', '2021-11-10 15:33:29', 0, 0, '0', '未知', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469448399593474', NULL, '-1', '2020-08-20 15:29:38', '2021-11-10 15:33:29', 0, 0, 'DISABLE', '是否禁用', '1290688121255587841', '【固定值】', 300, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469518025039873', NULL, '-1', '2020-08-20 15:29:55', '2021-11-10 15:33:29', 0, 0, '1', '禁用', '1296469448399593474', '-', 0, 0);
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
INSERT INTO `t_admin_dictionary` VALUES ('1368739394596401154', NULL, '-1', '2021-03-08 09:44:35', '2021-11-10 15:33:33', 0, 0, '0', '系统管理员', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1384697257961463810', NULL, '-1', '2021-04-21 10:35:27', '2021-11-10 15:33:33', 0, 0, '1', '平台人员', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968409441050625', NULL, '-1', '2021-06-02 13:57:32', '2021-11-10 15:33:34', 0, 0, 'DEFAULT', '默认字典(代码生成默认字典)', '1290688121255587841', '【固定值】用于代码生成默认使用的code值', 100, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968449656037377', NULL, '-1', '2021-06-02 13:57:42', '2021-11-10 15:33:34', 0, 0, '1', '默认值 1', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968504043577346', NULL, '-1', '2021-06-02 13:57:55', '2021-11-10 15:33:34', 0, 0, '2', '默认值 2', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968544350838786', NULL, '-1', '2021-06-02 13:58:04', '2021-11-10 15:33:34', 0, 0, '3', '默认值 3', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1404005220177985537', NULL, '-1', '2021-06-13 17:18:21', '2021-11-10 15:33:34', 0, 0, '0', '测试消息', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513445955194882', NULL, '-1', '2021-08-17 14:11:42', '2021-11-10 15:33:34', 0, 0, '4', '其他', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513925234118658', NULL, '-1', '2021-08-17 14:13:36', '2021-11-10 15:37:18', 0, 0, '3', '月卡购买', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513998571524097', NULL, '-1', '2021-08-17 14:13:53', '2021-11-10 15:37:18', 0, 0, '4', '其他', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1432260997380349954', NULL, '-1', '2021-08-30 16:36:49', '2021-11-10 15:33:34', 0, 0, 'TERMINAL', '终端', '1290686507555844098', '【动态值】 如有需要根据业务指定', 100, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1432261183641001986', NULL, '-1', '2021-08-30 16:37:33', '2021-11-10 15:33:34', 0, 0, '1', 'layui端', '1432260997380349954', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1432261342928084993', NULL, '-1', '2021-08-30 16:38:11', '2021-11-10 15:33:34', 0, 0, '2', 'avue端', '1432260997380349954', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1435127387682508801', NULL, '-1', '2021-09-07 14:26:46', '2021-12-08 20:31:31', 0, 0, '3', 'xxx端', '1432260997380349954', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1443767289248788481', NULL, '-1', '2021-10-01 10:38:39', '2021-11-10 15:33:35', 0, 0, 'ORGAN_ROOT', '机构组织级别', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1443767335407104002', NULL, '-1', '2021-10-01 10:38:50', '2021-11-10 15:33:35', 0, 0, '1', '一级', '1443767289248788481', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1443767375802445826', NULL, '-1', '2021-10-01 10:39:00', '2021-11-10 15:33:35', 0, 0, '2', '二级', '1443767289248788481', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1443767410984267777', NULL, '-1', '2021-10-01 10:39:08', '2021-11-10 15:33:35', 0, 0, '3', '三级', '1443767289248788481', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455153732051349505', NULL, NULL, '2021-11-01 20:44:19', '2021-11-10 15:33:35', 0, 0, 'VUE_FIELD_TYPE', 'VUE字段类型', '1290688121255587841', 'vue代码生成使用', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455153814570086402', NULL, NULL, '2021-11-01 20:44:39', '2021-11-10 15:33:35', 0, 0, '1', '文本-(input)', '1455153732051349505', '-', 1, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455153864008347650', NULL, NULL, '2021-11-01 20:44:51', '2021-11-10 15:33:35', 0, 0, '2', '数字-(number)', '1455153732051349505', '-', 2, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455154454599905281', NULL, NULL, '2021-11-01 20:47:12', '2021-11-10 15:33:35', 0, 0, '4', '单选-(radio)', '1455153732051349505', '-', 4, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455154827834241025', NULL, NULL, '2021-11-01 20:48:41', '2021-11-10 15:33:35', 0, 0, '9', '开关-(switch)', '1455153732051349505', '-', 9, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455154939453059073', NULL, NULL, '2021-11-01 20:49:07', '2021-11-10 15:33:35', 0, 0, '5', '多选-(checkbox)', '1455153732051349505', '-', 5, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455155390269435905', NULL, NULL, '2021-11-01 20:50:55', '2021-11-10 15:33:35', 0, 0, '10', '日期-(data)', '1455153732051349505', 'yyyy-MM-dd', 10, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1455155744738455553', NULL, NULL, '2021-11-01 20:52:19', '2021-11-10 15:33:35', 0, 0, '11', '日期时间-(datetime)', '1455153732051349505', 'yyyy-MM-dd hh:mm:ss', 11, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455155995658498049', NULL, NULL, '2021-11-01 20:53:19', '2021-11-10 15:33:36', 0, 0, '12', '时间-(time)', '1455153732051349505', '-', 12, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1455156254728073217', NULL, NULL, '2021-11-01 20:54:21', '2021-11-10 15:33:36', 0, 0, '6', '下拉选择-(select-单选)', '1455153732051349505', '-', 6, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1455156611625594881', NULL, NULL, '2021-11-01 20:55:46', '2021-11-11 22:01:14', 0, 0, '3', '密码-(password)', '1455153732051349505', '-', 3, 1);
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
INSERT INTO `t_admin_menu` VALUES ('1', '1', '-1', '2020-07-25 09:29:38', '2021-12-08 20:26:51', 1, 0, '0', '兮家-系统管理', '', '', 'layui-icon-template-1', 10000, 1, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1297047088646905857', '1', '-1', '2020-08-22 05:44:58', '2021-10-08 16:26:21', 0, 0, '4', '接口管理', NULL, '/page/manage_admin_authority_authority', 'layui-icon-file-b', 5, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1297533242571763714', '1', '-1', '2020-08-23 13:56:45', '2021-09-30 14:53:34', 0, 0, '1350297066072498179', 'banner 管理', NULL, '/page/manage_xj_banner_banner', 'layui-icon-file-b', 1, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1311827586636156929', '1', '-1', '2020-10-02 08:37:23', '2021-09-30 14:53:34', 0, 0, '1350297066072498179', '全局配置', NULL, '/page/manage_xj_config_config', 'layui-icon-file-b', 2, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1319948617724743682', '1', '-1', '2020-10-24 10:27:28', '2021-09-30 14:53:34', 0, 0, '1350298064077774850', '数据监控', '', '/druid', 'layui-icon-file-b', 2, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1321432835319414785', '1', '-1', '2020-10-28 12:45:12', '2021-09-30 14:53:34', 0, 0, '1350297066072498179', '系统日志', NULL, '/page/manage_xj_log_log', 'layui-icon-file-b', 4, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1323584939857395714', '1', '-1', '2020-11-03 19:16:55', '2021-09-30 14:53:34', 0, 0, '1350298064077774850', 'swagger-ui', NULL, '/swagger-ui.html', 'layui-icon-file-b', 1, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1332332514865364994', '1', '-1', '2020-11-27 22:36:37', '2021-09-30 14:53:34', 0, 0, '1350297066072498179', '黑/白名单', '/page/modules_admin_adminBlacklist_adminBlacklist', '/page/manage_xj_blacklist_blacklist', 'layui-icon-file-b', 3, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1350297066072498179', '1', '-1', '2021-01-16 12:21:22', '2021-12-08 20:26:51', 1, 0, '1', '增强功能', '', '', 'layui-icon-component', 200, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1350298064077774850', '1', '-1', '2021-01-16 12:25:20', '2021-12-08 20:26:51', 1, 0, '1', '系统功能', '', '-', 'layui-icon-set-sm', 400, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1369610726125096962', '1', '-1', '2021-03-10 19:26:56', '2021-09-30 14:53:35', 0, 0, '1350297066072498179', '消息管理', '', '/page/manage_xj_msg_msg', 'layui-icon-file-b', 5, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1400035312876515330', '1', '-1', '2021-06-02 18:23:24', '2021-12-08 20:26:51', 1, 0, '1', '代码生成', '', '', 'layui-icon-code-circle', 300, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1440255471893200897', '1', '-1', '2021-09-21 18:03:57', '2021-11-02 10:21:15', 0, 0, '0', 'a-vue', '', '', 'layui-icon-file-b', 1, 1, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1440255602914869250', '1', '-1', '2021-09-21 18:04:29', '2021-12-04 11:22:32', 0, 0, '1440255471893200897', '系统管理', '', '/views/admin', 'el-icon-setting', 100, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1440255716299489282', '1', '-1', '2021-09-21 18:04:56', '2021-11-20 19:16:42', 0, 0, '1440255602914869250', '菜单管理', '', '/views/admin/menu/menu', 'el-icon-document-remove', 20004, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1440256392576483330', '1', '-1', '2021-09-21 18:07:37', '2021-10-20 20:08:53', 0, 0, '0', 'a-vue-2', '', '/22', 'layui-icon-file-b', 2, 1, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1440256481906769922', '1', '-1', '2021-09-21 18:07:58', '2021-10-20 20:10:01', 0, 0, '1440256392576483330', '功能测试', '', '/views', 'layui-icon-file-b', 200, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1440271162033684482', '1', '-1', '2021-09-21 19:06:18', '2021-11-20 19:15:33', 0, 0, '1440255602914869250', '用户管理', '', '/views/admin/user/user', 'el-icon-document-remove', 20001, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1442156484086480897', '1', '-1', '2021-09-26 23:57:54', '2021-11-14 11:03:51', 0, 0, '1440255602914869250', '角色管理', '', '/views/admin/role/role', 'el-icon-document-remove', 20003, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1442156557235142657', '1', '-1', '2021-09-26 23:58:11', '2021-11-20 19:16:45', 0, 0, '1440255602914869250', '字典管理', '', '/views/admin/adminDictionary/adminDictionary', 'el-icon-document-remove', 20005, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1442156599396286466', '1', '-1', '2021-09-26 23:58:21', '2021-11-20 19:16:47', 0, 0, '1440255602914869250', '接口管理', '', '/views/admin/adminAuthority/adminAuthority', 'el-icon-document-remove', 20006, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1443489804154060802', '1', '-1', '2021-09-30 16:16:02', '2021-10-20 18:16:10', 0, 0, '4', '组织机构', '', '/page/manage_admin_organ_organ', 'layui-icon-file-b', 2, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('1449698334917865473', '1', NULL, '2021-10-17 19:26:30', '2021-10-20 19:44:40', 0, 0, '1449698274373087233', '页面-5级', NULL, '', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1449764190750285826', '1', NULL, '2021-10-17 23:48:11', '2021-12-04 11:24:44', 0, 0, '1440255471893200897', '增强功能', '', '/views/xj', 'el-icon-copy-document', 200, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1449764248052867074', '1', NULL, '2021-10-17 23:48:25', '2021-10-24 02:30:04', 1, 0, '1449764190750285826', '怕怕', NULL, '', 'el-icon-document-remove', 10001, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1450796419538522114', '1', NULL, '2021-10-20 20:09:55', '2021-11-11 13:42:56', 0, 0, '1440256481906769922', '测试页1', NULL, '/user', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1451979503835369474', '1443465040706416642', NULL, '2021-10-24 02:31:02', '2021-11-14 19:48:32', 0, 0, '1449764190750285826', 'Banner管理', '', '/views/xj/xjAdminBanner/xjAdminBanner', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1452091447254749186', '1', NULL, '2021-10-24 09:55:54', '2021-12-04 11:25:07', 0, 0, '1440255471893200897', '代码生成', NULL, '/views/gc', 'el-icon-edit', 0, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1452091513113710594', '1', NULL, '2021-10-24 09:56:10', '2021-11-16 11:11:47', 0, 0, '1452091447254749186', '数据表', NULL, '/views/gc/codeGeneration/codeGeneration', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1456054437146644481', '1', NULL, '2021-11-04 08:23:24', '2021-11-14 19:06:45', 0, 0, '1452091447254749186', '生成的代码测试页', NULL, '/views/gc/gcTest/gcTest', 'el-icon-document-remove', 1, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1457369967249879042', '1', NULL, '2021-11-07 23:30:51', '2021-11-07 23:30:51', 0, 0, '0', '测试', NULL, NULL, 'el-icon-document-remove', 0, 1, 0, 3);
INSERT INTO `t_admin_menu` VALUES ('1457370029065531394', '1', NULL, '2021-11-07 23:31:06', '2021-11-07 23:31:06', 0, 0, '1457369967249879042', '测试', NULL, NULL, 'el-icon-document-remove', 0, 2, 0, 3);
INSERT INTO `t_admin_menu` VALUES ('1457370075530031105', '1', NULL, '2021-11-07 23:31:17', '2021-11-07 23:31:17', 0, 0, '1457370029065531394', '啦啦', NULL, '/aaaa', 'el-icon-document-remove', 0, 3, 0, 3);
INSERT INTO `t_admin_menu` VALUES ('1457372083897004033', '1', NULL, '2021-11-07 23:39:16', '2021-11-07 23:39:16', 0, 0, '1457370029065531394', 'aaa', NULL, 'bbb', 'el-icon-document-remove', 0, 3, 0, 3);
INSERT INTO `t_admin_menu` VALUES ('1459712656557576194', '1', NULL, '2021-11-14 10:39:51', '2021-11-20 19:16:35', 0, 0, '1440255602914869250', '组织机构', NULL, '/views/admin/adminOrgan/adminOrgan', 'el-icon-document-remove', 20002, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1459850402525622274', '1', NULL, '2021-11-14 19:47:12', '2021-11-14 19:47:24', 0, 0, '1449764190750285826', '全局配置', NULL, '/views/xj/xjAdminConfig/xjAdminConfig', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1460481331314073602', '1', NULL, '2021-11-16 13:34:18', '2021-11-20 17:18:52', 1, 0, '1440255471893200897', '首页', NULL, '/views/wel/jvmInfo', 'el-icon-document-remove', 0, 2, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1461987433667141634', '1', NULL, '2021-11-20 17:19:01', '2021-12-04 11:23:31', 0, 0, '1440255471893200897', '首页', NULL, '/wel/jvmInfo', 'el-icon-menu', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1462256665587916801', '1', NULL, '2021-11-21 11:08:51', '2021-11-21 11:09:08', 0, 0, '1449764190750285826', '黑/白名单', NULL, '/views/xj/xjAdminBlacklist/xjAdminBlacklist', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1462261436877152258', '1', NULL, '2021-11-21 11:27:48', '2021-11-21 11:27:58', 0, 0, '1449764190750285826', '请求日志', NULL, '/views/xj/xjAdminLog/xjAdminLog', 'el-icon-document-remove', 0, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('1468415037767946242', '1', NULL, '2021-12-08 11:00:01', '2021-12-08 11:00:20', 0, 0, '1449764190750285826', '消息管理', NULL, '/views/xj/xjAdminMsg/xjAdminMsg', 'el-icon-s-comment', 5, 3, 0, 2);
INSERT INTO `t_admin_menu` VALUES ('21', '1', '-1', '2020-07-25 09:29:38', '2021-10-08 16:26:19', 0, 0, '4', '系统用户', '', '/page/manage_admin_user_user', 'layui-icon-file-b', 3, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('22', '1', '-1', '2020-07-25 09:29:38', '2021-10-08 16:26:20', 0, 0, '4', '角色管理', '', '/page/manage_admin_role_role', 'layui-icon-file-b', 4, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('25', '1', '-1', '2020-07-25 09:29:38', '2021-10-08 16:26:22', 0, 0, '4', '字典管理', '', '/page/manage_admin_dictionary_dictionary', 'layui-icon-file-b', 6, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('30', '1', '-1', '2020-07-25 09:29:38', '2021-09-30 14:53:34', 0, 0, '1400035312876515330', '数据表', '', '/page/manage_gc_dataBase_dataBase', 'layui-icon-file-b', 1, 3, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('4', '1', '-1', '2020-07-25 09:29:38', '2021-12-08 20:26:51', 1, 0, '1', '系统管理', NULL, '', 'layui-icon-set', 100, 2, 0, 1);
INSERT INTO `t_admin_menu` VALUES ('7', '1', '-1', '2020-07-25 09:29:38', '2021-09-30 14:53:33', 0, 0, '4', '菜单管理', '', '/page/manage_admin_menu_menu', 'layui-icon-file-b', 1, 3, 0, 1);

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
INSERT INTO `t_admin_organ` VALUES ('1443501889504210946', NULL, '-1', '2021-09-30 17:04:03', '2021-10-08 16:23:59', 0, 0, '0', 'zgs', '成都总公司', '-', 0, 0, 1);
INSERT INTO `t_admin_organ` VALUES ('1443502090977603585', NULL, '-1', '2021-09-30 17:04:51', '2021-10-08 16:24:00', 0, 0, '1443501889504210946', 'zgsa', '子公司a', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1443502157943861250', NULL, '-1', '2021-09-30 17:05:07', '2021-11-14 10:48:34', 0, 0, '1443501889504210946', 'zgsb', '子公司b', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1443502249136418817', NULL, '-1', '2021-09-30 17:05:29', '2021-10-08 16:32:42', 0, 0, '1443502090977603585', 'kfb', '开发部', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1443502302433439746', NULL, '-1', '2021-09-30 17:05:42', '2021-10-08 16:24:00', 0, 0, '1443502090977603585', 'csb', '测试部', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1443502428644241409', NULL, '-1', '2021-09-30 17:06:12', '2021-11-29 19:50:02', 0, 0, '1443502157943861250', 'yyb', '运营部', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1459717628875608065', NULL, NULL, '2021-11-14 10:59:36', '2021-11-28 21:27:45', 1, 0, '0', '111', '测试11', '-', 0, 0, 1);
INSERT INTO `t_admin_organ` VALUES ('1459717651424186369', NULL, NULL, '2021-11-14 10:59:42', '2021-11-28 21:27:42', 1, 0, '1459717628875608065', '121', '121', '-', 0, 0, 2);
INSERT INTO `t_admin_organ` VALUES ('1459717674845179906', NULL, NULL, '2021-11-14 10:59:47', '2021-11-28 21:27:42', 1, 0, '1459717651424186369', '11', '测试', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1459717751051489281', NULL, NULL, '2021-11-14 11:00:05', '2021-11-28 21:27:42', 1, 0, '1459717651424186369', 'test3', '11', '-', 0, 0, 3);
INSERT INTO `t_admin_organ` VALUES ('1468426496627490818', NULL, NULL, '2021-12-08 11:45:33', '2021-12-08 11:45:33', 0, 0, '1443501889504210946', 'zb', '公司总部', '-', 0, 0, 2);

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
  `terminal` int(1) DEFAULT 1 COMMENT '终端 (字段code)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('1443467633444806658', '1', '-1', '2021-09-30 14:47:56', '2021-10-14 21:02:56', 0, 0, 'avue-超管', 'avue超管', 'avue-sys', 0, 2);
INSERT INTO `t_admin_role` VALUES ('1447115588580159489', '1446872739607478273', NULL, '2021-10-10 16:23:36', '2021-11-16 10:00:17', 0, 0, 'avue 体验账号', '-', 'test', 0, 2);

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
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648706', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590446794257862656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648707', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590446794257862657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648708', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590446794257862660', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648709', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590446794257862658', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648710', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558673604610', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648711', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558677798912', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648712', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558673604611', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648713', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761949442057', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648714', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761953636352', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648715', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761949442058', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648716', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761949442059', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648717', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761953636353', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009704648718', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761949442060', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009780146178', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761953636354', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009780146179', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590454633059717120', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009780146180', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590454633063911424', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009780146181', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590454633063911425', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009780146182', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590454633063911427', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009780146183', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590454633059717121', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672130', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590454633063911429', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672131', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590454633059717122', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672132', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590454633063911426', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672133', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590081231815839745', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672134', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590082183939624962', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672135', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590082183939624963', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672136', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '613640045747900416', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672137', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590082183939624964', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672138', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406475452416', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672139', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406475452423', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672140', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406475452421', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672141', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406475452417', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672142', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406475452418', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672143', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406475452419', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672144', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '606303420856537088', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672145', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '606303420856537091', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672146', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '606303420856537089', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672147', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '606303420856537090', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672148', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '606303420856537092', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672149', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406475452424', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009834672150', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406479646727', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809474', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406479646720', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809475', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406475452425', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809476', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406479646722', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809477', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406479646723', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809478', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406479646724', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809479', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406479646725', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809480', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590478406479646721', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809481', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '616033178368479232', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809482', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '616033178368479236', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809483', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '616033178368479233', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809484', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '628404085946716160', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809485', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '616033178368479234', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809486', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '616033178368479235', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809487', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410304', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809488', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410307', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809489', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410310', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809490', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558673604608', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809491', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410305', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809492', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410313', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809493', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410308', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809494', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410306', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809495', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558673604609', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809496', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410312', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009880809497', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410309', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918338', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410311', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918339', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558669410314', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918340', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761953636358', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918341', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761953636362', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918342', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761953636359', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918343', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761953636360', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918344', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761953636361', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918345', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761957830656', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918346', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761957830660', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918347', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761957830657', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918348', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761957830658', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918349', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '594756761957830659', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009947918350', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518954377221', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055681', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518958571522', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055682', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '598128304653996032', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055683', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518958571520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055684', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518958571521', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055685', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518958571524', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055686', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558686187520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055687', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '595094558686187521', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055688', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518958571526', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055689', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518962765825', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055690', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518962765828', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055691', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '592136133727621120', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055692', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590417006180831232', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055693', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518962765830', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055694', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590395518962765829', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468558009994055695', NULL, NULL, '2021-12-08 20:28:08', '2021-12-08 20:28:08', 0, 0, '590398100552683520', '1443467633444806658');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019713', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590446794257862656', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019714', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590446794257862657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019715', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558673604610', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019716', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558677798912', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019717', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558673604611', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019718', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '594756761949442057', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019719', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '594756761949442058', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019720', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590454633059717120', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019721', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590454633063911425', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019722', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590454633063911427', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019723', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590454633059717121', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019724', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590454633063911429', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019725', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590081231815839745', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019726', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590082183939624962', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019727', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '613640045747900416', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019728', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590082183939624964', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019729', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590478406475452416', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019730', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590478406475452421', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019731', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590478406475452417', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019732', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '606303420856537088', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019733', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '606303420856537089', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019734', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590478406475452424', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725420019735', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590478406479646720', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905793', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '616033178368479232', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905794', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '616033178368479233', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905795', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '628404085946716160', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905796', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558669410304', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905797', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558669410310', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905798', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558673604608', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905799', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558669410305', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905800', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558669410313', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905801', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558669410308', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905802', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '594756761953636358', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905803', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '594756761953636359', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905804', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '594756761957830656', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905805', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '594756761957830657', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905806', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590395518954377221', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905807', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '598128304653996032', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905808', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590395518958571520', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905809', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558686187520', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905810', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '595094558686187521', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905811', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590395518958571526', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905812', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590395518962765825', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905813', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590395518962765828', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905814', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '592136133727621120', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905815', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590417006180831232', '1447115588580159489');
INSERT INTO `t_admin_role_auth` VALUES ('1468560725503905816', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, '590395518962765830', '1447115588580159489');

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
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003713', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1440255471893200897');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003714', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1461987433667141634');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003715', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1452091447254749186');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003716', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1452091513113710594');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003717', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1456054437146644481');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003718', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1440255602914869250');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003719', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1440271162033684482');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003720', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1459712656557576194');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003721', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1442156484086480897');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003722', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1440255716299489282');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003723', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1442156557235142657');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003724', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1442156599396286466');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003725', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1449764190750285826');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903702003726', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1462261436877152258');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903752335361', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1462256665587916801');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903752335362', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1459850402525622274');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903752335363', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1451979503835369474');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903752335364', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1468415037767946242');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903752335365', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1440256392576483330');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903752335366', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1440256481906769922');
INSERT INTO `t_admin_role_menu` VALUES ('1468557903752335367', NULL, NULL, '2021-12-08 20:27:43', '2021-12-08 20:27:43', 0, 0, '1443467633444806658', '1450796419538522114');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945884119041', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1440255471893200897');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284865', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1461987433667141634');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284866', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1452091447254749186');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284867', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1452091513113710594');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284868', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1456054437146644481');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284869', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1440255602914869250');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284870', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1440271162033684482');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284871', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1459712656557576194');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284872', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1442156484086480897');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284873', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1440255716299489282');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284874', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1442156557235142657');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284875', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1442156599396286466');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284876', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1449764190750285826');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945909284877', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1462261436877152258');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945942839298', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1462256665587916801');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945942839299', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1459850402525622274');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945942839300', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1451979503835369474');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945942839301', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1468415037767946242');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945942839302', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1440256392576483330');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945942839303', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1440256481906769922');
INSERT INTO `t_admin_role_menu` VALUES ('1468557945942839304', NULL, NULL, '2021-12-08 20:27:53', '2021-12-08 20:27:53', 0, 0, '1447115588580159489', '1450796419538522114');

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
INSERT INTO `t_admin_role_user` VALUES ('1458066740999204865', NULL, NULL, '2021-11-09 21:39:35', '2021-11-09 21:39:35', 0, 0, '1', '1443467633444806658');
INSERT INTO `t_admin_role_user` VALUES ('1460427595434729473', NULL, NULL, '2021-11-16 10:00:46', '2021-11-16 10:00:46', 0, 0, '1460427339745763329', '1447115588580159489');

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
INSERT INTO `t_admin_user` VALUES ('1', NULL, '-1', '2020-08-02 15:11:04', '2021-12-08 20:35:53', 0, 2, 'admin', '229db5460300fdcede08e56a90529475', 1, 0, '1443501889504210946,1443502090977603585,1443502249136418817', 0, 2, 'http://xijia.plus/oss/file/image/head/20200822150143006266-5.png', '10000', 'admin', '四川成都', 0, '2020-08-02 23:11:05', '2021-12-08 20:35:53', NULL, NULL);
INSERT INTO `t_admin_user` VALUES ('1460427339745763329', '1', NULL, '2021-11-16 09:59:45', '2021-12-08 20:36:11', 0, 0, 'test', '229db5460300fdcede08e56a90529475', 0, 0, '1443501889504210946,1443502090977603585,1443502302433439746', 0, 2, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/44592984-timg(9).jpg', '17600000000', 'test', '0', 0, '2021-11-16 09:59:46', '2021-12-08 20:36:12', NULL, NULL);

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
  `text` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更多信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_gc_test
-- ----------------------------
INSERT INTO `t_gc_test` VALUES ('1456780575175118849', NULL, NULL, '2021-11-06 08:28:48', '2021-11-09 21:32:51', 1, 0, '兮家小二', 20.00, 3, '3,2', 2, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/32859734-timg(3).jpg', '2021-11-06 08:28:31', '啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦啦啦啦啦啦啦啊啊啦啦');
INSERT INTO `t_gc_test` VALUES ('1458065155967528961', NULL, NULL, '2021-11-09 21:33:17', '2021-12-08 11:40:34', 0, 0, '测试', 22.00, 2, '2,3', 3, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/09798637-timg(2).jpg', '2021-11-09 09:33:11', '啦啦啦啦啦啦啦');

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
INSERT INTO `t_xj_admin_banner` VALUES ('1300260217146548226', NULL, NULL, '2020-08-31 10:32:48', '2021-11-14 18:49:49', 0, 0, 1, '测试2', '测试数据2', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210401214853224489-qs44ufe2024qs44ufe2024.jpg', 1, 1, 0, '/page/logoBanner/page/logoBanner');
INSERT INTO `t_xj_admin_banner` VALUES ('1300262684328435714', NULL, NULL, '2020-08-31 10:42:36', '2021-03-22 10:38:07', 0, 0, 1, '测试1', '测试数据一', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210322103754968659-aaaa.png', 0, 0, 2, 'http://www.baidu.com');
INSERT INTO `t_xj_admin_banner` VALUES ('1309111625118248961', NULL, NULL, '2020-09-24 20:45:06', '2021-02-05 15:36:11', 0, 0, 1, '测试', '测试描叙', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/2.jpg', 0, 0, 0, '');
INSERT INTO `t_xj_admin_banner` VALUES ('1369571919208206338', NULL, NULL, '2021-03-10 16:52:44', '2021-11-14 18:58:41', 1, 0, 1, '1', '1', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210310165236917290-5.jpg', 0, 0, 1, 'bbb');
INSERT INTO `t_xj_admin_banner` VALUES ('1451984726834319362', NULL, NULL, '2021-10-24 02:51:50', '2021-10-24 02:51:54', 1, 0, 1, '2', '3', '4', 5, 6, 7, '8');
INSERT INTO `t_xj_admin_banner` VALUES ('1459838027261095938', NULL, NULL, '2021-11-14 18:58:02', '2021-11-14 18:58:35', 0, 0, 1, '测试', '啦啦啦啦', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/41883812-timg(2).jpg', 0, 0, 0, '');

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
INSERT INTO `t_xj_admin_blacklist` VALUES ('1421369811404894210', NULL, NULL, '2021-07-31 15:19:05', '2021-11-21 11:18:54', 0, 0, 2, '192.168.2.163', '本地', 1);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1462259668311121921', NULL, NULL, '2021-11-21 11:20:47', '2021-11-21 11:20:47', 0, 0, 1, '1', '1', 0);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统增强表--全局数据配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_xj_admin_config
-- ----------------------------
INSERT INTO `t_xj_admin_config` VALUES ('1365174805250260994', NULL, '-1', '2021-02-26 13:40:11', '2021-09-15 22:15:48', 0, 0, 'entry_name', 'spring-boot-plus2', 'spring-boot-plus2-通用后台管理系统', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365182627308433409', NULL, '-1', '2021-02-26 14:11:17', '2021-09-07 16:19:45', 0, 0, 'login_bg_img', '背景图(登录页)', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210311113615990505-1.jpg', 0, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1365185332319997953', NULL, '-1', '2021-02-26 14:22:01', '2021-09-07 16:19:45', 0, 0, 'beian', '备案号(登录页)', '备案号：蜀ICP备19022468号-1', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365187122549551105', NULL, '-1', '2021-02-26 14:29:09', '2021-09-07 16:19:45', 0, 0, 'project_desc', '项目描叙(登录页)', '©2020-2021 该后台系统为个人开发运营，作者联系方式 QQ:1720696548', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1369572634420924417', NULL, '0', '2021-03-10 16:55:34', '2021-11-28 22:45:24', 0, 0, 'test', '测试数据', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/00717589-timg(2).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/13054806-timg(1).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/16142646-timg(3).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/19460031-timg(7).jpg', 3, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1383627414470467586', NULL, '-1', '2021-04-18 11:44:16', '2021-11-14 20:05:05', 0, 0, 'is_sign', '验签开关 ( true / false) -默认true', 'false', 0, 2);
INSERT INTO `t_xj_admin_config` VALUES ('1383636872395255809', NULL, '-1', '2021-04-18 12:21:51', '2021-11-14 20:05:00', 0, 0, 'is_swagger', 'swagger文档开关(true / false)', 'true', 0, 2);
INSERT INTO `t_xj_admin_config` VALUES ('1383644845431689218', NULL, '-1', '2021-04-18 12:53:32', '2021-11-14 20:04:54', 0, 0, 'is_login_token', '访问登录页是否需要令牌', 'false', 0, 2);
INSERT INTO `t_xj_admin_config` VALUES ('1432597381643304961', NULL, '-1', '2021-08-31 14:53:26', '2021-11-14 20:04:48', 0, 0, 'is_auth', 'api 接口是否验权(true / false)', 'true', 0, 2);
INSERT INTO `t_xj_admin_config` VALUES ('1441701074921598977', NULL, '-1', '2021-09-25 17:48:16', '2021-10-21 22:40:55', 0, 0, 'login_expiration_manage', '管理端登录令牌有效期 (单位分)', '200', 0, 0);

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
INSERT INTO `t_xj_admin_log` VALUES ('1468559672863625218', NULL, NULL, '2021-12-08 20:34:45', '2021-12-08 20:34:45', 0, 0, 'admin', '1', 0, 'http://vue.xijia.plus/views/xj/xjAdminMsg/xjAdminMsg', 'http://127.0.0.1:9049/api/admin/xj/msg', '/api/admin/xj/msg', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '44606', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminMsgController', 'base-plus--消息通知', '添加/发送消息', '[{\"content\":\"测试消息\",\"msgType\":0,\"userId\":\"1\",\"userType\":2}]', '{\"code\":200,\"data\":\"1468559672842653698\",\"msg\":\"成功\"}', 1, 29, 11);
INSERT INTO `t_xj_admin_log` VALUES ('1468559958554447874', NULL, NULL, '2021-12-08 20:35:53', '2021-12-08 20:35:53', 0, 0, '╥﹏╥', '0', -1, 'http://vue.xijia.plus/login', 'http://127.0.0.1:9049/api/admin/user/login', '/api/admin/user/login', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '44648', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', '用户登录', '[\"admin\",\"123456\",null]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 87, 83);
INSERT INTO `t_xj_admin_log` VALUES ('1468560035993882626', NULL, NULL, '2021-12-08 20:36:11', '2021-12-08 20:36:11', 0, 0, '╥﹏╥', '0', -1, 'http://vue.xijia.plus/login', 'http://127.0.0.1:9049/api/admin/user/login', '/api/admin/user/login', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '44686', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', '用户登录', '[\"test\",\"123456\",null]', '{\"code\":200,\"data\":true,\"msg\":\"成功\"}', 1, 41, 34);
INSERT INTO `t_xj_admin_log` VALUES ('1468560072673071106', NULL, NULL, '2021-12-08 20:36:20', '2021-12-08 20:36:20', 0, 0, 'test', '1460427339745763329', 0, 'http://vue.xijia.plus/views/admin/user/user', 'http://127.0.0.1:9049/api/admin/user/1460427339745763329', '/api/admin/user/1460427339745763329', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '44748', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', 'ID编辑', '[\"1460427339745763329\",{\"disable\":1}]', '{\"code\":10001,\"msg\":\"没有权限\"}', 0, 34, 0);
INSERT INTO `t_xj_admin_log` VALUES ('1468560076317921281', NULL, NULL, '2021-12-08 20:36:21', '2021-12-08 20:36:21', 0, 0, 'test', '1460427339745763329', 0, 'http://vue.xijia.plus/views/admin/user/user', 'http://127.0.0.1:9049/api/admin/user/1460427339745763329', '/api/admin/user/1460427339745763329', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '44752', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminUserController', 'base--用户管理', 'ID编辑', '[\"1460427339745763329\",{\"disable\":0}]', '{\"code\":10001,\"msg\":\"没有权限\"}', 0, 13, 0);
INSERT INTO `t_xj_admin_log` VALUES ('1468560203770236930', NULL, NULL, '2021-12-08 20:36:51', '2021-12-08 20:36:51', 0, 0, 'admin', '1', 0, 'http://vue.xijia.plus/views/xj/xjAdminMsg/xjAdminMsg', 'http://127.0.0.1:9049/api/admin/xj/msg', '/api/admin/xj/msg', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '44760', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminMsgController', 'base-plus--消息通知', '添加/发送消息', '[{\"content\":\"你只是一个小测试人员，没有修改数据的权限，知道嘛\",\"msgType\":0,\"userId\":\"1460427339745763329\",\"userType\":2}]', '{\"code\":200,\"data\":\"1468560203682156546\",\"msg\":\"成功\"}', 1, 38, 26);
INSERT INTO `t_xj_admin_log` VALUES ('1468560484264316929', NULL, NULL, '2021-12-08 20:37:58', '2021-12-08 20:37:58', 0, 0, 'admin', '1', 0, 'http://vue.xijia.plus/views/xj/xjAdminMsg/xjAdminMsg', 'http://127.0.0.1:9049/api/admin/xj/msg', '/api/admin/xj/msg', '172.17.0.1', '172.17.0.1', 'POST', '127.0.0.1', '44774', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminMsgController', 'base-plus--消息通知', '添加/发送消息', '[{\"content\":\"但是你有生成预览代码，和生成vue代码的权限哦，嘻哈，快去尝试一下吧\",\"msgType\":0,\"userId\":\"1460427339745763329\",\"userType\":2}]', '{\"code\":200,\"data\":\"1468560484188819457\",\"msg\":\"成功\"}', 1, 58, 17);
INSERT INTO `t_xj_admin_log` VALUES ('1468560725583597570', NULL, NULL, '2021-12-08 20:38:56', '2021-12-08 20:38:56', 0, 0, 'admin', '1', 0, 'http://vue.xijia.plus/views/admin/role/role', 'http://127.0.0.1:9049/api/admin/role/updRoleAuth', '/api/admin/role/updRoleAuth', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '44808', 'io.github.wslxm.springbootplus2.manage.admin.controller', 'io.github.wslxm.springbootplus2.manage.admin.controller.AdminRoleController', 'base--角色管理', '角色的URL权限分配', '[{\"authIds\":[\"590446794257862656\",\"590446794257862657\",\"595094558673604610\",\"595094558677798912\",\"595094558673604611\",\"594756761949442057\",\"594756761949442058\",\"590454633059717120\",\"590454633063911425\",\"590454633063911427\",\"590454633059717121\",\"590454633063911429\",\"590081231815839745\",\"590082183939624962\",\"613640045747900416\",\"590082183939624964\",\"590478406475452416\",\"590478406475452421\",\"590478406475452417\",\"606303420856537088\",\"606303420856537089\",\"590478406475452424\",\"590478406479646720\",\"616033178368479232\",\"616033178368479233\",\"628404085946716160\",\"595094558669410304\",\"595094558669410310\",\"595094558673604608\",\"595094558669410305\",\"595094558669410313\",\"595094558669410308\",\"594756761953636358\",\"594756761953636359\",\"594756761957830656\",\"594756761957830657\",\"590395518954377221\",\"598128304653996032\",\"590395518958571520\",\"595094558686187520\",\"595094558686187521\",\"590395518958571526\",\"590395518962765825\",\"590395518962765828\",\"592136133727621120\",\"590417006180831232\",\"590395518962765830\"],\"roleId\":\"1447115588580159489\"}]', '{\"code\":200,\"data\":true,\"msg\":\"编辑成功\"}', 1, 69, 49);
INSERT INTO `t_xj_admin_log` VALUES ('1468560855225339905', NULL, NULL, '2021-12-08 20:39:27', '2021-12-08 20:39:27', 0, 0, 'test', '1460427339745763329', 0, 'http://vue.xijia.plus/views/admin/user/user', 'http://127.0.0.1:9049/api/admin/xj/msg/1468560203682156546/read', '/api/admin/xj/msg/1468560203682156546/read', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '44854', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminMsgController', 'base-plus--消息通知', '消息修改为已读', '[\"1468560203682156546\"]', '{\"code\":10001,\"msg\":\"没有权限\"}', 0, 11, 0);
INSERT INTO `t_xj_admin_log` VALUES ('1468560858652086274', NULL, NULL, '2021-12-08 20:39:27', '2021-12-08 20:39:27', 0, 0, 'test', '1460427339745763329', 0, 'http://vue.xijia.plus/views/admin/user/user', 'http://127.0.0.1:9049/api/admin/xj/msg/1468560203682156546/read', '/api/admin/xj/msg/1468560203682156546/read', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '44858', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminMsgController', 'base-plus--消息通知', '消息修改为已读', '[\"1468560203682156546\"]', '{\"code\":10001,\"msg\":\"没有权限\"}', 0, 10, 0);
INSERT INTO `t_xj_admin_log` VALUES ('1468560859235094530', NULL, NULL, '2021-12-08 20:39:28', '2021-12-08 20:39:28', 0, 0, 'test', '1460427339745763329', 0, 'http://vue.xijia.plus/views/admin/user/user', 'http://127.0.0.1:9049/api/admin/xj/msg/1468560203682156546/read', '/api/admin/xj/msg/1468560203682156546/read', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '44862', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminMsgController', 'base-plus--消息通知', '消息修改为已读', '[\"1468560203682156546\"]', '{\"code\":10001,\"msg\":\"没有权限\"}', 0, 10, 0);
INSERT INTO `t_xj_admin_log` VALUES ('1468560859885211649', NULL, NULL, '2021-12-08 20:39:28', '2021-12-08 20:39:28', 0, 0, 'test', '1460427339745763329', 0, 'http://vue.xijia.plus/views/admin/user/user', 'http://127.0.0.1:9049/api/admin/xj/msg/1468560203682156546/read', '/api/admin/xj/msg/1468560203682156546/read', '172.17.0.1', '172.17.0.1', 'PUT', '127.0.0.1', '44866', 'io.github.wslxm.springbootplus2.manage.xj.controller', 'io.github.wslxm.springbootplus2.manage.xj.controller.XjAdminMsgController', 'base-plus--消息通知', '消息修改为已读', '[\"1468560203682156546\"]', '{\"code\":10001,\"msg\":\"没有权限\"}', 0, 12, 0);

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
INSERT INTO `t_xj_admin_msg` VALUES ('1468559672842653698', NULL, NULL, '2021-12-08 20:34:45', '2021-12-08 20:34:45', 0, 0, '1', '测试消息', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1468560203682156546', NULL, NULL, '2021-12-08 20:36:51', '2021-12-08 20:36:51', 0, 0, '1460427339745763329', '你只是一个小测试人员，没有修改数据的权限，知道嘛', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1468560484188819457', NULL, NULL, '2021-12-08 20:37:58', '2021-12-08 20:37:58', 0, 0, '1460427339745763329', '但是你有生成预览代码，和生成vue代码的权限哦，嘻哈，快去尝试一下吧', 2, 0, 0);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方支付记录表' ROW_FORMAT = Dynamic;

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
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '账号id (用户id或商家id  |  平台总账账号为0)',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `money_after` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '用户剩余金额 (交易后金额,如没有钱包默认0)',
  `money` decimal(10, 2) NOT NULL COMMENT '收入支出金额(正数)',
  `wallet_type` int(1) NOT NULL COMMENT '流水类型(1-收入 2-支出)',
  `business_type` int(1) NOT NULL COMMENT '业务类型( 字段code)',
  `business_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务描叙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单/流水/支付流水表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
