package com.ws.ldy.modules.sys.xj.service.impl;

import com.ws.ldy.enums.Base;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.sys.xj.mapper.XjAdminMsgMapper;
import com.ws.ldy.modules.sys.xj.model.dto.XjAdminMsgDTO;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminMsg;
import com.ws.ldy.modules.sys.xj.service.XjAdminMsgService;
import org.springframework.stereotype.Service;

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
    public boolean insertMsg(XjAdminMsgDTO dto) {
        XjAdminMsg entity = new XjAdminMsg();
        entity.setUserId(dto.getUserId());
        entity.setContent(dto.getContent());
        entity.setUserType(dto.getUserType());
        entity.setMsgType(dto.getMsgType());
        entity.setIsRead(Base.IsRead.V0.getValue());
        return this.save(entity);
    }
}
