package com.ws.ldy.modules.yw.test.model.dto;


import com.ws.ldy.common.annotation.XjSecret;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@ToString(callSuper = true)
@ApiModel(value = "EncryptDTO 对象", description = "测试请求加密")
public class EncryptDTO implements Serializable {
    private static final long serialVersionUID = -1877788394466788429L;

    @XjSecret
    private String a;
    @NotNull
    private String b;

    @XjSecret(isNext = true)
    private EncryptDTO encrypt;

    @XjSecret(isNext = true)
    private List<EncryptDTO> encrypts;
}
