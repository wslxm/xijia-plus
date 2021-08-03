package com.ws.ldy.config.error;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  //无参构造
public class RedisErrorException extends RuntimeException {

    private Integer code;
    private String msg;


    //直接传递
    public RedisErrorException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
