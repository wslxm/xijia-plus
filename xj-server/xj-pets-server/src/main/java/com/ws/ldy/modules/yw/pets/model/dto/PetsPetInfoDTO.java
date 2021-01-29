package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 宠物表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:33
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsPetInfoDTO 对象", description = "宠物表")
public class PetsPetInfoDTO extends BaseDto {

    private static final long serialVersionUID = -503731141283549216L;


    @ApiModelProperty(notes = "宠物名称", position = 1)
    @Length(min = 1, max = 32, message = "宠物名称 必须>=0 和 <=32位")
    private String nickname;

    @ApiModelProperty(notes = "宠物年龄", position = 2)
    @Range(min = 0, max = 99L, message = "宠物年龄 必须>=0 和 <=99")
    private Double age;

    @ApiModelProperty(notes = "宠物性别(字典 code)", position = 2)
    @Range(min = 0, max = 9L, message = "宠物性别 必须>=0 和 <=9")
    private Integer sex;

    @ApiModelProperty(notes = "宠物类别(宠物字典 code)", position = 3)
    @Length(min = 1, max = 32, message = "宠物类别 必须>=0 和 <=32位")
    private String type;

    @ApiModelProperty(notes = "宠物品种(宠物字典 code)", position = 4)
    @Length(min = 1, max = 32, message = "宠物品种 必须>=0 和 <=32位")
    private String breed;

    @ApiModelProperty(notes = "宠物头像", position = 5)
    @Length(min = 1, max = 512, message = "宠物头像 必须>=0 和 <=512位")
    private String headPic;

    @ApiModelProperty(notes = "宠物图片(索引 0- 正面 1-左侧 2-右侧 3-主人合照)", position = 6)
    private String photoPic;


    // 额外字段
    @ApiModelProperty(notes = "省", position = 7)
    private String province;

    @ApiModelProperty(notes = "市", position = 8)
    private String city;

    @ApiModelProperty(notes = "区", position = 9)
    private String area;


}

