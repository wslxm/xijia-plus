package io.github.wslxm.springbootplus2.redis.lock;


import io.github.wslxm.springbootplus2.redis.util.SpelUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * 定义切面织入
 *
 * @Author: dgg-wxw
 * @Date: 2020/5/20 15:36
 */
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Aspect
@Component
@Lazy
@Slf4j
public class DistributedLockAspect {

	@Autowired
	@Lazy
	private DistributedLockTemplate lockTemplate;

	@Pointcut("@annotation(io.github.wslxm.springbootplus2.redis.lock.DggDistributedLock)")
	public void pointcut() {
	}

	@Around(value = "pointcut()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = this.getSpecificmethod(joinPoint);
		DggDistributedLock ann = AnnotationUtils.findAnnotation(method, DggDistributedLock.class);
		return (Boolean) SpelUtil.parse(joinPoint.getTarget(), ann.condition(), method, joinPoint.getArgs()) ?
				lock(joinPoint, ann, SpelUtil.parse(joinPoint.getTarget(), ann.lockName(), method, joinPoint.getArgs()).toString()) : joinPoint.proceed();
	}

	@AfterThrowing(value = "pointcut()", throwing = "ex")
	public void afterThrowing(Throwable ex) throws Throwable {
		throw ex;
	}

	private Method getSpecificmethod(ProceedingJoinPoint pjp) {
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		// The method may be on an interface, but we need attributes from the
		// target class. If the target class is null, the method will be
		// unchanged.
		Class<?> targetClass = AopProxyUtils.ultimateTargetClass(pjp.getTarget());
		if (targetClass == null && pjp.getTarget() != null) {
			targetClass = pjp.getTarget().getClass();
		}
		Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
		// If we are dealing with method with generic parameters, find the
		// original method.
		specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
		return specificMethod;
	}

	private Object lock(ProceedingJoinPoint pjp, DggDistributedLock ann, final String lockName) throws Throwable {
		String prefix = ">>>>>>>>>>【Redisson 分布式锁】";
		String rLockName = "dgg-paas-redisson-lock:" + lockName;
		log.info("{} RLockName = {}", prefix, rLockName);

		if (ann.tryLock()) {
			return lockTemplate.tryLock(new DistributedLockCallback<Object>() {
				@Override
				public Object process() throws Throwable {
					return pjp.proceed();
				}

				@Override
				public String getLockName() {
					return rLockName;
				}

				@Override
				public String getExceptionDesc() {
					return ann.exceptionDesc();
				}
			}, ann.waitTime(), ann.leaseTime(), ann.timeUnit(), ann.fairLock());
		} else {
			return lockTemplate.lock(new DistributedLockCallback<Object>() {
				@Override
				public Object process() throws Throwable {
					return pjp.proceed();
				}

				@Override
				public String getLockName() {
					return rLockName;
				}

				@Override
				public String getExceptionDesc() {
					return ann.exceptionDesc();
				}
			}, ann.fairLock());
		}
	}
}
