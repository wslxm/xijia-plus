package com.ws.ldy.admincore.controller;

import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO  系统错误页拦截处理，由 ErrorPageConfig配置拦截跳转到此类方法
 *
 *  方式1、直接跳转到具体错误页面
 *  方式2、返回json格式数据，由前端处理
 * @date 2019/11/19 9:43
 * @return
 */
@SuppressWarnings("all")
@Controller
public class ErrorController {

    /**
     * TODO  方式1：系统错误跳错误页
     *
     * @param code 对应错误码，ErrorPageConfig配置
     * @date 2019/11/18 21:15
     * @return
     */
//    @RequestMapping(value = "/error/{code}")
//    public String error(@PathVariable int code, Model model) {
//        String pager = "/admin/error";
//
//        switch (code) {
//            case 404:
//                model.addAttribute("code", 404);
//                pager = "/admin/404";
//                break;
//            case 500:
//                model.addAttribute("code", 500);
//                pager = "/admin/error";
//                break;
//            default:
//        }
//        return pager;
//    }

    /**
     * TODO  方式2：系统错误返回json
     *
     * @param code 对应错误码，ErrorPageConfig配置
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @date 2019/11/18 21:14
     */
    @RequestMapping(value = "/error/{code}")
    @ResponseBody
    public ResponseData error(@PathVariable int code) {
        switch (code) {
            case 401:
                return  ResponseData.error("401", "未授权");
            case 403:
                return  ResponseData.error("403", "拒绝访问");
            case 404:
                return  ResponseData.error("404", "找不到页面,检查url是否正常");
            case 500:
                return  ResponseData.error("500", "系统错误了");
            default:
        }
        return  ResponseData.error("999", "未知错误");
    }
}
