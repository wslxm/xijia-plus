package com.ws.ldy.modules.third.base;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.FileUploadUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 通用文件上传下载
 *
 * @author peter 2018/10/20 21:32
 */
@RestController
@Api(value = "FileUploadController", tags = "v-1.1 -- 文件管理 --> 本地服务器")
@RequestMapping(BaseConstant.Uri.apiOpen +"/file")
public class FileUploadController extends BaseController {


    /**
     * 文件保存到的服务器路径
     */
    private static final String FILE_PATH = "File/";


    /**
     * 图片上传，需要赋值读写权限
     * @author wangsong
     * @param file
     * @param filePath
     * @date 2020/12/24 0024 17:42
     * @return com.ws.ldy.common.result.R
     * @version 1.0.0
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)//consumes = "multipart/*", headers = "content-type=multipart/form-data"
    @ApiOperation("单文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "文件路径,必须指定开头目录,当为任意文件时,后台不验证文件格式(" + "\r\n" +
                    "图片=image/" + "\r\n" +
                    "头像=image/head" + "\r\n" +
                    "音乐=music/" + "\r\n" +
                    "视频=video/" + "\r\n" +
                    "表格=excel/" + "\r\n" +
                    "PDF=pdf/" + "\r\n" +
                    "任意文件=file/" + "\r\n" +
                    ")", required = true, example = "image/")
    })
    public R uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) {
        // 接口名
        String interfaceName = request.getServletPath();
        // 文件名
        String fileName = file.getOriginalFilename();
        // 完整url
        String url = request.getRequestURL().toString();
        // 去调接口后url
        String baseUrl = url.replace(interfaceName, "");
        // 验证文件格式及路径（注意：这里接组了阿里云模块的 util 工具类）
        fileName = FileUploadUtil.getPath(filePath, fileName);
        // 上传路径,判断,不存在创建
        Path directory = Paths.get(FILE_PATH + filePath);
        try {
            // 获得文件流
            InputStream inputStream = file.getInputStream();
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            // 判断文件是否存在,存在删除
            if (Files.exists(directory.resolve(fileName))) {
                File newFile = new File(FILE_PATH + filePath + fileName);
                newFile.delete();
            }
            // 拷贝流到上传的目录
            Files.copy(inputStream, directory.resolve(fileName));
            // 绝对路径
            String path = baseUrl + "/" + FILE_PATH + filePath + fileName;
            return R.success(path);
        } catch (Exception e) {
            return R.error(RType.FILE_UPLOAD_FAILED);
        }
    }


    @ApiOperation("单文件删除")
    @RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
    @ApiImplicitParam(name = "filePath", value = "文件路径(相对路径||绝对路径), 如: File/image/1.jpg", required = true)
    public R deleteFile(String fileName) {
        File file = new File(fileName);
        // 判断目录或文件是否存在
        boolean result = file.exists();
        if (result) {
            // 判断是否为文件
            result = file.isFile();
            if (result) {
                //删除文件
                file.delete();
            }
        }
        return R.success();
    }

}

