package io.github.wslxm.springbootplus2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 通用页面跳转Controller,主要用于所有的页面跳转（添加页、修改页/查询页/等跳转）
 * <P>
 *  Freemarker --> 通用路由跳转
 * </P>
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 9:49
 */
@Controller
public class PageController {

    /**
     * 页面跳转,跳转对应templates的目录下的html页面,如：/page/admin_menu_menu 跳转到 admin/menu/menu.html,每一层的目录用_分隔
     * @author wangsong
     * @param fileName
     * @date 2021/1/23 0023 16:33
     * @return java.lang.String
     * @version 1.0.0
     */
    @RequestMapping(value = "/page/{fileName}", method = RequestMethod.GET)
    public String add(@PathVariable String fileName) {
        String url = fileName.replace("_", "/");
        return url;
    }
}
