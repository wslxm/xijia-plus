package com.github.wslxm.springbootplus2.config.filter.sing.annotation;

import java.lang.annotation.*;

/**
 * 指定请求响应参数 自动解密or加密注解
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/4/10 0010 15:59 
 * @version 1.0.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XjSecret {

    // 是否为下级数据（下级数据将进行递归加密or解密）
    boolean isNext() default false;

}

