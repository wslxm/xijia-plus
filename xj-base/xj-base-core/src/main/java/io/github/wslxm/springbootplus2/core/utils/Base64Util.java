package io.github.wslxm.springbootplus2.core.utils;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 核心参数加密工具类 (给 SysEncrypt 敏感参数加密/解密使用)
 * @author wangsong
 * @date 2021/3/29 0029 9:32
 * @return
 * @version 1.0.1
 */
@Slf4j
public class Base64Util {

    /**
     * 加密
     */
    public static String encode(String s1) {
        return Base64.encode(s1);
    }

    /**
     * 解密
     */
    public static String decrypt(String s1) {
        if (Objects.equals(s1, "")) {
            return s1;
        }
        return Base64.decodeStr(s1);
    }

    /**
     *  //   IUAjJCVeJiooKQ==  | !@#$%^&*()
     *  //   MTIzNDU2          | 123456
     * @param args
     */
    public static void main(String[] args) {
        String pwd = encode("123456");
        String decrypt = decrypt(pwd);
        log.info("key:" + pwd);
        log.info("val:" + decrypt);
    }
}
