//package com.ws.ldy.common.user;
//
//import com.ws.ldy.common.result.ResultEnum;
//import com.ws.ldy.config.error.ErrorException;
//import com.ws.ldy.config.auth.util.JwtUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
///**
// * 查询系统相关的用户信息
// */
//@Component
//public class AdminUserUtils {
//
//    private static HttpServletRequest request;
//    private static HttpSession session;
//
//    @Autowired
//    public void setHttpServletRequestBean(HttpServletRequest request) {
//        this.request = request;
//    }
//
//    @Autowired
//    public void setHttpSessionBean(HttpSession session) {
//        this.session = session;
//    }
//
//    //=================================================================
//    //===========================获取方法===============================
//    //=================================================================
//
//    /**
//     * TODO  获取系统用户token
//     *
//     * @return java.lang.String
//     * @date 2020/6/20 0020 16:56
//     */
//    public static String getToken() {
//        String token = request.getHeader("token");
//        if (StringUtils.isBlank(token) && !"null".equals(token)) {
//            //没有登录
//            throw new ErrorException(ResultEnum.ADMIN_IS_NO_LOGIN);
//        }
//        return token;
//    }
//
//    /**
//     * TODO   获取系统用户Id
//     *
//     * @return com.ws.ldy.admin.model.entity.UserAdmin
//     * @date 2020/6/20 0020 16:55
//     */
//    public static String getUserId() {
//        // 判断用户是否登录
//        return JwtUtil.getUserId(getToken());
//    }
//
//
//    /**
//     * TODO   获取系统用户名
//     *
//     * @return com.ws.ldy.admin.model.entity.UserAdmin
//     * @date 2020/6/20 0020 16:55
//     */
//    public static String getUsername() {
//        // 判断用户是否登录
//        return JwtUtil.getUsername(getToken());
//    }
//
//    /**
//     * TODO 获取用户权限列表
//     *
//     * @return java.lang.Integer
//     * @date 2020/6/20 0020 16:55
//     */
//    public static List<SimpleGrantedAuthority> getRoles() {
//        // 判断用户是否登录
//        return JwtUtil.getUserAuth(getToken());
//    }
//}
