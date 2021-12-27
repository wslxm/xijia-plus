package io.github.wslxm.springbootplus2.starter.wechat.mp.entity;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *  @author wangsong
 * WX jssdk 使用签名参数
 */
@Data
@ToString
public class WxMpJsapiSignatureVO implements Serializable {
    private static final long serialVersionUID = -8479843356608399895L;

    private String appId;
    private String nonceStr;
    private Long timestamp;
    private String url;
    private String signature;
}
