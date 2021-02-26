package com.lplb.config.swagger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/***
 * 接口文档配置信息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/20 0020 9:06
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {

    @Value("${swagger.sysAdminPackage}")
    private String sysAdminPackage;
    @Value("${swagger.sysBasePackage}")
    private String sysBasePackage;
    @Value("${swagger.ywAdminPackage}")
    private String ywAdminPackage;
    @Value("${swagger.ywClientPackage}")
    private String ywClientPackage;
    @Value("${swagger.author}")
    private String author;
    @Value("${swagger.email}")
    private String email;
    @Value("${swagger.url}")
    private String url;
    @Value("${swagger.version}")
    private String version;
    @Value("${swagger.defaultKey}")
    private String defaultKey;
    @Value("${swagger.defaultValue}")
    private String defaultValue;
    // 是否展示接口文档
    @Value("${swagger.isShow:true}")
    private Boolean isShow;

    // 定义分隔符
    private static final String splitor = ",";


    @Bean
    public Docket sysAdminApi() {
        // 名称
        String groupName = "ADMIN--系统模块";
        // 扫包路径
        return this.createDocket(groupName, sysAdminPackage);
    }

    @Bean
    public Docket sysBaseApi() {
        // 名称
        String groupName = "BASE--通用模块";
        // 扫包路径
        return this.createDocket(groupName, sysBasePackage);
    }


    @Bean
    public Docket ywAdminApi() {
        // 名称
        String groupName = "业务管理端";
        // 扫包路径
        return this.createDocket(groupName, ywAdminPackage);
    }


    @Bean
    public Docket ywClientApi() {
        // 名称
        String groupName = "业务用户端";
        // 扫包路径
        return this.createDocket(groupName, ywClientPackage);
    }


    /**
     * 创建swagger 目录
     * @return
     */
    public Docket createDocket(String groupName, String basePackage) {
        if(!isShow){
            return null;
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                // 全局参数 -> 默认token参数
                .globalOperationParameters(getGlobalParameter())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.lplb.modules.pets"
                .apis(basePackage(basePackage))   // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title(groupName)
                        .description("restful Swagger API 文档")
                        .termsOfServiceUrl(url)
                        .version(version)
                        .contact(new Contact(author, url, email))
                        .build());
    }


    /**
     * swagger全局header参数添加
     * @author wangsong
     * @date 2021/1/20 0020 14:45
     * @return java.util.List<springfox.documentation.service.Parameter>
     * @version 1.0.0
     */
    private List<Parameter> getGlobalParameter() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        // 管理端默认账号
        parameterBuilder
                .name(defaultKey)                           // key
                .scalarExample(defaultValue)               // value-默认token值 getAdminUserToken(username)
                .description("请求头参数")                   // 描叙
                .modelRef(new ModelRef("string"))     // 字符串参数
                .parameterType("header")                   // 请求头参数
                .order(-100)                               // 靠前
                .required(false)                           // 非必传
                .build();
        List<Parameter> parameters = Lists.newArrayList();
        parameters.add(parameterBuilder.build());
        return parameters;
    }


    //=============================================================================================
    //=============================================================================================
    //========================== 让swagger支持配置多个 包路径 ========================================
    //=============================================================================================
    //=============================================================================================

    /**
     * 让swagger支持配置多个 包路径
     * @author wangsong
     * @param basePackage
     * @date 2020/12/29 0029 11:18
     * @return com.google.common.base.Predicate<springfox.documentation.RequestHandler>
     * @version 1.0.0
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
}
