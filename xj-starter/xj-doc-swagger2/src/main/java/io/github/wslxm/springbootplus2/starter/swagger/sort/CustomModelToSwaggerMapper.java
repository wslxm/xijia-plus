package io.github.wslxm.springbootplus2.starter.swagger.sort;

import io.swagger.models.parameters.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2MapperImpl;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 重写 将Document转换成Swagger 类, 根据order进行排序 , 修复dto可以排序，但query类无法排序问题
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2020/8/21 0021 11:02
 */
@Primary
@Component("ServiceModelToSwagger2Mapper")
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CustomModelToSwaggerMapper extends ServiceModelToSwagger2MapperImpl {

    @Override
    protected List<Parameter> parameterListToParameterList(List<springfox.documentation.service.Parameter> list) {
        //list需要根据order|postion排序
        list = list.stream().sorted((p1, p2) -> Integer.compare(p1.getOrder(), p2.getOrder())).collect(Collectors.toList());
        return super.parameterListToParameterList(list);
    }
}
