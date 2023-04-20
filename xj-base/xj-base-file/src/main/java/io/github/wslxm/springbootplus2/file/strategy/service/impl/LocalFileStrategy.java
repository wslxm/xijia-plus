package io.github.wslxm.springbootplus2.file.strategy.service.impl;

import cn.hutool.core.io.FileUtil;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.file.constant.RequestConst;
import io.github.wslxm.springbootplus2.file.properties.FileProperties;
import io.github.wslxm.springbootplus2.file.strategy.service.FileStrategy;
import io.github.wslxm.springbootplus2.file.util.FileUploadUtil;
import io.github.wslxm.springbootplus2.file.util.FileGlobalHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    @Override
    public String upload(InputStream inputStream, String filePath, String fileName) {
        String applicationName = FileGlobalHeader.getApplicationName();
        String filenameRule = FileGlobalHeader.getFilenameRule();

        fileName = FileUploadUtil.getPath(filePath, fileName, filenameRule);
        try {
            // 目录路径
            String baseUrl = fileProperties.getLocal().getBaseUrl();
            String uploadPath = fileProperties.getLocal().getPath();
            Path directory = Paths.get(uploadPath + "/" + applicationName + "/" + filePath);
            // 判断目录是否存在，不存在创建
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            // 拷贝文件
            Files.copy(inputStream, directory.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
            // url路径
            return baseUrl + "/" + uploadPath + "/" + applicationName + "/" + filePath + fileName;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorException("上传过程中遇到错误");
        }
    }

    @Override
    public boolean del(String filePath) {
        // 去除访问地址
        if (filePath.contains(RequestConst.HTTP) || filePath.contains(RequestConst.HTTPS)) {
            filePath = filePath.replace(RequestConst.HTTP, "").replace(RequestConst.HTTPS, "");
            filePath = filePath.substring(filePath.indexOf("/") + 1);
        }
        return FileUtil.del(new File(filePath));
    }

    @Override
    public boolean delFolder(String prefix) {
        return FileUtil.del(new File(prefix));
    }
}
