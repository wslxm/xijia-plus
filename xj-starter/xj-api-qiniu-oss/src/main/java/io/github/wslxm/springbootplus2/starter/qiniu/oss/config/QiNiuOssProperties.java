package io.github.wslxm.springbootplus2.starter.qiniu.oss.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云 oss 配置类

 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.1
 */
@SuppressWarnings("all")
@Configuration
@ConfigurationProperties(prefix = "qiniu.oss")
@Data
@Slf4j
public class QiNiuOssProperties {


    public String accessKey;  // 秘钥ak
    public String secretKey;  // 秘钥sk
    public String bucket;     // 自定义的bucket
    public String hosts;      // 访问域名
    public String priKey;     // 设置链接有效期 priKey


    // 地域(华东 z0 华北 z1 华南 z2 北美 na0 东南亚 as0)
    public String area;


//    public void println() {
//        // 打印加载信息
//        log.info(ConsoleColors.YELLOW_BRIGHT +
//                "\r\n" +
//                "|---     七牛云 OSS配置加载成功     ---| \r\n" +
//                "|  accessKey: {} \r\n" +
//                "|  secretKey: {} \r\n" +
//                "|  bucket: {} \r\n" +
//                "|  hosts: {} \r\n" +
//                "|  priKey: {} \r\n" +
//                "|  area: {} \r\n" +
//                "| ----------------------------------|"
//                + ConsoleColors.RESET, accessKey, secretKey, bucket, hosts, priKey,  area);
//    }
}

