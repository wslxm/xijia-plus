package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.AdminConfigDTO;
import com.ws.ldy.modules.admin.model.entity.AdminConfig;
import com.ws.ldy.modules.admin.model.vo.AdminConfigVO;
import com.ws.ldy.modules.admin.service.AdminConfigService;
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
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/admin/adminConfig")
@Api(value = "AdminConfig", tags = "全局配置表-Config", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminConfigController extends BaseController<AdminConfigService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "code", value = "配置code|搜索值(不能重复)", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "name", value = "配置名称", required = false, paramType = "query", example = ""),

    })
    public R<IPage<AdminConfigVO>> findPage(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        Page<AdminConfig> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminConfig>()
                .orderByDesc(AdminConfig::getCreateTime)
                .eq(StringUtils.isNotBlank(code), AdminConfig::getCode, code)
                .like(StringUtils.isNotBlank(name), AdminConfig::getName, name)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, AdminConfigVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<AdminConfigVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), AdminConfigVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated AdminConfigDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        // 判code重复
        if (baseService.count(new LambdaQueryWrapper<AdminConfig>().eq(AdminConfig::getCode, dto.getCode())) > 0) {
            throw new ErrorException(RType.DICT_DUPLICATE);
        }
        return R.successInsert(baseService.save(dto.convert(AdminConfig.class)));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated AdminConfigDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        //
        AdminConfig config = baseService.getById(dto.getId());
        if (!config.getCode().equals(dto.getCode())) {
            if (baseService.count(new LambdaQueryWrapper<AdminConfig>().eq(AdminConfig::getCode, dto.getCode())) > 0) {
                throw new ErrorException(RType.DICT_DUPLICATE);
            }
        }
        return R.successUpdate(baseService.updateById(dto.convert(AdminConfig.class)));
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
