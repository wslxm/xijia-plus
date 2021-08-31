package com.ws.ldy.manage.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.auth.util.JwtUtil;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.enums.Base;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.xj.mapper.XjAdminMsgMapper;
import com.ws.ldy.manage.xj.model.dto.XjAdminMsgDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminMsg;
import com.ws.ldy.manage.xj.model.query.XjAdminMsgQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminMsgFindAllNumVO;
import com.ws.ldy.manage.xj.model.vo.XjAdminMsgVO;
import com.ws.ldy.manage.xj.service.XjAdminMsgService;
import org.springframework.stereotype.Service;

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
@Service
public class XjAdminMsgServiceImpl extends BaseIServiceImpl<XjAdminMsgMapper, XjAdminMsg> implements XjAdminMsgService {

    @Override
    public IPage<XjAdminMsgVO> list(XjAdminMsgQuery query) {
        LambdaQueryWrapper<XjAdminMsg> queryWrapper = new LambdaQueryWrapper<XjAdminMsg>()
                .orderByDesc(XjAdminMsg::getCreateTime)
                .eq(query.getIsRead() != null, XjAdminMsg::getIsRead, query.getIsRead())
                .eq(query.getIsLoginUser(), XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
                .in(StringUtils.isNotBlank(query.getMsgTypes()), XjAdminMsg::getMsgType, StringUtils.isNotBlank(query.getMsgTypes()) ? Arrays.asList(query.getMsgTypes().split(",")) : null)
                .notIn(StringUtils.isNotBlank(query.getNoMsgTypes()), XjAdminMsg::getMsgType, StringUtils.isNotBlank(query.getNoMsgTypes()) ? Arrays.asList(query.getNoMsgTypes().split(",")) : null);
        if (query.getCurrent() <= 0) {
            IPage<XjAdminMsgVO> page = new Page<>();
            return page.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), XjAdminMsgVO.class));
        } else {
            return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminMsgVO.class);
        }
    }

    @Override
    public String insertMsg(XjAdminMsgDTO dto) {
        XjAdminMsg entity = new XjAdminMsg();
        entity.setUserId(dto.getUserId());
        entity.setContent(dto.getContent());
        entity.setUserType(dto.getUserType());
        entity.setMsgType(dto.getMsgType());
        entity.setIsRead(Base.IsRead.V0.getValue());
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean updRead(String id) {
        XjAdminMsg entity = new XjAdminMsg();
        entity.setId(id);
        entity.setIsRead(Base.IsRead.V1.getValue());
        return this.updateById(entity);
    }

    @Override
    public Integer findUnreadNum() {
        return this.count(new LambdaQueryWrapper<XjAdminMsg>()
                .eq(XjAdminMsg::getIsRead, Base.IsRead.V0.getValue())
                .eq(XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
        );
    }

    @Override
    public XjAdminMsgFindAllNumVO findAllNum() {
        String userId = JwtUtil.getJwtUser(request).getUserId();
        XjAdminMsgFindAllNumVO vo = new XjAdminMsgFindAllNumVO();
        vo.setAllNum(this.count(new LambdaQueryWrapper<XjAdminMsg>().eq(XjAdminMsg::getUserId, userId)));
        vo.setHaveReadNum(this.count(new LambdaQueryWrapper<XjAdminMsg>().eq(XjAdminMsg::getIsRead, Base.IsRead.V1.getValue()).eq(XjAdminMsg::getUserId, userId)));
        vo.setUnreadNum(this.count(new LambdaQueryWrapper<XjAdminMsg>().eq(XjAdminMsg::getIsRead, Base.IsRead.V0.getValue()).eq(XjAdminMsg::getUserId, userId)));
        return vo;
    }
}
