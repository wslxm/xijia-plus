package com.ws.ldy.manage.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.manage.xj.model.dto.XjAdminConfigDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminConfig;
import com.ws.ldy.manage.xj.model.vo.XjAdminConfigVO;
import com.ws.ldy.manage.xj.service.XjAdminConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/adminConfig")
@Api(value = "XjAdminConfigController", tags = "base-plus--全局配置")
public class XjAdminConfigController extends BaseController<XjAdminConfigService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "code", value = "配置code|搜索值(不能重复)", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "name", value = "配置名称", required = false, paramType = "query", example = ""),

    })
    public R<IPage<XjAdminConfigVO>> findPage(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        Page<XjAdminConfig> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAdminConfig>()
                .orderByDesc(XjAdminConfig::getCreateTime)
                .eq(StringUtils.isNotBlank(code), XjAdminConfig::getCode, code)
                .like(StringUtils.isNotBlank(name), XjAdminConfig::getName, name)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAdminConfigVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<XjAdminConfigVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), XjAdminConfigVO.class));
    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "CODE查询", notes = "")
    @ApiImplicitParam(name = "code", value = "配置code|搜索值(不能重复)", required = false, paramType = "query", example = "")
    public R<XjAdminConfigVO> findByCode(@RequestParam String code) {
        XjAdminConfig xjAdminConfig = baseService.findByCode(code);
        return R.successFind(BeanDtoVoUtil.convert(xjAdminConfig, XjAdminConfigVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated XjAdminConfigDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated XjAdminConfigDTO dto) {
        return R.successUpdate(baseService.upd(dto));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }
}
