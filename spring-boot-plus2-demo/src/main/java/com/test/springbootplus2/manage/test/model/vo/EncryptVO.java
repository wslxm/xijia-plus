package com.test.springbootplus2.manage.test.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
/**
 * @author wangsong
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "EncryptVO 对象", description = "测试返回加密")
public class EncryptVO implements Serializable {
    private static final long serialVersionUID = -1877788394466788429L;

    private String a;

    private String b;


    private EncryptVO encrypt;

    private List<EncryptVO> encrypts;
}
