package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 页面访问记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:54:16
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsPageVisitVO 对象", description = "页面访问记录表")
public class PetsPageVisitVO extends BaseVo {

    private static final long serialVersionUID = -503731071570022407L;
    
    @ApiModelProperty(notes = "页面名称" ,position = 0)
    private String pageName;

    @ApiModelProperty(notes = "页面url地址" ,position = 1)
    private String pageUrl;

    @ApiModelProperty(notes = "访问用户id" ,position = 2)
    private String userId;

    @ApiModelProperty(notes = "访问用户ip" ,position = 3)
    private String ip;

}

