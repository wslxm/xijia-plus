package io.github.wslxm.springbootplus2.starter.pay.model.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *  @author wangsong
 */
@Data
@ToString
public class WxBaseResurtVO implements Serializable {

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
