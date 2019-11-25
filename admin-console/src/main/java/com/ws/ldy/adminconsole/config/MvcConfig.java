package com.ws.ldy.adminconsole.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author wangsong
 * @date 2019年4月10日 下午4:21:08
 */
@SuppressWarnings("all")
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 *
	 * 这里主要做单独的页面跳转 模板自动类：自动指定了/ 前缀（resources\templates） 模板自动类：自动指定了/ 后缀（.html）
	 * resources同webapp（也就是web根目录） templates同WEB-INF(web无法直接访问的资源)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");    	 // 主页
	}



	/**
	 * 赋值文件读写权限
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/File/**").addResourceLocations("file:File/");
	}


	/**
	 * addPathPatterns 拦截请求范围 excludePatterns 不拦截的请求集 .excludePathPatterns
	 * 排除不拦截的请求集
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 排除拦截范围
		List<String> excludePatterns = new ArrayList<>();
		excludePatterns.add("/views/**");              //排除静态文件
		excludePatterns.add("/css/**");
		excludePatterns.add("/js/**");
		excludePatterns.add("/layuiadmin/**");
		excludePatterns.add("/treetable-lay/**");
		excludePatterns.add("/api/**");                  //排除开放Api接口，全已 /api 开头
		excludePatterns.add("/page/console_user_login"); //排除登录页面
		excludePatterns.add("/userAdmin/login");         //排除登录验证接口
		// 拦截范围
		List<String> addPathPatterns = new ArrayList<>();
		addPathPatterns.add("/**");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePatterns);
	}
}
