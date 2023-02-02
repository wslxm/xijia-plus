package io.github.wslxm.springbootplus2.starter.websocket.service.impl;

import io.github.wslxm.springbootplus2.starter.redis.util.RedisUtil;
import io.github.wslxm.springbootplus2.starter.websocket.constant.WebsocketConst;
import io.github.wslxm.springbootplus2.starter.websocket.model.dto.WebsocketMsgDTO;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.OnlineUserVO;
import io.github.wslxm.springbootplus2.starter.websocket.model.vo.SendMsgVO;
import io.github.wslxm.springbootplus2.starter.websocket.server.WebsocketServer;
import io.github.wslxm.springbootplus2.starter.websocket.service.WebsocketService;
import io.github.wslxm.springbootplus2.starter.websocket.topic.WebsocketMsgPublisher;
import io.github.wslxm.springbootplus2.starter.websocket.util.WebsocketSpringContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


/**
 * @author wangsong
 * websocket 监听类(连接，断点，消息发送等)
 * <p>
 * /websocket/{userId}/{username}   =  /websocket/用户Id/用户名 来连接websocket，该参数会带到每一个监听方法中
 * </P>
 * @ServerEndpoint: socket链接地址
 */
@Service
public class WebsocketServiceImpl implements WebsocketService {

    @Autowired
    private WebsocketServer websocketServer;

    @Autowired
    private WebsocketMsgPublisher msgPublisher;


    @Override
    public void send(WebsocketMsgDTO dto) {
        SendMsgVO sendMsgVO = new SendMsgVO();
        sendMsgVO.setFrom(dto.getForm());
        sendMsgVO.setUsername(dto.getUsername());
        sendMsgVO.setTo(dto.getTo());
        sendMsgVO.setContent(dto.getContent());
        sendMsgVO.setExtras(dto.getExtras());
        sendMsgVO.setOnlineNum(this.getOnlineCount());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sendMsgVO.setCreateTime(df.format(LocalDateTime.now()));
        // 发送 redis 订阅消息
        msgPublisher.sendMsg(sendMsgVO);
    }

    @Override
    public Map<Object, OnlineUserVO> getOnlineUsers() {
        RedisUtil redisUtil = WebsocketSpringContextUtil.getBean(RedisUtil.class);
        Map<Object, Object> objectObjectMap = redisUtil.hEntries(WebsocketConst.WEBSOCKET_CACHE_KEY);
        Map<Object, OnlineUserVO> map = new HashMap<>();
        for (Object key : objectObjectMap.keySet()) {
            OnlineUserVO vo = new OnlineUserVO();
            BeanUtils.copyProperties(objectObjectMap.get(key), vo);
            map.put(key, vo);
        }
        return map;
    }

    @Override
    public Integer getOnlineCount() {
        RedisUtil redisUtil = WebsocketSpringContextUtil.getBean(RedisUtil.class);
        Long count = redisUtil.hSize(WebsocketConst.WEBSOCKET_CACHE_KEY);
        return Integer.parseInt(count + "");
    }


    @Override
    public boolean isOnline(String userId) {
        RedisUtil redisUtil = WebsocketSpringContextUtil.getBean(RedisUtil.class);
        return redisUtil.hHasKey(WebsocketConst.WEBSOCKET_CACHE_KEY, userId);
    }


}