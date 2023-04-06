package io.github.wslxm.springbootplus2.file.strategy.service.impl;

import cn.hutool.core.io.FileUtil;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.file.properties.FileProperties;
import io.github.wslxm.springbootplus2.file.strategy.service.FileStrategy;
import io.github.wslxm.springbootplus2.file.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.nio.file.*;

/**
 * 本地文件
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:19
 */
@Service
@Slf4j
public class LocalFileStrategy implements FileStrategy {

    /**
     * 文件上传配置
     */
    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private HttpServletRequest request;

    @Override
    public String upload(InputStream inputStream, String filePath, String fileName) {
        // 验证文件格式、保存路径，并处理文件名防止重复
        fileName = FileUploadUtil.getPath(filePath, fileName);
        try {
            // 目录路径
            String baseUrl = fileProperties.getLocal().getBaseUrl();
            String uploadPath = fileProperties.getLocal().getPath();
            Path directory = Paths.get(uploadPath + "/" + filePath);
            // 判断目录是否存在，不存在创建
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            // 拷贝文件
            Files.copy(inputStream, directory.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            // url路径
            String path = baseUrl + "/" + uploadPath + "/" + filePath + fileName;
            return path;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorException("上传过程中遇到错误");
        }
    }

    @Override
    public Boolean del(String filePath) {
        // 去除访问地址
        // 去除访问地址
        if (filePath.indexOf("http://") != -1 || filePath.indexOf("https://") != -1) {
            filePath = filePath.replace("http://", "").replace("https://", "");
            int index = filePath.indexOf("/");
            filePath = filePath.substring(index + 1);
        }
        return FileUtil.del(new File(filePath));
    }

    @Override
    public Boolean delFolder(String prefix) {
        return FileUtil.del(new File(prefix));
    }
}
