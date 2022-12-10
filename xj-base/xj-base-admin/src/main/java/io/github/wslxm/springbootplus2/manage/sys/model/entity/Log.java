package io.github.wslxm.springbootplus2.manage.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
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
@TableName("t_sys_log")
public class Log extends BaseEntity {

    private static final long serialVersionUID = 0L;

    /**
     * 请求人姓名
     */
    @TableField(value = "full_name")
    private String fullName;
    /**
     * 请求人Id (根据端来区分)
     */
    @TableField(value = "user_id")
    private String userId;
    /**
     * 请求终端(字典code, 如 0-管理端 1-用户端 -1-All)
     */
    @TableField(value = "type")
    private Integer type;
    /**
     * 请求来源
     */
    @TableField(value = "referer")
    private String referer;
    /**
     * 请求url
     */
    @TableField(value = "url")
    private String url;
    /**
     * 请求uri
     */
    @TableField(value = "uri")
    private String uri;
    /**
     * 用户真实Ip
     */
    @TableField(value = "ip")
    private String ip;
    /**
     * 用户主机名
     */
    @TableField(value = "host")
    private String host;
    /**
     * 请求方式(post-get)
     */
    @TableField(value = "method")
    private String method;
    /**
     * 服务器地址
     */
    @TableField(value = "server_name")
    private String serverName;
    /**
     * 服务器端口
     */
    @TableField(value = "port")
    private String port;
    /**
     * 请求包
     */
    @TableField(value = "package_name")
    private String packageName;
    /**
     * 请求类
     */
    @TableField(value = "class_name")
    private String className;
    /**
     * 请求类--swagger注释
     */
    @TableField(value = "class_desc")
    private String classDesc;
    /**
     * 请求方法--swagger注释
     */
    @TableField(value = "method_desc")
    private String methodDesc;
    /**
     * 请求数据
     */
    @TableField(value = "request_data")
    private String requestData;
    /**
     * 返回数据
     */
    @TableField(value = "response_data")
    private String responseData;
    /**
     * 1-请求成功 0-请求失败
     */
    @TableField(value = "state")
    private Integer state;
    /**
     * 程序响应总耗时
     */
    @TableField(value = "execute_time")
    private Long executeTime;
    /**
     * 业务执行总耗时
     */
    @TableField(value = "business_time")
    private Long businessTime;
}

