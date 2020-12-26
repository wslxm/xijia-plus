package com.ws.ldy.modules.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.modules.xj.model.dto.XjAdminLogDTO;
import com.ws.ldy.modules.xj.model.entity.XjAdminLog;
import com.ws.ldy.modules.xj.model.vo.XjAdminLogVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ws.ldy.enums.BaseConstant;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import com.ws.ldy.config.error.ErrorException;

import com.ws.ldy.modules.xj.service.XjAdminLogService;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.others.base.controller.BaseController;

import java.util.Arrays;


/**
 * 操作记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-28 20:44:32
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/admin/adminLog")
@Api(value = "XjAdminLogController", tags = "base-plus--操作记录", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class XjAdminLogController extends BaseController<XjAdminLogService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "fullName", value = "请求人", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "uri", value = "请求uri", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "methodDesc", value = "请求方法--swagger注释", required = false, paramType = "query", example = ""),

    })
    public R<IPage<XjAdminLogVO>> findPage(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String uri,
            @RequestParam(required = false) String classDesc,
            @RequestParam(required = false) String methodDesc
    ) {
        Page<XjAdminLog> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAdminLog>()
                .orderByDesc(XjAdminLog::getCreateTime)
                .like(StringUtils.isNotBlank(fullName), XjAdminLog::getFullName, fullName)
                .like(StringUtils.isNotBlank(uri), XjAdminLog::getUri, uri)
                .like(StringUtils.isNotBlank(classDesc), XjAdminLog::getClassDesc, classDesc)
                .like(StringUtils.isNotBlank(methodDesc), XjAdminLog::getMethodDesc, methodDesc)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAdminLogVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<XjAdminLogVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), XjAdminLogVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated XjAdminLogDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        XjAdminLog adminLog = dto.convert(XjAdminLog.class);
        return R.successInsert(baseService.save(adminLog));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated XjAdminLogDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(XjAdminLog.class)));
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
