package io.github.wslxm.springbootplus2.starter.robot.service;

import io.github.wslxm.springbootplus2.starter.robot.fenshu.FenShuApi;
import io.github.wslxm.springbootplus2.starter.robot.fenshu.WechatApi;
import io.github.wslxm.springbootplus2.starter.robot.properties.RobotProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class RobotService {

    @Autowired
    private FenShuApi fenShuApi;
    @Autowired
    private WechatApi wechatApi;
    @Autowired
    private RobotProperties robotProperties;


    /**
     * 发送机器人信息
     *
     * @return boolean
     */
    public boolean sendMsg(String content) {
        // 发送飞书消息
        if (robotProperties.getFeishu() != null
                && robotProperties.getFeishu().getUrl() != null && !"".equals(robotProperties.getFeishu().getUrl())
                && robotProperties.getFeishu().getSecret() != null && !"".equals(robotProperties.getFeishu().getSecret())) {
            Map<String, Object> contentMap = new HashMap<>();
            contentMap.put("text", content);
            fenShuApi.sendMsg(contentMap);
        }

        // 发送企业微信 消息
        if (robotProperties.getWechat() != null
                && robotProperties.getWechat().getUrl() != null && !"".equals(robotProperties.getWechat().getUrl())
        ) {
            Map<String, Object> wechatContentMap = new HashMap<>();
            wechatContentMap.put("content", content);
            String mobiles = robotProperties.getWechat().getMobiles();
            if (mobiles != null && !"".equals(mobiles)) {
                wechatContentMap.put("mentioned_mobile_list", Arrays.asList(mobiles.split(",")));
            }
            wechatApi.sendMsg(wechatContentMap);
        }
        return true;
    }
}
