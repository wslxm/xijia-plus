package com.ws.ldy.common.utils;

import com.ws.ldy.common.utils.id.SnowflakeIdUtil;
import com.ws.ldy.common.utils.id.UUIDUtil;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

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

    /**
     * uuid
     * @return
     */
    private static final AtomicLong AUTO_INCREMENT = new AtomicLong(1);

    public static String ybCodeNo() {
        String id = Long.toString(Calendar.getInstance().getTime().getTime() + AUTO_INCREMENT.getAndIncrement()).substring(1);
        return "YB-" + id;
    }

    public static String generateNo() {
        return Long.toString(Calendar.getInstance().getTime().getTime() + AUTO_INCREMENT.getAndIncrement()).substring(1);
    }

}
