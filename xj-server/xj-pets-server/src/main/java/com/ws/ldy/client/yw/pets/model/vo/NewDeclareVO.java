package com.ws.ldy.client.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@ApiModel(value = "NewDeclareVO", description = "最新互助/申报成功")
public class NewDeclareVO extends Convert {


    private static final long serialVersionUID = -8999459271457135554L;
    @ApiModelProperty(notes = " 帮助次数( 申请时)", position = 0)
    private Integer helpNum;

    @ApiModelProperty(notes = "互助金额", position = 1)
    private BigDecimal paidInAmount;

    @ApiModelProperty(notes = "加入天数（申请时） ", position = 2)
    private Integer joinDayNum;

    @ApiModelProperty(notes = "宠物名称 ", position = 3)
    private String nickname;

    @ApiModelProperty(notes = "宠物头像 ", position = 4)
    private String headPic;

    @ApiModelProperty(notes = "宠物性别(字典表code) ", position = 5)
    private Integer sex;

    @ApiModelProperty(notes = "宠物类型(字典表code) ", position = 6)
    private String type;

    @ApiModelProperty(notes = "宠物品种(字典表code) ", position = 6)
    private String breed;

    @ApiModelProperty(notes = "宠物年龄 ", position = 7)
    private Double age;

    @ApiModelProperty(notes = "病因 ", position = 8)
    private String pathogeny;
}
