package com.ws.ldy.admincore.controller;

import com.ws.ldy.admincore.common.vo.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件上传
 *
 * @author peter 2018/10/20 21:32
 */
@Controller
@Api(tags = {"base-core-file"}, description = "文件上传")
public class FileController {

    /**
     * 上传路径
     */
    private static String UPLOAD_PATH = "File/image/upload";

    // 图片上传，需要赋值读写权限
    @PostMapping(value = "/uploadImage")
    @ResponseBody
    @ApiOperation("图片上传")
    public ResponseData uploadImage(HttpServletRequest request) {
        try {
            MultipartFile image = ((MultipartHttpServletRequest) request).getFile("file");
            String name = image.getOriginalFilename();           // 获得文件名称
            InputStream inputStream = image.getInputStream();    // 获得文件流
            String url = request.getRequestURL().toString();     // 获得客户端发送请求的完整url
            String baseUrl = url.replace("/uploadImage", "");     // 获得去除接口前的url
            // 上传文件的目录路径，判断目录是否存在，不存在创建
            Path directory = Paths.get(UPLOAD_PATH);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            // 判断文件是否存在,存在删除
            if (Files.exists(directory.resolve(name))) {
                File file = new java.io.File(UPLOAD_PATH + "/" + name);
                file.delete();
            }
            // 拷贝文件
            Files.copy(inputStream, directory.resolve(name));
            // 返回相关信息--url路径--封装数据格式返回
            String path = baseUrl + "/" + UPLOAD_PATH + "/" + name;
            return ResponseData.success(path);
        } catch (Exception e) {
            return ResponseData.error("500", "上传失败");
        }
    }


    /**
     * 使用流将图片输出
     *
     * @param response
     * @param name
     * @throws IOException
     */
    @GetMapping("/getImage/{name}")
    public void getImage(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get(UPLOAD_PATH).resolve(name)));
        outputStream.flush();
        outputStream.close();
    }
}

