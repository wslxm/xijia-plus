package io.github.wslxm.springbootplus2.starter.redis.config.result;import io.swagger.annotations.ApiModel;import io.swagger.annotations.ApiModelProperty;import lombok.Data;import java.io.Serializable;/** * 返回的数据格式 * * @author 王松 * @WX-QQ 1720696548 * @date 2019/11/14 14:55 */@Data@ApiModel(value = "RedisR", description = "统一返回格式")public class RedisR<T> implements Serializable {	private static final long serialVersionUID = -5666504070515657048L;	@ApiModelProperty(value = "状态码")	private Integer code;	@ApiModelProperty(value = "状态描叙")	private String msg;	/**	 * 注解 @JsonInclude(value = JsonInclude.Include.NON_EMPTY) 设置空不返回	 */	@ApiModelProperty(value = "返回数据")	private T data;	@ApiModelProperty(value = "错误的栈内存信息")	private String errorMsg;	private RedisR(Integer code, String msg, T data, String errorMsg) {		this.code = code;		this.data = data;		this.msg = msg;		this.errorMsg = errorMsg;	}	public static <T> RedisR<Void> success() {		return new RedisR<>(RedisRType.SYS_SUCCESS.getValue(), RedisRType.SYS_SUCCESS.getMsg(), null, null);	}	public static <T> RedisR<T> success(T data) {		return new RedisR<>(RedisRType.SYS_SUCCESS.getValue(), RedisRType.SYS_SUCCESS.getMsg(), data, null);	}	public static <T> RedisR<String> error(Integer code, String msg) {		return new RedisR<>(code, msg, null, null);	}}