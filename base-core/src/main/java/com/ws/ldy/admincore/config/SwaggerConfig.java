package com.ws.ldy.admincore.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // .globalOperationParameters(getParameters()) //获取页面参数
                // .apis(RequestHandlerSelectors.basePackage("com.ws.ldy.xijiaserver.xjservice"))   // 自行修改为自己的包路径
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build().useDefaultResponseMessages(false);
    }

    /**
     * 获取swagger ApiInfo
     *
     * @return
     */
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("兮家手册")
                .description("兮家 Swagger API 文档")
                .termsOfServiceUrl("https://gitee.com/wslxm/spring-boot-plus2")
                .version("1.0.0")
                .contact(new Contact("王松", "https://gitee.com/wslxm/spring-boot-plus2", "1270696548@qq.com"))
                .build();
    }

    /**
     * 获取Swagger参数
     *
     * @return
     */
//    List<Parameter> getParameters() {
//        return Collections.singletonList(new ParameterBuilder()
//                .name("Authorization")
//                .defaultValue("Bearer " + JWTUtils.generate(1))
//                .description("Authorization")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build());
//    }
}
