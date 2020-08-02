package com.ws.ldy.config.error;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.others.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异常处理类/ 全局异常 /自定义异常
 */
//@ControllerAdvice
@SuppressWarnings("all")
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BaseController {


    /**
     * 运行时异常log4j日志信息转换中文提示，key=为异常类，value=提示信息，可任意扩展
     */
    private static Map<String, String> mapException = null;

    /**
     * 程序错误
     */
    public GlobalExceptionHandler() {
        mapException = new HashMap<>();
        mapException.put("NullPointerException", "空指针异常 : ");
        mapException.put("NumberFormatException", "字符串转换为数字异常,比如int i= Integer.parseInt(“ab3”) : ");
        mapException.put("ArrayIndexOutOfBoundsException", "数组越界 : ");
        mapException.put("StringIndexOutOfBoundsException", "字符串越界 : ");
        mapException.put("ClassCastException", "类型转换错误。比如 Object obj=new Object(); String s=(String)obj : ");
        mapException.put("UnsupportedOperationException", "该操作不被支持 : ");
        mapException.put("ArithmeticException", "算术错误，典型的就是0作为除数的时候 : ");
        mapException.put("IllegalArgumentException", "非法参数，在把字符串转换成数字的时候经常出现的一个异常 : ");
        mapException.put("UnsupportedEncodingException", "编码编译出错 : ");
    }


    /**
     * 全局异常|受检查异常 --> Exception 。可以理解为错误，必须要开发者解决以后才能编译通过，这里JSR 303 为受检查异常
     * 全局异常|运行时异常 --> RuntimeException extends Exception： 运行时异常,又称不受检查异常，不受检查！
     * 受检查异常解决的方法有两种1：throw到上层，2，try-catch处理。
     *
     * @param e
     * @return com.ws.ldy.admincore.platform.vo.ResponseData
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 10:06
     */
    @ExceptionHandler(Exception.class)
    public R<String> exceptionHandler(Exception e) {
        // 日志模板
        String logStr = "\n\r### [全局捕获异常] --> 请求URL: " + request.getRequestURL() + " --> 错误原因: ";
        // 错误类名
        String exceptionClass = e.getClass().getName();
        String exceptionClassName = exceptionClass.substring(exceptionClass.lastIndexOf(".") + 1, exceptionClass.length());
        // 详细错误信息
        StringBuffer errorDesc = new StringBuffer();
        errorDesc.append("\r\n异常类:" + exceptionClass + "\r\n详细错误内容:\r\n");
        if (e.getStackTrace() != null) {
            Arrays.stream(e.getStackTrace()).forEach(i -> errorDesc.append(i.toString() + "\r\n"));
        }
        // 全局异常已解析内容错误
        if (mapException.containsKey(exceptionClassName)) {
            /**
             * 程序错误 - mapException 中所有异常类（打印及返回完整错误信息）
             */
            log.info(logStr + mapException.get(exceptionClassName) + e.getMessage() + errorDesc.toString());
            return R.error(RType.SYS_ERROR_CODE_500, mapException.get(exceptionClassName) + e.getMessage() + errorDesc.toString());
        } else if (e instanceof ErrorException) {
            /**
             * 自定义异常->类ErrorException
             */
            ErrorException error = (ErrorException) e;
            log.info(logStr + error.toString());
            return R.error(error.getCode(), error.getMsg(), e.getMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            /**
             * 传递参数错误 - 枚举参数 |json参数错误, 请检查json是否完整，序列化失败（只打印核心错误内容）
             */
            HttpMessageNotReadableException error = (HttpMessageNotReadableException) e;
            String errorMsg = "  --> 【可能出现的情况如下：1、传递的JSON参数格式或参数错误 2、时间参数格式错误  \r\n 3、枚举参数错误】  --->  \r\n 详细错误信息：" + e.getMessage();
            log.info(logStr + errorMsg);
            return R.error(RType.SYSTEM_PARAMETER_ILLEGAL_PARAM, errorMsg);
        } else if (e instanceof MethodArgumentNotValidException) {
            /**
             * JSR 303 为参数验证错误（只打印核心错误内容）
             */
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            String field = fieldErrors.get(0).getField();   // 错误字段，多个错误，取第一个
            String msg = fieldErrors.get(0).getDefaultMessage(); //错误 message，多个错误，取第一个
            String errorMsg = RType.SYSTEM_PARAMETER_VALID_ILLEGAL.getMsg() + "  --> 【字段=" + field + " --> 提示用户的错误信息=" + msg + "】    -->    完整的栈错误信息：" + e.getMessage();
            log.info(logStr + errorMsg);
            return R.error(RType.SYSTEM_PARAMETER_VALID_ILLEGAL.getCode(), msg, errorMsg);
        } else if (e instanceof MissingServletRequestParameterException) {
            /**
             * 未传递 Parameter 参数验证错误, 一般为 @Parameter 指定参数未传递（只打印核心错误内容）
             */
            String parameterName = ((MissingServletRequestParameterException) e).getParameterName();
            String parameterType = ((MissingServletRequestParameterException) e).getParameterType();
            String errorMsg = RType.SYSTEM_PARAMETER_ILLEGAL_PARAM.getMsg() + "  --> 【 " + parameterName + ":" + parameterType + "】  --->  详细错误信息:" + e.getMessage();
            log.info(logStr + errorMsg);
            return R.error(RType.SYSTEM_PARAMETER_ILLEGAL_PARAM, errorMsg);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            /**
             * 方法参数类型不匹配mvc
             */
            MethodParameter parameter = ((MethodArgumentTypeMismatchException) e).getParameter();
            String name = ((MethodArgumentTypeMismatchException) e).getName();
            Class<?> requiredType = ((MethodArgumentTypeMismatchException) e).getRequiredType();
            String errorMsg = RType.SYSTEM_PARAMETER_WRONG_TYPE.getMsg() + "  --> 【 " + name + " : " + requiredType + "】  --->  详细错误信息:" + e.getMessage();
            log.info(logStr + errorMsg);
            return R.error(RType.SYSTEM_PARAMETER_WRONG_TYPE, errorMsg);
        } else {
            /**
             *  未解析到的错误（打印及返回完整错误信息）
             */
            log.info(logStr + e.getMessage() + errorDesc.toString());
            return R.error(RType.SYS_ERROR_CODE_500, e.getMessage() + errorDesc.toString());
        }
    }
}


