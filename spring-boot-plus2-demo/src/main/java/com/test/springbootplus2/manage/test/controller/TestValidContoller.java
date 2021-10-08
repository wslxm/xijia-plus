package com.test.springbootplus2.manage.test.controller;

import com.test.springbootplus2.manage.test.model.dto.ValidDTO;
import com.github.wslxm.springbootplus2.core.constant.BaseConstant;
import com.github.wslxm.springbootplus2.core.result.R;
import com.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseConstant.Uri.apiOpen + "/xj/valid")
@Api(value = "TestValidContoller", tags = "参数验证测试")
public class TestValidContoller {


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ApiOperation(value = "参数验签")
    public R<Boolean> test(@RequestBody ValidDTO dto) {

        // 字符串判断
        ValidUtil.isNotNull(dto.getPhone(), "用户电话");
        ValidUtil.isNotBlank(dto.getPhone(), "商家电话");
        ValidUtil.isStrLen(dto.getPhone(), 10, 11, "商家电话");
        ValidUtil.isPhone(dto.getPhone(), "商家电话");
        // 数字判断
        ValidUtil.isNotNull(dto.getNum(), "商品数量");
        ValidUtil.isEmail(dto.getEmail(), "商家邮箱");
        ValidUtil.isIDCard(dto.getIDCard(), "商家身份证");
        ValidUtil.isChinese(dto.getName(), "商家名称");
        return R.success(true);
    }
}
