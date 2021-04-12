package com.ws.ldy.modules.yw.controller;

import com.ws.ldy.common.annotation.Encrypt;
import com.ws.ldy.common.result.R;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.yw.dto.EncryptDTO;
import com.ws.ldy.modules.yw.dto.SignDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(BaseConstant.Uri.apiOpen + "/xj/sign")
@Api(value = "XjAdminBannerController", tags = "body参数验签测试")
public class TestSignContoller {


    /**
     * 参数验签
     *  /api/open/xj/sign/test1
     * @param dto
     * @return
     */
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ApiOperation(value = "参数验签")
    public R<Boolean> test1(@RequestBody(required = false) List<SignDto> dto) {
        System.out.println("成功执行");
        return R.success(true);
    }


    /**
     * 参数加密
     *  /api/open/xj/sign/test2
     * @param dto
     * @return
     */
    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    @ApiOperation(value = "参数加密")
    public R<Boolean> test2(@RequestBody(required = false) @Valid EncryptDTO dto) {
        System.out.println("成功执行");
        return R.success(true);
    }

    /**
     * 参数加密
     *  /api/open/xj/sign/test3
     * @param
     * @return
     */
    @RequestMapping(value = "/test3", method = RequestMethod.POST)
    @ApiOperation(value = "参数加密")
    public R<Boolean> test3(@RequestParam @Encrypt String a,@RequestParam String b) {
        System.out.println("成功执行");
        return R.success(true);
    }
}
