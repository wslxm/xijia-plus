package io.github.wslxm.springbootplus2.common.annotation;

import java.lang.annotation.*;

/**
 * 指定请求和响应参数 自动解密or加密注解
 *
 * @author wangsong
 * @version 1.0.1
 * @email 1720696548@qq.com
 * @date 2021/4/10 0010 15:59
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XjSecret {

    /**
     * 是否存在下级数据（存在下级数据将对下级数据 进行递归 加密or解密 操作）
     *
     * @return boolean
     * @version 1.0.0
     */
    boolean isNext() default false;

}

