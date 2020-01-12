package com.ws.ldy.adminconsole.config;

import com.ws.ldy.adminconsole.dao.AuthorityAdminDao;
import com.ws.ldy.adminconsole.dao.RoleAuthAdminDao;
import com.ws.ldy.adminconsole.entity.AuthorityAdmin;
import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.admincore.annotation.LdyAuthority;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import com.ws.ldy.admincore.utils.SignUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO  URL权限, 验签（只针对 /public 接口），xss攻击防御，URL特殊字符转译，防盗链, 允许跨域，log4j接口调用日志记录
 *
 * @param 接口访问处理
 * @date 2019/11/25 0025 11:21
 * @return
 */
@SuppressWarnings("all")
@Component
@Aspect
public class Aop {

    private static final Logger log = LoggerFactory.getLogger(Aop.class);

    @Resource
    private AuthorityAdminDao authorityAdminDao;
    @Resource
    private RoleAuthAdminDao roleAuthAdminDao;

    /**
     * 环绕通知：可以实现前置通知，后置通知，返回通知，例外通知的所有功能
     * 拦截所有模块下的 controller 接口
     *
     * @param jp:指定的连接点（拦截的业务方法）
     * @return
     * @Around：环绕通知的注解，指定切点 adminconsole.controller
     * @Around("execution(* com.ws.ldy.adminconsole.controller.*.*(..))")
     * @Around("execution(* com.ws.ldy..*.*(..))")
     */
    @Around("execution(* com.ws.ldy.*.controller.*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        // 允许所有跨域请求访问接口
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 获取参数
        Object[] args = jp.getArgs();
        // 1、验签
        if (!this.checkSign(request)) {
            return ResponseData.error("401", "验签失败");
        }
        // 2、防盗链处理（服务器获取资源被非法盗用）
        if (!this.antiTheftChain(request)) {
            return ResponseData.error("403", "且勿非法盗用资源");
            // request.getRequestDispatcher("/static/error.png").forward(request, response);
        }
        // 3、url 权限判断 false 返回
        if (!this.roleAuthority(jp, request)) {
            return ResponseData.error("403", "没有权限");
        }
        // 4、XSS 攻击处理（html特殊字符处理）
        Object[] newArgs = this.xssDefense(args);
        // 5、调用业务逻辑，返回响应参数,jp.proceed传入修改过的参数
        Object obj = jp.proceed(newArgs);
        // 6、请求响应日志，接口调用日志记录
        this.accessRecordLog(request, args, obj);
        return obj;
    }

    /**
     * 验签
     */
    private Boolean checkSign(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        // 只对存在 /public 的开放接口进行验签，不存在直接返回true
        if(servletPath.indexOf("/public") == -1 ) {
            return true;
        }
        // 验签
        Map<String, String> verifyMap = SignUtil.toVerifyMap(request.getParameterMap(), false);
        if (!SignUtil.verify(verifyMap)) {
            return false;
        }
        return true;
    }

    /**
     * TODO   用户角色权限判断
     *
     * @param jp
     * @param request
     * @return void
     * @date 2019/11/25 0025 11:20
     */
    private Boolean roleAuthority(ProceedingJoinPoint jp, HttpServletRequest request) {
        // 1、判断用户是否登录，未登陆前不验证授权
        UserAdmin user = (UserAdmin) request.getSession().getAttribute("user");
        if (user == null) {
            return true;
        }
        // 2、获取方法, 在获取权限注解
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        LdyAuthority annotation = targetMethod.getAnnotation(LdyAuthority.class);
        // 3、无权限注解直接放行
        if (annotation == null) {
            return true;
        }
        // 4、查询角色当前权限并把角色权限url 权限放入map容器（此处应添加缓存）
        List<AuthorityAdmin> list = authorityAdminDao.findUserIdRoleAuthority(user.getId());
        Map<String, AuthorityAdmin> map = new HashMap<>();
        list.forEach(item -> map.put(item.getName(), item));
        // 5、获取接口权限名称，判断是否有权限
        String authName = annotation.value()[0];
        if (map.containsKey(authName.trim())) {
            return true;
        } else {
            //无权限
            return false;
        }
    }


    /**
     * TODO  xss 攻击防御, <script> 参数转换 html 格式  +
     * TODO  URL特殊字符转码，参考：https://blog.csdn.net/qq_41463655/article/details/103390542
     * <p>
     * <p>
     * 1、api 接口携带 <script> 参数，返回页面输出展示参数
     * 2、如评论回复，评论 <script>alert('sss')</script> ，进入页面就会弹alert框
     * 3、如评论回复，评论 <script>window.location.href='http://www.itmayiedu.com';</script> ，进入页面会直接跳转页面
     * <p>
     * 参数转换前： <script>alert('sss')</script>   参数转换后：  &lt;script&gt;alert('sss')&lt;/script&gt;
     */
    private Object[] xssDefense(Object[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                //  特殊字符转码 如url的 + 号
                //  URLEncoder.encode 将特殊字符转译
                //  URLDecoder.decode 将转译的特殊字符还原
                args[i] = StringEscapeUtils.escapeHtml(URLDecoder.decode(args[i].toString()));
            }
        }
        return args;
    }


    /**
     * 防盗链处理
     *
     * “如果是直接在浏览器里输入有referer的页面,返回是null,也就是说referer只有从别的页面点击连接来到这页的才会有内容。
     * referer为NULL就是手工,非null就是连接过来的。刷新的时候,会检查服务端是否会有更新,
     * 没有的话,则使用本机的缓存,也就是说,你刷新时得到的响应依然是 前一次...”
     */
    private Boolean antiTheftChain(HttpServletRequest request) {
        //获得url 来源,request.getServerName()，相当于当前服务器来源，ip 或 请求地址
        String referer = request.getHeader("referer");
        if (referer == null) {
            //允许url直接访问
            return true;
        }
        if (!referer.contains(request.getServerName())) {
            return false;
        }
        //System.out.println("refer is" + "" + referer);
        return true;
    }


    /**
     * 请求响应日志（接口调用记录）
     *
     * @param request
     * @param args    请求参数
     * @param obj     响应参数
     */
    private void accessRecordLog(HttpServletRequest request, Object[] args, Object obj) {
        log.info("URL:[{}] --> REQUEST:[{}] \r\n---------------------------> RESPONSE:[{}]", request.getServletPath(), args, obj);
    }
}
