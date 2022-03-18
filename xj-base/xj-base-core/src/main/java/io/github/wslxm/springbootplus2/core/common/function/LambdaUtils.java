package io.github.wslxm.springbootplus2.core.common.function;import lombok.extern.slf4j.Slf4j;import java.io.Serializable;import java.lang.invoke.SerializedLambda;import java.lang.reflect.Method;import java.util.Map;import java.util.concurrent.ConcurrentHashMap;/** * 通过 Lambda 获取对象属性名 * 使用方法： LambdaUtils.convert(Test::getName) * * @author ws * @mail 1720696548@qq.com * @date 2020/4/12 0012 14:31 */@Slf4jpublic class LambdaUtils {    private static Map<Class, SerializedLambda> CLASS_LAMBDA_CACHE = new ConcurrentHashMap<>();    /***     * 转换方法引用为属性名,     * <P>     *     LambdaUtils.convert(AdminUser::getUsername)     * </P>     * @param fn ToFieldName     * @return 属性名     */    public static <T> String convert(SFunction<T> fn) {        SerializedLambda lambda = getSerializedLambda(fn);        if (lambda != null) {            // 获取方法名            String methodName = lambda.getImplMethodName();            String prefix = null;            String get = "get";            String is = "is";            if (methodName.startsWith(get)) {                prefix = get;            } else if (methodName.startsWith(is)) {                prefix = is;            }            if (prefix == null) {                log.error("无效的getter方法:{}", methodName);                return toLowerCaseFirstOne(methodName);            } else {                // 截取get/is之后的字符串并转换首字母为小写                return toLowerCaseFirstOne(methodName.replace(prefix, ""));            }        } else {            return null;        }    }    /**     * 首字母转小写     *     * @param s     * @return     */    static String toLowerCaseFirstOne(String s) {        if (Character.isLowerCase(s.charAt(0))) {            return s;        } else {            return Character.toLowerCase(s.charAt(0)) + s.substring(1);        }    }    /**     * 关键在于这个方法     */    static SerializedLambda getSerializedLambda(Serializable fn) {        SerializedLambda lambda = CLASS_LAMBDA_CACHE.get(fn.getClass());        // 先检查缓存中是否已存在        if (lambda == null) {            try {                // 提取 SerializedLambda并缓存                Method method = fn.getClass().getDeclaredMethod("writeReplace");                method.setAccessible(Boolean.TRUE);                lambda = (SerializedLambda) method.invoke(fn);                CLASS_LAMBDA_CACHE.put(fn.getClass(), lambda);            } catch (Exception e) {                log.info(e.toString());            }        }        return lambda;    }}