package io.github.wslxm.springbootplus2.websocket.config.result;

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
public enum WebSocketRType {

	// 系统相关 - 服务器内置状态码
	SYS_SUCCESS(200, "成功"),
	FILE_UPLOAD_FAILED(100002, "websocket服务错误"),
	;
	private Integer value;
	private String msg;

	WebSocketRType(Integer value, String msg) {
		this.value = value;
		this.msg = msg;
	}
}
