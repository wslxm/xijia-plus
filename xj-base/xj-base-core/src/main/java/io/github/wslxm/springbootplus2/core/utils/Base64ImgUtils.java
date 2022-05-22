package io.github.wslxm.springbootplus2.core.utils;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author wangsong
 */
@Slf4j
public class Base64ImgUtils {

//
//    /**
//     * 图片转base64字符串
//     *
//     * @param imgFile 图片路径
//     * @return
//     */
//    public static String imageToBase64Str(String imgFile) {
//        byte[] buffer = null;
//        try (InputStream is = new FileInputStream(imgFile);) {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            int len = 0;
//            byte[] b = new byte[1024];
//            while ((len = is.read(b, 0, b.length)) != -1) {
//                baos.write(b, 0, len);
//            }
//            buffer = baos.toByteArray();
//        } catch (IOException e) {
//            log.error(e.toString());
//        }
//        // 加密
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(buffer);
//    }
//
//
//    /**
//     * base64编码字符串转换为图片,并写入文件
//     *
//     * @param imgStr base64编码字符串
//     * @param path   图片路径
//     * @return
//     */
//    public static boolean base64StrToImage(String imgStr, String path) {
//        if (imgStr == null) {
//            return false;
//        }
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] b = null;
//        try {
//            // 解密
//            b = decoder.decodeBuffer(imgStr);
//        } catch (Exception e) {
//            return false;
//        }
//        // 处理数据
//        for (int i = 0; i < b.length; ++i) {
//            if (b[i] < 0) {
//                b[i] += 256;
//            }
//        }
//        //文件夹不存在则自动创建
//        File tempFile = new File(path);
//        if (!tempFile.getParentFile().exists()) {
//            tempFile.getParentFile().mkdirs();
//        }
//        try (OutputStream out = new FileOutputStream(tempFile)) {
//            out.write(b);
//            out.flush();
//        } catch (IOException e) {
//            log.error(e.toString());
//        }
//        return true;
//    }


    /**
     * file 转base64
     * @author wangsong
     * @param file
     * @date 2021/3/3 0003 15:00
     * @return java.lang.String
     * @version 1.0.1
     */
    public static String file2Base64(File file) {
        if (file == null) {
            return null;
        }
        String base64 = null;
        try (FileInputStream is = new FileInputStream(file)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = is.read(b, 0, b.length)) != -1) {
                baos.write(b, 0, len);
            }
            byte[] buffer = baos.toByteArray();
            base64 = Base64.encode(buffer);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return base64;
    }


    /**
     * base 64 转 file
     * @author wangsong
     * @param base64
     * @date 2021/3/3 0003 15:00
     * @return java.io.File
     * @version 1.0.1
     */
    public static File base64ToFile(String base64) {
        if (base64 == null || "".equals(base64)) {
            return null;
        }
        byte[] buff = Base64.decode(base64);
        File file = null;
        try {
            file = File.createTempFile("tmp", null);
        } catch (IOException e) {
            log.error(e.toString());
        }
        try (FileOutputStream fout = new FileOutputStream(file)) {
            fout.write(buff);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return file;
    }
}
