package com.ws.ldy.common.utils;

/**
 * TODO  获取默认值
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/6/20 0020 16:25
 */
public class DefaultsUtils {

    /**
     * Todo 获取 Integer 的默认值
     *
     * @param value
     * @param defaults
     * @return
     */
    public static Integer castToInt(Object value, Integer defaults) {
        if (value == null) {
            return defaults;
        } else {
            return Integer.parseInt(value.toString());
        }
    }


    /**
     * Todo 获取 String 的默认值
     *
     * @param value
     * @param defaults
     * @return
     */
    public static String castToString(Object value, String defaults) {
        if (value == null) {
            return defaults;
        } else {
            return String.valueOf(value.toString());
        }
    }


    /**
     * Todo 获取小数的默认值
     *
     * @param value
     * @param defaults
     * @return
     */
    public static double castToDouble(Object value, double defaults) {
        if (value == null) {
            return defaults;
        } else {
            return Double.parseDouble(value.toString());
        }
    }
}
