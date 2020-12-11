package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ws.ldy.enums.BaseConstant;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import com.ws.ldy.config.error.ErrorException;
import org.springframework.format.annotation.DateTimeFormat;

import com.ws.ldy.modules.admin.model.entity.AdminBlacklist;
import com.ws.ldy.modules.admin.model.vo.AdminBlacklistVO;
import com.ws.ldy.modules.admin.model.dto.AdminBlacklistDTO;
import com.ws.ldy.modules.admin.service.AdminBlacklistService;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.others.base.controller.BaseController;

import java.util.Arrays;
import java.time.LocalDateTime;


/**
 * 黑名单
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-27 22:44:49
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/admin/adminBlacklist")
@Api(value = "AdminBlacklistController", tags = "黑名单", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminBlacklistController extends BaseController<AdminBlacklistService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "type", value = "1-白名单(* 表示允许除黑名单外的所有ip请求, 否则只允许白名单中的ip请求) 2-黑名单(禁止ip访问)", required = false, paramType = "query", example = ""),

    })
    public R<IPage<AdminBlacklistVO>> findPage(
            @RequestParam(required = false) Integer type
    ) {
        Page<AdminBlacklist> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminBlacklist>()
                .orderByDesc(AdminBlacklist::getCreateTime)
                .eq(type != null, AdminBlacklist::getType, type)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, AdminBlacklistVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<AdminBlacklistVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), AdminBlacklistVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated AdminBlacklistDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        AdminBlacklist adminBlacklist = dto.convert(AdminBlacklist.class);
        boolean result = baseService.save(adminBlacklist);
        // 置空缓存
        BaseConstant.Cache.BLACKLIST_CACHE = null;
        return R.successInsert(result);
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated AdminBlacklistDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        boolean result = baseService.updateById(dto.convert(AdminBlacklist.class));
        // 置空缓存
        BaseConstant.Cache.BLACKLIST_CACHE = null;
        return R.successUpdate(result);
    }

    @RequestMapping(value = "/updDisable", method = RequestMethod.PUT)
    @ApiOperation(value = "禁用/启用", notes = "必须传递ID")
    public R<Boolean> updDisable(@RequestParam String id, @RequestParam String disable) {
        boolean result = baseService.update(new LambdaUpdateWrapper<AdminBlacklist>()
                .set(AdminBlacklist::getDisable, disable)
                .eq(AdminBlacklist::getId, id));
        // 置空缓存
        BaseConstant.Cache.BLACKLIST_CACHE = null;
        return R.successUpdate(result );
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        boolean result = baseService.removeById(id);
        // 置空缓存
        BaseConstant.Cache.BLACKLIST_CACHE = null;
        return R.successDelete(result);
    }
}
