package com.github.wslxm.springbootplus2.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解（幂等token验证注解），需要验证的接口添加
 * @author wangsong
 * @date: 2019年4月27日 下午9:37:02
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotentAuth {


}