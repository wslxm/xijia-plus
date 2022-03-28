package io.github.wslxm.springbootplus2.cache;

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

	/**
	 * 登录人接口权限 (根据 用户ID 缓存)
	 */
	String LOGIN_AUTH_USER_ID = "LOGIN_AUTH_USER_ID";
	/**
	 * 接口权限数据 (全部)
	 */
	String AUTH_MAP_ALL = "AUTH_MAP_ALL";
	/**
	 * 字典 ALL (全部)
	 */
	String DICT_LIST_ALL = "DICT_LIST_ALL";

	/**
	 * 全局配置 （根据code 缓存)
	 */
	String CONFIG_LIST_BY_CODE = "CONFIG_LIST_BY_CODE";

	/**
	 * 系统黑/白名单(根据 type 缓存)
	 */
	String BLACK_LIST_BY_TYPE = "BLACK_LIST_BY_TYPE";

}

