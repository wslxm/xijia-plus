package io.github.wslxm.springbootplus2.starter.aliyun.oss.starter.config.result;

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
public enum AliYunOssRType {

	// 系统相关 - 服务器内置状态码
	SYS_SUCCESS(200, "成功"),
	FILE_UPLOAD_FAILED(100001, "文件服务错误"),
	;
	private Integer value;
	private String msg;

	AliYunOssRType(Integer value, String msg) {
		this.value = value;
		this.msg = msg;
	}
}