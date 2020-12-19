package com.ws.ldy.others.aliyun.oss.util;

import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.config.error.ErrorException;

/**
 * 上传文件后缀名处理工具类
 * @author wangsong
 * @date 2020/12/11 0011 14:01
 * @return
 * @version 1.0.0
 */
public class AliFileUtil {


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
    public static String getPath(String filePath, String fileName) {
        if (filePath.lastIndexOf("/") != filePath.length() - 1) {
            throw new ErrorException(10002, "路径必须已[/]结尾");
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
                throw new ErrorException(10002, "图片仅支持上传-[jpg,png,jpeg,jif]");
            }
            fileName = LocalDateTimeUtil.getTimeStr20() + "-" + fileName;
        } else if (UPLOAD_PATH_MUSIC.equals(path)) {
            /**
             * 音频
             */
            if (!"mp3".equals(suffixName)) {
                throw new ErrorException(10002, "音乐仅支持上传-[mp3]");
            }
        } else if (UPLOAD_PATH_VIDEO.equals(path)) {
            /**
             * 视频
             */
            if (!"mp4".equals(suffixName)) {
                throw new ErrorException(10002, "视频仅支持上传-[mp4]");
            }
        } else if (UPLOAD_PATH_EXCEL.equals(path)) {
            /**
             * excel
             */
            if (!"xlsx".equals(suffixName) && !"xls".equals(suffixName)) {
                throw new ErrorException(10002, "EXCEL仅支持上传-[xlxs,xlx]");
            }
        } else if (UPLOAD_PATH_PDF.equals(path)) {
            /**
             * pdf
             */
            if (!"pdf".equals(suffixName)) {
                throw new ErrorException(10002, "PDF仅支持上传-[pdf]");
            }
        } else if (UPLOAD_PATH_FILE.equals(path)) {
            /**
             * 任意文件，不做限制
             */
        } else {
            throw new ErrorException(10002, "路径错误");
        }
        // 空格+逗号+括号+一些特殊符号 统一转为_,部分地方无法解析，如逗号分隔url, editor 编辑器, URL请求空格问题
        fileName = fileName
                .replaceAll(" ", "_")
                .replace(",", "_")
                .replaceAll("\\(", "_")
                .replaceAll("\\)", "_")
                .replaceAll("#", "_")
                .replaceAll("@", "_");
        return fileName;
    }



    /**
     *  1、获取保存后的文件类型
     *  <P>  阿里云,处理文件格式，不处理可能导致文件无法直接访问 </P>
     *  --- 1、如果后缀不在以下范围内，返回null,保存上传文件的格式
     *  --- 2、图片统一服务端统一保存为： image/jpg
     *
     * @param filenameExtension
     * @return
     */
    public static String getContentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx") ||
                filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx") ||
                filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (filenameExtension.equalsIgnoreCase(".mp4")) {
            return "video/mp4";
        }
        if (filenameExtension.equalsIgnoreCase(".mp3")) {
            return "audio/mp3";
        }
        if (filenameExtension.equalsIgnoreCase(".pdf")) {
            return " application/pdf";
        }
        return null;
    }
}
