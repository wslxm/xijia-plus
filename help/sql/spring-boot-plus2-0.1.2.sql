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

 Date: 09/08/2021 11:09:02
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
  `is_sign` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要验签(不受限于登录授权)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--权限接口' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES ('528072364102127621', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:49', 0, 737, '', '', '/api/admin/adminAuthority', 'base--URL权限管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127622', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:49', 0, 737, '528072364102127621', 'GET', '/api/admin/adminAuthority/findByRoleIdAuthorityTreeChecked', '查询所有 || 根据角色ID选中 -> Tree ', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127623', NULL, NULL, '2021-03-06 14:57:53', '2021-06-12 17:11:28', 0, 737, '528072364102127621', 'PUT', '/api/admin/adminAuthority/refreshAuthority', '扫描权限', 0, 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127624', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:42:33', 0, 737, '528072364102127621', 'GET', '/api/admin/adminAuthority/findList', '查询所有', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127625', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:19:58', 0, 737, '528072364102127621', 'GET', '/api/admin/adminAuthority/findByRoleIdList', '查询所有 || 根据角色ID选中', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127626', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 737, '528072364102127621', 'PUT', '/api/admin/adminAuthority/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127627', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 737, '', '', '/api/admin/adminDictionary', 'base--字典管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321920', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 737, '528072364102127627', 'POST', '/api/admin/adminDictionary/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321921', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 737, '528072364102127627', 'GET', '/api/admin/adminDictionary/generateEnum', '生成枚举', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321923', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 737, '528072364102127627', 'DELETE', '/api/admin/adminDictionary/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321924', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 737, '528072364102127627', 'PUT', '/api/admin/adminDictionary/upd', '编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321925', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 737, '528072364102127627', 'GET', '/api/admin/adminDictionary/findByCode', 'Code查询(默认返回Tree数据, 可指定Tree或List)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321926', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 737, '528072364102127627', 'PUT', '/api/admin/adminDictionary/updBySort', '修改排序', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321927', NULL, NULL, '2021-03-06 14:57:53', '2021-05-21 21:01:46', 0, 737, '528072364102127627', 'GET', '/api/admin/adminDictionary/findCodeGroup', '查询所有-code分组', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321928', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 737, '', '', '/api/admin/adminMenu', 'base--菜单管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321929', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 737, '528072364106321928', 'POST', '/api/admin/adminMenu/insert', '菜单添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321930', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 737, '528072364106321928', 'GET', '/api/admin/adminMenu/findByPidOrRoleId', 'pid + roleId 查询菜单列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321931', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 737, '528072364106321928', 'GET', '/api/admin/adminMenu/findByPidOrRoleIdTree', 'pid + roleId 查询菜单列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321932', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 737, '528072364106321928', 'GET', '/api/admin/adminMenu/findTree', '左导航菜单', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321933', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 737, '528072364106321928', 'GET', '/api/admin/adminMenu/findList', '查询所有', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321934', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 737, '528072364106321928', 'DELETE', '/api/admin/adminMenu/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321935', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '528072364106321928', 'PUT', '/api/admin/adminMenu/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321936', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '', '', '/api/admin/adminRole', 'base--角色管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516224', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '528072364106321936', 'POST', '/api/admin/adminRole/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516225', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '528072364106321936', 'PUT', '/api/admin/adminRole/updUserRole', '用户的角色分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516226', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '528072364106321936', 'PUT', '/api/admin/adminRole/updRoleAuth', '角色的URL权限分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516227', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '528072364106321936', 'PUT', '/api/admin/adminRole/updRoleAuthAll', '所有角色拥有所有权限', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516228', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '528072364106321936', 'PUT', '/api/admin/adminRole/updRoleMenu', '角色的菜单分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516229', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '528072364106321936', 'GET', '/api/admin/adminRole/findUserRole', '获取指定用户的角色列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516230', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 737, '528072364106321936', 'GET', '/api/admin/adminRole/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516231', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '528072364106321936', 'GET', '/api/admin/adminRole/findList', '查询所有', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516232', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '528072364106321936', 'DELETE', '/api/admin/adminRole/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516233', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '528072364106321936', 'PUT', '/api/admin/adminRole/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516234', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '', '', '/api/admin/adminUser', 'base--用户管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516235', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '528072364110516234', 'POST', '/api/admin/adminUser/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516236', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '528072364110516234', 'POST', '/api/admin/adminUser/bindWeChatMq', '微信公众号openId绑定', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516237', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '528072364110516234', 'GET', '/api/admin/adminUser/findByRoleId', '获取指定角色的用户列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516238', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '528072364110516234', 'PUT', '/api/admin/adminUser/updResetPassword', '重置任意用户密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516239', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 737, '528072364110516234', 'POST', '/api/admin/adminUser/login', '登录', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516240', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '528072364110516234', 'PUT', '/api/admin/adminUser/updByPassword', '当前登录用户密码修改', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516241', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '528072364110516234', 'GET', '/api/admin/adminUser/findUser', '个人信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710528', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '528072364110516234', 'GET', '/api/admin/adminUser/findId', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710529', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '528072364110516234', 'GET', '/api/admin/adminUser/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710530', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '528072364110516234', 'DELETE', '/api/admin/adminUser/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710532', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '528072364110516234', 'PUT', '/api/admin/adminUser/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710533', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '', '', '/api/admin/adminDatasource', 'base-gc--代码生成--数据源维护', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710534', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '528072364114710533', 'POST', '/api/admin/adminDatasource/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710535', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 737, '528072364114710533', 'PUT', '/api/admin/adminDatasource/updPwd', '修改/重置密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710536', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 737, '528072364114710533', 'POST', '/api/admin/adminDatasource/dataSourceTest', '数据源连接测试', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710537', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 737, '528072364114710533', 'GET', '/api/admin/adminDatasource/findId', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710538', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 737, '528072364114710533', 'GET', '/api/admin/adminDatasource/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710539', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 737, '528072364114710533', 'GET', '/api/admin/adminDatasource/findList', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710540', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 737, '528072364114710533', 'DELETE', '/api/admin/adminDatasource/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710541', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 737, '528072364114710533', 'DELETE', '/api/admin/adminDatasource/delByIds', '批量ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710542', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 737, '528072364114710533', 'PUT', '/api/admin/adminDatasource/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851074789376', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:55', 0, 603, '', '', '/api/client/xj/adminBanner', 'yh--base-plus--banner', 0, 1, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851074789377', NULL, NULL, '2021-03-22 09:09:16', '2021-06-02 18:00:15', 0, 603, '533782851074789376', 'GET', '/api/client/xj/adminBanner/findByPosition', '位置查询', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('533782851074789378', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 603, '', '', '/api/client/xj/adminConfig', 'yh--base-plus--全局配置', 0, 1, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851074789379', NULL, NULL, '2021-03-22 09:09:16', '2021-06-02 18:00:14', 0, 603, '533782851074789378', 'GET', '/api/client/xj/adminConfig/findByCode', 'CODE查询', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('533782851083177984', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 603, '528072364102127627', 'GET', '/api/admin/adminDictionary/findDictCategory', '查询字典类别级联数据', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851095760896', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 603, '', '', '/api/admin/dataBase', 'base-gc--代码生成--查询表数据', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851095760897', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 603, '533782851095760896', 'GET', '/api/admin/dataBase/findTableField', '查询指定表下使用字段内容', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851095760898', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 603, '533782851095760896', 'GET', '/api/admin/dataBase/findTable', '查询所有表名', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851095760899', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 603, '', '', '/api/admin/generate', 'base-gc--代码生成', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851099955200', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 603, '533782851095760899', 'GET', '/api/admin/generate/getPath', '代码生成路径', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851099955201', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 603, '533782851095760899', 'POST', '/api/admin/generate/preview', '生成预览代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851099955202', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:57', 0, 603, '533782851095760899', 'POST', '/api/admin/generate/generateCode', '生成代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('568337546359214084', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '', '', '/api/client/dictionary', 'yh--base--字典管理', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546359214085', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546359214084', 'GET', '/api/client/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546359214086', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546359214084', 'GET', '/api/client/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546363408384', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '', '', '/api/client/xj/adminMsg', 'yh--base-plus--消息通知', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546363408385', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546363408384', 'PUT', '/api/client/xj/adminMsg/read', '消息修改为已读', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546363408386', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546363408384', 'GET', '/api/client/xj/adminMsg/findUnreadNum', '查询未读数量', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546363408387', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546363408384', 'GET', '/api/client/xj/adminMsg/findPage', '分页查询', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546371796992', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '528072364110516234', 'GET', '/api/admin/adminUser/findList', '查询所有(只返回姓名/昵称/电话/id)', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185600', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '', '', '/api/admin/xj/adminBanner', 'base-plus--banner', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185601', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185600', 'POST', '/api/admin/xj/adminBanner/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185602', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185600', 'GET', '/api/admin/xj/adminBanner/findPage', '分页查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185603', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185600', 'GET', '/api/admin/xj/adminBanner/findId', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185604', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185600', 'PUT', '/api/admin/xj/adminBanner/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185605', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185600', 'DELETE', '/api/admin/xj/adminBanner/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185606', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185600', 'DELETE', '/api/admin/xj/adminBanner/delByIds', '批量ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185607', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '', '', '/api/admin/xj/adminBlacklist', 'base-plus--黑名单', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185608', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185607', 'POST', '/api/admin/xj/adminBlacklist/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185609', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185607', 'GET', '/api/admin/xj/adminBlacklist/findPage', '分页查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185610', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185607', 'GET', '/api/admin/xj/adminBlacklist/findId', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185611', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185607', 'PUT', '/api/admin/xj/adminBlacklist/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185612', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185607', 'DELETE', '/api/admin/xj/adminBlacklist/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185613', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185607', 'PUT', '/api/admin/xj/adminBlacklist/updDisable', '禁用/启用', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185614', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '', '', '/api/admin/xj/adminConfig', 'base-plus--全局配置', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546380185615', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185614', 'POST', '/api/admin/xj/adminConfig/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379904', NULL, NULL, '2021-06-25 17:38:14', '2021-07-12 09:50:35', 0, 164, '568337546380185614', 'GET', '/api/admin/xj/adminConfig/findByCode', 'CODE查询', 0, 0, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379905', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185614', 'GET', '/api/admin/xj/adminConfig/findPage', '分页查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379906', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185614', 'GET', '/api/admin/xj/adminConfig/findId', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379907', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185614', 'PUT', '/api/admin/xj/adminConfig/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379908', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546380185614', 'DELETE', '/api/admin/xj/adminConfig/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379909', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '', '', '/api/admin/xj/adminLog', 'base-plus--操作记录', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379910', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379909', 'POST', '/api/admin/xj/adminLog/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379911', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379909', 'GET', '/api/admin/xj/adminLog/findPage', '分页查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379912', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379909', 'GET', '/api/admin/xj/adminLog/findId', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379913', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379909', 'PUT', '/api/admin/xj/adminLog/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379914', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379909', 'DELETE', '/api/admin/xj/adminLog/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379915', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379909', 'DELETE', '/api/admin/xj/adminLog/delByIds', '批量ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379916', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '', '', '/api/admin/xj/adminMsg', 'base-plus--消息通知', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379917', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379916', 'POST', '/api/admin/xj/adminMsg/insert', '发送消息/添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379918', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379916', 'PUT', '/api/admin/xj/adminMsg/read', '消息修改为已读', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379919', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379916', 'GET', '/api/admin/xj/adminMsg/findUnreadNum', '查询未读数量', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379920', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379916', 'GET', '/api/admin/xj/adminMsg/findPage', '分页查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379921', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '', '', '/api/admin/xj/jvm/', 'base-plus--jvm信息获取', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('568337546384379922', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 164, '568337546384379921', 'GET', '/api/admin/xj/jvm//jvmInfo', '3、系统的jvm信息', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530678374400', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '', '', '/api/open/xj/sign', 'body参数验签测试', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530678374401', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374400', 'POST', '/api/open/xj/sign/test3/{a}', '参数加密', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530678374402', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374400', 'POST', '/api/open/xj/sign/test2', '参数加密', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530678374403', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374400', 'POST', '/api/open/xj/sign/test1', '参数验签', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530678374404', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '', '', '/api/admin/test1/testUser', '测试表', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530678374405', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374404', 'POST', '/api/admin/test1/testUser/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530678374406', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374404', 'GET', '/api/admin/test1/testUser/findPage', '分页查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530678374407', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374404', 'GET', '/api/admin/test1/testUser/findId', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530682568704', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374404', 'DELETE', '/api/admin/test1/testUser/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530682568705', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374404', 'PUT', '/api/admin/test1/testUser/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123136', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '', '', '/api/open/aliOssFile', 'AliYun --> OSS文件管理', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123137', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123136', 'GET', '/api/open/aliOssFile/fileList', 'OSS-文件Object列表', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123138', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123136', 'GET', '/api/open/aliOssFile/downloadZip', 'OSS-文件下载--多文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123139', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123136', 'GET', '/api/open/aliOssFile/download', 'OSS-文件下载--单文件下载', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123140', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123136', 'POST', '/api/open/aliOssFile/upload', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123141', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123136', 'DELETE', '/api/open/aliOssFile/del', 'OSS-文件删除', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123142', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123143', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123142', 'GET', '/api/open/websocket/getPath', '游客登录获取websocket连接地址', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123144', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123142', 'GET', '/api/open/websocket/getOnlineCount', '获取在线人数', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123145', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123142', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('584539530716123146', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123142', 'GET', '/api/open/websocket/getOnlineUsersList', '获取当前在线用户列表', 0, 2, 0, 0);

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
INSERT INTO `t_admin_dictionary` VALUES ('1290688742289403906', NULL, NULL, '2020-08-05 00:39:10', '2021-03-10 17:37:16', 0, 0, '2', '女', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1291341478399897601', NULL, NULL, '2020-08-06 11:52:54', '2021-02-05 14:46:54', 0, 0, '0', '未知', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469448399593474', NULL, NULL, '2020-08-20 15:29:38', '2020-08-20 15:29:38', 0, 0, 'DISABLE', '是否禁用', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469518025039873', NULL, NULL, '2020-08-20 15:29:55', '2020-08-20 15:29:55', 0, 0, '1', '禁用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469564455985154', NULL, NULL, '2020-08-20 15:30:06', '2020-08-20 15:30:06', 0, 0, '0', '启用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995475064434690', NULL, NULL, '2020-08-22 02:19:52', '2021-01-23 12:00:35', 0, 0, 'AUTHORITY_TYPE', '权限类型', '1290686507555844098', '默认类型, 来进行默认权限指定', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995560007479298', NULL, NULL, '2020-08-22 02:20:12', '2021-03-16 10:00:15', 0, 0, '0', '管理端接口', '1296995475064434690', '管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995610632728578', NULL, NULL, '2020-08-22 02:20:24', '2021-03-16 10:00:26', 0, 0, '1', '用户端接口', '1296995475064434690', '用户端, 类上标有该参数所有接口都会默认被列为-[需登录]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995742778470401', NULL, NULL, '2020-08-22 02:20:55', '2021-03-22 09:06:30', 0, 0, 'AUTHORITY_STATE', '权限状态', '1290686507555844098', '动态编辑权限状态', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995839977271297', NULL, NULL, '2020-08-22 02:21:19', '2021-03-15 20:35:49', 0, 0, '0', '无', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996062090833922', NULL, NULL, '2020-08-22 02:22:12', '2020-08-22 02:22:31', 0, 0, '1', '需登录', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996224863383554', NULL, NULL, '2020-08-22 02:22:50', '2020-08-22 02:22:50', 0, 0, '2', '需登录+授权', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1297705363906273282', NULL, NULL, '2020-08-24 01:20:43', '2021-01-23 11:50:33', 0, 0, '2', '通用接口', '1296995475064434690', '通用接口, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191840981327873', NULL, NULL, '2020-08-25 09:33:48', '2020-08-25 09:33:48', 0, 0, 'DELETED', '逻辑删除', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191898313269249', NULL, NULL, '2020-08-25 09:34:02', '2020-08-25 09:34:02', 0, 0, '0', '正常', '1298191840981327873', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191981998022658', NULL, NULL, '2020-08-25 09:34:22', '2020-08-25 09:34:22', 0, 0, '1', '已删除', '1298191840981327873', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194668697198594', NULL, NULL, '2020-08-25 09:45:02', '2021-03-15 20:32:48', 0, 0, 'BANNER_IS_SKIP', 'banner是否跳转', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194722350735361', NULL, NULL, '2020-08-25 09:45:15', '2020-09-24 21:40:23', 0, 0, '0', '否', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194801014906881', NULL, NULL, '2020-08-25 09:45:34', '2020-08-25 09:45:34', 0, 0, '1', '内部跳转', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194850285395969', NULL, NULL, '2020-08-25 09:45:46', '2020-08-25 09:45:46', 0, 0, '2', '外部跳转', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1303872194494435330', NULL, NULL, '2020-09-10 09:45:30', '2021-01-23 14:02:37', 0, 0, 'BANNER_POSITION', 'banner 位置', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1303872308608864257', NULL, NULL, '2020-09-10 09:45:57', '2021-01-23 14:02:12', 0, 0, '1', '用户端首页', '1303872194494435330', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580167513370625', NULL, NULL, '2020-09-23 09:33:17', '2021-01-23 14:06:12', 0, 0, 'MSG_USER_TYPE', '及时消息终端', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580236161544193', NULL, NULL, '2020-09-23 09:33:33', '2021-03-16 10:07:52', 0, 0, '1', '用户端', '1308580167513370625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580275248263169', NULL, NULL, '2020-09-23 09:33:42', '2021-03-16 10:41:35', 0, 0, '2', '管理端', '1308580167513370625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585499920769025', NULL, NULL, '2020-09-23 09:54:28', '2021-01-23 14:06:05', 0, 0, 'MSG_TYPE', '及时消息类型', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585615968772098', NULL, NULL, '2020-09-23 09:54:56', '2020-09-23 09:54:56', 0, 0, '1', '系统通知', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585669429370882', NULL, NULL, '2020-09-23 09:55:09', '2020-09-23 09:55:09', 0, 0, '2', '订单业务通知', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318470835622768641', NULL, NULL, '2020-10-20 08:35:17', '2021-06-02 17:56:32', 0, 0, 'HELP_CATEGORY', '帮助中心类别', '1393925384986198018', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471097611579394', NULL, NULL, '2020-10-20 08:36:20', '2021-06-02 17:56:42', 0, 0, 'HELP_VERSION', '帮助中心版本', '1393925384986198018', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471212665532417', NULL, NULL, '2020-10-20 08:36:47', '2021-03-22 09:54:18', 0, 0, '1', '采用技术', '1318470835622768641', '-', 0, 1);
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
INSERT INTO `t_admin_dictionary` VALUES ('1337244337121091586', NULL, NULL, '2020-12-11 11:54:29', '2021-06-02 17:56:51', 0, 0, 'FILE_TYPE', '文件类型', '1393925384986198018', '-', 0, 0);
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
INSERT INTO `t_admin_dictionary` VALUES ('1368739295631798273', NULL, NULL, '2021-03-08 09:44:11', '2021-04-21 10:38:51', 0, 0, 'POSITION', '部门职位', '1290686507555844098', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1368739394596401154', NULL, NULL, '2021-03-08 09:44:35', '2021-04-21 10:42:03', 0, 0, '0', '系统管理员', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1368739483377233921', NULL, NULL, '2021-03-08 09:44:56', '2021-04-21 10:35:55', 1, 0, '1', '预定部经理', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1371439400839356417', NULL, NULL, '2021-03-15 20:33:27', '2021-03-15 20:34:44', 1, 0, 'atest', 'a', '1337244241461600257', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1371439431722016770', NULL, NULL, '2021-03-15 20:33:35', '2021-03-15 20:34:44', 1, 0, '1', '1', '1371439400839356417', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1371439455742795777', NULL, NULL, '2021-03-15 20:33:40', '2021-03-15 20:34:44', 1, 0, '2', '2', '1371439400839356417', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1371439472469680130', NULL, NULL, '2021-03-15 20:33:44', '2021-03-15 20:34:44', 1, 0, '3', '3', '1371439400839356417', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1371439494296838146', NULL, NULL, '2021-03-15 20:33:50', '2021-03-15 20:34:06', 1, 0, '4', '4', '1371439400839356417', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1371440118774177794', NULL, NULL, '2021-03-15 20:36:18', '2021-03-15 20:36:47', 1, 0, 'atets', 'q', '1290686507555844098', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1384697257961463810', NULL, NULL, '2021-04-21 10:35:27', '2021-04-21 10:42:10', 0, 0, '1', '平台人员', '1368739295631798273', '-根据此类型进行预定订单排班', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1384697326420893698', NULL, NULL, '2021-04-21 10:35:43', '2021-04-21 10:41:43', 1, 0, '3', '会籍部-业务人员', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1384697542972809217', NULL, NULL, '2021-04-21 10:36:35', '2021-04-21 10:41:38', 1, 0, '2', 'vip联盟-业务人员', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925384986198018', NULL, NULL, '2021-05-16 21:44:44', '2021-05-16 21:45:03', 0, 0, 'SER', '兮家用户服务', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925564183642113', NULL, NULL, '2021-05-16 21:45:26', '2021-05-16 21:45:26', 0, 0, 'IS_VIP', '是否需要vip', '1393925384986198018', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925637302943746', NULL, NULL, '2021-05-16 21:45:44', '2021-05-16 21:45:44', 0, 0, '0', '不需要', '1393925564183642113', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925670421168129', NULL, NULL, '2021-05-16 21:45:52', '2021-05-16 21:45:52', 0, 0, '1', '需要', '1393925564183642113', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925841175478273', NULL, NULL, '2021-05-16 21:46:32', '2021-05-16 21:46:32', 0, 0, 'SKIP_TYPE', '跳转类型', '1393925384986198018', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925885773512706', NULL, NULL, '2021-05-16 21:46:43', '2021-05-16 21:46:43', 0, 0, '0', '内部跳转', '1393925841175478273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925924549853186', NULL, NULL, '2021-05-16 21:46:52', '2021-05-16 21:46:52', 0, 0, '1', '外部跳转', '1393925841175478273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393926103206232065', NULL, NULL, '2021-05-16 21:47:35', '2021-05-16 21:48:15', 0, 0, 'FUN_STATE', '功能状态', '1393925384986198018', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393926364360376322', NULL, NULL, '2021-05-16 21:48:37', '2021-05-16 21:48:37', 0, 0, '0', '开发中', '1393926103206232065', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393926404223041538', NULL, NULL, '2021-05-16 21:48:47', '2021-05-20 00:24:15', 0, 0, '1', '可使用', '1393926103206232065', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968409441050625', NULL, NULL, '2021-06-02 13:57:32', '2021-06-02 17:55:59', 0, 0, 'DEFAULT', '默认字典(代码生成默认字典)', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968449656037377', NULL, NULL, '2021-06-02 13:57:42', '2021-06-02 13:57:42', 0, 0, '1', '默认值 1', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968504043577346', NULL, NULL, '2021-06-02 13:57:55', '2021-06-02 13:57:55', 0, 0, '2', '默认值 2', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968544350838786', NULL, NULL, '2021-06-02 13:58:04', '2021-06-02 13:58:04', 0, 0, '3', '默认值 3', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1404005220177985537', NULL, NULL, '2021-06-13 17:18:21', '2021-06-14 10:12:10', 0, 0, '0', '测试', '1308585499920769025', '-', 0, 0);

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
INSERT INTO `t_admin_menu` VALUES ('1', NULL, NULL, '2020-07-25 09:29:38', '2021-06-04 16:58:43', 0, 0, '0', '兮家-系统管理', '', '', 'layui-icon-template-1', 1, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('100', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:32:39', 1, 0, '1323584197721440258', '外链', NULL, '', 'layui-icon-file-b', 600, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('101', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:02', 1, 0, '134', 'layui模板', NULL, 'http://localhost:9049/views/index.html', 'layui-icon-file-b', 60001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('102', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:02', 1, 0, '134', 'layui官网', NULL, 'https://www.layui.com', 'layui-icon-file-b', 60002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('123', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:16', 1, 0, '135', '我的CSDN', NULL, 'https://blog.csdn.net/qq_41463655', 'layui-icon-file-b', 60006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('124', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:02', 1, 0, '134', '百度', NULL, 'http://www.baidu.com', 'layui-icon-file-b', 60005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('125', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:16', 1, 0, '135', '我的码云', NULL, 'https://gitee.com/wslxm', 'layui-icon-file-b', 60004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('126', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:02', 1, 0, '134', '阿里云', NULL, 'https://homenew.console.aliyun.com', 'layui-icon-file-b', 60003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879303106289665', NULL, NULL, '2020-07-31 00:49:06', '2021-05-11 18:54:35', 1, 0, '1350298064077774850', 'MD编辑器', NULL, '', 'layui-icon-file-b', 500, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879370219347969', NULL, NULL, '2020-07-31 00:49:22', '2021-03-08 17:29:20', 1, 0, '1288879303106289665', '编辑', NULL, '/page/base_markdown_index', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879436422242305', NULL, NULL, '2020-07-31 00:49:38', '2021-03-08 17:29:29', 1, 0, '1288879303106289665', '展示', NULL, '/page/base_markdown_detail', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('129', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:02', 1, 0, '134', '蚂蚁课堂', NULL, 'http://www.mayikt.com/', 'layui-icon-file-b', 60007, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1297047088646905857', NULL, NULL, '2020-08-22 05:44:58', '2021-08-09 10:09:04', 0, 0, '4', '接口管理', NULL, '/page/manage_admin_authority_authority', 'layui-icon-file-b', 10005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1297533242571763714', NULL, NULL, '2020-08-23 13:56:45', '2021-08-09 10:09:23', 0, 0, '1350297066072498179', 'banner 管理', NULL, '/page/manage_xj_banner_banner', 'layui-icon-file-b', 10008, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('130', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:02', 1, 0, '134', '腾讯课堂', NULL, 'https://ke.qq.com/user/index/index.html#/plan/cid=291872&term_id=102601151', 'layui-icon-file-b', 60008, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('131', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:08', 1, 0, '133', '哔哩哔哩', NULL, 'https://www.bilibili.com/', 'layui-icon-file-b', 60009, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1311827586636156929', NULL, NULL, '2020-10-02 08:37:23', '2021-08-09 10:09:25', 0, 0, '1350297066072498179', '全局配置', NULL, '/page/manage_xj_config_config', 'layui-icon-file-b', 10009, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1312705440890540033', NULL, NULL, '2020-10-04 18:45:40', '2021-03-11 11:03:48', 1, 0, '1350307256670900225', '菜谱', '', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1312705547715268610', NULL, NULL, '2020-10-04 18:46:06', '2021-08-06 17:17:33', 1, 0, '1312705440890540033', '菜品管理', NULL, '/page/manage_yw_caipu_cpInfo_cpInfo', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1312755066842730498', NULL, NULL, '2020-10-04 22:02:52', '2021-08-06 17:17:36', 1, 0, '1312705440890540033', '菜品类别', NULL, '/page/manage_yw_caipu_cpCategory_cpCategory', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1317864829549146113', NULL, NULL, '2020-10-18 16:27:14', '2021-05-11 18:55:01', 1, 0, '1350298064077774850', '系统信息', NULL, '', 'layui-icon-file-b', 700, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1317865066573459457', NULL, NULL, '2020-10-18 16:28:11', '2021-05-11 18:55:01', 1, 0, '1317864829549146113', '系统监控', NULL, 'http://xijia.plus/bootAdmin', 'layui-icon-file-b', 70001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1318472952462770177', NULL, NULL, '2020-10-20 08:43:41', '2021-08-06 17:17:40', 0, 0, '1393917150250442754', '兮家手册', '', '/page/manage_yw_ser_help_help', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1318535862241251330', NULL, NULL, '2020-10-20 12:53:40', '2021-05-11 19:08:21', 1, 0, '1350305706179313666', '手册展示', NULL, '/page/client_help_index', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1319948617724743682', NULL, NULL, '2020-10-24 10:27:28', '2021-06-02 18:03:27', 0, 0, '1350298064077774850', '数据监控', '', 'http://xijia.plus/druid', 'layui-icon-file-b', 70002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1321432835319414785', NULL, NULL, '2020-10-28 12:45:12', '2021-08-09 10:09:30', 0, 0, '1350297066072498179', '系统日志', NULL, '/page/manage_xj_log_log', 'layui-icon-file-b', 10012, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584197721440258', NULL, NULL, '2020-11-03 19:13:58', '2021-06-04 16:58:52', 0, 0, '0', '兮家-server', '', '', 'layui-icon-template-1', 3, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584742796410881', NULL, NULL, '2020-11-03 19:16:08', '2021-05-11 18:55:05', 1, 0, '1350298064077774850', '开发文档', NULL, '', 'layui-icon-file-b', 900, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584867987996674', NULL, NULL, '2020-11-03 19:16:38', '2021-05-11 18:55:05', 1, 0, '1323584742796410881', ' knife4j-ui', NULL, '/doc.html', 'layui-icon-file-b', 90001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584939857395714', NULL, NULL, '2020-11-03 19:16:55', '2021-05-11 18:54:57', 0, 0, '1350298064077774850', 'swagger-ui', NULL, '/swagger-ui.html', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('133', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:08', 1, 0, '100', '娱乐', NULL, '', 'layui-icon-file-b', 60003, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1330860076629590017', NULL, NULL, '2020-11-23 21:05:43', '2021-05-11 18:54:35', 1, 0, '1288879303106289665', ' new编辑', '', '/page/base_markdown2_index', 'layui-icon-file-b', 1, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1330860135886716929', NULL, NULL, '2020-11-23 21:05:57', '2021-05-11 18:54:35', 1, 0, '1288879303106289665', ' new展示', NULL, '/page/base_markdown2_detail', 'layui-icon-file-b', 1, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1332332514865364994', NULL, NULL, '2020-11-27 22:36:37', '2021-08-09 10:09:27', 0, 0, '1350297066072498179', '黑/白名单', '/page/modules_admin_adminBlacklist_adminBlacklist', '/page/manage_xj_blacklist_blacklist', 'layui-icon-file-b', 10011, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1332869984937848833', NULL, NULL, '2020-11-29 10:12:22', '2021-05-11 19:08:21', 1, 0, '1350305706179313666', 'java 代码运行器', NULL, '/page/client_tool_javaCodeRun', 'layui-icon-file-b', 50000, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1335434955345399809', NULL, NULL, '2020-12-06 12:04:39', '2021-05-11 19:08:21', 1, 0, '1350305706179313666', 'new聊天室', NULL, '/page/client_websocket_lts', 'layui-icon-file-b', 50003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1339486097113202690', NULL, NULL, '2020-12-17 16:22:26', '2021-03-11 11:03:48', 1, 0, '1350307256670900225', '文件', '', '', 'layui-icon-file-b', 900, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1339486194576244738', NULL, NULL, '2020-12-17 16:22:49', '2021-06-01 15:18:24', 0, 0, '1393917150250442754', '文件管理', '', '/page/modules_yw_ser_xjFile_xjFile', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1339574156777242625', NULL, NULL, '2020-12-17 22:12:22', '2021-05-11 19:08:21', 1, 0, '1350305706179313666', '文件查看', NULL, '/page/client_file_index', 'layui-icon-file-b', 2, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('134', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:02', 1, 0, '100', '工作学习', NULL, '', 'layui-icon-file-b', 60002, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1340651066982731778', NULL, NULL, '2020-12-20 21:31:37', '2021-03-11 11:03:48', 1, 0, '1350307256670900225', '段子', '', '', 'layui-icon-file-b', 1000, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1340651170208747521', NULL, NULL, '2020-12-20 21:32:01', '2021-08-06 17:17:53', 1, 0, '1340651066982731778', '段子管理', NULL, '/page/manage_yw_astory_xjAstory_xjAstory', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1340651277373214722', NULL, NULL, '2020-12-20 21:32:27', '2021-08-06 17:17:57', 1, 0, '1340651066982731778', '段子分类', NULL, '/page/manage_yw_astory_xjAstoryCategory_xjAstoryCategory', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('135', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:16', 1, 0, '100', '我的系列', NULL, '', 'layui-icon-file-b', 60001, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350297066072498179', NULL, NULL, '2021-01-16 12:21:22', '2021-06-04 17:01:48', 0, 0, '1323584197721440258', '系统增强功能', '', '', 'layui-icon-component', 101, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350298064077774850', NULL, NULL, '2021-01-16 12:25:20', '2021-06-12 13:11:19', 0, 0, '1323584197721440258', '系统功能', '', '-', 'layui-icon-set-sm', 103, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350305706179313666', NULL, NULL, '2021-01-16 12:55:42', '2021-05-11 19:08:21', 1, 0, '1323584197721440258', '业务单页', '', '', 'layui-icon-file-b', 105, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350307256670900225', NULL, NULL, '2021-01-16 13:01:51', '2021-03-11 11:03:48', 1, 0, '1323584197721440258', '业务管理', '', '', 'layui-icon-file-b', 104, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('136', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:08', 1, 0, '133', 'bilbil视频下载', NULL, 'https://xbeibeix.com/api/bilibili/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1369610726125096962', NULL, NULL, '2021-03-10 19:26:56', '2021-08-09 10:09:32', 0, 0, '1350297066072498179', '消息管理', '', '/page/manage_xj_msg_msg', 'layui-icon-file-b', 10013, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('137', NULL, NULL, '2020-07-25 09:29:38', '2021-03-08 17:30:02', 1, 0, '134', '蚂蚁课堂资料下载', NULL, 'http://down.mayikt.com/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1371745117362311170', NULL, NULL, '2021-03-16 16:48:16', '2021-04-26 11:43:21', 1, 0, '0', 'vue-3.0', '', '', 'layui-icon-file-b', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1371747827318890497', NULL, NULL, '2021-03-16 16:59:02', '2021-04-26 11:43:21', 1, 0, '1371745117362311170', '系统管理', '', '', 'el-icon-s-tools', 2, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1371748168869453826', NULL, NULL, '2021-03-16 17:00:23', '2021-04-26 11:43:21', 1, 0, '1371747827318890497', '菜单管理', 'system/menuManager', 'system/menuManager', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1371748298020462594', NULL, NULL, '2021-03-16 17:00:54', '2021-04-26 11:43:21', 1, 0, '1371747827318890497', '系统用户', 'system/userManager', 'system/userManager', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1371748371894738946', NULL, NULL, '2021-03-16 17:01:12', '2021-04-26 11:43:21', 1, 0, '1371747827318890497', '角色管理', 'system/roleManager', 'system/roleManager', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1371748448222683138', NULL, NULL, '2021-03-16 17:01:30', '2021-04-26 11:43:21', 1, 0, '1371747827318890497', '字典管理', 'system/dictionaryManager', 'system/dictionaryManager', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1371748619442561026', NULL, NULL, '2021-03-16 17:02:11', '2021-03-16 17:49:54', 1, 0, '1371747827318890497', '角色菜单权限', 'system/roleMenuAuth', 'system/roleMenuAuth', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1371748740725055490', NULL, NULL, '2021-03-16 17:02:40', '2021-04-26 11:43:21', 1, 0, '1371747827318890497', 'banner 管理', 'system/banner', 'system/banner', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1371748816792952833', NULL, NULL, '2021-03-16 17:02:58', '2021-03-16 17:38:01', 1, 0, '1371747827318890497', '全局配置', 'system/configManager', 'system/configManager', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1371760971646873603', NULL, NULL, '2021-03-16 17:51:16', '2021-03-16 19:11:40', 1, 0, '1371747827318890497', '角色菜单权限', 'system/roleMenuAuth', 'system/roleMenuAuth', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1373813425423097858', NULL, NULL, '2021-03-22 09:46:59', '2021-03-22 09:47:13', 1, 0, '1371747827318890497', '首页', NULL, NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1373814335884861441', NULL, NULL, '2021-03-22 09:50:36', '2021-04-26 11:43:21', 1, 0, '1371745117362311170', '首页', 'dashboard/index', NULL, 'el-icon-menu', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1373920029728415745', NULL, NULL, '2021-03-22 16:50:35', '2021-04-26 11:43:21', 1, 0, '1371745117362311170', '渠道管理', '', NULL, 'el-icon-s-marketing', 1, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1373920193734090753', NULL, NULL, '2021-03-22 16:51:15', '2021-04-26 11:43:21', 1, 0, '1373920029728415745', '球场管理', 'channel/court/index', '', 'el-icon-s-marketing', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1381818163611639810', NULL, NULL, '2021-04-13 11:54:57', '2021-04-26 11:43:21', 1, 0, '1373920029728415745', '租车管理', 'channel/car/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1382518153787023362', NULL, NULL, '2021-04-15 10:16:28', '2021-04-26 11:43:21', 1, 0, '1373920029728415745', '车型管理', 'channel/carModel/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1382528589634805761', NULL, NULL, '2021-04-15 10:57:56', '2021-04-26 11:43:21', 1, 0, '1373920029728415745', '行程套餐', 'channel/trip/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1382535878873784322', NULL, NULL, '2021-04-15 11:26:54', '2021-04-26 11:43:21', 1, 0, '1373920029728415745', '酒店管理', 'channel/hotel/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1382585668898725890', NULL, NULL, '2021-04-15 14:44:45', '2021-04-26 11:43:21', 1, 0, '1373920029728415745', '球具管理', 'channel/ballSet/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1382601227975598081', NULL, NULL, '2021-04-15 15:46:34', '2021-04-26 11:43:21', 1, 0, '1373920029728415745', '陪打管理', 'channel/partner/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1382624871070113794', NULL, NULL, '2021-04-15 17:20:31', '2021-04-26 11:43:21', 1, 0, '1373920029728415745', '不记名卡管理', 'channel/cardd/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1382939990597672962', NULL, NULL, '2021-04-16 14:12:42', '2021-04-26 11:43:21', 1, 0, '1371745117362311170', '预订中心', '', NULL, 'el-icon-s-platform', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1382940093194543106', NULL, NULL, '2021-04-16 14:13:06', '2021-04-26 11:43:21', 1, 0, '1382939990597672962', '订单管理', 'ReservationCenter/order/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1384395237245329410', NULL, NULL, '2021-04-20 14:35:20', '2021-04-20 15:19:13', 1, 0, '1382939990597672962', '酒店订单', 'ReservationCenter/hotelOrder/index', NULL, 'el-icon-location', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1393917029781643265', NULL, NULL, '2021-05-16 21:11:32', '2021-06-04 16:58:34', 0, 0, '0', '兮家-用户功能', '', '', 'layui-icon-template-1', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1393917150250442754', NULL, NULL, '2021-05-16 21:12:00', '2021-06-04 17:00:49', 0, 0, '1393917029781643265', '功能管理', '', '', 'layui-icon-senior', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1393917211910905857', NULL, NULL, '2021-05-16 21:12:15', '2021-08-06 17:18:11', 0, 0, '1393917150250442754', '用户菜单', '', '/page/manage_yw_ser_serFun_serFun', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('140', NULL, NULL, '2020-07-25 09:29:38', '2021-05-11 19:08:21', 1, 0, '1350305706179313666', '聊天室', NULL, '/page/client_websocket_websocket', 'layui-icon-file-b', 50002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1400035312876515330', NULL, NULL, '2021-06-02 18:23:24', '2021-06-04 17:00:33', 0, 0, '1323584197721440258', '代码生成', '', '', 'layui-icon-code-circle', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1400035366265810946', NULL, NULL, '2021-06-02 18:23:37', '2021-06-02 18:24:02', 1, 0, '1400035312876515330', '页面', '', '/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1403573486634409985', NULL, NULL, '2021-06-12 12:42:50', '2021-06-12 12:43:24', 1, 0, '0', 'aaa', '', '', 'layui-icon-login-weibo', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1403573570432409601', NULL, NULL, '2021-06-12 12:43:09', '2021-06-12 12:43:17', 1, 0, '0', 'ccc', '', '', 'layui-icon-file-b', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1403580322913722370', NULL, NULL, '2021-06-12 13:09:59', '2021-06-12 13:13:42', 1, 0, '0', 'aaaa', '', '', 'layui-icon-file-b', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1403580372033216514', NULL, NULL, '2021-06-12 13:10:10', '2021-06-12 13:15:32', 1, 0, '0', 'bbbb', '', '', 'layui-icon-file-b', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('141', NULL, NULL, '2020-07-30 18:16:24', '2021-06-01 15:41:07', 1, 0, '1323584197721440258', '代码生成', NULL, '', 'layui-icon-file-b', 102, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('21', NULL, NULL, '2020-07-25 09:29:38', '2021-08-09 10:08:57', 0, 0, '4', '系统用户', '', '/page/manage_admin_user_user', 'layui-icon-file-b', 10002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('22', NULL, NULL, '2020-07-25 09:29:38', '2021-08-09 10:08:59', 0, 0, '4', '角色管理', '', '/page/manage_admin_role_role', 'layui-icon-file-b', 10003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('23', NULL, NULL, '2020-07-25 09:29:38', '2021-08-09 10:09:06', 0, 0, '4', '角色菜单权限', '', '/page/manage_admin_role_roleMenuAuth', 'layui-icon-file-b', 10006, 3, 1);
INSERT INTO `t_admin_menu` VALUES ('24', NULL, NULL, '2020-07-25 09:29:38', '2021-08-09 10:09:08', 0, 0, '4', '角色URL权限', '', '/page/manage_admin_role_roleUrlAuth', 'layui-icon-file-b', 10007, 3, 1);
INSERT INTO `t_admin_menu` VALUES ('25', NULL, NULL, '2020-07-25 09:29:38', '2021-08-09 10:09:02', 0, 0, '4', '字典管理', '', '/page/manage_admin_dictionary_dictionary', 'layui-icon-file-b', 10004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('30', NULL, NULL, '2020-07-25 09:29:38', '2021-08-09 10:09:21', 0, 0, '1400035312876515330', '数据表', '', '/page/manage_gc_dataBase_dataBase', 'layui-icon-file-b', 40006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('4', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '系统管理', NULL, '', 'layui-icon-set', 100, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('43', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '43', 'ccc', NULL, '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('7', NULL, NULL, '2020-07-25 09:29:38', '2021-08-09 10:08:53', 0, 0, '4', '菜单管理', '', '/page/manage_admin_menu_menu', 'layui-icon-file-b', 10001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('93', NULL, NULL, '2020-07-25 09:29:38', '2021-05-11 19:08:21', 1, 0, '1350305706179313666', '文字注释', NULL, '/page/client_tool_fhConvert', 'layui-icon-file-b', 50001, 3, 0);

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
INSERT INTO `t_admin_role` VALUES ('1', NULL, NULL, '2020-07-25 09:30:08', '2021-04-01 21:49:22', 0, 0, '系统管理员', '拥有该系统所有操作权限', 'SYS', 0);
INSERT INTO `t_admin_role` VALUES ('1368731765383950337', NULL, NULL, '2021-03-08 09:14:16', '2021-03-08 09:15:05', 1, 0, '1', '1', '1', 0);
INSERT INTO `t_admin_role` VALUES ('1368811392576073729', NULL, NULL, '2021-03-08 14:30:40', '2021-03-08 17:16:25', 1, 0, '7', '测试', 'test', 0);
INSERT INTO `t_admin_role` VALUES ('1369586905980686338', NULL, NULL, '2021-03-10 17:52:17', '2021-03-10 17:52:51', 1, 0, '23', '1', '1', 0);
INSERT INTO `t_admin_role` VALUES ('1369587103423352834', NULL, NULL, '2021-03-10 17:53:04', '2021-03-10 17:54:23', 1, 0, '1335434955345399809', '3', '2', 0);
INSERT INTO `t_admin_role` VALUES ('1369587792547500035', NULL, NULL, '2021-03-10 17:55:48', '2021-03-10 18:03:33', 1, 0, '78', '2', '1', 0);
INSERT INTO `t_admin_role` VALUES ('1369589287858819073', NULL, NULL, '2021-03-10 18:01:44', '2021-03-10 18:01:52', 1, 0, '1335434955345399809', '2', '1', 0);
INSERT INTO `t_admin_role` VALUES ('1369589706886565889', NULL, NULL, '2021-03-10 18:03:24', '2021-03-10 18:03:30', 1, 0, '1', '3', '2', 0);
INSERT INTO `t_admin_role` VALUES ('1384407338139525121', NULL, NULL, '2021-04-20 15:23:25', '2021-05-09 11:04:42', 0, 0, '体验成员', '体验成员', 'TEST', 0);
INSERT INTO `t_admin_role` VALUES ('1384412081993818113', NULL, NULL, '2021-04-20 15:42:16', '2021-04-20 15:42:24', 1, 0, 'qq', 'qq', NULL, 0);
INSERT INTO `t_admin_role` VALUES ('31', NULL, NULL, '2020-07-25 09:30:08', '2021-04-20 15:24:13', 1, 0, '系统体验用户', '开发给第三方人员使用的角色，多数功能接口权限受限', 'TY_USER', 0);

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
INSERT INTO `t_admin_role_auth` VALUES ('1400300402811396098', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364102127621', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402832367618', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364102127622', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402832367619', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364102127623', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402840756225', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364102127624', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402840756226', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364102127625', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402849144834', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364102127626', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402849144835', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364102127627', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402853339137', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402853339138', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402857533441', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321923', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402857533442', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321924', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402865922050', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321925', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402870116354', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321926', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402870116355', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321927', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402874310657', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321928', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402874310658', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321929', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402874310659', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321930', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402882699266', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321931', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402882699267', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321932', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402886893569', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321933', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402886893570', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321934', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402895282178', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321935', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402895282179', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364106321936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402895282180', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516224', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402895282181', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516225', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402903670786', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516226', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402903670787', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516227', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402903670788', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516228', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402903670789', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516229', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402912059393', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516230', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402912059394', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516231', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402912059395', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516232', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402912059396', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516233', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402920448001', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516234', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402920448002', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516235', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402928836610', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516236', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402933030914', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516237', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402933030915', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516238', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402933030916', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516239', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402933030917', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516240', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402933030918', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364110516241', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402933030919', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710528', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402933030920', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710529', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419522', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710530', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419523', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710532', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419524', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710533', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419525', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710534', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419526', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710535', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419527', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419528', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419529', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710538', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419530', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710539', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419531', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710540', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419532', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710541', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419533', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '528072364114710542', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402941419534', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '533782851083177984', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402949808130', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '533782851095760896', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402949808131', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '533782851095760897', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402949808132', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '533782851095760898', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402949808133', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '533782851095760899', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402949808134', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '533782851099955200', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402949808135', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '533782851099955201', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002434', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '533782851099955202', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002435', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203862609920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002436', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203866804224', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002437', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203866804225', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002438', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203866804226', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002439', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203866804227', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002440', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203866804228', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002441', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203866804229', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002442', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203866804230', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002443', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998528', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002444', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998529', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002445', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998530', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002446', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998531', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402954002447', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998532', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402962391042', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998533', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402962391043', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998534', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402962391044', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998535', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402962391045', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402962391046', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402962391047', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998538', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402966585345', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998539', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402966585346', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203870998540', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402966585347', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203875192840', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402966585348', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203875192841', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402966585349', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203875192842', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402966585350', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203875192843', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402966585351', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203875192844', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779649', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203875192845', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779650', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203879387136', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779651', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203879387137', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779652', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203879387138', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779653', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203879387139', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779654', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '537112203879387140', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779655', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218477150208', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779656', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218481344512', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779657', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218481344513', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779658', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218481344514', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779659', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218481344515', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779660', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218481344516', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779661', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218481344517', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779662', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218481344518', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779663', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218489733120', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779664', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218489733121', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779665', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218489733122', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779666', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218489733123', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779667', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218489733124', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779668', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218489733125', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402970779669', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218489733126', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402979168257', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218489733127', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402979168258', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218493927424', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402979168259', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218493927425', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400300402979168260', NULL, NULL, '2021-06-03 11:56:47', '2021-06-03 11:56:47', 0, 0, '560261218493927426', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400728976107831298', NULL, NULL, '2021-06-04 16:19:46', '2021-06-04 16:19:46', 0, 0, '560707878739120128', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400728976120414209', NULL, NULL, '2021-06-04 16:19:46', '2021-06-04 16:19:46', 0, 0, '560707878734925824', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799972171778', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703362654209', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799984754690', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703362654210', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799984754691', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703362654211', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799984754692', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703362654212', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799984754693', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703362654213', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799988948993', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703362654214', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799988948994', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703362654208', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799988948995', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042817', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799988948996', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042818', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168799997337601', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042819', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800001531906', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042816', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800005726210', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042821', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800005726211', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042822', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800005726212', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042823', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800005726213', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042824', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800005726214', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042825', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800009920513', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042826', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800009920514', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042827', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800009920515', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703371042820', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800009920516', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703375237121', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403168800009920517', NULL, NULL, '2021-06-11 09:54:46', '2021-06-11 09:54:46', 0, 0, '563147703375237120', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403562876244860930', NULL, NULL, '2021-06-12 12:00:39', '2021-06-12 12:00:39', 0, 0, '563541778662297601', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403562876244860931', NULL, NULL, '2021-06-12 12:00:39', '2021-06-12 12:00:39', 0, 0, '563541778662297600', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565314', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364102127621', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565315', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364102127622', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565316', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364102127624', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565317', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364102127625', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565318', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364102127627', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565319', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321921', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565320', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321925', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565321', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321927', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565322', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '533782851083177984', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565323', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321928', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565324', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321930', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565325', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321931', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565326', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321932', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565327', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321933', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989447565328', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364106321936', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953922', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364110516229', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953923', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364110516230', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953924', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364110516231', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953925', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364110516234', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953926', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364110516237', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953927', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364110516241', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953928', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364114710528', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953929', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364114710529', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953930', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364114710533', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953931', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364114710537', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953932', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364114710538', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953933', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '528072364114710539', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989455953934', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '533782851095760896', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148226', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '533782851095760897', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148227', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '533782851095760898', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148228', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '533782851095760899', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148229', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '533782851099955200', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148230', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203862609920', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148231', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203866804225', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148232', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203866804226', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148233', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203866804230', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148234', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203870998529', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148235', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203870998530', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148236', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203870998534', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148237', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203870998536', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148238', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203870998537', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989460148239', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203870998538', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989464342529', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203875192840', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989464342530', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203875192842', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989464342531', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203875192843', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989464342532', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203879387137', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989464342533', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203879387139', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989464342534', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '537112203879387140', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989464342535', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '563541778662297600', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403649989464342536', NULL, NULL, '2021-06-12 17:46:48', '2021-06-12 17:46:48', 0, 0, '563541778662297601', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403999770669789185', NULL, NULL, '2021-06-13 16:56:42', '2021-06-13 16:56:42', 0, 0, '563978673187852288', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403999770678177794', NULL, NULL, '2021-06-13 16:56:42', '2021-06-13 16:56:42', 0, 0, '563978673200435200', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1403999770678177795', NULL, NULL, '2021-06-13 16:56:42', '2021-06-13 16:56:42', 0, 0, '563978673187852288', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1403999770678177796', NULL, NULL, '2021-06-13 16:56:42', '2021-06-13 16:56:42', 0, 0, '563978673200435200', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1407517540850159618', NULL, NULL, '2021-06-23 09:55:07', '2021-06-23 09:55:07', 0, 0, '567496442109890560', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1407517540850159619', NULL, NULL, '2021-06-23 09:55:07', '2021-06-23 09:55:07', 0, 0, '567496442139250688', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1407517540850159620', NULL, NULL, '2021-06-23 09:55:07', '2021-06-23 09:55:07', 0, 0, '567496442109890560', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1407517540850159621', NULL, NULL, '2021-06-23 09:55:07', '2021-06-23 09:55:07', 0, 0, '567496442139250688', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1407532458529845250', NULL, NULL, '2021-06-23 10:54:23', '2021-06-23 10:54:23', 0, 0, '567511360074813440', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1407532458529845251', NULL, NULL, '2021-06-23 10:54:23', '2021-06-23 10:54:23', 0, 0, '567511360074813440', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427906', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383617', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427907', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383616', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427908', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383619', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427909', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383618', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427910', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383621', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427911', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383620', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427912', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383623', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427913', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383624', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427914', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383622', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427915', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383626', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427916', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383627', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427917', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427918', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427919', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577922', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427920', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577923', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427921', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577924', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427922', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927590383625', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427923', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577926', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427924', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577927', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427925', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577928', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427926', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577929', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427927', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577930', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427928', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577931', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427929', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577925', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427930', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577933', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427931', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577934', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427932', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577935', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427933', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427934', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577937', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427935', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577938', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427936', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772224', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427937', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772225', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427938', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772226', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427939', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927594577932', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427940', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772228', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427941', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772229', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427942', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772230', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427943', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772231', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427944', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772232', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427945', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966528', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427946', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927598772227', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024082427947', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966530', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176642', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966531', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176643', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966532', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176644', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966533', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176645', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966534', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176646', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966535', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176647', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966529', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176648', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176649', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966538', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176650', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966539', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176651', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966540', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176652', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966541', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176653', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966542', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176654', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176655', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966544', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176656', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966545', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176657', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927602966543', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176658', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927607160833', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176659', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927607160834', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176660', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927607160832', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176661', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927615549440', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176662', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938049', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176663', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938050', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176664', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938051', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176665', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938052', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176666', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938053', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176667', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938054', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176668', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938048', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176669', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938056', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176670', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938057', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176671', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938058', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176672', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938059', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176673', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938060', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176674', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938061', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176675', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938055', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176676', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938063', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176677', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938064', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176678', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938065', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176679', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938066', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176680', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938067', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176681', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938068', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176682', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927623938062', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176683', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132353', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176684', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132354', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176685', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132355', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176686', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132356', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176687', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132357', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176688', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132358', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176689', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132359', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176690', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132352', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176691', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132361', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176692', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132362', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176693', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132363', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176694', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132364', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176695', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132365', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176696', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132366', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176697', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132360', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176698', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132368', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176699', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132367', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176700', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132370', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176701', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132371', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176702', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927628132369', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176703', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326656', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176704', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326658', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176705', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326659', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176706', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326660', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176707', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326661', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176708', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326662', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176709', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326657', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176710', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326664', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408263024120176711', NULL, NULL, '2021-06-25 11:17:25', '2021-06-25 11:17:25', 0, 0, '568241927632326663', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676866', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214081', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676867', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214082', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676868', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214083', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676869', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214080', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676870', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214085', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676871', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214086', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676872', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214084', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676873', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546363408385', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676874', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546363408386', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676875', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546363408387', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676876', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546363408384', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676877', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546371796992', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676878', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185601', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676879', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185602', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676880', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185603', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676881', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185604', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676882', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185605', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676883', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185606', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676884', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185600', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676885', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185608', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676886', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185609', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676887', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185610', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676888', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185611', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676889', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185612', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676890', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185613', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676891', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185607', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676892', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185615', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676893', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379904', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676894', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379905', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676895', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379906', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676896', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379907', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676897', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379908', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676898', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185614', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676899', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379910', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676900', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379911', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676901', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379912', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676902', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379913', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676903', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379914', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676904', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379915', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676905', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379909', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676906', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379917', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676907', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379918', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676908', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379919', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676909', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676910', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379916', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676911', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379922', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676912', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676913', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214081', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676914', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214082', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676915', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214083', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676916', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214080', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676917', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214085', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676918', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214086', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676919', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546359214084', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676920', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546363408385', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676921', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546363408386', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676922', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546363408387', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676923', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546363408384', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676924', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546371796992', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676925', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185601', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676926', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185602', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676927', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185603', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676928', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185604', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676929', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185605', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676930', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185606', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676931', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185600', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676932', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185608', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676933', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185609', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676934', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185610', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676935', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185611', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676936', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185612', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676937', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185613', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676938', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185607', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676939', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185615', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676940', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379904', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676941', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379905', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676942', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379906', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676943', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379907', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676944', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379908', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676945', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546380185614', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676946', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379910', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676947', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379911', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676948', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379912', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676949', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379913', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676950', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379914', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676951', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379915', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676952', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379909', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676953', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379917', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676954', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379918', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676955', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379919', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676956', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379920', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676957', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379916', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676958', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379922', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408358640880676959', NULL, NULL, '2021-06-25 17:38:14', '2021-06-25 17:38:14', 0, 0, '568337546384379921', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711724216322', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926785', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711732604930', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926784', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711732604931', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926787', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711732604932', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926786', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711732604933', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926789', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711732604934', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926790', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711736799233', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926791', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711736799234', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926788', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711736799235', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926793', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711736799236', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926792', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711736799237', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614972121088', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711736799238', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614972121089', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711736799239', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926785', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993538', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926784', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993539', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926787', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993540', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926786', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993541', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926789', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993542', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926790', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993543', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926791', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993544', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926788', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993545', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926793', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993546', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614967926792', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711740993547', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614972121088', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414760711745187841', NULL, NULL, '2021-07-13 09:36:54', '2021-07-13 09:36:54', 0, 0, '574739614972121089', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1414774417925431298', NULL, NULL, '2021-07-13 10:31:22', '2021-07-13 10:31:22', 0, 0, '574753322104393728', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1414774417938014210', NULL, NULL, '2021-07-13 10:31:22', '2021-07-13 10:31:22', 0, 0, '574753322104393728', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056107024385', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961210187776', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056115412993', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961210187777', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056115412994', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961210187778', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056115412995', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961205993472', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056115412996', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102336', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607298', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102337', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607299', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102338', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607300', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102339', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607301', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102340', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607302', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961268908032', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607303', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102342', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607304', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102343', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607305', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102344', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607306', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102345', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607307', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102341', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607308', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961210187776', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607309', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961210187777', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607310', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961210187778', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607311', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961205993472', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607312', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102336', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607313', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102337', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607314', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102338', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607315', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102339', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607316', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102340', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607317', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961268908032', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607318', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102342', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607319', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102343', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607320', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102344', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607321', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102345', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423919056119607322', NULL, NULL, '2021-08-07 16:08:46', '2021-08-07 16:08:46', 0, 0, '583897961273102341', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967391883266', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870551666689', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967391883267', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870551666690', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967391883268', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870551666691', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967391883269', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870551666688', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967391883270', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860993', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466178', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860994', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466179', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860995', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466180', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860996', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466181', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860997', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466182', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860992', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466183', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870551666689', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466184', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870551666690', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466185', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870551666691', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466186', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870551666688', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466187', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860993', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466188', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860994', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967404466189', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860995', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967412854785', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860996', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967412854786', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860997', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424546967412854787', NULL, NULL, '2021-08-09 09:43:59', '2021-08-09 09:43:59', 0, 0, '584525870555860992', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628118405121', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374401', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628118405122', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374402', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628118405123', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374403', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628118405124', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374400', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628118405125', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374405', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628126793729', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374406', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628126793730', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374407', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628126793731', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530682568704', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628126793732', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530682568705', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628126793733', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374404', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628130988033', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123137', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628130988034', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123138', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628130988035', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123139', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628130988036', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123140', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628130988037', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123141', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628130988038', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123136', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628130988039', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123143', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628130988040', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123144', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182337', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123145', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182338', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123146', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182339', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123142', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182340', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374401', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182341', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374402', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182342', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374403', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182343', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374400', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182344', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374405', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182345', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374406', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182346', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374407', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182347', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530682568704', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628135182348', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530682568705', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376642', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530678374404', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376643', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123137', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376644', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123138', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376645', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123139', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376646', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123140', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376647', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123141', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376648', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123136', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376649', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123143', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376650', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123144', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376651', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123145', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376652', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123146', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1424560628139376653', NULL, NULL, '2021-08-09 10:38:16', '2021-08-09 10:38:16', 0, 0, '584539530716123142', '1384407338139525121');

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
INSERT INTO `t_admin_role_menu` VALUES ('1381579363673223170', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417473', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '4');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417474', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417475', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417476', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417477', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417478', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1297047088646905857');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417479', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '23');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417480', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '24');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363677417481', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1350297066072498179');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363681611778', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1318472952462770177');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363681611779', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1339486194576244738');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363681611780', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1297533242571763714');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363681611781', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1311827586636156929');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363681611782', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1332332514865364994');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363681611783', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1321432835319414785');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363681611784', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1369610726125096962');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363685806081', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '30');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363685806082', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1350298064077774850');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363685806088', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1319948617724743682');
INSERT INTO `t_admin_role_menu` VALUES ('1381579363685806090', NULL, NULL, '2021-04-12 20:06:03', '2021-04-12 20:06:03', 0, 0, '31', '1323584939857395714');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855128428545', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1393917029781643265');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855136817153', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1393917150250442754');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855136817154', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1339486194576244738');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855136817155', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1393917211910905857');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855136817156', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855136817157', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '4');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855141011457', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855141011458', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855141011459', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855141011460', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855141011461', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1297047088646905857');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855141011462', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '23');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855141011463', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '24');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855145205762', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1323584197721440258');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855145205763', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1350297066072498179');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855145205764', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1318472952462770177');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855145205765', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1297533242571763714');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855145205766', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1311827586636156929');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855145205767', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1332332514865364994');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855145205768', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1321432835319414785');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855145205769', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1369610726125096962');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855149400067', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '30');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855149400068', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1350298064077774850');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855149400069', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1323584939857395714');
INSERT INTO `t_admin_role_menu` VALUES ('1399631855149400070', NULL, NULL, '2021-06-01 15:40:12', '2021-06-01 15:40:12', 0, 0, '1', '1319948617724743682');
INSERT INTO `t_admin_role_menu` VALUES ('1400035313212059650', NULL, NULL, '2021-06-02 18:23:24', '2021-06-02 18:23:24', 0, 0, '1', '1400035312876515330');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525889', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525890', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '4');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525891', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '7');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525892', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '21');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525893', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '22');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525894', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '25');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525895', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1297047088646905857');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525896', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '23');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525897', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '24');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525898', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1323584197721440258');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525899', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1400035312876515330');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525900', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '30');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525901', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1350297066072498179');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525902', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1297533242571763714');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525903', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1311827586636156929');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525904', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1332332514865364994');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525905', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1321432835319414785');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525906', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1369610726125096962');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525907', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1350298064077774850');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525908', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1323584939857395714');
INSERT INTO `t_admin_role_menu` VALUES ('1400036317999525909', NULL, NULL, '2021-06-02 18:27:24', '2021-06-02 18:27:24', 0, 0, '1384407338139525121', '1319948617724743682');

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
INSERT INTO `t_admin_role_user` VALUES ('1', NULL, NULL, '2021-03-12 18:26:57', '2021-03-12 18:27:03', 0, 0, '1', '1');
INSERT INTO `t_admin_role_user` VALUES ('1384410038998671361', NULL, NULL, '2021-04-20 15:34:09', '2021-04-20 15:34:09', 0, 0, '1371086008254328834', '1384407338139525121');
INSERT INTO `t_admin_role_user` VALUES ('2', NULL, NULL, '2021-04-26 11:42:30', '2021-04-26 11:42:30', 0, 0, '1', '31');

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
  `position` int(1) NOT NULL DEFAULT 0 COMMENT '职位(字典code)',
  `wx_open_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信公众号openId (需进行h5授权获得, 用于给管理端用户发送微信模板信息)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表--系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', NULL, NULL, '2020-08-02 15:11:04', '2021-08-09 09:57:13', 0, 0, 'http://xijia.plus/oss/file/image/head/20200822150143006266-5.png', 'wangsong', '17628689969', '兮家小二', 'dfd5e22c8ee4de7da4f07a75fefb6420', '四川成都', 0, 1, 0, '2020-08-02 23:11:05', '2021-08-09 09:57:12', 1, NULL);
INSERT INTO `t_admin_user` VALUES ('1313865056773025794', NULL, NULL, '2020-10-07 15:33:34', '2021-03-08 09:28:42', 1, 0, 'http://xijia.plus/oss/file/image/head/20201007233323096566-tp (15).jpg', '1', '1', '1', '624bef4ab99df8464ac8dc9e4616e067', '1', 1, 1, 0, '2020-10-07 23:33:35', '2021-01-15 15:25:29', 0, NULL);
INSERT INTO `t_admin_user` VALUES ('1368753680643276801', NULL, NULL, '2021-03-08 10:41:21', '2021-03-08 10:43:18', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210308104112449874-1.jpg', '11', '1', '1', '624bef4ab99df8464ac8dc9e4616e067', '1', 1, 0, 0, '2021-03-08 10:41:22', NULL, 0, NULL);
INSERT INTO `t_admin_user` VALUES ('1368754110140055553', NULL, NULL, '2021-03-08 10:43:03', '2021-03-08 10:43:20', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210308104246663754-1.jpg', '22', '2141', '1', '624bef4ab99df8464ac8dc9e4616e067', '1', 1, 0, 0, '2021-03-08 10:43:05', NULL, 0, NULL);
INSERT INTO `t_admin_user` VALUES ('1369230316023054338', NULL, NULL, '2021-03-09 18:15:19', '2021-03-09 18:15:26', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210309181459145927-1.jpg', 'wangsong2', '1', '王松2', '8b634720a20201bde3947af2c1e1dccb', 'z', 22, 1, 0, '2021-03-09 18:15:21', NULL, 0, NULL);
INSERT INTO `t_admin_user` VALUES ('1369588977560014849', NULL, NULL, '2021-03-10 18:00:30', '2021-03-10 18:00:38', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210310180023398020-2.jpg', '11', '1', '1', '624bef4ab99df8464ac8dc9e4616e067', '1', 1, 0, 0, '2021-03-10 18:00:33', NULL, 0, NULL);
INSERT INTO `t_admin_user` VALUES ('1369591924834521090', NULL, NULL, '2021-03-10 18:12:13', '2021-03-10 18:12:56', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210310181204942659-5.jpg', '1', '2', '1', '4248456cc5540deb16685bef40ca2959', '4', 4, 2, 0, '2021-03-10 18:12:15', NULL, 0, NULL);
INSERT INTO `t_admin_user` VALUES ('1371086008254328834', NULL, NULL, '2021-03-14 21:09:12', '2021-07-08 16:35:33', 0, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210314210827213063-5.jpg', '10000', '10000', '体验用户', '7121b2eeea031a4ecfffcdecb60158fa', '0', 0, 0, 0, '2021-03-14 21:09:13', '2021-07-08 16:35:35', 1, NULL);
INSERT INTO `t_admin_user` VALUES ('1371744755276435457', NULL, NULL, '2021-03-16 16:46:50', '2021-05-11 17:20:01', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210322104221528735-0080L5aXgy1gcrfgxe0wsj30pg0ron7h20-20E589AFE69CAC.jpg', 'guquan', '15828327664', '顾全', '229db5460300fdcede08e56a90529475', '四川成都   ', 24, 1, 0, '2021-03-16 16:46:50', '2021-04-20 15:33:10', 0, NULL);
INSERT INTO `t_admin_user` VALUES ('1385163034765975554', NULL, NULL, '2021-04-22 17:25:29', '2021-05-11 17:19:51', 1, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210422172533199229-1617283088827.jpg', 'lijunfa', '18780570608', '李军发', '229db5460300fdcede08e56a90529475', '45454', 22, 1, 0, '2021-04-22 17:26:17', '2021-04-22 17:26:49', 0, NULL);
INSERT INTO `t_admin_user` VALUES ('20', NULL, NULL, '2020-07-25 09:31:07', '2021-03-12 16:42:35', 1, 0, 'http://xijia.plus/oss/file/image/head/20200712000102739895-1.png', '10000', '10000', '系统体验用户', '7121b2eeea031a4ecfffcdecb60158fa', '四川成都', 20, 2, 0, '2020-01-31 10:15:07', '2021-03-12 16:40:57', 0, NULL);

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
-- Table structure for t_test_user
-- ----------------------------
DROP TABLE IF EXISTS `t_test_user`;
CREATE TABLE `t_test_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '测试表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_test_user
-- ----------------------------
INSERT INTO `t_test_user` VALUES ('1423570991589703681', NULL, NULL, '2021-08-06 17:05:46', '2021-08-06 17:06:00', 1, 0, '2', '3');
INSERT INTO `t_test_user` VALUES ('1423571017002991618', NULL, NULL, '2021-08-06 17:05:52', '2021-08-06 17:06:05', 1, 0, '3', '4');

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
INSERT INTO `t_xj_admin_banner` VALUES ('1300258807352893441', NULL, NULL, '2020-08-31 10:27:12', '2021-04-14 22:25:07', 1, 0, 1, '测试3', '测试数据3', 'http://yabei.520ban.com/oss/file/image/banner/20200910093614691653-qs44ufe2024qs44ufe2024.jpg', 3, 0, 0, '/page/banner');
INSERT INTO `t_xj_admin_banner` VALUES ('1300260217146548226', NULL, NULL, '2020-08-31 10:32:48', '2021-04-14 22:25:10', 0, 0, 1, '测试2', '测试数据2', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210401214853224489-qs44ufe2024qs44ufe2024.jpg', 1, 1, 0, '/page/logoBanner/page/logoBanner');
INSERT INTO `t_xj_admin_banner` VALUES ('1300262684328435714', NULL, NULL, '2020-08-31 10:42:36', '2021-03-22 10:38:07', 0, 0, 1, '测试1', '测试数据一', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210322103754968659-aaaa.png', 0, 0, 2, 'http://www.baidu.com');
INSERT INTO `t_xj_admin_banner` VALUES ('1303881706571894785', NULL, NULL, '2020-09-10 10:23:18', '2020-09-24 16:16:13', 1, 0, 1, '231', '312', 'http://yabei.520ban.com/oss/file/image/banner/20200919134953270044-2dea1d468fae5da7a7ec808d3ca41ca3.jpg', 1, 0, 2, 'https://www.xiaopiu.com/project?proid=5f2d1df9fd472f065fa9a43d');
INSERT INTO `t_xj_admin_banner` VALUES ('1309111625118248961', NULL, NULL, '2020-09-24 20:45:06', '2021-02-05 15:36:11', 0, 0, 1, '测试', '测试描叙', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/2.jpg', 0, 0, 0, '');
INSERT INTO `t_xj_admin_banner` VALUES ('1309668956979171329', NULL, NULL, '2020-09-26 09:39:46', '2020-09-26 09:49:12', 1, 0, 1, 'q', '1', 'http://yabei.520ban.com/oss/file/image/banner/20200926093943912599-dog_logo.jpg', 0, 0, 2, NULL);
INSERT INTO `t_xj_admin_banner` VALUES ('1309670283884990466', NULL, NULL, '2020-09-26 09:45:02', '2020-09-26 09:49:21', 1, 0, 1, '1', '1', 'http://yabei.520ban.com/oss/file/image/banner/20200926094455863590-0080L5aXgy1gcrfgxe0wsj30pg0ron7h.jpg', 0, 0, 1, NULL);
INSERT INTO `t_xj_admin_banner` VALUES ('1309670412985667586', NULL, NULL, '2020-09-26 09:45:33', '2020-09-26 09:49:18', 1, 0, 1, '1', '1', 'http://yabei.520ban.com/oss/file/image/banner/20200926094529206937-c0140160gy1gdk3igvhalj20qm0qmwgg.jpg', 0, 0, 1, NULL);
INSERT INTO `t_xj_admin_banner` VALUES ('1309671292019511297', NULL, NULL, '2020-09-26 09:49:03', '2020-09-26 09:49:15', 1, 0, 1, '1', '1', 'http://yabei.520ban.com/oss/file/image/banner/20200926094858974599-c0140160gy1gdk3igvhalj20qm0qmwgg.jpg', 0, 0, 1, '11');
INSERT INTO `t_xj_admin_banner` VALUES ('1369571919208206338', NULL, NULL, '2021-03-10 16:52:44', '2021-03-10 16:52:55', 0, 0, 1, '1', '1', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210310165236917290-5.jpg', 0, 0, 1, 'bbb');

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
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332333202949324802', NULL, NULL, '2020-11-27 22:39:21', '2021-03-28 13:44:58', 0, 0, 1, '*', '允许所有 ip 地址访问，优先级比黑名单(*) 高 ， 开启了白名单(*), 黑名单（*）将无效', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332337401510551554', NULL, NULL, '2020-11-27 22:56:02', '2021-06-28 11:09:43', 0, 0, 2, '*', '禁止所有 ip 访问,除本地 [127.0.0.1 / localhost] ,不建议配置在所有资源上，一旦配置，所有用户(包括自己) 将无法访问所有资源，因为每个用户的ip地址都不一样， 开启此功能需提前配置所有用户的ip地址为白名单', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332374043059408898', NULL, NULL, '2020-11-28 01:21:40', '2020-11-28 02:16:08', 1, 0, 1, '127.0.0.1', '本地ip', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332385237027622913', NULL, NULL, '2020-11-28 02:06:09', '2020-11-28 02:22:30', 1, 0, 1, 'xijia.plus', '放行所有在主站域名访问的请求', 0);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1421369811404894210', NULL, NULL, '2021-07-31 15:19:05', '2021-07-31 15:22:05', 0, 0, 2, '192.168.2.163', '本地', 1);

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
INSERT INTO `t_xj_admin_config` VALUES ('1365182627308433409', NULL, NULL, '2021-02-26 14:11:17', '2021-03-11 11:36:21', 0, 0, 'login_bg_img', '背景图(登录页)', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210311113615990505-1.jpg', 0, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1365185332319997953', NULL, NULL, '2021-02-26 14:22:01', '2021-02-26 14:29:28', 0, 0, 'beian', '备案号(登录页)', '备案号：蜀ICP备19022468号-1', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365187122549551105', NULL, NULL, '2021-02-26 14:29:09', '2021-02-26 14:56:26', 0, 0, 'project_desc', '项目描叙(登录页)', '©2020-2021 该后台系统为个人开发运营，作者联系方式 QQ:1720696548', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365189285623484417', NULL, NULL, '2021-02-26 14:37:44', '2021-02-26 14:56:37', 1, 0, 'login_code_img', '滑动验证码图片集', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/timg2028929.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/u3D12224875882C42672150826fm3D21426gp3D0.jpg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/timg.jpg', 0, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1369572634420924417', NULL, NULL, '2021-03-10 16:55:34', '2021-03-10 16:56:04', 0, 0, 'test', '测试', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210310165529350451-1615366529000.jpeg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210310165518220164-1.jpg', 3, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1383627414470467586', NULL, NULL, '2021-04-18 11:44:16', '2021-08-02 11:10:15', 0, 0, 'is_sign', '验签开关 ( true / false) -默认true', 'false', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1383636872395255809', NULL, NULL, '2021-04-18 12:21:51', '2021-04-19 10:25:30', 0, 0, 'is_swagger', 'Swagger文档开关(true / false)', 'true', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1383644845431689218', NULL, NULL, '2021-04-18 12:53:32', '2021-04-19 10:24:54', 0, 0, 'is_login_token', 'api 管理端登录是否需要访问令牌', 'false', 0, 0);

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
INSERT INTO `t_xj_admin_msg` VALUES ('1', NULL, NULL, '2021-02-05 17:07:20', '2021-06-24 16:55:30', 0, 0, '1', '系统通知1', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('1404004565266829314', NULL, NULL, '2021-06-13 17:15:45', '2021-06-13 17:15:45', 0, 0, '1371086008254328834', '测试消息', 2, 1, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1404005056965033986', NULL, NULL, '2021-06-13 17:17:43', '2021-06-24 17:03:54', 0, 0, '1', '测试111', 2, 1, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('2', NULL, NULL, '2021-02-05 17:41:38', '2021-06-14 09:49:10', 0, 0, '1', '系统通知2', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('3', NULL, NULL, '2021-02-05 17:42:44', '2021-06-24 16:55:24', 0, 0, '1', '系统通知3', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('4', NULL, NULL, '2021-02-06 09:59:01', '2021-06-14 12:16:28', 0, 0, '1', '系统通知4', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('5', NULL, NULL, '2021-02-06 09:59:21', '2021-06-14 12:16:21', 0, 0, '1', '系统通知5', 2, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('6', NULL, NULL, '2021-02-06 09:59:29', '2021-06-14 09:49:11', 0, 0, '1', '系统通知6', 2, 