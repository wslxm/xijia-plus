package com.ws.ldy.others.aliyunoss.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.others.aliyunoss.util.OSSUtil;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * 阿里云OSS 文件上传下载
 *
 * @author peter 2018/10/20 21:32
 */
@RestController
@Api(value = "AliOssController", tags = "文件管理--文件保存到阿里云OSS")
@RequestMapping("/aliOssFile")
public class AliOssController extends BaseController {

    // 阿里云oss工具类
    @Autowired
    private OSSUtil ossUtil;

    /**
     * 文件内网访问OSS域名(下载访问,上传)
     * 此域名主要作用于返回的访问url拼接，以及下载时 url过滤获取到真正的oss地址路径
     */
    private final String YM_PATH = "http://xijia.plus/"; //外网: xijia-sz.oss-cn-shenzhen.aliyuncs.com

    // 文件保存路径
    private final static String FILE_PATH = "oss/file/";

    // 文件保存路径地址
    private final static String UPLOAD_PATH_IMAGE = "image";  //  oss/file/image
    private final static String UPLOAD_PATH_MUSIC = "music";  //  oss/file/music
    private final static String UPLOAD_PATH_VIDEO = "video";  //  oss/file/video
    private final static String UPLOAD_PATH_EXCEL = "excel";  //  oss/file/excel


    @RequestMapping(value = "/upload", method = RequestMethod.POST) //consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("OSS-文件上传,返回完整可访问当前服务内网访问OSS的完整URL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录(" + "\r\n" +
                    "图片=image/" + "\r\n" +
                    "头像=image/head" + "\r\n" +
                    "音乐=music/" + "\r\n" +
                    "视频=video/" + "\r\n" +
                    "表格=excel/" + "\r\n" +
                    ")", required = true)
    })
    public R<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) {
        // 验证文件格式及路径，并获取文件上传路径, file.getOriginalFilename()=原文件名
        String fileName = getPath(filePath, file.getOriginalFilename());
        try {
            // 获得上传的文件流
            InputStream inputStream = file.getInputStream();
            // 上传到OSS的路径 = ObjectKey
            String path = FILE_PATH + filePath + fileName;
            ossUtil.upload(path, inputStream);
            // 返回内网访问地址（域名+ oss存储路径）
            return R.success(YM_PATH + path);
        } catch (Exception e) {
            return R.error(RType.SYS_ERROR_CODE_500);
        }
    }


    /**
     *  文件下载
     *
     * @param filePath 文件路径
     */
    @ApiOperation("OSS-文件下载")
    @ApiImplicitParam(name = "filePath", value = "文件保存的完整可访问URL,或OSS相对路径", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam String filePath) {
        // 去除域名 ,获得oss存储路径
        filePath = filePath.replace(YM_PATH, "");
        // 获取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        // 从oss获取文件流
        InputStream inputStream = ossUtil.download(filePath);
        try {
            // 下载
            BufferedInputStream in = new BufferedInputStream(inputStream);
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
            response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            // 放入outputStream流
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[1024];
            int len = 0;
            int i = 0;
            while ((len = in.read(buffer)) > 0) {
                i = i + len;
                outputStream.write(buffer, 0, len);
            }
            //最后的内容
            outputStream.write(buffer);
            //响应返回字节长度-无效:response.addHeader("Content-Length", "" + i);//  System.out.println(i);
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ErrorException(RType.SYS_ERROR_CODE_500.getCode(), "文件下载失败");
        }
    }


    /**
     * oss-文件列表
     */
    @ApiOperation("OSS-文件Object列表")
    @RequestMapping(value = "/fileList", method = RequestMethod.GET)
    public R<List<OSSObjectSummary>> fileList() {
        List<OSSObjectSummary> objectListing = ossUtil.getObjectListing();
        return R.success(objectListing);
    }


    /**
     * oss-文件删除
     */
    @ApiOperation("OSS-文件删除")
    @ApiImplicitParam(name = "filePath", value = "文件保存的完整可访问URL,或OSS相对路径", required = true)
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public R del(@RequestParam String filePath) {
        // 去除域名 ,获得oss存储路径
        filePath = filePath.replace(YM_PATH, "");
        ossUtil.deleteObject(filePath);
        return R.success();
    }


    /**
     * 上传路径文件格式判断
     *
     * @param filePath 文件上传路径
     * @param fileName 文件名称
     * @return fileName
     */
    private String getPath(String filePath, String fileName) {
        if (filePath.lastIndexOf("/") != filePath.length() - 1) {
            throw new ErrorException(10002, "路径必须已[/]结尾");
        }
        // 目录开头
        String path = filePath.split("/")[0];
        // 后缀名
        String suffixName = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
        if (UPLOAD_PATH_IMAGE.equals(path)) {
            // 图片
            if (!"jpg".equals(suffixName) && !"png".equals(suffixName)) {
                throw new ErrorException(10002, "图片仅支持上传-[jpg,png]");
            }
            //修改fileName的引用,提交17位时间+3位随机数(20前缀)
            fileName = LocalDateTimeUtil.getTimeStr20() + "-" + fileName;
            // filePath = filePath.replace(suffixName, "") + UUIDUtil.creatUUID() + "-";
        } else if (UPLOAD_PATH_MUSIC.equals(path)) {
            // 音乐
            if (!"mp3".equals(suffixName)) {
                throw new ErrorException(10002, "音乐仅支持上传-[mp3]");
            }
        } else if (UPLOAD_PATH_VIDEO.equals(path)) {
            // 视频
            if (!"mp4".equals(suffixName)) {
                throw new ErrorException(10002, "视频仅支持上传-[mp4]");
            }
        } else if (UPLOAD_PATH_EXCEL.equals(path)) {
            //excel
            if (!"xlsx".equals(suffixName) && !"xls".equals(suffixName)) {
                throw new ErrorException(10002, "EXCEL仅支持上传-[xlxs,xlx]");
            }
        } else {
            throw new ErrorException(10002, "路径错误");
        }
        return fileName;
    }
}

