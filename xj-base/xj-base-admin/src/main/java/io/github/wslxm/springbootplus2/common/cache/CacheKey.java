package io.github.wslxm.springbootplus2.common.cache;

/**
 * 系统缓存key
 *
 * <P>
 *     系统中已存在的相关缓存
 * </P>
 *
 * @author wangsong
 * @version 1.0.1
 * @mail 1720696548@qq.com
 * @date 2021/7/30 0030 18:18
 */
public interface CacheKey {

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
    String CONFIG_BY_CODE = "CONFIG_BY_CODE";

    /**
     * 系统黑/白名单(根据 type 缓存)
     */
    String BLACK_LIST_BY_TYPE = "BLACK_LIST_BY_TYPE";

    /**
     * benner  （根据位置缓存)
     */
    String BENNER_BY_POSITION = "BENNER_BY_POSITION";

}

