package com.ws.ldy.base.controller;

import com.ws.ldy.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.common.utils.DefaultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("all")
public class BaseController<S extends BaseIServiceImpl> {

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


    //================================================================
    //================================================================
    //=========================== 分页相关 ============================
    //================================================================
    //================================================================

    /**
     * TODO 获取分页对象   === mybatis-plus
     *
     * @return
     */
    protected <T> com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> getPage() {
        // 页数
        Integer cursor = DefaultsUtils.castToInt(request.getParameter("current"), 1);
        // 分页大小
        Integer limit = DefaultsUtils.castToInt(request.getParameter("size"), 20);
        return new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(cursor, limit);
    }
}
