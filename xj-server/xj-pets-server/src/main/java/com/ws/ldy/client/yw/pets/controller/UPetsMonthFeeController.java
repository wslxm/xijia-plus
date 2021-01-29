package com.ws.ldy.client.yw.pets.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.entity.PetsMonthFee;
import com.ws.ldy.modules.yw.pets.model.vo.PetsMonthFeeVO;
import com.ws.ldy.modules.yw.pets.service.PetsMonthFeeService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 月费表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:53:43
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsMonthFee")
@Api(value = "UPetsMonthFeeController", tags = "yh--月费表")
public class UPetsMonthFeeController extends BaseController<PetsMonthFeeService> {


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前用户支付/续费金额", notes = "根据申报成功数量来判断基数调整金额")
    public R<List<PetsMonthFeeVO>> findList() {
        List<PetsMonthFee> petsMonthFeeList = baseService.findList();
        return R.success(BeanDtoVoUtil.listVo(petsMonthFeeList, PetsMonthFeeVO.class));
    }
}
