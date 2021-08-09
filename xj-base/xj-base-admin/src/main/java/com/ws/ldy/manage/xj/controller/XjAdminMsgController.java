package com.ws.ldy.manage.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.auth.util.JwtUtil;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.enums.Base;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.manage.xj.model.dto.XjAdminMsgDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminMsg;
import com.ws.ldy.manage.xj.model.vo.XjAdminMsgVO;
import com.ws.ldy.manage.xj.service.XjAdminMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 订单-->及时消息通知表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-09-23 10:40:23
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/adminMsg")
@Api(value = "XjAdminMsgController", tags = "base-plus--消息通知")
public class XjAdminMsgController extends BaseController<XjAdminMsgService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "isRead", value = "是否已读(0-未读 1-已读)", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "msgTypes", value = "查询指定状态集", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "noMsgTypes", value = "排除查询指定状态集", required = false, paramType = "query", example = ""),
    })
    public R<IPage<XjAdminMsgVO>> findPage(@RequestParam(required = false) String isRead,
                                           @RequestParam(required = false) String msgTypes,
                                           @RequestParam(required = false) String noMsgTypes) {
        Page<XjAdminMsg> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAdminMsg>()
                .orderByDesc(XjAdminMsg::getCreateTime)
                .eq(isRead != null, XjAdminMsg::getIsRead, isRead)
                .eq(XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
                .in(StringUtils.isNotBlank(msgTypes), XjAdminMsg::getMsgType, StringUtils.isNotBlank(msgTypes) ? Arrays.asList(msgTypes.split(",")) : null)
                .notIn(StringUtils.isNotBlank(noMsgTypes), XjAdminMsg::getMsgType, StringUtils.isNotBlank(noMsgTypes) ? Arrays.asList(noMsgTypes.split(",")) : null)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAdminMsgVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "发送消息/添加", notes = "")
    public R<Boolean> insert(@RequestBody @Validated XjAdminMsgDTO dto) {
        return R.success(baseService.insertMsg(dto));
    }


    @RequestMapping(value = "/read", method = RequestMethod.PUT)
    @ApiOperation(value = "消息修改为已读", notes = "")
    public R<Boolean> updIsRead(String id) {
        boolean res = baseService.update(new LambdaUpdateWrapper<XjAdminMsg>()
                .set(XjAdminMsg::getIsRead, Base.IsRead.V1.getValue())
                .eq(XjAdminMsg::getId, id)
        );
        return R.success(res);
    }

    @RequestMapping(value = "/findUnreadNum", method = RequestMethod.GET)
    @ApiOperation(value = "查询未读数量", notes = "")
    public R<Integer> findUnreadSum() {
        int count = baseService.count(new LambdaQueryWrapper<XjAdminMsg>()
                .eq(XjAdminMsg::getIsRead, Base.IsRead.V0.getValue())
                .eq(XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
        );
        return R.successFind(count);
    }
}
