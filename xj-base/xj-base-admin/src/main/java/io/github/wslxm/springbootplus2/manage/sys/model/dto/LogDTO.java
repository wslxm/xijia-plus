package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;


import lombok.Data;
import lombok.ToString;

/**
 * 操作记录表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-28 20:44:32
 */
@Data
@ToString(callSuper = true)
public class LogDTO extends Convert {

    private static final long serialVersionUID = 0L;

    /**
     * 请求人"
     */
    private String fullName;

    /**
     * 请求人Id (根据端来区分)"
     */
    private String userId;

    /**
     * 请求终端(字典code, 如 0-管理端 1-用户端 -1-All)"
     */
    private Integer type;

    /**
     * 请求来源"
     */
    private String referer;

    /**
     * 请求url"
     */
    private String url;

    /**
     * 请求uri"
     */
    private String uri;

    /**
     * 用户真实Ip"
     */
    private String ip;

    /**
     * 用户主机名"
     */
    private String host;

    /**
     * 请求方式(post-get)"
     */
    private String method;

    /**
     * 服务器地址"
     */
    private String serverName;

    /**
     * 服务器端口"
     */
    private String port;

    /**
     * 请求包"
     */
    private String packageName;

    /**
     * 请求类"
     */
    private String className;

    /**
     * 请求类--swagger注释"
     */
    private String classDesc;

    /**
     * 请求方法--swagger注释"
     */
    private String methodDesc;

    /**
     * 请求数据"
     */
    private String requestData;

    /**
     * 返回数据"
     */
    private String responseData;

    /**
     * 1-请求成功 0-请求失败"
     */
    private Integer state;

    /**
     * 程序响应总耗时"
     */
    private Long executeTime;

    /**
     * 业务执行总耗时"
     */
    private Long businessTime;
}

