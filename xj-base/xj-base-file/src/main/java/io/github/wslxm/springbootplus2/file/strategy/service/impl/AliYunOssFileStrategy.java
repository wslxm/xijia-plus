package io.github.wslxm.springbootplus2.file.strategy.service.impl;

import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.file.config.FileProperties;
import io.github.wslxm.springbootplus2.file.strategy.service.FileStrategy;
import io.github.wslxm.springbootplus2.file.util.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


/**
 * 阿里云oss 文件上传
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:18 
 * @version 1.0.0
 */
@Service
public class AliYunOssFileStrategy implements FileStrategy {

    @Autowired
    private OSSUtil ossUtil;

    /**
     * 文件上传配置
     */
    @Autowired
    private FileProperties fileProperties;


    @Override
    public String upload(MultipartFile file, String filePath, String fileName) {
        String url = null;
        try {
            InputStream inputStream = file.getInputStream();
            // 参数1：上传后保存的跟路径地址
            url = ossUtil.upload(fileProperties.getAliyunOss().getPath(), filePath, fileName, inputStream);
        } catch (IOException e) {
            throw new ErrorException("上传过程中遇到错误");
        }
        return url;
    }


    @Override
    public Boolean del(String filePath) {
        return ossUtil.deleteObject(filePath);
    }
}
