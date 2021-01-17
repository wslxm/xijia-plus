//package com.ws.ldy.modules.modules.sys.tool.util;
//
//import com.ws.ldy.common.utils.DynamicLoader;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Map;
//
///**
// * java 代码运行器
// * @author wangsong
// * @date 2020/12/11 0011 13:55
// * @return
// * @version 1.0.0
// */
//public class JavaCodeRunUtil {
//
//    /**
//     * 这里会抛出警告内容，如下，可不必理会
//     * 警告: Can't initialize javac processor due to (most likely) a class loader problem: java.lang.NoClassDefFoundError: com/sun/tools/javac/processing/JavacProcessingEnvironment
//     */
//    public static String run(String javaSrc) {
//        // 获取类名
//        String className = javaSrc.substring(0, javaSrc.indexOf("{"));
//        className = className.replace("public class", "").trim();
//        // 动态生成类
//        // 获得控制台内容
////       PrintStream oldPrintStream2 =new PrintStream(); // 将原来的System.out交给printStream 对象保存
////        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();   // 设置新的out
////        //System.setOut(new PrintStream(bos2));
//        Map<String, byte[]> bytecode = DynamicLoader.compile(className + ".java", javaSrc);
////        System.setOut(oldPrintStream2);
//        Method main = null;
//        try {
//            // 获取动态生成的类
//            DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader(bytecode);
//            Class clazz = classLoader.loadClass(className);
//            // 获取main方法, 并随意设置一个默认参数，然后通过invoke调用
//            main = clazz.getMethod("main", String[].class);
//        } catch (Exception e) {
////            e.printStackTrace();
////            return "编译错误：" + e.toString();
//            return error(e);
//        }
//        // 获得控制台内容
//        PrintStream oldPrintStream = System.out; // 将原来的System.out交给printStream 对象保存
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();   // 设置新的out
//        System.setOut(new PrintStream(bos));
//        try {
//            // 执行(args参数值随意)
//            String[] args = new String[]{"20211290"};
//            main.invoke(null, (Object) args);
//            // 获取动态执行代码打印的内容
//            System.setOut(oldPrintStream);
//            // 返回执行中打印的内容
//            return bos.toString();
//        } catch (Exception e) {
//            return error(e);
//        }
//    }
//
//
//    /**
//     * 异常信息获取
//     * @author wangsong
//     * @param e 错误信息
//     * @date 2020/12/17 0017 11:25
//     * @return java.lang.String
//     * @version 1.0.0
//     */
//    public static String error(Exception e) {
//
//        // 错误类名
//        String exceptionClass = e.getCause().getClass().getName();
//        // String exceptionClassName = exceptionClass.substring(exceptionClass.lastIndexOf(".") + 1, exceptionClass.length());
//        // 详细错误信息
//        StringBuffer errorDesc = new StringBuffer();
//        errorDesc.append("\r\n异常类:" + exceptionClass + "\r\n异常信息：" + e.getCause().toString() + "\r\n详细错误内容:\r\n");
//        if (e.getCause().getStackTrace() != null) {
//            Arrays.stream(e.getCause().getStackTrace()).forEach(i -> errorDesc.append(i.toString() + "\r\n"));
//        }
//        // 返回异常信息
//        return errorDesc.toString();
//    }
//}
