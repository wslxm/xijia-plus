package io.github.wslxm.springbootplus2.manage.xj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminMsgDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminMsg;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminMsgQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgFindAllNumVO;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgVO;

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
public interface XjAdminMsgService extends IService<XjAdminMsg> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgVO>
     * @version 1.0.0
     */
    IPage<XjAdminMsgVO> list(XjAdminMsgQuery query);


    /**
     * 添加/发送消息（如果需要的端webSocket已连接，同时发送webSocket信息）
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(XjAdminMsgDTO dto);


    /**
     * 修改信息为已读
     *
     * @param id id
     * @return boolean
     * @version 1.0.0
     */
    boolean updRead(String id);

    /**
     * 查询未读消息数量
     *
     * @return java.lang.Integer
     * @version 1.0.0
     */
    Long findUnreadNum();


    /**
     * 查询已读/未读数量
     *
     * @return io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgFindAllNumVO
     * @version 1.0.0
     */
    XjAdminMsgFindAllNumVO findAllNum();
}

