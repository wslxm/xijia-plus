package com.test.springbootplus2.manage.test.model.dto;

import io.github.wslxm.springbootplus2.common.annotation.XjSecret;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
/**
 * 测试请求加密
 * @author wangsong
 */
@Data
@ToString(callSuper = true)
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
