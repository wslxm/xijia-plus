package com.test.springbootplus2.manage.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "测试 对象", description = "测试")
public class Page<T> {

    private T param;

}
