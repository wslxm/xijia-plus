package com.test.springbootplus2.manage.test.model.vo;

import io.github.wslxm.springbootplus2.common.annotation.XjSecret;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangsong
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "Encrypt2VO 对象", description = "测试返回脱敏")
public class Encrypt2VO implements Serializable {
	private static final long serialVersionUID = -1877788394466788429L;

	private String a;

	@XjSecret(type = 4)
	private String phone;

	@XjSecret(isNext = true)
	private Encrypt2VO encrypt;

	@XjSecret(isNext = true)
	private List<Encrypt2VO> encrypts;
}
