package com.ws.ldy.config.mvc;

import com.ws.ldy.config.auth.filter.JwtAuthFilter;
import com.ws.ldy.config.auth.filter.RequestFilter;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
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


    @Autowired
    private AdminAuthorityService adminAuthorityService;

    /**
     * 访问URL路径与 resources\templates 页面(.html）路径映射配置, 这里主要做单独的页面跳转
     * <p>
     * 如: 访问/login  为访问 xj_login.html 页面，自动指定了/ 后缀（.html） resources同webapp（也就是web根目录） templates同WEB-INF
     * </P>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 主页,
        registry.addViewController("/index").setViewName("modules/admin/xj-index");
        // 登录页
        registry.addViewController("/").setViewName("modules/admin/xj_login");
        registry.addViewController("/login").setViewName("modules/admin/xj_login");
        // 文本转符号
        registry.addViewController("/fh").setViewName("front/symbol/main");
        // 兮家手册
        registry.addViewController("/help").setViewName("modules/admin/help/index");
    }


    /**
     * 静态资源访问路径映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //文件保存路径, 让程序可以访问到改路径, 访问 /File 读取项目跟路径的File目录
        registry.addResourceHandler("/File/**").addResourceLocations("file:File/");

        // 解决静态资源无法访问（可选）
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        // 直接在浏览器访问：根目录/swagger-ui.html
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        // 需要用到的webjars（包含js、css等）
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        //
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * 跨域处理
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/29 0029 19:16 
     * @version 1.0.0
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); /*是否允许请求带有验证信息*/
        corsConfiguration.addAllowedOrigin("*");     /*允许访问的客户端域名*/
        corsConfiguration.addAllowedHeader("*");     /*允许服务端访问的客户端请求头*/
        corsConfiguration.addAllowedMethod("*");     /*允许访问的方法名,GET POST等*/
        corsConfiguration.addExposedHeader("token"); /*暴露哪些头部信息 不能用*因为跨域访问默认不能获取全部头部信息*/
        corsConfiguration.addExposedHeader("TOKEN");
        corsConfiguration.addExposedHeader("Authorization");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


    /**
     * 请求参数过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean requestFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtAuthFilter(adminAuthorityService));
        filterRegistrationBean.addUrlPatterns("/*");      // 拦截所有
        filterRegistrationBean.setName("requestFilter");  // 设置过滤器名称
        filterRegistrationBean.setOrder(1);//执行次序
        return filterRegistrationBean;
    }


    /**
     * 登录授权过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean jwtAuthFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestFilter());
        filterRegistrationBean.addUrlPatterns("/*");      // 拦截所有
        filterRegistrationBean.setName("jwtAuthFilter");  // 设置过滤器名称
        filterRegistrationBean.setOrder(2);//执行次序
        return filterRegistrationBean;
    }


}
