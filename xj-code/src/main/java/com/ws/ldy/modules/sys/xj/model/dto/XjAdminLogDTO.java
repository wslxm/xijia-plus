package com.ws.ldy.modules.sys.xj.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
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
@ApiModel(value = "XjAdminLogDTO 对象", description = "操作记录表")
public class XjAdminLogDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "请求人" ,position = 0)
    private String fullName;

    @ApiModelProperty(notes = "请求人Id (根据端来区分)" ,position = 1)
    private String userId;

    @ApiModelProperty(notes = "请求终端(字典code, 如 0-管理端 1-用户端 -1-All)" ,position = 2)
    private Integer type;

    @ApiModelProperty(notes = "请求来源" ,position = 3)
    private String referer;

    @ApiModelProperty(notes = "请求url" ,position = 4)
    private String url;

    @ApiModelProperty(notes = "请求uri" ,position = 5)
    private String uri;

    @ApiModelProperty(notes = "用户真实Ip" ,position = 6)
    private String ip;

    @ApiModelProperty(notes = "用户主机名" ,position = 7)
    private String host;

    @ApiModelProperty(notes = "请求方式(post-get)" ,position = 8)
    private String method;

    @ApiModelProperty(notes = "服务器地址" ,position = 9)
    private String serverName;

    @ApiModelProperty(notes = "服务器端口" ,position = 10)
    private String port;

    @ApiModelProperty(notes = "请求包" ,position = 11)
    private String packageName;

    @ApiModelProperty(notes = "请求类" ,position = 12)
    private String className;

    @ApiModelProperty(notes = "请求类--swagger注释" ,position = 13)
    private String classDesc;

    @ApiModelProperty(notes = "请求方法--swagger注释" ,position = 14)
    private String methodDesc;

    @ApiModelProperty(notes = "请求数据" ,position = 15)
    private String requestData;

    @ApiModelProperty(notes = "返回数据" ,position = 16)
    private String responseData;

    @ApiModelProperty(notes = "1-请求成功 0-请求失败" ,position = 17)
    private Integer state;

    @ApiModelProperty(notes = "程序响应总耗时" ,position = 18)
    private Long executeTime;

    @ApiModelProperty(notes = "业务执行总耗时" ,position = 18)
    private Long businessTime;
}

