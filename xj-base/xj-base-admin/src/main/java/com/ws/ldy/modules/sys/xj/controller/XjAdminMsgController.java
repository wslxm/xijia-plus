package com.ws.ldy.modules.sys.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.enums.Base;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminMsg;
import com.ws.ldy.modules.sys.xj.model.vo.XjAdminMsgVO;
import com.ws.ldy.modules.sys.xj.service.XjAdminMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/adminMsg")
@Api(value = "XjAdminMsgController", tags = "base-plus--消息通知")
public class XjAdminMsgController extends BaseController<XjAdminMsgService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "isRead", value = "是否已读(0-未读 1-已读)", required = false, paramType = "query", example = "20"),
    })
    public R<IPage<XjAdminMsgVO>> findPage(Integer isRead) {
        Page<XjAdminMsg> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAdminMsg>()
                .orderByDesc(XjAdminMsg::getCreateTime)
                .eq(isRead != null, XjAdminMsg::getIsRead, isRead)
                .eq( XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAdminMsgVO.class));
    }


    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @ApiOperation(value = "消息修改为已读", notes = "")
    public R<Boolean> updIsRead(String id) {
        boolean res = baseService.update(new LambdaUpdateWrapper<XjAdminMsg>()
                .set(XjAdminMsg::getIsRead, Base.IsRead.V1.getValue())
                .eq(XjAdminMsg::getId, id)
        );
        return R.successFind(res);
    }
}
