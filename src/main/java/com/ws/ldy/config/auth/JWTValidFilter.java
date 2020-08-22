package com.ws.ldy.config.auth;

import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.modules.admin.model.vo.AdminUserVO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *  jwt --> token 信息认证
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/5 0005 17:29
 */
@SuppressWarnings("unchecked")
@Slf4j
public class JWTValidFilter extends BasicAuthenticationFilter {


    // 异常处理类
    private HandlerExceptionResolver resolver;

    /**
     * SecurityConfig 配置中创建该类实例
     */
    public JWTValidFilter(AuthenticationManager authenticationManager, HandlerExceptionResolver resolver) {
        // 获取授权管理
        super(authenticationManager);
        // 获取异常处理类
        this.resolver = resolver;
    }


    /**
     * 拦截请求
     * <>
     *     token认证，授权认证，
     *     没有Token 直接放行, 让请求接入权限认证, 需要授权的接口没有token当然是认证不过的啦
     *     需要授权的接口, 在token 中获取当前登录用户的权限, 当前用户没有当前请求的接口权限当然也是认证不过的啦
     *     //===
     *     前端接口认证：暂无处理
     * </>
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 是否被权限管理,没有直接放行，log.info("请求方式:{} 请求URL:{} ", request.getMethod(), request.getServletPath());
        String uri = request.getRequestURI();
        if (!BaseConstant.Cache.AUTH_MAP.containsKey(uri)) {
            super.doFilterInternal(request, response, chain);
            return;
        }

        // 是否禁用 (抛出异常)
        AdminAuthority adminAuthority = BaseConstant.Cache.AUTH_MAP.get(uri);
        if (adminAuthority.getDisable().equals(Enums.Base.Disable.DISABLE_1.getValue())) {
            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_DISABLE));
            return;
        }

        if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_0.getValue())) {
            // 0- 无需登录
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_1.getValue())) {
            // 1- 需登录
            AdminUserVO userVO = this.loginVerification(request, response);
            if (userVO == null) {
                return;
            }
            this.refreshToken(response, userVO);
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue())) {
            // 2- 需登录+授权
            AdminUserVO userVO = this.loginVerification(request, response);
            if (userVO == null) {
                return;
            }
            if (!userVO.getAuthList().contains(request.getRequestURI())) {
                resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_NO_PERMISSION));
                return;
            }
            this.refreshToken(response, userVO);
        }
        //  执行成功，向下走
        super.doFilterInternal(request, response, chain);
    }


    /**
     * 获取登录信息，如过 token无效过期等，会进入对应的异常信息中
     * @param request
     * @param response
     * @return
     */
    public AdminUserVO loginVerification(HttpServletRequest request, HttpServletResponse response) {
        try {
            return JwtUtil.getUser(request.getHeader(BaseConstant.Sys.TOKEN));
        } catch (SignatureException ex) {
            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_JWT_SIGN_ERROR));
        } catch (ExpiredJwtException ex) {
            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_LOGIN_EXPIRED));
        } catch (Exception e) {
            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_JWT_PARSING_ERROR));
        }
        return null;
    }

    /**
     * 重新生成token，每次的有效请求接口都将刷新 Token(同时刷新有效期 和 用户当前权限)
     * @param response
     * @param userVo
     * @return
     */
    public void refreshToken(HttpServletResponse response, AdminUserVO userVo) {
        // 获取原数据
        //AdminUserVO userVo = JwtUtil.getUser(request.getHeader(BaseConstant.Sys.TOKEN));
        // 用户数据
        AdminUser adminUser = BeanDtoVoUtil.convert(userVo, AdminUser.class);
        // 权限数据
        List<String> authList = userVo.getAuthList();
        // 生成token
        String newToken = JwtUtil.createToken(adminUser, authList);
        // 放入Header
        response.setHeader(BaseConstant.Sys.TOKEN, newToken);
    }
}
