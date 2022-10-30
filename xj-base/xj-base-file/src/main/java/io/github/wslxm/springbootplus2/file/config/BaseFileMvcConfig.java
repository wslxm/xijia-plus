package io.github.wslxm.springbootplus2.file.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
public class BaseFileMvcConfig implements WebMvcConfigurer {


    @Value("${file.local.path:null}")
    private String localPath;

    /**
     * 本地文件上传时的路径映射配置，静态资源访问路径映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (localPath != null) {
            registry.addResourceHandler("/" + localPath + "/**").addResourceLocations("file:" + localPath + "/");
        }
    }
}
