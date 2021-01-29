package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapital;
import com.ws.ldy.modules.yw.pets.model.vo.PetsCapitalVO;
import com.ws.ldy.modules.yw.pets.service.PetsCapitalService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 互助资金表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:52:40
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsCapital")
@Api(value = "PetsCapitalController", tags = "互助资金表")
public class PetsCapitalController extends BaseController<PetsCapitalService> {


    @RequestMapping(value = "/findCapital", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
    })
    public R<IPage<PetsCapitalVO>> findCapital() {
        Page<PetsCapital> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PetsCapital>()
                .orderByDesc(PetsCapital::getCreateTime)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PetsCapitalVO.class));
    }


    @RequestMapping(value = "/findCapitalOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询平台资金", notes = "")
    public R<PetsCapitalVO> findCapitalOne() {
        List<PetsCapital> list = baseService.list();
        if (list.size() <= 0) {
            throw new ErrorException(RType.SYS_ERROR_DOES_NOT_EXIST);
        }
        return R.successFind(BeanDtoVoUtil.convert(list.get(0), PetsCapitalVO.class));
    }
}
