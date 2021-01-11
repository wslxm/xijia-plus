package com.ws.ldy.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Enums {

    /**
     * -
     */
    interface Admin {

        // -
        @Getter
        @AllArgsConstructor
        enum AdminUserPosition implements IEnum<Integer> {
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
        enum AuthorityState implements IEnum<Integer> {
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
        enum AuthorityType implements IEnum<Integer> {
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
        enum BannerIsSkip implements IEnum<Integer> {
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
        enum BannerPosition implements IEnum<Integer> {
            BANNER_POSITION_1(1, "医生端首页"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum BlacklistType implements IEnum<Integer> {
            BLACKLIST_TYPE_1(1, "白名单"),    // -
            BLACKLIST_TYPE_2(2, "黑名单"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum BusinessType implements IEnum<Integer> {
            BUSINESS_TYPE_1(1, "月缴费"),    // -
            BUSINESS_TYPE_2(2, "互助发放"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum ConfigCode implements IEnum<Integer> {
            CONFIG_CODE_0(0, "方案中心收件地址"),    // -
            CONFIG_CODE_1(1, "平台运营人员信息"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum HelpCategory implements IEnum<Integer> {
            HELP_CATEGORY_0(0, "开始使用"),    // -
            HELP_CATEGORY_1(1, "采用技术"),    // -
            HELP_CATEGORY_2(2, "系统功能"),    // -
            HELP_CATEGORY_3(3, "引入技术"),    // -
            HELP_CATEGORY_4(4, "运维部署"),    // -
            HELP_CATEGORY_5(5, "常用技术文章"),    // -
            HELP_CATEGORY_6(6, "其他"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum HelpVersion implements IEnum<Integer> {
            HELP_VERSION_0(0, "0.x "),    // -
            HELP_VERSION_1(1, "1.x"),    // -
            HELP_VERSION_2(2, "1.5x "),    // -
            HELP_VERSION_3(3, "2.x"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum MenuRoot implements IEnum<Integer> {
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
        enum MsgType implements IEnum<Integer> {
            MSG_TYPE_1(1, "系统通知"),    // -
            MSG_TYPE_2(2, "订单业务通知"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum MsgUserType implements IEnum<Integer> {
            MSG_USER_TYPE_1(1, "用户端"),    // -
            MSG_USER_TYPE_2(2, "管理端"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // 交易状态( 0-已发起 1-回调成功(临时状态) 2-交易失败 3-交易成功 )
        @Getter
        @AllArgsConstructor
        enum PayState implements IEnum<Integer> {
            PAY_STATE_0(0, "已发起"),    // -
            PAY_STATE_1(1, "回调成功"),    // -
            PAY_STATE_2(2, "交易失败"),    // -
            PAY_STATE_3(3, "交易成功"),    // -
            PAY_STATE_4(4, "订单异常"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum PayType implements IEnum<Integer> {
            PAY_TYPE_1(1, "支付"),    // -
            PAY_TYPE_2(2, "充值"),    // -
            PAY_TYPE_3(3, "退款"),    // -
            PAY_TYPE_4(4, "商家打款"),    // -
            ;
            private Integer value;
            private String desc;
        }
    }

    /**
     * -
     */
    interface Base {

        // -
        @Getter
        @AllArgsConstructor
        enum Deleted implements IEnum<Integer> {
            DELETED_0(0, "正常"),    // -
            DELETED_1(1, "已删除"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum Disable implements IEnum<Integer> {
            DISABLE_0(0, "启用"),    // -
            DISABLE_1(1, "禁用"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum Gender implements IEnum<Integer> {
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
        enum PayChannel implements IEnum<Integer> {
            PAY_CHANNEL_1(1, "支付宝"),    // -
            PAY_CHANNEL_2(2, "微信"),    // -
            PAY_CHANNEL_3(3, "银联"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // 该字段只展示, 程序中直接使用 true/ false 即可
        @Getter
        @AllArgsConstructor
        enum Whether implements IEnum<Integer> {
            WHETHER_0(0, "否"),    // 对应数据库 tinyint = 0 = false
            WHETHER_1(1, "是"),    // 对应数据库 tinyint = 1 = true
            ;
            private Integer value;
            private String desc;
        }
    }

    /**
     * -
     */
    interface Pet {

        // -
        @Getter
        @AllArgsConstructor
        enum AccountType implements IEnum<Integer> {
            ACCOUNT_TYPE_1(1, "平台"),    // -
            ACCOUNT_TYPE_2(2, "消费者"),    // -
            ACCOUNT_TYPE_3(3, "商家"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum DeclaceErrorType implements IEnum<Integer> {
            DECLACE_ERROR_TYPE_1(1, "宠物信息不全或错误"),    // -
            DECLACE_ERROR_TYPE_2(2, "主人信息不全或错误"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum DeclareState implements IEnum<Integer> {
            DECLARE_STATE_0(0, "申报中"),    // -
            DECLARE_STATE_1(1, "已驳回"),    // -
            DECLARE_STATE_2(2, "申报失败"),    // -
            DECLARE_STATE_3(3, "申报成功"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum InviteConfigType implements IEnum<Integer> {
            INVITE_CONFIG_TYPE_1(1, "分享邀请"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum OrderState implements IEnum<Integer> {
            ORDER_STATE_0(0, "待支付"),    // -
            ORDER_STATE_1(1, "支付失败"),    // -
            ORDER_STATE_2(2, "支付成功"),    // -
            ORDER_STATE_3(3, "已绑定宠物"),    // -
            ORDER_STATE_4(4, "订单失效"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum PetsSex implements IEnum<Integer> {
            PETS_SEX_1(1, "弟弟"),    // -
            PETS_SEX_2(2, "妹妹"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum WalletType implements IEnum<Integer> {
            WALLET_TYPE_1(1, "收入"),    // -
            WALLET_TYPE_2(2, "支出"),    // -
            ;
            private Integer value;
            private String desc;
        }
    }
}