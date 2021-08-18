/*
 Navicat Premium Data Transfer

 Source Server         : 47.107.128.84
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 47.107.128.84:3306
 Source Schema         : xijia-server

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 18/08/2021 09:10:43
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
INSERT INTO `t_admin_authority` VALUES ('528072364102127621', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:49', 0, 672, '', '', '/api/admin/adminAuthority', 'base--URL权限管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127622', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:49', 0, 672, '528072364102127621', 'GET', '/api/admin/adminAuthority/findByRoleIdAuthorityTreeChecked', '查询所有 || 根据角色ID选中 -> Tree ', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127623', NULL, NULL, '2021-03-06 14:57:53', '2021-05-11 15:57:01', 0, 672, '528072364102127621', 'PUT', '/api/admin/adminAuthority/refreshAuthority', '扫描权限', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127624', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:42:33', 0, 672, '528072364102127621', 'GET', '/api/admin/adminAuthority/findList', '查询所有', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127625', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:19:58', 0, 672, '528072364102127621', 'GET', '/api/admin/adminAuthority/findByRoleIdList', '查询所有 || 根据角色ID选中', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127626', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 672, '528072364102127621', 'PUT', '/api/admin/adminAuthority/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364102127627', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 672, '', '', '/api/admin/adminDictionary', 'base--字典管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321920', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 672, '528072364102127627', 'POST', '/api/admin/adminDictionary/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321921', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 672, '528072364102127627', 'GET', '/api/admin/adminDictionary/generateEnum', '生成枚举', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321923', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 672, '528072364102127627', 'DELETE', '/api/admin/adminDictionary/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321924', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 672, '528072364102127627', 'PUT', '/api/admin/adminDictionary/upd', '编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321925', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:50', 0, 672, '528072364102127627', 'GET', '/api/admin/adminDictionary/findByCode', 'Code查询(默认返回Tree数据, 可指定Tree或List)', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321926', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 672, '528072364102127627', 'PUT', '/api/admin/adminDictionary/updBySort', '修改排序', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321927', NULL, NULL, '2021-03-06 14:57:53', '2021-05-21 21:01:46', 0, 672, '528072364102127627', 'GET', '/api/admin/adminDictionary/findCodeGroup', '查询所有-code分组', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321928', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 672, '', '', '/api/admin/adminMenu', 'base--菜单管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321929', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 672, '528072364106321928', 'POST', '/api/admin/adminMenu/insert', '菜单添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321930', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 672, '528072364106321928', 'GET', '/api/admin/adminMenu/findByPidOrRoleId', 'pid + roleId 查询菜单列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321931', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 672, '528072364106321928', 'GET', '/api/admin/adminMenu/findByPidOrRoleIdTree', 'pid + roleId 查询菜单列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321932', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 672, '528072364106321928', 'GET', '/api/admin/adminMenu/findTree', '左导航菜单', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321933', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 672, '528072364106321928', 'GET', '/api/admin/adminMenu/findList', '查询所有', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321934', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:51', 0, 672, '528072364106321928', 'DELETE', '/api/admin/adminMenu/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321935', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '528072364106321928', 'PUT', '/api/admin/adminMenu/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364106321936', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '', '', '/api/admin/adminRole', 'base--角色管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516224', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '528072364106321936', 'POST', '/api/admin/adminRole/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516225', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '528072364106321936', 'PUT', '/api/admin/adminRole/updUserRole', '用户的角色分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516226', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '528072364106321936', 'PUT', '/api/admin/adminRole/updRoleAuth', '角色的URL权限分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516227', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '528072364106321936', 'PUT', '/api/admin/adminRole/updRoleAuthAll', '所有角色拥有所有权限', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516228', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '528072364106321936', 'PUT', '/api/admin/adminRole/updRoleMenu', '角色的菜单分配', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516229', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '528072364106321936', 'GET', '/api/admin/adminRole/findUserRole', '获取指定用户的角色列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516230', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:52', 0, 672, '528072364106321936', 'GET', '/api/admin/adminRole/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516231', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '528072364106321936', 'GET', '/api/admin/adminRole/findList', '查询所有', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516232', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '528072364106321936', 'DELETE', '/api/admin/adminRole/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516233', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '528072364106321936', 'PUT', '/api/admin/adminRole/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516234', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '', '', '/api/admin/adminUser', 'base--用户管理', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516235', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '528072364110516234', 'POST', '/api/admin/adminUser/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516236', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '528072364110516234', 'POST', '/api/admin/adminUser/bindWeChatMq', '微信公众号openId绑定', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516237', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '528072364110516234', 'GET', '/api/admin/adminUser/findByRoleId', '获取指定角色的用户列表', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516238', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '528072364110516234', 'PUT', '/api/admin/adminUser/updResetPassword', '重置任意用户密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516239', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:53', 0, 672, '528072364110516234', 'POST', '/api/admin/adminUser/login', '登录', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516240', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '528072364110516234', 'PUT', '/api/admin/adminUser/updByPassword', '当前登录用户密码修改', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364110516241', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '528072364110516234', 'GET', '/api/admin/adminUser/findUser', '个人信息', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710528', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '528072364110516234', 'GET', '/api/admin/adminUser/findId', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710529', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '528072364110516234', 'GET', '/api/admin/adminUser/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710530', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '528072364110516234', 'DELETE', '/api/admin/adminUser/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710532', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '528072364110516234', 'PUT', '/api/admin/adminUser/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710533', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '', '', '/api/admin/adminDatasource', 'base-gc--代码生成--数据源维护', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710534', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '528072364114710533', 'POST', '/api/admin/adminDatasource/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710535', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:54', 0, 672, '528072364114710533', 'PUT', '/api/admin/adminDatasource/updPwd', '修改/重置密码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710536', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 672, '528072364114710533', 'POST', '/api/admin/adminDatasource/dataSourceTest', '数据源连接测试', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710537', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 672, '528072364114710533', 'GET', '/api/admin/adminDatasource/findId', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710538', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 672, '528072364114710533', 'GET', '/api/admin/adminDatasource/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710539', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 672, '528072364114710533', 'GET', '/api/admin/adminDatasource/findList', '列表查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710540', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 672, '528072364114710533', 'DELETE', '/api/admin/adminDatasource/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710541', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 672, '528072364114710533', 'DELETE', '/api/admin/adminDatasource/delByIds', '批量ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('528072364114710542', NULL, NULL, '2021-03-06 14:57:53', '2021-05-08 17:22:55', 0, 672, '528072364114710533', 'PUT', '/api/admin/adminDatasource/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851074789376', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:55', 0, 538, '', '', '/api/client/xj/adminBanner', 'yh--base-plus--banner', 0, 1, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851074789377', NULL, NULL, '2021-03-22 09:09:16', '2021-06-02 18:00:15', 0, 538, '533782851074789376', 'GET', '/api/client/xj/adminBanner/findByPosition', '位置查询', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('533782851074789378', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 538, '', '', '/api/client/xj/adminConfig', 'yh--base-plus--全局配置', 0, 1, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851074789379', NULL, NULL, '2021-03-22 09:09:16', '2021-06-02 18:00:14', 0, 538, '533782851074789378', 'GET', '/api/client/xj/adminConfig/findByCode', 'CODE查询', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('533782851083177984', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 538, '528072364102127627', 'GET', '/api/admin/adminDictionary/findDictCategory', '查询字典类别级联数据', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851095760896', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 538, '', '', '/api/admin/dataBase', 'base-gc--代码生成--查询表数据', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851095760897', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 538, '533782851095760896', 'GET', '/api/admin/dataBase/findTableField', '查询指定表下使用字段内容', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851095760898', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 538, '533782851095760896', 'GET', '/api/admin/dataBase/findTable', '查询所有表名', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851095760899', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 538, '', '', '/api/admin/generate', 'base-gc--代码生成', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851099955200', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 538, '533782851095760899', 'GET', '/api/admin/generate/getPath', '代码生成路径', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851099955201', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:56', 0, 538, '533782851095760899', 'POST', '/api/admin/generate/preview', '生成预览代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('533782851099955202', NULL, NULL, '2021-03-22 09:09:16', '2021-05-08 17:22:57', 0, 538, '533782851095760899', 'POST', '/api/admin/generate/generateCode', '生成代码', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203833249794', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:57', 0, 506, '', '', '/api/client/dictionary', 'yh--base--字典管理', 0, 1, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203833249795', NULL, NULL, '2021-03-31 13:38:57', '2021-06-02 18:00:13', 0, 506, '537112203833249794', 'GET', '/api/client/dictionary/findCodeGroup', '查询所有-code分组', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('537112203837444096', NULL, NULL, '2021-03-31 13:38:57', '2021-06-02 18:00:12', 0, 506, '537112203833249794', 'GET', '/api/client/dictionary/findByCode', 'Code查询(Tree)', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('537112203837444097', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:57', 0, 506, '', '', '/api/client/xj/adminMsg', 'yh--base-plus--消息通知', 0, 1, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203837444098', NULL, NULL, '2021-03-31 13:38:57', '2021-06-02 18:00:12', 0, 506, '537112203837444097', 'PUT', '/api/client/xj/adminMsg/read', '消息修改为已读', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('537112203837444099', NULL, NULL, '2021-03-31 13:38:57', '2021-06-02 18:00:11', 0, 506, '537112203837444097', 'GET', '/api/client/xj/adminMsg/findUnreadNum', '查询未读数量', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('537112203837444100', NULL, NULL, '2021-03-31 13:38:57', '2021-06-02 18:00:11', 0, 506, '537112203837444097', 'GET', '/api/client/xj/adminMsg/findPage', '分页查询', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('537112203862609920', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '', '', '/api/admin/xj/adminBanner', 'base-plus--banner', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203866804224', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '537112203862609920', 'POST', '/api/admin/xj/adminBanner/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203866804225', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '537112203862609920', 'GET', '/api/admin/xj/adminBanner/findId', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203866804226', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '537112203862609920', 'GET', '/api/admin/xj/adminBanner/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203866804227', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '537112203862609920', 'PUT', '/api/admin/xj/adminBanner/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203866804228', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '537112203862609920', 'DELETE', '/api/admin/xj/adminBanner/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203866804229', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '537112203862609920', 'DELETE', '/api/admin/xj/adminBanner/delByIds', '批量ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203866804230', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '', '', '/api/admin/xj/adminBlacklist', 'base-plus--黑名单', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998528', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '537112203866804230', 'POST', '/api/admin/xj/adminBlacklist/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998529', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:58', 0, 506, '537112203866804230', 'GET', '/api/admin/xj/adminBlacklist/findId', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998530', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203866804230', 'GET', '/api/admin/xj/adminBlacklist/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998531', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203866804230', 'PUT', '/api/admin/xj/adminBlacklist/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998532', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203866804230', 'DELETE', '/api/admin/xj/adminBlacklist/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998533', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203866804230', 'PUT', '/api/admin/xj/adminBlacklist/updDisable', '禁用/启用', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998534', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '', '', '/api/admin/xj/adminConfig', 'base-plus--全局配置', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998535', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203870998534', 'POST', '/api/admin/xj/adminConfig/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998536', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203870998534', 'GET', '/api/admin/xj/adminConfig/findId', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998537', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203870998534', 'GET', '/api/admin/xj/adminConfig/findByCode', 'CODE查询', 0, 0, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998538', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203870998534', 'GET', '/api/admin/xj/adminConfig/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998539', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:22:59', 0, 506, '537112203870998534', 'PUT', '/api/admin/xj/adminConfig/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203870998540', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:00', 0, 506, '537112203870998534', 'DELETE', '/api/admin/xj/adminConfig/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203875192840', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '', '', '/api/admin/xj/adminLog', 'base-plus--操作记录', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203875192841', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '537112203875192840', 'POST', '/api/admin/xj/adminLog/insert', '添加', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203875192842', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '537112203875192840', 'GET', '/api/admin/xj/adminLog/findId', 'ID查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203875192843', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '537112203875192840', 'GET', '/api/admin/xj/adminLog/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203875192844', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '537112203875192840', 'PUT', '/api/admin/xj/adminLog/upd', 'ID编辑', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203875192845', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '537112203875192840', 'DELETE', '/api/admin/xj/adminLog/del', 'ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203879387136', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '537112203875192840', 'DELETE', '/api/admin/xj/adminLog/delByIds', '批量ID删除', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203879387137', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '', '', '/api/admin/xj/adminMsg', 'base-plus--消息通知', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203879387138', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:01', 0, 506, '537112203879387137', 'PUT', '/api/admin/xj/adminMsg/read', '消息修改为已读', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203879387139', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:02', 0, 506, '537112203879387137', 'GET', '/api/admin/xj/adminMsg/findUnreadNum', '查询未读数量', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('537112203879387140', NULL, NULL, '2021-03-31 13:38:57', '2021-05-08 17:23:02', 0, 506, '537112203879387137', 'GET', '/api/admin/xj/adminMsg/findPage', '分页查询', 0, 0, 2, 1);
INSERT INTO `t_admin_authority` VALUES ('543635757584224257', NULL, NULL, '2021-04-18 13:41:15', '2021-05-08 17:23:03', 0, 311, '', '', '/api/open/websocket', 'Websocket  -->  消息通知/即时通讯', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('543635757584224258', NULL, NULL, '2021-04-18 13:41:15', '2021-05-08 17:23:03', 0, 311, '543635757584224257', 'POST', '/api/open/websocket/send', '发送消息', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('543635757584224259', NULL, NULL, '2021-04-18 13:41:15', '2021-05-08 17:23:03', 0, 311, '543635757584224257', 'GET', '/api/open/websocket/getOnlineCount', '获取在线人数', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('543635757584224260', NULL, NULL, '2021-04-18 13:41:15', '2021-05-08 17:23:03', 0, 311, '543635757584224257', 'GET', '/api/open/websocket/getOnlineUsersList', '获取当前在线用户列表', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('543635757584224261', NULL, NULL, '2021-04-18 13:41:15', '2021-05-08 17:23:03', 0, 311, '543635757584224257', 'GET', '/api/open/websocket/getPath', '游客登录获取websocket连接地址', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('545026427607715840', NULL, NULL, '2021-04-22 09:46:29', '2021-05-08 17:23:03', 0, 303, '', '', '/api/open/aliOssFile', 'AliYun --> OSS文件管理', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('545026427607715841', NULL, NULL, '2021-04-22 09:46:29', '2021-05-08 17:23:04', 0, 303, '545026427607715840', 'DELETE', '/api/open/aliOssFile/del', 'OSS-文件删除', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('545026427607715842', NULL, NULL, '2021-04-22 09:46:29', '2021-05-08 17:23:04', 0, 303, '545026427607715840', 'POST', '/api/open/aliOssFile/upload', 'OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('545026427607715843', NULL, NULL, '2021-04-22 09:46:29', '2021-05-08 17:23:04', 0, 303, '545026427607715840', 'GET', '/api/open/aliOssFile/fileList', 'OSS-文件Object列表', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('545026427607715844', NULL, NULL, '2021-04-22 09:46:29', '2021-05-08 17:23:04', 0, 303, '545026427607715840', 'GET', '/api/open/aliOssFile/downloadZip', 'OSS-文件下载--多文件下载', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('545026427607715845', NULL, NULL, '2021-04-22 09:46:29', '2021-05-08 17:23:04', 0, 303, '545026427607715840', 'GET', '/api/open/aliOssFile/download', 'OSS-文件下载--单文件下载', 0, 2, 0, 1);
INSERT INTO `t_admin_authority` VALUES ('560004623600062464', NULL, NULL, '2021-06-02 17:45:16', '2021-06-02 17:45:16', 0, 189, '', '', '/api/admin/xj/jvm/', 'base-plus--jvm信息获取', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('560004623600062465', NULL, NULL, '2021-06-02 17:45:16', '2021-06-02 17:45:16', 0, 189, '560004623600062464', 'GET', '/api/admin/xj/jvm//jvmInfo', '3、系统的jvm信息', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('561176414431350784', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 175, '', '', '/api/open/qq', 'QQ  -->  QQ互联', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('561176414431350785', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 175, '561176414431350784', 'GET', '/api/open/qq/login', 'qq登录,通过code', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('561176414431350786', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 175, '561176414431350784', 'GET', '/api/open/qq/getQQLoginUrl', '获取qq登录链接', 0, 2, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('564274372513239040', NULL, NULL, '2021-06-14 12:31:42', '2021-06-14 12:31:42', 0, 126, '528072364110516234', 'GET', '/api/admin/adminUser/findList', '查询所有(只返回姓名/昵称/电话/id)', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('564274372525821952', NULL, NULL, '2021-06-14 12:31:42', '2021-06-14 12:31:42', 0, 126, '537112203879387137', 'POST', '/api/admin/xj/adminMsg/insert', '发送消息/添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461448904704', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/client/ser/file', '常用工具/文件', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461448904705', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 15:16:58', 0, 44, '586329461448904704', 'GET', '/api/client/ser/file/findList', '列表查询', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099008', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/client/ser/serFun', '功能表', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099009', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 15:16:46', 0, 44, '586329461453099008', 'GET', '/api/client/ser/serFun/findTree', '查询所有,根据pid分组', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099010', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 15:17:05', 0, 44, '586329461453099008', 'PUT', '/api/client/ser/serFun/updVisitNum', '访问量+1', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099011', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/client/ser/help', '兮家手册-帮助中心', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099012', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 11:27:57', 0, 44, '586329461453099011', 'GET', '/api/client/ser/help/findTree', '查看 -->  左侧菜单-- 帮助中心tree菜单', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099013', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 11:27:57', 0, 44, '586329461453099011', 'GET', '/api/client/ser/help/findById', '查看 -->  ID查询 -- 查看详情调用此方法获取数据,浏览量自动+1', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099014', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/client/ser/tool', '系统小工具/小功能相关接口', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099015', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 15:17:00', 0, 44, '586329461453099014', 'GET', '/api/client/ser/tool/cron', '3、解析cron最近10次运行', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099016', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 15:16:59', 0, 44, '586329461453099014', 'GET', '/api/client/ser/tool/fhConvert', '1、符号转文字工具', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099017', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 15:16:59', 0, 44, '586329461453099014', 'POST', '/api/client/ser/tool/javaCodeRun', '2、java代码运行器', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461453099018', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/client/ser/serUser', '用户', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293312', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 15:16:51', 0, 44, '586329461453099018', 'POST', '/api/client/ser/serUser/qqLogin', 'qq互联登录', 0, 1, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293313', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 15:16:53', 0, 44, '586329461453099018', 'GET', '/api/client/ser/serUser/findUser', '查询个人信息', 0, 1, 1, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293314', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/admin/ser/file', '常用工具/文件', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293315', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461457293314', 'POST', '/api/admin/ser/file/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293316', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461457293314', 'PUT', '/api/admin/ser/file/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293317', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461457293314', 'GET', '/api/admin/ser/file/findId', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293318', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461457293314', 'DELETE', '/api/admin/ser/file/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293319', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461457293314', 'GET', '/api/admin/ser/file/findPage', '分页查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461457293320', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461457293314', 'DELETE', '/api/admin/ser/file/delByIds', '批量ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487616', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/admin/ser/serFun', '功能表', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487617', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487616', 'POST', '/api/admin/ser/serFun/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487618', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487616', 'GET', '/api/admin/ser/serFun/findList', '查询所有', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487619', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487616', 'PUT', '/api/admin/ser/serFun/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487620', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487616', 'DELETE', '/api/admin/ser/serFun/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487621', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487616', 'PUT', '/api/admin/ser/serFun/updPid', '变更父级', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487622', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/admin/ser/help', 'base-plus--帮助中心', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487623', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487622', 'POST', '/api/admin/ser/help/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487624', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487622', 'PUT', '/api/admin/ser/help/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487625', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487622', 'GET', '/api/admin/ser/help/findId', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487626', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487622', 'DELETE', '/api/admin/ser/help/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487627', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487622', 'GET', '/api/admin/ser/help/findPage', '分页查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461461487628', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '', '', '/api/admin/ser/serUser', '用户', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461465681920', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487628', 'POST', '/api/admin/ser/serUser/insert', '添加', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461465681921', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487628', 'PUT', '/api/admin/ser/serUser/upd', 'ID编辑', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461465681922', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487628', 'GET', '/api/admin/ser/serUser/findId', 'ID查询', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461465681923', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487628', 'DELETE', '/api/admin/ser/serUser/del', 'ID删除', 0, 0, 2, 0);
INSERT INTO `t_admin_authority` VALUES ('586329461465681924', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 44, '586329461461487628', 'GET', '/api/admin/ser/serUser/findPage', '分页查询', 0, 0, 2, 0);

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
INSERT INTO `t_admin_dictionary` VALUES ('1290687277911076865', NULL, NULL, '2020-08-05 00:33:21', '2021-08-17 14:08:27', 0, 0, 'MENU_ROOT', '菜单级别', '1290686507555844098', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687351005212673', NULL, NULL, '2020-08-05 00:33:38', '2020-08-07 16:47:57', 0, 0, '1', '顶部菜单', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687461252493314', NULL, NULL, '2020-08-05 00:34:05', '2020-08-07 16:47:57', 0, 0, '2', '菜单', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687547940368386', NULL, NULL, '2020-08-05 00:34:25', '2020-08-07 16:47:57', 0, 0, '3', '页面', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688121255587841', NULL, NULL, '2020-08-05 00:36:42', '2020-08-09 17:39:03', 0, 0, 'BASE', '通用枚举', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688660164931586', NULL, NULL, '2020-08-05 00:38:51', '2021-08-17 14:10:04', 0, 0, 'GENDER', '性别', '1290688121255587841', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688703043301378', NULL, NULL, '2020-08-05 00:39:01', '2020-08-07 16:47:58', 0, 0, '1', '男', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688742289403906', NULL, NULL, '2020-08-05 00:39:10', '2021-03-10 17:37:16', 0, 0, '2', '女', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1291341478399897601', NULL, NULL, '2020-08-06 11:52:54', '2021-02-05 14:46:54', 0, 0, '0', '未知', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469448399593474', NULL, NULL, '2020-08-20 15:29:38', '2021-08-17 14:09:58', 0, 0, 'DISABLE', '是否禁用', '1290688121255587841', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469518025039873', NULL, NULL, '2020-08-20 15:29:55', '2020-08-20 15:29:55', 0, 0, '1', '禁用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469564455985154', NULL, NULL, '2020-08-20 15:30:06', '2020-08-20 15:30:06', 0, 0, '0', '启用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995475064434690', NULL, NULL, '2020-08-22 02:19:52', '2021-08-17 14:08:21', 0, 0, 'AUTHORITY_TYPE', '权限类型', '1290686507555844098', '【固定值】：用于新接口自动生成【权限状态】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995560007479298', NULL, NULL, '2020-08-22 02:20:12', '2021-03-16 10:00:15', 0, 0, '0', '管理端接口', '1296995475064434690', '管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995610632728578', NULL, NULL, '2020-08-22 02:20:24', '2021-03-16 10:00:26', 0, 0, '1', '用户端接口', '1296995475064434690', '用户端, 类上标有该参数所有接口都会默认被列为-[需登录]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995742778470401', NULL, NULL, '2020-08-22 02:20:55', '2021-08-17 14:08:13', 0, 0, 'AUTHORITY_STATE', '权限状态', '1290686507555844098', '【固定值】：用于编辑指定接口的【权限状态】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995839977271297', NULL, NULL, '2020-08-22 02:21:19', '2021-03-15 20:35:49', 0, 0, '0', '无', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996062090833922', NULL, NULL, '2020-08-22 02:22:12', '2020-08-22 02:22:31', 0, 0, '1', '需登录', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996224863383554', NULL, NULL, '2020-08-22 02:22:50', '2020-08-22 02:22:50', 0, 0, '2', '需登录+授权', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1297705363906273282', NULL, NULL, '2020-08-24 01:20:43', '2021-01-23 11:50:33', 0, 0, '2', '通用接口', '1296995475064434690', '通用接口, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191840981327873', NULL, NULL, '2020-08-25 09:33:48', '2021-08-17 14:09:52', 0, 0, 'DELETED', '逻辑删除', '1290688121255587841', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191898313269249', NULL, NULL, '2020-08-25 09:34:02', '2020-08-25 09:34:02', 0, 0, '0', '正常', '1298191840981327873', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298191981998022658', NULL, NULL, '2020-08-25 09:34:22', '2020-08-25 09:34:22', 0, 0, '1', '已删除', '1298191840981327873', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194668697198594', NULL, NULL, '2020-08-25 09:45:02', '2021-08-17 14:16:39', 0, 0, 'BANNER_IS_SKIP', 'banner是否跳转', '1337244241461600257', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194722350735361', NULL, NULL, '2020-08-25 09:45:15', '2020-09-24 21:40:23', 0, 0, '0', '否', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194801014906881', NULL, NULL, '2020-08-25 09:45:34', '2020-08-25 09:45:34', 0, 0, '1', '内部跳转', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1298194850285395969', NULL, NULL, '2020-08-25 09:45:46', '2020-08-25 09:45:46', 0, 0, '2', '外部跳转', '1298194668697198594', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1303872194494435330', NULL, NULL, '2020-09-10 09:45:30', '2021-08-17 14:16:05', 0, 0, 'BANNER_POSITION', 'banner 位置', '1337244241461600257', '【动态值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1303872308608864257', NULL, NULL, '2020-09-10 09:45:57', '2021-08-17 14:19:47', 0, 0, '1', '用户端首页', '1303872194494435330', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580167513370625', NULL, NULL, '2020-09-23 09:33:17', '2021-08-17 14:16:29', 0, 0, 'MSG_USER_TYPE', '及时消息终端', '1337244241461600257', '【动态值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580236161544193', NULL, NULL, '2020-09-23 09:33:33', '2021-03-16 10:07:52', 0, 0, '1', '用户端', '1308580167513370625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308580275248263169', NULL, NULL, '2020-09-23 09:33:42', '2021-03-16 10:41:35', 0, 0, '2', '管理端', '1308580167513370625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585499920769025', NULL, NULL, '2020-09-23 09:54:28', '2021-08-17 14:16:21', 0, 0, 'MSG_TYPE', '及时消息类型', '1337244241461600257', '【动态值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585615968772098', NULL, NULL, '2020-09-23 09:54:56', '2020-09-23 09:54:56', 0, 0, '1', '系统通知', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1308585669429370882', NULL, NULL, '2020-09-23 09:55:09', '2020-09-23 09:55:09', 0, 0, '2', '订单业务通知', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318470835622768641', NULL, NULL, '2020-10-20 08:35:17', '2021-08-17 14:14:54', 0, 0, 'HELP_CATEGORY', '帮助中心类别', '1393925384986198018', '【动态值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471097611579394', NULL, NULL, '2020-10-20 08:36:20', '2021-08-17 14:14:36', 0, 0, 'HELP_VERSION', '帮助中心版本', '1393925384986198018', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471212665532417', NULL, NULL, '2020-10-20 08:36:47', '2021-08-17 17:42:36', 0, 0, '1', '采用技术', '1318470835622768641', '-', 3, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1318471270811168770', NULL, NULL, '2020-10-20 08:37:01', '2021-08-17 17:42:38', 0, 0, '3', '引入技术', '1318470835622768641', '-', 5, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471304038445058', NULL, NULL, '2020-10-20 08:37:09', '2021-08-17 17:42:37', 0, 0, '2', '系统功能', '1318470835622768641', '-', 4, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471387207299073', NULL, NULL, '2020-10-20 08:37:29', '2021-08-17 17:42:42', 0, 0, '6', '其他', '1318470835622768641', '-', 8, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471451912826882', NULL, NULL, '2020-10-20 08:37:44', '2021-08-17 17:42:25', 0, 0, '0', '开始使用', '1318470835622768641', '-', 1, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318471572557787138', NULL, NULL, '2020-10-20 08:38:13', '2021-08-16 11:48:29', 0, 0, '0', '0.1.1 - ', '1318471097611579394', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318533693752475649', NULL, NULL, '2020-10-20 12:45:03', '2021-08-16 11:48:16', 0, 0, '1', '0.1.2 +', '1318471097611579394', '-', 1, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1318533820672114690', NULL, NULL, '2020-10-20 12:45:33', '2021-08-16 11:47:51', 0, 0, '2', '1.5x ', '1318471097611579394', '-', 2, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1318533884811411458', NULL, NULL, '2020-10-20 12:45:48', '2021-08-16 11:47:50', 0, 0, '3', '2.x', '1318471097611579394', '-', 3, 1);
INSERT INTO `t_admin_dictionary` VALUES ('1320196942961070081', NULL, NULL, '2020-10-25 02:54:13', '2021-08-17 17:42:39', 0, 0, '4', '运维部署', '1318470835622768641', '-', 6, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1320202350207533058', NULL, NULL, '2020-10-25 03:15:43', '2021-08-17 17:42:40', 0, 0, '5', '常用技术文章', '1318470835622768641', '-', 7, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332329973427494913', NULL, NULL, '2020-11-27 22:26:31', '2021-08-17 14:16:12', 0, 0, 'BLACKLIST_TYPE', '黑/白名单类型', '1337244241461600257', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332330105569042434', NULL, NULL, '2020-11-27 22:27:02', '2020-11-27 22:27:02', 0, 0, '1', '白名单', '1332329973427494913', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1332330148963311619', NULL, NULL, '2020-11-27 22:27:13', '2020-11-27 22:27:13', 0, 0, '2', '黑名单', '1332329973427494913', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244241461600257', NULL, NULL, '2020-12-11 11:54:06', '2021-01-23 14:08:26', 0, 0, 'XJ', '系统增强功能枚举', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1337244337121091586', NULL, NULL, '2020-12-11 11:54:29', '2021-08-17 14:15:28', 0, 0, 'FILE_TYPE', '文件类型', '1393925384986198018', '【动态值】', 0, 0);
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
INSERT INTO `t_admin_dictionary` VALUES ('1352856400451117058', NULL, NULL, '2021-01-23 13:51:15', '2021-08-17 14:10:32', 0, 0, 'PAY_TYPE', '支付类型', '1352856255286255617', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856492264431617', NULL, NULL, '2021-01-23 13:51:37', '2021-01-23 13:51:37', 0, 0, '1', '支付', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856528012484610', NULL, NULL, '2021-01-23 13:51:45', '2021-01-23 13:51:52', 0, 0, '2', '充值', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856603073748994', NULL, NULL, '2021-01-23 13:52:03', '2021-01-23 13:52:03', 0, 0, '3', '退款', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856663249428482', NULL, NULL, '2021-01-23 13:52:18', '2021-01-23 13:52:18', 0, 0, '4', '商家打款', '1352856400451117058', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856892170346498', NULL, NULL, '2021-01-23 13:53:12', '2021-08-17 14:11:33', 0, 0, 'PAY_CHANNEL', '支付渠道', '1352856255286255617', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856932938981378', NULL, NULL, '2021-01-23 13:53:22', '2021-01-23 13:53:22', 0, 0, '1', '支付宝', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352856979743219713', NULL, NULL, '2021-01-23 13:53:33', '2021-01-23 13:53:33', 0, 0, '2', '微信', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857025708597250', NULL, NULL, '2021-01-23 13:53:44', '2021-01-23 13:53:44', 0, 0, '3', '银联', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857215228223489', NULL, NULL, '2021-01-23 13:54:29', '2021-08-17 14:11:18', 0, 0, 'PAY_STATE', '支付状态', '1352856255286255617', '【固定值】用于记录支付交易请求状态', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857264104448001', NULL, NULL, '2021-01-23 13:54:41', '2021-01-23 13:54:41', 0, 0, '0', '已发起', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857305888104450', NULL, NULL, '2021-01-23 13:54:51', '2021-01-23 13:54:51', 0, 0, '1', '回调成功', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857349592752130', NULL, NULL, '2021-01-23 13:55:01', '2021-01-23 13:55:01', 0, 0, '2', '交易失败', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857389069541377', NULL, NULL, '2021-01-23 13:55:11', '2021-01-23 13:55:11', 0, 0, '3', '交易成功', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857426407235585', NULL, NULL, '2021-01-23 13:55:20', '2021-01-23 13:55:20', 0, 0, '4', '订单异常', '1352857215228223489', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857793505304577', NULL, NULL, '2021-01-23 13:56:47', '2021-08-17 14:12:25', 0, 0, 'PAY_BUSINESS', '支付业务', '1352856255286255617', '【动态值】当前支付业务', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352857906709569537', NULL, NULL, '2021-01-23 13:57:14', '2021-01-23 13:57:14', 0, 0, '1', '用户下单', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1352858096959004674', NULL, NULL, '2021-01-23 13:57:59', '2021-08-17 14:13:44', 0, 0, '2', 'vip 充值/续费', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528050694148097', NULL, NULL, '2021-02-05 11:14:43', '2021-08-17 14:10:23', 0, 0, 'WALLET_TYPE', '流水类型', '1352856255286255617', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528121372364801', NULL, NULL, '2021-02-05 11:15:00', '2021-02-05 11:15:00', 0, 0, '1', '收入', '1357528050694148097', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357528154167627779', NULL, NULL, '2021-02-05 11:15:07', '2021-02-05 11:15:07', 0, 0, '2', '支出', '1357528050694148097', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612114939858945', NULL, NULL, '2021-02-05 16:48:45', '2021-08-17 14:10:09', 0, 0, 'IS_READ', '是否已读', '1290688121255587841', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612150536916994', NULL, NULL, '2021-02-05 16:48:54', '2021-02-05 16:48:54', 0, 0, '0', '未读', '1357612114939858945', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1357612182854029315', NULL, NULL, '2021-02-05 16:49:01', '2021-02-05 16:49:01', 0, 0, '1', '已读', '1357612114939858945', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1368739295631798273', NULL, NULL, '2021-03-08 09:44:11', '2021-08-17 14:08:35', 0, 0, 'POSITION', '部门职位', '1290686507555844098', '【动态值】, 如有需要根据业务指定', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1368739394596401154', NULL, NULL, '2021-03-08 09:44:35', '2021-04-21 10:42:03', 0, 0, '0', '系统管理员', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1384697257961463810', NULL, NULL, '2021-04-21 10:35:27', '2021-08-17 14:03:55', 0, 0, '1', '平台人员', '1368739295631798273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925384986198018', NULL, NULL, '2021-05-16 21:44:44', '2021-05-16 21:45:03', 0, 0, 'SER', '兮家用户服务', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925564183642113', NULL, NULL, '2021-05-16 21:45:26', '2021-08-17 14:14:27', 0, 0, 'IS_VIP', '是否需要vip', '1393925384986198018', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925637302943746', NULL, NULL, '2021-05-16 21:45:44', '2021-05-16 21:45:44', 0, 0, '0', '不需要', '1393925564183642113', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925670421168129', NULL, NULL, '2021-05-16 21:45:52', '2021-05-16 21:45:52', 0, 0, '1', '需要', '1393925564183642113', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925841175478273', NULL, NULL, '2021-05-16 21:46:32', '2021-08-17 14:14:14', 0, 0, 'SKIP_TYPE', '跳转类型', '1393925384986198018', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925885773512706', NULL, NULL, '2021-05-16 21:46:43', '2021-05-16 21:46:43', 0, 0, '0', '内部跳转', '1393925841175478273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393925924549853186', NULL, NULL, '2021-05-16 21:46:52', '2021-05-16 21:46:52', 0, 0, '1', '外部跳转', '1393925841175478273', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393926103206232065', NULL, NULL, '2021-05-16 21:47:35', '2021-08-17 14:15:12', 0, 0, 'FUN_STATE', '功能状态', '1393925384986198018', '【固定值】', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393926364360376322', NULL, NULL, '2021-05-16 21:48:37', '2021-05-16 21:48:37', 0, 0, '0', '开发中', '1393926103206232065', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1393926404223041538', NULL, NULL, '2021-05-16 21:48:47', '2021-05-20 00:24:15', 0, 0, '1', '可使用', '1393926103206232065', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968409441050625', NULL, NULL, '2021-06-02 13:57:32', '2021-08-17 14:09:37', 0, 0, 'DEFAULT', '默认字典(代码生成默认字典)', '1290688121255587841', '【固定值】用于代码生成默认使用的code值', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968449656037377', NULL, NULL, '2021-06-02 13:57:42', '2021-06-02 13:57:42', 0, 0, '1', '默认值 1', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968504043577346', NULL, NULL, '2021-06-02 13:57:55', '2021-06-02 13:57:55', 0, 0, '2', '默认值 2', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1399968544350838786', NULL, NULL, '2021-06-02 13:58:04', '2021-06-02 13:58:04', 0, 0, '3', '默认值 3', '1399968409441050625', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1404005220177985537', NULL, NULL, '2021-06-13 17:18:21', '2021-06-14 10:12:10', 0, 0, '0', '测试', '1308585499920769025', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513445955194882', NULL, NULL, '2021-08-17 14:11:42', '2021-08-17 14:11:42', 0, 0, '4', '其他', '1352856892170346498', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513925234118658', NULL, NULL, '2021-08-17 14:13:36', '2021-08-17 14:13:36', 0, 0, '3', '月卡购买', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427513998571524097', NULL, NULL, '2021-08-17 14:13:53', '2021-08-17 14:13:53', 0, 0, '4', '其他', '1352857793505304577', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427515237539565569', NULL, NULL, '2021-08-17 14:18:49', '2021-08-17 14:18:49', 0, 0, '8', '其他', '1337244337121091586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1427566457625088001', NULL, NULL, '2021-08-17 17:42:21', '2021-08-17 17:42:44', 0, 0, '7', '项目/代码规范', '1318470835622768641', '-', 2, 0);

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
INSERT INTO `t_admin_menu` VALUES ('1297047088646905857', NULL, NULL, '2020-08-22 05:44:58', '2021-08-12 16:14:33', 0, 0, '4', '接口管理', NULL, '/page/manage_admin_authority_authority', 'layui-icon-file-b', 10005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1297533242571763714', NULL, NULL, '2020-08-23 13:56:45', '2021-08-12 16:14:46', 0, 0, '1350297066072498179', 'banner 管理', NULL, '/page/manage_xj_banner_banner', 'layui-icon-file-b', 10008, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1311827586636156929', NULL, NULL, '2020-10-02 08:37:23', '2021-08-12 16:14:48', 0, 0, '1350297066072498179', '全局配置', NULL, '/page/manage_xj_config_config', 'layui-icon-file-b', 10009, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1318472952462770177', NULL, NULL, '2020-10-20 08:43:41', '2021-08-14 15:11:01', 0, 0, '1393917150250442754', '兮家手册', '', '/page/manage_ser_help_help', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1319948617724743682', NULL, NULL, '2020-10-24 10:27:28', '2021-06-02 18:03:27', 0, 0, '1350298064077774850', '数据监控', '', 'http://xijia.plus/druid', 'layui-icon-file-b', 70002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1321432835319414785', NULL, NULL, '2020-10-28 12:45:12', '2021-08-12 16:14:51', 0, 0, '1350297066072498179', '系统日志', NULL, '/page/manage_xj_log_log', 'layui-icon-file-b', 10012, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584197721440258', NULL, NULL, '2020-11-03 19:13:58', '2021-06-01 15:38:50', 0, 0, '0', '兮家-server', NULL, '', 'layui-icon-file-b', 3, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1323584939857395714', NULL, NULL, '2020-11-03 19:16:55', '2021-05-11 18:54:57', 0, 0, '1350298064077774850', 'swagger-ui', NULL, '/swagger-ui.html', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1332332514865364994', NULL, NULL, '2020-11-27 22:36:37', '2021-08-13 09:23:31', 0, 0, '1350297066072498179', '黑/白名单', '', '/page/manage_xj_blacklist_blacklist', 'layui-icon-file-b', 10011, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1339486194576244738', NULL, NULL, '2020-12-17 16:22:49', '2021-08-14 15:11:04', 0, 0, '1393917150250442754', '文件管理', '', '/page/manage_ser_xjFile_xjFile', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1350297066072498179', NULL, NULL, '2021-01-16 12:21:22', '2021-06-01 15:39:14', 0, 0, '1323584197721440258', '系统增强功能', '', '', 'layui-icon-file-b', 101, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1350298064077774850', NULL, NULL, '2021-01-16 12:25:20', '2021-06-01 15:39:10', 0, 0, '1323584197721440258', '系统功能', '', '-', 'layui-icon-file-b', 103, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1369610726125096962', NULL, NULL, '2021-03-10 19:26:56', '2021-08-12 16:14:53', 0, 0, '1350297066072498179', '消息管理', '', '/page/manage_xj_msg_msg', 'layui-icon-file-b', 10013, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1393917029781643265', NULL, NULL, '2021-05-16 21:11:32', '2021-05-16 21:11:32', 0, 0, '0', '兮家-用户功能', '', '', 'layui-icon-file-b', 0, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('1393917150250442754', NULL, NULL, '2021-05-16 21:12:00', '2021-06-01 15:18:17', 0, 0, '1393917029781643265', '功能管理', '', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1393917211910905857', NULL, NULL, '2021-05-16 21:12:15', '2021-08-14 15:11:06', 0, 0, '1393917150250442754', '用户菜单', '', '/page/manage_ser_serFun_serFun', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1400035312876515330', NULL, NULL, '2021-06-02 18:23:24', '2021-06-02 18:23:24', 0, 0, '1323584197721440258', '代码生成', '', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1401357604772470785', NULL, NULL, '2021-06-06 09:57:42', '2021-06-06 09:57:42', 0, 0, '1393917029781643265', '用户管理', '', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1401357646077976578', NULL, NULL, '2021-06-06 09:57:52', '2021-08-14 15:11:07', 0, 0, '1401357604772470785', '用户列表', '', '/page/manage_ser_serUser_serUser', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('21', NULL, NULL, '2020-07-25 09:29:38', '2021-08-12 16:14:27', 0, 0, '4', '系统用户', NULL, '/page/manage_admin_user_user', '', 10002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('22', NULL, NULL, '2020-07-25 09:29:38', '2021-08-12 16:14:29', 0, 0, '4', '角色管理', NULL, '/page/manage_admin_role_role', '', 10003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('23', NULL, NULL, '2020-07-25 09:29:38', '2021-08-12 16:14:36', 0, 0, '4', '角色菜单权限', NULL, '/page/manage_admin_role_roleMenuAuth', '', 10006, 3, 1);
INSERT INTO `t_admin_menu` VALUES ('24', NULL, NULL, '2020-07-25 09:29:38', '2021-08-12 16:14:37', 0, 0, '4', '角色URL权限', NULL, '/page/manage_admin_role_roleUrlAuth', '', 10007, 3, 1);
INSERT INTO `t_admin_menu` VALUES ('25', NULL, NULL, '2020-07-25 09:29:38', '2021-08-12 16:14:31', 0, 0, '4', '字典管理', NULL, '/page/manage_admin_dictionary_dictionary', '', 10004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('30', NULL, NULL, '2020-07-25 09:29:38', '2021-08-12 16:14:43', 0, 0, '1400035312876515330', '数据表', '', '/page/manage_gc_dataBase_dataBase', '1', 40006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('4', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '系统管理', NULL, '', 'layui-icon-set', 100, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('7', NULL, NULL, '2020-07-25 09:29:38', '2021-08-12 16:14:25', 0, 0, '4', '菜单管理', '', '/page/manage_admin_menu_menu', 'layui-icon-home', 10001, 3, 0);

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
INSERT INTO `t_admin_role` VALUES ('1384407338139525121', NULL, NULL, '2021-04-20 15:23:25', '2021-05-09 11:04:42', 0, 0, '体验成员', '体验成员', 'TEST', 0);

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
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770882', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364102127621', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770883', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364102127622', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770884', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364102127623', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770885', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364102127624', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770886', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364102127625', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770887', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364102127626', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770888', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364102127627', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770889', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770890', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770891', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321923', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770892', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321924', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770893', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321925', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770894', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321926', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770895', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321927', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770896', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321928', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770897', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321929', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770898', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321930', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770899', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321931', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770900', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321932', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770901', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321933', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770902', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321934', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770903', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321935', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770904', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364106321936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770905', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516224', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770906', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516225', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770907', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516226', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770908', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516227', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770909', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516228', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770910', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516229', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770911', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516230', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770912', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516231', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770913', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516232', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770914', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516233', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770915', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516234', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770916', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516235', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770917', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516236', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770918', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516237', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770919', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516238', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770920', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516239', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770921', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516240', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770922', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364110516241', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770923', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710528', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770924', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710529', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770925', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710530', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770926', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710532', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770927', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710533', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770928', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710534', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770929', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710535', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770930', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770931', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770932', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710538', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770933', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710539', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770934', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710540', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770935', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710541', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770936', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '528072364114710542', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770937', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '533782851083177984', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770938', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '533782851095760896', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770939', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '533782851095760897', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770940', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '533782851095760898', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770941', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '533782851095760899', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770942', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '533782851099955200', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770943', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '533782851099955201', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770944', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '533782851099955202', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770945', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203862609920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770946', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203866804224', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770947', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203866804225', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770948', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203866804226', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770949', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203866804227', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770950', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203866804228', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770951', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203866804229', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770952', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203866804230', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770953', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998528', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770954', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998529', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770955', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998530', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770956', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998531', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770957', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998532', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770958', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998533', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770959', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998534', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770960', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998535', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770961', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998536', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770962', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998537', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770963', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998538', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770964', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998539', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770965', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203870998540', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770966', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192832', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770967', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192833', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888731770968', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192834', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888899543041', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192835', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263297', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192836', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263298', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192837', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263299', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192838', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263300', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192839', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263301', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192840', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263302', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192841', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263303', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192842', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263304', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192843', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263305', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192844', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263306', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203875192845', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263307', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387136', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263308', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387137', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263309', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387138', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263310', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387139', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263311', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387140', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263312', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387141', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263313', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387142', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263314', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387143', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263315', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387144', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263316', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387145', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263317', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387146', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263318', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387147', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263319', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387148', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263320', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387149', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263321', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387150', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1384408888958263322', NULL, NULL, '2021-04-20 15:29:34', '2021-04-20 15:29:34', 0, 0, '537112203879387151', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1385047523826765825', NULL, NULL, '2021-04-22 09:46:29', '2021-04-22 09:46:29', 0, 0, '545026427607715841', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1385047523868708865', NULL, NULL, '2021-04-22 09:46:29', '2021-04-22 09:46:29', 0, 0, '545026427607715842', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1385047523868708866', NULL, NULL, '2021-04-22 09:46:29', '2021-04-22 09:46:29', 0, 0, '545026427607715843', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1385047523868708867', NULL, NULL, '2021-04-22 09:46:29', '2021-04-22 09:46:29', 0, 0, '545026427607715844', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1385047523868708868', NULL, NULL, '2021-04-22 09:46:29', '2021-04-22 09:46:29', 0, 0, '545026427607715845', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1385047523868708869', NULL, NULL, '2021-04-22 09:46:29', '2021-04-22 09:46:29', 0, 0, '545026427607715840', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1386854426949500930', NULL, NULL, '2021-04-27 09:27:15', '2021-04-27 09:27:15', 0, 0, '546833329824075776', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1386854426949500931', NULL, NULL, '2021-04-27 09:27:15', '2021-04-27 09:27:15', 0, 0, '546833329828270080', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1386854426949500932', NULL, NULL, '2021-04-27 09:27:15', '2021-04-27 09:27:15', 0, 0, '546833329828270081', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1391933696352722945', NULL, NULL, '2021-05-11 09:50:27', '2021-05-11 09:50:27', 0, 0, '551912599361556480', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1391933696361111554', NULL, NULL, '2021-05-11 09:50:27', '2021-05-11 09:50:27', 0, 0, '551912599365750784', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1391933696361111555', NULL, NULL, '2021-05-11 09:50:27', '2021-05-11 09:50:27', 0, 0, '551912599365750785', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1392076242341130241', NULL, NULL, '2021-05-11 19:16:53', '2021-05-11 19:16:53', 0, 0, '552055145429602305', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1392076242349518850', NULL, NULL, '2021-05-11 19:16:53', '2021-05-11 19:16:53', 0, 0, '552055145429602304', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1392081510198206465', NULL, NULL, '2021-05-11 19:37:49', '2021-05-11 19:37:49', 0, 0, '552060413047607297', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1392081510206595073', NULL, NULL, '2021-05-11 19:37:49', '2021-05-11 19:37:49', 0, 0, '552060413047607298', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1392081510206595074', NULL, NULL, '2021-05-11 19:37:49', '2021-05-11 19:37:49', 0, 0, '552060413047607299', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1392081510206595075', NULL, NULL, '2021-05-11 19:37:49', '2021-05-11 19:37:49', 0, 0, '552060413047607300', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1392081510206595076', NULL, NULL, '2021-05-11 19:37:49', '2021-05-11 19:37:49', 0, 0, '552060413047607301', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1392081510210789377', NULL, NULL, '2021-05-11 19:37:49', '2021-05-11 19:37:49', 0, 0, '552060413047607296', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1393916643129745410', NULL, NULL, '2021-05-16 21:09:59', '2021-05-16 21:09:59', 0, 0, '553895545815568385', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1393916643146522626', NULL, NULL, '2021-05-16 21:09:59', '2021-05-16 21:09:59', 0, 0, '553895545815568386', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1393916643146522627', NULL, NULL, '2021-05-16 21:09:59', '2021-05-16 21:09:59', 0, 0, '553895545815568387', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1393916643146522628', NULL, NULL, '2021-05-16 21:09:59', '2021-05-16 21:09:59', 0, 0, '553895545815568388', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1393916643154911234', NULL, NULL, '2021-05-16 21:09:59', '2021-05-16 21:09:59', 0, 0, '553895545815568389', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1393916643159105538', NULL, NULL, '2021-05-16 21:09:59', '2021-05-16 21:09:59', 0, 0, '553895545815568384', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1393919130330996738', NULL, NULL, '2021-05-16 21:19:52', '2021-05-16 21:19:52', 0, 0, '553898032672935936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1394614127871135746', NULL, NULL, '2021-05-18 19:21:33', '2021-05-18 19:21:33', 0, 0, '554593030007558144', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1394614127879524354', NULL, NULL, '2021-05-18 19:21:33', '2021-05-18 19:21:33', 0, 0, '554593030003363840', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1394615598499008513', NULL, NULL, '2021-05-18 19:27:23', '2021-05-18 19:27:23', 0, 0, '554594501226795008', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1395046514551767042', NULL, NULL, '2021-05-19 23:59:43', '2021-05-19 23:59:43', 0, 0, '555025417212399616', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1395752200759709697', NULL, NULL, '2021-05-21 22:43:52', '2021-05-21 22:43:52', 0, 0, '555731103281975297', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1395752200768098305', NULL, NULL, '2021-05-21 22:43:52', '2021-05-21 22:43:52', 0, 0, '555731103281975296', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1395752782648139777', NULL, NULL, '2021-05-21 22:46:11', '2021-05-21 22:46:11', 0, 0, '555731685308764161', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1395752782664916994', NULL, NULL, '2021-05-21 22:46:11', '2021-05-21 22:46:11', 0, 0, '555731685308764160', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1397193126883426305', NULL, NULL, '2021-05-25 22:09:35', '2021-05-25 22:09:35', 0, 0, '557172028742963200', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399625908788256769', NULL, NULL, '2021-06-01 15:16:34', '2021-06-01 15:16:34', 0, 0, '559604810341552129', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399625908796645378', NULL, NULL, '2021-06-01 15:16:34', '2021-06-01 15:16:34', 0, 0, '559604810341552128', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399627879377059841', NULL, NULL, '2021-06-01 15:24:24', '2021-06-01 15:24:24', 0, 0, '559606783262461953', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399627879389642753', NULL, NULL, '2021-06-01 15:24:24', '2021-06-01 15:24:24', 0, 0, '559606783262461954', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399627879389642754', NULL, NULL, '2021-06-01 15:24:24', '2021-06-01 15:24:24', 0, 0, '559606783262461955', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399627879389642755', NULL, NULL, '2021-06-01 15:24:24', '2021-06-01 15:24:24', 0, 0, '559606783266656256', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399627879389642756', NULL, NULL, '2021-06-01 15:24:24', '2021-06-01 15:24:24', 0, 0, '559606783266656257', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399627879393837057', NULL, NULL, '2021-06-01 15:24:24', '2021-06-01 15:24:24', 0, 0, '559606783266656258', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399627879393837058', NULL, NULL, '2021-06-01 15:24:24', '2021-06-01 15:24:24', 0, 0, '559606783262461952', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399648667115225090', NULL, NULL, '2021-06-01 16:47:00', '2021-06-01 16:47:00', 0, 0, '559627570899980289', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399648667123613698', NULL, NULL, '2021-06-01 16:47:00', '2021-06-01 16:47:00', 0, 0, '559627570899980290', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399648667123613699', NULL, NULL, '2021-06-01 16:47:00', '2021-06-01 16:47:00', 0, 0, '559627570899980291', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399648667123613700', NULL, NULL, '2021-06-01 16:47:00', '2021-06-01 16:47:00', 0, 0, '559627570899980288', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399653181302087682', NULL, NULL, '2021-06-01 17:04:56', '2021-06-01 17:04:56', 0, 0, '559632084210159617', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399653181310476290', NULL, NULL, '2021-06-01 17:04:56', '2021-06-01 17:04:56', 0, 0, '559632084210159618', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399653181314670594', NULL, NULL, '2021-06-01 17:04:56', '2021-06-01 17:04:56', 0, 0, '559632084210159619', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399653181314670595', NULL, NULL, '2021-06-01 17:04:56', '2021-06-01 17:04:56', 0, 0, '559632084210159620', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399653181314670596', NULL, NULL, '2021-06-01 17:04:56', '2021-06-01 17:04:56', 0, 0, '559632084210159621', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399653181314670597', NULL, NULL, '2021-06-01 17:04:56', '2021-06-01 17:04:56', 0, 0, '559632084214353920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399653181314670598', NULL, NULL, '2021-06-01 17:04:56', '2021-06-01 17:04:56', 0, 0, '559632084214353921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1399653181314670599', NULL, NULL, '2021-06-01 17:04:56', '2021-06-01 17:04:56', 0, 0, '559632084210159616', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400025719853133825', NULL, NULL, '2021-06-02 17:45:16', '2021-06-02 17:45:16', 0, 0, '560004623600062465', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400025719861522433', NULL, NULL, '2021-06-02 17:45:16', '2021-06-02 17:45:16', 0, 0, '560004623600062464', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400030166540910593', NULL, NULL, '2021-06-02 18:02:56', '2021-06-02 18:02:56', 0, 0, '560009070304694273', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400030166545104898', NULL, NULL, '2021-06-02 18:02:56', '2021-06-02 18:02:56', 0, 0, '560009070304694274', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400030166545104899', NULL, NULL, '2021-06-02 18:02:56', '2021-06-02 18:02:56', 0, 0, '560009070304694272', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041217', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364102127621', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041218', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364102127622', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041219', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364102127624', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041220', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364102127625', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041221', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364102127627', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041222', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321921', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041223', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321925', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041224', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321927', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041225', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '533782851083177984', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041226', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321928', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041227', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321930', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041228', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321931', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041229', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321932', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041230', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321933', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041231', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364106321936', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041232', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364110516229', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041233', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364110516230', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041234', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364110516231', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041235', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364110516234', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041236', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364110516237', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041237', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364110516241', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041238', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364114710528', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041239', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364114710529', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041240', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364114710533', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041241', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364114710537', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041242', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364114710538', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041243', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '528072364114710539', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036451932041244', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '533782851095760896', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344385', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '533782851095760897', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344386', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '533782851095760898', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344387', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '533782851095760899', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344388', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '533782851099955200', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344389', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '533782851099955201', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344390', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203862609920', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344391', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203866804225', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344392', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203866804226', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344393', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203866804230', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344394', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203870998529', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344395', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203870998530', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344396', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203870998534', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344397', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203870998536', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344398', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203870998537', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344399', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203870998538', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344400', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203875192840', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344401', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203875192842', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344402', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203875192843', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344403', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203879387137', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344404', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203879387139', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344405', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '537112203879387140', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344406', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '553895545815568384', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344407', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '553898032672935936', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344408', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '559606783262461952', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344409', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '559606783262461954', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344410', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '559606783266656258', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344411', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '559632084210159616', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344412', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '559632084210159619', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344413', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '559632084210159620', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344414', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '560004623600062464', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1400036452003344415', NULL, NULL, '2021-06-02 18:27:56', '2021-06-02 18:27:56', 0, 0, '560004623600062465', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401193759420514305', NULL, NULL, '2021-06-05 23:06:38', '2021-06-05 23:06:38', 0, 0, '561172661930168320', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401193759433097218', NULL, NULL, '2021-06-05 23:06:38', '2021-06-05 23:06:38', 0, 0, '561172661930168320', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401197511732924417', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 0, '561176414431350785', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401197511737118721', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 0, '561176414431350786', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401197511737118722', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 0, '561176414431350784', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401197511737118723', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 0, '561176414431350785', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401197511737118724', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 0, '561176414431350786', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401197511741313025', NULL, NULL, '2021-06-05 23:21:32', '2021-06-05 23:21:32', 0, 0, '561176414431350784', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792207454209', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780737', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792215842817', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780738', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792215842818', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780739', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792215842819', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780740', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792215842820', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693525975040', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792215842821', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780736', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792215842822', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780737', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792220037122', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780738', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792220037123', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780739', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792220037124', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780740', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792220037125', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693525975040', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401357792220037126', NULL, NULL, '2021-06-06 09:58:26', '2021-06-06 09:58:26', 0, 0, '561336693521780736', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401364460383334401', NULL, NULL, '2021-06-06 10:24:56', '2021-06-06 10:24:56', 0, 0, '561343361571753984', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401364460395917313', NULL, NULL, '2021-06-06 10:24:56', '2021-06-06 10:24:56', 0, 0, '561343361571753985', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401364460395917314', NULL, NULL, '2021-06-06 10:24:56', '2021-06-06 10:24:56', 0, 0, '561343361580142592', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401364460395917315', NULL, NULL, '2021-06-06 10:24:56', '2021-06-06 10:24:56', 0, 0, '561343361571753984', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401364460395917316', NULL, NULL, '2021-06-06 10:24:56', '2021-06-06 10:24:56', 0, 0, '561343361571753985', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401364460400111617', NULL, NULL, '2021-06-06 10:24:56', '2021-06-06 10:24:56', 0, 0, '561343361580142592', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821385736193', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723186540545', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821398319106', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723186540546', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821398319107', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723186540544', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821398319108', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723190734849', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821398319109', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723194929152', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821402513410', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723194929153', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821402513411', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723190734848', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821406707713', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723186540545', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821406707714', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723186540546', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821406707715', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723186540544', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821410902018', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723190734849', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821410902019', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723194929152', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821410902020', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723194929153', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1401368821410902021', NULL, NULL, '2021-06-06 10:42:16', '2021-06-06 10:42:16', 0, 0, '561347723190734848', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1404295470653689857', NULL, NULL, '2021-06-14 12:31:42', '2021-06-14 12:31:42', 0, 0, '564274372513239040', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1404295470662078465', NULL, NULL, '2021-06-14 12:31:42', '2021-06-14 12:31:42', 0, 0, '564274372525821952', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1404295470662078466', NULL, NULL, '2021-06-14 12:31:42', '2021-06-14 12:31:42', 0, 0, '564274372513239040', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1404295470662078467', NULL, NULL, '2021-06-14 12:31:42', '2021-06-14 12:31:42', 0, 0, '564274372525821952', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746817', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036882038785', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746818', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036882038786', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746819', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036882038787', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746820', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036882038784', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746821', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233089', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746822', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233090', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746823', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233091', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746824', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233092', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746825', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233093', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746826', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233088', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746827', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233095', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746828', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427392', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746829', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233094', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746830', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427394', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746831', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427395', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746832', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427396', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746833', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427397', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746834', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427398', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746835', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427393', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746836', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036953341952', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746837', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427399', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746838', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536256', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746839', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536257', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746840', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036953341953', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746841', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536259', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746842', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536260', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746843', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536261', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746844', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536262', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134099746845', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536263', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907586', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730560', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907587', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536258', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907588', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730562', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907589', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730563', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907590', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730561', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907591', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730565', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907592', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730566', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907593', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730567', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907594', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036965924864', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907595', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036965924865', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907596', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730564', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907597', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036882038785', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907598', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036882038786', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907599', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036882038787', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907600', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036882038784', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907601', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233089', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907602', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233090', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907603', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233091', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907604', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233092', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907605', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233093', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907606', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233088', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907607', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233095', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907608', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427392', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907609', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036886233094', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907610', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427394', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907611', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427395', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907612', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427396', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907613', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427397', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907614', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427398', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907615', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427393', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907616', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036953341952', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907617', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036890427399', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907618', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536256', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907619', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536257', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907620', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036953341953', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907621', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536259', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907622', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536260', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907623', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536261', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907624', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536262', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907625', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536263', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907626', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730560', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907627', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036957536258', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907628', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730562', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907629', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730563', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907630', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730561', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907631', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730565', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134275907632', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730566', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134405931010', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730567', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134405931011', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036965924864', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134405931012', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036965924865', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1408242134405931013', NULL, NULL, '2021-06-25 09:54:23', '2021-06-25 09:54:23', 0, 0, '568221036961730564', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166380511233', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068894326785', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166397288450', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068894326784', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166397288451', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521088', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166401482753', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521089', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166401482754', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068894326786', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166405677057', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521091', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166409871362', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521092', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166414065666', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521090', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166414065667', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521094', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166414065668', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521095', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166418259970', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521096', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166418259971', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521093', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166418259972', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521098', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166418259973', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521099', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166418259974', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521097', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166418259975', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715393', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166426648577', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715394', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166426648578', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715395', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166426648579', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715396', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166430842881', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715397', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166430842882', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715398', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166430842883', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715392', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166435037185', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715400', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166435037186', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715401', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166435037187', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715402', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166439231490', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715403', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166439231491', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715404', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166439231492', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715399', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166443425793', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909696', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166443425794', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909697', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166443425795', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909698', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166447620098', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909699', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166447620099', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909700', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166447620100', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715405', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166451814402', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909702', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166451814403', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909703', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166456008706', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909704', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166456008707', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909705', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166456008708', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909706', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166456008709', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909701', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166456008710', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068894326785', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166456008711', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068894326784', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166456008712', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521088', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166464397314', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521089', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166464397315', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068894326786', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166464397316', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521091', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166468591618', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521092', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166468591619', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521090', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166468591620', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521094', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166468591621', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521095', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166472785922', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521096', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166472785923', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521093', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166472785924', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521098', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980226', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521099', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980227', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068898521097', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980228', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715393', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980229', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715394', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980230', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715395', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980231', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715396', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980232', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715397', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980233', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715398', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980234', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715392', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980235', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715400', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980236', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715401', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980237', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715402', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980238', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715403', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980239', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715404', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166476980240', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715399', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166485368834', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909696', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166485368835', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909697', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166485368836', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909698', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166485368837', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909699', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166485368838', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909700', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166485368839', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068902715405', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166485368840', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909702', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166485368841', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909703', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166489563137', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909704', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166489563138', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909705', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166489563139', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909706', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1423206166489563140', NULL, NULL, '2021-08-05 16:56:06', '2021-08-05 16:56:06', 0, 0, '583185068906909701', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557802545154', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461448904705', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557819322369', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461448904704', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557819322370', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099009', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557819322371', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099010', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557819322372', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099008', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557819322373', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099012', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557819322374', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099013', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557827710978', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099011', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557827710979', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099015', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557827710980', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099016', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557827710981', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099017', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905282', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099014', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905283', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293312', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905284', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293313', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905285', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099018', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905286', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293315', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905287', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293316', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905288', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293317', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905289', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293318', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905290', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293319', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557831905291', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293320', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293889', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293314', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293890', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487617', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293891', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487618', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293892', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487619', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293893', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487620', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293894', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487621', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293895', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487616', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293896', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487623', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557840293897', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487624', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557848682497', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487625', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557848682498', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487626', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557848682499', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487627', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557848682500', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487622', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557848682501', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681920', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557848682502', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681921', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557852876802', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681922', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557852876803', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681923', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557852876804', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681924', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557852876805', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487628', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557852876806', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461448904705', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557857071105', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461448904704', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557857071106', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099009', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557857071107', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099010', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557857071108', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099008', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557857071109', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099012', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265409', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099013', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265410', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099011', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265411', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099015', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265412', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099016', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265413', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099017', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265414', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099014', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265415', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293312', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265416', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293313', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265417', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461453099018', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265418', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293315', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265419', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293316', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557861265420', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293317', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557869654017', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293318', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557869654018', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293319', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557869654019', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293320', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557869654020', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461457293314', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557869654021', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487617', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557869654022', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487618', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848321', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487619', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848322', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487620', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848323', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487621', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848324', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487616', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848325', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487623', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848326', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487624', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848327', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487625', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848328', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487626', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848329', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487627', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557873848330', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487622', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557882236930', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681920', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557882236931', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681921', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557882236932', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681922', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557882236933', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681923', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557882236934', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461465681924', '1384407338139525121');
INSERT INTO `t_admin_role_auth` VALUES ('1426350557882236935', NULL, NULL, '2021-08-14 09:10:48', '2021-08-14 09:10:48', 0, 0, '586329461461487628', '1384407338139525121');

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
INSERT INTO `t_admin_role_menu` VALUES ('1401357606093676546', NULL, NULL, '2021-06-06 09:57:42', '2021-06-06 09:57:42', 0, 0, '1', '1401357604772470785');
INSERT INTO `t_admin_role_menu` VALUES ('1401357647210438657', NULL, NULL, '2021-06-06 09:57:52', '2021-06-06 09:57:52', 0, 0, '1', '1401357646077976578');

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
INSERT INTO `t_admin_role_user` VALUES ('1404303218460069890', NULL, NULL, '2021-06-14 13:02:32', '2021-06-14 13:02:32', 0, 0, '1404303131520536578', '1');
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
INSERT INTO `t_admin_user` VALUES ('1', NULL, NULL, '2020-08-02 15:11:04', '2021-08-18 09:08:49', 0, 0, 'http://xijia.plus/oss/file/image/head/20200822150143006266-5.png', 'wangsong', '17628689969', '兮家小二', 'dfd5e22c8ee4de7da4f07a75fefb6420', '四川成都', 0, 1, 0, '2020-08-02 23:11:05', '2021-08-18 09:08:49', 1, NULL);
INSERT INTO `t_admin_user` VALUES ('1371086008254328834', NULL, NULL, '2021-03-14 21:09:12', '2021-08-17 17:36:24', 0, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210314210827213063-5.jpg', '10000', '10000', '体验用户', '7121b2eeea031a4ecfffcdecb60158fa', '0', 0, 0, 0, '2021-03-14 21:09:13', '2021-08-17 17:36:24', 1, NULL);
INSERT INTO `t_admin_user` VALUES ('1404303131520536578', NULL, NULL, '2021-06-14 13:02:11', '2021-06-14 13:22:08', 0, 0, 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/head/20210614130155853054-1.png', 'yangyue', '17600000000', '阳月', '229db5460300fdcede08e56a90529475', '巴中', 21, 2, 0, '2021-06-14 13:02:12', '2021-06-14 13:22:08', 0, NULL);

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
INSERT INTO `t_xj_admin_banner` VALUES ('1300260217146548226', NULL, NULL, '2020-08-31 10:32:48', '2021-07-31 14:17:37', 0, 0, 1, '测试2', '测试数据2', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210401214853224489-qs44ufe2024qs44ufe2024.jpg', 1, 1, 0, '/page/logoBanner/page/logoBanner');
INSERT INTO `t_xj_admin_banner` VALUES ('1300262684328435714', NULL, NULL, '2020-08-31 10:42:36', '2021-03-22 10:38:07', 0, 0, 1, '测试1', '测试数据一', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/20210322103754968659-aaaa.png', 0, 0, 2, 'http://www.baidu.com');
INSERT INTO `t_xj_admin_banner` VALUES ('1309111625118248961', NULL, NULL, '2020-09-24 20:45:06', '2021-02-05 15:36:11', 0, 0, 1, '测试', '测试描叙', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/banner/2.jpg', 0, 0, 0, '');
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
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332337401510551554', NULL, NULL, '2020-11-27 22:56:02', '2021-03-28 15:00:15', 0, 0, 2, '*', '禁止所有 ip 访问,除本地 [127.0.0.1 / localhost] ,不建议配置在所有资源上，一旦配置，所有用户(包括自己) 将无法访问所有资源，因为每个用户的ip地址都不一样， 开启此功能需提前配置所有用户的ip地址为白名单', 1);
INSERT INTO `t_xj_admin_blacklist` VALUES ('1332374043059408898', NULL, NULL, '2020-11-28 01:21:40', '2021-08-17 15:00:56', 1, 0, 1, '127.0.0.1', '放行本地ip', 0);

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
INSERT INTO `t_xj_admin_config` VALUES ('1365174805250260994', NULL, NULL, '2021-02-26 13:40:11', '2021-06-04 12:51:18', 0, 0, 'entry_name', '项目名称(登录页+首页)', '兮家-运营平台', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365182627308433409', NULL, NULL, '2021-02-26 14:11:17', '2021-03-11 11:36:21', 0, 0, 'login_bg_img', '背景图(登录页)', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210311113615990505-1.jpg', 0, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1365185332319997953', NULL, NULL, '2021-02-26 14:22:01', '2021-06-06 11:10:29', 0, 0, 'beian', '备案号(登录页)', '备案号：蜀ICP备19022468号-2', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1365187122549551105', NULL, NULL, '2021-02-26 14:29:09', '2021-02-26 14:56:26', 0, 0, 'project_desc', '项目描叙(登录页)', '©2020-2021 该后台系统为个人开发运营，作者联系方式 QQ:1720696548', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1369572634420924417', NULL, NULL, '2021-03-10 16:55:34', '2021-03-10 16:56:04', 0, 0, 'test', '测试', 'http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210310165529350451-1615366529000.jpeg,http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/config/20210310165518220164-1.jpg', 3, 1);
INSERT INTO `t_xj_admin_config` VALUES ('1383627414470467586', NULL, NULL, '2021-04-18 11:44:16', '2021-07-28 18:52:45', 0, 0, 'is_sign', '验签开关 ( true / false) -默认true', 'true', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1383636872395255809', NULL, NULL, '2021-04-18 12:21:51', '2021-07-28 18:52:09', 0, 0, 'is_swagger', 'Swagger文档开关(true / false)', 'true', 0, 0);
INSERT INTO `t_xj_admin_config` VALUES ('1383644845431689218', NULL, NULL, '2021-04-18 12:53:32', '2021-07-31 14:22:12', 0, 0, 'is_login_token', 'api 管理端登录是否需要访问令牌', 'false', 0, 0);

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
-- Records of t_xj_admin_datasource
-- ----------------------------
INSERT INTO `t_xj_admin_datasource` VALUES ('1410417736680509442', NULL, NULL, '2021-07-01 09:59:27', '2021-07-14 09:50:52', 0, 0, '藏文化', 'cult', 'rm-bp1b88uc0hxk5wgv0co.mysql.rds.aliyuncs.com', 'lpzs_test', 'Lplb_zslp@123@$#%_lp_lb_20200826', '', '', 'UPDATE_DATE,CREATE_DATE,ID', '', '', '');

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
INSERT INTO `t_xj_admin_msg` VALUES ('1', NULL, NULL, '2021-02-05 17:07:20', '2021-02-06 09:59:10', 0, 0, '1', '系统通知1', 0, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1404306257665441794', NULL, NULL, '2021-06-14 13:14:37', '2021-06-14 13:14:37', 0, 0, '1404303131520536578', '哈喽，小姐姐妳好丫', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1404306380545966081', NULL, NULL, '2021-06-14 13:15:06', '2021-06-14 13:15:06', 0, 0, '1404303131520536578', '哈喽，小姐姐', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1404306586381434882', NULL, NULL, '2021-06-14 13:15:55', '2021-06-14 13:15:55', 0, 0, '1', '？？？？', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1407584098360246273', NULL, NULL, '2021-06-23 14:19:35', '2021-06-23 14:19:35', 0, 0, '', '共和国跟', 1, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1410196422812274689', NULL, NULL, '2021-06-30 19:20:02', '2021-06-30 19:20:02', 0, 0, '1', '啦啦啦啦啦啦', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1410422000769339394', NULL, NULL, '2021-07-01 10:16:24', '2021-07-01 10:16:24', 0, 0, '', 'hello', 1, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1412704008581189633', NULL, NULL, '2021-07-07 17:24:17', '2021-07-07 17:24:17', 0, 0, '1', '给力~', 2, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('1415498915280666625', NULL, NULL, '2021-07-15 10:30:14', '2021-07-15 10:30:14', 0, 0, '', '', 1, 2, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('2', NULL, NULL, '2021-02-05 17:41:38', '2021-02-06 09:59:06', 0, 0, '1', '系统通知2', 0, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('3', NULL, NULL, '2021-02-05 17:42:44', '2021-02-06 09:59:12', 0, 0, '1', '系统通知3', 0, 0, 0);
INSERT INTO `t_xj_admin_msg` VALUES ('4', NULL, NULL, '2021-02-06 09:59:01', '2021-06-22 16:18:28', 0, 0, '1', '系统通知4', 0, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('5', NULL, NULL, '2021-02-06 09:59:21', '2021-06-04 16:07:13', 0, 0, '1', '系统通知5', 0, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('6', NULL, NULL, '2021-02-06 09:59:29', '2021-06-04 16:07:12', 0, 0, '1', '系统通知6', 0, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('7', NULL, NULL, '2021-02-06 10:00:37', '2021-06-02 18:24:45', 0, 0, '1', '系统通知7', 0, 0, 1);
INSERT INTO `t_xj_admin_msg` VALUES ('8', NULL, NULL, '2021-02-06 10:00:47', '2021-06-02 18:24:44', 0, 0, '1', '系统通知8', 0, 0, 1);

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
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '账号id (用户id或商家id  |  平台总账账号为0)',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `money_after` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '用户剩余金额 (交易后金额,如没有钱包默认0)',
  `money` decimal(10, 2) NOT NULL COMMENT '收入支出金额(正数)',
  `wallet_type` int(1) NOT NULL COMMENT '流水类型(1-收入 2-支出)',
  `business_type` int(1) NOT NULL COMMENT '业务类型( 字段code)',
  `business_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务描叙',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单/流水/支付流水表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
