package com.ws.ldy.config.aspect.gateway;


import com.ws.ldy.common.cache.BaseCache;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.sys.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.sys.admin.service.AdminAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SysAuth {

    /**
     * 接口权限
     */
    @Autowired
    private AdminAuthorityService adminAuthorityService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    /**
     * 绝对放行接口，不受限于- 动态权限管理(勿随意配置)
     */
    List<String> URIS = new ArrayList<String>() {{
        add("/api/admin/adminUser/login");          // 管理端登录接口
        add("/api/admin/adminRole/updRoleAuthAll"); // 给所有角色分配所有权限
    }};


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
    public R<JwtUser> loginAuth() {
        // 1、是否为绝对放行接口,是直接放行
        String uri = request.getRequestURI();
        if (URIS.contains(uri)) {
            return R.success(null);
        }
        // 2、是否被权限管理, 没有直接放行，log.info("请求方式:{} 请求URL:{} ", request.getMethod(), request.getServletPath());
        if (!BaseCache.AUTH_MAP.containsKey(uri)) {
            return R.success(null);
        }
        // 3、接口是否禁用，是直接返回禁用信息
        AdminAuthority adminAuthority = BaseCache.AUTH_MAP.get(uri);
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
            R<JwtUser> result = JwtUtil.getJwtUserR(request);
            if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
                // error
                return result;
            }
            // 刷新token有效期
            JwtUser jwtUser = result.getData();
            this.refreshToken(jwtUser);
            return R.success(jwtUser);
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue())) {
            /**
             *  2- 需登录+授权 (100% 管理端才会进入, 验证用户信息的权限列表中是否存在当前接口，存在放行，不存在拦截返回无权限访问)
             */
            R<JwtUser> result = JwtUtil.getJwtUserR(request);
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
            this.refreshToken(jwtUser);
            return R.success(jwtUser);
        }
        return R.success(null);
    }


    /**
     * 刷新 token, 每次的有效请求接口都将刷新 Token(同时刷新有效期 和 用户当前权限)
     * <p>
     *    用于每次请求后刷新token有效期, 用户登录授权时刷新返回token
     * <p/>
     * @param jwtUser
     * @return
     */
    public void refreshToken(JwtUser jwtUser) {
        // 如果是管理端登录,当用户权限数据发生改变,刷新权限
        if (jwtUser.getType().equals(JwtUtil.userType[0])
                && !BaseCache.AUTH_VERSION.equals(jwtUser.getAuthVersion())) {
            List<String> authorityList = adminAuthorityService.findByUserIdaAndDisableFetchAuthority(jwtUser.getUserId());
            //添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
            // 刷新权限数据
            jwtUser.setAuthList(authorityList);
            // 刷新版本号
            jwtUser.setAuthVersion(BaseCache.AUTH_VERSION);
            log.info("用户ID:[{}] 用户名:[{}] 的权限数据已刷新", jwtUser.getUserId(), jwtUser.getFullName());
        }
        // 5、生成jwt
        String jwtToken = JwtUtil.createToken(jwtUser, response);
        // 放入Header
        response.setHeader(JwtUtil.TOKEN, jwtToken);
    }
}
