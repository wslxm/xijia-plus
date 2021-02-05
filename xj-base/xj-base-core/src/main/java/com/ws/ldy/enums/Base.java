package com.ws.ldy.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("all")
public interface Base{

    // -
    @Getter
    @AllArgsConstructor
    enum Deleted implements IEnum<Integer> {
        V0(0, "正常"),    // -
        V1(1, "已删除"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum Disable implements IEnum<Integer> {
        V0(0, "启用"),    // -
        V1(1, "禁用"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum Gender implements IEnum<Integer> {
        V0(0, "未知"),    // -
        V1(1, "男"),    // -
        V2(2, "女"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum IsRead implements IEnum<Integer> {
        V0(0, "未读"),    // -
        V1(1, "已读"),    // -
        ;
        private Integer value;
        private String desc;
    }
}