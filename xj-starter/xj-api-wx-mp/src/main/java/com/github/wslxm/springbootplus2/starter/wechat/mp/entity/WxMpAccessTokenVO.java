package com.github.wslxm.springbootplus2.starter.wechat.mp.entity;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WxMpAccessTokenVO  implements Serializable {

    private static final long serialVersionUID = 3017284676933857916L;
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;

}
