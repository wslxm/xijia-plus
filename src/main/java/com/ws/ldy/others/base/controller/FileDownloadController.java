package com.ws.ldy.others.base.controller;

import com.ws.ldy.common.utils.FileDownloadUtil;
import com.ws.ldy.enums.BaseConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 文件下载
 *
 * @author peter 2018/10/20 21:32
 */
@RestController
@Api(value = "FileDownloadController", tags = "v-1.1 -- 文件下载",consumes = BaseConstant.InterfaceType.PC_BASE)
@RequestMapping(BaseConstant.Sys.API + "/download")
public class FileDownloadController extends BaseController {


    /**
     * 网络文件下载
     */
    @ApiOperation("单文件下载")
    @ApiImplicitParam(name = "filePath", value = "文件可访问的完整URL", required = true)
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePath) {
        // 获取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        // 文件下载
        FileDownloadUtil.download(filePath, fileName,response);
    }

    /**
     * 网络文件打包下载
     */
    @ApiOperation("多文件下载(zip)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePaths", value = "文件可访问的完整URL,多个逗号分隔", required = true),
            @ApiImplicitParam(name = "zipName", value = "下载后的文件名", required = true)
    })
    @RequestMapping(value = "/zip", method = RequestMethod.GET)
    public void downloadNet(@RequestParam String filePaths, @RequestParam String zipName) {
        // 文件打包下载
        FileDownloadUtil.downloadZip(Arrays.asList(filePaths.split(",")), zipName,response);
    }



    /**
     * 使用流将图片输出
     *
     * @param
     * @throws IOException
     */
//    public void getImage(String filePage) throws IOException {
//        response.setContentType("image/jpeg;charset=utf-8");
//        response.setHeader("Content-Disposition", "inline; filename=girls.png");
//        ServletOutputStream outputStream = response.getOutputStream();
//        // Paths.get(跟目录).resolve(文件路径)
//        outputStream.write(Files.readAllBytes(Paths.get("/").resolve(filePage)));
//        outputStream.flush();
//        outputStream.close();
//    }
}
