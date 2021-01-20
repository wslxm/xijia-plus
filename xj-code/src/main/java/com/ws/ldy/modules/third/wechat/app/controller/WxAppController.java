package com.ws.ldy.modules.third.wechat.app.controller;


import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.third.wechat.app.util.WxAppUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信网页授权
 * @author wangsong
 * @date 2020/9/22 0022 11:49
 * @return
 * @version 1.0.0
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/wx/app/auth")
@Api(value = "WxAppController", tags = "WX  -->  APP小程序登录", consumes = BaseConstant.InterfaceType.PC_BASE)
public class WxAppController {

    @Autowired
    private WxAppUtil wxAppUtil;


    @RequestMapping(value = "/getOpenId", method = RequestMethod.GET)
    @ApiOperation(value = "通过code 获取openId", notes = "")
    public R<String> getOpenId(@RequestParam String code) {
        return R.success(wxAppUtil.getOpenId(code));
    }
}