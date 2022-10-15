package io.github.wslxm.springbootplus2.starter.aliyun.oss.service.impl;

import com.aliyun.oss.model.OSSObjectSummary;
import io.github.wslxm.springbootplus2.starter.aliyun.oss.service.AliOssService;
import io.github.wslxm.springbootplus2.starter.aliyun.oss.util.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * 阿里云OSS 文件上传下载
 * <p>
 * consumes = "multipart/*", headers = "content-type=multipart/form-data"
 * </P>
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2018/10/20 21:32
 */
@Service
@Slf4j
public class AliOssServiceImpl implements AliOssService {


    @Autowired
    private OSSUtil ossUtil;


    @Override
    public String upload(MultipartFile file,
                         String filePath,
                         String fileName) throws IOException {
        InputStream inputStream = file.getInputStream();
        return ossUtil.upload(filePath, fileName, inputStream);
    }

    @Override
    public List<OSSObjectSummary> fileList() {
        return ossUtil.getObjectListing();
    }

    @Override
    public Boolean del(@RequestParam String filePath) {
        return ossUtil.deleteObject(filePath);
    }
}

