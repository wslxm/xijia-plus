package io.github.wslxm.springbootplus2.config.filter.sing.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加签 md5工具类 (加密为32位小写)
 *
 * @author wangsong
 * @version 1.0.1
 * @date 2021/4/1 0001 21:02
 * @return
 */
public class Md5SignUtil {

    /**
     * md5加密 32位 小写
     *
     * @param plainText
     * @return
     */
    public static String md5(String plainText) {
        String reMd5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            reMd5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reMd5;
    }
}

