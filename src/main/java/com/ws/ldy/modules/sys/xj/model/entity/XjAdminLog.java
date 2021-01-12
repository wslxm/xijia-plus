package com.ws.ldy.modules.sys.xj.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 操作记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-28 20:44:32
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_admin_log")
@ApiModel(value = "XjAdminLog 对象", description = "操作记录表")
public class XjAdminLog extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "请求人" ,position = 0)
    @TableField(value = "full_name")
    private String fullName;

    @ApiModelProperty(notes = "请求人Id (根据端来区分)" ,position = 1)
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(notes = "请求终端(字典code, 如 0-管理端 1-用户端 -1-All)" ,position = 2)
    @TableField(value = "type")
    private Integer type;

    @ApiModelProperty(notes = "请求来源" ,position = 3)
    @TableField(value = "referer")
    private String referer;

    @ApiModelProperty(notes = "请求url" ,position = 4)
    @TableField(value = "url")
    private String url;

    @ApiModelProperty(notes = "请求uri" ,position = 5)
    @TableField(value = "uri")
    private String uri;

    @ApiModelProperty(notes = "用户真实Ip" ,position = 6)
    @TableField(value = "ip")
    private String ip;

    @ApiModelProperty(notes = "用户主机名" ,position = 7)
    @TableField(value = "host")
    private String host;

    @ApiModelProperty(notes = "请求方式(post-get)" ,position = 8)
    @TableField(value = "method")
    private String method;

    @ApiModelProperty(notes = "服务器地址" ,position = 9)
    @TableField(value = "server_name")
    private String serverName;

    @ApiModelProperty(notes = "服务器端口" ,position = 10)
    @TableField(value = "port")
    private String port;

    @ApiModelProperty(notes = "请求包" ,position = 11)
    @TableField(value = "package_name")
    private String packageName;

    @ApiModelProperty(notes = "请求类" ,position = 12)
    @TableField(value = "class_name")
    private String className;

    @ApiModelProperty(notes = "请求类--swagger注释" ,position = 13)
    @TableField(value = "class_desc")
    private String classDesc;

    @ApiModelProperty(notes = "请求方法--swagger注释" ,position = 14)
    @TableField(value = "method_desc")
    private String methodDesc;

    @ApiModelProperty(notes = "请求数据" ,position = 15)
    @TableField(value = "request_data")
    private String requestData;

    @ApiModelProperty(notes = "返回数据" ,position = 16)
    @TableField(value = "response_data")
    private String responseData;

    @ApiModelProperty(notes = "1-请求成功 0-请求失败" ,position = 17)
    @TableField(value = "state")
    private Integer state;

    @ApiModelProperty(notes = "程序响应总耗时" ,position = 18)
    @TableField(value = "execute_time")
    private Long executeTime;

    @ApiModelProperty(notes = "业务执行总耗时" ,position = 18)
    @TableField(value = "business_time")
    private Long businessTime;
}

