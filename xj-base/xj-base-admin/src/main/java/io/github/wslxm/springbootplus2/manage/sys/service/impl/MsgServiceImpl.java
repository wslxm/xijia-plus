package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Admin;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.MsgMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.MsgDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Msg;
import io.github.wslxm.springbootplus2.manage.sys.model.query.MsgQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.MsgFindAllNumVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.MsgVO;
import io.github.wslxm.springbootplus2.manage.sys.service.DictionaryService;
import io.github.wslxm.springbootplus2.manage.sys.service.MsgService;
import io.github.wslxm.springbootplus2.starter.websocket.model.dto.WebsocketMsgDTO;
import io.github.wslxm.springbootplus2.starter.websocket.service.WebsocketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
public class MsgServiceImpl extends BaseServiceImpl<MsgMapper, Msg> implements MsgService {

    @Autowired
    private WebsocketService webSocketService;

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public BasePage<MsgVO> findPage(MsgQuery query) {
        if (query.getIsLoginUser() == null) {
            query.setIsLoginUser(true);
        }
        LambdaQueryWrapper<Msg> queryWrapper = new LambdaQueryWrapper<Msg>()
                .orderByDesc(Msg::getCreateTime)
                .eq(query.getIsRead() != null, Msg::getIsRead, query.getIsRead())
                .eq(query.getIsLoginUser(), Msg::getUserId, JwtUtil.getJwtUser(request).getUserId())
                .in(StringUtils.isNotBlank(query.getMsgTypes()), Msg::getMsgType, StringUtils.isNotBlank(query.getMsgTypes()) ? Arrays.asList(query.getMsgTypes().split(",")) : null)
                .notIn(StringUtils.isNotBlank(query.getNoMsgTypes()), Msg::getMsgType, StringUtils.isNotBlank(query.getNoMsgTypes()) ? Arrays.asList(query.getNoMsgTypes().split(",")) : null);
        return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), MsgVO.class);
    }

    @Override
    public String insert(MsgDTO dto) {
        // 根据业务类型获取配置
        DictionaryVO msgTypeDict = dictionaryService.findDictCategoryNext("MSG_TYPE", dto.getMsgType() + "");
        ValidUtil.isTrue(msgTypeDict == null, "没有找到对应的字典配置");

        // 获取动态参数
        String paramsStr = null;
        if (dto.getRouteParams() != null && dto.getRouteParams().size() > 0) {
            StringBuilder params = new StringBuilder("");
            Map<String, String> routeParams = dto.getRouteParams();
            for (String key : routeParams.keySet()) {
                params.append("&").append(key).append("=").append(routeParams.get(key));
            }
            paramsStr = params.toString().substring(1);
        }

        // 拼接路由一
        StringBuilder routePath = new StringBuilder("");
        if (StringUtils.isNotEmpty(msgTypeDict.getExt2())) {
            routePath.append(msgTypeDict.getExt2());
            routePath.append(routePath.toString().contains("?") ? "&" + paramsStr : "?" + paramsStr);
        }
        // 拼接路由二
        StringBuilder routePathTwo = new StringBuilder("");
        if (StringUtils.isNotEmpty(msgTypeDict.getExt3())) {
            routePathTwo.append(msgTypeDict.getExt3());
            routePathTwo.append(routePath.toString().contains("?") ? "&" + paramsStr : "?" + paramsStr);
        }

        // 拼接发送json内容
        Map<String, String> contentMap = new HashMap<>(4);
        // 标题
        contentMap.put("title", msgTypeDict.getExt1());
        // 消息
        contentMap.put("message", dto.getContent());
        // 跳转地址
        contentMap.put("routePath", routePath.toString());
        // 第二跳转地址(可让其 用户pc端使用第一路由 + 用户app端使用第二路由)
        contentMap.put("routePathTwo", routePathTwo.toString());


        // 保存消息
        Msg entity = new Msg();
        entity.setUserId(dto.getUserId());
        entity.setContent(JSON.toJSONString(contentMap));
        entity.setUserType(dto.getUserType());
        entity.setMsgType(dto.getMsgType());
        entity.setIsRead(Base.IsRead.V0.getValue());
        boolean b = this.save(entity);

        // 发送webSocket消息
        boolean isWebsocket = dto.getIsWebsocket() == null ? false : dto.getIsWebsocket();
        if (isWebsocket) {
            WebsocketMsgDTO websocketMsgDTO = new WebsocketMsgDTO();
            websocketMsgDTO.setForm("sys");
            websocketMsgDTO.setUsername("系统");
            websocketMsgDTO.setTo(dto.getUserId());
            websocketMsgDTO.setContent(JSON.toJSONString(entity));
            //websocketMsgDTO.setExtras(null);
            webSocketService.send(websocketMsgDTO);
        }
        return entity.getId();
    }

    @Override
    public boolean updRead(String id) {
        Msg entity = new Msg();
        entity.setId(id);
        entity.setIsRead(Base.IsRead.V1.getValue());
        return this.updateById(entity);
    }

    @Override
    public Long findUnreadNum() {
        return this.count(new LambdaQueryWrapper<Msg>()
                .eq(Msg::getIsRead, Base.IsRead.V0.getValue())
                .eq(Msg::getUserId, JwtUtil.getJwtUser(request).getUserId())
        );
    }

    @Override
    public MsgFindAllNumVO findAllNum() {
        String userId = JwtUtil.getJwtUser(request).getUserId();
        MsgFindAllNumVO vo = new MsgFindAllNumVO();
        vo.setAllNum(this.count(new LambdaQueryWrapper<Msg>().eq(Msg::getUserId, userId)));
        vo.setHaveReadNum(this.count(new LambdaQueryWrapper<Msg>().eq(Msg::getIsRead, Base.IsRead.V1.getValue()).eq(Msg::getUserId, userId)));
        vo.setUnreadNum(this.count(new LambdaQueryWrapper<Msg>().eq(Msg::getIsRead, Base.IsRead.V0.getValue()).eq(Msg::getUserId, userId)));
        return vo;
    }


    public boolean sendSysMsg(String userId, String content) {
        return this.sendSysMsg(userId, content, true);
    }

    /**
     * 发送系统信息
     *
     * @param userId 用户id
     * @param content 内容
     */
    public boolean sendSysMsg(String userId, String content, boolean isWebsocket) {
        // 消息参数
        MsgDTO dto = new MsgDTO();
        dto.setUserId(userId);
        dto.setContent(content);
        dto.setUserType(Admin.MsgUserType.V2.getValue());
        dto.setMsgType(Admin.MsgType.V1.getValue());
        dto.setRouteParams(Maps.newHashMap());
        dto.setIsWebsocket(isWebsocket);
        // 发送消息
        this.insert(dto);
        return true;
    }
}
