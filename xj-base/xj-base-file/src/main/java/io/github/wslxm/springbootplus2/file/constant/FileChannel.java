package io.github.wslxm.springbootplus2.file.constant;

/**
 * 文件渠道 常量类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:39 
 * @version 1.0.0
 */
public interface FileChannel {

    /**
     * 阿里云
     */
    String ALI_YUN_OSS = "ALI_YUN_OSS";
    /**
     * 本地
     */
    String LOCAL = "LOCAL";
    /**
     * 单独部署本地服务器, 在使用代理直接上传到本地服务器中
     */
    String LOCAL_PROXY = "LOCAL_PROXY";
}
