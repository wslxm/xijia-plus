package io.github.wslxm.springbootplus2.common.auth.util;

import io.github.wslxm.springbootplus2.core.constant.NumberConst;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/***
 * md5 密码加密工具类
 * @author 王松
 * @email 1720696548@qq.com
 * @date 2020/7/5 0005 22:06 
 */
@Slf4j
public class Md5Util {

    /**
     * md5盐-加密盐值
     */
    private static final String FIXED_SALT = "xijia";


    /**
     * md5 加密 加盐
     * <p>
     * 加密规则： 固定盐 + 密码 +  3次动态盐  (动态盐 = 用户id(建议)  或 用户姓名  或 多种组合)
     * </P>
     *
     * @param password 密码
     * @param salt     盐值
     * @return 加密后的密码
     */
    public static String encode(String password, String salt) {
        // 对密码进行加盐 start ---
        StringBuilder sb = new StringBuilder(FIXED_SALT);
        sb.append(password);
        for (int i = 0; i < NumberConst.THREE; i++) {
            sb.append(salt);
        }
        password = sb.toString();
        // 对密码进行加盐 end  ---

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
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
        // 参数1= 密码   参数2= 用户id
        log.info(Md5Util.encode("$5M!xOdNq1AM49$x", "1"));
    }
}