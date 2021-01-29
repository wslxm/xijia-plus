package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.entity.PetsUser;
import com.ws.ldy.modules.yw.pets.model.vo.PetsUserVO;
import com.ws.ldy.modules.yw.pets.service.PetsUserService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsUser")
@Api(value = "PetsUserController", tags = "用户表")
public class PetsUserController extends BaseController<PetsUserService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "用户信息--分页查询(黑名单/白名单)", notes = "    " +
            " 统计-  查询报销次数\n" +
            " 统计-  宠物数量\n" +
            " 统计-  缴费次数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "fullName", value = "姓名", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "phone", value = "电话", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "disable", value = "禁用(字典code 0-正常 1-禁用)", required = false, paramType = "query", example = ""),

    })
    public R<IPage<PetsUserVO>> findPage(@RequestParam(required = false) String fullName,
                                         @RequestParam(required = false) String phone,
                                         @RequestParam(required = false) String disable
    ) {
        Page<PetsUserVO> resPage = baseService.findPage(this.getPage(), fullName, phone, disable);
        return R.successFind(resPage);
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "用户信息--ID查询(注意：这里没有带分页查询的统计数据)", notes = "")
    public R<PetsUserVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), PetsUserVO.class));
    }


    @RequestMapping(value = "/updDisable", method = RequestMethod.PUT)
    @ApiOperation(value = "禁用(黑名单)/启用", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户Id", required = true, paramType = "query", example = ""),
            @ApiImplicitParam(name = "disable", value = "disable=0 正常 | disable=1 禁用", required = true, paramType = "query", example = "")
    })
    public R<Boolean> findId(@RequestParam String id, @RequestParam Integer disable) {
        baseService.update(new LambdaUpdateWrapper<PetsUser>()
                .set(PetsUser::getDisable, disable)
                .eq(PetsUser::getId, id)
        );
        return R.success(true);
    }


    @RequestMapping(value = "/findTotal", method = RequestMethod.GET)
    @ApiOperation(value = "查询加入总人数(=注册总人数)", notes = "")
    public R<Integer> findTotal() {
        return R.success(baseService.count());
    }


}
