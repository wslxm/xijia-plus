package com.ws.ldy.modules.third.aliyun.oss.util;


import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.ImgUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 上文件上传格式验证器，及文件后缀名 统一处理工具类
 * @author wangsong
 * @date 2020/12/11 0011 14:01
 * @return
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class FileUploadUtil {

    /**
     * 文件保存路径地址(前端传递判断  如： filePath=image/ 或 filePath=image/a/b/
     */
    private final static String PATH_IMAGE = "image";  //  oss/file/image  图片
    private final static String PATH_MUSIC = "music";  //  oss/file/music  音频
    private final static String PATH_VIDEO = "video";  //  oss/file/video  视频
    private final static String PATH_DOC = "doc";      //  oss/file/doc    文档(excel/pdf/word 等)
    private final static String PATH_FILE = "file";    //  oss/file/file   任意文件

    /**
     * 文件上传失败code
     */
    private final static Integer errorCode = 10002;

    /**
     * 图片最小体积开始压缩 (图片大于该值进行压缩操作) 1M * 1.5 = 1.5M  -->  只对大于1.5M的图片进行压缩
     */
    private final static double imgMinReduce = (1024 * 1024) * 1.5;


    /**
     * 文件格式验证
     */
    private static List<String> imageSuffix;           // 图片支持格式后缀
    private static List<String> musicSuffix;           // 音频支持格式后缀
    private static List<String> videoSuffix;           // 视频支持格式后缀
    private static List<String> docSuffix;             // 文档支持格式后缀(excel/pdf/word 等)
    private static List<String> excludeFileSuffix;     // 任意文件禁止上传的文件格式

    static {
        imageSuffix = Arrays.asList("bmp,jpg,png,jpeg,gif");
        musicSuffix = Arrays.asList("mp3");
        videoSuffix = Arrays.asList("mp4");
        docSuffix = Arrays.asList("xlsx,xls,pdf");
        excludeFileSuffix = Arrays.asList("jsp,js,java");
    }


    /**
     * 上传路径文件格式判断
     *
     * @param filePath 文件上传路径 (上方静态变量)
     * @param fileName 当前上传的文件名称
     * @return fileName
     */
    public static R<String> getPath(String filePath, String fileName) {
        if (filePath.lastIndexOf("/") != filePath.length() - 1) {
            return R.error(errorCode, "The path must end with [/]");
        }
        // 特殊符号处理
        try {
            fileName = URLEncoder.encode(fileName, "utf-8");
            fileName =   fileName.replaceAll("\\+", "%20").replaceAll("%", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 获取上传的跟目录(如：image)
        String path = filePath.split("/")[0];
        // 获取后缀(小写)
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
        if (PATH_IMAGE.equals(path)) {
            // 图片(重命名)
            R<String> stringR = formatVerification(imageSuffix, null, suffixName, fileName);
            return R.success(getTimeStr20() + "-" + fileName);
        } else if (PATH_MUSIC.equals(path)) {
            // 音频
            return formatVerification(musicSuffix, null, suffixName, fileName);
        } else if (PATH_VIDEO.equals(path)) {
            // 视频
            return formatVerification(videoSuffix, null, suffixName, fileName);
        } else if (PATH_DOC.equals(path)) {
            // 文档
            return formatVerification(docSuffix, null, suffixName, fileName);
        } else if (PATH_FILE.equals(path)) {
            // 任意文件，不做限制,推送文件禁止上传
            return formatVerification(null, excludeFileSuffix, suffixName, fileName);
        } else {
            // 日志错误
            return R.error(errorCode, "Path error");
        }
    }


    /**
     * 格式验证
     * suffixs    验证集(判断是否存在当前值)
     * suffixs    排除集(判断是否存在当前值)
     * suffixName 当前值
     */
    public static R<String> formatVerification(List<String> suffixs, List<String> excludeFileSuffix, String suffixName, String fileName) {
        // 验证格式是否在范围内
        if (!suffixs.contains(suffixName)) {
            return R.error(errorCode, "格式错误,仅支持:" + suffixs.toString());
        }
        // 验证格式是否禁止上传
        if (excludeFileSuffix.contains(suffixName)) {
            return R.error(errorCode, "禁止上传文件格式:" + excludeFileSuffix.toString());
        }
        return R.success(fileName);
    }


    /**
     * 判断是否为图片，是图片进行压缩后返回
     * @param filePath
     * @param isReduce
     * @param inputStream
     * @return
     */
    public static InputStream imgReduce(String filePath, Boolean isReduce, InputStream inputStream) {
        // 目录开头
        String path = filePath.split("/")[0];
        if (PATH_IMAGE.equals(path)) {
            // true压缩(默认null压缩), false不压缩
            if (isReduce == null || isReduce) {
                // 图片 ,获取文件大小(字节) 1024字节=1kb  1024000=1MB
                int count = 0;
                try {
                    while (count == 0) {
                        count = inputStream.available();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 小于n 值不压缩
                if (count < imgMinReduce) {
                    return inputStream;
                }
                // 图片压缩
                byte[] bytes = ImgUtils.inputstreamToByte(inputStream);
                bytes = ImgUtils.compressPic(bytes);
                inputStream = ImgUtils.byteToInputStream(bytes);
            }
        }
        return inputStream;
    }


    /**
     * 获取随机串
     * @return
     */
    private static String getTimeStr20() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        timeStamp += (random.nextInt(10) + "") + (random.nextInt(10) + "") + (random.nextInt(10) + "");
        return timeStamp;
    }
}
