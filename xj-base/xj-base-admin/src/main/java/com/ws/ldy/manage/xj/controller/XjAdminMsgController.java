package com.ws.ldy.manage.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.auth.util.JwtUtil;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.enums.Base;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.utils.excel.ExcelUtil;
import com.ws.ldy.manage.xj.model.dto.XjAdminMsgDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminMsg;
import com.ws.ldy.manage.xj.model.query.XjAdminMsgQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminMsgVO;
import com.ws.ldy.manage.xj.service.XjAdminMsgService;
import io.swagger.annotations.Api;
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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/msg")
@Api(value = "XjAdminMsgController", tags = "base-plus--消息通知")
public class XjAdminMsgController extends BaseController<XjAdminMsgService> {

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<XjAdminMsgVO>> list(@ModelAttribute @Validated XjAdminMsgQuery query) {
        LambdaQueryWrapper<XjAdminMsg> queryWrapper = new LambdaQueryWrapper<XjAdminMsg>()
                .orderByDesc(XjAdminMsg::getCreateTime)
                .eq(query.getIsRead() != null, XjAdminMsg::getIsRead, query.getIsRead())
                .eq(XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
                .in(StringUtils.isNotBlank(query.getMsgTypes()), XjAdminMsg::getMsgType, StringUtils.isNotBlank(query.getMsgTypes()) ? Arrays.asList(query.getMsgTypes().split(",")) : null)
                .notIn(StringUtils.isNotBlank(query.getNoMsgTypes()), XjAdminMsg::getMsgType, StringUtils.isNotBlank(query.getNoMsgTypes()) ? Arrays.asList(query.getNoMsgTypes().split(",")) : null);
        if (query.getIsExport()) {
            // excel
            ExcelUtil.exportExcelDownload(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminMsgVO.class), response);
            return null;
        } else if (query.getCurrent() <= 0) {
            // list
            IPage<XjAdminMsgVO> page = new Page<>();
            page = page.setRecords(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminMsgVO.class));
            return R.successFind(page);
        } else {
            // page
            IPage<XjAdminMsgVO> page = BeanDtoVoUtil.pageVo(baseService.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminMsgVO.class);
            return R.successFind(page);
        }
    }

    @ApiOperation(value = "查询未读数量(当前登录用户)")
    @GetMapping(value = "/findUnreadNum")
    public R<Integer> unread() {
        int count = baseService.count(new LambdaQueryWrapper<XjAdminMsg>()
                .eq(XjAdminMsg::getIsRead, Base.IsRead.V0.getValue())
                .eq(XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
        );
        return R.successFind(count);
    }

    @PostMapping
    @ApiOperation(value = "添加/发送消息")
    public R<Boolean> insert(@RequestBody @Validated XjAdminMsgDTO dto) {
        return R.success(baseService.insertMsg(dto));
    }


    @PutMapping(value = "/{id}/read")
    @ApiOperation(value = "消息修改为已读")
    public R<Boolean> upd(@PathVariable String id) {
        XjAdminMsg entity = new XjAdminMsg();
        entity.setId(id);
        entity.setIsRead(Base.IsRead.V1.getValue());
        return R.successUpdate(baseService.updateById(entity));
    }
}
