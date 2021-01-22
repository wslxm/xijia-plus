package com.ws.ldy.modules.third.qiniu.oss.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@ApiModel(value = "TokenAndUrlVO 对象", description = "七牛云前端直传需要数据")
public class TokenAndUrlVO implements Serializable {

    private static final long serialVersionUID = 8736268831471221864L;
    @ApiModelProperty(notes = "上传接口" ,position = 0)
    private String hosts;

    @ApiModelProperty(notes = "公共上传接口" ,position = 0)
    private String pubHosts;

    @ApiModelProperty(notes = "上传token" ,position = 0)
    private String token;
}
