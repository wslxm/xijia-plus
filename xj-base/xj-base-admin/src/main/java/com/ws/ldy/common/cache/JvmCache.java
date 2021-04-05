package com.ws.ldy.common.cache;

import com.ws.ldy.modules.sys.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统jvm 缓存
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/3/15 0015 9:08
 * @version 1.0.0
 */
public class JvmCache {

    /**
     * 数据字典(code分组), 不包括禁用数据，等同于 Enums.java 文件的数据
     */
    private static List<AdminDictionaryVO> dictList = new ArrayList<>();

    /**
     * 当前系统的所有权限接口数据（key = uri ),用于登录验证用户接口权限避免去数据库查询接口数据
     */
    private static Map<String, AdminAuthority> authMap = new HashMap<>();


    //============================================================================
    //=========================== get/set/del=====================================
    //============================================================================

    public static List<AdminDictionaryVO> getDictList() {
        return dictList;
    }

    public static void setDictList(List<AdminDictionaryVO> list) {
        dictList = list;
    }

    public static void delDictList() {
        dictList = new ArrayList<>();
    }

    //
    public static Map<String, AdminAuthority> getAuthMap() {
        return authMap;
    }

    public static void setAuthMap(Map<String, AdminAuthority> map) {
        authMap = map;
    }

    public static void delAuthMap() {
        authMap = new HashMap<>();
    }

    /**
     * 清空使用jvm缓存
     */
    public static void delAll() {
        delDictList();
        delAuthMap();
    }
}
