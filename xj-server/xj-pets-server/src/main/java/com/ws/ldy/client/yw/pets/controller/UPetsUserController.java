package com.ws.ldy.client.yw.pets.controller;

import com.ws.ldy.client.yw.pets.model.dto.LoginDTO;
import com.ws.ldy.client.yw.pets.model.dto.UpdHeadOrNameOrPhoneDTO;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.dto.PetsUserDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsUser;
import com.ws.ldy.modules.yw.pets.model.vo.PetsUserVO;
import com.ws.ldy.modules.yw.pets.service.PetsUserService;
import com.ws.ldy.modules.third.aliyun.sms.util.AliSmsUtil;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:03:46
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsUser")
@Api(value = "UPetsUserController", tags = "yh--用户表")
public class UPetsUserController extends BaseController<PetsUserService> {


    @Autowired
    private AliSmsUtil aliSmsUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "有账号登录/没有账号注册")
    public R<Boolean> login(@RequestBody @Validated LoginDTO dto) {
        Boolean result = baseService.login(dto);
        return R.success(result);
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ApiOperation(value = "查询登录用户信息", notes = "有账号登录/没有账号注册")
    public R<PetsUserVO> findUser() {
        PetsUser user = baseService.findUser();
        return R.success(BeanDtoVoUtil.convert(user, PetsUserVO.class));
    }


    @RequestMapping(value = "/findNew5", method = RequestMethod.GET)
    @ApiOperation(value = "查询最近加入平台的5个用户", notes = "")
    public R<List<PetsUserVO>> findNew5() {
        List<PetsUser> petsUsers = baseService.findNew5();
        return R.success(BeanDtoVoUtil.listVo(petsUsers, PetsUserVO.class));
    }


    @RequestMapping(value = "/findTotal", method = RequestMethod.GET)
    @ApiOperation(value = "查询加入总人数(=注册总人数)", notes = "")
    public R<Integer> findTotal() {
        return R.success(baseService.count());
    }


    @RequestMapping(value = "/updPetOwner", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑--添加宠物主人信息", notes = "必须传递ID")
    public R<Boolean> updPetOwner(@RequestBody @Validated PetsUserDTO dto) {
        Boolean b = baseService.updPetOwner(dto);
        return R.successUpdate(true);
    }


    @RequestMapping(value = "/updHeadOrNameOrPhone", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑--个人信息编辑(头像/昵称/电话)", notes = "必须传递ID")
    public R<Boolean> updHeadOrNameOrPhone(@RequestBody @Validated UpdHeadOrNameOrPhoneDTO dto) {
        baseService.updHeadOrNameOrPhone(dto);
        return R.success(true);
    }


    @RequestMapping(value = "/updPhoneSmsCode", method = RequestMethod.PUT)
    @ApiOperation(value = "修改电话前发送短信", notes = "")
    public R<Boolean> updPhoneSmsCode(@RequestParam String phone) {
        R<String> codeData = aliSmsUtil.sendCode(phone);
        return R.success(true);
    }


    @RequestMapping(value = "/isItCertified", method = RequestMethod.GET)
    @ApiOperation(value = "是否认证（返回 true-已认证）", notes = "")
    public R<Boolean> isItCertified() {
        boolean result = baseService.isItCertified(JwtUtil.getJwtUser(request).getUserId());
        return R.success(result);
    }
}


