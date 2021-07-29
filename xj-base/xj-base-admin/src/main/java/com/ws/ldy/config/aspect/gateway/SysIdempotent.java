package com.ws.ldy.config.aspect.gateway;

import com.ws.ldy.common.annotation.ApiIdempotent;
import com.ws.ldy.common.annotation.ApiIdempotentAuth;
import com.ws.ldy.common.idempotent.util.XJIdempotentUtils;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.error.ErrorException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 幂等处理AOP
 * @author wangsong
 * @date 2020/12/19 0019 17:16
 * @return
 * @version 1.0.0
 */
//@Slf4j
//@Aspect
@Component
public class SysIdempotent {


    // @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    // public Object aroundSave(ProceedingJoinPoint proceed) throws Throwable {
    //     // 获取请求参数
    //     ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    //     HttpServletRequest request = sra.getRequest();
    //     HttpServletResponse response = sra.getResponse();
    //     return run(proceed, request, response);
    // }

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /**
     *  幂等 token 名称
     */
    private static String IDEMPOTENT_TOKEN = "IDEMPOTENT_TOKEN";


    /**
     * 执行幂等操作
     * @author wangsong
     * @param proceed
     * @date 2020/12/20 0020 18:33
     * @return java.lang.Object
     * @version 1.0.0
     */
    public R run(ProceedingJoinPoint proceed) {
        // 获取请求参数
        MethodSignature signature = (MethodSignature) proceed.getSignature();
        ApiIdempotent xjApiIdempotent = signature.getMethod().getAnnotation(ApiIdempotent.class);
        if (xjApiIdempotent != null) {
            // 生成幂等token放入 Header
            String idempotentToken = XJIdempotentUtils.saveApiIdempotent();
            response.setHeader(IDEMPOTENT_TOKEN, idempotentToken);
            return R.success();
            // return proceed.proceed();
        }
        //
        ApiIdempotentAuth xjIsApiIdempotent = signature.getMethod().getAnnotation(ApiIdempotentAuth.class);
        if (xjIsApiIdempotent != null) {
            // 验证幂等token
            String idempotentToken = request.getHeader(IDEMPOTENT_TOKEN);
            boolean result = XJIdempotentUtils.verificationApiIdempotent(idempotentToken);
            if (!result) {
                // 判断是否重复请求
                // 9901
                throw new ErrorException(RType.SYS_IS_IDEMPOTENT);
                // return R.error(9901, "重复请求");
            }
        }
        return R.success();
        // return proceed.proceed();
    }
}
























