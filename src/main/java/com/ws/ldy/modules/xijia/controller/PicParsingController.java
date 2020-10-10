package com.ws.ldy.modules.xijia.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.xijia.service.PicParsingService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/consumer/xijia/pic")
@Api(value = "PicParsingController", tags = "图片文字解析")
public class PicParsingController extends BaseController {

    @Autowired
    private   PicParsingService picParsingService;

    @RequestMapping(value = "/picTextExtract", method = RequestMethod.POST)//,
    @ApiOperation("解析图片文字")
    public R PicTextExtract(@RequestParam("file") MultipartFile file) {
       return R.success(picParsingService.PicTextExtract(file));
    }
}
