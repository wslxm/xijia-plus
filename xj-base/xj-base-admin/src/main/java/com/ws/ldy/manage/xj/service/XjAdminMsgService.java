package com.ws.ldy.manage.xj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.manage.xj.model.dto.XjAdminMsgDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminMsg;
import com.ws.ldy.manage.xj.model.query.XjAdminMsgQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminMsgVO;

/**
 * 订单-->及时消息通知表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-09-23 10:40:23
 */
public interface XjAdminMsgService extends IService<XjAdminMsg> {

    IPage<XjAdminMsgVO> list(XjAdminMsgQuery query);
    /**
     * 发送消息
     */
     boolean insertMsg(XjAdminMsgDTO dto);


}

