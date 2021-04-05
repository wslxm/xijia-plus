package com.ws.ldy.common.utils;

import com.ws.ldy.common.utils.id.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Id 生成工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/8/16 0016 20:28
 * @version 1.0.0
 */
@Slf4j
public class IdUtil {

    /**
     * 随机数对象
     */
    private static final Random RANDOM = new Random();

    /**
     * 自增长对象
     */
    private static AtomicLong autoIncrement = new AtomicLong(1);


    /**
     * 1、雪花算法 Id （可作用于id生成, 不会重复）
     */
    public static String snowflakeId() {
        return SnowflakeIdUtil.nextId().toString();
    }


    /**
     * 2、uuId
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 3、获取 指定前缀+时间戳+自增涨Id (可作用于用户编号生成, 自增加只能同一个示列使用, 多个示列使用无法保存有序值，请定义多个 new AtomicLong(1))
     */
    public static String timestampSelfIncreasingId(String prefix) {
        String id = Long.toString(Calendar.getInstance().getTime().getTime() + autoIncrement.getAndIncrement()).substring(1);
        return prefix + id;
    }

    /**
     * 4、获取 时间戳+自增涨Id (可作用于用户编号生成, 自增加只能同一个示列使用, 多个示列使用无法保存有序值，请定义多个 new AtomicLong(1))
     */
    public static String timestampSelfIncreasingId() {
        return Long.toString(Calendar.getInstance().getTime().getTime())
                + autoIncrement.getAndIncrement();
    }


    /**
     * 5、获取 14位时间戳 + 6位随机数（可作用于订单号）
     * synchronized, 单服务运行下时间戳永不重复
     */
    public synchronized static String timestampRandom() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuilder randomStr = new StringBuilder("");
        int len = 6;
        for (int i = 0; i < len; i++) {
            randomStr.append(RANDOM.nextInt(10));
        }
        return format.format(new Date()) + randomStr;
    }


    public static void main(String[] args) {
        // 自增id
        for (int i = 0; i < 100; i++) {
            log.debug(timestampSelfIncreasingId());
        }
        // 14位时间戳 + 6位随机数
        log.debug(timestampRandom());

    }
}
