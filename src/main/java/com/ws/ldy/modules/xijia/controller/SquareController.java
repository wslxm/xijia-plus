package com.ws.ldy.modules.xijia.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.xijia.service.SquareService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 段子
 * @author wangsong
 * @date 2020/10/7 0007 18:43
 * @return
 * @version 1.0.0
 */
@RestController
@RequestMapping("/consumer/api/square")
@Api(value = "PicParsingController", tags = "段子")
public class SquareController extends BaseController {


    @Autowired
    private SquareService squareService;

    @RequestMapping(value = "/duanzi", method = RequestMethod.GET)
    @ApiOperation("获取段子")
    public R duanzi(int type, Integer page) {
        // TODO 需要处理成APP_id 请求方式，原方式以停止更新
        return R.success(squareService.duanzi(type, page));
    }
}
