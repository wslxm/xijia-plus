package io.github.wslxm.springbootplus2.starter.wechat.open.controller;


import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.wechat.open.model.vo.WxUserInfoVO;
import io.github.wslxm.springbootplus2.starter.wechat.open.server.WxOpenLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author wangsong
 */
@RestController
@Api(value = "WxOpenController", tags = "微信  -->  微信开放平台")
@RequestMapping(BaseConstant.Uri.API_OPEN +"/wx")
public class WxOpenController {


    @Autowired
    private WxOpenLoginService wxOpenLoginService;

    @RequestMapping(value = "/getWxLoginUrl", method = RequestMethod.GET)
    @ApiOperation("获取wx登录链接")
    public R<String> getWxLoginUrl() {
        return R.success(wxOpenLoginService.getWxOpenLoginUrl());
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "wx登录获取微信用户信息,通过code", notes = "文档地址：https://developers.weixin.qq.com/doc/oplatform/Website_App/WeChat_Login/Wechat_Login.html")
    public R<WxUserInfoVO> login(String code) {
        return R.success(wxOpenLoginService.getUserInfo(code));
    }

}

