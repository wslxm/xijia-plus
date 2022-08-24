package io.github.wslxm.springbootplus2.starter.redis.config.error;


import io.github.wslxm.springbootplus2.starter.redis.config.result.RedisR;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
  * 异常处理类/ 全局异常 /自定义异常
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2022/8/22 0022 20:52
  * @version 1.0.0
  */
@RestControllerAdvice
@Slf4j
public class RedisGlobalExceptionHandler {

	/**
	 * 全局异常|受检查异常 --> Exception 。可以理解为错误，必须要开发者解决以后才能编译通过，这里JSR 303 为受检查异常
	 * 全局异常|运行时异常 --> RuntimeException extends Exception： 运行时异常,又称不受检查异常，不受检查！
	 * 受检查异常解决的方法有两种1：throw到上层，2，try-catch处理。
	 *
	 * @param e
	 * @return io.github.wslxm.admincore.platform.vo.ResponseData
	 * @author ws
	 * @mail 1720696548@qq.com
	 * @date 2020/2/9 0009 10:06
	 */
	@ExceptionHandler(RedisErrorException.class)
	public RedisR exceptionHandler(RedisErrorException e) {
		log.error(e.toString());
		return RedisR.error(e.getCode(), e.getMsg());
	}
}


