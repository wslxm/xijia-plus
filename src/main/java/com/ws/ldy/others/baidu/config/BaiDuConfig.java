package com.ws.ldy.others.baidu.config;

/**
 * 百度api 配置类
 * @author wangsong
 * @date 2020/12/24 0024 16:55
 * @return
 * @version 1.0.0
 */
public class BaiDuConfig {

    /**
     * 在百度api访问地址
     */
    public final static String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
    /**
     * 百度 clientId，在百度api控制台获取
     */
    public final static String clientId = "3671xuH3f8w5Pwt99v6X3I2D";
    /**
     * 百度 clientSecret，在百度api控制台获取
     */
    public final static String clientSecret = "oY7GsIEDfIFgMh8bZkirtpAvtdbHDzYR";


    //==============================================================================
    //=========================== api 接口配置 ==========================================
    //==============================================================================

    /**
     * 图像文字识别地址
     */
    public final static String PIC_GENERAL_BASIC_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
}
