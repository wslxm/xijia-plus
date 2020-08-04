package com.ws.ldy.common.utils;

import java.util.regex.Pattern;

public class StringUtil {


    /**
     *  判断字符串是否是一个isInteger 数字类型
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
