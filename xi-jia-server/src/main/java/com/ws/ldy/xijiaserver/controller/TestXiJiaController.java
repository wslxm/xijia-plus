package com.ws.ldy.xijiaserver.controller;


import com.ws.ldy.admincore.common.error.ErrorException;
import com.ws.ldy.admincore.common.vo.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = {"xi-jia-server-Test"}, description = "测试类")
@RequestMapping("/api")
public class TestXiJiaController {

    @ApiOperation("测试1/0异常")
    @GetMapping("/test")
    @ResponseBody
    public void test(){
       //int i =  1/0;
        throw new ErrorException("1000000","自定义异常测试");
    }

    @GetMapping("/test2")
    @ResponseBody
    public ResponseData test2(String name){
        System.out.println(name);
        return ResponseData.success(name);
    }
}
