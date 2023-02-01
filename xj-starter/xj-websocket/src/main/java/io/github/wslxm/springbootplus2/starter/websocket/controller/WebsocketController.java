package io.github.wslxm.springbootplus2.starter.websocket.controller;


import io.github.wslxm.springbootplus2.starter.websocket.config.result.WebSocketR;
import io.github.wslxm.springbootplus2.starter.websocket.model.dto.WebsocketMsgDTO;
import io.github.wslxm.springbootplus2.starter.websocket.service.WebsocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * websocket类
 * <p>
 *
 * @author wangsong
 * @version 1.0.1
 * @ServerEndpoint: socket链接地址
 * <p/>
 * @mail 1720696548@qq.com
 * @date 2020/9/30 0030 16:33
 */
@Api(value = "WebsocketController", tags = "Websocket  -->  消息通知/即时通讯")
@RequestMapping("/api/open/websocket")
@RestController
@Slf4j
public class WebsocketController {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private WebsocketService websocketService;


    private final static String HTTPS = "https";
    private static List<String> LOCALHOSTS = null;

    public WebsocketController() {
        LOCALHOSTS = new ArrayList<>();
        LOCALHOSTS.add("127.0.0.1");
        LOCALHOSTS.add("localhost");
    }


    /**
     * websocket 端口号
     */
    @Value("${server.port}")
    private String port;

    /**
     * websocket接口, WebsocketServiceImpl的 @ServerEndpoint 内参数=socket接口名
     */
    private static String INTERFACE_NAME = "/websocket";


    /**
     * 获取webSocket  连接地址,  // 实际情况根据用户 token获取用户信息返回
     * 获取socket地址
     * 获取用户名
     * 获取用户Id
     */
    @RequestMapping(value = "/getPath", method = RequestMethod.GET)
    @ApiOperation("获取模拟游客登录的 websocket 连接地址")
    public Object getPath() {
        // 获取地址 127.0.0.1 | localhost | 线上域名
        String serverName = request.getServerName();
        // 随机用户id + 用户名
        String userId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String username = "游客:" + new SimpleDateFormat("ssSSS").format(new Date());

        // 判断是https请求还是http,对应使用 ws 或 wss
        String serverPrefix = "ws://";
        String referer = request.getHeader("referer");
        if (referer != null && !"".equals(referer) && HTTPS.equals(referer.substring(0, HTTPS.length()))) {
            serverPrefix = "wss://";
        }
        // 如果是线上 (域名+socket地址+id+用户名)
        String path = serverPrefix + serverName + INTERFACE_NAME + "/" + userId + "/" + username;
        // 如果是本地 (ip + 端口 + socket地址 + id +用户名)
        if (LOCALHOSTS.contains(serverName)) {
            path = serverPrefix + serverName + ":" + port + INTERFACE_NAME + "/" + userId + "/" + username;
        }
        // 返回参数
        Map<String, String> map = new HashMap<>(4);
        map.put("path", path);
        map.put("userId", userId);
        map.put("username", username);
        log.info("websocket请求地址:" + path);
        return WebSocketR.success(map);
    }


    /**
     * 发送消息
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ApiOperation("发送消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "form", value = "发送人Id", required = true, example = "1"),
            @ApiImplicitParam(name = "username", value = "发送人用户名", required = true, example = "系统测试"),
            @ApiImplicitParam(name = "to", value = "接收人Id, 全部为ALL", required = true, example = "ALL"),
            @ApiImplicitParam(name = "content", value = "发送内容", required = true, example = "我是测试消息"),
            @ApiImplicitParam(name = "extras", value = "附加发送内容", required = true, example = "我是附加内容")
    })
    public Object send(@RequestBody WebsocketMsgDTO dto) {
        websocketService.send(dto);
        return WebSocketR.success();
    }

    /**
     * 获取当前在线人数
     */
    @RequestMapping(value = "/getOnlineCount", method = RequestMethod.GET)
    @ApiOperation("获取在线人数")
    public Object getOnlineCount() {
        // Integer onlineCount = websocketService.getOnlineCount();
        return WebSocketR.success(0);
    }


    @RequestMapping(value = "/getOnlineUsersList", method = RequestMethod.GET)
    @ApiOperation("获取当前在线用户列表")
    public Object getOnlineUsersList() {
        return WebSocketR.success(websocketService.getOnlineUsersList());
    }


    @RequestMapping(value = "/isOnline", method = RequestMethod.GET)
    @ApiOperation("判断指定用户是否在线")
    public Object isOnline(@RequestParam String userId) {
        return WebSocketR.success(websocketService.isOnline(userId));
    }
}
