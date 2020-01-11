package com.ws.ldy.adminconsole.config;

import com.ws.ldy.adminconsole.dao.AuthorityAdminDao;
import com.ws.ldy.adminconsole.dao.RoleAuthAdminDao;
import com.ws.ldy.adminconsole.entity.AuthorityAdmin;
import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.admincore.annotation.LdyAuthority;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import com.ws.ldy.admincore.utils.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @param 授权
 * @date 2019/11/25 0025 11:21
 * @return
 */
@SuppressWarnings("all")
@Component
@Aspect
public class AopAuthority {

    @Resource
    private AuthorityAdminDao authorityAdminDao;
    @Resource
    private RoleAuthAdminDao roleAuthAdminDao;

    /**
     * 环绕通知：可以实现前置通知，后置通知，返回通知，例外通知的所有功能
     *
     * @param jp:指定的连接点（拦截的业务方法）
     * @return
     * @Around：环绕通知的注解，指定切点
     */
    @Around("execution(* com.ws.ldy.adminconsole.controller.*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint jp) {
        //获取方法
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        LdyAuthority annotation = targetMethod.getAnnotation(LdyAuthority.class);
        //获取参数
        Object[] args = jp.getArgs();
        Object obj = null;
        try {
            //判断方法上是否存在权限注解，有判断当前角色是否有权限
            if(annotation == null || roleAuthority()){
                //无权限注解，或者有权限//obj = 返回值
                obj = jp.proceed();
            }  else{
                //输出错误提示
                HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json; charset=utf-8");
                ResponseData responseData = ResponseData.error("403", "没有权限");
                PrintWriter writer = response.getWriter();
                writer.write(JsonUtil.getJsonString(responseData));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * TODO   授权
     *
     * @return void
     * @date 2019/11/25 0025 11:20
     */
    private Boolean roleAuthority() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //接口
        String servletPath = request.getServletPath();
        // 当前用户,未登陆前不验证授权
        UserAdmin user = (UserAdmin) request.getSession().getAttribute("user");
        if(user == null){
            return true;
        }
        //角色权限
        List<AuthorityAdmin> list = authorityAdminDao.findUserIdRoleAuthority(user.getId());
        Map<String, AuthorityAdmin> map = new HashMap<>();
        list.forEach(item -> map.put(item.getUrl(), item));
        //判断权限
        String[] splitUrl = servletPath.split("\\/");
        String upUrl =  servletPath;
        //url带{} 参数的处理
        if (isInteger(splitUrl[splitUrl.length - 1])) {
            int index = servletPath.lastIndexOf("/");
            upUrl = servletPath.substring(0,index);
        }
        if(map.containsKey(upUrl)){
            System.out.println( "---->  " +servletPath );
            return true;
        }else{
            System.out.println( "---->  " +servletPath  +"   is no authority");
            return false;
        }
    }


    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
