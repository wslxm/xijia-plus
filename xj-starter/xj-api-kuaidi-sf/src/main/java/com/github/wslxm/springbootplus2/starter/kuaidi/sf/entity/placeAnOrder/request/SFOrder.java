package com.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 顺丰下单请求参数
 * @author wangsong
 * @date 2020/9/23 0023 14:30
 * @return
 * @version 1.0.0
 */
@Data
@ToString
public class SFOrder implements Serializable {

    private static final long serialVersionUID = -7686972227370335902L;
    /**
     * 托寄物信息
     */
    private List<CargoDetails> cargoDetails;

    /**
     * 收寄双方信息
     */
    private List<ContactInfoList> contactInfoList;

    /**
     * 响应报文的语言，
     * 缺省值为zh-CN，
     * 目前支持以下值zh-CN
     * 表示中文简体，
     * zh-TW或zh-HK或
     * zh-MO表示中文繁体，
     * en表示英文
     *
     * 填写  zh_CN
     */
    private String language;

    /**
     * 客户订单号
     */
    private String orderId;

    /**
     * 是否通过手持终端
     * 通知顺丰收派
     * 员上门收件，支持以下值：
     * 1：要求 0：不要求
     *
     * 1-要求上门取 (并打印电子面单)
     */
    private Integer isDocall;


    /**
     * 付款方式，支持以下值：
     * 1:寄方付
     * 2:收方付
     * 3:第三方付
     *
     * 	Number(2) 3-使用第三方付
     */
    private Integer payMethod;


    /**
     * 顺丰月结卡号
     */
    private String monthlyCard;
    /**
     * 下单备注
     */
    private String remark;
}
