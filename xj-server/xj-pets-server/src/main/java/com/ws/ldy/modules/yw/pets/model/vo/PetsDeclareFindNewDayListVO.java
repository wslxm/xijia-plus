package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * 申报信息分页查询 额外返回参数
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-31 17:41:21
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsDeclareFindNewDayListVO 对象", description = "查询指定时间内的申报数据")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetsDeclareFindNewDayListVO extends Convert {


    private static final long serialVersionUID = -7878070232329058334L;

    @ApiModelProperty(notes = "申报id", position = 1)
    private String id;

    @ApiModelProperty(notes = "申报金额", position = 2)
    private BigDecimal declareMoney;

    @ApiModelProperty(notes = "用户id", position = 3)
    private String userId;

    @ApiModelProperty(notes = "用户微信名", position = 4)
    private String wxName;

    @ApiModelProperty(notes = "用户姓名", position = 5)
    private String fullName;

    @ApiModelProperty(notes = "用户姓名", position = 6)
    private String idCard;

    @ApiModelProperty(notes = "用户电话", position = 7)
    private String phone;

}
