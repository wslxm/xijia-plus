package io.github.wslxm.springbootplus2.config.aspect.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XjCurrentLimit {

    /**
     * 允许每秒的 请求数量
     * @return
     */
    int qbs() default 200;

}
