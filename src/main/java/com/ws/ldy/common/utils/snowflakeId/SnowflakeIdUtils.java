package com.ws.ldy.common.utils.snowflakeId;

/**
 * @description: 使用雪花算法生成全局id
 */
public class SnowflakeIdUtils {
    private static SnowflakeIdWorker idWorker;
    static {
        idWorker = new SnowflakeIdWorker(1, 1);
    }

    public static Long nextId() {
        return idWorker.nextId();
    }
}
