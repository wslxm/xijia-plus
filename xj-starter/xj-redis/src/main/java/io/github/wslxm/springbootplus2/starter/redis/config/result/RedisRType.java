package io.github.wslxm.springbootplus2.starter.redis.config.result;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常常量类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 11:16
 * @return
 */

@Getter
@NoArgsConstructor
public enum RedisRType {

	// 系统相关 - 服务器内置状态码
	SYS_SUCCESS(200, "成功"),
	SYS_ERROR(100003, "redis 错误"),
	;
	private Integer value;
	private String msg;

	RedisRType(Integer value, String msg) {
		this.value = value;
		this.msg = msg;
	}
}
