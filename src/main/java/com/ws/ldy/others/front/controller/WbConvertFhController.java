package com.ws.ldy.others.front.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.others.base.controller.BaseController;
import com.ws.ldy.others.front.util.TransformUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 符号字符生成器
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/front/wbConvertFhController")
@Api(value = "wbConvertFhController", tags = "v-1.0 -- 文字转符号",consumes = BaseConstant.InterfaceType.PC_BASE)
public class WbConvertFhController extends BaseController {


    @ApiOperation("")
    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = false, paramType = "query"),
            @ApiImplicitParam(name = "narrow", value = "缩小倍数（建议3或4）", required = false, paramType = "query")
    })
    public R<String> convert(@RequestParam String content, @RequestParam Integer narrow) {
        String transform = null;
        if (StringUtils.isNotBlank(content)) {
            transform = TransformUtil.transform(content, narrow);
        }
        //System.out.println(transform);
        //String replace = transform.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;&nbsp;");
        return R.success(transform);
    }
}
//&nbsp; ：一个字符的半角的不断行的空格，如果需要在网页中插入多个空格，可以将“&nbsp;”代码写多遍；
//        &ensp; ：一个字符的半角的空格，也可以将“&ensp;”写多遍来插入多个空格；
//        &emsp;

