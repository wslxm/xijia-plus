/*
 Navicat Premium Data Transfer

 Source Server         : jie
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-8vbkpoec225c821d8vo.mysql.zhangbei.rds.aliyuncs.com:3306
 Source Schema         : xijia-plus

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 24/03/2023 11:28:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
-- Table structure for t_gc_datasource
-- ----------------------------
DROP TABLE IF EXISTS `t_gc_datasource`;
CREATE TABLE `t_gc_datasource`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `db_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db -标题',
  `db_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'db 库名',
  `db_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'db 连接地址',
  `db_username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'db 账号',
  `db_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'db 密码',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `describe` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `project_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名/路径，如：xj-server/xj-test-server',
  `pack_path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '包路径 (如: io.github.wslxm)',
  `root_module` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '根模块 (固定为：modules(管理端), 用户端为：client)',
  `modules_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子模块 (业务分类,如用户管理,订单管理模块拆分，也可以统一一个名称放在一起)',
  `db_table_prefix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db 表前缀 (生成的类名会过滤掉前缀)',
  `db_field_prefix` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'db 字段前缀 (生成的字段名会过滤掉前缀)',
  `entity_swagger` tinyint(1) NULL DEFAULT NULL COMMENT '实体类是否使用swagger注释 (false情况下使用doc注释)',
  `filter_crud` tinyint(1) NULL DEFAULT NULL COMMENT '是否过滤crud方法- 默认生成 (controller/service/mapper/xml)',
  `father_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成路径(不填默认当前项目跟目录,可指定绝对路径)',
  `vue_field_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '排除vue字段类型 (字典code值，参考字典生成字段类型，如: 18=富文本 19=md编辑器 )',
  `base_fields` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库通用字段',
  `keyword_array` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库关键字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--gc--代码生成动态数据源' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_gc_datasource
-- ----------------------------
INSERT INTO `t_gc_datasource` VALUES ('1553023868891852801', '-1', '-1', '2022-07-29 22:25:16', '2022-10-30 16:10:12', 0, 0, 'spring-boot-plus2 (本地)', 'spring-boot-plus2', '127.0.0.1:3306', 'root', 'MTIzNDU2', 'ws', '1720696548@qq.com', '::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655', 'xj-base/xj-base-admin', 'io.github.wslxm.springbootplus2', 'manage', 'account', 't_', NULL, 1, 0, '', '18,19', 'id,create_user,update_user,create_time,update_time,deleted,version', 'time,desc,name,key,value,mysql,info,form,sort,icon,like,unlock,unLock,comment,disable,force,describe');
INSERT INTO `t_gc_datasource` VALUES ('1553190478550188034', '-1', '-1', '2022-07-30 09:27:21', '2022-10-30 16:10:12', 0, 0, 'xijia-tool-app (pro)', 'xijia-tool-app', 'rm-8vbkpoec225c821d8vo.mysql.zhangbei.rds.aliyuncs.com', 'root', 'WEpyb290MTIzNDU2', 'ws', '1720696548@qq.com', '::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655', 'xj-base/xj-base-admin', 'io.github.wslxm.springbootplus2', 'manage', 'test', 't_', NULL, 0, NULL, 'F://javagc/xijia-tool-app/', '18,19', 'id,create_user,update_user,create_time,update_time,deleted,version', 'time,desc,name,key,value,mysql,info,form,sort,icon,like,unlock,unLock,comment,disable,force,describe');
INSERT INTO `t_gc_datasource` VALUES ('1608273245390303234', NULL, NULL, '2022-12-29 09:26:35', '2022-12-29 09:54:02', 0, 0, 'xijia-plus (当前远程版)', 'xijia-plus', 'rm-8vbkpoec225c821d8vo.mysql.zhangbei.rds.aliyuncs.com', 'wangsong', 'WEpzcHJpbmdib290cGx1c0Aj', 'ws', '1720696548@qq.com', '::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655', 'xj-base/xj-base-admin', 'io.github.wslxm.springbootplus2', 'manage', 'sys', 't_sys_', NULL, 1, 0, '', '18,19', 'id,create_user,update_user,create_time,update_time,deleted,version', 'time,desc,name,key,value,mysql,info,form,sort,icon,like,unlock,unLock,comment,disable,force,describe,host,port,from,to');

-- ----------------------------
-- Table structure for t_gc_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_gc_menu`;
CREATE TABLE `t_gc_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '指定父id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
  `two_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第二路由 (前后端分离前端使用第二路由)',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `root` int(1) NOT NULL DEFAULT 0 COMMENT '目录级别(1-系统, 2-菜单 ，3-页面, 4-按钮)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--gc--自关联测试表 (模拟菜单)' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_gc_menu
-- ----------------------------
INSERT INTO `t_gc_menu` VALUES ('1440255471893200897', '-1', '-1', '2021-09-21 18:03:57', '2022-12-08 17:47:59', 0, 0, '0', '系统管理', '', '', 'el-icon-setting', 1, 1, 0);
INSERT INTO `t_gc_menu` VALUES ('1440255602914869250', '-1', '-1', '2021-09-21 18:04:29', '2022-10-30 16:11:41', 0, 0, '1440255471893200897', '系统管理', '', '', 'el-icon-setting', 100, 2, 0);
INSERT INTO `t_gc_menu` VALUES ('1440256392576483330', '-1', '-1', '2021-09-21 18:07:37', '2022-12-08 17:47:39', 0, 0, '0', '测试 (test)', '', '', 'layui-icon-file-b', 2, 1, 0);
INSERT INTO `t_gc_menu` VALUES ('1440256481906769922', '-1', '-1', '2021-09-21 18:07:58', '2022-10-30 16:11:42', 0, 0, '1440256392576483330', '功能测试', '', '', 'layui-icon-file-b', 200, 2, 0);
INSERT INTO `t_gc_menu` VALUES ('1440271162033684482', '-1', '-1', '2021-09-21 19:06:18', '2022-10-30 16:11:42', 0, 0, '1440255602914869250', '用户管理', '', '/views/admin/user/user', 'el-icon-document-remove', 10004, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1442156484086480897', '-1', '-1', '2021-09-26 23:57:54', '2022-10-30 16:11:42', 0, 0, '1440255602914869250', '角色管理', '', '/views/admin/role/role', 'el-icon-document-remove', 10005, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1449698334917865473', '-1', '-1', '2021-10-17 19:26:30', '2022-10-30 16:11:42', 0, 0, '1449698274373087233', '页面-5级', NULL, '', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1449764190750285826', '-1', '-1', '2021-10-17 23:48:11', '2022-10-30 16:11:43', 0, 0, '1440255471893200897', '增强功能', '', '', 'el-icon-copy-document', 200, 2, 0);
INSERT INTO `t_gc_menu` VALUES ('1450796419538522114', '-1', '-1', '2021-10-20 20:09:55', '2022-10-30 16:11:43', 0, 0, '1440256481906769922', '测试页1', NULL, '/test1', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1451979503835369474', '-1', '-1', '2021-10-24 02:31:02', '2022-10-30 16:11:43', 0, 0, '1449764190750285826', 'Banner管理', '', '/views/admin/banner/banner', 'el-icon-document-remove', 20003, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1452091447254749186', '-1', '-1', '2021-10-24 09:55:54', '2022-10-30 16:11:43', 0, 0, '1440255471893200897', '代码生成', NULL, '', 'el-icon-edit', 0, 2, 0);
INSERT INTO `t_gc_menu` VALUES ('1452091513113710594', '-1', '-1', '2021-10-24 09:56:10', '2022-10-30 16:11:43', 0, 0, '1452091447254749186', '数据表', NULL, '/views/gc/codeGeneration/codeGeneration', 'el-icon-document-remove', 2, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1456054437146644481', '-1', '-1', '2021-11-04 08:23:24', '2022-10-30 16:11:44', 0, 0, '1452091447254749186', '生成的代码测试页', NULL, '/views/test/gcTest/gcTest', 'el-icon-document-remove', 3, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1457369967249879042', '-1', '-1', '2021-11-07 23:30:51', '2022-10-30 16:11:44', 0, 0, '0', '商家端', NULL, NULL, 'el-icon-document-remove', 3, 1, 0);
INSERT INTO `t_gc_menu` VALUES ('1457370029065531394', '-1', '-1', '2021-11-07 23:31:06', '2022-10-30 16:11:44', 0, 0, '1457369967249879042', '系统管理', NULL, NULL, 'el-icon-document-remove', 0, 2, 0);
INSERT INTO `t_gc_menu` VALUES ('1457370075530031105', '-1', '-1', '2021-11-07 23:31:17', '2022-10-30 16:11:44', 0, 0, '1457370029065531394', '角色管理', NULL, '/views/admin/role/role', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1457372083897004033', '-1', '-1', '2021-11-07 23:39:16', '2022-10-30 16:11:45', 0, 0, '1457370029065531394', '员工管理', NULL, '/views/admin/user/user', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1459712656557576194', '-1', '-1', '2021-11-14 10:39:51', '2022-10-30 16:11:45', 0, 0, '1440255602914869250', '部门管理', NULL, '/views/admin/dep/dep', 'el-icon-document-remove', 10006, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1459850402525622274', '-1', '-1', '2021-11-14 19:47:12', '2022-10-30 16:11:45', 0, 0, '1449764190750285826', '全局配置', NULL, '/views/admin/config/config', 'el-icon-document-remove', 20001, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1461987433667141634', '-1', '-1', '2021-11-20 17:19:01', '2022-10-30 16:11:45', 0, 0, '1440255471893200897', '首页', NULL, '/wel/jvmInfo', 'el-icon-menu', 0, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1462256665587916801', '-1', '-1', '2021-11-21 11:08:51', '2022-10-30 16:11:46', 0, 0, '1449764190750285826', '黑/白名单', NULL, '/views/admin/blacklist/blacklist', 'el-icon-document-remove', 10007, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1462261436877152258', '-1', '-1', '2021-11-21 11:27:48', '2022-10-30 16:11:46', 0, 0, '1449764190750285826', '请求日志', NULL, '/views/admin/log/log', 'el-icon-document-remove', 20005, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1468415037767946242', '-1', '-1', '2021-12-08 11:00:01', '2022-10-30 16:11:46', 0, 0, '1449764190750285826', '消息管理', NULL, '/views/admin/msg/msg', 'el-icon-s-comment', 20004, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1481642095692222465', '-1', '-1', '2022-01-13 22:59:36', '2022-10-30 16:11:46', 0, 0, '1440256481906769922', '测试页2', NULL, '/test2', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1482970741228965889', '-1', '-1', '2022-01-17 14:59:11', '2022-10-30 16:11:46', 0, 0, '1457369967249879042', '业务管理', NULL, NULL, 'el-icon-document-remove', 1, 2, 0);
INSERT INTO `t_gc_menu` VALUES ('1482970818676789249', '-1', '-1', '2022-01-17 14:59:30', '2022-10-30 16:11:47', 0, 0, '1482970741228965889', '业务功能1', NULL, '/yw1', 'el-icon-document-remove', 1, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1482970868152799234', '-1', '-1', '2022-01-17 14:59:41', '2022-12-28 20:25:24', 0, 0, '1482970741228965889', '业务功能2', NULL, '/yw2', 'el-icon-document-remove', 2, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1482970903854714882', '-1', '-1', '2022-01-17 14:59:50', '2022-12-28 20:25:19', 0, 0, '1482970741228965889', '业务功能3', '-', '/yw3', 'el-icon-document-remove', 3, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1482979903354703874', '-1', '-1', '2022-01-17 15:35:36', '2022-10-30 16:11:47', 0, 0, '1457370029065531394', '菜单管理', NULL, '/views/admin/menu/menu', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1516699625820524545', '-1', '-1', '2022-04-20 16:45:44', '2022-10-30 16:11:47', 0, 0, '1440255602914869250', '菜单管理', NULL, '/views/admin/menuv2/menu', 'el-icon-document-remove', 10003, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1516699798743289857', '-1', '-1', '2022-04-20 16:46:25', '2022-10-30 16:11:48', 0, 0, '1440255602914869250', '字典管理', NULL, '/views/admin/dictionaryv2/dictionary', 'el-icon-document-remove', 10002, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1516699922710138882', '-1', '-1', '2022-04-20 16:46:55', '2022-10-30 16:11:48', 0, 0, '1440255602914869250', '接口管理', NULL, '/views/admin/authorityv2/authority', 'el-icon-document-remove', 10001, 3, 0);
INSERT INTO `t_gc_menu` VALUES ('1552998827391221761', '-1', '-1', '2022-07-29 20:45:45', '2022-10-30 16:11:48', 0, 0, '1452091447254749186', '数据源', NULL, '/views/gc/db/datasource', 'el-icon-document-remove', 1, 3, 1);
INSERT INTO `t_gc_menu` VALUES ('1607943038670237697', '1', NULL, '2022-12-28 11:34:28', '2022-12-28 11:34:45', 0, 0, '1452091447254749186', '自关联表', NULL, '/views/gc/gcMenu/gcMenu', 'el-icon-document-remove', 4, 3, 0);

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
  `head_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单图文件',
  `head_files` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '多图文件 (多图-默认限制10张图片, 字符串分割存储)',
  `video_files` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频文件 (视频-默认多上传)',
  `files` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任意文件 (任意文件上传-默认多上传)',
  `time` datetime NULL DEFAULT NULL COMMENT '时间 (默认 yyyy-MM-dd HH:mm:ss 格式)',
  `time_two` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时间-小时 (默认 hh:mm 字串)',
  `text` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更多信息-text (大文本)',
  `text_two` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '更多信息-fwb (富文本)',
  `text_three` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '更多信息-md (md编辑器)',
  `cascader` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '级联选择器  (字符串分割存储)',
  `array` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数组框 (字符串分割存储)',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标 ',
  `color` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '颜色选择器',
  `map` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址选择器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--gc--代码生成测试表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_gc_test
-- ----------------------------
INSERT INTO `t_gc_test` VALUES ('1568185006784897026', '-1', '-1', '2022-09-09 18:30:16', '2022-10-30 16:10:16', 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '#C71585', '120.11669741258027,30.28989139415141,浙江省杭州市西湖区古荡街道天堂软件园南区');
INSERT INTO `t_gc_test` VALUES ('1568185740393791489', '-1', '-1', '2022-09-09 18:33:11', '2023-02-03 11:51:19', 0, 0, '小二', 25.00, 1, '2,3', 2, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/img/54265860-20.jpg', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/img/58930081-timg(3).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/img/58917931-timg(4).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/img/09284264-1.jpg', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/video/09100766-女主-被医生按倒-不要!-18.08s.mp4', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/all/07038710-timg(8).jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/all/07038917-timg(9).jpg', '2022-09-09 22:32:28', '07:00', '啦啦啦', '<p>啦啦啦</p>', '啦啦啦', '1,2,3', '1,2', 'el-icon-s-promotion', '#5C4740', '120.1678525029123,30.28485154849538,浙江省杭州市拱墅区朝晖街道浙江省人民医院浙江省人民医院(朝晖院区)');

-- ----------------------------
-- Table structure for t_sys_address
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_address`;
CREATE TABLE `t_sys_address`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '区划信息id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父级挂接id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '区划名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区划编码',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `level` tinyint(1) NOT NULL COMMENT '级次id 0:省/自治区/直辖市 1:市级 2:县级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3221 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_address
-- ----------------------------
INSERT INTO `t_sys_address` VALUES ('1', NULL, NULL, NULL, '2020-06-09 13:56:05', 0, 0, '0', '北京', '110000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('10', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '通州区', '110112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('100', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '武安市', '130481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1000', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '莲都区', '331102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1001', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '青田县', '331121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1002', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '缙云县', '331122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1003', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '遂昌县', '331123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1004', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '松阳县', '331124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1005', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '云和县', '331125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1006', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '庆元县', '331126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1007', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '景宁畲族自治县', '331127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1008', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '999', '龙泉市', '331181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1009', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '安徽省', '340000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('101', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '邢台市', '130500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1010', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '合肥市', '340100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1011', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '瑶海区', '340102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1012', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '庐阳区', '340103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1013', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '蜀山区', '340104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1014', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '包河区', '340111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1015', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '长丰县', '340121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1016', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '肥东县', '340122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1017', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '肥西县', '340123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1018', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '庐江县', '340124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1019', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1010', '巢湖市', '340181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('102', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '桥东区', '130502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1020', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '芜湖市', '340200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1021', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1020', '镜湖区', '340202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1022', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1020', '弋江区', '340203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1023', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1020', '鸠江区', '340207', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1024', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1020', '三山区', '340208', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1025', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1020', '芜湖县', '340221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1026', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1020', '繁昌县', '340222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1027', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1020', '南陵县', '340223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1028', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1020', '无为市', '340281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1029', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '蚌埠市', '340300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('103', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '桥西区', '130503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1030', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1029', '龙子湖区', '340302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1031', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1029', '蚌山区', '340303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1032', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1029', '禹会区', '340304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1033', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1029', '淮上区', '340311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1034', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1029', '怀远县', '340321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1035', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1029', '五河县', '340322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1036', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1029', '固镇县', '340323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1037', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '淮南市', '340400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1038', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1037', '大通区', '340402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1039', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1037', '田家庵区', '340403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('104', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '邢台县', '130521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1040', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1037', '谢家集区', '340404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1041', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1037', '八公山区', '340405', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1042', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1037', '潘集区', '340406', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1043', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1037', '凤台县', '340421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1044', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1037', '寿县', '340422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1045', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '马鞍山市', '340500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1046', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1045', '花山区', '340503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1047', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1045', '雨山区', '340504', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1048', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1045', '博望区', '340506', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1049', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1045', '当涂县', '340521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('105', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '临城县', '130522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1050', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1045', '含山县', '340522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1051', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1045', '和县', '340523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1052', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '淮北市', '340600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1053', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1052', '杜集区', '340602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1054', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1052', '相山区', '340603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1055', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1052', '烈山区', '340604', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1056', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1052', '濉溪县', '340621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1057', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '铜陵市', '340700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1058', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1057', '铜官区', '340705', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1059', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1057', '义安区', '340706', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('106', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '内丘县', '130523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1060', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1057', '郊区', '340711', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1061', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1057', '枞阳县', '340722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1062', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '安庆市', '340800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1063', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '迎江区', '340802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1064', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '大观区', '340803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1065', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '宜秀区', '340811', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1066', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '怀宁县', '340822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1067', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '太湖县', '340825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1068', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '宿松县', '340826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1069', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '望江县', '340827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('107', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '柏乡县', '130524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1070', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '岳西县', '340828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1071', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '桐城市', '340881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1072', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1062', '潜山市', '340882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1073', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '黄山市', '341000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1074', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1073', '屯溪区', '341002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1075', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1073', '黄山区', '341003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1076', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1073', '徽州区', '341004', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1077', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1073', '歙县', '341021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1078', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1073', '休宁县', '341022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1079', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1073', '黟县', '341023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('108', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '隆尧县', '130525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1080', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1073', '祁门县', '341024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1081', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '滁州市', '341100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1082', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1081', '琅琊区', '341102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1083', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1081', '南谯区', '341103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1084', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1081', '来安县', '341122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1085', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1081', '全椒县', '341124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1086', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1081', '定远县', '341125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1087', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1081', '凤阳县', '341126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1088', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1081', '天长市', '341181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1089', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1081', '明光市', '341182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('109', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '任县', '130526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1090', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '阜阳市', '341200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1091', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1090', '颍州区', '341202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1092', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1090', '颍东区', '341203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1093', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1090', '颍泉区', '341204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1094', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1090', '临泉县', '341221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1095', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1090', '太和县', '341222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1096', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1090', '阜南县', '341225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1097', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1090', '颍上县', '341226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1098', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1090', '界首市', '341282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1099', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '宿州市', '341300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('11', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '顺义区', '110113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('110', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '南和县', '130527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1100', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1099', '埇桥区', '341302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1101', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1099', '砀山县', '341321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1102', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1099', '萧县', '341322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1103', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1099', '灵璧县', '341323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1104', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1099', '泗县', '341324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1105', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '六安市', '341500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1106', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1105', '金安区', '341502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1107', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1105', '裕安区', '341503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1108', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1105', '叶集区', '341504', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1109', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1105', '霍邱县', '341522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('111', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '宁晋县', '130528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1110', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1105', '舒城县', '341523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1111', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1105', '金寨县', '341524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1112', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1105', '霍山县', '341525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1113', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '亳州市', '341600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1114', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1113', '谯城区', '341602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1115', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1113', '涡阳县', '341621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1116', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1113', '蒙城县', '341622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1117', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1113', '利辛县', '341623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1118', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '池州市', '341700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1119', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1118', '贵池区', '341702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('112', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '巨鹿县', '130529', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1120', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1118', '东至县', '341721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1121', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1118', '石台县', '341722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1122', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1118', '青阳县', '341723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1123', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1009', '宣城市', '341800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1124', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1123', '宣州区', '341802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1125', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1123', '郎溪县', '341821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1126', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1123', '泾县', '341823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1127', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1123', '绩溪县', '341824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1128', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1123', '旌德县', '341825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1129', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1123', '宁国市', '341881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('113', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '新河县', '130530', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1130', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1123', '广德市', '341882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1131', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '福建省', '350000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('1132', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '福州市', '350100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1133', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '鼓楼区', '350102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1134', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '台江区', '350103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1135', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '仓山区', '350104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1136', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '马尾区', '350105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1137', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '晋安区', '350111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1138', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '长乐区', '350112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1139', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '闽侯县', '350121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('114', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '广宗县', '130531', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1140', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '连江县', '350122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1141', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '罗源县', '350123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1142', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '闽清县', '350124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1143', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '永泰县', '350125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1144', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '平潭县', '350128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1145', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1132', '福清市', '350181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1146', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '厦门市', '350200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1147', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1146', '思明区', '350203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1148', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1146', '海沧区', '350205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1149', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1146', '湖里区', '350206', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('115', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '平乡县', '130532', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1150', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1146', '集美区', '350211', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1151', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1146', '同安区', '350212', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1152', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1146', '翔安区', '350213', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1153', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '莆田市', '350300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1154', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1153', '城厢区', '350302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1155', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1153', '涵江区', '350303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1156', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1153', '荔城区', '350304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1157', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1153', '秀屿区', '350305', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1158', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1153', '仙游县', '350322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1159', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '三明市', '350400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('116', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '威县', '130533', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1160', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '梅列区', '350402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1161', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '三元区', '350403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1162', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '明溪县', '350421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1163', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '清流县', '350423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1164', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '宁化县', '350424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1165', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '大田县', '350425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1166', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '尤溪县', '350426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1167', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '沙县', '350427', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1168', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '将乐县', '350428', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1169', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '泰宁县', '350429', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('117', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '清河县', '130534', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1170', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '建宁县', '350430', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1171', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1159', '永安市', '350481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1172', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '泉州市', '350500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1173', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '鲤城区', '350502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1174', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '丰泽区', '350503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1175', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '洛江区', '350504', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1176', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '泉港区', '350505', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1177', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '惠安县', '350521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1178', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '安溪县', '350524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1179', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '永春县', '350525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('118', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '临西县', '130535', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1180', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '德化县', '350526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1181', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '金门县', '350527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1182', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '石狮市', '350581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1183', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '晋江市', '350582', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1184', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1172', '南安市', '350583', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1185', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '漳州市', '350600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1186', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '芗城区', '350602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1187', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '龙文区', '350603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1188', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '云霄县', '350622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1189', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '漳浦县', '350623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('119', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '南宫市', '130581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1190', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '诏安县', '350624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1191', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '长泰县', '350625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1192', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '东山县', '350626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1193', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '南靖县', '350627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1194', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '平和县', '350628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1195', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '华安县', '350629', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1196', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1185', '龙海市', '350681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1197', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '南平市', '350700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1198', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '延平区', '350702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1199', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '建阳区', '350703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('12', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '昌平区', '110114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('120', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '101', '沙河市', '130582', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1200', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '顺昌县', '350721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1201', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '浦城县', '350722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1202', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '光泽县', '350723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1203', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '松溪县', '350724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1204', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '政和县', '350725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1205', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '邵武市', '350781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1206', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '武夷山市', '350782', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1207', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1197', '建瓯市', '350783', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1208', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '龙岩市', '350800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1209', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1208', '新罗区', '350802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('121', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '保定市', '130600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1210', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1208', '永定区', '350803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1211', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1208', '长汀县', '350821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1212', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1208', '上杭县', '350823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1213', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1208', '武平县', '350824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1214', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1208', '连城县', '350825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1215', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1208', '漳平市', '350881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1216', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1131', '宁德市', '350900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1217', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '蕉城区', '350902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1218', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '霞浦县', '350921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1219', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '古田县', '350922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('122', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '竞秀区', '130602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1220', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '屏南县', '350923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1221', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '寿宁县', '350924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1222', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '周宁县', '350925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1223', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '柘荣县', '350926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1224', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '福安市', '350981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1225', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1216', '福鼎市', '350982', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1226', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '江西省', '360000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('1227', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '南昌市', '360100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1228', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '东湖区', '360102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1229', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '西湖区', '360103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('123', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '莲池区', '130606', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1230', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '青云谱区', '360104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1231', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '青山湖区', '360111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1232', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '新建区', '360112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1233', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '红谷滩区', '360113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1234', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '南昌县', '360121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1235', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '安义县', '360123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1236', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1227', '进贤县', '360124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1237', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '景德镇市', '360200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1238', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1237', '昌江区', '360202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1239', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1237', '珠山区', '360203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('124', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '满城区', '130607', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1240', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1237', '浮梁县', '360222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1241', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1237', '乐平市', '360281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1242', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '萍乡市', '360300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1243', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1242', '安源区', '360302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1244', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1242', '湘东区', '360313', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1245', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1242', '莲花县', '360321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1246', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1242', '上栗县', '360322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1247', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1242', '芦溪县', '360323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1248', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '九江市', '360400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1249', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '濂溪区', '360402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('125', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '清苑区', '130608', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1250', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '浔阳区', '360403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1251', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '柴桑区', '360404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1252', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '武宁县', '360423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1253', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '修水县', '360424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1254', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '永修县', '360425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1255', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '德安县', '360426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1256', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '都昌县', '360428', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1257', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '湖口县', '360429', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1258', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '彭泽县', '360430', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1259', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '瑞昌市', '360481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('126', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '徐水区', '130609', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1260', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '共青城市', '360482', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1261', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1248', '庐山市', '360483', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1262', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '新余市', '360500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1263', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1262', '渝水区', '360502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1264', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1262', '分宜县', '360521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1265', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '鹰潭市', '360600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1266', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1265', '月湖区', '360602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1267', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1265', '余江区', '360603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1268', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1265', '贵溪市', '360681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1269', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '赣州市', '360700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('127', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '涞水县', '130623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1270', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '章贡区', '360702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1271', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '南康区', '360703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1272', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '赣县区', '360704', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1273', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '信丰县', '360722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1274', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '大余县', '360723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1275', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '上犹县', '360724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1276', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '崇义县', '360725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1277', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '安远县', '360726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1278', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '龙南县', '360727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1279', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '定南县', '360728', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('128', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '阜平县', '130624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1280', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '全南县', '360729', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1281', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '宁都县', '360730', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1282', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '于都县', '360731', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1283', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '兴国县', '360732', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1284', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '会昌县', '360733', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1285', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '寻乌县', '360734', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1286', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '石城县', '360735', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1287', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1269', '瑞金市', '360781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1288', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '吉安市', '360800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1289', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '吉州区', '360802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('129', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '定兴县', '130626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1290', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '青原区', '360803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1291', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '吉安县', '360821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1292', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '吉水县', '360822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1293', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '峡江县', '360823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1294', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '新干县', '360824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1295', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '永丰县', '360825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1296', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '泰和县', '360826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1297', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '遂川县', '360827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1298', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '万安县', '360828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1299', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '安福县', '360829', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('13', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '大兴区', '110115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('130', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '唐县', '130627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1300', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '永新县', '360830', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1301', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1288', '井冈山市', '360881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1302', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '宜春市', '360900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1303', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '袁州区', '360902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1304', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '奉新县', '360921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1305', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '万载县', '360922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1306', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '上高县', '360923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1307', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '宜丰县', '360924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1308', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '靖安县', '360925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1309', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '铜鼓县', '360926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('131', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '高阳县', '130628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1310', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '丰城市', '360981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1311', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '樟树市', '360982', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1312', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1302', '高安市', '360983', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1313', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '抚州市', '361000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1314', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '临川区', '361002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1315', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '东乡区', '361003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1316', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '南城县', '361021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1317', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '黎川县', '361022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1318', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '南丰县', '361023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1319', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '崇仁县', '361024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('132', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '容城县', '130629', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1320', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '乐安县', '361025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1321', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '宜黄县', '361026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1322', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '金溪县', '361027', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1323', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '资溪县', '361028', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1324', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1313', '广昌县', '361030', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1325', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1226', '上饶市', '361100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1326', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '信州区', '361102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1327', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '广丰区', '361103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1328', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '广信区', '361104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1329', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '玉山县', '361123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('133', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '涞源县', '130630', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1330', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '铅山县', '361124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1331', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '横峰县', '361125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1332', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '弋阳县', '361126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1333', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '余干县', '361127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1334', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '鄱阳县', '361128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1335', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '万年县', '361129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1336', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '婺源县', '361130', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1337', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1325', '德兴市', '361181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1338', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '山东省', '370000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('1339', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '济南市', '370100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('134', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '望都县', '130631', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1340', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '历下区', '370102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1341', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '市中区', '370103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1342', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '槐荫区', '370104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1343', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '天桥区', '370105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1344', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '历城区', '370112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1345', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '长清区', '370113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1346', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '章丘区', '370114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1347', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '济阳区', '370115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1348', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '莱芜区', '370116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1349', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '钢城区', '370117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('135', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '安新县', '130632', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1350', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '平阴县', '370124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1351', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1339', '商河县', '370126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1352', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '青岛市', '370200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1353', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '市南区', '370202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1354', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '市北区', '370203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1355', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '黄岛区', '370211', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1356', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '崂山区', '370212', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1357', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '李沧区', '370213', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1358', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '城阳区', '370214', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1359', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '即墨区', '370215', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('136', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '易县', '130633', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1360', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '胶州市', '370281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1361', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '平度市', '370283', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1362', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1352', '莱西市', '370285', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1363', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '淄博市', '370300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1364', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1363', '淄川区', '370302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1365', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1363', '张店区', '370303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1366', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1363', '博山区', '370304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1367', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1363', '临淄区', '370305', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1368', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1363', '周村区', '370306', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1369', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1363', '桓台县', '370321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('137', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '曲阳县', '130634', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1370', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1363', '高青县', '370322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1371', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1363', '沂源县', '370323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1372', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '枣庄市', '370400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1373', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1372', '市中区', '370402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1374', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1372', '薛城区', '370403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1375', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1372', '峄城区', '370404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1376', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1372', '台儿庄区', '370405', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1377', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1372', '山亭区', '370406', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1378', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1372', '滕州市', '370481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1379', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '东营市', '370500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('138', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '蠡县', '130635', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1380', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1379', '东营区', '370502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1381', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1379', '河口区', '370503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1382', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1379', '垦利区', '370505', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1383', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1379', '利津县', '370522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1384', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1379', '广饶县', '370523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1385', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '烟台市', '370600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1386', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '芝罘区', '370602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1387', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '福山区', '370611', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1388', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '牟平区', '370612', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1389', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '莱山区', '370613', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('139', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '顺平县', '130636', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1390', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '长岛县', '370634', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1391', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '龙口市', '370681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1392', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '莱阳市', '370682', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1393', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '莱州市', '370683', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1394', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '蓬莱市', '370684', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1395', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '招远市', '370685', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1396', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '栖霞市', '370686', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1397', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1385', '海阳市', '370687', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1398', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '潍坊市', '370700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1399', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '潍城区', '370702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('14', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '怀柔区', '110116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('140', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '博野县', '130637', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1400', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '寒亭区', '370703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1401', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '坊子区', '370704', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1402', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '奎文区', '370705', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1403', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '临朐县', '370724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1404', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '昌乐县', '370725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1405', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '青州市', '370781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1406', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '诸城市', '370782', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1407', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '寿光市', '370783', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1408', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '安丘市', '370784', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1409', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '高密市', '370785', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('141', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '雄县', '130638', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1410', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1398', '昌邑市', '370786', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1411', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '济宁市', '370800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1412', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '任城区', '370811', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1413', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '兖州区', '370812', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1414', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '微山县', '370826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1415', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '鱼台县', '370827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1416', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '金乡县', '370828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1417', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '嘉祥县', '370829', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1418', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '汶上县', '370830', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1419', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '泗水县', '370831', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('142', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '涿州市', '130681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1420', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '梁山县', '370832', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1421', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '曲阜市', '370881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1422', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1411', '邹城市', '370883', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1423', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '泰安市', '370900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1424', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1423', '泰山区', '370902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1425', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1423', '岱岳区', '370911', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1426', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1423', '宁阳县', '370921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1427', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1423', '东平县', '370923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1428', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1423', '新泰市', '370982', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1429', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1423', '肥城市', '370983', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('143', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '定州市', '130682', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1430', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '威海市', '371000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1431', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1430', '环翠区', '371002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1432', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1430', '文登区', '371003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1433', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1430', '荣成市', '371082', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1434', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1430', '乳山市', '371083', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1435', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '日照市', '371100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1436', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1435', '东港区', '371102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1437', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1435', '岚山区', '371103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1438', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1435', '五莲县', '371121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1439', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1435', '莒县', '371122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('144', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '安国市', '130683', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1440', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '临沂市', '371300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1441', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '兰山区', '371302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1442', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '罗庄区', '371311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1443', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '河东区', '371312', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1444', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '沂南县', '371321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1445', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '郯城县', '371322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1446', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '沂水县', '371323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1447', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '兰陵县', '371324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1448', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '费县', '371325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1449', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '平邑县', '371326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('145', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '121', '高碑店市', '130684', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1450', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '莒南县', '371327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1451', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '蒙阴县', '371328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1452', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1440', '临沭县', '371329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1453', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '德州市', '371400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1454', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '德城区', '371402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1455', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '陵城区', '371403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1456', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '宁津县', '371422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1457', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '庆云县', '371423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1458', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '临邑县', '371424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1459', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '齐河县', '371425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('146', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '张家口市', '130700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1460', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '平原县', '371426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1461', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '夏津县', '371427', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1462', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '武城县', '371428', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1463', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '乐陵市', '371481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1464', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1453', '禹城市', '371482', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1465', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '聊城市', '371500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1466', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1465', '东昌府区', '371502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1467', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1465', '茌平区', '371503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1468', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1465', '阳谷县', '371521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1469', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1465', '莘县', '371522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('147', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '桥东区', '130702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1470', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1465', '东阿县', '371524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1471', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1465', '冠县', '371525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1472', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1465', '高唐县', '371526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1473', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1465', '临清市', '371581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1474', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '滨州市', '371600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1475', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1474', '滨城区', '371602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1476', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1474', '沾化区', '371603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1477', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1474', '惠民县', '371621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1478', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1474', '阳信县', '371622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1479', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1474', '无棣县', '371623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('148', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '桥西区', '130703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1480', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1474', '博兴县', '371625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1481', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1474', '邹平市', '371681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1482', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1338', '菏泽市', '371700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1483', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '牡丹区', '371702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1484', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '定陶区', '371703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1485', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '曹县', '371721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1486', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '单县', '371722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1487', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '成武县', '371723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1488', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '巨野县', '371724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1489', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '郓城县', '371725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('149', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '宣化区', '130705', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1490', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '鄄城县', '371726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1491', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1482', '东明县', '371728', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1492', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '河南省', '410000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('1493', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '郑州市', '410100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1494', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '中原区', '410102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1495', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '二七区', '410103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1496', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '管城回族区', '410104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1497', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '金水区', '410105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1498', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '上街区', '410106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1499', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '惠济区', '410108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('15', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '平谷区', '110117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('150', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '下花园区', '130706', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1500', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '中牟县', '410122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1501', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '巩义市', '410181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1502', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '荥阳市', '410182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1503', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '新密市', '410183', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1504', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '新郑市', '410184', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1505', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1493', '登封市', '410185', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1506', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '开封市', '410200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1507', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '龙亭区', '410202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1508', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '顺河回族区', '410203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1509', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '鼓楼区', '410204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('151', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '万全区', '130708', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1510', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '禹王台区', '410205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1511', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '祥符区', '410212', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1512', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '杞县', '410221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1513', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '通许县', '410222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1514', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '尉氏县', '410223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1515', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1506', '兰考县', '410225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1516', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '洛阳市', '410300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1517', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '老城区', '410302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1518', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '西工区', '410303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1519', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '瀍河回族区', '410304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('152', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '崇礼区', '130709', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1520', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '涧西区', '410305', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1521', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '吉利区', '410306', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1522', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '洛龙区', '410311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1523', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '孟津县', '410322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1524', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '新安县', '410323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1525', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '栾川县', '410324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1526', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '嵩县', '410325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1527', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '汝阳县', '410326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1528', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '宜阳县', '410327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1529', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '洛宁县', '410328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('153', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '张北县', '130722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1530', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '伊川县', '410329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1531', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1516', '偃师市', '410381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1532', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '平顶山市', '410400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1533', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '新华区', '410402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1534', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '卫东区', '410403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1535', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '石龙区', '410404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1536', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '湛河区', '410411', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1537', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '宝丰县', '410421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1538', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '叶县', '410422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1539', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '鲁山县', '410423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('154', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '康保县', '130723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1540', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '郏县', '410425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1541', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '舞钢市', '410481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1542', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1532', '汝州市', '410482', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1543', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '安阳市', '410500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1544', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '文峰区', '410502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1545', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '北关区', '410503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1546', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '殷都区', '410505', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1547', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '龙安区', '410506', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1548', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '安阳县', '410522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1549', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '汤阴县', '410523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('155', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '沽源县', '130724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1550', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '滑县', '410526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1551', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '内黄县', '410527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1552', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1543', '林州市', '410581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1553', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '鹤壁市', '410600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1554', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1553', '鹤山区', '410602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1555', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1553', '山城区', '410603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1556', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1553', '淇滨区', '410611', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1557', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1553', '浚县', '410621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1558', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1553', '淇县', '410622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1559', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '新乡市', '410700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('156', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '尚义县', '130725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1560', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '红旗区', '410702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1561', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '卫滨区', '410703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1562', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '凤泉区', '410704', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1563', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '牧野区', '410711', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1564', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '新乡县', '410721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1565', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '获嘉县', '410724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1566', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '原阳县', '410725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1567', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '延津县', '410726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1568', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '封丘县', '410727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1569', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '卫辉市', '410781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('157', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '蔚县', '130726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1570', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '辉县市', '410782', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1571', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1559', '长垣市', '410783', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1572', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '焦作市', '410800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1573', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '解放区', '410802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1574', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '中站区', '410803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1575', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '马村区', '410804', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1576', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '山阳区', '410811', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1577', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '修武县', '410821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1578', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '博爱县', '410822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1579', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '武陟县', '410823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('158', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '阳原县', '130727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1580', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '温县', '410825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1581', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '沁阳市', '410882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1582', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1572', '孟州市', '410883', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1583', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '濮阳市', '410900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1584', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1583', '华龙区', '410902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1585', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1583', '清丰县', '410922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1586', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1583', '南乐县', '410923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1587', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1583', '范县', '410926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1588', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1583', '台前县', '410927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1589', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1583', '濮阳县', '410928', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('159', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '怀安县', '130728', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1590', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '许昌市', '411000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1591', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1590', '魏都区', '411002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1592', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1590', '建安区', '411003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1593', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1590', '鄢陵县', '411024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1594', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1590', '襄城县', '411025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1595', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1590', '禹州市', '411081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1596', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1590', '长葛市', '411082', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1597', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '漯河市', '411100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1598', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1597', '源汇区', '411102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1599', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1597', '郾城区', '411103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('16', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '密云区', '110118', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('160', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '怀来县', '130730', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1600', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1597', '召陵区', '411104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1601', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1597', '舞阳县', '411121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1602', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1597', '临颍县', '411122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1603', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '三门峡市', '411200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1604', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1603', '湖滨区', '411202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1605', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1603', '陕州区', '411203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1606', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1603', '渑池县', '411221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1607', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1603', '卢氏县', '411224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1608', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1603', '义马市', '411281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1609', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1603', '灵宝市', '411282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('161', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '涿鹿县', '130731', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1610', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '南阳市', '411300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1611', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '宛城区', '411302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1612', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '卧龙区', '411303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1613', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '南召县', '411321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1614', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '方城县', '411322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1615', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '西峡县', '411323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1616', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '镇平县', '411324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1617', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '内乡县', '411325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1618', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '淅川县', '411326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1619', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '社旗县', '411327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('162', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '146', '赤城县', '130732', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1620', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '唐河县', '411328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1621', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '新野县', '411329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1622', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '桐柏县', '411330', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1623', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1610', '邓州市', '411381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1624', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '商丘市', '411400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1625', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '梁园区', '411402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1626', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '睢阳区', '411403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1627', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '民权县', '411421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1628', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '睢县', '411422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1629', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '宁陵县', '411423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('163', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '承德市', '130800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1630', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '柘城县', '411424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1631', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '虞城县', '411425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1632', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '夏邑县', '411426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1633', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1624', '永城市', '411481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1634', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '信阳市', '411500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1635', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '浉河区', '411502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1636', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '平桥区', '411503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1637', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '罗山县', '411521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1638', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '光山县', '411522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1639', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '新县', '411523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('164', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '双桥区', '130802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1640', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '商城县', '411524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1641', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '固始县', '411525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1642', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '潢川县', '411526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1643', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '淮滨县', '411527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1644', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1634', '息县', '411528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1645', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '周口市', '411600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1646', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '川汇区', '411602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1647', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '淮阳区', '411603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1648', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '扶沟县', '411621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1649', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '西华县', '411622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('165', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '双滦区', '130803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1650', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '商水县', '411623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1651', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '沈丘县', '411624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1652', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '郸城县', '411625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1653', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '太康县', '411627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1654', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '鹿邑县', '411628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1655', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1645', '项城市', '411681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1656', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1492', '驻马店市', '411700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1657', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '驿城区', '411702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1658', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '西平县', '411721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1659', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '上蔡县', '411722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('166', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '鹰手营子矿区', '130804', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1660', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '平舆县', '411723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1661', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '正阳县', '411724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1662', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '确山县', '411725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1663', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '泌阳县', '411726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1664', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '汝南县', '411727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1665', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '遂平县', '411728', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1666', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1656', '新蔡县', '411729', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1667', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '1492', '济源市', '419001', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1668', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '湖北省', '420000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('1669', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '武汉市', '420100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('167', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '承德县', '130821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1670', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '江岸区', '420102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1671', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '江汉区', '420103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1672', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '硚口区', '420104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1673', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '汉阳区', '420105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1674', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '武昌区', '420106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1675', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '青山区', '420107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1676', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '洪山区', '420111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1677', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '东西湖区', '420112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1678', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '汉南区', '420113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1679', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '蔡甸区', '420114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('168', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '兴隆县', '130822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1680', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '江夏区', '420115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1681', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '黄陂区', '420116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1682', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1669', '新洲区', '420117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1683', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '黄石市', '420200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1684', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1683', '黄石港区', '420202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1685', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1683', '西塞山区', '420203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1686', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1683', '下陆区', '420204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1687', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1683', '铁山区', '420205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1688', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1683', '阳新县', '420222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1689', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1683', '大冶市', '420281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('169', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '滦平县', '130824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1690', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '十堰市', '420300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1691', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1690', '茅箭区', '420302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1692', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1690', '张湾区', '420303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1693', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1690', '郧阳区', '420304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1694', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1690', '郧西县', '420322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1695', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1690', '竹山县', '420323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1696', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1690', '竹溪县', '420324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1697', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1690', '房县', '420325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1698', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1690', '丹江口市', '420381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1699', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '宜昌市', '420500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('17', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '延庆区', '110119', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('170', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '隆化县', '130825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1700', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '西陵区', '420502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1701', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '伍家岗区', '420503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1702', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '点军区', '420504', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1703', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '猇亭区', '420505', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1704', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '夷陵区', '420506', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1705', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '远安县', '420525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1706', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '兴山县', '420526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1707', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '秭归县', '420527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1708', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '长阳土家族自治县', '420528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1709', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '五峰土家族自治县', '420529', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('171', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '丰宁满族自治县', '130826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1710', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '宜都市', '420581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1711', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '当阳市', '420582', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1712', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1699', '枝江市', '420583', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1713', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '襄阳市', '420600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1714', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '襄城区', '420602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1715', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '樊城区', '420606', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1716', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '襄州区', '420607', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1717', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '南漳县', '420624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1718', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '谷城县', '420625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1719', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '保康县', '420626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('172', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '宽城满族自治县', '130827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1720', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '老河口市', '420682', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1721', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '枣阳市', '420683', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1722', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1713', '宜城市', '420684', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1723', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '鄂州市', '420700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1724', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1723', '梁子湖区', '420702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1725', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1723', '华容区', '420703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1726', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1723', '鄂城区', '420704', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1727', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '荆门市', '420800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1728', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1727', '东宝区', '420802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1729', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1727', '掇刀区', '420804', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('173', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '围场满族蒙古族自治县', '130828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1730', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1727', '沙洋县', '420822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1731', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1727', '钟祥市', '420881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1732', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1727', '京山市', '420882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1733', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '孝感市', '420900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1734', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1733', '孝南区', '420902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1735', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1733', '孝昌县', '420921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1736', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1733', '大悟县', '420922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1737', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1733', '云梦县', '420923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1738', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1733', '应城市', '420981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1739', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1733', '安陆市', '420982', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('174', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '163', '平泉市', '130881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1740', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1733', '汉川市', '420984', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1741', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '荆州市', '421000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1742', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1741', '沙市区', '421002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1743', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1741', '荆州区', '421003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1744', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1741', '公安县', '421022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1745', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1741', '监利县', '421023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1746', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1741', '江陵县', '421024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1747', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1741', '石首市', '421081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1748', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1741', '洪湖市', '421083', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1749', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1741', '松滋市', '421087', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('175', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '沧州市', '130900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1750', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '黄冈市', '421100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1751', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '黄州区', '421102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1752', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '团风县', '421121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1753', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '红安县', '421122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1754', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '罗田县', '421123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1755', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '英山县', '421124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1756', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '浠水县', '421125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1757', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '蕲春县', '421126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1758', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '黄梅县', '421127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1759', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '麻城市', '421181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('176', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '新华区', '130902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1760', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1750', '武穴市', '421182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1761', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '咸宁市', '421200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1762', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1761', '咸安区', '421202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1763', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1761', '嘉鱼县', '421221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1764', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1761', '通城县', '421222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1765', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1761', '崇阳县', '421223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1766', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1761', '通山县', '421224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1767', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1761', '赤壁市', '421281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1768', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '随州市', '421300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1769', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1768', '曾都区', '421303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('177', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '运河区', '130903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1770', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1768', '随县', '421321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1771', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1768', '广水市', '421381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1772', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1668', '恩施土家族苗族自治州', '422800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1773', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1772', '恩施市', '422801', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1774', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1772', '利川市', '422802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1775', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1772', '建始县', '422822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1776', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1772', '巴东县', '422823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1777', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1772', '宣恩县', '422825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1778', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1772', '咸丰县', '422826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1779', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1772', '来凤县', '422827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('178', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '沧县', '130921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1780', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1772', '鹤峰县', '422828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1781', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '1668', '仙桃市', '429004', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1782', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '1668', '潜江市', '429005', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1783', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '1668', '天门市', '429006', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1784', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '1668', '神农架林区', '429021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1785', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '湖南省', '430000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('1786', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '长沙市', '430100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1787', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '芙蓉区', '430102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1788', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '天心区', '430103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1789', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '岳麓区', '430104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('179', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '青县', '130922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1790', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '开福区', '430105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1791', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '雨花区', '430111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1792', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '望城区', '430112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1793', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '长沙县', '430121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1794', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '浏阳市', '430181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1795', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1786', '宁乡市', '430182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1796', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '株洲市', '430200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1797', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '荷塘区', '430202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1798', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '芦淞区', '430203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1799', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '石峰区', '430204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('18', NULL, NULL, NULL, '2020-06-09 14:04:54', 0, 0, '0', '天津', '120000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('180', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '东光县', '130923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1800', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '天元区', '430211', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1801', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '渌口区', '430212', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1802', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '攸县', '430223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1803', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '茶陵县', '430224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1804', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '炎陵县', '430225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1805', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1796', '醴陵市', '430281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1806', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '湘潭市', '430300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1807', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1806', '雨湖区', '430302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1808', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1806', '岳塘区', '430304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1809', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1806', '湘潭县', '430321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('181', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '海兴县', '130924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1810', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1806', '湘乡市', '430381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1811', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1806', '韶山市', '430382', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1812', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '衡阳市', '430400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1813', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '珠晖区', '430405', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1814', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '雁峰区', '430406', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1815', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '石鼓区', '430407', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1816', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '蒸湘区', '430408', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1817', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '南岳区', '430412', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1818', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '衡阳县', '430421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1819', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '衡南县', '430422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('182', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '盐山县', '130925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1820', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '衡山县', '430423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1821', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '衡东县', '430424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1822', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '祁东县', '430426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1823', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '耒阳市', '430481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1824', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1812', '常宁市', '430482', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1825', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '邵阳市', '430500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1826', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '双清区', '430502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1827', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '大祥区', '430503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1828', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '北塔区', '430511', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1829', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '新邵县', '430522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('183', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '肃宁县', '130926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1830', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '邵阳县', '430523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1831', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '隆回县', '430524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1832', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '洞口县', '430525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1833', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '绥宁县', '430527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1834', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '新宁县', '430528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1835', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '城步苗族自治县', '430529', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1836', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '武冈市', '430581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1837', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1825', '邵东市', '430582', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1838', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '岳阳市', '430600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1839', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '岳阳楼区', '430602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('184', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '南皮县', '130927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1840', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '云溪区', '430603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1841', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '君山区', '430611', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1842', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '岳阳县', '430621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1843', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '华容县', '430623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1844', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '湘阴县', '430624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1845', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '平江县', '430626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1846', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '汨罗市', '430681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1847', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1838', '临湘市', '430682', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1848', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '常德市', '430700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1849', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '武陵区', '430702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('185', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '吴桥县', '130928', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1850', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '鼎城区', '430703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1851', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '安乡县', '430721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1852', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '汉寿县', '430722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1853', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '澧县', '430723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1854', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '临澧县', '430724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1855', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '桃源县', '430725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1856', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '石门县', '430726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1857', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1848', '津市市', '430781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1858', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '张家界市', '430800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1859', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1858', '永定区', '430802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('186', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '献县', '130929', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1860', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1858', '武陵源区', '430811', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1861', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1858', '慈利县', '430821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1862', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1858', '桑植县', '430822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1863', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '益阳市', '430900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1864', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1863', '资阳区', '430902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1865', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1863', '赫山区', '430903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1866', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1863', '南县', '430921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1867', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1863', '桃江县', '430922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1868', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1863', '安化县', '430923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1869', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1863', '沅江市', '430981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('187', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '孟村回族自治县', '130930', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1870', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '郴州市', '431000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1871', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '北湖区', '431002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1872', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '苏仙区', '431003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1873', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '桂阳县', '431021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1874', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '宜章县', '431022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1875', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '永兴县', '431023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1876', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '嘉禾县', '431024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1877', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '临武县', '431025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1878', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '汝城县', '431026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1879', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '桂东县', '431027', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('188', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '泊头市', '130981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1880', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '安仁县', '431028', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1881', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1870', '资兴市', '431081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1882', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '永州市', '431100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1883', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '零陵区', '431102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1884', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '冷水滩区', '431103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1885', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '祁阳县', '431121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1886', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '东安县', '431122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1887', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '双牌县', '431123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1888', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '道县', '431124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1889', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '江永县', '431125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('189', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '任丘市', '130982', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1890', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '宁远县', '431126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1891', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '蓝山县', '431127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1892', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '新田县', '431128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1893', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1882', '江华瑶族自治县', '431129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1894', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '怀化市', '431200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1895', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '鹤城区', '431202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1896', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '中方县', '431221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1897', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '沅陵县', '431222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1898', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '辰溪县', '431223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1899', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '溆浦县', '431224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('19', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '和平区', '120101', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('190', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '黄骅市', '130983', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1900', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '会同县', '431225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1901', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '麻阳苗族自治县', '431226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1902', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '新晃侗族自治县', '431227', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1903', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '芷江侗族自治县', '431228', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1904', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '靖州苗族侗族自治县', '431229', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1905', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '通道侗族自治县', '431230', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1906', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1894', '洪江市', '431281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1907', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '娄底市', '431300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1908', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1907', '娄星区', '431302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1909', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1907', '双峰县', '431321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('191', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '175', '河间市', '130984', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1910', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1907', '新化县', '431322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1911', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1907', '冷水江市', '431381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1912', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1907', '涟源市', '431382', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1913', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1785', '湘西土家族苗族自治州', '433100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1914', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1913', '吉首市', '433101', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1915', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1913', '泸溪县', '433122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1916', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1913', '凤凰县', '433123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1917', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1913', '花垣县', '433124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1918', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1913', '保靖县', '433125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1919', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1913', '古丈县', '433126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('192', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '廊坊市', '131000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1920', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1913', '永顺县', '433127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1921', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1913', '龙山县', '433130', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1922', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '广东省', '440000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('1923', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '广州市', '440100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1924', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '荔湾区', '440103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1925', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '越秀区', '440104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1926', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '海珠区', '440105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1927', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '天河区', '440106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1928', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '白云区', '440111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1929', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '黄埔区', '440112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('193', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '安次区', '131002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1930', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '番禺区', '440113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1931', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '花都区', '440114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1932', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '南沙区', '440115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1933', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '从化区', '440117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1934', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1923', '增城区', '440118', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1935', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '韶关市', '440200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1936', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '武江区', '440203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1937', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '浈江区', '440204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1938', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '曲江区', '440205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1939', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '始兴县', '440222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('194', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '广阳区', '131003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1940', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '仁化县', '440224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1941', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '翁源县', '440229', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1942', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '乳源瑶族自治县', '440232', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1943', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '新丰县', '440233', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1944', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '乐昌市', '440281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1945', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1935', '南雄市', '440282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1946', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '深圳市', '440300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1947', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '罗湖区', '440303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1948', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '福田区', '440304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1949', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '南山区', '440305', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('195', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '固安县', '131022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1950', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '宝安区', '440306', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1951', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '龙岗区', '440307', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1952', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '盐田区', '440308', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1953', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '龙华区', '440309', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1954', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '坪山区', '440310', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1955', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1946', '光明区', '440311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1956', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '珠海市', '440400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1957', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1956', '香洲区', '440402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1958', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1956', '斗门区', '440403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1959', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1956', '金湾区', '440404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('196', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '永清县', '131023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1960', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '汕头市', '440500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1961', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1960', '龙湖区', '440507', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1962', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1960', '金平区', '440511', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1963', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1960', '濠江区', '440512', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1964', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1960', '潮阳区', '440513', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1965', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1960', '潮南区', '440514', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1966', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1960', '澄海区', '440515', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1967', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1960', '南澳县', '440523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1968', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '佛山市', '440600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1969', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1968', '禅城区', '440604', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('197', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '香河县', '131024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1970', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1968', '南海区', '440605', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1971', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1968', '顺德区', '440606', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1972', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1968', '三水区', '440607', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1973', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1968', '高明区', '440608', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1974', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '江门市', '440700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1975', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1974', '蓬江区', '440703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1976', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1974', '江海区', '440704', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1977', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1974', '新会区', '440705', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1978', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1974', '台山市', '440781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1979', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1974', '开平市', '440783', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('198', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '大城县', '131025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1980', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1974', '鹤山市', '440784', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1981', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1974', '恩平市', '440785', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1982', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '湛江市', '440800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1983', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '赤坎区', '440802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1984', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '霞山区', '440803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1985', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '坡头区', '440804', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1986', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '麻章区', '440811', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1987', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '遂溪县', '440823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1988', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '徐闻县', '440825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1989', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '廉江市', '440881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('199', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '文安县', '131026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1990', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '雷州市', '440882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1991', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1982', '吴川市', '440883', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1992', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '茂名市', '440900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1993', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1992', '茂南区', '440902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1994', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1992', '电白区', '440904', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1995', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1992', '高州市', '440981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1996', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1992', '化州市', '440982', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1997', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1992', '信宜市', '440983', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('1998', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '肇庆市', '441200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('1999', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1998', '端州区', '441202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '东城区', '110101', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('20', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '河东区', '120102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('200', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '大厂回族自治县', '131028', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2000', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1998', '鼎湖区', '441203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2001', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1998', '高要区', '441204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2002', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1998', '广宁县', '441223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2003', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1998', '怀集县', '441224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2004', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1998', '封开县', '441225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2005', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1998', '德庆县', '441226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2006', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '1998', '四会市', '441284', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2007', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '惠州市', '441300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2008', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2007', '惠城区', '441302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2009', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2007', '惠阳区', '441303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('201', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '霸州市', '131081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2010', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2007', '博罗县', '441322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2011', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2007', '惠东县', '441323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2012', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2007', '龙门县', '441324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2013', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '梅州市', '441400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2014', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2013', '梅江区', '441402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2015', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2013', '梅县区', '441403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2016', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2013', '大埔县', '441422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2017', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2013', '丰顺县', '441423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2018', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2013', '五华县', '441424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2019', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2013', '平远县', '441426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('202', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '192', '三河市', '131082', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2020', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2013', '蕉岭县', '441427', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2021', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2013', '兴宁市', '441481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2022', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '汕尾市', '441500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2023', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2022', '城区', '441502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2024', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2022', '海丰县', '441521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2025', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2022', '陆河县', '441523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2026', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2022', '陆丰市', '441581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2027', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '河源市', '441600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2028', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2027', '源城区', '441602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2029', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2027', '紫金县', '441621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('203', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '衡水市', '131100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2030', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2027', '龙川县', '441622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2031', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2027', '连平县', '441623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2032', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2027', '和平县', '441624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2033', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2027', '东源县', '441625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2034', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '阳江市', '441700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2035', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2034', '江城区', '441702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2036', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2034', '阳东区', '441704', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2037', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2034', '阳西县', '441721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2038', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2034', '阳春市', '441781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2039', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '清远市', '441800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('204', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '桃城区', '131102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2040', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2039', '清城区', '441802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2041', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2039', '清新区', '441803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2042', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2039', '佛冈县', '441821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2043', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2039', '阳山县', '441823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2044', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2039', '连山壮族瑶族自治县', '441825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2045', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2039', '连南瑶族自治县', '441826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2046', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2039', '英德市', '441881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2047', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2039', '连州市', '441882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2048', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '东莞市', '441900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2049', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '中山市', '442000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('205', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '冀州区', '131103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2050', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '潮州市', '445100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2051', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2050', '湘桥区', '445102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2052', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2050', '潮安区', '445103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2053', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2050', '饶平县', '445122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2054', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '揭阳市', '445200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2055', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2054', '榕城区', '445202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2056', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2054', '揭东区', '445203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2057', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2054', '揭西县', '445222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2058', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2054', '惠来县', '445224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2059', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2054', '普宁市', '445281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('206', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '枣强县', '131121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2060', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '1922', '云浮市', '445300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2061', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2060', '云城区', '445302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2062', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2060', '云安区', '445303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2063', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2060', '新兴县', '445321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2064', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2060', '郁南县', '445322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2065', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2060', '罗定市', '445381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2066', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '广西壮族自治区', '450000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2067', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '南宁市', '450100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2068', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '兴宁区', '450102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2069', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '青秀区', '450103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('207', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '武邑县', '131122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2070', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '江南区', '450105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2071', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '西乡塘区', '450107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2072', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '良庆区', '450108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2073', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '邕宁区', '450109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2074', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '武鸣区', '450110', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2075', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '隆安县', '450123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2076', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '马山县', '450124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2077', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '上林县', '450125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2078', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '宾阳县', '450126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2079', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2067', '横县', '450127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('208', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '武强县', '131123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2080', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '柳州市', '450200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2081', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '城中区', '450202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2082', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '鱼峰区', '450203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2083', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '柳南区', '450204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2084', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '柳北区', '450205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2085', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '柳江区', '450206', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2086', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '柳城县', '450222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2087', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '鹿寨县', '450223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2088', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '融安县', '450224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2089', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '融水苗族自治县', '450225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('209', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '饶阳县', '131124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2090', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2080', '三江侗族自治县', '450226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2091', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '桂林市', '450300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2092', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '秀峰区', '450302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2093', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '叠彩区', '450303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2094', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '象山区', '450304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2095', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '七星区', '450305', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2096', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '雁山区', '450311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2097', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '临桂区', '450312', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2098', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '阳朔县', '450321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2099', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '灵川县', '450323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('21', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '河西区', '120103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('210', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '安平县', '131125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2100', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '全州县', '450324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2101', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '兴安县', '450325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2102', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '永福县', '450326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2103', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '灌阳县', '450327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2104', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '龙胜各族自治县', '450328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2105', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '资源县', '450329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2106', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '平乐县', '450330', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2107', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '荔浦市', '450381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2108', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2091', '恭城瑶族自治县', '450332', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2109', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '梧州市', '450400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('211', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '故城县', '131126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2110', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2109', '万秀区', '450403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2111', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2109', '长洲区', '450405', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2112', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2109', '龙圩区', '450406', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2113', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2109', '苍梧县', '450421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2114', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2109', '藤县', '450422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2115', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2109', '蒙山县', '450423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2116', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2109', '岑溪市', '450481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2117', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '北海市', '450500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2118', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2117', '海城区', '450502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2119', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2117', '银海区', '450503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('212', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '景县', '131127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2120', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2117', '铁山港区', '450512', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2121', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2117', '合浦县', '450521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2122', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '防城港市', '450600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2123', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2122', '港口区', '450602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2124', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2122', '防城区', '450603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2125', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2122', '上思县', '450621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2126', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2122', '东兴市', '450681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2127', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '钦州市', '450700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2128', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2127', '钦南区', '450702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2129', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2127', '钦北区', '450703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('213', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '阜城县', '131128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2130', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2127', '灵山县', '450721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2131', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2127', '浦北县', '450722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2132', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '贵港市', '450800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2133', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2132', '港北区', '450802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2134', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2132', '港南区', '450803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2135', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2132', '覃塘区', '450804', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2136', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2132', '平南县', '450821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2137', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2132', '桂平市', '450881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2138', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '玉林市', '450900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2139', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2138', '玉州区', '450902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('214', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '203', '深州市', '131182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2140', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2138', '福绵区', '450903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2141', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2138', '容县', '450921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2142', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2138', '陆川县', '450922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2143', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2138', '博白县', '450923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2144', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2138', '兴业县', '450924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2145', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2138', '北流市', '450981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2146', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '百色市', '451000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2147', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '右江区', '451002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2148', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '田阳区', '451003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2149', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '田东县', '451022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('215', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '山西省', '140000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2150', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '德保县', '451024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2151', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '那坡县', '451026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2152', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '凌云县', '451027', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2153', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '乐业县', '451028', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2154', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '田林县', '451029', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2155', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '西林县', '451030', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2156', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '隆林各族自治县', '451031', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2157', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '靖西市', '451081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2158', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2146', '平果市', '451082', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2159', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '贺州市', '451100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('216', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '太原市', '140100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2160', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2159', '八步区', '451102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2161', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2159', '平桂区', '451103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2162', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2159', '昭平县', '451121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2163', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2159', '钟山县', '451122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2164', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2159', '富川瑶族自治县', '451123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2165', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '河池市', '451200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2166', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '金城江区', '451202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2167', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '宜州区', '451203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2168', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '南丹县', '451221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2169', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '天峨县', '451222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('217', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '小店区', '140105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2170', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '凤山县', '451223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2171', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '东兰县', '451224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2172', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '罗城仫佬族自治县', '451225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2173', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '环江毛南族自治县', '451226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2174', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '巴马瑶族自治县', '451227', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2175', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '都安瑶族自治县', '451228', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2176', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2165', '大化瑶族自治县', '451229', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2177', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '来宾市', '451300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2178', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2177', '兴宾区', '451302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2179', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2177', '忻城县', '451321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('218', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '迎泽区', '140106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2180', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2177', '象州县', '451322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2181', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2177', '武宣县', '451323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2182', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2177', '金秀瑶族自治县', '451324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2183', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2177', '合山市', '451381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2184', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2066', '崇左市', '451400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2185', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2184', '江州区', '451402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2186', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2184', '扶绥县', '451421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2187', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2184', '宁明县', '451422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2188', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2184', '龙州县', '451423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2189', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2184', '大新县', '451424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('219', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '杏花岭区', '140107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2190', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2184', '天等县', '451425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2191', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2184', '凭祥市', '451481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2192', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '海南省', '460000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2193', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2192', '海口市', '460100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2194', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2193', '秀英区', '460105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2195', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2193', '龙华区', '460106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2196', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2193', '琼山区', '460107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2197', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2193', '美兰区', '460108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2198', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2192', '三亚市', '460200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2199', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2198', '海棠区', '460202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('22', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '南开区', '120104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('220', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '尖草坪区', '140108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2200', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2198', '吉阳区', '460203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2201', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2198', '天涯区', '460204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2202', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2198', '崖州区', '460205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2203', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2192', '三沙市', '460300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2204', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2192', '儋州市', '460400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2205', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '五指山市', '469001', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2206', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '琼海市', '469002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2207', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '文昌市', '469005', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2208', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '万宁市', '469006', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2209', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '东方市', '469007', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('221', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '万柏林区', '140109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2210', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '定安县', '469021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2211', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '屯昌县', '469022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2212', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '澄迈县', '469023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2213', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '临高县', '469024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2214', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '白沙黎族自治县', '469025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2215', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '昌江黎族自治县', '469026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2216', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '乐东黎族自治县', '469027', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2217', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '陵水黎族自治县', '469028', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2218', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '保亭黎族苗族自治县', '469029', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2219', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '2192', '琼中黎族苗族自治县', '469030', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('222', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '晋源区', '140110', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2220', NULL, NULL, NULL, '2020-06-09 14:08:26', 0, 0, '0', '重庆', '500000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2221', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '万州区', '500101', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2222', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '涪陵区', '500102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2223', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '渝中区', '500103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2224', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '大渡口区', '500104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2225', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '江北区', '500105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2226', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '沙坪坝区', '500106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2227', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '九龙坡区', '500107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2228', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '南岸区', '500108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2229', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '北碚区', '500109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('223', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '清徐县', '140121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2230', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '綦江区', '500110', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2231', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '大足区', '500111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2232', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '渝北区', '500112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2233', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '巴南区', '500113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2234', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '黔江区', '500114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2235', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '长寿区', '500115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2236', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '江津区', '500116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2237', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '合川区', '500117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2238', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '永川区', '500118', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2239', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '南川区', '500119', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('224', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '阳曲县', '140122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2240', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '璧山区', '500120', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2241', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '铜梁区', '500151', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2242', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '潼南区', '500152', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2243', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '荣昌区', '500153', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2244', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '开州区', '500154', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2245', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '梁平区', '500155', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2246', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '武隆区', '500156', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2247', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '城口县', '500229', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2248', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '丰都县', '500230', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2249', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '垫江县', '500231', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('225', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '娄烦县', '140123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2250', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '忠县', '500233', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2251', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '云阳县', '500235', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2252', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '奉节县', '500236', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2253', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '巫山县', '500237', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2254', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '巫溪县', '500238', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2255', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '石柱土家族自治县', '500240', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2256', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '秀山土家族苗族自治县', '500241', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2257', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '酉阳土家族苗族自治县', '500242', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2258', NULL, NULL, NULL, '2020-06-09 14:09:42', 0, 0, '3219', '彭水苗族土家族自治县', '500243', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2259', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '四川省', '510000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('226', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '216', '古交市', '140181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2260', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '成都市', '510100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2261', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '锦江区', '510104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2262', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '青羊区', '510105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2263', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '金牛区', '510106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2264', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '武侯区', '510107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2265', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '成华区', '510108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2266', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '龙泉驿区', '510112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2267', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '青白江区', '510113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2268', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '新都区', '510114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2269', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '温江区', '510115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('227', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '大同市', '140200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2270', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '双流区', '510116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2271', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '郫都区', '510117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2272', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '金堂县', '510121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2273', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '大邑县', '510129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2274', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '蒲江县', '510131', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2275', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '新津县', '510132', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2276', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '都江堰市', '510181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2277', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '彭州市', '510182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2278', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '邛崃市', '510183', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2279', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '崇州市', '510184', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('228', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '新荣区', '140212', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2280', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2260', '简阳市', '510185', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2281', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '自贡市', '510300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2282', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2281', '自流井区', '510302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2283', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2281', '贡井区', '510303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2284', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2281', '大安区', '510304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2285', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2281', '沿滩区', '510311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2286', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2281', '荣县', '510321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2287', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2281', '富顺县', '510322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2288', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '攀枝花市', '510400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2289', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2288', '东区', '510402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('229', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '平城区', '140213', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2290', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2288', '西区', '510403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2291', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2288', '仁和区', '510411', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2292', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2288', '米易县', '510421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2293', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2288', '盐边县', '510422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2294', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '泸州市', '510500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2295', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2294', '江阳区', '510502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2296', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2294', '纳溪区', '510503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2297', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2294', '龙马潭区', '510504', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2298', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2294', '泸县', '510521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2299', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2294', '合江县', '510522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('23', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '河北区', '120105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('230', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '云冈区', '140214', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2300', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2294', '叙永县', '510524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2301', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2294', '古蔺县', '510525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2302', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '德阳市', '510600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2303', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2302', '旌阳区', '510603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2304', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2302', '罗江区', '510604', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2305', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2302', '中江县', '510623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2306', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2302', '广汉市', '510681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2307', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2302', '什邡市', '510682', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2308', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2302', '绵竹市', '510683', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2309', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '绵阳市', '510700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('231', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '云州区', '140215', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2310', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '涪城区', '510703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2311', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '游仙区', '510704', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2312', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '安州区', '510705', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2313', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '三台县', '510722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2314', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '盐亭县', '510723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2315', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '梓潼县', '510725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2316', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '北川羌族自治县', '510726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2317', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '平武县', '510727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2318', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2309', '江油市', '510781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2319', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '广元市', '510800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('232', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '阳高县', '140221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2320', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2319', '利州区', '510802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2321', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2319', '昭化区', '510811', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2322', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2319', '朝天区', '510812', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2323', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2319', '旺苍县', '510821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2324', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2319', '青川县', '510822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2325', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2319', '剑阁县', '510823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2326', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2319', '苍溪县', '510824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2327', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '遂宁市', '510900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2328', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2327', '船山区', '510903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2329', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2327', '安居区', '510904', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('233', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '天镇县', '140222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2330', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2327', '蓬溪县', '510921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2331', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2327', '大英县', '510923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2332', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2327', '射洪市', '510981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2333', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '内江市', '511000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2334', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2333', '市中区', '511002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2335', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2333', '东兴区', '511011', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2336', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2333', '威远县', '511024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2337', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2333', '资中县', '511025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2338', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2333', '隆昌市', '511083', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2339', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '乐山市', '511100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('234', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '广灵县', '140223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2340', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '市中区', '511102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2341', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '沙湾区', '511111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2342', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '五通桥区', '511112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2343', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '金口河区', '511113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2344', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '犍为县', '511123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2345', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '井研县', '511124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2346', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '夹江县', '511126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2347', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '沐川县', '511129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2348', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '峨边彝族自治县', '511132', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2349', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '马边彝族自治县', '511133', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('235', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '灵丘县', '140224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2350', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2339', '峨眉山市', '511181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2351', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '南充市', '511300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2352', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '顺庆区', '511302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2353', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '高坪区', '511303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2354', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '嘉陵区', '511304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2355', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '南部县', '511321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2356', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '营山县', '511322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2357', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '蓬安县', '511323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2358', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '仪陇县', '511324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2359', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '西充县', '511325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('236', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '浑源县', '140225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2360', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2351', '阆中市', '511381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2361', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '眉山市', '511400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2362', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2361', '东坡区', '511402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2363', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2361', '彭山区', '511403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2364', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2361', '仁寿县', '511421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2365', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2361', '洪雅县', '511423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2366', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2361', '丹棱县', '511424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2367', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2361', '青神县', '511425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2368', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '宜宾市', '511500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2369', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '翠屏区', '511502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('237', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '227', '左云县', '140226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2370', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '南溪区', '511503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2371', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '叙州区', '511504', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2372', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '江安县', '511523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2373', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '长宁县', '511524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2374', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '高县', '511525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2375', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '珙县', '511526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2376', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '筠连县', '511527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2377', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '兴文县', '511528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2378', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2368', '屏山县', '511529', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2379', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '广安市', '511600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('238', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '阳泉市', '140300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2380', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2379', '广安区', '511602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2381', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2379', '前锋区', '511603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2382', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2379', '岳池县', '511621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2383', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2379', '武胜县', '511622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2384', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2379', '邻水县', '511623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2385', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2379', '华蓥市', '511681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2386', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '达州市', '511700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2387', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2386', '通川区', '511702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2388', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2386', '达川区', '511703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2389', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2386', '宣汉县', '511722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('239', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '238', '城区', '140302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2390', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2386', '开江县', '511723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2391', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2386', '大竹县', '511724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2392', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2386', '渠县', '511725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2393', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2386', '万源市', '511781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2394', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '雅安市', '511800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2395', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2394', '雨城区', '511802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2396', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2394', '名山区', '511803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2397', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2394', '荥经县', '511822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2398', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2394', '汉源县', '511823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2399', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2394', '石棉县', '511824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('24', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '红桥区', '120106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('240', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '238', '矿区', '140303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2400', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2394', '天全县', '511825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2401', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2394', '芦山县', '511826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2402', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2394', '宝兴县', '511827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2403', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '巴中市', '511900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2404', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2403', '巴州区', '511902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2405', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2403', '恩阳区', '511903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2406', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2403', '通江县', '511921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2407', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2403', '南江县', '511922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2408', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2403', '平昌县', '511923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2409', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '资阳市', '512000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('241', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '238', '郊区', '140311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2410', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2409', '雁江区', '512002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2411', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2409', '安岳县', '512021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2412', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2409', '乐至县', '512022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2413', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '阿坝藏族羌族自治州', '513200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2414', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '马尔康市', '513201', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2415', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '汶川县', '513221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2416', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '理县', '513222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2417', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '茂县', '513223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2418', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '松潘县', '513224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2419', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '九寨沟县', '513225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('242', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '238', '平定县', '140321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2420', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '金川县', '513226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2421', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '小金县', '513227', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2422', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '黑水县', '513228', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2423', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '壤塘县', '513230', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2424', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '阿坝县', '513231', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2425', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '若尔盖县', '513232', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2426', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2413', '红原县', '513233', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2427', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '甘孜藏族自治州', '513300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2428', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '康定市', '513301', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2429', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '泸定县', '513322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('243', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '238', '盂县', '140322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2430', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '丹巴县', '513323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2431', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '九龙县', '513324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2432', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '雅江县', '513325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2433', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '道孚县', '513326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2434', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '炉霍县', '513327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2435', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '甘孜县', '513328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2436', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '新龙县', '513329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2437', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '德格县', '513330', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2438', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '白玉县', '513331', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2439', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '石渠县', '513332', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('244', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '长治市', '140400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2440', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '色达县', '513333', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2441', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '理塘县', '513334', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2442', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '巴塘县', '513335', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2443', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '乡城县', '513336', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2444', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '稻城县', '513337', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2445', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2427', '得荣县', '513338', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2446', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2259', '凉山彝族自治州', '513400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2447', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '西昌市', '513401', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2448', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '木里藏族自治县', '513422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2449', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '盐源县', '513423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('245', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '潞州区', '140403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2450', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '德昌县', '513424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2451', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '会理县', '513425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2452', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '会东县', '513426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2453', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '宁南县', '513427', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2454', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '普格县', '513428', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2455', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '布拖县', '513429', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2456', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '金阳县', '513430', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2457', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '昭觉县', '513431', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2458', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '喜德县', '513432', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2459', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '冕宁县', '513433', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('246', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '上党区', '140404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2460', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '越西县', '513434', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2461', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '甘洛县', '513435', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2462', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '美姑县', '513436', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2463', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2446', '雷波县', '513437', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2464', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '贵州省', '520000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2465', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '贵阳市', '520100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2466', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '南明区', '520102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2467', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '云岩区', '520103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2468', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '花溪区', '520111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2469', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '乌当区', '520112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('247', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '屯留区', '140405', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2470', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '白云区', '520113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2471', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '观山湖区', '520115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2472', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '开阳县', '520121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2473', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '息烽县', '520122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2474', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '修文县', '520123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2475', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2465', '清镇市', '520181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2476', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '六盘水市', '520200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2477', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2476', '钟山区', '520201', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2478', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2476', '六枝特区', '520203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2479', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2476', '水城县', '520221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('248', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '潞城区', '140406', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2480', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2476', '盘州市', '520281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2481', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '遵义市', '520300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2482', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '红花岗区', '520302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2483', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '汇川区', '520303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2484', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '播州区', '520304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2485', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '桐梓县', '520322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2486', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '绥阳县', '520323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2487', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '正安县', '520324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2488', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '道真仡佬族苗族自治县', '520325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2489', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '务川仡佬族苗族自治县', '520326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('249', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '襄垣县', '140423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2490', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '凤冈县', '520327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2491', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '湄潭县', '520328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2492', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '余庆县', '520329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2493', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '习水县', '520330', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2494', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '赤水市', '520381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2495', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2481', '仁怀市', '520382', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2496', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '安顺市', '520400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2497', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2496', '西秀区', '520402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2498', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2496', '平坝区', '520403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2499', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2496', '普定县', '520422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('25', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '东丽区', '120110', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('250', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '平顺县', '140425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2500', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2496', '镇宁布依族苗族自治县', '520423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2501', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2496', '关岭布依族苗族自治县', '520424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2502', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2496', '紫云苗族布依族自治县', '520425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2503', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '毕节市', '520500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2504', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2503', '七星关区', '520502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2505', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2503', '大方县', '520521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2506', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2503', '黔西县', '520522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2507', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2503', '金沙县', '520523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2508', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2503', '织金县', '520524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2509', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2503', '纳雍县', '520525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('251', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '黎城县', '140426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2510', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2503', '威宁彝族回族苗族自治县', '520526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2511', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2503', '赫章县', '520527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2512', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '铜仁市', '520600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2513', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '碧江区', '520602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2514', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '万山区', '520603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2515', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '江口县', '520621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2516', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '玉屏侗族自治县', '520622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2517', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '石阡县', '520623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2518', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '思南县', '520624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2519', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '印江土家族苗族自治县', '520625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('252', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '壶关县', '140427', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2520', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '德江县', '520626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2521', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '沿河土家族自治县', '520627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2522', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2512', '松桃苗族自治县', '520628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2523', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '黔西南布依族苗族自治州', '522300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2524', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2523', '兴义市', '522301', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2525', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2523', '兴仁市', '522302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2526', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2523', '普安县', '522323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2527', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2523', '晴隆县', '522324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2528', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2523', '贞丰县', '522325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2529', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2523', '望谟县', '522326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('253', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '长子县', '140428', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2530', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2523', '册亨县', '522327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2531', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2523', '安龙县', '522328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2532', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '黔东南苗族侗族自治州', '522600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2533', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '凯里市', '522601', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2534', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '黄平县', '522622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2535', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '施秉县', '522623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2536', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '三穗县', '522624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2537', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '镇远县', '522625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2538', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '岑巩县', '522626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2539', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '天柱县', '522627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('254', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '武乡县', '140429', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2540', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '锦屏县', '522628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2541', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '剑河县', '522629', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2542', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '台江县', '522630', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2543', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '黎平县', '522631', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2544', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '榕江县', '522632', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2545', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '从江县', '522633', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2546', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '雷山县', '522634', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2547', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '麻江县', '522635', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2548', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2532', '丹寨县', '522636', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2549', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2464', '黔南布依族苗族自治州', '522700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('255', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '沁县', '140430', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2550', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '都匀市', '522701', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2551', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '福泉市', '522702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2552', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '荔波县', '522722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2553', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '贵定县', '522723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2554', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '瓮安县', '522725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2555', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '独山县', '522726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2556', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '平塘县', '522727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2557', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '罗甸县', '522728', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2558', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '长顺县', '522729', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2559', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '龙里县', '522730', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('256', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '244', '沁源县', '140431', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2560', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '惠水县', '522731', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2561', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2549', '三都水族自治县', '522732', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2562', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '云南省', '530000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2563', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '昆明市', '530100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2564', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '五华区', '530102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2565', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '盘龙区', '530103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2566', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '官渡区', '530111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2567', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '西山区', '530112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2568', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '东川区', '530113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2569', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '呈贡区', '530114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('257', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '晋城市', '140500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2570', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '晋宁区', '530115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2571', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '富民县', '530124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2572', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '宜良县', '530125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2573', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '石林彝族自治县', '530126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2574', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '嵩明县', '530127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2575', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '禄劝彝族苗族自治县', '530128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2576', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '寻甸回族彝族自治县', '530129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2577', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2563', '安宁市', '530181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2578', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '曲靖市', '530300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2579', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '麒麟区', '530302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('258', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '257', '城区', '140502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2580', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '沾益区', '530303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2581', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '马龙区', '530304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2582', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '陆良县', '530322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2583', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '师宗县', '530323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2584', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '罗平县', '530324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2585', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '富源县', '530325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2586', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '会泽县', '530326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2587', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2578', '宣威市', '530381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2588', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '玉溪市', '530400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2589', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '红塔区', '530402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('259', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '257', '沁水县', '140521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2590', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '江川区', '530403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2591', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '通海县', '530423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2592', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '华宁县', '530424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2593', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '易门县', '530425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2594', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '峨山彝族自治县', '530426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2595', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '新平彝族傣族自治县', '530427', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2596', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '元江哈尼族彝族傣族自治县', '530428', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2597', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2588', '澄江市', '530481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2598', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '保山市', '530500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2599', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2598', '隆阳区', '530502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('26', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '西青区', '120111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('260', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '257', '阳城县', '140522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2600', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2598', '施甸县', '530521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2601', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2598', '龙陵县', '530523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2602', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2598', '昌宁县', '530524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2603', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2598', '腾冲市', '530581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2604', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '昭通市', '530600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2605', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '昭阳区', '530602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2606', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '鲁甸县', '530621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2607', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '巧家县', '530622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2608', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '盐津县', '530623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2609', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '大关县', '530624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('261', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '257', '陵川县', '140524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2610', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '永善县', '530625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2611', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '绥江县', '530626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2612', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '镇雄县', '530627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2613', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '彝良县', '530628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2614', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '威信县', '530629', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2615', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2604', '水富市', '530681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2616', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '丽江市', '530700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2617', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2616', '古城区', '530702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2618', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2616', '玉龙纳西族自治县', '530721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2619', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2616', '永胜县', '530722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('262', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '257', '泽州县', '140525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2620', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2616', '华坪县', '530723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2621', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2616', '宁蒗彝族自治县', '530724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2622', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '普洱市', '530800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2623', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '思茅区', '530802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2624', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '宁洱哈尼族彝族自治县', '530821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2625', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '墨江哈尼族自治县', '530822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2626', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '景东彝族自治县', '530823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2627', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '景谷傣族彝族自治县', '530824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2628', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '镇沅彝族哈尼族拉祜族自治县', '530825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2629', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '江城哈尼族彝族自治县', '530826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('263', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '257', '高平市', '140581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2630', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '孟连傣族拉祜族佤族自治县', '530827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2631', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '澜沧拉祜族自治县', '530828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2632', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2622', '西盟佤族自治县', '530829', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2633', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '临沧市', '530900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2634', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2633', '临翔区', '530902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2635', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2633', '凤庆县', '530921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2636', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2633', '云县', '530922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2637', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2633', '永德县', '530923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2638', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2633', '镇康县', '530924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2639', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2633', '双江拉祜族佤族布朗族傣族自治县', '530925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('264', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '朔州市', '140600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2640', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2633', '耿马傣族佤族自治县', '530926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2641', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2633', '沧源佤族自治县', '530927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2642', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '楚雄彝族自治州', '532300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2643', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '楚雄市', '532301', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2644', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '双柏县', '532322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2645', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '牟定县', '532323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2646', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '南华县', '532324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2647', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '姚安县', '532325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2648', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '大姚县', '532326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2649', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '永仁县', '532327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('265', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '264', '朔城区', '140602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2650', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '元谋县', '532328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2651', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '武定县', '532329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2652', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2642', '禄丰县', '532331', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2653', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '红河哈尼族彝族自治州', '532500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2654', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '个旧市', '532501', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2655', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '开远市', '532502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2656', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '蒙自市', '532503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2657', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '弥勒市', '532504', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2658', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '屏边苗族自治县', '532523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2659', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '建水县', '532524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('266', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '264', '平鲁区', '140603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2660', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '石屏县', '532525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2661', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '泸西县', '532527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2662', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '元阳县', '532528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2663', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '红河县', '532529', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2664', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '金平苗族瑶族傣族自治县', '532530', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2665', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '绿春县', '532531', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2666', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2653', '河口瑶族自治县', '532532', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2667', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '文山壮族苗族自治州', '532600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2668', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2667', '文山市', '532601', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2669', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2667', '砚山县', '532622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('267', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '264', '山阴县', '140621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2670', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2667', '西畴县', '532623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2671', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2667', '麻栗坡县', '532624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2672', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2667', '马关县', '532625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2673', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2667', '丘北县', '532626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2674', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2667', '广南县', '532627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2675', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2667', '富宁县', '532628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2676', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '西双版纳傣族自治州', '532800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2677', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2676', '景洪市', '532801', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2678', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2676', '勐海县', '532822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2679', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2676', '勐腊县', '532823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('268', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '264', '应县', '140622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2680', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '大理白族自治州', '532900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2681', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '大理市', '532901', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2682', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '漾濞彝族自治县', '532922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2683', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '祥云县', '532923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2684', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '宾川县', '532924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2685', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '弥渡县', '532925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2686', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '南涧彝族自治县', '532926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2687', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '巍山彝族回族自治县', '532927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2688', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '永平县', '532928', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2689', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '云龙县', '532929', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('269', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '264', '右玉县', '140623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2690', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '洱源县', '532930', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2691', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '剑川县', '532931', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2692', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2680', '鹤庆县', '532932', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2693', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '德宏傣族景颇族自治州', '533100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2694', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2693', '瑞丽市', '533102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2695', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2693', '芒市', '533103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2696', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2693', '梁河县', '533122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2697', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2693', '盈江县', '533123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2698', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2693', '陇川县', '533124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2699', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '怒江傈僳族自治州', '533300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('27', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '津南区', '120112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('270', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '264', '怀仁市', '140681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2700', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2699', '泸水市', '533301', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2701', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2699', '福贡县', '533323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2702', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2699', '贡山独龙族怒族自治县', '533324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2703', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2699', '兰坪白族普米族自治县', '533325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2704', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2562', '迪庆藏族自治州', '533400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2705', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2704', '香格里拉市', '533401', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2706', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2704', '德钦县', '533422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2707', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2704', '维西傈僳族自治县', '533423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2708', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '西藏自治区', '540000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2709', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2708', '拉萨市', '540100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('271', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '晋中市', '140700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2710', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2709', '城关区', '540102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2711', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2709', '堆龙德庆区', '540103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2712', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2709', '达孜区', '540104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2713', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2709', '林周县', '540121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2714', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2709', '当雄县', '540122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2715', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2709', '尼木县', '540123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2716', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2709', '曲水县', '540124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2717', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2709', '墨竹工卡县', '540127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2718', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2708', '日喀则市', '540200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2719', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '桑珠孜区', '540202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('272', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '榆次区', '140702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2720', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '南木林县', '540221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2721', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '江孜县', '540222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2722', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '定日县', '540223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2723', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '萨迦县', '540224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2724', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '拉孜县', '540225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2725', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '昂仁县', '540226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2726', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '谢通门县', '540227', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2727', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '白朗县', '540228', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2728', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '仁布县', '540229', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2729', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '康马县', '540230', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('273', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '太谷区', '140703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2730', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '定结县', '540231', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2731', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '仲巴县', '540232', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2732', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '亚东县', '540233', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2733', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '吉隆县', '540234', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2734', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '聂拉木县', '540235', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2735', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '萨嘎县', '540236', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2736', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2718', '岗巴县', '540237', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2737', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2708', '昌都市', '540300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2738', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '卡若区', '540302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2739', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '江达县', '540321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('274', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '榆社县', '140721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2740', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '贡觉县', '540322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2741', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '类乌齐县', '540323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2742', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '丁青县', '540324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2743', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '察雅县', '540325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2744', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '八宿县', '540326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2745', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '左贡县', '540327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2746', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '芒康县', '540328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2747', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '洛隆县', '540329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2748', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2737', '边坝县', '540330', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2749', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2708', '林芝市', '540400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('275', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '左权县', '140722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2750', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2749', '巴宜区', '540402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2751', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2749', '工布江达县', '540421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2752', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2749', '米林县', '540422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2753', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2749', '墨脱县', '540423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2754', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2749', '波密县', '540424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2755', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2749', '察隅县', '540425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2756', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2749', '朗县', '540426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2757', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2708', '山南市', '540500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2758', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '乃东区', '540502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2759', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '扎囊县', '540521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('276', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '和顺县', '140723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2760', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '贡嘎县', '540522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2761', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '桑日县', '540523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2762', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '琼结县', '540524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2763', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '曲松县', '540525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2764', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '措美县', '540526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2765', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '洛扎县', '540527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2766', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '加查县', '540528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2767', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '隆子县', '540529', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2768', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '错那县', '540530', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2769', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2757', '浪卡子县', '540531', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('277', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '昔阳县', '140724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2770', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2708', '那曲市', '540600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2771', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '色尼区', '540602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2772', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '嘉黎县', '540621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2773', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '比如县', '540622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2774', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '聂荣县', '540623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2775', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '安多县', '540624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2776', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '申扎县', '540625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2777', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '索县', '540626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2778', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '班戈县', '540627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2779', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '巴青县', '540628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('278', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '寿阳县', '140725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2780', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '尼玛县', '540629', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2781', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2770', '双湖县', '540630', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2782', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2708', '阿里地区', '542500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2783', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2782', '普兰县', '542521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2784', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2782', '札达县', '542522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2785', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2782', '噶尔县', '542523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2786', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2782', '日土县', '542524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2787', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2782', '革吉县', '542525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2788', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2782', '改则县', '542526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2789', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2782', '措勤县', '542527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('279', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '祁县', '140727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2790', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '陕西省', '610000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2791', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '西安市', '610100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2792', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '新城区', '610102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2793', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '碑林区', '610103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2794', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '莲湖区', '610104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2795', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '灞桥区', '610111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2796', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '未央区', '610112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2797', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '雁塔区', '610113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2798', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '阎良区', '610114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2799', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '临潼区', '610115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('28', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '北辰区', '120113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('280', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '平遥县', '140728', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2800', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '长安区', '610116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2801', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '高陵区', '610117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2802', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '鄠邑区', '610118', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2803', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '蓝田县', '610122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2804', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2791', '周至县', '610124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2805', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '铜川市', '610200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2806', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2805', '王益区', '610202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2807', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2805', '印台区', '610203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2808', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2805', '耀州区', '610204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2809', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2805', '宜君县', '610222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('281', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '灵石县', '140729', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2810', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '宝鸡市', '610300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2811', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '渭滨区', '610302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2812', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '金台区', '610303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2813', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '陈仓区', '610304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2814', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '凤翔县', '610322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2815', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '岐山县', '610323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2816', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '扶风县', '610324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2817', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '眉县', '610326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2818', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '陇县', '610327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2819', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '千阳县', '610328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('282', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '271', '介休市', '140781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2820', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '麟游县', '610329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2821', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '凤县', '610330', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2822', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2810', '太白县', '610331', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2823', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '咸阳市', '610400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2824', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '秦都区', '610402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2825', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '杨陵区', '610403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2826', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '渭城区', '610404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2827', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '三原县', '610422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2828', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '泾阳县', '610423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2829', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '乾县', '610424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('283', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '运城市', '140800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2830', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '礼泉县', '610425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2831', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '永寿县', '610426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2832', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '长武县', '610428', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2833', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '旬邑县', '610429', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2834', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '淳化县', '610430', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2835', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '武功县', '610431', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2836', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '兴平市', '610481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2837', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2823', '彬州市', '610482', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2838', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '渭南市', '610500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2839', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '临渭区', '610502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('284', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '盐湖区', '140802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2840', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '华州区', '610503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2841', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '潼关县', '610522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2842', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '大荔县', '610523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2843', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '合阳县', '610524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2844', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '澄城县', '610525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2845', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '蒲城县', '610526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2846', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '白水县', '610527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2847', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '富平县', '610528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2848', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '韩城市', '610581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2849', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2838', '华阴市', '610582', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('285', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '临猗县', '140821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2850', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '延安市', '610600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2851', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '宝塔区', '610602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2852', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '安塞区', '610603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2853', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '延长县', '610621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2854', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '延川县', '610622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2855', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '志丹县', '610625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2856', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '吴起县', '610626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2857', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '甘泉县', '610627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2858', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '富县', '610628', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2859', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '洛川县', '610629', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('286', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '万荣县', '140822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2860', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '宜川县', '610630', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2861', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '黄龙县', '610631', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2862', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '黄陵县', '610632', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2863', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2850', '子长市', '610681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2864', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '汉中市', '610700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2865', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '汉台区', '610702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2866', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '南郑区', '610703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2867', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '城固县', '610722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2868', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '洋县', '610723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2869', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '西乡县', '610724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('287', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '闻喜县', '140823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2870', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '勉县', '610725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2871', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '宁强县', '610726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2872', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '略阳县', '610727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2873', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '镇巴县', '610728', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2874', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '留坝县', '610729', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2875', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2864', '佛坪县', '610730', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2876', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '榆林市', '610800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2877', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '榆阳区', '610802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2878', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '横山区', '610803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2879', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '府谷县', '610822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('288', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '稷山县', '140824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2880', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '靖边县', '610824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2881', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '定边县', '610825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2882', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '绥德县', '610826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2883', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '米脂县', '610827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2884', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '佳县', '610828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2885', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '吴堡县', '610829', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2886', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '清涧县', '610830', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2887', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '子洲县', '610831', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2888', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2876', '神木市', '610881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2889', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '安康市', '610900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('289', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '新绛县', '140825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2890', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '汉滨区', '610902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2891', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '汉阴县', '610921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2892', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '石泉县', '610922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2893', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '宁陕县', '610923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2894', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '紫阳县', '610924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2895', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '岚皋县', '610925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2896', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '平利县', '610926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2897', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '镇坪县', '610927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2898', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '旬阳县', '610928', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2899', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2889', '白河县', '610929', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('29', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '武清区', '120114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('290', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '绛县', '140826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2900', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2790', '商洛市', '611000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2901', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2900', '商州区', '611002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2902', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2900', '洛南县', '611021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2903', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2900', '丹凤县', '611022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2904', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2900', '商南县', '611023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2905', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2900', '山阳县', '611024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2906', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2900', '镇安县', '611025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2907', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2900', '柞水县', '611026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2908', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '甘肃省', '620000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('2909', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '兰州市', '620100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('291', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '垣曲县', '140827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2910', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2909', '城关区', '620102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2911', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2909', '七里河区', '620103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2912', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2909', '西固区', '620104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2913', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2909', '安宁区', '620105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2914', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2909', '红古区', '620111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2915', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2909', '永登县', '620121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2916', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2909', '皋兰县', '620122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2917', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2909', '榆中县', '620123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2918', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '嘉峪关市', '620200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2919', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '金昌市', '620300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('292', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '夏县', '140828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2920', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2919', '金川区', '620302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2921', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2919', '永昌县', '620321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2922', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '白银市', '620400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2923', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2922', '白银区', '620402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2924', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2922', '平川区', '620403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2925', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2922', '靖远县', '620421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2926', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2922', '会宁县', '620422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2927', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2922', '景泰县', '620423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2928', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '天水市', '620500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2929', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2928', '秦州区', '620502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('293', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '平陆县', '140829', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2930', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2928', '麦积区', '620503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2931', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2928', '清水县', '620521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2932', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2928', '秦安县', '620522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2933', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2928', '甘谷县', '620523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2934', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2928', '武山县', '620524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2935', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2928', '张家川回族自治县', '620525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2936', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '武威市', '620600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2937', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2936', '凉州区', '620602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2938', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2936', '民勤县', '620621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2939', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2936', '古浪县', '620622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('294', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '芮城县', '140830', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2940', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2936', '天祝藏族自治县', '620623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2941', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '张掖市', '620700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2942', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2941', '甘州区', '620702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2943', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2941', '肃南裕固族自治县', '620721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2944', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2941', '民乐县', '620722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2945', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2941', '临泽县', '620723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2946', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2941', '高台县', '620724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2947', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2941', '山丹县', '620725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2948', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '平凉市', '620800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2949', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2948', '崆峒区', '620802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('295', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '永济市', '140881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2950', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2948', '泾川县', '620821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2951', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2948', '灵台县', '620822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2952', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2948', '崇信县', '620823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2953', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2948', '庄浪县', '620825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2954', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2948', '静宁县', '620826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2955', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2948', '华亭市', '620881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2956', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '酒泉市', '620900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2957', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2956', '肃州区', '620902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2958', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2956', '金塔县', '620921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2959', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2956', '瓜州县', '620922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('296', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '283', '河津市', '140882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2960', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2956', '肃北蒙古族自治县', '620923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2961', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2956', '阿克塞哈萨克族自治县', '620924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2962', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2956', '玉门市', '620981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2963', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2956', '敦煌市', '620982', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2964', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '庆阳市', '621000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2965', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2964', '西峰区', '621002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2966', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2964', '庆城县', '621021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2967', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2964', '环县', '621022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2968', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2964', '华池县', '621023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2969', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2964', '合水县', '621024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('297', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '忻州市', '140900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2970', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2964', '正宁县', '621025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2971', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2964', '宁县', '621026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2972', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2964', '镇原县', '621027', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2973', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '定西市', '621100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2974', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2973', '安定区', '621102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2975', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2973', '通渭县', '621121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2976', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2973', '陇西县', '621122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2977', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2973', '渭源县', '621123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2978', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2973', '临洮县', '621124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2979', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2973', '漳县', '621125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('298', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '忻府区', '140902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2980', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2973', '岷县', '621126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2981', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '陇南市', '621200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2982', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '武都区', '621202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2983', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '成县', '621221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2984', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '文县', '621222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2985', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '宕昌县', '621223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2986', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '康县', '621224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2987', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '西和县', '621225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2988', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '礼县', '621226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2989', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '徽县', '621227', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('299', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '定襄县', '140921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2990', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2981', '两当县', '621228', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2991', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '临夏回族自治州', '622900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('2992', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2991', '临夏市', '622901', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2993', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2991', '临夏县', '622921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2994', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2991', '康乐县', '622922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2995', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2991', '永靖县', '622923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2996', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2991', '广河县', '622924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2997', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2991', '和政县', '622925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2998', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2991', '东乡族自治县', '622926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('2999', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '2991', '积石山保安族东乡族撒拉族自治县', '622927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '西城区', '110102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('30', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '宝坻区', '120115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('300', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '五台县', '140922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3000', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '2908', '甘南藏族自治州', '623000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3001', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3000', '合作市', '623001', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3002', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3000', '临潭县', '623021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3003', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3000', '卓尼县', '623022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3004', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3000', '舟曲县', '623023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3005', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3000', '迭部县', '623024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3006', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3000', '玛曲县', '623025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3007', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3000', '碌曲县', '623026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3008', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3000', '夏河县', '623027', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3009', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '青海省', '630000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('301', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '代县', '140923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3010', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3009', '西宁市', '630100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3011', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3010', '城东区', '630102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3012', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3010', '城中区', '630103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3013', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3010', '城西区', '630104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3014', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3010', '城北区', '630105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3015', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3010', '湟中区', '630106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3016', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3010', '大通回族土族自治县', '630121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3017', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3010', '湟源县', '630123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3018', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3009', '海东市', '630200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3019', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3018', '乐都区', '630202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('302', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '繁峙县', '140924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3020', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3018', '平安区', '630203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3021', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3018', '民和回族土族自治县', '630222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3022', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3018', '互助土族自治县', '630223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3023', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3018', '化隆回族自治县', '630224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3024', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3018', '循化撒拉族自治县', '630225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3025', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3009', '海北藏族自治州', '632200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3026', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3025', '门源回族自治县', '632221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3027', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3025', '祁连县', '632222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3028', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3025', '海晏县', '632223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3029', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3025', '刚察县', '632224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('303', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '宁武县', '140925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3030', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3009', '黄南藏族自治州', '632300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3031', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3030', '同仁县', '632321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3032', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3030', '尖扎县', '632322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3033', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3030', '泽库县', '632323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3034', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3030', '河南蒙古族自治县', '632324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3035', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3009', '海南藏族自治州', '632500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3036', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3035', '共和县', '632521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3037', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3035', '同德县', '632522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3038', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3035', '贵德县', '632523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3039', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3035', '兴海县', '632524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('304', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '静乐县', '140926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3040', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3035', '贵南县', '632525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3041', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3009', '果洛藏族自治州', '632600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3042', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3041', '玛沁县', '632621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3043', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3041', '班玛县', '632622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3044', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3041', '甘德县', '632623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3045', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3041', '达日县', '632624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3046', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3041', '久治县', '632625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3047', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3041', '玛多县', '632626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3048', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3009', '玉树藏族自治州', '632700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3049', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3048', '玉树市', '632701', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('305', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '神池县', '140927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3050', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3048', '杂多县', '632722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3051', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3048', '称多县', '632723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3052', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3048', '治多县', '632724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3053', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3048', '囊谦县', '632725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3054', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3048', '曲麻莱县', '632726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3055', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3009', '海西蒙古族藏族自治州', '632800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3056', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3055', '格尔木市', '632801', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3057', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3055', '德令哈市', '632802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3058', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3055', '茫崖市', '632803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3059', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3055', '乌兰县', '632821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('306', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '五寨县', '140928', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3060', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3055', '都兰县', '632822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3061', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3055', '天峻县', '632823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3062', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '宁夏回族自治区', '640000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('3063', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3062', '银川市', '640100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3064', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3063', '兴庆区', '640104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3065', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3063', '西夏区', '640105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3066', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3063', '金凤区', '640106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3067', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3063', '永宁县', '640121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3068', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3063', '贺兰县', '640122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3069', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3063', '灵武市', '640181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('307', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '岢岚县', '140929', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3070', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3062', '石嘴山市', '640200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3071', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3070', '大武口区', '640202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3072', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3070', '惠农区', '640205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3073', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3070', '平罗县', '640221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3074', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3062', '吴忠市', '640300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3075', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3074', '利通区', '640302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3076', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3074', '红寺堡区', '640303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3077', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3074', '盐池县', '640323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3078', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3074', '同心县', '640324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3079', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3074', '青铜峡市', '640381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('308', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '河曲县', '140930', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3080', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3062', '固原市', '640400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3081', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3080', '原州区', '640402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3082', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3080', '西吉县', '640422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3083', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3080', '隆德县', '640423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3084', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3080', '泾源县', '640424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3085', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3080', '彭阳县', '640425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3086', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3062', '中卫市', '640500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3087', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3086', '沙坡头区', '640502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3088', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3086', '中宁县', '640521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3089', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3086', '海原县', '640522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('309', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '保德县', '140931', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3090', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '新疆维吾尔自治区', '650000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('3091', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '乌鲁木齐市', '650100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3092', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3091', '天山区', '650102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3093', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3091', '沙依巴克区', '650103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3094', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3091', '新市区', '650104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3095', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3091', '水磨沟区', '650105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3096', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3091', '头屯河区', '650106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3097', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3091', '达坂城区', '650107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3098', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3091', '米东区', '650109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3099', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3091', '乌鲁木齐县', '650121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('31', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '滨海新区', '120116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('310', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '偏关县', '140932', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3100', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '克拉玛依市', '650200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3101', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3100', '独山子区', '650202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3102', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3100', '克拉玛依区', '650203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3103', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3100', '白碱滩区', '650204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3104', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3100', '乌尔禾区', '650205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3105', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '吐鲁番市', '650400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3106', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3105', '高昌区', '650402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3107', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3105', '鄯善县', '650421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3108', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3105', '托克逊县', '650422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3109', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '哈密市', '650500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('311', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '297', '原平市', '140981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3110', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3109', '伊州区', '650502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3111', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3109', '巴里坤哈萨克自治县', '650521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3112', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3109', '伊吾县', '650522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3113', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '昌吉回族自治州', '652300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3114', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3113', '昌吉市', '652301', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3115', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3113', '阜康市', '652302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3116', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3113', '呼图壁县', '652323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3117', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3113', '玛纳斯县', '652324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3118', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3113', '奇台县', '652325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3119', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3113', '吉木萨尔县', '652327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('312', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '临汾市', '141000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3120', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3113', '木垒哈萨克自治县', '652328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3121', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '博尔塔拉蒙古自治州', '652700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3122', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3121', '博乐市', '652701', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3123', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3121', '阿拉山口市', '652702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3124', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3121', '精河县', '652722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3125', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3121', '温泉县', '652723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3126', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '巴音郭楞蒙古自治州', '652800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3127', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '库尔勒市', '652801', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3128', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '轮台县', '652822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3129', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '尉犁县', '652823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('313', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '尧都区', '141002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3130', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '若羌县', '652824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3131', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '且末县', '652825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3132', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '焉耆回族自治县', '652826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3133', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '和静县', '652827', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3134', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '和硕县', '652828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3135', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3126', '博湖县', '652829', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3136', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '阿克苏地区', '652900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3137', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '阿克苏市', '652901', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3138', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '库车市', '652902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3139', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '温宿县', '652922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('314', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '曲沃县', '141021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3140', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '沙雅县', '652924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3141', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '新和县', '652925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3142', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '拜城县', '652926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3143', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '乌什县', '652927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3144', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '阿瓦提县', '652928', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3145', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3136', '柯坪县', '652929', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3146', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '克孜勒苏柯尔克孜自治州', '653000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3147', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3146', '阿图什市', '653001', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3148', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3146', '阿克陶县', '653022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3149', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3146', '阿合奇县', '653023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('315', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '翼城县', '141022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3150', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3146', '乌恰县', '653024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3151', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '喀什地区', '653100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3152', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '喀什市', '653101', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3153', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '疏附县', '653121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3154', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '疏勒县', '653122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3155', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '英吉沙县', '653123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3156', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '泽普县', '653124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3157', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '莎车县', '653125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3158', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '叶城县', '653126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3159', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '麦盖提县', '653127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('316', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '襄汾县', '141023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3160', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '岳普湖县', '653128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3161', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '伽师县', '653129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3162', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '巴楚县', '653130', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3163', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3151', '塔什库尔干塔吉克自治县', '653131', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3164', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '和田地区', '653200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3165', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3164', '和田市', '653201', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3166', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3164', '和田县', '653221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3167', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3164', '墨玉县', '653222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3168', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3164', '皮山县', '653223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3169', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3164', '洛浦县', '653224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('317', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '洪洞县', '141024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3170', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3164', '策勒县', '653225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3171', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3164', '于田县', '653226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3172', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3164', '民丰县', '653227', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3173', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '伊犁哈萨克自治州', '654000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3174', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '伊宁市', '654002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3175', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '奎屯市', '654003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3176', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '霍尔果斯市', '654004', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3177', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '伊宁县', '654021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3178', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '察布查尔锡伯自治县', '654022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3179', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '霍城县', '654023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('318', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '古县', '141025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3180', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '巩留县', '654024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3181', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '新源县', '654025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3182', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '昭苏县', '654026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3183', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '特克斯县', '654027', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3184', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3173', '尼勒克县', '654028', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3185', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '塔城地区', '654200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3186', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3185', '塔城市', '654201', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3187', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3185', '乌苏市', '654202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3188', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3185', '额敏县', '654221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3189', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3185', '沙湾县', '654223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('319', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '安泽县', '141026', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3190', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3185', '托里县', '654224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3191', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3185', '裕民县', '654225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3192', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3185', '和布克赛尔蒙古自治县', '654226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3193', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '3090', '阿勒泰地区', '654300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3194', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3193', '阿勒泰市', '654301', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3195', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3193', '布尔津县', '654321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3196', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3193', '富蕴县', '654322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3197', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3193', '福海县', '654323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3198', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3193', '哈巴河县', '654324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3199', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3193', '青河县', '654325', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('32', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '宁河区', '120117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('320', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '浮山县', '141027', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3200', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '3193', '吉木乃县', '654326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3201', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '石河子市', '659001', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3202', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '阿拉尔市', '659002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3203', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '图木舒克市', '659003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3204', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '五家渠市', '659004', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3205', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '北屯市', '659005', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3206', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '铁门关市', '659006', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3207', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '双河市', '659007', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3208', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '可克达拉市', '659008', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3209', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '昆玉市', '659009', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('321', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '吉县', '141028', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3210', NULL, NULL, NULL, '2020-06-08 10:25:33', 0, 0, '3090', '胡杨河市', '659010', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('3211', NULL, NULL, NULL, '2020-06-09 14:10:11', 0, 0, '0', '中国台湾', '710000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('3212', NULL, NULL, NULL, '2020-06-09 14:13:19', 0, 0, '0', '中国香港', '810000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('3213', NULL, NULL, NULL, '2020-06-09 16:25:04', 0, 0, '0', '中国澳门', '820000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('3216', NULL, NULL, NULL, '2020-06-09 14:00:41', 0, 0, '1', '北京市', '110010', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3217', NULL, NULL, NULL, '2020-06-09 14:02:38', 0, 0, '18', '天津市', '120010', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3218', NULL, NULL, NULL, '2020-06-09 14:06:26', 0, 0, '780', '上海市', '310010', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('3219', NULL, NULL, NULL, '2020-06-09 14:08:59', 0, 0, '2220', '重庆市', '500010', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('322', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '乡宁县', '141029', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('323', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '大宁县', '141030', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('324', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '隰县', '141031', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('325', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '永和县', '141032', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('326', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '蒲县', '141033', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('327', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '汾西县', '141034', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('328', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '侯马市', '141081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('329', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '312', '霍州市', '141082', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('33', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '静海区', '120118', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('330', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '215', '吕梁市', '141100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('331', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '离石区', '141102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('332', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '文水县', '141121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('333', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '交城县', '141122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('334', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '兴县', '141123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('335', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '临县', '141124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('336', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '柳林县', '141125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('337', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '石楼县', '141126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('338', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '岚县', '141127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('339', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '方山县', '141128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('34', NULL, NULL, NULL, '2020-06-09 14:04:25', 0, 0, '3217', '蓟州区', '120119', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('340', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '中阳县', '141129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('341', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '交口县', '141130', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('342', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '孝义市', '141181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('343', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '330', '汾阳市', '141182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('344', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '内蒙古自治区', '150000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('345', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '呼和浩特市', '150100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('346', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '新城区', '150102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('347', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '回民区', '150103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('348', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '玉泉区', '150104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('349', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '赛罕区', '150105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('35', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '河北省', '130000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('350', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '土默特左旗', '150121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('351', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '托克托县', '150122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('352', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '和林格尔县', '150123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('353', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '清水河县', '150124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('354', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '345', '武川县', '150125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('355', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '包头市', '150200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('356', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '东河区', '150202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('357', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '昆都仑区', '150203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('358', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '青山区', '150204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('359', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '石拐区', '150205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('36', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '石家庄市', '130100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('360', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '白云鄂博矿区', '150206', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('361', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '九原区', '150207', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('362', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '土默特右旗', '150221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('363', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '固阳县', '150222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('364', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '355', '达尔罕茂明安联合旗', '150223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('365', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '乌海市', '150300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('366', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '365', '海勃湾区', '150302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('367', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '365', '海南区', '150303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('368', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '365', '乌达区', '150304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('369', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '赤峰市', '150400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('37', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '长安区', '130102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('370', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '红山区', '150402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('371', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '元宝山区', '150403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('372', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '松山区', '150404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('373', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '阿鲁科尔沁旗', '150421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('374', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '巴林左旗', '150422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('375', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '巴林右旗', '150423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('376', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '林西县', '150424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('377', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '克什克腾旗', '150425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('378', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '翁牛特旗', '150426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('379', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '喀喇沁旗', '150428', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('38', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '桥西区', '130104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('380', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '宁城县', '150429', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('381', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '369', '敖汉旗', '150430', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('382', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '通辽市', '150500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('383', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '382', '科尔沁区', '150502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('384', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '382', '科尔沁左翼中旗', '150521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('385', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '382', '科尔沁左翼后旗', '150522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('386', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '382', '开鲁县', '150523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('387', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '382', '库伦旗', '150524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('388', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '382', '奈曼旗', '150525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('389', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '382', '扎鲁特旗', '150526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('39', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '新华区', '130105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('390', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '382', '霍林郭勒市', '150581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('391', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '鄂尔多斯市', '150600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('392', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '东胜区', '150602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('393', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '康巴什区', '150603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('394', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '达拉特旗', '150621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('395', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '准格尔旗', '150622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('396', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '鄂托克前旗', '150623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('397', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '鄂托克旗', '150624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('398', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '杭锦旗', '150625', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('399', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '乌审旗', '150626', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('4', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '朝阳区', '110105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('40', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '井陉矿区', '130107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('400', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '391', '伊金霍洛旗', '150627', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('401', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '呼伦贝尔市', '150700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('402', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '海拉尔区', '150702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('403', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '扎赉诺尔区', '150703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('404', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '阿荣旗', '150721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('405', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '莫力达瓦达斡尔族自治旗', '150722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('406', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '鄂伦春自治旗', '150723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('407', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '鄂温克族自治旗', '150724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('408', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '陈巴尔虎旗', '150725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('409', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '新巴尔虎左旗', '150726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('41', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '裕华区', '130108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('410', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '新巴尔虎右旗', '150727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('411', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '满洲里市', '150781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('412', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '牙克石市', '150782', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('413', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '扎兰屯市', '150783', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('414', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '额尔古纳市', '150784', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('415', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '401', '根河市', '150785', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('416', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '巴彦淖尔市', '150800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('417', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '416', '临河区', '150802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('418', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '416', '五原县', '150821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('419', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '416', '磴口县', '150822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('42', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '藁城区', '130109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('420', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '416', '乌拉特前旗', '150823', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('421', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '416', '乌拉特中旗', '150824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('422', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '416', '乌拉特后旗', '150825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('423', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '416', '杭锦后旗', '150826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('424', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '乌兰察布市', '150900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('425', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '集宁区', '150902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('426', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '卓资县', '150921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('427', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '化德县', '150922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('428', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '商都县', '150923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('429', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '兴和县', '150924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('43', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '鹿泉区', '130110', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('430', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '凉城县', '150925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('431', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '察哈尔右翼前旗', '150926', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('432', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '察哈尔右翼中旗', '150927', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('433', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '察哈尔右翼后旗', '150928', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('434', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '四子王旗', '150929', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('435', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '424', '丰镇市', '150981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('436', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '兴安盟', '152200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('437', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '436', '乌兰浩特市', '152201', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('438', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '436', '阿尔山市', '152202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('439', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '436', '科尔沁右翼前旗', '152221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('44', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '栾城区', '130111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('440', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '436', '科尔沁右翼中旗', '152222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('441', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '436', '扎赉特旗', '152223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('442', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '436', '突泉县', '152224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('443', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '锡林郭勒盟', '152500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('444', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '二连浩特市', '152501', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('445', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '锡林浩特市', '152502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('446', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '阿巴嘎旗', '152522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('447', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '苏尼特左旗', '152523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('448', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '苏尼特右旗', '152524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('449', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '东乌珠穆沁旗', '152525', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('45', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '井陉县', '130121', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('450', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '西乌珠穆沁旗', '152526', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('451', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '太仆寺旗', '152527', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('452', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '镶黄旗', '152528', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('453', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '正镶白旗', '152529', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('454', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '正蓝旗', '152530', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('455', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '443', '多伦县', '152531', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('456', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '344', '阿拉善盟', '152900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('457', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '456', '阿拉善左旗', '152921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('458', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '456', '阿拉善右旗', '152922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('459', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '456', '额济纳旗', '152923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('46', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '正定县', '130123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('460', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '辽宁省', '210000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('461', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '沈阳市', '210100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('462', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '和平区', '210102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('463', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '沈河区', '210103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('464', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '大东区', '210104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('465', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '皇姑区', '210105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('466', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '铁西区', '210106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('467', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '苏家屯区', '210111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('468', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '浑南区', '210112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('469', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '沈北新区', '210113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('47', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '行唐县', '130125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('470', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '于洪区', '210114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('471', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '辽中区', '210115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('472', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '康平县', '210123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('473', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '法库县', '210124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('474', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '461', '新民市', '210181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('475', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '大连市', '210200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('476', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '中山区', '210202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('477', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '西岗区', '210203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('478', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '沙河口区', '210204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('479', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '甘井子区', '210211', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('48', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '灵寿县', '130126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('480', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '旅顺口区', '210212', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('481', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '金州区', '210213', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('482', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '普兰店区', '210214', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('483', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '长海县', '210224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('484', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '瓦房店市', '210281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('485', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '475', '庄河市', '210283', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('486', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '鞍山市', '210300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('487', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '486', '铁东区', '210302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('488', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '486', '铁西区', '210303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('489', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '486', '立山区', '210304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('49', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '高邑县', '130127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('490', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '486', '千山区', '210311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('491', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '486', '台安县', '210321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('492', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '486', '岫岩满族自治县', '210323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('493', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '486', '海城市', '210381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('494', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '抚顺市', '210400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('495', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '494', '新抚区', '210402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('496', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '494', '东洲区', '210403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('497', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '494', '望花区', '210404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('498', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '494', '顺城区', '210411', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('499', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '494', '抚顺县', '210421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('5', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '丰台区', '110106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('50', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '深泽县', '130128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('500', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '494', '新宾满族自治县', '210422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('501', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '494', '清原满族自治县', '210423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('502', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '本溪市', '210500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('503', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '502', '平山区', '210502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('504', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '502', '溪湖区', '210503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('505', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '502', '明山区', '210504', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('506', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '502', '南芬区', '210505', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('507', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '502', '本溪满族自治县', '210521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('508', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '502', '桓仁满族自治县', '210522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('509', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '丹东市', '210600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('51', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '赞皇县', '130129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('510', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '509', '元宝区', '210602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('511', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '509', '振兴区', '210603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('512', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '509', '振安区', '210604', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('513', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '509', '宽甸满族自治县', '210624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('514', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '509', '东港市', '210681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('515', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '509', '凤城市', '210682', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('516', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '锦州市', '210700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('517', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '516', '古塔区', '210702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('518', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '516', '凌河区', '210703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('519', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '516', '太和区', '210711', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('52', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '无极县', '130130', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('520', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '516', '黑山县', '210726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('521', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '516', '义县', '210727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('522', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '516', '凌海市', '210781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('523', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '516', '北镇市', '210782', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('524', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '营口市', '210800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('525', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '524', '站前区', '210802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('526', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '524', '西市区', '210803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('527', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '524', '鲅鱼圈区', '210804', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('528', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '524', '老边区', '210811', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('529', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '524', '盖州市', '210881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('53', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '平山县', '130131', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('530', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '524', '大石桥市', '210882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('531', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '阜新市', '210900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('532', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '531', '海州区', '210902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('533', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '531', '新邱区', '210903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('534', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '531', '太平区', '210904', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('535', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '531', '清河门区', '210905', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('536', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '531', '细河区', '210911', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('537', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '531', '阜新蒙古族自治县', '210921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('538', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '531', '彰武县', '210922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('539', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '辽阳市', '211000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('54', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '元氏县', '130132', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('540', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '539', '白塔区', '211002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('541', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '539', '文圣区', '211003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('542', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '539', '宏伟区', '211004', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('543', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '539', '弓长岭区', '211005', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('544', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '539', '太子河区', '211011', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('545', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '539', '辽阳县', '211021', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('546', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '539', '灯塔市', '211081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('547', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '盘锦市', '211100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('548', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '547', '双台子区', '211102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('549', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '547', '兴隆台区', '211103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('55', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '赵县', '130133', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('550', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '547', '大洼区', '211104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('551', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '547', '盘山县', '211122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('552', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '铁岭市', '211200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('553', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '552', '银州区', '211202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('554', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '552', '清河区', '211204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('555', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '552', '铁岭县', '211221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('556', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '552', '西丰县', '211223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('557', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '552', '昌图县', '211224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('558', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '552', '调兵山市', '211281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('559', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '552', '开原市', '211282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('56', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '辛集市', '130181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('560', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '朝阳市', '211300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('561', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '560', '双塔区', '211302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('562', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '560', '龙城区', '211303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('563', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '560', '朝阳县', '211321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('564', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '560', '建平县', '211322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('565', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '560', '喀喇沁左翼蒙古族自治县', '211324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('566', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '560', '北票市', '211381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('567', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '560', '凌源市', '211382', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('568', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '460', '葫芦岛市', '211400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('569', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '568', '连山区', '211402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('57', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '晋州市', '130183', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('570', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '568', '龙港区', '211403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('571', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '568', '南票区', '211404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('572', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '568', '绥中县', '211421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('573', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '568', '建昌县', '211422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('574', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '568', '兴城市', '211481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('575', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '吉林省', '220000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('576', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '长春市', '220100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('577', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '南关区', '220102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('578', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '宽城区', '220103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('579', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '朝阳区', '220104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('58', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '36', '新乐市', '130184', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('580', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '二道区', '220105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('581', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '绿园区', '220106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('582', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '双阳区', '220112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('583', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '九台区', '220113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('584', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '农安县', '220122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('585', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '榆树市', '220182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('586', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '576', '德惠市', '220183', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('587', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '吉林市', '220200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('588', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '昌邑区', '220202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('589', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '龙潭区', '220203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('59', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '唐山市', '130200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('590', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '船营区', '220204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('591', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '丰满区', '220211', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('592', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '永吉县', '220221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('593', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '蛟河市', '220281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('594', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '桦甸市', '220282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('595', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '舒兰市', '220283', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('596', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '587', '磐石市', '220284', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('597', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '四平市', '220300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('598', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '597', '铁西区', '220302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('599', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '597', '铁东区', '220303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('6', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '石景山区', '110107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('60', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '路南区', '130202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('600', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '597', '梨树县', '220322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('601', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '597', '伊通满族自治县', '220323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('602', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '597', '公主岭市', '220381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('603', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '597', '双辽市', '220382', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('604', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '辽源市', '220400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('605', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '604', '龙山区', '220402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('606', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '604', '西安区', '220403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('607', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '604', '东丰县', '220421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('608', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '604', '东辽县', '220422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('609', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '通化市', '220500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('61', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '路北区', '130203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('610', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '609', '东昌区', '220502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('611', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '609', '二道江区', '220503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('612', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '609', '通化县', '220521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('613', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '609', '辉南县', '220523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('614', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '609', '柳河县', '220524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('615', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '609', '梅河口市', '220581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('616', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '609', '集安市', '220582', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('617', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '白山市', '220600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('618', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '617', '浑江区', '220602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('619', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '617', '江源区', '220605', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('62', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '古冶区', '130204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('620', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '617', '抚松县', '220621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('621', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '617', '靖宇县', '220622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('622', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '617', '长白朝鲜族自治县', '220623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('623', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '617', '临江市', '220681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('624', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '松原市', '220700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('625', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '624', '宁江区', '220702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('626', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '624', '前郭尔罗斯蒙古族自治县', '220721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('627', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '624', '长岭县', '220722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('628', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '624', '乾安县', '220723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('629', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '624', '扶余市', '220781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('63', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '开平区', '130205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('630', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '白城市', '220800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('631', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '630', '洮北区', '220802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('632', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '630', '镇赉县', '220821', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('633', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '630', '通榆县', '220822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('634', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '630', '洮南市', '220881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('635', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '630', '大安市', '220882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('636', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '575', '延边朝鲜族自治州', '222400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('637', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '636', '延吉市', '222401', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('638', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '636', '图们市', '222402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('639', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '636', '敦化市', '222403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('64', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '丰南区', '130207', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('640', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '636', '珲春市', '222404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('641', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '636', '龙井市', '222405', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('642', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '636', '和龙市', '222406', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('643', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '636', '汪清县', '222424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('644', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '636', '安图县', '222426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('645', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '黑龙江省', '230000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('646', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '哈尔滨市', '230100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('647', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '道里区', '230102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('648', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '南岗区', '230103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('649', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '道外区', '230104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('65', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '丰润区', '130208', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('650', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '平房区', '230108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('651', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '松北区', '230109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('652', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '香坊区', '230110', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('653', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '呼兰区', '230111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('654', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '阿城区', '230112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('655', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '双城区', '230113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('656', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '依兰县', '230123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('657', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '方正县', '230124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('658', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '宾县', '230125', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('659', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '巴彦县', '230126', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('66', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '曹妃甸区', '130209', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('660', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '木兰县', '230127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('661', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '通河县', '230128', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('662', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '延寿县', '230129', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('663', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '尚志市', '230183', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('664', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '646', '五常市', '230184', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('665', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '齐齐哈尔市', '230200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('666', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '龙沙区', '230202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('667', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '建华区', '230203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('668', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '铁锋区', '230204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('669', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '昂昂溪区', '230205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('67', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '滦南县', '130224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('670', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '富拉尔基区', '230206', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('671', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '碾子山区', '230207', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('672', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '梅里斯达斡尔族区', '230208', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('673', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '龙江县', '230221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('674', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '依安县', '230223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('675', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '泰来县', '230224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('676', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '甘南县', '230225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('677', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '富裕县', '230227', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('678', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '克山县', '230229', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('679', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '克东县', '230230', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('68', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '乐亭县', '130225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('680', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '拜泉县', '230231', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('681', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '665', '讷河市', '230281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('682', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '鸡西市', '230300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('683', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '鸡冠区', '230302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('684', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '恒山区', '230303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('685', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '滴道区', '230304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('686', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '梨树区', '230305', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('687', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '城子河区', '230306', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('688', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '麻山区', '230307', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('689', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '鸡东县', '230321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('69', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '迁西县', '130227', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('690', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '虎林市', '230381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('691', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '682', '密山市', '230382', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('692', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '鹤岗市', '230400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('693', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '692', '向阳区', '230402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('694', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '692', '工农区', '230403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('695', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '692', '南山区', '230404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('696', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '692', '兴安区', '230405', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('697', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '692', '东山区', '230406', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('698', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '692', '兴山区', '230407', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('699', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '692', '萝北县', '230421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('7', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '海淀区', '110108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('70', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '玉田县', '130229', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('700', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '692', '绥滨县', '230422', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('701', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '双鸭山市', '230500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('702', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '701', '尖山区', '230502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('703', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '701', '岭东区', '230503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('704', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '701', '四方台区', '230505', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('705', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '701', '宝山区', '230506', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('706', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '701', '集贤县', '230521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('707', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '701', '友谊县', '230522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('708', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '701', '宝清县', '230523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('709', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '701', '饶河县', '230524', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('71', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '遵化市', '130281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('710', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '大庆市', '230600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('711', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '萨尔图区', '230602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('712', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '龙凤区', '230603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('713', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '让胡路区', '230604', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('714', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '红岗区', '230605', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('715', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '大同区', '230606', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('716', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '肇州县', '230621', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('717', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '肇源县', '230622', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('718', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '林甸县', '230623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('719', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '710', '杜尔伯特蒙古族自治县', '230624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('72', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '迁安市', '130283', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('720', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '伊春市', '230700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('721', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '伊美区', '230717', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('722', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '乌翠区', '230718', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('723', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '友好区', '230719', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('724', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '嘉荫县', '230722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('725', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '汤旺县', '230723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('726', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '丰林县', '230724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('727', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '大箐山县', '230725', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('728', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '南岔县', '230726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('729', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '金林区', '230751', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('73', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '59', '滦州市', '130284', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('730', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '720', '铁力市', '230781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('731', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '佳木斯市', '230800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('732', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '向阳区', '230803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('733', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '前进区', '230804', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('734', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '东风区', '230805', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('735', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '郊区', '230811', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('736', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '桦南县', '230822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('737', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '桦川县', '230826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('738', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '汤原县', '230828', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('739', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '同江市', '230881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('74', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '秦皇岛市', '130300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('740', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '富锦市', '230882', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('741', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '731', '抚远市', '230883', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('742', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '七台河市', '230900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('743', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '742', '新兴区', '230902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('744', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '742', '桃山区', '230903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('745', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '742', '茄子河区', '230904', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('746', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '742', '勃利县', '230921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('747', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '牡丹江市', '231000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('748', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '东安区', '231002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('749', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '阳明区', '231003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('75', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '74', '海港区', '130302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('750', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '爱民区', '231004', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('751', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '西安区', '231005', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('752', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '林口县', '231025', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('753', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '绥芬河市', '231081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('754', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '海林市', '231083', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('755', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '宁安市', '231084', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('756', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '穆棱市', '231085', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('757', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '747', '东宁市', '231086', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('758', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '黑河市', '231100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('759', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '758', '爱辉区', '231102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('76', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '74', '山海关区', '130303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('760', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '758', '逊克县', '231123', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('761', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '758', '孙吴县', '231124', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('762', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '758', '北安市', '231181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('763', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '758', '五大连池市', '231182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('764', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '758', '嫩江市', '231183', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('765', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '绥化市', '231200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('766', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '北林区', '231202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('767', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '望奎县', '231221', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('768', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '兰西县', '231222', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('769', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '青冈县', '231223', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('77', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '74', '北戴河区', '130304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('770', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '庆安县', '231224', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('771', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '明水县', '231225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('772', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '绥棱县', '231226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('773', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '安达市', '231281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('774', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '肇东市', '231282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('775', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '765', '海伦市', '231283', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('776', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '645', '大兴安岭地区', '232700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('777', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '776', '漠河市', '232701', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('778', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '776', '呼玛县', '232721', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('779', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '776', '塔河县', '232722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('78', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '74', '抚宁区', '130306', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('780', NULL, NULL, NULL, '2020-06-09 14:05:16', 0, 0, '0', '上海', '310000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('781', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '黄浦区', '310101', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('782', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '徐汇区', '310104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('783', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '长宁区', '310105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('784', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '静安区', '310106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('785', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '普陀区', '310107', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('786', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '虹口区', '310109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('787', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '杨浦区', '310110', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('788', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '闵行区', '310112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('789', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '宝山区', '310113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('79', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '74', '青龙满族自治县', '130321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('790', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '嘉定区', '310114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('791', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '浦东新区', '310115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('792', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '金山区', '310116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('793', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '松江区', '310117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('794', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '青浦区', '310118', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('795', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '奉贤区', '310120', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('796', NULL, NULL, NULL, '2020-06-09 14:06:41', 0, 0, '3218', '崇明区', '310151', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('797', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '江苏省', '320000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('798', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '南京市', '320100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('799', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '玄武区', '320102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('8', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '门头沟区', '110109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('80', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '74', '昌黎县', '130322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('800', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '秦淮区', '320104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('801', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '建邺区', '320105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('802', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '鼓楼区', '320106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('803', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '浦口区', '320111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('804', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '栖霞区', '320113', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('805', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '雨花台区', '320114', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('806', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '江宁区', '320115', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('807', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '六合区', '320116', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('808', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '溧水区', '320117', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('809', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '798', '高淳区', '320118', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('81', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '74', '卢龙县', '130324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('810', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '无锡市', '320200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('811', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '810', '锡山区', '320205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('812', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '810', '惠山区', '320206', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('813', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '810', '滨湖区', '320211', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('814', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '810', '梁溪区', '320213', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('815', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '810', '新吴区', '320214', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('816', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '810', '江阴市', '320281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('817', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '810', '宜兴市', '320282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('818', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '徐州市', '320300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('819', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '鼓楼区', '320302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('82', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '35', '邯郸市', '130400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('820', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '云龙区', '320303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('821', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '贾汪区', '320305', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('822', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '泉山区', '320311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('823', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '铜山区', '320312', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('824', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '丰县', '320321', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('825', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '沛县', '320322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('826', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '睢宁县', '320324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('827', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '新沂市', '320381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('828', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '818', '邳州市', '320382', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('829', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '常州市', '320400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('83', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '邯山区', '130402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('830', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '829', '天宁区', '320402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('831', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '829', '钟楼区', '320404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('832', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '829', '新北区', '320411', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('833', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '829', '武进区', '320412', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('834', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '829', '金坛区', '320413', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('835', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '829', '溧阳市', '320481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('836', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '苏州市', '320500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('837', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '虎丘区', '320505', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('838', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '吴中区', '320506', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('839', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '相城区', '320507', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('84', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '丛台区', '130403', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('840', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '姑苏区', '320508', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('841', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '吴江区', '320509', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('842', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '常熟市', '320581', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('843', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '张家港市', '320582', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('844', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '昆山市', '320583', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('845', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '836', '太仓市', '320585', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('846', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '南通市', '320600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('847', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '846', '崇川区', '320602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('848', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '846', '港闸区', '320611', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('849', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '846', '通州区', '320612', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('85', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '复兴区', '130404', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('850', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '846', '如东县', '320623', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('851', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '846', '启东市', '320681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('852', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '846', '如皋市', '320682', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('853', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '846', '海门市', '320684', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('854', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '846', '海安市', '320685', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('855', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '连云港市', '320700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('856', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '855', '连云区', '320703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('857', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '855', '海州区', '320706', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('858', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '855', '赣榆区', '320707', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('859', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '855', '东海县', '320722', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('86', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '峰峰矿区', '130406', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('860', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '855', '灌云县', '320723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('861', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '855', '灌南县', '320724', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('862', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '淮安市', '320800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('863', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '862', '淮安区', '320803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('864', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '862', '淮阴区', '320804', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('865', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '862', '清江浦区', '320812', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('866', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '862', '洪泽区', '320813', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('867', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '862', '涟水县', '320826', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('868', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '862', '盱眙县', '320830', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('869', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '862', '金湖县', '320831', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('87', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '肥乡区', '130407', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('870', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '盐城市', '320900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('871', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '亭湖区', '320902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('872', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '盐都区', '320903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('873', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '大丰区', '320904', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('874', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '响水县', '320921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('875', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '滨海县', '320922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('876', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '阜宁县', '320923', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('877', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '射阳县', '320924', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('878', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '建湖县', '320925', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('879', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '870', '东台市', '320981', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('88', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '永年区', '130408', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('880', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '扬州市', '321000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('881', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '880', '广陵区', '321002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('882', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '880', '邗江区', '321003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('883', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '880', '江都区', '321012', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('884', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '880', '宝应县', '321023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('885', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '880', '仪征市', '321081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('886', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '880', '高邮市', '321084', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('887', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '镇江市', '321100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('888', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '887', '京口区', '321102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('889', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '887', '润州区', '321111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('89', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '临漳县', '130423', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('890', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '887', '丹徒区', '321112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('891', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '887', '丹阳市', '321181', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('892', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '887', '扬中市', '321182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('893', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '887', '句容市', '321183', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('894', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '泰州市', '321200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('895', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '894', '海陵区', '321202', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('896', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '894', '高港区', '321203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('897', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '894', '姜堰区', '321204', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('898', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '894', '兴化市', '321281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('899', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '894', '靖江市', '321282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('9', NULL, NULL, NULL, '2020-06-09 13:59:29', 0, 0, '3216', '房山区', '110111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('90', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '成安县', '130424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('900', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '894', '泰兴市', '321283', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('901', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '797', '宿迁市', '321300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('902', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '901', '宿城区', '321302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('903', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '901', '宿豫区', '321311', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('904', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '901', '沭阳县', '321322', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('905', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '901', '泗阳县', '321323', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('906', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '901', '泗洪县', '321324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('907', NULL, NULL, NULL, '2020-06-09 13:32:22', 0, 0, '0', '浙江省', '330000', NULL, 0);
INSERT INTO `t_sys_address` VALUES ('908', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '杭州市', '330100', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('909', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '上城区', '330102', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('91', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '大名县', '130425', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('910', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '下城区', '330103', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('911', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '江干区', '330104', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('912', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '拱墅区', '330105', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('913', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '西湖区', '330106', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('914', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '滨江区', '330108', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('915', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '萧山区', '330109', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('916', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '余杭区', '330110', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('917', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '富阳区', '330111', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('918', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '临安区', '330112', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('919', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '桐庐县', '330122', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('92', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '涉县', '130426', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('920', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '淳安县', '330127', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('921', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '908', '建德市', '330182', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('922', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '宁波市', '330200', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('923', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '海曙区', '330203', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('924', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '江北区', '330205', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('925', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '北仑区', '330206', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('926', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '镇海区', '330211', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('927', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '鄞州区', '330212', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('928', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '奉化区', '330213', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('929', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '象山县', '330225', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('93', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '磁县', '130427', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('930', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '宁海县', '330226', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('931', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '余姚市', '330281', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('932', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '922', '慈溪市', '330282', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('933', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '温州市', '330300', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('934', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '鹿城区', '330302', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('935', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '龙湾区', '330303', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('936', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '瓯海区', '330304', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('937', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '洞头区', '330305', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('938', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '永嘉县', '330324', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('939', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '平阳县', '330326', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('94', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '邱县', '130430', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('940', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '苍南县', '330327', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('941', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '文成县', '330328', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('942', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '泰顺县', '330329', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('943', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '瑞安市', '330381', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('944', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '乐清市', '330382', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('945', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '933', '龙港市', '330383', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('946', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '嘉兴市', '330400', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('947', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '946', '南湖区', '330402', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('948', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '946', '秀洲区', '330411', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('949', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '946', '嘉善县', '330421', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('95', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '鸡泽县', '130431', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('950', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '946', '海盐县', '330424', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('951', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '946', '海宁市', '330481', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('952', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '946', '平湖市', '330482', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('953', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '946', '桐乡市', '330483', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('954', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '湖州市', '330500', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('955', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '954', '吴兴区', '330502', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('956', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '954', '南浔区', '330503', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('957', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '954', '德清县', '330521', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('958', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '954', '长兴县', '330522', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('959', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '954', '安吉县', '330523', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('96', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '广平县', '130432', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('960', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '绍兴市', '330600', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('961', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '960', '越城区', '330602', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('962', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '960', '柯桥区', '330603', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('963', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '960', '上虞区', '330604', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('964', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '960', '新昌县', '330624', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('965', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '960', '诸暨市', '330681', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('966', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '960', '嵊州市', '330683', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('967', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '金华市', '330700', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('968', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '婺城区', '330702', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('969', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '金东区', '330703', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('97', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '馆陶县', '130433', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('970', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '武义县', '330723', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('971', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '浦江县', '330726', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('972', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '磐安县', '330727', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('973', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '兰溪市', '330781', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('974', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '义乌市', '330782', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('975', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '东阳市', '330783', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('976', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '967', '永康市', '330784', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('977', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '衢州市', '330800', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('978', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '977', '柯城区', '330802', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('979', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '977', '衢江区', '330803', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('98', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '魏县', '130434', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('980', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '977', '常山县', '330822', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('981', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '977', '开化县', '330824', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('982', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '977', '龙游县', '330825', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('983', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '977', '江山市', '330881', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('984', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '舟山市', '330900', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('985', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '984', '定海区', '330902', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('986', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '984', '普陀区', '330903', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('987', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '984', '岱山县', '330921', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('988', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '984', '嵊泗县', '330922', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('989', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '台州市', '331000', NULL, 1);
INSERT INTO `t_sys_address` VALUES ('99', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '82', '曲周县', '130435', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('990', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '椒江区', '331002', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('991', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '黄岩区', '331003', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('992', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '路桥区', '331004', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('993', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '三门县', '331022', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('994', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '天台县', '331023', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('995', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '仙居县', '331024', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('996', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '温岭市', '331081', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('997', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '临海市', '331082', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('998', NULL, NULL, NULL, '2020-06-08 10:21:49', 0, 0, '989', '玉环市', '331083', NULL, 2);
INSERT INTO `t_sys_address` VALUES ('999', NULL, NULL, NULL, '2020-06-08 10:21:12', 0, 0, '907', '丽水市', '331100', NULL, 1);

-- ----------------------------
-- Table structure for t_sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_authority`;
CREATE TABLE `t_sys_authority`  (
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
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '接口禁用(0-否 1-是)',
  `type` int(1) NOT NULL COMMENT '终端(字典code, 如 0-管理端 1-用户端 更多待定)',
  `state` int(1) NOT NULL COMMENT '授权状态(字典code  0-无需登录 1-需登录 2-需登录+授权(已废弃) 3-需Oauth2 授权 )',
  `is_sign` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要验签(不受限于登录授权)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--URL权限管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_authority
-- ----------------------------
INSERT INTO `t_sys_authority` VALUES ('1620238841316827136', NULL, NULL, '2023-01-31 09:53:41', '2023-01-31 09:53:41', 0, 117, '741218298418565120', 'DELETE', '/api/open/file/delFolder', '文件目录删除', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('1620706738136829952', NULL, NULL, '2023-02-01 16:52:58', '2023-02-01 16:52:58', 0, 54, '720779973740335104', 'GET', '/api/open/websocket/isOnline', '判断指定用户是否在线', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('1621020034236874752', NULL, NULL, '2023-02-02 13:37:51', '2023-02-02 13:37:51', 0, 33, '720779973740335104', 'GET', '/api/open/websocket/getOnlineUsers', '获取当前在线用户列表', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('1639086330523389952', NULL, NULL, '2023-03-24 10:06:53', '2023-03-24 10:06:53', 0, 9, '0', '', '/api/admin/sys/address', '', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('1639086330523389953', NULL, NULL, '2023-03-24 10:06:53', '2023-03-24 10:06:53', 0, 9, '1639086330523389952', 'POST', '/api/admin/sys/address', '添加', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('1639086330523389954', NULL, NULL, '2023-03-24 10:06:53', '2023-03-24 10:06:53', 0, 9, '1639086330523389952', 'GET', '/api/admin/sys/address/tree', '树结构数据', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('1639086330523389955', NULL, NULL, '2023-03-24 10:06:53', '2023-03-24 10:06:53', 0, 9, '1639086330523389952', 'GET', '/api/admin/sys/address/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('1639086330523389956', NULL, NULL, '2023-03-24 10:06:53', '2023-03-24 10:06:53', 0, 9, '1639086330523389952', 'GET', '/api/admin/sys/address/{id}', 'ID查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('1639086330523389957', NULL, NULL, '2023-03-24 10:06:53', '2023-03-24 10:06:53', 0, 9, '1639086330523389952', 'DELETE', '/api/admin/sys/address/{id}', 'ID删除', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('1639086330523389958', NULL, NULL, '2023-03-24 10:06:53', '2023-03-24 10:06:53', 0, 9, '1639086330523389952', 'PUT', '/api/admin/sys/address/{id}', 'ID编辑', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('697255086296010752', '-1', '0', '2022-06-28 11:29:37', '2022-10-30 16:10:20', 0, 626, '0', '', '/api/open/redis', 'Redis  -->  Redis 测试', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('697398103711551488', '-1', '0', '2022-06-28 12:57:58', '2022-10-30 16:10:21', 0, 624, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest2/{key}', 'redis 分布式锁加锁测试2', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('697398103711551489', '-1', '0', '2022-06-28 12:57:58', '2022-10-30 16:10:21', 0, 624, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest1/{key}', 'redis 分布式锁加锁测试', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('697398103711551490', '-1', '0', '2022-06-28 12:57:58', '2022-10-30 16:10:21', 0, 624, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest3', 'redis 分布式注解锁测试', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('700879734145421312', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:22', 0, 590, '0', '', '/api/admin/test/gcTest', '代码生成测试表', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('700879734145421314', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:22', 0, 590, '700879734145421312', 'POST', '/api/admin/test/gcTest', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('700879734145421315', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:22', 0, 590, '700879734145421312', 'DELETE', '/api/admin/test/gcTest/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('700879734149615616', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:22', 0, 590, '700879734145421312', 'PUT', '/api/admin/test/gcTest/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('700879734149615617', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:23', 0, 590, '700879734145421312', 'GET', '/api/admin/test/gcTest/{id}', 'ID查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('720779973740335104', '-1', NULL, '2022-08-28 17:29:10', '2022-10-30 16:10:23', 0, 454, '0', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('720779973740335105', '-1', NULL, '2022-08-28 17:29:10', '2022-10-30 16:10:23', 0, 454, '720779973740335104', 'GET', '/api/open/websocket/getPath', '获取模拟游客登录的 websocket 连接地址', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('720779973740335106', '-1', NULL, '2022-08-28 17:29:10', '2022-10-30 16:10:23', 0, 454, '720779973740335104', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('720779973740335108', '-1', NULL, '2022-08-28 17:29:10', '2022-10-30 16:10:24', 0, 454, '720779973740335104', 'GET', '/api/open/websocket/getOnlineCount', '获取当前在线人数', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('721146444106567680', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:24', 0, 432, '0', '', '/api/admin/sys/user', 'base--sys--用户管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444106567681', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:24', 0, 432, '721146444106567680', 'POST', '/api/admin/sys/user', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444106567682', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:24', 0, 432, '721146444106567680', 'GET', '/api/admin/sys/user/{id}', 'ID查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444106567683', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:24', 0, 432, '721146444106567680', 'PUT', '/api/admin/sys/user/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761984', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 432, '721146444106567680', 'DELETE', '/api/admin/sys/user/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761985', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 432, '721146444106567680', 'GET', '/api/admin/sys/user/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761986', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 432, '721146444106567680', 'PUT', '/api/admin/sys/user/{id}/resetPassword', '重置任意用户密码', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761987', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 432, '721146444106567680', 'PUT', '/api/admin/sys/user/updByPassword', '修改当前登录人的密码', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761988', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 432, '721146444106567680', 'GET', '/api/admin/sys/user/findByRoleId', '获取指定角色的用户列表', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761989', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:26', 0, 432, '721146444106567680', 'GET', '/api/admin/sys/user/findUser', '查询当前登录人的个人信息', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761990', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:26', 0, 432, '721146444106567680', 'PUT', '/api/admin/sys/user/updUser', '修改当前登录人的信息', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761991', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:26', 0, 432, '721146444106567680', 'GET', '/api/admin/sys/user/list/keyData', '查询所有-只返回关键数据(姓名/昵称/电话/id)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761992', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:26', 0, 432, '721146444106567680', 'POST', '/api/admin/sys/user/login', '用户登录', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956288', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 432, '0', '', '/api/admin/sys/dictionary', 'base--sys--字典管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956289', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 432, '721146444114956288', 'GET', '/api/admin/sys/dictionary/list', '列表查询 (默认返回Tree数据,可指定Tree或List)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956290', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 432, '721146444114956288', 'POST', '/api/admin/sys/dictionary', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956291', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 432, '721146444114956288', 'PUT', '/api/admin/sys/dictionary/{id}', '编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956292', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 432, '721146444114956288', 'DELETE', '/api/admin/sys/dictionary/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956293', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:28', 0, 432, '721146444114956288', 'GET', '/api/admin/sys/dictionary/findCodeGroup', '查询所有-code分组', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956294', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:28', 0, 432, '721146444114956288', 'GET', '/api/admin/sys/dictionary/generateEnum', '生成枚举', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150592', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:28', 0, 432, '721146444114956288', 'GET', '/api/admin/sys/dictionary/list/category', '获取类别(级联数据)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150593', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:28', 0, 432, '0', '', '/api/admin/sys/dep', 'base--sys--组织机构', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150595', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:29', 0, 432, '721146444119150593', 'POST', '/api/admin/sys/dep', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150596', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:29', 0, 432, '721146444119150593', 'PUT', '/api/admin/sys/dep/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150597', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:30', 0, 432, '721146444119150593', 'DELETE', '/api/admin/sys/dep/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344899', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 432, '0', '', '/api/admin/sys/menu', 'base--sys--菜单管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344901', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 432, '721146444123344899', 'POST', '/api/admin/sys/menu', '菜单添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344902', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 432, '721146444123344899', 'PUT', '/api/admin/sys/menu/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344903', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 432, '721146444123344899', 'DELETE', '/api/admin/sys/menu/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344904', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 432, '721146444123344899', 'GET', '/api/admin/sys/menu/findTree', '左导航菜单', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733504', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 432, '0', '', '/api/admin/sys/role', 'base--sys--角色管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733505', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:32', 0, 432, '721146444131733504', 'POST', '/api/admin/sys/role', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733506', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:32', 0, 432, '721146444131733504', 'PUT', '/api/admin/sys/role/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733507', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:32', 0, 432, '721146444131733504', 'DELETE', '/api/admin/sys/role/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733508', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 432, '721146444131733504', 'GET', '/api/admin/sys/role/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927808', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 432, '0', '', '/api/admin/sys/blacklist', 'base--sys--黑名单', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927809', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 432, '721146444135927808', 'POST', '/api/admin/sys/blacklist', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927810', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 432, '721146444135927808', 'PUT', '/api/admin/sys/blacklist/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927811', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 432, '721146444135927808', 'DELETE', '/api/admin/sys/blacklist/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927812', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:34', 0, 432, '721146444135927808', 'GET', '/api/admin/sys/blacklist/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444140122112', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:34', 0, 432, '0', '', '/api/admin/sys/authority', 'base--sys--URL权限管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444140122113', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:34', 0, 432, '721146444140122112', 'GET', '/api/admin/sys/authority/list', '查询所有-接口管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444140122114', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:34', 0, 432, '721146444140122112', 'PUT', '/api/admin/sys/authority/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444140122115', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:35', 0, 432, '721146444140122112', 'PUT', '/api/admin/sys/authority/refreshAuthority', '扫描权限', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316416', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:35', 0, 432, '0', '', '/api/admin/sys/config', 'base--sys--全局配置', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316417', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:35', 0, 432, '721146444144316416', 'POST', '/api/admin/sys/config', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316418', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:35', 0, 432, '721146444144316416', 'GET', '/api/admin/sys/config/{id}', 'ID查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316419', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:36', 0, 432, '721146444144316416', 'PUT', '/api/admin/sys/config/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316420', '-1', NULL, '2022-08-29 17:45:22', '2022-11-29 17:22:53', 0, 432, '721146444144316416', 'DELETE', '/api/admin/sys/config/{id}', 'ID删除', 0, 0, 3, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316421', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:36', 0, 432, '721146444144316416', 'GET', '/api/admin/sys/config/findPage', '分页查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316422', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:36', 0, 432, '721146444144316416', 'GET', '/api/admin/sys/config/findByCode', 'CODE查询', 0, 0, 0, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805924446208', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:36', 0, 427, '0', '', '/api/admin/gc/dataBase', 'base--gc--代码生成--查询表数据', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805928640512', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:37', 0, 427, '721151805924446208', 'GET', '/api/admin/gc/dataBase/table/list', '查询所有表名', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805928640513', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:37', 0, 427, '721151805924446208', 'GET', '/api/admin/gc/dataBase/table/field', '查询指定表下所有字段内容', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834816', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:37', 0, 427, '0', '', '/api/admin/gc/generate', 'base--gc--代码生成', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834817', '-1', NULL, '2022-08-29 18:06:44', '2023-01-08 12:05:14', 0, 427, '721151805932834816', 'GET', '/api/admin/gc/generate/getPath', '代码生成路径查看', 0, 0, 0, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834818', '-1', NULL, '2022-08-29 18:06:44', '2022-11-21 00:05:45', 0, 427, '721151805932834816', 'POST', '/api/admin/gc/generate/generateCode', '生成代码', 0, 0, 3, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834819', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:38', 0, 427, '721151805932834816', 'POST', '/api/admin/gc/generate/preview', '生成预览代码', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834820', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:38', 0, 427, '721151805932834816', 'POST', '/api/admin/gc/generate/generateCodeVue', '生成Vue代码(将直接下载)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834821', '-1', NULL, '2022-08-29 18:06:44', '2023-02-02 09:48:00', 0, 427, '721151805932834816', 'POST', '/api/admin/gc/generate/generateCodeJavaAndVue', '生成java + vue代码(将直接下载)', 0, 0, 0, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834822', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:38', 0, 427, '0', '', '/api/admin/gc/datasource', 'base--gc--代码生成--数据源维护', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029120', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:39', 0, 427, '721151805932834822', 'POST', '/api/admin/gc/datasource', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029121', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:39', 0, 427, '721151805932834822', 'GET', '/api/admin/gc/datasource/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029122', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:39', 0, 427, '721151805932834822', 'DELETE', '/api/admin/gc/datasource/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029123', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:39', 0, 427, '721151805932834822', 'PUT', '/api/admin/gc/datasource/{id}/updPwd', '修改/重置密码', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029124', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:40', 0, 427, '721151805932834822', 'GET', '/api/admin/gc/datasource/{id}', 'id查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029125', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:40', 0, 427, '721151805932834822', 'POST', '/api/admin/gc/datasource/dataSourceTest/{id}', '数据源连接测试', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029126', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:40', 0, 427, '721151805932834822', 'PUT', '/api/admin/gc/datasource/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721158911297720320', '-1', NULL, '2022-08-29 18:34:57', '2022-10-30 16:10:40', 0, 425, '0', '', '/api/admin/sys/jvm', 'base--sys--jvm信息获取', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721158911297720321', '-1', NULL, '2022-08-29 18:34:57', '2022-10-30 16:10:41', 0, 425, '721158911297720320', 'GET', '/api/admin/sys/jvm/jvmInfo', '获取系统的jvm信息', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721158911301914624', '-1', NULL, '2022-08-29 18:34:57', '2022-10-30 16:10:41', 0, 425, '0', '', '/api/admin/sys/log', 'base--sys--操作记录', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721158911301914625', '-1', NULL, '2022-08-29 18:34:57', '2022-10-30 16:10:41', 0, 425, '721158911301914624', 'GET', '/api/admin/sys/log/findPage', '分页查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884352', '-1', NULL, '2022-08-29 18:42:53', '2022-10-30 16:10:43', 0, 422, '0', '', '/api/admin/sys/msg', 'base--sys--消息通知', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884353', '-1', NULL, '2022-08-29 18:42:53', '2022-11-25 15:14:06', 0, 422, '721160918934884352', 'POST', '/api/admin/sys/msg', '添加/发送消息', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884354', '-1', NULL, '2022-08-29 18:42:53', '2022-11-14 16:51:15', 0, 422, '721160918934884352', 'GET', '/api/admin/sys/msg/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884355', '-1', NULL, '2022-08-29 18:42:53', '2022-10-30 16:10:44', 0, 422, '721160918934884352', 'GET', '/api/admin/sys/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884356', '-1', NULL, '2022-08-29 18:42:53', '2022-10-30 16:10:44', 0, 422, '721160918934884352', 'PUT', '/api/admin/sys/msg/{id}/read', '消息修改为已读', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884357', '-1', NULL, '2022-08-29 18:42:53', '2022-10-30 16:10:44', 0, 422, '721160918934884352', 'GET', '/api/admin/sys/msg/findAllNum', '查询全部/已读/未读数量(当前登录用户)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641344', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 420, '0', '', '/api/admin/sys/banner', 'base--sys--banner', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641345', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 420, '721162557955641344', 'POST', '/api/admin/sys/banner', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641346', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 420, '721162557955641344', 'GET', '/api/admin/sys/banner/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641347', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 420, '721162557955641344', 'DELETE', '/api/admin/sys/banner/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641348', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 420, '721162557955641344', 'PUT', '/api/admin/sys/banner/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('724479128698490880', '-1', NULL, '2022-09-06 22:28:14', '2022-10-30 16:10:46', 0, 404, '697255086296010752', 'GET', '/api/open/redis/getDataNo', '获取分布式唯一数据编号', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('724479128698490881', '-1', NULL, '2022-09-06 22:28:14', '2022-10-30 16:10:46', 0, 404, '697255086296010752', 'GET', '/api/open/redis/getOrderNo', '获取分布式唯一订单号', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('733526914786004992', '-1', NULL, '2022-09-30 21:40:57', '2022-10-30 16:10:46', 0, 311, '700879734145421312', 'GET', '/api/admin/test/gcTest/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298418565120', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:46', 0, 288, '0', '', '/api/open/file', '文件管理', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298418565122', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:46', 0, 288, '741218298418565120', 'GET', '/api/open/file/downloadZip', '文件下载--多文件下载 (批量下载-打压缩包)', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298418565123', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:47', 0, 288, '741218298418565120', 'GET', '/api/open/file/download', '文件下载--单文件下载', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298422759424', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:47', 0, 288, '741218298418565120', 'POST', '/api/open/file/upload', '文件上传,可在指定路径后追加子路径,以/结尾，上传成功返回完整可访问URL', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298422759425', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:47', 0, 288, '741218298418565120', 'DELETE', '/api/open/file/del', '文件删除', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('756396041112260608', NULL, NULL, '2022-11-26 16:14:51', '2022-11-26 16:14:51', 0, 240, '721146444123344899', 'GET', '/api/admin/sys/menu/tree', '列表查询(不支持分页)', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903468490752', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '0', '', '/api/client/sys/config', 'yh--base-plus--全局配置', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903468490753', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '760767903468490752', 'GET', '/api/client/sys/config/findByCode', 'CODE查询', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903476879360', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '0', '', '/api/client/sys/banner', 'yh--base-plus--banner', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903476879361', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '760767903476879360', 'GET', '/api/client/sys/banner/list/{position}', '列表-位置查询', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903485267968', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '0', '', '/api/client/sys/msg', 'yh--base-plus--消息通知', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903485267969', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '760767903485267968', 'GET', '/api/client/sys/msg/findPage', '分页查询', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903485267970', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '760767903485267968', 'GET', '/api/client/sys/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903485267971', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '760767903485267968', 'PUT', '/api/client/sys/msg/{id}/read', '消息修改为已读', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903489462272', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '0', '', '/api/client/sys/dictionary', 'yh--base--字典管理', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903489462273', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '760767903489462272', 'GET', '/api/client/sys/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903489462274', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 230, '760767903489462272', 'GET', '/api/client/sys/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820955799552', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '0', '', '/api/admin/test/gcMenu', '基础表--菜单', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820955799553', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '771899820955799552', 'POST', '/api/admin/test/gcMenu', '添加', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820955799554', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '771899820955799552', 'GET', '/api/admin/test/gcMenu/tree', '树结构数据', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820959993856', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '771899820955799552', 'GET', '/api/admin/test/gcMenu/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820959993857', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '771899820955799552', 'DELETE', '/api/admin/test/gcMenu/{id}', 'ID删除', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820959993858', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '771899820955799552', 'GET', '/api/admin/test/gcMenu/{id}', 'ID查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820959993859', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '771899820955799552', 'PUT', '/api/admin/test/gcMenu/{id}', 'ID编辑', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820972576768', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '721146444119150593', 'GET', '/api/admin/sys/dep/tree', '树结构数据', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820972576769', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '721146444119150593', 'GET', '/api/admin/sys/dep/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820972576770', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 144, '721146444119150593', 'GET', '/api/admin/sys/dep/{id}', 'ID查询', 0, 0, 1, 0);

-- ----------------------------
-- Table structure for t_sys_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_banner`;
CREATE TABLE `t_sys_banner`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--banner' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_banner
-- ----------------------------
INSERT INTO `t_sys_banner` VALUES ('1300260217146548226', '-1', '-1', '2020-08-31 10:32:48', '2022-10-30 16:10:36', 0, 0, 1, '测试2', '测试数据2', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210401214853224489-qs44ufe2024qs44ufe2024.jpg', 4, 0, 0, '/page/logoBanner/page/logoBanner');
INSERT INTO `t_sys_banner` VALUES ('1300262684328435714', '-1', '-1', '2020-08-31 10:42:36', '2022-12-04 17:23:28', 0, 0, 1, '测试1', '测试数据一', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210322103754968659-aaaa.png', 2, 0, 2, 'http://www.baidu.com');
INSERT INTO `t_sys_banner` VALUES ('1309111625118248961', '-1', '-1', '2020-09-24 20:45:06', '2023-01-08 11:27:57', 0, 0, 1, '测试', '测试描叙', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/2.jpg', 1, 0, 2, '');
INSERT INTO `t_sys_banner` VALUES ('1459838027261095938', '-1', '-1', '2021-11-14 18:58:02', '2022-12-04 17:23:29', 0, 0, 1, '测试', '啦啦啦啦', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/file/gc/41883812-timg(2).jpg', 3, 0, 0, '');

-- ----------------------------
-- Table structure for t_sys_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_blacklist`;
CREATE TABLE `t_sys_blacklist`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--黑名单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_blacklist
-- ----------------------------
INSERT INTO `t_sys_blacklist` VALUES ('1332333202949324802', '-1', '-1', '2020-11-27 22:39:21', '2023-03-22 16:07:47', 0, 0, 1, '*', '允许所有 ip 地址访问，优先级比黑名单(*) 高 ， 开启了白名单(*), 黑名单（*）将无效', 1);
INSERT INTO `t_sys_blacklist` VALUES ('1332337401510551554', '-1', '-1', '2020-11-27 22:56:02', '2022-10-30 16:10:41', 1, 0, 2, '*', '禁止所有 ip 访问,除本地 [127.0.0.1 / localhost] ,不建议配置在所有资源上，一旦配置，所有用户(包括自己) 将无法访问所有资源，因为每个用户的ip地址都不一样， 开启此功能需提前配置所有用户的ip地址为白名单', 0);
INSERT INTO `t_sys_blacklist` VALUES ('1421369811404894210', '-1', '-1', '2021-07-31 15:19:05', '2023-01-03 02:50:54', 1, 0, 2, '192.168.1.10', '本地', 0);
INSERT INTO `t_sys_blacklist` VALUES ('1462259668311121921', '-1', '-1', '2021-11-21 11:20:47', '2022-10-30 16:10:41', 1, 0, 1, '1', '1', 0);

-- ----------------------------
-- Table structure for t_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_config`;
CREATE TABLE `t_sys_config`  (
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
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '类型(0-文本 1-图片 2-开关 3-富文本)',
  `desc` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `ext1` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--全局配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_config
-- ----------------------------
INSERT INTO `t_sys_config` VALUES ('1365182627308433409', NULL, '-1', '2021-02-26 14:11:17', '2022-12-29 11:17:45', 0, 0, 'img-test', '背景图(登录页)', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210311113615990505-1.jpg', 999, 1, '暂未使用', NULL, NULL, NULL);
INSERT INTO `t_sys_config` VALUES ('1383627414470467586', NULL, '-1', '2021-04-18 11:44:16', '2022-12-08 17:52:23', 0, 0, 'is_sign', '验签开关', 'false', 3, 2, '验签总开关 |  true  需验签(默认)   false=无需验签, 开启后可在接口管理中对单个接口进行配置', NULL, NULL, NULL);
INSERT INTO `t_sys_config` VALUES ('1383636872395255809', NULL, '-1', '2021-04-18 12:21:51', '2023-01-08 12:04:41', 0, 0, 'is_swagger', 'swagger文档开关', 'true', 66, 2, '动态开关是否可在线查看接口文档，关闭后所有接口将隐藏展示', NULL, NULL, NULL);
INSERT INTO `t_sys_config` VALUES ('1441701074921598977', NULL, '-1', '2021-09-25 17:48:16', '2022-10-15 17:21:21', 0, 0, 'login_expiration_manage', '登录有效期', '60', 1, 0, '登录状态切对当前系统无如何操作后，当前登录状态保持时长,  防止离开后被别人操作， 单位分 （配置内容针对的是管理端）', NULL, NULL, NULL);
INSERT INTO `t_sys_config` VALUES ('1591258898825293825', NULL, NULL, '2022-11-12 10:37:40', '2022-12-29 11:17:44', 0, 0, 'markdown-test', '11', '\n\n## 提示\n::: tip \n  你可以点击 toolbar 中的 tip 来快速插入\n:::\n\n::: warning\n  这是一段警告\n:::\n\n::: danger\n这是一个危险警告\n:::\n\n::: details\n  这是一个详情块，在 IE / Edge 中不生效\n:::\n\n::: tip 自定义标题\n  你也可以自定义块中的标题\n:::\n\n::: danger STOP\n  危险区域，禁止通行\n:::\n\n## 代码块\n```v\n// v-md-editor 插件\nimport VueMarkdownEditor from \'@kangc/v-md-editor\';\nimport \'@kangc/v-md-editor/lib/style/base-editor.css\';\nimport vuepressTheme from \'@kangc/v-md-editor/lib/theme/vuepress.js\';\nimport \'@kangc/v-md-editor/lib/theme/style/vuepress.css\';\nimport Prism from \'prismjs\';\nVueMarkdownEditor.use(vuepressTheme, {\n  Prism,\n});\n\n```\n\n\n```json\n{\n  \"name\": \"avue-cli\",\n  \"version\": \"2.0.0\",\n  \"private\": true,\n  \"scripts\": {\n    \"serve\": \"vue-cli-service serve\",\n    \"build\": \"vue-cli-service build\",\n    \"lint\": \"vue-cli-service lint\",\n    \"analyz\": \"npm_config_report=true npm run build\",\n    \"test:unit\": \"vue-cli-service test:unit\",\n    \"test:e2e\": \"vue-cli-service test:e2e\"\n  },\n```\n\n## 内容定位\n::: align-left\n  left\n:::\n\n::: align-center\n  center\n:::\n\n::: align-right\n  right\n:::\n\n', 999, 4, '', '', '', '');
INSERT INTO `t_sys_config` VALUES ('1597859174027370498', NULL, NULL, '2022-11-30 15:44:47', '2022-12-29 11:17:44', 0, 0, 'vue-tinymce-test', '1', '<p>1</p>', 999, 3, '', '', '', '');

-- ----------------------------
-- Table structure for t_sys_dep
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dep`;
CREATE TABLE `t_sys_dep`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父Id (顶级父id=0)',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司/部门名称',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司/部门描叙',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司/部门编码 (便于查询使用,不可重复)',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--组织机构' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_dep
-- ----------------------------
INSERT INTO `t_sys_dep` VALUES ('1443502302433439746', '-1', '-1', '2021-09-30 17:05:42', '2022-10-30 16:10:54', 0, 0, '1443502090977603585', '测试部', '-', 'csb', 0, 0);
INSERT INTO `t_sys_dep` VALUES ('1443502428644241409', '-1', '-1', '2021-09-30 17:06:12', '2022-10-30 16:10:54', 0, 0, '1443502157943861250', '运营部', '-', 'yyb', 0, 0);
INSERT INTO `t_sys_dep` VALUES ('1481913168983756802', '-1', '-1', '2022-01-14 16:56:46', '2022-10-30 16:10:55', 0, 0, '1481913127925714945', 'xx部门', '-', 'xx-dep', 0, 0);
INSERT INTO `t_sys_dep` VALUES ('1481913213086863362', '-1', '-1', '2022-01-14 16:56:57', '2022-10-30 16:10:55', 0, 0, '1481913127925714945', 'xx部门2', '-', 'xx-dep2', 0, 0);
INSERT INTO `t_sys_dep` VALUES ('1548901388543594498', '-1', '-1', '2022-07-18 13:24:02', '2022-12-29 10:48:22', 0, 0, '0', '测试公司1', '测试公司1的描述', 'TEST01', 1, 0);
INSERT INTO `t_sys_dep` VALUES ('1548901468621246466', '-1', '-1', '2022-07-18 13:24:22', '2022-10-30 16:10:55', 0, 0, '1548901388543594498', '部门1', '部门1描述', '1', 3, 0);
INSERT INTO `t_sys_dep` VALUES ('1548901576150618114', '-1', '-1', '2022-07-18 13:24:47', '2023-02-03 09:20:05', 0, 0, '1548901468621246466', '部门1_1', '部门1_1desc', '3', 0, 0);
INSERT INTO `t_sys_dep` VALUES ('1560880103058071554', '-1', '-1', '2022-08-20 14:43:09', '2023-03-24 11:24:33', 0, 0, '1608290949786574850', '部门3', '-', 'code3', 0, 0);
INSERT INTO `t_sys_dep` VALUES ('1608290949786574850', NULL, NULL, '2022-12-29 10:36:56', '2023-03-24 11:24:18', 0, 0, '0', '测试公司2', '-', 'code2', 2, 0);

-- ----------------------------
-- Table structure for t_sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dictionary`;
CREATE TABLE `t_sys_dictionary`  (
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
  `ext1` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--字典管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_dictionary
-- ----------------------------
INSERT INTO `t_sys_dictionary` VALUES ('1290684671448936449', '-1', '-1', '2020-08-05 00:23:00', '2023-02-01 19:30:53', 0, 0, 'ENUMS', '枚举字典', '0', '状态/动态字段值，如：state，type，gender等, 可直接生成 前/ 后端枚举对象类代码', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290686507555844098', '-1', '-1', '2020-08-05 00:30:17', '2022-10-30 16:11:04', 0, 0, 'ADMIN', '系统枚举(动态值)', '1290684671448936449', '-', 3, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290687277911076865', '-1', '-1', '2020-08-05 00:33:21', '2022-10-30 16:11:04', 0, 0, 'MENU_ROOT', '菜单级别', '1290688121255587841', '【固定值】', 1000, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290687351005212673', '-1', '-1', '2020-08-05 00:33:38', '2022-10-30 16:11:04', 0, 0, '1', '顶部菜单', '1290687277911076865', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290687461252493314', '-1', '-1', '2020-08-05 00:34:05', '2022-10-30 16:11:04', 0, 0, '2', '菜单', '1290687277911076865', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290687547940368386', '-1', '-1', '2020-08-05 00:34:25', '2022-10-30 16:11:05', 0, 0, '3', '页面', '1290687277911076865', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290688121255587841', '-1', '-1', '2020-08-05 00:36:42', '2023-01-08 11:25:43', 0, 0, 'BASE', '系统枚举(固定值)', '1290684671448936449', '-', 2, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290688660164931586', '-1', '-1', '2020-08-05 00:38:51', '2022-12-29 11:12:46', 0, 0, 'GENDER', '性别', '1290688121255587841', '【固定值】', 700, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290688703043301378', '-1', '-1', '2020-08-05 00:39:01', '2022-10-30 16:11:05', 0, 0, '1', '男', '1290688660164931586', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1290688742289403906', '-1', '-1', '2020-08-05 00:39:10', '2022-10-30 16:11:06', 0, 0, '2', '女', '1290688660164931586', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1291341478399897601', '-1', '-1', '2020-08-06 11:52:54', '2022-10-30 16:11:06', 0, 0, '0', '未知', '1290688660164931586', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296469448399593474', '-1', '-1', '2020-08-20 15:29:38', '2022-10-30 16:11:07', 0, 0, 'DISABLE', '是否禁用', '1290688121255587841', '【固定值】', 600, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296469518025039873', '-1', '-1', '2020-08-20 15:29:55', '2022-10-30 16:11:07', 0, 0, '1', '禁用', '1296469448399593474', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296469564455985154', '-1', '-1', '2020-08-20 15:30:06', '2022-10-30 16:11:07', 0, 0, '0', '启用', '1296469448399593474', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296995475064434690', '-1', '-1', '2020-08-22 02:19:52', '2022-10-30 16:11:07', 0, 0, 'AUTHORITY_TYPE', '权限类型', '1290688121255587841', '【固定值】：用于新接口自动生成【权限状态】', 900, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296995560007479298', '-1', '-1', '2020-08-22 02:20:12', '2022-10-30 16:11:08', 0, 0, '0', '管理端接口', '1296995475064434690', '管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296995610632728578', '-1', '-1', '2020-08-22 02:20:24', '2022-10-30 16:11:08', 0, 0, '1', '用户端接口', '1296995475064434690', '用户端, 类上标有该参数所有接口都会默认被列为-[需登录]', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296995742778470401', '-1', '-1', '2020-08-22 02:20:55', '2022-10-30 16:11:08', 0, 0, 'AUTHORITY_STATE', '权限状态', '1290688121255587841', '【固定值】：用于编辑指定接口的【权限状态】', 800, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296995839977271297', '-1', '-1', '2020-08-22 02:21:19', '2022-10-30 16:11:08', 0, 0, '0', '无', '1296995742778470401', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296996062090833922', '-1', '-1', '2020-08-22 02:22:12', '2022-10-30 16:11:08', 0, 0, '1', '需登录', '1296995742778470401', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1296996224863383554', '-1', '-1', '2020-08-22 02:22:50', '2022-10-30 16:11:09', 0, 0, '2', '需登录+授权', '1296995742778470401', '-已移除角色关联url, 菜单即权限', 0, 1, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1297705363906273282', '-1', '-1', '2020-08-24 01:20:43', '2022-10-30 16:11:09', 0, 0, '2', '通用接口', '1296995475064434690', '通用接口, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1298191840981327873', '-1', '-1', '2020-08-25 09:33:48', '2022-10-30 16:11:09', 0, 0, 'DELETED', '逻辑删除', '1290688121255587841', '【固定值】', 500, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1298191898313269249', '-1', '-1', '2020-08-25 09:34:02', '2022-10-30 16:11:09', 0, 0, '0', '正常', '1298191840981327873', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1298191981998022658', '-1', '-1', '2020-08-25 09:34:22', '2022-10-30 16:11:09', 0, 0, '1', '已删除', '1298191840981327873', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1298194668697198594', '-1', '-1', '2020-08-25 09:45:02', '2022-12-29 11:09:28', 0, 0, 'BANNER_IS_SKIP', '是否跳转', '1290688121255587841', '【固定值】', 1100, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1298194722350735361', '-1', '-1', '2020-08-25 09:45:15', '2022-10-30 16:11:10', 0, 0, '0', '否', '1298194668697198594', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1298194801014906881', '-1', '-1', '2020-08-25 09:45:34', '2022-10-30 16:11:10', 0, 0, '1', '内部跳转', '1298194668697198594', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1298194850285395969', '-1', '-1', '2020-08-25 09:45:46', '2022-10-30 16:11:10', 0, 0, '2', '外部跳转', '1298194668697198594', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1303872194494435330', '-1', '-1', '2020-09-10 09:45:30', '2022-10-30 16:11:10', 0, 0, 'BANNER_POSITION', 'banner 位置', '1290686507555844098', '【动态值】', 300, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1303872308608864257', '-1', '-1', '2020-09-10 09:45:57', '2022-10-30 16:11:11', 0, 0, '1', '用户端首页', '1303872194494435330', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1308580167513370625', '-1', '-1', '2020-09-23 09:33:17', '2022-10-30 16:11:11', 0, 0, 'MSG_USER_TYPE', '及时消息终端', '1290686507555844098', '【动态值】', 500, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1308580236161544193', '-1', '-1', '2020-09-23 09:33:33', '2022-10-30 16:11:11', 0, 0, '1', '用户端', '1308580167513370625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1308580275248263169', '-1', '-1', '2020-09-23 09:33:42', '2022-10-30 16:11:11', 0, 0, '2', '管理端', '1308580167513370625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1308585499920769025', '-1', '-1', '2020-09-23 09:54:28', '2022-10-30 16:11:11', 0, 0, 'MSG_TYPE', '及时消息类型', '1290686507555844098', '【动态值】', 400, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1308585615968772098', '-1', '-1', '2020-09-23 09:54:56', '2022-10-30 16:11:12', 0, 0, '1', '管理端 - 系统通知', '1308585499920769025', '-', 1, 0, '系统通知', NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1332329973427494913', '-1', '-1', '2020-11-27 22:26:31', '2022-10-30 16:11:12', 0, 0, 'BLACKLIST_TYPE', '黑/白名单类型', '1290688121255587841', '【固定值】', 1300, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1332330105569042434', '-1', '-1', '2020-11-27 22:27:02', '2022-10-30 16:11:12', 0, 0, '1', '白名单', '1332329973427494913', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1332330148963311619', '-1', '-1', '2020-11-27 22:27:13', '2022-10-30 16:11:13', 0, 0, '2', '黑名单', '1332329973427494913', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1352826209829978114', '-1', '-1', '2021-01-23 11:51:17', '2022-10-30 16:11:13', 0, 0, '3', 'Oauth2 接口', '1296995475064434690', '接口默认默认需通过 appId，appSecret生成的 accessToken 来进行oauth2 令牌验证可访问', 0, 1, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1352826389480407041', '-1', '-1', '2021-01-23 11:52:00', '2022-10-30 16:11:13', 0, 0, '3', '需Oauth2 授权', '1296995742778470401', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1357612114939858945', '-1', '-1', '2021-02-05 16:48:45', '2022-10-30 16:11:18', 0, 0, 'IS_READ', '是否已读', '1290688121255587841', '【固定值】', 1200, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1357612150536916994', '-1', '-1', '2021-02-05 16:48:54', '2022-10-30 16:11:18', 0, 0, '0', '未读', '1357612114939858945', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1357612182854029315', '-1', '-1', '2021-02-05 16:49:01', '2022-10-30 16:11:18', 0, 0, '1', '已读', '1357612114939858945', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1368739295631798273', '-1', '-1', '2021-03-08 09:44:11', '2022-10-30 16:11:19', 0, 0, 'POSITION', '部门职位', '1290686507555844098', '【动态值】, 如有需要根据业务指定', 200, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1368739394596401154', '-1', '-1', '2021-03-08 09:44:35', '2022-10-30 16:11:19', 0, 0, '0', '系统管理员(老板)', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1384697257961463810', '-1', '-1', '2021-04-21 10:35:27', '2022-10-30 16:11:19', 0, 0, '1', '部门经理', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1399968409441050625', '-1', '-1', '2021-06-02 13:57:32', '2022-12-29 11:07:40', 0, 0, 'DEFAULT', '默认字典 (代码生成默认字典)', '1290688121255587841', '【固定值】用于代码生成默认使用的code值', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1399968449656037377', '-1', '-1', '2021-06-02 13:57:42', '2022-10-30 16:11:20', 0, 0, '1', '默认值 1', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1399968504043577346', '-1', '-1', '2021-06-02 13:57:55', '2022-10-30 16:11:20', 0, 0, '2', '默认值 2', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1399968544350838786', '-1', '-1', '2021-06-02 13:58:04', '2022-10-30 16:11:20', 0, 0, '3', '默认值 3', '1399968409441050625', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1404005220177985537', '-1', '-1', '2021-06-13 17:18:21', '2022-10-30 16:11:20', 0, 0, '0', '管理端 - 测试消息', '1308585499920769025', '-', 0, 0, '测试消息', NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455153732051349505', '-1', '-1', '2021-11-01 20:44:19', '2022-12-29 11:08:28', 0, 0, 'VUE_FIELD_TYPE', '字段类型 (代码生成支持表单类型)', '1290688121255587841', 'vue代码生成使用', 100, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455153814570086402', '-1', '-1', '2021-11-01 20:44:39', '2022-10-30 16:11:23', 0, 0, '1', '文本-(input)', '1455153732051349505', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455153864008347650', '-1', '-1', '2021-11-01 20:44:51', '2022-10-30 16:11:23', 0, 0, '2', '数字-(number)', '1455153732051349505', '-', 2, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455154454599905281', '-1', '-1', '2021-11-01 20:47:12', '2022-10-30 16:11:24', 0, 0, '4', '单选-(radio)', '1455153732051349505', '-', 4, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455154827834241025', '-1', '-1', '2021-11-01 20:48:41', '2022-10-30 16:11:24', 0, 0, '9', '开关-(switch)', '1455153732051349505', '-', 9, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455154939453059073', '-1', '-1', '2021-11-01 20:49:07', '2022-10-30 16:11:24', 0, 0, '5', '多选-(checkbox)', '1455153732051349505', '-', 5, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455155390269435905', '-1', '-1', '2021-11-01 20:50:55', '2022-10-30 16:11:24', 0, 0, '10', '日期-(data)', '1455153732051349505', 'yyyy-MM-dd', 10, 1, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455155744738455553', '-1', '-1', '2021-11-01 20:52:19', '2022-10-30 16:11:24', 0, 0, '11', '日期时间-(datetime)', '1455153732051349505', 'yyyy-MM-dd HH:mm:ss', 11, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455155995658498049', '-1', '-1', '2021-11-01 20:53:19', '2022-10-30 16:11:25', 0, 0, '12', '时间-小时选择 (time)', '1455153732051349505', '默认 hh:mm 格式，06:00 到 23::00, 步长30分钟,  如： 09::00 | 09:30', 12, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455156254728073217', '-1', '-1', '2021-11-01 20:54:21', '2022-10-30 16:11:25', 0, 0, '6', '下拉选择-(select-单选)', '1455153732051349505', '-默认支持搜索', 6, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455156611625594881', '-1', '-1', '2021-11-01 20:55:46', '2022-10-30 16:11:25', 0, 0, '3', '密码-(password)', '1455153732051349505', '-', 3, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455157509512835073', '-1', '-1', '2021-11-01 20:59:20', '2022-10-30 16:11:25', 0, 0, '7', '下拉选择 (select-单选+搜索)', '1455153732051349505', '-', 7, 1, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455157594946613250', '-1', '-1', '2021-11-01 20:59:40', '2022-10-30 16:11:26', 0, 0, '8', '下拉选择 (select-多选+搜索)', '1455153732051349505', '-', 8, 1, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455158056173252609', '-1', '-1', '2021-11-01 21:01:30', '2022-10-30 16:11:26', 0, 0, '13', '文件上传 (单图)', '1455153732051349505', '默认限制 jpg/png/gif 格式', 13, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455158128055234561', '-1', '-1', '2021-11-01 21:01:47', '2022-10-30 16:11:26', 0, 0, '14', '文件上传 (多图)', '1455153732051349505', '默认限制文件数量10', 14, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455158254165372929', '-1', '-1', '2021-11-01 21:02:18', '2022-10-30 16:11:26', 0, 0, '15', '文件上传（单视频）', '1455153732051349505', '默认限制mp4格式', 15, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1455160116759310338', '-1', '-1', '2021-11-01 21:09:42', '2022-10-30 16:11:26', 0, 0, '16', '文件上传（任意文件）', '1455153732051349505', '-默认限制文件数量10', 16, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1456761651003920386', '-1', '-1', '2021-11-06 07:13:37', '2022-10-30 16:11:27', 0, 0, '17', '大文本(textarea)', '1455153732051349505', '-', 17, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1459853262768320513', '-1', '-1', '2021-11-14 19:58:34', '2022-12-29 11:08:32', 0, 0, 'CONFIG_TYPE', '全局配置类型', '1290688121255587841', '-', 200, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1459853371690201089', '-1', '-1', '2021-11-14 19:59:00', '2022-10-30 16:11:27', 0, 0, '0', '文本', '1459853262768320513', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1459853401289404418', '-1', '-1', '2021-11-14 19:59:07', '2022-10-30 16:11:27', 0, 0, '1', '图片', '1459853262768320513', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1459853475214012418', '-1', '-1', '2021-11-14 19:59:25', '2022-10-30 16:11:27', 0, 0, '2', '开关', '1459853262768320513', '-', 2, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1477131575404531714', '-1', '-1', '2022-01-01 12:16:26', '2022-10-30 16:11:28', 0, 0, 'DEMO_TEST', 'demo模块测试', '1290684671448936449', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1477131743994580994', '-1', '-1', '2022-01-01 12:17:06', '2022-12-29 11:10:42', 0, 0, 'TEST', '测试', '1477131575404531714', '-', 100, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1477131857387589634', '-1', '-1', '2022-01-01 12:17:33', '2022-12-29 11:11:02', 0, 0, '0', '测试0', '1477131743994580994', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1477131918590873601', '-1', '-1', '2022-01-01 12:17:47', '2022-12-29 11:11:10', 0, 0, '1', '测试1', '1477131743994580994', '-', 1, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1477131953227436034', '-1', '-1', '2022-01-01 12:17:56', '2022-12-29 11:11:16', 0, 0, '2', '测试2', '1477131743994580994', '-', 2, 0, '1', '2', '3');
INSERT INTO `t_sys_dictionary` VALUES ('1481632150259240961', '-1', '-1', '2022-01-13 22:20:04', '2022-10-30 16:11:29', 0, 0, '2', '员工', '1368739295631798273', '-', 0, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1524590015236018178', '-1', '-1', '2022-05-12 11:19:19', '2022-10-30 16:11:29', 0, 0, '3', '富文本', '1459853262768320513', '-', 3, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1525481792788762625', '-1', '-1', '2022-05-14 22:22:56', '2022-10-30 16:11:29', 0, 0, '18', '富文本(tinymce)', '1455153732051349505', 'vue-tinymce 富文本插件', 18, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_dictionary` VALUES ('1537714554153410561', '-1', '-1', '2022-06-17 16:31:31', '2022-10-30 16:11:30', 0, 0, '4', 'markdown 文本', '1459853262768320513', '-', 4, 0, '', '', '');
INSERT INTO `t_sys_dictionary` VALUES ('1539432646667579394', '-1', '-1', '2022-06-22 10:18:38', '2022-10-30 16:11:30', 0, 0, '2', '管理端 - 用户信息变动', '1308585499920769025', '-已配置路由', 2, 0, '用户信息变动', '/views/admin/user/user', '/app/user/details');
INSERT INTO `t_sys_dictionary` VALUES ('1540889700338765825', '-1', '-1', '2022-06-26 10:48:27', '2022-10-30 16:11:30', 0, 0, '19', 'md编辑器 (v-md-editor)', '1455153732051349505', 'v-md-editor 插件', 19, 0, '', '', '');
INSERT INTO `t_sys_dictionary` VALUES ('1567731693815603202', '-1', '-1', '2022-09-08 12:28:57', '2022-10-30 16:11:30', 0, 0, '20', '级联选择器 (cascader)', '1455153732051349505', '需自行替换级联选择数据', 20, 0, '', '', '');
INSERT INTO `t_sys_dictionary` VALUES ('1568096167659663362', '-1', '-1', '2022-09-09 12:37:15', '2022-10-30 16:11:30', 0, 0, '21', '数组框 (array)', '1455153732051349505', '默认限制长度10, 默认内容为字符串逗号分割', 21, 0, '', '', '');
INSERT INTO `t_sys_dictionary` VALUES ('1568096550914191362', '-1', '-1', '2022-09-09 12:38:46', '2022-10-30 16:11:31', 0, 0, '22', '图标选择器 (Icon)', '1455153732051349505', '-', 22, 0, '', '', '');
INSERT INTO `t_sys_dictionary` VALUES ('1568096838635057153', '-1', '-1', '2022-09-09 12:39:55', '2022-10-30 16:11:31', 0, 0, '23', '颜色选择器 (color)', '1455153732051349505', '-', 23, 0, '', '', '');
INSERT INTO `t_sys_dictionary` VALUES ('1568097061667172353', '-1', '-1', '2022-09-09 12:40:48', '2022-10-30 16:11:31', 0, 0, '24', '地图坐标选择器 (Map)', '1455153732051349505', '-', 24, 0, '', '', '');
INSERT INTO `t_sys_dictionary` VALUES ('1607671299155460097', NULL, NULL, '2022-12-27 17:34:40', '2022-12-29 11:14:32', 0, 0, '5', 'json 文本', '1459853262768320513', '-基于md 实现', 5, 1, '', '', '');

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--操作记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1440255471893200897', '-1', '-1', '2021-09-21 18:03:57', '2023-02-06 22:48:25', 0, 0, '0', '系统管理', '', '', 'el-icon-setting', 1, 1, 0);
INSERT INTO `t_sys_menu` VALUES ('1440255602914869250', '-1', '-1', '2021-09-21 18:04:29', '2022-10-30 16:11:41', 0, 0, '1440255471893200897', '系统管理', '', '', 'el-icon-setting', 100, 2, 0);
INSERT INTO `t_sys_menu` VALUES ('1440256392576483330', '-1', '-1', '2021-09-21 18:07:37', '2022-12-08 17:47:39', 0, 0, '0', '测试 (test)', '', '', 'layui-icon-file-b', 2, 1, 0);
INSERT INTO `t_sys_menu` VALUES ('1440256481906769922', '-1', '-1', '2021-09-21 18:07:58', '2022-10-30 16:11:42', 0, 0, '1440256392576483330', '功能测试', '', '', 'layui-icon-file-b', 200, 2, 0);
INSERT INTO `t_sys_menu` VALUES ('1440271162033684482', '-1', '-1', '2021-09-21 19:06:18', '2022-10-30 16:11:42', 0, 0, '1440255602914869250', '用户管理', '', '/views/admin/user/user', 'el-icon-document-remove', 10004, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1442156484086480897', '-1', '-1', '2021-09-26 23:57:54', '2022-10-30 16:11:42', 0, 0, '1440255602914869250', '角色管理', '', '/views/admin/role/role', 'el-icon-document-remove', 10005, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1449698334917865473', '-1', '-1', '2021-10-17 19:26:30', '2022-10-30 16:11:42', 0, 0, '1449698274373087233', '页面-5级', NULL, '', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1449764190750285826', '-1', '-1', '2021-10-17 23:48:11', '2022-10-30 16:11:43', 0, 0, '1440255471893200897', '增强功能', '', '', 'el-icon-copy-document', 200, 2, 0);
INSERT INTO `t_sys_menu` VALUES ('1450796419538522114', '-1', '-1', '2021-10-20 20:09:55', '2022-10-30 16:11:43', 0, 0, '1440256481906769922', '测试页1', NULL, '/test1', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1451979503835369474', '-1', '-1', '2021-10-24 02:31:02', '2022-10-30 16:11:43', 0, 0, '1449764190750285826', 'Banner管理', '', '/views/admin/banner/banner', 'el-icon-document-remove', 20003, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1452091447254749186', '-1', '-1', '2021-10-24 09:55:54', '2022-10-30 16:11:43', 0, 0, '1440255471893200897', '代码生成', NULL, '', 'el-icon-edit', 0, 2, 0);
INSERT INTO `t_sys_menu` VALUES ('1452091513113710594', '-1', '-1', '2021-10-24 09:56:10', '2022-10-30 16:11:43', 0, 0, '1452091447254749186', '数据表', NULL, '/views/gc/codeGeneration/codeGeneration', 'el-icon-document-remove', 2, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1456054437146644481', '-1', '-1', '2021-11-04 08:23:24', '2022-10-30 16:11:44', 0, 0, '1452091447254749186', '生成的代码测试页', NULL, '/views/test/gcTest/gcTest', 'el-icon-document-remove', 3, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1457369967249879042', '-1', '-1', '2021-11-07 23:30:51', '2022-10-30 16:11:44', 0, 0, '0', '商家端', NULL, NULL, 'el-icon-document-remove', 3, 1, 0);
INSERT INTO `t_sys_menu` VALUES ('1457370029065531394', '-1', '-1', '2021-11-07 23:31:06', '2022-10-30 16:11:44', 0, 0, '1457369967249879042', '系统管理', NULL, NULL, 'el-icon-document-remove', 0, 2, 0);
INSERT INTO `t_sys_menu` VALUES ('1457370075530031105', '-1', '-1', '2021-11-07 23:31:17', '2022-10-30 16:11:44', 0, 0, '1457370029065531394', '角色管理', NULL, '/views/admin/role/role', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1457372083897004033', '-1', '-1', '2021-11-07 23:39:16', '2022-10-30 16:11:45', 0, 0, '1457370029065531394', '员工管理', NULL, '/views/admin/user/user', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1459712656557576194', '-1', '-1', '2021-11-14 10:39:51', '2022-10-30 16:11:45', 0, 0, '1440255602914869250', '部门管理', NULL, '/views/admin/dep/dep', 'el-icon-document-remove', 10006, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1459850402525622274', '-1', '-1', '2021-11-14 19:47:12', '2022-10-30 16:11:45', 0, 0, '1449764190750285826', '全局配置', NULL, '/views/admin/config/config', 'el-icon-document-remove', 20001, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1461987433667141634', '-1', '-1', '2021-11-20 17:19:01', '2022-10-30 16:11:45', 0, 0, '1440255471893200897', '首页', NULL, '/wel/jvmInfo', 'el-icon-menu', 0, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1462256665587916801', '-1', '-1', '2021-11-21 11:08:51', '2023-02-01 13:37:31', 0, 0, '1449764190750285826', '黑/白名单', NULL, '/views/admin/blacklist/blacklist', 'el-icon-document-remove', 20002, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1462261436877152258', '-1', '-1', '2021-11-21 11:27:48', '2022-10-30 16:11:46', 0, 0, '1449764190750285826', '请求日志', NULL, '/views/admin/log/log', 'el-icon-document-remove', 20005, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1468415037767946242', '-1', '-1', '2021-12-08 11:00:01', '2022-10-30 16:11:46', 0, 0, '1449764190750285826', '消息管理', NULL, '/views/admin/msg/msg', 'el-icon-s-comment', 20004, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1481642095692222465', '-1', '-1', '2022-01-13 22:59:36', '2022-10-30 16:11:46', 0, 0, '1440256481906769922', '测试页2', NULL, '/test2', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1482970741228965889', '-1', '-1', '2022-01-17 14:59:11', '2022-10-30 16:11:46', 0, 0, '1457369967249879042', '业务管理', NULL, NULL, 'el-icon-document-remove', 1, 2, 0);
INSERT INTO `t_sys_menu` VALUES ('1482970818676789249', '-1', '-1', '2022-01-17 14:59:30', '2023-02-06 04:03:45', 0, 0, '1482970741228965889', '业务功能1', NULL, '/yw1', 'el-icon-document-remove', 1, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1482970868152799234', '-1', '-1', '2022-01-17 14:59:41', '2022-10-30 16:11:47', 0, 0, '1482970741228965889', '业务功能2', NULL, '/yw2', 'el-icon-document-remove', 2, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1482970903854714882', '-1', '-1', '2022-01-17 14:59:50', '2022-10-30 16:11:47', 0, 0, '1482970741228965889', '业务功能3', NULL, '/yw3', 'el-icon-document-remove', 3, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1482979903354703874', '-1', '-1', '2022-01-17 15:35:36', '2022-10-30 16:11:47', 0, 0, '1457370029065531394', '菜单管理', NULL, '/views/admin/menu/menu', 'el-icon-document-remove', 0, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1516699625820524545', '-1', '-1', '2022-04-20 16:45:44', '2022-10-30 16:11:47', 0, 0, '1440255602914869250', '菜单管理', NULL, '/views/admin/menuv2/menu', 'el-icon-document-remove', 10003, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1516699798743289857', '-1', '-1', '2022-04-20 16:46:25', '2022-10-30 16:11:48', 0, 0, '1440255602914869250', '字典管理', NULL, '/views/admin/dictionaryv2/dictionary', 'el-icon-document-remove', 10002, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1516699922710138882', '-1', '-1', '2022-04-20 16:46:55', '2022-10-30 16:11:48', 0, 0, '1440255602914869250', '接口管理', NULL, '/views/admin/authorityv2/authority', 'el-icon-document-remove', 10001, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1552998827391221761', '-1', '-1', '2022-07-29 20:45:45', '2022-10-30 16:11:48', 0, 0, '1452091447254749186', '数据源', NULL, '/views/gc/db/datasource', 'el-icon-document-remove', 1, 3, 1);
INSERT INTO `t_sys_menu` VALUES ('1607943038670237697', '1', NULL, '2022-12-28 11:34:28', '2022-12-28 11:34:45', 0, 0, '1452091447254749186', '自关联表', NULL, '/views/gc/gcMenu/gcMenu', 'el-icon-document-remove', 4, 3, 0);
INSERT INTO `t_sys_menu` VALUES ('1639084479350562817', '1', NULL, '2023-03-24 09:59:28', '2023-03-24 09:59:50', 0, 0, '1449764190750285826', '地址管理', NULL, '/views/admin/address/address', 'el-icon-document-remove', 20006, 3, 0);

-- ----------------------------
-- Table structure for t_sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_msg`;
CREATE TABLE `t_sys_msg`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--消息通知' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_msg
-- ----------------------------
INSERT INTO `t_sys_msg` VALUES ('1539641080650199042', '-1', '-1', '2022-06-23 00:07:02', '2022-10-30 16:09:43', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?fullName=平台主账号\",\"routePathTwo\":\"/app/user/details&fullName=平台主账号\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询 名字= 平台主账号 的数据\"}', 2, 2, 0);
INSERT INTO `t_sys_msg` VALUES ('1539641211692838913', '-1', '-1', '2022-06-23 00:07:34', '2022-10-30 16:09:43', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?null\",\"routePathTwo\":\"/app/user/details&null\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询所有数据\"}', 2, 2, 1);
INSERT INTO `t_sys_msg` VALUES ('1539641447932817410', '-1', '-1', '2022-06-23 00:08:30', '2022-10-30 16:09:43', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin\",\"routePathTwo\":\"/app/user/details&username=admin\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询账号= admin的数据\"}', 2, 2, 1);
INSERT INTO `t_sys_msg` VALUES ('1539642142241124354', '-1', '-1', '2022-06-23 00:11:16', '2022-10-30 16:09:43', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin\",\"routePathTwo\":\"/app/user/details&username=admin\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询账号= admin的数据\"}', 2, 2, 1);
INSERT INTO `t_sys_msg` VALUES ('1539642214546731009', '-1', '-1', '2022-06-23 00:11:33', '2022-10-30 16:09:44', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin\",\"routePathTwo\":\"/app/user/details&username=admin\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,查询账号= admin的数据\"}', 2, 2, 1);
INSERT INTO `t_sys_msg` VALUES ('1539643597421342721', '-1', '-1', '2022-06-23 00:17:02', '2022-10-30 16:09:44', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin&fullname=平台主账号\",\"routePathTwo\":\"/app/user/details&username=admin&fullname=平台主账号\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,多参数查询\"}', 2, 2, 1);
INSERT INTO `t_sys_msg` VALUES ('1539643743827718145', '-1', '-1', '2022-06-23 00:17:37', '2022-10-30 16:09:44', 0, 0, '1', '{\"routePath\":\"/views/admin/user/user?username=admin&fullName=平台主账号\",\"routePathTwo\":\"/app/user/details&username=admin&fullName=平台主账号\",\"title\":\"用户信息变动\",\"message\":\"我是测试消息，跳转到用户列表,多参数查询\"}', 2, 2, 1);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询code(不能重复)',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--角色管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1443467633444806658', '-1', '-1', '2021-09-30 14:47:56', '2023-01-17 15:31:40', 0, 0, 'avue-超管', 'SYS', 'avue超管0', 0);
INSERT INTO `t_sys_role` VALUES ('1447115588580159489', '-1', '-1', '2021-10-10 16:23:36', '2023-01-17 15:31:38', 0, 0, 'avue 体验账号', 'test', '-', 0);

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--角色/菜单关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('1432596175978037250', '-1', NULL, '2021-08-31 14:48:38', '2022-10-30 16:09:00', 0, 0, '1432596175629910017', '7');
INSERT INTO `t_sys_role_menu` VALUES ('1432596175978037251', '-1', NULL, '2021-08-31 14:48:38', '2022-10-30 16:09:00', 0, 0, '1432596175629910017', '21');
INSERT INTO `t_sys_role_menu` VALUES ('1432596175978037252', '-1', NULL, '2021-08-31 14:48:38', '2022-10-30 16:09:00', 0, 0, '1432596175629910017', '22');
INSERT INTO `t_sys_role_menu` VALUES ('1432596175978037253', '-1', NULL, '2021-08-31 14:48:38', '2022-10-30 16:09:00', 0, 0, '1432596175629910017', '25');
INSERT INTO `t_sys_role_menu` VALUES ('1432596175978037254', '-1', NULL, '2021-08-31 14:48:38', '2022-10-30 16:09:01', 0, 0, '1432596175629910017', '1297047088646905857');
INSERT INTO `t_sys_role_menu` VALUES ('1435124452189843458', '-1', NULL, '2021-09-07 14:15:06', '2022-10-30 16:09:01', 0, 0, '1430703281969082369', '7');
INSERT INTO `t_sys_role_menu` VALUES ('1435124452189843459', '-1', NULL, '2021-09-07 14:15:06', '2022-10-30 16:09:01', 0, 0, '1430703281969082369', '21');
INSERT INTO `t_sys_role_menu` VALUES ('1435124452189843460', '-1', NULL, '2021-09-07 14:15:06', '2022-10-30 16:09:01', 0, 0, '1430703281969082369', '22');
INSERT INTO `t_sys_role_menu` VALUES ('1435124452189843461', '-1', NULL, '2021-09-07 14:15:06', '2022-10-30 16:09:01', 0, 0, '1430703281969082369', '1297047088646905857');
INSERT INTO `t_sys_role_menu` VALUES ('1435124452198232066', '-1', NULL, '2021-09-07 14:15:06', '2022-10-30 16:09:02', 0, 0, '1430703281969082369', '25');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770316419073', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:07', 0, 0, '1447115588580159489', '1440255471893200897');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770316419074', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:07', 0, 0, '1447115588580159489', '1461987433667141634');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770316419075', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:07', 0, 0, '1447115588580159489', '1452091447254749186');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770329001986', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:08', 0, 0, '1447115588580159489', '1452091513113710594');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770329001987', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:08', 0, 0, '1447115588580159489', '1456054437146644481');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770329001988', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:08', 0, 0, '1447115588580159489', '1440255602914869250');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770329001989', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:09', 0, 0, '1447115588580159489', '1516699922710138882');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770333196290', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:09', 0, 0, '1447115588580159489', '1516699798743289857');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770333196291', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:09', 0, 0, '1447115588580159489', '1516699625820524545');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770333196292', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:10', 0, 0, '1447115588580159489', '1440271162033684482');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770333196293', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:10', 0, 0, '1447115588580159489', '1442156484086480897');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770333196294', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:10', 0, 0, '1447115588580159489', '1459712656557576194');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770333196295', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:10', 0, 0, '1447115588580159489', '1449764190750285826');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770337390594', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:10', 0, 0, '1447115588580159489', '1462256665587916801');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770337390595', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:11', 0, 0, '1447115588580159489', '1459850402525622274');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770337390596', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:11', 0, 0, '1447115588580159489', '1451979503835369474');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770337390597', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:11', 0, 0, '1447115588580159489', '1468415037767946242');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770337390598', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:11', 0, 0, '1447115588580159489', '1462261436877152258');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770337390599', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:11', 0, 0, '1447115588580159489', '1440256392576483330');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770337390600', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:12', 0, 0, '1447115588580159489', '1440256481906769922');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770341584898', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:12', 0, 0, '1447115588580159489', '1481642095692222465');
INSERT INTO `t_sys_role_menu` VALUES ('1567368770341584899', '-1', NULL, '2022-09-07 12:26:48', '2022-10-30 16:09:12', 0, 0, '1447115588580159489', '1450796419538522114');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160234774529', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1440255471893200897');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160238968833', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1461987433667141634');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160238968834', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1452091447254749186');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160238968835', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1452091513113710594');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160238968836', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1456054437146644481');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160238968837', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1440255602914869250');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160238968838', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1516699922710138882');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160238968839', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1516699798743289857');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160238968840', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1516699625820524545');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163138', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1440271162033684482');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163139', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1442156484086480897');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163140', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1459712656557576194');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163141', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1449764190750285826');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163142', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1462256665587916801');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163143', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1459850402525622274');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163144', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1451979503835369474');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163145', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1468415037767946242');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163146', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1462261436877152258');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163147', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1440256392576483330');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163148', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1440256481906769922');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163149', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1481642095692222465');
INSERT INTO `t_sys_role_menu` VALUES ('1599225160243163150', NULL, NULL, '2022-12-04 10:12:45', '2022-12-04 10:12:45', 0, 0, '1443467633444806658', '1450796419538522114');
INSERT INTO `t_sys_role_menu` VALUES ('1607943039328743425', NULL, NULL, '2022-12-28 11:34:28', '2022-12-28 11:34:28', 0, 0, '1443467633444806658', '1607943038670237697');
INSERT INTO `t_sys_role_menu` VALUES ('1639084480101343233', NULL, NULL, '2023-03-24 09:59:28', '2023-03-24 09:59:28', 0, 0, '1443467633444806658', '1639084479350562817');

-- ----------------------------
-- Table structure for t_sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_user`;
CREATE TABLE `t_sys_role_user`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--角色/用户关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_role_user
-- ----------------------------
INSERT INTO `t_sys_role_user` VALUES ('1476437959124643841', '-1', '-1', '2021-12-30 14:20:14', '2022-10-30 16:09:31', 0, 0, '1476437958340308994', '1447115588580159489');
INSERT INTO `t_sys_role_user` VALUES ('1608290338785533954', NULL, NULL, '2022-12-29 10:34:30', '2022-12-29 10:34:30', 0, 0, '1', '1443467633444806658');
INSERT INTO `t_sys_role_user` VALUES ('1639105748473155586', NULL, NULL, '2023-03-24 11:23:59', '2023-03-24 11:23:59', 0, 0, '1460427339745763329', '1447115588580159489');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
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
  `dep_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司/部门id,多层级前端自行分割数据',
  `position` int(1) NOT NULL DEFAULT 0 COMMENT '职位 (字典code)',
  `head_pic` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号(第二账号)',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `reg_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `remarks` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'base--sys--用户管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', '-1', '0', '2020-08-05 07:11:04', '2023-03-24 11:23:18', 0, 13, 'admin', 'd9880822dd584adce3cde4b024776eef', 1, 0, '1548901388543594498,1548901468621246466,1548901576150618114', 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/53206946-4.png', '10000', '平台主账号', '成都', 11, '2020-08-05 15:11:05', '2023-03-24 11:23:17', NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('1460427339745763329', '-1', '0', '2021-11-17 01:59:45', '2023-03-24 11:23:58', 0, 2, 'test', '992171f4f472ae8360a32663d9529339', 2, 0, '1548901388543594498,1548901468621246466,1560880103058071554', 2, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/user/20230203-3654-121101125449-2.jpg', '17600000001', '测试账号', '0', 0, '2021-11-17 01:59:46', '2023-03-22 15:56:02', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
