package com.ws.ldy.modules.sys.base.controller;


import com.ws.ldy.common.result.R;
import com.ws.ldy.config.idempotent.annotation.XjApiIdempotent;
import com.ws.ldy.enums.BaseConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "IdempotentController", tags = "v-1.4 -- 幂等",consumes = BaseConstant.InterfaceType.PC_BASE)
@RequestMapping(BaseConstant.Sys.API + "/idempotent")
public class IdempotentController {


    @RequestMapping(value = "/findIdempotentToken", method = RequestMethod.GET)
    @ApiOperation(value = "获取幂等TOKEN", notes = "调用此接口，将生成幂等TOKEN 放入响应头的 IDEMPOTENT_TOKEN 字段中")
    @XjApiIdempotent
    public R<Void> findIdempotentToken() {
       return R.success();
    }
}
