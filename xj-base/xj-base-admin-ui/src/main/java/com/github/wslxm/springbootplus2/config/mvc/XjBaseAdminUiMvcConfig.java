package com.github.wslxm.springbootplus2.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 页面访问资源路径配置
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:20
 */
@SuppressWarnings("all")
@Configuration
public class XjBaseAdminUiMvcConfig implements WebMvcConfigurer {

    /**
     * 访问URL路径与 resources\templates 页面(.html）路径映射配置, 这里主要做单独的页面跳转
     * <p>
     * 如: 访问/login  为访问 sys_login.html 页面，自动指定了/ 后缀（.html） resources同webapp（也就是web根目录） templates同WEB-INF
     * </P>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("manage/sys_index");        // 管理端主页
        registry.addViewController("/login").setViewName("manage/sys_login");        // 管理端登录页
        registry.addViewController("/").setViewName("manage/sys_login");             // 管理端登录页
    }


    /**
     * 静态资源访问路径映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问（可选）,优先读取  resources/resources  ->  resources/static  ->  resources/public
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/");
    }
}
