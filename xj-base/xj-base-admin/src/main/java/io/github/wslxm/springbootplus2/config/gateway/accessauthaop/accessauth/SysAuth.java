package io.github.wslxm.springbootplus2.config.gateway.accessauthaop.accessauth;


import io.github.wslxm.springbootplus2.common.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.common.cache.AuthCacheKeyUtil;
import io.github.wslxm.springbootplus2.common.cache.XjCacheUtil;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Authority;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  @author wangsong
 */
@Component
@Slf4j
public class SysAuth {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ConfigService configService;

    /**
     * 默认放行token, 让swagger可以访问接口
     */
    @Value("${swagger.defaultValue:xijia@123}")
    private String tokenDefaultValue;

    private final static String FALSE = "false";
    /**
     * 绝对放行接口，不受限于- 动态权限管理(勿随意配置)
     */
    private final List<String> URIS = new ArrayList<>();

    public SysAuth() {
        // 管理端登录接口
        URIS.add("/api/admin/sys/user/login");
    }


    /**
     * 登录授权认证
     * <>
     *     token认证，授权认证，
     *     没有Token 直接放行, 让请求接入权限认证, 需要授权的接口没有token当然是认证不过的啦
     *     需要授权的接口, 在token 中获取当前登录用户的权限, 当前用户没有当前请求的接口权限当然也是认证不过的啦
     *     // ===
     *     前端接口认证：暂无处理
     *     返回：
     *       - 如果需要登录, 返回的jwt存储的用户信息, 用于记录日志, 如果接口不需要登录,返回的 null
     *       - 如果登录过期或无接口权限,返回对应的错误信息，会直接返回到前端
     * </>
     */
    public Result<JwtUser> loginAuth() {
        // 1、是否为绝对放行接口,是直接放行
        String uri = request.getRequestURI();
        if (URIS.contains(uri)) {
            return Result.success(null);
        }
        // 2、是否被权限管理, 没有直接放行
        Map<String, Authority> authMap = XjCacheUtil.findAuthAllToMap();
        String cacheKey = AuthCacheKeyUtil.getAuthCacheKey(request.getMethod(), request.getRequestURI());
        if (!authMap.containsKey(cacheKey)) {
            return Result.success(null);
        }
        // 3、接口是否禁用，是直接返回禁用信息
        Authority adminAuthority = authMap.get(cacheKey);
        if (adminAuthority.getDisable().equals(Base.Disable.V1.getValue())) {
            //禁用
            return Result.error(ResultType.AUTHORITY_DISABLE);
        }
        // 请求同TOKEN值当为token 时直接放行
        String defaultValues = tokenDefaultValue.split("\\|")[0].trim();
        String headerTokenDefault = request.getHeader(JwtUtil.TOKEN);
        if (defaultValues.equals(headerTokenDefault)) {
            return Result.success(null);
        }

        // 4、登录/授权验证
        if (adminAuthority.getState().equals(Base.AuthorityState.V0.getValue())) {
            /**
             *  0- 无需登录 (不做任何处理)
             */
            return Result.success(null);
        } else if (adminAuthority.getState().equals(Base.AuthorityState.V1.getValue())) {
            /**
             *  1- 需登录 (能获取用户信息jwtUser 即成功)
             */
            Result<JwtUser> result = JwtUtil.getJwtUserR(request, response);
            if (!result.getCode().equals(ResultType.SYS_SUCCESS.getValue())) {
                // error
                return result;
            }
            return Result.success(result.getData());
        }
        // 不做任何处理
        return Result.success(null);
    }
}
