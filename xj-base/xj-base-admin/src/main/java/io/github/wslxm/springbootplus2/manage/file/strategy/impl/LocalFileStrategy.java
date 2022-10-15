package io.github.wslxm.springbootplus2.manage.file.strategy.impl;

import io.github.wslxm.springbootplus2.manage.file.strategy.FileStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地文件
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:19 
 * @version 1.0.0
 */
@Component
public class LocalFileStrategy implements FileStrategy {

    @Override
    public String upload(MultipartFile file, String filePath, String fileName) {
        return null;
    }

    @Override
    public Object fileList() {
        return null;
    }

    @Override
    public Object del(String filePath) {
        return null;
    }
}
