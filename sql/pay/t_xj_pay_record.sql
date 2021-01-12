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

 Date: 28/12/2020 17:06:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易订单号',
  `wallet_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易流水号( 支付/退款/放款等)',
  `platform_fee` decimal(10, 2) DEFAULT NULL COMMENT '平台手续费(元)',
  `channel_fee` decimal(10, 2) DEFAULT NULL COMMENT '第三方手续费(元), 为0时,手续费累加在 platform_fee',
  `money_total` decimal(10, 2) DEFAULT NULL COMMENT '交易总金额( 元)',
  `pay_state` int(1) DEFAULT NULL COMMENT '交易状态( 0-已发起 1-回调成功(临时状态) 2-交易失败 3-交易成功 )',
  `pay_channel` int(1) DEFAULT NULL COMMENT '支付渠道( 字典code 如1-支付宝 2-微信 3-银行卡 等)',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付类型( 字典code 如 1-支付 2-充值 3-退款 等)',
  `business_type` int(1) DEFAULT NULL COMMENT '业务类型( 字典code -根据系统业务定制 如  1-用户支付  2-平台打款 等)',
  `business_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务描叙( 追踪具体业务)',
  `request_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求数据',
  `response_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '响应数据',
  `callback_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '回调数据',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方支付记录表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
