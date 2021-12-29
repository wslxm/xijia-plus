package io.github.wslxm.springbootplus2.starter.wechat.mp.entity;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wangsong
 */
@Data
@ToString
public class WxMpAccessTokenVO implements Serializable {

    private static final long serialVersionUID = 3017284676933857916L;
    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String openid;
    private String scope;

}
