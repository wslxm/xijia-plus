package com.ws.ldy.client.sys.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminBanner;
import com.ws.ldy.modules.sys.xj.model.vo.XjAdminBannerVO;
import com.ws.ldy.modules.sys.xj.service.XjAdminBannerService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * banner表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/client/xj/adminBanner")
@Api(value = "UXjAdminBannerController", tags = "yh--base-plus--banner", consumes = BaseConstant.InterfaceType.PC_USER)
public class UXjAdminBannerController extends BaseController<XjAdminBannerService> {


    @RequestMapping(value = "/findByPosition", method = RequestMethod.GET)
    @ApiOperation(value = "位置查询", notes = "")
    @ApiImplicitParam(name = "position", value = "位置(字典code)", required = true, paramType = "query", example = "")
    public R<List<XjAdminBannerVO>> findPage(@RequestParam Integer position) {
        List<XjAdminBanner> list = baseService.list(new LambdaQueryWrapper<XjAdminBanner>()
                .orderByAsc(XjAdminBanner::getSort)
                .orderByDesc(XjAdminBanner::getCreateTime)
                .eq(XjAdminBanner::getPosition, position)
        );
        return R.successFind(BeanDtoVoUtil.listVo(list, XjAdminBannerVO.class));
    }
}
