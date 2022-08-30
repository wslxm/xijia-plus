package io.github.wslxm.springbootplus2.starter.aliyun.oss.service;

import com.aliyun.oss.model.OSSObjectSummary;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
     * 文件生成
     *
     * @param file
     * @param filePath
     * @param resType
     * @return
     */
    public Object upload(@RequestParam(required = true) MultipartFile file,
                         @RequestParam(required = true) String filePath,
                         @RequestParam(required = false) Integer resType);


    /**
     * oss-文件列表
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

    /**
     * 网络文件下载
     * @author wangsong
     * @param filePath
     * @date 2022/8/22 0022 20:50
     * @return void
     * @version 1.0.0
     */
    public void downloadNet(String filePath);

    /**
     * 网络文件打包下载
     * @author wangsong
     * @param filePaths
     * @param zipName
     * @date 2022/8/22 0022 20:50
     * @return void
     * @version 1.0.0
     */
    public void downloadNet(String filePaths, String zipName);
}

