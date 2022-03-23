package com.test.springbootplus2.manage.test.model.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@ApiModel(value = "CacheDTO 对象", description = "缓存测试对象")
public class CacheDTO implements Serializable {

	private String id;
	private String name;
}
