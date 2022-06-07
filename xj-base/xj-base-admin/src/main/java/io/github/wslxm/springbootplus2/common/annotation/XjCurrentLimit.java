package io.github.wslxm.springbootplus2.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  @author wangsong
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XjCurrentLimit {

    /**
     * 允许每秒的 请求数量
     * @return
     */
    int qbs() default 200;

	/**
	 * 限流错误信息
	 *
	 * @return
	 */
	String errorMsg() default "当前服务人数过多,请稍后重试!";
}
