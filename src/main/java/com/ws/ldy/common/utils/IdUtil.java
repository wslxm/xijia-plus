package com.ws.ldy.common.utils;

import com.ws.ldy.common.utils.id.SnowflakeIdUtil;
import com.ws.ldy.common.utils.id.UUIDUtil;

/**
 * Id 生成工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/8/16 0016 20:28
 * @version 1.0.0
 */
public class IdUtil {

    /**
     * 雪花是否生成Id
     * @return
     */
    public static String snowflakeId() {
        return SnowflakeIdUtil.nextId().toString();
    }


    /**
     * uuid
     * @return
     */
    public static String uuid() {
        return UUIDUtil.creatUUID();
    }
}
