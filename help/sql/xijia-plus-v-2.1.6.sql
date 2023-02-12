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

 Date: 12/02/2023 21:58:31
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
  `time` datetime NULL DEFAULT NULL COMMENT '时间 (默认 yyyy-MM-dd hh:mm:ss 格式)',
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
INSERT INTO `t_sys_authority` VALUES ('1620238841316827136', NULL, NULL, '2023-01-31 09:53:41', '2023-01-31 09:53:41', 0, 90, '741218298418565120', 'DELETE', '/api/open/file/delFolder', '文件目录删除', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('1620706738136829952', NULL, NULL, '2023-02-01 16:52:58', '2023-02-01 16:52:58', 0, 27, '720779973740335104', 'GET', '/api/open/websocket/isOnline', '判断指定用户是否在线', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('1621020034236874752', NULL, NULL, '2023-02-02 13:37:51', '2023-02-02 13:37:51', 0, 6, '720779973740335104', 'GET', '/api/open/websocket/getOnlineUsers', '获取当前在线用户列表', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('697255086296010752', '-1', '0', '2022-06-28 11:29:37', '2022-10-30 16:10:20', 0, 599, '0', '', '/api/open/redis', 'Redis  -->  Redis 测试', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('697398103711551488', '-1', '0', '2022-06-28 12:57:58', '2022-10-30 16:10:21', 0, 597, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest2/{key}', 'redis 分布式锁加锁测试2', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('697398103711551489', '-1', '0', '2022-06-28 12:57:58', '2022-10-30 16:10:21', 0, 597, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest1/{key}', 'redis 分布式锁加锁测试', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('697398103711551490', '-1', '0', '2022-06-28 12:57:58', '2022-10-30 16:10:21', 0, 597, '697255086296010752', 'GET', '/api/open/redis/redissonDistributedLockTest3', 'redis 分布式注解锁测试', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('700879734145421312', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:22', 0, 563, '0', '', '/api/admin/test/gcTest', '代码生成测试表', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('700879734145421314', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:22', 0, 563, '700879734145421312', 'POST', '/api/admin/test/gcTest', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('700879734145421315', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:22', 0, 563, '700879734145421312', 'DELETE', '/api/admin/test/gcTest/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('700879734149615616', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:22', 0, 563, '700879734145421312', 'PUT', '/api/admin/test/gcTest/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('700879734149615617', '-1', '0', '2022-07-06 11:32:41', '2022-10-30 16:10:23', 0, 563, '700879734145421312', 'GET', '/api/admin/test/gcTest/{id}', 'ID查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('720779973740335104', '-1', NULL, '2022-08-28 17:29:10', '2022-10-30 16:10:23', 0, 427, '0', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('720779973740335105', '-1', NULL, '2022-08-28 17:29:10', '2022-10-30 16:10:23', 0, 427, '720779973740335104', 'GET', '/api/open/websocket/getPath', '获取模拟游客登录的 websocket 连接地址', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('720779973740335106', '-1', NULL, '2022-08-28 17:29:10', '2022-10-30 16:10:23', 0, 427, '720779973740335104', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('720779973740335108', '-1', NULL, '2022-08-28 17:29:10', '2022-10-30 16:10:24', 0, 427, '720779973740335104', 'GET', '/api/open/websocket/getOnlineCount', '获取当前在线人数', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('721146444106567680', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:24', 0, 405, '0', '', '/api/admin/sys/user', 'base--sys--用户管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444106567681', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:24', 0, 405, '721146444106567680', 'POST', '/api/admin/sys/user', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444106567682', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:24', 0, 405, '721146444106567680', 'GET', '/api/admin/sys/user/{id}', 'ID查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444106567683', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:24', 0, 405, '721146444106567680', 'PUT', '/api/admin/sys/user/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761984', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 405, '721146444106567680', 'DELETE', '/api/admin/sys/user/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761985', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 405, '721146444106567680', 'GET', '/api/admin/sys/user/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761986', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 405, '721146444106567680', 'PUT', '/api/admin/sys/user/{id}/resetPassword', '重置任意用户密码', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761987', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 405, '721146444106567680', 'PUT', '/api/admin/sys/user/updByPassword', '修改当前登录人的密码', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761988', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:25', 0, 405, '721146444106567680', 'GET', '/api/admin/sys/user/findByRoleId', '获取指定角色的用户列表', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761989', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:26', 0, 405, '721146444106567680', 'GET', '/api/admin/sys/user/findUser', '查询当前登录人的个人信息', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761990', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:26', 0, 405, '721146444106567680', 'PUT', '/api/admin/sys/user/updUser', '修改当前登录人的信息', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761991', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:26', 0, 405, '721146444106567680', 'GET', '/api/admin/sys/user/list/keyData', '查询所有-只返回关键数据(姓名/昵称/电话/id)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444110761992', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:26', 0, 405, '721146444106567680', 'POST', '/api/admin/sys/user/login', '用户登录', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956288', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 405, '0', '', '/api/admin/sys/dictionary', 'base--sys--字典管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956289', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 405, '721146444114956288', 'GET', '/api/admin/sys/dictionary/list', '列表查询 (默认返回Tree数据,可指定Tree或List)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956290', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 405, '721146444114956288', 'POST', '/api/admin/sys/dictionary', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956291', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 405, '721146444114956288', 'PUT', '/api/admin/sys/dictionary/{id}', '编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956292', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:27', 0, 405, '721146444114956288', 'DELETE', '/api/admin/sys/dictionary/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956293', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:28', 0, 405, '721146444114956288', 'GET', '/api/admin/sys/dictionary/findCodeGroup', '查询所有-code分组', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444114956294', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:28', 0, 405, '721146444114956288', 'GET', '/api/admin/sys/dictionary/generateEnum', '生成枚举', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150592', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:28', 0, 405, '721146444114956288', 'GET', '/api/admin/sys/dictionary/list/category', '获取类别(级联数据)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150593', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:28', 0, 405, '0', '', '/api/admin/sys/dep', 'base--sys--组织机构', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150595', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:29', 0, 405, '721146444119150593', 'POST', '/api/admin/sys/dep', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150596', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:29', 0, 405, '721146444119150593', 'PUT', '/api/admin/sys/dep/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444119150597', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:30', 0, 405, '721146444119150593', 'DELETE', '/api/admin/sys/dep/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344899', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 405, '0', '', '/api/admin/sys/menu', 'base--sys--菜单管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344901', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 405, '721146444123344899', 'POST', '/api/admin/sys/menu', '菜单添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344902', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 405, '721146444123344899', 'PUT', '/api/admin/sys/menu/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344903', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 405, '721146444123344899', 'DELETE', '/api/admin/sys/menu/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444123344904', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 405, '721146444123344899', 'GET', '/api/admin/sys/menu/findTree', '左导航菜单', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733504', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:31', 0, 405, '0', '', '/api/admin/sys/role', 'base--sys--角色管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733505', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:32', 0, 405, '721146444131733504', 'POST', '/api/admin/sys/role', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733506', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:32', 0, 405, '721146444131733504', 'PUT', '/api/admin/sys/role/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733507', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:32', 0, 405, '721146444131733504', 'DELETE', '/api/admin/sys/role/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444131733508', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 405, '721146444131733504', 'GET', '/api/admin/sys/role/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927808', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 405, '0', '', '/api/admin/sys/blacklist', 'base--sys--黑名单', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927809', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 405, '721146444135927808', 'POST', '/api/admin/sys/blacklist', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927810', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 405, '721146444135927808', 'PUT', '/api/admin/sys/blacklist/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927811', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:33', 0, 405, '721146444135927808', 'DELETE', '/api/admin/sys/blacklist/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444135927812', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:34', 0, 405, '721146444135927808', 'GET', '/api/admin/sys/blacklist/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444140122112', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:34', 0, 405, '0', '', '/api/admin/sys/authority', 'base--sys--URL权限管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444140122113', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:34', 0, 405, '721146444140122112', 'GET', '/api/admin/sys/authority/list', '查询所有-接口管理', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444140122114', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:34', 0, 405, '721146444140122112', 'PUT', '/api/admin/sys/authority/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444140122115', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:35', 0, 405, '721146444140122112', 'PUT', '/api/admin/sys/authority/refreshAuthority', '扫描权限', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316416', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:35', 0, 405, '0', '', '/api/admin/sys/config', 'base--sys--全局配置', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316417', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:35', 0, 405, '721146444144316416', 'POST', '/api/admin/sys/config', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316418', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:35', 0, 405, '721146444144316416', 'GET', '/api/admin/sys/config/{id}', 'ID查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316419', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:36', 0, 405, '721146444144316416', 'PUT', '/api/admin/sys/config/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316420', '-1', NULL, '2022-08-29 17:45:22', '2022-11-29 17:22:53', 0, 405, '721146444144316416', 'DELETE', '/api/admin/sys/config/{id}', 'ID删除', 0, 0, 3, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316421', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:36', 0, 405, '721146444144316416', 'GET', '/api/admin/sys/config/findPage', '分页查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721146444144316422', '-1', NULL, '2022-08-29 17:45:22', '2022-10-30 16:10:36', 0, 405, '721146444144316416', 'GET', '/api/admin/sys/config/findByCode', 'CODE查询', 0, 0, 0, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805924446208', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:36', 0, 400, '0', '', '/api/admin/gc/dataBase', 'base--gc--代码生成--查询表数据', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805928640512', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:37', 0, 400, '721151805924446208', 'GET', '/api/admin/gc/dataBase/table/list', '查询所有表名', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805928640513', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:37', 0, 400, '721151805924446208', 'GET', '/api/admin/gc/dataBase/table/field', '查询指定表下所有字段内容', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834816', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:37', 0, 400, '0', '', '/api/admin/gc/generate', 'base--gc--代码生成', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834817', '-1', NULL, '2022-08-29 18:06:44', '2023-01-08 12:05:14', 0, 400, '721151805932834816', 'GET', '/api/admin/gc/generate/getPath', '代码生成路径', 0, 0, 0, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834818', '-1', NULL, '2022-08-29 18:06:44', '2022-11-21 00:05:45', 0, 400, '721151805932834816', 'POST', '/api/admin/gc/generate/generateCode', '生成代码', 0, 0, 3, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834819', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:38', 0, 400, '721151805932834816', 'POST', '/api/admin/gc/generate/preview', '生成预览代码', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834820', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:38', 0, 400, '721151805932834816', 'POST', '/api/admin/gc/generate/generateCodeVue', '生成Vue代码(将直接下载)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834821', '-1', NULL, '2022-08-29 18:06:44', '2023-02-02 09:48:00', 0, 400, '721151805932834816', 'POST', '/api/admin/gc/generate/generateCodeJavaAndVue', '生成java + vue代码(将直接下载)', 0, 0, 0, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805932834822', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:38', 0, 400, '0', '', '/api/admin/gc/datasource', 'base--gc--代码生成--数据源维护', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029120', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:39', 0, 400, '721151805932834822', 'POST', '/api/admin/gc/datasource', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029121', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:39', 0, 400, '721151805932834822', 'GET', '/api/admin/gc/datasource/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029122', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:39', 0, 400, '721151805932834822', 'DELETE', '/api/admin/gc/datasource/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029123', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:39', 0, 400, '721151805932834822', 'PUT', '/api/admin/gc/datasource/{id}/updPwd', '修改/重置密码', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029124', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:40', 0, 400, '721151805932834822', 'GET', '/api/admin/gc/datasource/{id}', 'id查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029125', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:40', 0, 400, '721151805932834822', 'POST', '/api/admin/gc/datasource/dataSourceTest/{id}', '数据源连接测试', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721151805937029126', '-1', NULL, '2022-08-29 18:06:44', '2022-10-30 16:10:40', 0, 400, '721151805932834822', 'PUT', '/api/admin/gc/datasource/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721158911297720320', '-1', NULL, '2022-08-29 18:34:57', '2022-10-30 16:10:40', 0, 398, '0', '', '/api/admin/sys/jvm', 'base--sys--jvm信息获取', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721158911297720321', '-1', NULL, '2022-08-29 18:34:57', '2022-10-30 16:10:41', 0, 398, '721158911297720320', 'GET', '/api/admin/sys/jvm/jvmInfo', '获取系统的jvm信息', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721158911301914624', '-1', NULL, '2022-08-29 18:34:57', '2022-10-30 16:10:41', 0, 398, '0', '', '/api/admin/sys/log', 'base--sys--操作记录', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721158911301914625', '-1', NULL, '2022-08-29 18:34:57', '2022-10-30 16:10:41', 0, 398, '721158911301914624', 'GET', '/api/admin/sys/log/findPage', '分页查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884352', '-1', NULL, '2022-08-29 18:42:53', '2022-10-30 16:10:43', 0, 395, '0', '', '/api/admin/sys/msg', 'base--sys--消息通知', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884353', '-1', NULL, '2022-08-29 18:42:53', '2022-11-25 15:14:06', 0, 395, '721160918934884352', 'POST', '/api/admin/sys/msg', '添加/发送消息', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884354', '-1', NULL, '2022-08-29 18:42:53', '2022-11-14 16:51:15', 0, 395, '721160918934884352', 'GET', '/api/admin/sys/msg/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884355', '-1', NULL, '2022-08-29 18:42:53', '2022-10-30 16:10:44', 0, 395, '721160918934884352', 'GET', '/api/admin/sys/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884356', '-1', NULL, '2022-08-29 18:42:53', '2022-10-30 16:10:44', 0, 395, '721160918934884352', 'PUT', '/api/admin/sys/msg/{id}/read', '消息修改为已读', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721160918934884357', '-1', NULL, '2022-08-29 18:42:53', '2022-10-30 16:10:44', 0, 395, '721160918934884352', 'GET', '/api/admin/sys/msg/findAllNum', '查询全部/已读/未读数量(当前登录用户)', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641344', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 393, '0', '', '/api/admin/sys/banner', 'base--sys--banner', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641345', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 393, '721162557955641344', 'POST', '/api/admin/sys/banner', '添加', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641346', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 393, '721162557955641344', 'GET', '/api/admin/sys/banner/findPage', '列表查询', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641347', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 393, '721162557955641344', 'DELETE', '/api/admin/sys/banner/{id}', 'ID删除', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('721162557955641348', '-1', NULL, '2022-08-29 18:49:22', '2022-10-30 16:10:45', 0, 393, '721162557955641344', 'PUT', '/api/admin/sys/banner/{id}', 'ID编辑', 0, 0, 1, 1);
INSERT INTO `t_sys_authority` VALUES ('724479128698490880', '-1', NULL, '2022-09-06 22:28:14', '2022-10-30 16:10:46', 0, 377, '697255086296010752', 'GET', '/api/open/redis/getDataNo', '获取分布式唯一数据编号', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('724479128698490881', '-1', NULL, '2022-09-06 22:28:14', '2022-10-30 16:10:46', 0, 377, '697255086296010752', 'GET', '/api/open/redis/getOrderNo', '获取分布式唯一订单号', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('733526914786004992', '-1', NULL, '2022-09-30 21:40:57', '2022-10-30 16:10:46', 0, 284, '700879734145421312', 'GET', '/api/admin/test/gcTest/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298418565120', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:46', 0, 261, '0', '', '/api/open/file', '文件管理', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298418565122', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:46', 0, 261, '741218298418565120', 'GET', '/api/open/file/downloadZip', '文件下载--多文件下载 (批量下载-打压缩包)', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298418565123', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:47', 0, 261, '741218298418565120', 'GET', '/api/open/file/download', '文件下载--单文件下载', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298422759424', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:47', 0, 261, '741218298418565120', 'POST', '/api/open/file/upload', '文件上传,可在指定路径后追加子路径,以/结尾，上传成功返回完整可访问URL', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('741218298422759425', '-1', NULL, '2022-10-15 19:03:48', '2022-10-30 16:10:47', 0, 261, '741218298418565120', 'DELETE', '/api/open/file/del', '文件删除', 0, 2, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('756396041112260608', NULL, NULL, '2022-11-26 16:14:51', '2022-11-26 16:14:51', 0, 213, '721146444123344899', 'GET', '/api/admin/sys/menu/tree', '列表查询(不支持分页)', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903468490752', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '0', '', '/api/client/sys/config', 'yh--base-plus--全局配置', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903468490753', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '760767903468490752', 'GET', '/api/client/sys/config/findByCode', 'CODE查询', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903476879360', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '0', '', '/api/client/sys/banner', 'yh--base-plus--banner', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903476879361', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '760767903476879360', 'GET', '/api/client/sys/banner/list/{position}', '列表-位置查询', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903485267968', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '0', '', '/api/client/sys/msg', 'yh--base-plus--消息通知', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903485267969', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '760767903485267968', 'GET', '/api/client/sys/msg/findPage', '分页查询', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903485267970', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '760767903485267968', 'GET', '/api/client/sys/msg/findUnreadNum', '查询未读数量(当前登录用户)', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903485267971', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '760767903485267968', 'PUT', '/api/client/sys/msg/{id}/read', '消息修改为已读', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903489462272', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '0', '', '/api/client/sys/dictionary', 'yh--base--字典管理', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903489462273', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '760767903489462272', 'GET', '/api/client/sys/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('760767903489462274', NULL, NULL, '2022-12-08 17:46:53', '2022-12-08 17:46:53', 0, 203, '760767903489462272', 'GET', '/api/client/sys/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 0, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820955799552', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '0', '', '/api/admin/test/gcMenu', '基础表--菜单', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820955799553', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '771899820955799552', 'POST', '/api/admin/test/gcMenu', '添加', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820955799554', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '771899820955799552', 'GET', '/api/admin/test/gcMenu/tree', '树结构数据', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820959993856', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '771899820955799552', 'GET', '/api/admin/test/gcMenu/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820959993857', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '771899820955799552', 'DELETE', '/api/admin/test/gcMenu/{id}', 'ID删除', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820959993858', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '771899820955799552', 'GET', '/api/admin/test/gcMenu/{id}', 'ID查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820959993859', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '771899820955799552', 'PUT', '/api/admin/test/gcMenu/{id}', 'ID编辑', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820972576768', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '721146444119150593', 'GET', '/api/admin/sys/dep/tree', '树结构数据', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820972576769', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '721146444119150593', 'GET', '/api/admin/sys/dep/findPage', '列表查询', 0, 0, 1, 0);
INSERT INTO `t_sys_authority` VALUES ('771899820972576770', NULL, NULL, '2023-01-08 11:01:12', '2023-01-08 11:01:12', 0, 117, '721146444119150593', 'GET', '/api/admin/sys/dep/{id}', 'ID查询', 0, 0, 1, 0);

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
INSERT INTO `t_sys_blacklist` VALUES ('1332333202949324802', '-1', '-1', '2020-11-27 22:39:21', '2023-01-03 08:18:43', 0, 0, 1, '*', '允许所有 ip 地址访问，优先级比黑名单(*) 高 ， 开启了白名单(*), 黑名单（*）将无效', 0);
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
INSERT INTO `t_sys_config` VALUES ('1564811793453887489', NULL, '-1', '2022-08-31 11:06:19', '2022-12-29 11:17:00', 1, 0, 'test', 'test', '### 123\n![image.png](http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/vMdEditor/26661516-image.png)\n\n```sql\nselect * from asd', 99, 4, '测试数据', '', '', '');
INSERT INTO `t_sys_config` VALUES ('1586639328887476226', NULL, NULL, '2022-10-30 16:41:09', '2022-12-29 11:15:05', 1, 0, '1', '1', '<p>1</p>', 0, 3, '', '', '', '');
INSERT INTO `t_sys_config` VALUES ('1591258898825293825', NULL, NULL, '2022-11-12 10:37:40', '2022-12-29 11:17:44', 0, 0, 'markdown-test', '11', '\n\n## 提示\n::: tip \n  你可以点击 toolbar 中的 tip 来快速插入\n:::\n\n::: warning\n  这是一段警告\n:::\n\n::: danger\n这是一个危险警告\n:::\n\n::: details\n  这是一个详情块，在 IE / Edge 中不生效\n:::\n\n::: tip 自定义标题\n  你也可以自定义块中的标题\n:::\n\n::: danger STOP\n  危险区域，禁止通行\n:::\n\n## 代码块\n```v\n// v-md-editor 插件\nimport VueMarkdownEditor from \'@kangc/v-md-editor\';\nimport \'@kangc/v-md-editor/lib/style/base-editor.css\';\nimport vuepressTheme from \'@kangc/v-md-editor/lib/theme/vuepress.js\';\nimport \'@kangc/v-md-editor/lib/theme/style/vuepress.css\';\nimport Prism from \'prismjs\';\nVueMarkdownEditor.use(vuepressTheme, {\n  Prism,\n});\n\n```\n\n\n```json\n{\n  \"name\": \"avue-cli\",\n  \"version\": \"2.0.0\",\n  \"private\": true,\n  \"scripts\": {\n    \"serve\": \"vue-cli-service serve\",\n    \"build\": \"vue-cli-service build\",\n    \"lint\": \"vue-cli-service lint\",\n    \"analyz\": \"npm_config_report=true npm run build\",\n    \"test:unit\": \"vue-cli-service test:unit\",\n    \"test:e2e\": \"vue-cli-service test:e2e\"\n  },\n```\n\n## 内容定位\n::: align-left\n  left\n:::\n\n::: align-center\n  center\n:::\n\n::: align-right\n  right\n:::\n\n', 999, 4, '', '', '', '');
INSERT INTO `t_sys_config` VALUES ('1597859174027370498', NULL, NULL, '2022-11-30 15:44:47', '2022-12-29 11:17:44', 0, 0, 'vue-tinymce-test', '1', '<p>1</p>', 999, 3, '', '', '', '');
INSERT INTO `t_sys_config` VALUES ('1600043434145034242', NULL, NULL, '2022-12-06 16:24:16', '2022-12-29 11:14:54', 1, 0, 'ff', 's', 's', 0, 0, '', '', '', '');
INSERT INTO `t_sys_config` VALUES ('1600043498917670914', NULL, NULL, '2022-12-06 16:24:32', '2022-12-29 11:14:51', 1, 0, '11', '1', '1', 0, 0, '', '', '', '');

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
INSERT INTO `t_sys_dep` VALUES ('1560880103058071554', '-1', '-1', '2022-08-20 14:43:09', '2022-12-29 10:38:59', 0, 0, '1608290949786574850', '2', '-', '2', 0, 0);
INSERT INTO `t_sys_dep` VALUES ('1608290949786574850', NULL, NULL, '2022-12-29 10:36:56', '2022-12-29 10:48:23', 0, 0, '0', '公司2', '-', 'code2', 2, 0);

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
INSERT INTO `t_sys_dictionary` VALUES ('1455155744738455553', '-1', '-1', '2021-11-01 20:52:19', '2022-10-30 16:11:24', 0, 0, '11', '日期时间-(datetime)', '1455153732051349505', 'yyyy-MM-dd hh:mm:ss', 11, 0, NULL, NULL, NULL);
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
INSERT INTO `t_sys_role_user` VALUES ('1599222577390759937', NULL, NULL, '2022-12-04 10:02:29', '2022-12-04 10:02:29', 0, 0, '685453406529261568', '1447115588580159489');
INSERT INTO `t_sys_role_user` VALUES ('1608290338785533954', NULL, NULL, '2022-12-29 10:34:30', '2022-12-29 10:34:30', 0, 0, '1', '1443467633444806658');
INSERT INTO `t_sys_role_user` VALUES ('1621318272001536001', NULL, NULL, '2023-02-03 09:22:51', '2023-02-03 09:22:51', 0, 0, '1460427339745763329', '1447115588580159489');
INSERT INTO `t_sys_role_user` VALUES ('1622766709917151234', NULL, NULL, '2023-02-07 09:18:27', '2023-02-07 09:18:27', 0, 0, '1622766708868575232', '1443467633444806658');
INSERT INTO `t_sys_role_user` VALUES ('1622766709933928450', NULL, NULL, '2023-02-07 09:18:27', '2023-02-07 09:18:27', 0, 0, '1622766708868575232', '1447115588580159489');

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
INSERT INTO `t_sys_user` VALUES ('1', '-1', '0', '2020-08-05 07:11:04', '2023-02-03 10:48:32', 0, 13, 'admin', 'd9880822dd584adce3cde4b024776eef', 1, 0, '1548901388543594498,1548901468621246466,1548901576150618114', 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/53206946-4.png', '10000', '平台主账号', '成都', 11, '2020-08-05 15:11:05', '2023-02-03 10:48:35', NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('1460427339745763329', '-1', '0', '2021-11-17 01:59:45', '2023-02-12 17:50:06', 0, 2, 'test', '992171f4f472ae8360a32663d9529339', 2, 0, '1548901388543594498,1548901468621246466,1560880103058071554', 2, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/user/20230203-3654-121101125449-2.jpg', '17600000001', 'Qqq1', '0', 0, '2021-11-17 01:59:46', '2023-02-12 17:50:06', NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('1622766708868575232', NULL, NULL, '2023-02-07 09:18:27', '2023-02-07 09:18:27', 0, 0, 'yunan', '589015ecfbc026ea24565f628bb3c6e6', 0, 0, '1548901388543594498,1548901468621246466,1548901576150618114', 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/user/20230207-7417-985.jpg', '13190000105', '于男', '辽宁省新民市新柳街前营子村101号', 1, '2023-02-07 09:18:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('685453406529261568', '-1', '1', '2022-05-14 21:53:57', '2023-02-02 13:38:47', 0, 0, 'hexin', 'd508eefe214884b363bf33882afb4ed3', 1, 0, '1548901388543594498,1548901468621246466,1548901576150618114', 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/25847053-8.jpg', '17600000000', '何鑫2', '1', 11, '2022-05-14 21:53:57', '2023-02-02 13:38:48', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
