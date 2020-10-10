package com.ws.ldy.modules.xijia.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ws.ldy.modules.xijia.service.SquareService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SquareServiceImpl extends BaseIServiceImpl implements SquareService {

    private final static String path = "https://www.mxnzp.com/api";  // 请求地址
    private final static String dzUrl = "/jokes/list?page=";         // 分页段子
    private final static String dzRandomUrl = "/jokes/list/random";  // 随机段子

    @Override
    public String duanzi(int type, Integer page) {
        ResponseEntity<String> results;
        if (type == 1) {
            results = restTemplate.exchange(path + dzUrl + page, HttpMethod.GET, null, String.class);
        } else {
            results = restTemplate.exchange(path + dzRandomUrl, HttpMethod.GET, null, String.class);
        }
        String jsonStr = results.getBody();
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        String data = jsonObject.getStr("data");
        return  data;
    }
}
