package com.ws.ldy.common.function;import com.ws.ldy.modules.admin.model.entity.AdminUser;import java.io.Serializable;import java.lang.invoke.SerializedLambda;import java.lang.reflect.Method;import java.util.Map;import java.util.concurrent.ConcurrentHashMap;/** *   通过 Lambda 获取对象属性名 * 使用方法： LambdaTypeInfo.convertToFieldName(Test::getName) * * @author ws * @mail 1720696548@qq.com * @date 2020/4/12 0012 14:31 */public class LambdaTypeInfo {    /**     * 测试通过 Lambda 获取对象属性名     *     * @param args     * @return void     * @author ws     * @mail 1720696548@qq.com     * @date 2020/4/24 0024 9:47     */    public static void main(String[] args) {        System.out.println(LambdaTypeInfo.convertToFieldName(AdminUser::getUsername));    }        private static Map<Class, SerializedLambda> CLASS_LAMBDA_CACHE = new ConcurrentHashMap<>();    /***     * 转换方法引用为属性名     * @param fn     * @return     */    static <T> String convertToFieldName(SFunction<T> fn) {        SerializedLambda lambda = getSerializedLambda(fn);        // 获取方法名        String methodName = lambda.getImplMethodName();        String prefix = null;        if (methodName.startsWith("get")) {            prefix = "get";        } else if (methodName.startsWith("is")) {            prefix = "is";        }        if (prefix == null) {            System.out.println("无效的getter方法: " + methodName);        }        // 截取get/is之后的字符串并转换首字母为小写        return toLowerCaseFirstOne(methodName.replace(prefix, ""));    }    /**     * 首字母转小写     *     * @param s     * @return     */    static String toLowerCaseFirstOne(String s) {        if (Character.isLowerCase(s.charAt(0))) {            return s;        } else {            return Character.toLowerCase(s.charAt(0)) + s.substring(1);        }    }    /**     * 关键在于这个方法     */    static SerializedLambda getSerializedLambda(Serializable fn) {        SerializedLambda lambda = CLASS_LAMBDA_CACHE.get(fn.getClass());        // 先检查缓存中是否已存在        if (lambda == null) {            try {                // 提取SerializedLambda并缓存                Method method = fn.getClass().getDeclaredMethod("writeReplace");                method.setAccessible(Boolean.TRUE);                lambda = (SerializedLambda) method.invoke(fn);                CLASS_LAMBDA_CACHE.put(fn.getClass(), lambda);            } catch (Exception e) {                e.printStackTrace();            }        }        return lambda;    }}