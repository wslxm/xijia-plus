package com.ws.ldy.config.swagger;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.modules.admin.model.vo.AdminUserVO;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.admin.service.AdminUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class SwaggerConfig {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminAuthorityService adminAuthorityService;




    @Bean
    public Docket baseApi() {  //swagger.ui-config.operations-sorter=method
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("BASE--通用接口")
                .globalOperationParameters(getGlobalParameter(1))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ws.ldy.others"))  // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("BASE--通用接口")
                        .description("BASE--通用接口 Swagger API 文档")
                        .termsOfServiceUrl("https://gitee.com/wslxm/spring-boot-plus2")
                        .version("1.0.0")
                        .contact(new Contact("王松", "https://gitee.com/wslxm/spring-boot-plus2", "1270696548@qq.com"))
                        .build());
    }


    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ADMIN--系统模块")
                .globalOperationParameters(getGlobalParameter(1))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ws.ldy.modules.admin"))   // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("ADMIN--系统模块")
                        .description("ADMIN--系统模块Swagger API 文档")
                        .termsOfServiceUrl("https://gitee.com/wslxm/spring-boot-plus2")
                        .version("1.0.0")
                        .contact(new Contact("王松", "https://gitee.com/wslxm/spring-boot-plus2", "1270696548@qq.com"))
                        .build());
    }


    @Bean
    public Docket yaBeiAdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("食谱API")
                .globalOperationParameters(getGlobalParameter(1))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ws.ldy.modules.caipu.controller"))   // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("食谱API")
                        .description("食谱API Swagger API 文档")
                        .termsOfServiceUrl("https://gitee.com/wslxm/spring-boot-plus2")
                        .version("1.0.0")
                        .contact(new Contact("王松", "https://gitee.com/wslxm/spring-boot-plus2", "1270696548@qq.com"))
                        .build());
    }



    /**
     * swagger全局header参数添加
     * type = 1 管理端  2 医生端
     */
    private List<Parameter> getGlobalParameter(Integer type) {
        if (type == 1) {
            ParameterBuilder parameterBuilder = new ParameterBuilder();
            parameterBuilder
                    .name(BaseConstant.Sys.TOKEN) // key
                    .scalarExample(getAdminUserToken("10000"))    //用户端账号 -->     value 默认token值
                    .description("请求头参数")      // 描叙
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .order(-1)
                    .required(false)
                    .build();
            List<Parameter> parameters = Lists.newArrayList();
            parameters.add(parameterBuilder.build());
            return parameters;
        } else if (type == 2) {
            ParameterBuilder parameterBuilder = new ParameterBuilder();
            parameterBuilder
                    .name(BaseConstant.Sys.TOKEN) // key
                    .scalarExample(null)    //用户端账号 -->     value 默认token值
                    .description("请求头参数")      // 描叙
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .order(-1)
                    .required(false)
                    .build();
            List<Parameter> parameters = Lists.newArrayList();
            parameters.add(parameterBuilder.build());
            return parameters;
        }
        return null;
    }


    /**
     * 获取系统超管账号，默认swagger token
     * @param username
     * @return
     */
    public String getAdminUserToken(String username) {
        // 1、判断账号
        AdminUser user = adminUserService.getOne(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username));
        if (user == null) {
            log.info("管理端账号注入swagger 失败，没有获取到账号信息");
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        // 登录成功
        // 4、获取权限列表,保存权限-未禁用,管理端(登录+认证的)
        List<String> authList = adminAuthorityService.findByUserIdaAndDisableFetchAuthority(user.getId());
        // 5、生成jwt
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(user.getId());
        jwtUser.setUsername(user.getUsername());
        jwtUser.setFullName(user.getFullName());
        jwtUser.setType(Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue());
        jwtUser.setHead(user.getHead());
        jwtUser.setPhone(user.getPhone());
        // 添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
        jwtUser.setAuthList(authList);
        jwtUser.setAuthVersion(BaseConstant.Cache.AUTH_VERSION);
        String jwtToken = JwtUtil.createToken(jwtUser);
        log.info("管理端账号注入swagger 默认token成功，当前账号为:{} 账号姓名为：{} " + user.getUsername(), user.getFullName());
        return jwtToken;
    }


}
