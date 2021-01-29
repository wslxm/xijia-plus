package com.ws.ldy.modules.yw.pets.model.vo;


import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsPetInfoFindCityVO 对象", description = "查询宠物及城市信息")
public class PetsPetInfoFindCityVO extends Convert {


    private static final long serialVersionUID = -2180881754008274119L;
    @ApiModelProperty(notes = "宠物类别(宠物字典 code)", position = 3)
    private String type;

    @ApiModelProperty(notes = "省", position = 5)
    private String province;

    @ApiModelProperty(notes = "市", position = 6)
    private String city;

}
