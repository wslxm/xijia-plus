package com.ws.ldy.common.cache;

import com.ws.ldy.modules.sys.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;

import java.util.HashMap;
import java.util.Map;


/**
 * 系统的缓存数据(所有通用)
 */
public class BaseCache {
    /**
     * 当前系统的所有权限接口数据（key = uri ）
     * 权限版本号：version，当版本号一致时, 用户调用任何接口都将刷新Token, 重而热更新用户的访问权限
     */
    public static Map<String, AdminAuthority> AUTH_MAP = new HashMap<>();
    public static Integer AUTH_VERSION = 0;

    /**
     * 数据字典(code分组), 不包括禁用数据，等同于 Enums.java 文件的数据
     * 版本号：version，当版本号一致时, 不返回前台 dictVOGroupMap 数据
     */
    public static Map<String, AdminDictionaryVO.FindCodeGroup> DICT_MAP_GROUP = null;

}