package com.ws.ldy.core.auth.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/***
 *  md5 工具类
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/5 0005 22:06 
 */
@Slf4j
public class MD5Util {

    /**
     * md5盐-加密盐值
     */
    private static final String SALT = "xijia";

    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        log.debug(MD5Util.encode("123456"));
    }
}