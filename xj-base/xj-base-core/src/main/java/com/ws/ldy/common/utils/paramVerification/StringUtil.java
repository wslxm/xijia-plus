package com.ws.ldy.common.utils.paramVerification;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {


    /**
     * 是否为数字验证
     */
    static final Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    /**
     * 判断字符串是否是一个isInteger 数字类型
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        return pattern.matcher(str).matches();
    }
}
