package com.ws.ldy.common.cache;

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
    DICT_LIST_KEY("dict-list-key"),
    AUTH_MAP_KEY("auth-map-key"),
    CONFIG_MAP_KEY("config-map-key"),
    ;
    private String key;
}

