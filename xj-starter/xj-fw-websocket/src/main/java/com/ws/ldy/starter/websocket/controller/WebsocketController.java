package com.ws.ldy.starter.websocket.controller;


import com.ws.ldy.core.result.R;
import com.ws.ldy.starter.websocket.model.vo.OnlineUserVO;
import com.ws.ldy.starter.websocket.service.WebsocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  websocket类
 *  <p>
 *   @ServerEndpoint: socket链接地址
 *  <p/>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/30 0030 16:33
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Api(value = "WebsocketController", tags = "Websocket  -->  消息通知/即时通讯")
@RequestMapping( "/api/open/websocket")
@RestController
@Slf4j
public class WebsocketController {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private WebsocketService websocketService;

    /**
     * websocket 端口号
     */
    @Value("${server.port}")
    private String port;

    /**
     * websocket接口, WebsocketServiceImpl的 @ServerEndpoint 内参数=socket接口名
     */
    private static String interfaceName = "/websocket";


    /**
     * 获取webSocket  连接地址,  // 实际情况根据用户 token获取用户信息返回
     * 获取socket地址
     * 获取用户名
     * 获取用户Id
     */
    @RequestMapping(value = "/getPath", method = RequestMethod.GET)
    @ApiOperation("游客登录获取websocket连接地址")
    public R<Map<String, String>> getPath() {
        // 获取地址 127.0.0.1 | localhost | 线上域名
        String serverName = request.getServerName();
        // 随机用户id + 用户名
        String userId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String username = "游客:" + new SimpleDateFormat("ssSSS").format(new Date());
        // 如果是线上 (域名+socket地址+id+用户名)
        String path = "ws://" + serverName + interfaceName + "/" ;//+ userId + "/" + username
        // 如果是本地 (ip + 端口 + socket地址 + id +用户名)
        if ("127.0.0.1".equals(serverName) || "localhost".equals(serverName)) {
            path = "ws://" + serverName + ":" + port + interfaceName + "/";
        }
        //返回参数
        Map<String, String> map = new HashMap<>();
        map.put("path", path);
        map.put("userId", userId);
        map.put("username", username);
        log.info("websocket请求地址11:" + path);
        return R.success(map);
    }


    /**
     * 发送消息
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ApiOperation("发送消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "form", value = "发送人Id", required = true),
            @ApiImplicitParam(name = "username", value = "发送人用户名", required = true),
            @ApiImplicitParam(name = "headPic", value = "发送人头像", required = true),
            @ApiImplicitParam(name = "to", value = "接收人Id, 全部为ALL", required = true),
            @ApiImplicitParam(name = "content", value = "发送内容", required = true),
            @ApiImplicitParam(name = "extras", value = "附加发送内容", required = true)
    })
    public R<Void> send(String form, String username,String headPic, String to, String content, String extras) {
        websocketService.send(form, username,headPic, to, content, extras);
        return R.success();
    }

    /**
     *  获取当前在线人数
     */
    @RequestMapping(value = "/getOnlineCount", method = RequestMethod.GET)
    @ApiOperation("获取在线人数")
    public R<Integer> getOnlineCount() {
        Integer onlineCount = websocketService.getOnlineCount();
        return R.success(onlineCount);
    }


    @RequestMapping(value = "/getOnlineUsersList", method = RequestMethod.GET)
    @ApiOperation("获取当前在线用户列表")
    public R<List<OnlineUserVO>> getOnlineUsersList() {
        return R.success(websocketService.getOnlineUsersList());
    }
}
