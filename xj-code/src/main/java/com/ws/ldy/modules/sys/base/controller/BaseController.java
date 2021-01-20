package com.ws.ldy.modules.sys.base.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.common.utils.DefaultsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController<S extends IService> {

    @Autowired
    protected S baseService;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected RestTemplate restTemplate;

    /**
     * 获取分页对象   === mybatis-plus
     *
     * @return
     */
    protected <T> com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> getPage() {
        // 页数
        Integer cursor = DefaultsUtil.castToInt(request.getParameter("current"), 1);
        // 分页大小
        Integer limit = DefaultsUtil.castToInt(request.getParameter("size"), 20);
        return new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(cursor, limit);
    }


    /**
     * 获取请求地址
     * @author wang-song
     * @param request
     * @date 2020/7/14 0014 14:16
     * @return java.lang.String
     * @version 1.0.0
     */
    protected static String getIpAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
