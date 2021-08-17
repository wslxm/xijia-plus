package com.ws.ldy.core.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("all")
public interface Xj{

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum BannerIsSkip implements IEnum<Integer> {
        V0(0, "否"),    // -
        V1(1, "内部跳转"),    // -
        V2(2, "外部跳转"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【动态值】
    @Getter
    @AllArgsConstructor
    enum BannerPosition implements IEnum<Integer> {
        V1(1, "用户端首页"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum BlacklistType implements IEnum<Integer> {
        V1(1, "白名单"),    // -
        V2(2, "黑名单"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【动态值】
    @Getter
    @AllArgsConstructor
    enum MsgType implements IEnum<Integer> {
        V0(0, "测试"),    // -
        V1(1, "系统通知"),    // -
        V2(2, "订单业务通知"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【动态值】
    @Getter
    @AllArgsConstructor
    enum MsgUserType implements IEnum<Integer> {
        V1(1, "用户端"),    // -
        V2(2, "管理端"),    // -
        ;
        private Integer value;
        private String desc;
    }
}