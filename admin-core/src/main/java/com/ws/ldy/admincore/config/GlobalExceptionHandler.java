//package com.ws.ldy.admincore.config;
//
//import com.ws.ldy.admincore.controller.BaseController;
//import com.ws.ldy.admincore.controller.vo.ResponseData;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 全局异常捕获返回500错误
// */
//@ControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler extends BaseController {
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public ResponseData exceptionHandler(Exception e) {
//        log.info("###全局捕获异常###,error:{}", e);
//        return ResponseData.error();
//    }
//}
//
