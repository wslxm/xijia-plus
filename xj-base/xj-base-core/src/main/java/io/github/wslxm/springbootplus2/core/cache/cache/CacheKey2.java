package io.github.wslxm.springbootplus2.core.cache.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 缓存key
 *
 * @author wangsong
 * @version 1.0.1
 * @mail 1720696548@qq.com
 * @date 2021/7/30 0030 18:18
 */
public interface CacheKey2 {
	//    // 字典
//    DICT_LIST_KEY("dict-list-key"),
//    // 接口权限
//    AUTH_MAP_KEY("auth-map-key"),
//    // 全局配置
//	CONFIG_MAP_KEY("config-map-key"),
//    // 登录人信息
//    LOGIN_ADMIN_USER("login-admin-user"),

	/**
	 * 接口权限数据
	 */
	String AUTH_MAP_ALL = "AUTH_MAP_ALL";
	/**
	 * 字典 ALL
	 */
	String DICT_LIST_ALL = "DICT_LIST_ALL";

	/**
	 * 全局配置 (根据 code 缓存)
	 */
	String CONFIG_BY_CODE = "CONFIG_BY_CODE";
	/**
	 * 系统黑/白名单(根据 type 缓存)
	 */
	String BLACK_LIST_BY_TYPE = "BLACK_LIST_BY_TYPE";

}

