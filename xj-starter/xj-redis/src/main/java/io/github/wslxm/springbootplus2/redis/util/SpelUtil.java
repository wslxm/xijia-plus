package io.github.wslxm.springbootplus2.redis.util;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 解析SPEL 表达式
 *
 * @Author: dgg-wxw
 * @Date: 2020/7/8 10:12
 */
@Slf4j
public class SpelUtil {

	private static final WebApplicationType WEB_APPLICATION_TYPE;

	private static final String[] SERVLET_INDICATOR_CLASSES = {"javax.servlet.Servlet",
			"org.springframework.web.context.ConfigurableWebApplicationContext"};

	private static final String WEBMVC_INDICATOR_CLASS = "org.springframework.web.servlet.DispatcherServlet";

	private static final String WEBFLUX_INDICATOR_CLASS = "org.springframework.web.reactive.DispatcherHandler";

	private static final String JERSEY_INDICATOR_CLASS = "org.glassfish.jersey.servlet.ServletContainer";

	private static final String SERVLET_APPLICATION_CONTEXT_CLASS = "org.springframework.web.context.WebApplicationContext";

	private static final String REACTIVE_APPLICATION_CONTEXT_CLASS = "org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext";

	static WebApplicationType deduceFromClasspath() {
		if (ClassUtils.isPresent(WEBFLUX_INDICATOR_CLASS, null) && !ClassUtils.isPresent(WEBMVC_INDICATOR_CLASS, null)
				&& !ClassUtils.isPresent(JERSEY_INDICATOR_CLASS, null)) {
			return WebApplicationType.REACTIVE;
		}
		for (String className : SERVLET_INDICATOR_CLASSES) {
			if (!ClassUtils.isPresent(className, null)) {
				return WebApplicationType.NONE;
			}
		}
		return WebApplicationType.SERVLET;
	}

	private static final Set<Class<? extends Annotation>> REQUEST_MAPPING_ANNOTATIONS = new LinkedHashSet<>(3);

	static {
		REQUEST_MAPPING_ANNOTATIONS.add(RequestMapping.class);
		REQUEST_MAPPING_ANNOTATIONS.add(PostMapping.class);
		REQUEST_MAPPING_ANNOTATIONS.add(GetMapping.class);
		WEB_APPLICATION_TYPE = deduceFromClasspath();
	}

	public static Object parse(String spel, Method method, Object[] args) {
		//获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer u =
				new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);
		//使用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		//SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		//把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		return parser.parseExpression(spel).getValue(context, Object.class);
	}

	/**
	 * 支持 #p0 参数索引的表达式解析
	 *
	 * @param target 根对象,method 所在的对象
	 * @param spel   表达式
	 * @param method 目标方法
	 * @param args   方法入参
	 * @return 解析后的字符串
	 */
	public static Object parse(Object target, String spel, Method method, Object[] args) {
		String prefix = ">>>>>>>>>>【SpEL 表达式】";
		Object key;
		if (StringUtils.isEmpty(spel)) {
			key = generateKey(target, method, args);
		} else {
			//获取被拦截方法参数名列表(使用Spring支持类库)
			LocalVariableTableParameterNameDiscoverer u =
					new LocalVariableTableParameterNameDiscoverer();
			String[] paraNameArr = u.getParameterNames(method);
			//使用SPEL进行key的解析
			ExpressionParser parser = new SpelExpressionParser();
			//#root
			ExpressionRootObject rootObject = ExpressionRootObject.builder()
            //      .caches(cacheMap.values())
					.method(method)
					.methodName(method.getName())
					.args(args)
					.target(target)
					.targetClass(target.getClass())
					.build();
			//SPEL上下文
			StandardEvaluationContext context = new MethodBasedEvaluationContext(rootObject, method, args, u);
			//把方法参数放入SPEL上下文中
			for (int i = 0; i < paraNameArr.length; i++) {
				context.setVariable(paraNameArr[i], args[i]);
			}
			key = parser.parseExpression(spel).getValue(context, Object.class);
		}
		log.debug("{} spel = {}, parse = {}", prefix, spel, key);
		return key;
	}

	public static Object generateKey(Object target, Method method, Object[] args) {
		final String[] classMapping = new String[1];
		Collection<? extends Annotation> classAnns =
				AnnotatedElementUtils.findAllMergedAnnotations(target.getClass(), REQUEST_MAPPING_ANNOTATIONS);
		classAnns.stream().filter(ann -> ann instanceof RequestMapping).forEach(
				ann -> classMapping[0] = ((RequestMapping) ann).value()[0]);

		final String[] methodMapping = new String[1];
		Collection<? extends Annotation> methodAnns =
				AnnotatedElementUtils.findAllMergedAnnotations(method, REQUEST_MAPPING_ANNOTATIONS);
		methodAnns.stream().filter(ann -> ann instanceof RequestMapping).forEach(
				ann -> methodMapping[0] = ((RequestMapping) ann).value()[0]);
		methodAnns.stream().filter(ann -> ann instanceof PostMapping).forEach(
				ann -> methodMapping[0] = ((PostMapping) ann).value()[0]);
		methodAnns.stream().filter(ann -> ann instanceof GetMapping).forEach(
				ann -> methodMapping[0] = ((GetMapping) ann).value()[0]);

		StringBuilder sb = new StringBuilder();
		if (classMapping[0] != null) {
			sb.append(StringUtils.startsWithIgnoreCase(classMapping[0], "/") ? classMapping[0] : "/" + classMapping[0]);
		}
		if (methodMapping[0] != null) {
			sb.append(StringUtils.startsWithIgnoreCase(methodMapping[0], "/") ? methodMapping[0] : "/" + methodMapping[0]);
		} else {
			sb.append(method);
		}
		sb.append(CacheConstant.CACHE_KEY_SEPARATOR_COLON).append(Md5Utils.hash(getReqDataStr(args)));
		return sb.toString();
	}

	/**
	 * 获取请求参数，构造成字符串
	 *
	 * @param args 数据
	 * @return java.lang.String
	 **/
	private static String getReqDataStr(Object[] args) {
		StringBuilder result = new StringBuilder();
		if (args == null || args.length <= 0) {
			return result.toString();
		}
		for (Object arg : args) {
			if (isInputString(arg)) {
				String jsonString = JSON.toJSONString(arg);
				result.append(jsonString).append(",");
			}
		}

		//去除最后一个逗号
		if (result.length() > 0) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

	/**
	 * 判断对象是否是用户输入的数据
	 *
	 * @param arg 对象
	 * @return boolean
	 **/
	private static boolean isInputString(Object arg) {
		switch (WEB_APPLICATION_TYPE) {
			case SERVLET:
				return !(arg instanceof ServletRequest)
						&& !(arg instanceof ServletResponse)
						&& !(containsMultipartFile(arg));
			case REACTIVE:
				return !(arg instanceof ServerHttpRequest)
						&& !(arg instanceof ServerHttpResponse)
						&& !(containsMultipartFile(arg));
			default:
				return !(containsMultipartFile(arg));
		}
	}

	/**
	 * 判断是否包含MultipartFile类型数据
	 *
	 * @param object 数据
	 * @return boolean
	 **/
	private static boolean containsMultipartFile(Object object) {
		if (object instanceof MultipartFile) {
			return true;
		}
		if (object instanceof Collection) {
			Collection list = (Collection) object;
			for (Object o : list) {
				if (o instanceof MultipartFile) {
					return true;
				}
			}
		}
		return false;
	}
}
