package com.ws.ldy.manage.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.config.error.ErrorException;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.manage.xj.model.dto.XjAdminBannerDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminBanner;
import com.ws.ldy.manage.xj.model.vo.XjAdminBannerVO;
import com.ws.ldy.manage.xj.service.XjAdminBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/adminBanner")
@Api(value = "XjAdminBannerController", tags = "base-plus--banner")
public class XjAdminBannerController extends BaseController<XjAdminBannerService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "name", value = "banner标题", required = false, paramType = "query", example = ""),
    })
    public R<IPage<XjAdminBannerVO>> findPage(@RequestParam(required = false) String name
    ) {
        Page<XjAdminBanner> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAdminBanner>()
                .orderByAsc(XjAdminBanner::getPosition)
                .orderByAsc(XjAdminBanner::getSort)
                .orderByDesc(XjAdminBanner::getCreateTime)
                .eq(StringUtils.isNotBlank(name), XjAdminBanner::getName, name)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAdminBannerVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<XjAdminBannerVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), XjAdminBannerVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated XjAdminBannerDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        XjAdminBanner adminBanner = dto.convert(XjAdminBanner.class);
        return R.successInsert(baseService.save(adminBanner));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated XjAdminBannerDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(XjAdminBanner.class)));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量ID删除", notes = "")
    public R<Boolean> delByIds(@RequestParam String[] ids) {
        return R.successDelete(baseService.removeByIds(Arrays.asList(ids)));
    }
}
