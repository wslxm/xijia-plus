//package com.ws.ldy.config.annotation;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
////         1.CONSTRUCTOR:用于描述构造器
////         2.FIELD:用于描述域
////         3.LOCAL_VARIABLE:用于描述局部变量
////         4.METHOD:用于描述方法
////         5.PACKAGE:用于描述包
////         6.PARAMETER:用于描述参数
////         7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
//
///**
// * 自定义注解，可标记于类和方法上
// *
// * @author wangsong
// * @date: 2019年4月21日 下午8:37:39
// */
//@Target(value = {ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface JWT {
//    /**
//     * 是否需要Token 认证
//     *
//     * @return
//     */
//    boolean isToken() default true;
//
//    /**
//     * 是否需要接口权限日志
//     *
//     * @return
//     */
//    boolean isAuthority() default true;
//}
