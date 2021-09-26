package com.ws.ldy.core.cache.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 缓存key
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/7/30 0030 18:18 
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CacheKey {
    //
    DICT_LIST_KEY("dict-list-key"),       // 字典
    AUTH_MAP_KEY("auth-map-key"),         // 接口权限
    CONFIG_MAP_KEY("config-map-key"),     // 全局配置
    LOGIN_ADMIN_USER("login-admin-user"), // 登录人信息
    BLACK_LIST("black-list"),             // 系统黑/白名单
    ;
    private String key;
}

