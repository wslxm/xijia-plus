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

 Date: 12/01/2021 11:07:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_xj_file
-- ----------------------------
DROP TABLE IF EXISTS `t_xj_file`;
CREATE TABLE `t_xj_file`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名(标题)',
  `desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件描叙',
  `size` double(10, 2) NOT NULL COMMENT '文件大小',
  `suffix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件格式(后缀)',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件url',
  `type` int(2) NOT NULL COMMENT '文件类型(1-开发工具 2-源码  3-文档   4-图片 5-音频 6-视频 7-sql)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '额外功能表--常用工具文件管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_xj_file
-- ----------------------------
INSERT INTO `t_xj_file` VALUES ('1339486612781879297', NULL, NULL, '2020-12-17 16:24:29', '2020-12-17 21:33:19', 1, 0, '1', '2', 3.00, '4', '5', 6);
INSERT INTO `t_xj_file` VALUES ('1339502966322843650', NULL, NULL, '2020-12-17 17:29:28', '2020-12-17 21:33:21', 1, 0, 'wifi.txt', '1', 21.00, '.txt', 'http://localhost:9049/oss/file/file/xj/1/wifi.txt', 1);
INSERT INTO `t_xj_file` VALUES ('1339555902683889665', NULL, NULL, '2020-12-17 20:59:49', '2020-12-22 16:07:48', 1, 0, 'vuejs-203.5981.152.zip', 'idea 的 vuejs 插件， 20年3月版idea', 1030829.00, '.zip', 'http://localhost:9049/oss/file/file/xj/1/vuejs-203.5981.152.zip', 1);
INSERT INTO `t_xj_file` VALUES ('1339556412065333250', NULL, NULL, '2020-12-17 21:01:51', '2020-12-17 23:33:45', 1, 0, '18、燃 Act #01.mp4', '视频', 52.53, 'mp4', 'http://localhost:9049/oss/file/file/xj/6/18、燃_Act__01.mp4', 6);
INSERT INTO `t_xj_file` VALUES ('1339557408925564929', NULL, NULL, '2020-12-17 21:05:49', '2020-12-17 23:34:00', 1, 0, '13、当我苏醒的那一刻，世界：你毛都不是！.mp4', '这是一个视频', 84161450.00, '.mp4', 'http://localhost:9049/oss/file/file/xj/1/13、当我苏醒的那一刻，世界：你毛都不是！.mp4', 1);
INSERT INTO `t_xj_file` VALUES ('1339559586037776386', NULL, NULL, '2020-12-17 21:14:28', '2020-12-22 16:07:18', 1, 0, '13、当我苏醒的那一刻，世界：你毛都不是！.mp4', '当我苏醒的那一刻，世界：你毛都不是！', 84161.45, 'mp4', 'http://localhost:9049/oss/file/file/xj/1/13、当我苏醒的那一刻，世界：你毛都不是！.mp4', 1);
INSERT INTO `t_xj_file` VALUES ('1339561464477794307', NULL, NULL, '2020-12-17 21:21:55', '2020-12-17 22:53:15', 1, 0, '第一期目录.xlsx', 'lala', 12.86, 'xlsx', 'http://localhost:9049/oss/file/file/xj/1/第一期目录.xlsx', 1);
INSERT INTO `t_xj_file` VALUES ('1339563464292581377', NULL, NULL, '2020-12-17 21:29:52', '2020-12-17 22:53:18', 1, 0, 'wifi.txt', '1', 21.00, '.txt', 'http://localhost:9049/oss/file/file/xj/1/wifi.txt', 1);
INSERT INTO `t_xj_file` VALUES ('1339563487755517954', NULL, NULL, '2020-12-17 21:29:58', '2020-12-17 22:53:21', 1, 0, 'wifi.txt', '1', 21.00, '.txt', 'http://localhost:9049/oss/file/file/xj/1/wifi.txt', 1);
INSERT INTO `t_xj_file` VALUES ('1339563571675152387', NULL, NULL, '2020-12-17 21:30:18', '2020-12-17 22:53:24', 1, 0, '第一期目录.xlsx', '2', 12.86, 'xlsx', 'http://localhost:9049/oss/file/file/xj/6/第一期目录.xlsx', 6);
INSERT INTO `t_xj_file` VALUES ('1339570356351942658', NULL, NULL, '2020-12-17 21:57:15', '2020-12-17 22:53:28', 1, 0, '第一期目录.xlsx', '啦啦，ok啦', 12.86, 'xlsx', 'http://localhost:9049/oss/file/file/xj/1/第一期目录.xlsx', 1);
INSERT INTO `t_xj_file` VALUES ('1339588139043299330', NULL, NULL, '2020-12-17 23:07:55', '2020-12-17 23:36:07', 1, 0, '18、燃 Act #01.mp4', '啦啦', 52.53, 'mp4', 'http://localhost:9049/oss/file/file/xj/1/18、燃_Act__01.mp4', 6);
INSERT INTO `t_xj_file` VALUES ('1339595501330296834', NULL, NULL, '2020-12-17 23:37:10', '2020-12-22 16:07:11', 1, 0, '18、燃 Act #01.mp4', '视频\n', 50.10, 'mp4', 'http://localhost:9049/oss/file/file/xj/1/18、燃_Act__01.mp4', 6);
INSERT INTO `t_xj_file` VALUES ('1339603447510536193', NULL, NULL, '2020-12-18 00:08:45', '2020-12-22 16:07:27', 1, 0, '11、恭喜你发现宝藏！！！.mp4', '视频', 47.74, 'mp4', 'http://xijia.plus/oss/file/file/xj/1/11、恭喜你发现宝藏！！！.mp4', 1);
INSERT INTO `t_xj_file` VALUES ('1339604405892222977', NULL, NULL, '2020-12-18 00:12:33', '2020-12-22 16:07:24', 1, 0, '6、回眸一笑百媚生，六宫粉黛无颜色.mp4', '视频', 138.01, 'mp4', 'http://xijia.plus/oss/file/file/xj/1/6、回眸一笑百媚生，六宫粉黛无颜色.mp4', 1);
INSERT INTO `t_xj_file` VALUES ('1339750544306147329', NULL, NULL, '2020-12-18 09:53:15', '2020-12-22 16:19:01', 0, 0, 'jdk-8u261-linux-x64.tar.gz', 'jdk 8 linux 解压版，解压配置环境变量即可 Linux 任意目录使用，\n参考文章：https://blog.csdn.net/qq_41463655/article/details/99173682', 136.48, 'gz', 'http://xijia.plus/oss/file/file/xj/1/jdk-8u261-linux-x64.tar.gz', 1);
INSERT INTO `t_xj_file` VALUES ('1341294129748922370', NULL, NULL, '2020-12-22 16:06:55', '2020-12-22 16:06:55', 0, 0, 'ngrok_windows_amd64.zip', '纯免费的内网穿透工具, 在后台开通免费隧道, 启动exe放入隧道id即可使用，后台地址: http://www.ngrok.cc/login.html', 3.88, 'zip', 'http://xijia.plus/oss/file/file/xj/null/ngrok_windows_amd64.zip', 1);
INSERT INTO `t_xj_file` VALUES ('1341296210484416513', NULL, NULL, '2020-12-22 16:15:11', '2020-12-22 16:19:42', 0, 0, 'apache-jmeter-5.3.zip', '接口压测工具，直接双击bin 目录下的 ApacheJMeter.jar 或 cmd命令java -jar ApacheJMeter.jar   运行， 使用说明：1、添加线程组   2、添加[http请求] 和 [参数信息]   3.添加监听器[结果树] 和 [聚合报告]', 67.38, 'zip', 'http://xijia.plus/oss/file/file/xj/null/apache-jmeter-5.3.zip', 1);

SET FOREIGN_KEY_CHECKS = 1;
