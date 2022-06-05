package io.github.wslxm.springbootplus2.starter.aliyun.oss.starter.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import io.github.wslxm.springbootplus2.starter.aliyun.oss.starter.config.result.AliYunOssR;
import io.github.wslxm.springbootplus2.starter.aliyun.oss.starter.service.AliOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 阿里云OSS 文件上传下载
 * <p>
 * consumes = "multipart/*", headers = "content-type=multipart/form-data"
 * </P>
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2018/10/20 21:32
 */
@RestController
@Api(value = "AliOssController", tags = "AliYun --> OSS文件管理")
@RequestMapping("/api/open/aliOssFile")
@Slf4j
public class AliOssController {


    @Autowired
    private AliOssService aliOssService;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("OSS-文件上传,可在指定路径后追加子路径,以/结尾，返回完整可访问当前服务内网访问OSS的完整URL")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录,可使用二级目录,三级目录等等,如头像上传(" + "\r\n" +
                    "图片=image/" + "\r\n" +
                    "头像=image/head/  (二级目录)" + "\r\n" +
                    "音乐=music/" + "\r\n" +
                    "视频=video/" + "\r\n" +
                    "文档=doc/" + "\r\n" +
                    "表格=excel/" + "\r\n" +
                    "任意文件=file/" + "\r\n" +
                    ")", required = true),
            @ApiImplicitParam(name = "isReduce", value = "是否压缩(默认 true, 压缩后图片MB大小直线下降, 放大后的清晰度将下降)", required = false),
            @ApiImplicitParam(name = "resType", value = "返回类型(1- data=url(默认)  2- data=[name:xxx ,url: xxx])", required = false)
    })
    public Object upload(@RequestParam(required = true) MultipartFile file,
                                     @RequestParam(required = true) String filePath,
                                     @RequestParam(required = false) Integer resType,
                                     @RequestParam(required = false) Boolean isReduce) {
        return AliYunOssR.success(aliOssService.upload(file, filePath, resType, isReduce));
    }


    /**
     * oss-文件列表
     */
    @ApiOperation("OSS-文件Object列表")
    @RequestMapping(value = "/fileList", method = RequestMethod.GET)
    public Object fileList() {
        return AliYunOssR.success(aliOssService.fileList());
    }


    /**
     * oss-文件删除
     */
    @ApiOperation("OSS-文件删除")
    @ApiImplicitParam(name = "filePath", value = "文件保存的完整可访问URL,或OSS相对路径", required = true)
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public Object del(@RequestParam String filePath) {
        return AliYunOssR.success(aliOssService.del(filePath));
    }

    /**
     * 网络文件下载
     */
    @ApiOperation("OSS-文件下载--单文件下载")
    @ApiImplicitParam(name = "filePath", value = "文件可访问的完整URL", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePath) {
        aliOssService.downloadNet(filePath);
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
        aliOssService.downloadNet(filePaths, zipName);
    }
}

