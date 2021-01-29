package com.ws.ldy.modules.yw.pets.model.vo;


import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsDeclareFindHomePageVO 对象", description = "首页申报信息")
public class PetsDeclareFindHomePageVO extends Convert {


    private static final long serialVersionUID = -4311646320752303488L;
    @ApiModelProperty(notes = "申报id", position = 0)
    private String id;

    @ApiModelProperty(notes = "申请时间", position = 2)
    private LocalDateTime createTime;

    @ApiModelProperty(notes = "申报人姓名", position = 1)
    private String fullName;
}
