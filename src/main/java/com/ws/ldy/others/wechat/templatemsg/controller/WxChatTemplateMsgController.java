package com.ws.ldy.others.wechat.templatemsg.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.others.wechat.templatemsg.service.WxChatTemplateMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 微信公众号测试接口
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/9 0009 14:41 
 * @version 1.0.0
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/wechat/template/msg")
@Api(value = "WxChatTemplateMsgController", tags = "v-1.2 -- WeChat --> 微信模板消息推送",consumes = BaseConstant.InterfaceType.PC_BASE)
public class WxChatTemplateMsgController {

    @Autowired
    private WxChatTemplateMsgService wxChatTemplateMsgService;

    /**
     * 我的openId:  o8nrg503tfKwepDE4zKeP2g9PujU
     * @return
     */
    @RequestMapping(value = "/sendTest", method = RequestMethod.GET)
    @ApiOperation("推送测试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信openId", required = true, paramType = "query", example = "o8nrg503tfKwepDE4zKeP2g9PujU"),
            @ApiImplicitParam(name = "content", value = "发送内容", required = true, paramType = "query", example = "")
    })
    public R<Boolean> sendTest(String openId, String content) {
        wxChatTemplateMsgService.sendTest(openId, content);
        return R.success(true);
    }
}
