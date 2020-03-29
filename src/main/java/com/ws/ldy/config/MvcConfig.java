package com.ws.ldy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


/**
 * TODO  登录拦截、赋值文件读写权限、页面跳转
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:20
 */
@SuppressWarnings("all")
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 这里主要做单独的页面跳转 模板自动类：自动指定了/ 前缀（resources\templates） 模板自动类：自动指定了/ 后缀（.html）
     * resources同webapp（也就是web根目录） templates同WEB-INF(web无法直接访问的资源)
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 主页
        registry.addViewController("/index").setViewName("index");
        //登录页
        registry.addViewController("/").setViewName("/page/console_user_login");
    }


    /**
     * 赋值文件读写权限，swagger-ui的路径处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/File/**").addResourceLocations("file:File/");

        // 解决静态资源无法访问（可选）
        /*registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");*/
        // 直接在浏览器访问：根目录/swagger-ui.html
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // 需要用到的webjars（包含js、css等）
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * addPathPatterns 拦截请求范围 excludePatterns 不拦截的请求集 .excludePathPatterns
     * 排除不拦截的请求集
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 排除后台登录拦截范围
        List<String> excludePatterns = new ArrayList<>();
        // 排除后台管理静态资源文件
        excludePatterns.add("/views/**");
        excludePatterns.add("/static/css/**");
        excludePatterns.add("/js/**");
        excludePatterns.add("/layuiadmin/**");
        excludePatterns.add("/treetable-lay/**");
        excludePatterns.add("/template/**");
        // 排除前端接口/public/**，/api/**，/open/**
        excludePatterns.add("/public/**");
        excludePatterns.add("/api/**");
        excludePatterns.add("/open/**");
        // 排除登录页面，排除登录验证接口，排除错误提示接口
        excludePatterns.add("/page/console_user_login");
        excludePatterns.add("/userAdmin/login");
        excludePatterns.add("/error/**");
        // 排除Swagger-ui相关
        excludePatterns.add("/webjars/**");
        excludePatterns.add("/v2/**");
        excludePatterns.add("/swagger-ui.html/**");
        excludePatterns.add("/swagger-resources/**");
        //Swagger-ui-layui相关
        excludePatterns.add("/docs.html/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePatterns);
    }
}
