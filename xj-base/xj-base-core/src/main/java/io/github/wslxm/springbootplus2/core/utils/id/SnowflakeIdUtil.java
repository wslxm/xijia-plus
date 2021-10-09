package io.github.wslxm.springbootplus2.core.utils.id;

/**
 * @description: 使用雪花算法生成全局id
 */
public class SnowflakeIdUtil {
    private static SnowflakeIdWorker idWorker;
    static {
        idWorker = new SnowflakeIdWorker(1, 1);
    }
    public static Long nextId() {
        return idWorker.nextId();
    }
}
