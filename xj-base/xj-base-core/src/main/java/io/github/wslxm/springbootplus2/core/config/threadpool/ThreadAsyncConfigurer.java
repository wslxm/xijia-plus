package io.github.wslxm.springbootplus2.core.config.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 统一线程池
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/6/10 14:07
 */
@Configuration
@EnableAsync
public class ThreadAsyncConfigurer implements AsyncConfigurer {

	private final static Logger log = LoggerFactory.getLogger(ThreadAsyncConfigurer.class);

	@Value("${xj.thread.corePoolSize:2}")
	private int corePoolSize;

	@Value("${xj.thread.maxPoolSize:16}")
	private int maxPoolSize;

	@Value("${xj.thread.queueCapacity:10}")
	private int queueCapacity;

	@Value("${xj.thread.keepAlive:60}")
	private int keepAlive;

	@Value("${xj.thread.awaitTermination:300}")
	private int awaitTermination;

	@Value("${xj.thread.threadNamePrefix:xj-thread-}")
	private String threadNamePrefix;

	@Bean
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
		// 设置核心线程数 -->  长期运行的线程，底层使用了懒加载机制
		threadPool.setCorePoolSize(corePoolSize);
		// 设置最大线程数 -->  不长期运行的线程，当核心线程处理不过来的时候创建额外线程处理任务
		threadPool.setMaxPoolSize(maxPoolSize);
		// 线程池缓冲队列容量  --> ThreadPoolTaskExecutor中默认使用 LinkedBlockingQueue 队列
		threadPool.setQueueCapacity(queueCapacity);
		// 额外线程超时关闭  -->  当线程大于核心线程数，额外线程处于空闲时, 自动关闭额外线程的时间
		threadPool.setKeepAliveSeconds(keepAlive);
		// 核心线程超时关闭  -->  线程池中核心线程空闲时间达到 keepAliveTime也将关闭
		threadPool.setAwaitTerminationSeconds(awaitTermination);
		// 线程名前缀
		threadPool.setThreadNamePrefix(threadNamePrefix);
		// 等待任务在关机时完成--表明等待所有线程执行完
		threadPool.setWaitForTasksToCompleteOnShutdown(true);
		// 设置拒绝策略 CallerRunsPolicy 不由线程池中线程执行，由调用者所在线程执行
		threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 初始化线程
		threadPool.initialize();
		return threadPool;
	}

	/**
	 * 异常处理
	 *
	 * @return
	 */
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new CustomAsyncExceptionHandler();
	}

	/**
	 * 自定义异常处理类
	 */
	static class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
		@Override
		public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
			log.error("==========================" + throwable.getMessage() + "=======================", throwable);
			log.error("exception method:" + method.getName());
			for (Object param : obj) {
				log.error("Parameter value - " + param);
			}
		}
	}
}