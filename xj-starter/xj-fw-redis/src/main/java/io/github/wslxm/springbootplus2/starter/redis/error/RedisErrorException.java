package io.github.wslxm.springbootplus2.starter.redis.error;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021/12/27 18:05
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
public class RedisErrorException extends RuntimeException {

    private Integer code;
    private String msg;


    /**
     * 直接传递
     *
     * @author wangsong
     * @date 2021/12/27 9:23
     * @version 1.0.0
     */
    public RedisErrorException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
