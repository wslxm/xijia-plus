package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.AdminBannerDTO;
import com.ws.ldy.modules.admin.model.entity.AdminBanner;
import com.ws.ldy.modules.admin.model.vo.AdminBannerVO;
import com.ws.ldy.modules.admin.service.AdminBannerService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/admin/adminBanner")
@Api(value = "AdminBanner", tags = "banner表", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminBannerController extends BaseController<AdminBannerService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "name", value = "banner标题", required = false, paramType = "query", example = ""),

    })
    public R<IPage<AdminBannerVO>> findPage(@RequestParam(required = false) String name
    ) {
        Page<AdminBanner> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminBanner>()
                .orderByAsc(AdminBanner::getPosition)
                .orderByAsc(AdminBanner::getSort)
                .orderByDesc(AdminBanner::getCreateTime)
                .eq(StringUtils.isNotBlank(name), AdminBanner::getName, name)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, AdminBannerVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<AdminBannerVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), AdminBannerVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated AdminBannerDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        AdminBanner adminBanner = dto.convert(AdminBanner.class);
        return R.successInsert(baseService.save(adminBanner));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated AdminBannerDTO dto) {
          if (StringUtils.isBlank(dto.getId())) {
              throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
          }
        return R.successUpdate(baseService.updateById(dto.convert(AdminBanner.class)));
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
