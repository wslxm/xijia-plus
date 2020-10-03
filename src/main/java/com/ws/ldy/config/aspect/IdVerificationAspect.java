package com.ws.ldy.config.aspect;//package com.ws.ldy.config.aspect;
//
//import com.ws.ldy.common.result.RType;
//import com.ws.ldy.common.utils.JsonUtil;
//import com.ws.ldy.config.error.ErrorException;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
///**
// * ID 参数验证器
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2020/7/20 0020 9:12
// * @version 1.0.0
// */
//@Component
//@Aspect
//@Slf4j
//public class IdVerificationAspect {
//
//
//    /**
//     * insert 方法判断ID（不传Id, 空字符串也不允许）
//     * @param proceed
//     * @return
//     * @throws Throwable
//     */
//    @Around("execution(* com.ws.ldy.*..*.insert(..))")
//    public Object insert(ProceedingJoinPoint proceed) throws Throwable {
//        Object id = JsonUtil.parseMap(proceed.getArgs()[0]).get("id");
//        // log.info("判断add方法是否传递Id参数 ==> id:{}", id);
//        if (id != null) {
//            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
//        }
//        return proceed.proceed();
//    }
//
//
//    /**
//     * upd 方法判断ID（必传Id，不能传递空字符串）
//     * @param proceed
//     * @return
//     * @throws Throwable
//     */
//    @Around("execution(* com.ws.ldy.*..*.upd(..))")
//    public Object upd(ProceedingJoinPoint proceed) throws Throwable {
//        Object id = JsonUtil.parseMap(proceed.getArgs()[0]).get("id");
//        // log.info("判断upd方法是否传递Id参数 ==> id:{}", id);
//        if (id == null || "".equals(id)) {
//            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
//        }
//        return proceed.proceed();
//    }
//
//
//    /**
//     * 判断 findId 方法ID参数（必传Id，不能传递空字符串）
//     * @param proceed
//     * @return
//     * @throws Throwable
//     */
//    @Around("execution(* com.ws.ldy.*..*.findId(..))")
//    public Object findId(ProceedingJoinPoint proceed) throws Throwable {
//        Object id = proceed.getArgs()[0];
//        // log.info("判断findId方法是否传递Id参数 ==> id:{}", id);
//        if (id == null || "".equals(id)) {
//            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
//        }
//        return proceed.proceed();
//    }
//
//
//    /**
//     * 判断del 方法 Id参数（必传Id，不能传递空字符串）
//     * @param proceed
//     * @return
//     * @throws Throwable
//     */
//    @Around("execution(* com.ws.ldy.*..*.del(..))")
//    public Object del(ProceedingJoinPoint proceed) throws Throwable {
//        Object id = proceed.getArgs()[0];
//        // log.info("判断removeId方法是否传递Id参数 ==> id:{}", id);
//        if (id == null || "".equals(id)) {
//            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
//        }
//        return proceed.proceed();
//    }
//}
//
//
//
