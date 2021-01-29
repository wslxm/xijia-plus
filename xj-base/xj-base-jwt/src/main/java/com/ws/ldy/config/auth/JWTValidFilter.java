//package com.ws.ldy.config.auth;
//
//import com.ws.ldy.common.result.RType;
//import com.ws.ldy.config.auth.entity.JwtUser;
//import com.ws.ldy.config.auth.util.JwtUtil;
//import com.ws.ldy.config.error.ErrorException;
//import com.ws.ldy.enums.BaseConstant;
//import com.ws.ldy.enums.Enums;
//import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
//import com.ws.ldy.modules.admin.model.vo.AdminUserVO;
//import com.ws.ldy.modules.admin.service.AdminAuthorityService;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.SignatureException;
//import lombok.extern.slf4j.Slf4j;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//
///**
// *  jwt --> token 信息认证
// *
// * @author 王松
// * @mail 1720696548@qq.com
// * @date 2020/7/5 0005 17:29
// */
//@SuppressWarnings("unchecked")
//@Slf4j
//public class JWTValidFilter extends BasicAuthenticationFilter {
//
//    // 异常处理类
//    private HandlerExceptionResolver resolver;
//
//    // 权限server
//    private AdminAuthorityService adminAuthorityService;
//
//    /**
//     * SecurityConfig 配置中创建该类实例
//     */
//    public JWTValidFilter(AuthenticationManager authenticationManager, HandlerExceptionResolver resolver, AdminAuthorityService adminAuthorityService) {
//        // 获取授权管理
//        super(authenticationManager);
//        // 获取异常处理类
//        this.resolver = resolver;
//        // 获取权限server
//        this.adminAuthorityService = adminAuthorityService;
//    }
//
//
//    /**
//     * 拦截请求
//     * <>
//     *     token认证，授权认证，
//     *     没有Token 直接放行, 让请求接入权限认证, 需要授权的接口没有token当然是认证不过的啦
//     *     需要授权的接口, 在token 中获取当前登录用户的权限, 当前用户没有当前请求的接口权限当然也是认证不过的啦
//     *     // ===
//     *     前端接口认证：暂无处理
//     * </>
//     *
//     * @param request
//     * @param response
//     * @param chain
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // 1、是否为绝对放行接口,是直接放行
//        String uri = request.getRequestURI();
//        if (BaseConstant.Sys.URIS.contains(uri)) {
//            super.doFilterInternal(request, response, chain);
//            return;
//        }
//        // 2、是否被权限管理, 没有直接放行，log.info("请求方式:{} 请求URL:{} ", request.getMethod(), request.getServletPath());
//        if (!BaseConstant.Cache.AUTH_MAP.containsKey(uri)) {
//            super.doFilterInternal(request, response, chain);
//            return;
//        }
//        // 3、接口是否禁用，是直接返回禁用信息
//        AdminAuthority adminAuthority = BaseConstant.Cache.AUTH_MAP.get(uri);
//        if (adminAuthority.getDisable().equals(Enums.Base.Disable.DISABLE_1.getValue())) {
//            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_DISABLE));
//            return;
//        }
//        // 4、登录/授权验证
//        if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_0.getValue())) {
//            /**
//             *  0- 无需登录 (不做任何处理)
//             */
//        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_1.getValue())) {
//            /**
//             *  1- 需登录 (能获取用户信息jwtUser 即成功)
//             */
//            JwtUser jwtUser = this.getJwtUser(request, response);
//            if (jwtUser == null) {
//                return;
//            }
//            // 刷新token有效期
//            this.refreshToken(response, jwtUser);
//        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue())) {
//            /**
//             *  2- 需登录+授权 (100% 管理端才会进入, 验证用户信息的权限列表中是否存在当前接口，存在放行，不存在拦截返回无权限访问)
//             */
//            JwtUser jwtUser = this.getJwtUser(request, response);
//            if (jwtUser == null) {
//                return;
//            }
//            if (jwtUser.getAuthList() == null || !jwtUser.getAuthList().contains(request.getRequestURI())) {
//                resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_NO_PERMISSION));
//                return;
//            }
//            // 刷新token有效期
//            this.refreshToken(response, jwtUser);
//        }
//        //  执行成功，向下走
//        super.doFilterInternal(request, response, chain);
//    }
//
//
//    /**
//     * 获取登录信息，如过 token无效过期等，会进入对应的异常信息中
//     * @param request
//     * @param response
//     * @return
//     */
//    public JwtUser getJwtUser(HttpServletRequest request, HttpServletResponse response) {
//        String token = request.getHeader(BaseConstant.Sys.TOKEN);
//        try {
//            if (StringUtils.isNotBlank(token)) {
//                // 正常就返回,不然就进入异常
//                return JwtUtil.getJwtUser(token);
//            }
//            // 没有token
//            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_NO_TOKEN));
//        } catch (SignatureException ex) {
//            // JWT签名与本地计算签名不匹配
//            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_JWT_SIGN_ERROR));
//        } catch (ExpiredJwtException ex) {
//            // 登录过期
//            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_LOGIN_EXPIRED));
//        } catch (Exception e) {
//            // JWT解析错误
//            resolver.resolveException(request, response, null, new ErrorException(RType.AUTHORITY_JWT_PARSING_ERROR));
//        }
//        return null;
//    }
//
//
//    /**
//     * 重新生成 token，每次的有效请求接口都将刷新 Token(同时刷新有效期 和 用户当前权限)
//     * @param response
//     * @param jwtUser
//     * @return
//     */
//    public void refreshToken(HttpServletResponse response, JwtUser jwtUser) {
//        // 如果是管理端登录,当用户权限数据发生改变,刷新权限
//        if (jwtUser.getType().equals(Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())
//                && !BaseConstant.Cache.AUTH_VERSION.equals(jwtUser.getAuthVersion())) {
//            //
//            List<String> authorityList = adminAuthorityService.findByUserIdaAndDisableFetchAuthority(jwtUser.getUserId());
//            // 添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
//            // 刷新权限数据
//            jwtUser.setAuthList(authorityList);
//            // 刷新版本号
//            jwtUser.setAuthVersion(BaseConstant.Cache.AUTH_VERSION);
//            log.info("用户ID:[{}] 用户名:[{}] 的权限数据已刷新", jwtUser.getUserId(), jwtUser.getFullName());
//        }
//        // 5、生成jwt
//        String jwtToken = JwtUtil.createToken(jwtUser);
//        // 放入Header
//        response.setHeader(BaseConstant.Sys.TOKEN, jwtToken);
//    }
//}
