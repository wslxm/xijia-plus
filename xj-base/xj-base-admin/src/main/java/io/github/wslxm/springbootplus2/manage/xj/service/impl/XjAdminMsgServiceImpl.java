package io.github.wslxm.springbootplus2.manage.xj.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.xj.mapper.XjAdminMsgMapper;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminMsgDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminMsg;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminMsgQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgFindAllNumVO;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminMsgService;
import io.github.wslxm.springbootplus2.starter.websocket.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 订单-->及时消息通知表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-09-23 10:40:23
 */
@Service
public class XjAdminMsgServiceImpl extends BaseIServiceImpl<XjAdminMsgMapper, XjAdminMsg> implements XjAdminMsgService {

    @Autowired
    private WebsocketService webSocketService;

    @Override
    public IPage<XjAdminMsgVO> list(XjAdminMsgQuery query) {
        if (query.getIsLoginUser() == null) {
            query.setIsLoginUser(true);
        }
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
    public String insert(XjAdminMsgDTO dto) {
        XjAdminMsg entity = new XjAdminMsg();
        entity.setUserId(dto.getUserId());
        entity.setContent(dto.getContent());
        entity.setUserType(dto.getUserType());
        entity.setMsgType(dto.getMsgType());
        entity.setIsRead(Base.IsRead.V0.getValue());
        boolean b = this.save(entity);
        // 发送webSocket消息
        webSocketService.send("sys-sms", "系统消息", "", dto.getUserId(), JSON.toJSONString(entity), null);
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
