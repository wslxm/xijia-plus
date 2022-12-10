package com.test.springbootplus2.manage.test.model.dto;


import io.github.wslxm.springbootplus2.core.base.model.Convert;
import lombok.Data;
import lombok.ToString;
/**
 * 验签测试
 * @author wangsong
 */
@Data
@ToString(callSuper = true)
public class ValidDTO extends Convert {

    private String name;
    private Integer num;
    private String phone;
    private String email;
    private String idCard;


}
