package com.test.springbootplus2.manage.test.controller;

import com.test.springbootplus2.manage.test.model.dto.EncryptDTO;
import com.test.springbootplus2.manage.test.model.dto.Page;
import com.test.springbootplus2.manage.test.model.dto.SignDto;
import com.test.springbootplus2.manage.test.model.vo.EncryptVO;
import com.ws.ldy.config.filter.sing.annotation.XjSecret;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(BaseConstant.Uri.apiOpen + "/xj/sign")
@Api(value = "XjAdminBannerController", tags = "body参数验签测试")
public class TestSignContoller {



    @RequestMapping(value = "/test8", method = RequestMethod.POST)
    @ApiOperation(value = "参数验签")
    public R<Boolean> test8(@RequestBody Page<SignDto> dto) {
        System.out.println("成功执行");
        return R.success(true);
    }


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
    public R<EncryptVO> test2(@RequestBody @Valid EncryptDTO dto) {
        System.out.println("获取参数" + dto.toString());
        //二级对象
        EncryptVO nextEncryptVO = new EncryptVO();
        nextEncryptVO.setA("123");
        nextEncryptVO.setB("456");
        //二级集合
        List<EncryptVO> nextEncryptListVO = new ArrayList<>();
        nextEncryptListVO.add(nextEncryptVO);
        nextEncryptListVO.add(nextEncryptVO);
        //一级数据
        EncryptVO encryptVO = new EncryptVO();
        encryptVO.setA("a");
        encryptVO.setB("b");
        //加入二级数据
        encryptVO.setEncrypt(nextEncryptVO);
        encryptVO.setEncrypts(nextEncryptListVO);
        return R.success(encryptVO);
    }

    /**
     * 参数加密
     *  /api/open/xj/sign/test3
     * @param
     * @return
     */
    @RequestMapping(value = "/test3/{a}", method = RequestMethod.POST)
    @ApiOperation(value = "参数加密")
    public R<Boolean> test3(@PathVariable @XjSecret String a, @RequestHeader @XjSecret String b) {
        System.out.println("成功执行");
        return R.success(true);
    }
}
