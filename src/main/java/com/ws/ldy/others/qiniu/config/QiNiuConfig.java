package com.ws.ldy.others.qiniu.config;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.ws.ldy.common.utils.ConsoleColors;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 七牛云 oss 配置类
 * <P> 阿里云oss控制台： https://oss.console.aliyun.com/overview <P/>
 * <P> 继承  CommandLineRunner  是让该配置类在项目启动成功后再执行，确保 ${isAliOss} 参数的正常读取 </P>
 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.0
 */
@SuppressWarnings("ALL")
@Data
@Component
@Slf4j
@ToString
public class QiNiuConfig implements CommandLineRunner {

    /**
     * 环境配置(true= 正式环境  false= 测试环境), 在yml中配置 true，false来读取不同的配置
     */
    @Value("${xj.isQiNiuOss:false}")
    private boolean isAliOss;

    public static String accessKey;  // 秘钥ak
    public static String secretKey;  // 秘钥sk
    public static String bucket;     // 自定义的bucket
    public static String hosts;      // 访问域名
    public static String priKey;     // 设置链接有效期priKey
    public static String pubBucket;  // 公开Bucket
    public static String pubHosts;   // 公开Hosts

    /**
     * 上传管理器
     */
    public static UploadManager uploadManager = null;

    /**
     * 初始化配置数据
     * @author wangsong
     * @date 2020/12/11 0011 17:32
     */
    @Override
    public void run(String... args) {
        //  构造一个带指定Zone对象的配置类
        //  Configuration cfg = new Configuration(Zone.zone0());// Zone.zone0() 指华东
        //  Configuration cfg = new Configuration(Zone.zone1());//华北
        //  Configuration cfg = new Configuration(Zone.zone2());//华南
        Configuration cfg = null;
        if (this.isAliOss) {
            // 正式环境
            accessKey = "TdAnY62PtrBRCaoBvq8Zl2qXlJz5CZvjbsWwpoNZ";
            secretKey = "tGBHE4RE8Y0drlRXbfWxctlpWRVCTaGNRV8yDzqY";
            bucket = "lplb-file";
            hosts = "http://file.520ban.com/";
            priKey = "d7982faa03b99f8b2198e28838ba70b9c89877b8";
            pubBucket = "pubfile";
            pubHosts = "http://pubfile.520ban.com/";
            // 华东
            cfg = new Configuration(Zone.zone0());
        } else {
            // 测试环境
            accessKey = "FetmKgfKFaS7ZAE12j5uzNSrXDkf6pp5LyAxtzWy";
            secretKey = "iY74NEU14YUA3lDUb1vy3Rt18pnEQvTgx37WxH2r";
            bucket = "xi-jia";
            hosts = "http://qiniu.file.xijia.plus/";
            priKey = "";
            pubBucket = "";
            pubHosts = "";
            //华南
            cfg = new Configuration(Zone.zone2());
        }
        // 注册上传过滤器 ...其他参数参考类注释
        uploadManager = new UploadManager(cfg);
        // 打印加载信息
        log.info(ConsoleColors.YELLOW_BRIGHT +
                "\r\n" +
                "|---     七牛云 OSS配置加载成功     ---| \r\n" +
                "|  isAliOss: {} \r\n" +
                "|  accessKey: {} \r\n" +
                "|  secretKey: {} \r\n" +
                "|  bucket: {} \r\n" +
                "|  hosts: {} \r\n" +
                "|  priKey: {} \r\n" +
                "|  pubBucket: {} \r\n" +
                "|  pubHosts: {} \r\n" +
                "| ----------------------------------|"
                + ConsoleColors.RESET, this.isAliOss, accessKey, secretKey, bucket, hosts, priKey, pubBucket, pubHosts);
    }
}

