package com.ws.ldy.common.utils;

import com.ws.ldy.common.utils.id.SnowflakeIdUtil;

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
public class IdUtil {

    /**
     * 时间格式
     */
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    private static Random random = new Random();

    /**
     * 子增长Id
     */
    private static AtomicLong AUTO_INCREMENT = new AtomicLong(1);


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
     * 3、获取 指定前缀+时间戳+自增涨Id (可作用于用户编号生成, 自增加只能同一个示列使用, 多个示列使用无法保存有序，请定义多个 new AtomicLong(1))
     */
    public synchronized static String timestampSelfIncreasingId(String prefix) {
        String id = Long.toString(Calendar.getInstance().getTime().getTime() + AUTO_INCREMENT.getAndIncrement()).substring(1);
        return prefix + id;
    }

    /**
     * 4、获取 时间戳+自增涨Id (可作用于用户编号生成, 自增加只能同一个示列使用, 多个示列使用无法保存有序，请定义多个 new AtomicLong(1))
     */
    public synchronized static String timestampSelfIncreasingId() {
        return Long.toString(Calendar.getInstance().getTime().getTime() + AUTO_INCREMENT.getAndIncrement()).substring(1);
    }


    /**
     * 5、获取 14位时间戳 + 6位随机数（可作用于订单号）
     */
    public synchronized static String timestampRandom() {
        String randomStr = "";
        int len = 6;
        for (int i = 0; i < len; i++) {
            randomStr += random.nextInt(10) + "";
        }
        return format.format(new Date()) + randomStr;
    }


    public static void main(String[] args) {
        System.out.println(timestampRandom());
    }
}
