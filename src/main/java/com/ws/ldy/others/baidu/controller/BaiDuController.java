package com.ws.ldy.others.baidu.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.others.baidu.service.PicParsingService;
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
@RequestMapping("/baidu")
@Api(value = "PicParsingController", tags = "v-1.4 --  百度api接口")
public class BaiDuController extends BaseController {

    @Autowired
    private PicParsingService picParsingService;

    @RequestMapping(value = "/picTextExtract", method = RequestMethod.POST)
    @ApiOperation(value = "解析图片文字", notes = "上传图片,解析图片中的文字(百度api)")
    public R<String> picTextExtract(@RequestParam("file") MultipartFile file) {
        return R.success(picParsingService.PicTextExtract(file));
    }
}
