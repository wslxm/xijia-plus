package com.ws.ldy.modules.yw.dto;


import com.ws.ldy.common.annotation.Encrypt;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString(callSuper = true)
@ApiModel(value = "EncryptDTO 对象", description = "测试加密")
public class EncryptDTO implements Serializable {
    private static final long serialVersionUID = -1877788394466788429L;

    @Encrypt
    private String a;
    @NotNull
    private String b;
}
