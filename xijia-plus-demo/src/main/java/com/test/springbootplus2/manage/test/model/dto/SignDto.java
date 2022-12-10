package com.test.springbootplus2.manage.test.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
/**
 * 验签测试
 * @author wangsong
 */
@Data
@ToString(callSuper = true)
public class SignDto implements Serializable {

    private static final long serialVersionUID = 879334186471238662L;
    private Integer a;
    private Integer b;
    private Integer d;
    private Integer c;
    @NotBlank
    private Integer e;
    private LocalDateTime a1;
    /**
     * 测试下级
     */
    private SignDto signDto;
    /**
     * 测试list下级
     */
    private List<SignDto> dtos;
}
