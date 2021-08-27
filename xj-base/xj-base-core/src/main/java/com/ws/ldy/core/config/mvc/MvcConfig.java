package com.ws.ldy.core.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *  登录拦截、赋值文件读写权限、页面跳转
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:20
 */
@SuppressWarnings("all")
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 静态资源访问路径映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 文件保存路径, 让程序可以访问到改路径, 访问 /File 读取项目跟路径的File目录
        registry.addResourceHandler("/File/**").addResourceLocations("file:File/");
        // 解决静态资源无法访问（可选）， 优先读取  resources/resources  ->  resources/static  ->  resources/public
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/");
    }
}
