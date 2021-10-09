package io.github.wslxm.springbootplus2.starter.qq.controller;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.qq.model.vo.QQLoginVO;
import io.github.wslxm.springbootplus2.starter.qq.service.QQLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * qq登录
 *
 * @author wangsong
 * @date 2019年6月18日 下午8:04:15
 */
@RestController
@Api(value = "QQController", tags = "QQ  -->  QQ互联")
@RequestMapping("/api/open/qq")
public class QQController {


    @Autowired
    private QQLoginService qqLoginService;


    @RequestMapping(value = "/getQQLoginUrl", method = RequestMethod.GET)
    @ApiOperation("获取qq登录链接")
    public R<String> getQQLoginUrl()  {
        return R.success(qqLoginService.getQQLoginUrl());
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation("qq登录,通过code")
    public R<QQLoginVO> login(String code)  {
        return R.success(qqLoginService.getUserInfo(code));
    }
}
