package com.ws.ldy.admincore.controller.vo;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * TODO  返回的数据格式(layui 必须，及其他通用返回数据格式)
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 14:55
 */
public class Data implements Serializable {

    private static final long serialVersionUID = -5666504070515657048L;

    /**
     * TODO  返回数据，因layui 定义属性无法接受，故使用Map代替
     * code  状态码
     * msg   提示信息
     * data  数据
     */
    private static Map<String, Object> resData = new Hashtable<>();

    /**
     * TODO  成功返回
     *
     * @param data 数据
     * @date 2019/11/14 14:57
     */
    public Data(Object data) {
        resData.put("code", "0");
        resData.put("msg", "ok");
        resData.put("data", data);
    }

    /**
     * TODO  成功返回-带分页
     *
     * @param data  数据
     * @param count 数据总数
     * @date 2019/11/14 14:57
     */
    public Data(Object data, Integer count) {
        resData.put("code", "0");
        resData.put("msg", "ok");
        resData.put("data", data);
        resData.put("count", count);
    }

    /***
     * TODO  错误返回
     *
     * @param code 错误码
     * @param msg 错误信息
     * @date 2019/11/14 14:57
     * @return
     */
    public Data(Integer code, String msg) {
        resData.put("code", code);
        resData.put("msg", msg);
        resData.put("data", 0);
    }

    /***
     * TODO  获取返回信息
     *
     * @date 2019/11/19 9:32
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> getResData() {
       // System.out.println(resData.toString());
        return resData;
    }
}
