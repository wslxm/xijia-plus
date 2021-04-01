package com.ws.ldy.modules.yw;

import com.ws.ldy.common.result.R;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.yw.dto.SignDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(BaseConstant.Uri.apiOpen + "/xj/sign")
@Api(value = "XjAdminBannerController", tags = "body参数验签测试")
public class TestSignContoller {


    // /api/open/xj/sign/test1
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public R<Boolean> test1(@RequestBody(required = false) List<SignDto> dto) {
        System.out.println("成功执行");
        return R.success(true);
    }

//    @RequestMapping(value = "/test2", method = RequestMethod.POST)
//    public R<Boolean> test2(@RequestBody SignDto dto) {
//        System.out.println("成功执行");
//        return R.success(true);
//    }
//
//    @RequestMapping(value = "/test3", method = RequestMethod.POST)
//    @ApiOperation(value = "验签参数", notes = "")
//    public R<Boolean> test3(@RequestBody SignDto dto) {
//        System.out.println("成功执行");
//        return R.success(true);
//    }

}
