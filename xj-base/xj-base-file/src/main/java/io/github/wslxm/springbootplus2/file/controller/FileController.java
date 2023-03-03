package io.github.wslxm.springbootplus2.file.controller;

import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.file.properties.FileProperties;
import io.github.wslxm.springbootplus2.file.strategy.context.FileChannelContext;
import io.github.wslxm.springbootplus2.file.strategy.service.FileStrategy;
import io.github.wslxm.springbootplus2.file.util.FileDownloadUtil;
import io.github.wslxm.springbootplus2.file.util.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/api/open/file")
public class FileController {

    /**
     * 文件访问器
     */
    @Autowired
    private FileChannelContext fileChannelContext;

    /**
     * 文件上传配置
     */
    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;


    @SneakyThrows
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("文件上传,可在指定路径后追加子路径,以/结尾，上传成功返回完整可访问URL")
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
        FileStrategy fileStrategy = fileChannelContext.getChannel(fileProperties.getChannel());

        // 上传的最后一级目录拼接来源的最后一级地址
        String referer = request.getHeader("referer");
        if (referer != null) {
            referer = referer.split("\\?")[0];
            String[] refererArray = referer.split("/");
            filePath += refererArray[refererArray.length - 1] + "/";
        }

        // 验证文件格式、保存路径，并处理文件名防止重复
        String fileName = FileUploadUtil.getPath(filePath, file.getOriginalFilename());
        // 上传
        String url = fileStrategy.upload(file.getInputStream(), filePath, fileName);
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

    //  @ApiOperation("文件列表")
    //  @RequestMapping(value = "/fileList", method = RequestMethod.GET)
    //  public Object fileList() {
    //      // 指定文件处理渠道
    //      FileStrategy fileStrategy = fileContext.getChannel(fileChannel);
    //      return Result.success(fileStrategy.fileList());
    //  }

    @ApiOperation("文件删除")
    @ApiImplicitParam(name = "filePath", value = "文件存储路径 或 文件可访问的URL ", required = true)
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public Object del(@RequestParam String filePath) {
        FileStrategy fileStrategy = fileChannelContext.getChannel(fileProperties.getChannel());
        return Result.success(fileStrategy.del(filePath));
    }

    @ApiOperation("文件目录删除")
    @ApiImplicitParam(name = "filePath", value = "文件目录路径", required = true)
    @RequestMapping(value = "/delFolder", method = RequestMethod.DELETE)
    public Object delFolder(@RequestParam String filePath) {
        FileStrategy fileStrategy = fileChannelContext.getChannel(fileProperties.getChannel());
        return Result.success(fileStrategy.delFolder(filePath));
    }

    @ApiOperation("文件下载--单文件下载")
    @ApiImplicitParam(name = "filePath", value = "文件可访问的完整URL", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam String filePath) {
        FileDownloadUtil.download(filePath, response);
    }

    @ApiOperation("文件下载--多文件下载 (批量下载-打压缩包)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePaths", value = "文件可访问的完整URL,多个逗号分隔", required = true),
            @ApiImplicitParam(name = "zipName", value = "下载后的文件名", required = true)
    })
    @RequestMapping(value = "/downloadZip", method = RequestMethod.GET)
    public void downloadZip(@RequestParam String filePaths, @RequestParam String zipName) {
        FileDownloadUtil.downloadZip(Arrays.asList(filePaths.split(",")), zipName, response);
    }
}

