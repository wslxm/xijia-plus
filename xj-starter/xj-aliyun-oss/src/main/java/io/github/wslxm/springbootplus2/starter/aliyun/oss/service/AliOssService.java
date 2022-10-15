package io.github.wslxm.springbootplus2.starter.aliyun.oss.service;

import com.aliyun.oss.model.OSSObjectSummary;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 阿里云oss
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/8/22 0022 20:54
 * @version 1.0.0
 */
public interface AliOssService {


    /**
     * 文件上传
     *
     * @param file
     * @param filePath
     * @param resType
     * @return
     */
    public String upload(@RequestParam(required = true) MultipartFile file,
                         @RequestParam(required = true) String filePath,
                         @RequestParam(required = true) String fileName) throws IOException;


    /**
     * oss-文件列表获取
     * @author wangsong
     * @date 2022/8/22 0022 20:50
     * @return java.util.List<com.aliyun.oss.model.OSSObjectSummary>
     * @version 1.0.0
     */
    public List<OSSObjectSummary> fileList();

    /**
     *  oss-文件删除
     * @author wangsong
     * @param filePath
     * @date 2022/8/22 0022 20:50
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    public Boolean del(String filePath);

}

