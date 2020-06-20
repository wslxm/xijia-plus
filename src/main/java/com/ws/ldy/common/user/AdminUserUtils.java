package com.ws.ldy.common.user;

import com.ws.ldy.admin.model.entity.UserAdmin;
import com.ws.ldy.common.result.ResultEnum;
import com.ws.ldy.config.error.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 查询系统相关的用户信息
 */
@Component
public class AdminUserUtils {
    /**
     * 管理端用户key前缀
     */
    public final static String ADMIN = "admin-";


    private static HttpServletRequest request;
    private static HttpSession session;

    @Autowired
    public void setHttpServletRequestBean(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    public void setHttpSessionBean(HttpSession session) {
        this.session = session;
    }

    //=================================================================
    //===========================获取方法===============================
    //=================================================================

    /**
     * TODO  获取系统用户token
     *
     * @return java.lang.String
     * @date 2020/6/20 0020 16:56
     */
    public static String getToken() {
        String token = request.getHeader(AdminUserUtils.ADMIN + "token");
        return token;
    }

    /**
     * TODO   获取系统用户完整数据
     *
     * @return com.ws.ldy.admin.model.entity.UserAdmin
     * @date 2020/6/20 0020 16:55
     */
    public static UserAdmin getUserAdmin() {
        String token = request.getHeader("token");
        UserAdmin userAdmin = (UserAdmin) session.getAttribute(AdminUserUtils.ADMIN + token);
        // 判断用户是否登录
        if (userAdmin == null) {
            throw new ErrorException(ResultEnum.ADMIN_IS_NO_LOGIN);
        }
        return userAdmin;
    }

    /**
     * TODO 获取系统用户Id
     *
     * @return java.lang.Integer
     * @date 2020/6/20 0020 16:55
     */
    public static Integer getId() {
        String token = request.getHeader("token");
        UserAdmin userAdmin = (UserAdmin) session.getAttribute(AdminUserUtils.ADMIN + token);
        // 判断用户是否登录
        if (userAdmin == null) {
            throw new ErrorException(ResultEnum.ADMIN_IS_NO_LOGIN);
        }
        return userAdmin.getId();
    }
}
