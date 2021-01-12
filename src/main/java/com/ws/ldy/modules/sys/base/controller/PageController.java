package com.ws.ldy.modules.sys.base.controller;

import com.ws.ldy.enums.BaseConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 通用页面跳转Controller,主要用于所有的页面跳转（添加页、修改页/查询页/等跳转）
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 9:49
 */
@Controller
@Api(value = "PageController", tags = "v-1.0 -- Freemarker --> 通用路由跳转", consumes = BaseConstant.InterfaceType.PC_BASE)
public class PageController {

    @RequestMapping(value = "/page/{fileName}", method = RequestMethod.GET)
    @ApiOperation(value = "页面跳转", notes = "跳转对应templates的目录下的html页面,如：/page/admin_menu_menu 跳转到 admin/menu/menu.html,每一层的目录用_分隔")
    public String add(@PathVariable String fileName) {
        String url = fileName.replace("_", "/");
        return url;
    }
}
