package com.test.springbootplus2.manage.test.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "ExcelTestVO ", description = "用户信息")
public class ExcelTestVO {

    @ApiModelProperty(notes = "用户编号" ,position = 0)
    private String userNo;

    @ApiModelProperty(notes = "用户名称" ,position = 0)
    private String username;

    @ApiModelProperty(value = "用户电话" ,position = 0)
    private String userPhone;
}
