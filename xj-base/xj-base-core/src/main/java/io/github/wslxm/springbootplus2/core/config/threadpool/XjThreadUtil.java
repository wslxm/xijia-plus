package io.github.wslxm.springbootplus2.core.config.threadpool;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * 异步线程 util
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/6/10 14:14
 */
@Component
public class XjThreadUtil {

	/**
	 * 线程池对象
	 */
	private static Executor executor;


	@Resource(name = "getAsyncExecutor")
	public void setExecutor(Executor executor) {
		XjThreadUtil.executor = executor;
	}


	/**
	 * 异步执行方法
	 * <p>
	 * 使用方法:
	 * ThreadUtils.asyncExecute(() -> {
	 * });
	 * </P>
	 *
	 * @author wangsong
	 * @email 1720696548@qq.com
	 * @date 2022/6/10 14:20
	 */
	public static void asyncExecute(Runnable command) {
		XjThreadUtil.executor.execute(command);
	}


	/**
	 * 线程休眠
	 *
	 * @param ms
	 */
	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException var3) {
			Thread.currentThread().interrupt();
		}
	}
}
