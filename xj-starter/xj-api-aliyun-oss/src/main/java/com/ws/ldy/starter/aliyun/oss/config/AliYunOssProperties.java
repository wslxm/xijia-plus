package com.ws.ldy.starter.aliyun.oss.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云oss 配置类
 * <P> 阿里云oss控制台： https://oss.console.aliyun.com/overview <P/>
 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
@Slf4j
public class AliYunOssProperties {

    private String bucket;          // Bucket 域名 （访问文件的域名）
    private String endpoint;        // Endpoint地域节点 （上传文件的域名）
    private String accessKeyId;     // 阿里云下oss 的 accessKeyId  (访问密钥，您可以在控制台上创建和查看)
    private String accessKeySecret; // 阿里云下oss 的 accessKeySecret (访问密钥，您可以在控制台上创建和查看)
    private String bucketName;      // Bucket 名称

    /**
     * 打印配置信息
     */
//    public void println() {
//        log.info(ConsoleColors.YELLOW_BRIGHT +
//                "\r\n" +
//                "|---      阿里云OSS配置    ---| \r\n" +
//                "|  bucket: {} \r\n" +
//                "|  endpoint: {} \r\n" +
//                "|  accessKeyId: {} \r\n" +
//                "|  accessKeySecret: {} \r\n" +
//                "|  bucketName: {} \r\n" +
//                "| ----------------------------------|"
//                + ConsoleColors.RESET, bucket, endpoint, accessKeyId, accessKeySecret, bucketName);
//    }
}

