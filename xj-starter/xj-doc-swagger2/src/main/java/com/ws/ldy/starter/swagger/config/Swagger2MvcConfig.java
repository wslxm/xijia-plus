package com.ws.ldy.starter.swagger.config;

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
@Configuration
public class Swagger2MvcConfig implements WebMvcConfigurer {


    /**
     * 静态资源访问路径映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问（可选）
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 直接在浏览器访问：根目录/swagger-ui.html
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 需要用到的webjars（包含js、css等）
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
