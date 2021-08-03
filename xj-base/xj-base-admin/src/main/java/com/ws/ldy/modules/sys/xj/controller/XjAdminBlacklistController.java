package com.ws.ldy.modules.sys.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.cache.CacheUtil;
import com.ws.ldy.common.cache.CacheKey;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.aspect.gateway.SysBlacklist;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.sys.xj.model.dto.XjAdminBlacklistDTO;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminBlacklist;
import com.ws.ldy.modules.sys.xj.model.vo.XjAdminBlacklistVO;
import com.ws.ldy.modules.sys.xj.service.XjAdminBlacklistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/adminBlacklist")
@Api(value = "XjAdminBlacklistController", tags = "base-plus--黑名单")
public class XjAdminBlacklistController extends BaseController<XjAdminBlacklistService> {

    @Autowired
    private SysBlacklist sysBlacklist;

    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "type", value = "1-白名单(* 表示允许除黑名单外的所有ip请求, 否则只允许白名单中的ip请求) 2-黑名单(禁止ip访问)", required = false, paramType = "query", example = ""),

    })
    public R<IPage<XjAdminBlacklistVO>> findPage(
            @RequestParam(required = false) Integer type
    ) {
        Page<XjAdminBlacklist> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAdminBlacklist>()
                .orderByDesc(XjAdminBlacklist::getCreateTime)
                .eq(type != null, XjAdminBlacklist::getType, type)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAdminBlacklistVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<XjAdminBlacklistVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), XjAdminBlacklistVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated XjAdminBlacklistDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        XjAdminBlacklist adminBlacklist = dto.convert(XjAdminBlacklist.class);
        boolean result = baseService.save(adminBlacklist);
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return R.successInsert(result);
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated XjAdminBlacklistDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        boolean result = baseService.updateById(dto.convert(XjAdminBlacklist.class));
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return R.successUpdate(result);
    }

    @RequestMapping(value = "/updDisable", method = RequestMethod.PUT)
    @ApiOperation(value = "禁用/启用", notes = "必须传递ID")
    public R<Boolean> updDisable(@RequestParam String id, @RequestParam String disable) {
        boolean result = baseService.update(new LambdaUpdateWrapper<XjAdminBlacklist>()
                .set(XjAdminBlacklist::getDisable, disable)
                .eq(XjAdminBlacklist::getId, id));
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return R.successUpdate(result);
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        boolean result = baseService.removeById(id);
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return R.successDelete(result);
    }
}
