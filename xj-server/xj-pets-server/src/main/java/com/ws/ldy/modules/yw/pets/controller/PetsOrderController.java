package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderPageVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderVO;
import com.ws.ldy.modules.yw.pets.service.PetsOrderService;
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
 * 支付订单/缴费管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:07
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsOrder")
@Api(value = "PetsOrderController", tags = "支付订单/缴费管理表")
public class PetsOrderController extends BaseController<PetsOrderService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "订单列表--分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "orderState", value = "订单状态( 字典code 0-待支付*默认 1-支付失败 2-支付成功/待绑定宠物  3-已绑定宠物 4-订单失效)", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "fullName", value = "姓名", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "phone", value = "状态", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", example = ""),

    })
    public R<IPage<PetsOrderPageVO>> findPage(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer orderState,
            @RequestParam(required = false) String orderNo
    ) {
        Page<PetsOrderPageVO> page = baseService.findPage(this.getPage(), fullName, phone, orderState, orderNo);
        return R.successFind(page);
    }


    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "订单详情--ID查询", notes = "")
    public R<PetsOrderVO> findId(@RequestParam String id) {
        // TODO 订单详情
        // 联表查询用户信息
        // 联表查询错误信息
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), PetsOrderVO.class));
    }
}
