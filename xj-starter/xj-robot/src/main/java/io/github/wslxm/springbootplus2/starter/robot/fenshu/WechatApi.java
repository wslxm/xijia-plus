package io.github.wslxm.springbootplus2.starter.robot.fenshu;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.starter.robot.properties.RobotProperties;
import io.github.wslxm.springbootplus2.starter.robot.properties.RobotWechatProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WechatApi {

    @Autowired
    private RobotProperties robotProperties;

    /**
     * 发送信息
     *
     * @param  contentMap
     *      <p>
     *       contentMap 如下:
     *         "text": {
     *             "content": "广州今日天气：29度，大部分多云，降雨概率：60%",
     *             "mentioned_list":["wangqing","@all"],
     *             "mentioned_mobile_list":["13800001111","@all"]
     *         }
     *      <p/>
     */
    public void sendMsg(Map<String, Object> contentMap) {
        RobotWechatProperties wechatProperties = robotProperties.getWechat();
        // 拼接消息体
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("msgtype", "text");
        paramMap.put("text", contentMap);
        // 请求
        HttpRequest.post(wechatProperties.getUrl())
                .header("Content-Type", "application/json;charset=utf-8")
                .body(JSON.toJSONString(paramMap))
                .execute().body();
    }
}
