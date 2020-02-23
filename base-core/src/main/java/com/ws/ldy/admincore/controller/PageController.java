package com.ws.ldy.admincore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * TODO    通用页面跳转Controller,主要用于添加页、修改页/查询页/等跳转
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 9:49
 */
@Controller
@Api(tags = {"base-core-page"}, description = "通用路径跳转")
public class PageController {

    @GetMapping("/page/{fileName}")
    @ApiOperation("跳转对应templates的目录下的html页面, 如：/page/admin_menu_menu 跳转到 admin/menu/menu.html ,每一层的目录用_分隔")
    public String add(@PathVariable String fileName) {
        String url = fileName.replace("_", "/");
        return url;
    }
}
