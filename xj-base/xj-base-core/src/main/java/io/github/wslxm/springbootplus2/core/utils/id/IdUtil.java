package io.github.wslxm.springbootplus2.core.utils.id;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Id 生成工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/8/16 0016 20:28
 * @version 1.0.1
 */
@Slf4j
public class IdUtil {
    /**
     * 1、雪花算法 Id （可作用于id生成, 不会重复）
     */
    public static String snowflakeId() {
        return SnowflakeIdUtil.nextId().toString();
    }

}
