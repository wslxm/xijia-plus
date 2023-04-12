package io.github.wslxm.springbootplus2.file.strategy.service.impl;

import io.github.wslxm.springbootplus2.file.properties.FileProperties;
import io.github.wslxm.springbootplus2.file.strategy.service.FileStrategy;
import io.github.wslxm.springbootplus2.file.util.FileUploadUtil;
import io.github.wslxm.springbootplus2.file.util.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;


/**
 * 阿里云oss 文件上传
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:18
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
    public String upload(InputStream inputStream, String filePath, String fileName) {
        // 验证文件格式、保存路径，并处理文件名防止重复
        fileName = FileUploadUtil.getPath(filePath, fileName);
        // 参数1：上传后保存的跟路径地址
        return ossUtil.upload(fileProperties.getAliyunOss().getPath(), filePath, fileName, inputStream);
    }


    @Override
    public Boolean del(String filePath) {
        // 去除访问地址
        if (filePath.indexOf("http://") != -1 || filePath.indexOf("https://") != -1) {
            filePath = filePath.replace("http://", "").replace("https://", "");
            int index = filePath.indexOf("/");
            filePath = filePath.substring(index + 1);
        }
        return ossUtil.deleteObject(filePath);
    }

    @Override
    public Boolean delFolder(String filePath) {
        return ossUtil.deleteObjectFolde(filePath);
    }
}
