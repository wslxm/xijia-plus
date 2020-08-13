package com.ws.ldy.enums;

import lombok.AllArgsConstructor;

import lombok.Getter;;
public interface Enums {

    /**
     * -
     */
    interface Admin{

        @Getter
        @AllArgsConstructor
        enum MenuRoot{
            MENU_ROOT_1(1, "顶部菜单"),
            MENU_ROOT_2(2, "菜单"),
            MENU_ROOT_3(3, "页面"),
            MENU_ROOT_4(4, "按钮"),
            ;
            private int value;
            private String desc;
        }
    }

    /**
     * -
     */
    interface Base{

        @Getter
        @AllArgsConstructor
        enum Gender{
            GENDER_0(0, "女"),
            GENDER_1(1, "男"),
            GENDER_2(2, "未知"),
            ;
            private int value;
            private String desc;
        }
    }

    /**
     * -
     */
    interface Dev{

        @Getter
        @AllArgsConstructor
        enum TaskItem{
            TASK_ITEM_1(1, "xj-admin"),
            TASK_ITEM_2(2, "xj-colud"),
            ;
            private int value;
            private String desc;
        }

        @Getter
        @AllArgsConstructor
        enum TaskState{
            TASK_STATE_0(0, "未开始"),
            TASK_STATE_1(1, "正在进行"),
            TASK_STATE_2(2, "已完成"),
            TASK_STATE_3(3, "已撤销"),
            ;
            private int value;
            private String desc;
        }
    }
}