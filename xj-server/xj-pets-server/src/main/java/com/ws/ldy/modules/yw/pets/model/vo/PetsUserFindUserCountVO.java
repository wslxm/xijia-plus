package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 用户表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:03:46
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsUserVO 对象", description = "用户表")
public class PetsUserFindUserCountVO extends BaseVo {


    private static final long serialVersionUID = -3961881645045381343L;
    @ApiModelProperty(notes = "用户总数", position = 1)
    private Long userTotal;

    @ApiModelProperty(notes = "女用户总数", position = 2)
    private Long girlUserTotal;

    @ApiModelProperty(notes = "男用户总数", position = 3)
    private Long boyUserTotal;

    @ApiModelProperty(notes = "未知用户总数", position = 4)
    private Long unknownUserTotal;
}

