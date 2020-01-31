package com.ws.ldy.adminconsole.template.impl;

import com.ws.ldy.adminconsole.dao.AuthorityAdminDao;
import com.ws.ldy.adminconsole.entity.AuthorityAdmin;
import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.adminconsole.template.CheckAopFilter;
import com.ws.ldy.admincore.annotation.LdyAuthority;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * TODO  3、Url 权限判断
  * @author 王松
  * @mail  1720696548@qq.com
  * @date  2020/1/13 0013 20:43
  */
@Component
public class RoleAuthorityFilter extends CheckAopFilter {
    @Resource
    private AuthorityAdminDao authorityAdminDao;

    @Override
    public ResponseData start(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        // 1、判断用户是否登录，未登陆前不验证授权
        UserAdmin user = (UserAdmin) request.getSession().getAttribute("user");
        if (user == null) {
            //用户未登录直接放行
            return ResponseData.success(0);
        }
        // 2、获取方法, 在获取权限注解，jp=aop通知拦截获取的请求信息
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 获取请求方法
        Method targetMethod = methodSignature.getMethod();
        // 获取接口上的LdyAuthority注解
        LdyAuthority annotation = targetMethod.getAnnotation(LdyAuthority.class);
        // 3、无权限注解直接放行
        if (annotation == null) {
            return ResponseData.success(0);
        }
        // 4、查询角色当前权限并把角色权限url 权限放入map容器（此处应添加缓存）
        List<AuthorityAdmin> list = authorityAdminDao.findUserIdRoleAuthority(user.getId());
        Map<String, AuthorityAdmin> map = new HashMap<>();
        list.forEach(item -> map.put(item.getName(), item));
        // 5、获取接口权限名称，判断是否有权限
        String authName = annotation.value()[0];
        if (map.containsKey(authName.trim())) {
            return ResponseData.success(0);
        } else {
            //无权限
            return ResponseData.error("403", "没有访问权限");
        }
    }
}