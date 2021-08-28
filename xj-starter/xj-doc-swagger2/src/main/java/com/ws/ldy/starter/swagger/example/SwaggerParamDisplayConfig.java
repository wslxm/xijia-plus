//package com.ws.ldy.starter.swagger2config;
//
//import com.fasterxml.classmate.ResolvedType;
//import com.google.common.base.Optional;
//import io.swagger.annotations.ApiModel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import springfox.documentation.service.AllowableListValues;
//import springfox.documentation.service.AllowableValues;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.ParameterBuilderPlugin;
//import springfox.documentation.spi.service.contexts.ParameterContext;
//
//import java.util.ArrayList;
//
//
///**
// * 处理枚举和 数字、Boolean属性设置默认的example  默认值
// * <p>
// * 对swagger展示进行修改 1. 数字、Boolean属性设置默认的example 2. 支持将枚举变量的描述按照枚举类定义展示
// * 参考：https://juejin.im/post/5cdd60026fb9a0321f04401b  原文多数内容已经被我修改,修改为无需定义注解
// * <p>
// * 判断字段类型是否为枚举，是通过反射获取枚举对象并获取相关内容，查询给字段的swagger2 的注解注释信息 description赋值
// *
// * @author ws
// * @mail 1720696548@qq.com
// * @date 2020/2/28 0028 8:31
// */
//@SuppressWarnings("all")
//@Component
//@Primary
//@Slf4j
//public class SwaggerParamDisplayConfig implements ParameterBuilderPlugin {
//
//    @Override
//    public void apply(ParameterContext context) {
//        //获取当前字段的类型
//        Optional<ResolvedType> type = context.parameterBuilder().build().getType();
//        String className = type.get().toString();
//        if (className.equals("java.util.List<java.lang.String>")) {
//            return;
//        }
//        Class<?> aClass = null;
//        try {
//            aClass = Class.forName(className);
//        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
//            return;
//        }
//        //设置对数字的字段设置默认的example
//        setDefaultExample(context, aClass);
//        //为枚举字段设置注释
//        descForEnumFields(context, aClass);
//        // 为枚举字段设置注释
//        this.descForEnumFields(context, aClass);
//    }
//
//
//    /**
//     * TODO
//     *
//     * @param context   swagger2 注释对象
//     * @param fieldType fieldType 字段类型
//     * @return void
//     * @author ws
//     * @mail 1720696548@qq.com
//     * @date 2020/2/28 0028 8:01
//     */
//    private void descForEnumFields(ParameterContext context, Class fieldType) {
//        //判断字段是否被定义为枚举
//        if (!fieldType.isEnum()) {
//            return;
//        }
//        try {
//            //获得枚举并获取相关枚举对象属性
//            Class<?> aClass = Class.forName(fieldType.getName());
//            Enum[] enumConstants = (Enum[]) aClass.getEnumConstants();
//            // 判断该枚举类上属否存在swagger2的 ApiModel 注解
//            StringBuffer apiModelDescription = new StringBuffer();
//            ApiModel apiModel = aClass.getDeclaredAnnotation(ApiModel.class);
//            if (apiModel != null) {
//                String value = apiModel.value();
//                String desc = apiModel.description();
//                if (value != null && desc != null) {
//                    apiModelDescription.append(value + " : " + desc);
//                } else if (value != null) {
//                    apiModelDescription.append(value);
//                } else if (desc != null) {
//                    apiModelDescription.append(desc);
//                }
//            }
//            // 添加枚举内数据-description
//            StringBuffer description = new StringBuffer();
//            ArrayList<String> enums = new ArrayList<>();
//            for (Enum enum1 : enumConstants) {
//                //得到枚举实例名，enum1.name() ：枚举属性名，enum1：枚举属性数据
//                description.append("\r\n" + enum1); //+ enum1.name() + ":" +
//                enums.add(enum1.name());
//            }
//            // 获取注释信息
//            String joinText = apiModelDescription + "\r\n{" + String.join("; ", description.substring(0, description.length() - 0)) + "\r\n}";
//            // 获取枚举值内容
//            AllowableValues allowableValues = new AllowableListValues(enums, enums.size() + "");
//            // 赋值
//            context.parameterBuilder()
//                    .allowableValues(allowableValues)    // 枚举字段内容回写
//                    .description(joinText)               // 注释内容回写
//                    .build();
//        } catch (
//                Exception e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    /**
//     * 设置默认的example
//     */
//    private void setDefaultExample(ParameterContext context, Class fieldType) {
//        if (Number.class.isAssignableFrom(fieldType)) {
//            context.parameterBuilder().scalarExample("0");
//        }
//        if (Boolean.class.isAssignableFrom(fieldType)) {
//            context.parameterBuilder().scalarExample("true");
//        }
//    }
//
//    @Override
//    public boolean supports(DocumentationType documentationType) {
//        return true;
//    }
//}
//
