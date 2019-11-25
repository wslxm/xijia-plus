package com.ws.ldy.admincore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO    url 权限
 *
 * @date  2019/11/25 0025 7:45
 * @return
 */
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LdyAuthority {

    /**
     * 1，权限名，2。权限描叙
     */
     String [] value();
}
