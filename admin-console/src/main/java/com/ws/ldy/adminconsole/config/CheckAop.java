package com.ws.ldy.adminconsole.config;

import com.ws.ldy.adminconsole.dao.RoleAuthAdminDao;
import com.ws.ldy.adminconsole.factory.CheckAopContext;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
  * TODO  URL权限, 验签（只针对 /public 接口），xss攻击防御，URL特殊字符转译，防盗链, 允许跨域，log4j接口调用日志记录
  * @author 王松
  * @mail  1720696548@qq.com
  * @date  2020/1/13 0013 20:20
  */
@SuppressWarnings("all")
@Component
@Aspect
public class CheckAop {

    private static final Logger log = LoggerFactory.getLogger(CheckAop.class);

    @Resource
    private RoleAuthAdminDao roleAuthAdminDao;
    @Resource
    private CheckAopContext checkAopContext;

    /**
     * TODO  拦截所有模块下的 controller 接口, 环绕通知：可以实现前置通知，后置通知，返回通知，例外通知的所有功能
     * @Around("execution(* com.ws.ldy.adminconsole.controller.*.*(..))")
     * @Around("execution(* com.ws.ldy..*.*(..))")
     *
     * @author 王松
     * @mail  1720696548@qq.com
     * @param jp 指定的连接点（拦截的业务方法）
     * @date  2020/1/13 0013 20:18
     * @return java.lang.Object
     */
    @Around("execution(* com.ws.ldy.*.controller.*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        // 允许所有跨域请求访问接口
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader("Access-Control-Allow-Origin", "*");
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取请求参数
        Object[] args = jp.getArgs();
        // 开始验证 URL权限, 验签（只针对 /public 接口），xss攻击防御，URL特殊字符转译，防盗链,日志记录
        ResponseData responseData = checkAopContext.Check(jp, request, args);
        if(responseData.getCode().equals("0")){
            //成功返回具体参数，不需要二次封装responseData对象
            return responseData.getData();
        }else{
            //错误返回
            return responseData;
        }
    }
}
