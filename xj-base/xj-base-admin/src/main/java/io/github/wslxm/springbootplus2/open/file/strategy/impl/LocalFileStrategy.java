package io.github.wslxm.springbootplus2.open.file.strategy.impl;

import cn.hutool.core.io.FileUtil;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.open.file.strategy.FileStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 本地文件
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:19 
 * @version 1.0.0
 */
@Service
public class LocalFileStrategy implements FileStrategy {

    /**
     * 上传路径
     */
    @Value("${file.local.path}")
    private String uploadPath;

    /**
     * 访问地址
     */
    @Value("${file.local.baseUrl}")
    private String baseUrl;


    @Autowired
    private HttpServletRequest request;

    @Override
    public String upload(MultipartFile file, String filePath, String fileName) {
        try {
            InputStream inputStream = file.getInputStream();
            // 获得客户端发送请求的完整url以及uri
            // String url = request.getRequestURL().toString();
            // String requestUri = request.getRequestURI();
            // 获得去除接口前的url
            // String zpath = url.replace(requestUri, "");
            // 目录路径
            Path directory = Paths.get(uploadPath + "/" + filePath);
            // 判断目录是否存在，不存在创建
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            // 判断文件是否存在,存在删除（因为已经提前处理了文件重复问题，不可能已存在）
            // if (Files.exists(directory.resolve(name))) {
            //     File file1 = new java.io.File(UPLOAD_PATH + "/" + name);
            //     file1.delete();
            // }
            // 拷贝文件
            Files.copy(inputStream, directory.resolve(fileName));
            // url路径
            // String path = serverConfig.getUrl() + "/" + UPLOAD_PATH + "/" + name;
            String path = baseUrl + "/" + uploadPath + "/" + filePath + fileName;
            return path;
        } catch (Exception e) {
            throw new ErrorException("上传过程中遇到错误");
        }
    }


    @Override
    public Boolean del(String filePath) {
        File delFile = new File(filePath);
        return FileUtil.del(delFile);
    }
}
