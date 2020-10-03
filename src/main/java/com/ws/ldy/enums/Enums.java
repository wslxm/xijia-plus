package com.ws.ldy.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Enums {

    /**
     * -
     */
    interface Admin{

        // -
        @Getter
        @AllArgsConstructor
        enum AdminUserPosition implements IEnum {
            ADMIN_USER_POSITION_0(0, "系统管理员"),    // -
            ADMIN_USER_POSITION_1(1, "销售"),    // -
            ADMIN_USER_POSITION_2(2, "工厂"),    // -
            ADMIN_USER_POSITION_3(3, "方案中心"),    // -
            ADMIN_USER_POSITION_4(4, "客服"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum AuthorityState implements IEnum {
            AUTHORITY_STATE_0(0, "无"),    // -
            AUTHORITY_STATE_1(1, "需登录"),    // -
            AUTHORITY_STATE_2(2, "需登录+授权"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum AuthorityType implements IEnum {
            AUTHORITY_TYPE_0(0, "管理端"),    // 管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]
            AUTHORITY_TYPE_1(1, "用户端"),    // 用户端, 类上标有该参数所有接口都会默认被列为-[需登录]
            AUTHORITY_TYPE_100(100, "未分类"),    // 未分类的端, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum BannerIsSkip implements IEnum {
            BANNER_IS_SKIP_0(0, "否"),    // -
            BANNER_IS_SKIP_1(1, "内部跳转"),    // -
            BANNER_IS_SKIP_2(2, "外部跳转"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum BannerPosition implements IEnum {
            BANNER_POSITION_1(1, "医生端首页"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum ConfigCode implements IEnum {
            CONFIG_CODE_0(0, "方案中心收件地址"),    // -
            CONFIG_CODE_1(1, "平台运营人员信息"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum MenuRoot implements IEnum {
            MENU_ROOT_1(1, "顶部菜单"),    // -
            MENU_ROOT_2(2, "菜单"),    // -
            MENU_ROOT_3(3, "页面"),    // -
            MENU_ROOT_4(4, "按钮"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum MsgType implements IEnum {
            MSG_TYPE_1(1, "系统通知"),    // -
            MSG_TYPE_2(2, "订单业务通知"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum MsgUserType implements IEnum {
            MSG_USER_TYPE_1(1, "医生端"),    // -
            MSG_USER_TYPE_2(2, "管理端"),    // -
            ;
            private Integer value;
            private String desc;
        }
    }

    /**
     * -
     */
    interface Base{

        // -
        @Getter
        @AllArgsConstructor
        enum Deleted implements IEnum {
            DELETED_0(0, "正常"),    // -
            DELETED_1(1, "已删除"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum Disable implements IEnum {
            DISABLE_0(0, "启用"),    // -
            DISABLE_1(1, "禁用"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum Gender implements IEnum {
            GENDER_1(1, "男"),    // -
            GENDER_0(0, "女"),    // -
            GENDER_2(2, "未知"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum IsMail implements IEnum {
            IS_MAIL_0(0, "未邮寄"),    // -
            IS_MAIL_1(1, "已邮寄"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum IsNeedMail implements IEnum {
            IS_NEED_MAIL_0(0, "否"),    // -
            IS_NEED_MAIL_1(1, "是"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum IsRead implements IEnum {
            IS_READ_0(0, "未读"),    // -
            IS_READ_1(1, "已读"),    // -
            ;
            private Integer value;
            private String desc;
        }
    }

    /**
     * -
     */
    interface Yb{

        // -
        @Getter
        @AllArgsConstructor
        enum ArticlePosition implements IEnum {
            ARTICLE_POSITION_0(0, "无"),    // -
            ARTICLE_POSITION_1(1, "主页"),    // -
            ARTICLE_POSITION_2(2, "登录页"),    // -
            ARTICLE_POSITION_3(3, "添加患者信息-照片面相照"),    // -
            ARTICLE_POSITION_4(4, "添加患者信息-照片口内照"),    // -
            ARTICLE_POSITION_5(5, "添加患者信息-吐烟视频"),    // -
            ARTICLE_POSITION_6(6, "添加患者信息-X光"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum ArticleType implements IEnum {
            ARTICLE_TYPE_1(1, "学院"),    // -
            ARTICLE_TYPE_2(2, "帮助中心"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum IsRepeat implements IEnum {
            IS_REPEAT_0(0, "否"),    // -
            IS_REPEAT_1(1, "是"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum LogisticsState implements IEnum {
            LOGISTICS_STATE_0(0, "未寄送"),    // -
            LOGISTICS_STATE_1(1, "已寄送"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // 管理端 -- 接 ORDER_STATE 流程状态，注意勿重复 (一旦运行,禁止修改code, 只能新增)
        @Getter
        @AllArgsConstructor
        enum OrderAdminType implements IEnum {
            ORDER_ADMIN_TYPE_21(21, "患者列表"),    // -查询所有
            ORDER_ADMIN_TYPE_22(22, "方案中心"),    // -
            ORDER_ADMIN_TYPE_23(23, "客服中心"),    // -
            ORDER_ADMIN_TYPE_24(24, "工厂(废弃)"),    // -
            ORDER_ADMIN_TYPE_25(25, "发货管理"),    // -
            ORDER_ADMIN_TYPE_26(26, "方案中心-所有数据"),    // -
            ORDER_ADMIN_TYPE_27(27, "客服中心-所有数据"),    // -
            ORDER_ADMIN_TYPE_28(28, "工厂-所有患者(废弃)"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -拥挤○牙列间隙―O前突O开合 ○前牙反合○后牙反合○深覆盖○深复合O其他
        @Getter
        @AllArgsConstructor
        enum OrderFeatures implements IEnum {
            ORDER_FEATURES_0(0, "拥挤"),    // -
            ORDER_FEATURES_1(1, "牙列间隙"),    // -
            ORDER_FEATURES_2(2, "前突"),    // -
            ORDER_FEATURES_3(3, "开合"),    // -
            ORDER_FEATURES_4(4, "前牙反合"),    // -
            ORDER_FEATURES_5(5, "后牙反合"),    // -
            ORDER_FEATURES_6(6, "深覆盖"),    // -
            ORDER_FEATURES_7(7, "深复合"),    // -
            ORDER_FEATURES_8(8, "其他"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum OrderIsAppliance implements IEnum {
            ORDER_IS_APPLIANCE_0(0, "否"),    // -
            ORDER_IS_APPLIANCE_1(1, "是"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // 草稿
        @Getter
        @AllArgsConstructor
        enum OrderIsDraft implements IEnum {
            ORDER_IS_DRAFT_0(0, "否"),    // -
            ORDER_IS_DRAFT_1(1, "是"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // 终诊
        @Getter
        @AllArgsConstructor
        enum OrderIsFinal implements IEnum {
            ORDER_IS_FINAL_0(0, "否"),    // -
            ORDER_IS_FINAL_1(1, "是"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // 订单的每一步的操作状态 (一旦运行,禁止修改code, 只能新增)
        @Getter
        @AllArgsConstructor
        enum OrderLogState implements IEnum {
            ORDER_LOG_STATE_1(1, "助理提交患者信息给医生"),    // -
            ORDER_LOG_STATE_2(2, "医生提交患者信息到方案中心"),    // -
            ORDER_LOG_STATE_3(3, "方案中心受理"),    // -
            ORDER_LOG_STATE_4(4, "方案中心驳回"),    // -
            ORDER_LOG_STATE_5(5, "医生邮寄牙模到方案中心"),    // -
            ORDER_LOG_STATE_6(6, "方案中心出方案"),    // -
            ORDER_LOG_STATE_7(7, "医生同意方案"),    // -
            ORDER_LOG_STATE_8(8, "医生驳回方案"),    // -
            ORDER_LOG_STATE_9(9, "放弃治疗"),    // -
            ORDER_LOG_STATE_10(10, "重新治疗"),    // -
            ORDER_LOG_STATE_11(11, "客服同意"),    // -
            ORDER_LOG_STATE_12(12, "工厂受理开始制作矫治器(废弃)"),    // -
            ORDER_LOG_STATE_13(13, "客服填写快递单号"),    // -
            ORDER_LOG_STATE_14(14, "医生收到矫治器"),    // -
            ORDER_LOG_STATE_15(15, "终诊"),    // -
            ORDER_LOG_STATE_16(16, "复诊"),    // -
            ORDER_LOG_STATE_17(17, "归档"),    // -
            ORDER_LOG_STATE_18(18, "医生取消寄件"),    // -
            ORDER_LOG_STATE_19(19, "撤销归档"),    // -
            ORDER_LOG_STATE_20(20, "方案中心人员查看患者信息"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum OrderStage implements IEnum {
            ORDER_STAGE_0(0, "初诊"),    // -
            ORDER_STAGE_1(1, "复诊"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -牙贝核心业务状态值   (一旦运行,禁止修改code, 只能新增)
        @Getter
        @AllArgsConstructor
        enum OrderState implements IEnum {
            ORDER_STATE_0(0, "无"),    // -草稿箱的数据
            ORDER_STATE_1(1, "待提交方案中心"),    // 【待处理】助理创建提交的数据, 需医生提交到方案中心
            ORDER_STATE_2(2, "待方案中心病例受理中"),    // -----------> 医生已提交病例到方案中心
            ORDER_STATE_3(3, "方案中心病例资料退回整改"),    // 【待处理】方案中心病例评估不通过,需医生修改病例资料重新提交
            ORDER_STATE_4(4, "待医生邮寄牙模(废弃)"),    // 	 ----------->方案中心病例已受理
            ORDER_STATE_5(5, "待方案中心出方案"),    // 	 ----------->方案中心已收到医生邮寄的牙模(如果需要),开始制作方案
            ORDER_STATE_6(6, "已出方案待医生确认方案"),    // 【待确认方案】方案中心已出方案 ,等待医生确认方案
            ORDER_STATE_7(7, "医生不同意该方案"),    // ----------->需方案中心重新出方案
            ORDER_STATE_8(8, "医生已同意该方案"),    // ----------->医生同意方案后 [等待客服确认]  或  [放弃治疗]
            ORDER_STATE_9(9, "已放弃治疗"),    // ----------->放弃治疗,订单暂定为已完成订单, 重新治疗状态变为待客服确认方案
            ORDER_STATE_10(10, "客服已同意该方案"),    // ----------->客服同意方案 ----> 等待工厂受理
            ORDER_STATE_11(11, "工厂已受理该订单(废弃)"),    // 【待收货】工厂已受理, 正在制作矫治器，添预发货时间，展示邮寄按钮
            ORDER_STATE_12(12, "制作完成客服已寄出"),    // 【待收货】矫治器制作完成已寄出
            ORDER_STATE_13(13, "已完成 / 医生已收到矫治器"),    // 【已完成+已收货】医生已收到矫治器, 订单完成 ,可以添加复诊
            ORDER_STATE_14(14, "已归档"),    // 【已归档】不能再操作
            ;
            private Integer value;
            private String desc;
        }

        // 医生端首页-- type=1 草稿箱type=2 进行中type=3 已完成type=4 归档type=5 代处理type=6 代确认type=7 代收货type=8 已收货
        @Getter
        @AllArgsConstructor
        enum OrderUserType implements IEnum {
            ORDER_USER_TYPE_1(1, "草稿"),    // -
            ORDER_USER_TYPE_2(2, "进行中"),    // -
            ORDER_USER_TYPE_3(3, "已完成"),    // -
            ORDER_USER_TYPE_4(4, "归档"),    // -
            ORDER_USER_TYPE_5(5, "代处理"),    // -
            ORDER_USER_TYPE_6(6, "代确认"),    // -
            ORDER_USER_TYPE_7(7, "待收货"),    // -
            ORDER_USER_TYPE_8(8, "已收货"),    // -
            ORDER_USER_TYPE_9(9, "待发货-点击顺丰图片"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum YbUserType implements IEnum {
            YB_USER_TYPE_1(1, "医生"),    // -
            YB_USER_TYPE_2(2, "助理"),    // -
            YB_USER_TYPE_3(3, "经销商"),    // -
            YB_USER_TYPE_4(4, "诊所"),    // -
            ;
            private Integer value;
            private String desc;
        }
    }
}