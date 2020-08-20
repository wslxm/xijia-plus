package com.ws.ldy.enums;

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
        enum MenuRoot{
            MENU_ROOT_1(1, "顶部菜单"),    // -
            MENU_ROOT_2(2, "菜单"),    // -
            MENU_ROOT_3(3, "页面"),    // -
            MENU_ROOT_4(4, "按钮"),    // -
            ;
            private int value;
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
        enum Disable{
            DISABLE_0(0, "启用"),    // -
            DISABLE_1(1, "禁用"),    // -
            ;
            private int value;
            private String desc;
        }

        // -
        @Getter
        @AllArgsConstructor
        enum Gender{
            GENDER_0(0, "女"),    // -
            GENDER_1(1, "男"),    // -
            GENDER_2(2, "未知"),    // -
            ;
            private int value;
            private String desc;
        }
    }

    /**
     * -
     */
    interface Dev{

        // Task + Bug 展示的项目名称
        @Getter
        @AllArgsConstructor
        enum BaseItem{
            BASE_ITEM_1(1, "xj-admin"),    // 个人开发项目--通用后台管理系统
            BASE_ITEM_2(2, "xj-cloud"),    // 个人搭建的基于spring-colud-ailbaba微服务基础架构
            BASE_ITEM_3(3, "牙贝"),    // 牙贝，医院牙科项目
            BASE_ITEM_4(4, "慧车行"),    // 鹏程慧车行系统
            ;
            private int value;
            private String desc;
        }

        // Task + Bug 任务状态
        @Getter
        @AllArgsConstructor
        enum BaseState{
            BASE_STATE_0(0, "未开始"),    // -
            BASE_STATE_1(1, "正在进行"),    // -
            BASE_STATE_2(2, "已完成"),    // -
            BASE_STATE_3(3, "已撤销"),    // -
            ;
            private int value;
            private String desc;
        }
    }
}