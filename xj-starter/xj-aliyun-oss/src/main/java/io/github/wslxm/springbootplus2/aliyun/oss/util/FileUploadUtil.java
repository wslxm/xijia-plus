package io.github.wslxm.springbootplus2.aliyun.oss.util;


import io.github.wslxm.springbootplus2.aliyun.oss.config.error.AliYunOssErrorException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
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
 * @version 1.0.1
 */
@SuppressWarnings("all")
@Slf4j
public class FileUploadUtil {

    /**
     * 文件保存路径地址(前端传递判断  如： filePath=image/ 或 filePath=image/a/b/
     */
    private final static String PATH_IMAGE = "image";  //  oss/file/image  图片
    private final static String PATH_MUSIC = "music";  //  oss/file/music  音频
    private final static String PATH_VIDEO = "video";  //  oss/file/video  视频
    private final static String PATH_DOC = "doc";      //  oss/file/doc    文档(excel/pdf/word 等)
    private final static String PATH_EXCEL = "excel";  //  oss/file/excel  文档(excel/pdf/word 等)
    private final static String PATH_FILE = "file";    //  oss/file/file   任意文件

    /**
     * 图片最小体积开始压缩 (图片大于该值进行压缩操作) 1M * 1.5 = 0.5M  -->  只对大于0.5M的图片进行压缩
     */
    private final static double imgMinReduce = (1024 * 1024) * 0.5;


    /**
     * 文件格式验证
     */
    private static List<String> imageSuffix;           // 图片支持格式后缀
    private static List<String> musicSuffix;           // 音频支持格式后缀
    private static List<String> videoSuffix;           // 视频支持格式后缀
    private static List<String> docSuffix;             // 文档支持格式后缀(excel/pdf/word 等)
    private static List<String> excelSuffix;           // 文档支持格式后缀(excel/pdf/word 等)
    private static List<String> excludeFileSuffix;     // 任意文件禁止上传的文件格式

    static {
        // 浏览器 主要图像文件支持
        imageSuffix = Arrays.asList(("bmp,jpg,png,jpeg,gif").split(","));
        musicSuffix = Arrays.asList(("mp3").split(","));
        videoSuffix = Arrays.asList(("mp4").split(","));
        docSuffix = Arrays.asList(("pdf,rtf,doc,docx").split(","));
        excelSuffix = Arrays.asList(("xlsx,xls").split(","));
        excludeFileSuffix = Arrays.asList(("jsp,js,java").split(","));
    }


    /**
     * 上传路径文件格式判断
     *
     * @param filePath 文件上传路径 (上方静态变量)
     * @param fileName 当前上传的文件名称
     * @return fileName
     */
    public static String getPath(String filePath, String fileName) {
        if (filePath.lastIndexOf("/") != filePath.length() - 1) {
            throw new AliYunOssErrorException("路径必须以 [/] 结束");
        }

        // 文件名中对url中不安全的字符处理
        fileName = fileName.replaceAll("\\+", "")
                .replaceAll(" ", "")
                .replaceAll("/", "")
                .replaceAll("\\?", "")
                .replaceAll("%", "")
                .replaceAll("#", "")
                .replaceAll("&", "")
                .replaceAll("=", "");

        // 获取上传的跟目录(如：image)
        String path = filePath.split("/")[0];
        // 获取后缀(小写)
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();

        // 文件格式验证, 以及处理文件名, 让其可以传递重复文件
        if (PATH_IMAGE.equals(path)) {
            // 图片
            return formatVerification(imageSuffix, null, suffixName, fileName);
        } else if (PATH_MUSIC.equals(path)) {
            // 音频
            return formatVerification(musicSuffix, null, suffixName, fileName);
        } else if (PATH_VIDEO.equals(path)) {
            // 视频
            return formatVerification(videoSuffix, null, suffixName, fileName);
        } else if (PATH_DOC.equals(path)) {
            // 文档
            return formatVerification(docSuffix, null, suffixName, fileName);
        } else if (PATH_EXCEL.equals(path)) {
            // EXCEL
            return formatVerification(excelSuffix, null, suffixName, fileName);
        } else if (PATH_FILE.equals(path)) {
            // 任意文件，不做限制, 推送文件禁止上传
            return formatVerification(null, excludeFileSuffix, suffixName, fileName);
        } else {
            // 不支持的路径
            throw new AliYunOssErrorException("filePath参数错误,不支持的保存路径");
        }
    }


    /**
     * 格式验证
     * suffixs    验证集(判断是否存在当前值)
     * suffixs    排除集(判断是否存在当前值)
     * suffixName 当前值
     */
    private static String formatVerification(List<String> suffixs, List<String> excludeFileSuffix, String suffixName, String fileName) {
        // 验证格式是否在范围内
        if (suffixs != null && !suffixs.contains(suffixName)) {
            throw new AliYunOssErrorException("格式错误,仅支持:" + suffixs.toString() + ", 当前格式为：" + suffixName);
        }
        // 验证格式是否禁止上传
        if (excludeFileSuffix != null && excludeFileSuffix.contains(suffixName)) {
            throw new AliYunOssErrorException("禁止上传文件格式:" + excludeFileSuffix.toString());
        }
        return getTimeStr20() + "-" + fileName;
    }


    /**
     * 获取随机串（时间-- 2位秒+3位毫秒+3位随机数 = 8位随机串）
     *
     * @return
     */
    private static String getTimeStr20() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        String timeStamp = new SimpleDateFormat("ssSSS").format(new Date());
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            timeStamp += (random.nextInt(10) + "");
        }
        return timeStamp;
    }


    /**
     * 判断是否为图片，是图片进行压缩后返回
     * @param filePath
     * @param reduceSize 期望压缩后大小
     * @param inputStream
     * @return
     */
    public static InputStream imgReduce(String filePath, Boolean isReduce, InputStream inputStream) {
        // 目录开头
        String path = filePath.split("/")[0];
        if (PATH_IMAGE.equals(path)) {
            // 设置期望压缩后大小，默认500内, 如果期望大小小于500，则强制 50 内
            isReduce = isReduce == null ? true : isReduce;
            if (isReduce) {
                // 获取文件大小(字节) 1024字节=1kb  1024000=1MB
                Integer imgSize = getImgSize(inputStream);
                // 上次压缩大小
                Integer oldImgSize = imgSize;
                // 压缩比例
                float qality = (float) 0.3;
                // 图片压缩
                byte[] bytes = ImgUtils.inputstreamToByte(inputStream);
                bytes = ImgUtils.compressPic(bytes, qality);
                inputStream = ImgUtils.byteToInputStream(bytes);
            }
        }
        return inputStream;
    }


    /**
     * 获取图片大小
     */
    private static Integer getImgSize(InputStream inputStream) {
        int count = 0;
        try {
            while (count == 0) {
                count = inputStream.available();
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
        return count;
    }
}
