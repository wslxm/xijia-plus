package com.ws.ldy.modules.third.wechat.pay.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WxBaseResurtVO extends Convert {

    private static final long serialVersionUID = 2970116969189914480L;
    /**
     * 返回状态码.
     */
    protected String returnCode;
    /**
     * 返回信息.
     */
    protected String returnMsg;
    /**
     * 业务结果.
     */
    private String resultCode;
    /**
     * 错误代码.
     */
    private String errCode;
    /**
     * 错误代码描述.
     */
    private String errCodeDes;
    /**
     * 公众账号ID.
     */
    private String appid;
    /**
     * 商户号.
     */
    private String mchId;
    /**
     * 服务商模式下的子公众账号ID.
     */
    private String subAppId;
    /**
     * 服务商模式下的子商户号.
     */
    private String subMchId;
    /**
     * 随机字符串.
     */
    private String nonceStr;
}
