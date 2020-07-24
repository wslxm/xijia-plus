package com.ws.ldy.config.swagger;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket baseApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("BASE-全局通用接口")
                .globalOperationParameters(getGlobalParameter())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ws.ldy.others"))  // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("兮家手册-BASE")
                        .description("兮家 Swagger API 文档")
                        .termsOfServiceUrl("https://gitee.com/wslxm/spring-boot-plus2")
                        .version("1.0.0")
                        .contact(new Contact("王松", "https://gitee.com/wslxm/spring-boot-plus2", "1270696548@qq.com"))
                        .build());
    }


    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ADMIN-系统模块接口")
                .globalOperationParameters(getGlobalParameter())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ws.ldy.modules.admin"))   // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("兮家手册-ADMIN")
                        .description("兮家 Swagger API 文档")
                        .termsOfServiceUrl("https://gitee.com/wslxm/spring-boot-plus2")
                        .version("1.0.0")
                        .contact(new Contact("王松", "https://gitee.com/wslxm/spring-boot-plus2", "1270696548@qq.com"))
                        .build());
    }

    @Bean
    public Docket xijiaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dev-开发计划模块接口")
                .globalOperationParameters(getGlobalParameter())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ws.ldy.modules.dev"))   // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("兮家手册-dev")
                        .description("兮家 Swagger API 文档")
                        .termsOfServiceUrl("https://gitee.com/wslxm/spring-boot-plus2")
                        .version("1.0.0")
                        .contact(new Contact("王松", "https://gitee.com/wslxm/spring-boot-plus2", "1270696548@qq.com"))
                        .build());
    }



    /**
     * swagger全局header参数添加
     */
    private List<Parameter> getGlobalParameter() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder
                .scalarExample("token") //账号默认token
                .name("Token")
                .description("请求头参数")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false)
                .build();
        List<Parameter> parameters = Lists.newArrayList();
        parameters.add(parameterBuilder.build());
        return parameters;
    }
}
