package com.ws.ldy.admincore.common.error;

import com.ws.ldy.admincore.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ws.ldy.admincore.common.vo.ResponseData;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理类/ 全局异常 /自定义异常
 */
//@ControllerAdvice
@SuppressWarnings("Java8MapApi")
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BaseController {


    /**
     * 运行时异常log4j日志信息转换中文提示，key=为异常类，value=提示信息，可任意扩展
     */
    private static Map<String, String> MapException = new HashMap();
    public GlobalExceptionHandler() {
        MapException.put("NullPointerException", "空指针异常");
        MapException.put("NumberFormatException", "字符串转换为数字异常,比如int i= Integer.parseInt(“ab3”)");
        MapException.put("ArrayIndexOutOfBoundsException", "数组越界");
        MapException.put("StringIndexOutOfBoundsException", "字符串越界");
        MapException.put("ClassCastException", "类型转换错误。比如 Object obj=new Object(); String s=(String)obj");
        MapException.put("UnsupportedOperationException", "该操作不被支持");
        MapException.put("ArithmeticException", "算术错误，典型的就是0作为除数的时候");
        MapException.put("IllegalArgumentException", "非法参数，在把字符串转换成数字的时候经常出现的一个异常");
    }


    /**
     * TODO  全局异常 --> 受检查异常。可以理解为错误，必须要开发者解决以后才能编译通过，
     * 解决的方法有两种1：throw到上层，2，try-catch处理。
     *
     * @param e
     * @return com.ws.ldy.admincore.common.vo.ResponseData
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 10:06
     */
    @ExceptionHandler(Exception.class)
    public ResponseData exceptionHandler(Exception e) {
        StringBuffer url = request.getRequestURL();
        log.info("###全局捕获异常###,url:{} -->  ERROR:{}", url, e);
        return ResponseData.error();
    }


    /**
     * TODO  全局异常--> 运行时异常,又称不受检查异常，不受检查！
     * <p>
     * 因为不受检查，所以在代码中可能会有RunTimeException时Java编译检查时不会告诉你有这个异常，但是在实际运行代码时则会暴露出来，比如经典的1/0，空指针等。如果不处理也会被Java自己处理
     * <p>
     * RuntimeException是开发中最容易遇到的，下面列举一下常见的RuntimeException：
     * 1、NullPointerException：见的最多了，其实很简单，一般都是在null对象上调用方法了。
     * 2、NumberFormatException：继承IllegalArgumentException，字符串转换为数字时出现。比如int i= Integer.parseInt(“ab3”);
     * 3、ArrayIndexOutOfBoundsException:数组越界。比如 int[] a=new int[3]; int b=a[3];
     * 4、StringIndexOutOfBoundsException：字符串越界。比如 String s=“hello”; char c=s.chatAt(6);
     * 5、ClassCastException:类型转换错误。比如 Object obj=new Object(); String s=(String)obj;
     * 6、UnsupportedOperationException:该操作不被支持。如果我们希望不支持这个方法，可以抛出这个异常。既然不支持还要这个干吗？有可能子类中不想支持父类中有的方法，可以直接抛出这个异常。
     * 7、ArithmeticException：算术错误，典型的就是0作为除数的时候。
     * 8、IllegalArgumentException：非法参数，在把字符串转换成数字的时候经常出现的一个异常，我们可以在自己的程序中好好利用这个异常。
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseData exceptionHandler(RuntimeException e) {
        StringBuffer url = request.getRequestURL();
        String exceptionClass = e.toString().substring(0, e.toString().indexOf(":"));
        String exceptionClassName = exceptionClass.substring(exceptionClass.lastIndexOf(".") + 1, exceptionClass.length());
        if (MapException.containsKey(exceptionClassName)) {
            log.info("\n\r### [全局捕获异常] --> 请求URL:{} -->  错误原因:{}\n\r ---------> 详细错误日志:", url, MapException.get(exceptionClassName), e);
        } else {
            log.info("\n\r### [全局捕获异常] --> 请求URL:{} -->  错误原因:{}\n\r ---------> 详细错误日志:", url, "未解析", e);
        }
        return ResponseData.error();
    }


    /**
     * TODO  自定义异常捕获（返回用户/前端友好提示）
     * <p>
     * 任意地方使用：throw new ErrorException("1000000","自定义异常测试");
     * 返回：{"code": "1000000","msg": "自定义异常测试"}
     * <p>
     * @param e
     * @return com.ws.ldy.admincore.common.vo.ResponseData
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 10:06
     */
    @ExceptionHandler(ErrorException.class)
    public ResponseData exceptionHandler(ErrorException e) {
        StringBuffer url = request.getRequestURL();
        log.info("### [自定义异常] --> 请求URL:{} --> [code:{},msg:{}]", url, e.getCode(), e.getMsg());
        return ResponseData.error(e.getCode(), e.getMsg());
    }
}

