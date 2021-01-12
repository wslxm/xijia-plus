package com.ws.ldy.config.idempotent.aop;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.idempotent.annotation.XjApiIdempotent;
import com.ws.ldy.config.idempotent.annotation.XjIsApiIdempotent;
import com.ws.ldy.config.idempotent.util.XJIdempotentUtils;
import com.ws.ldy.enums.BaseConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

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
//@Component
public class XjApiIdempotentAop {


//    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
//    public Object aroundSave(ProceedingJoinPoint proceed) throws Throwable {
//        return run(proceed);
//    }


    /**
     * 执行幂等操作
     * @author wangsong
     * @param proceed
     * @date 2020/12/20 0020 18:33
     * @return java.lang.Object
     * @version 1.0.0
     */
    public static R run(ProceedingJoinPoint proceed, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // 获取请求参数
        MethodSignature signature = (MethodSignature) proceed.getSignature();
        XjApiIdempotent xjApiIdempotent = signature.getMethod().getAnnotation(XjApiIdempotent.class);
        if (xjApiIdempotent != null) {
            // 生成幂等token放入 Header
            String idempotentToken = XJIdempotentUtils.saveApiIdempotent();
            response.setHeader(BaseConstant.Sys.IDEMPOTENT_TOKEN, idempotentToken);
            return R.success();
            //return proceed.proceed();
        }

        XjIsApiIdempotent xjIsApiIdempotent = signature.getMethod().getAnnotation(XjIsApiIdempotent.class);
        if (xjIsApiIdempotent != null) {
            // 验证幂等token
            String idempotentToken = request.getHeader(BaseConstant.Sys.IDEMPOTENT_TOKEN);
            boolean result = XJIdempotentUtils.verificationApiIdempotent(idempotentToken);
            if (!result) {
                // 判断是否重复请求
                //throw new ErrorException(RType.SYS_IS_IDEMPOTENT);
                return R.error(RType.SYS_IS_IDEMPOTENT);
            }
        }
        return R.success();
        // return proceed.proceed();
    }
}
























