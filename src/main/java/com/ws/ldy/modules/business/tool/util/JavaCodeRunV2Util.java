package com.ws.ldy.modules.business.tool.util;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java 代码运行器 (CustomStringJavaCompiler )
 * @author wangsong
 * @date 2020/12/11 0011 13:55
 * @return
 * @version 1.0.0
 */
@SuppressWarnings("ALL")
public class JavaCodeRunV2Util {
    //类全名
    private String fullClassName;
    private String sourceCode;
    //存放编译之后的字节码(key:类全名,value:编译之后输出的字节码)
    private Map<String, ByteJavaFileObject> javaFileObjectMap = new ConcurrentHashMap<>();
    //获取java的编译器
    private JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    //存放编译过程中输出的信息
    private DiagnosticCollector<JavaFileObject> diagnosticsCollector = new DiagnosticCollector<>();
    //执行结果（控制台输出的内容）
    private String runResult;
    //编译耗时(单位ms)
    private long compilerTakeTime;
    //运行耗时(单位ms)
    private long runTakeTime;


    public JavaCodeRunV2Util(String sourceCode) {
        this.sourceCode = sourceCode;
        this.fullClassName = getFullClassName(sourceCode);
    }

    /**
     * 编译字符串源代码,编译失败在 diagnosticsCollector 中获取提示信息
     *
     * @return true:编译成功 false:编译失败
     */
    public boolean compiler() {
        long startTime = System.currentTimeMillis();
        //标准的内容管理器,更换成自己的实现，覆盖部分方法
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnosticsCollector, null, null);
        JavaFileManager javaFileManager = new StringJavaFileManage(standardFileManager);
        //构造源代码对象
        JavaFileObject javaFileObject = new StringJavaFileObject(fullClassName, sourceCode);
        //获取一个编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, javaFileManager, diagnosticsCollector, null, null, Arrays.asList(javaFileObject));
        //设置编译耗时
        compilerTakeTime = System.currentTimeMillis() - startTime;
        return task.call();
    }

    /**
     * 执行main方法，重定向System.out.print
     */
    public void runMainMethod() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
        PrintStream out = System.out;
        try {
            long startTime = System.currentTimeMillis();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            //PrintStream PrintStream = new PrintStream("/Users/andy/Desktop/tem.sql"); //输出到文件
            System.setOut(printStream);

            StringClassLoader scl = new StringClassLoader();
            Class<?> aClass = scl.findClass(fullClassName);
            Method main = aClass.getMethod("main", String[].class);
            Object[] pars = new Object[]{1};
            pars[0] = new String[]{};
            main.invoke(null, pars); //调用main方法
            //设置运行耗时
            runTakeTime = System.currentTimeMillis() - startTime;
            //设置打印输出的内容
            runResult = new String(outputStream.toByteArray(), "utf-8");
        } finally {
            //还原默认打印的对象
            System.setOut(out);
        }

    }

    /**
     * @return 编译信息(错误 警告)
     */
    public String getCompilerMessage() {
        StringBuilder sb = new StringBuilder();
        List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticsCollector.getDiagnostics();
        for (Diagnostic diagnostic : diagnostics) {
            sb.append(diagnostic.toString()).append("\r\n");
        }
        return sb.toString();
    }

    /**
     * @return 控制台打印的信息
     */
    public String getRunResult() {
        return runResult;
    }


    public long getCompilerTakeTime() {
        return compilerTakeTime;
    }

    public long getRunTakeTime() {
        return runTakeTime;
    }

    /**
     * 获取类的全名称
     *
     * @param sourceCode 源码
     * @return 类的全名称
     */
    public static String getFullClassName(String sourceCode) {
        String className = "";
        Pattern pattern = Pattern.compile("package\\s+\\S+\\s*;");
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            className = matcher.group().replaceFirst("package", "").replace(";", "").trim() + ".";
        }

        pattern = Pattern.compile("class\\s+\\S+\\s+\\{");
        matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            className += matcher.group().replaceFirst("class", "").replace("{", "").trim();
        }
        return className;
    }

    /**
     * 自定义一个字符串的源码对象
     */
    private class StringJavaFileObject extends SimpleJavaFileObject {
        //等待编译的源码字段
        private String contents;

        //java源代码 => StringJavaFileObject对象 的时候使用
        public StringJavaFileObject(String className, String contents) {
            super(URI.create("string:///" + className.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE);
            this.contents = contents;
        }

        //字符串源码会调用该方法
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return contents;
        }

    }

    /**
     * 自定义一个编译之后的字节码对象
     */
    private class ByteJavaFileObject extends SimpleJavaFileObject {
        //存放编译后的字节码
        private ByteArrayOutputStream outPutStream;

        public ByteJavaFileObject(String className, Kind kind) {
            super(URI.create("string:///" + className.replaceAll("\\.", "/") + Kind.SOURCE.extension), kind);
        }

        //StringJavaFileManage 编译之后的字节码输出会调用该方法（把字节码输出到outputStream）
        @Override
        public OutputStream openOutputStream() {
            outPutStream = new ByteArrayOutputStream();
            return outPutStream;
        }

        //在类加载器加载的时候需要用到
        public byte[] getCompiledBytes() {
            return outPutStream.toByteArray();
        }
    }

    /**
     * 自定义一个JavaFileManage来控制编译之后字节码的输出位置
     */
    private class StringJavaFileManage extends ForwardingJavaFileManager {
        StringJavaFileManage(JavaFileManager fileManager) {
            super(fileManager);
        }

        //获取输出的文件对象，它表示给定位置处指定类型的指定类。
        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
            ByteJavaFileObject javaFileObject = new ByteJavaFileObject(className, kind);
            javaFileObjectMap.put(className, javaFileObject);
            return javaFileObject;
        }
    }

    /**
     * 自定义类加载器, 用来加载动态的字节码
     */
    private class StringClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            ByteJavaFileObject fileObject = javaFileObjectMap.get(name);
            if (fileObject != null) {
                byte[] bytes = fileObject.getCompiledBytes();
                return defineClass(name, bytes, 0, bytes.length);
            }
            try {
                return ClassLoader.getSystemClassLoader().loadClass(name);
            } catch (Exception e) {
                return super.findClass(name);
            }
        }
    }

    /**
     * 异常信息获取
     * @author wangsong
     * @param e 错误信息
     * @date 2020/12/17 0017 11:25
     * @return java.lang.String
     * @version 1.0.0
     */
    public static String error(Exception e) {
        // 错误类名
        String exceptionClass = e.getCause().getClass().getName();
        // String exceptionClassName = exceptionClass.substring(exceptionClass.lastIndexOf(".") + 1, exceptionClass.length());
        // 详细错误信息
        StringBuffer errorDesc = new StringBuffer();
        errorDesc.append("\r\n异常类:" + exceptionClass + "\r\n异常信息：" + e.getCause().toString() + "\r\n详细错误内容:\r\n");
        if (e.getCause().getStackTrace() != null) {
            Arrays.stream(e.getCause().getStackTrace()).forEach(i -> errorDesc.append(i.toString() + "\r\n"));
        }
        // 返回异常信息
        return errorDesc.toString();
    }

    /**
     * 运行
     * @author wangsong
     * @param code
     * @date 2020/12/17 0017 11:59
     * @return java.lang.String
     * @version 1.0.0
     */
    public static  String run(String code) {
        JavaCodeRunV2Util compiler = new JavaCodeRunV2Util(code);
        boolean res = compiler.compiler();
        if (res) {
            System.out.println("编译成功");
            try {
                // 执行
                compiler.runMainMethod();
            } catch (Exception e) {
                return error(e);
                // e.printStackTrace();
            }
            return compiler.getRunResult();
        } else {
            System.out.println("编译失败");
            return compiler.getCompilerMessage();
        }
    }


    public static void main(String[] args) {
        String code = "public class HelloWorld {\n" +
                "    public static void main(String []args) {\n" +
                "\t\tfor(int i=0; i < 1; i++){\n" +
                "\t\t\t       System.out.println(\"Hello World!\");\n" +
                "\t\t}\n" +
                "    }\n" +
                "}";
        JavaCodeRunV2Util compiler = new JavaCodeRunV2Util(code);
        boolean res = compiler.compiler();
        if (res) {
            System.out.println("编译成功");
            System.out.println("compilerTakeTime：" + compiler.getCompilerTakeTime());
            try {
                compiler.runMainMethod();
                System.out.println("runTakeTime：" + compiler.getRunTakeTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(compiler.getRunResult());
            System.out.println("诊断信息：" + compiler.getCompilerMessage());
        } else {
            System.out.println("编译失败");
            System.out.println(compiler.getCompilerMessage());
        }
    }
}