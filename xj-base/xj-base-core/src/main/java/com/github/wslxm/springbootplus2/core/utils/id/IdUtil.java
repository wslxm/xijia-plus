package com.github.wslxm.springbootplus2.core.utils.id;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
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
     * 1、雪花算法 Id （可作用于id生成, 不会重复）
     */
    public static String snowflakeId() {
        return SnowflakeIdUtil.nextId().toString();
    }


    /**
     * 2、获取 14位时间戳 +  6位自增长值-如果位数不到,使用0代替（000001）
     * <p>
     *    可作用于订单号, 退款号生成等
     *    synchronized, 单服务运行下时间戳+自增值 永不重复， 如果为集群建议使用redis 自增api [redisTemplate.opsForValue().increment(newKey, delta)]
     *    increment >= 999999, 一但自增值大于999999, 重置为0, 表示每秒支持生成999999 个号，一但记录到 999999 或服务器重置时, 重置为0重新开始递增
     * </p>
     *
     */
    private static AtomicLong noIncrement = new AtomicLong(1);

    public synchronized static String getNo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        long increment = noIncrement.getAndIncrement();
        if(increment >= 999999){
            noIncrement.set(1);
        }
        return format.format(new Date()) + String.format("%06d",increment);
    }



    public static void main(String[] args) {
        // 自增id
        for (int i = 0; i < 90000; i++) {
            // log.debug(timestampSelfIncreasingId());
            log.debug(getNo());
        }
    }
}
