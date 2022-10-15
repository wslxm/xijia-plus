package io.github.wslxm.springbootplus2.manage.file.controller;

import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.file.util.FileDownloadUtil;
import io.github.wslxm.springbootplus2.manage.file.constant.FileChannel;
import io.github.wslxm.springbootplus2.manage.file.strategy.FileContext;
import io.github.wslxm.springbootplus2.manage.file.strategy.FileStrategy;
import io.github.wslxm.springbootplus2.manage.file.util.FileUploadUtil;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 文件管理
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
@Api(value = "FileController", tags = "文件管理")
@RequestMapping(BaseConstant.Uri.API_OPEN + "/file")
@Slf4j
public class FileController extends BaseController {

    /**
     * 文件访问器
     */
    @Autowired
    private FileContext fileContext;

    /**
     * 文件文件  (TODO 此处可做成 yml 或 全局配置中动态配置)
     */
    private final static String FILE_CHANNEL = FileChannel.ALI_YUN_OSS;


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
            @ApiImplicitParam(name = "resType", value = "返回类型(1- data=url(默认)  2- data=[name:xxx ,url: xxx])", required = false)
    })
    public Result<Object> upload(@RequestParam(required = true) MultipartFile file,
                                 @RequestParam(required = true) String filePath,
                                 @RequestParam(required = false) Integer resType) {
        // 指定文件处理渠道
        FileStrategy fileStrategy = fileContext.getChannel(FILE_CHANNEL);
        // 验证文件格式、保存路径，并处理文件名防止重复
        String fileName = FileUploadUtil.getPath(filePath, file.getOriginalFilename());
        // 上传
        String url = fileStrategy.upload(file, filePath, fileName);
        // 返回数据处理
        if (resType == null || resType == 1) {
            return Result.success(url);
        } else {
            Map<String, String> resMap = new HashMap<>(2, 1);
            resMap.put("name", file.getOriginalFilename());
            resMap.put("url", url);
            return Result.success(resMap);
        }
    }


    /**
     * oss-文件列表
     */
    @ApiOperation("OSS-文件Object列表")
    @RequestMapping(value = "/fileList", method = RequestMethod.GET)
    public Object fileList() {
        // 指定文件处理渠道
        FileStrategy fileStrategy = fileContext.getChannel(FILE_CHANNEL);
        return Result.success(fileStrategy.fileList());
    }


    /**
     * oss-文件删除
     */
    @ApiOperation("OSS-文件删除")
    @ApiImplicitParam(name = "filePath", value = "文件保存的完整可访问URL,或OSS相对路径", required = true)
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public Object del(@RequestParam String filePath) {
        FileStrategy fileStrategy = fileContext.getChannel(FILE_CHANNEL);
        return Result.success(fileStrategy.del(filePath));
    }


    /**
     * 网络可直接访问文件 单下载
     */
    @ApiOperation("OSS-文件下载--单文件下载")
    @ApiImplicitParam(name = "filePath", value = "文件可访问的完整URL", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePath) {
        // 获取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        FileDownloadUtil.download(filePath, fileName, response);
    }


    /**
     * 网络可直接访问文件 批量下载(打压缩包)
     */
    @ApiOperation("OSS-文件下载--多文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePaths", value = "文件可访问的完整URL,多个逗号分隔", required = true),
            @ApiImplicitParam(name = "zipName", value = "下载后的文件名", required = true)
    })
    @RequestMapping(value = "/downloadZip", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePaths, @RequestParam String zipName) {
        FileDownloadUtil.downloadZip(Arrays.asList(filePaths.split(",")), zipName, response);
    }
}

