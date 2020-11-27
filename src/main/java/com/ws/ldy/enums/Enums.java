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
            AUTHORITY_TYPE_100(100, "通用接口"),    // 未分类的端, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]
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
    }

    /**
     * -
     */
    interface Base{

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
        enum IsMail implements IEnum<Integer> {
            IS_MAIL_0(0, "未邮寄"),    // -
            IS_MAIL_1(1, "已邮寄"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum IsNeedMail implements IEnum<Integer> {
            IS_NEED_MAIL_0(0, "否"),    // -
            IS_NEED_MAIL_1(1, "是"),    // -
            ;
            private Integer value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum IsRead implements IEnum<Integer> {
            IS_READ_0(0, "未读"),    // -
            IS_READ_1(1, "已读"),    // -
            ;
            private Integer value;
            private String desc;
        }
    }
}