package com.ws.ldy.base.controller;

import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.result.ResultEnum;
import com.ws.ldy.common.websocket.model.vo.OnlineUserVO;
import com.ws.ldy.common.websocket.service.WebsocketService;
import com.ws.ldy.config.error.ErrorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * websocket ip 或域名
     */
    @Value("${websocket.ip}")
    private String ip;

    /**
     * websocket 端口号
     */
    @Value("${websocket.port}")
    private String port;

    /**
     * websocket接口
     */
    @Value("${websocket.interfaceName}")
    private String interfaceName;


    /**
     * TODO 获取webSocket  连接地址,  // 实际情况根据用户 token获取用户信息返回
     * 获取socket地址
     * 获取用户名
     * 获取用户Id
     */
    @RequestMapping(value = "/getPath", method = RequestMethod.GET)
    @ApiOperation("游客登录获取websocket连接地址")
    public Result<Map<String, String>> getPath() {
        // 配置检查
        if (StringUtils.isBlank(ip) || StringUtils.isBlank(port) || StringUtils.isBlank(interfaceName)) {
            throw new ErrorException(ResultEnum.SYS_SOCKET_CONFIG_ERROR);
        }
        // 随机用户名
        String username = "游客:" + new SimpleDateFormat("ssSSS").format(new Date());
        // 随机用户id
        String userId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        // 连接地址， // "ws://192.168.0.154:9049/websocket/1/张三"
        String path = "ws://" + ip + ":" + port + interfaceName + "/" + userId + "/" + username;
        log.info("websocket请求地址:" + path);

        //返回参数
        Map<String, String> map = new HashMap<>();
        map.put("path", path);
        map.put("userId", userId);
        map.put("username", username);
        return Result.success(map);
    }

    // websocket 逻辑代码
    @Autowired
    private WebsocketService websocketService;

    /**
     * TODO 发送消息
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ApiOperation("发送消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "form", value = "发送人Id", required = true),
            @ApiImplicitParam(name = "username", value = "发送人用户名", required = true),
            @ApiImplicitParam(name = "to", value = "接收人Id, 全部为ALL", required = true),
            @ApiImplicitParam(name = "content", value = "发送内容", required = true),
            @ApiImplicitParam(name = "extras", value = "附加发送内容", required = true)
    })
    public Result<Void> send(String form, String username, String to, String content, String extras) {
        websocketService.send(form, username, to, content, extras);
        return Result.success();
    }

    /**
     * TODO 获取当前在线人数
     */
    @RequestMapping(value = "/getOnlineCount", method = RequestMethod.GET)
    @ApiOperation("获取在线人数")
    public Result<Integer> getOnlineCount() {
        Integer onlineCount = websocketService.getOnlineCount();
        return Result.success(onlineCount);
    }


    @RequestMapping(value = "/getOnlineUsersList", method = RequestMethod.GET)
    @ApiOperation("获取当前在线用户列表")
    public Result<List<OnlineUserVO>> getOnlineUsersList() {
        return Result.success(websocketService.getOnlineUsersList());
    }
}
