package com.ws.ldy.modules.third.wechat.app.controller;


import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.third.wechat.app.util.WxAppSubscribeUtil;
import com.ws.ldy.modules.third.wechat.app.util.WxAppUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 微信网页授权
 * @author wangsong
 * @date 2020/9/22 0022 11:49
 * @return
 * @version 1.0.0
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/wx/app")
@Api(value = "WxAppController", tags = "WX  -->  APP小程序", consumes = BaseConstant.InterfaceType.PC_BASE)
public class WxAppController {

    @Autowired
    private WxAppUtil wxAppUtil;

    @Autowired
    private WxAppSubscribeUtil wxAppSubscribeUtil;

    @RequestMapping(value = "/auth/getOpenId", method = RequestMethod.GET)
    @ApiOperation(value = "通过code 获取openId", notes = "")
    public R<String> getOpenId(@RequestParam String code) {
        return R.success(wxAppUtil.getOpenId(code));
    }


    @RequestMapping(value = "/subscribe/sendMsgTest", method = RequestMethod.POST)
    @ApiOperation(value = "订阅消息发送", notes = "")
    public R<String> sendMsg(@RequestParam String openId) {
        String userName = "兮家小二";
        String productName = "月缴费";
        BigDecimal amount = new BigDecimal("30.00");
        String time = LocalDateTimeUtil.convertLDTToStr(LocalDateTimeUtil.now());
        return R.success(wxAppSubscribeUtil.payMsg(openId, userName, productName, amount, time));
    }
}