package io.github.wslxm.springbootplus2.starter.swagger.config;


import io.github.wslxm.springbootplus2.starter.swagger.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * 接口文档配置信息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/20 0020 9:06
 * @version 1.0.1
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket swaggerDocketA() {
        if (swaggerProperties.getPackages().size() == 0) {
            return this.createDocket("A|com");
        } else {
            return this.createDocket(swaggerProperties.getPackages().get("0") == null ? "A|xxx.xxx" : swaggerProperties.getPackages().get("0") + "|A");
        }
    }

    @Bean
    public Docket swaggerDocketB() {
        return this.createDocket(swaggerProperties.getPackages().get("1") == null ? "B|xxx.xxx" : swaggerProperties.getPackages().get("1") + "|B");
    }

    @Bean
    public Docket swaggerDocketC() {
        return this.createDocket(swaggerProperties.getPackages().get("2") == null ? "C|xxx.xxx" : swaggerProperties.getPackages().get("2") + "|C");
    }

    @Bean
    public Docket swaggerDocketD() {
        return this.createDocket(swaggerProperties.getPackages().get("3") == null ? "D|xxx.xxx" : swaggerProperties.getPackages().get("3") + "|D");
    }

    @Bean
    public Docket swaggerDocketE() {
        return this.createDocket(swaggerProperties.getPackages().get("4") == null ? "E|xxx.xxx" : swaggerProperties.getPackages().get("4") + "|E");
    }

    @Bean
    public Docket swaggerDocketF() {
        return this.createDocket(swaggerProperties.getPackages().get("5") == null ? "F|xxx.xxx" : swaggerProperties.getPackages().get("5") + "|F");
    }

    @Bean
    public Docket swaggerDocketG() {
        return this.createDocket(swaggerProperties.getPackages().get("6") == null ? "G|xxx.xxx" : swaggerProperties.getPackages().get("6") + "|G");
    }

    @Bean
    public Docket swaggerDocketH() {
        return this.createDocket(swaggerProperties.getPackages().get("7") == null ? "H|xxx.xxx" : swaggerProperties.getPackages().get("7") + "|H");
    }

    @Bean
    public Docket swaggerDocketI() {
        return this.createDocket(swaggerProperties.getPackages().get("8") == null ? "I|xxx.xxx" : swaggerProperties.getPackages().get("8") + "|I");
    }

    @Bean
    public Docket swaggerDocketJ() {
        return this.createDocket(swaggerProperties.getPackages().get("9") == null ? "J|xxx.xxx" : swaggerProperties.getPackages().get("9") + "|J");
    }


    /**
     * 创建swagger 目录
     *
     * @param groupNameOrPackage
     * @return springfox.documentation.spring.web.plugins.Docket
     * @author wangsong
     * @date 2021/7/28 0028 14:29
     * @version 1.0.1
     */
    public Docket createDocket(Object groupNameOrPackage) {
        String groupName = getGroupName(groupNameOrPackage.toString());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getPackage(groupNameOrPackage.toString())))
                // .apis(basePackage(getPackage(groupNameOrPackage.toString())))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                // 全局参数 -> 默认token参数
                .globalOperationParameters(getGlobalParameter())
                // 文档信息
                .apiInfo(getApiInfo(groupName));
    }


    /**
     * 获取模块名称, 如果没有则自动使用分区号填充
     *
     * @param groupNameOrPackage
     * @return
     */
    private String getGroupName(String groupNameOrPackage) {
        String[] split = groupNameOrPackage.split("\\|");
        // 获取名称,如果是yml配置获取,手动拼接上分区标签 A/B/C/D 等
        String groupName = split[0];
        int len = 2;
        if (split.length > len) {
            groupName = split[2] + "--" + groupName;
        }
        return groupName.trim();
    }


    /**
     * 获取扫包路径，如果关闭了swagger或yml 没有配置当前分区,设置为一个不存在的包 xxx.xxx
     *
     * @param groupNameOrPackage
     */
    private String getPackage(String groupNameOrPackage) {
        String[] split = groupNameOrPackage.split("\\|");
        // 获取包路径, 如果关闭了swagger, 让其扫码一个不存在的包，文档中将看不到没有任何接口
        String basePackage = split[1];
        if (!swaggerProperties.getIsShow()) {
            basePackage = "xxx.xxx";
        }
        return basePackage.trim();
    }


    /**
     * 添加社交信息
     *
     * @param groupName
     * @return
     */
    private ApiInfo getApiInfo(String groupName) {
        return new ApiInfoBuilder()
                .title(groupName)
                .description("restful Swagger API 文档")
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .version(swaggerProperties.getVersion())
                .contact(new Contact(swaggerProperties.getAuthor(), swaggerProperties.getUrl(), swaggerProperties.getEmail()))
                .build();
    }


    /**
     * swagger全局header参数添加
     *
     * @return java.util.List<springfox.documentation.service.Parameter>
     * @author wangsong
     * @date 2021/1/20 0020 14:45
     * @version 1.0.1
     */
    private List<Parameter> getGlobalParameter() {
        String[] keys = swaggerProperties.getDefaultKey().split("\\|", -1);
        String[] values = swaggerProperties.getDefaultValue().split("\\|", -1);
        List<Parameter> parameters = new ArrayList<>();
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            String value = "";
            if (values.length > i) {
                value = values[i];
            }
            // 默认请求头
            ParameterBuilder parameterBuilder = new ParameterBuilder();
            parameterBuilder.name(key.trim())
                    .scalarExample(value.trim())
                    .description("请求头参数")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .order(-999 + i)
                    .required(false);
            parameters.add(parameterBuilder.build());
        }
        return parameters;
    }


    /**
     * 增加如下配置可解决Spring Boot 2.6.x + 与 Swagger 3.0.0 不兼容问题
     **/
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }

}
