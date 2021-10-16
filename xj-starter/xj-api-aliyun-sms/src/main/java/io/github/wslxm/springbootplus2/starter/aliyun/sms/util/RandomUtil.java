package io.github.wslxm.springbootplus2.starter.aliyun.sms.util;

import java.util.Random;

/**
 * 随机数工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/22 0022 18:47
 * @version 1.0.1
 */
public class RandomUtil {

    private static Random x = new Random();

    /**
     * 获取指定位数的随机验证码
     * @author wangsong

     * @param len
     * @date 2020/9/22 0022 18:50
     * @return java.lang.String
     * @version 1.0.1
     */
    public static String code(Integer len) {
        String code = "";
        for (int i = 0; i < len; i++) {
            code += x.nextInt(10);
        }
        return code;
    }
}
