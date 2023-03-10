package com.test.springbootplus2.manage.test.model.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 缓存测试对象
 * @author wangsong
 * @param null
 * @date 2022/8/22 0022 20:51
 * @return
 * @version 1.0.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "CacheDTO 对象", description = "缓存测试对象")
public class CacheDTO implements Serializable {

    private String id;
    private String name;
}
