package io.github.wslxm.springbootplus2.core.utils.paramverification;

import java.util.regex.Pattern;

/**
 *  @author wangsong
 * 字符串工具类
 */
public class StringUtil {


    /**
     * 是否为数字验证
     */
    static final Pattern PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    /**
     * 判断字符串是否是一个isInteger 数字类型
     * @param str
     * @return true if 是
     */
    public static boolean isInteger(String str) {
        return PATTERN.matcher(str).matches();
    }
}
