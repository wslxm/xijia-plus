package com.test.springbootplus2.manage.test.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
  * excel测试对象
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2022/8/22 0022 20:52
  * @version 1.0.0
  */
@Data
@ToString(callSuper = true)
@ApiModel(value = "ExcelTestVO ", description = "excel测试对象")
public class ExcelTestVO {

    @ApiModelProperty(notes = "用户编号" ,position = 0)
    private String userNo;

    @ApiModelProperty(notes = "用户名称" ,position = 0)
    private String username;

    @ApiModelProperty(value = "用户电话" ,position = 0)
    private String userPhone;
}
