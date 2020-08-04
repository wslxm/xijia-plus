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

 Date: 05/08/2020 01:39:11
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
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限类Id（方法与类/层级关系展示）',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式(GET/POST/PUT/DELETE)',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限url',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限接口表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES ('1289497185192263682', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '', '', '/dev/devBug', 'Bug修复');
INSERT INTO `t_admin_authority` VALUES ('1289497185217429506', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185192263682', 'PUT', '/dev/devBug/upd', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('1289497185225818113', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185192263682', 'DELETE', '/dev/devBug/del', 'ID删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185234206721', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185192263682', 'POST', '/dev/devBug/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1289497185250983937', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185192263682', 'GET', '/dev/devBug/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('1289497185255178241', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185192263682', 'DELETE', '/dev/devBug/delByIds', 'ID批量删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185263566849', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '', '', '/dev/devNorm', '开发规范');
INSERT INTO `t_admin_authority` VALUES ('1289497185271955458', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185263566849', 'PUT', '/dev/devNorm/upd', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('1289497185276149762', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185263566849', 'DELETE', '/dev/devNorm/del', 'ID删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185292926977', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185263566849', 'POST', '/dev/devNorm/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1289497185301315586', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185263566849', 'GET', '/dev/devNorm/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('1289497185305509890', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185263566849', 'DELETE', '/dev/devNorm/delByIds', '批量ID删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185318092802', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '', '', '/dev/devTask', '开发任务');
INSERT INTO `t_admin_authority` VALUES ('1289497185334870018', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185318092802', 'PUT', '/dev/devTask/upd', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('1289497185343258626', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185318092802', 'DELETE', '/dev/devTask/del', 'ID删除');
INSERT INTO `t_admin_authority` VALUES ('1289497185355841537', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185318092802', 'POST', '/dev/devTask/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1289497185360035841', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185318092802', 'GET', '/dev/devTask/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('1289497185368424450', NULL, NULL, '2020-08-01 17:44:21', '2020-08-01 17:44:21', 0, 4, '1289497185318092802', 'DELETE', '/dev/devTask/delByIds', '批量ID删除');
INSERT INTO `t_admin_authority` VALUES ('1289943047471169537', NULL, NULL, '2020-08-02 15:16:02', '2020-08-02 15:16:02', 0, 2, '1289497185192263682', 'PUT', '/dev/devBug/updByState', 'ID编辑状态');
INSERT INTO `t_admin_authority` VALUES ('1289943056124018690', NULL, NULL, '2020-08-02 15:16:04', '2020-08-02 15:16:04', 0, 2, '1289497185318092802', 'PUT', '/dev/devTask/updByState', 'ID编辑状态');
INSERT INTO `t_admin_authority` VALUES ('1289943057503944705', NULL, NULL, '2020-08-02 15:16:04', '2020-08-02 15:16:04', 0, 2, '1289497185318092802', 'GET', '/dev/devTask/findId', 'ID查询');
INSERT INTO `t_admin_authority` VALUES ('1289943060104413186', NULL, NULL, '2020-08-02 15:16:05', '2020-08-02 15:16:05', 0, 2, '1289497185318092802', 'GET', '/dev/devTask/findList', '查询所有');
INSERT INTO `t_admin_authority` VALUES ('1290681117346652162', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '', '', '/admin/adminAuthority', 'URL权限管理');
INSERT INTO `t_admin_authority` VALUES ('1290681117409566722', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117346652162', 'GET', '/admin/adminAuthority/findList', '查询所有 || 根据角色ID选中');
INSERT INTO `t_admin_authority` VALUES ('1290681117413761026', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117346652162', 'PUT', '/admin/adminAuthority/refreshAuthority', '扫描权限');
INSERT INTO `t_admin_authority` VALUES ('1290681117422149633', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '', '', '/admin/adminDictionary', '字典管理');
INSERT INTO `t_admin_authority` VALUES ('1290681117438926849', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117422149633', 'POST', '/admin/adminDictionary/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1290681117451509762', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117422149633', 'GET', '/admin/adminDictionary/findList', '查询所有');
INSERT INTO `t_admin_authority` VALUES ('1290681117459898369', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117422149633', 'GET', '/admin/adminDictionary/findCodeGroup', '查询所有-code分组');
INSERT INTO `t_admin_authority` VALUES ('1290681117468286978', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117422149633', 'GET', '/admin/adminDictionary/findByCode', 'Code查询');
INSERT INTO `t_admin_authority` VALUES ('1290681117476675586', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117422149633', 'PUT', '/admin/adminDictionary/upd', '编辑');
INSERT INTO `t_admin_authority` VALUES ('1290681117485064193', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117422149633', 'DELETE', '/admin/adminDictionary/del', 'ID删除');
INSERT INTO `t_admin_authority` VALUES ('1290681117493452801', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117422149633', 'PUT', '/admin/adminDictionary/updBySort', '修改排序');
INSERT INTO `t_admin_authority` VALUES ('1290681117501841410', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '', '', '/admin/adminMenu', '菜单管理');
INSERT INTO `t_admin_authority` VALUES ('1290681117510230018', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117501841410', 'POST', '/admin/adminMenu/insert', '菜单添加');
INSERT INTO `t_admin_authority` VALUES ('1290681117518618625', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117501841410', 'GET', '/admin/adminMenu/findByPidOrRoleId', 'pid + roleId 查询菜单列表');
INSERT INTO `t_admin_authority` VALUES ('1290681117527007234', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117501841410', 'GET', '/admin/adminMenu/findList', '查询所有');
INSERT INTO `t_admin_authority` VALUES ('1290681117535395842', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117501841410', 'PUT', '/admin/adminMenu/upd', '编辑');
INSERT INTO `t_admin_authority` VALUES ('1290681117547978754', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117501841410', 'DELETE', '/admin/adminMenu/del', 'ID删除');
INSERT INTO `t_admin_authority` VALUES ('1290681117552173057', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117501841410', 'GET', '/admin/adminMenu/findTree', '左导航菜单');
INSERT INTO `t_admin_authority` VALUES ('1290681117560561666', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '', '', '/admin/adminRole', '角色管理');
INSERT INTO `t_admin_authority` VALUES ('1290681117573144577', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'POST', '/admin/adminRole/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1290681117581533186', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'GET', '/admin/adminRole/findList', '查询所有');
INSERT INTO `t_admin_authority` VALUES ('1290681117581533187', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'PUT', '/admin/adminRole/upd', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('1290681117594116097', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'GET', '/admin/adminRole/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('1290681117598310401', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'GET', '/admin/adminRole/findUserRole', '获取用户的当前角色');
INSERT INTO `t_admin_authority` VALUES ('1290681117610893314', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'DELETE', '/admin/adminRole/del', 'ID删除');
INSERT INTO `t_admin_authority` VALUES ('1290681117615087617', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'PUT', '/admin/adminRole/updRoleMenu', '角色的菜单分配');
INSERT INTO `t_admin_authority` VALUES ('1290681117627670530', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'PUT', '/admin/adminRole/updUserRole', '用户的角色分配');
INSERT INTO `t_admin_authority` VALUES ('1290681117636059138', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117560561666', 'PUT', '/admin/adminRole/updRoleAuth', '角色的URL权限分配');
INSERT INTO `t_admin_authority` VALUES ('1290681117644447746', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '', '', '/admin/adminUser', '用户管理');
INSERT INTO `t_admin_authority` VALUES ('1290681117652836353', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117644447746', 'POST', '/admin/adminUser/insert', '添加');
INSERT INTO `t_admin_authority` VALUES ('1290681117665419266', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117644447746', 'PUT', '/admin/adminUser/upd', 'ID编辑');
INSERT INTO `t_admin_authority` VALUES ('1290681117673807874', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117644447746', 'GET', '/admin/adminUser/findPage', '分页查询');
INSERT INTO `t_admin_authority` VALUES ('1290681117678002177', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117644447746', 'DELETE', '/admin/adminUser/del', 'ID删除');
INSERT INTO `t_admin_authority` VALUES ('1290681117682196481', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117644447746', 'PUT', '/admin/adminUser/updByPassword', '密码修改');
INSERT INTO `t_admin_authority` VALUES ('1290681117694779394', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117644447746', 'DELETE', '/admin/adminUser/delByIds', '批量ID删除');
INSERT INTO `t_admin_authority` VALUES ('1290681117703168001', NULL, NULL, '2020-08-05 00:08:52', '2020-08-05 00:08:52', 0, 0, '1290681117644447746', 'GET', '/admin/adminUser/findRoleUser', '获取指定角色的当前用户');
INSERT INTO `t_admin_authority` VALUES ('284', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 5, '0', '', '/dataBase', '数据库表查询');
INSERT INTO `t_admin_authority` VALUES ('285', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 5, '284', 'GET', '/dataBase/findTable', '查询所有表名');
INSERT INTO `t_admin_authority` VALUES ('286', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 5, '284', 'GET', '/dataBase/findTableField', '查询指定表下使用字段内容');
INSERT INTO `t_admin_authority` VALUES ('287', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 5, '0', '', '/generate', '代码生成器-只限于页面调用');
INSERT INTO `t_admin_authority` VALUES ('289', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 5, '287', 'POST', '/generate/preview', '生成预览代码');
INSERT INTO `t_admin_authority` VALUES ('290', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 5, '287', 'GET', '/generate/getPath', '代码生成路径');
INSERT INTO `t_admin_authority` VALUES ('291', NULL, NULL, '2020-07-25 09:28:34', '2020-07-25 09:28:34', 0, 5, '287', 'POST', '/generate/generateCode', '生成代码');

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
INSERT INTO `t_admin_dictionary` VALUES ('1290683991241879553', NULL, NULL, '2020-08-05 00:20:17', '2020-08-05 01:29:06', 0, 0, 'sys', '系统数据', '0', '系统配置数据,切勿随意修改', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290684671448936449', NULL, NULL, '2020-08-05 00:23:00', '2020-08-05 01:29:04', 0, 0, 'enums', '枚举字典', '0', '数据库所有的状态字段值，如：state，type，gender等', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290686507555844098', NULL, NULL, '2020-08-05 00:30:17', '2020-08-05 01:25:56', 0, 0, 'admin-enum', '系统模块枚举', '1290684671448936449', 'admin---系统模块枚举', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290686763962036225', NULL, NULL, '2020-08-05 00:31:18', '2020-08-05 01:26:01', 0, 0, 'dev-enum', '开发模块枚举', '1290684671448936449', 'dev---开发模块枚举', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687277911076865', NULL, NULL, '2020-08-05 00:33:21', '2020-08-05 01:27:44', 0, 0, 'AdminMenu-root', '菜单级别', '1290686507555844098', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687351005212673', NULL, NULL, '2020-08-05 00:33:38', '2020-08-05 01:17:01', 0, 0, '1', '顶部菜单', '1290687277911076865', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687461252493314', NULL, NULL, '2020-08-05 00:34:05', '2020-08-05 00:34:05', 0, 0, '2', '菜单', '1290687277911076865', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687547940368386', NULL, NULL, '2020-08-05 00:34:25', '2020-08-05 00:34:25', 0, 0, '3', '页面', '1290687277911076865', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687608594198529', NULL, NULL, '2020-08-05 00:34:40', '2020-08-05 00:34:40', 0, 0, '4', '按钮', '1290687277911076865', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688121255587841', NULL, NULL, '2020-08-05 00:36:42', '2020-08-05 01:26:11', 0, 0, 'base-enum', '通用枚举', '1290684671448936449', 'base---通用枚举', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688660164931586', NULL, NULL, '2020-08-05 00:38:51', '2020-08-05 00:38:51', 0, 0, 'Gender', '性别', '1290688121255587841', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688703043301378', NULL, NULL, '2020-08-05 00:39:01', '2020-08-05 00:39:01', 0, 0, '1', '男', '1290688660164931586', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688742289403906', NULL, NULL, '2020-08-05 00:39:10', '2020-08-05 00:39:10', 0, 0, '0', '女', '1290688660164931586', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290690446414147586', NULL, NULL, '2020-08-05 00:45:56', '2020-08-05 00:45:56', 0, 0, 'test', '测试数据', '0', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290690810123218946', NULL, NULL, '2020-08-05 00:47:23', '2020-08-05 00:47:23', 0, 0, '1', '测试数据1', '1290690446414147586', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290690909788270593', NULL, NULL, '2020-08-05 00:47:47', '2020-08-05 00:47:47', 0, 0, '3', '测试数据3', '1290690446414147586', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694011719323649', NULL, NULL, '2020-08-05 01:00:07', '2020-08-05 01:00:07', 0, 0, '1', '1', '1290690810123218946', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694044355203074', NULL, NULL, '2020-08-05 01:00:14', '2020-08-05 01:00:14', 0, 0, '3', '3', '1290690810123218946', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694593746112514', NULL, NULL, '2020-08-05 01:02:25', '2020-08-05 01:02:25', 0, 0, '1', '1', '1290690909788270593', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694620853899266', NULL, NULL, '2020-08-05 01:02:32', '2020-08-05 01:02:32', 0, 0, '2', '2', '1290690909788270593', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694644753043457', NULL, NULL, '2020-08-05 01:02:37', '2020-08-05 01:02:37', 0, 0, '3', '3', '1290690909788270593', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694667049963521', NULL, NULL, '2020-08-05 01:02:43', '2020-08-05 01:02:43', 0, 0, '4', '4', '1290690909788270593', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694684838006785', NULL, NULL, '2020-08-05 01:02:47', '2020-08-05 01:02:47', 0, 0, '5', '5', '1290690909788270593', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694701980127233', NULL, NULL, '2020-08-05 01:02:51', '2020-08-05 01:02:51', 0, 0, '6', '6', '1290690909788270593', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290699139683782657', NULL, NULL, '2020-08-05 01:20:29', '2020-08-05 01:20:29', 0, 0, 'DevTask-state', '任务状态', '1290686763962036225', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290699362304856066', NULL, NULL, '2020-08-05 01:21:22', '2020-08-05 01:21:22', 0, 0, '0', '未开始', '1290699139683782657', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290699416495263746', NULL, NULL, '2020-08-05 01:21:35', '2020-08-05 01:22:42', 0, 0, '1', '正在进行', '1290699139683782657', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290699458731905026', NULL, NULL, '2020-08-05 01:21:45', '2020-08-05 01:22:50', 0, 0, '2', '已完成', '1290699139683782657', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290699505599057922', NULL, NULL, '2020-08-05 01:21:56', '2020-08-05 01:22:59', 0, 0, '3', '已撤销', '1290699139683782657', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290699978137735170', NULL, NULL, '2020-08-05 01:23:49', '2020-08-05 01:23:49', 0, 0, 'DevBug-state', 'BUG状态', '1290686763962036225', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290700055099019265', NULL, NULL, '2020-08-05 01:24:07', '2020-08-05 01:24:54', 0, 0, '0', '未开始', '1290699978137735170', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290700115199201282', NULL, NULL, '2020-08-05 01:24:22', '2020-08-05 01:24:22', 0, 0, '1', '正在进行', '1290699978137735170', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290700159658823681', NULL, NULL, '2020-08-05 01:24:32', '2020-08-05 01:24:32', 0, 0, '2', '已完成', '1290699978137735170', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290700198498078721', NULL, NULL, '2020-08-05 01:24:42', '2020-08-05 01:24:42', 0, 0, '3', '已撤销', '1290699978137735170', '-', 0);
INSERT INTO `t_admin_dictionary` VALUES ('34', NULL, NULL, '2020-07-25 09:29:07', '2020-08-05 01:28:52', 0, 0, 'item', '项目', '1290683991241879553', '开发模块与BUG模块项目名称', 0);
INSERT INTO `t_admin_dictionary` VALUES ('35', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 10:07:15', 0, 0, 'item-xj-admin', 'xj-admin', '34', '个人开发项目--通用后台管理系统', 0);
INSERT INTO `t_admin_dictionary` VALUES ('36', NULL, NULL, '2020-07-25 09:29:07', '2020-07-25 10:07:47', 0, 0, 'item-xj-colud', 'xj-colud', '34', '个人搭建的基于spring-colud-ailbaba微服务基础架构', 0);

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
INSERT INTO `t_admin_menu` VALUES ('1', NULL, NULL, '2020-07-25 09:29:38', '2020-08-02 14:53:50', 0, 0, '0', '兮家-系统管理', '', '0', 1, 1, 0);
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
INSERT INTO `t_admin_role_auth` VALUES ('1289943613681238018', NULL, NULL, '2020-08-02 15:18:17', '2020-08-02 15:18:17', 0, 0, '1289497185192263682', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613693820929', NULL, NULL, '2020-08-02 15:18:17', '2020-08-02 15:18:17', 0, 0, '1289497185217429506', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613693820930', NULL, NULL, '2020-08-02 15:18:17', '2020-08-02 15:18:17', 0, 0, '1289497185225818113', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613693820931', NULL, NULL, '2020-08-02 15:18:17', '2020-08-02 15:18:17', 0, 0, '1289497185234206721', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613693820932', NULL, NULL, '2020-08-02 15:18:17', '2020-08-02 15:18:17', 0, 0, '1289497185250983937', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613693820933', NULL, NULL, '2020-08-02 15:18:17', '2020-08-02 15:18:17', 0, 0, '1289497185255178241', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613693820934', NULL, NULL, '2020-08-02 15:18:17', '2020-08-02 15:18:17', 0, 0, '1289943047471169537', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613698015234', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185263566849', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613698015235', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185271955458', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613698015236', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185276149762', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613698015237', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185292926977', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613698015238', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185301315586', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613698015239', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185305509890', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613698015240', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185318092802', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209538', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185334870018', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209539', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185343258626', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209540', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185355841537', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209541', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185360035841', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209542', NULL, NULL, '2020-08-02 15:18:18', '2020-08-02 15:18:18', 0, 0, '1289497185368424450', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209543', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '1289943056124018690', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209544', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '1289943057503944705', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209545', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '1289943060104413186', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209546', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '235', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209547', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '1289943025140695042', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209548', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '236', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209549', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '238', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613702209550', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '1289943027619528706', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613710598145', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '1289943028403863554', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613710598146', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '1289943029116895234', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613710598147', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '1289943030329049090', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613710598148', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '281', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613710598149', NULL, NULL, '2020-08-02 15:18:19', '2020-08-02 15:18:19', 0, 0, '282', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613710598150', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '246', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613710598151', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943031511842817', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613714792450', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943032690442242', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613714792451', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943033273450498', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613714792452', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943033898401794', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613714792453', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943034531741698', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613714792454', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '250', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613714792455', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '254', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986754', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943036482093057', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986755', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943037048324098', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986756', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943039019646977', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986757', NULL, NULL, '2020-08-02 15:18:20', '2020-08-02 15:18:20', 0, 0, '1289943039690735617', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986758', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '1289943041045495810', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986759', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '258', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986760', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '271', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986761', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '273', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986762', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '274', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986763', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '259', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986764', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '1289943043197173761', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613718986765', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '1289943044384161793', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375362', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '1289943045067833345', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375363', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '1289943045667618818', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375364', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '1289943046284181506', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375365', NULL, NULL, '2020-08-02 15:18:21', '2020-08-02 15:18:21', 0, 0, '267', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375366', NULL, NULL, '2020-08-02 15:18:22', '2020-08-02 15:18:22', 0, 0, '278', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375367', NULL, NULL, '2020-08-02 15:18:22', '2020-08-02 15:18:22', 0, 0, '284', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375368', NULL, NULL, '2020-08-02 15:18:22', '2020-08-02 15:18:22', 0, 0, '285', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375369', NULL, NULL, '2020-08-02 15:18:22', '2020-08-02 15:18:22', 0, 0, '286', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375370', NULL, NULL, '2020-08-02 15:18:22', '2020-08-02 15:18:22', 0, 0, '287', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375371', NULL, NULL, '2020-08-02 15:18:22', '2020-08-02 15:18:22', 0, 0, '289', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375372', NULL, NULL, '2020-08-02 15:18:22', '2020-08-02 15:18:22', 0, 0, '290', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1289943613727375373', NULL, NULL, '2020-08-02 15:18:22', '2020-08-02 15:18:22', 0, 0, '291', '2');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282589646850', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185192263682', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035458', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185217429506', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035459', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185225818113', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035460', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185234206721', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035461', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185250983937', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035462', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185255178241', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035463', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289943047471169537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035464', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185263566849', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035465', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185271955458', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035466', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185276149762', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035467', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185292926977', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282598035468', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185301315586', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424065', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185305509890', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424066', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185318092802', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424067', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185334870018', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424068', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185343258626', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424069', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185355841537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424070', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185360035841', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424071', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289497185368424450', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424072', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289943056124018690', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424073', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289943057503944705', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424074', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1289943060104413186', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424075', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117346652162', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282606424076', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117409566722', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812674', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117413761026', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812675', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117422149633', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812676', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117438926849', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812677', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117451509762', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812678', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117459898369', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812679', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117468286978', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812680', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117476675586', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812681', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117485064193', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282614812682', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117493452801', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006977', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117501841410', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006978', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117510230018', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006979', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117518618625', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006980', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117527007234', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006981', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117535395842', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006982', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117547978754', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006983', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117552173057', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006984', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117560561666', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006985', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117573144577', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006986', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117581533186', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006987', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117581533187', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006988', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117594116097', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006989', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117598310401', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006990', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117610893314', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006991', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117615087617', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282619006992', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117627670530', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395586', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117636059138', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395587', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117644447746', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395588', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117652836353', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395589', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117665419266', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395590', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117673807874', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395591', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117678002177', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395592', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117682196481', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395593', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117694779394', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282627395594', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '1290681117703168001', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282631589889', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '284', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282631589890', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '285', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282631589891', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '286', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282631589892', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '287', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282631589893', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '289', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282631589894', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '290', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681282631589895', NULL, NULL, '2020-08-05 00:09:32', '2020-08-05 00:09:32', 0, 0, '291', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481563234305', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1289497185250983937', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481563234306', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1289497185301315586', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481563234307', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1289497185360035841', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622914', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1289943057503944705', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622915', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1289943060104413186', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622916', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117409566722', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622917', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117451509762', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622918', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117459898369', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622919', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117468286978', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622920', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117518618625', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622921', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117527007234', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481571622922', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117552173057', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817217', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117581533186', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817218', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117594116097', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817219', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117598310401', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817220', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117673807874', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817221', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '1290681117703168001', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817222', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '285', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817223', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '286', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817224', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '289', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1290681481575817225', NULL, NULL, '2020-08-05 00:10:19', '2020-08-05 00:10:19', 0, 0, '290', '31');

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
INSERT INTO `t_admin_role_user` VALUES ('1289506443594153985', NULL, NULL, '2020-08-01 10:21:08', '2020-08-01 10:21:08', 0, 0, '20', '31');
INSERT INTO `t_admin_role_user` VALUES ('1289941838182023169', NULL, NULL, '2020-08-02 15:11:14', '2020-08-02 15:11:14', 0, 0, '1289941795546923009', '1');
INSERT INTO `t_admin_role_user` VALUES ('1289941838194606081', NULL, NULL, '2020-08-02 15:11:14', '2020-08-02 15:11:14', 0, 0, '1289941795546923009', '2');
INSERT INTO `t_admin_role_user` VALUES ('42', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '1');
INSERT INTO `t_admin_role_user` VALUES ('43', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '2');
INSERT INTO `t_admin_role_user` VALUES ('44', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '3');
INSERT INTO `t_admin_role_user` VALUES ('45', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '1');
INSERT INTO `t_admin_role_user` VALUES ('46', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '2');
INSERT INTO `t_admin_role_user` VALUES ('47', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '3');
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
INSERT INTO `t_admin_user` VALUES ('1', NULL, NULL, '2020-08-02 15:11:04', '2020-08-05 00:18:12', 0, 0, 'http://xijia.plus/oss/file/image/head/20200802231102875788-tp (31).jpg', '1720696548', '王松', 'dfd5e22c8ee4de7da4f07a75fefb6420', '四川成都', 24, 1, 0, '2020-08-02 23:11:05', '2020-08-05 00:18:13');
INSERT INTO `t_admin_user` VALUES ('1289940576963198978', NULL, NULL, '2020-08-02 15:06:13', '2020-08-04 19:35:15', 0, 0, 'http://xijia.plus/oss/file/image/head/20200802230612821546-tp (23).jpg', '1', '1', '624bef4ab99df8464ac8dc9e4616e067', '1', 1, 0, 0, '2020-08-02 23:06:14', NULL);
INSERT INTO `t_admin_user` VALUES ('20', NULL, NULL, '2020-07-25 09:31:07', '2020-08-02 17:37:51', 0, 0, 'http://xijia.plus/oss/file/image/head/20200712000102739895-1.png', 'admin', '游客', '58a42a22f652b6cd9a34f840cd4ea559', '四川成都', 0, 0, 0, '2020-01-31 10:15:07', '2020-08-03 01:37:52');
INSERT INTO `t_admin_user` VALUES ('42', NULL, NULL, '2020-07-25 09:31:07', '2020-08-01 14:27:21', 0, 0, 'http://xijia.plus/oss/file/image/head/20200711235225041786-3.png', '17628689969', '李四', 'dfd5e22c8ee4de7da4f07a75fefb6420', '四川成都', 22, 1, 0, '2020-07-11 23:53:24', NULL);

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
INSERT INTO `t_dev_bug` VALUES ('1289559552445919234', '2020-08-01 13:52:10', '1', NULL, '2020-08-01 13:53:29', 0, 0, '1', 'item-xj-admin', 'id删除异常', 'id删除字段类型错误', 1, 2, '2020-08-01 00:00:00', '2020-08-01 21:53:30', 1.00, 1.00);
INSERT INTO `t_dev_bug` VALUES ('1289559832394739714', '2020-08-01 13:53:17', '1', NULL, '2020-08-01 13:53:35', 0, 0, '1', 'item-xj-admin', '权限url扫描异常', '更新权限url 时后台错误', 1, 2, '2020-08-01 00:00:00', '2020-08-01 21:53:35', 1.00, 2.00);
INSERT INTO `t_dev_bug` VALUES ('1289980627638067202', '2020-08-02 17:45:23', '1', NULL, '2020-08-04 04:16:54', 0, 0, '1', 'item-xj-admin', '服务器添加头像 405 Method Not Allowed', '405 Method Not Allowed，\n前端ajax发起post请求，结果返回405，Not allow method！原因为 Apache、IIS、Nginx等绝大多数web服务器，\n都不允许静态文件响应POST请求。\n\n错误原因：接口与Nginx配置冲突，访问地址不对 oss/', 1, 2, '2020-08-09 00:00:00', '2020-08-04 04:16:54', 2.00, 2.00);

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
INSERT INTO `t_dev_norm` VALUES ('1289116285992501250', '2020-07-31 16:30:47', NULL, NULL, '2020-08-01 10:22:16', 0, 0, '测试添加', '啊是发撒\nsafsafasface[吐] 11', 0);
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
  `item` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目(字典表code)',
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
INSERT INTO `t_dev_task` VALUES ('1289557981020565505', '2020-08-01 13:45:56', '1', NULL, '2020-08-01 14:15:41', 0, 0, '1', 'item-xj-admin', '数据表-添加是否必填字段', '添加是否必填，方便查看字段', 1, 2, '2020-08-01 00:00:00', '2020-08-01 22:15:42', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289558965448880129', '2020-08-01 13:49:50', '1', NULL, '2020-08-02 12:36:56', 0, 0, '1', 'item-xj-admin', '代码中所有 TODO 标识处理', ' TODO 代处理代码, 勿烂用', 1, 2, '2020-08-02 00:00:00', '2020-08-02 20:36:57', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289559346019053569', '2020-08-01 13:51:21', '1', NULL, '2020-08-02 02:35:56', 0, 0, '1', 'item-xj-admin', '代码生成器controller优化', '1、添加findId 查询\n2、分页查询排序改为根据创建时间 DESC倒序\n3、添加 mapper.xml 层代码\n4、代码生成器DTO添加必填验证注解', 1, 2, '2020-08-02 00:00:00', '2020-08-02 10:35:57', 2.00, 4.00);
INSERT INTO `t_dev_task` VALUES ('1289580275457675266', '2020-08-01 15:14:31', '1', NULL, '2020-08-02 03:21:44', 0, 0, '1', 'item-xj-admin', '修改返回对象+返回枚举', '通用返回对象优化，返回枚举类优化', 1, 2, '2020-08-02 00:00:00', '2020-08-02 11:21:45', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289734714160132098', '2020-08-02 01:28:12', '1', NULL, '2020-08-02 15:38:46', 0, 0, '1', 'item-xj-admin', 'jwt TOKEN 自动刷新', 'token到期会自动失效，任意操作后自动刷新token', 1, 2, '2020-08-02 00:00:00', '2020-08-02 23:38:47', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289752060748636162', '2020-08-02 02:37:08', '1', NULL, '2020-08-02 04:41:34', 0, 0, '1', 'item-xj-admin', 'JSR 303 验证返回错误信息处理', '全局异常处理jsr303返回dto字段标注的异常内容', 1, 2, '2020-08-02 00:00:00', '2020-08-02 12:41:35', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289763468169199617', '2020-08-02 03:22:27', '1', NULL, '2020-08-02 04:41:38', 0, 0, '1', 'item-xj-admin', 'req 请求参数不匹配返回错误处理', 'req 请求参数不匹配返回对应错误信息', 1, 2, '2020-08-02 00:00:00', '2020-08-02 12:41:39', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289783274108891137', '2020-08-02 04:41:09', '1', NULL, '2020-08-02 04:41:42', 0, 0, '1', 'item-xj-admin', 'req 没有请求参数异常信息返回处理', '优化请求错误返回信息', 1, 2, '2020-08-02 00:00:00', '2020-08-02 12:41:43', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289916333886050305', '2020-08-02 13:29:53', '1', NULL, '2020-08-02 15:27:06', 0, 0, '1', 'item-xj-admin', '接口名称统一', '部分接口名称不统一\n1、分页查询 --> findPage\n2、查询使用 --> findList\n3、id查询 --> findId\n4、id编辑  --> upd\n5、添加 --> insert\n6 、id删除  -->  del\n7、多id删除  delByIds', 1, 2, '2020-08-02 00:00:00', '2020-08-02 23:27:07', 2.00, 3.00);
INSERT INTO `t_dev_task` VALUES ('1289928054088421377', '2020-08-02 14:16:27', '1', NULL, '2020-08-02 14:29:07', 0, 0, '1', 'item-xj-admin', '请求日志优化', '请求log 日志处理', 1, 2, '2020-08-02 00:00:00', '2020-08-02 22:29:08', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289928341167558658', '2020-08-02 14:17:36', '1', NULL, '2020-08-02 14:17:36', 0, 0, '1', 'item-xj-admin', '登录处理，目前只支持账号密码登录', '1、账号密码登录\n2、手机号+验证码登录\n3、手机号+密码登录', 1, 0, '2020-08-02 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1289928892554956802', '2020-08-02 14:19:47', '1', NULL, '2020-08-04 23:41:33', 0, 0, '1', 'item-xj-admin', '阿里云短信', '添加阿里云短信工具类，用于登录等其他用途', 1, 2, '2020-08-02 00:00:00', '2020-08-04 23:41:34', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289929809945690114', '2020-08-02 14:23:26', '1', NULL, '2020-08-04 06:41:27', 0, 0, '1', 'item-xj-admin', 'swagger 方法@ApiOperation 优化', '统一格式为：\n@ApiOperation(value = \"操作功能\",notes= \"详细描叙\")', 1, 2, '2020-08-06 00:00:00', '2020-08-04 06:41:27', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289937151382781953', '2020-08-02 14:52:36', '1', NULL, '2020-08-02 14:52:36', 0, 0, '1', 'item-xj-admin', '前端页面接口参数命名优化', '所有html参数命名以html名称开头，防止所有页面重复找不到引用', 1, 0, '2020-08-06 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1289937407927386113', '2020-08-02 14:53:37', '1', NULL, '2020-08-02 14:53:37', 0, 0, '1', 'item-xj-admin', '所有类名优化', '以数据库名称为准，如: t_admin_user = AdminUser', 1, 0, '2020-08-06 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1289942318287224834', '2020-08-02 15:13:08', '1', NULL, '2020-08-04 06:40:13', 0, 0, '1', 'item-xj-admin', '字典管理删除优化', '删除父级时同时删除所有子层级实际\n优化查询，无极限\n优化添加，code 不重复\n优化应该，code 不重复', 1, 2, '2020-08-06 00:00:00', '2020-08-04 06:40:13', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289949333222977538', '2020-08-02 15:41:01', '1', NULL, '2020-08-02 16:26:38', 0, 0, '1', 'item-xj-admin', '开发任务功能优化', '点击第二页未携带第一页的查询条件', 1, 2, '2020-08-06 00:00:00', '2020-08-03 00:26:39', 1.00, 1.50);
INSERT INTO `t_dev_task` VALUES ('13', '2020-07-25 10:49:21', NULL, NULL, '2020-08-01 13:46:39', 0, 0, '1', 'item-xj-admin', 'bug修复-完整功能', 'bug修复页的全部功能内容', 1, 2, '2020-08-09 00:00:00', '2020-08-01 21:46:39', 8.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('14', '2020-07-25 10:51:01', NULL, NULL, '2020-07-25 10:51:01', 0, 0, '1', 'item-xj-admin', '学习计划-完整功能', '学习计划页的全部功能内容', 1, 0, '2020-08-08 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('15', '2020-07-25 10:52:30', NULL, NULL, '2020-07-25 10:52:30', 0, 0, '1', 'item-xj-admin', '更新记录-全功能', '更新记录也的全部功能内容', 1, 0, '2020-08-23 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('16', '2020-07-25 10:53:18', NULL, NULL, '2020-07-31 17:41:36', 0, 0, '1', 'item-xj-admin', '开发规范-全功能', '开发规范页的全部功能内容', 1, 2, '2020-08-30 00:00:00', '2020-07-31 17:41:37', 8.00, 3.00);
INSERT INTO `t_dev_task` VALUES ('18', '2020-07-25 12:24:11', NULL, NULL, '2020-08-01 15:13:14', 0, 0, '1', 'item-xj-admin', '密码加密', '用户密码进行MD5 加盐/加密\n1、修改密码\n2、登录\n3、添加', 1, 2, '2020-08-01 00:00:00', '2020-08-01 23:13:15', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('3', '2020-07-12 20:30:13', NULL, NULL, '2020-08-04 04:32:13', 0, 0, '1', 'item-xj-admin', '字典表操作后自动刷新', '添加/编辑/删除时未自动刷新页面数据，需优化功能', 1, 2, '2020-07-12 00:00:00', '2020-08-04 04:32:14', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('4', '2020-07-12 20:32:02', NULL, NULL, '2020-07-25 10:25:05', 0, 0, '1', 'item-xj-admin', '菜单图标', '添加和编辑时图标改为可选', 1, 0, '2020-07-18 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('7', '2020-07-12 23:26:44', NULL, NULL, '2020-07-25 10:41:23', 0, 0, '1', 'item-xj-admin', '开发任务模块', '完成输入实际耗时，撤销已完成删除完成时间及实际耗时', 1, 2, '2020-07-11 00:00:00', '2020-07-25 10:41:24', 2.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('8', '2020-07-12 23:29:02', NULL, NULL, '2020-07-30 17:08:14', 0, 0, '1', 'item-xj-admin', '开发任务模块', '添加任务详情页，鼠标移动到任务名或任务内存处自动弹出任务详情...', 1, 0, '2020-07-18 00:00:00', NULL, 0.00, NULL);
INSERT INTO `t_dev_task` VALUES ('9', '2020-07-12 23:39:11', NULL, NULL, '2020-08-01 10:19:47', 0, 0, '1', 'item-xj-admin', '每页记录数BUG处理', '选择了其他页数后面，点击搜索每页记录数被回滚到10', 4, 3, '2020-07-18 00:00:00', NULL, 2.00, NULL);

SET FOREIGN_KEY_CHECKS = 1;
