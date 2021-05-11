package com.ws.ldy.common.annotation;

import java.lang.annotation.*;

/**
 * 指定请求响应参数加密 ,支持 query + body
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/4/10 0010 15:59 
 * @version 1.0.0
 */
//@Target({ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.TYPE_USE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

}

