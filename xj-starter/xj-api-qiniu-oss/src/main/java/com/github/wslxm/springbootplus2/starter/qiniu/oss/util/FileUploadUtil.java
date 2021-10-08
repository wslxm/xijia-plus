package com.github.wslxm.springbootplus2.starter.qiniu.oss.util;


import com.github.wslxm.springbootplus2.starter.qiniu.oss.result.QiNiuRType;
import com.github.wslxm.springbootplus2.core.result.R;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 上文件上传格式验证器，及文件后缀名 统一处理工具类
 * @author wangsong
 * @date 2020/12/11 0011 14:01
 * @return
 * @version 1.0.0
 */
public class FileUploadUtil {


    // 文件保存路径地址
    private final static String UPLOAD_PATH_IMAGE = "image";  //  oss/file/image  图片
    private final static String UPLOAD_PATH_MUSIC = "music";  //  oss/file/music  音频
    private final static String UPLOAD_PATH_VIDEO = "video";  //  oss/file/video  视频
    private final static String UPLOAD_PATH_EXCEL = "excel";  //  oss/file/excel  表格
    private final static String UPLOAD_PATH_PDF = "pdf";      //  oss/file/pdf    pdf文件
    private final static String UPLOAD_PATH_FILE = "file";    //  oss/file/file   任意文件

    /**
     * 上传路径文件格式判断
     *
     * @param filePath 文件上传路径 (上方静态变量)
     * @param fileName 当前市场的文件名称
     * @return fileName
     */
    public static R<String> getPath(String filePath, String fileName) {
        if (filePath.lastIndexOf("/") != filePath.length() - 1) {
            R.error(QiNiuRType.FILE_UPLOAD_FAILED.getValue(), "路径必须已[/]结尾");
        }
        // 目录开头
        String path = filePath.split("/")[0];
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (UPLOAD_PATH_IMAGE.equals(path)) {
            /**
             * 图片 （ 图片重命名- (17位时间+3位随机数+原文件名称）-- 生成20位前缀
             */
            if (!"jpg".equals(suffixName) && !"png".equals(suffixName) && !"jpeg".equals(suffixName) && !"gif".equals(suffixName)) {
                R.error(QiNiuRType.FILE_UPLOAD_FAILED.getValue(), "图片仅支持上传-[jpg,png,jpeg,jif]");
            }
            fileName = getTimeStr20() + "-" + fileName;
        } else if (UPLOAD_PATH_MUSIC.equals(path)) {
            /**
             * 音频
             */
            if (!"mp3".equals(suffixName)) {
                R.error(QiNiuRType.FILE_UPLOAD_FAILED.getValue(), "音乐仅支持上传-[mp3]");
            }
        } else if (UPLOAD_PATH_VIDEO.equals(path)) {
            /**
             * 视频
             */
            if (!"mp4".equals(suffixName)) {
                R.error(QiNiuRType.FILE_UPLOAD_FAILED.getValue(), "视频仅支持上传-[mp4]");
            }
        } else if (UPLOAD_PATH_EXCEL.equals(path)) {
            /**
             * excel
             */
            if (!"xlsx".equals(suffixName) && !"xls".equals(suffixName)) {
                R.error(QiNiuRType.FILE_UPLOAD_FAILED.getValue(), "EXCEL仅支持上传-[xlxs,xlx]");
            }
        } else if (UPLOAD_PATH_PDF.equals(path)) {
            /**
             * pdf
             */
            if (!"pdf".equals(suffixName)) {
                R.error(QiNiuRType.FILE_UPLOAD_FAILED.getValue(), "PDF仅支持上传-[pdf]");
            }
        } else if (UPLOAD_PATH_FILE.equals(path)) {
            /**
             * 任意文件，不做限制
             */
        } else {
            R.error(QiNiuRType.FILE_UPLOAD_FAILED.getValue(), "路径错误");
        }
        // 空格+逗号+括号+一些特殊符号 统一转为_,部分地方无法解析，如逗号分隔url, editor 编辑器, URL请求空格问题
        fileName = fileName
                .replaceAll(" ", "_")
                .replace(",", "_")
                .replaceAll("\\(", "_")
                .replaceAll("\\)", "_")
                .replaceAll("#", "_")
                .replaceAll("@", "_");
        return R.success(fileName);
    }


    /**
     * 判断是否为图片，是图片进行压缩后返回
     * @param filePath
     * @param isReduce
     * @param inputStream
     * @return
     */
//    public static InputStream imgReduce(String filePath, Boolean isReduce, InputStream inputStream) {
//        // 目录开头
//        String path = filePath.split("/")[0];
//        if (UPLOAD_PATH_IMAGE.equals(path)) {
//            // 是图片，没有传递默认压缩, true压缩，false不压缩
//            if (isReduce == null || isReduce) {
//                // 图片压缩
//                byte[] bytes = ImgUtils.inputstreamToByte(inputStream);
//                bytes = ImgUtils.compressPic(bytes);
//                inputStream = ImgUtils.byteToInputStream(bytes);
//            }
//        }
//        return inputStream;
//    }


    /**
     * 获取随机串
     * @return
     */
    @SneakyThrows
    private static String getTimeStr20() {
        Thread.sleep(1);
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        timeStamp += (random.nextInt(10) + "") + (random.nextInt(10) + "") + (random.nextInt(10) + "");
        return timeStamp;
    }
}
