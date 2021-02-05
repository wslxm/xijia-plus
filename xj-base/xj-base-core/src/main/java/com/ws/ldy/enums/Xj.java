package com.ws.ldy.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("all")
public interface Xj{

    // -
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

    // -
    @Getter
    @AllArgsConstructor
    enum BannerPosition implements IEnum<Integer> {
        V1(1, "用户端首页"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum BlacklistType implements IEnum<Integer> {
        V1(1, "白名单"),    // -
        V2(2, "黑名单"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum FileType implements IEnum<Integer> {
        V1(1, "开发工具"),    // -
        V2(2, "源码"),    // -
        V3(3, "文档"),    // -pdf /word 等
        V4(4, "图片"),    // -
        V5(5, "音频"),    // -
        V6(6, "视频"),    // -
        V7(7, "sql"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum HelpCategory implements IEnum<Integer> {
        V0(0, "开始使用"),    // -
        V1(1, "采用技术"),    // -
        V2(2, "系统功能"),    // -
        V3(3, "引入技术"),    // -
        V4(4, "运维部署"),    // -
        V5(5, "常用技术文章"),    // -
        V6(6, "其他"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum HelpVersion implements IEnum<Integer> {
        V0(0, "0.x "),    // -
        V1(1, "1.x"),    // -
        V2(2, "1.5x "),    // -
        V3(3, "2.x"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum MsgType implements IEnum<Integer> {
        V1(1, "系统通知"),    // -
        V2(2, "订单业务通知"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
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