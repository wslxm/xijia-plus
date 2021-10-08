package com.test.springbootplus2.manage.test.model.dto;


import com.github.wslxm.springbootplus2.core.base.model.Convert;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "SignDto 对象", description = "验签测试")
public class ValidDTO extends Convert {

    private String name;
    private Integer num;
    private String phone;
    private String email;
    private String iDCard;


}
