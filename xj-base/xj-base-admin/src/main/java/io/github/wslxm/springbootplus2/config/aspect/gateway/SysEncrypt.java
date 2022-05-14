package io.github.wslxm.springbootplus2.config.aspect.gateway;


import io.github.wslxm.springbootplus2.config.aspect.util.Base64Util;
import io.github.wslxm.springbootplus2.config.filter.sing.annotation.XjSecret;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;


/**
 * 请求 参数自动解密 和 响应参数自动加密工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/4/9 0009 17:16
 * @version 1.0.1
 */
@Aspect
@Component
@Slf4j
public class SysEncrypt {

    /**
     * 是否开启加密(true=开启)
     */
    private boolean isEncrypt = true;

    /**
     * 请求参数解密
     * @param pjp
     * @return args
     * @throws Throwable
     */
    public R<Object[]> decrypt(ProceedingJoinPoint pjp) {
        // 是否开启解密
        if (!isEncrypt) {
            return R.success(pjp.getArgs());
        }
        // 获取请求参数
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Object[] args = pjp.getArgs();
        if (args == null || args.length == 0 || args[0] == null) {
            return R.success(args);
        }
        // 获取方法上所有参数注解：返回的是一个二维数组Annotation[][],
        // 每个参数上可能有多个注解,是一个一维数组,多个参数又是一维数组,就组成了二维数组,所有我们在遍历的时候,第一次遍历拿到的数组下标就是方法参数的下标
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof RequestBody
                        || annotation instanceof ModelAttribute) {
                    // RequestBody 或 ModelAttribute 方式接收参数
                    args[0] = this.decryptOrEncryptEntity(args[0], 2).getData();
                } else if (annotation instanceof RequestParam
                        || annotation instanceof PathVariable
                        || annotation instanceof RequestHeader
                ) {
                    // RequestParam 方式接收参数
                    args = this.decryptParam(args, parameterAnnotations, parameterAnnotation);
                }
            }
        }
        return R.success(args);
    }


    /**
     * query 参数解密
     * @param args 参数
     * @return
     */
    public Object[] decryptParam(Object[] args, Annotation[][] parameterAnnotations, Annotation[] parameterAnnotation) {
        // 方法上的 query 参数是否存在 XjSecret 注解,是则否需要解密
        boolean isXjSecret = false;
        for (Annotation annotation : parameterAnnotation) {
            if (annotation instanceof XjSecret) {
                isXjSecret = true;
                break;
            }
        }
        if (isXjSecret) {
            // query指定参数解密
            int paramIndex = ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
            Object paramValue = args[paramIndex];
            args[paramIndex] = Base64Util.decrypt((String) paramValue);
        }
        return args;
    }


    /**
     * body参数解密or加密（使用反射）
     * 流程：
     *  1、获取到 aop 代码后接收参数的实体类参数 obj
     *  2、获取请求entity 类的所有参数
     *  3、判断为子对象或子集合，是进行递归重复1-5操作，只是参数就进行加密or解密操作
     *  4、获取到需要解密or加密的的参数 (默认参数为obj类型)
     *  5、对需要解密or加密的参数 进行 base64解密or加密 (默认参数为obj类型)
     * @param obj 参数 args参数
     * @param type 1=加密 2=解密
     * @return
     */
    public R<Object> decryptOrEncryptEntity(Object obj, Integer type) {
        if (obj == null) {
            return R.success(obj);
        }
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        String logMsg = (type == 1 ? "加密" : "解密");
        for (Field field : declaredFields) {
            String name = field.getName();
            field.setAccessible(true);
            XjSecret xjEncrypt = field.getAnnotation(XjSecret.class);
            // 判断是否为子对象或子list集合，是的话进行递归解密
            if (xjEncrypt != null) {
                if (xjEncrypt.isNext()) {
                    try {
                        Object fieldVal = field.get(obj);
                        if (fieldVal == null) {
                            continue;
                        }
                        if (fieldVal instanceof Collection<?>) {
                            // 集合[对象参数]
                            List<Object> list = (List<Object>) fieldVal;
                            for (Object zObj : list) {
                                this.decryptOrEncryptEntity(zObj, type);
                            }
                        } else {
                            // 对象参数
                            this.decryptOrEncryptEntity(fieldVal, type);
                        }
                    } catch (Exception e) {
                        log.error(name + ": 参数" + logMsg + "失败");
                        return R.error(RType.PARAM_DECRYPTION_ERROR.getValue(), RType.PARAM_DECRYPTION_ERROR.getMsg(), obj, name + logMsg + ": 失败");
                        /// e.printStackTrace();
                    }
                } else {
                    try {
                        String fieldVal = (String) field.get(obj);
                        if (type == 1) {
                            field.set(obj, Base64Util.encode(fieldVal));
                        } else if (type == 2) {
                            field.set(obj, Base64Util.decrypt(fieldVal));
                        }
                    } catch (Exception e) {
                        log.error(name + ": 参数" + logMsg + "失败");
                        return R.error(RType.PARAM_DECRYPTION_ERROR.getValue(), RType.PARAM_DECRYPTION_ERROR.getMsg(), obj, name + logMsg + ": 失败");
                        /// e.printStackTrace();
                    }
                }
            }
        }
        return R.success(obj);
    }


    /**
     * 返回参数加密
     * @author wangsong
     * @date 2021/4/9 0009 17:17
     * @return args
     * @version 1.0.1
     */
    public Object encrypt(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!isEncrypt) {
            return obj;
        }
        R<Object> r = null;
        try {
            r = (R<Object>) obj;
        } catch (Exception e) {
            return obj;
        }
        this.decryptOrEncryptEntity(r.getData(), 1);
        return obj;
    }
}
