package com.ws.ldy.base.controller;

import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.socket.OnlineUserVO;
import com.ws.ldy.common.socket.WebsocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * websocket类
 *
 * @ServerEndpoint: socket链接地址
 */
@Api(value = "WebSocketController", tags = "websocket 相关通知/聊天")
@RequestMapping("/websocket")
@RestController
@Slf4j
public class WebSocketController {
    /**
     * TODO 获取webSocket  连接地址,  // 实际情况根据用户 token获取用户信息返回
     * 获取socket地址
     * 获取用户名
     * 获取用户Id
     * 读取服务器,ip 端口可看：https://blog.csdn.net/qq_41463655/article/details/92002474
     */
    @RequestMapping(value = "/getPath", method = RequestMethod.GET)
    @ApiOperation("游客登录获取websocket连接地址")
    public Result<Map<String, String>> getPath() {
        // 随机用户名
        String username = "游客:" + new SimpleDateFormat("ssSSS").format(new Date());
        // 随机用户id
        String userId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        // 连接地址
        String path = "ws://192.168.0.154:9049/websocket/" + userId + "/" + username;
        Map<String, String> map = new HashMap<>();
        map.put("path", path);
        map.put("userId", userId);
        map.put("username", username);
        return Result.success(map);
    }


    @Autowired
    private WebsocketService websocketService;

    /**
     * TODO 发送消息
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ApiOperation("发送消息")
    public Result<Void> send(String form, String username, String to, String content, String extras) {
        websocketService.send(form, username, to, content, extras);
        return Result.success();
    }

    /**
     * TODO 获取当前在线人数
     */
    @RequestMapping(value = "/getOnlineCount", method = RequestMethod.GET)
    @ApiOperation("发送消息")
    public Result<Integer> getOnlineCount() {
        Integer onlineCount = websocketService.getOnlineCount();
        return Result.success(onlineCount);
    }


    @RequestMapping(value = "/getOnlineUsersList", method = RequestMethod.GET)
    @ApiOperation("获取当前在线用户列表")
    public Result<List<OnlineUserVO>> getOnlineUsersList() {
        return Result.success(websocketService.getOnlineUsersList());
    }

    ;
}
