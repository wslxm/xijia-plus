package com.ws.ldy.config.swagger;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.schema.Annotations;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.AllowableValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.schema.ApiModelProperties;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * TODO  对swagger2 字段为枚举的对象进行处理，添加枚举对象具体的描叙信息
 * <p>
 * 对swagger展示进行修改 1. 数字、Boolean属性设置默认的example 2. 支持将枚举变量的描述按照枚举类定义展示
 * 参考：https://juejin.im/post/5cdd60026fb9a0321f04401b  原文多数内容已经被我修改,修改为无需定义注解
 * <p>
 * 判断字段类型是否为枚举，是通过反射获取枚举对象并获取相关内容，查询给字段的swagger2 的注解注释信息 description赋值
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/28 0028 8:31
 */
@Component
@Primary
@Slf4j
public class SwaggerDisplayConfig implements ModelPropertyBuilderPlugin {

    /**
     * 是否允许swagger
     */
    @Value("${com.gateway.swagger.enable:true}")
    private Boolean enableSwagger;

    @Override
    public void apply(ModelPropertyContext context) {
        //如果不支持swagger的话，直接返回
        if (!enableSwagger) {
            return;
        }
        //获取当前字段的类型
        final Class fieldType = context.getBeanPropertyDefinition().get().getField().getRawType();

        //设置对数字的字段设置默认的example
        setDefaultExample(context, fieldType);

        //为枚举字段设置注释
        descForEnumFields(context, fieldType);
    }

    /**
     * 为枚举字段设置注释
     */
    /**
     * TODO
     *
     * @param context   swagger2 注释对象
     * @param fieldType fieldType 字段类型
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/28 0028 8:01
     */
    private void descForEnumFields(ModelPropertyContext context, Class fieldType) {
        Optional<ApiModelProperty> annotation = Optional.absent();

        if (context.getAnnotatedElement().isPresent()) {
            annotation = annotation.or(ApiModelProperties.findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(Annotations.findPropertyAnnotation(
                    context.getBeanPropertyDefinition().get(),
                    ApiModelProperty.class));
        }
        try {

            //判断字段是否被定义为枚举
            if (!fieldType.isEnum()) {
                return;
            }
            //获得枚举并获取相关枚举对象属性
            Class<?> aClass = Class.forName(fieldType.getName());
            Enum[] enumConstants = (Enum[]) aClass.getEnumConstants();
            System.out.println(enumConstants);
            ArrayList<String> enums = new ArrayList<>();
            StringBuffer description = new StringBuffer();
            for (Enum enum1 : enumConstants) {
                //得到枚举实例名，enum1.name() ：枚举属性名，enum1：枚举属性数据
                description.append("\r\n" + enum1);
                enums.add(enum1.name());
            }
            // 获取注释信息
            String joinText = "\r\n{" + String.join(";", description.substring(0, description.length() - 0)) + "\r\n}";

            // 获取当前注解对象,并赋值description参数
            Field mField = ModelPropertyBuilder.class.getDeclaredField("description");
            mField.setAccessible(true);
            // 原注释信息+ 自定义内容
            joinText = mField.get(context.getBuilder()) + joinText;
            // 赋值
            final ResolvedType resolvedType = context.getResolver().resolve(fieldType);
            AllowableValues allowableValues = new AllowableListValues(enums, enums.size() + "");
            context.getBuilder()
                    .allowableValues(allowableValues)    // 枚举字段内容回写
                    .description(joinText)               // 注释内容回写
                    .type(resolvedType);                 // 字段类型
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    /**
     * 设置默认的example
     */
    private void setDefaultExample(ModelPropertyContext context, Class fieldType) {
        if (Number.class.isAssignableFrom(fieldType)) {
            context.getBuilder().example("0");
        }
        if (Boolean.class.isAssignableFrom(fieldType)) {
            context.getBuilder().example("true");
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}

