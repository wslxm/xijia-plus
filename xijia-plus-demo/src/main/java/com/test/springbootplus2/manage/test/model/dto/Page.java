package com.test.springbootplus2.manage.test.model.dto;


import lombok.Data;
import lombok.ToString;
/**
 * 测试
 * @author wangsong
 */
@Data
@ToString(callSuper = true)
public class Page<T> {

    private T param;

}
