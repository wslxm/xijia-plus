package io.github.wslxm.springbootplus2.file.properties;

import lombok.Data;

/**
 * 本地文件 配置类
 * <P> 阿里云oss控制台： https://oss.console.aliyun.com/overview <P/>
 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.1
 */
@Data
public class LocalProperties {
    /**
     * 文件保存目录
     */
    private String path;
    /**
     * 文件访问地址
     */
    private String baseUrl;
}

