package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 医院表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 11:26:04
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsHospitalVO 对象", description = "医院表")
public class PetsHospitalVO extends BaseVo {

    private static final long serialVersionUID = -503739073597411334L;
    
    @ApiModelProperty(notes = "医院名称" ,position = 0)
    private String name;

    @ApiModelProperty(notes = "电话" ,position = 1)
    private String phone;

    @ApiModelProperty(notes = "医院地址" ,position = 2)
    private String address;

    @ApiModelProperty(notes = "1-禁用-黑名单 / 0-启用" ,position = 3)
    private Integer disable;

}

