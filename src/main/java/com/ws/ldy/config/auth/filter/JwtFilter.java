package com.ws.ldy.config.auth.filter;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 接口登录授权
 *
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/10/28 0028 19:50 
 * @version 1.0.0
 */
@Component
@Slf4j
public class JwtFilter {

    // 接口权限
    @Autowired
    private AdminAuthorityService adminAuthorityService;

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
     * @param request
     * @param response
     */
    public R<JwtUser> doFilterInternal(HttpServletRequest request, HttpServletResponse response) {
        // 1、是否为绝对放行接口,是直接放行
        String uri = request.getRequestURI();
        if (BaseConstant.Sys.URIS.contains(uri)) {
            return R.success(null);
        }
        // 2、是否被权限管理, 没有直接放行，log.info("请求方式:{} 请求URL:{} ", request.getMethod(), request.getServletPath());
        if (!BaseConstant.Cache.AUTH_MAP.containsKey(uri)) {
            return R.success(null);
        }
        // 3、接口是否禁用，是直接返回禁用信息
        AdminAuthority adminAuthority = BaseConstant.Cache.AUTH_MAP.get(uri);
        if (adminAuthority.getDisable().equals(Enums.Base.Disable.DISABLE_1.getValue())) {
            //禁用
            return R.error(RType.AUTHORITY_DISABLE);
        }
        // 4、登录/授权验证
        if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_0.getValue())) {
            /**
             *  0- 无需登录 (不做任何处理)
             */
            return R.success(null);
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_1.getValue())) {
            /**
             *  1- 需登录 (能获取用户信息jwtUser 即成功)
             */
            R<JwtUser> result = this.getJwtUser(request, response);
            if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
                // error
                return result;
            }
            // 刷新token有效期
            JwtUser jwtUser = result.getData();
            this.refreshToken(response, jwtUser);
            return R.success(jwtUser);
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue())) {
            /**
             *  2- 需登录+授权 (100% 管理端才会进入, 验证用户信息的权限列表中是否存在当前接口，存在放行，不存在拦截返回无权限访问)
             */
            R<JwtUser> result = this.getJwtUser(request, response);
            if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
                // error
                return result;
            }
            // 判断权限
            JwtUser jwtUser = result.getData();
            if (jwtUser.getAuthList() == null || !jwtUser.getAuthList().contains(request.getRequestURI())) {
                return R.error(RType.AUTHORITY_NO_PERMISSION);
            }
            // 刷新token有效期
            this.refreshToken(response, jwtUser);
            return R.success(jwtUser);
        }
        return R.success(null);
    }


    /**
     * 获取登录信息，如过 token无效过期等，会进入对应的异常信息中
     * @param request
     * @param response
     * @return
     */
    private R<JwtUser> getJwtUser(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(BaseConstant.Sys.TOKEN);
        try {
            if (StringUtils.isNotBlank(token)) {
                // 正常就返回,不然就进入异常
                return R.success(JwtUtil.getJwtUser(token));
            }
            // 没有token
            return R.error(RType.AUTHORITY_NO_TOKEN);
        } catch (SignatureException ex) {
            // JWT签名与本地计算签名不匹配
            return R.error(RType.AUTHORITY_JWT_SIGN_ERROR);
        } catch (ExpiredJwtException ex) {
            // 登录过期
            return R.error(RType.AUTHORITY_LOGIN_EXPIRED);
        } catch (Exception e) {
            // JWT解析错误
            return R.error(RType.AUTHORITY_JWT_PARSING_ERROR);
        }
    }


    /**
     * 刷新 token, 每次的有效请求接口都将刷新 Token(同时刷新有效期 和 用户当前权限)
     * @param response
     * @param jwtUser
     * @return
     */
    private void refreshToken(HttpServletResponse response, JwtUser jwtUser) {
        // 如果是管理端登录,当用户权限数据发生改变,刷新权限
        if (jwtUser.getType().equals(Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())
                && !BaseConstant.Cache.AUTH_VERSION.equals(jwtUser.getAuthVersion())) {
            //
            List<String> authorityList = adminAuthorityService.findByUserIdaAndDisableFetchAuthority(jwtUser.getUserId());
            // 添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
            // 刷新权限数据
            jwtUser.setAuthList(authorityList);
            // 刷新版本号
            jwtUser.setAuthVersion(BaseConstant.Cache.AUTH_VERSION);
            log.info("用户ID:[{}] 用户名:[{}] 的权限数据已刷新", jwtUser.getUserId(), jwtUser.getFullName());
        }
        // 5、生成jwt
        String jwtToken = JwtUtil.createToken(jwtUser);
        // 放入Header
        response.setHeader(BaseConstant.Sys.TOKEN, jwtToken);
    }

}
