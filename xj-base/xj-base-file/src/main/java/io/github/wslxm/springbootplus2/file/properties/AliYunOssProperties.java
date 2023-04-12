package io.github.wslxm.springbootplus2.file.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云oss 配置类
 * <P> 阿里云oss控制台： https://oss.console.aliyun.com/overview <P/>
 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.1
 */
@Data
@Slf4j
public class AliYunOssProperties {

    /**
     * 文件存储路径
     */
    private String path;
    /**
     * Bucket 域名 （访问文件的域名）
     */
    private String bucket;
    /**
     * Endpoint地域节点 （上传文件的域名）
     */
    private String endpoint;
    /**
     * 阿里云下oss 的 accessKeyId  (访问密钥，您可以在控制台上创建和查看)
     */
    private String accessKeyId;
    /**
     * 阿里云下oss 的 accessKeySecret (访问密钥，您可以在控制台上创建和查看)
     */
    private String accessKeySecret;
    /**
     *  Bucket 名称
     */
    private String bucketName;
}
