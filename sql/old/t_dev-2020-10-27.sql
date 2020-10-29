/*
 Navicat Premium Data Transfer

 Source Server         : 47.107.128.84
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 47.107.128.84:3306
 Source Schema         : spring-boot-plus2

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 27/10/2020 21:10:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dev_bug
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_bug`;
CREATE TABLE `t_dev_bug`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常  1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
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
-- Records of t_dev_bug
-- ----------------------------
INSERT INTO `t_dev_bug` VALUES ('1289559552445919234', '1', NULL, '2020-08-01 13:52:10', '2020-08-01 13:53:29', 0, 0, '1', 'item-xj-admin', 'id删除异常', 'id删除字段类型错误', 1, 2, '2020-08-01 00:00:00', '2020-08-01 21:53:30', 1.00, 1.00);
INSERT INTO `t_dev_bug` VALUES ('1289559832394739714', '1', NULL, '2020-08-01 13:53:17', '2020-08-01 13:53:35', 0, 0, '1', 'item-xj-admin', '权限url扫描异常', '更新权限url 时后台错误', 1, 2, '2020-08-01 00:00:00', '2020-08-01 21:53:35', 1.00, 2.00);
INSERT INTO `t_dev_bug` VALUES ('1289980627638067202', '1', NULL, '2020-08-02 17:45:23', '2020-08-04 04:16:54', 0, 0, '1', 'item-xj-admin', '服务器添加头像 405 Method Not Allowed', '405 Method Not Allowed，\n前端ajax发起post请求，结果返回405，Not allow method！原因为 Apache、IIS、Nginx等绝大多数web服务器，\n都不允许静态文件响应POST请求。\n\n错误原因：接口与Nginx配置冲突，访问地址不对 oss/', 1, 2, '2020-08-09 00:00:00', '2020-08-04 04:16:54', 2.00, 2.00);
INSERT INTO `t_dev_bug` VALUES ('1291802505147133953', '1', NULL, '2020-08-07 18:24:51', '2020-08-09 10:57:26', 0, 0, '1', 'item-xj-admin', '菜单分配', '选择菜单，角色回显错误', 1, 2, '2020-08-08 00:00:00', '2020-08-09 10:57:27', 1.00, 1.00);

-- ----------------------------
-- Table structure for t_dev_norm
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_norm`;
CREATE TABLE `t_dev_norm`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常  1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规范名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规范内容(md-富文本)',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '开发规范' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_norm
-- ----------------------------
INSERT INTO `t_dev_norm` VALUES ('1289116285992501250', NULL, NULL, '2020-07-31 16:30:47', '2020-08-01 10:22:16', 0, 0, '测试添加', '啊是发撒\nsafsafasface[吐] 11', 0);
INSERT INTO `t_dev_norm` VALUES ('1289126554407862273', NULL, NULL, '2020-07-31 17:11:36', '2020-07-31 17:15:20', 0, 0, '测试编辑', '\n表头|表头|表头\n:---:|:--:|:---:\n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n', 0);
INSERT INTO `t_dev_norm` VALUES ('1289133797173895169', NULL, NULL, '2020-07-31 17:40:22', '2020-07-31 17:40:22', 0, 0, '测试-数据查看', 'face[嘻嘻] face[嘻嘻] face[嘻嘻]\n\n![图片加载失败](https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1576026414&di=4121c010940f72cab1cbcea65e698d0f&src=http://hbimg.b0.upaiyun.com/b1cce6f996734bdbb9b3fb9ef7705deabc980e35493b-ysf8BZ_fw658)\n\n[http://www.baidu.com](http://www.baidu.com)\n\n - 是的发送的\n - 阿斯顿发生大\n - 阿斯顿发斯蒂芬\n\n\n1. 阿斯顿发斯蒂芬\n2. 阿斯顿发送到发士大夫\n3. 阿斯顿发送到发大\n\n\n\n~~~\n	public static void main(String[] args){\n		System.out.print(\"Hello World\");\n	}\n~~~\n\n -----\n\n\n> Java世界上最好的语言\n\n\n -  跨平台\n -  面向对象\n -  牛掰\n\n -----\n\n\n1. 大众化\n2. 应用广\n3. 方向全\n\n -----\n\n\n表头|表头|表头\n:---:|:--:|:---:\n内容|内容|内容\n\n -----\n\n\n强制换行（双空格加回车）\n\n\n\n强制换行\n		', 0);
INSERT INTO `t_dev_norm` VALUES ('1289149437230190594', NULL, NULL, '2020-07-31 18:42:31', '2020-07-31 18:48:18', 0, 0, '接口方法统一命名', '\n## 方法统一命名\n\n### 查询\n| 方法 | 方法命名| 必传参数  |\n|:--------| :-------------| :-------------:|\n| ID查询 | findId|id|\n| 分页查询 | findPage | current=页数 size=记录数|\n| 所有查询 | findList | 无|\n| 查询 必须 xx+yy 条件 | findXxAndYy | xx && yy|\n| 查询 根据 xx+yy 其中一个| findXxOrYy | xx 或 yy|\n\n### 添加\n| 方法 | 方法命名  | 必传参数 |\n|:--------| :-------------| :-------------:|\n| 添加 | add  或  insert | dto\n\n### 修改\n| 方法 | 方法命名  | 必传参数 |\n|:--------| :-------------| :-------------:|\n| ID编辑所有 | upd | dto+id|\n| 编辑 xx 字段 | updXx |  xx|\n| 编辑 xx + yy字段 | updXxAndYy | xx + yy|\n\n### 删除\n| 方法 | 方法命名 | 必传参数      |\n|:--------| :-------------| :-------------:|\n| ID删除 | removeId |id|\n| 根据 xx字段删除 | removeXx | xx|\n| 根据 xx + yy 字段删除 | removeXxAndYy | xx + yy|\n\n### 判断\n| 方法 | 方法命名 | 必传参数   | 返回\n|:--------| :-------------| :-------------:| :-------------:|\n| 判断???  | is???  | 判断必传递参数 | true 或 false|\n', 0);

-- ----------------------------
-- Table structure for t_dev_renew
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_renew`;
CREATE TABLE `t_dev_renew`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常  1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
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
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常  1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '学习内容(md-富文本)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学习计划' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_study
-- ----------------------------
INSERT INTO `t_dev_study` VALUES ('1288877310027554818', NULL, NULL, '2020-07-31 00:41:11', '2020-07-31 00:41:11', 0, 0, '1', '2', 1);

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
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：正常  1：删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `task_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指派给id',
  `item` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '项目(字典表code)',
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
INSERT INTO `t_dev_task` VALUES ('1', '2020-06-27 12:04:22', NULL, NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '开发任务模块', '类型，状态搜索，开始，完成，撤销任务等', 1, 3, '2020-05-05 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('10', '2020-07-25 10:10:04', NULL, NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '数据库表结构通用字段重定义', '除id外的6大通用字段\n-- 添加\nalter table t_dev_norm add `create_user`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT \'创建账户id\';\nalter table t_dev_norm add `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT \'更新账户id\';\nalter table t_dev_norm add `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT \'创建时间(自动插入)\';\nalter table t_dev_norm add `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT \'更新时间(自动插入)\';\nalter table t_dev_norm add `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT \'逻辑删除字段(0：正常 1：删除)\';\nalter table t_dev_norm add `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT \'乐观锁\';', 1, 2, '2020-07-25 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('11', '2020-07-25 10:11:52', NULL, NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '任务字体颜色修改', '已完成改为绿色\n未开始改为红色\n正在执行默认粉红色\n撤销的默认黑色', 1, 2, '2020-07-25 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('12', '2020-07-25 10:18:52', NULL, NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '添加任务完成时间', '任务完成后没有完成时间', 1, 2, '2020-07-25 00:00:00', '2020-07-25 10:39:28', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289557981020565505', '2020-08-01 13:45:56', '1', NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '数据表-添加是否必填字段', '添加是否必填，方便查看字段', 1, 2, '2020-08-01 00:00:00', '2020-08-01 22:15:42', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289558965448880129', '2020-08-01 13:49:50', '1', NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '代码中所有 TODO 标识处理', ' TODO 代处理代码, 勿烂用', 1, 2, '2020-08-02 00:00:00', '2020-08-02 20:36:57', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289559346019053569', '2020-08-01 13:51:21', '1', NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '代码生成器controller优化', '1、添加findId 查询\n2、分页查询排序改为根据创建时间 DESC倒序\n3、添加 mapper.xml 层代码\n4、代码生成器DTO添加必填验证注解', 1, 2, '2020-08-02 00:00:00', '2020-08-02 10:35:57', 2.00, 4.00);
INSERT INTO `t_dev_task` VALUES ('1289580275457675266', '2020-08-01 15:14:31', '1', NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '修改返回对象+返回枚举', '通用返回对象优化，返回枚举类优化', 1, 2, '2020-08-02 00:00:00', '2020-08-02 11:21:45', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289734714160132098', '2020-08-02 01:28:12', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'jwt TOKEN 自动刷新', 'token到期会自动失效，任意操作后自动刷新token', 1, 2, '2020-08-02 00:00:00', '2020-08-02 23:38:47', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289752060748636162', '2020-08-02 02:37:08', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'JSR 303 验证返回错误信息处理', '全局异常处理jsr303返回dto字段标注的异常内容', 1, 2, '2020-08-02 00:00:00', '2020-08-02 12:41:35', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289763468169199617', '2020-08-02 03:22:27', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'req 请求参数不匹配返回错误处理', 'req 请求参数不匹配返回对应错误信息', 1, 2, '2020-08-02 00:00:00', '2020-08-02 12:41:39', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289783274108891137', '2020-08-02 04:41:09', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'req 没有请求参数异常信息返回处理', '优化请求错误返回信息', 1, 2, '2020-08-02 00:00:00', '2020-08-02 12:41:43', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289916333886050305', '2020-08-02 13:29:53', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '接口名称统一', '部分接口名称不统一\n1、分页查询 --> findPage\n2、查询使用 --> findList\n3、id查询 --> findId\n4、id编辑  --> upd\n5、添加 --> insert\n6 、id删除  -->  del\n7、多id删除  delByIds', 1, 2, '2020-08-02 00:00:00', '2020-08-02 23:27:07', 2.00, 3.00);
INSERT INTO `t_dev_task` VALUES ('1289928054088421377', '2020-08-02 14:16:27', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '请求日志优化', '请求log 日志处理', 1, 2, '2020-08-02 00:00:00', '2020-08-02 22:29:08', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289928341167558658', '2020-08-02 14:17:36', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '登录处理，目前只支持账号密码登录', '1、账号密码登录\n2、手机号+验证码登录\n3、手机号+密码登录', 1, 0, '2020-08-02 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1289928892554956802', '2020-08-02 14:19:47', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '阿里云短信', '添加阿里云短信工具类，用于登录等其他用途', 1, 2, '2020-08-02 00:00:00', '2020-08-04 23:41:34', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289929809945690114', '2020-08-02 14:23:26', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'swagger 方法@ApiOperation 优化', '统一格式为：\n@ApiOperation(value = \"操作功能\",notes= \"详细描叙\")', 1, 2, '2020-08-06 00:00:00', '2020-08-04 06:41:27', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289937151382781953', '2020-08-02 14:52:36', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '前端页面接口参数命名优化', '所有html 接口地址参数命名统一', 1, 0, '2020-08-06 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1289937407927386113', '2020-08-02 14:53:37', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '所有类名优化', '以数据库名称为准，如: t_admin_user = AdminUser', 1, 2, '2020-08-06 00:00:00', '2020-08-06 09:28:40', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289942318287224834', '2020-08-02 15:13:08', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典管理删除优化', '删除父级时同时删除所有子层级实际\n优化查询，无极限\n优化添加，code 不重复\n优化应该，code 不重复', 1, 2, '2020-08-06 00:00:00', '2020-08-04 06:40:13', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289949333222977538', '2020-08-02 15:41:01', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '开发任务功能优化', '点击第二页未携带第一页的查询条件', 1, 2, '2020-08-06 00:00:00', '2020-08-03 00:26:39', 1.00, 1.50);
INSERT INTO `t_dev_task` VALUES ('1291185151111348225', '2020-08-06 09:31:43', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '角色添加禁用功能', '角色添加禁用功能，\n1、禁用后的角色将没有对应的菜单权限\n2、禁用后的角色将没有对应的url接口权限，重新登录生效\n', 1, 2, '2020-08-07 00:00:00', '2020-08-08 02:01:14', 2.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1291185386852204546', '2020-08-06 09:32:39', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '接口权限添加禁用功能', '接口禁用后任何人无法访问该接口，', 1, 0, '2020-08-07 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1291338450041131010', '2020-08-06 11:40:53', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典添加禁用功能', '字典添加禁用功能，禁用数据除了当前列表外，其他地方的页面查询都不查询出来禁用数据', 1, 2, '2020-08-09 00:00:00', '2020-08-08 01:19:05', 2.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1291777119851974658', '2020-08-07 16:43:59', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典管理生成代码', '1、生成 java常量类，做逻辑判断\n2、生成 js 常量key，做缓存数据获取', 1, 0, '2020-08-08 00:00:00', NULL, 3.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1291798116088971266', '2020-08-07 18:07:25', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '权限分配联动选择', '选中父级自动选择子菜单', 1, 2, '2020-08-09 00:00:00', '2020-08-08 15:32:27', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1292261691161096193', '2020-08-09 08:49:30', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '禁用统一使用 upd 方法来操作', '所有禁用功能使用upd方法', 1, 2, '2020-08-09 09:30:00', '2020-08-09 10:58:55', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1292261904844107777', '2020-08-09 08:50:21', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典管理生成静态数据', '字典管理生成对应的后台数据+ 前端数据', 1, 0, '2020-08-09 10:30:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292294460721389569', '2020-08-09 10:59:43', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '后台代码优化', '后台代码优化，删除无用代码，规范整理', 1, 0, '2020-08-09 11:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('13', '2020-07-25 10:49:21', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'bug修复-完整功能', 'bug修复页的全部功能内容', 1, 2, '2020-08-09 00:00:00', '2020-08-01 21:46:39', 8.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('14', '2020-07-25 10:51:01', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '学习计划-完整功能', '学习计划页的全部功能内容', 1, 0, '2020-08-08 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('15', '2020-07-25 10:52:30', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '更新记录-全功能', '更新记录也的全部功能内容', 1, 0, '2020-08-23 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('16', '2020-07-25 10:53:18', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '开发规范-全功能', '开发规范页的全部功能内容', 1, 2, '2020-08-30 00:00:00', '2020-07-31 17:41:37', 8.00, 3.00);
INSERT INTO `t_dev_task` VALUES ('18', '2020-07-25 12:24:11', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '密码加密', '用户密码进行MD5 加盐/加密\n1、修改密码\n2、登录\n3、添加', 1, 2, '2020-08-01 00:00:00', '2020-08-01 23:13:15', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('3', '2020-07-12 20:30:13', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典表操作后自动刷新', '添加/编辑/删除时未自动刷新页面数据，需优化功能', 1, 2, '2020-07-12 00:00:00', '2020-08-04 04:32:14', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('4', '2020-07-12 20:32:02', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '菜单图标', '添加和编辑时图标改为可选', 1, 0, '2020-07-18 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('7', '2020-07-12 23:26:44', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '开发任务模块', '完成输入实际耗时，撤销已完成删除完成时间及实际耗时', 1, 2, '2020-07-11 00:00:00', '2020-07-25 10:41:24', 2.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('8', '2020-07-12 23:29:02', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '开发任务模块', '添加任务详情页，鼠标移动到任务名或任务内存处自动弹出任务详情...', 1, 0, '2020-07-18 00:00:00', NULL, 0.00, NULL);
INSERT INTO `t_dev_task` VALUES ('9', '2020-07-12 23:39:11', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '每页记录数BUG处理', '选择了其他页数后面，点击搜索每页记录数被回滚到10', 4, 3, '2020-07-18 00:00:00', NULL, 2.00, NULL);

SET FOREIGN_KEY_CHECKS = 1;
