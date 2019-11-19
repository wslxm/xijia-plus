package com.ws.ldy.admincore.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * TODO  错误拦截跳转指定页面
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/18 20:44
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
        registry.addErrorPages(error400Page,error401Page,error404Page,error500Page);
    }
}


