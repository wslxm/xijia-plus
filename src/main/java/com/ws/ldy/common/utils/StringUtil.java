package com.ws.ldy.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    /**
     * 判断字符串是否是一个isInteger 数字类型
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    /**
     * 判断字符串是否为手机号
     * 正则表达
     * 手机号码由11位数字组成，
     * 匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 19+任意数
     * 147
     */
    public static boolean isPhone(String str) {
        String regExp = "^(((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|19[0-9])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
