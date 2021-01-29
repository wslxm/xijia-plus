package com.ws.ldy.client.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@ApiModel(value = "UpdPetBindDTO", description = "宠物绑定1")
public class UpdPetBindDTO extends Convert {


    @ApiModelProperty(notes = "类型 1-绑定原宠物(必传id)  2-添加新宠物", position = 1)
    @Range(min = 1, max = 2, message = "类型只能 1到2")
    @NotNull
    private Integer updType;

    @ApiModelProperty(value = "类型1参数---原宠物id")
    private String oldPetId;

    @ApiModelProperty(notes = "类型1+2参数---订单号", position = 1)
    @NotBlank
    private String orderNo;

    @ApiModelProperty(notes = "类型2参数---订单号字段--宠物名称", position = 1)
    @Length(min = 1, max = 32, message = "宠物名称 必须>=0 和 <=32位")
    private String nickname;

    @ApiModelProperty(notes = "类型2参数---字段--宠物年龄", position = 2)
    @Range(min = 0, max = 99L, message = "宠物年龄 必须>=0 和 <=99")
    private Double age;

    @ApiModelProperty(notes = "类型2参数---字段--宠物性别(字典 code)", position = 2)
    @Range(min = 0, max = 9L, message = "宠物性别 必须>=0 和 <=9")
    private Integer sex;

    @ApiModelProperty(notes = "类型2参数---宠物类别(宠物字典 code)", position = 3)
    @Length(min = 1, max = 32, message = "宠物类别 必须>=0 和 <=32位")
    private String type;

    @ApiModelProperty(notes = "类型2参数---字段--宠物品种(宠物字典 code)", position = 4)
    @Length(min = 1, max = 32, message = "宠物品种 必须>=0 和 <=32位")
    private String breed;

    @ApiModelProperty(notes = "类型2参数---字段--宠物头像", position = 5)
    @Length(min = 1, max = 512, message = "宠物头像 必须>=0 和 <=512位")
    private String headPic;

    @ApiModelProperty(notes = "类型2参数---字段--宠物图片(索引 0- 正面 1-左侧 2-右侧 3-主人合照)", position = 6)
    private String photoPic;

    //
    @ApiModelProperty(notes = "省" ,position = 7)
    private String province;

    @ApiModelProperty(notes = "市" ,position = 8)
    private String city;

    @ApiModelProperty(notes = "区" ,position = 9)
    private String area;
}
