package com.test.springbootplus2.manage.test.model.vo;

import io.github.wslxm.springbootplus2.common.annotation.XjSecret;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
/**
 * 测试返回加密
 * @author wangsong
 */
@Data
@ToString(callSuper = true)
public class EncryptVO implements Serializable {
    private static final long serialVersionUID = -1877788394466788429L;

    private String a;

    @XjSecret(type = 1)
    private String b;

    @XjSecret(isNext = true)
    private EncryptVO encrypt;

    @XjSecret(isNext = true)
    private List<EncryptVO> encrypts;
}
