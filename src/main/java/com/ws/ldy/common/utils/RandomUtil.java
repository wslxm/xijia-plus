package com.ws.ldy.common.utils;

import java.util.Random;

/**
 * 随机数工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/22 0022 18:47
 * @version 1.0.0
 */
public class RandomUtil {

    /**
     * 获取指定位数的随机验证码
     * @author wangsong
    
 * @param digits
     * @date 2020/9/22 0022 18:50
     * @return java.lang.String
     * @version 1.0.0
     */
    public static String code(Integer digits) {
        String code = "";
        Random x = new Random();
        for (int i = 0; i < digits; i++) {
            code +=  x.nextInt(10);
        }
        return code;
    }
}
