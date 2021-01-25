package com.ws.ldy.modules.third.wechat.mq.controller;


import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.modules.third.wechat.mq.entity.WxAccessTokenVO;
import com.ws.ldy.modules.third.wechat.mq.entity.WxUserInfoVO;
import com.ws.ldy.modules.third.wechat.mq.util.WxMqH5AuthUtil;
import com.ws.ldy.modules.third.wechat.mq.util.WxMqTemplateMsgUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/api/open/wx/mq")
@Api(value = "WxMqController", tags = "WX  -->  微信网页授权")
public class WxMqController {

    @Autowired
    private WxMqH5AuthUtil wxMqH5AuthUtil;


    @Autowired
    private WxMqTemplateMsgUtil wxMqTemplateMsgUtil;


    @RequestMapping(value = "/auth/getAuthCodeUrl", method = RequestMethod.GET)
    @ApiOperation(value = "网页授权登录 -->  1、获取授权URL, 并重定向到指定页", notes = "" +
            "\r\n 注意： 返回的url需在微信开发者工具访问或手机微信中打开" +
            "\r\n 注意： code 有效期5分钟" +
            "\r\n 注意： 需配置：网页授权获取用户基本信息域名" +
            "\r\n 请求该接口返回的 URL[ data=url ] 使用微信打开,会重定向到callback参数地址并携带 code参数"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1=弹窗用户点击授权 2=静默授权" +
                    "\r\n type=2、以scope=snsapi_base 只获取用户openId，用户无感知, 直接进入回调页" +
                    "\r\n type=1、以scope=snsapi_userinfo 获取用户openId+用户基本信息, 需要用户手动同意" +
                    "", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "callback", value = "网页授权地址" +
                    "\r\n 1、callback 必须为参数为可访问的完整url, 不能为域名" +
                    "\r\n 2、必须指定访问文件地址(html/jsp/js等)  如： http://xijia.plus/base/js/app.js, 一般为html页面",
                    required = true, paramType = "query", example = "http://xijia.plus/base/js/app.js")
    })
    public R<String> getAuthCodeUrl(@RequestParam Integer type, String callback) {
        return wxMqH5AuthUtil.getAuthCodeUrl(type, callback);
    }


    @RequestMapping(value = "/auth/getOpenId", method = RequestMethod.GET)
    @ApiOperation(value = "网页授权登录 -->  2、通过code 获取openId", notes = "")
    public R<String> getOpenId(@RequestParam String code) {
        R<WxAccessTokenVO> wxAccessTokenVOData = wxMqH5AuthUtil.getAccessToken(code);
        if (!wxAccessTokenVOData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            return R.error(wxAccessTokenVOData.getCode(), wxAccessTokenVOData.getMsg());
        } else {
            return R.success(wxAccessTokenVOData.getData().getOpenid());
        }
    }


    @RequestMapping(value = "/auth/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "网页授权登录 -->  2、通过code 获取openId + 微信用户信息b", notes = "")
    public R<WxUserInfoVO> getUserInfo(@RequestParam(required = false) String code, @RequestParam(required = false) String openId) {
        return wxMqH5AuthUtil.getUserInfo(code);
    }


    @RequestMapping(value = "/template/sendTest", method = RequestMethod.GET)
    @ApiOperation("模板信息  -->  推送测试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信openId(默认我的测试公众号的测试openId)", required = true, paramType = "query", example = "o8nrg503tfKwepDE4zKeP2g9PujU"),
            @ApiImplicitParam(name = "content", value = "发送内容", required = true, paramType = "query", example = "")
    })
    public R<Boolean> sendTest(String openId, String content) {
        wxMqTemplateMsgUtil.sendTest(openId, content);
        return R.success(true);
    }
}