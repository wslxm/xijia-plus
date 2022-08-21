package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 黑名单
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-11-27 22:44:49
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "BlacklistVO 对象", description = "黑名单")
public class BlacklistVO extends BaseVo {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "1-白名单(* 表示允许除黑名单外的所有ip请求, 否则只允许白名单中的ip请求) 2-黑名单(禁止ip访问)" ,position = 0)
    private Integer type;

    @ApiModelProperty(notes = "白名单ip/黑名单ip" ,position = 1)
    private String ip;

    @ApiModelProperty(notes = "备注" ,position = 2)
    private String desc;

    @ApiModelProperty(notes = "禁用(0-启用 1-禁用)" ,position = 3)
    private Integer disable;

}

