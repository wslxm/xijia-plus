package com.test.springbootplus2.manage.test.controller;

import com.test.springbootplus2.manage.test.model.dto.EncryptDTO;
import com.test.springbootplus2.manage.test.model.dto.Page;
import com.test.springbootplus2.manage.test.model.dto.SignDto;
import com.test.springbootplus2.manage.test.model.vo.Encrypt2VO;
import com.test.springbootplus2.manage.test.model.vo.EncryptVO;
import io.github.wslxm.springbootplus2.common.annotation.XjSecret;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
/**
 * @author wangsong
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_OPEN + "/test/sign")
@Api(value = "BannerController", tags = "body参数验签测试")
public class TestSignContoller {


    @RequestMapping(value = "/test8", method = RequestMethod.POST)
    @ApiOperation(value = "参数验签")
    public Result<Boolean> test8(@RequestBody Page<SignDto> dto) {
        System.out.println("成功执行");
        return Result.success(true);
    }


    /**
     * 参数验签
     *  /api/open/xj/sign/test1
     * @param dto
     * @return
     */
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ApiOperation(value = "参数验签")
    public Result<Boolean> test1(@RequestBody(required = false) List<SignDto> dto) {
        System.out.println("成功执行");
        return Result.success(true);
    }


    /**
     * 参数加密
     *  /api/open/xj/sign/test2
     * @param dto
     * @return
     */
    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    @ApiOperation(value = "请求参数解密,响应参数加密")
    public Result<EncryptVO> test2(@RequestBody @Valid EncryptDTO dto) {
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
        return Result.success(encryptVO);
    }

    /**
     * 参数加密
     *  /api/open/xj/sign/test3
     * @param
     * @return
     */
    @RequestMapping(value = "/test3/{a}", method = RequestMethod.POST)
    @ApiOperation(value = "请求参数解密")
    public Result<Boolean> test3(@PathVariable @XjSecret String a, @RequestHeader @XjSecret String b) {
        System.out.println("成功执行");
        return Result.success(true);
    }



    @RequestMapping(value = "/test4/{a}", method = RequestMethod.POST)
    @ApiOperation(value = "响应参数脱敏")
    public Result<Encrypt2VO> test4() {
        //二级对象
        Encrypt2VO nextEncryptVO = new Encrypt2VO();
        nextEncryptVO.setA("123");
        nextEncryptVO.setPhone("17628680001");
        //二级集合
        List<Encrypt2VO> nextEncryptListVO = new ArrayList<>();
        nextEncryptListVO.add(nextEncryptVO);
        nextEncryptListVO.add(nextEncryptVO);
        //一级数据
        Encrypt2VO encryptVO = new Encrypt2VO();
        encryptVO.setA("a");
        encryptVO.setPhone("17628680002");
        //加入二级数据
        encryptVO.setEncrypt(nextEncryptVO);
        encryptVO.setEncrypts(nextEncryptListVO);

        return Result.success(encryptVO);
    }
}
