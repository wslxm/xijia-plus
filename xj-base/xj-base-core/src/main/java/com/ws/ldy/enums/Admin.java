package com.ws.ldy.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Admin{

    // 动态编辑权限状态
    @Getter
    @AllArgsConstructor
    enum AuthorityState implements IEnum<Integer> {
        //
        V0(0, "无"),    // -
        V1(1, "需登录"),    // -
        V2(2, "需登录+授权"),    // -
        V3(3, "需Oauth2 授权"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 默认类型, 来进行默认权限指定
    @Getter
    @AllArgsConstructor
    enum AuthorityType implements IEnum<Integer> {
        //
        V0(0, "管理端"),    // 管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]
        V1(1, "用户端"),    // 用户端, 类上标有该参数所有接口都会默认被列为-[需登录]
        V2(2, "通用接口"),    // 通用接口, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]
        V3(3, "Oauth2 接口"),    // 接口默认默认需通过 appId，appSecret生成的 accessToken 来进行oauth2 令牌验证可访问
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum MenuRoot implements IEnum<Integer> {
        //
        V1(1, "顶部菜单"),    // -
        V2(2, "菜单"),    // -
        V3(3, "页面"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum Position implements IEnum<Integer> {
        //
        V0(0, "系统管理"),    // -
        V1(1, "平台用户"),    // -
        ;
        private Integer value;
        private String desc;
    }
}