package io.github.wslxm.springbootplus2.manage.file.strategy.impl;

import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.manage.file.strategy.FileStrategy;
import io.github.wslxm.springbootplus2.starter.aliyun.oss.service.AliOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 阿里云oss 文件上传
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:18 
 * @version 1.0.0
 */
@Component
public class AliYunOssFileStrategy implements FileStrategy {

    @Autowired
    private AliOssService aliOssService;

    @Override
    public String upload(MultipartFile file, String filePath, String fileName) {
        String url = null;
        try {
            url = aliOssService.upload(file, filePath, fileName);
        } catch (IOException e) {
            throw new ErrorException("上传过程中遇到错误");
        }
        return url;
    }

    @Override
    public Object fileList() {
        return aliOssService.fileList();
    }

    @Override
    public Object del(String filePath) {
        return aliOssService.del(filePath);
    }
}
