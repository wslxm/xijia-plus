package com.ws.ldy.config.aspect.gateway;


import com.ws.ldy.common.annotation.Encrypt;
import com.ws.ldy.config.aspect.Base64Util;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


/**
 * 请求参数自动解密 和 响应参数自动加密
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/4/9 0009 17:16 
 * @version 1.0.0
 */
@Aspect
@Component
public class SysEncrypt {


    /**
     * 请求参数解密
     * @param pjp
     * @return args
     * @throws Throwable
     */
    public Object[] decrypt(ProceedingJoinPoint pjp) throws IllegalAccessException {
        // 获取请求参数
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Object[] args = pjp.getArgs();
        if (args == null || args.length == 0 || args[0] == null) {
            return args;
        }
        // 获取所有参数注解返回的是一个二维数组Annotation[][],每个参数上可能有多个注解,是一个一维数组,
        // 多个参数又是一维数组,就组成了二维数组,所有我们在遍历的时候,第一次遍历拿到的数组下标就是方法参数的下标,
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        // 再根据Object[] args= joinPoint.getArgs(); 拿到所有的参数,根据指定的下标即可拿到对象的值
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            // 判断字段类型，是否需要解密
            boolean isBody = false;
            boolean isQuery = false;
            boolean isEncrypt = false;
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof RequestBody) {
                    isBody = true;
                } else if (annotation instanceof RequestParam) {
                    // 判断参数是body还是query
                    isQuery = true;
                } else if (annotation instanceof Encrypt) {
                    isEncrypt = true;
                }
            }
            if(isQuery && isEncrypt){
                int paramIndex = ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
                Object paramValue = args[paramIndex];
                args[paramIndex] = Base64Util.decrypt((String) paramValue);
            }
            if(isBody){
                args = decryptBody(args);
            }
        }
        return args;
    }


    /**
     * body参数解密
     * @param args 参数
     * @return
     */
    public Object[] decryptBody(Object[] args) throws IllegalAccessException {
        //获取请求的实体类
        Class<?> aClass = args[0].getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            // 判断是否存在加密注解
            Encrypt xjIsApiIdempotent = field.getAnnotation(Encrypt.class);
            if (xjIsApiIdempotent != null) {
                String name = field.getName();
                //设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
                String val = (String) field.get(args[0]);
                try {
                    field.set(args[0], Base64Util.decrypt(val));
                }catch (Exception e){

                }
                System.out.println(name + ": 需要解密");
            }
        }
        return args;
    }


    /**
     * 返回参数加密
     * @author wangsong
     * @param pjp
     * @date 2021/4/9 0009 17:17
     * @return args
     * @version 1.0.0
     */
    public Object[] encrypt(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        return args;
    }
}
