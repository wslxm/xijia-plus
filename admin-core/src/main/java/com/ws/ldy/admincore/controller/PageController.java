package com.ws.ldy.adminconsole.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * TODO    通用页面跳转Controller,主要用于添加页、主页
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/13 9:49
 */
@Controller
public class PageController {

    /**
     * TODO  跳转指定页添加页  示例：/page/menu_addRoot1
     *
     * @param fileName --> menu_addRoot1 ：对应页面目录路径, 每一层的目录用 _分隔
     */
    @RequestMapping("/page/{fileName}")
    public String add(@PathVariable String fileName){
        String url =fileName.replace("_","/");
        return url;
    }
}
