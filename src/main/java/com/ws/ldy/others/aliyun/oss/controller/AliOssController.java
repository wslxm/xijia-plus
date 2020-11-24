package com.ws.ldy.others.aliyun.oss.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.FileDownloadUtil;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.common.utils.PathUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.others.aliyun.oss.util.OSSUtil;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 阿里云OSS 文件上传下载
 *
 * @author peter 2018/10/20 21:32
 */
@RestController
@Api(value = "AliOssController", tags = "v-1.1 -- 文件管理 --> 阿里云OSS", consumes = BaseConstant.InterfaceType.PC_BASE)
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/aliOssFile")
public class AliOssController extends BaseController {

    // 阿里云oss工具类
    @Autowired
    private OSSUtil ossUtil;

    // 文件保存到oss的路径
    private final static String FILE_PATH = "oss/file/";

    // 文件保存路径地址
    private final static String UPLOAD_PATH_IMAGE = "image";  //  oss/file/image
    private final static String UPLOAD_PATH_MUSIC = "music";  //  oss/file/music
    private final static String UPLOAD_PATH_VIDEO = "video";  //  oss/file/video
    private final static String UPLOAD_PATH_EXCEL = "excel";  //  oss/file/excel
    private final static String UPLOAD_PATH_FILE = "file";  //  oss/file/file


    @RequestMapping(value = "/upload", method = RequestMethod.POST) //consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录(" + "\r\n" +
                    "图片=image/" + "\r\n" +
                    "头像=image/head" + "\r\n" +
                    "音乐=music/" + "\r\n" +
                    "视频=video/" + "\r\n" +
                    "表格=excel/" + "\r\n" +
                    "任意文件=file/" + "\r\n" +
                    ")", required = true)
    })
    public R<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) {

        // 验证文件格式及路径，并获取文件上传路径, file.getOriginalFilename()=原文件名
        String fileName = getPath(filePath, file.getOriginalFilename());
        try {
            // 获得上传的文件流
            InputStream inputStream = file.getInputStream();
            // 上传到OSS的路径 = ObjectKey
            String path = FILE_PATH + filePath + fileName;
            path = ossUtil.upload(path, inputStream);
            // 返回内网访问地址（域名+ oss存储路径）
            // 项目ip+端口 || 域名
            String baseUrl = PathUtil.getBaseUrl(request);
            return R.success(baseUrl + "/" + path);
        } catch (Exception e) {
            return R.error(RType.FILE_UPLOAD_FAILED);
        }
    }


    /**
     * 多文件上传
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/2 0002 8:54 
     * @version 1.0.0
     */
    @RequestMapping(value = "/uploads", method = RequestMethod.POST, headers = "content-type=multipart/form-data") //consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录(" + "\r\n" +
                    "图片=image/" + "\r\n" +
                    "头像=image/head" + "\r\n" +
                    "音乐=music/" + "\r\n" +
                    "视频=video/" + "\r\n" +
                    "表格=excel/" + "\r\n" +
                    "任意文件=file/" + "\r\n" +
                    ")", required = true, example = "image/")
    })
    public R<List<String>> uploads(@RequestParam("files") MultipartFile[] files, @RequestParam("filePath") String filePath) {
        //接收到的文件
        // List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        List<String> pathList = new ArrayList();
        MultipartFile file = null;
        for (int i = 0; i < files.length; ++i) {
            // 获取文件
            file = files[i];
            // 验证文件格式及路径，并获取文件上传路径, file.getOriginalFilename()=原文件名
            String fileName = getPath(filePath, file.getOriginalFilename());
            try {
                // 获得上传的文件流
                InputStream inputStream = file.getInputStream();
                // 上传到OSS的路径 = ObjectKey
                String path = FILE_PATH + filePath + fileName;
                path = ossUtil.upload(path, inputStream);
                // 返回内网访问地址（域名+ oss存储路径）
                // 项目ip+端口 || 域名
                String baseUrl = PathUtil.getBaseUrl(request);
                pathList.add(baseUrl + "/" + path);
            } catch (Exception e) {
                return R.error(RType.FILE_UPLOAD_FAILED);
            }
        }
        return R.success(pathList);
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
        ossUtil.deleteObject(filePath);
        return R.success();
    }

    /**
     * 网络文件下载
     */
    @ApiOperation("OSS-文件下载--单文件下载")
    @ApiImplicitParam(name = "filePath", value = "文件可访问的完整URL", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePath) {
        // 获取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        // 文件下载
        FileDownloadUtil.download(filePath, fileName, response);
    }

    /**
     * 网络文件打包下载
     */
    @ApiOperation("OSS-文件下载--多文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePaths", value = "文件可访问的完整URL,多个逗号分隔", required = true),
            @ApiImplicitParam(name = "zipName", value = "下载后的文件名", required = true)
    })
    @RequestMapping(value = "/downloadZip", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePaths, @RequestParam String zipName) {
        // 文件打包下载
        FileDownloadUtil.downloadZip(Arrays.asList(filePaths.split(",")), zipName, response);
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
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (UPLOAD_PATH_IMAGE.equals(path)) {
            // 图片
            if (!"jpg".equals(suffixName) && !"png".equals(suffixName) && !"jpeg".equals(suffixName) && !"gif".equals(suffixName)) {
                throw new ErrorException(10002, "图片仅支持上传-[jpg,png,jpeg]");
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
        } else if (UPLOAD_PATH_FILE.equals(path)) {
            // 任意文件，不做限制
        } else {
            throw new ErrorException(10002, "路径错误");
        }
        //去除+空格+逗号，括号统一转为*, editor 编辑器无法解析括号
        fileName = fileName
                .replaceAll(" ", "")
                .replace(",", "")
                .replaceAll("\\(", "*")
                .replaceAll("\\)", "*");
        return fileName;
    }
}

