package com.ws.ldy.others.aliyun.oss.config;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 阿里云oss 配置类
 * <P> 阿里云oss控制台： https://oss.console.aliyun.com/overview <P/>
 * <P> 继承  CommandLineRunner  是让该配置类在项目启动成功后再执行，确保 ${isAliOss} 参数的正常读取 </P>
 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.0
 */
@Data
@Component
@Slf4j
@ToString
public class OssConfig implements CommandLineRunner {

    /**
     * 环境配置(true= 正式环境  false= 测试环境)
     */
    @Value("${isAliOss:false}")
    private boolean isAliOss;
    /**
     * Bucket 域名 （访问域名）
     */
    public static String bucket;
    /**
     * Endpoint地域节点） （上传域名）
     */
    public static String endpoint;
    /**
     * 阿里云下oss 的 accessKeyId  (访问密钥，您可以在控制台上创建和查看)
     */
    public static String accessKeyId;
    /**
     *  阿里云下oss 的 accessKeySecret (访问密钥，您可以在控制台上创建和查看)
     */
    public static String accessKeySecret;
    /**
     * Bucket 名称
     */
    public static String bucketName;


    /**
     * 初始化配置数据
     * @author wangsong
     * @date 2020/12/11 0011 17:32
     */
    @Override
    public void run(String... args) throws Exception {
        if (this.isAliOss) {
            // 正式环境(使用内网)
            bucket = "xijia-sz.oss-cn-shenzhen-internal.aliyuncs.com";
            endpoint = "oss-cn-shenzhen-internal.aliyuncs.com";
            accessKeyId = "LTAI4Fyfy2DTf4yUrvKqHwH4";
            accessKeySecret = "Jf3gn1kSRLdh1j14F5mznnJyIBIhA8";
            bucketName = "xijia-sz";
        } else {
            // 测试环境(使用外网)
            bucket = "xijia-sz.oss-cn-shenzhen.aliyuncs.com";
            endpoint = "oss-cn-shenzhen.aliyuncs.com";
            accessKeyId = "LTAI4Fyfy2DTf4yUrvKqHwH4";
            accessKeySecret = "Jf3gn1kSRLdh1j14F5mznnJyIBIhA8";
            bucketName = "xijia-sz";
        }
        log.info("阿里云OSS配置加载成功 ===> isAliOss:{} ==> bucket:[{}] ==> endpoint:[{}] ==> bucketName:[{}]", this.isAliOss, bucket, endpoint, bucketName);
    }
}

