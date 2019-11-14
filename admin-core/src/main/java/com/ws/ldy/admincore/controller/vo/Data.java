package com.ws.ldy.admincore.controller.vo;

import java.util.Hashtable;
import java.util.Map;

/**
 * TODO  返回的数据格式(layui 必须)
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 14:55
 */
public class Data {

    Map<String, Object> resData = new Hashtable<>();

    private int code;

    private String msg;

    private Object data;

    private int count;

    /**
     * TODO  成功返回
     *
     * @param data
     * @return
     * @date 2019/11/14 14:57
     */
    public Data(Object data) {
        resData.put("code", "0");
        resData.put("msg", "ok");
        resData.put("data", data);
        System.out.println(resData.toString());
    }

    /**
     * TODO  成功返回-带分页
     *
     * @param data
     * @return
     * @date 2019/11/14 14:57
     */
    public Data(Object data, Integer count) {
        resData.put("code", "0");
        resData.put("msg", "ok");
        resData.put("data", data);
        resData.put("count", count);
       // System.out.println(resData.toString());
    }

    /***
     * TODO  错误返回
     * @param code 错误码
     * @param msg 错误信息
     * @date 2019/11/14 14:57
     * @return
     */
    public Data(Integer code, String msg) {
        resData.put("code", code);
        resData.put("msg", msg);
        resData.put("data", null);
        System.out.println(resData.toString());
    }

    public Map<String, Object> getResData() {
        return resData;
    }
}
