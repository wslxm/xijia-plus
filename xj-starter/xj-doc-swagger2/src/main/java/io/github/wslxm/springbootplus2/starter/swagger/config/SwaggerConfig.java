package io.github.wslxm.springbootplus2.starter.swagger.config;

import io.github.wslxm.springbootplus2.starter.swagger.properties.SwaggerProperties;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/***
 * 接口文档配置信息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/20 0020 9:06
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
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
    public Docket SwaggerDocketC() {
        return this.createDocket(swaggerProperties.getPackages().get("2") == null ? "C|xxx.xxx" : swaggerProperties.getPackages().get("2") + "|C");
    }

    @Bean
    public Docket SwaggerDocketD() {
        return this.createDocket(swaggerProperties.getPackages().get("3") == null ? "D|xxx.xxx" : swaggerProperties.getPackages().get("3") + "|D");
    }

    @Bean
    public Docket SwaggerDocketE() {
        return this.createDocket(swaggerProperties.getPackages().get("4") == null ? "E|xxx.xxx" : swaggerProperties.getPackages().get("4") + "|E");
    }

    @Bean
    public Docket SwaggerDocketF() {
        return this.createDocket(swaggerProperties.getPackages().get("5") == null ? "F|xxx.xxx" : swaggerProperties.getPackages().get("5") + "|F");
    }

    @Bean
    public Docket SwaggerDocketG() {
        return this.createDocket(swaggerProperties.getPackages().get("6") == null ? "G|xxx.xxx" : swaggerProperties.getPackages().get("6") + "|G");
    }

    @Bean
    public Docket SwaggerDocketH() {
        return this.createDocket(swaggerProperties.getPackages().get("7") == null ? "H|xxx.xxx" : swaggerProperties.getPackages().get("7") + "|H");
    }

    @Bean
    public Docket SwaggerDocketI() {
        return this.createDocket(swaggerProperties.getPackages().get("8") == null ? "I|xxx.xxx" : swaggerProperties.getPackages().get("8") + "|I");
    }

    @Bean
    public Docket SwaggerDocketJ() {
        return this.createDocket(swaggerProperties.getPackages().get("9") == null ? "J|xxx.xxx" : swaggerProperties.getPackages().get("9") + "|J");
    }


    /**
     * 创建swagger 目录
     * @author wangsong
     * @param groupNameOrPackage
     * @date 2021/7/28 0028 14:29
     * @return springfox.documentation.spring.web.plugins.Docket
     * @version 1.0.0
     */
    public Docket createDocket(Object groupNameOrPackage) {
        String groupName = getGroupName(groupNameOrPackage.toString());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.lplb.modules.pets")
                .apis(basePackage(getPackage(groupNameOrPackage.toString())))
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
     * 获取模块名称, 自动填充分区号,
     * @param groupNameOrPackage
     * @return
     */
    private String getGroupName(String groupNameOrPackage) {
        String[] split = groupNameOrPackage.split("\\|");
        // 获取名称,如果是yml配置获取,手动拼接上分区标签 A/B/C/D 等
        String groupName = split[0];
        if (split.length > 2) {
            groupName = split[2] + "--" + groupName;
        }
        return groupName.trim();
    }


    /**
     * 获取扫包路径，如果关闭了swagger或yml 没有配置当前分区,设置为一个不存在的包 xxx.xxx
     * @param groupNameOrPackage
     */
    private String getPackage(String groupNameOrPackage) {
        String[] split = groupNameOrPackage.split("\\|");
        // 获取包路径, 如果关闭了swagger, 让其扫码一个不存在的包
        String basePackage = split[1];
        if (!swaggerProperties.getIsShow()) {
            basePackage = "xxx.xxx";
        }
        return basePackage.trim();
    }


    /**
     * 添加社交信息
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
     * @author wangsong
     * @date 2021/1/20 0020 14:45
     * @return java.util.List<springfox.documentation.service.Parameter>
     * @version 1.0.0
     */
    private List<Parameter> getGlobalParameter() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        // 管理端默认账号
        parameterBuilder.name(swaggerProperties.getDefaultKey())
                .scalarExample(swaggerProperties.getDefaultValue())
                .description("请求头参数")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .order(-999)
                .required(false)
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

    // 定义分隔符
    private static final String splitor = ",";


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
