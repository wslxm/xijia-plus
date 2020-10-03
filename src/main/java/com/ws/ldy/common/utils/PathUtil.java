package com.ws.ldy.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
  * 路径相关工具类
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2020/8/26 0026 19:12
  * @version 1.0.0      
  */
public class PathUtil {


    /**
     * 获取项目的Ip 地址+端口 或者域名
     * @return
     */
    public static  String getBaseUrl(HttpServletRequest request) {
        // 接口名
        String interfaceName = request.getServletPath();
        // 完整url
        String url = request.getRequestURL().toString();
        // 去调接口后url
        String baseUrl = url.replace(interfaceName, "");
        return baseUrl;
    }
}
