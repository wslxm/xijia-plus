package io.github.wslxm.springbootplus2.config.aspect.gateway;

import io.github.wslxm.springbootplus2.common.annotation.ApiIdempotentAuth;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.common.annotation.ApiIdempotent;
import io.github.wslxm.springbootplus2.common.idempotent.util.XjIdempotentUtils;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 幂等处理AOP
 *
 * @author wangsong
 * @version 1.0.1
 * @date 2020/12/19 0019 17:16
 */
@Component
public class SysIdempotent {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /**
     * 幂等 token 名称
     */
    private static String IDEMPOTENT_TOKEN = "IDEMPOTENT_TOKEN";


    /**
     * 执行幂等操作
     *
     * @param proceed proceed
     * @return java.lang.Object
     * @author wangsong
     * @date 2020/12/20 0020 18:33
     * @version 1.0.1
     */
    public R run(ProceedingJoinPoint proceed) {
        // 获取请求参数
        MethodSignature signature = (MethodSignature) proceed.getSignature();
        ApiIdempotent xjApiIdempotent = signature.getMethod().getAnnotation(ApiIdempotent.class);
        if (xjApiIdempotent != null) {
            // 生成幂等token放入 Header
            String idempotentToken = XjIdempotentUtils.saveApiIdempotent();
            response.setHeader(IDEMPOTENT_TOKEN, idempotentToken);
            return R.success();
        }
        ApiIdempotentAuth xjIsApiIdempotent = signature.getMethod().getAnnotation(ApiIdempotentAuth.class);
        if (xjIsApiIdempotent != null) {
            // 验证幂等token
            String idempotentToken = request.getHeader(IDEMPOTENT_TOKEN);
            boolean result = XjIdempotentUtils.verificationApiIdempotent(idempotentToken);
            if (!result) {
                // 判断是否重复请求 9901
                throw new ErrorException(RType.SYS_IS_IDEMPOTENT);
            }
        }
        return R.success();
    }
}
























