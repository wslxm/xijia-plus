package com.ws.ldy.adminconsole.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author wangsong
 * @date 2019年4月10日 下午4:21:08
 */
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
}
