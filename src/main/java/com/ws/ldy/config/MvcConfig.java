package com.ws.ldy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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
        registry.addViewController("/xj-index").setViewName("xj-index");
        registry.addViewController("/xj-admin").setViewName("xj-admin");
        // 登录页
        registry.addViewController("/").setViewName("xj_admin_login");
        registry.addViewController("/login").setViewName("xj_admin_login");
    }


    /**
     * 赋值文件读写权限，swagger-ui的路径处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件保存路径开发权限
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
}
//bootAdmin/login